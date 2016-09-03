<%-- sanVHBATempConfig.jsp --%>
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
			
			var vsanNameInt = 0,defaultVsanId=0,defaultWwpnId=0;
			var vhbaConfigDataResponse = ajaxCallGet("getSanVhbaTemplateConfigDetails.html", true, "json");
			var VHBATempDataTable = { items:JSON.parse("[" + vhbaConfigDataResponse + "]") };
			var vsanConfigDataResponse = ajaxCallGet("getSanVSanConfigDetails.html", true, "json");
			var vsanConfigDataTable = { items:JSON.parse("[" + vsanConfigDataResponse + "]") };
			var wwpnConfigDataResponse = ajaxCallGet("getSanWwpnConfigDetails.html", true, "json");
			var sanWWPNPoolDataTable = { items:JSON.parse("[" + wwpnConfigDataResponse + "]") };
			var qosPolicyDataResponse = ajaxCallGet("getLanQosPolicyDetails.html", true, "json");
			var qosPolicyDataTable = { items:JSON.parse("[" + qosPolicyDataResponse + "]") };

			
			// Calculating the lastly used vsan
			var count = vhbaConfigDataResponse.length;
			if(count > 0){
				var lastNameUsed = JSON.parse(vhbaConfigDataResponse[count-1]).vhbaName;
				vsanNameInt = extractNumericValueFromAlphaNumericString(lastNameUsed);
			}
			// Finding the default vsan id
			dojo.forEach(vsanConfigDataTable.items,function(obj , i){
				if(obj.vsanName == 'default'){
					defaultVsanId = obj.id;
				}
			});
			
			// Finding the default wwpn id
			dojo.forEach(sanWWPNPoolDataTable.items,function(obj , i){
				if(obj.wwpnName == 'default'){
					defaultWwpnId = obj.id;
				}
			});
			
var switchId=[];
	switchId.push({value:"A",	label:"A"});
	switchId.push({value:"B",	label:"B"});
var templateType=[];
	templateType.push({value:"initial-template",	label:"Initial Template"});
	templateType.push({value:"updating-template",	label:"Updating Template"});
var vsanName=[];
	dojo.forEach(vsanConfigDataTable.items,function(obj , i){
		vsanName.push({value:obj.id,	label:obj.vsanName});
	});
var wwpnPoolName=[];
	dojo.forEach(sanWWPNPoolDataTable.items,function(obj , i){
		wwpnPoolName.push({value:obj.id,	label:obj.wwpnName});
	});
var qospolicyname=[];
	qospolicyname.push({value:null,label: LABEL_SELECT});
	dojo.forEach(qosPolicyDataTable.items,function(obj , i){
		qospolicyname.push({value:obj.id,	label:obj.name});
	});
var VHBATempColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'vhbaName',sortable: true,width: 100,vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 140,vAlignment: "middle",align:'center',editable: true,
	             		editWidget:{
                  			widget:xwt.widget.notification.ValidationTextBox,
                  			options: {
   	                			trim: true,
   	                			maxlength:"45",
      						}
	                   	}	
                   },
                   {label: 'WWPN',attr: 'sanWwpn',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center',editable: true, formatter: formatterGetWwpnName,
                	   editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: wwpnPoolName}
   		                   	  }   
                   },
                   {label: 'FI Id',attr: 'switchId',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,
                	   editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: switchId}
		                   	  }
                   },
                   {label: 'Template Type',attr: 'templateType',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetTemplateTypeName,
                	   editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: templateType}
      		                   	  }
                   },
                   {label: 'VSAN',attr: 'sanVsan',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true, formatter: formatterGetVsanName,
                	   editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: vsanName},
      		                   	  }   
                   },
                   {label: 'QoS<br/>Policy',attr: 'lanQosPolicy',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: true, formatter: formatterGetQosPolicyName,
                	   editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: qospolicyname}
   		                   	  }
                   },
                   {label: 'Organization',attr: 'organizations',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: getOgranizationDropDown()}
   		                   	  }
                     }
                   ];		

function formatterGetTemplateTypeName(data, item, store){
	return returnFormatterDropDownLabel(templateType,data, item, store);
}
function formatterGetVsanName(data, item, store){
	return returnFormatterDropDownLabel(vsanName,data, item, store);
}
function formatterGetWwpnName(data, item, store){
	return returnFormatterDropDownLabel(wwpnPoolName,data, item, store);
}
function formatterGetQosPolicyName(data, item, store){
	return returnFormatterDropDownLabel(qospolicyname,data, item, store);
}

