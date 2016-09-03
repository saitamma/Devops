<%-- serversServiceTempConfig.jsp --%>
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
			dojo.require("dojox.form.CheckedMultiSelect");
			dojo.require("xwt.widget.form.TextButton");  

			var serviceTemplateConfigDataResponse = ajaxCallGet("getServiceTemplateConfigDetails.html", true, "json");
			console.log(serviceTemplateConfigDataResponse);
			var serversServiceTempDataTable = { items:JSON.parse("[" + serviceTemplateConfigDataResponse + "]") };
			var localDiskPolicyValues = [], bootPolicyValues=[],serverPoolValues=[],vnicValues=[],vhbaValuesList=[],WWNNPoolValues=[],uuidValues=[],
			connectivityPolicyValues=[], hostfirmwareValues=[], maintenancePolicyValues = [], biosPolicyValues = [], lanConnectivityPolicyValues=[];    
			var tempVhbaMultiSel = [],tempVnicMultiSel = [];//, multiselectVhbaDrop; //,multiselectVnicDrop;
			
			var maintenancePolicyDataResponse = ajaxCallGet("getMaintenancePolicyDetails.html", true, "json");
			if(maintenancePolicyDataResponse){
				maintenancePolicyValues.push({value:null,label: LABEL_SELECT});
				dojo.forEach(JSON.parse("["+maintenancePolicyDataResponse+"]"),function(eachMaintenancepolicy,i){
					maintenancePolicyValues.push({value: eachMaintenancepolicy.id,label: eachMaintenancepolicy.name});
				});
			}
			
			var biosPolicyDataResponse = ajaxCallGet("getserversBiosPolicyDetails.html", true, "json");
			if(biosPolicyDataResponse){
				biosPolicyValues.push({value:null,label: LABEL_SELECT});
				dojo.forEach(JSON.parse("["+biosPolicyDataResponse+"]"),function(eachBiospolicy,i){
					biosPolicyValues.push({value: eachBiospolicy.id,label: eachBiospolicy.name});
				});
			}
			  
			var hostfirmWareDataResponse = ajaxCallGet("getserversHostFirmwareDetails.html", true, "json");
			if(hostfirmWareDataResponse){
				hostfirmwareValues.push({value:null,label: LABEL_SELECT});
				dojo.forEach(JSON.parse("["+hostfirmWareDataResponse+"]"),function(eachhostfirmWare,i){
					hostfirmwareValues.push({value: eachhostfirmWare.id,label: eachhostfirmWare.name});
				});
			}
			
			var localDiskPolicyConfigDataResponse = ajaxCallGet("getLocalDiskPolicyConfigDetails.html", true, "json");
			if(localDiskPolicyConfigDataResponse){
				localDiskPolicyValues.push({value:null,label: LABEL_SELECT});
				dojo.forEach(JSON.parse("["+localDiskPolicyConfigDataResponse+"]"),function(eachLocalDiskPolicy,i){
					localDiskPolicyValues.push({value: eachLocalDiskPolicy.id,	label: eachLocalDiskPolicy.name});
				});
			}
			
			var bootPolicyConfigDataResponse = ajaxCallGet("getBootPolicyConfigDetails.html", true, "json");
			if(bootPolicyConfigDataResponse){
				bootPolicyValues.push({value:null,label: LABEL_SELECT});
				dojo.forEach(JSON.parse("["+bootPolicyConfigDataResponse+"]"),function(eachBootPolicy,i){
					bootPolicyValues.push({value:eachBootPolicy.id,	label:eachBootPolicy.name});
				});
			}
			
			var serverPoolConfigDataResponse = ajaxCallGet("getServerPoolConfigDetails.html", true, "json");
			if(serverPoolConfigDataResponse){
				serverPoolValues.push({value:null,label:LABEL_SELECT});
				dojo.forEach(JSON.parse("["+serverPoolConfigDataResponse+"]"),function(eachServerPool,i){
					serverPoolValues.push({value:eachServerPool.id,	label:eachServerPool.name});
				});
			}
			
			var vhbaConfigDataResponse = ajaxCallGet("getSanVhbaDetails.html", true, "json");
			if(vhbaConfigDataResponse){
				dojo.forEach(JSON.parse("["+vhbaConfigDataResponse+"]"),function(eachVhba,i){
					vhbaValuesList.push({value:eachVhba.id,	label:eachVhba.name});
				});
			}
			 
			var vnicConfigDataResponse = ajaxCallGet("getLanVnicDetails.html", true, "json");
			if(vnicConfigDataResponse){
				dojo.forEach(JSON.parse("["+vnicConfigDataResponse+"]"),function(eachvnic,i){
					vnicValues.push({value:eachvnic.id,	label:eachvnic.name});
				});
			}

			var wwnnConfigDataResponse = ajaxCallGet("getSanWwnnConfigDetails.html", true, "json");
			if(wwnnConfigDataResponse){
				WWNNPoolValues.push({value:null,label:LABEL_SELECT});
				dojo.forEach(JSON.parse("["+wwnnConfigDataResponse+"]"),function(eachWwnn,i){
					WWNNPoolValues.push({value:eachWwnn.id,	label:eachWwnn.wwnnName});
				});
			}
			
			var uuidPoolConfigDataResponse = ajaxCallGet("getUUIDPoolConfigDetails.html", true, "json");
			if(uuidPoolConfigDataResponse){
				//uuidValues.push({value:null,label:"&nbsp;"});
				dojo.forEach(JSON.parse("["+uuidPoolConfigDataResponse+"]"),function(eachUuidPool,i){
					uuidValues.push({value:eachUuidPool.id,	label:eachUuidPool.name});
				});
			}
			
			var sanConnectivityPolicyConfigDataResponse = ajaxCallGet("getSanConnectivityPolicyDetails.html", true, "json");			  
			if(sanConnectivityPolicyConfigDataResponse){
				connectivityPolicyValues.push({value:null,label: LABEL_SELECT});
				dojo.forEach(JSON.parse("["+sanConnectivityPolicyConfigDataResponse+"]"),function(eachConnectivityPolicy,i){
					connectivityPolicyValues.push({value: eachConnectivityPolicy.id,	label: eachConnectivityPolicy.name});
				});
			}
			
			var lanConnectivityPolicyConfigDataResponse = ajaxCallGet("getLanConnectivityPolicyDetails.html", true, "json");			
			if(lanConnectivityPolicyConfigDataResponse){				
				lanConnectivityPolicyValues.push({value:null,label: LABEL_SELECT});				
				dojo.forEach(JSON.parse("["+lanConnectivityPolicyConfigDataResponse+"]"),function(eachLanConnectivityPolicy,i){
					lanConnectivityPolicyValues.push({value: eachLanConnectivityPolicy.id,	label: eachLanConnectivityPolicy.name});
				});
			}
			// Calculating the max used ServersPool Policy name
			var profileTemplateMax = 0; 
			var count = serviceTemplateConfigDataResponse.length;
			if(count > 0){
				var lastNameUsed = JSON.parse(serviceTemplateConfigDataResponse[count-1]).name;
				profileTemplateMax = extractNumericValueFromAlphaNumericString(lastNameUsed);
			}
			
			var templateType=[];
			templateType.push({value:"initial-template",	label:"Initial Template"});
			templateType.push({value:"updating-template",	label:"Updating Template"});
			
			var serversServiceTempColumns = [
                {label: 'dbID',	attr: 'id',	hidden:true	},
                {label: 'Name',attr: 'name',sorted: 'ascending',width: "9%",vAlignment: "middle",align:'center',editable: false},
                {label: 'Description',attr: 'description',sorted: 'ascending',width: "10%",vAlignment: "middle",align:'center',editable: false},
                {label: 'Type',attr: 'type',sorted: 'ascending',width: "10%",vAlignment: "middle",align:'center',editable: false,hidden:true},                
                {label: 'Server Pool',attr: 'serversServerPool',width: "10%",vAlignment: "middle",align:'center',formatter: formatterGetServerPoolName,editable: false,hidden:true},
                {label: 'Local Disk<br />Policy',attr: 'serversLocalDisk',width: "10%",vAlignment: "middle",align:'center',formatter: formatterGetLocalDiskPolicyName,editable: false,hidden:true},
                {label: 'vNIC',attr: 'lanVnic',vAlignment: "middle",width: "10%",align:'center',editable: false,formatter: formatterGetVnicTemplateName},
                {label: 'LAN Connectivity<br />Policy',attr: 'lanConnectivityPolicy',width: "13%",vAlignment: "middle",align:'center',editable: false,formatter: formatterGetLanConnectivityPolicyName},
                {label: 'vHBA',attr: 'sanVhba',vAlignment: "middle",width: "9%",align:'center',editable: false,formatter: formatterGetMultipleVhbaNames},                
                {label: 'SAN Connectivity<br />Policy',attr: 'sanConnectivityPolicy',width: "13%",vAlignment: "middle",align:'center',editable: false,formatter: formatterGetConnectivityPolicyName},
                {label: 'WWNN',attr: 'sanWwnn',vAlignment: "middle",align:'center',editable: false,formatter: formatterGetWWNNName,hidden:true},               	               	
               	{label: 'Boot Policy',attr: 'serversBootPolicy',width: "10%",vAlignment: "middle",align:'center',formatter: formatterGetBootPolicyName,editable: false},
               	{label: 'UUID',attr: 'serversUuidPool',vAlignment: "middle",align:'center',width: "8%",editable: false,formatter: formatterGetUUIDName},
               	{label: 'Hostfirmware Package',attr: 'hostFirmwarePackage',vAlignment: "middle",align:'center',width: "9%",editable: false,formatter: formatterGetHostfirmwareName,hidden:true},
               	{label: 'Bios Policy',attr: 'biosPolicy',vAlignment: "middle",align:'center',width: "9%",editable: false,formatter: formatterGetBiosPolicyName,hidden:true},
               	{label: 'Maintenance Policy',attr: 'maintenancePolicy',vAlignment: "middle",align:'center',width: "9%",editable: false,formatter: formatterGetMaintenancePolicyName,hidden:true},
               	{label: 'Organization',attr: 'organizations',vAlignment: "middle",align:'center',width: "10%",editable: false,formatter: formatterGetOrgName}
          ];		

			
