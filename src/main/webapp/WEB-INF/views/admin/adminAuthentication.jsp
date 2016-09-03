<%-- adminAuthentication.jsp --%>
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
	
	var adminAuthenticationRealmArr = [];
	adminAuthenticationRealmArr.push({value:"Local",	label:"Local"});
	adminAuthenticationRealmArr.push({value:"Ldap",		label:"Ldap"});
	adminAuthenticationRealmArr.push({value:"Radius",	label:"Radius"});
	adminAuthenticationRealmArr.push({value:"Tacacs",	label:"Tacacs"});
	
	var adminAuthenticationDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminAuthenticationDomainDetails.html", true, "json") +"]")};
	
	//Ldap General request 
	var ldapGeneralSettings = ajaxCallGet("getLdapGeneralConfig.html", true, "json");
	//Ldap provider request 
	var adminAuthenticationProviderDataTable = {items:JSON.parse("["+ ajaxCallGet("getLdapProviderConfig.html", true, "json") +"]")};
	//Ldap provider group request 
	var adminAuthenticationGroupDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminLdapProviderGroupDetails.html", true, "json") +"]")};;
	
	//Tacacs General request 
	var tacacsGeneralSettings = ajaxCallGet("getTacacsGeneralConfig.html", true, "json");
	//Tacacs provider request 
	var adminAuthenticationTacacsProviderDataTable = {items:JSON.parse("["+ ajaxCallGet("getTacacsProviderConfig.html", true, "json") +"]")};
	//Tacacs provider group request 
	var adminAuthenticationTacacsGroupDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminTacacsProviderGroupDetails.html", true, "json") +"]")};;
	
	//Radius General request 
	var radiusGeneralSettings = ajaxCallGet("getRadiusGeneralConfig.html", true, "json");
	//Radius provider request 
	var adminAuthenticationRadiusProviderDataTable = {items:JSON.parse("["+ ajaxCallGet("getRadiusProviderConfig.html", true, "json") +"]")};
	//Radius provider group request 
	var adminAuthenticationRadiusGroupDataTable = {items:JSON.parse("["+ ajaxCallGet("getAdminRadiusProviderGroupDetails.html", true, "json") +"]")};;
	
	var ldapProviderGroupList = [], tacacsProviderGroupList = [], radiusProviderGroupList = [];
	ldapProviderGroupList.push({value:"",	label: LABEL_SELECT});
	dojo.forEach(adminAuthenticationGroupDataTable.items,function(obj , i){
		ldapProviderGroupList.push({value:obj.id,	label:obj.name});
	});
	
	tacacsProviderGroupList.push({value:"",	label: LABEL_SELECT});
	dojo.forEach(adminAuthenticationTacacsGroupDataTable.items,function(obj , i){
		tacacsProviderGroupList.push({value:obj.id,	label:obj.name});
	});
	
	
	radiusProviderGroupList.push({value:"",	label: LABEL_SELECT});
	dojo.forEach(adminAuthenticationRadiusGroupDataTable.items,function(obj , i){
		radiusProviderGroupList.push({value:obj.id,	label:obj.name});
	});
	
	var adminAuthenticationColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: "15%",vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'Realm',attr: 'realm',sorted: 'ascending',width: "15%",vAlignment: "middle",align:'center',editable: false, /*formatter: formatterGetRealmName,
                	    editWidget: {
    						widget: xwt.widget.form.DropDown,
    						options: {options: adminAuthenticationRealmArr,readOnly:true}
    		                   	  } */ 
                   },
                   {label: 'Web Session<br/>Refresh Period',attr: 'refreshPeriod',sorted: 'ascending',width: "15%",vAlignment: "middle",align:'center',editable: true,
                      	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
                      		options: {
                      			trim: true,
                      			regExp:REG_EX_NUMBER_60_TO_172800,
                      			required: true,
                      			maxlength:"16",
                      			invalidMessage: MSG_BET_60_TO_172800
          					}
            				}	
                      },
                      {label: 'Web Session Timeout',attr: 'sessionTimeout',sorted: 'ascending',width: "15%",vAlignment: "middle",align:'center',editable: true,
                         	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
                         		options: {
                         			trim: true,
                         			regExp:REG_EX_NUMBER_60_TO_172800,
                         			required: true,
                         			maxlength:"16",
                         			invalidMessage: MSG_BET_60_TO_172800
             					}
               				}	
                         },
                         {label: 'Provider Group',attr: 'providerGroup',sorted: 'ascending',width: "20%",vAlignment: "middle",align:'center',editable: true,formatter: formatterGetProviderNameUsingRealm,
                        	 editWidget: {
          						widget: xwt.widget.form.DropDown,
          						options: {options: [] , id: "providerGroupForLdapRadiusTacacsId"}
          		                   	  }
                         },
                         {label: 'Two Factor',	attr: 'twoFactor',	sorted: 'ascending',width: "12%",vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
                        	 editWidget: {
         						widget: xwt.widget.form.DropDown,
         						options: {options: [ {label: "Yes",value: "yes"}, {label: "No",value: "no"}] , id: "twoFactorAuthDroupDownId"}
         		                   	  }
                         }
                   
                   ];		

	