function VHBATempGenerateData(){
	var VHBATempFormObj =  dojo.formToObject("VHBATempTableForm");
	if(dijit.byId("VHBATempTableForm").validate()==false || VHBATempFormObj.noOfVHBA == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	// return dataGeneration when skipSan Is checked
	if(dijit.byId("skipSanConfigDataBtn").get("value")){
		displayNotificationAlert(MSG_ALERT_CHECKED_SKIPSAN);
		return false;
	}
	
 	for(i = 1 ; i <= parseInt(VHBATempFormObj.noOfVHBA) ; i++){
 		vsanNameInt++;
 		var vhbaName = "HBA"+vsanNameInt;
 		if(!checkTableFieldValueUnique(VHBATempDataStoreTab,"vhbaName",vhbaName)){
 			continue;
 		}
 		if(defaultWwpnId == 0){
 			defaultWwpnId = (sanWWPNPoolDataTable.items.length == 0)?null:sanWWPNPoolDataTable.items[0].id;
 		}
		if(defaultVsanId == 0){
			defaultVsanId = (vsanConfigDataTable.items.length == 0)?null:vsanConfigDataTable.items[0].id;
 		}
 		var defItemx = {
				"id":0,
				"vhbaName":	vhbaName,
				"description": vhbaName,
				"sanWwpn": defaultWwpnId,
				"switchId": (i%2==0)?"B":"A",
				"templateType":templateType[1].value,
				"sanVsan": defaultVsanId,
				"lanQosPolicy": qospolicyname[0].value,
				"organizations":parseInt(VHBATempFormObj.VHBATemplateOrganization)
				};
 		VHBATempConfigTable.store.newItem(defItemx);
 	}
 	VHBATempDataStoreTab.save();
 	VHBATempConfigTable.refresh();
 	
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 
	    		 // ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	VHBATemplateOrganization.addOption(selectOrganizationArr);

	    		 	VHBATempDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			 var VHBATempTablejson = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("manageSanVhbaTemplateConfig.html" , VHBATempTablejson, null, "json");
	    			 saveComplete();
	    			 updateZeroIdsInDataStore(response, this);
			     	};

			     	VHBATempDataStoreTab.cancelEdit = function(){
			    	 this.closeEditor(this);
			     	};
			     	
			     	dojo.addOnLoad(function () {
			     		VHBATempConfigTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.vhbaName != newitem.vhbaName){
									if(!checkTableFieldValueUnique(VHBATempDataStoreTab,"vhbaName",newitem.vhbaName)){
										return false;
									}
								}
								return true;
							}
						};
						
					});	
			     	
	    	 },1000);
	 
	     });
 
// function for Save data to servere
function saveVHBATempConfData(){
	if( getDataStoreSize(VHBATempDataStoreTab) > 0 ){
		var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":4,"activeStateSubMenuIndex":4});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	}else{
		displayNotificationAlert(MSG_ATLEAST_ONvHBA);
		return false;
	}
}
</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1115px;">
				<legend>vHBA Templates Configuration</legend>

				<div class="vnicTemplateConfig">
					<form id="VHBATempTableForm" data-dojo-id="macPoolTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="VHBATemplateOrganization"
									name="VHBATemplateOrganization"
									data-dojo-id="VHBATemplateOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">No. Of vHBA Templates:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfVHBA" name="noOfVHBA" data-dojo-id="noOfVHBA"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="VHBATempgenerateDataBtn"
										onClick="VHBATempGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft addClassForColumnHeight"
					style="padding-left: 0px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1115px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="VHBATempDataStoreTab" data="VHBATempDataTable"></span>
						<div style="width: 1115px !important;" id="VHBATable2TollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="VHBATempConfigTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="VHBATempConfigTable" data-dojo-id="VHBATempConfigTable"
							dojoType="xwt.widget.table.Table" store="VHBATempDataStoreTab"
							structure="VHBATempColumns" style="width: 1115px; height: 240px;"
							selectMultiple="true" selectAllOption="true" showIndex="false"
							selectModel="input" filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>
</html>