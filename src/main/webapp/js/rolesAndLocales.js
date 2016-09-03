var selectLdapLocalesOrganizationArr = getOgranizationDropDown();

var adminLdapRolesColumns = [
			{label: 'dbID',	attr: 'id',	hidden:true	},
			{label: 'Name',attr: 'name',sorted: 'ascending', width: 180,vAlignment: "middle",align:'center',editable: true,
				editWidget: {widget:xwt.widget.notification.ValidationTextBox,
					options: {
						id:"adminLdapRoleNames",
						trim: true,
						regExp:REG_EX_NAME,
						required: true,
						maxlength:"16",
						invalidMessage: MSG_NAME
					}
					}	
			},
			{label: 'Privileges',attr: 'privileges',sorted: 'ascending',width: 240,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLdapPrivilegesName,
        	   editWidget: {
					widget: dojox.form.CheckedMultiSelect,
					options: {options: [] ,id:"multiSelectIdforPrivileges",dropDown:true,multiple:true}
	                   	  }
           }];

var adminLdapLocalesColumns = [
                 			{label: 'dbID',	attr: 'id',	hidden:true	},
                 			{label: 'Name',attr: 'name',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,
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
                 			{label: 'Organizations',attr: 'organizations',sorted: 'ascending',width: 260,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLdapOrganizationName,
                         	   editWidget: {
                 					widget: dojox.form.CheckedMultiSelect,
                 					options: {options: [] ,id:"multiSelectIdforOrganizations",dropDown:true,multiple:true}
                 	                   	  }
                            }];


function formatterGetLdapPrivilegesName(data, item, store){
	var returnPrivilegesName = "";
	dojo.forEach(privilegesList,function(pList,ind){
		dojo.forEach(item.privileges,function(v,i){
			if(v == pList.value){
				returnPrivilegesName += pList.label+",";
				return false;
			}
		});
	});
	if( returnPrivilegesName != ""){
		return returnPrivilegesName.slice(0, - 1);
	}else return LABEL_SELECT;
}

function formatterGetLdapOrganizationName(data, item, store){
	var returnOrganizationName = "";
	dojo.forEach(selectLdapLocalesOrganizationArr,function(oList,ind){
		dojo.forEach(item.organizations,function(v,i){
			if(v == oList.value){
				returnOrganizationName += oList.label+",";
				return false;
			}
		});
	});
	if( returnOrganizationName != ""){
		return returnOrganizationName.slice(0, - 1);
	}else return LABEL_SELECT;
}

var ldapRoleEditedItem, ldapLocaleEditedItem;
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){
	    		// add privileges dropdown
	    		adminPrivileges.addOption(privilegesList);
	    		adminLdapLocalesOrganization.addOption(selectLdapLocalesOrganizationArr);
	   		 	
	    		dojo.connect(dijit.byId("multiSelectIdforPrivileges"),"onChange",function(newValue){
	    			if(newValue.length == 0){
	    				adminLdapRolesDataStoreTab.setValue(ldapRoleEditedItem, "privileges", null);
	    			}else{
	    				adminLdapRolesDataStoreTab.setValue(ldapRoleEditedItem, "privileges", newValue);
	    			}
	    		});
	    		dojo.connect(adminLdapRolesTable,"onEdit",function(item){
	    			ldapRoleEditedItem = item;
	    			dijit.byId("multiSelectIdforPrivileges").removeOption(dijit.byId("multiSelectIdforPrivileges").getOptions());
	    			dijit.byId("multiSelectIdforPrivileges").addOption(privilegesList);
	    			dijit.byId("multiSelectIdforPrivileges").set("value",item.privileges);
	    			console.log(item);
	    			if(item.name[0] == 'admin'){
	    				dijit.byId("adminLdapRoleNames").set("readOnly",true);
	    				dijit.byId("multiSelectIdforPrivileges").set("readOnly",true);
	    			}else{
	    				dijit.byId("adminLdapRoleNames").set("readOnly",false);
	    				dijit.byId("multiSelectIdforPrivileges").set("readOnly",false);
	    			}
	    		});
	    		
	    		adminLdapRolesDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminLdapRolesTable1json = returnChangedDataFromDataStore(this,json);
	    			console.log(adminLdapRolesTable1json);
	    			var response = ajaxCallPostWithJsonContent("manageAdminRoleDetails.html" , adminLdapRolesTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
			  // validation on edit row
			     adminLdapRolesTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(adminLdapRolesDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
			     
			     // ============================
			     dojo.connect(dijit.byId("multiSelectIdforOrganizations"),"onChange",function(newValue){
		    			if(newValue.length == 0){
		    				adminLdapLocalesDataStoreTab.setValue(ldapLocaleEditedItem, "organizations", null);
		    			}else{
		    				adminLdapLocalesDataStoreTab.setValue(ldapLocaleEditedItem, "organizations", newValue);
		    			}
		    		});
			     
			     dojo.connect(adminLdapLocalesTable,"onEdit",function(item){
			    	 	ldapLocaleEditedItem = item;
		    			dijit.byId("multiSelectIdforOrganizations").removeOption(dijit.byId("multiSelectIdforOrganizations").getOptions());
		    			dijit.byId("multiSelectIdforOrganizations").addOption(selectLdapLocalesOrganizationArr);
		    			dijit.byId("multiSelectIdforOrganizations").set("value",item.organizations);
		    		});
			     adminLdapLocalesDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var adminLdapLocalesTable1json = returnChangedDataFromDataStore(this,json);
		    			console.log(adminLdapLocalesTable1json);
		    			var response = ajaxCallPostWithJsonContent("manageAdminLocaleDetails.html" , adminLdapLocalesTable1json, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
				     };
				  // validation on edit row
				     adminLdapLocalesTable.validateRow = {
				    			errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
									if(oldvalues.name != newitem.name){
										if(!checkTableFieldValueUnique(adminLdapLocalesDataStoreTab,"name",newitem.name)){
											return false;
										}
									}
								return true;
							}
						 };
			     
	    	 },1000);
	     });


