var standByDijitObj;
var standByDijitObjDown;
var currentUrl = window.location.href;
var redirectUrl = "j_spring_security_logout";
if(currentUrl.indexOf("ucsada-dev") > -1){
	redirectUrl = "https://www-stage.cisco.com/autho/logout.html?ReturnUrl=http://www-stage.cisco.com/web/fw/lo/logout.html?locale=en_US&redirectTo=https://ucsada-dev.cloudapps.cisco.com";
}else if(currentUrl.indexOf("ucsada-stg") > -1){
	redirectUrl = "https://www-stage.cisco.com/autho/logout.html?ReturnUrl=http://www-stage.cisco.com/web/fw/lo/logout.html?locale=en_US&redirectTo=https://ucsada-stg.cloudapps.cisco.com";
}else if(currentUrl.indexOf("ucsada.cloudapps") > -1){
	redirectUrl = "https://www.cisco.com/autho/logout.html?ReturnUrl=http://www.cisco.com/web/fw/lo/logout.html?locale=en_US&redirectTo=https://ucsada.cloudapps.cisco.com";
}
var logoutMin = 30;
var setTimeoutObj = null;
setTimeoutObj = setTimeout(function(){window.location.href=redirectUrl;},logoutMin*60*1000);

function dealWithError(error, ioargs) {		
	switch (ioargs.xhr.status) {
	case 403:
		sendRedirectWhie403();
		break;
	case 401:
		top.window.location.href = redirectUrl;
		break;
	case 500:
		if(standByDijitObj && standByDijitObj != undefined){
			standByDijitObj.hide();
		}
		if(standByDijitObjDown && standByDijitObjDown != undefined){
			standByDijitObjDown.hide();
		}
		console.error("Error in the server");
		displayNotificationAlert("There was some error in the Server while performing this operation.");
		break;
	case 504:
		if(standByDijitObj && standByDijitObj != undefined){
			standByDijitObj.hide();
		}
		if(standByDijitObjDown && standByDijitObjDown != undefined){
			standByDijitObjDown.hide();
		}
		console.error("Error in the server timeout");
		displayNotificationAlert("Server timeout while performing this operation.");
		break;
	default:
		if (ioargs.xhr.response == "" && ioargs.xhr.status == 0 && getIsJSessionId() == false) {
			console.log("Default : session is expired so redirecting for logout...")
			sendRedirectWhie403();
		}
		console.log(getIsJSessionId());
		console.error("No need to logout");
	}
}
function sendRedirectWhie403(){
	function handleLogout() {
		console.error("Redirecting to login page..");
		top.window.location.href = redirectUrl;
	}
	var message = "You have been logged out!!";
		var notification = new xwt.widget.notification.Alert({
			messageType : "warning",
			buttons : [ {
				label : "OK",
				baseClass : "defaultButton",
				onClick : handleLogout
			} ]
		});
		notification.setDialogContent(message);
}
function displayNotificationAlert(message, messageType) {
	if(messageType == undefined || messageType == ''){
		messageType="critical";
		}
	var notification = new xwt.widget.notification.Alert({
		messageType : messageType,
		buttons : [ {
			label : "OK",
			baseClass : "defaultButton"
		} ]
	});
	notification.setDialogContent(message);

	}
// Confirm popupbox
function displayConfirmationDialog(message,okClickFunc){
	var formDlg2 = new xwt.widget.notification.Alert({
		messageType: "warning",
		buttons: [{
			label: "OK",
			baseClass: "defaultButton",
			onClick: okClickFunc
		}, {
			label: "Cancel",
			onClick: function(){
				//alert("cancel");
			}
		}]
	});
  formDlg2.setDialogContent(message);
}

