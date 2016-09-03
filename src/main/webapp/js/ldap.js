var adminLapProviderColumns = [
            {label: 'dbID',	attr: 'id',	hidden:true	},
			{label: 'Hostname',attr: 'hostname',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center'},
			{label: 'Order',attr: 'providerOrder',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
			{label: 'Port',attr: 'port',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
			{label: 'Bind DN',attr: 'bindDn',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
			{label: 'Base DN',attr: 'baseDn',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center'},
			
			{label: 'adminLdap General',attr: 'adminLdapGeneral',align:'center',hidden: true},
			{label: 'Enable SSL',attr: 'EnableSsl',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Filter',attr: 'filter',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Attribute',attr: 'attribute',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Password',attr: 'providerPassword',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Confirm Password',attr: 'confirmPassword',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Timeout',attr: 'timeout',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'vendor',attr: 'vendor',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Group Authorization',attr: 'groupAuthorization',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Group Recursion',attr: 'groupRecursion',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Target Attribute',attr: 'targetAttribute',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true},
			{label: 'Use Primary Group',attr: 'usePrimaryGroup',sorted: 'ascending',vAlignment: "middle",align:'center',hidden: true}];

var adminLdapProviderGroupColumns = [
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
			{label: 'Provider',attr: 'provider',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLdapProviderName,
        	   editWidget: {
					widget: dojox.form.CheckedMultiSelect,
					options: {options: [] ,id:"multiSelectIdforProviderGroup",dropDown:true,multiple:true}
	                   	  }
           }];

function formatterGetLdapProviderName(data, item, store){
	var returnLdapProviderName = "",allProviderData;
	adminLdapProviderDataStoreTab.fetch({ onComplete: function(items, request){allProviderData = items;}});
	
	dojo.forEach(allProviderData,function(providerList,ind){
		
		dojo.forEach(item.provider,function(v,i){
			if(v == providerList.id[0]){
				returnLdapProviderName += providerList.hostname+",";
				return false;
			}
		});
	});
	
	if( returnLdapProviderName != ""){
		return returnLdapProviderName.slice(0, - 1);
	}else return LABEL_SELECT;
}

var ldapProviderEditedItem = null, ldapProviderGroupEditedItem;
var isDeselectedRow = false;
require(["dojo/ready", "dojo/_base/json"], function(ready, json){
    	setTimeout(function(){
    		//set general setting values
    		adminLdapGeneralSettingTableForm.set("value",ldapGeneralSettings);
    		
    		dojo.connect(dijit.byId("multiSelectIdforProviderGroup"),"onChange",function(newValue){
    			if(newValue.length == 0){
    				adminLdapProviderGroupDataStoreTab.setValue(ldapProviderGroupEditedItem, "provider", null);
    			}else{
    				adminLdapProviderGroupDataStoreTab.setValue(ldapProviderGroupEditedItem, "provider", newValue);
    			}
    		});
    		
    		dojo.connect(adminLdapProviderGroupTable,"onEdit",function(item){
    			ldapProviderGroupEditedItem = item;
    			var ldapProviderMultiselectArr = [];
    			adminLdapProviderDataStoreTab.fetch({
    		    	  onComplete: function(items, request){
    		    		 dojo.forEach(items,function(val, i){
    		    			 ldapProviderMultiselectArr.push({value:val.id[0],	label:val.hostname[0]});
    		    		 }) ;	  
    		    	  }
    			});
    			dijit.byId("multiSelectIdforProviderGroup").removeOption(dijit.byId("multiSelectIdforProviderGroup").getOptions());
    			dijit.byId("multiSelectIdforProviderGroup").addOption(ldapProviderMultiselectArr);
    			dijit.byId("multiSelectIdforProviderGroup").set("value",item.provider);
    		});
    		// ldap provider group name validation
    		adminLdapProviderGroupTable.validateRow = {
					errorMessage: MSG_DUPLICATE_NAME,
					isValid: function (oldvalues, newitem) {
					if(oldvalues.name != newitem.name){
						if(!checkTableFieldValueUnique(adminLdapProviderGroupDataStoreTab,"name",newitem.name)){
							return false;
						}
					}
					return true;
				}
			};
    		
    		adminLdapProviderDataStoreTab._saveCustom = function(saveComplete, saveFailed){
    			var ldapProviderTablejson = returnChangedDataFromDataStore(this,json);
    			var response = ajaxCallPostWithJsonContent("manageLdapProviderConfig.html" , ldapProviderTablejson, null, "json");
    			saveComplete();
    			updateZeroIdsInDataStore(response, this);
    			
    			try {
    				if(response[0] == "success"){
        				var adminldapproviderGrp = {items:JSON.parse("["+ ajaxCallGet("getAdminLdapProviderGroupDetails.html", true, "json") +"]")};
        				adminLdapProviderGroupDataStoreTab.clearOnClose = true;
        				adminLdapProviderGroupDataStoreTab.data = adminldapproviderGrp;
        				adminLdapProviderGroupDataStoreTab.close();
        				adminLdapProviderGroupTable.closeEditor();
        				adminLdapProviderGroupTable.refresh();
        			}
				} catch (e) {
					// TODO: handle exception
				}
    			
		     };
		     
		     adminLdapProviderGroupDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var ldapProviderGroupTablejson = returnChangedDataFromDataStore(this,json);
	    			console.log(ldapProviderGroupTablejson);
	    			var response = ajaxCallPostWithJsonContent("manageAdminLdapProviderGroupDetails.html" , ldapProviderGroupTablejson, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
		     
		     dojo.connect(adminLdapProviderTable,"onDeselect",function(item){
		    	 isDeselectedRow = true;
		     });
		     dojo.connect(adminLdapProviderTable,"onClick",function(item){
				ldapProviderEditedItem = item;
				if(adminLdapProviderTable.isSelected(item) || isDeselectedRow){
					isDeselectedRow = false;	
					return false;
				}
				adminAuthProviderAddEditTableRowForm.set('value', item);
				providerEditTableRowCPassword.set("value",item.providerPassword);
				dojo.fx.wipeOut({node : dojo.byId("providerTableStartDiv"),duration : 1000}).play();
				dojo.fx.wipeIn({node : dojo.byId("providerTableRowAddEdit"),duration : 1000}).play();
			});
		     
    		
    	});
});
function addLdapProvidersAndGroups(){
	dojo.query("#titleOfDomainCreation").style("display","none");
	dojo.fx.wipeOut({node : dojo.byId("createAuthDomainFieldsAndTableDiv"),duration : 1000}).play();
	dojo.fx.wipeIn({node : dojo.byId("displayLdapProvidersAndGroupsTables"),duration : 1000}).play();
}
//Create provider form
function createLdapProvider(){
	ldapProviderEditedItem = null;
	dojo.fx.wipeOut({node : dojo.byId("providerTableStartDiv"),duration : 1000}).play();
	dojo.fx.wipeIn({node : dojo.byId("providerTableRowAddEdit"),duration : 1000}).play();
	adminAuthProviderAddEditTableRowForm.reset(); // reset form fields
	
}
//cancel provider add/edit form
function providerEditTableRowCancelBtnFun(){
	dojo.fx.wipeOut({node : dojo.byId("providerTableRowAddEdit"),duration : 1000}).play();
	dojo.fx.wipeIn({node : dojo.byId("providerTableStartDiv"),duration : 1000}).play();
}

//cancel provider add/edit form
function providerAddEditTableRowSaveData(){
		
		var formJsonObj = dojo.formToObject("adminAuthProviderAddEditTableRowForm");
		formJsonObj.providerOrder = parseInt(formJsonObj.providerOrder);
		formJsonObj.port = parseInt(formJsonObj.port);
		formJsonObj.timeout = parseInt(formJsonObj.timeout);
		
		if(dijit.byId("adminAuthProviderAddEditTableRowForm").validate()==false ){
			return false;
		}
		if(!checkTableFieldValueUnique(adminLdapProviderDataStoreTab,"hostname",formJsonObj.hostname) && ldapProviderEditedItem == null){
			displayNotificationAlert(MSG_DUPLICATE_NAME);
			return false;
		}
		if(ldapProviderEditedItem != null && !checkTableFieldValueUnique(adminLdapProviderDataStoreTab,"hostname",formJsonObj.hostname) &&  (ldapProviderEditedItem.hostname != formJsonObj.hostname) ){
			displayNotificationAlert(MSG_DUPLICATE_NAME);
			return false;
		}
		if(!checkTableFieldValueUnique(adminLdapProviderDataStoreTab,"providerOrder",formJsonObj.providerOrder) && ldapProviderEditedItem == null){
			displayNotificationAlert(MSG_DUPLICATE_ORDER);
			return false;
		}
		if(ldapProviderEditedItem != null && !checkTableFieldValueUnique(adminLdapProviderDataStoreTab,"providerOrder",formJsonObj.providerOrder ) &&  (ldapProviderEditedItem.providerOrder[0] != formJsonObj.providerOrder ) ){
			displayNotificationAlert(MSG_DUPLICATE_ORDER);
			return false;
		}
		if( (formJsonObj.providerPassword != formJsonObj.cPassword) && formJsonObj.providerPassword != null){
			displayNotificationAlert(MSG_PASSWORD_CPASSWORD_MATCH);
			return false;
		}
		if(ldapProviderEditedItem == null){
			var addLdapRow = {
					"id":0,
					"hostname" : formJsonObj.hostname,
					"providerOrder" : formJsonObj.providerOrder,
					"bindDn" : formJsonObj.bindDn,
					"baseDn" : formJsonObj.baseDn,
					"port" : formJsonObj.port,
					"enableSsl" : formJsonObj.enableSsl,
					"filter" : formJsonObj.filter,
					"attribute" : formJsonObj.attribute,
					"providerPassword": formJsonObj.providerPassword,
					"timeout" : formJsonObj.timeout,
					"vendor" : formJsonObj.vendor,
					"groupAuthorization" : formJsonObj.groupAuthorization,
					"groupRecursion" : formJsonObj.groupRecursion,
					"targetAttribute" : formJsonObj.targetAttribute,
					"usePrimaryGroup" : formJsonObj.usePrimaryGroup
				}
			adminLdapProviderTable.store.newItem(addLdapRow);
			adminLdapProviderDataStoreTab.save();
			adminLdapProviderTable.refresh();
			
		}else{
			 updateDataStoreWithJsonObj(adminLdapProviderDataStoreTab, ldapProviderEditedItem, formJsonObj);
		}
		console.log(ldapProviderEditedItem);
		dojo.fx.wipeOut({node : dojo.byId("providerTableRowAddEdit"),duration : 1000}).play();
		dojo.fx.wipeIn({node : dojo.byId("providerTableStartDiv"),duration : 1000}).play();
		
}

function adminAuthLdapProviderAndGroupSaveFunc(){
	if(dijit.byId("adminLdapGeneralSettingTableForm").validate()==false){
   		return false;
   	}
	var generalSettingFormObj = dojo.formToObject("adminLdapGeneralSettingTableForm");
	var generalSettingId = ajaxCallPostWithJsonContent("manageLdapGeneralConfig.html" , dojo.toJson(generalSettingFormObj), null, "json");
	adminLdapGeneralSettingId.set("value",generalSettingId);
	
	// add provider group in dropdown
	ldapProviderGroupList = [];
	ldapProviderGroupList.push({value:"",	label: LABEL_SELECT});
	adminLdapProviderGroupDataStoreTab.fetch({
    	  onComplete: function(items, request){
    		 dojo.forEach(items,function(val, i){
    			 ldapProviderGroupList.push({value:val.id[0],	label:val.name[0]});
    		 }) ;	  
    	  }
	});

	adminAuthenticationProviderGroup.removeOption(adminAuthenticationProviderGroup.getOptions());
	adminAuthenticationProviderGroup.addOption(ldapProviderGroupList);
	
	var adminAuthenticationDataTableData = {items:JSON.parse("["+ ajaxCallGet("getAdminAuthenticationDomainDetails.html", true, "json") +"]")};
	adminAuthenticationDataStoreTab.clearOnClose = true;
	adminAuthenticationDataStoreTab.data = adminAuthenticationDataTableData;
	adminAuthenticationDataStoreTab.close();
	adminAuthenticationTable.closeEditor();
	adminAuthenticationTable.refresh();
	
	dojo.query("#titleOfDomainCreation").style("display","block");
	dojo.fx.wipeOut({node : dojo.byId("displayLdapProvidersAndGroupsTables"),duration : 1000}).play();
	dojo.fx.wipeIn({node : dojo.byId("createAuthDomainFieldsAndTableDiv"),duration : 1000}).play();
}

function copyProviderToGroupTable(){
	
	var selectedLdapProviderRow = adminLdapProviderTable.selected();
	if(selectedLdapProviderRow == "" || selectedLdapProviderRow == null){
		return false;
	}
	var selectedIds = [];
	dojo.forEach(selectedLdapProviderRow,function(val,ind){
		selectedIds[ind] = val.id[0];
	});
	var getHighestNumber = getMaxNumberOfNameFromDataStore(adminLdapProviderGroupDataStoreTab);
	var addLdapProviderGroup = {
			"id":0,
			"name" : "Ldap-Group"+ ( ++getHighestNumber),
			"provider" : selectedIds
	}

	adminLdapProviderGroupTable.store.newItem(addLdapProviderGroup);
	adminLdapProviderGroupDataStoreTab.save();
	adminLdapProviderGroupTable.refresh();
	
	adminLdapProviderTable.clearSelections(); // clear provider selected row.
}



