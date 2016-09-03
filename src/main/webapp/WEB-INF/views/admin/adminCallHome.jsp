<%-- adminCallHome.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/claroGrid.css" />
<script type="text/javascript">
	dojo.require("xwt.widget.form.DropDown");
	dojo.require("xwt.widget.table.Table");
	dojo.require("xwt.widget.table.Toolbar");
	dojo.require("xwt.widget.notification.Form");
	dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileWriteStore");
	dojo.require("dijit.form.Button");
	dojo.require("dojox.form.MultiComboBox");
	dojo.require("dojox.form.CheckedMultiSelect");
			
	var alertGroupDropdownList = [], formatArr = [], levelArr = [], stateArr = [], causeArr = [];	
	var adminCallHomeRecipientData = {items:[]};
	var adminCallHomePolicyData = {items:JSON.parse("["+ ajaxCallGet("getAdminCallHomePolicyDetails.html", true, "json") +"]")};
	
	var adminProfileTableData = {items:JSON.parse("["+ ajaxCallGet("getCallHomeProfile.html", true, "json") +"]")};
	
	 var callHomeAlertGroupList = JSON.parse("["+ ajaxCallGet("getCallHomeAlertGroup.html", true, "json") +"]");
	dojo.forEach(callHomeAlertGroupList,function(obj , i){
		alertGroupDropdownList.push({value:obj.id, label:obj.name});
	});
	
	formatArr.push({value:"xml", label:"Xml"});
	formatArr.push({value:"fullTxt", label:"Full Txt"});
	formatArr.push({value:"shortTxt", label:"Short Txt"});
	
	levelArr.push({value:"debug", label:"Debug"});levelArr.push({value:"normal", label:"Normal"});
	levelArr.push({value:"notification", label:"Notification"});levelArr.push({value:"warning", label:"Warning"});
	levelArr.push({value:"minor", label:"Minor"});levelArr.push({value:"major", label:"Major"});
	levelArr.push({value:"critical", label:"Critical"});levelArr.push({value:"fatal", label:"Fatal"});
	levelArr.push({value:"disaster", label:"Disaster"});
	
	stateArr.push({value:"enabled", label:"Enabled"});
	stateArr.push({value:"disabled", label:"Disabled"});
	
	causeArr.push({value:"arp-targets-config-error", label:"arp-targets-config-error"});causeArr.push({value:"association-failed", label:"association-failed"});
	causeArr.push({value:"configuration-failure", label:"configuration-failure"});causeArr.push({value:"connectivity-problem", label:"connectivity-problem"});
	causeArr.push({value:"election-failure", label:"election-failure"});causeArr.push({value:"equipment-degraded", label:"equipment-degraded"});
	causeArr.push({value:"equipment-disabled", label:"equipment-disabled"});causeArr.push({value:"equipment-inaccessible", label:"equipment-inaccessible"});
	causeArr.push({value:"equipment-inoperable", label:"equipment-inoperable"});causeArr.push({value:"equipment-offline", label:"equipment-offline"});
	causeArr.push({value:"equipment-problem", label:"equipment-problem"});causeArr.push({value:"fru-problem", label:"fru-problem"});
	causeArr.push({value:"identity-unestablishable", label:"identity-unestablishable"});causeArr.push({value:"inventory-failed", label:"inventory-failed"});
	causeArr.push({value:"license-graceperiod-expired", label:"license-graceperiod-expired"});causeArr.push({value:"limit-reached", label:"limit-reached"});
	causeArr.push({value:"link-down", label:"link-down"});causeArr.push({value:"management-services-failure", label:"management-services-failure"});
	causeArr.push({value:"management-services-unresponsive", label:"management-services-unresponsive"});causeArr.push({value:"mgmtif-down", label:"mgmtif-down"});
	causeArr.push({value:"ndisc-targets-config-error", label:"ndisc-targets-config-error"});causeArr.push({value:"port-failed", label:"port-failed"});
	causeArr.push({value:"power-problem", label:"power-problem"});causeArr.push({value:"thermal-problem", label:"thermal-problem"});
	causeArr.push({value:"version-incompatible", label:"version-incompatible"});causeArr.push({value:"vif-ids-mismatch", label:"vif-ids-mismatch"});
	causeArr.push({value:"voltage-problem", label:"voltage-problem"});
	
	var adminProfileColumns = [
	       {label: 'dbID',	attr: 'id',	hidden:true	},
	       {label: 'Name',attr: 'name',sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: true,
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
            {label: 'description',	attr: 'description',	hidden:true	},
	        {label: 'Level',attr: 'level',sorted: 'ascending',width: 105,vAlignment: "middle",align:'center',editable: true, formatter : formatterGetLavelName,
            	  editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: levelArr,maxHeight:100}
		           }
	        },          
	        {label: 'Format',attr: 'format',width: 105,vAlignment: "middle",align:'center',editable: true, formatter : formatterGetFormatName,
	        	 editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: formatArr}  
		           }
	         },
	         {label: 'Alert Group',attr: 'alertGroupId',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetMultipleAlertGroupName,
	              editWidget: {
	                   	widget: dojox.form.CheckedMultiSelect,
	                    options: {options: alertGroupDropdownList,dropDown:true,multiple:true,id:"alertGroupIdForProfile"}
	               }
	         },
	         {label: 'Max Msg<br/>Size',attr: 'maxMsgSize',sorted: 'ascending',width: 83,vAlignment: "middle",align:'center',editable: true,
	             	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
	              	options: {
	                trim: true,
	                regExp: REG_EX_NUMBER_1_TO_5000000,
	                required: true,
	                maxlength:"7",
	                invalidMessage: MSG_BET_1_5000000
	          		}
	        	 } 
	         },
	         {label: 'Recipient',attr: 'recipient',width: 110,vAlignment: "middle",align:'center',formatter: formatterConfigureCallHomeProfile}
	       ];
	var adminCallHomeRecipientColumns = [
					{label: 'dbID',	attr: 'id',	hidden:true	},
					{label: 'profileId',	attr: 'adminCallhomeProfile',	hidden:true	},
					{label: 'Email',attr: 'email',sorted: 'ascending',width: 330,vAlignment: "middle",align:'center',editable: true,
							editWidget: {widget:xwt.widget.notification.ValidationTextBox,
					     options: {
					     trim: true,
					     regExp: REG_EX_EMAIL_ADDERESS,
					     required: true,
					     maxlength:"80",
					     invalidMessage: MSG_EMAIL_ADDERESS
					     	}
							}
					 }
				 ];
	
	var adminCallHomePolicyColumns = [
	                 					{label: 'dbID',	attr: 'id',	hidden:true	},	                 					
	                 					{label: 'State',attr: 'state',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,formatter : formatterGetStateName,
	                 		            	  editWidget: {
	                 								widget: xwt.widget.form.DropDown,
	                 								options: {options: stateArr,maxHeight:100}
	                 				           }
	                 			       	},
	                 			       	{label: 'Cause',attr: 'cause',sorted: 'ascending',width: 160,vAlignment: "middle",align:'center',editable: true,
	                 		            	  editWidget: {
	                 								widget: xwt.widget.form.DropDown,
	                 								options: {options: causeArr,maxHeight:100}
	                 				           }
	                 			        },
	                 				 ];

	function formatterGetFormatName(data, item, store){
		return returnFormatterDropDownLabel(formatArr,data, item, store);
	}
	function formatterGetLavelName(data, item, store){
		return returnFormatterDropDownLabel(levelArr,data, item, store);
	}
	function formatterGetStateName(data, item, store){
		return returnFormatterDropDownLabel(stateArr,data, item, store);
	}
	function formatterGetMultipleAlertGroupName(data, item, store){
		var returnAlertGroupName = "";
		dojo.forEach(alertGroupDropdownList,function(alertGroupVal,ind){
			dojo.forEach(item.alertGroupId,function(v,i){
				if(v == alertGroupVal.value){
					returnAlertGroupName += alertGroupVal.label+",";
					return false;
				}
			});
		});
		if( returnAlertGroupName != ""){
			return returnAlertGroupName.slice(0, - 1);
		}else return LABEL_SELECT;
	}
	
	function formatterConfigureCallHomeProfile(data,item){
		return '<button id="serversSlotsConfigureBtn'+item.id[0]+'" class="btn btn-primary" onClick="configureCallHomeProfile(event,'+item.id[0]+');" type="button">Recipient</button>';
	}
	
	function adminCallHomeProfileRecipientGenerateData(){
		var adminRecipoientsFormObj =  dojo.formToObject("adminCallHomeProfileRecipientForm");
		if(adminCallHomeProfileRecipientForm.validate()==false || adminRecipoientsFormObj.email == ""){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		var recipientsData = {
				"id" : 0,				
				"adminCallhomeProfile" : parseInt(adminRecipoientsFormObj.adminCallhomeProfile), 
				"email" : adminRecipoientsFormObj.email	
		};
		 adminCallHomeRecipientTable.store.newItem(recipientsData);
		 adminCallHomeRecipientDataStore.save();
		adminCallHomeRecipientTable.refresh();
		callHomeRecipientsEmail.set("value","");
	}
	function adminCallHomeProfileGenerateData(){
		var adminProfileFormObj =  dojo.formToObject("adminCallHomeProfileForm");
		var alertGroupVal = dijit.byId("adminCallHomeProfileAlertGroup").get('value');
		
		if(adminCallHomeProfileForm.validate()==false || adminProfileFormObj.adminCallHomeProfileName == ""){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}		
		if(!checkTableFieldValueUnique(adminProfileDataStore,"name",adminProfileFormObj.adminCallHomeProfileName)){
			displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
			return false;
		}
		
		var	decr = "Built-in "+adminProfileFormObj.adminCallHomeProfileFormat+" format for "+adminProfileFormObj.adminCallHomeProfileName+"  profile";
		
		var adminProfileData = {
				"id":0,
				"name":	adminProfileFormObj.adminCallHomeProfileName,
				"description" : decr,
				"level": adminProfileFormObj.adminCallHomeProfileLevel,					
				"format" : adminProfileFormObj.adminCallHomeProfileFormat,
				"alertGroupId":(alertGroupVal.length == 0 || alertGroupVal == "")?null:alertGroupVal,
				"maxMsgSize" : adminProfileFormObj.adminCallHomeProfileMaxMessageSize
		};
		adminProfileTable.store.newItem(adminProfileData);
		
		adminProfileDataStore.save();
		adminProfileTable.refresh();
		adminCallHomeProfileForm.reset();
		
		var adminCallHomeProfileAlertGroup = dijit.byId('adminCallHomeProfileAlertGroup');
		adminCallHomeProfileAlertGroup.removeOption();  
		adminCallHomeProfileAlertGroup.reset();				
	}
	
	function adminCallHomePolicyGenerateData() {
		var adminPolicyFormObj =  dojo.formToObject("adminCallHomePolicyForm");			
		var adminPolicyData = {
				"id" : 0,				
				"state" : adminPolicyFormObj.state, 
				"cause" : adminPolicyFormObj.cause	
		};	
		adminCallHomePolicyTable.store.newItem(adminPolicyData);
		adminCallHomePolicyDataStore.save();
		adminCallHomePolicyTable.refresh();
		adminCallHomePolicyForm.reset();
		
	}
	function configureCallHomeProfile(e, profileId){
		dojo.stopEvent(e);
		dojo.style(adminCallHomeProfileRecipientDialog.buttonGroup.getItemAt(0).get("domNode"), "display", "none");
		dijit.byId("adminCallHomeProfileId").set("value",profileId);
		callHomeRecipientsEmail.set("value","");
		var callHomeProfileRecipientsData = { items:JSON.parse("[" + ajaxCallPostWithJsonContent("getProfileRecipientsDetails.html",parseInt(profileId), null, "json")  + "]")};
		
		adminCallHomeRecipientDataStore.clearOnClose = true;
		adminCallHomeRecipientDataStore.data = callHomeProfileRecipientsData; 
		adminCallHomeRecipientDataStore.close();
		adminCallHomeRecipientTable.closeEditor();
		adminCallHomeRecipientTable.refresh();
		
		adminCallHomeProfileRecipientDialog.show();  
	}
var onEditTableRowItem;

require(["dojo/ready", "dojo/_base/json"],
	function(ready, json){
	         
    	setTimeout(function(){
    		
    		adminCallHomeProfileAlertGroup.addOption(alertGroupDropdownList);
    		adminCallHomeProfileLevel.addOption(levelArr);
    		adminCallHomeProfileFormat.addOption(formatArr);
    		
    		adminCallHomePolicyState.addOption(stateArr);
    		adminCallHomePolicyCause.addOption(causeArr);
    		
    		//save data for profile
    		adminProfileDataStore._saveCustom = function(saveComplete, saveFailed){
    			var adminCHProfileTablejson = returnChangedDataFromDataStore(this,json);
    			console.log(adminCHProfileTablejson);
    			var response = ajaxCallPostWithJsonContent("manageAdminCallHomeProfile.html" , adminCHProfileTablejson, null, "json");
    			console.log(response);
    			saveComplete();
    			updateZeroIdsInDataStore(response, this);
		     };
		     
		   //save data for profile recipients
	    		adminCallHomeRecipientDataStore._saveCustom = function(saveComplete, saveFailed){
	    			var adminRecipientsTablejson = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageCallHomeProfileRecipients.html" , adminRecipientsTablejson, null, "json");
	    			console.log(response);
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
		     // save admin call home Policy data
		     adminCallHomePolicyDataStore._saveCustom = function(saveComplete, saveFailed){
	    			var adminCHPolicyTablejson = returnChangedDataFromDataStore(this,json);
	    			var response = ajaxCallPostWithJsonContent("manageAdminCallHomePolicyDetails.html" , adminCHPolicyTablejson, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
    		// validation on edit row
		     adminProfileTable.validateRow = {
						isValid: function (oldvalues, newitem) {
							if(oldvalues.name != newitem.name){
								if(oldvalues.name == 'CiscoTAC-1' || oldvalues.name == 'full_txt' || oldvalues.name == 'short_txt' ){
									this.errorMessage = MSG_CANT_CHANGE_DEFAULT;
									return false;
					    		 }
								if(!checkTableFieldValueUnique(adminProfileDataStore,"name",newitem.name)){
									this.errorMessage = MSG_DUPLICATE_NAME;
									return false;
								}
							}
						return true;
					}
				 };
		     		    		
    		 dojo.connect(adminProfileTable,"onEdit",function(item){
    			 onEditTableRowItem = item;
    			 var multiSelAltGroup = dijit.byId('alertGroupIdForProfile');
    			 if(item.alertGroupId != undefined && item.alertGroupId[0] != null){
    				 multiSelAltGroup.set("value", item.alertGroupId);
    			 }else{
    				 multiSelAltGroup.removeOption();
    				 multiSelAltGroup.reset();
    			 }
    		 });
    		 
    		 dojo.connect(dijit.byId('alertGroupIdForProfile'),"onChange",function(newValue){
		    	 if(newValue.length == 0){
		    		 adminProfileDataStore.setValue(onEditTableRowItem, "alertGroupId", null);
    			}else{
    				adminProfileDataStore.setValue(onEditTableRowItem, "alertGroupId", newValue);
    			}
		     });
    		 
    		 dojo.connect(adminProfileTable,"onSelect",function(item, row){
	    		 if(item.name == 'CiscoTAC-1' || item.name == 'full_txt' || item.name == 'short_txt' ){
	    			 this.deselect(item);
	    		 }
	    	});
		     
    	});
    });
		    			
	//function for Save data to servere
	function saveAdminCallHomeOnNext(){				
		var saveNavState = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":1,"activeStateSubMenuIndex":5});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , saveNavState, null, "text");					
		return true;
	}  	    			
	</script>
</head>
<body>
	<div id="parentDiv" class="tundraCssForMultiSelect"> 
		<div class="floatleft addCssIntreeTable removeBorderFromTH sameAsPrime">
			<fieldset class="heightOfFieldset" style="width: 750px;">
				<legend>Call Home Profile</legend>
				<div class="commonclassForFormFieldsCallHomeSetting">
						<form id="adminCallHomeProfileForm"   
							data-dojo-id="adminCallHomeProfileForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="adminCallHomeProfileName"
											name="adminCallHomeProfileName" style="width: 148px;"
											data-dojo-id="adminCallHomeProfileName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>  
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">Level:</label>
										</div>
									</td>
									<td height="30">
										<select id="adminCallHomeProfileLevel" data-dojo-id="adminCallHomeProfileLevel"
												data-dojo-type="xwt.widget.form.DropDown"												
												name="adminCallHomeProfileLevel"
												maxHeight="100" style="width: 118px">
										</select>												
									</td>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">Format:</label>
										</div>
									</td>
									<td height="30">
										<select id="adminCallHomeProfileFormat" data-dojo-id="adminCallHomeProfileFormat"
												data-dojo-type="xwt.widget.form.DropDown"												
												name="adminCallHomeProfileFormat"
												maxHeight="100" style="width: 118px">
										</select>										
									</td>
								</tr>
								<tr>
									<td height="30">
										<div class="labelspace">
											<label style="float: left;">Alert Group:</label>
										</div>
									</td>
									<td height="30">
										<select id="adminCallHomeProfileAlertGroup" data-dojo-id="adminCallHomeProfileAlertGroup"
												data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"
												name="adminAlertGroupDrpDown">
											</select>										
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Max Msg Size:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="adminCallHomeProfileMaxMessageSize"
											name="adminCallHomeProfileMaxMessageSize" style="width: 100px;"
											data-dojo-id="adminCallHomeProfileMaxMessageSize"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern: REG_EX_NUMBER_1_TO_5000000,trim:"true",required:"true", maxlength:"7", invalidMessage:MSG_BET_1_5000000 ' value="1000000"></div>
									</td>									
									<td style="padding-left: 10px; text-align: right;" colspan="2">  
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="adminCallHomeProfileGenerateDataBtn"  
											onClick="adminCallHomeProfileGenerateData();" type="button">Add</button>
									</td>
								</tr>																		
							</table>
						</form>
					</div>
					<div class="floatleft addClassForColumnHeight" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 748px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								data-dojo-id="adminProfileDataStore" data="adminProfileTableData"></span>
							<div style="width: 748px !important;"
								id="adminProfileTableToolBar"
								dojoType="xwt.widget.table.ContextualToolbar"  
								tableId="adminProfileTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="adminProfileTable" data-dojo-id="adminProfileTable"
								dojoType="xwt.widget.table.Table" store="adminProfileDataStore"
								structure="adminProfileColumns"
								style="width: 748px; height: 225px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
					</div></div>				
			</fieldset>
		</div>

			<div class="floatleft">
				<fieldset class="heightOfFieldset" style="width: 350px;margin-left : 10px">
					<legend>Call Home Policy</legend>
						<div class="commonclassForFormFieldsCallHomeSetting">
                                  <form id="adminCallHomePolicyForm" data-dojo-id="adminCallHomePolicyForm"  data-dojo-type="xwt.widget.notification.Form" name="tableForm">
                                         <table>
                                                <tr style="height: 30px; text-align: center;">
                                                       <td>
                                                              <div class="labelspace">
                                                                     <label style="float: center;">State:</label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                       		<select id="adminCallHomePolicyState" data-dojo-id="adminCallHomePolicyState"
																		data-dojo-type="xwt.widget.form.DropDown"												
																		name="state"
																		maxHeight="100" style="width: 150px">
															</select>
                                                       </td>
                                                       <td style="padding-left: 10px;">&nbsp;</td>
                                                </tr>
                                                <tr style="height: 30px; text-align: center;">
                                                       <td>
                                                              <div class="labelspace">
                                                                     <label style="float: center;">Cause:</label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                              <select id="adminCallHomePolicyCause" data-dojo-id="adminCallHomePolicyCause"
																		data-dojo-type="xwt.widget.form.DropDown"												
																		name="cause"
																		maxHeight="100" style="width: 150px">
															</select>
                                                       </td>
                                                       <td style="padding-left: 10px;">
                                                              <button data-dojo-type="dijit/form/Button" data-dojo-id="adminCallHomePolicyGenerateDataBtn" onClick="adminCallHomePolicyGenerateData();" type="button">Add</button>
                                                       </td>
                                                </tr>
                                         </table>
                                  </form>
                           </div>
                           <div class="floatleft" style="padding-top: 10px;">
                                  <div dojotype="dijit.layout.ContentPane" region="left"
                                         style="width: 340px; overflow: hidden;" splitter="true">
                                         <span dojoType="dojo.data.ItemFileWriteStore"
                                                jsId="adminCallHomePolicyDataStore"
                                                data="adminCallHomePolicyData"></span>
                                         <div style="width: 340px !important;"
                                                id="adminCallHomePolicyTableTollBar"
                                                dojoType="xwt.widget.table.ContextualToolbar"
                                                tableId="adminCallHomePolicyTable" quickFilter="false">
                                                <div dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
                                         </div>
                                         <div id="adminCallHomePolicyTable"
                                                data-dojo-id="adminCallHomePolicyTable"
                                                dojoType="xwt.widget.table.Table"
                                                store="adminCallHomePolicyDataStore"
                                                structure="adminCallHomePolicyColumns"
                                                style="width: 340px; height: 225px;" selectMultiple="true"
                                                selectAllOption="true" showIndex="false" selectModel="input"
                                                filterOnServer=false></div>
                                  </div>
                           </div>							
				</fieldset>
			</div>
	</div>
	
	
<div id="adminCallHomeProfileRecipientDialog" button2Label="Close" button1Label="Save"
              data-dojo-id="adminCallHomeProfileRecipientDialog" title=""
              dojoType="xwt.widget.layout.Dialog" closable="true"
              style="display: none;">
              <div style="width: 45rem; height: 25rem;">
                     <fieldset style="height: 250px; width: 415px; margin: 0px; margin-bottom: 0px;">
                           <legend>Recipients Address</legend>
                           <div class="adminCallHomeProfileRecipients">
                                  <form id="adminCallHomeProfileRecipientForm" data-dojo-id="adminCallHomeProfileRecipientForm"  data-dojo-type="xwt.widget.notification.Form" name="tableForm">
                                   <input id="adminCallHomeProfileId" name="adminCallhomeProfile" data-dojo-id="adminCallHomeProfileId" type="hidden" dojoType="dijit.form.TextBox"></input>
                                         <table style="width: 400px; text-align: center;">
                                                <tr style="height: 30px; text-align: center;">
                                                       <td>
                                                              <div class="labelspace">
                                                                     <label style="float: center;">Email:<em>*</em></label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                              <div id="callHomeRecipientsEmail" name="email" style="width:120px;"data-dojo-id="callHomeRecipientsEmail"
                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
                                                                     data-dojo-props='regExp:REG_EX_EMAIL_ADDERESS,trim:"true", maxlength:"80", invalidMessage:MSG_EMAIL_ADDERESS'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                       </td>
                                                       <td style="padding-left: 10px;">
                                                              <button data-dojo-type="dijit/form/Button" data-dojo-id="adminCallHomeProfileRecipientGenerateDataBtn" onClick="adminCallHomeProfileRecipientGenerateData();" type="button">Add</button>
                                                       </td>
                                                </tr>
                                         </table>
                                  </form>
                           </div>
                           <div class="floatleft" style="padding-left: 7px; padding-top: 10px;">
                                  <div dojotype="dijit.layout.ContentPane" region="left"
                                         style="width: 400px; overflow: hidden;" splitter="true">
                                         <span dojoType="dojo.data.ItemFileWriteStore"
                                                jsId="adminCallHomeRecipientDataStore"
                                                data="adminCallHomeRecipientData"></span>
                                         <div style="width: 400px !important;"
                                                id="adminCallHomeRecipientTableTollBar"
                                                dojoType="xwt.widget.table.ContextualToolbar"
                                                tableId="adminCallHomeRecipientTable" quickFilter="false">
                                                <div dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="delete"></div>
                                         </div>
                                         <div id="adminCallHomeRecipientTable"
                                                data-dojo-id="adminCallHomeRecipientTable"
                                                dojoType="xwt.widget.table.Table"
                                                store="adminCallHomeRecipientDataStore"
                                                structure="adminCallHomeRecipientColumns"
                                                style="width: 400px; height: 150px;" selectMultiple="true"
                                                selectAllOption="true" showIndex="false" selectModel="input"
                                                filterOnServer=false></div>
                                  </div>
                           </div>
                     </fieldset>                
              </div>

       </div>
       
</body>
</html>
