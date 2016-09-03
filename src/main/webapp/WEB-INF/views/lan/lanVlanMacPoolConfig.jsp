<%-- lanVlanMacPoolConfig.jsp --%>
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
			dojo.require("xwt.widget.form.DropDown");
			dojo.require("xwt.widget.form.UnifiedIPAddress");
			
			var macpoolNameInt = 0; 
			var assignmentOrderList = [{label:"Default",value:"default"},{label:"Sequential",value:"sequential"}];
			
			var vsanConfigDataResponse = JSON.parse("[" + ajaxCallGet("getSanVSanConfigDetails.html", true, "json") +"]");
			
			var vlanConfigDataResponse = ajaxCallGet("getLanVlanConfigDetails.html", true, "json");
			var vlanDataTable1 = { items:JSON.parse("[" + vlanConfigDataResponse + "]") };
			var macpoolConfigDataResponse = ajaxCallGet("getLanMacpoolConfigDetails.html", true, "json");
			var macPoolDataTable2 = { items:JSON.parse("[" + macpoolConfigDataResponse + "]") };

			// Calculating the max used MACPool name
			if(macpoolConfigDataResponse.length > 0){
				macpoolNameInt = extractNumericValueFromAlphaNumericString(JSON.parse(macpoolConfigDataResponse[ (macpoolConfigDataResponse.length) -1]).macpoolName);
			}
			
			var vlanColumns = [
                {label: 'dbID',	attr: 'id',	hidden:true	},
                {label: 'Name',attr: 'vlanName',sorted: 'ascending',width: 100,vAlignment: "middle",editable: false,align:'center',editable: true,
                	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
                		options: {
                			trim: true,
                			regExp:REG_EX_NAME,
                			required: true,
                			maxlength:"16",
                			invalidMessage: MSG_NAME
    					}
      				}	
                },
                {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
                	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
                		options: {
                			trim: true,
                			maxlength:"45",
    					}
      				}	
                },
                {label: 'VLAN Id',attr: 'vlanId',width: 90, vAlignment: "middle",align:'center',editable: true,
                	editWidget: {
                		widget:xwt.widget.notification.ValidationTextBox,
							options: {
								regExp: REG_EX_NUMBER_1_TO_3967_OR_4048_TO_4093,
								required: true,
	                			trim: true,
	                			maxlength:"4",
								invalidMessage: MSG_BET_1_TO_3967_OR_4048_TO_4093
								}
                			}
                	}
                ]; 
			var macPoolColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'macpoolName',sorted: 'ascending',width: 85,vAlignment: "middle",align:'center',editable: true,
                	   editWidget: {widget:xwt.widget.notification.ValidationTextBox,
	                   		options: {
	                   			trim: true,
	                   			regExp:REG_EX_NAME,
	                			required: true,
	                			maxlength:"16",
	                			invalidMessage: MSG_NAME
	       					}
         				}   
                   },
                   {label: 'Description',attr: 'macpoolDescription',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
                	   editWidget: {widget:xwt.widget.notification.ValidationTextBox,
	                   		options: {
	                   			trim: true,
	                   			maxlength:"45",
	       					}
        				}   
                   },
                   {label: 'From Address',attr: 'fromAddress',sorted: 'ascending',width: 115,vAlignment: "middle",align:'center',editable: true,
                	  editWidget: {
                   		widget:xwt.widget.notification.ValidationTextBox,
   							options: {
   								regExp:REG_EX_MAC_ADDRESS,
   								required: true,
   	                			trim: true,
   								invalidMessage:MSG_MACADDRESS,
   								id:"tableRowMACFromAddress"
   								}
                   			} 
                   },
                   {label: 'To Address',attr: 'toAddress',sorted: 'ascending',width: 105,vAlignment: "middle",align:'center',editable: true,
                	   editWidget: {
                      		widget:xwt.widget.notification.ValidationTextBox,
      							options: {
      								regExp:REG_EX_MAC_ADDRESS,
      								required: true,
      	                			trim: true,
      								invalidMessage:MSG_MACADDRESS,
      								id:"tableRowMACToAddress"
      								}
                      			}
                   },
                   {label: 'Assignment<br/>Order',attr: 'assignmentOrder',width: 100,vAlignment: "middle",align:'center',editable: true,formatter : formatterGetAssignOrderName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: assignmentOrderList}
   		                   	  }
                    },
                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
					editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: getOgranizationDropDown()}
		                   	  }
                   		}
                   ];		

