<%-- lanNetworkControlPolicy.jsp --%>
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
	
	var lanNetworkControlPolicyCDPArr = [], LNCPMACRegisterModeArr = [], LNCPActionOnUplinkFailArr = [], LNCPForgeArr = [];
	lanNetworkControlPolicyCDPArr.push({value:"disabled",	label:"Disabled"});
	lanNetworkControlPolicyCDPArr.push({value:"enabled",	label:"Enabled"});
	
	LNCPMACRegisterModeArr.push({value:"only-native-vlan",	label:"Only Native Vlan"});
	LNCPMACRegisterModeArr.push({value:"all-host-vlans",	label:"All Host Vlans"});
	
	LNCPActionOnUplinkFailArr.push({value:"link-down",	label:"Link Down"});
	LNCPActionOnUplinkFailArr.push({value:"warning",	label:"Warning"});
	
	LNCPForgeArr.push({value:"allow",	label:"Allow"});
	if(infraServerModel.get("value") != "Cisco UCS 6324" || (infrastructureParsedResult != undefined && infrastructureParsedResult.serverModel != "Cisco UCS 6324") ) {
		LNCPForgeArr.push({value:"deny",	label:"Deny"});
    }
	
	var lanNetworkControlPolicyDataTable = {items:JSON.parse("["+ ajaxCallGet("getLanNetworkControlPolicyDetails.html", true, "json") +"]")}; 
 
	var lanNetworkControlPolicyColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'ncpName',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
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
	               {label: 'Description',attr: 'description',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true,
	            	   editWidget: {
                		   widget:xwt.widget.notification.ValidationTextBox,
	                   		options: {
	                   			trim: true,
	                   			maxlength:"256",
	       					}
         				}
                   },
                   {label: 'CDP',attr: 'cdp',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetCDPName,
                	   editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: lanNetworkControlPolicyCDPArr}
      		                   	  }
                   },
                   {label: 'MAC Register Mode',attr: 'macRegisterMode',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetMACRegName,
                	   editWidget: {
     						widget: xwt.widget.form.DropDown,
     						options: {options: LNCPMACRegisterModeArr}
     		                   	  }
                   },
                   {label: 'Action On Uplink Fail',attr: 'uplinkFailAction',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetActionUplinkFailName,
                	   editWidget: {
    						widget: xwt.widget.form.DropDown,
    						options: {options: LNCPActionOnUplinkFailArr,id:"ActionOnUplinkFailDropDownWarning"}
    		                   	  }
                   },
                   {label: 'MAC Security',attr: 'forge',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetForgeName,
                	   editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: LNCPForgeArr}
   		                   	  }
                   },
                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: getOgranizationDropDown()}
   		                   	  }
                      		}
                   ];		


