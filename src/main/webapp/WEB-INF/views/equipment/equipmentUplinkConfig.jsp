<%-- equipmentUplinkConfig.jsp --%>
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
			dojo.require("xwt.widget.table.GlobalToolbar");
			dojo.require("xwt.widget.layout.Dialog");
			dojo.require("dijit.form.MultiSelect");
			dojo.require("dijit.form.CheckBox");
			
			var equipUplinkConfigDataResponse = ajaxCallGet("fetchEquipmentUplinkConfig.html", true, "json");
			var dataEquipUplinkConfig = {items:JSON.parse("["+equipUplinkConfigDataResponse+"]")}; 
			var equipFCPortConfigDataResponse = ajaxCallGet("fetchEquipmentFCPortConfig.html", true, "json");
			var dataEquipmentFCConfig = {items:JSON.parse("["+equipFCPortConfigDataResponse+"]")};
			
			var equipUplinkFCColumns = [{
				label: 'dbID',
				attr: 'id',
				hidden:true
			},
			{
				label: 'FI Id',
				attr: 'fiId',
				sortable: true,
				sorted: 'ascending',
				width: 70,
				vAlignment: "middle",
				align:'center',
			},
			{
				label: 'Port Id',
				attr: 'portId',
				sortable: true,
				sorted: 'ascending',
				width: 80,
				vAlignment: "middle",
				align:'center',
				formatter:updateBitMap
			},
			{
				label: 'Slot Id',
				attr: 'slotId',
				sorted: 'ascending',
				width: 80,
				vAlignment: "middle",
				align:'center',
			},
			{
				label: 'User Label',
				attr: 'userLabel',
				width: 130,
				vAlignment: "middle",
				align:'center',
				editable: true,
				editWidget: {widget:xwt.widget.notification.ValidationTextBox,
            		options: {
            			trim: true,
            			maxlength:"45",
					}
  				}
			}]; 
			
			function updateBitMap(portId , item){
				if(item.fiId == 'A' && globalFIAmodelBitMap.length != 0){
					globalFIAmodelBitMap[transformSlotToPort(item.slotId+"/"+portId) -1] = 1;	
				} else if(item.fiId == 'B' && globalFIBmodelBitMap.length != 0){
					globalFIBmodelBitMap[transformSlotToPort(item.slotId+"/"+portId) -1] = 1;	
				}
				return portId+"";
			}
		
        function addPortToUplinkPort_A_B(){
        	var uplinkFormObject =  dojo.formToObject("uplinkTableForm");
        	var	selectedPortsArrA = availablePortsForUplink_A_B.get('value');
        	var	selectedPortsArrB = availablePortsForFC_A_B.get('value');
        	
        	if(selectedPortsArrA == "" && selectedPortsArrB == "" ){
        		return false;
        	}
        
        	for(i = 0 ; i < selectedPortsArrA.length ; i++){
        		equipmentUplinkConfigTablePopup.store.newItem({ "id":0, "fiId": "A", "portId": returnPortId(selectedPortsArrA[i]), "slotId": returnSlotId(selectedPortsArrA[i]), "userLabel":"Uplink"});
         	}
       		for(i = 0 ; i < selectedPortsArrB.length ; i++){
       			equipmentUplinkConfigTablePopup.store.newItem({ "id":0, "fiId": "B", "portId": returnPortId(selectedPortsArrB[i]), "slotId": returnSlotId(selectedPortsArrB[i]), "userLabel":"Uplink"});
       		}

         	equipUplinkDataStoreTable.save();
         	equipmentUplinkConfigTablePopup.refresh();
         	equipmentUplinkConfigTableForFIAB.refresh();
         	
         	element=document.getElementById('availablePortsForUplink_A_B');
         	for(i = 0 ; i < selectedPortsArrA.length ; i++){
         		if(element.selectedIndex != -1){
         			element.removeChild(element.options[element.selectedIndex]);
         		}
            }
         	element2=document.getElementById('availablePortsForFC_A_B');
         	for(i = 0 ; i < selectedPortsArrB.length ; i++){
         		if(element2.selectedIndex != -1){
         			element2.removeChild(element2.options[element2.selectedIndex]);
         		}
            }
         }
   
   function isExistDataInDataStore(datastoreObj,selectedSlotIdPortId,fiId){
		var existData = false; 
    	datastoreObj.fetch({
			 onComplete: function(items, request){
				 if(items.length>0){
					 for(var j=0; j< items.length; j++){
							if( (items[j].fiId == fiId) && (items[j].slotId == returnSlotId(selectedSlotIdPortId)) && (items[j].portId >= returnPortId(selectedSlotIdPortId)) ){
								existData = true;
								return false;
							}
					}
				 }
			 }
		});
		return existData;
	}
     
       function addPortToFCPort_A_B(){
        	var uplinkFormObject =  dojo.formToObject("uplinkTableForm");
        	var	selectedPortsArrA = availablePortsForUplink_A_B.get('value');
        	var	selectedPortsArrB = availablePortsForFC_A_B.get('value');
        	
        	if(selectedPortsArrA == "" && selectedPortsArrB == "" ){
        		return false;
        	}
        	if( ( selectedPortsArrA != "" && (returnPortId(selectedPortsArrA[0]) % 2) == 0 )  ||  ( selectedPortsArrB != "" && (returnPortId(selectedPortsArrB[0]) % 2) == 0 ) ){
        		displayNotificationAlert(MSG_ODD_PORT_CONFIG);
        		return false;
        	}
        	//check for Existing port
        	for(var i=0; i< selectedPortsArrA.length; i++){
        		isExist = isExistDataInDataStore(equipUplinkDataStoreTable,selectedPortsArrA[i],"A");
        		if(isExist){
        			displayNotificationAlert(MSG_CONFIG_FC_PORT_ERROR);
        			return false;
        		}
        		isExist = isExistDataInDataStore(equipServerDataStoreFiA,selectedPortsArrA[i],"A");
        		if(isExist){
        			displayNotificationAlert(MSG_CONFIG_FC_PORT_ERROR);
        			return false;
        		}
        	}
        	for(var i=0; i< selectedPortsArrB.length; i++){
        		isExist = isExistDataInDataStore(equipUplinkDataStoreTable,selectedPortsArrB[i],"B");
        		if(isExist){
        			displayNotificationAlert(MSG_CONFIG_FC_PORT_ERROR);
        			return false;
        		}
        		isExist = isExistDataInDataStore(equipServerDataStoreFiB,selectedPortsArrB[i],"B");
        		if(isExist){
        			displayNotificationAlert(MSG_CONFIG_FC_PORT_ERROR);
        			return false;
        		}
        	}
        	// Insert for FI A
        	var removeOptionsArrOfA = [];
        	var firstSlotPort = 32;
        	if(globalFIAmodelBitMap.length == 96){
        		firstSlotPort = 48;
        	}
        	for(var i=0; i< selectedPortsArrA.length; i++){
        		//
        		if(returnPortId(selectedPortsArrA[i]) <= firstSlotPort && returnSlotId(selectedPortsArrA[i]) == 1){
        			loopCount = firstSlotPort;
        		}
        		else{
        			loopCount = 16;
        		}
        		if(globalFIAmodelBitMap[transformSlotToPort(selectedPortsArrA[i]) - 1] == 1){
        			continue;
        		}
        		for(p = returnPortId(selectedPortsArrA[i]); p <= loopCount; p++){
        			globalFIAmodelBitMap[transformSlotToPort(returnSlotId(selectedPortsArrA[i])+"/"+p) -1] = 1;
        			equipmentFCTableConfigPopup.store.newItem({ "id":0, "fiId": "A", "portId": p, "slotId": returnSlotId(selectedPortsArrA[i]), "userLabel":"Uplink"});
        			
        			removeOptionsArrOfA.push(returnSlotId(selectedPortsArrA[i])+"/"+p);
        		}
        	}
        	
        	// remove port from Dropdown when enterd in table.
    		elementA=document.getElementById('availablePortsForUplink_A_B');
    		for (var i = elementA.options.length-1; i>=0; i--) {
				    if( removeOptionsArrOfA.indexOf(elementA.options[i].value) > -1 ) {
				    	elementA.remove(i);
				   } 
				}
    		
        	// Insert for FI B
        	var removeOptionsArrOfB = [];
        	for(var i=0; i< selectedPortsArrB.length; i++){
        		if(returnPortId(selectedPortsArrB[i]) <= firstSlotPort && returnSlotId(selectedPortsArrB[i]) == 1){
        			loopCount = firstSlotPort;
        		}
        		else{
        			loopCount = 16;
        		}
        		if(globalFIBmodelBitMap[transformSlotToPort(selectedPortsArrB[i]) - 1] == 1){
        			continue;
        		}
        		for(p = returnPortId(selectedPortsArrB[i]); p <= loopCount; p++){
        			globalFIBmodelBitMap[transformSlotToPort(returnSlotId(selectedPortsArrB[i])+"/"+p) -1] = 1;
        			equipmentFCTableConfigPopup.store.newItem({ "id":0, "fiId": "B", "portId": p, "slotId": returnSlotId(selectedPortsArrB[i]), "userLabel":"Uplink" });
        			
        			removeOptionsArrOfB.push(returnSlotId(selectedPortsArrB[i])+"/"+p);
        		}
        	}
			
    		// remove port from Dropdown when enterd in table.
    		elementB=document.getElementById('availablePortsForFC_A_B');
    		for (i = elementB.options.length-1; i>=0; i--) {
				    if( removeOptionsArrOfB.indexOf(elementB.options[i].value) > -1 ) {
					   elementB.remove(i);
				   } 
				}
    		
         	equipmentFCDataStore.save();
         	equipmentFCTableConfigPopup.refresh();
         	equipmentFCPortTableForAB.refresh();
         }
       
   function uplinkAndFabricChannelPopup(){
	   dojo.style(uplinkAndFabricChannelDialog.buttonGroup.getItemAt(1).get("domNode"), "display", "none");	// Hide Cancel button
	   uplinkAndFabricChannelDialog.show();
   }
   
   function setDisableMultiSelectWhileUnChecked(){
		var isChecked = dijit.byId('isCheckedForFiBCopy').get("value");
		if(isChecked){
				dijit.byId('availablePortsForFC_A_B').set("selected", "");
				dijit.byId('availablePortsForFC_A_B').attr("disabled", true);
		}else{
				dijit.byId('availablePortsForFC_A_B').attr("disabled", false);
		}
	}
   
    require(["dojo/ready", "dojo/_base/json"], 
   		 function(ready, json){
   	 	
	    	 setTimeout(function(){
	    		 
	    		 var clickOkPopup = uplinkAndFabricChannelDialog.buttonGroup.getItemAt(0);
	    		 dojo.connect(clickOkPopup,"onClick",function(){
	    			 uplinkAndFabricChannelDialog.hide();
	    		 });
	    		 genrateDropdownForUplinkAndFCPorts('availablePortsForUplink_A_B',globalFIAmodelBitMap);
	    		 genrateDropdownForUplinkAndFCPorts('availablePortsForFC_A_B',globalFIBmodelBitMap);
					
	    		 equipUplinkDataStoreTable._saveCustom = function(saveComplete, saveFailed){
	    			 var equipmentUplinkTableAjson = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("manageEquipmentUplinkConfig.html" , equipmentUplinkTableAjson, null, "json");
	    			 saveComplete();
	    			 updateZeroIdsInDataStore(response, this);
			     };
			     dojo.connect(equipUplinkDataStoreTable, "onDelete", function(deletedItem){
			    	 if(deletedItem.fiId == "A"){
			    		 globalFIAmodelBitMap[transformSlotToPort(deletedItem.slotId+"/"+deletedItem.portId) -1 ] = 0;
				    	 removeOptions('availablePortsForUplink_A_B'); 
				    	 genrateDropdownForUplinkAndFCPorts('availablePortsForUplink_A_B',globalFIAmodelBitMap);
			    	 }
			    	 if(deletedItem.fiId == "B"){
			    		 globalFIBmodelBitMap[transformSlotToPort(deletedItem.slotId+"/"+deletedItem.portId) -1 ] = 0;
			    		 removeOptions('availablePortsForFC_A_B');
			    		 genrateDropdownForUplinkAndFCPorts('availablePortsForFC_A_B',globalFIBmodelBitMap);
			    	 }
			     });
			     
			     equipmentFCDataStore._saveCustom = function(saveComplete, saveFailed){
			    	 var equipmentFCTableBjson = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("manageEquipmentFCPortConfig.html" , equipmentFCTableBjson, null, "json");
	    			 saveComplete();
	    		     
	    			 updateZeroIdsInDataStore(response, this);
			     };
			     dojo.connect(dijit.byId("equipmentFCTableConfigPopup"),"onSelect",function(item,row){
			    	 if( (item.portId[0]%2) != 0){
			    		 this.deselect(item);
		    		 }
			     });
			     dojo.connect(equipmentFCDataStore, "onDelete", function(deletedItem){
			    	 if(deletedItem.fiId == "A"){
			    		 globalFIAmodelBitMap[transformSlotToPort(deletedItem.slotId+"/"+deletedItem.portId) -1 ] = 0;
				    	 removeOptions('availablePortsForUplink_A_B'); 
				    	 genrateDropdownForUplinkAndFCPorts('availablePortsForUplink_A_B',globalFIAmodelBitMap);
				    	 deletePreviousDataFromFCTable(equipmentFCDataStore,deletedItem.slotId+"/"+deletedItem.portId,"A");
			    	 }
			    	 if(deletedItem.fiId == "B"){
			    		 globalFIBmodelBitMap[transformSlotToPort(deletedItem.slotId+"/"+deletedItem.portId) -1 ] = 0;
			    		 removeOptions('availablePortsForFC_A_B');
			    		 genrateDropdownForUplinkAndFCPorts('availablePortsForFC_A_B',globalFIBmodelBitMap);
			    		 deletePreviousDataFromFCTable(equipmentFCDataStore,deletedItem.slotId+"/"+deletedItem.portId,"B");
			    	 }
			    	 
			    	 
			     });
			  
			     //save mode while onClick of radio button
			    /*  dojo.query("#deviceModeForm input[type=\"radio\"]").connect("onclick", function(){
			    	}); */
			     
	    	 },1000);
   	 
	     });
    
    function deletePreviousDataFromFCTable(deleteDataStoreObj,deletedSlotIdPortId,fiId){
    	deleteDataStoreObj.fetch({
    		onComplete: function(items, request){
    			for(var i = 0; i < items.length; i++){
    				if( (items[i].slotId == returnSlotId(deletedSlotIdPortId)) && (items[i].fiId == fiId) && (items[i].portId <= returnPortId(deletedSlotIdPortId)) ){console.log(items[i].portId);
    					deleteDataStoreObj.deleteItem(items[i]);
    				}
    	        }
    			
    		}
      	});
    	//deleteDataStoreObj.save();
    }
 
	/* Vishnu, Call function from common.js to save data for Equip Uplink Conf*/  
	function saveEquipmentUplinkConfData(){
			var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":0});
			response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
    		return true;
		
	}
	
   </script>

