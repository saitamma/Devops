<%-- serversLocalDiskPolicyConfig.jsp --%>
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

			var localDiskPolicyConfigDataResponse = ajaxCallGet("getLocalDiskPolicyConfigDetails.html", true, "json");
			var serversLocalDiskPolicyDataTable = { items:JSON.parse("[" + localDiskPolicyConfigDataResponse + "]") };
 
			// Calculating the max used Servers LocalDisc
			var localDiscMax = 0; 
			var count = localDiskPolicyConfigDataResponse.length;
			if(count > 0){
				var lastNameUsed = JSON.parse(localDiskPolicyConfigDataResponse[count-1]).name;
				localDiscMax = extractNumericValueFromAlphaNumericString(lastNameUsed);
			}
			
			var localDiskPolicyMode=[];
				localDiskPolicyMode.push({value:"No Local Storage",	label:"No Local Storage"});
				localDiskPolicyMode.push({value:"RAID 0 Striped",	label:"RAID 0 Striped"});
				localDiskPolicyMode.push({value:"RAID 1 Mirrored",	label:"RAID 1 Mirrored"});
				localDiskPolicyMode.push({value:"Any Configuration",label:"Any Configuration",selected:true});
				localDiskPolicyMode.push({value:"No RAID",	label:"No RAID"});
				localDiskPolicyMode.push({value:"RAID 5 Striped Parity",label:"RAID 5 Striped Parity"});
				localDiskPolicyMode.push({value:"RAID 6 Striped Dual Parity",label:"RAID 6 Striped Dual Parity"});
				localDiskPolicyMode.push({value:"RAID 10 Mirrored And Striped",label:"RAID 10 Mirrored And Striped"});
				localDiskPolicyMode.push({value:"RAID 50 Striped Parity And Striped",label:"RAID 50 Striped Parity And Striped"});
				localDiskPolicyMode.push({value:"RAID 60 Striped Dual Parity And Striped",	label:"RAID 60 Striped Dual Parity And Striped"});
			
			var serversLocalDiskPolicyColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 170,vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 170,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 	                			trim: true,
 	                			maxlength:"45"
 							}
               			}   
                   },
                   {label: 'Mode',attr: 'mode',sorted: 'ascending',width: 160,vAlignment: "middle",align:'center',editable: true,
                	   editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: localDiskPolicyMode}
      		                   	  }
                   },
                   {label: 'Protect Configuration',attr: 'protectConfiguration',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
                	  // filterType: "boolean",
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {
   							options: [{label: "Yes",value: "1"}, {label: "No",value: "0"}]
		   						}
		   					}
                   	},
                   {label: 'Organization',attr: 'organizations',width: 200,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: getOgranizationDropDown()}
   		                   	  }
                      		}
                   ];		


function serversLocalDiskPolicyGenerateData(){
	var formObject2 =  dojo.formToObject("serversLocalDiskPolicyTableForm");
	if(dijit.byId("serversLocalDiskPolicyTableForm").validate()==false || formObject2.noOfLocalDisoPolicy == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	
 	var selectedOrg = parseInt(formObject2.serversLocalDiskPolicyOrganization);
 	var noOfLocalDisoPolicy = parseInt(formObject2.noOfLocalDisoPolicy);

 	for(i = 1 ; i <= noOfLocalDisoPolicy ; i++){
 		localDiscMax++;
		var localDiscName = "LocalDisk"+localDiscMax;
 		if(!checkTableFieldValueUnique(serversLocalDiskPolicyDataStoreTab,"name",localDiscName)){
 			continue;
 		}
 		var serversLocalDiskPolicyDataGenreate = {
 				"id":0,
 				"name":	localDiscName,
 				"description": "Disk Policy "+localDiscMax,
 				"mode": "Any Configuration",
 				"protectConfiguration":1,
 				"organizations":selectedOrg
 			};
 		serversLocalDiskPolicyTable.store.newItem(serversLocalDiskPolicyDataGenreate);
 	}
 	serversLocalDiskPolicyDataStoreTab.save();
 	serversLocalDiskPolicyTable.refresh();
 	
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 
	    		 // ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	serversLocalDiskPolicyOrganization.addOption(selectOrganizationArr);

	    		 	serversLocalDiskPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversLocalDiskPolicyTablejson = returnChangedDataFromDataStore(this,json);
		    			console.log(serversLocalDiskPolicyTablejson);
		    			var response = ajaxCallPostWithJsonContent("manageLocalDiskPolicyConfig.html" , serversLocalDiskPolicyTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
			     	};
			     	
			     	dojo.addOnLoad(function () {
			     		serversLocalDiskPolicyTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversLocalDiskPolicyDataStoreTab,"name",newitem.name)){
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
function saveServersLocalDiskPolicyConfigData(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":3});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	 console.log("LocalDiskPolicy Save");
}


</script>
</head>
<body class="prime">
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1050px;">
				<legend>Local Disk Policy Configuration</legend>

				<div class="vnicTemplateConfig">
					<form id="serversLocalDiskPolicyTableForm"
						data-dojo-id="serversLocalDiskPolicyTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversLocalDiskPolicyOrganization"
									name="serversLocalDiskPolicyOrganization"
									data-dojo-id="serversLocalDiskPolicyOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of Local Disk
											Policies:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfLocalDisoPolicy" name="noOfLocalDisoPolicy"
										data-dojo-id="noOfLocalDisoPolicy"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversLocalDiskPolicyGenerateDataBtn"
										onClick="serversLocalDiskPolicyGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft"
					style="padding-left: 25px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1003px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversLocalDiskPolicyDataStoreTab"
							data="serversLocalDiskPolicyDataTable"></span>
						<div style="width: 1000px !important;"
							id="serversLocalDiskPolicyTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversLocalDiskPolicyTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversLocalDiskPolicyTable"
							data-dojo-id="serversLocalDiskPolicyTable"
							dojoType="xwt.widget.table.Table"
							store="serversLocalDiskPolicyDataStoreTab"
							structure="serversLocalDiskPolicyColumns"
							style="width: 1000px; height: 237px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>