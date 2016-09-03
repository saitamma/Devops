<%-- adminBackupConfiguration.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/claroGrid.css" />
<script type="text/javascript">
dojo.require("xwt.widget.form.DropDown");
dojo.require("xwt.widget.form.TextButton");					  	
dojo.require("xwt.widget.notification.Form");
dojo.require("dijit.form.TextBox"); 
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.RadioButton");

var backupConfigDataResponse = ajaxCallGet("getAdminBackupConfig.html", true, "json");
var parsedBackupConfigDataResponse;
try {
	 parsedBackupConfigDataResponse = JSON.parse(backupConfigDataResponse);
} catch (e) {
	console.log("no data saved");
}

function selectRadioForBachupStatus(status){
	if(status == 'enable'){			    		
		enableDisableFieldsForBackupConfig(false);	
	}
	else{
		var backupId = dijit.byId("adminBackupConfigId").get("value");
		adminBackupConfigForm.reset();
		dijit.byId("adminBackupConfigId").set("value",backupId);
		enableDisableFieldsForBackupConfig(true);
	}
}

dojo.ready(function(){
	 setTimeout(function(){ 					 
		 
		 dojo.connect(dijit.byId("adminBackupConfigType"),"onChange",function(){					    	
		    	if(this.value == 'config-system' || this.value == 'full-state'){
		    		adminBackupConfigPreserveIdentities.set("value","no");
		    		enableDisablePreserveIdentitiesByConfigType(true);		    		
		    	}
		    	else{
		    		enableDisablePreserveIdentitiesByConfigType(false);
		    	}					    	
		     });
		 dojo.connect(dijit.byId("adminBackupConfigProtocol"),"onChange",function(){					    	
		    	if(this.value == 'tftp'){
		    		adminBackupConfigUser.set("value","");
		    		adminBackupConfigUser.set("required",false);
		    		adminBackupConfigPassword.set("value","");
		    		enableDisableFieldForUserAndPassword(true);	    			
		    	}
		    	else{
		    		enableDisableFieldForUserAndPassword(false);
		    		adminBackupConfigUser.set("required",true);
		    	}					    	
		     });
		 adminBackupConfigForm.set("value",parsedBackupConfigDataResponse);
		if( dijit.byId("adminBackupConfigBackupStatusYes").checked == true ){
			selectRadioForBachupStatus("enable");
		}
	  },1000);
});

function enableDisablePreserveIdentitiesByConfigType(status){
	adminBackupConfigPreserveIdentities.set("disabled",status);  
}
function enableDisableFieldForUserAndPassword(status){
	adminBackupConfigUser.set("readOnly",status);
	adminBackupConfigPassword.set("readOnly",status);
}
function enableDisableFieldsForBackupConfig(status){
	adminBackupConfigAdminStatus.set("disabled",status);
	adminBackupConfigType.set("disabled",status);
	adminBackupConfigPreserveIdentities.set("disabled",status);	
	adminBackupConfigProtocol.set("disabled",status);
	adminBackupConfigHostname.set("readOnly",status);
	adminBackupConfigRemoteFile.set("readOnly",status);
	adminBackupConfigUser.set("readOnly",status);
	adminBackupConfigPassword.set("readOnly",status);	
}

