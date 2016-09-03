<%-- lanVnicConfig.jsp --%>
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
	
	var lanVnicTemplateArr = [],lanEthernetAdapterPolicyArr = [],lanVnicTemplateArr1=[],lanEthernetAdapterPolicyArr1=[];
	var lanEthernetAdapterPoliciesData = {items:JSON.parse("["+ ajaxCallGet("getLanEthernetAdapterPoliciesDetails.html", true, "json") +"]")}; 
	var lanVnicTemplateData = {items:JSON.parse("["+ ajaxCallGet("getLanVnicTemplateConfigDetails.html", true, "json") +"]")};
	
	lanVnicTemplateArr1.push({value: "",label: LABEL_SELECT});
	dojo.forEach(lanVnicTemplateData.items,function(obj , i){
		lanVnicTemplateArr.push({value:obj.id,	label:obj.vnictName});
		lanVnicTemplateArr1.push({value:obj.id,	label:obj.vnictName});
	});
	lanEthernetAdapterPolicyArr.push({value: null,label: LABEL_SELECT});
	lanEthernetAdapterPolicyArr1.push({value: "", label: LABEL_SELECT});
	dojo.forEach(lanEthernetAdapterPoliciesData.items,function(obj , i){
		lanEthernetAdapterPolicyArr.push({value:obj.id,	label:obj.name});  
		lanEthernetAdapterPolicyArr1.push({value:obj.id,	label:obj.name});
	});
	
	var lanVnicTableData = {items:JSON.parse("["+ ajaxCallGet("getLanVnicDetails.html", true, "json") +"]")};
	
	var lanVnicColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 300,vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'vNIC Template',attr: 'lanVnicTemplate',sorted: 'ascending',width: 350,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLanVnicTempName,
                	   editWidget: {
    						widget: xwt.widget.form.DropDown,
    						options: {options: lanVnicTemplateArr}
    		                   	  }
                   },
                   {label: 'Ethernet Adapter Policy',attr: 'lanEthernetAdapterPolicies',sorted: 'ascending',width: 350,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLanEthernetAdapterPolicyName,
                	   editWidget: {
    						widget: xwt.widget.form.DropDown,
    						options: {options: lanEthernetAdapterPolicyArr}
    		           }
                   }                   
	];		    

	function formatterGetLanVnicTempName(data, item, store){
		return returnFormatterDropDownLabel(lanVnicTemplateArr,data, item, store);
	}
	function formatterGetLanEthernetAdapterPolicyName(data, item, store){
		return returnFormatterDropDownLabel(lanEthernetAdapterPolicyArr,data, item, store);
	}

	function lanVnicGenerateData(){
		var lanVnicFormObj =  dojo.formToObject("lanVnicTableForm");
		console.log(lanVnicFormObj);
		if(dijit.byId("lanVnicTableForm").validate()==false || lanVnicFormObj.lanVnicName == "" || 
				lanVnicFormObj.lanVnicTemplateDrpDown == "" ){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		if(!checkTableFieldValueUnique(lanVnicDataStoreTab,"name",lanVnicFormObj.lanVnicName)){
			displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
			return false;
		}
		var lanVnicData = {
					"id":0,
					"name":	lanVnicFormObj.lanVnicName,
					"lanVnicTemplate" : parseInt(lanVnicFormObj.lanVnicTemplateDrpDown),
					"lanEthernetAdapterPolicies" : lanVnicFormObj.lanEthernetAdapterPolicyDrpDown != "" ?parseInt(lanVnicFormObj.lanEthernetAdapterPolicyDrpDown):null,
		};
		lanVnicTable.store.newItem(lanVnicData);
		
		lanVnicDataStoreTab.save();
		lanVnicTable.refresh();
	 	dijit.byId("lanVnicName").set("value","");
	}
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	     
	    	setTimeout(function(){	   		 	
	   		 	
	    		lanVnicTemplate.addOption(lanVnicTemplateArr1);
	    		lanEthernetAdapterPolicy.addOption(lanEthernetAdapterPolicyArr1);
	    		
		   		lanVnicDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminAuthDomainTable1json = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageLanVnic.html" , adminAuthDomainTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     lanVnicTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(lanVnicDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
			     
	    	 },1000);
	 
	     });


function saveLanVnicOnNext(){
	//TODO
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":7});
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
					<legend>vNIC Configuration</legend>
					<div class="commonclassForFormFields">
						<form id="lanVnicTableForm" data-dojo-id="lanVnicTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="lanVnicName" name="lanVnicName" style="width: 135px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">vNIC Template:<em>*</em></label>
										</div>
									</td>
									<td><select id="lanVnicTemplate"
										data-dojo-id="lanVnicTemplate"
										name="lanVnicTemplateDrpDown"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 155px; border: 1px solid #b4b4b4;" /></td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">Ethernet Adapter Policy:</label>
										</div>
									</td>
									<td><select id="lanEthernetAdapterPolicy"
										data-dojo-id="lanEthernetAdapterPolicy"
										name="lanEthernetAdapterPolicyDrpDown"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 155px; border: 1px solid #b4b4b4;" /></td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="lanVnicGenerateDataBtn"
											onClick="lanVnicGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft"
						style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1130px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								data-dojo-id="lanVnicDataStoreTab" data="lanVnicTableData"></span>
							<div style="width: 1130px !important;" id="lanVnicTableToolBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="lanVnicTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="lanVnicTable" data-dojo-id="lanVnicTable" dojoType="xwt.widget.table.Table"  
								store="lanVnicDataStoreTab" structure="lanVnicColumns"
								style="width: 1130px; height: 245px;" selectMultiple="true"
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
