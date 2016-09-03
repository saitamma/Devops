<%-- serversLanSanPolicyConfig.jsp --%>
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
		dojo.require("xwt.widget.form.DropDown");
		dojo.require("xwt.widget.notification.ValidationTextBox");
		dojo.require("xwt.widget.notification.Form");

		var vNICTempValues=[],WWNNPoolValues = [];
		
		var vnicConfigDataResponse = ajaxCallGet("getLanVnicTemplateConfigDetails.html", true, "json");
		if(vnicConfigDataResponse){
			dojo.forEach(JSON.parse("["+vnicConfigDataResponse+"]"),function(eachVnic,i){
				vNICTempValues.push({value:eachVnic.id,	label:eachVnic.vnicName});
			});
		}
		var wwnnConfigDataResponse = ajaxCallGet("getSanWwnnConfigDetails.html", true, "json");
		if(vnicConfigDataResponse){
			dojo.forEach(JSON.parse("["+wwnnConfigDataResponse+"]"),function(eachWWNN,i){
				WWNNPoolValues.push({value:eachWWNN.id,	label:eachWWNN.wwnnName});
			});
		}
		
		var bootPolicyLanConfigDataResponse = ajaxCallGet("getBootPolicyLanConfigDetails.html", true, "json");
		var serversLanPolicyDataTable = { items:JSON.parse("[" + bootPolicyLanConfigDataResponse + "]") };
		
		var bootPolicySanConfigDataResponse = ajaxCallGet("getBootPolicySanConfigDetails.html", true, "json");
		var serversSanPolicyDataTable = { items:JSON.parse("[" + bootPolicySanConfigDataResponse + "]") };
		
var serversLanPolicyColumns = [
               {label: 'dbID',	attr: 'id',	hidden:true	},
               {label: 'Name',attr: 'name',sorted: 'ascending',width: 60,vAlignment: "middle",align:'center',editable: true,
            		editWidget:{
            			widget:xwt.widget.notification.ValidationTextBox,
            			options: {
								regExp:"^[a-zA-Z0-9-_]*$",
								required: true,
	                			trim: true,
	                			maxlength:"44",
	                			invalidMessage: "Only alphanumeric input allowed."
								}
            		}   
               },
               {label: 'Description',attr: 'description',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: true},
               {label: 'Order',attr: 'lanOrder',sorted: 'ascending',width: 40,vAlignment: "middle",align:'center'},
               {label: 'vNIC',attr: 'lanVnic',sorted: 'ascending',width: 70,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetVNICName,
					editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: vNICTempValues}
		                   	  }
               },
               {label: 'Type',attr: 'type',sorted: 'ascending',width: 60,vAlignment: "middle",align:'center'},
               {label: 'Organization',attr: 'organizations',width: 100,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
					editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: getOgranizationDropDown()}
		                   	  }
                  		}
               ];

var serversSanPolicyColumns = [
                               {label: 'dbID',	attr: 'id',	hidden:true	},
                               {label: 'Name',attr: 'name',sorted: 'ascending',width: 60,vAlignment: "middle",align:'center',editable: true,
                            		editWidget:{
                            			widget:xwt.widget.notification.ValidationTextBox,
                            			options: {
                								regExp:"^[a-zA-Z0-9-_]*$",
                								required: true,
                	                			trim: true,
                	                			maxlength:"44",
                	                			invalidMessage: "Only alphanumeric input allowed."
                								}
                            		}   
                               },
                               {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true},
                               {label: 'Order',attr: 'sanOrder',sorted: 'ascending',width: 50,vAlignment: "middle",align:'center'},
                               {label: 'vNIC',attr: 'lanVnic',sorted: 'ascending',width: 70,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetVNICName,
               					editWidget: {
               						widget: xwt.widget.form.DropDown,
               						options: {options: vNICTempValues}
               		                   	  }
                              	},
                               {label: 'Type',attr: 'type',sorted: 'ascending',width: 50,vAlignment: "middle",align:'center'},
                               {label: 'LUN',attr: 'lun',sorted: 'ascending',width: 50,vAlignment: "middle",align:'center',editable:true},
                               {label: 'WWNN',attr: 'wwnnId',sorted: 'ascending',width: 60,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetWWNNName,
               					editWidget: {
               						widget: xwt.widget.form.DropDown,
               						options: {options: WWNNPoolValues}
               		                   	  }
                               },
                               {label: 'Organization',attr: 'organizations',width: 90,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
                					editWidget: {
                						widget: xwt.widget.form.DropDown,
                						options: {options: getOgranizationDropDown()}
                		                   	  }
                                  		}
                               ];
  
