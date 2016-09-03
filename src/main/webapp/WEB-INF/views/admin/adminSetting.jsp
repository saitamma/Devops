<%-- adminSetting.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/claroGrid.css" />
<script type="text/javascript">
			dojo.require("xwt.widget.form.DropDown");
			dojo.require("xwt.widget.form.TextButton");
			dojo.require("xwt.widget.form.UnifiedIPAddress");
			
		  	dojo.require("dojo.data.ItemFileWriteStore");
		  	dojo.require("xwt.widget.table.Table");
		  	dojo.require("xwt.widget.notification.Form");
		    dojo.require("dijit.form.TextBox");
		    dojo.require("xwt.widget.layout.Dialog");
		    dojo.require("xwt.widget.table.Toolbar");
		    
		    dojo.require("dojox.grid.LazyTreeGrid");
            dojo.require("dijit.tree.ForestStoreModel");
			dojo.require("dojox.grid.LazyTreeGridStoreModel");
			dojo.require("dijit.form.Button");
		    
			var dataForDNS = { items:JSON.parse("[" + ajaxCallGet("fetchDnsData.html", true, "json") + "]") };
			var dataForNTP = { items:JSON.parse("[" + ajaxCallGet("fetchNtpData.html", true, "json") + "]") };
			var dataForTimezone = ajaxCallGet("fetchTimezoneData.html", true, "text");
			
			var selectTimeZoneArr=[];
			var listOfTimeZones = { timeZone:JSON.parse("[" + ajaxCallGet("fetchListOfTimeZones.html", true, "json") + "]") };
			selectTimeZoneArr.push( {value:null, label:LABEL_SELECT} );
			dojo.forEach(listOfTimeZones.timeZone,function(timeZoneListObj , i){
				var toolTip = (timeZoneListObj.comments == "" || timeZoneListObj.comments == null)?timeZoneListObj.timeZone : timeZoneListObj.comments;
				selectTimeZoneArr.push({value:timeZoneListObj.id,	label: "<div tooltip='"+ toolTip +"' onmouseover='showTooltip(this)' onmouseout='hideTooltip(this)'>"+ timeZoneListObj.timeZone +"</div>"});
			});
			
			var configureColumnsForDNS = [
			                              { label: 'ID', attr: 'id', hidden: true},
										  { label: 'DNS', attr: 'ipAddress', vAlignment: "middle", width:"50%", editable: true, align:'center',	
			                            	  		editWidget: {widget:xwt.widget.notification.ValidationTextBox,
						                            		options: {
						                            			regExp: REG_EX_IPADDRESS,
						                            			required: true,
						                            			trim: true,
						                            			maxlength:"45",
						                            			invalidMessage: MSG_IPADDRESS
						        							}
			                              			}
			                              	}
			                             ];
	
			var configureColumnsForNTP = [
			                              { label: 'ID', attr: 'id',hidden: true },
										  { label: 'NTP', attr: 'ipAddress', vAlignment: "middle",width:"50%", editable: true, align:'center',
			                            	 		 editWidget: {widget:xwt.widget.notification.ValidationTextBox,
					                            		options: {
					                            			regExp: REG_EX_NTP,
					                            			required: true,
					                            			trim: true,
					                            			maxlength:"45",
					                            			invalidMessage: MSG_INVALID
						        							}
	                              			}
			                              }
			                             ];
	
			
			var adminWriteStore = new dojo.data.ItemFileWriteStore({data:adminOrgStoreData});
			var adminTreeModel = new dojox.grid.LazyTreeGridStoreModel({store: adminWriteStore, childrenAttrs: ['children']});
			
			var adminOrgColumns = [
				{name: 'Name', field: 'name', width: 'auto'},
				{name: 'Action', field: 'depth', label: "Action",width: '120px',formatter: addButton}
			];
			
			function addButton(depth,index){
				if(depth < 3){
					return '<button class="btn btn-default" onClick="javascript:adminSubOrgAddDialog();" type="button">Add Sub-Org</button>';
				}
				else {
					return "";
				}
			}
				
			function generateDataForDNS(){
				var count = getDataStoreSize(dataStoreForDNS);
				console.log(count);
				var dnsValue = dijit.byId("dnsTextBox").get('value');
		    	if(dijit.byId("dnsTextBox").isValid()==false || dijit.byId("dnsTextBox").get("value") == ""){
		    		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		    		return false;
		    	}
				if(!checkTableFieldValueUnique(dataStoreForDNS,"ipAddress",dnsValue)){
					displayNotificationAlert(MSG_DUPLICATE_ENTRY);
					return false;
				}
				if(count < 4){
			    	var defItemx = {
			    			"id": "0",
							"ipAddress":dnsValue
						};
			    	tableForDNS.store.newItem(defItemx);
			    	dataStoreForDNS.save();
			    	tableForDNS.refresh();
			    	
			    	dijit.byId("dnsTextBox").set("value","");
				}else{
					displayNotificationAlert(MSG_DNS_ALLOWED,"critical");
					
				}
			
			}

			function generateDataForNTP(){
				var count = getDataStoreSize(dataStoreForNTP);
				var ntpValue = dijit.byId("ntpTextBox").get('value');
				if(dijit.byId("ntpTextBox").isValid()==false || dijit.byId("ntpTextBox").get("value") == ""){
					displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		    		return false;
		    	}
				if(!checkTableFieldValueUnique(dataStoreForNTP,"ipAddress",ntpValue)){
					displayNotificationAlert(MSG_DUPLICATE_ENTRY);
					return false;
				}
				if(count < 4){
					var defItemx = {
						"id": "0",
						"ipAddress":ntpValue
					};
					tableForNTP.store.newItem(defItemx);
					dataStoreForNTP.save();
					tableForNTP.refresh();
				}else{
					displayNotificationAlert(MSG_NTP_ALLOWED,"critical");
					
				}
				dijit.byId("ntpTextBox").set("value","");
			}
	
	// Add Sub Org.
	function adminSubOrgAddDialog(){
		addEditOrgForm.reset();
		orgId.set('value',0);
		orgParentId.set("value",0);
		modeOfOrg.set('value','subOrg');
		adminOrgDialog.show();
		setTimeout(function(){dijit.byId("orgName").focus();},500);
		}
	
	// Edit Parent Org OR Sub ORG.
	function adminOrgEditDialog(){
		var getSelectedRow = adminOrgTreeGrid.selection.getSelected()[0];
		addEditOrgForm.reset();
		orgId.set('value',getSelectedRow.id);
		orgParentId.set("value",getSelectedRow.parentId);
		orgName.set('value',getSelectedRow.name);
		modeOfOrg.set('value',"updateOrg");
		adminOrgDialog.show();
		setTimeout(function(){dijit.byId("orgName").focus();},500);
		}
			

		function adminAddSubOrg(orgName,orgId){
			var parentItem = adminOrgTreeGrid.selection.getSelected()[0];
			if(parentItem){
				var item = {id:orgId,parentId:parentItem.id[0], name:orgName,depth: (parseInt(parentItem.depth[0]) + 1 )};
				var returnResult = saveAddEditOrgData(item);
				if(returnResult){
					item = {id:returnResult.id,parentId:parentItem.id[0], name:orgName,depth: (parseInt(parentItem.depth[0]) + 1 )};
					adminOrgTreeGrid.store.newItem(item, {parent: parentItem, attribute: "children"});
					adminOrgTreeGrid.expand(adminOrgTreeGrid.store.getIdentity(parentItem));
				}
				else{
					displayNotificationAlert(MSG_ERROR_WHILESAVE);
				}
			}
		}
		
		function adminUpdateOrg(orgName,orgId,orgParentId){
			//save edited item
			var getSelectedRowForSave = adminOrgTreeGrid.selection.getSelected()[0];
			var item = {id:orgId,parentId:orgParentId, name:orgName};
			var returnResult = saveAddEditOrgData(item);
			if(returnResult == "success"){
				adminWriteStore.setValue(getSelectedRowForSave, "id",orgId);
				adminWriteStore.setValue(getSelectedRowForSave, "parentId",orgParentId);
				adminWriteStore.setValue(getSelectedRowForSave, "name", orgName);
			}
			else{
				displayNotificationAlert(MSG_ERROR_WHILESAVE);
			}
		}
		
	function removeSelectedOrg(){
		displayConfirmationDialog(MSG_DELETED,deleteOrg);
		return false;
	}
