<%-- lanPortChannelConfig.jsp --%>
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
			dojo.require("xwt.widget.notification.Form");
			dojo.require("xwt.widget.notification.ValidationTextBox");
			dojo.require("dijit.form.MultiSelect");

	    	var equipUplinkConfigDataResponse = ajaxCallGet("fetchEquipmentUplinkForPortChannel.html", true, "json");
			var parsedInputEquipUplinkConfigDataResponse = JSON.parse(equipUplinkConfigDataResponse);
			
			var portChannelConfigDataResponse = ajaxCallGet("getPortChannelConfigDetails.html", true, "json");
			var parsedInputPortChannelConfigDataResponse = JSON.parse(portChannelConfigDataResponse);
			var lanPortChannelDataTable1 = { items:JSON.parse("[" + parsedInputPortChannelConfigDataResponse.A + "]") };
			var lanPortChannelDataTable2 = { items:JSON.parse("[" + parsedInputPortChannelConfigDataResponse.B + "]") };
			
var lanPortChannelColumns = [
                {label: 'dbID',	attr: 'id',	hidden:true	},
                {label: 'uplinkId',	attr: 'equipmentUplink', hidden:true },
                {label: 'ID',attr: 'fiId',sorted: 'ascending',width: 40,vAlignment: "middle",align:'center',hidden:true},
                {label: 'Name',attr: 'fiName',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
                {label: 'Description',attr: 'description',sorted: 'ascending',width: 160,vAlignment: "middle",align:'center',editable: true,
                	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
                		options: {
                			trim: true,
                			maxlength:"45",
    					}
      				}	
                },
                {label: 'Port Id',attr: 'portId',width: 80,vAlignment: "middle",align:'center'},
                {label: 'Slot Id', attr: 'slotId', width: 80, vAlignment: "middle", align:'center'},
                ]; 


