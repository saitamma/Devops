<%-- lanMgmtIpPoolConfig.jsp --%>
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
			
			//get current project detail
			var getProjectDetails = JSON.parse("["+ ajaxCallGet("fetchCurrentProjectDetails.html", true, "json") +"]");

			var mgmtIPpoolConfigDataResponse = ajaxCallGet("getLanMgmtIPpoolConfigDetails.html", true, "json");
			var lanMgmtIpPoolDataTable = {items:JSON.parse("["+mgmtIPpoolConfigDataResponse+"]")}; 
 
var lanMgmtIpPoolColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 80,vAlignment: "middle",align:'center'},
	               {label: 'Description',attr: 'description',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,
	            	   editWidget: {
                		   widget:xwt.widget.notification.ValidationTextBox,
	                   		options: {
	                   			trim: true,
	                   			maxlength:"45",
	       					}
         				}
                   },
                   {label: 'From Address',attr: 'fromAddress',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center'},
                   {label: 'To Address',attr: 'toAddress',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center'},
                   {label: 'Default Gateway',attr: 'defaultGateway',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 								regExp: REG_EX_IPADDRESS,
 								required: true,
 	                			trim: true,
 	                			invalidMessage: MSG_IPADDRESS
 								}
               			}
                   },
                   {label: 'Subnet Mask',attr: 'subnetMask',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: true,editWidget:{
		           		widget:xwt.widget.notification.ValidationTextBox,
		        			options: {
									regExp: REG_EX_IPADDRESS,
									required: true,
		              			trim: true,
		              			invalidMessage: MSG_IPADDRESS
									}
		        		}
                   },
                   {label: 'DNS',attr: 'dns',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 								regExp: REG_EX_IPADDRESS,
 								required: true,
 	                			trim: true,
 	                			invalidMessage: MSG_IPADDRESS
 								}
               			}
                   },
                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: getOgranizationDropDown(),readOnly:true}
   		                   	  }
                      		}
                   ];		


function lanMgmtIpPoolGenerateData(){
	var formObject2 =  dojo.formToObject("lanMgmtIpPoolTableForm");
	if(dijit.byId("lanMgmtIpPoolTableForm").validate()==false || formObject2.lanMgmtIpStartingIpAddress == "" || formObject2.lanMgmtIpStartingIpAddress == "0.0.0.0" || formObject2.lanMgmtIpNoOfIpAddress == "" || formObject2.lanMgmtIpDefaultGateway == "" || formObject2.lanMgmtIpSubnetMask == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	} else if(formObject2.lanMgmtIpDNS == ""){
		dijit.byId("lanMgmtIpDNS").set("value","0.0.0.0");
		formObject2.lanMgmtIpDNS = "0.0.0.0";
	}
	if( (parseInt(formObject2.lanMgmtIpStartingIpAddress.split(".")[3]) + parseInt(formObject2.lanMgmtIpNoOfIpAddress)) > 255){
		displayNotificationAlert(MSG_IPFROM_TO_RANGE);
		return false;
	}
	
 	var selectedOrg = parseInt(formObject2.lanMgmtIpPoolOrganization);
 	var lanMgmtIpNoOfIpAddress = parseInt(formObject2.lanMgmtIpNoOfIpAddress);

 	var lanMgmtIpPoolDataGenreate = {
			"id":0,
			"name":"ext-mgmt",
			"description": "ext mgmt",
			"fromAddress":formObject2.lanMgmtIpStartingIpAddress,
			"toAddress":0,
			"noOfAddresses" : lanMgmtIpNoOfIpAddress,
			"defaultGateway": formObject2.lanMgmtIpDefaultGateway,
			"subnetMask": formObject2.lanMgmtIpSubnetMask,
			"dns":formObject2.lanMgmtIpDNS,
			"organizations":selectedOrg
		};
	lanMgmtIpPoolTable.store.newItem(lanMgmtIpPoolDataGenreate);
	
 	lanMgmtIpPoolDataStoreTab.save();
 	lanMgmtIpPoolTable.refresh();
 
 	dijit.byId("lanMgmtIpStartingIpAddress").set("value","");
 	dijit.byId("lanMgmtIpNoOfIpAddress").set("value","");
 	dijit.byId("lanMgmtIpDefaultGateway").set("value","");
 	dijit.byId("lanMgmtIpSubnetMask").set("value","255.255.255.0");
 	dijit.byId("lanMgmtIpDNS").set("value","0.0.0.0");
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){
	    		if(getProjectDetails.length > 0 && getProjectDetails[0].ipPoolAssignmentOrder == "sequential" ){
					dijit.byId("radio_sequential").set("checked",true);
				}else{
					dijit.byId("radio_default").set("checked",true);
				}
	    		
	    		// ADD dropDown val for Organization //
	   		 	var selectOrganizationArr = getOgranizationDropDown();
	   		 	lanMgmtIpPoolOrganization.addOption(selectOrganizationArr);
	
	   		 	lanMgmtIpPoolDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var lanMgmtIpPoolTablejson = returnChangedDataFromDataStore(this,json);
	    			console.log(lanMgmtIpPoolTablejson);
	    			var response = ajaxCallPostWithJsonContent("manageLanMgmtIPpoolConfig.html" , lanMgmtIpPoolTablejson, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
	    			updateToAddressInDataStore(response, this);
		     	};

	    	 },1000);
	 
	     });

