<%-- serversServerPoolAndQualifierConfig.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
			dojo.require("xwt.widget.table.Table");
			dojo.require("xwt.widget.table.Toolbar");
			dojo.require("dojo.data.ItemFileReadStore");
			dojo.require("dijit.form.Button");
			dojo.require("dijit.form.Form");
			dojo.require("xwt.widget.notification.ValidationTextBox");
			dojo.require("xwt.widget.notification.Form");

			var serverPoolMax = 0, serverPoolQualifierMax = 0; 
			var serverPoolConfigDataResponse = ajaxCallGet("getServerPoolConfigDetails.html", true, "json");
			var serversServerPoolDataTable = { items:JSON.parse("[" + serverPoolConfigDataResponse + "]") };
			
			var serverPoolQualifierConfigDataResponse = ajaxCallGet("getServerPoolQualifierConfigDetails.html", true, "json");
			var serversServerQualifierDataTable = { items:JSON.parse("[" + serverPoolQualifierConfigDataResponse + "]") };
			
			// Calculating the max used ServersPool name
			var count = serverPoolConfigDataResponse.length;
			if(count > 0){
				var lastNameUsed = JSON.parse(serverPoolConfigDataResponse[count-1]).name;
				serverPoolMax = extractNumericValueFromAlphaNumericString(lastNameUsed);
			}
			
			// Calculating the max used ServersPool Qualifier name
			var localDiscMax = 0; 
			var count2 = serverPoolQualifierConfigDataResponse.length;
			if(count2 > 0){
				var lastNameUsed = JSON.parse(serverPoolQualifierConfigDataResponse[count2-1]).name;
				serverPoolQualifierMax = extractNumericValueFromAlphaNumericString(lastNameUsed);
			}
var serversServerQualifierColumns = [
                                     {label: 'dbID',	attr: 'id',	hidden:true	},
                                     {label: 'Name',attr: 'name',sorted: 'ascending',width: 80,vAlignment: "middle",align:'center',editable: true,
                                  		editWidget:{
                                  			widget:xwt.widget.notification.ValidationTextBox,
                                  			options: {
                   								regExp:REG_EX_NAME,
                   								required: true,
                   	                			trim: true,
                   	                			maxlength:"16",
                   	                			invalidMessage: MSG_NAME
                   							}
                                  		}   
                                     },
                                     {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
                                    	 editWidget:{
                                    			widget:xwt.widget.notification.ValidationTextBox,
                                    			options: {
                      	                			trim: true,
                      	                			maxlength:"45",
                      							}
                                    		}	 
                                     },
                                     {label: 'Chassis<br />Min Id',attr: 'chassisMinId',sorted: 'ascending',width: 80,vAlignment: "middle",align:'center',editable: true,
            	                    	   editWidget: {
             		                       		widget:xwt.widget.notification.ValidationTextBox,
             		       							options: {
             		       								regExp: REG_EX_NUMBER_1TO255,
             		       								required: true,
             		       	                			trim: true,
             		       	                			maxlength:"2",
             		       								invalidMessage: MSG_UPTO255
             		       								}
             		                       			}
                                     },
                                     {label: 'Chassis<br />Max Id',attr: 'chassisMaxId',sorted: 'ascending',width: 80, vAlignment: "middle",align:'center',editable: true,
          	                    	   editWidget: {
          		                       		widget:xwt.widget.notification.ValidationTextBox,
          		       							options: {
          		       								regExp: REG_EX_NUMBER_1TO255,
          		       								required: true,
          		       	                			trim: true,
          		       	                			maxlength:"2",
          		       								invalidMessage: MSG_UPTO255
          		       								}
          		                       			}
                                     },
                                     /* {label: 'Slot<br />min id',attr: 'rackMinId',sorted: 'ascending',width: 70,vAlignment: "middle",align:'center',editable: true,
          	                    	   editWidget: {
           		                       		widget:xwt.widget.notification.ValidationTextBox,
           		       							options: {
           		       								regExp:REG_EX_NUMBER_1_TO_8,
           		       								required: true,
           		       	                			trim: true,
           		       	                			maxlength:"1",
           		       								invalidMessage: MSG_BET_1_8
           		       								}
           		                       			}
                                   },
                                   {label: 'Slot<br />max id',attr: 'rackMaxId',sorted: 'ascending',width: 70,vAlignment: "middle",align:'center',editable: true,
        	                    	   editWidget: {
        		                       		widget:xwt.widget.notification.ValidationTextBox,
        		       							options: {
        		       								regExp:REG_EX_NUMBER_1_TO_8,
        		       								required: true,
        		       	                			trim: true,
        		       	                			maxlength:"1",
        		       								invalidMessage: MSG_BET_1_8
        		       								}
        		                       			}
                                   }, */
                                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
                    					editWidget: {
                    						widget: xwt.widget.form.DropDown,
                    						options: {options: getOgranizationDropDown()}
                    		                   	  }
                                      	},
                                    {label: 'Slot Configure',attr: 'configure',width: 120,vAlignment: "middle",align:'center',formatter: formatterConfigureSlots}
                                     ];
			                         

			
var serversServerPoolColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 80,vAlignment: "middle",align:'center',editable: true,
                		editWidget:{
                			widget:xwt.widget.notification.ValidationTextBox,
                			options: {
  								regExp:REG_EX_NAME,
  								required: true,
  	                			trim: true,
  	                			maxlength:"16",
  	                			invalidMessage: MSG_NAME
  								}
                		}   
                   },
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 	                			trim: true,
 	                			maxlength:"45",
 							}
               			}   
                   },
                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: getOgranizationDropDown()}
   		                   	  }
                      		}
                   ];		

var serversSlotsColumns = [
                           {label: 'dbID',      attr: 'id',   hidden:true   },
                           {label: "spqId", attr: "serversServerPoolQualifier", hidden:true},
                           {label: 'Min Id',attr: 'minId',sorted: 'ascending',width: 220,vAlignment: "middle",align:'center',editable: true,
                             editWidget: {
                                          widget:xwt.widget.notification.ValidationTextBox,
                                         options: {
                                             regExp:REG_EX_NUMBER_1_TO_8,
                                             required: true,
                                             trim: true,
                                             maxlength:"1",
                                             invalidMessage: MSG_BET_1_8
                                             }
                                        }
                           },
                           {label: 'Max Id',attr: 'maxId',sorted: 'ascending',width: 220,vAlignment: "middle",align:'center',editable: true,
                              editWidget: {
                                         widget:xwt.widget.notification.ValidationTextBox,
                                          options: {
                                          regExp:REG_EX_NUMBER_1_TO_8,
                                          required: true,
                                          trim: true,
                                          maxlength:"1",
                                          invalidMessage: MSG_BET_1_8
                                        }
                                    }
                           }
                           ];

