<%-- sanConnectivityPolicy.jsp --%>
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
	 
	var WWNNPoolValues = [], WWNNPoolValuesArr = [], vhbaDropdownList = [];
	var sanWwnnArrData = {items:JSON.parse("["+ ajaxCallGet("getSanWwnnConfigDetails.html", true, "json") +"]")};
	var sanVhbaArrData = {items:JSON.parse("["+ ajaxCallGet("getSanVhbaDetails.html", true, "json") +"]")};
	var sanConnPolTableData = {items:JSON.parse("["+ ajaxCallGet("getSanConnectivityPolicyDetails.html", true, "json") +"]")};

	WWNNPoolValues.push({value: null,label: LABEL_SELECT});
	WWNNPoolValuesArr.push({value: "",label: LABEL_SELECT});
	dojo.forEach(sanWwnnArrData.items,function(obj , i){
		WWNNPoolValues.push({value:obj.id,	label:obj.wwnnName});
		WWNNPoolValuesArr.push({value:obj.id,	label:obj.wwnnName});
	});
	
	dojo.forEach(sanVhbaArrData.items,function(obj , i){
		vhbaDropdownList.push({value:obj.id, label:obj.name});
	});
	
	var sanConnPolColumns = [
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
          {label: 'WWNN Pool',attr: 'sanWwnn',sorted: 'ascending',width: 160,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetWWNNName,
       	   editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {options: WWNNPoolValues}
          	}
          },
          {label: 'vHBA',attr: 'vhbaId',width: 170,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetMultipleVhbaNamesConPol,
			editWidget: {
			widget: dojox.form.CheckedMultiSelect,
				options: {options: vhbaDropdownList,dropDown:true,multiple:true,id:"vhbaForConnPol"}
        	}
         },
         {label: 'Organization',attr: 'organizations',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
			editWidget: {
				widget: xwt.widget.form.DropDown,
				options: {options: getOgranizationDropDown()}
        	}
		}                  
	];		

function formatterGetWWNNName(data, item, store){
	return returnFormatterDropDownLabel(WWNNPoolValues,data, item, store);
}

	function sanConnPolGenerateData(){
		var sanConnPolFormObj =  dojo.formToObject("sanConnPolicyForm");
		var vhbaVals = dijit.byId("sanVHBA").get('value');
		
		if(sanConnPolicyForm.validate()==false || sanConnPolFormObj.sanConnPolName == ""){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		if(!checkTableFieldValueUnique(sanConnPolDataStore,"name",sanConnPolFormObj.sanConnPolName)){
			displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
			return false;
		}
		var sanConnPolData = {
					"id":0,
					"name":	sanConnPolFormObj.sanConnPolName,
					"description": sanConnPolFormObj.sanConnPolName,
					"sanWwnn" : sanConnPolFormObj.sanWWNNDrpDown != "" ?parseInt(sanConnPolFormObj.sanWWNNDrpDown):null,
					"vhbaId" : (vhbaVals.length == 0 || vhbaVals == "")?null:vhbaVals,
					"organizations":parseInt(sanConnPolFormObj.connPolOrg)
		};
		sanConnPolTable.store.newItem(sanConnPolData);
		
		sanConnPolDataStore.save();
		sanConnPolTable.refresh();
		sanConnPolicyForm.reset();
		
		var sanVHBA = dijit.byId('sanVHBA');
		sanVHBA.removeOption();
		sanVHBA.reset();
	}
 
var onEditTableRowItem;
require(["dijit/form/Form", "dojo/ready", "dojo/_base/json"], 
		 function(Form,  ready, json){
         
	    	setTimeout(function(){	   		 	
	    		sanWWNN.addOption(WWNNPoolValuesArr);
	    		sanVHBA.addOption(vhbaDropdownList);
	    		connPolOrg.addOption(getOgranizationDropDown());
	    		
		   		sanConnPolDataStore._saveCustom = function(saveComplete, saveFailed){
	    			var sanConnPolChangedData = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageSanConnectivityPolicyConfig.html" , sanConnPolChangedData, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     sanConnPolTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(sanConnPolDataStore,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
		    	
			     dojo.connect(sanConnPolTable,"onEdit",function(item){
			    	 onEditTableRowItem = item;
	    			 var multiSelVhba = dijit.byId('vhbaForConnPol');
	    			 if(item.vhbaId != undefined && item.vhbaId[0] != null){
	    				 multiSelVhba.set("value", item.vhbaId);
	    			 }else{
	    				 multiSelVhba.removeOption();
	    				 multiSelVhba.reset();
	    			 }
	    		 });
			     
			     dojo.connect(dijit.byId('vhbaForConnPol'),"onChange",function(newValue){
			    	 if(newValue.length == 0){
			    		 sanConnPolDataStore.setValue(onEditTableRowItem, "vhbaId", null);
	    			}else{
	    				sanConnPolDataStore.setValue(onEditTableRowItem, "vhbaId", newValue);
	    			}
			     });
			     
			     
	    	 },1000);
	 
	     });


function saveSanConnPolicyOnNext(){
	//TODO
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":0});
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
					<legend>Connectivity Policy Configuration</legend>
					<div class="commonclassForFormFields">
						<form id="sanConnPolicyForm" data-dojo-id="sanConnPolicyForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Organization:</label>
										</div>
									</td>
									<td><select id="connPolOrg" name="connPolOrg"
										data-dojo-id="connPolOrg"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 140px" /></td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="sanConnPolName" name="sanConnPolName"
											style="width: 135px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">WWNN Pool:</label>
										</div>
									</td>
									<td><select id="sanWWNN" data-dojo-id="sanWWNN"
										name="sanWWNNDrpDown"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 155px; border: 1px solid #b4b4b4;" /></td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">vHBA:</label>
										</div>
									</td>
									<td>
											<select id="sanVHBA" data-dojo-id="sanVHBA"
												data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"
												name="sanVHBADrpDown">
											</select>
										
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="sanConnPolGenerateDataBtn"
											onClick="sanConnPolGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1130px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								data-dojo-id="sanConnPolDataStore" data="sanConnPolTableData"></span>
							<div style="width: 1130px !important;"
								id="sanConnPolTableToolBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="sanConnPolTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="sanConnPolTable" data-dojo-id="sanConnPolTable"
								dojoType="xwt.widget.table.Table" store="sanConnPolDataStore"
								structure="sanConnPolColumns"
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
