function formatterConfigureLanSanPolicy(data,item){
	return '<button id="serversBootPolicyConfigureBtn'+item.id[0]+'" class="btn btn-primary" onClick="configureLanSanPolicy(event,'+item.id[0]+');" type="button">Config</button>';
}
function formatterAddTargetSanPolicy(data,item){
		return '<button id="serversAddTargetConfigureBtn'+item.id[0]+'" class="btn btn-primary" onClick="addTargetSanPolicy(event,'+item.id[0]+');" type="button">Add Target</button>';
	}

function serversBootPolicyGenerateData(){
	var formObject2 =  dojo.formToObject("serversBootPolicyTableForm");
	if(dijit.byId("serversBootPolicyTableForm").validate()==false || formObject2.noOfBootPolicy == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	
 	var selectedOrg = parseInt(formObject2.serversBootPolicyOrganization);
 	var noOfBootPolicy = parseInt(formObject2.noOfBootPolicy);

 	for(i = 1 ; i <= noOfBootPolicy ; i++){
 		bootPolicyInt++;
		var bootPolicyName = "Boot-P"+bootPolicyInt;
 		if(!checkTableFieldValueUnique(serversBootPolicyDataStoreTab,"name",bootPolicyName)){
 			continue;
 		}
 		var serversBootPolicyDefItem = {
 				"id":0,
 				"name":	bootPolicyName,
 				"description": "Boot Policy "+bootPolicyInt,
 				"enforceVnicName":1,
 				"rebootOnUpdate": 0,
 				"bootMode": bootModes[0].value,
 				"secureBoot": "n/a",
 				"organizations":selectedOrg
 			};
 		serversBootPolicyTable.store.newItem(serversBootPolicyDefItem);
 	}
 	serversBootPolicyDataStoreTab.save();
 	serversBootPolicyTable.refresh();
 	
 }
 
var bootPolicyForLanDataTable={items:[]},bootPolicyForSanDataTable={items:[]},bootPolicyForSanTargetDataTable={items:[]};
function configureLanSanPolicy(e,bootPolicyId){
	dojo.empty("dndForLANandSANandLocaldisk");
	var orderArray = [];
	tempBootPolicyId = bootPolicyId;
	dojo.stopEvent(e);
	dijit.byId("bootPolicyId").set("value",bootPolicyId);
	
	// get LAN Config
	bootPolicyLanConfigDataResponse = ajaxCallPostWithJsonContent("getBootPolicyLanConfigDetails.html",parseInt(bootPolicyId), null, "json");
	console.log(bootPolicyLanConfigDataResponse);
	var loadDataFromDBForLAN = { items:JSON.parse("[" + bootPolicyLanConfigDataResponse  + "]") };
	if(loadDataFromDBForLAN.items.length > 0){
		orderArray[loadDataFromDBForLAN.items[0].order - 1] = "LAN";
	}
	bootPolicyForLanDataStoreTable.clearOnClose = true;
	bootPolicyForLanDataStoreTable.data = loadDataFromDBForLAN; 
	bootPolicyForLanDataStoreTable.close();
	bootPolicyForLanTablePopup.closeEditor();
	bootPolicyForLanTablePopup.refresh();
	
	// get SAN config
	bootPolicySanConfigDataResponse = ajaxCallPostWithJsonContent("getBootPolicySanConfigDetails.html",parseInt(bootPolicyId), null, "json");
	console.log(bootPolicySanConfigDataResponse);
	var loadDataFromDBForSAN = { items:JSON.parse("[" + bootPolicySanConfigDataResponse + "]") };
	if(loadDataFromDBForSAN.items.length > 0){
		orderArray[loadDataFromDBForSAN.items[0].order - 1] = "SAN";
	}
	bootPolicyForSanDataStore.clearOnClose = true;
	bootPolicyForSanDataStore.data = loadDataFromDBForSAN; 
	bootPolicyForSanDataStore.close();
	bootPolicyForSanTableConfigPopup.closeEditor();
	bootPolicyForSanTableConfigPopup.refresh();
	
	// get SAN Target config
	bootPolicySanTargetConfigDataResponse = ajaxCallPostWithJsonContent("getBootPolicySanTargetConfigDetails.html",parseInt(bootPolicyId), null, "json");
	console.log(bootPolicySanTargetConfigDataResponse);
	var loadDataFromDBForSANTarget = { items:JSON.parse("[" + bootPolicySanTargetConfigDataResponse + "]") };
	bootPolicyForSanTargetDataStore.clearOnClose = true;
	bootPolicyForSanTargetDataStore.data = loadDataFromDBForSANTarget; 
	bootPolicyForSanTargetDataStore.close();
	bootPolicyForSanTargetTableConfigPopup.closeEditor();
	bootPolicyForSanTargetTableConfigPopup.refresh();
	
	// get Local Disc config
	var loadDataFromDBForLocalDisc = { items:JSON.parse("[" + ajaxCallPostWithJsonContent("getBootPolicyLocalDiscConfigDetails.html",parseInt(bootPolicyId), null, "json") + "]") };
	makeDisabledFalseLocalDisk();
	
	if(loadDataFromDBForLocalDisc.items.length > 0){
		dojo.forEach(loadDataFromDBForLocalDisc.items,function(val,i){
			dijit.byId(val.device).set("checked",true);
			orderArray[val.bootOrder - 1] = val.device.replace(/_/g, " ");
			
			if(val.device == "Local_Disk"){
				makeDisabledTrueLocalDisk();
			}
		});
		
	}
	dojo.forEach(orderArray,function(v,i){
		if(v != undefined){
			addDragnDropList(v);
		}
	});

	if(getDataStoreSize(bootPolicyForLanDataStoreTable) == 2){
		 dijit.byId("addLanPolicyConfig").set("disabled", true);
	 }else{
		 dijit.byId("addLanPolicyConfig").set("disabled", false);
	 }
	 if(getDataStoreSize(bootPolicyForSanDataStore) == 2){
		 dijit.byId("addSANPolicyConfig").set("disabled", true);
	 }else{
		 dijit.byId("addSANPolicyConfig").set("disabled", false);
	 }
	
	dojo.style(serversBootPolicyConfigureDialog.buttonGroup.getItemAt(1).get("domNode"), "display", "none");	// Hide Cancel button
	serversBootPolicyConfigureDialog.show();
}

function getDefaultOrderForLanSan(){
	var orderItems = [],order=1;
	var source = new dojo.dnd.Source(document.getElementById("dndForLANandSANandLocaldisk"));
	
	var orderedDataItems = source.getAllNodes().map(function(node){
			orderLabel = source.getItem(node.id).data;
			orderItems[orderLabel] = order++;
	    });
	return orderItems;
}

function addDragnDropList(addDndNode){
	 var source = new dojo.dnd.Source(dojo.byId("dndForLANandSANandLocaldisk"));
		 source.insertNodes(false, [addDndNode]);
}
function removeDragnDropList(removeDndNode){
	var source = new dojo.dnd.Source("dndForLANandSANandLocaldisk");
		//source.selectAll().deleteSelectedNodes(); //dojoDndItemSelected getSelectedNodes
	source.forInItems(function(data, id){
		if(data.data == removeDndNode){
			var n = dojo.byId(id);
			source.delItem(id);
			domConstrct.destroy(n);
		}
	});
}

function checkSanBeforeChangingLocalDisk(node){
	
	if(getDataStoreSize(bootPolicyForSanDataStore) > 0) {
		displayNotificationAlert(MSG_BOOTCONFIG_WITH_SAN_AND_LOCALDISK);
		dijit.byId(node).set("checked", false);
		return false;
	}
	return true;
}

function checkLocalDiskBeforeAddingSAN(){
	
	if(dijit.byId("Local_Disk").get("checked") || dijit.byId("Local_LUN").get("checked") || dijit.byId("SD_Card").get("checked") || dijit.byId("Internal_USB").get("checked") || dijit.byId("External_USB").get("checked")) {
		displayNotificationAlert(MSG_BOOTCONFIG_WITH_SAN_AND_LOCALDISK);
		return false;
	}
	return true;
}

function addLanConfig(){
	var countData = getDataStoreSize(bootPolicyForLanDataStoreTable);
	if(countData == 0){
		addDragnDropList("LAN");
	}
	
	var bootPolicyLanName = "LAN-"+typeDropdownValue[countData].label;
	if(checkTableFieldValueUnique(bootPolicyForLanDataStoreTable,"name",bootPolicyLanName)){
		var defItemx = {
			"id":0,
			"name":	bootPolicyLanName,
			"description": bootPolicyLanName,
			"order": getDefaultOrderForLanSan()["LAN"],
			"lanVnic":vNICValuesList[0].value, //(vNICValuesList.length == 1)? null :vNICValuesList[1].value,
			"type": typeDropdownValue[countData].label,
			"serversBootPolicy": parseInt(dijit.byId("bootPolicyId").get("value"))
		};
	}
	console.log(defItemx);
	bootPolicyForLanTablePopup.store.newItem(defItemx);
	bootPolicyForLanDataStoreTable.save();
	if(countData == 1){
		dijit.byId("addLanPolicyConfig").set("disabled", true);
	}
}

function addSANConfig(){
	
	if(checkLocalDiskBeforeAddingSAN()) {
		var countData = getDataStoreSize(bootPolicyForSanDataStore);
		if(countData == 0){
			addDragnDropList("SAN");
		}
		
		var bootPolicySanName = "SAN-"+typeDropdownValue[countData].label;
		if(checkTableFieldValueUnique(bootPolicyForSanDataStore,"name",bootPolicySanName)){
			var defItemx = {
				"id":0,
				"name":	bootPolicySanName,
				"description": bootPolicySanName,
				"order": getDefaultOrderForLanSan()["SAN"],
				"sanVhba": vhbaValues[0].value, //(vhbaValues.length == 1)? null :vhbaValues[1].value,
				"type": typeDropdownValue[countData].label,
				"serversBootPolicy":parseInt(dijit.byId("bootPolicyId").get("value"))
			};
		}
		bootPolicyForSanTableConfigPopup.store.newItem(defItemx);
		bootPolicyForSanDataStore.save();
		if(countData == 1){
			dijit.byId("addSANPolicyConfig").set("disabled", true);
		}
	}
}

function addTargetSanPolicy(e,serversBootPolicySan){
		dojo.stopEvent(e);
		
		var countData = checkTableFieldValueRowsLength(bootPolicyForSanTargetDataStore,"serversBootPolicySan",serversBootPolicySan);
		if(countData <= 1){
			var bootPolicySanTargetName = "SAN-Target-"+typeDropdownValue[countData].label;
			var defItemx = {
					"id":0,
					"serversBootPolicy":parseInt(dijit.byId("bootPolicyId").get("value")),
					"serversBootPolicySan": serversBootPolicySan,
					"name": bootPolicySanTargetName,
					"type": typeDropdownValue[countData].label,
					"lunId" : 0,
					"wwpnAddress" : "20:00:00:25:B5:00:00:00"
					};
			
			bootPolicyForSanTargetTableConfigPopup.store.newItem(defItemx);
			bootPolicyForSanTargetDataStore.save();
		}else{
			displayNotificationAlert(MSG_ADD_TWO_SAN_TARGET_POLICY);
		}
}
function makeDisabledFalseLocalDisk(){
	/* make local disk policy checkbox Enabled because parent is chacked*/
	dijit.byId("Local_Disk").set({disabled:false,checked:false});
	dijit.byId("Local_LUN").set({disabled:false,checked:false});
	dijit.byId("SD_Card").set({disabled:false,checked:false});
	dijit.byId("Internal_USB").set({disabled:false,checked:false});
	dijit.byId("External_USB").set({disabled:false,checked:false});
}
function makeDisabledTrueLocalDisk(){
	/* make local disk policy checkbox disabled because parent is chacked*/
	dijit.byId("Local_LUN").set({disabled:true,checked:false});
	dijit.byId("SD_Card").set({disabled:true,checked:false});
	dijit.byId("Internal_USB").set({disabled:true,checked:false});
	dijit.byId("External_USB").set({disabled:true,checked:false});
}
//Check unique field value while inserting.
function checkTableFieldValueRowsLength(datastoreObject,fieldName,fieldValue){
	var temp = {},returnValue; 
			 temp[fieldName] = fieldValue;
			 datastoreObject.fetch({
				 query: temp,
				 onComplete: function(items, request){
					 returnValue =items.length;
				 }
			});
		return returnValue;
}
// Delete San Boot Policy
function deleteSanBootPolicy(){
		displayConfirmationDialog(MSG_DELETED,deleteSanBootPolicyConfirm);
		return false;
}

function deleteSanBootPolicyConfirm(){
	 var selectedRows = bootPolicyForSanTableConfigPopup.selected();
	if(selectedRows.length>0){
		for(var i=0; i < selectedRows.length; i++){
			if(checkTableFieldValueRowsLength(bootPolicyForSanTargetDataStore,"serversBootPolicySan",selectedRows[i].id[0])){
				displayNotificationAlert(MSG_DELETING_SAN_BOOT_POLICY);
				return false;
			}else{
				bootPolicyForSanTableConfigPopup.store.deleteItem(selectedRows[i]);
			}
		}
	}
	bootPolicyForSanDataStore.save();
	bootPolicyForSanTableConfigPopup.refresh();
	
	bootPolicyForSanTableConfigPopup.clearSelections();
	dijit.byId("deleteSANPolicyConfig").set("disabled", true);
	
}
var selectedPrimarySanBootPolicy,selectedSecondrySanBootPolicy;
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 	
	    		 	dojo.connect(dijit.byId("BootPolicySanTargetWWPNAddress"),"onKeyUp",function(){
	    		 		macAddressValidUpperCase(dijit.byId("BootPolicySanTargetWWPNAddress"));
	    		 	});
	    		 
					dijit.byId("deleteSANPolicyConfig").set("disabled", true);
					dojo.connect(bootPolicyForSanTableConfigPopup, "onSelect", function(item, row){
						dijit.byId("deleteSANPolicyConfig").set("disabled", false);
					});
					dojo.connect(bootPolicyForSanTableConfigPopup, "onDeselect", function(item, row){
						if(bootPolicyForSanTableConfigPopup.selected().length == 0){
							dijit.byId("deleteSANPolicyConfig").set("disabled", true);
						}
					});
					
	    		 dojo.connect(dijit.byId("Local_Disk"),"onClick",function(){
		    			if(dijit.byId("Local_Disk").get("checked")){
		    				
		    				if(checkSanBeforeChangingLocalDisk("Local_Disk")) {
			    				addDragnDropList("Local Disk");
			    				makeDisabledTrueLocalDisk(); removeDragnDropList("Local LUN"); removeDragnDropList("SD Card"); removeDragnDropList("Internal USB"); removeDragnDropList("External USB");
		    				}
		    			}
		    			else{
		    				removeDragnDropList("Local Disk");
		    				makeDisabledFalseLocalDisk();
		    			}
	    		 });
				dojo.connect(dijit.byId("Local_LUN"),"onClick",function(){
					if(dijit.byId("Local_LUN").get("checked")){
						
						if(checkSanBeforeChangingLocalDisk("Local_LUN")) {
							addDragnDropList("Local LUN");
						}
					}else{
						removeDragnDropList("Local LUN");
					}
				});
				dojo.connect(dijit.byId("SD_Card"),"onClick",function(){
					if(dijit.byId("SD_Card").get("checked")){
						
						if(checkSanBeforeChangingLocalDisk("SD_Card")) {
							addDragnDropList("SD Card");
						}
					}else{
						removeDragnDropList("SD Card");
					}
				});
				dojo.connect(dijit.byId("Internal_USB"),"onClick",function(){
					if(dijit.byId("Internal_USB").get("checked")){
						
						if(checkSanBeforeChangingLocalDisk("Internal_USB")) {
							addDragnDropList("Internal USB");
						}
					}else{
						removeDragnDropList("Internal USB");
					}
				});
				dojo.connect(dijit.byId("External_USB"),"onClick",function(){
					if(dijit.byId("External_USB").get("checked")){
						
						if(checkSanBeforeChangingLocalDisk("External_USB")) {
							addDragnDropList("External USB");
						}
					}else{
						removeDragnDropList("External USB");
					}
				});
	    		 // ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	serversBootPolicyOrganization.addOption(selectOrganizationArr);
	    		 	
	    		 	dojo.connect(dijit.byId("bootModeId"),"onChange",function(){
	    		 		var selectDijitObj = dijit.byId("bootSecurityId");
	    		 		dojo.forEach(selectDijitObj.getOptions(),function(val,i){
			     			selectDijitObj.removeOption(val.value);
			     		});
			     		if(this.value == "uefi"){
			     			selectDijitObj.addOption({value:"yes",label:"Yes"});
	    		 			selectDijitObj.addOption({value:"no",label:"No"});
			     		}
			     		else{
			     			selectDijitObj.addOption({value:"n/a",label:"N/A"});
			     		}
		    			
		    		});
	    		 	
	    		 	dojo.connect(dijit.byId("serversBootPolicyTable"),"onEdit",function(item){
	    		 		var yesSelected = false, noSelected = false, selectDijitObj = dijit.byId("bootSecurityId");
			     		dojo.forEach(selectDijitObj.getOptions(),function(val,i){
			     			selectDijitObj.removeOption(val.value);
			     		});
			     		if(item.secureBoot[0]=="yes"){
			     			yesSelected = true;
			     		}
			     		if(item.secureBoot[0]=="no"){
			     			noSelected = true;
			     		}
	    		 		if(item.bootMode[0]=="uefi"){
	    		 			selectDijitObj.addOption({value:"yes",label:"Yes"});
	    		 			selectDijitObj.addOption({value:"no",label:"No"});
	    		 			selectDijitObj.getOptions()[0].selected = yesSelected;
	    		 			selectDijitObj.getOptions()[1].selected = noSelected;
    		 				
	    		 			selectDijitObj.loadAndOpenDropDown();
    		 				selectDijitObj.closeDropDown();
	    		 		}else{
		    		 		selectDijitObj.addOption({value:"n/a",label:"N/A"});
	    		 		}
	    		 	});

	    		 	serversBootPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversBootPolicyTablejson = returnChangedDataFromDataStore(this,json);
		    			var response = ajaxCallPostWithJsonContent("manageBootPolicyConfig.html" , serversBootPolicyTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
			     	};
			     	
			     	//Save LAN boot policy
			     	bootPolicyForLanDataStoreTable._saveCustom = function(saveComplete, saveFailed){
		    			var lanBootPolicyTablejson = returnChangedDataFromDataStore(this,json);
		    			console.log(lanBootPolicyTablejson);
		    			var response = ajaxCallPostWithJsonContent("manageBootPolicyLanConfig.html" , lanBootPolicyTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
		    			bootPolicyForLanTablePopup.refresh();
			     	};
			     	//Save SAN boot policy
			     	bootPolicyForSanDataStore._saveCustom = function(saveComplete, saveFailed){
		    			var sanBootPolicyTablejson = returnChangedDataFromDataStore(this,json);
		    			var response = ajaxCallPostWithJsonContent("manageBootPolicySanConfig.html" , sanBootPolicyTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
		    			bootPolicyForSanTableConfigPopup.refresh();
			     	};
			     	
			     	//Save SAN Target boot policy 
			     	bootPolicyForSanTargetDataStore._saveCustom = function(saveComplete, saveFailed){
		    			var sanTargetBootPolicyTablejson = returnChangedDataFromDataStore(this,json);
		    			var response = ajaxCallPostWithJsonContent("manageBootPolicySanTargetConfig.html" , sanTargetBootPolicyTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
		    			bootPolicyForSanTargetTableConfigPopup.refresh();
			     	};
			     	// LAN Policy OnDelete Action
			     	dojo.connect(bootPolicyForLanDataStoreTable, "onDelete", function(deletedItem){
			     		var countData = getDataStoreSize(bootPolicyForLanDataStoreTable)
			     		if(countData == 0){
			     			removeDragnDropList("LAN");
			     		}
			     		if(countData == 1){
			     			if(deletedItem.type == "Primary"){
			     				bootPolicyForLanDataStoreTable.fetch({
			  			    	  onComplete: function(items, request){
			  			    		  if(items && items.length > 0){
			  					    		var item = items[0];
			  					    		bootPolicyForLanDataStoreTable.setValue(item, "name", "LAN-Primary");
			  					    		bootPolicyForLanDataStoreTable.setValue(item, "description", "LAN-Primary");
			  					    		bootPolicyForLanDataStoreTable.setValue(item, "type", "Primary");
			  					    	}	  
			  			    	 	}
			  			    	});
			     			}
			     		}
			     		bootPolicyForLanDataStoreTable.save();
			     		 dijit.byId("addLanPolicyConfig").set("disabled", false);
			     	});
			     	
			     // SAN Policy OnDelete Action
			     	dojo.connect(bootPolicyForSanDataStore, "onDelete", function(deletedItem){
			     		var countData = getDataStoreSize(bootPolicyForSanDataStore);
			     		if(countData == 0){
			     			removeDragnDropList("SAN");
			     		}
			     		if(countData == 1){
			     			if(deletedItem.type == "Primary"){
			     				bootPolicyForSanDataStore.fetch({
			  			    	  onComplete: function(items, request){
			  			    		  if(items && items.length > 0){
			  					    		var item = items[0];
			  					    		bootPolicyForSanDataStore.setValue(item, "name", "SAN-Primary");
			  					    		bootPolicyForSanDataStore.setValue(item, "description", "SAN-Primary");
			  					    		bootPolicyForSanDataStore.setValue(item, "type", "Primary");
			  					    		
			  					    		bootPolicyForSanTargetTableConfigPopup.refresh();
			  					    	}	  
			  			    	 	}
			  			    	});
			     			}
			     		}
			     		bootPolicyForSanDataStore.save();
			     		dijit.byId("addSANPolicyConfig").set("disabled", false);
			     	});
			     
			     // SAN Target Policy OnDelete Action
			     	dojo.connect(bootPolicyForSanTargetDataStore, "onDelete", function(deletedItem){
			     		if(deletedItem.type == "Primary"){
			     			bootPolicyForSanTargetDataStore.fetch({
			     				  query: {serversBootPolicySan: deletedItem.serversBootPolicySan[0]},
			  			    	  onComplete: function(items, request){
			  			    		  if(items && items.length > 0){
			  					    		var item = items[0];
			  					    		bootPolicyForSanTargetDataStore.setValue(item, "name", "SAN-Target-Primary");
			  					    		bootPolicyForSanTargetDataStore.setValue(item, "type", "Primary");
			  					    	}	  
			  			    	 	}
			  			    	});
			     			}
			     		bootPolicyForSanTargetDataStore.save();
			     	});
			     
			     	var bootPolicySaveBtn = serversBootPolicyConfigureDialog.buttonGroup.getItemAt(0);
			     	var bootPolicyCancelBtn = serversBootPolicyConfigureDialog.buttonGroup.getItemAt(1);
			     	
			     	dojo.connect(bootPolicyCancelBtn,"onClick",function(){
			     		dojo.empty("dndForLANandSANandLocaldisk");
			     	});
			     	
			     	dojo.connect(bootPolicySaveBtn,"onClick",function(){
			     		
			     		if(!checkNullFieldValueInDataStore(bootPolicyForLanDataStoreTable,"lanVnic") || !checkNullFieldValueInDataStore(bootPolicyForSanDataStore,"sanVhba") ){
			     			displayNotificationAlert(MSG_BOOTPOLICY_POPUP_REQUIRED_FIELD);
			     			return false;
			     		}
			     		
						// Validate for at least one one entry for SAN primary and secondary Target
						var returnVal = false;
						bootPolicyForSanDataStore.fetch({
	  			    	  onComplete: function(items, request){
	  			    		  if(items && items.length > 0){
	  			    			for(var i=0; i< items.length; i++){
	  			    				var rowCount = checkTableFieldValueRowsLength(bootPolicyForSanTargetDataStore,"serversBootPolicySan",items[i].id[0]);
	  			    				if(rowCount == 0){
	  			    					returnVal = true;
	  			    					return false;
	  			    				}
	  			    			  }
	  					    	}	  
	  			    	 	}
	  			    	});
						if(returnVal){
							displayNotificationAlert(MSG_ATLEAST_ONE_SAN_TARGET);
							return false;
						}
			     		// Order Save for LAN
			     		bootPolicyForLanDataStoreTable.fetch({
		  			    	  onComplete: function(items, request){
		  			    		  if(items && items.length > 0){
		  			    			  for(var i=0; i< items.length; i++){
		  					    		bootPolicyForLanDataStoreTable.setValue(items[i], "order", getDefaultOrderForLanSan()["LAN"]);
		  			    			  }
		  					    	}	  
		  			    	 	}
		  			    	});

			     		// Oder save for SAN
			     		 bootPolicyForSanDataStore.fetch({
	  			    	  onComplete: function(items, request){
	  			    		  if(items && items.length > 0){
	  			    			for(var i=0; i< items.length; i++){
	  			    				bootPolicyForSanDataStore.setValue(items[i], "order", getDefaultOrderForLanSan()["SAN"]);
	  			    			  }
	  					    	}	  
	  			    	 	}
	  			    	});
			     		bootPolicyForLanDataStoreTable.save(); // save order in DB for LAN
			     		bootPolicyForSanDataStore.save(); // save order in DB for SAN
			     		
			     		var bootLocalDiskPolicyToSave = JSON.parse(dojo.formToJson("localDiskPolicyConfigPopup"));
			     		var localDiskOrderValue = [],SaveLocalDiskOrderWithVal = [];
			     		if(bootLocalDiskPolicyToSave.Local_Disk){
			     			localDiskOrderValue.push({id:0,serversBootPolicy:tempBootPolicyId,device:bootLocalDiskPolicyToSave.Local_Disk,bootOrder:getDefaultOrderForLanSan()["Local Disk"]});
			     		}
			     		else{
			     			if(bootLocalDiskPolicyToSave.Local_LUN){
			     				localDiskOrderValue.push({id:0,serversBootPolicy:tempBootPolicyId,device:bootLocalDiskPolicyToSave.Local_LUN,bootOrder:getDefaultOrderForLanSan()["Local LUN"]});
			     			}
			     			if(bootLocalDiskPolicyToSave.SD_Card){
			     				localDiskOrderValue.push({id:0,serversBootPolicy:tempBootPolicyId,device:bootLocalDiskPolicyToSave.SD_Card,bootOrder:getDefaultOrderForLanSan()["SD Card"]});
			     			}
			     			if(bootLocalDiskPolicyToSave.Internal_USB){
			     				localDiskOrderValue.push({id:0,serversBootPolicy:tempBootPolicyId,device:bootLocalDiskPolicyToSave.Internal_USB,bootOrder:getDefaultOrderForLanSan()["Internal USB"]});
			     			}
			     			if(bootLocalDiskPolicyToSave.External_USB){
			     				localDiskOrderValue.push({id:0,serversBootPolicy:tempBootPolicyId,device:bootLocalDiskPolicyToSave.External_USB,bootOrder:getDefaultOrderForLanSan()["External USB"]});
			     			}
			     		}
			     		if(localDiskOrderValue.length == 0){
			     			localDiskOrderValue.push({serversBootPolicy:tempBootPolicyId});
			     		}
			     		var response = ajaxCallPostWithJsonContent("manageBootPolicyLocalDiscConfig.html",JSON.stringify(localDiskOrderValue), null, "json");
			     		
			     		dojo.empty("dndForLANandSANandLocaldisk");
			     		serversBootPolicyConfigureDialog.hide();
			     		
			     	});
			     	
	    	dojo.addOnLoad(function () {
			     		serversBootPolicyTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversBootPolicyDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
								return true;
							}
						};
						
					});
	    	
	    	 },1000);
	 
	     });

