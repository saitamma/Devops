<%-- adminLdapGroupMaps.jsp --%>
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
	dojo.require("dijit.form.CheckBox");
	dojo.require("dojox.form.CheckedMultiSelect");
	dojo.require("dojo.fx");
	
	var adminLdapPrivilegesList = {items:JSON.parse("["+ ajaxCallGet("fetchRolePrivileges.html", true, "json") +"]")};
	
	var adminLdapGroupMapDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminLdapGroupMapDetails.html", true, "json") +"]")};
	
	var adminLdapRolesDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminRoleDetails.html", true, "json") +"]")};
	
	var adminLdapLocalesDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminLocaleDetails.html", true, "json") +"]")};
	
	
	var ldapRolesList = [],ldapLocalesList = [],privilegesList = [],organizationList = [];

	dojo.forEach(adminLdapPrivilegesList.items,function(obj , i){
		privilegesList.push({value:obj.id,	label:obj.name});
	});
	
	dojo.forEach(adminLdapRolesDataTable.items,function(obj , i){
		ldapRolesList.push({value:obj.id,	label:obj.name});
	});
	dojo.forEach(adminLdapLocalesDataTable.items,function(obj , i){
		ldapLocalesList.push({value:obj.id,	label:obj.name});
	});
	
	var adminLdapGroupMapColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'twoFactor',	attr: 'twoFactor',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: "40%",vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'Role',attr: 'ldapRoleId',sorted: 'ascending',width: "26%",vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLdapRolesName,
                	   editWidget: {
       					widget: dojox.form.CheckedMultiSelect,
       					options: {options: [] ,id:"multiSelectIdforLdapRoles",dropDown:true,multiple:true}
       	                   	  }
                   },
                   {label: 'Locale',attr: 'ldapLocaleId',sorted: 'ascending',width: "26%",vAlignment: "middle",align:'center',editable: true,formatter: formatterGetLdapLocalesName,
                	   editWidget: {
          					widget: dojox.form.CheckedMultiSelect,
          					options: {options: [] ,id:"multiSelectIdforLdapLocales",dropDown:true,multiple:true}
          	                   	  }
                   }
                   ];		

function adminLdapGroupMapGenerateData(){
	var adminLdapGroupMapFormObj =  dojo.formToObject("adminLdapGroupMapTableForm");
	
	if(dijit.byId("adminLdapGroupMapTableForm").validate()==false || adminLdapGroupMapFormObj.adminLdapGroupMapName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(adminLdapGroupMapDataStoreTab,"name",adminLdapGroupMapFormObj.adminLdapGroupMapName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var ldapGroupmapRole = adminLdapGroupMapRole.get("value");
	var ldapGroupmapLocale = adminLdapGroupMapLocale.get("value");
	var tableRowDefItem = {
				"id":0,
				"name":	adminLdapGroupMapFormObj.adminLdapGroupMapName,
				"ldapRoleId" :  (ldapGroupmapRole.length == 0 || ldapGroupmapRole == "")?null: ldapGroupmapRole,
				"ldapLocaleId" : (ldapGroupmapLocale.length == 0 || ldapGroupmapLocale == "")?null: ldapGroupmapLocale,
	};

	adminLdapGroupMapTable.store.newItem(tableRowDefItem);
	
	adminLdapGroupMapDataStoreTab.save();
	adminLdapGroupMapTable.refresh();
	adminLdapGroupMapTableForm.reset();
	
	adminLdapGroupMapRole.removeOption();
	adminLdapGroupMapRole.reset();
	
	adminLdapGroupMapLocale.removeOption();
	adminLdapGroupMapLocale.reset();
	
}

function changeBtnIconWhileSelect(widgetObj , value){
	if(value.length != 0){
			widgetObj.set("iconClass","fi-edit");
		}else{
			widgetObj.set("iconClass","fi-plus");
		}
}
var ldapGroupMapEditedItem;
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){	   		 	
	   		 	// Add Option of role
	    		adminLdapGroupMapRole.addOption(ldapRolesList);
	   		 	// Add Option of locale
	   		 	adminLdapGroupMapLocale.addOption(ldapLocalesList);
	    		
	   		 	dojo.connect(adminLdapGroupMapRole,"onChange",function(){
	   		 		changeBtnIconWhileSelect(AddEditLdapGroupMapRoleBtn,this.value);
	   		 	});
		   		 dojo.connect(adminLdapGroupMapLocale,"onChange",function(){
		   		 		changeBtnIconWhileSelect(AddEditLdapGroupMapLocaleBtn,this.value);
		   		 });
	   		 	
		   		 dojo.connect(dijit.byId("multiSelectIdforLdapRoles"),"onChange",function(newValue){
		    			if(newValue.length == 0){
		    				adminLdapGroupMapDataStoreTab.setValue(ldapGroupMapEditedItem, "ldapRoleId", null);
		    			}else{
		    				adminLdapGroupMapDataStoreTab.setValue(ldapGroupMapEditedItem, "ldapRoleId", newValue);
		    			}
		    		});
		   		dojo.connect(dijit.byId("multiSelectIdforLdapLocales"),"onChange",function(newValue){
	    			if(newValue.length == 0){
	    				adminLdapGroupMapDataStoreTab.setValue(ldapGroupMapEditedItem, "ldapLocaleId", null);
	    			}else{
	    				adminLdapGroupMapDataStoreTab.setValue(ldapGroupMapEditedItem, "ldapLocaleId", newValue);
	    			}
	    		});
			     dojo.connect(adminLdapGroupMapTable,"onEdit",function(item){
			    	 	ldapGroupMapEditedItem = item;
		    			dijit.byId("multiSelectIdforLdapRoles").removeOption(dijit.byId("multiSelectIdforLdapRoles").getOptions());
		    			dijit.byId("multiSelectIdforLdapRoles").addOption(ldapRolesList);
		    			dijit.byId("multiSelectIdforLdapRoles").set("value",item.ldapRoleId);
		    			//---
		    			dijit.byId("multiSelectIdforLdapLocales").removeOption(dijit.byId("multiSelectIdforLdapLocales").getOptions());
		    			dijit.byId("multiSelectIdforLdapLocales").addOption(ldapLocalesList);
		    			dijit.byId("multiSelectIdforLdapLocales").set("value",item.ldapLocaleId);
		    			
		    		});
			     
	    		adminLdapGroupMapDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminLdapGroupMapTable1json = returnChangedDataFromDataStore(this,json);
	    			console.log(adminLdapGroupMapTable1json);
	    			var response = ajaxCallPostWithJsonContent("manageAdminLdapGroupMapDetails.html" , adminLdapGroupMapTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
			  // validation on edit row
			     adminLdapGroupMapTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(adminLdapGroupMapDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
			     
	    	 },1000);
	    	dojo.fx.wipeOut({node : dojo.byId("displayLdapRolesAndLocalesTables"),duration : 1000}).play();
	     });
	     
