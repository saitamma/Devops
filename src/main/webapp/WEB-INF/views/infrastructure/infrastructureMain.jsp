<%-- infrastructure.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
	    <script type="text/javascript">
			var djConfig = {
			    parseOnLoad: true
			};
		</script>
    	<script type="text/javascript">
    	
    	var equipmentEthernetFc;
    	try {
    		var equipmentEthernetFc = JSON.parse(ajaxCallGet("fetchEquipmentEthernetFcMode.html", true, "json")[0]);
    		console.log(equipmentEthernetFc);
    	} catch (e) {
    		console.error("some error in ethernet and fc mode getting request");
    	}
    	
    		dojo.require("xwt.xwt");
    		dojo.require("dijit.form.Form");
			dojo.require("xwt.widget.form.DropDown");
			
			
			dojo.ready(function(){
				setTimeout(function(){
					
					var	serverModelArr = [];
					serverModelArr.push({value: "",	label: LABEL_SELECT});
					serverModelArr.push({value: "Cisco UCS 6248",	label: "Cisco UCS 6248"});
					serverModelArr.push({value: "Cisco UCS 6296",	label: "Cisco UCS 6296"});
					serverModelArr.push({value: "Cisco UCS 6324",	label: "Cisco UCS 6324"});
					infraServerModel.addOption(serverModelArr);
					
					var	softwareReleaseMiniArr = [];
					softwareReleaseMiniArr.push({value: "",	label: LABEL_SELECT});
					softwareReleaseMiniArr.push({value:"3.0 (2c)",label:"3.0 (2c)"});
					softwareReleaseMiniArr.push({value:"3.1 (1e)",label:"3.1 (1e)"});

					var	softwareReleaseArr = [];
					softwareReleaseArr.push({value: "",	label: LABEL_SELECT});
					softwareReleaseArr.push({value: "2.2 (2c)",	label: "2.2 (2c)"});
					softwareReleaseArr.push({value: "2.2 (3d)",	label: "2.2 (3d)"});
					softwareReleaseArr.push({value: "2.2 (5b)",	label: "2.2 (5b)"});
					softwareReleaseArr.push({value: "3.1 (1e)",	label: "3.1 (1e)"});
					
					if(infraServerModel.get("value") == "Cisco UCS 6324"){
						infraSoftwareRelease.removeOption(infraSoftwareRelease.getOptions());
						infraSoftwareRelease.addOption(softwareReleaseMiniArr);
					}else{
						infraSoftwareRelease.removeOption(infraSoftwareRelease.getOptions());
						infraSoftwareRelease.addOption(softwareReleaseArr);
					}
					
					var	ioModuleArr = [];
					ioModuleArr.push({value: "", label: LABEL_SELECT});
					ioModuleArr.push({value: "4", label: "2204"});
					ioModuleArr.push({value: "8", label: "2208"});
					infraIoModule.addOption(ioModuleArr);
					
					dojo.connect(infraServerModel,"onChange",function(){
						 if(this.value == "Cisco UCS 6324"){
							infraSoftwareRelease.removeOption(infraSoftwareRelease.getOptions());
							infraSoftwareRelease.addOption(softwareReleaseMiniArr);
							infraIoModule.set("value","");
							infraIoModule.set("readOnly",true);
							radio_Ethernetswitch.set({"checked":false,"disabled":true});
							radio_FCEndHost.set({"checked":false,"disabled":true});
							radio_EthernetEndHost.set("checked",true); //
							radio_FCswitch.set("checked",true);
						}else{
							infraSoftwareRelease.removeOption(infraSoftwareRelease.getOptions());
							infraSoftwareRelease.addOption(softwareReleaseArr);
							infraIoModule.set("value","");
							infraIoModule.set("readOnly",false);
							radio_Ethernetswitch.set("disabled",false);
							radio_FCEndHost.set({"checked":true,"disabled":false});
						}
					});
					
					if(infrastructureParsedResult != undefined){
						infraServerModel.set("value", infrastructureParsedResult.serverModel);
						infraServerModel.set("disabled",true);
						setTimeout(function(){
							infraSoftwareRelease.set("value", infrastructureParsedResult.softwareVersion);	
							infraSoftwareRelease.set("disabled",true);
							infraIoModule.set("value", infrastructureParsedResult.ioModule);
							infraIoModule.set("disabled",true);
							//set value of ethernet fc mode
							deviceModeMiniForm.set("value",equipmentEthernetFc);
						},100);
					}
				
				if(infraServerModel.get("value") == "Cisco UCS 6324" || (infrastructureParsedResult != undefined && infrastructureParsedResult.serverModel == "Cisco UCS 6324") ) {
					radio_Ethernetswitch.set("disabled",true);
					radio_FCEndHost.set("disabled",true);
			    }else{
			    	radio_Ethernetswitch.set("disabled",false);
					radio_FCEndHost.set("disabled",false);
			    }
				
					
				}, 1000);
			});

		function saveInfrastructureData(){
			
			var infraFormObj =  dojo.formToJson("infrastructureTableForm");
			var jsonFormatValidate = JSON.parse(infraFormObj);
			if((jsonFormatValidate.ioModule == "" && jsonFormatValidate.serverModel!="Cisco UCS 6324") || (jsonFormatValidate.serverModel=="" || jsonFormatValidate.softwareVersion=="")){
				displayNotificationAlert(MSG_INFRASTRUCTURE);
				return false;
			}
			if(!infraServerModel.get("disabled") && !infraSoftwareRelease.get("disabled") && !infraIoModule.get("disabled")) {
				generateBitMapForModelNumber(JSON.parse(infraFormObj).serverModel);
				var response = ajaxCallPostWithJsonContent("saveInfrastructure.html" , infraFormObj, null, "text");	
				
				if(response != "success"){
					displayNotificationAlert(MSG_ERROR_WHILESAVE);
					return false;
				}
			}
			
			// save ethernetAndFcMode
			var jsonOfEtherFcMode =  dojo.formToObject("deviceModeMiniForm");
			response = ajaxCallPostWithJsonContent("saveEthernetFcMode.html" , JSON.stringify(jsonOfEtherFcMode), null, "text");
			
			var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":1,"activeStateSubMenuIndex":0});
			ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
			infraServerModel.set("disabled",true);
			infraSoftwareRelease.set("disabled",true);
			infraIoModule.set("disabled",true);

			return true;
		}
	
	</script>
	<style type="text/css">
	.prime .dijitSelectReadOnly{
	    background-color: #F5F5F5;
    	border: 0.1rem solid #C8C8C8;
        cursor: not-allowed !important;
        color: #7F7F7F;
    }
    .prime .dijitSelectReadOnly *{
    	cursor: not-allowed !important;
    }
	</style>
    </head>
    <body>
    	<hr class="topLine">
    		<div id="parentDiv">
    			<div class="floatleft">
					<fieldset style="height: 300px;margin-top:20px;">
						<legend>Hardware Settings</legend>
							<form id="infrastructureTableForm" data-dojo-id="infrastructureTableForm" data-dojo-type="dijit.form.Form" name="infrastructureTableForm">
								<table style="padding:15px 0 0px 30px;" width="500px">
									<tr >
										<td height="50px">
											<div class="labelspace">
												<label style="float:left;">Fabric Interconnect:<em>*</em></label>			
											</div>
										</td>
										<td height="50px">
											<select id="infraServerModel" name="serverModel" data-dojo-id="infraServerModel" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 160px" />
										</td>
									</tr>
									<tr>
										<td height="50px">
											<div class="labelspace">
												<label style="float:left;">Software Release:<em>*</em></label>			
											</div>
										</td>
										<td height="50px">
											<select id="infraSoftwareRelease" name="softwareVersion" data-dojo-id="infraSoftwareRelease" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 160px" />
										</td>
									</tr>
									<tr>
										<td height="50px">
											<div class="labelspace">
												<label style="float:left;">IO Module:<em>*</em></label>			
											</div>
										</td>
										<td height="50px">
											<select id="infraIoModule" name="ioModule" data-dojo-id="infraIoModule" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 160px" />
										</td>
									</tr>
								</table>								
							</form>
						<form id="deviceModeMiniForm"
								data-dojo-id="deviceModeMiniForm"
								data-dojo-type="xwt.widget.notification.Form" name="deviceModeMiniForm">
						<table style="padding:0px 0 10px 30px;" width="500px">
								<tr >
									<td height="50px">
										<div class="labelspace">
											<label style="float:left;">Ethernet Mode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>			
										</div>
									</td>
									<td height="50px">
										<input name="ethernetMode" id="radio_EthernetEndHost" type="radio" data-dojo-id="radio_EthernetEndHost" dojoType="dijit/form/RadioButton" checked="true" value="end-host"/>
										<label for="radio_EthernetEndHost">End-Host</label> 	
										
										<input name="ethernetMode" id="radio_Ethernetswitch" data-dojo-id="radio_Ethernetswitch" type="radio" dojoType="dijit/form/RadioButton" value="switch" />
										<label for="radio_Ethernetswitch">Switch</label>
									</td>
								</tr>
								<tr >
									<td height="50px">
										<div class="labelspace">
											<label style="float:left;">FC Mode:</label>			
										</div>
									</td>
									<td height="50px">
										<input name="fcMode" id="radio_FCEndHost" type="radio" data-dojo-id="radio_FCEndHost" checked="true" dojoType="dijit/form/RadioButton" value="end-host"/>
										<label for="radio_FCEndHost">End-Host</label> 	
										
										<input name="fcMode" id="radio_FCswitch" type="radio" data-dojo-id="radio_FCswitch" dojoType="dijit/form/RadioButton" value="switch" />
										<label for="radio_FCswitch">Switch</label>
									</td>
								</tr>
								
						</table>
						</form>
					</fieldset>
				</div>
				<div class="floatleft hardware_setting_img" style="width: 720px;">
					<img src="images/ucsb_lg_600x480sm.jpg" alt="UCS ADA Setting">
				</div>
			</div>
	</body>
</html>