// function for Save data to servere
function saveLanMgmtIPPoolConfigData(){
	var AssignmentOrderJsonObj = JSON.parse(dojo.formToJson("lanMgmtIpPoolAssignmentOrderForm"));
		AssignmentOrderJsonObj["id"] = ${activeProjectId};
	response = ajaxCallPostWithJsonContent("updateIpPoolAssignmentOrder.html" , JSON.stringify(AssignmentOrderJsonObj), null, "text");
	
	// Save Wizard Status
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":4,"activeStateSubMenuIndex":0});
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
				<legend>Management IP Pool Configuration</legend>
				
				<div style="border-bottom: 1px solid #C0C0C0;padding: 5px 0px 15px 25px;">
						<form id="lanMgmtIpPoolAssignmentOrderForm"
						data-dojo-id="lanMgmtIpPoolAssignmentOrderForm"
						data-dojo-type="xwt.widget.notification.Form" name="assignmentOrderForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Assignment Order:</label>
										</div>
									</td>
									<td>
										<input name="ipPoolAssignmentOrder" id="radio_default" type="radio" dojoType="dijit.form.RadioButton" value="default"/>
										<label for="radio_default">Default</label> 	
										<input name="ipPoolAssignmentOrder" id="radio_sequential" type="radio" dojoType="dijit.form.RadioButton" value="sequential" />
										<label for="radio_sequential">Sequential</label>
									</td>
								</tr>
							</table>
						</form>
				</div>
				<div class="lanMgmtIPPoolConfig">
					<form id="lanMgmtIpPoolTableForm"
						data-dojo-id="lanMgmtIpPoolTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td height="36px;" style="display: none">
									<div class="labelspace">
										<label style="float: left;">Organizations:</label>
									</div>
								</td>
								<td style="display: none"><select
									id="lanMgmtIpPoolOrganization" name="lanMgmtIpPoolOrganization"
									data-dojo-id="lanMgmtIpPoolOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;"
									readOnly="true" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">From IP Address:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanMgmtIpStartingIpAddress"
										name="lanMgmtIpStartingIpAddress"
										data-dojo-id="lanMgmtIpStartingIpAddress"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px"
										data-dojo-props='trim:"true", maxlength:"40",pattern: REG_EX_IPADDRESS, invalidMessage:MSG_IPADDRESS'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of IP Addresses:<em>*</em></label>
									</div>
								</td>
								<td colspan="2">
									<div id="lanMgmtIpNoOfIpAddress" name="lanMgmtIpNoOfIpAddress"
										data-dojo-id="lanMgmtIpNoOfIpAddress"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px"
										data-dojo-props='regExp:REG_EX_NUMBER_1TO255,trim:"true", maxlength:"3", invalidMessage:MSG_UPTO255'></div>
								</td>
							</tr>
							<tr>
								<td height="36px;">
									<div class="labelspace">
										<label style="float: left;">Default gateway:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanMgmtIpDefaultGateway"
										name="lanMgmtIpDefaultGateway"
										data-dojo-id="lanMgmtIpDefaultGateway"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px"
										data-dojo-props='trim:"true", maxlength:"40",pattern: REG_EX_IPADDRESS, invalidMessage:MSG_IPADDRESS'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Subnet Mask:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanMgmtIpSubnetMask" name="lanMgmtIpSubnetMask"
										data-dojo-id="lanMgmtIpSubnetMask"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px" value="255.255.255.0"
										data-dojo-props='trim:"true", required:"true",maxlength:"40",pattern: REG_EX_IPADDRESS, invalidMessage:MSG_IPADDRESS'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">DNS:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanMgmtIpDNS" name="lanMgmtIpDNS"
										data-dojo-id="lanMgmtIpDNS"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px" value="0.0.0.0" 
										data-dojo-props='trim:"true", required:"true", maxlength:"40",pattern: REG_EX_IPADDRESS, invalidMessage:MSG_IPADDRESS'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="lanMgmtIpPoolGenerateDataBtn"
										onClick="lanMgmtIpPoolGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft"
					style="padding-left: 25px; padding-top: 10px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1003px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="lanMgmtIpPoolDataStoreTab" data="lanMgmtIpPoolDataTable"></span>
						<div style="width: 1000px !important;"
							id="lanMgmtIpPoolTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="lanMgmtIpPoolTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="lanMgmtIpPoolTable" data-dojo-id="lanMgmtIpPoolTable"
							dojoType="xwt.widget.table.Table"
							store="lanMgmtIpPoolDataStoreTab"
							structure="lanMgmtIpPoolColumns"
							style="width: 1000px; height: 175px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>