/*return changed data in datastore*/
function returnChangedDataFromDataStore(dataStoreObject,jsonObj){
	var changeSet  = dataStoreObject._pending;
    var changed = {};
    changed.addOrUpdate = [];
    changed.deleted = [];
    
    for(var i in changeSet._modifiedItems){
        var item = null;
        if(dataStoreObject._itemsByIdentity){
            item = dataStoreObject._itemsByIdentity[i];
        }else{
            item = dataStoreObject._arrayOfAllItems[i];
        }
        changed.addOrUpdate.push(itemToJS(dataStoreObject, item));
    }
    for(var i in changeSet._newItems){
        var item = null;
        if(dataStoreObject._itemsByIdentity){
            item = dataStoreObject._itemsByIdentity[i];
        }else{
            item = dataStoreObject._arrayOfAllItems[i];
        }
        changed.addOrUpdate.push(itemToJS(dataStoreObject, item));
    }
    for(var i in changeSet._deletedItems){
    	//if(changeSet._deletedItems[i]["id"][0] !=0){
    		changed.deleted.push(changeSet._deletedItems[i]["id"][0]);
    	//}
    }
  
    return jsonObj.toJson(changed, true);
}

function getDeletedPortChannelIds(dataStoreObject,jsonObj){
	console.log(dataStoreObject);
	var changeSet  = dataStoreObject._pending;
    var changed = {};
    changed.deletedPortChannelIds = [];
    for(var i in changeSet._deletedItems){
    		changed.deletedPortChannelIds.push(changeSet._deletedItems[i]["slotId"][0]+"/"+changeSet._deletedItems[i]["portId"][0]+"/"+changeSet._deletedItems[i]["equipmentUplink"][0]);
    }
  
    return jsonObj.toJson(changed, true);
}
/*Convert array to json*/
var itemToJS = function(store, item){
    // summary: Function to convert an item into a simple JS object.
    // store:
    //    The datastore the item came from.
    // item:
    //    The item in question.
    var js = {};
    if(item && store){
        // Determine the attributes we need to process.
        var attributes = store.getAttributes(item);
        if(attributes && attributes.length > 0){
            var i;
            for(i = 0; i < attributes.length; i++){
                var values = store.getValues(item, attributes[i]);
                if(values){
                    // Handle multivalued and single-valued attributes.
                    if(values.length > 1 ){
                        var j;
                        js[attributes[i]] = [];
                        for(j = 0; j < values.length; j++ ){
                            var value = values[j];
                            // Check that the value isn't another item. If it is, process it as an item.
                            if(store.isItem(value)){
                                js[attributes[i]].push(itemToJS(store, value));
                            }else{
                                js[attributes[i]].push(value);
                            }
                        }
                    }else{
                        if(store.isItem(values[0])){
                            js[attributes[i]] = itemToJS(store, values[0]);
                        }else{
                            js[attributes[i]] = values[0];
                        }
                    }
                }
            }
        }
    }
    return js;
};