function serversServerQualifierGenerateData(){
	  var formObject2 =  dojo.formToObject("serversServerQualifierTableForm");
		if(dijit.byId("serversServerQualifierTableForm").validate()==false || formObject2.noOfServerQualifier == ""){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		
	 	var selectedOrg = parseInt(formObject2.serversServerQualifierOrganization);
	 	var noOfServerQualifier = parseInt(formObject2.noOfServerQualifier);

	 	for(i = 1 ; i <= noOfServerQualifier ; i++){
	 		serverPoolQualifierMax++;
			var qualifierName = "SerPool_Q"+serverPoolQualifierMax;
	 		if(!checkTableFieldValueUnique(serversServerQualifierDataStoreTab,"name",qualifierName)){
	 			continue;
	 		}
	 		var serversServerQualifierDataGenreate = {
	 				"id":0,
	 				"name":	qualifierName,
	 				"description": "SP Qualifier "+serverPoolQualifierMax,
	 				"chassisMinId":1,
	 				"chassisMaxId": 1,
	 				//"rackMinId":1,
	 				//"rackMaxId": 1,
	 				"organizations":selectedOrg
	 			};
	 		serversServerQualifierTable.store.newItem(serversServerQualifierDataGenreate);
	 	}
	 	serversServerQualifierDataStoreTab.save();
	 	serversServerQualifierTable.refresh();
	 	
	 }

function serversServerPoolGenerateData(){
	var formObject2 =  dojo.formToObject("serversServerPoolTableForm");
	if(dijit.byId("serversServerPoolTableForm").validate()==false || formObject2.serversNoOfServerPool == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	
 	var selectedOrg = parseInt(formObject2.serversServerPoolOrganization);
 	var serversNoOfServerPool = parseInt(formObject2.serversNoOfServerPool);

 	for(i = 1 ; i <= serversNoOfServerPool ; i++){
 		serverPoolMax++;
		var serverPoolName = "SerPool"+serverPoolMax;
 		if(!checkTableFieldValueUnique(serversServerPoolDataStoreTab,"name",serverPoolName)){
 			continue;
 		}
 		var serversServerPoolDataGenreate = {
 				"id":0,
 				"name":	serverPoolName,
 				"description": "Server Pool "+serverPoolMax,
 				"organizations":selectedOrg
 			};
 		serversServerPoolTable.store.newItem(serversServerPoolDataGenreate);
 	}
 	serversServerPoolDataStoreTab.save();
 	serversServerPoolTable.refresh();
 	
 }
 
var serversSlotsDataTable = {items:[]};
function configureSlots(e, qualifierId){
	dojo.stopEvent(e);
	dijit.byId("serverQualifierId").set("value",qualifierId);
	dijit.byId("slotMinId").set("value","");
	dijit.byId("slotMaxId").set("value","");
	var loadDataFromDBForSlots = { items:JSON.parse("[" + ajaxCallPostWithJsonContent("getSPQSlotMappingConfigDetails.html",parseInt(qualifierId), true, "json") + "]") };
	
	serversSlotsDataStoreTab.clearOnClose = true;
	serversSlotsDataStoreTab.data = loadDataFromDBForSlots; 
	serversSlotsDataStoreTab.close();
	serversSlotsTable.closeEditor();
	serversSlotsTable.refresh();
	
	
	dojo.style(serversSlotsConfigureDialog.buttonGroup.getItemAt(0).get("domNode"), "display", "none"); // Hide Cancel button 
	serversSlotsConfigureDialog.show();
}
function serversSlotsGenerateData(){
	var formObject2 =  dojo.formToObject("serversSlotsTableForm");

	if(dijit.byId("serversSlotsTableForm").validate()==false || formObject2.slotMinId == "" || formObject2.slotMaxId == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	else if(parseInt(formObject2.slotMinId) > parseInt(formObject2.slotMaxId) ){
		displayNotificationAlert(MSG_MINID_MAXID_GREATER);
		return false;
	}
	else{
		if(!checkForDuplicateSlotMinMaxIdRows(serversSlotsDataStoreTab,{minId: formObject2.slotMinId ,maxId: formObject2.slotMaxId })){
			displayNotificationAlert(MSG_DUPLICATE_ENTRY);
			return false;
		}
	}
	var serversSlotsDefItem = {
			"id":0,
			"minId": formObject2.slotMinId,
			"maxId": formObject2.slotMaxId,
			"serversServerPoolQualifier": parseInt(formObject2.serverQualifierId)
			};
	serversSlotsTable.store.newItem(serversSlotsDefItem);
	serversSlotsDataStoreTab.save();
	serversSlotsTable.refresh();
	dijit.byId("slotMaxId").set("value","");
	dijit.byId("slotMinId").set("value","");

}
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 
	    		 // ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	serversServerQualifierOrganization.addOption(selectOrganizationArr);
	    		 	serversServerPoolOrganization.addOption(selectOrganizationArr);

	    		 	serversServerPoolDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversServerPoolTablejson = returnChangedDataFromDataStore(this,json);
		    			var response = ajaxCallPostWithJsonContent("manageServerPoolConfig.html" , serversServerPoolTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
			     	};
			     	
				 	serversServerQualifierDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversServerQualifierTablejson = returnChangedDataFromDataStore(this,json);
		    			var response = ajaxCallPostWithJsonContent("manageServerPoolQualifierConfig.html" , serversServerQualifierTablejson, null, "json");
		    			saveComplete();
	    				updateZeroIdsInDataStore(response, this);
			     	};
			     	
			     	serversSlotsDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversServerQualifierSlotTablejson = returnChangedDataFromDataStore(this,json);
		    			var response = ajaxCallPostWithJsonContent("manageServerPoolQualifierSlotConfig.html" , serversServerQualifierSlotTablejson, null, "json");
		    			saveComplete();
	    				updateZeroIdsInDataStore(response, this);
			     	};
		
			     	
			     	dojo.addOnLoad(function () {
			     		serversServerPoolTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversServerPoolDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
								return true;
							}
						};
				    	serversServerQualifierTable.validateRow = {
								isValid: function (oldvalues, newitem) {
									if(oldvalues.name != newitem.name){
										if(!checkTableFieldValueUnique(serversServerQualifierDataStoreTab,"name",newitem.name)){
											this.errorMessage = MSG_DUPLICATE_NAME;
											return false;
										}
									}
									if(newitem.chassisMinId > newitem.chassisMaxId){
										this.errorMessage = MSG_MINID_MAXID_GREATER;
										return false;
									}
								return true;
							}
						};
				    	
				    	serversSlotsTable.validateRow = {
								errorMessage: MSG_MINID_MAXID_GREATER,
								isValid: function (oldvalues, newitem) {
									if(parseInt(newitem.minId) > parseInt(newitem.maxId)){
										return false;
									}
									else if(parseInt(oldvalues.minId) != parseInt(newitem.minId) || parseInt(oldvalues.maxId) != parseInt(newitem.maxId) ){
										return checkForDuplicateSlotMinMaxIdRows(serversSlotsDataStoreTab,{minId: newitem.minId,maxId:newitem.maxId});
									}
								return true;
							}
						};
				    	
					});

	    	 },1000);
	 
	     });

