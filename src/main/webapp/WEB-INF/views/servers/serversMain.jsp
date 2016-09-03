<%-- serversMain.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
		<script type="text/javascript">
		dojo.ready(function(){
			setTimeout(function(){
				var serversSubNav = 10;
				var hasCompletedMenu = getWizardStatusValueJsonObj.hasCompletedMenuIndex;
				var hasLastVisitSubTeb = getWizardStatusValueJsonObj.hasCompletedSubMenuIndex;
				var activeTaskNav = getWizardStatusValueJsonObj.activeStateMenuIndex;
				var activeLeftNav =	getWizardStatusValueJsonObj.activeStateSubMenuIndex;
				
				if(activeTaskNav == 5){
					var equipTabContainer = dijit.byId("mainTabContainer_servers");
					equipTabContainer.selectChild("serversTab"+activeLeftNav);
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= activeLeftNav ; i++){
							addIconClassOnLeftTab("serversTab" + i);
							addLeftTabDisabledTrueOrFalse("serversTab" + i,false);
						}
					}
				}else{
					if(activeTaskNav > 5){
						for(i = 0 ; i < serversSubNav ; i++){
							addIconClassOnLeftTab("serversTab" + i);
							addLeftTabDisabledTrueOrFalse("serversTab" + i,false);
						}
					}
				}
			
				if(hasCompletedMenu.length > 5 ){
					for(i = 0 ; i < serversSubNav ; i++){
						addIconClassOnLeftTab("serversTab" + i);
						addLeftTabDisabledTrueOrFalse("serversTab" + i,false);
					}
				}
				
				//sratring from 0-5
				if(hasCompletedMenu.length == 5 && hasLastVisitSubTeb != null){
					var equipTabContainer = dijit.byId("mainTabContainer_servers");
					equipTabContainer.selectChild("serversTab"+hasLastVisitSubTeb );
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= hasLastVisitSubTeb; i++){
							addIconClassOnLeftTab("serversTab" + i);
							addLeftTabDisabledTrueOrFalse("serversTab" + i,false);
						}
					}
				}
			
			},300);
		});
	</script>
    </head>
    <body>
		<div class="contentarea" style="margin-top: 1rem; padding-left: 0.4rem;">
			<div id="mainTabContainer_servers" dojoType="xwt.widget.layout.XwtTabContainer" persist="false" class="MainTabWidthHeight" tabPosition="left">
				<div id="serversTab0"  iconClass="fi-checkTrans" disabled="false" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversUUIDPoolConfig.html" tooltip="UUID Pool Configurations" title="<b>UUID Pool</b>" selected="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab1"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversBootPolicyConfig.html" tooltip="Boot Policy Configurations" title="<b>Boot Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab2"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversLocalDiskPolicyConfig.html" tooltip="Local Disk Policy Configurations" title="<b>Local Disk Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab3"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversServerPoolAndQualifierConfig.html" tooltip="Server Pool &amp; Qualifier Configurations" title="<b>Server Pool &amp; Qualifier</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab4"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversServerPoolPolicyConfig.html" tooltip="Server Pool Policy Configurations" title="<b>Server Pool Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab5"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversHostFirmware.html" tooltip="Host Firmware Configurations" title="<b>Host Firmware</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab6"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversBiosPolicyConfig.html" tooltip="BIOS Policy Configurations" title="<b>BIOS Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab7"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversMaintenancePolicyConfig.html" tooltip="Maintenance Policy Configurations" title="<b>Maintenance Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab8"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversServiceTempConfig.html" tooltip="Service Profile Template Configurations" title="<b>Service Profile Template</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<div id="serversTab9"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="serversServiceProfileConfig.html" tooltip="Service Profile" title="<b>Service Profile</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
			</div>
		</div>
	</body>
</html>