/* Vishnu, Save data for every nav before going next nav*/
function saveDataForEveryNavBeforeNext(currentNavId){
	switch (currentNavId){
		
		case "infrastructure" :
			return saveInfrastructureData();
			break;
		case "admin" :
			var currentTaskTabContainer = dijit.byId("mainTabContainer_admin");
			var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
			var leftNavId = currentSelectedLeftNavTab.get("id");
			if(leftNavId == "adminTab0"){
				return saveAdminNavOnNext();
			}
			if(leftNavId == "adminTab1"){
				return saveAdminAuthenticationOnNext();
			}
			if(leftNavId == "adminTab2"){
				return saveAdminLdapGroupMapsOnNext();
			}
			if(leftNavId == "adminTab3"){
				return saveAdminCallHomeSettingOnNext();
			}
			if(leftNavId == "adminTab4"){
				return saveAdminCallHomeOnNext();
			}
			if(leftNavId == "adminTab5"){
				return saveAdminBackupConfigOnNext();
			}
			break;
		case "equipment" :
			var currentTaskTabContainer = dijit.byId("mainTabContainer_equipment");
			var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
			var leftNavId = currentSelectedLeftNavTab.get("id");
			if(leftNavId == "equipmentTab0"){
				return saveEquipmentChasisConfData();
			}
			if(leftNavId == "equipmentTab1"){
				return saveEquipmentServerConfData();
			}
			if(leftNavId == "equipmentTab2"){
				return saveEquipmentUplinkConfData();
			}
			break;
		case "lan" :
			var currentTaskTabContainer = dijit.byId("mainTabContainer_lan");
			var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
			var leftNavId = currentSelectedLeftNavTab.get("id");
			if(leftNavId == "lanTab0"){
				return saveLanPortChannelData();
			}
			if(leftNavId == "lanTab1"){
				return saveVlanMacPoolConfData();
			}
			if(leftNavId == "lanTab2"){
				return saveLanNetworkControlPolicyOnNext();
			}
			if(leftNavId == "lanTab3"){
				return saveLanQosPolicyOnNext();
			}
			if(leftNavId == "lanTab4"){  
				return saveLanEthernetAdapterPoliciesOnNext();    
			}
			if(leftNavId == "lanTab5"){
				return saveVnicTempConfData();
			}
			if(leftNavId == "lanTab6"){
				return saveLanVnicOnNext();    
			}
			if(leftNavId == "lanTab7"){
				return saveLanConnPolicyOnNext();    
			}
			if(leftNavId == "lanTab8"){
				return saveLanMgmtIPPoolConfigData();
			}
			break;
		case "san" :
			var currentTaskTabContainer = dijit.byId("mainTabContainer_san");
			var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
			var leftNavId = currentSelectedLeftNavTab.get("id");

			if(leftNavId == "sanTab0"){
				return saveVsanConfigData();
			}
			if(leftNavId == "sanTab1"){
				return saveWWxNConfigData();
			}
			if(leftNavId == "sanTab2"){
				return saveSanAdapterPoliciesOnNext();
			}
			if(leftNavId == "sanTab3"){
				return saveVHBATempConfData();
			}
			if(leftNavId == "sanTab4"){
				return saveSanvHBAOnNext();
			}
			if(leftNavId == "sanTab5"){
				return saveSanConnPolicyOnNext();
			}
			break;
		case "servers" :
			var currentTaskTabContainer = dijit.byId("mainTabContainer_servers");
			var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
			var leftNavId = currentSelectedLeftNavTab.get("id");
			if(leftNavId == "serversTab0"){
				return saveServersUUIDPoolConfigData();
			}
			if(leftNavId == "serversTab1"){
				return saveServersBootPolicyConfigData();
			}
			if(leftNavId == "serversTab2"){
				return saveServersLocalDiskPolicyConfigData();
			}
			if(leftNavId == "serversTab3"){
				return saveServersServerPoolConfigData();
			}
			if(leftNavId == "serversTab4"){
				return saveServersServerPoolQualifierAndPolicyConfig();
			}
			if(leftNavId == "serversTab5"){
				return saveserversHostFirmwareOnNext();
			}
			if(leftNavId == "serversTab6"){
				return saveserversBiosPolicyOnNext();
			}
			if(leftNavId == "serversTab7"){
				return saveServersMaintenancePolicyOnNext();
			}
			if(leftNavId == "serversTab8"){
				return saveServersServiceTempConfigData();
			}
			if(leftNavId == "serversTab9"){
				return saveServersServiceTempInstantiateConfig();
			}
			break;
		case "configure" :
			return  true;
			break;
	}
}

function ajaxCallGet(url, sync, handleAs) {
	clearTimeout(setTimeoutObj);
	setTimeoutObj = setTimeout(function(){window.location.href=redirectUrl;},logoutMin*60*1000);
	var result;
	var xhrArgs = ({
      	url : url,
		sync : sync,
		handleAs : handleAs,
		load : function(response) {
			result = response;
		},
		error : function(error, ioargs) {
			dealWithError(error, ioargs);
		},
		preventCache:true
	});
	var deferred = dojo.xhrGet(xhrArgs);
	
    if(result === undefined){
    	throw "error";
	}else{
		return result;
	}
}

function ajaxCallPost(url, sync, handleAs) {
	clearTimeout(setTimeoutObj);
	setTimeoutObj = setTimeout(function(){window.location.href=redirectUrl;},logoutMin*60*1000);
	var result;
	var xhrArgs = ({
      	 url : url,
		sync : sync,
		handleAs : handleAs,
		load : function(response) {
			result = response;
		},
		error : function(error, ioargs) {
			dealWithError(error, ioargs);
		}
	});
	var deferred = dojo.xhrPost(xhrArgs);
	
    if (result === undefined) {
    	throw "error";
	} else{
		return result;
	}
}

