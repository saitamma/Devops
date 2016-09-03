<%-- equipmentServerConfig.jsp --%>
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
			dojo.require("xwt.widget.form.DropDown");
			dojo.require("xwt.widget.table.GlobalToolbar");
            
			var equipServerConfigDataResponse = ajaxCallGet("fetchEquipmentServerConfig.html", true, "json");
			var parsedInputEquipServerConfigDataResponse = JSON.parse(equipServerConfigDataResponse);
			var dataForFIAEquipServerConfig = { items:JSON.parse("[" + parsedInputEquipServerConfigDataResponse.A + "]") };
			var dataForFIBEquipServerConfig = { items:JSON.parse("[" + parsedInputEquipServerConfigDataResponse.B + "]") };
			var chassisCountInfo = parseInt(ajaxCallGet("fetchSelectedChassisInfo.html", true, "json"));

			var slotIdOptions = [];
			for(var i = 0 ; i< getNumberOfSlots(); i++){
				slotIdOptions.push({value: i+1, label: (i+1)+""});
			}
			
				var equipServerColumnsFIA = [
			     	{ label: 'dbID', attr: 'id', hidden:true },
					{ label: 'ID', attr: 'fiId', sorted: 'ascending', vAlignment: "middle", align:'center', hidden:true },
					{label: 'Chassis', attr: 'chassis', sortable: true, sorted: 'ascending', width: 90, vAlignment: "middle", align:'center', editable: true,
						editWidget: {
			 				widget: xwt.widget.form.DropDown,
			 				options: { options: [], id:"chassisDropDownFIA", maxHeight:200 }
			 					}
					},
					{ label: 'Slot Id', attr: 'slotId', sortable: true, sorted: 'ascending', width: 80, vAlignment: "middle", align:'center',editable: true,
						editWidget: {
   			 				widget: xwt.widget.form.DropDown,
   			 				options: { options: slotIdOptions }
   			 			}
					},
					{ label: 'Port Id', attr: 'portId', sortable: true,  sorted: 'ascending', width: 80, vAlignment: "middle", align:'center', formatter:updateBitMap, editable: true,
	                   	   editWidget: {
	                       		widget:xwt.widget.notification.ValidationTextBox,
	       							options: {
	       								regExp:REG_EX_NUMBER_1_TO_48,
	       								required: true,
	       	                			trim: true,
	       	                			maxlength:"2",
	       								invalidMessage:MSG_BET_1_48
	       							}
	                       		}
						},
					{label: 'User Label', attr: 'userLabel', width: 170, vAlignment: "middle", align:'center', editable: true, 
						editWidget: {
							widget:xwt.widget.notification.ValidationTextBox,
		            		options: { trim: true, maxlength:"45"}
		  					}
					}]; 
			var equipServerColumnsFIB = [
   			     	{ label: 'dbID', attr: 'id', hidden:true },
   					{ label: 'ID', attr: 'fiId', sorted: 'ascending', vAlignment: "middle", align:'center', hidden:true },
   					{label: 'Chassis', attr: 'chassis', sortable: true, sorted: 'ascending', width: 90, vAlignment: "middle", align:'center', editable: true,
   						editWidget: {
   			 				widget: xwt.widget.form.DropDown,
   			 				options: { options: [], id:"chassisDropDownFIB", maxHeight:200 }
   			 					}
   					},
   					{ label: 'Slot Id', attr: 'slotId', sortable: true, sorted: 'ascending', width: 80, vAlignment: "middle", align:'center',editable: true,
   						editWidget: {
   			 				widget: xwt.widget.form.DropDown,
   			 				options: { options: slotIdOptions}
   			 			}
   					},
   					{ label: 'Port Id', attr: 'portId', sortable: true, sorted: 'ascending', width: 80, vAlignment: "middle", align:'center', formatter:updateBitMap, editable: true,
   						editWidget: {
                       		widget:xwt.widget.notification.ValidationTextBox,
       							options: {
       								regExp:REG_EX_NUMBER_1_TO_48,
       								required: true,
       	                			trim: true,
       	                			maxlength:"2",
       								invalidMessage:MSG_BET_1_48
       							}
                       		}
   					},
   					{label: 'User Label', attr: 'userLabel', width: 170, vAlignment: "middle", align:'center', editable: true, 
   						editWidget: {
   							widget:xwt.widget.notification.ValidationTextBox,
   		            		options: { trim: true, maxlength:"45"}
   		  					}
   					}];
			
		function getNumberOfSlots(){
			var numberOfSlots = 2;
			if(globalFIAmodelBitMap.length == 96){
				numberOfSlots = 4;
			}
			return numberOfSlots;
		}	
		function populateChassisDropdown(chassisCount,chassisDropdownId,selectedVal){
			var selectChassisDijitObj = dijit.byId(chassisDropdownId);
			dojo.forEach(selectChassisDijitObj.getOptions(),function(val,i){
				selectChassisDijitObj.removeOption(val.value);
     		});

			if(chassisCount == 0){
				chassisCount = 20;
			}
			var chassisOptionsArr = [];
			if(chassisCount > 0){
				for(var i=1; i<=chassisCount; i++){
					chassisOptionsArr.push({value: i.toString(), label: i.toString()});
				}
				selectChassisDijitObj.addOption(chassisOptionsArr);
				selectChassisDijitObj.set("value",selectedVal);
				selectChassisDijitObj.loadAndOpenDropDown();
				selectChassisDijitObj.closeDropDown();
			}
		}
			
		function updateBitMap(portId , item){
			if(item.fiId == 'A' && globalFIAmodelBitMap.length != 0){
				globalFIAmodelBitMap[transformSlotToPort(item.slotId+"/"+portId) -1] = 1;
			} else if(item.fiId == 'B' && globalFIBmodelBitMap.length != 0){
				globalFIBmodelBitMap[transformSlotToPort(item.slotId+"/"+portId) -1] = 1;
			}
			return portId+"";
		}
		
		
		function confirmToDeleteServerPortWhileGenrate(){
			if(dijit.byId("equipmentNoOfChasis").get("value") != "" && ( getDataStoreSize(equipServerDataStoreFiA) || getDataStoreSize(equipServerDataStoreFiB) > 0 )){
				displayConfirmationDialog(MSG_EQUIP_SERVER_CONFIRM_GENERATE,equipmentServerConfigGenerateData);
				return false;
			}else if(dijit.byId("equipmentNoOfChasis").get("value") != ""){
				equipmentServerConfigGenerateData();
			}else{
				displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
				return false;
			}
		}
		
        function equipmentServerConfigGenerateData(){
        	var chassisConfActionVal = parseInt(selectAction.get("value"));

        	// Delete all existing posts from FIA or FIB
         	deleteAllDataFromTable(equipServerDataStoreFiA);
			deleteAllDataFromTable(equipServerDataStoreFiB);
			
        	var count = parseInt(dijit.byId("equipmentNoOfChasis").get("value"));
        	var totalPortsTobeGenerated = count*chassisConfActionVal;
         	
        	var availablePortIdsForA = getAvailablePortIds('A', totalPortsTobeGenerated, null , null);
        	var availablePortIdsForB = getAvailablePortIds('B', totalPortsTobeGenerated, null , null);
         	
         	if(availablePortIdsForA.length < totalPortsTobeGenerated || availablePortIdsForB.length < totalPortsTobeGenerated){
         		displayNotificationAlert(MSG_NOT_ENOUGH_PORT);
         		return false;
         	}
			
	        // Storing no.of chassis details 
	   		var obj = {id:0,projectKey:"CHASSIS_COUNT",projectValue:count};
	   		response = ajaxCallPostWithJsonContent("updateSelectedChassisInfo.html" , JSON.stringify(obj), null, "text");
	   		if(response == "success"){
	   			chassisCountInfo = count;
	   		}
			
         	var chassisCount = 0;
         	var iomPortCount = 1;
         	for(i = 0 ; i < totalPortsTobeGenerated ; i++){
         		if(i % chassisConfActionVal == 0){
         			chassisCount++;
         			iomPortCount = 1;
         		}else{
         			iomPortCount ++;
         		}
     			equipmentServerConfigTableA.store.newItem({ "id":0, "fiId": "A", "portId": parseInt(tranformToSlot(availablePortIdsForA[i]).split("/")[1]) , "slotId": parseInt(tranformToSlot(availablePortIdsForA[i]).split("/")[0]),"chassis":chassisCount, "userLabel":"Chassis "+ chassisCount +" IOM port "+ iomPortCount });
     			equipmentServerConfigTableB.store.newItem({ "id":0, "fiId": "B", "portId": parseInt(tranformToSlot(availablePortIdsForB[i]).split("/")[1]) , "slotId": parseInt(tranformToSlot(availablePortIdsForB[i]).split("/")[0]),"chassis":chassisCount, "userLabel":"Chassis "+ chassisCount +" IOM port "+ iomPortCount });

         	}
   		 
         	equipServerDataStoreFiA.save();
	     	equipmentServerConfigTableA.refresh();
	     	equipServerDataStoreFiB.save();
		    equipmentServerConfigTableB.refresh();
		   
         }
        
       function checkForFreePorts(oldItem, newItem, bitMapObj, obj){
    	   var newPort = newItem.slotId+"/"+newItem.portId;
    	   var newPortnumber = transformSlotToPort(newPort);
    	   if( (newPortnumber-1) > bitMapObj.length){
    		   obj.errorMessage = MSG_CONFIG_SERVER_PORT_INVALID;
    		   return false;
    	   }
			if( bitMapObj[newPortnumber - 1] == 1){
				obj.errorMessage = MSG_CONFIG_SERVER_PORT_ERROR;
				return false;
			}else{
				var oldPort = oldItem.slotId+"/"+oldItem.portId;
				bitMapObj[transformSlotToPort(oldPort)-1] = 0;
			}
			return true;
       }
        
     require(["dojo/ready", "dojo/_base/json"], function(ready, json){
	 	setTimeout(function(){
	 		
	    	var	noOfChasisArr = [];
			noOfChasisArr.push({value: "", label:LABEL_SELECT, selected: true});
			for(var i=1;i<=20;i++){
				noOfChasisArr.push({value: i +"",	label: i +""});
			}
			equipmentNoOfChasis.addOption(noOfChasisArr);
			
			dojo.connect(dijit.byId("equipmentServerConfigTableA"),"onEdit",function(item){
				populateChassisDropdown(chassisCountInfo,"chassisDropDownFIA",item.chassis[0]);
   		 	 });
			
			 dojo.connect(dijit.byId("equipmentServerConfigTableB"),"onEdit",function(item){
				populateChassisDropdown(chassisCountInfo,"chassisDropDownFIB",item.chassis[0]);
   		 	 });
			 
    		 equipServerDataStoreFiA._features["generateId"] = true;
    		 equipServerDataStoreFiB._features["generateId"] = true;
    		 
    		 equipServerDataStoreFiA._saveCustom = function(saveComplete, saveFailed){
    			 var equipmentTableAJson = returnChangedDataFromDataStore(this,json);
    			 var response = ajaxCallPostWithJsonContent("manageEquipmentServerConfig.html" , equipmentTableAJson, null, "json");
    			 saveComplete();
    		     	    			 
    			 updateZeroIdsInDataStore(response, this);
		     };

		     equipServerDataStoreFiB._saveCustom = function(saveComplete, saveFailed){
		    	 var equipmentTableBJson = returnChangedDataFromDataStore(this,json);
		    	 var response = ajaxCallPostWithJsonContent("manageEquipmentServerConfig.html" , equipmentTableBJson , null, "json");
    			 saveComplete();
		    	 
    			 updateZeroIdsInDataStore(response, this);
		     };
		     
		     dojo.connect(equipServerDataStoreFiA, "onDelete", function(deletedItem){
		    	 globalFIAmodelBitMap[transformSlotToPort(deletedItem.slotId+"/"+deletedItem.portId) -1 ] = 0;
		     });
		     
		     dojo.connect(equipServerDataStoreFiB, "onDelete", function(deletedItem){
		    	 globalFIBmodelBitMap[transformSlotToPort(deletedItem.slotId+"/"+deletedItem.portId) -1 ] = 0;
		     });
    	   equipmentServerConfigTableA.validateRow = {
				isValid: function (oldItem, newItem) {
					if((oldItem.slotId[0] != newItem.slotId) || (oldItem.portId[0] != newItem.portId)){
						return checkForFreePorts(oldItem, newItem, globalFIAmodelBitMap, this);
					}
					return true;
				}
			 };
    	   
    	   equipmentServerConfigTableB.validateRow = {
   				isValid: function (oldItem, newItem) {
   					if((oldItem.slotId[0] != newItem.slotId) || (oldItem.portId[0] != newItem.portId)){
   						return checkForFreePorts(oldItem, newItem, globalFIBmodelBitMap);
   					}
				return true;
   				}
   			 };
			     
	    },1000);
    });

    function saveEquipmentServerConfData(){
    	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":2,"activeStateSubMenuIndex":2});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		
		return true;
    }
    
   </script>