// function for Save data to servere
function saveServersServerPoolConfigData(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":4});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	 console.log("LocalDiskPolicy Save");
}


</script>
</head>
<body class="prime">
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 370px;">
				<legend>Server Pool Configuration</legend>

				<div class="serverpoolQualifierConfig">
					<form id="serversServerPoolTableForm"
						data-dojo-id="serversServerPoolTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr style="height: 30px;">
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversServerPoolOrganization"
									name="serversServerPoolOrganization"
									data-dojo-id="serversServerPoolOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 90px" /></td>
							</tr>
							<tr>
								<td align="right">
									<div class="labelspace">
										<label style="float: left;">Number Of Server Pools:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="serversNoOfServerPool" name="serversNoOfServerPool"
										data-dojo-id="serversNoOfServerPool"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 72px;"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversServerPoolGenerateDataBtn"
										onClick="serversServerPoolGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft" style="padding-left: 5px; padding-top: 20px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 360px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversServerPoolDataStoreTab"
							data="serversServerPoolDataTable"></span>
						<div style="width: 358px !important;"
							id="serversServerPoolTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversServerPoolTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversServerPoolTable"
							data-dojo-id="serversServerPoolTable"
							dojoType="xwt.widget.table.Table"
							store="serversServerPoolDataStoreTab"
							structure="serversServerPoolColumns"
							style="width: 355px; height: 215px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>
		<div class="floatleft">
			<fieldset class="heightOfFieldset" style="margin-left: 10px;width: 660px;">
				<legend>Server Pool Qualifier Configuration</legend>
				<div class="serversLanSanPolicyConfig">
					<form id="serversServerQualifierTableForm"
						data-dojo-id="serversServerQualifierTableForm"
						data-dojo-type="xwt.widget.notification.Form"
						name="serversServerQualifierTableForm">
						<table style="width: 530px">
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversServerQualifierOrganization"
									data-dojo-id="serversServerQualifierOrganization"
									name="serversServerQualifierOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" 
									style="width: 90px;" />
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of Qualifiers:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfServerQualifier" name="noOfServerQualifier"
										style="width: 50px;" data-dojo-id="noOfServerQualifier"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", promptMessage:"", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversServerQualifierGenerateDataBtn"
										onClick="serversServerQualifierGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft" id="addIdForChangeTableHeaderHeight" style="padding-left: 0px; padding-top: 20px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 660px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversServerQualifierDataStoreTab"
							data="serversServerQualifierDataTable"></span>
						<div style="width: 660px !important;"
							id="serversServerQualifierTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversServerQualifierTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversServerQualifierTable"
							data-dojo-id="serversServerQualifierTable"
							dojoType="xwt.widget.table.Table"
							store="serversServerQualifierDataStoreTab"
							structure="serversServerQualifierColumns"
							style="width: 660px; height: 245px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>

			</fieldset>
		</div>
	</div>

	<div id="serversSlotsConfigureDialog" button2Label="Close"
              data-dojo-id="serversSlotsConfigureDialog" title=""
              dojoType="xwt.widget.layout.Dialog" closable="true"
              style="display: none;">
              <div style="width: 60rem; height: 35rem;">
                     <fieldset style="height: 350px; width: 585px; margin: 0px; margin-bottom: 0px;">
                           <legend>Configure Qualifier Slots</legend>

                           <div class="serverSlotsConfig">
                                  <form id="serversSlotsTableForm" data-dojo-id="serversSlotsTableForm"  data-dojo-type="xwt.widget.notification.Form" name="tableForm">
                                   <input id="serverQualifierId" name="serverQualifierId" data-dojo-id="serverQualifierId" type="hidden" dojoType="dijit.form.TextBox"></input>
                                         <table style="width: 400px; margin: auto;">
                                                <tr style="height: 30px;">
                                                       <td>
                                                              <div class="labelspace">
                                                                     <label style="float: center;">Min Id:<em>*</em></label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                              <div id="slotMinId" name="slotMinId" style="width:50px;"data-dojo-id="slotMinId"
                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_8,trim:"true", maxlength:"1", invalidMessage:MSG_BET_1_8'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                       </td>
                                                       <td>
                                                              <div class="labelspace">
                                                                     <label style="float: left;">Max Id:<em>*</em></label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                              <div id="slotMaxId" name="slotMaxId" style="width:50px;"
                                                                     data-dojo-id="slotMaxId"
                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_8,trim:"true", maxlength:"1", invalidMessage:MSG_BET_1_8'></div>
                                                       </td>
                                                       <td style="padding-left: 10px;">
                                                              <button data-dojo-type="dijit/form/Button"
                                                                     data-dojo-id="serversSlotsGenerateDataBtn"
                                                                     onClick="serversSlotsGenerateData();" type="button">Add</button>
                                                       </td>
                                                </tr>
                                         </table>
                                  </form>
                           </div>
                           <div class="floatleft" style="padding-left: 7px; padding-top: 20px;">
                                  <div dojotype="dijit.layout.ContentPane" region="left"
                                         style="width: 570px; overflow: hidden;" splitter="true">
                                         <span dojoType="dojo.data.ItemFileWriteStore"
                                                jsId="serversSlotsDataStoreTab"
                                                data="serversSlotsDataTable"></span>
                                         <div style="width: 570px !important;"
                                                id="serversSlotsTableTollBar"
                                                dojoType="xwt.widget.table.ContextualToolbar"
                                                tableId="serversSlotsTable" quickFilter="false">
                                                <div dojoType="xwt.widget.table.ContextualButtonGroup"
                                                       showButtons="delete"></div>
                                         </div>
                                         <div id="serversSlotsTable"
                                                data-dojo-id="serversSlotsTable"
                                                dojoType="xwt.widget.table.Table"
                                                store="serversSlotsDataStoreTab"
                                                structure="serversSlotsColumns"
                                                style="width: 570px; height: 220px;" selectMultiple="true"
                                                selectAllOption="true" showIndex="false" selectModel="input"
                                                filterOnServer=false></div>
                                  </div>
                           </div>
                     </fieldset>                
              </div>

       </div>
	
	
	
	
</body>
</html>