function ajaxCallPostWithJsonContent(url, jsonContent, functionName, handleAs) {
	clearTimeout(setTimeoutObj);
	setTimeoutObj = setTimeout(function(){window.location.href=redirectUrl;},logoutMin*60*1000);
	var type = "text";
	if (handleAs) {
		type = handleAs;
	}

	var result;
	var xhrArgs = ({
		url : url,
		sync : true,
		content : {
			jsonObj : jsonContent
		},
		handleAs : type,
		load : function(response) {
			result = response;
		},
		error : function(error, ioargs) {
			if (functionName && isFunction(functionName)) {
				functionName(ioargs.xhr.status);
			}
			dealWithError(error, ioargs);
		}
	});
	var deferred = dojo.xhrPost(xhrArgs);
	
    if (result === undefined) {
    	throw "error";
	} else{
		return result;
	}
}

function updateIdsInDataStoreAfterSave(jsonResponse, dataStoreObj, queryField ){
	if(jsonResponse != "success"){
		var queryValue ;
		 var temp = {}; 
		 for( var i = 0; i < jsonResponse.length; i++) {
			 var data = JSON.parse(jsonResponse[i]);
			 for( var propertyName in data ) {
				 if(propertyName == queryField){
					 queryValue = data[propertyName];
					 break;
				 }
			 }
			temp[queryField] = queryValue;
			 dataStoreObj.fetch({
				 query: temp,
				 onComplete: function(items, request){
					 if(items && items.length > 0){
						 var item = items[0];
						 dataStoreObj.setValue(item, "id", data.id);
						 dataStoreObj._pending = {
								 _newItems:{},
					  			_modifiedItems:{},
					  			_deletedItems:{}
					  		};
					 }	  
				 }
			});
		}
	}
}

function getDataStoreSize(datastore){
	//return datastore._arrayOfAllItems.length;
	var totalCount = 0;
	datastore.fetch(
			   {
			     onComplete:function(items,request) // items is an array
			      {
			    	 totalCount = items.length;// number of items in ask.json
			      }
			   });
	return totalCount;
}

var globalFIAmodelBitMap = [];
var globalFIBmodelBitMap = [];

function generateBitMapForModelNumber(selectedServerModel){
	//scenario
	// 1. first time save
	// 2. already have been saved and landed in this screen itself
	// 3. already have been saved but landed in equipment screen -- important and not addressed
	
	if(globalFIAmodelBitMap.length == 0 && globalFIBmodelBitMap.length == 0){
		var count = parseInt(selectedServerModel.substring(selectedServerModel.length-2,selectedServerModel.length));
		for(i= 0; i<count ; i++){
			globalFIAmodelBitMap[i] = 0;
			globalFIBmodelBitMap[i] = 0;
		}
	}
}

function getAvailablePortIds(fi, count, startingId, endingId){
	
	if(fi == 'A'){
		var globalFImodelBitMap = eval(globalFIAmodelBitMap);
	}else{
		var globalFImodelBitMap = eval(globalFIBmodelBitMap);
	}
	var availablePortIds = [];
	if(count){
		var myCount = 0;
		for(i = 0; i < globalFImodelBitMap.length ; i++){
			if(globalFImodelBitMap[i] == 0){
				availablePortIds.push(i+1);
				if(++myCount == count){
					break;
				}
			}
		}
	}
	if(startingId && endingId){
		for(i = startingId -1; i < endingId ; i++){
			if(globalFImodelBitMap[i] == 0){
				availablePortIds.push(i+1);
			}
		}
	}
	return availablePortIds;
}


function getOgranizationDropDown(){
	var	selectOrgArr = [];
	//selectOrgArr.push({value:"",	label:"Select"});
	 dojo.forEach(adminOrgDropDown.items,function(obj , i){
		 selectOrgArr.push({value:obj.id,	label:obj.name});
	// SubOrg loop	 
		 dojo.forEach(obj.children,function(child1Obj , chInd){
			 selectOrgArr.push({value:child1Obj.id,	label:obj.name+"/"+child1Obj.name});
			// SubOrg loop	 
			 dojo.forEach(child1Obj.children,function(child2Obj , chInd){
				 selectOrgArr.push({value:child2Obj.id,	label:obj.name+"/"+child1Obj.name+"/"+child2Obj.name});
				// SubOrg loop	 
   			 dojo.forEach(child2Obj.children,function(child3Obj , chInd){
   				selectOrgArr.push({value:child3Obj.id,	label:obj.name+"/"+child1Obj.name+"/"+child2Obj.name+"/"+child3Obj.name});
   				// SubOrg loop	 
	    			 dojo.forEach(child3Obj.children,function(child4Obj , chInd){
	    				 selectOrgArr.push({value:child4Obj.id,	label:obj.name+"/"+child1Obj.name+"/"+child2Obj.name+"/"+child3Obj.name+"/"+child4Obj.name});
	    				// SubOrg loop	 
		    			 dojo.forEach(child4Obj.children,function(child5Obj , chInd){
		    				 selectOrgArr.push({value:child5Obj.id,	label:obj.name+"/"+child1Obj.name+"/"+child2Obj.name+"/"+child3Obj.name+"/"+child4Obj.name+"/"+child5Obj.name});
		    		 	});
	    		 	});
   		 	});
		 	});
		 });
		
	 });
	return selectOrgArr;
}