</head>
<body>
	<div id="parentDiv" >
 		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 100%;">
				<legend>Ethernet And Fibre Channel Uplink Ports</legend>
		        	<div class="equipServerConfig" style="display: table;margin: 0 auto;padding: 10px 0 0;">
		        		<form id="uplinkTableForm" data-dojo-id="tableForm" data-dojo-type="xwt.widget.notification.Form" name="tableForm"    action="#" method="post">
				        	<table>
				        		<tr>
				        			<td style="padding-left: 10px;">
				        				<button class="btn btn-primary" onClick="uplinkAndFabricChannelPopup();" type="button">Configure Ethernet &amp; FC Uplink Ports</button>
				        			</td>
				        		</tr>
				        	</table>
					  	</form>
			        </div>
			        <div class="floatleft" style="padding-left:20px;padding-top:15px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
			        	 <span dojoType="dojo.data.ItemFileWriteStore" jsId="equipUplinkDataStoreTable" data="dataEquipUplinkConfig"></span>
							<div style="width:500px !important;"  id="equipmentUplinkTollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentUplinkConfigTableForFIAB" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="none"></div>
									<div style="padding-top:12px;"><label>Ethernet Uplink Ports FI A &amp; B</label></div>
							</div>
							<div id="equipmentUplinkConfigTableForFIAB" 
								data-dojo-id="equipmentUplinkConfigTableForFIAB" 
								dojoType="xwt.widget.table.Table"
								store="equipUplinkDataStoreTable" 
								structure="equipUplinkFCColumns"
								rowsPerPage="100"
								style="width: 500px; height: 240px;" ></div>
						</div>
			        </div>
			        
			        <div class="floatleft" style="padding-left:20px;padding-top:15px;padding-right:16px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
			        	 <span dojoType="dojo.data.ItemFileWriteStore" jsId="equipmentFCDataStore" data="dataEquipmentFCConfig"></span>
							<div style="width:500px !important;"  id="equipmentFCTollBar" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentFCPortTableForAB" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="none"></div>
									<div style="padding-top:12px;"><label>Fibre Channel Uplink Ports FI A &amp; B</label></div>
							</div>
							<div id="equipmentFCPortTableForAB" 
								data-dojo-id="equipmentFCPortTableForAB" 
								dojoType="xwt.widget.table.Table" 
								store="equipmentFCDataStore" 
								structure="equipUplinkFCColumns"
								rowsPerPage="100"
								style="width:500px; height:240px;" ></div>
							</div>
			        </div>
				
			
			</fieldset>
		</div>
	</div>
