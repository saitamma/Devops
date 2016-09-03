<%-- lanConnectivityPolicy.jsp --%>
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
	 
	vnicDropdownList = [];	
	var lanVnicArrData = {items:JSON.parse("["+ ajaxCallGet("getLanVnicDetails.html", true, "json") +"]")};
	var lanConnPolTableData = {items:JSON.parse("["+ ajaxCallGet("getLanConnectivityPolicyDetails.html", true, "json") +"]")};
	
	dojo.forEach(lanVnicArrData.items,function(obj , i){
		vnicDropdownList.push({value:obj.id, label:obj.name});
	});
	
	var lanConnPolColumns = [
          {label: 'dbID',	attr: 'id',	hidden:true	},
          {label: 'Name',attr: 'name',sorted: 'ascending',width: 160,vAlignment: "middle",align:'center',editable: true,
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
          {label: 'Description',attr: 'description',sorted: 'ascending',width: 220,vAlignment: "middle",align:'center',editable: true,
       		editWidget:{
        			widget:xwt.widget.notification.ValidationTextBox,
        			options: {
             			trim: true,
             			maxlength:"45",
					}
             	}	
         },          
          {label: 'vNIC',attr: 'vnicId',width: 190,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetMultipleVnicNamesConPol,
			editWidget: {
			widget: dojox.form.CheckedMultiSelect,
				options: {options: vnicDropdownList,dropDown:true,multiple:true,id:"vnicForConnPol"}
        	}
         },
         {label: 'Organization',attr: 'organizations',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
			editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {options: getOgranizationDropDown()}    
        	}
		}                  
	];		

	function lanConnPolGenerateData(){
		var lanConnPolFormObj =  dojo.formToObject("lanConnPolicyForm");
		var vnicVals = dijit.byId("lanVnic").get('value');
		
		if(lanConnPolicyForm.validate()==false || lanConnPolFormObj.lanConnPolName == ""){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		if(!checkTableFieldValueUnique(lanConnPolDataStore,"name",lanConnPolFormObj.lanConnPolName)){
			displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
			return false;
		}
		var lanConnPolData = {
					"id":0,
					"name":	lanConnPolFormObj.lanConnPolName,
					"description": lanConnPolFormObj.lanConnPolName,					
					"vnicId" : (vnicVals.length == 0 || vnicVals == "")?null:vnicVals,
					"organizations":parseInt(lanConnPolFormObj.lanConnPolOrg)
		};
		lanConnPolTable.store.newItem(lanConnPolData);    
		
		lanConnPolDataStore.save();
		lanConnPolTable.refresh();
		lanConnPolicyForm.reset();
		
		var lanVnic = dijit.byId('lanVnic');
		lanVnic.removeOption();
		lanVnic.reset();
	}
 
var onEditTableRowItem;
require(["dijit/form/Form", "dojo/ready", "dojo/_base/json"], 
		 function(Form,  ready, json){
         
	    	setTimeout(function(){	   		 		    		
	    		lanVnic.addOption(vnicDropdownList);
	    		lanConnPolOrg.addOption(getOgranizationDropDown());
	    		
		   		lanConnPolDataStore._saveCustom = function(saveComplete, saveFailed){
	    			var lanConnPolChangedData = returnChangedDataFromDataStore(this,json);
	    			
	    			var response = ajaxCallPostWithJsonContent("manageLanConnectivityPolicyConfig.html" , lanConnPolChangedData, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     lanConnPolTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(lanConnPolDataStore,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
		    	
			     dojo.connect(lanConnPolTable,"onEdit",function(item){
			    	 onEditTableRowItem = item;
	    			 var multiSelVnic = dijit.byId('vnicForConnPol');
	    			 if(item.vnicId != undefined && item.vnicId[0] != null){
	    				 multiSelVnic.set("value", item.vnicId);
	    			 }else{
	    				 multiSelVnic.removeOption();
	    				 multiSelVnic.reset();
	    			 }
	    		 });
			     
			     dojo.connect(dijit.byId('vnicForConnPol'),"onChange",function(newValue){
			    	 if(newValue.length == 0){
			    		 lanConnPolDataStore.setValue(onEditTableRowItem, "vnicId", null);
	    			}else{
	    				lanConnPolDataStore.setValue(onEditTableRowItem, "vnicId", newValue);
	    			}
			     });
			     
			     
	    	 },1000);
	 
	     });


function saveLanConnPolicyOnNext(){
	//TODO
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":8});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	
	return true;
}
</script>
</head>
<body>
	<div id="parentDiv" class="tundraCssForMultiSelect">
		<div class="floatleft">
			<div class="floatleft">
				<fieldset class="heightOfFieldset" style="width: 1130px;">
					<legend>LAN Connectivity Policy Configuration</legend>
					<div class="commonclassForFormFields">
						<form id="lanConnPolicyForm" data-dojo-id="lanConnPolicyForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Organization:</label>
										</div>
									</td>
									<td><select id="lanConnPolOrg" name="lanConnPolOrg"
										data-dojo-id="lanConnPolOrg"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 140px" /></td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>   
										<div id="lanConnPolName" name="lanConnPolName"
											style="width: 135px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>									
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">vNIC:</label>
										</div>
									</td>
									<td>
											<select id="lanVnic" data-dojo-id="lanVnic"
												data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"
												name="lanVnicDrpDown">
											</select>
										
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="lanConnPolGenerateDataBtn"
											onClick="lanConnPolGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1130px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								data-dojo-id="lanConnPolDataStore" data="lanConnPolTableData"></span>
							<div style="width: 1130px !important;"
								id="lanConnPolTableToolBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="lanConnPolTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="lanConnPolTable" data-dojo-id="lanConnPolTable"
								dojoType="xwt.widget.table.Table" store="lanConnPolDataStore"
								structure="lanConnPolColumns"
								style="width: 1130px; height: 240px;" selectMultiple="true"
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
