var adminRadiusProviderColumns = [
            {label: 'dbID',	attr: 'id',	hidden:true	},
   			{label: 'Hostname',attr: 'hostname',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center'},
   			{label: 'Order',attr: 'radiusOrder',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center'},
   			{label: 'Authorization Port',attr: 'authorizationPort',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center'},
   			
   			{label: 'Key',attr: 'sslKey',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
   			{label: 'Confirm Key',attr: 'confirmKey',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Timeout',attr: 'timeout',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Retries',attr: 'retries',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true}];
			
   var adminRadiusProviderGroupColumns = [
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
   			{label: 'Provider',attr: 'provider',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetRadiusProviderName,
           	   editWidget: {
   					widget: dojox.form.CheckedMultiSelect,
   					options: {options: [] ,id:"multiSelectIdforRadiusProviderGroup",dropDown:true,multiple:true}
   	                   	  }
              }];

   function formatterGetRadiusProviderName(data, item, store){
   	var returnRadiusProviderName = "",allProviderData;
   	adminRadiusProviderDataStoreTab.fetch({ onComplete: function(items, request){allProviderData = items;}});
   	
   	dojo.forEach(allProviderData,function(providerList,ind){
   		
   		dojo.forEach(item.provider,function(v,i){
   			if(v == providerList.id[0]){
   				returnRadiusProviderName += providerList.hostname+",";
   				return false;
   			}
   		});
   	});
   	
   	if( returnRadiusProviderName != ""){
   		return returnRadiusProviderName.slice(0, - 1);
   	}else return LABEL_SELECT;
   }

   var radiusProviderEditedItem = null, radiusProviderGroupEditedItem;
   var isDeselectedRow = false;
   require(["dojo/ready", "dojo/_base/json"], function(ready, json){
       	setTimeout(function(){
       		//set general setting values
       		adminRadiusGeneralSettingTableForm.set("value",radiusGeneralSettings);
       		
       		dojo.connect(dijit.byId("multiSelectIdforRadiusProviderGroup"),"onChange",function(newValue){
       			if(newValue.length == 0){
       				adminRadiusProviderGroupDataStoreTab.setValue(radiusProviderGroupEditedItem, "provider", null);
       			}else{
       				adminRadiusProviderGroupDataStoreTab.setValue(radiusProviderGroupEditedItem, "provider", newValue);
       			}
       		});
       		
       		dojo.connect(adminRadiusProviderGroupTable,"onEdit",function(item){
       			radiusProviderGroupEditedItem = item;
       			var radiusProviderMultiselectArr = [];
       			adminRadiusProviderDataStoreTab.fetch({
       		    	  onComplete: function(items, request){
       		    		 dojo.forEach(items,function(val, i){
       		    			 radiusProviderMultiselectArr.push({value:val.id[0],	label:val.hostname[0]});
       		    		 }) ;	  
       		    	  }
       			});
       			dijit.byId("multiSelectIdforRadiusProviderGroup").removeOption(dijit.byId("multiSelectIdforRadiusProviderGroup").getOptions());
       			dijit.byId("multiSelectIdforRadiusProviderGroup").addOption(radiusProviderMultiselectArr);
       			dijit.byId("multiSelectIdforRadiusProviderGroup").set("value",item.provider);
       		});
       		// radius provider group name validation
       		adminRadiusProviderGroupTable.validateRow = {
   					errorMessage: MSG_DUPLICATE_NAME,
   					isValid: function (oldvalues, newitem) {
   					if(oldvalues.name != newitem.name){
   						if(!checkTableFieldValueUnique(adminRadiusProviderGroupDataStoreTab,"name",newitem.name)){
   							return false;
   						}
   					}
   					return true;
   				}
   			};
       		
       		adminRadiusProviderDataStoreTab._saveCustom = function(saveComplete, saveFailed){
       			var radiusProviderTablejson = returnChangedDataFromDataStore(this,json);
       			var response = ajaxCallPostWithJsonContent("manageRadiusProviderConfig.html" , radiusProviderTablejson, null, "json");
       			saveComplete();
       			updateZeroIdsInDataStore(response, this);
       			
       			try {
       				if(response[0] == "success"){
           				var adminradiusproviderGrp = {items:JSON.parse("["+ ajaxCallGet("getAdminRadiusProviderGroupDetails.html", true, "json") +"]")};
           				adminRadiusProviderGroupDataStoreTab.clearOnClose = true;
           				adminRadiusProviderGroupDataStoreTab.data = adminradiusproviderGrp;
           				adminRadiusProviderGroupDataStoreTab.close();
           				adminRadiusProviderGroupTable.closeEditor();
           				adminRadiusProviderGroupTable.refresh();
           			}
   				} catch (e) {
   					// TODO: handle exception
   				}
       			
   		     };
   		     
   		     adminRadiusProviderGroupDataStoreTab._saveCustom = function(saveComplete, saveFailed){
   	    			var radiusProviderGroupTablejson = returnChangedDataFromDataStore(this,json);
   	    			console.log(radiusProviderGroupTablejson);
   	    			var response = ajaxCallPostWithJsonContent("manageAdminRadiusProviderGroupDetails.html" , radiusProviderGroupTablejson, null, "json");
   	    			saveComplete();
   	    			updateZeroIdsInDataStore(response, this);
   			     };
   		     
   		     dojo.connect(adminRadiusProviderTable,"onDeselect",function(item){
   		    	 isDeselectedRow = true;
   		     });
   		     dojo.connect(adminRadiusProviderTable,"onClick",function(item){
   				radiusProviderEditedItem = item;
   				if(adminRadiusProviderTable.isSelected(item) || isDeselectedRow){
   					isDeselectedRow = false;	
   					return false;
   				}
   				adminAuthRadiusProviderAddEditTableRowForm.set('value', item);
   				radiusProviderEditTableRowConfirmKey.set("value",item.sslKey);
   				dojo.fx.wipeOut({node : dojo.byId("radiusProviderTableStartDiv"),duration : 1000}).play();
   				dojo.fx.wipeIn({node : dojo.byId("radiusProviderTableRowAddEdit"),duration : 1000}).play();
   			});
   		     
       		
       	});
   });
   function addRadiusProvidersAndGroups(){
   	dojo.query("#titleOfDomainCreation").style("display","none");
   	dojo.fx.wipeOut({node : dojo.byId("createAuthDomainFieldsAndTableDiv"),duration : 1000}).play();
   	dojo.fx.wipeIn({node : dojo.byId("displayRadiusProvidersAndGroupsTables"),duration : 1000}).play();
   }
   //Create provider form
   function createRadiusProvider(){
   	radiusProviderEditedItem = null;
   	dojo.fx.wipeOut({node : dojo.byId("radiusProviderTableStartDiv"),duration : 1000}).play();
   	dojo.fx.wipeIn({node : dojo.byId("radiusProviderTableRowAddEdit"),duration : 1000}).play();
   	adminAuthRadiusProviderAddEditTableRowForm.reset(); // reset form fields
   	
   }
   //cancel provider add/edit form
   function radiusProviderEditTableRowCancelBtnFun(){
   	dojo.fx.wipeOut({node : dojo.byId("radiusProviderTableRowAddEdit"),duration : 1000}).play();
   	dojo.fx.wipeIn({node : dojo.byId("radiusProviderTableStartDiv"),duration : 1000}).play();
   }

   //cancel provider add/edit form
   function radiusProviderAddEditTableRowSaveData(){
   		
   		var formJsonObj = dojo.formToObject("adminAuthRadiusProviderAddEditTableRowForm");
   		formJsonObj.radiusOrder = parseInt(formJsonObj.radiusOrder);
   		formJsonObj.authorizationPort = parseInt(formJsonObj.authorizationPort);
   		formJsonObj.timeout = parseInt(formJsonObj.timeout);
   		
   		if(dijit.byId("adminAuthRadiusProviderAddEditTableRowForm").validate()==false ){
   			return false;
   		}
   		if(!checkTableFieldValueUnique(adminRadiusProviderDataStoreTab,"hostname",formJsonObj.hostname) && radiusProviderEditedItem == null){
   			displayNotificationAlert(MSG_DUPLICATE_NAME);
   			return false;
   		}
   		if(radiusProviderEditedItem != null && !checkTableFieldValueUnique(adminRadiusProviderDataStoreTab,"hostname",formJsonObj.hostname) &&  (radiusProviderEditedItem.hostname != formJsonObj.hostname) ){
   			displayNotificationAlert(MSG_DUPLICATE_NAME);
   			return false;
   		}
   		if(!checkTableFieldValueUnique(adminRadiusProviderDataStoreTab,"radiusOrder",formJsonObj.radiusOrder) && radiusProviderEditedItem == null){
   			displayNotificationAlert(MSG_DUPLICATE_ORDER);
   			return false;
   		}
   		if(radiusProviderEditedItem != null && !checkTableFieldValueUnique(adminRadiusProviderDataStoreTab,"radiusOrder",formJsonObj.radiusOrder ) &&  (radiusProviderEditedItem.radiusOrder[0] != formJsonObj.radiusOrder ) ){
   			displayNotificationAlert(MSG_DUPLICATE_ORDER);
   			return false;
   		}
   		if( (formJsonObj.sslKey != formJsonObj.confirmKey) && formJsonObj.sslKey != null){
   			displayNotificationAlert(MSG_PASSWORD_CKEY_MATCH);
   			return false;
   		}
   	
   		if(radiusProviderEditedItem == null){
   			var addRadiusRow = {
   					"id":0,
   					"hostname" : formJsonObj.hostname,
   					"radiusOrder" : formJsonObj.radiusOrder,
   					"authorizationPort" : formJsonObj.authorizationPort,
   					"sslKey": formJsonObj.sslKey,
   					"timeout" : formJsonObj.timeout,
   					"retires" : formJsonObj.retires
   				}
   			adminRadiusProviderTable.store.newItem(addRadiusRow);
   			adminRadiusProviderDataStoreTab.save();
   			adminRadiusProviderTable.refresh();
   			
   		}else{
   			 updateDataStoreWithJsonObj(adminRadiusProviderDataStoreTab, radiusProviderEditedItem, formJsonObj);
   		}
   		console.log(radiusProviderEditedItem);
   		dojo.fx.wipeOut({node : dojo.byId("radiusProviderTableRowAddEdit"),duration : 1000}).play();
   		dojo.fx.wipeIn({node : dojo.byId("radiusProviderTableStartDiv"),duration : 1000}).play();
   		
   }

   function adminAuthRadiusProviderAndGroupSaveFunc(){
	   	if(dijit.byId("adminRadiusGeneralSettingTableForm").validate()==false){
	   		return false;
	   	}
	   	var generalSettingFormObj = dojo.formToObject("adminRadiusGeneralSettingTableForm");
	   	var generalSettingId = ajaxCallPostWithJsonContent("manageRadiusGeneralConfig.html" , dojo.toJson(generalSettingFormObj), null, "json");
	   	adminRadiusGeneralSettingId.set("value",generalSettingId);
	   	
	   	// add provider group in dropdown
	   	radiusProviderGroupList = [];
	   	radiusProviderGroupList.push({value:"",	label: LABEL_SELECT});
	   	adminRadiusProviderGroupDataStoreTab.fetch({
	       	  onComplete: function(items, request){
	       		 dojo.forEach(items,function(val, i){
	       			radiusProviderGroupList.push({value:val.id[0],	label:val.name[0]});
	       		 }) ;	  
	       	  }
	   	});
	   	adminAuthenticationProviderGroup.removeOption(adminAuthenticationProviderGroup.getOptions());
	   	adminAuthenticationProviderGroup.addOption(radiusProviderGroupList);
	   	
	   	var adminAuthenticationDataTableData = {items:JSON.parse("["+ ajaxCallGet("getAdminAuthenticationDomainDetails.html", true, "json") +"]")};
		adminAuthenticationDataStoreTab.clearOnClose = true;
		adminAuthenticationDataStoreTab.data = adminAuthenticationDataTableData;
		adminAuthenticationDataStoreTab.close();
		adminAuthenticationTable.closeEditor();
		adminAuthenticationTable.refresh();
		
	   	dojo.query("#titleOfDomainCreation").style("display","block");
	   	dojo.fx.wipeOut({node : dojo.byId("displayRadiusProvidersAndGroupsTables"),duration : 1000}).play();
	   	dojo.fx.wipeIn({node : dojo.byId("createAuthDomainFieldsAndTableDiv"),duration : 1000}).play();
   }

   function copyRadiusProviderToGroupTable(){
   	
   	var selectedRadiusProviderRow = adminRadiusProviderTable.selected();
   	if(selectedRadiusProviderRow == "" || selectedRadiusProviderRow == null){
   		return false;
   	}
   	var selectedIds = [];
   	dojo.forEach(selectedRadiusProviderRow,function(val,ind){
   		selectedIds[ind] = val.id[0];
   	});
   	
   	var getHighestNumber = getMaxNumberOfNameFromDataStore(adminRadiusProviderGroupDataStoreTab);
   	
   	var addRadiusProviderGroup = {
   			"id":0,
   			"name" : "Radius-Group"+ ( ++getHighestNumber),
   			"provider" : selectedIds
   	}

   	adminRadiusProviderGroupTable.store.newItem(addRadiusProviderGroup);
   	adminRadiusProviderGroupDataStoreTab.save();
   	adminRadiusProviderGroupTable.refresh();
   	
   	adminRadiusProviderTable.clearSelections(); // clear provider selected row.
   }