// Update Id while create Records.
function updateZeroIdsInDataStore(jsonResponse, dataStoreObj ){
	if(jsonResponse != "success"){
		 for( var i = 0; i < jsonResponse.length; i++) {
			 var OneByOneResp = JSON.parse(jsonResponse[i]);
			 dataStoreObj.fetch({
			    	  query: { id: 0},
			    	  onComplete: function(items, request){
			    		  if(items && items.length > 0){
					    		var item = items[0];
					    		dataStoreObj.setValue(item, "id", OneByOneResp.id);
					    		dataStoreObj._pending = {
					  				_newItems:{},
					  				_modifiedItems:{},
					  				_deletedItems:{}
					  			};
					    	}	  
			    	  }
			    	});
			}
	 }
}

function callDataStoreForUpdateZeroIdOfThisRow(jsonResponse, dataStoreObj){
	if(jsonResponse != "success"){
		 for( var i = 0; i < jsonResponse.length; i++) {
			 var OneByOneResp = JSON.parse(jsonResponse[i]);
			 dataStoreObj.fetch({
		    	  query: { configureAs: OneByOneResp.configureAs, portId : OneByOneResp.portId},
		    	  onComplete: function(items, request){
		    		  if(items && items.length > 0){
				    		var item = items[0];
				    		dataStoreObj.setValue(item, "id", OneByOneResp.id);
				    		dataStoreObj.setValue(item, "configureAsOldValue", OneByOneResp.configureAs);
				    		dataStoreObj._pending = {
				  				_newItems:{},
				  				_modifiedItems:{},
				  				_deletedItems:{}
				  			};
				    	}	  
		    	  }
		    	});
		 }
	}
		 
}

function addIconClassOnLeftTab(leftNavId){
	dijit.byId(leftNavId).set("iconClass", "fi-check");
}

function addLeftTabDisabledTrueOrFalse(leftNavId,status){
	if(dijit.byId(leftNavId) != undefined){
		dijit.byId(leftNavId).set("disabled", status);
	}
}

// Flush table data function
function deleteAllDataFromTable(deleteDataStoreObj){
		deleteDataStoreObj.fetch({
			onComplete: function(items, request){
				for(var i = 0; i < items.length; i++){
					//console.log(items[i]);
					deleteDataStoreObj.deleteItem(items[i]);
		        }
				
			}
	  	});
		deleteDataStoreObj.save();
}

//Check unique field value while inserting.
function checkTableFieldValueUnique(datastoreObject,fieldName,fieldValue,isEditMode){
	if(isEditMode == undefined || isEditMode == false || isEditMode == ""){
		isEditMode = false;
	}	 
	var temp = {},returnValue; 
			 temp[fieldName] = fieldValue;
			 datastoreObject.fetch({
				 query: temp,
				 queryOptions:{ignoreCase:true,deep:true},
				 onComplete: function(items, request){
					 if(isEditMode){
						 if(items.length>1){
						 		returnValue = false;
						 	 }
						 	 else{
						 		 returnValue = true;
						 	 }
					 }else{
						 if(items.length>0){
						 		returnValue = false;
						 	 }
						 	 else{
						 		 returnValue = true;
						 	 }
					 }
					 
				 }
			});
		return returnValue;
}

