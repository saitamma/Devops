<%-- adminCallHomeSetting.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/claroGrid.css" />
<script type="text/javascript">
dojo.require("xwt.widget.form.DropDown");
dojo.require("xwt.widget.form.TextButton");					  	
dojo.require("xwt.widget.table.Table");
dojo.require("xwt.widget.notification.Form");
dojo.require("dijit.form.TextBox");   		   

var callHomeGeneratSettingDataResponse = ajaxCallGet("getCallHomeGeneralSetting.html", true, "json");
var generalSetting,sysInventory;
try {
	var callHomeSettingRes=JSON.parse(callHomeGeneratSettingDataResponse);
	generalSetting = JSON.parse(callHomeSettingRes.generalList[0]);
	sysInventory = JSON.parse(callHomeSettingRes.sysInventoryList[0]);
} catch (e) {
	console.log("no data saved");
}



dojo.ready(function(){
	 setTimeout(function(){ 					 
		  dojo.connect(dijit.byId("adminCallHomeSettingState"),"onChange",function(){					    	
		    	if(this.value == 'on'){				    		
		    		enableDisableFieldsForGeneralSetting(false);	
		    	}
		    	else{
		    		var settingId = dijit.byId("adminCallHomeSettingId").get("value");
		    		adminCallHomeGeneralSettingForm.reset();
		    		dijit.byId("adminCallHomeSettingId").set("value",settingId);
		    		enableDisableFieldsForGeneralSetting(true);
		    	}					    	
		     });		 
		 adminCallHomeGeneralSettingForm.set("value",generalSetting);
		 adminCallHomeSettingSysInventoryForm.set("value",sysInventory);					   				    		
	  },1000);
});

function enableDisableFieldsForGeneralSetting(status){
	adminCallHomeSettingSwitchPriority.set("disabled",status);
	adminCallHomeSettingThrottling.set("disabled",status);
	adminCallHomeSettingContact.set("readOnly",status);
	adminCallHomeSettingPhone.set("readOnly",status);
	adminCallHomeSettingEmail.set("readOnly",status);
	adminCallHomeSettingAddress.set("readOnly",status);
	adminCallHomeSettingCustomerId.set("readOnly",status);
	adminCallHomeSettingContractId.set("readOnly",status);
	adminCallHomeSettingSiteId.set("readOnly",status);
	adminCallHomeSettingEmailFrom.set("readOnly",status);
	adminCallHomeSettingEmailReplyTo.set("readOnly",status);
	adminCallHomeSettingHostName.set("readOnly",status);
	adminCallHomeSettingPort.set("readOnly",status);	 
}

function enableDisableFieldsForSysInventory(status){
	adminCallHomeSettingTimeLastSent.set("readOnly",status);
	adminCallHomeSettingNextScheduled.set("readOnly",status);  
}

