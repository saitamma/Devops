<%-- serversBootPolicyConfig.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/serversBootPolicyConfig.js"></script>
<script type="text/javascript">
var domConstrct;
dojo.require("xwt.widget.table.Table");
dojo.require("xwt.widget.table.Toolbar");
dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.Form");
dojo.require("xwt.widget.form.DropDown");
dojo.require("xwt.widget.notification.ValidationTextBox");
dojo.require("xwt.widget.notification.Form");
dojo.require("xwt.widget.layout.AccordionContainer");
dojo.require("xwt.widget.layout.AccordionPane");
dojo.require("xwt.widget.layout.Dialog");
dojo.require("dojo.dnd.Source");
require(["dojo/dom-construct","dojo/dom"], function(domConstruct,dom){
    // Take a string and turn it into a DOM node
    domConstrct = domConstruct;
});

var tempBootPolicyId,bootPolicyInt = 0;
var vhbaValues=[],vNICValuesList=[],typeDropdownValue = [{label: "Primary",value: "Primary"}, {label: "Secondary",value: "Secondary"}];
var bootModes = [{label: "Legacy",value: "legacy"}, {label: "Uefi",value: "uefi"}];
var bootSecurities = [{label: "N/A",value: "n/a", disabled: false},{label: "Yes",value: "yes", disabled: false}, {label: "No",value: "no", disabled: false}];

var vhbaConfigDataResponse = ajaxCallGet("getSanVhbaDetails.html", true, "json");
if(vhbaConfigDataResponse){
		vhbaValues.push({value:null,label: LABEL_SELECT});
	dojo.forEach(JSON.parse("["+vhbaConfigDataResponse+"]"),function(eachVhba,i){
		vhbaValues.push({value:eachVhba.id,	label:eachVhba.name});
	});
}
var vnicConfigDataResponse = ajaxCallGet("getLanVnicDetails.html", true, "json");
if(vnicConfigDataResponse){
		vNICValuesList.push({value:null,label: LABEL_SELECT});
	dojo.forEach(JSON.parse("["+vnicConfigDataResponse+"]"),function(eachVnic,i){
		vNICValuesList.push({value:eachVnic.id,	label:eachVnic.name});
	});
}

var bootPolicyConfigDataResponse = ajaxCallGet("getBootPolicyConfigDetails.html", true, "json");
var serversBootPolicyDataTable = { items:JSON.parse("[" + bootPolicyConfigDataResponse + "]") };

// Calculating the max used BootPolicy
var count = bootPolicyConfigDataResponse.length;
if(count > 0){
	var lastNameUsed = JSON.parse(bootPolicyConfigDataResponse[count-1]).name;
	bootPolicyInt = extractNumericValueFromAlphaNumericString(lastNameUsed);
}

var serversBootPolicyColumns = [
       {label: 'dbID',	attr: 'id',	hidden:true	},
       {label: 'Name',attr: 'name',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: true,
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
       {label: 'Description',attr: 'description',sorted: 'ascending',width: 130,vAlignment: "middle",align:'center',editable: true,
    	   editWidget:{
      			widget:xwt.widget.notification.ValidationTextBox,
      			options: {
          			trim: true,
          			maxlength:"45",
					}
    		}   
       },
       {label: 'Enforce vNIC/vHBA ',attr: 'enforceVnicName',sorted: 'ascending',width: 160,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
    	   filterType: "boolean",
			editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {
					options: [ {label: "Yes",value: "1"}, {label: "No",value: "0"}]
					}
				}
       },
       {label: 'Reboot On<br />Update',attr: 'rebootOnUpdate',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
    	   filterType: "boolean",
			editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {
					options: [{label: "Yes",value: "1"}, {label: "No",value: "0"}]
						}
					}
       	},
       	{label: 'Boot<br/>Mode',attr: 'bootMode',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: true, formatter: formatterGetBootMode,
       		editWidget: {
					widget: xwt.widget.form.DropDown,
					options: {options: bootModes, id: "bootModeId"}
	                   	  }
       	},
       	{label: 'Boot<br/>Security',attr: 'secureBoot',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetBootSecurity,
 			editWidget: {
 				widget: xwt.widget.form.DropDown,
 				options: {
 					options: bootSecurities, id: "bootSecurityId"
 						}
 					}
        },
       {label: 'Organization',attr: 'organizations',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
			editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {options: getOgranizationDropDown()}
                  	  }
        },
        {label: 'Configure',attr: 'configure',width: 100,vAlignment: "middle",align:'center',formatter: formatterConfigureLanSanPolicy}
       ];		


