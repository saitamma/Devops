<%-- sanVsanConfig.jsp --%>
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
	
	var vlanConfigDataResponse = JSON.parse("[" + ajaxCallGet("getLanVlanConfigDetails.html", true, "json") + "]");
	
	var vsanNameInt = 0;
	var FCoeVlanId = 0;
	var vsanConfigDataResponse = ajaxCallGet("getSanVSanConfigDetails.html", true, "json");
	var vsanConfigDataTable = { items:JSON.parse("[" + vsanConfigDataResponse + "]") };
	
	// Calculating the max used VSanName
	var count = vsanConfigDataResponse.length;
	if(count > 0){
		var lastNameUsed = JSON.parse(vsanConfigDataResponse[count-1]).vsanName;
		var lastFcoeVlanId = JSON.parse(vsanConfigDataResponse[count-1]).fcoeVlanName;
		vsanNameInt = extractNumericValueFromAlphaNumericString((lastNameUsed == "default")?"default1":lastNameUsed);
		FCoeVlanId = (parseInt(lastFcoeVlanId)==4048 || parseInt(lastFcoeVlanId)==3967 || parseInt(lastFcoeVlanId)==4093)?1:parseInt(lastFcoeVlanId);
	}
	
	var lanVlanConfigDataResponse = ajaxCallGet("getLanVlanConfigDetails.html", true, "json");
	var vlanConfigDataTable = { items:JSON.parse("[" + lanVlanConfigDataResponse + "]") };
	
	var templateType=[];
	templateType.push({value:"Initial Template",	label:"Initial Template"});
	templateType.push({value:"Updating Template",	label:"Updating Template"});
	
	var vsanConfigColumns = [
	                       {label: 'dbID',	attr: 'id',	hidden:true	},
	                       {label: 'FI Id',attr: 'fiId',sortable: true,width: 70,vAlignment: "middle",align:'center',editable: false},
	                       {label: 'Name',attr: 'vsanName',sortable: true,width: 200,vAlignment: "middle",align:'center',editable: true,
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
		                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 190,vAlignment: "middle",align:'center',editable: true,
		                	   editWidget: {
		                		   widget:xwt.widget.notification.ValidationTextBox,
			                   		options: {
			                   			trim: true,
			                   			maxlength:"45",
			       					}
		         				}
		                   },
	                       {label: 'VSAN Id',attr: 'fcoeVsanId',sortable: true,sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,
	                    	   editWidget: {
	                       		widget:xwt.widget.notification.ValidationTextBox,
	       							options: {
	       								regExp:REG_EX_FCOE_VSAN_VLAN_ID,
	       								required: true,
	       	                			trim: true,
	       	                			maxlength:"4",
	       								invalidMessage: MSG_FCOE_VSAN_VLAN_ID
	       								}
	                       			}
	                       },
	                       {label: 'FCoE VLAN Id',attr: 'fcoeVlanName',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true, 
		                	   editWidget:{
		                   			widget:xwt.widget.notification.ValidationTextBox,
		                   			options: {
		                   					regExp: REG_EX_NUMBER_1_TO_3967_OR_4048_TO_4093,
		       								required: true,
		       	                			trim: true,
		       	                			maxlength:"4",
		       	                			invalidMessage: MSG_BET_1_TO_3967_OR_4048_TO_4093
		       								}
			                   		}   
	                       }
	                       ];	