// refresh contantpane
function refreshContantpaneWhileTaskChange(taskName){
	ajaxCallGet("checkIfSessionExist.html", true, "text");
	changeButtonAccordingToTaskNav(taskName);
	var currentTaskTabContainer = dijit.byId("mainTabContainer_"+taskName);
	if(currentTaskTabContainer != undefined){
		var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
		var leftNavId = currentSelectedLeftNavTab.get("id");
		if(dijit.byId(leftNavId) != undefined){
			dijit.byId(leftNavId).refresh();
		}
	}
}

function changeButtonAccordingToTaskNav(taskName){
	if(taskName == "configure"){
		dijit.byId("exitButtonForWizard").set("label","Finish");
		dojo.query(".nextButtonClass").style("display", "none");
	}
	else{
		dijit.byId("exitButtonForWizard").set("label","Save and exit");
		dojo.query(".nextButtonClass").style("display", "inline-block");
	}
	
}

//function to reset license
function reset(){
	var resetLic = new xwt.widget.notification.Alert({
		messageType: "warning",
		buttons: [{
			label: "OK",
			baseClass: "defaultButton",
			onClick: function(){
				window.location.href = 'resetLicense.html';
			}
		}, {
			label: "Cancel",
			onClick: function(){
				window.location.href = 'listProjects.html';
			}
		}]
	});
	resetLic.setDialogContent("Reset License will erase all the data from the system and cannot be recovered back, do you want to proceed?");
	
}

function removeOptions(selectboxId){
	document.getElementById(selectboxId).options.length = 0;
}

function returnRemainderAfter16Divided(portNumer){
	var remainder = portNumer%16;
	if(remainder ==0){
		remainder = 16;
	}
	return remainder;
}
function tranformToSlot(portNumer){
	// input 33 output 2/1
	var firstSlotPort = 32;
	if(globalFIAmodelBitMap.length == 96){
		firstSlotPort = 48;
	}
	var selectLabel = "";
	 if(portNumer <= firstSlotPort){
		 selectLabel = 1+"/"+ portNumer;
	 }
	 else if( portNumer > firstSlotPort && portNumer <= (firstSlotPort+16) ){
		 selectLabel = 2+"/"+ returnRemainderAfter16Divided(portNumer);
	 }
	 else if( portNumer > (firstSlotPort+16) && portNumer <= (firstSlotPort+16+16)){
		 selectLabel = 3+"/"+ returnRemainderAfter16Divided(portNumer);
	 }
	 else if( portNumer > (firstSlotPort+16+16) && portNumer <= (firstSlotPort+16+16+16)){
		 selectLabel = 4+"/"+ returnRemainderAfter16Divided(portNumer);
	 }
	 /*else if( portNumer > 80 && portNumer <= 96){
		 selectLabel = 5+"/"+ returnRemainderAfter16Divided(portNumer);
	 }*/
	 return selectLabel;
}

function transformSlotToPort(portNumerPlusSlot){
	//input 2/1 output 33 portNumerPlusSlot => 2/1
	// 16 * numberOfSlot + portNumber;
	var firstSlotPort = 32;
	if(globalFIAmodelBitMap.length == 96){
		firstSlotPort = 48;
	}
	var portSlotArr = portNumerPlusSlot.split("/");
	if(parseInt(portSlotArr[1]) <= firstSlotPort && parseInt(portSlotArr[0]) == 1){
		return parseInt(portSlotArr[1]);
	}
	else {
		if(firstSlotPort == 48){
			return (16 * parseInt(portSlotArr[0]))+ parseInt(portSlotArr[1]) + 16;
		}else {
			return (16 * parseInt(portSlotArr[0]))+ parseInt(portSlotArr[1]);
		}
	}
}

function returnPortId(combineSlotPort /*slot/port*/){
	return parseInt(combineSlotPort.split("/")[1]);
}

function returnSlotId(combineSlotPort /*slot/port*/){
	return parseInt(combineSlotPort.split("/")[0]);
}

function genrateDropdownForUplinkAndFCPorts(selectBoxId,BitMapObj){
	var select = document.getElementById(selectBoxId);
	dojo.forEach(BitMapObj,function(isUsedVal,portValIndex){
		 if(isUsedVal == 0){
			var selectlabel = tranformToSlot( portValIndex +1 );
			var opt = document.createElement('option');
		    opt.value = selectlabel;
		    opt.innerHTML = selectlabel;
		    select.appendChild(opt);
		 }
	 });
}