function formatterGetVNICName(data, item, store){
	return returnFormatterDropDownLabel(vNICTempValues,data, item, store);
}
function formatterGetWWNNName(data, item, store){
	return returnFormatterDropDownLabel(WWNNPoolValues,data, item, store);
}
  function serversLanPolicyGenerateData(){
	  var formObject2 =  dojo.formToObject("serversLanPolicyTableForm");
		if(dijit.byId("serversLanPolicyTableForm").validate()==false || formObject2.noOfLanBootPolicy == ""){
			return false;
		}
		
	 	var selectedOrg = parseInt(formObject2.serversLanPolicyOrganization);
	 	var noOfLanBootPolicy = parseInt(formObject2.noOfLanBootPolicy);

	 	for(i = 1 ; i <= noOfLanBootPolicy ; i++){
	 		var serversLanPolicyDataGenreate = {
	 				"id":0,
	 				"name":	"Boot-P"+i,
	 				"description": "Boot-P"+i,
	 				"lanOrder": 1,
	 				"lanVnic":(vNICTempValues.length == 0)? null :vNICTempValues[0].id,
	 				"type": "Primary",
	 				"organizations":selectedOrg
	 			};
	 		serversLanPolicyTable.store.newItem(serversLanPolicyDataGenreate);
	 	}
	 	serversLanPolicyDataStoreTab.save();
	 	serversLanPolicyTable.refresh();
	 	
	 	dijit.byId("noOfLanBootPolicy").set("value","");
	 }
  function serversSanPolicyGenerateData(){
	  var formObject2 =  dojo.formToObject("serversSanPolicyTableForm");
		if(dijit.byId("serversSanPolicyTableForm").validate()==false || formObject2.noOfSanBootPolicy == ""){
			return false;
		}
		
	 	var selectedOrg = parseInt(formObject2.serversSanPolicyOrganization);
	 	var noOfSanBootPolicy = parseInt(formObject2.noOfSanBootPolicy);

	 	for(i = 1 ; i <= noOfSanBootPolicy ; i++){
	 		var serversSanPolicyDataGenreate = {
	 				"id":0,
	 				"name":	"Boot-P"+i,
	 				"description": "Boot-P"+i,
	 				"sanOrder": 1,
	 				"lanVnic":(vNICTempValues.length == 0)? null :vNICTempValues[0].id,
	 				"type": "Primary",
	 				"lun" : "lun",
	 				"wwnnId" : (WWNNPoolValues.length == 0)? null :WWNNPoolValues[0].id,
	 				"organizations":selectedOrg
	 			};
	 		serversSanPolicyTable.store.newItem(serversSanPolicyDataGenreate);
	 	}
	 	serversSanPolicyDataStoreTab.save();
	 	serversSanPolicyTable.refresh();
	 
	 	dijit.byId("noOfSanBootPolicy").set("value","");
	 }
  require(["dojo/ready", "dojo/_base/json"], 
			 function(ready, json){
		 	
		    	 setTimeout(function(){
		    		 
		    		 // ADD dropDown val for Organization //
		    		 	var selectOrganizationArr = getOgranizationDropDown();
		    		 	serversLanPolicyOrganization.addOption(selectOrganizationArr);
		    		 	serversSanPolicyOrganization.addOption(selectOrganizationArr);

		    		 	serversLanPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
			    			var serversLanPolicyTablejson = returnChangedDataFromDataStore(this,json);
			    			var response = ajaxCallPostWithJsonContent("manageBootPolicyLanConfig.html" , serversLanPolicyTablejson, null, "json");
			    			saveComplete();
			    			updateZeroIdsInDataStore(response, this);
				     	};
				     	
				     	serversSanPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
				    			var serversSanPolicyTablejson = returnChangedDataFromDataStore(this,json);
				    			var response = ajaxCallPostWithJsonContent("manageBootPolicySanConfig.html" , serversSanPolicyTablejson, null, "json");
				    			saveComplete();
				    			updateZeroIdsInDataStore(response, this);
					    };
				     	dojo.addOnLoad(function () {
				     		serversLanPolicyTable.validateRow = {
									errorMessage: "Please make sure entry is not duplicate.",
									isValid: function (oldvalues, newitem) {
									if(oldvalues.name != newitem.name){
										if(!checkTableFieldValueUnique(serversLanPolicyDataStoreTab,"name",newitem.name)){
											return false;
										}
									}
									return true;
								}
							};
				     		serversSanPolicyTable.validateRow = {
									errorMessage: "Please make sure entry is not duplicate.",
									isValid: function (oldvalues, newitem) {
									if(oldvalues.name != newitem.name){
										if(!checkTableFieldValueUnique(serversSanPolicyDataStoreTab,"name",newitem.name)){
											return false;
										}
									}
									return true;
								}
							};
						});

		    	 },1000);
		 
		     });

	// function for Save data to servere
	function saveServersLanSanPolicyConfigData(){
		 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":3});
			response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
			return true;
	}
 </script>
    </head>
    <body>
	    <div id="parentDiv">
			<div class="floatleft addCssIntreeTable">
				<fieldset style="height: 370px;width: 510px;margin: 0px 15px 0px 0px;">
					<legend>Boot Policy(LAN) Configuration</legend>
						<div class="serversLanSanPolicyConfig">
							<form id="serversLanPolicyTableForm" data-dojo-id="serversLanPolicyTableForm" data-dojo-type="xwt.widget.notification.Form" name="serversLanPolicyTableForm">
					        	<table>
					        		<tr>
					        		<td>
					        				<div class="labelspace">
												<label style="float:left;">Organizations</label>			
											</div>
					        			</td>
					        			<td>
					        				<select id="serversLanPolicyOrganization" data-dojo-id="serversLanPolicyOrganization" name="serversLanPolicyOrganization" data-dojo-type="xwt.widget.form.DropDown" style="width: 90px;" />
					        			</td>
					        			<td>
						        			<div class="labelspace">
												<label style="float:left;">Number of Boot Policy</label>			
											</div>
										</td>
					        			<td>
					        				<div id="noOfLanBootPolicy" name = "noOfLanBootPolicy"  style="width: 50px;" data-dojo-id="noOfLanBootPolicy" data-dojo-type="xwt.widget.notification.ValidationTextBox" regExp="^[0-9]*$" data-dojo-props='trim:"true", maxlength:"20", promptMessage:"", invalidMessage:"Only numeric input allowed."'></div>
					        			</td>
					        			<td style="padding-left: 10px;">
					        				<button data-dojo-type="dijit/form/Button" data-dojo-id="serversLanPolicyGenerateDataBtn" onClick="serversLanPolicyGenerateData();" type="button">Add</button>
					        			</td>
					        		</tr>
					        	</table>
						  	</form>
						</div>
				          <div class="floatleft" style="padding-left:5px;padding-top:30px;"> 
					          <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
						        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="serversLanPolicyDataStoreTab" data="serversLanPolicyDataTable"></span>
									<div style="width:500px !important;"  id="serversLanPolicyTableTollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="serversLanPolicyTable" quickFilter="false">
											<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
									</div>
									<div id="serversLanPolicyTable" 
										data-dojo-id="serversLanPolicyTable" 
										dojoType="xwt.widget.table.Table"
										store="serversLanPolicyDataStoreTab" 
										structure="serversLanPolicyColumns" 
										style="width: 500px; height: 200px;"
										selectMultiple="true" 
										selectAllOption="true" 
										showIndex="false" 
										selectModel="input"  
										filterOnServer=false></div>
								</div>
							</div>
					
				</fieldset>
			</div>
			<div class="floatleft">
				<fieldset style="height: 370px;width: 600px;margin: 0px;">
					<legend>Boot Policy(SAN) Configuration</legend>
						<div class="vnicTemplateConfig">
							<form id="serversSanPolicyTableForm" data-dojo-id="serversSanPolicyTableForm" data-dojo-type="xwt.widget.notification.Form" name="serversSanPolicyTableForm">
					        	<table>
					        		<tr>
					        		<td>
					        				<div class="labelspace">
												<label style="float:left;">Organizations</label>			
											</div>
					        			</td>
					        			<td>
					        				<select id="serversSanPolicyOrganization" data-dojo-id="serversSanPolicyOrganization" name="serversSanPolicyOrganization" data-dojo-type="xwt.widget.form.DropDown" style="width: 100px;" />
					        			</td>
					        			<td>
						        			<div class="labelspace">
												<label style="float:left;">Number of Boot Policy</label>			
											</div>
										</td>
					        			<td>
					        				<div id="noOfSanBootPolicy" name = "noOfSanBootPolicy"  style="width: 70px;" data-dojo-id="noOfSanBootPolicy" data-dojo-type="xwt.widget.notification.ValidationTextBox" regExp="^[0-9]*$" data-dojo-props='trim:"true", maxlength:"20", promptMessage:"", invalidMessage:"Only numeric input allowed."'></div>
					        			</td>
					        			<td style="padding-left: 10px;">
					        				<button data-dojo-type="dijit/form/Button" data-dojo-id="serversSanPolicyGenerateDataBtn" onClick="serversSanPolicyGenerateData();" type="button">Add</button>
					        			</td>
					        		</tr>
					        	</table>
						  	</form>
						</div>
			     		<div class="floatleft" style="padding-left:5px;padding-top:30px;"> 
					          <div dojotype="dijit.layout.ContentPane" region="left" style="width:590px; overflow:hidden;" splitter="true">
						        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="serversSanPolicyDataStoreTab" data="serversSanPolicyDataTable"></span>
									<div style="width:590px !important;"  id="serversSanPolicyTableTollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="serversSanPolicyTable" quickFilter="false">
											<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
									</div>
									<div id="serversSanPolicyTable" 
										data-dojo-id="serversSanPolicyTable" 
										dojoType="xwt.widget.table.Table"
										store="serversSanPolicyDataStoreTab" 
										structure="serversSanPolicyColumns" 
										style="width: 590px; height: 200px;"
										selectMultiple="true" 
										selectAllOption="true" 
										showIndex="false" 
										selectModel="input" 
										filterOnServer=false></div>
								</div>
							</div>
					</fieldset>
				</div>
		</div>
	</body>