// While Confirm delete then Org will delete	
	function deleteOrg(){
		var selectedRow = adminOrgTreeGrid.selection.getSelected()[0];
		var item = {id:selectedRow.id[0],parentId:selectedRow.parentId[0], name:selectedRow.name[0]};
			if(saveDeleteOrgData(item)){
				adminOrgTreeGrid.removeSelectedRows();
			}
			 else{
				displayNotificationAlert(MSG_DELETE_ORG_DEPENDENCY);
			} 
	}

    require(["dojo/ready", "dojo/_base/json"], 
   		 function(ready, json){
   	 	
	    	 setTimeout(function(){
	    		    	
	    		// ADD dropDown val for TimeZone
    			adminSelectTimeZone.addOption(selectTimeZoneArr);
    			
    			if(dataForTimezone != null){
	    			adminSelectTimeZone.set("value", dataForTimezone);
    			}			

	    		//Desc:Add Org or Sub Org
	    		var orgSaveBtn = adminOrgDialog.buttonGroup.getItemAt(0);
				var orgCancelBtn = adminOrgDialog.buttonGroup.getItemAt(1);
				
				dojo.connect(orgSaveBtn, "onClick", function(){
					
					if(dijit.byId("addEditOrgForm").validate() == false || dijit.byId("orgName").get('value') == ""){
						return false;
					}
					var orgName = dijit.byId("orgName").get('value');
					var orgId = dijit.byId("orgId").get('value');
					
					if(dijit.byId("modeOfOrg").get('value') == 'subOrg'){
						//Unique validation on name while adding
						if(!checkTableFieldValueUnique(adminWriteStore,"name",orgName)){
							displayNotificationAlert(MSG_DUPLICATE_SUBORG);
							return false;
						}
						adminOrgDialog.hide();
						adminAddSubOrg(orgName,orgId);
					}
					if(dijit.byId("modeOfOrg").get('value') == 'updateOrg') {
						//Unique validation on name while Editing
						if(!checkTableFieldValueUnique(adminWriteStore,"name",orgName,true)){
							displayNotificationAlert(MSG_DUPLICATE_SUBORG);
							return false;
						}
						var orgParentId = dijit.byId("orgParentId").get('value');
						adminUpdateOrg(orgName,orgId,orgParentId);
					}
					
					adminOrgDialog.hide();
				});
				dojo.connect(orgCancelBtn, "onClick", function(){
				});
    			
	    		 dataStoreForDNS._saveCustom = function(saveComplete, saveFailed){
	    			 var adminSettingStoreForDNS = returnChangedDataFromDataStore(this,json);
	    			 var response = ajaxCallPostWithJsonContent("manageDnsData.html",adminSettingStoreForDNS, null, "json");
	    			 saveComplete();
	    			 
	    			 //TODO: check this
	    			 updateIdsInDataStoreAfterSave(response, this, "ipAddress" ); //{
			    };
			     
			     dataStoreForNTP._saveCustom = function(saveComplete, saveFailed){
			    	 var adminSettingStoreForNTP = returnChangedDataFromDataStore(this,json);
			    	 var response = ajaxCallPostWithJsonContent("manageNtpData.html",adminSettingStoreForNTP, null, "json");
	    			 saveComplete();
	    			 updateIdsInDataStoreAfterSave(response, this, "ipAddress" ); //{
			     };
			     
			  dojo.connect(dijit.byId("adminOrgTreeGrid"),"onSelected",function(rowIndex){
				  var getSelectedRow = adminOrgTreeGrid.selection.getSelected()[0];
					 if(getSelectedRow.name[0] == "root"){
						 dijit.byId("editOrgSubOrg").attr("disabled", true);
						 dijit.byId("deleteOrg").attr("disabled", true);
					 }
					 else{
						 dijit.byId("editOrgSubOrg").attr("disabled", false);
						 dijit.byId("deleteOrg").attr("disabled", false);
					 }
					 
			  });   
			
				// NTP inline edit validation function
				dojo.addOnLoad(function () {
					tableForDNS.validateRow = {
							errorMessage: MSG_DUPLICATE_ENTRY,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.ipAddress != newitem.ipAddress){
									if(!checkTableFieldValueUnique(dataStoreForDNS,"ipAddress",newitem.ipAddress)){
										return false;
									}
								}
							return true;
						}
					};
					tableForNTP.validateRow = {
							errorMessage: MSG_DUPLICATE_ENTRY,
							
							isValid: function (oldvalues, newitem) {
								if(oldvalues.ipAddress != newitem.ipAddress){
									if(!checkTableFieldValueUnique(dataStoreForNTP,"ipAddress",newitem.ipAddress)){
										return false;
									}
								}
								return true;
							}
						};
					
				});
				
			     
	    	 },1000);
   	 
	     });
	
    function saveAddEditOrgData(jsonObjToSave){
		/*@author: Vishnu
		 *@desc : This function will save data while add/edit opration perform.*/
		 var addSqur = "["+JSON.stringify(jsonObjToSave)+"]";
		 var updateK = "addOrUpdate";
		 var deleK = "deleted";
		 var jsonObject = { };
		 jsonObject[updateK] = JSON.parse(addSqur);
		 jsonObject[deleK] = JSON.parse("[]");
		 var jsonStringToSave = JSON.stringify(jsonObject);
		 var response = ajaxCallPostWithJsonContent("updateOrganization.html" , jsonStringToSave, null, "json");
		 try{
			 var orgJsonParse = JSON.parse(response);
		 }catch(e){
			 orgJsonParse = "success"; // while update org that time success return so we can not parse that.
		 }
		 adminOrgDropDown = {items:JSON.parse("[" + ajaxCallGet("getOrganizations.html", true, "json") + "]")};
		 return orgJsonParse;
		// return true;
	 }
	
	function saveDeleteOrgData(jsonToDelete){
		/*@author: Vishnu
		 *@desc : This function will delete opration perform.*/
		 var addSqur = "["+JSON.stringify(jsonToDelete)+"]";
		 var updateK = "addOrUpdate";
		 var deleK = "deleted";
		 var jsonObject = { };
		 jsonObject[updateK] = JSON.parse("[]");
		 jsonObject[deleK] = JSON.parse(addSqur);
		 var jsonStringTodeleted = JSON.stringify(jsonObject);
		 
		 var response = ajaxCallPostWithJsonContent("updateOrganization.html" , jsonStringTodeleted, null, "json");
		 
		 if(response == "failure") {
			 //displayNotificationAlert("This Organization is used for other stages, kindly unlinked before deleting.");
			 return false;
		 } else {
			 adminOrgDropDown = {items:JSON.parse("[" + ajaxCallGet("getOrganizations.html", true, "json") + "]")};
		 }
		 return true;
	 }
    
    function saveAdminNavOnNext(){
		/*@author: Vishnu
		 *@desc : This function will save data while next button clicked.
		*/
		var adminTimeZone = dijit.byId("adminSelectTimeZone").get("value");
		var jsonOfTimeZoneTobeSaved = JSON.stringify({"timeZone":adminTimeZone});
		var response = ajaxCallPostWithJsonContent("manageTimeZone.html",jsonOfTimeZoneTobeSaved, null, "text");
		if(response != "success"){
			console.error("Error while saving timezone information.");
			return false;
		}
		
		var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":1,"activeStateSubMenuIndex":1});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		
		return true;
	}
    
	</script>