<!-- Uplonk portChannel And FC Dilog data -->
	
	<div id="uplinkAndFabricChannelDialog" data-dojo-id="uplinkAndFabricChannelDialog" button1Label="Save" title="" dojoType="xwt.widget.layout.Dialog" closable="true" style="display: none;">
			<div style="width:100rem;height: 45rem;">
					<div class="floatleft" style="padding-left: 35px;">
						<div>Available Ports In FI A</div>
						<select id="availablePortsForUplink_A_B" data-dojo-id="availablePortsForUplink_A_B" name="availablePortsForUplink_A_B" data-dojo-type="dijit.form.MultiSelect" style="width: 120px;height: 440px;"></select>
					</div>
					<div class="floatleft" style="padding-left: 20px;">
						<div>Available Ports In FI B</div>
						<select id="availablePortsForFC_A_B" data-dojo-id="availablePortsForFC_A_B" name="availablePortsForFC_A_B" data-dojo-type="dijit.form.MultiSelect" style="width: 120px;height: 440px;"></select>
					</div>
					<div class="floatleft" style="padding-left: 10px;">
						<div style="padding-left: 10px;display: none;">
							<input id="isCheckedForFiBCopy" data-dojo-id="isCheckedForFiBCopy" onClick="javascript:setDisableMultiSelectWhileUnChecked();" dojoType="dijit.form.CheckBox" checked="true" />
							<label for="isCheckedForFiBCopy" style="margin-right:30px;">Copy To FI B</label>
						</div>
						
						<div style="padding-top: 140px;">
							<button data-dojo-type="dijit/form/Button" data-dojo-id="addPortToUplink_A_B" onClick="addPortToUplinkPort_A_B();" type="button">Add To ETH Uplink &gt;</button>
						</div>
						<div style="clear: both;">&nbsp;</div>
						<div style="padding-top: 150px;">
							<button data-dojo-type="dijit/form/Button" data-dojo-id="addPortToFC_A_B" onClick="addPortToFCPort_A_B();" type="button">Add To FC Uplink &gt;</button>
						</div>
						<div style="clear: both;">&nbsp;</div>
	        				<div id="importantNote" data-dojo-id="importantNote" style="width: 150px;text-align: justify;font-size:11px;">
								<label style="color:#2B9ADB;   text-align: left;" >Note:</label>
								Configuring FC uplink ports <span style="font-weight: bold;">on the fixed port module</span> will reboot the FI once the configuration is pushed to UCS manager. 
							</div>
					</div>
					
					<div class="floatleft">
						<!-- <fieldset style="height: 440px;width: 450px;margin-top: 0px;margin-bottom: 0px;margin-left: 10px;display: inline;">
							<legend>Ethernet uplink and FC uplink ports</legend> -->
						        <div style="padding-left:10px;">
						        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:430px; overflow:hidden;" splitter="true">
							        	
										<div style="width:480px !important;"  id="equipmentUplinkTollBarPopup" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentUplinkConfigTablePopup" quickFilter="false">
												<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
												<label>Ethernet Uplink Ports FI A &amp; B</label>
										</div>
										<div id="equipmentUplinkConfigTablePopup" 
											data-dojo-id="equipmentUplinkConfigTablePopup" 
											dojoType="xwt.widget.table.Table"
											store="equipUplinkDataStoreTable" 
											structure="equipUplinkFCColumns" 
											style="width: 430px; height: 160px;"
											selectMultiple="true" 
											selectAllOption="true" 
											showIndex="false" 
											selectModel="input"
											rowsPerPage="100"
											filterOnServer=false></div>
									</div>
						        </div>
						        
						        <div style="padding-left:10px;padding-top:20px;">
						        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:430px; overflow:hidden;" splitter="true">
								        
										<div style="width:480px !important;"  id="equipmentFCTollBarPopup" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentFCTableConfigPopup" quickFilter="false">
												<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
												<label>Fibre Channel Uplink Ports FI A & B</label>
										</div>
										<div id="equipmentFCTableConfigPopup" 
											data-dojo-id="equipmentFCTableConfigPopup" 
											dojoType="xwt.widget.table.Table" 
											store="equipmentFCDataStore" 
											structure="equipUplinkFCColumns" 
											style="width:430px; height:190px;" 
											selectMultiple="true" 
											selectAllOption="true" 
											showIndex="false" 
											selectModel="input"
											rowsPerPage="100"
											filterOnServer=false></div>
										</div>
						        </div>
							
						<!-- </fieldset> -->
					</div>
			</div>
			<div style="float: left;width: 890px;text-align: justify;padding: 5px 3px 3px 3px;border: 1px dashed #F42E2C;margin: 5px 0px 0px 36px;">
				<div style="height: 30px;width: 25px;" class="fi-info floatleft"></div> Only odd numbered ports can be selected as FC ports. Once an odd number port is selected as FC port all the remaining ports coming after the selected port in that slot will get added as FC ports by default.
			</div>
		</div>
	
</body>
</html>