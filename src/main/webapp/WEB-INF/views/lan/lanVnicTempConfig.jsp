<%-- lanVnicTempConfig.jsp --%>
<script type="text/javascript">
			dojo.require("xwt.widget.table.Table");
			dojo.require("xwt.widget.table.Toolbar");
			dojo.require("dojo.data.ItemFileReadStore");
			dojo.require("dijit.form.Button");
			dojo.require("dijit.form.Form");
			dojo.require("xwt.widget.notification.ValidationTextBox");
			dojo.require("xwt.widget.notification.Form");
			dojo.require("dojox.form.CheckedMultiSelect");
			
var noOfvNICs = 0; 
var maxeth = 0, maxnic = 0;
var vnicConfigDataResponse = ajaxCallGet("getLanVnicTemplateConfigDetails.html", true, "json");
var vnicTempDataTable = { items:JSON.parse("[" + vnicConfigDataResponse + "]") };
var macpoolConfigDataResponse = ajaxCallGet("getLanMacpoolConfigDetails.html", true, "json");
var macPoolDataTable = { items:JSON.parse("[" + macpoolConfigDataResponse + "]") };
var networkControlPolicyDataResponse = ajaxCallGet("getLanNetworkControlPolicyDetails.html", true, "json");
var networkControlPolicyDataTable = { items:JSON.parse("[" + networkControlPolicyDataResponse + "]") };
var qosPolicyDataResponse = ajaxCallGet("getLanQosPolicyDetails.html", true, "json");
var qosPolicyDataTable = { items:JSON.parse("[" + qosPolicyDataResponse + "]") };
var getVlanConfigDataResponse = ajaxCallGet("getLanVlanConfigDetails.html", true, "json");
var getVlanData = { items:JSON.parse("[" + getVlanConfigDataResponse + "]") };

//Calculating the max used vNIC name
dojo.forEach(vnicTempDataTable.items,function(obj , i){
	var alphanumericName = obj.vnictName;
	var numericVal = extractNumericValueFromAlphaNumericString(alphanumericName);
	var name  = alphanumericName.replace(/[0-9]/g, '');
	if(name == "nic"){
		if(numericVal > maxnic){
			maxnic = numericVal+1;
		}
	}
	if(name == "eth"){
		if(numericVal > maxeth){
			maxeth = numericVal+1;
		}
	}
});

var switchId=[],target=[],templateType=[],macpoolname=[],networkcontrolpolicyname=[],qospolicyname=[],osTypeArr=[],vlanDropdownList=[];

	osTypeArr.push({value:"Windows",	label:"Windows"});
	osTypeArr.push({value:"Linux",	label:"Linux"});
	osTypeArr.push({value:"VMware",	label:"VMware"});
	
	switchId.push({value:"A",	label:"A"});
	switchId.push({value:"B",	label:"B"});
	switchId.push({value:"A-B",	label:"A-B"});
	switchId.push({value:"B-A",	label:"B-A"});

	target.push({value:"Adapter",	label:"Adapter"});
	target.push({value:"VM",	label:"VM"});
	
	templateType.push({value:"initial-template",	label:"Initial Template"});
	templateType.push({value:"updating-template",	label:"Updating Template"});

	macpoolname.push({value:null,label: LABEL_SELECT});
	dojo.forEach(macPoolDataTable.items,function(obj , i){
		macpoolname.push({value:obj.id,	label:obj.macpoolName});
	});
	
	networkcontrolpolicyname.push({value:null,label: LABEL_SELECT});
	dojo.forEach(networkControlPolicyDataTable.items,function(obj , i){
		networkcontrolpolicyname.push({value:obj.id,	label:obj.ncpName});
	});
	
	qospolicyname.push({value:null,label: LABEL_SELECT});
	dojo.forEach(qosPolicyDataTable.items,function(obj , i){
		qospolicyname.push({value:obj.id,	label:obj.name});
	});
	//var tempVlanMultiSel = [];
	//vlanDropdownList.push({value:null,label: LABEL_SELECT});
	//tempVlanMultiSel.push({value:null,label: LABEL_SELECT});
	dojo.forEach(getVlanData.items,function(obj , i){
		vlanDropdownList.push({value:obj.id,label:obj.vlanName});
		//tempVlanMultiSel.push({value:obj.id,label:obj.vlanName});
	});
	//var multiselectVlanDrop = tempVlanMultiSel.splice(1,vlanDropdownList.length);
	
	var vnicTempColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'vnictName',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: false},
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: false},
                   {label: 'VLAN',attr: 'vlanId',sorted: 'ascending',width: 170,vAlignment: "middle",align:'center',editable: false, formatter: formatterGetVlanMutipleName},
                   {label: 'MAC Pool',attr: 'lanMacpool',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: false, formatter: formatterGetMacPoolName},
                   {label: 'Network<br/>Control Policy',attr: 'lanNetworkControlPolicy',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: false, formatter: formatterGetNetworkControlPolicyName},
                   {label: 'QoS<br/>Policy',attr: 'lanQosPolicy',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: false, formatter: formatterGetQosPolicyName},
                   {label: 'OS Type',attr: 'osType',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
                   {label: 'FI Id',attr: 'switchId',sorted: 'ascending',width: 60,vAlignment: "middle",align:'center',editable: false,hidden:true},
                   {label: 'Target',attr: 'target',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: false},
                   {label: 'Template Type',attr: 'templateType',width: 150,vAlignment: "middle",align:'center',editable: false,hidden:true},
                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: false,formatter: formatterGetOrgName}
                   ];		