function formatterGetAssignOrderName(data, item, store){
	return returnFormatterDropDownLabel(assignmentOrderList,data, item, store);
}
function macPoolGenerateData(){
	var formObject2 =  dojo.formToObject("macPoolTableForm");
	if(dijit.byId("macPoolTableForm").validate()==false || formObject2.noOfMacPool == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
 	var selectedOrg = parseInt(formObject2.macPoolSelectOrg);
 	var noOfMacPool = parseInt(formObject2.noOfMacPool);
 	
 	var macPoolExistList = "";
 	var macPoolName = "MAC"+ (++macpoolNameInt);
	if(checkTableFieldValueUnique(macPoolDataStoreTab2,"macpoolName",macPoolName)){
		var macPoolDataTable2Genreate = {
			"id":0,
			"macpoolName":	macPoolName,
			"macpoolDescription": macPoolName,
			"fromAddress": formObject2.fromAddress,
			"toAddress":0,
			"noOfAddresses":noOfMacPool,
			"assignmentOrder":formObject2.assignmentOrder,
			"organizations":selectedOrg
		};
	macPoolConfigTable2.store.newItem(macPoolDataTable2Genreate);
	}
		
 	if(macPoolExistList != ""){
 		displayNotificationAlert("MACPool Names: "+macPoolExistList+" already exists.");
 	}
 	macPoolDataStoreTab2.save();
 	macPoolConfigTable2.refresh();
 	dijit.byId("noOfMacPool").set("value","");
 	
 }
function openRowEditingMode(item, parent){
	dijit.byId("macPoolConfigTable2").edit(item);
}
function deleteAllDataFromVLANTable(deleteDataStoreObj){
	deleteDataStoreObj.fetch({
		onComplete: function(items, request){
			for(var i = 0; i < items.length; i++){
				if( (items[i].vlanDefault[0] == false || items[i].vlanDefault == 0) ){
					deleteDataStoreObj.deleteItem(items[i]);
				}
	        }
			
		}
  	});
	deleteDataStoreObj.save();
}

function vlanGenerateData(){
	var formObject2 =  dojo.formToObject("vlanTableForm");
	if(dijit.byId("vlanTableForm").validate()==false || formObject2.vlanStartinId == "" || formObject2.noOfVlan == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	var isGenrateData = true;
 	var vlanStartId = parseInt(formObject2.vlanStartinId);
 	var noOfVlan = parseInt(formObject2.noOfVlan);
 	
 	for(var i = vlanStartId ; i < (vlanStartId + noOfVlan) ; i++){
 		if(checkTableFieldValueUnique(vlanDataStoreTab1,"vlanName","vlan"+ i) == false){
 			isGenrateData = false; 
 			break;
 		}
 		else if(checkTableFieldValueUnique(vlanDataStoreTab1,"vlanId", ""+i) == false){
 			isGenrateData = false; 
 			break;
 		}else{
 			dojo.forEach(vsanConfigDataResponse,function(data,indx){
 				 if(parseInt(data.fcoeVlanName) == i){
 					isGenrateData = false;
 				 }
 			 });
 		}
 	}
 	if(isGenrateData == false){
 		displayNotificationAlert(MSG_VLAN_GENERATE_UNIQUE);
 		return false;
 	}
 	for(var i = vlanStartId ; i < (vlanStartId + noOfVlan) ; i++){
 		var defItemx = {
					"id":0,
					"vlanName": "vlan"+ i,
					"description": "vlan"+ i,
					"vlanId": ""+i,
					"vlanDefault":0
		};
 		vlanConfigTable1.store.newItem(defItemx);
 	}
 	vlanDataStoreTab1.save();
 	vlanConfigTable1.refresh();
 	dijit.byId("vlanStartinId").set("value","");
 	dijit.byId("noOfVlan").set("value","");
}
 
require(["dojo/ready", "dojo/_base/json"], 
  		 function(ready, json){
  	 	
	    	 setTimeout(function(){
	    		 
	    		 dojo.connect(fromAddress,"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("fromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("tableRowMACFromAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("tableRowMACFromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("tableRowMACToAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("tableRowMACToAddress"));
	    		 });
	    		 // ADD dropDown val for Organization //@Parameter is option JSId
	    		 var selectOrganizationArr = getOgranizationDropDown();
	    		 macPoolSelectOrg.addOption(selectOrganizationArr);
	    		 
	    		 
	    		 vlanDataStoreTab1._saveCustom = function(saveComplete, saveFailed){
	    			 var vlanTable1json = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("manageLanVlanConfig.html" , vlanTable1json, null, "json");
	    			 saveComplete();
	    			 updateZeroIdsInDataStore(response, this);
			     };
			     
			     macPoolDataStoreTab2._saveCustom = function(saveComplete, saveFailed){
	    			 var macPoolTable2json = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("manageLanMacpoolConfig.html" , macPoolTable2json, null, "json");
	    			 saveComplete();
	    			 updateZeroIdsInDataStore(response, this);
	    			 updateToAddressInDataStore(response, this);
			     };
			     
		    	 vlanConfigTable1.validateRow = {
						isValid: function (oldvalues, newitem) {
							if(oldvalues.vlanName != newitem.vlanName){
								if(oldvalues.vlanDefault[0] == true ){
									this.errorMessage = MSG_CANT_CHANGE_DEFAULT;
									return false;
					    		 }
								else if(!checkTableFieldValueUnique(vlanDataStoreTab1,"vlanName",newitem.vlanName)){
									this.errorMessage = MSG_DUPLICATE_NAME;
									return false;
								}
							}
							else if(oldvalues.vlanId != newitem.vlanId){
								if(!checkTableFieldValueUnique(vlanDataStoreTab1,"vlanId",newitem.vlanId)){
									this.errorMessage = MSG_DUPLICATE_NAME;
									return false;
								}
							}
						
						return true;
					}
				 };
		    	 macPoolConfigTable2.validateRow = {
						errorMessage: MSG_DUPLICATE_NAME,
						isValid: function (oldvalues, newitem) {
						if(oldvalues.macpoolName != newitem.macpoolName){
							if(!checkTableFieldValueUnique(macPoolDataStoreTab2,"macpoolName",newitem.macpoolName)){
								return false;
							}
						}
						return true;
					}
				};
				


	    	 dojo.connect(dijit.byId("vlanConfigTable1"),"onSelect",function(item, row){
	    		 if(item.vlanDefault[0] == true){
	    			 this.deselect(item);
	    		 }
	    	 });
	    	 
	    	 },1000);
  	 
	     });

//Save data in back-end
function saveVlanMacPoolConfData(){
	
	if( getDataStoreSize(vlanDataStoreTab1) > 0 && getDataStoreSize(macPoolDataStoreTab2) > 0){
		var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":2});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	}else{
		displayNotificationAlert(MSG_VLAN_MAC_ONE_REQUIRED);
		return false;
	}
}

</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 380px;">
				<legend>VLAN</legend>
					<div class="vnicTemplateConfig" style="padding-top: 0px;">
							<form id="vlanTableForm" data-dojo-id="vlanTableForm" data-dojo-type="xwt.widget.notification.Form" name="tableForm">
								<table>
								<tr>
									<td style="height: 40px;">
										<div class="labelspace">
											<label style="float:left;">VLAN starting Id:<em>*</em></label>			
										</div>
									</td>
									<td colspan="2">
										<div id="vlanStartinId" name = "vlanStartinId" data-dojo-id="vlanStartinId" style="width:50px;" data-dojo-type="xwt.widget.notification.ValidationTextBox" data-dojo-props='regExp:REG_EX_NUMBER_1_TO_3967_OR_4048_TO_4093, trim:"true", maxlength:"4", invalidMessage:MSG_BET_1_TO_3967_OR_4048_TO_4093'></div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float:left;">Number Of VLANs:<em>*</em></label>			
										</div></td>
									<td>
										<div id="noOfVlan" name="noOfVlan" data-dojo-id="noOfVlan" style="width:50px;" data-dojo-type="xwt.widget.notification.ValidationTextBox" data-dojo-props='regExp:REG_EX_NUMBER, trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button" data-dojo-id="generateVlanBtn" onClick="vlanGenerateData();" type="button">Add</button>
									</td>
								</tr>
								
							</table>
						</form>
					</div>
			          <div class="floatleft" style="padding-left:5px;padding-top:20px;"> 
				          <div dojotype="dijit.layout.ContentPane" region="left" style="width:373px; overflow:hidden;" splitter="true">
					        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="vlanDataStoreTab1" data="vlanDataTable1"></span>
								<div style="width:370px !important;"  id="vlanTable1TollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="vlanConfigTable1" quickFilter="false">
										<div  id="vlanButtonGroupId" dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
								</div>
								<div id="vlanConfigTable1" 
									data-dojo-id="vlanConfigTable1" 
									dojoType="xwt.widget.table.Table"
									store="vlanDataStoreTab1" 
									structure="vlanColumns" 
									style="width: 370px; height: 215px;"
									selectMultiple="true" 
									selectAllOption="true" 
									selectModel="input"
									filterOnServer=false></div>
							</div>
						</div>
				
			</fieldset>
		</div>
		<div class="floatleft">
			<fieldset class="heightOfFieldset" style="width: 700px;margin-left: 10px;">
				<legend>MAC Pool</legend>
					<div class="vnicTemplateConfig" style="padding-top:0px; ">
						<form id="macPoolTableForm" data-dojo-id="macPoolTableForm" data-dojo-type="xwt.widget.notification.Form" name="tableForm">
				        	<table style="width: 690px;">
				        		<tr>
				        			<td style="height: 40px;">
				        				<div class="labelspace">
											<label style="float:left;">Organization:</label>			
										</div>
				        			</td>
				        			<td>
				        				<select id="macPoolSelectOrg" data-dojo-id="macPoolSelectOrg" name="macPoolSelectOrg" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 134px" />
				        			</td>
				        			<td style="height: 40px;">
				        				<div class="labelspace">
											<label style="float:left;">Assignment Order:</label>			
										</div>
				        			</td>
				        			<td>
				        				<select id="macPoolAssignmentOrder" data-dojo-id="macPoolAssignmentOrder" name="assignmentOrder" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 80px">
				        					<option value="default">Default</option>
				        					<option value="sequential">Sequential</option>
				        				</select>
				        			</td>
				        			<td colspan=""></td>
				        		</tr>
				        		<tr>
				        			<td>
					        			<div class="labelspace">
											<label style="float:left;">From Address:<em>*</em></label>			
										</div>
									</td>
				        			<td>
										<div id=fromAddress name = "fromAddress" data-dojo-id="fromAddress" style="width:120px;" data-dojo-type="xwt.widget.notification.ValidationTextBox" value="00:25:B5:00:00:00" data-dojo-props='regExp:REG_EX_MAC_ADDRESS, trim:"true",required:"true", invalidMessage:MSG_MACADDRESS'></div>
									</td>
				        			<td>
					        			<div class="labelspace">
											<label style="float:left;">Number Of MAC Addresses:<em>*</em></label>			
										</div>
									</td>
				        			<td>
				        				<div id="noOfMacPool"  style="width:70px;" name = "noOfMacPool" data-dojo-id="noOfMacPool" data-dojo-type="xwt.widget.notification.ValidationTextBox" data-dojo-props='regExp:REG_EX_NUMBER_UPTO1000, trim:"true", maxlength:"4", invalidMessage:MSG_UPTO1000'></div>
				        			</td>
				        			<td style="padding-left: 10px;">
				        				<button data-dojo-type="dijit/form/Button" data-dojo-id="macPoolgenerateDataBtn" onClick="macPoolGenerateData();" type="button">Add</button>
				        			</td>
				        		</tr>
				        	</table>
					  	</form>
					</div>
		     		<div class="floatleft addClassForColumnHeight" style="padding-left:5px;padding-top:18px;"> 
				          <div dojotype="dijit.layout.ContentPane" region="left" style="width:690px; overflow:hidden;" splitter="true">
					        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="macPoolDataStoreTab2" data="macPoolDataTable2"></span>
								<div style="width:690px !important;"  id="macPoolTable2TollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="macPoolConfigTable2" quickFilter="false">
										<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
								</div>
								<div id="macPoolConfigTable2" 
									data-dojo-id="macPoolConfigTable2" 
									dojoType="xwt.widget.table.Table"
									store="macPoolDataStoreTab2" 
									structure="macPoolColumns" 
									style="width: 690px; height: 215px;"
									selectMultiple="true" 
									selectAllOption="true" 
									selectModel="input"
									filterOnServer=false></div>
							</div>
						</div>
				</fieldset>
			</div>
	</div>
</body>
</html>