function formatterGetProviderNameUsingRealm(data, item, store){
	var returnStr = "";
	if(item.realm[0] == "Ldap"){
		returnStr = returnLabelForDropDown(ldapProviderGroupList, data);
	}else if(item.realm[0] == "Radius"){
		returnStr = returnLabelForDropDown(radiusProviderGroupList, data);
	}else if(item.realm[0] == "Tacacs"){
		returnStr = returnLabelForDropDown(tacacsProviderGroupList, data);
	}
 return returnStr;
}
function adminAuthenticationGenerateData(){
	var adminAuthenticationFormObj =  dojo.formToObject("adminAuthenticationTableForm");
	
	if(dijit.byId("adminAuthenticationTableForm").validate()==false || adminAuthenticationFormObj.adminAuthenticationName == "" || 
			adminAuthenticationFormObj.adminAuthenticationRefreshPeriod == "" || adminAuthenticationFormObj.adminAuthenticationSessionTimeout == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(adminAuthenticationFormObj.adminAuthenticationProviderGroup != undefined && adminAuthenticationFormObj.adminAuthenticationProviderGroup == "" && adminAuthenticationFormObj.adminAuthenticationRealm == "ldap"){
		displayNotificationAlert(MSG_FILL_PROVIDER_GROUP);
		return false;
	}
	if(!checkTableFieldValueUnique(adminAuthenticationDataStoreTab,"name",adminAuthenticationFormObj.adminAuthenticationName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	if(parseInt(adminAuthenticationFormObj.adminAuthenticationRefreshPeriod) > parseInt(adminAuthenticationFormObj.adminAuthenticationSessionTimeout)){
		displayNotificationAlert(MSG_REFRESH_PERIOD_VALID,"warning");
		return false;
	}
	var tableRowDefItem = {
				"id":0,
				"twoFactor" : dijit.byId("adminAuthenticationTwoFactor").get("checked") == true ? "yes":"no", 
				"name":	adminAuthenticationFormObj.adminAuthenticationName,
				"refreshPeriod" : adminAuthenticationFormObj.adminAuthenticationRefreshPeriod,
				"sessionTimeout" : adminAuthenticationFormObj.adminAuthenticationSessionTimeout,
				"realm" : adminAuthenticationFormObj.adminAuthenticationRealm,
				"providerGroup" : adminAuthenticationFormObj.adminAuthenticationProviderGroup
	};
	adminAuthenticationTable.store.newItem(tableRowDefItem);
	
	adminAuthenticationDataStoreTab.save();
	adminAuthenticationTable.refresh();
	adminAuthenticationTableForm.reset();
}
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){	   		 	
	   		 	// ADD fropdown val for Realm
	   		 	adminAuthenticationRealm.addOption(adminAuthenticationRealmArr);
	   		 	
	   		 	dojo.connect(adminAuthenticationProviderGroup,"onChange",function(){
	   		 		if(this.value != ""){
	   		 			authProviderGroupAddButton.set("iconClass","fi-edit");
	   		 		}else{
	   		 			authProviderGroupAddButton.set("iconClass","fi-plus");
	   		 		}
	   		 	})
	   		 	
	   		 	dojo.connect(adminAuthenticationRealm,"onChange",function(){
	   		 		authProviderGroupAddButton.set("iconClass","fi-plus");
	   		 		
	   		 		adminAuthenticationProviderGroup.set("disabled",false);
	   		 		authProviderGroupAddButton.set("disabled",false);
	   		 		adminAuthenticationTwoFactor.set("disabled",true);
	   		 	
	   	   		 	adminAuthenticationProviderGroup.removeOption(adminAuthenticationProviderGroup.getOptions());
	   		
	   		 		if(this.value == "Ldap"){
	   		 			adminAuthenticationProviderGroup.addOption(ldapProviderGroupList);
	   		 		}
		   		 	else if(this.value == "Radius"){
		   		 		adminAuthenticationProviderGroup.addOption(radiusProviderGroupList);
		   		 		adminAuthenticationTwoFactor.set("disabled",false);
		   		 	}
	   		 		else if(this.value == "Tacacs"){
		   				adminAuthenticationProviderGroup.addOption(tacacsProviderGroupList);
		   				adminAuthenticationTwoFactor.set("disabled",false);
	   		 		}
	   		 		else{
	   		 			adminAuthenticationProviderGroup.addOption({value:"",label:""});
		   		 		adminAuthenticationProviderGroup.set("disabled",true);
		   		 		authProviderGroupAddButton.set("disabled",true);
	   		 		}
	   		 		dijit.byId(adminAuthenticationProviderGroup).set("value","");
	   		 	});
	   		 	
	   		 	dojo.connect(adminAuthenticationTable,"onEdit",function(item){
	   		 		if(item.realm[0] == "Local" || item.realm[0] == "Ldap"){
	   		 			dijit.byId("twoFactorAuthDroupDownId").set("readOnly",true);
	   		 		}else{
	   		 			dijit.byId("twoFactorAuthDroupDownId").set("readOnly",false);
	   		 		}
	   		 		
	   		 		var providerGroupDDObj = dijit.byId("providerGroupForLdapRadiusTacacsId");
	   		 		providerGroupDDObj.set("disabled",false);
	   		 		if(item.realm[0] == "Ldap"){
	   		 			providerGroupDDObj.removeOption(providerGroupDDObj.getOptions());
	   		 			providerGroupDDObj.addOption(ldapProviderGroupList);
	   		 			providerGroupDDObj.set("value",item.providerGroup[0]);
	   		 		}else if(item.realm[0] == "Radius"){
	   		 			providerGroupDDObj.removeOption(providerGroupDDObj.getOptions());
	   		 			providerGroupDDObj.addOption(radiusProviderGroupList);
	   		 			providerGroupDDObj.set("value",item.providerGroup[0]);
	   		 		}else if(item.realm[0] == "Tacacs"){
	   		 			providerGroupDDObj.removeOption(providerGroupDDObj.getOptions());
	   		 			providerGroupDDObj.addOption(tacacsProviderGroupList);
	   		 			providerGroupDDObj.set("value",item.providerGroup[0]);
	   		 		}else{
	   		 			providerGroupDDObj.removeOption(providerGroupDDObj.getOptions());
	   		 			providerGroupDDObj.set("disabled",true);
	   		 		}
	   		 	});
		   		adminAuthenticationDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminAuthDomainTable1json = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageAdminAuthenticationDomain.html" , adminAuthDomainTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     adminAuthenticationTable.validateRow = {
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(adminAuthenticationDataStoreTab,"name",newitem.name)){
										this.errorMessage = MSG_DUPLICATE_NAME;
										return false;
									}
								}
								if(parseInt(newitem.refreshPeriod) > parseInt(newitem.sessionTimeout)){
									this.errorMessage = MSG_REFRESH_PERIOD_VALID;
									return false;
								}
								
							return true;
						}
					 };
			     
	    	 },1000);
    		dojo.fx.wipeOut({node : dojo.byId("displayLdapProvidersAndGroupsTables"),duration : 1200}).play();
    		dojo.fx.wipeOut({node : dojo.byId("displayTacacsProvidersAndGroupsTables"),duration : 1200}).play();
    		dojo.fx.wipeOut({node : dojo.byId("displayRadiusProvidersAndGroupsTables"),duration : 1200}).play();
	     });