</head>
<body>
	<div id="parentDiv" class="widtInPercent">
		<div class="floatleft addCssIntreeTable removeBorderFromTH sameAsPrime" style="width: 40%;">
			<fieldset class="heightOfFieldset" style="width: 95%;">
				<legend>Configure Organizations</legend>
				<div id="adminOrgGrod" dojoType="xwt.widget.table.ContextualToolbar"
					tableId="adminOrgTreeGrid" quickFilter="false">
					<div dojoType="xwt.widget.table.ContextualButtonGroup"
						showButtons="none"></div>
					<div id="editOrgSubOrg" dojoType="dijit.form.Button"
						title="Edit Org" iconClass="fi-edit" showLabel="false"
						disabled="true" onclick="return adminOrgEditDialog();"></div>
					<div id="deleteOrg" dojoType="dijit.form.Button" title="Delete Org"
						iconClass="fi-delete" showLabel="false" disabled="true"
						onclick="return removeSelectedOrg();"></div>
				</div>
				<div id='adminOrgTreeGrid' data-dojo-id='adminOrgTreeGrid'
					dojoType='dojox.grid.LazyTreeGrid' structure='adminOrgColumns'
					treeModel='adminTreeModel' keepSelection='true' rowSelector='true'
					style="height: 250px;"></div>

			</fieldset>
		</div>
		<div class="floatleft" style="width: 60%;">
			<fieldset class="heightOfFieldset" style="width: 95%; margin-left:20px;">
				<legend>Admin Settings</legend>
				<table style="padding-left: 190px;" width="95%">
					<tr>
						<td height="30px">
							<div class="labelspace">
								<label style="float: left;">Time Zone:</label>
							</div>
						</td>
						<td height="30px" style="padding-left: 12px;"><select
							id="adminSelectTimeZone" data-dojo-id="adminSelectTimeZone"
							name="timeZone" data-dojo-type="xwt.widget.form.DropDown"
							maxHeight="350" style="width: 194px" labelType="html"
							labelAttr="label" /></td>
					</tr>
				</table>
				<div class="floatleft"
					style="width: 50%; overflow: hidden; margin-top: 20px;">
					<table>
						<tr>
							<td height="30px">
								<div class="labelspace">
									<label style="float: left;">Configure DNS:<em>*</em></label>
								</div>
							</td>
							<td height="30px" style="">
								<div id="dnsTextBox" data-dojo-id="dnsTextBox"
									style="width: 110px;"
									data-dojo-type="xwt.widget.notification.ValidationTextBox"
									data-dojo-props='trim:"true",maxlength:"45", promptMessage:"",pattern: REG_EX_IPADDRESS, invalidMessage:MSG_IPADDRESS, i18nPackageName:"xwt", i18nBundleName:"XWTProperties"'></div>
								<button data-dojo-type="dijit/form/Button"
									data-dojo-id="generateDataBtn" onClick="generateDataForDNS();"
									type="button">Add</button>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="floatleft" style="padding-top: 2px;">
									<div dojotype="dijit.layout.ContentPane" region="top"
										style="width:330px; overflow: hidden;" splitter="false">
										<span dojoType="dojo.data.ItemFileWriteStore"
											jsId="dataStoreForDNS" data=dataForDNS></span>
										<div style="width:330px;" id="dnsToolBar"
											dojoType="xwt.widget.table.ContextualToolbar"
											tableId="tableForDNS" quickFilter="false">
											<div dojoType="xwt.widget.table.ContextualButtonGroup"
												showButtons="delete"></div>
										</div>
										<div id="tableForDNS" data-dojo-id="tableForDNS"
											dojoType="xwt.widget.table.Table" store="dataStoreForDNS"
											structure="configureColumnsForDNS"
											style="width:330px; height: 160px; overflow: hidden;"
											selectMultiple="true" selectAllOption="true"
											showIndex="false" selectModel="input" filterOnServer=false></div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="floatleft"
					style="width: 49%; overflow: hidden; margin-top: 20px; padding-left: 4px;">
					<table>
						<tr>
							<td height="30px">
								<div class="labelspace">
									<label style="float: left;">Configure NTP:<em>*</em></label>
								</div>
							</td>
							<td height="30px" style="">
								<div id="ntpTextBox" data-dojo-id="ntpTextBox"
									style="width: 110px;"
									data-dojo-type="xwt.widget.notification.ValidationTextBox"
									data-dojo-props='regExp:REG_EX_NTP, trim:"true", maxlength:"45", promptMessage:"", invalidMessage:MSG_INVALID, i18nPackageName:"xwt", i18nBundleName:"XWTProperties"'></div>
								<button data-dojo-type="dijit/form/Button"
									data-dojo-id="generateDataBtn" onClick="generateDataForNTP();"
									type="button">Add</button>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="floatleft" style="padding-top: 2px;">
									<div dojotype="dijit.layout.ContentPane" region="top"
										style="width:330px;overflow: hidden;" splitter="false">
										<span dojoType="dojo.data.ItemFileWriteStore"
											jsId="dataStoreForNTP" data=dataForNTP></span>
										<div style="width:330px;" id="ntpToolbar"
											dojoType="xwt.widget.table.ContextualToolbar"
											tableId="tableForNTP" quickFilter="false">
											<div dojoType="xwt.widget.table.ContextualButtonGroup"
												showButtons="delete"></div>
										</div>
										<div id="tableForNTP" data-dojo-id="tableForNTP"
											dojoType="xwt.widget.table.Table" store="dataStoreForNTP"
											structure="configureColumnsForNTP"
											style="width:330px;height: 160px; overflow: hidden;"
											selectMultiple="true" selectAllOption="true"
											showIndex="false" selectModel="input" filterOnServer=false></div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>

			</fieldset>
		</div>
	</div>

	<div id="adminOrgDialog" data-dojo-id="adminOrgDialog"
		button1Label="Save" title="Add/Edit Organization"
		dojoType="xwt.widget.layout.Dialog" closable=true
		style="display: none;">
		<div style="width: 100%;">
			<form class="xwtNotification" id="addEditOrgForm"
				data-dojo-id="addEditOrgForm"
				dojoType="xwt.widget.notification.Form" action="" name="example"
				method="post">
				<input id="orgId" data-dojo-id="orgId" type="hidden" required="true"
					dojoType="dijit.form.TextBox"></input> <input id="orgParentId"
					data-dojo-id="orgParentId" type="hidden"
					dojoType="dijit.form.TextBox"></input> <input id="modeOfOrg"
					value="addOrg" data-dojo-id="modeOfOrg" type="hidden"
					dojoType="dijit.form.TextBox"></input>
				<div style="height: 33px;">
					<div class="labelspace">
						<label style="float: left;">Name:<em>*</em></label>
					</div>
					<div id="orgName" data-dojo-id="orgName"
						data-dojo-type="xwt.widget.notification.ValidationTextBox"
						data-dojo-props='regExp:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME, i18nPackageName:"xwt", i18nBundleName:"XWTProperties"'></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
