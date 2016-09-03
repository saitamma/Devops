<%-- sanVHBAConfig.jsp --%>
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
	
	var sanVHBATemplateArr = [],sanAdapterPolicyArr = [],sanVHBATemplateArr1=[],sanAdapterPolicyArr1=[];
	var sanAdapterPoliciesData = {items:JSON.parse("["+ ajaxCallGet("getSanAdapterPoliciesDetails.html", true, "json") +"]")};
	var vhbaTemplateData = {items:JSON.parse("["+ ajaxCallGet("getSanVhbaTemplateConfigDetails.html", true, "json") +"]")};
	
	sanVHBATemplateArr1.push({value: "",label: LABEL_SELECT});
	dojo.forEach(vhbaTemplateData.items,function(obj , i){
		sanVHBATemplateArr.push({value:obj.id,	label:obj.vhbaName});
		sanVHBATemplateArr1.push({value:obj.id,	label:obj.vhbaName});
	});
	sanAdapterPolicyArr.push({value: null,label: LABEL_SELECT});
	sanAdapterPolicyArr1.push({value: "", label: LABEL_SELECT});
	dojo.forEach(sanAdapterPoliciesData.items,function(obj , i){
		sanAdapterPolicyArr.push({value:obj.id,	label:obj.name});
		sanAdapterPolicyArr1.push({value:obj.id,	label:obj.name});
	});
	
	var sanVHBATableData = {items:JSON.parse("["+ ajaxCallGet("getSanVhbaDetails.html", true, "json") +"]")};
	
	var sanVHBAColumns = [
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
                   {label: 'vHBA Template',attr: 'sanVhbaTemplate',sorted: 'ascending',width: 350,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetVhbaTempName,
                	   editWidget: {
    						widget: xwt.widget.form.DropDown,
    						options: {options: sanVHBATemplateArr}
    		                   	  }
                   },
                   {label: 'FC Adapter Policy',attr: 'sanAdapterPolicies',sorted: 'ascending',width: 350,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetAdapterPolicyName,
                	   editWidget: {
    						widget: xwt.widget.form.DropDown,
    						options: {options: sanAdapterPolicyArr}
    		           }
                   }                   
	];		


	function sanVHBAGenerateData(){
		var sanVHBAFormObj =  dojo.formToObject("sanVHBATableForm");
		console.log(sanVHBAFormObj);
		if(dijit.byId("sanVHBATableForm").validate()==false || sanVHBAFormObj.sanVHBAName == "" || 
				sanVHBAFormObj.sanVHBATemplateDrpDown == "" ){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		if(!checkTableFieldValueUnique(sanVHBADataStoreTab,"name",sanVHBAFormObj.sanVHBAName)){
			displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
			return false;
		}
		var sanVhbaData = {
					"id":0,
					"name":	sanVHBAFormObj.sanVHBAName,
					"sanVhbaTemplate" : parseInt(sanVHBAFormObj.sanVHBATemplateDrpDown),
					"sanAdapterPolicies" : sanVHBAFormObj.sanAdapterPolicyDrpDown != "" ?parseInt(sanVHBAFormObj.sanAdapterPolicyDrpDown):null,
		};
		sanVHBATable.store.newItem(sanVhbaData);
		
		sanVHBADataStoreTab.save();
		sanVHBATable.refresh();
	 	dijit.byId("sanVHBAName").set("value","");
	}
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){	   		 	
	   		 	
	    		sanVHBATemplate.addOption(sanVHBATemplateArr1);
	    		sanAdapterPolicy.addOption(sanAdapterPolicyArr1);
	    		
		   		sanVHBADataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminAuthDomainTable1json = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageSanVhba.html" , adminAuthDomainTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     sanVHBATable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(sanVHBADataStoreTab,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
			     
	    	 },1000);
	 
	     });


function saveSanvHBAOnNext(){
	//TODO
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":4,"activeStateSubMenuIndex":5});
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
					<legend>vHBA Configuration</legend>
					<div class="commonclassForFormFields">
						<form id="sanVHBATableForm" data-dojo-id="sanVHBATableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="sanVHBAName" name="sanVHBAName" style="width: 135px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">vHBA Template:<em>*</em></label>
										</div>
									</td>
									<td><select id="sanVHBATemplate"
										data-dojo-id="sanVHBATemplate"
										name="sanVHBATemplateDrpDown"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 155px; border: 1px solid #b4b4b4;" /></td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">FC Adapter Policy:</label>
										</div>
									</td>
									<td><select id="sanAdapterPolicy"
										data-dojo-id="sanAdapterPolicy"
										name="sanAdapterPolicyDrpDown"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 155px; border: 1px solid #b4b4b4;" /></td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="sanVHBAGenerateDataBtn"
											onClick="sanVHBAGenerateData();" type="button">Add</button>
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
								data-dojo-id="sanVHBADataStoreTab" data="sanVHBATableData"></span>
							<div style="width: 1130px !important;" id="sanVHBATableToolBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="sanVHBATable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="sanVHBATable" data-dojo-id="sanVHBATable" dojoType="xwt.widget.table.Table"
								store="sanVHBADataStoreTab" structure="sanVHBAColumns"
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