// function for Save data to Ldap Group map
function saveAdminLdapGroupMapsOnNext(){
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":1,"activeStateSubMenuIndex":3});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	
	return true;
}
</script>
<script type="text/javascript" src="js/rolesAndLocales.js"></script>
</head>
<body>
	<div id="parentDiv" class="widtInPercent tundraCssForMultiSelect">
		<div class="floatleft widtInPercent">
			<div class="floatleft" style="width: 97.5%;overflow: hidden;">
				<fieldset class="heightOfFieldset" style="width: 97.5%;;">
					<legend id="titleOfLdapGroupMapCreation">LDAP Group Map Configuration</legend>
					<div id="createAdminLdapGroupMapTableDiv" style="margin: 5px;">
					<div class="commonclassForFormFields widtInPercent">
						<form id="adminLdapGroupMapTableForm"
							data-dojo-id="adminLdapGroupMapTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="adminLdapGroupMapName"
											name="adminLdapGroupMapName" style="width: 135px;"
											data-dojo-id="adminLdapGroupMapName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">Role:</label>
										</div>
									</td>
									<td>
									<select id="adminLdapGroupMapRole"
												data-dojo-id="adminLdapGroupMapRole"
												data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"
												name="groupMapRole" ></select>
												
									<div dojoType="dijit.form.Button" id="AddEditLdapGroupMapRoleBtn" data-dojo-id="AddEditLdapGroupMapRoleBtn" iconClass="fi-plus"
											title="Add/Edit Roles" showLabel="false" data-dojo-props=' '
											onclick="AddEditLdapRolesLocales();"></div>
									</td>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">Locale:</label>
										</div>
									</td>
									<td>
									<select id="adminLdapGroupMapLocale"
												data-dojo-id="adminLdapGroupMapLocale"
												data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"
												name="groupMapLocale" ></select>
									
									<div dojoType="dijit.form.Button" id="AddEditLdapGroupMapLocaleBtn" data-dojo-id="AddEditLdapGroupMapLocaleBtn" iconClass="fi-plus"
											title="Add/Edit Locales" showLabel="false" data-dojo-props=' '
											onclick="AddEditLdapRolesLocales();"></div>
									</td>
									<td style="padding-left: 10px;width: 150px;text-align: right;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="adminLdapGroupMapGenerateDataBtn"
											onClick="adminLdapGroupMapGenerateData();" type="button">Add</button>
									</td>
								</tr>
								
							</table>
						</form>
					</div>
					
					<div class="floatleft widtInPercent" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 100%; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="adminLdapGroupMapDataStoreTab"
								data="adminLdapGroupMapDataTable"></span>
							<div style="width: 100% !important;"
								id="adminLdapGroupMapTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="adminLdapGroupMapTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="adminLdapGroupMapTable"
								data-dojo-id="adminLdapGroupMapTable"
								dojoType="xwt.widget.table.Table"
								store="adminLdapGroupMapDataStoreTab"
								structure="adminLdapGroupMapColumns"
								style="width: 100%; height: 230px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					</div>
		
					<!-- Roles And Locales section start here -->
						<jsp:include page="rolesAndLocales.jsp"></jsp:include>
					<!-- End here -->
					
					
				</fieldset>
			</div>
		</div>

	</div>
</body>
</html>
