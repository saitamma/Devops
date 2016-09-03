<%-- serversHostFirmware.jsp --%>
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
	dojo.require("dijit.form.CheckBox");
	
	
	var serversHostFirmwareDataTable = {items:JSON.parse("["+ ajaxCallGet("getserversHostFirmwareDetails.html", true, "json") +"]")};
	
	var serversHostFirmwareColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: "40%",vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: "30%",vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 	                			trim: true,
 	                			maxlength:"256",
 							}
               			}   
                   },
                   {label: 'Organization',attr: 'organizations',width: "15%",vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
      					editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: getOgranizationDropDown()}
      		                   	  }
                     }
                   ];		

function serversHostFirmwareGenerateData(){
	var serversHostFirmwareFormObj =  dojo.formToObject("serversHostFirmwareTableForm");
	
	if(dijit.byId("serversHostFirmwareTableForm").validate()==false || serversHostFirmwareFormObj.serversHostFirmwareName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(serversHostFirmwareDataStoreTab,"name",serversHostFirmwareFormObj.serversHostFirmwareName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var tableRowDefItem = {
				"id":0,
				"name":	serversHostFirmwareFormObj.serversHostFirmwareName,
				"description" : serversHostFirmwareFormObj.serversHostFirmwareName,
				"organizations" : parseInt(serversHostFirmwareFormObj.serversHostFirmwareOrganization)
	};

	serversHostFirmwareTable.store.newItem(tableRowDefItem);
	
	serversHostFirmwareDataStoreTab.save();
	serversHostFirmwareTable.refresh();
	serversHostFirmwareTableForm.reset();
}
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){	   		 	
	    		// ADD dropDown val for Organization //
    		 	var selectOrganizationArr = getOgranizationDropDown();
    		 	serversHostFirmwareOrganization.addOption(selectOrganizationArr);
    		 	
	    		serversHostFirmwareDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var serversHostFirmwareTable1json = returnChangedDataFromDataStore(this,json);
	    			console.log(serversHostFirmwareTable1json);
	    			var response = ajaxCallPostWithJsonContent("manageHostFirmwareConfig.html" , serversHostFirmwareTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
			  // validation on edit row
			     serversHostFirmwareTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversHostFirmwareDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
			     
	    	 },1000);
	     });
	     
// function for Save data to Ldap Group map
function saveserversHostFirmwareOnNext(){
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":6});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	
	return true;
}
</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft">
				<fieldset class="heightOfFieldset" style="width: 1050px;">
					<legend id="titleOfHostFirmwareCreation">Host Firmware Configuration</legend>
					<div id="createserversHostFirmwareTableDiv" style="margin: 5px;">
					<div class="commonclassForFormFields">
						<form id="serversHostFirmwareTableForm"
							data-dojo-id="serversHostFirmwareTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
									</td>
									<td><select id="serversHostFirmwareOrganization"
										name="serversHostFirmwareOrganization"
										data-dojo-id="serversHostFirmwareOrganization"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 140px" />
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="serversHostFirmwareName"
											name="serversHostFirmwareName" style="width: 135px;"
											data-dojo-id="serversHostFirmwareName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="serversHostFirmwareGenerateDataBtn"
											onClick="serversHostFirmwareGenerateData();" type="button">Add</button>
									</td>
								</tr>
								
							</table>
						</form>
					</div>
					
					<div class="floatleft" style="padding-left: 20px; padding-top: 30px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1000px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="serversHostFirmwareDataStoreTab"
								data="serversHostFirmwareDataTable"></span>
							<div style="width: 1000px !important;"
								id="serversHostFirmwareTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="serversHostFirmwareTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="serversHostFirmwareTable"
								data-dojo-id="serversHostFirmwareTable"
								dojoType="xwt.widget.table.Table"
								store="serversHostFirmwareDataStoreTab"
								structure="serversHostFirmwareColumns"
								style="width: 1000px; height: 235px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					</div>
				</fieldset>
		</div>

	</div>
</body>
</html>