function AddEditLdapRolesLocales(){
	dojo.query("#titleOfLdapGroupMapCreation").style("display","none");
	dojo.fx.wipeOut({node : dojo.byId("createAdminLdapGroupMapTableDiv"),duration : 1000}).play();
	dojo.fx.wipeIn({node : dojo.byId("displayLdapRolesAndLocalesTables"),duration : 1000}).play();
}

function adminLdapRolesGenerateData(){
	var adminLdapRolesFormObj =  dojo.formToObject("adminLdapRolesTableForm");
	
	if(dijit.byId("adminLdapRolesTableForm").validate()==false || adminLdapRolesFormObj.adminLdapRolesName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(adminLdapRolesDataStoreTab,"name",adminLdapRolesFormObj.adminLdapRolesName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var privileges = adminPrivileges.get("value");
	
	var tableRowDefItem = {
				"id":0,
				"name":	adminLdapRolesFormObj.adminLdapRolesName,
				"privileges" : (privileges.length == 0 || privileges == "")?null:privileges
	};
	adminLdapRolesTable.store.newItem(tableRowDefItem);
	
	adminLdapRolesDataStoreTab.save();
	adminLdapRolesTable.refresh();
	adminLdapRolesTableForm.reset();
	
	adminPrivileges.removeOption();
	adminPrivileges.reset();
}

function adminLdapLocalesGenerateData(){
	var adminLdapLocalesFormObj =  dojo.formToObject("adminLdapLocalesTableForm");
	
	if(dijit.byId("adminLdapLocalesTableForm").validate()==false || adminLdapLocalesFormObj.adminLdapLocalesName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(adminLdapLocalesDataStoreTab,"name",adminLdapLocalesFormObj.adminLdapLocalesName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var ldapLocalesOrg = adminLdapLocalesOrganization.get("value");
	var tableRowDefItem = {
				"id":0,
				"name":	adminLdapLocalesFormObj.adminLdapLocalesName,
				"organizations" : (ldapLocalesOrg.length == 0 || ldapLocalesOrg == "")?null:ldapLocalesOrg
	};
	adminLdapLocalesTable.store.newItem(tableRowDefItem);
	
	adminLdapLocalesDataStoreTab.save();
	adminLdapLocalesTable.refresh();
	adminLdapLocalesTableForm.reset();
	
	adminLdapLocalesOrganization.removeOption();
	adminLdapLocalesOrganization.reset();
	
}

function adminLdapRolesLocalesSaveFunc(){
	
	// add Roles in dropdown
	ldapRolesList = [],ldapLocalesList = [];
	
	adminLdapRolesDataStoreTab.fetch({
    	  onComplete: function(items, request){
    		 dojo.forEach(items,function(val, i){
    			 ldapRolesList.push({value:val.id[0],	label:val.name[0]});
    		 }) ;	  
    	  }
	});
	adminLdapGroupMapRole.removeOption(adminLdapGroupMapRole.getOptions());
	adminLdapGroupMapRole.addOption(ldapRolesList);
	
	// add Locales in dropdown
	adminLdapLocalesDataStoreTab.fetch({
    	  onComplete: function(items, request){
    		 dojo.forEach(items,function(val, i){
    			 ldapLocalesList.push({value:val.id[0],	label:val.name[0]});
    		 }) ;	  
    	  }
	});
	adminLdapGroupMapLocale.removeOption(adminLdapGroupMapLocale.getOptions());
	adminLdapGroupMapLocale.addOption(ldapLocalesList);
	
	var adminLdapGroupMapDataTableData = {items:JSON.parse("["+ ajaxCallGet("getAdminLdapGroupMapDetails.html", true, "json") +"]")};
	adminLdapGroupMapDataStoreTab.clearOnClose = true;
	adminLdapGroupMapDataStoreTab.data = adminLdapGroupMapDataTableData;
	adminLdapGroupMapDataStoreTab.close();
	adminLdapGroupMapTable.closeEditor();
	adminLdapGroupMapTable.refresh();
	
	dojo.query("#titleOfLdapGroupMapCreation").style("display","block");
	dojo.fx.wipeOut({node : dojo.byId("displayLdapRolesAndLocalesTables"),duration : 1000}).play();
	dojo.fx.wipeIn({node : dojo.byId("createAdminLdapGroupMapTableDiv"),duration : 1000}).play();
}