function lanPortChannelGenerateData(){
	if(dijit.byId("lanPortChannelTableForm").validate()==false || lanPortChannelName.get("value") == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	var portChannelName = lanPortChannelName.get("value");
	var	selectedPortsArr = availablePorts.get('value');
	for( k=0; k < selectedPortsArr.length; k++){
		lanPortChannelConfigTable1.store.newItem({ "id":0,"equipmentUplink":parseInt(selectedPortsArr[k].split("/")[2]), "fiId": "A", "fiName":portChannelName,"description":portChannelName,"portId": selectedPortsArr[k].split("/")[1],"slotId":selectedPortsArr[k].split("/")[0]});
	}
	lanPortChannelDataStoreTab1.save();
	lanPortChannelConfigTable1.refresh();
	
	element=document.getElementById('availablePorts');
	while(element.selectedIndex != -1){
 		element.removeChild(element.options[element.selectedIndex]);
    }
 }
 
function lanPortChannelGenerateDataB(){
	if(dijit.byId("lanPortChannelTableFormB").validate()==false || lanPortChannelNameB.get("value") == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	var portChannelNameB = lanPortChannelNameB.get("value");
	var	selectedPortsArrB = availablePortsB.get('value');
	for( k=0; k < selectedPortsArrB.length; k++){
		lanPortChannelConfigTable2.store.newItem({ "id":0,"equipmentUplink":parseInt(selectedPortsArrB[k].split("/")[2]), "fiId": "B", "fiName":portChannelNameB,"description":portChannelNameB,"portId": selectedPortsArrB[k].split("/")[1],"slotId": selectedPortsArrB[k].split("/")[0]});
	}
	lanPortChannelDataStoreTab2.save();
	lanPortChannelConfigTable2.refresh();
	
	element=document.getElementById('availablePortsB');
	while(element.selectedIndex != -1){
 		element.removeChild(element.options[element.selectedIndex]);
    }
	
 }
 
require(["dojo/ready", "dojo/_base/json"], 
 		 function(ready, json){
 	 	
	    	 setTimeout(function(){
	    		 var tableSavedPortIdA = [];
					dojo.forEach(JSON.parse("[" + parsedInputPortChannelConfigDataResponse.A + "]"),function(value,i){
						tableSavedPortIdA.push(value.portId);
					});
					var select = document.getElementById('availablePorts');
					removeOptions("availablePorts"); // 1st delete
					dojo.forEach(JSON.parse("["+parsedInputEquipUplinkConfigDataResponse.A+"]"),function(value,i){
						if(tableSavedPortIdA.indexOf(value.portId) < 0){
							var opt = document.createElement('option');
						    opt.value = value.slotId+"/"+value.portId+"/"+value.id;
						    opt.innerHTML = value.slotId+"/"+value.portId;
						    select.appendChild(opt);
						}
					});
					
					
				// Table Side B
				var tableSavedPortIdB = [];
					dojo.forEach(JSON.parse("[" + parsedInputPortChannelConfigDataResponse.B + "]"),function(value,i){
						tableSavedPortIdB.push(value.portId);
					});
					var selectB = document.getElementById('availablePortsB');
					removeOptions("availablePortsB");
					dojo.forEach(JSON.parse("["+parsedInputEquipUplinkConfigDataResponse.B+"]"),function(value,i){
						if(tableSavedPortIdB.indexOf(value.portId) < 0){
							var opt = document.createElement('option');
						    opt.value = value.slotId+"/"+value.portId+"/"+value.id;
						    opt.innerHTML = value.slotId+"/"+value.portId;
						    selectB.appendChild(opt);
						}
					});
	    		 
					
	    		 lanPortChannelDataStoreTab1._saveCustom = function(saveComplete, saveFailed){
	    			 var getDeletedPortChannelIdA = getDeletedPortChannelIds(this,json);
						dojo.forEach(JSON.parse(getDeletedPortChannelIdA).deletedPortChannelIds,function(value,i){
								var opt = document.createElement('option');
							    opt.value = value;
							    opt.innerHTML = value.split("/")[0]+"/"+value.split("/")[1];
							    select.appendChild(opt);
						});
						
						
	    			 var portChannelTable1json = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("managePortChannelConfig.html" , portChannelTable1json, null, "json");
	    			 saveComplete();
						updateZeroIdsInDataStore(response, this);
			     };
			     lanPortChannelDataStoreTab2._saveCustom = function(saveComplete, saveFailed){
			    	 var getDeletedPortChannelIdB = getDeletedPortChannelIds(this,json);
						dojo.forEach(JSON.parse(getDeletedPortChannelIdB).deletedPortChannelIds,function(value,i){
								var opt = document.createElement('option');
							    opt.value = value;
							    opt.innerHTML = value.split("/")[0]+"/"+value.split("/")[1];
							    selectB.appendChild(opt);
						});
						
	    			 var portChannelTable2json = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("managePortChannelConfig.html" , portChannelTable2json, null, "json");
	    			 saveComplete();
	    			 updateZeroIdsInDataStore(response, this);
			     };
			     
			     
	    	 },1000);
 	 
	 });


//Save data in back-end
function saveLanPortChannelData(){
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":1});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	return true;
}

</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 510px;">
				<legend>FI A</legend>
				<div class="vnicTemplateConfig">
					<form id="lanPortChannelTableForm"
						data-dojo-id="lanPortChannelTableForm"
						data-dojo-type="xwt.widget.notification.Form"
						name="lanPortChannelTableForm">
						<table>
							<tr>
								<td valign="top">
									<div class="labelspace">
										<label style="float: left;">Name:<em>*</em></label>
									</div>
								</td>
								<td valign="top">
									<div id="lanPortChannelName" value="PC-A1"
										name="lanPortChannelName" style="width: 100px;"
										data-dojo-id="lanPortChannelName"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
								</td>
								<td valign="top">
									<div class="labelspace">
										<label style="float: left;">Ports:<em>*</em></label>
									</div>
								</td>
								<td valign="top"><select id="availablePorts"
									data-dojo-id="availablePorts" name="availablePorts"
									data-dojo-type="dijit.form.MultiSelect" style="width: 80px;">
								</select></td>
								<td valign="top" style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="lanPortChannelGenerateDataBtn"
										onClick="lanPortChannelGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft" style="padding-left: 5px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 500px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="lanPortChannelDataStoreTab1"
							data="lanPortChannelDataTable1"></span>
						<div style="width: 500px !important;"
							id="lanPortChannelConfigTable1TollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="lanPortChannelConfigTable1" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="lanPortChannelConfigTable1"
							data-dojo-id="lanPortChannelConfigTable1"
							dojoType="xwt.widget.table.Table"
							store="lanPortChannelDataStoreTab1"
							structure="lanPortChannelColumns"
							style="width: 500px; height: 200px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>

			</fieldset>
		</div>
		<div class="floatleft">
			<fieldset class="heightOfFieldset" style="width: 510px; margin-left: 10px;">
				<legend>FI B</legend>
				<div class="vnicTemplateConfig">
					<form id="lanPortChannelTableFormB"
						data-dojo-id="lanPortChannelTableFormB"
						data-dojo-type="xwt.widget.notification.Form"
						name="lanPortChannelTableForm">
						<table>
							<tr>
								<td valign="top">
									<div class="labelspace">
										<label style="float: left;">Name:<em>*</em></label>
									</div>
								</td>
								<td valign="top">
									<div id="lanPortChannelNameB" value="PC-B1"
										name="lanPortChannelNameB" style="width: 100px;"
										data-dojo-id="lanPortChannelNameB"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
								</td>
								<td valign="top">
									<div class="labelspace">
										<label style="float: left;">Ports:<em>*</em></label>
									</div>
								</td>
								<td valign="top"><select id="availablePortsB"
									data-dojo-id="availablePortsB" name="availablePortsB"
									data-dojo-type="dijit.form.MultiSelect" style="width: 80px;"></select>
								</td>
								<td valign="top" style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="lanPortChannelGenerateDataBtnB"
										onClick="lanPortChannelGenerateDataB();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft" style="padding-left: 5px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 500px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="lanPortChannelDataStoreTab2"
							data="lanPortChannelDataTable2"></span>
						<div style="width: 500px !important;"
							id="lanPortChannelTable2TollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="lanPortChannelConfigTable2" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="lanPortChannelConfigTable2"
							data-dojo-id="lanPortChannelConfigTable2"
							dojoType="xwt.widget.table.Table"
							store="lanPortChannelDataStoreTab2"
							structure="lanPortChannelColumns"
							style="width: 500px; height: 200px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</body>
</html>