function formatterGetMacPoolName(data, item, store){
	return returnFormatterDropDownLabel(macpoolname,data, item, store);
}
function formatterGetNetworkControlPolicyName(data, item, store){
	return returnFormatterDropDownLabel(networkcontrolpolicyname,data, item, store);
}

function formatterGetQosPolicyName(data, item, store){
	return returnFormatterDropDownLabel(qospolicyname,data, item, store);
}

function vnicTempGenerateData(){
	var vnicTempFormObj =  dojo.formToObject("vnicTempTableForm");
	if(dijit.byId("vnicTempTableForm").validate()==false || vnicTempFormObj.noOfVnic == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
 	for(i = 0 ; i < parseInt(vnicTempFormObj.noOfVnic) ; i++){
 		var osType = vnicTempFormObj.osType;
 		var vnictName = (osType == "Windows" || osType == "VMware")?"nic"+maxnic:"eth"+ maxeth;
 		if(!checkTableFieldValueUnique(vnicTempDataStoreTab,"vnictName",vnictName)){
 			if(osType == "Windows" || osType == "VMware"){
 				maxnic++;
 				vnictName = "nic"+maxnic;
 			} else{
 				maxeth++;
 				vnictName = "eth"+ maxeth;
 			}
 		}
 		var vnicTempDataTableDefItem = {
 					"id":0,
 					"vnictName":	vnictName,
 					"description": vnictName,
 					"lanMacpool": macpoolname[0].value,
 					"lanNetworkControlPolicy": networkcontrolpolicyname[0].value, 
 					"lanQosPolicy": qospolicyname[0].value,
 					"vlanId": null,
 					"switchId": (i%2 == 0)?"A":"B",
 					"target":"Adapter",
 					"templateType":templateType[1].value,
 					"osType": vnicTempFormObj.osType,
 					"organizations":parseInt(vnicTempFormObj.vnicTemplateOrganization)
 		};
 		vnicTempConfigTable.store.newItem(vnicTempDataTableDefItem);
 		(osType == "Windows" || osType == "VM Ware")?maxnic++:maxeth++;
 	}
 	vnicTempDataStoreTab.save();
 	vnicTempConfigTable.refresh();
 	dijit.byId("noOfVnic").set("value","");
}

var lanVnicTempEditedItem;
var isDeselectedRow = false;

require(["dojo/ready", "dojo/_base/json"], 
		function(ready, json){
	    	setTimeout(function(){
	    		
	    		// ADD dropDown val for Organization //@Parameter is option JSId
    		 	var selectOrganizationArr = getOgranizationDropDown();
    		 	vnicTemplateOrganization.addOption(selectOrganizationArr);
    		 	
    		 	osType.addOption(osTypeArr);
    		 /* Add Option for Edit Form */
	    		 //adding option for temp typ
	    		 lanVnicTempEditTableRowTempType.addOption(templateType);
	    		//adding option for Vlan
	    		lanVnicTempEditTableRowVlan.addOption(vlanDropdownList);
	    		//adding option for MAC Pool
	    		lanVnicTempEditTableRowMacPool.addOption(macpoolname);
	    		//adding option for NWContrlPolicy
	    		lanVnicTempEditTableRowNetworkControlPolicy.addOption(networkcontrolpolicyname);
	    		//adding option for QoS
	    		lanVnicTempEditTableRowQosPolicy.addOption(qospolicyname);
	    		//adding option for Switch Id
	    		lanVnicTempEditTableRowSwitchId.addOption(switchId);
	    		//adding option for Target
	    		lanVnicTempEditTableRowTarget.addOption(target);
	    		//adding option for Org
	    		lanVnicTempEditTableRowOrg.addOption(selectOrganizationArr);
   	// End for populating data
    		 	
	    		vnicTempDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var vnicTable1json = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageLanVnicConfig.html" , vnicTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
			     
			     dojo.connect(vnicTempConfigTable,"onDeselect",function(item){
			    	 isDeselectedRow = true;
			     });
			     dojo.connect(vnicTempConfigTable,"onClick",function(item){
			    	 
			    	 if(vnicTempConfigTable.isSelected(item) || isDeselectedRow){
							isDeselectedRow = false;	
							return false;
						}
						lanVnicTempEditedItem = item;
						lanVnicTempEditTableRowVlan.removeOption();
						lanVnicTempEditTableRowVlan.reset();
						
						lanVnicTempEditTableRowForm.set('value', item);
						lanVnicTempEditTableRowMacPool.set("value",item.lanMacpool);
						lanVnicTempEditTableRowNetworkControlPolicy.set("value",item.lanNetworkControlPolicy);
						lanVnicTempEditTableRowQosPolicy.set("value",item.lanQosPolicy);
						
						dojo.query("#lanVnicTableDataId").style("display", "none");
						dojo.byId("nameOfLanVnicRow").innerHTML = item.vnictName;
						dojo.query("#lanVnicTempEditTableRowDiv").style("display","block");
						
						
						
			     });
			    
		},1000);
});

function lanVnicTempEditTableRowCancelBtnFun(){
	lanVnicTempEditTableRowVlan.removeOption();
	lanVnicTempEditTableRowVlan.reset();
	
	dojo.query("#lanVnicTempEditTableRowDiv").style("display","none");
	dojo.query("#lanVnicTableDataId").style("display", "block");
	vnicTempConfigTable.refresh();
}
function lanVnicTempEditTableRowSaveData(){
	var formJsonObj = dojo.formToObject("lanVnicTempEditTableRowForm");
	if(dijit.byId("lanVnicTempEditTableRowForm").validate()==false ){
		return false;
	}
	if(!checkTableFieldValueUnique(vnicTempDataStoreTab,"vnictName",formJsonObj.vnictName) && lanVnicTempEditedItem.vnictName != formJsonObj.vnictName){
		displayNotificationAlert(MSG_DUPLICATE_NAME);
		return false;
	}
	
	var lanVnicName = formJsonObj.vnictName;
	var lanVnicDesc = formJsonObj.description;
	var lanVnicTempType = formJsonObj.templateType;
	
	var lanVnicMacpoolId = (formJsonObj.lanMacpool=="" || formJsonObj.lanMacpool == null)? null :parseInt(formJsonObj.lanMacpool);
	var lanVnicNetworkControlPolicyId = (formJsonObj.lanNetworkControlPolicy == "" || formJsonObj.lanNetworkControlPolicy == null)?null : parseInt(formJsonObj.lanNetworkControlPolicy);
	var lanVnicQosPolicyId = (formJsonObj.lanQosPolicy == "" || formJsonObj.lanQosPolicy == null)?null:parseInt(formJsonObj.lanQosPolicy);
	var lanVnicVlanId = [];
	if( formJsonObj.vlanId != undefined && (formJsonObj.vlanId).length != 0 && formJsonObj.vlanId != "" ){
		dojo.forEach(formJsonObj.vlanId,function(val,i){
			lanVnicVlanId[i] = parseInt(val);
		});
	}
	
	var lanVnicOsType = lanVnicTempEditedItem.osType[0];
	var lanVnicSwitchId = formJsonObj.switchId;
	var lanVnicTarget = formJsonObj.target;
	var lanVnicOrg = parseInt(formJsonObj.organizations);
	
	var sendEditedFormValue = {vnictName: lanVnicName,description: lanVnicDesc,templateType: lanVnicTempType,lanMacpool: lanVnicMacpoolId,lanNetworkControlPolicy: lanVnicNetworkControlPolicyId,lanQosPolicy: lanVnicQosPolicyId,
			vlanId: lanVnicVlanId,osType: lanVnicOsType,switchId: lanVnicSwitchId,target: lanVnicTarget, organizations: lanVnicOrg};
	 updateDataStoreWithJsonObj(vnicTempDataStoreTab, lanVnicTempEditedItem, sendEditedFormValue);
	 
	 lanVnicTempEditTableRowVlan.removeOption();
	 lanVnicTempEditTableRowVlan.reset();
	 dojo.query("#lanVnicTempEditTableRowDiv").style("display","none");
	 dojo.query("#lanVnicTableDataId").style("display", "block");
     vnicTempConfigTable.refresh();
	 
}
//Save data in back-end
function saveVnicTempConfData(){
	if( getDataStoreSize(vnicTempDataStoreTab) > 0 ){
		var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":6});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	}else{
		displayNotificationAlert(MSG_ATLEAST_ONvNIC);
		return false;
	}
}
	     

</script>

	<div id="parentDiv" class="tundraCssForMultiSelect">
 		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1100px;">
				<legend>vNIC Template Configuration</legend>  
		        <div class="vnicTemplateConfig">
		        	<form id="vnicTempTableForm" data-dojo-id="vnicTempTableForm" data-dojo-type="xwt.widget.notification.Form" name="tableForm">
				    	<table>
				        	<tr>
				        		<td>
				        			<div class="labelspace">
										<label style="float:left;">Organization:</label>			
									</div>
				        		</td>
				        		<td>
				        			<select id="vnicTemplateOrganization" name="vnicTemplateOrganization" data-dojo-id="vnicTemplateOrganization" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 140px" />
				        		</td>
				        		<td>
					        		<div class="labelspace">
										<label style="float:left;">No. Of vNIC Templates:<em>*</em></label>			
									</div>
								</td>
				       			<td>
				       				<div id="noOfVnic" name = "noOfVnic" data-dojo-id="noOfVnic" data-dojo-type="xwt.widget.notification.ValidationTextBox" data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
				       			</td>
				       			<td>
				       				<div class="labelspace">
										<label style="float:left;">OS type:</label>			
									</div>
			        			</td>
			        			<td>
			        				<select id="osType" name="osType" data-dojo-id="osType" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 140px" />
				        		</td>
				        		<td style="padding-left: 10px;">
				        			<button data-dojo-type="dijit/form/Button" data-dojo-id="vnicTempgenerateDataBtn" onClick="vnicTempGenerateData();" type="button">Add</button>
				        		</td>
				       		</tr>
				       	</table>
					</form>
				</div>
				
				<!--  Edit LANVNIC table row form -->
				<div class="commonclassForFormFields" id="lanVnicTempEditTableRowDiv" style="display: none;padding-top: 40px;">
					<div class="pageHeader"><h2>Editing LAN vNIC: <span id="nameOfLanVnicRow"></span></h2></div>
						<form id="lanVnicTempEditTableRowForm"
							data-dojo-id="lanVnicTempEditTableRowForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table style="padding-top: 10px; width: 100%;">
							<tr>
							<td height="40">
									<div class="labelspace">
										<label style="float: left;">Name:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="lanVnicTempEditTableRowName"
										data-dojo-id="lanVnicTempEditTableRowName"
										name="vnictName" style="width: 150px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", required:"true", invalidMessage:MSG_NAME'></div>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Description:</label>
									</div>
								</td>
								<td height="40">
									<div id="lanVnicTempEditTableRowDescription"
										data-dojo-id="lanVnicTempEditTableRowDescription"
										name="description" style="width: 150px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"45" '></div>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Template Type:</label>
									</div>
								</td>
								<td height="40"><select id="lanVnicTempEditTableRowTempType"
									data-dojo-id="lanVnicTempEditTableRowTempType"
									name="templateType"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px" /></td>
								</tr>
								
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">VLAN:</label>
									</div>
								</td>
								<td>
								<select id="lanVnicTempEditTableRowVlan"
											data-dojo-id="lanVnicTempEditTableRowVlan"
											data-dojo-type="dojox/form/CheckedMultiSelect"
											data-dojo-props="dropDown:true,multiple:true, required:false"
											name="vlanId">
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">MAC Pool:</label>
									</div>
								</td>
								<td>
									<select id="lanVnicTempEditTableRowMacPool"
										data-dojo-id="lanVnicTempEditTableRowMacPool"
										name="lanMacpool"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">N/W Control Policy:</label>
									</div>
								</td>
								<td>
									<select id="lanVnicTempEditTableRowNetworkControlPolicy"
										data-dojo-id="lanVnicTempEditTableRowNetworkControlPolicy"
										name="lanNetworkControlPolicy"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" />
								</td>
								</tr>
								
								<tr>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">QoS Policy:</label>
									</div>
									</td>
									<td><select id="lanVnicTempEditTableRowQosPolicy"
										data-dojo-id="lanVnicTempEditTableRowQosPolicy"
										name="lanQosPolicy"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 168px; border: 1px solid #b4b4b4;" /></td>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">OS Type:</label>
									</div>
								</td>
								<td>
										<div id="lanVnicTempEditTableRowOsType"
										data-dojo-id="lanVnicTempEditTableRowOsType"
										name="osType" style="width: 150px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='required:"true", disabled:"true" '></div>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">FI Id:</label>
									</div>
								</td>
								<td><select id="lanVnicTempEditTableRowSwitchId"
									data-dojo-id="lanVnicTempEditTableRowSwitchId"
									name="switchId"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px; border: 1px solid #b4b4b4;" /></td>
									
								</tr>
								<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Target:</label>
									</div>
								</td>
								<td>
									<select id="lanVnicTempEditTableRowTarget"
									data-dojo-id="lanVnicTempEditTableRowTarget"
									name="target"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px; border: 1px solid #b4b4b4;" />
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organizations:</label>
									</div>
								</td>
								<td><select id="lanVnicTempEditTableRowOrg"
									name="organizations"
									data-dojo-id="lanVnicTempEditTableRowOrg"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 168px" /></td>
								</tr>
								<tr>
									<td height="55" style="padding-left: 25px;" colspan="6">
									<button dojoType="xwt.widget.form.TextButton" 
										id="lanVnicTempEditTableRowSaveDataBtn" baseClass="defaultButton"
										onClick="lanVnicTempEditTableRowSaveData();">Save</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button dojoType="xwt.widget.form.TextButton" 
										id="lanVnicTempEditTableRowCalcelBtn"
										onClick="lanVnicTempEditTableRowCancelBtnFun();">Cancel</button>
									</td>
								</tr>
							</table>
						</form>
				</div>
				<!-- End Edit LANVNIC form  -->
				
				<div class="floatleft addClassForColumnHeight" id="lanVnicTableDataId" style="padding-left:4px;padding-top:30px;"> 
					<div dojotype="dijit.layout.ContentPane" region="left" style="width:1093px; overflow:hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore" jsId="vnicTempDataStoreTab" data="vnicTempDataTable"></span>
						<div style="width:1090px !important;"  id="vnicTempTable2TollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="vnicTempConfigTable" quickFilter="false">
							<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
						</div>
						<div id="vnicTempConfigTable" data-dojo-id="vnicTempConfigTable"  dojoType="xwt.widget.table.Table" store="vnicTempDataStoreTab"  structure="vnicTempColumns"  style="width: 1090px; height: 235px;" selectMultiple="true"  selectAllOption="true" 
							showIndex="false" selectModel="input" filterOnServer=false></div>
					</div>
				</div>	
			</fieldset>
		</div>
	</div>