function addEditProviderGroups(){
	var selectedRealm = adminAuthenticationRealm.get("value");
	if(selectedRealm == "Ldap"){
		addLdapProvidersAndGroups();
	}
	else if(selectedRealm == "Radius"){
		addRadiusProvidersAndGroups();
	}
	else if(selectedRealm == "Tacacs"){
		addTacacsProvidersAndGroups();
	}
}
// function for Save data to servere
function saveAdminAuthenticationOnNext(){
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":1,"activeStateSubMenuIndex":2});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	
	return true;
}
</script>
<script type="text/javascript" src="js/ldap.js"></script>
<script type="text/javascript" src="js/radius.js"></script>
<script type="text/javascript" src="js/tacacs.js"></script>

</head>
<body>
	<div id="parentDiv" class="widtInPercent tundraCssForMultiSelect">
		<div class="floatleft widtInPercent">
			<div class="floatleft" style="width: 97.5%;overflow: hidden;">
				<fieldset class="heightOfFieldset" style="width: 97.5%;;">
					<legend id="titleOfDomainCreation">Domain Configuration</legend>   
					<div id="createAuthDomainFieldsAndTableDiv" style="margin: 5px;">
					<div class="commonclassForFormFields widtInPercent">
						<form id="adminAuthenticationTableForm"
							data-dojo-id="adminAuthenticationTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="adminAuthenticationName"
											name="adminAuthenticationName" style="width: 125px;"
											data-dojo-id="adminAuthenticationName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Refresh Period (sec):<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="adminAuthenticationRefreshPeriod"
											name="adminAuthenticationRefreshPeriod" style="width: 125px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox" value="60"
											data-dojo-props='pattern:REG_EX_NUMBER_60_TO_172800, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_BET_60_TO_172800'></div>
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Session Timeout (sec):<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="adminAuthenticationSessionTimeout"
											name="adminAuthenticationSessionTimeout"
											style="width: 125px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox" value="7200"
											data-dojo-props='pattern:REG_EX_NUMBER_60_TO_172800, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_BET_60_TO_172800'></div>
									</td>
								</tr>
								<tr>
									<td height="40">
										<div class="labelspace">
											<label style="float: left;">Realm:</label>
										</div>
									</td>
									<td><select id="adminAuthenticationRealm"
										name="adminAuthenticationRealm"
										data-dojo-id="adminAuthenticationRealm"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 145px; border: 1px solid #b4b4b4;"></select></td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Provider Group:</label>
										</div>
									</td>
									<td>
										<select id="adminAuthenticationProviderGroup"
											name="adminAuthenticationProviderGroup"
											data-dojo-id="adminAuthenticationProviderGroup"
											data-dojo-type="xwt.widget.form.DropDown" maxHeight="120"
											data-dojo-props="disabled:true"
											style="width: 145px; border: 1px solid #b4b4b4;"></select>
										
										<div dojoType="dijit.form.Button" id="authProviderGroupAddButton" data-dojo-id="authProviderGroupAddButton" iconClass="fi-plus"
											title="Add/Edit Provider Groups" showLabel="false" data-dojo-props=' disabled:"true" '
											onclick="addEditProviderGroups();"></div>
											
										
										
										</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Two Factor
												Authentication:</label>
										</div>
									</td>
									<td><input id="adminAuthenticationTwoFactor"
										data-dojo-id="adminAuthenticationTwoFactor"
										name="adminAuthenticationTwoFactor"
										data-dojo-props="disabled:true" dojoType="dijit.form.CheckBox"
										checked="false" /></td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="adminAuthenticationGenerateDataBtn"
											onClick="adminAuthenticationGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div class="floatleft addClassForColumnHeight widtInPercent" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 100%; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="adminAuthenticationDataStoreTab"
								data="adminAuthenticationDataTable"></span>
							<div style="width: 100% !important;"
								id="adminAuthenticationTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="adminAuthenticationTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="adminAuthenticationTable"
								data-dojo-id="adminAuthenticationTable"
								dojoType="xwt.widget.table.Table"
								store="adminAuthenticationDataStoreTab"
								structure="adminAuthenticationColumns"
								style="width: 100%; height: 200px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					</div>
					
					<!-- Provider table and group table section start here -->
						<jsp:include page="ldap.jsp" />
						<jsp:include page="radius.jsp" />
						<jsp:include page="tacacs.jsp" />
					
					<!-- End here -->
					
					
				</fieldset>
			</div>
		</div>

	</div>
</body>
</html>