var bootPolicyForLanColumns = [
                 {label: 'dbID',	attr: 'id',	hidden: true	},
                 {label: 'Name',attr: 'name',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center'},
                 {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
                 {label: 'Order',attr: 'order',sorted: 'ascending',width: 80,vAlignment: "middle",align:'center'},
                 {label: 'vNIC<em>*</em>',attr: 'lanVnic',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetVNICName,
  					editWidget: {
  						widget: xwt.widget.form.DropDown,
  						options: {options: vNICValuesList,maxHeight:150}
  		                   	  }
                 },
                 {label: 'Type',attr: 'type',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center'}
                 ];
                 
function formatterGetVNICName(data, item, store){
	return returnFormatterDropDownLabel(vNICValuesList,data, item, store);
}
var bootPolicyForSanColumns = [
                 {label: 'dbID',	attr: 'id',	hidden: true	},
                 {label: 'Name',attr: 'name',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center'},
                 {label: 'Description',attr: 'description',sorted: 'ascending',width: 85,vAlignment: "middle",align:'center'},
                 {label: 'Order',attr: 'order',sorted: 'ascending',width: 60,vAlignment: "middle",align:'center'},
                 {label: 'vHBA<em>*</em>',attr: 'sanVhba',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetVhbaName,
 					editWidget: {
 						widget: xwt.widget.form.DropDown,
 						options: {options: vhbaValues,maxHeight:150}
 		                   	  }
                	},
                 {label: 'Type',attr: 'type',sorted: 'ascending',width: 65,vAlignment: "middle",align:'center'},
                 {label: 'Add Target',attr: 'add_target',width: 115,vAlignment: "middle",align:'center',formatter: formatterAddTargetSanPolicy}
                 ];

function formatterGetVhbaName(data, item, store){
	return returnFormatterDropDownLabel(vhbaValues,data, item, store);
}
var bootPolicyForSanTargetColumns = [
                             {label: 'dbID',	attr: 'id',	hidden: true	},
                             {label: 'Name',attr: 'name',sorted: 'ascending',width: 140,vAlignment: "middle",align:'center'},
                             {label: 'Parent Name',attr: 'serversBootPolicySan', sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',formatter: formatterGetSANParentName},
                             {label: 'Type',attr: 'type',sorted: 'ascending',width: 70,vAlignment: "middle",align:'center'},
                              {label: 'LUN Id',attr: 'lunId',sorted: 'ascending',width: 70,vAlignment: "middle",align:'center',editable:true,
                            	 editWidget:{
                           			widget:xwt.widget.notification.ValidationTextBox,
                           			options: {
               								regExp:REG_EX_NUMBER_UPTO255,
               								required: true,
               	                			trim: true,
               	                			maxlength:"4",
               	                			invalidMessage: MSG_UPTO255
               								}
                           		}
                             },
                             {label: 'WWPN',attr: 'wwpnAddress',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: true,
                            	 editWidget: {
 	                         		widget:xwt.widget.notification.ValidationTextBox,
 	         							options: {
 	         								regExp:REG_EX_WWXN,
 	         								required: true,
 	         	                			trim: true,
 	         								invalidMessage:MSG_WWXN,
 	         								id: "BootPolicySanTargetWWPNAddress"
 	         								}
 	                         			} 
                             } 
                             ];

//function for Save data to servere
function saveServersBootPolicyConfigData(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":2});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	 console.log("UUID Save");
}

</script>
</head>
<body class="prime">
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1050px;">
				<legend>Boot Policy Configuration</legend>

				<div class="vnicTemplateConfig">
					<form id="serversBootPolicyTableForm"
						data-dojo-id="serversBootPolicyTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversBootPolicyOrganization"
									name="serversBootPolicyOrganization"
									data-dojo-id="serversBootPolicyOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of Boot Policies:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfBootPolicy" name="noOfBootPolicy"
										data-dojo-id="noOfBootPolicy"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversBootPolicyGenerateDataBtn"
										onClick="serversBootPolicyGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft addClassForColumnHeight" style="padding-left: 25px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1003px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversBootPolicyDataStoreTab"
							data="serversBootPolicyDataTable"></span>
						<div style="width: 1000px !important;"
							id="serversBootPolicyTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversBootPolicyTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversBootPolicyTable"
							data-dojo-id="serversBootPolicyTable"
							dojoType="xwt.widget.table.Table"
							store="serversBootPolicyDataStoreTab"
							structure="serversBootPolicyColumns"
							style="width: 1000px; height: 235px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>

	<div id="serversBootPolicyConfigureDialog" button1Label="Save"
		data-dojo-id="serversBootPolicyConfigureDialog" title=""
		dojoType="xwt.widget.layout.Dialog" closable="false"
		style="display: none;">
		<div style="width: 100%; height: 48rem;">
			<input id="bootPolicyId" data-dojo-id="bootPolicyId" type="hidden"
				dojoType="dijit.form.TextBox"></input>
			

				<div>
					<div class="floatleft" style="padding-left: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 570px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="bootPolicyForLanDataStoreTable"
								data="bootPolicyForLanDataTable"></span>
							<div style="width: 570px !important;"
								id="bootPolicyForLanTollBarPopup"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="bootPolicyForLanTablePopup" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
								<div dojoType="dijit.form.Button" iconClass="fi-plus"
									title="Add LAN Policy" id="addLanPolicyConfig" showLabel="false"
									onclick="return addLanConfig();"></div>
								<label>LAN Boot Policy</label>
							</div>
							<div id="bootPolicyForLanTablePopup"
								data-dojo-id="bootPolicyForLanTablePopup"
								dojoType="xwt.widget.table.Table"
								store="bootPolicyForLanDataStoreTable"
								structure="bootPolicyForLanColumns"
								style="width: 570px; height: 130px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					<div class="floatleft" style="padding: 0px 0px 0px 15px;">
						<fieldset style="margin: 0px;width: 510px;height: 165px;">
							<legend>Drag Element To Change Boot Order</legend>
							<ol dojoType="dojo.dnd.Source" class="container"
							id="dndForLANandSANandLocaldisk" data-dojo-props="autoSync: true"
							accept="widget" style="margin: 0px;">
							<!-- <li class="dojoDndItem" id="lanOrder">LAN</li> -->
						</ol>
						 </fieldset>
					</div>
				</div>
				<div style="clear: both;"></div>
				<div>
					<div class="floatleft" style="padding-left: 10px; padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 570px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="bootPolicyForSanDataStore" data="bootPolicyForSanDataTable"></span>
							<div style="width: 570px !important;"
								id="bootPolicyForSanTollBarPopup"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="bootPolicyForSanTableConfigPopup" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="none"></div>
								<div dojoType="dijit.form.Button" iconClass="fi-delete"
									title="Delete SAN Policy" id="deleteSANPolicyConfig" showLabel="false"
									onclick="return deleteSanBootPolicy();"></div>
								<div dojoType="dijit.form.Button" iconClass="fi-plus"
									title="Add SAN Policy" id="addSANPolicyConfig" showLabel="false"
									onclick="return addSANConfig();"></div>
								<label>SAN Boot Policy</label>
							</div>
							<div id="bootPolicyForSanTableConfigPopup"
								data-dojo-id="bootPolicyForSanTableConfigPopup"
								dojoType="xwt.widget.table.Table"
								store="bootPolicyForSanDataStore"
								structure="bootPolicyForSanColumns"
								style="width: 570px; height: 180px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					
					<div class="floatleft" style="padding-left: 10px; padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 540px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="bootPolicyForSanTargetDataStore" data="bootPolicyForSanTargetDataTable"></span>
							<div style="width: 540px !important;"
								id="bootPolicyForSanTargetTollBarPopup"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="bootPolicyForSanTargetTableConfigPopup" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
								<label>SAN Target Policy</label>
							</div>
							<div id="bootPolicyForSanTargetTableConfigPopup"
								data-dojo-id="bootPolicyForSanTargetTableConfigPopup"
								dojoType="xwt.widget.table.Table"
								store="bootPolicyForSanTargetDataStore"
								structure="bootPolicyForSanTargetColumns"
								style="width: 540px; height: 180px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
				</div>
				
				<div>
					<form id="localDiskPolicyConfigPopup"
						data-dojo-id="localDiskPolicyConfigPopup"
						data-dojo-type="xwt.widget.notification.Form"
						name="localDiskPolicyConfigForm">
						<fieldset
							style="height: 50px; background-color: transparent; width: 632px; margin: 20px 0 0 15px;display: inline;">
							<legend>
								<input id="Local_Disk" dojoType="dijit.form.CheckBox"
									value="Local_Disk" name="Local_Disk" /><label for="Local_Disk"
									style="margin: 0px; font-weight: bold;">Local Disk</label>
							</legend>
	
							<input id="Local_LUN" dojoType="dijit.form.CheckBox"
								value="Local_LUN" name="Local_LUN" /> <label for="Local_LUN"
								style="margin-right: 30px;"> Local LUN </label> <input
								id="SD_Card" dojoType="dijit.form.CheckBox" value="SD_Card"
								name="SD_Card" /> <label for="SD_Card"
								style="margin-right: 30px;"> SD Card </label> <input
								id="Internal_USB" dojoType="dijit.form.CheckBox"
								value="Internal_USB" name="Internal_USB" /> <label
								for="Internal_USB" style="margin-right: 30px;"> Internal
								USB </label> <input id="External_USB" dojoType="dijit.form.CheckBox"
								value="External_USB" name="External_USB" /> <label
								for="External_USB" style="margin-right: 30px;"> External
								USB </label>
	
						</fieldset>
					</form>
				</div>

			
		</div>

	</div>


</body>
</html>