function lanNetworkControlPolicyGenerateData(){
	var lanNetworkCntlPolicyFormObj =  dojo.formToObject("lanNetworkControlPolicyTableForm");
	if(dijit.byId("lanNetworkControlPolicyTableForm").validate()==false || lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(lanNetworkControlPolicyDataStoreTab,"ncpName",lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var lanNCPDataTableDefItem = {
				"id":0,
				"ncpName":	lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyName,
				"description": lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyName,
				"cdp": lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyCdp,
				"macRegisterMode": lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyMacRegMode,
				"uplinkFailAction": lanNetworkCntlPolicyFormObj.lanNCPActionOnUplinkfail,
				"forge":lanNetworkCntlPolicyFormObj.lanNCPForge,
				"organizations":parseInt(lanNetworkCntlPolicyFormObj.lanNetworkControlPolicyOrganization)
	};
	lanNetworkControlPolicyTable.store.newItem(lanNCPDataTableDefItem);
	
	lanNetworkControlPolicyDataStoreTab.save();
	lanNetworkControlPolicyTable.refresh();
 	dijit.byId("lanNetworkControlPolicyName").set("value","");
 	dijit.byId("lanNetworkControlPolicyCdp").reset();
 	dijit.byId("lanNetworkControlPolicyMacRegMode").reset();
 	dijit.byId("lanNCPActionOnUplinkfail").reset();
 	dijit.byId("lanNCPForge").reset();
 	dijit.byId("lanNetworkControlPolicyOrganization").reset();
 	
}
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){
	    		dijit.byId("lanNCPActionOnUplinkfail").on("change",function(){
	    			if(this.value == "warning"){
	    				displayNotificationAlert(MSG_LAN_N_C_P_WARNING,"warning");
	    			}
	    		});
	    		dijit.byId("ActionOnUplinkFailDropDownWarning").on("change",function(){
	    			if(this.value == "warning"){
	    				displayNotificationAlert(MSG_LAN_N_C_P_WARNING,"warning");
	    			}
	    		});
	    		// ADD dropDown val for Organization //
	   		 	var selectOrganizationArr = getOgranizationDropDown();
	   		 	lanNetworkControlPolicyOrganization.addOption(selectOrganizationArr);
	   		 	// Add Dropdown for CDP
	   		 	lanNetworkControlPolicyCdp.addOption(lanNetworkControlPolicyCDPArr);
	   			// Add Dropdown for MAC Reg Mode
	   		 	lanNetworkControlPolicyMacRegMode.addOption(LNCPMACRegisterModeArr);
	   			// Add Dropdown for uplink fail
		   		lanNCPActionOnUplinkfail.addOption(LNCPActionOnUplinkFailArr);
		   		// Add Dropdown for forge
		   		lanNCPForge.addOption(LNCPForgeArr);
	   		 	
		   		lanNetworkControlPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var lanN_C_PTable1json = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageLanNetworkControlPolicyConfig.html" , lanN_C_PTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     lanNetworkControlPolicyTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.ncpName != newitem.ncpName){
									if(!checkTableFieldValueUnique(lanNetworkControlPolicyDataStoreTab,"ncpName",newitem.ncpName)){
										return false;
									}
								}
							return true;
						}
					 };
			
		    	
	    	 },1000);
	 
	     });

// function for Save data to servere
function saveLanNetworkControlPolicyOnNext(){

	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":3});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	
	return true;
}
</script>
</head>
<body>
		<div id="parentDiv">
 		<div class="floatleft">
				<div class="floatleft">
					<fieldset class="heightOfFieldset" style="width: 1130px;">
					<legend>Network Control Policy</legend>
					<div class="commonclassForFormFields">
						<form id="lanNetworkControlPolicyTableForm"
							data-dojo-id="lanNetworkControlPolicyTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td height="36px;">
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td>
								<select
									id="lanNetworkControlPolicyOrganization" name="lanNetworkControlPolicyOrganization"
									data-dojo-id="lanNetworkControlPolicyOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;"/>
									</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Name:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanNetworkControlPolicyName"
										name="lanNetworkControlPolicyName" style="width: 135px;"
										data-dojo-id="lanNetworkControlPolicyName"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">CDP:</label>
									</div>
								</td>
								<td>
									<select id="lanNetworkControlPolicyCdp" name="lanNetworkControlPolicyCdp"
									data-dojo-id="lanNetworkControlPolicyCdp"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;"/>
								</td>
							</tr>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">MAC Register Mode:</label>
									</div>
								</td>
								<td>
									<select id="lanNetworkControlPolicyMacRegMode" name="lanNetworkControlPolicyMacRegMode"
									data-dojo-id="lanNetworkControlPolicyMacRegMode"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;"/>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Action On Uplink Fail:</label>
									</div>
								</td>
								<td>
									<select id="lanNCPActionOnUplinkfail" name="lanNCPActionOnUplinkfail"
									data-dojo-id="lanNCPActionOnUplinkfail"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;"/>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">MAC Security:</label>
									</div>
								</td>
								<td>
									<select id="lanNCPForge" name="lanNCPForge"
									data-dojo-id="lanNCPForge"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;"/>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="lanNetworkControlPolicyGenerateDataBtn"
										onClick="lanNetworkControlPolicyGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
					</div>
					<div class="floatleft" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1130px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="lanNetworkControlPolicyDataStoreTab" data="lanNetworkControlPolicyDataTable"></span>
							<div style="width: 1130px !important;"
								id="lanNetworkControlPolicyTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="lanNetworkControlPolicyTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="lanNetworkControlPolicyTable" data-dojo-id="lanNetworkControlPolicyTable"
								dojoType="xwt.widget.table.Table"
								store="lanNetworkControlPolicyDataStoreTab"
								structure="lanNetworkControlPolicyColumns"
								style="width: 1130px; height: 220px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
					   </div>
					</div>
				</fieldset>
			</div>
		</div>
		
	</div>
   </body>
</html>