// Return Integer value of Name
function extractNumericValueFromAlphaNumericString(name){
	var integerVal;
	integerVal = parseInt(name.replace ( /[^\d.]/g, '' ));
	if(integerVal >= 0){
		return integerVal;
	}
	return 0;
}

//Update ToAddress after creating Record.
function updateToAddressInDataStore(jsonResponse, dataStoreObj ){
	if(jsonResponse != "success"){
		 for( var i = 0; i < jsonResponse.length; i++) {
			 var OneByOneResp = JSON.parse(jsonResponse[i]);
			 dataStoreObj.fetch({
			    	  query: { toAddress: 0},
			    	  onComplete: function(items, request){
			    		  if(items && items.length > 0){
					    		var item = items[0];
					    		dataStoreObj.setValue(item, "toAddress", OneByOneResp.toAddress);
					    		dataStoreObj._pending = {
					  				_newItems:{},
					  				_modifiedItems:{},
					  				_deletedItems:{}
					  			};
					    	}	  
			    	  }
			    	});
			}
	 }
}

// compare HexaDecimal Value
function compareHexaDecimalValue(from,to,separator){
	 var fromAddition = 0,toAddition = 0;
	 dojo.forEach(from.split(separator),function(v,i){
		 fromAddition += parseInt(v, 16);
	 });
	 dojo.forEach(to.split(separator),function(v,i){
		 toAddition += parseInt(v, 16);
	 });
	 if(fromAddition > toAddition){
		 return false;
	 }
	 if(fromAddition > toAddition){
		 return true;
	 }
}

function showTooltip(el) {
    dijit.showTooltip(el.getAttribute('tooltip'), el);
}

function hideTooltip(el) {
    dijit.hideTooltip(el);
}

function checkNullFieldValueInDataStore(datastoreObject,fieldName){
		var returnValue = true;
				 datastoreObject.fetch({
					 onComplete: function(items, request){
						 if(items.length>0){
							 for(var i = 0; i < items.length; i++){
								 if(items[i][fieldName][0] == null){
									 returnValue = false;
									 break;
								 }
							 }
						 		
						}
					 }
				});
			return returnValue;
}

function checkForDuplicateSlotMinMaxIdRows(dataStoreObj,data){
	var returnVal = true;
	serversSlotsDataStoreTab.fetch({
    	  query: {minId: data.minId,maxId:data.maxId},
    	  onComplete: function(items, request){
    		  if(items && items.length > 0){
		    		returnVal = false;
		    	}	  
    	  }
    	});
	return returnVal;
}

function updateDataStoreWithJsonObj(dataStore, item, formObj){
	var keys = Object.keys(formObj);
	dojo.forEach(keys,function(val,ind){
		dataStore.setValue(item, keys[ind], formObj[keys[ind]]);
	});
	dataStore.save();
}

function leftTabAddProperties(tabContainer){
	//adds ioArgs and onShow - refresh property
	var currentTaskTabContainer = dijit.byId(tabContainer);
	var maxNumberOfLeftNavs = currentTaskTabContainer.getChildren().length;	
	var children = currentTaskTabContainer.getChildren();
	
	for(i = 0 ; i < maxNumberOfLeftNavs; i++){
		var child = dijit.byId(children[i].id);
		 child.set("ioArgs",{error : function(error, ioargs) {dealWithError(error, ioargs);}});
		 dojo.connect(child,"onShow",function(){
			 child.refresh();
		 });	
	}
}

function getMaxNumberOfNameFromDataStore(store){
	var maxVal = 0;
	store.fetch({
  	  onComplete: function(items, request){
  		  if(items && items.length > 0){
  			for(i = 0 ; i < items.length; i++){
  				var temp = parseInt(items[i].name[0].replace ( /[^\d.]/g, '' ));
  				if(temp > maxVal){
  					maxVal = temp;
  				}
  			}
		 }	  
  	  }
  	});
	return maxVal;
}

function macAddressValidUpperCase(obj){
	obj.set("value",obj.get("value").toUpperCase());
}

function getIsJSessionId(){
	var pattern = RegExp("currentProject" + "=.[^;]*")
    matched = document.cookie.match(pattern)
    if(matched){
        var cookie = matched[0].split('=')
        return cookie[1]
    }
    return false
}