function saveCallHomeGeneralAndSysInventory(){
	var generalSettingFormObj =  dojo.formToJson("adminCallHomeGeneralSettingForm");	
	var jsonFormatValidateForGs = JSON.parse(generalSettingFormObj);
	jsonFormatValidateForGs.id = parseInt(jsonFormatValidateForGs.id);
	jsonFormatValidateForGs.port = parseInt(jsonFormatValidateForGs.port);      
	if(jsonFormatValidateForGs.state=='on'){
		
		if(dijit.byId("adminCallHomeGeneralSettingForm").validate()==false || dijit.byId("adminCallHomeSettingSysInventoryForm").validate() == false){
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		
	}else{
		jsonFormatValidateForGs.switchPriority = "debugging";
		jsonFormatValidateForGs.throttling = "on";		
	}  	
	var sysInventoryFormObj =  dojo.formToJson("adminCallHomeSettingSysInventoryForm");
	var jsonFormatValidateForSi = JSON.parse(sysInventoryFormObj);
	jsonFormatValidateForSi.id = parseInt(jsonFormatValidateForSi.id);	
	jsonFormatValidateForSi.sendIntervalDays = parseInt(jsonFormatValidateForSi.sendIntervalDays);
	jsonFormatValidateForSi.sendIntervalHours = parseInt(jsonFormatValidateForSi.sendIntervalHours);
	jsonFormatValidateForSi.sendIntervalMinutes = parseInt(jsonFormatValidateForSi.sendIntervalMinutes);
	
	var jsonPair = {}; 
	jsonPair["generalSetting"] = [jsonFormatValidateForGs];
	jsonPair["systemInventory"] = [jsonFormatValidateForSi];	
	var generalSettingResponse = ajaxCallPostWithJsonContent("manageCallHomeGeneralSetting.html" ,JSON.stringify(jsonPair), null, "text");
	return generalSettingResponse;
}
//function for Save data to admin
function saveAdminCallHomeSettingOnNext(){	
	resp = saveCallHomeGeneralAndSysInventory();
	if(resp == "success"){
		var saveNavState = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":1,"activeStateSubMenuIndex":4});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , saveNavState, null, "text");
		return true;
	}else {
		return false;
	}
	
}  
</script>
</head>  
<body> 
	<div id="parentDiv" class="widtInPercent">
		<div class="floatleft addCssIntreeTable removeBorderFromTH sameAsPrime" style="width: 65%;">
			<fieldset class="heightOfFieldset" style="width: 95%;">
				<legend>General Setting</legend>
					<div class="commonclassForFormFieldsCallHomeSetting">				
						<form id="adminCallHomeGeneralSettingForm"
							data-dojo-id="adminCallHomeGeneralSettingForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<input type="hidden" name="id" value="0" id="adminCallHomeSettingId" data-dojo-type="dijit/form/TextBox" data-dojo-props="trim:true" />
							<table>
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">State:</label>
									</div>
								</td>
								<td height="40"><select id="adminCallHomeSettingState"
									data-dojo-id="adminCallHomeSettingState"
									name="state"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 118px">
									<option value="off">Off</option>
									<option value="on">On</option>									
									</select>
								</td>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Switch Priority:</label>
									</div>
								</td>
								<td height="40">
								<select id="adminCallHomeSettingSwitchPriority"
									data-dojo-id="adminCallHomeSettingSwitchPriority"
									name="switchPriority"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 118px" disabled="true">    
									<option value="debug">Debugging</option>
									<option value="emergency">Emergencies</option>
									<option value="alert">Alerts</option>
									<option value="critical">Critical</option>
									<option value="error">Errors</option>
									<option value="warning">Warnings</option>
									<option value="notice">Notifications</option>
									<option value="info">Information</option>
									</select>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Throttling:</label>
									</div>
								</td>
								<td height="40">
								<select id="adminCallHomeSettingThrottling"
									data-dojo-id="adminCallHomeSettingThrottling"
									name="throttling"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 118px" disabled="true">
									<option value="on">On</option>
									<option value="off">Off</option>  
									</select>  
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Contact:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingContact"
										data-dojo-id="adminCallHomeSettingContact"
										name="contact" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true",required:"true", maxlength:"255"' readOnly=true></div>
								</td>
								</tr>
								
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Phone:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingPhone"
										data-dojo-id="adminCallHomeSettingPhone"
										name="phone" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_PHONE, trim:"true", maxlength:"16", required:"true", invalidMessage:MSG_PHONE' readOnly=true></div>  
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Email:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingEmail"
										data-dojo-id="adminCallHomeSettingEmail"
										name="email" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_EMAIL_ADDERESS, trim:"true", maxlength:"80", required:"true", invalidMessage:MSG_EMAIL_ADDERESS' readOnly=true></div>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Address:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingAddress"
										data-dojo-id="adminCallHomeSettingAddress"
										name="address" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"255", required:"true"' readOnly=true></div>
								</td>
							</tr>
								
								<tr>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">Customer ID:</label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingCustomerId"
										data-dojo-id="adminCallHomeSettingCustomerId"
										name="customerId" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"510"' readOnly=true></div>
								</td>								
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Contract ID:<em>*</em></label>
									</div>
								</td>
								<td height="40">  
									<div id="adminCallHomeSettingContractId"
										data-dojo-id="adminCallHomeSettingContractId"
										name="contractId" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"510", required:"true"' readOnly=true></div>
								</td>								
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Site ID:</label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingSiteId"
										data-dojo-id="adminCallHomeSettingSiteId"
										name="siteId" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"510"' readOnly=true></div>
								</td>									
								</tr>
								<tr>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">From:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingEmailFrom"
										data-dojo-id="adminCallHomeSettingEmailFrom"
										name="emailFrom" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_EMAIL_ADDERESS, trim:"true", maxlength:"80", required:"true", invalidMessage:MSG_EMAIL_ADDERESS' readOnly=true></div>
								</td>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">Reply To:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingEmailReplyTo"
										data-dojo-id="adminCallHomeSettingEmailReplyTo"
										name="replyTo" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_EMAIL_ADDERESS, trim:"true", maxlength:"80", required:"true", invalidMessage:MSG_EMAIL_ADDERESS' readOnly=true></div>
								</td>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">Host:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingHostName"
										data-dojo-id="adminCallHomeSettingHostName"
										name="host" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern: REG_EX_EITHERIP_OR_HOST, trim:"true", maxlength:"255", required:"true", invalidMessage:MSG_HOSTNAME_IP' readOnly=true></div>
								</td>									
								</tr>
								<tr>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">Port:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminCallHomeSettingPort"
										data-dojo-id="adminCallHomeSettingPort"
										name="port" style="width: 100px;"  
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_NUMBER_1_TO_65535, trim:"true", maxlength:"5", required:"true", invalidMessage:MSG_BET_1_TO_65535' readOnly=true value="25"></div>
								</td>
								<td colspan="4"></td>									
								</tr>								
							</table>      
						</form>						
					</div>
			</fieldset>
		</div>
		<div class="floatleft" style="width: 33%;">
			<fieldset class="heightOfFieldset" style="width: 95%; margin-left:20px;">
				<legend>System Inventory</legend>
					<div class="commonclassForFormFieldsCallHomeSetting">
						<form id="adminCallHomeSettingSysInventoryForm"
									data-dojo-id="adminCallHomeSettingSysInventoryForm"
									data-dojo-type="xwt.widget.notification.Form" name="tableForm">
									<input type="hidden" name="id" value="0" id="adminCallHomeSettingInventoryId" data-dojo-type="dijit/form/TextBox" data-dojo-props="trim:true" />									
								<table>									
									<tr>
										<td height="40">
											<div class="labelspace">
												<label style="float: left;">Send Periodically:</label>
											</div>
										</td>
										<td height="40"><select id="adminCallHomeSettingSendPeriodically"
											data-dojo-id="adminCallHomeSettingSendPeriodically"
											name="sendPeriodically"
											data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
											style="width: 118px">
											<option value="off">Off</option>
											<option value="on">On</option>									
											</select>
										</td>
									</tr>
									<tr>
										<td height="40">
											<div class="labelspace">
												<label style="float: left;">Send Interval (days):<em>*</em></label>
											</div>
										</td>
										<td height="40">
											<div id="adminCallHomeSettingSendInterval"
												data-dojo-id="adminCallHomeSettingSendInterval"
												name="sendIntervalDays" style="width: 100px;"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='pattern:REG_EX_NUMBER_1_TO_30, trim:"true", maxlength:"16", required:"true", invalidMessage:MSG_BET_1_30' value="30"></div>
										</td>
									</tr>
									<tr>
										<td height="40">
											<div class="labelspace">
												<label style="float: left;">Hour Of Day To Send:<em>*</em></label>
											</div>
										</td>
										<td height="40">
											<div id="adminCallHomeSettingHodToSend"
												data-dojo-id="adminCallHomeSettingHodToSend"
												name="sendIntervalHours" style="width: 100px;"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='pattern:REG_EX_NUMBER_0_TO_23, trim:"true", maxlength:"16", required:"true", invalidMessage:MSG_BET_0_23' value="0"></div>
										</td>
									</tr>
									<tr>
										<td height="40">
											<div class="labelspace">
												<label style="float: left;">Minute Of Hour:<em>*</em></label>
											</div>
										</td>
										<td height="40">
											<div id="adminCallHomeSettingMinuteOfHour"
												data-dojo-id="adminCallHomeSettingMinuteOfHour"
												name="sendIntervalMinutes" style="width: 100px;"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='pattern:REG_EX_NUMBER_0_TO_59, trim:"true", maxlength:"16", required:"true", invalidMessage:MSG_BET_0_59' value="0"></div>
										</td>
									</tr>											
								</table>
						</form>								
					</div>
			</fieldset>
		</div>
	</div>	
</body>
</html>
