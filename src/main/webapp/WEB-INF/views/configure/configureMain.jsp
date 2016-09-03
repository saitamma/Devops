<%-- configureMain.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	dojo.require("dijit.form.SimpleTextarea");
	dojo.require("xwt.widget.notification.Form");
	dojo.require("xwt.widget.notification.ValidationTextBox");
	dojo.require("dijit.form.Button");
	dojo.require("xwt.widget.layout.Dialog");
	dojo.require("xwt.widget.notification.ProgressBar");

	var standByDijitObj;
	var standByDijitObjDown;
	function makeButtonAsEnabled() {
		dijit.byId("pingUCS").set("disabled", false);
		dijit.byId("pushConfigurationUCS").set("disabled", false);
	}
	function makeButtonAsDisabled() {
		dijit.byId("pingUCS").set("disabled", true);
		dijit.byId("pushConfigurationUCS").set("disabled", true);
	}
	
	dojo.ready(function() {
				setTimeout(
						function() {
							standByDijitObj = dijit.byId("progressBarDialogId");
							var title = dojo.query('.progressBarDialog .dijitDialogTitleBar').style('display', 'none');
					        var button = dojo.query('.progressBarDialog .xwtTextButtonGroup').style('display', 'none');
							
					        // for download only progress bar
					        standByDijitObjDown = dijit.byId("progressBarDialogDownloadId");
							var title = dojo.query('.progressBarDialogDownLoad .dijitDialogTitleBar').style('display', 'none');
					        var button = dojo.query('.progressBarDialogDownLoad .xwtTextButtonGroup').style('display', 'none');
					        // Enabled button
							
							dojo.connect(dijit.byId("configureIpAddressOfUCS"),
									"onKeyUp", function() {
										if (dijit.byId(
												"configureIpAddressOfUCS").get(
												"value") != ""
												&& dijit.byId(
														"configureUserName")
														.get("value") != ""
												&& dijit.byId(
														"configurePassword")
														.get("value") != "") {
											makeButtonAsEnabled();
										} else {
											makeButtonAsDisabled();
										}
									});
							dojo.connect(dijit.byId("configureUserName"),
									"onKeyUp", function() {
										if (dijit.byId(
												"configureIpAddressOfUCS").get(
												"value") != ""
												&& dijit.byId(
														"configureUserName")
														.get("value") != ""
												&& dijit.byId(
														"configurePassword")
														.get("value") != "") {
											makeButtonAsEnabled();
										} else {
											makeButtonAsDisabled();
										}
									});
							dojo.connect(dijit.byId("configurePassword"),
									"onKeyUp", function() {
										if (dijit.byId(
												"configureIpAddressOfUCS").get(
												"value") != ""
												&& dijit.byId(
														"configureUserName")
														.get("value") != ""
												&& dijit.byId(
														"configurePassword")
														.get("value") != "") {
											makeButtonAsEnabled();
										} else {
											makeButtonAsDisabled();
										}
									});

							dojo.connect(
											dijit.byId("pingUCS"),
											"onClick",
											function() {
												if (dijit.byId(
														"configureTableForm")
														.validate() == false) {
													return false;
												}
												standByDijitObj.show();
												var ConfigureFormObj = dojo
														.formToJson("configureTableForm");

												setTimeout(
														function() {
															var response = ajaxCallPostWithJsonContent(
																	"verifyUCSCredentials.html",
																	ConfigureFormObj,
																	null,
																	"text");
															if (response == "true") {
																displayNotificationAlert(
																		MSG_PING_SUCCESS,
																		'information');
															} else {
																displayNotificationAlert(MSG_PING_FAILURE);
															}
															standByDijitObj.hide();

														}, 1000);
											});

							dojo.connect( dijit.byId("pushConfigurationUCS"), "onClick", function() {
												if (dijit.byId("configureTableForm").validate() == false) {
													return false;
												}
												standByDijitObj.show();
												var configureFormObj = dojo.formToJson("configureTableForm");
												setTimeout(function() {
															var response = JSON.parse(ajaxCallPostWithJsonContent("configureUcs.html",configureFormObj,null,"text"));
															standByDijitObj.hide();
															if ((response.Download_Template != undefined && response.Download_Template == false)) {
																displayNotificationAlert(MSG_ERROR_DOWNLOAD_TEMP);
															} else if ((response.Conf_Creation != undefined && response.Conf_Creation == false)) {
																displayNotificationAlert(MSG_ERROR_CREATING_CONFIG);
															} else if ((response.Push_Conf != undefined && response.Push_Conf == false)) {
																displayNotificationAlert(MSG_ERROR_PUSH_CONFIG);
															} else {
																var getLogger = JSON.parse( ajaxCallPostWithJsonContent("getPushConfigLogs.html",configureFormObj, null, "text") );
																var logVal = "";
																dojo.forEach(getLogger,function(valuesLog,i){
																	logVal += JSON.parse(valuesLog).name+" : "+JSON.parse(valuesLog).description+" : "+JSON.parse(valuesLog).status+"\n";
																});
																dijit.byId("consoleContentOutput").set("value",logVal);
																displayNotificationAlert( MSG_SUCCESS_PUSH, 'information');
															}
														}, 1000);
										});

							dojo.connect( dijit.byId("downloadUCSConfiguration"), "onClick", function() {
										downloadData();
									});
							
							dojo.connect( dijit.byId("downloadUCSAdaUtility"), "onClick", function() {								
								downloadUcsAdaUtility();
							});

						}, 300);
			});

	function downloadData() {
        standByDijitObjDown.show();
        
		var configureFormObj = dojo.formToJson("configureTableForm");
		setTimeout( function() {
						response = JSON.parse(ajaxCallPostWithJsonContent( "processDownloadWithSession.html", configureFormObj, null,"text"));

						if (response.Zip_Creation != undefined
								&& response.Zip_Creation == true) {
							var iframe = document.getElementById("downloadFrame");
							iframe.src = "downloadUcsData.html";
						} else {
							displayNotificationAlert(MSG_ERROR_ZIPPING, "critical");
						}
						standByDijitObjDown.hide();
				}, 1000);
	}