function formatterGetMaintenancePolicyName(data, item, store){
	return returnFormatterDropDownLabel(maintenancePolicyValues,data, item, store);
}
function formatterGetBiosPolicyName(data, item, store){
	return returnFormatterDropDownLabel(biosPolicyValues,data, item, store);
}
function formatterGetHostfirmwareName(data, item, store){
	return returnFormatterDropDownLabel(hostfirmwareValues,data, item, store);
}
function formatterGetBootPolicyName(data, item, store){
	return returnFormatterDropDownLabel(bootPolicyValues,data, item, store);
}
function formatterGetServerPoolName(data, item, store){
	return returnFormatterDropDownLabel(serverPoolValues,data, item, store);
}
function formatterGetLocalDiskPolicyName(data, item, store){
	return returnFormatterDropDownLabel(localDiskPolicyValues,data, item, store);
}
function formatterGetWWNNName(data, item, store){
	return returnFormatterDropDownLabel(WWNNPoolValues,data, item, store);
}
function formatterGetConnectivityPolicyName(data, item, store){
	return returnFormatterDropDownLabel(connectivityPolicyValues,data, item, store);
}
function formatterGetUUIDName(data, item, store){
	return returnFormatterDropDownLabel(uuidValues,data, item, store);
}
function formatterGetLanConnectivityPolicyName(data, item, store){
	return returnFormatterDropDownLabel(lanConnectivityPolicyValues,data, item, store);
}
// Generate data
function serversServiceTempGenerateData(){
	var formObject2 =  dojo.formToObject("serversServiceTempTableForm");
	if(dijit.byId("serversServiceTempTableForm").validate()==false || formObject2.noOfServiceProTemp == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	
 	var selectedOrg = parseInt(formObject2.serversServiceTempOrganization);
 	var noOfServiceProTemp = parseInt(formObject2.noOfServiceProTemp);

 	for(i = 1 ; i <= noOfServiceProTemp ; i++){
 		profileTemplateMax++;
		var templateName = "Template_"+profileTemplateMax;
 		if(!checkTableFieldValueUnique(serversServiceTempDataStoreTab,"name",templateName)){
 			continue;
 		}
 		var serversServiceTempDataGenreate = {
 				"id":0,
 				"name":	templateName,
 				"description": "Profile_Template Desc "+profileTemplateMax,
 				"type": templateType[1].value,
				"serversBootPolicy":bootPolicyValues[0].value,
 				"serversServerPool":serverPoolValues[0].value,
 				"serversLocalDisk" : localDiskPolicyValues[0].value,
 				"sanVhba": null, //vhbaValuesList[0].value,
 				"lanVnic": null, //vnicValues[0].value,
 				"sanWwnn":(WWNNPoolValues.length == 0)? null :WWNNPoolValues[0].value,
 				"sanConnectivityPolicy":connectivityPolicyValues[0].value,
 				"lanConnectivityPolicy":lanConnectivityPolicyValues[0].value,
 				"serversUuidPool":(uuidValues.length == 0)? null :uuidValues[0].value,
 				"hostFirmwarePackage":(hostfirmwareValues.length == 0)? null: hostfirmwareValues[0].value,
 				"maintenancePolicy":(maintenancePolicyValues.length == 0)? null: maintenancePolicyValues[0].value,
 				"biosPolicy":(biosPolicyValues.length == 0)? null: biosPolicyValues[0].value,
 				"organizations":selectedOrg
 			};
 		serversServiceTempTable.store.newItem(serversServiceTempDataGenreate);
 	}
 	serversServiceTempDataStoreTab.save();
 	serversServiceTempTable.refresh();
 	
 }
 
var serversSPTEditedItem;
var isDeselectedRow = false;
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 	
		    		/* Add Option for Edit Form */
		    		var selectOrganizationArr = getOgranizationDropDown();
	    		 	serversServiceTempOrganization.addOption(selectOrganizationArr);
		    		serversSPTEditTableRowTempType.addOption(templateType);
		    		serversSPTEditTableRowBootPolicy.addOption(bootPolicyValues);
		    		serversSPTEditTableRowServerPool.addOption(serverPoolValues);
		    		serversSPTEditTableRowLocalDiskPolicy.addOption(localDiskPolicyValues);
		    		serversSPTEditTableRowVHBAMulti.addOption(vhbaValuesList);
		    		serversSPTEditTableRowVnic.addOption(vnicValues);		    		  
		    		serversSPTEditTableRowConnPolicy.addOption(connectivityPolicyValues);		    		   
		    		serversSPTEditTableRowLanConnPolicy.addOption(lanConnectivityPolicyValues);
		    		serversSPTEditTableRowWWNN.addOption(WWNNPoolValues);
		    		serversSPTEditTableRowWUUID.addOption(uuidValues);
		    		serversSPTEditTableRowHostfirmware.addOption(hostfirmwareValues);
		    		serversSPTEditTableRowBiosPolicy.addOption(biosPolicyValues);
		    		serversSPTEditTableRowMaintenancePolicy.addOption(maintenancePolicyValues);
		    		serversSPTEditTableRowOrg.addOption(selectOrganizationArr);
	    			// End for populating data
	    		
	    		 	serversServiceTempDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversServiceTempTablejson = returnChangedDataFromDataStore(this,json);
		    			console.log(serversServiceTempTablejson);
		    			var response = ajaxCallPostWithJsonContent("manageServiceTemplateConfig.html" , serversServiceTempTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
			     	};
			
	     	dojo.connect(serversServiceTempTable,"onDeselect",function(item){
		    	 isDeselectedRow = true;
		     });
			dojo.connect(serversServiceTempTable,"onClick",function(item){
				if(serversServiceTempTable.isSelected(item) || isDeselectedRow){
					isDeselectedRow = false;	
					return false;
				}
				serversSPTEditedItem = item;
				serversSPTEditTableRowName.set("value",item.name);
				serversSPTEditTableRowDescription.set("value",item.description);
				serversSPTEditTableRowTempType.set("value",item.type);
				serversSPTEditTableRowBootPolicy.set("value",item.serversBootPolicy);
				serversSPTEditTableRowServerPool.set("value",item.serversServerPool);
				serversSPTEditTableRowLocalDiskPolicy.set("value",item.serversLocalDisk);
				
	   			serversSPTEditTableRowVHBAMulti.removeOption();
   				serversSPTEditTableRowVHBAMulti.reset();
   				console.log(item);
   				if(item.sanVhba != undefined && item.sanVhba[0] != null){
					serversSPTEditTableRowVHBAMulti.set("value", item.sanVhba);
	   			 }
   				serversSPTEditTableRowVnic.removeOption();
   				serversSPTEditTableRowVnic.reset();
	   			 if(item.lanVnic != undefined && item.lanVnic[0] != null){
	   				serversSPTEditTableRowVnic.set("value", item.lanVnic);
	   			 }
				serversSPTEditTableRowWWNN.set("value",item.sanWwnn);
				serversSPTEditTableRowConnPolicy.set("value",item.sanConnectivityPolicy);
				serversSPTEditTableRowLanConnPolicy.set("value",item.lanConnectivityPolicy);
				serversSPTEditTableRowWUUID.set("value",item.serversUuidPool);
				serversSPTEditTableRowHostfirmware.set("value",item.hostFirmwarePackage);
				serversSPTEditTableRowBiosPolicy.set("value",item.biosPolicy);
				serversSPTEditTableRowMaintenancePolicy.set("value",item.maintenancePolicy);
				serversSPTEditTableRowOrg.set("value",item.organizations);
				
				dojo.query("#addIdForChangeTableHeaderHeightForSPT").style("display", "none");
				dojo.byId("nameOfSPTRow").innerHTML = item.name;
				dojo.query("#serversSPTEditTableRowDiv").style("display","block");
			});
			
			dojo.connect(serversSPTEditTableRowConnPolicy,"onChange",function(){
				
				if(this.value !== null && this.value !== ""){
					serversSPTEditTableRowVHBAMulti.removeOption();
					serversSPTEditTableRowVHBAMulti.reset();
					serversSPTEditTableRowVHBAMulti.removeOption();
					serversSPTEditTableRowVHBAMulti.reset();
					
					serversSPTEditTableRowVHBAMulti.set("disabled",true);
					serversSPTEditTableRowWWNN.set({value:null,disabled: true});
				}
				else{
					serversSPTEditTableRowVHBAMulti.set("disabled",false);
					serversSPTEditTableRowWWNN.set({value:null,disabled: false});
				}
				
			});
			
			dojo.connect(serversSPTEditTableRowLanConnPolicy,"onChange",function(){
				
				if(this.value !== null && this.value !== ""){
					serversSPTEditTableRowVnic.removeOption();
					serversSPTEditTableRowVnic.reset();
					serversSPTEditTableRowVnic.removeOption();
					serversSPTEditTableRowVnic.reset();
					
					serversSPTEditTableRowVnic.set("disabled",true);					
				}
				else{
					serversSPTEditTableRowVnic.set("disabled",false);					
				}
				
			});

	    	 },1000);
	 
	     });