function vsanConfigGenerateData(){
	var vsanConfigFormObj =  dojo.formToObject("vsanConfigTableForm");
	if((dijit.byId("vsanConfigTableForm").validate()==false ) || (vsanConfigFormObj.noOfVsanASide == "" && vsanConfigFormObj.noOfVsanBSide == "") ){
		displayNotificationAlert(MSG_NO_VSAN_EITHER_A_B); // 
		return false;
	}
	// return dataGeneration when skipSan Is checked
	if(dijit.byId("skipSanConfigDataBtn").get("value")){
		displayNotificationAlert(MSG_ALERT_CHECKED_SKIPSAN);
		return false;
	}
 	getNewItemSideWise(parseInt(vsanConfigFormObj.noOfVsanASide),"A");
 	getNewItemSideWise(parseInt(vsanConfigFormObj.noOfVsanBSide),"B");
 	
 	vsanConfigDataStoreTab.save();
 	vsanConfigConfigTable.refresh();
 	
 	dijit.byId("noOfVsanASide").set("value","");
 	dijit.byId("noOfVsanBSide").set("value","");
 }
 
 function getNewItemSideWise(noOfVsan,side){

	 for(i = 1; i <= noOfVsan ; i++){
		 FCoeVlanId++;
		 vsanNameInt++;
		 dojo.forEach(vlanConfigDataResponse,function(data,i){
			 if(parseInt(data.vlanId) == FCoeVlanId){
				 FCoeVlanId = ++FCoeVlanId;
			 }
		 });
		 
		var vsanName = "VSAN"+vsanNameInt;
 		if(!checkTableFieldValueUnique(vsanConfigDataStoreTab,"vsanName",vsanName)){
 			continue;
 		}
 		if(!checkTableFieldValueUnique(vsanConfigDataStoreTab,"fcoeVlanName",""+FCoeVlanId)){
 			FCoeVlanId++;
 		}
 		if(!checkTableFieldValueUnique(vsanConfigDataStoreTab,"fcoeVsanId",""+vsanNameInt)){
 			vsanNameInt++;
 		}
 		if( (FCoeVlanId>3967 || FCoeVlanId > 4093)  ){
 			return false;
 		}
 		if(vsanNameInt > 4093){
 			return false;
 		}
 		var defItemx = {
				"id":0,
				"fiId":side,
				"vsanName":	vsanName,
				"description": vsanName,
				"fcoeVsanId": ""+vsanNameInt,
				"fcoeVlanName": ""+FCoeVlanId, 
				};
 		vsanConfigConfigTable.store.newItem(defItemx);
	 	}
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
	function(ready, json){
	 	
	    setTimeout(function(){
	    		 
	    	vsanConfigDataStoreTab._saveCustom = function(saveComplete, saveFailed){
    			var vsanConfigTablejson = returnChangedDataFromDataStore(this,json);
    			var response = ajaxCallPostWithJsonContent("manageSanVSanConfig.html" , vsanConfigTablejson, null, "json");
    			saveComplete();
    			updateZeroIdsInDataStore(response, this);
		    };
		    
	    	vsanConfigConfigTable.validateRow = {
					isValid: function (oldvalues, newitem) {
						if(oldvalues.vsanName != newitem.vsanName){
							
							if(oldvalues.vsanName == 'default'){
								this.errorMessage = MSG_CANT_CHANGE_DEFAULT;
								return false;
				    		 }
							else if(!checkTableFieldValueUnique(vsanConfigDataStoreTab,"vsanName",newitem.vsanName)){
								this.errorMessage = MSG_DUPLICATE_NAME;
								return false;
							}
						}
						else if(oldvalues.fcoeVlanName != newitem.fcoeVlanName){
							if(!checkTableFieldValueUnique(vsanConfigDataStoreTab,"fcoeVlanName",newitem.fcoeVlanName) ){
								this.errorMessage = MSG_DUPLICATE_FCoE_VLANID;
								return false;
							}
							else{
								var validateReturn = false;
								dojo.forEach(vlanConfigDataResponse,function(data,i){
									 if(parseInt(data.vlanId) == parseInt(newitem.fcoeVlanName)){
										 validateReturn = true;
									 }
								 });
								this.errorMessage = MSG_DUPLICATE_NOT_ALLOWED_VLANID;
							 	return (validateReturn)?false:true;
							}
						}
						else if(oldvalues.fcoeVsanId != newitem.fcoeVsanId){
							if(!checkTableFieldValueUnique(vsanConfigDataStoreTab,"fcoeVsanId",newitem.fcoeVsanId) ){
								this.errorMessage = MSG_DUPLICATE_VSANID;
								return false;
							}
						}
						return true;
					}
				};
		    
		    dojo.connect(dijit.byId("vsanConfigConfigTable"),"onSelect",function(item, row){
	    		 if(item.vsanName == 'default'){
	    			 this.deselect(item);
	    		 }
	    	});
		    
		},1000);
});
 
 
 // function for Save data to servere
 function saveVsanConfigData(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":4,"activeStateSubMenuIndex":1});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
 }
</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1115px;">
				<legend>VSAN Configuration</legend>
				<div class="vnicTemplateConfig">
					<form id="vsanConfigTableForm" data-dojo-id="vsanConfigTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of VSANs On A Side:</label>
									</div>
								</td>
								<td>
									<div id="noOfVsanASide" name="noOfVsanASide"
										data-dojo-id="noOfVsanASide"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of VSANs On B Side:</label>
									</div>
								</td>
								<td>
									<div id="noOfVsanBSide" name="noOfVsanBSide"
										data-dojo-id="noOfVsanBSide"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="vsanConfigGenerateDataBtn"
										onClick="vsanConfigGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft"
					style="padding-left: 0px; padding-top: 20px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1115px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="vsanConfigDataStoreTab" data="vsanConfigDataTable"></span>
						<div style="width: 1115px !important;"
							id="vsanConfigTable2TollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="vsanConfigConfigTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="vsanConfigConfigTable"
							data-dojo-id="vsanConfigConfigTable"
							dojoType="xwt.widget.table.Table" store="vsanConfigDataStoreTab"
							structure="vsanConfigColumns"
							style="width: 1115px; height: 245px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>