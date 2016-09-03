<%-- serversMaintenancePolicy.jsp --%>
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
	dojo.require("dojox.form.MultiComboBox");
	dojo.require("dojox.form.CheckedMultiSelect");
	 
	var rebootPolicyArr = [];
	var maintenancePolTableData = {items:JSON.parse("["+ ajaxCallGet("getMaintenancePolicyDetails.html", true, "json") +"]")};

	rebootPolicyArr.push({value: "immediate",label: "Immediate"});
	rebootPolicyArr.push({value: "user-ack",label: "User Ack"});
	//rebootPolicyArr.push({value: "timer-automatic",label: "Timer Automatic"});
	
	var maintenancePolColumns = [
          {label: 'dbID',	attr: 'id',	hidden:true	},
          {label: 'Name',attr: 'name',sorted: 'ascending',width: 230,vAlignment: "middle",align:'center',editable: true,
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
          {label: 'Description',attr: 'description',sorted: 'ascending',width: 250,vAlignment: "middle",align:'center',editable: true,
       		editWidget:{
        			widget:xwt.widget.notification.ValidationTextBox,
        			options: {
             			trim: true,
             			maxlength:"45",
					}
             	}	
         },
          {label: 'Reboot Policy',attr: 'rebootPolicy',sorted: 'ascending',width: 250,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetRebootPolicy,
       	   editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {options: rebootPolicyArr}
          	}
          },
         {label: 'Organization',attr: 'organizations',width: 220,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
			editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {options: getOgranizationDropDown()}
        	}
		}                  
	];		

	function formatterGetRebootPolicy(data, item, store){
		return returnFormatterDropDownLabel(rebootPolicyArr, data, item, store);
	}

	function maintenancePolGenerateData(){
		var maintenancePolFormObj =  dojo.formToObject("maintenancePolForm");
		
		if(maintenancePolForm.validate()==false || maintenancePolFormObj.maintenancePolName == ""){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		if(!checkTableFieldValueUnique(maintenancePolDataStore,"name",maintenancePolFormObj.maintenancePolName)){
			displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
			return false;
		}
		var maintenancePolData = {
					"id":0,
					"name":	maintenancePolFormObj.maintenancePolName,
					"description": maintenancePolFormObj.maintenancePolName,
					"rebootPolicy" : maintenancePolFormObj.maintenanceRebootPolDrpDown,
					"organizations":parseInt(maintenancePolFormObj.maintenancePolOrg)
		};
		maintenancePolTable.store.newItem(maintenancePolData);
		
		maintenancePolDataStore.save();
		maintenancePolTable.refresh();
		maintenancePolForm.reset();
	}
 
 
	require(["dijit/form/Form", "dojo/ready", "dojo/_base/json"], 
		 function(Form,  ready, json){
         
	    	setTimeout(function(){
	    		maintenanceRebootPol.addOption(rebootPolicyArr);
	    		maintenancePolOrg.addOption(getOgranizationDropDown());
	    		
		   		maintenancePolDataStore._saveCustom = function(saveComplete, saveFailed){
	    			var mPolChangedData = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageServersMaintenancePolicyConfig.html" , mPolChangedData, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     maintenancePolTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(maintenancePolDataStore,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
	    	 },1000);
	 
	     });

	function saveServersMaintenancePolicyOnNext(){
		var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":8});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	}
</script>
</head>
<body>
	<div id="parentDiv" class="tundraCssForMultiSelect">
		<div class="floatleft">
			<div class="floatleft">
				<fieldset class="heightOfFieldset" style="width: 1050px;">
					<legend>Maintenance Policy Configuration</legend>
					<div style="margin: 5px;">
					<div class="commonclassForFormFields">
						<form id="maintenancePolForm" data-dojo-id="maintenancePolForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Organization:</label>
										</div>
									</td>
									<td><select id="maintenancePolOrg" name="maintenancePolOrg"
										data-dojo-id="maintenancePolOrg"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 140px" /></td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="maintenancePolName" name="maintenancePolName"
											style="width: 135px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">Reboot Policy:</label>
										</div>
									</td>
									<td><select id="maintenanceRebootPol" data-dojo-id="maintenanceRebootPol"
										name="maintenanceRebootPolDrpDown"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 155px; border: 1px solid #b4b4b4;"/>
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											onClick="maintenancePolGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-top: 30px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1040px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								data-dojo-id="maintenancePolDataStore" data="maintenancePolTableData"></span>
							<div style="width: 1040px !important;"
								id="maintenancePolTableToolBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="maintenancePolTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="maintenancePolTable" data-dojo-id="maintenancePolTable"
								dojoType="xwt.widget.table.Table" store="maintenancePolDataStore"
								structure="maintenancePolColumns"
								style="width: 1040px; height: 220px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>