</head>
<body>
	
	<div id="parentDiv" class="">
 		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 100%;">
				<legend>Server Ports</legend>
           	
		        	<div class="equipServerConfig">
		           	   <form id="equipmentServerTableForm" data-dojo-id="equipmentServerTableForm" data-dojo-type="dijit.form.Form" name="equipmentServerTableForm"    action="#" method="post">
		           	   	<table>
							<tr >
								<td height="30px">
									<div class="labelspace">
										<label style="float:left;">Number Of Chassis:<em>*</em></label>			
									</div>
								</td>
								<td height="30px">
									<select id="equipmentNoOfChasis" data-dojo-id="equipmentNoOfChasis" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 140px" />
								</td>
								<td height="30px" style="padding-left: 10px;">
									<button data-dojo-type="dijit.form.Button" data-dojo-id="equipmentGenerateDataBtn" onClick="confirmToDeleteServerPortWhileGenrate();" type="button">Add</button>
								</td>
							</tr>
						</table>
		           	   	  
					  </form>
			        </div>
			        <div class="quote-container">
						  <blockquote class="note">
						   <span class="fi-minor"></span>Please make sure the server ports being configured are exactly the same as the real connections. If any chassis fails to come up the tool will not be able to detect it.
						  </blockquote>
					</div>
			        
			        
			        <div class="floatleft" style="padding-left:20px;padding-top:15px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
				        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="equipServerDataStoreFiA" data="dataForFIAEquipServerConfig"></span>
							<div style="width:500px !important;"  id="equipmentServerToolBarA" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentServerConfigTableA" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
									<label>FI A</label>
							</div>
							<div id="equipmentServerConfigTableA" 
								data-dojo-id="equipmentServerConfigTableA" 
								dojoType="xwt.widget.table.Table"
								store="equipServerDataStoreFiA" 
								structure="equipServerColumnsFIA" 
								style="width: 500px; height: 180px;"
								selectMultiple="true" 
								selectAllOption="true" 
								showIndex="false" 
								selectModel="input" 
								rowsPerPage="100"
								filterOnServer=false></div>
						</div>
			        </div>
			        
			        <div class="floatleft" style="padding-left:20px;padding-top:15px;padding-right:16px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
					        <span dojoType="dojo.data.ItemFileWriteStore" jsId="equipServerDataStoreFiB" data="dataForFIBEquipServerConfig"></span>
							<div style="width:500px !important;"  id="equipmentServerToolBarB" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentServerConfigTableB" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
									<label>FI B</label>
							</div>
							<div id="equipmentServerConfigTableB" 
								data-dojo-id="equipmentServerConfigTableB" 
								dojoType="xwt.widget.table.Table" 
								store="equipServerDataStoreFiB" 
								structure="equipServerColumnsFIB" 
								style="width:500px; height:180px;" 
								selectMultiple="true" 
								selectAllOption="true" 
								showIndex="false" 
								selectModel="input"
								rowsPerPage="100"
								filterOnServer=false></div>
							</div>
			        </div>
			</fieldset>
		</div>
	</div>
</body>
</html>