function serversSPTEditTableRowCancelBtnFun(){
	serversSPTEditTableRowVHBAMulti.removeOption();
	serversSPTEditTableRowVHBAMulti.reset();
	//
	serversSPTEditTableRowVnic.removeOption();
   	serversSPTEditTableRowVnic.reset();
		
	dojo.query("#serversSPTEditTableRowDiv").style("display","none");
	dojo.query("#addIdForChangeTableHeaderHeightForSPT").style("display", "block");
	serversServiceTempTable.refresh();
}
function serversSPTEditTableRowSaveData(){
	var formJsonObj = dojo.formToObject("serversSPTEditTableRowForm");
	if(dijit.byId("serversSPTEditTableRowForm").validate()==false ){
		return false;
	}
	if(!checkTableFieldValueUnique(serversServiceTempDataStoreTab,"name",formJsonObj.name) && serversSPTEditedItem.name != formJsonObj.name){
		displayNotificationAlert(MSG_DUPLICATE_NAME);
		return false;
	}
	var SPTName = formJsonObj.name;
	var SPTDesc = formJsonObj.description;
	var SPTType = formJsonObj.type;
	var SPTServersBootPolicy = (formJsonObj.serversBootPolicy=="" || formJsonObj.serversBootPolicy == null)? null :parseInt(formJsonObj.serversBootPolicy);
	var SPTServerPool = (formJsonObj.serversServerPool == "" || formJsonObj.serversServerPool == null)?null : parseInt(formJsonObj.serversServerPool);
	var SPTLocalDisk = (formJsonObj.serversLocalDisk == "" || formJsonObj.serversLocalDisk == null)?null:parseInt(formJsonObj.serversLocalDisk);
	var SPTvHBATemp = [];
	if( formJsonObj.sanVhba != undefined && (formJsonObj.sanVhba).length != 0 && formJsonObj.sanVhba != "" ){
		dojo.forEach(formJsonObj.sanVhba,function(val,i){
			SPTvHBATemp[i] = parseInt(val);
		});
	}
	var SPTvNIC = [];
	if( formJsonObj.lanVnic != undefined && (formJsonObj.lanVnic).length != 0 && formJsonObj.lanVnic != "" ){
		dojo.forEach(formJsonObj.lanVnic,function(val,i){
			SPTvNIC[i] = parseInt(val);
		});
	}
	var SPTWWNN = (formJsonObj.sanWwnn == undefined || formJsonObj.sanWwnn == "" || formJsonObj.sanWwnn == null )?null : parseInt(formJsonObj.sanWwnn);
	var SPTConnPolicy = (formJsonObj.sanConnectivityPolicy == "" || formJsonObj.sanConnectivityPolicy == null)?null : parseInt(formJsonObj.sanConnectivityPolicy);
	var SPTUUID = parseInt(formJsonObj.serversUuidPool);
	var SPTHostfirmware = parseInt(formJsonObj.hostFirmwarePackage);
	var SPTBiosPolicy = parseInt(formJsonObj.biosPolicy);
	var SPTMaintenancePolicy = parseInt(formJsonObj.maintenancePolicy);
	var SPTOrg = parseInt(formJsonObj.organizations);
	var SPTLanConnPolicy = (formJsonObj.lanConnectivityPolicy == "" || formJsonObj.lanConnectivityPolicy == null)?null : parseInt(formJsonObj.lanConnectivityPolicy);
	
	var sendEditedFormValue = {name:SPTName,description:SPTDesc,type:SPTType,serversBootPolicy:SPTServersBootPolicy,serversServerPool:SPTServerPool,serversLocalDisk:SPTLocalDisk,
			sanVhba:SPTvHBATemp,lanVnic:SPTvNIC,sanWwnn:SPTWWNN,sanConnectivityPolicy:SPTConnPolicy,lanConnectivityPolicy:SPTLanConnPolicy,serversUuidPool:SPTUUID,hostFirmwarePackage:SPTHostfirmware,biosPolicy:SPTBiosPolicy,maintenancePolicy:SPTMaintenancePolicy,organizations:SPTOrg};
	 updateDataStoreWithJsonObj(serversServiceTempDataStoreTab, serversSPTEditedItem, sendEditedFormValue);
	
	serversSPTEditTableRowVHBAMulti.removeOption();
	serversSPTEditTableRowVHBAMulti.reset();
	//
	serversSPTEditTableRowVnic.removeOption();
   	serversSPTEditTableRowVnic.reset();
	dojo.query("#serversSPTEditTableRowDiv").style("display","none");
	dojo.query("#addIdForChangeTableHeaderHeightForSPT").style("display", "block");
	serversServiceTempTable.refresh();
}
// function for Save data to servere
function saveServersServiceTempConfigData(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":9});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	 console.log("LocalDiskPolicy Save");
}