function downloadUcsAdaUtility(){
	var iframe = document.getElementById("downloadFrame");
	iframe.src = "downloadUcsAdaUtility.html";
}
</script>
</head>
<body>

	<hr class="topLine">
	<div id="parentDiv">
		<div class="floatleft">
			<fieldset style="height: 350px; width: 1225px;">
				<legend>Configure UCS Manager</legend>
				<div id="configurationContainer">
					<div class="ConfigurationScreen floatleft" style="width:96%; height: 320px;padding: 15px 20px 0 20px;">
						<div id="downloadingImportantNote1" style="font-size: 11px; font-family:Arial; text-align: justify;height: 30px" class="paddingBottom">
							<div class="floatleft" style="width: 24px; font-size: 18px;font-family: CiscoSansThin, Arial;">1.</div>
							<div style="font-size: 18px;font-family: CiscoSansThin, Arial;">Download Config Utility jar (If you haven't downloaded previously). Config Utility jar is used to push the configuration file to the device.</div> 
						</div>
						<div class="paddingLeft" id = "downloadButton" style="display: table;margin: auto;height: 70px">
								<button data-dojo-type="dijit.form.Button" disabled="false"
								data-dojo-id="downloadUCSAdaUtility"
								id="downloadUCSAdaUtility"
								title="Download UCS configuration"
								type="button">Download Config Utility &nbsp;</button>
						</div>
						
						<div id="downloadingImportantNote2" style="font-size: 11px; font-family:Arial; text-align: justify;height: 30px" class="paddingBottom" >
							<div class="floatleft" style="width: 24px; font-size: 18px;font-family: CiscoSansThin, Arial;">2.</div>
							<div style="font-size: 18px;font-family: CiscoSansThin, Arial;">Download the Configuration created using workflow.</div> 
						</div>
						<div class="paddingLeft" id = "downloadButton" style="display: table;margin: auto;height: 70px">
								<button data-dojo-type="dijit.form.Button" disabled="false"
								data-dojo-id="downloadUCSConfiguration"
								id="downloadUCSConfiguration"
								title="Download UCS configuration"
								type="button">Download Configuration</button>
						</div>
																								
						<div id="downloadingImportantNote3" style="font-size: 11px; font-family:Arial; text-align: justify;height: 30px" class="paddingBottom">
							<div class="floatleft" style="width: 25px; font-size: 18px;font-family: CiscoSansThin, Arial;height: 25px">3.</div>
							<div style="font-size: 18px;font-family: CiscoSansThin, Arial;">Run the Config Utility jar on your local machine which you have downloaded in step 1, upload the configuration file which you have downloaded in step 2, click on ping button to test the device status(optional), click on push button to configure your device.</div> 
						</div>
						
						<div id="downloadingImportantNote4" style="font-size: 11px; font-family:Arial; text-align: justify;line-height: 70px" class="paddingBottom">
							<div class="fi-info floatleft" style="height: 120px;width: 24px;"></div>													
							<div style="font-size: 18px;font-family: CiscoSansThin, Arial;">Detailed instructions on how to install utility and configure device is given <a href="downloadUcsUtilityHelpDocument.html" target="_blank" style="font-size: 18px;font-family: CiscoSansThin, Arial;">here</a></div> 
						</div>						
					</div>
					
				</div>
				<div class="ConfigurationScreen floatleft" style="width: 800px;">
					<div id="pingPushContainer" style="display:none;">
						<form id="configureTableForm" data-dojo-id="configureTableForm"
							data-dojo-type="xwt.widget.notification.Form"
							name="configureTableForm">
							<table>
								<tr>
									<td>
										<div class="labelspace">
											<label style="float: left;">IP address of UCS:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="configureIpAddressOfUCS" name="url"
											style="width: 100px;" data-dojo-id="configureIpAddressOfUCS"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											regExp="(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])"
											data-dojo-props='trim:"true", required:"true", maxlength:"40", promptMessage:"", invalidMessage:MSG_IPADDRESS'></div>
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Username:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="configureUserName" name="userName"
											data-dojo-id="configureUserName" style="width: 100px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='trim:"true",required:"true", maxlength:"20", promptMessage:"", invalidMessage:MSG_USERNAME'></div>
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Password:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="configurePassword" name="password" type="password"
											data-dojo-id="configurePassword" style="width: 100px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='trim:"true",required:"true", maxlength:"20", promptMessage:"", invalidMessage:MSG_PASSWORD'></div>
									</td>
								</tr>
								<tr>
									<td colspan="6" height="30">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="6">
										<div style="padding-left: 465px;" class="floatleft">
											<button data-dojo-type="dijit.form.Button" disabled="true"
												data-dojo-id="pingUCS" id="pingUCS" type="button"
												title="Ping and verify UCS credentials">Ping</button>
										</div>
										<div style="padding-left: 60px;" class="floatleft">
											<button data-dojo-type="dijit.form.Button" disabled="true"
												data-dojo-id="pushConfigurationUCS"
												id="pushConfigurationUCS" class="configPushClass"
												type="button">Push Configuration</button>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="floatleft" style="display:none;">
					<fieldset style="height: 140px; width: 1195px;">
						<legend>Console Output</legend>
						<div dojoType="dijit.form.SimpleTextarea"
							style="height: 100px; width: 1183px;" readOnly="true"
							id="consoleContentOutput">No logs available.</div>
					</fieldset>
				</div>
			</fieldset>
		</div>
	</div>
	<iframe id="downloadFrame" style="display: none;" src=""></iframe>
	
	<div class="progressBarDialog" dojoType="xwt.widget.layout.Dialog" id="progressBarDialogId" style="width: 35rem;border: 2px solid #666;">
            <div style="overflow:hidden;" dojoType='dijit.layout.ContentPane'>
               <div maximum="20" indeterminate="true" jsid="intProgress" id="progressBarId" showInformationtext=true infoText = "&lt;b&gt;Please wait for the current operation to finish, It may take longer time based on network and configurations supplied. &lt;/b&gt;" showBoundingBox=false dojoType="xwt.widget.notification.ProgressBar"></div>
            </div>
    </div>
    
    <div class="progressBarDialogDownLoad" dojoType="xwt.widget.layout.Dialog" id="progressBarDialogDownloadId" style="width: 35rem;border: 2px solid #666;">
            <div style="overflow:hidden;" dojoType='dijit.layout.ContentPane'>
               <div maximum="20" indeterminate="true" jsid="intProgressDownload" showInformationtext=true infoText = "&lt;b&gt;Please wait for the current operation to finish. &lt;/b&gt;" showBoundingBox=false dojoType="xwt.widget.notification.ProgressBar"></div>
            </div>
    </div>
    
</body>
</html>