function saveBackupConfiguration(){
	var commonJson = "";
	var defaultJson = "{\"id\":0,\"backupStatus\":\"disable\",\"adminState\":\"disable\",\"backupType\":\"config-all\",\"preserveIdentities\":\"no\",\"protocol\":\"ftp\",\"hostname\":null,\"remoteFile\":null,\"userName\":null,\"password\":null}";		
	
	if(dijit.byId("adminBackupConfigBackupStatusNo").checked == true) {
		var defaultJsonObj = JSON.parse(defaultJson);
		defaultJsonObj.id = parseInt(dijit.byId("adminBackupConfigId").get("value"));		
		commonJson = JSON.stringify(defaultJsonObj);					
	}
	else {
		var backupConfigFormObj =  dojo.formToJson("adminBackupConfigForm");	
		var jsonFormatValidateForBackupConfig = JSON.parse(backupConfigFormObj);
		jsonFormatValidateForBackupConfig.id = parseInt(jsonFormatValidateForBackupConfig.id);
		
		if(dijit.byId("adminBackupConfigForm").validate()==false) {
			displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
			return false;
		}
		if(jsonFormatValidateForBackupConfig.backupType == 'config-system' || jsonFormatValidateForBackupConfig.backupType == 'full-state') {
			jsonFormatValidateForBackupConfig.preserveIdentities = 'no';
		}		
		commonJson = JSON.stringify(jsonFormatValidateForBackupConfig);			
	}	
	console.log(commonJson);
	var response = ajaxCallPostWithJsonContent("manageAdminBackupConfig.html" ,"["+commonJson+"]", null, "text");
	console.log(response);
	return response;
}
//function for Save data to admin
function saveAdminBackupConfigOnNext(){	
	resp = saveBackupConfiguration();
	if(resp == "success"){
		var saveNavState = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":2,"activeStateSubMenuIndex":0});
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
		<div class="floatleft addCssIntreeTable removeBorderFromTH sameAsPrime" style="width: 97.5%;">
			<fieldset class="heightOfFieldset" style="width: 97.5%;">
				<legend>Create Backup Operation</legend>
					<div class="commonclassForFormFieldsCallHomeSetting">				
						<form id="adminBackupConfigForm"
							data-dojo-id="adminBackupConfigForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<input type="hidden" name="id" value="0" id="adminBackupConfigId" data-dojo-type="dijit/form/TextBox" data-dojo-props="trim:true" />
							<div style="border-bottom: 1px solid #C0C0C0;padding: 5px 0px 15px 0px;">
							<table style="width: 385px;">
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Do you want to configure backup?:</label>
									</div>
								</td>
								<td>
									<input name="backupStatus" id="adminBackupConfigBackupStatusNo" onclick="selectRadioForBachupStatus(this.value)" checked="checked" type="radio" dojoType="dijit.form.RadioButton" value="disable"/>
									<label style="padding-left: 0px;" for="radio_default">No</label> 	
									<input name="backupStatus" id="adminBackupConfigBackupStatusYes" onclick="selectRadioForBachupStatus(this.value)" type="radio" dojoType="dijit.form.RadioButton" value="enable" />
									<label style="padding-left: 0px;" for="radio_sequential">Yes</label>
									</td>
								<td colspan="4"></td>
							</tr>
							</table>
							</div>
							
							<table style="width: 100%">
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Admin State:</label>
									</div>
								</td>
								<td height="40">
								<select id="adminBackupConfigAdminStatus"
									data-dojo-id="adminBackupConfigAdminStatus"
									name="adminState"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 118px" disabled="true">    
									<option value="disable">Disable</option>
									<option value="enable">Enable</option>									
									</select>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Type:</label>
									</div>
								</td>
								<td height="40">
								<select id="adminBackupConfigType"
									data-dojo-id="adminBackupConfigType"
									name="backupType"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 150px" disabled="true">
									<option value="config-all">All Configuration</option>
									<option value="full-state">Full State</option>
									<option value="config-system">System Configuration</option>
									<option value="config-logical">Logical Configuration</option>  
									</select>  
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Preserve Identities:</label>
									</div>
								</td>
								<td height="40">									
									<select id="adminBackupConfigPreserveIdentities"
									data-dojo-id=adminBackupConfigPreserveIdentities
									name="preserveIdentities"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 118px" disabled="true">    
									<option value="no">No</option>
									<option value="yes">Yes</option>									
									</select>
								</td>
							</tr>
								
							<tr>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Protocol:</label>
									</div>
								</td>
								<td height="40">									
									<select id="adminBackupConfigProtocol"
									data-dojo-id="adminBackupConfigProtocol"
									name="protocol"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 118px" disabled="true">
									<option value="ftp">FTP</option>
									<option value="tftp">TFTP</option>
									<option value="scp">SCP</option>
									<option value="sftp">SFTP</option>  
									</select>  
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Hostname:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminBackupConfigHostname"
										data-dojo-id="adminBackupConfigHostname"
										name="hostname" style="width: 134px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern: REG_EX_EITHERIP_OR_HOST,trim:"true", maxlength:"256", required:"true",invalidMessage:MSG_HOSTNAME_IP' readOnly=true></div>
								</td>
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Remote File:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminBackupConfigRemoteFile"
										data-dojo-id="adminBackupConfigRemoteFile"
										name="remoteFile" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"128", required:"true"' readOnly=true></div>
								</td>
							</tr>							
								<tr>
									<td height="40">
									<div class="labelspace">
										<label style="float: left;">User:<em>*</em></label>
									</div>
								</td>
								<td height="40">
									<div id="adminBackupConfigUser"
										data-dojo-id="adminBackupConfigUser"
										name="userName" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"45", required:"true"' readOnly=true></div>
								</td>								
								<td height="40">
									<div class="labelspace">
										<label style="float: left;">Password:</label>
									</div>
								</td>
								<td height="40">  
									<div id="adminBackupConfigPassword" type="password"
										data-dojo-id="adminBackupConfigPassword"
										name=password style="width: 134px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='trim:"true", maxlength:"45"' readOnly=true></div>
								</td>								
								<td colspan="2"></td>							
							</tr>											
							</table>      
						</form>						
					</div>
			</fieldset>
		</div>		
	</div>	
</body>
</html>
