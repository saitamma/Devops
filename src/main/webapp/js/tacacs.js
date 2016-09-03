var adminTacacsProviderColumns = [
               {label: 'dbID',	attr: 'id',	hidden:true	},
   			{label: 'Hostname',attr: 'hostname',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center'},
   			{label: 'Order',attr: 'providerOrder',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center'},
   			{label: 'Port',attr: 'port',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center'},
   			
   			{label: 'Timeout',attr: 'timeout',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
   			{label: 'Key',attr: 'providerKey',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
   			{label: 'Confirm Key',attr: 'confirmKey',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true}];

   var adminTacacsProviderGroupColumns = [
   			{label: 'dbID',	attr: 'id',	hidden:true	},
   			{label: 'Name',attr: 'name',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
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
   			{label: 'Provider',attr: 'provider',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetTacacsProviderName,
           	   editWidget: {
   					widget: dojox.form.CheckedMultiSelect,
   					options: {options: [] ,id:"multiSelectIdforTacacsProviderGroup",dropDown:true,multiple:true}
   	                   	  }
              }];

   function formatterGetTacacsProviderName(data, item, store){
   	var returnTacacsProviderName = "",allProviderData;
   	adminTacacsProviderDataStoreTab.fetch({ onComplete: function(items, request){allProviderData = items;}});
   	
   	dojo.forEach(allProviderData,function(providerList,ind){
   		
   		dojo.forEach(item.provider,function(v,i){
   			if(v == providerList.id[0]){
   				returnTacacsProviderName += providerList.hostname+",";
   				return false;
   			}
   		});
   	});
   	
   	if( returnTacacsProviderName != ""){
   		return returnTacacsProviderName.slice(0, - 1);
   	}else return LABEL_SELECT;
   }

   var tacacsProviderEditedItem = null, tacacsProviderGroupEditedItem;
   var isDeselectedRow = false;
   require(["dojo/ready", "dojo/_base/json"], function(ready, json){
       	setTimeout(function(){
       		//set general setting values
       		adminTacacsGeneralSettingTableForm.set("value",tacacsGeneralSettings);
       		
       		dojo.connect(dijit.byId("multiSelectIdforTacacsProviderGroup"),"onChange",function(newValue){
       			if(newValue.length == 0){
       				adminTacacsProviderGroupDataStoreTab.setValue(tacacsProviderGroupEditedItem, "provider", null);
       			}else{
       				adminTacacsProviderGroupDataStoreTab.setValue(tacacsProviderGroupEditedItem, "provider", newValue);
       			}
       		});
       		
       		dojo.connect(adminTacacsProviderGroupTable,"onEdit",function(item){
       			tacacsProviderGroupEditedItem = item;
       			var tacacsProviderMultiselectArr = [];
       			adminTacacsProviderDataStoreTab.fetch({
       		    	  onComplete: function(items, request){
       		    		 dojo.forEach(items,function(val, i){
       		    			 tacacsProviderMultiselectArr.push({value:val.id[0],	label:val.hostname[0]});
       		    		 }) ;	  
       		    	  }
       			});
       			dijit.byId("multiSelectIdforTacacsProviderGroup").removeOption(dijit.byId("multiSelectIdforTacacsProviderGroup").getOptions());
       			dijit.byId("multiSelectIdforTacacsProviderGroup").addOption(tacacsProviderMultiselectArr);
       			dijit.byId("multiSelectIdforTacacsProviderGroup").set("value",item.provider);
       		});
       		// tacacs provider group name validation
       		adminTacacsProviderGroupTable.validateRow = {
   					errorMessage: MSG_DUPLICATE_NAME,
   					isValid: function (oldvalues, newitem) {
   					if(oldvalues.name != newitem.name){
   						if(!checkTableFieldValueUnique(adminTacacsProviderGroupDataStoreTab,"name",newitem.name)){
   							return false;
   						}
   					}
   					return true;
   				}
   			};
       		
       		adminTacacsProviderDataStoreTab._saveCustom = function(saveComplete, saveFailed){
       			var tacacsProviderTablejson = returnChangedDataFromDataStore(this,json);
       			var response = ajaxCallPostWithJsonContent("manageTacacsProviderConfig.html" , tacacsProviderTablejson, null, "json");
       			saveComplete();
       			updateZeroIdsInDataStore(response, this);
       			
       			try {
       				if(response[0] == "success"){
           				var admintacacsproviderGrp = {items:JSON.parse("["+ ajaxCallGet("getAdminTacacsProviderGroupDetails.html", true, "json") +"]")};
           				adminTacacsProviderGroupDataStoreTab.clearOnClose = true;
           				adminTacacsProviderGroupDataStoreTab.data = admintacacsproviderGrp;
           				adminTacacsProviderGroupDataStoreTab.close();
           				adminTacacsProviderGroupTable.closeEditor();
           				adminTacacsProviderGroupTable.refresh();
           			}
   				} catch (e) {
   					// TODO: handle exception
   				}
       			
   		     };
   		     
   		     adminTacacsProviderGroupDataStoreTab._saveCustom = function(saveComplete, saveFailed){
   	    			var tacacsProviderGroupTablejson = returnChangedDataFromDataStore(this,json);
   	    			console.log(tacacsProviderGroupTablejson);
   	    			var response = ajaxCallPostWithJsonContent("manageAdminTacacsProviderGroupDetails.html" , tacacsProviderGroupTablejson, null, "json");
   	    			saveComplete();
   	    			updateZeroIdsInDataStore(response, this);
   			     };
   		     
   		     dojo.connect(adminTacacsProviderTable,"onDeselect",function(item){
   		    	 isDeselectedRow = true;
   		     });
   		     dojo.connect(adminTacacsProviderTable,"onClick",function(item){
   				tacacsProviderEditedItem = item;
   				if(adminTacacsProviderTable.isSelected(item) || isDeselectedRow){
   					isDeselectedRow = false;	
   					return false;
   				}
   				adminAuthTacacsProviderAddEditTableRowForm.set('value', item);
   				tacacsProviderEditTableRowConfirmKey.set("value",item.providerKey);
   				dojo.fx.wipeOut({node : dojo.byId("tacacsProviderTableStartDiv"),duration : 1000}).play();
   				dojo.fx.wipeIn({node : dojo.byId("tacacsProviderTableRowAddEdit"),duration : 1000}).play();
   			});
   		     
       		
       	});
   });
   function addTacacsProvidersAndGroups(){
   	dojo.query("#titleOfDomainCreation").style("display","none");
   	dojo.fx.wipeOut({node : dojo.byId("createAuthDomainFieldsAndTableDiv"),duration : 1000}).play();
   	dojo.fx.wipeIn({node : dojo.byId("displayTacacsProvidersAndGroupsTables"),duration : 1000}).play();
   }
   //Create provider form
   function createProvider(){
   	tacacsProviderEditedItem = null;
   	dojo.fx.wipeOut({node : dojo.byId("tacacsProviderTableStartDiv"),duration : 1000}).play();
   	dojo.fx.wipeIn({node : dojo.byId("tacacsProviderTableRowAddEdit"),duration : 1000}).play();
   	adminAuthTacacsProviderAddEditTableRowForm.reset(); // reset form fields
   	
   }
   //cancel provider add/edit form
   function tacacsProviderEditTableRowCancelBtnFun(){
   	dojo.fx.wipeOut({node : dojo.byId("tacacsProviderTableRowAddEdit"),duration : 1000}).play();
   	dojo.fx.wipeIn({node : dojo.byId("tacacsProviderTableStartDiv"),duration : 1000}).play();
   }

   //cancel provider add/edit form
   function tacacsProviderAddEditTableRowSaveData(){
   		
   		var formJsonObj = dojo.formToObject("adminAuthTacacsProviderAddEditTableRowForm");
   		formJsonObj.providerOrder = parseInt(formJsonObj.providerOrder);
   		formJsonObj.port = parseInt(formJsonObj.port);
   		formJsonObj.timeout = parseInt(formJsonObj.timeout);
   		
   		if(dijit.byId("adminAuthTacacsProviderAddEditTableRowForm").validate()==false ){
   			return false;
   		}
   		if(!checkTableFieldValueUnique(adminTacacsProviderDataStoreTab,"hostname",formJsonObj.hostname) && tacacsProviderEditedItem == null){
   			displayNotificationAlert(MSG_DUPLICATE_NAME);
   			return false;
   		}
   		if(tacacsProviderEditedItem != null && !checkTableFieldValueUnique(adminTacacsProviderDataStoreTab,"hostname",formJsonObj.hostname) &&  (tacacsProviderEditedItem.hostname != formJsonObj.hostname) ){
   			displayNotificationAlert(MSG_DUPLICATE_NAME);
   			return false;
   		}
   		if(!checkTableFieldValueUnique(adminTacacsProviderDataStoreTab,"providerOrder",formJsonObj.providerOrder) && tacacsProviderEditedItem == null){
   			displayNotificationAlert(MSG_DUPLICATE_ORDER);
   			return false;
   		}
   		if(tacacsProviderEditedItem != null && !checkTableFieldValueUnique(adminTacacsProviderDataStoreTab,"providerOrder",formJsonObj.providerOrder ) &&  (tacacsProviderEditedItem.providerOrder[0] != formJsonObj.providerOrder ) ){
   			displayNotificationAlert(MSG_DUPLICATE_ORDER);
   			return false;
   		}
   		if( (formJsonObj.providerKey != formJsonObj.confirmKey) && formJsonObj.providerKey != null){
   			displayNotificationAlert(MSG_PASSWORD_CKEY_MATCH);
   			return false;
   		}
   		if(tacacsProviderEditedItem == null){
   			var addTacacsRow = {
   					"id":0,
   					"hostname" : formJsonObj.hostname,
   					"providerOrder" : formJsonObj.providerOrder,
   					"port" : formJsonObj.port,
   					"providerKey": formJsonObj.providerKey,
   					"timeout" : formJsonObj.timeout
   				}
   			adminTacacsProviderTable.store.newItem(addTacacsRow);
   			adminTacacsProviderDataStoreTab.save();
   			adminTacacsProviderTable.refresh();
   			
   		}else{
   			 updateDataStoreWithJsonObj(adminTacacsProviderDataStoreTab, tacacsProviderEditedItem, formJsonObj);
   		}
   		console.log(tacacsProviderEditedItem);
   		dojo.fx.wipeOut({node : dojo.byId("tacacsProviderTableRowAddEdit"),duration : 1000}).play();
   		dojo.fx.wipeIn({node : dojo.byId("tacacsProviderTableStartDiv"),duration : 1000}).play();
   		
   }

   function adminAuthTacacsProviderAndGroupSaveFunc(){
	   if(dijit.byId("adminTacacsGeneralSettingTableForm").validate()==false){
	   		return false;
	   	}
	   	var generalSettingFormObj = dojo.formToObject("adminTacacsGeneralSettingTableForm");
	   	var generalSettingId = ajaxCallPostWithJsonContent("manageTacacsGeneralConfig.html" , dojo.toJson(generalSettingFormObj), null, "json");
	   	adminTacacsGeneralSettingId.set("value",generalSettingId);
	   	
	   	// add provider group in dropdown
	   	tacacsProviderGroupList = [];
	   	tacacsProviderGroupList.push({value:"",	label: LABEL_SELECT});
	   	adminTacacsProviderGroupDataStoreTab.fetch({
	       	  onComplete: function(items, request){
	       		 dojo.forEach(items,function(val, i){
	       			tacacsProviderGroupList.push({value:val.id[0],	label:val.name[0]});
	       		 }) ;	  
	       	  }
	   	});
	   	adminAuthenticationProviderGroup.removeOption(adminAuthenticationProviderGroup.getOptions());
	   	adminAuthenticationProviderGroup.addOption(tacacsProviderGroupList);
	   	
	   	var adminAuthenticationDataTableData = {items:JSON.parse("["+ ajaxCallGet("getAdminAuthenticationDomainDetails.html", true, "json") +"]")};
		adminAuthenticationDataStoreTab.clearOnClose = true;
		adminAuthenticationDataStoreTab.data = adminAuthenticationDataTableData;
		adminAuthenticationDataStoreTab.close();
		adminAuthenticationTable.closeEditor();
		adminAuthenticationTable.refresh();
		
	   	dojo.query("#titleOfDomainCreation").style("display","block");
	   	dojo.fx.wipeOut({node : dojo.byId("displayTacacsProvidersAndGroupsTables"),duration : 1000}).play();
	   	dojo.fx.wipeIn({node : dojo.byId("createAuthDomainFieldsAndTableDiv"),duration : 1000}).play();
   }

   function copyTacacsProviderToGroupTable(){
   	
   	var selectedTacacsProviderRow = adminTacacsProviderTable.selected();
   	if(selectedTacacsProviderRow == "" || selectedTacacsProviderRow == null){
   		return false;
   	}
   	var selectedIds = [];
   	dojo.forEach(selectedTacacsProviderRow,function(val,ind){
   		selectedIds[ind] = val.id[0];
   	});
   	
   	var getHighestNumber = getMaxNumberOfNameFromDataStore(adminTacacsProviderGroupDataStoreTab);
   	
   	var addTacacsProviderGroup = {
   			"id":0,
   			"name" : "Tacacs-Group"+ ( ++getHighestNumber),
   			"provider" : selectedIds
   	}

   	adminTacacsProviderGroupTable.store.newItem(addTacacsProviderGroup);
   	adminTacacsProviderGroupDataStoreTab.save();
   	adminTacacsProviderGroupTable.refresh();
   	
   	adminTacacsProviderTable.clearSelections(); // clear provider selected row.
   }