</script>
</head>
<body>
	<div id="parentDiv" class="tundraCssForMultiSelect widtInPercent">
		<div class="floatleft addCssIntreeTable" style="width: 98%;padding-left:5px;">
			<fieldset class="heightOfFieldset" style="width: 99%;;">
				<legend>Service Profile Template Configuration</legend>

				<div class="vnicTemplateConfig">
					<form id="serversServiceTempTableForm"
						data-dojo-id="serversServiceTempTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversServiceTempOrganization"
									name="serversServiceTempOrganization"
									data-dojo-id="serversServiceTempOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of Service Profile
											Templates:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfServiceProTemp" name="noOfServiceProTemp"
										data-dojo-id="noOfServiceProTemp" style="width: 120px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversServiceTempGenerateDataBtn"
										onClick="serversServiceTempGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<!--  Edit SPT table row form -->
				<div class="commonclassForFormFields" id="serversSPTEditTableRowDiv" style="display: none;padding-top: 20px;">
					<div class="pageHeader"><h2>Editing SPT: <span id="nameOfSPTRow"></span></h2></div>
						<form id="serversSPTEditTableRowForm"
							data-dojo-id="serversSPTEditTableRowForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table style="padding-top: 10px; width: 100%;">
							<tr>
							<td height="30">
									<div class="labelspace">
										<label style="float: left;">Name:<em>*</em></label>
									</div>
								</td>
								<td height="30">
									<div id="serversSPTEditTableRowName"
										data-dojo-id="serversSPTEditTableRowName"
										name="name" style="width: 150px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", required:"true", invalidMessage:MSG_NAME'></div>
								</td>
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">Description:</label>
									</div>
								</td>
								<td height="30">
									<div id="serversSPTEditTableRowDescription"
										data-dojo-id="serversSPTEditTableRowDescription"
										name="description" style="width: 150px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"45" '></div>
								</td>
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">Template Type:</label>
									</div>
								</td>
								<td height="30"><select id="serversSPTEditTableRowTempType"
									data-dojo-id="serversSPTEditTableRowTempType"
									name="type"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px" /></td>
								</tr>
								
							<tr>
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">Boot Policy:</label>
									</div>
								</td>
								<td><select id="serversSPTEditTableRowBootPolicy"
									data-dojo-id="serversSPTEditTableRowBootPolicy"
									name="serversBootPolicy"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px; border: 1px solid #b4b4b4;" /></td>
									
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">Server Pool:</label>
									</div>
								</td>
								<td>
									<select id="serversSPTEditTableRowServerPool"
										data-dojo-id="serversSPTEditTableRowServerPool"
										name="serversServerPool"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
								</td>
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">Local Disk Policy:</label>
									</div>
								</td>
								<td>
									<select id="serversSPTEditTableRowLocalDiskPolicy"
										data-dojo-id="serversSPTEditTableRowLocalDiskPolicy"
										name="serversLocalDisk"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
								</td>
								</tr>
								
								<tr>
									<td height="30">
									<div class="labelspace">
										<label style="float: left;">SAN Connectivity Policy:</label>
									</div>
									</td>
									<td><select id="serversSPTEditTableRowConnPolicy"
										data-dojo-id="serversSPTEditTableRowConnPolicy"
										name="sanConnectivityPolicy"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" /></td>
									<td height="30">
									<div class="labelspace">
										<label style="float: left;">vHBA:</label>
									</div>
								</td>
								<td>
									<div id="serversSPTEditTableRowVHBATempMultiSelForm">
										<select id="serversSPTEditTableRowVHBAMulti"
											data-dojo-id="serversSPTEditTableRowVHBAMulti"
											data-dojo-type="dojox/form/CheckedMultiSelect"
											data-dojo-props="dropDown:true,multiple:true, required:false"
											name="sanVhba">
										</select>
									</div>
								</td>
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">WWNN Pool:</label>
									</div>
								</td>
								<td><select id="serversSPTEditTableRowWWNN"
									data-dojo-id="serversSPTEditTableRowWWNN"
									name="sanWwnn"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px; border: 1px solid #b4b4b4;" /></td>
									
								</tr>
								<tr>
									<td height="30">
									<div class="labelspace">
										<label style="float: left;">LAN Connectivity Policy:</label>
									</div>
									</td>
									<td><select id="serversSPTEditTableRowLanConnPolicy"
										data-dojo-id="serversSPTEditTableRowLanConnPolicy"
										name="lanConnectivityPolicy"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" /></td>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">vNIC:</label>   
										</div>
									</td>
									<td>
										<div id="serversSPTEditTableRowVnicMultiSelForm">
											<select id="serversSPTEditTableRowVnic"
												data-dojo-id="serversSPTEditTableRowVnic"
												data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"
												name="lanVnic">
											</select>
										</div>
									</td>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">UUID:</label>
										</div>
									</td>
									<td><select id="serversSPTEditTableRowWUUID"
										data-dojo-id="serversSPTEditTableRowWUUID"
										name="serversUuidPool"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
									</td>
									
								</tr>
								<tr>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">Hostfirmware Package:</label>
										</div>
									</td>
									<td><select id="serversSPTEditTableRowHostfirmware"
										data-dojo-id="serversSPTEditTableRowHostfirmware"
										name="hostFirmwarePackage"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
									</td>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">Bios Policy:</label>
										</div>
									</td>
									<td><select id="serversSPTEditTableRowBiosPolicy"
										data-dojo-id="serversSPTEditTableRowBiosPolicy"
										name="biosPolicy"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
									</td>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">Maintenance Policy:</label>
										</div>
									</td>
									<td><select id="serversSPTEditTableRowMaintenancePolicy"
										data-dojo-id="serversSPTEditTableRowMaintenancePolicy"
										name="maintenancePolicy"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
									</td>
									
								</tr>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Organizations:</label>
										</div>
									</td>
									<td><select id="serversSPTEditTableRowOrg"
										name="organizations"
										data-dojo-id="serversSPTEditTableRowOrg"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px" />
									</td>
								</tr>
								<tr>
									<td height="55" colspan="5" style="padding-left: 25px;" colspan="2">
									<button dojoType="xwt.widget.form.TextButton" 
										id="serversSPTEditTableRowSaveDataBtn" baseClass="defaultButton"
										onClick="serversSPTEditTableRowSaveData();">Save</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button dojoType="xwt.widget.form.TextButton" 
										id="serversSPTEditTableRowCalcelBtn"
										onClick="serversSPTEditTableRowCancelBtnFun();">Cancel</button>
									</td>
								</tr>
							</table>
						</form>
				</div>
				<!-- End Edit SPT form  -->
				<div class="floatleft widtInPercent" id="addIdForChangeTableHeaderHeightForSPT"
					style="padding-left: 0px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 100%; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversServiceTempDataStoreTab"
							data="serversServiceTempDataTable"></span>
						<div style="width:100%; !important;"
							id="serversServiceTempTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversServiceTempTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversServiceTempTable"
							data-dojo-id="serversServiceTempTable"
							dojoType="xwt.widget.table.Table"
							store="serversServiceTempDataStoreTab"
							structure="serversServiceTempColumns"
							style="width: 100%; height: 238px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>
 
	</div>
</body>
</html>