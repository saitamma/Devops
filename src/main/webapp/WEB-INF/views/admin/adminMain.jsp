<%-- adminMain.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
		<script type="text/javascript">
		dojo.ready(function(){
			setTimeout(function(){
				var adminSubNav = 6;
				var hasCompletedMenu = getWizardStatusValueJsonObj.hasCompletedMenuIndex;
				var hasLastVisitSubTeb = getWizardStatusValueJsonObj.hasCompletedSubMenuIndex;
				var activeTaskNav = getWizardStatusValueJsonObj.activeStateMenuIndex;
				var activeLeftNav =	getWizardStatusValueJsonObj.activeStateSubMenuIndex;
				
				if(activeTaskNav == 1){
					var equipTabContainer = dijit.byId("mainTabContainer_admin");
					equipTabContainer.selectChild("adminTab"+activeLeftNav);
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= activeLeftNav ; i++){
							addIconClassOnLeftTab("adminTab" + i);
							addLeftTabDisabledTrueOrFalse("adminTab" + i,false);
						}
					}
				}else{
					if(activeTaskNav > 1){
						for(i = 0 ; i < adminSubNav ; i++){
							addIconClassOnLeftTab("adminTab" + i);
							addLeftTabDisabledTrueOrFalse("adminTab" + i,false);
						}
					}
				}
			
				if(hasCompletedMenu.length > 1 ){
					for(i = 0 ; i < adminSubNav ; i++){
						addIconClassOnLeftTab("adminTab" + i);
						addLeftTabDisabledTrueOrFalse("adminTab" + i,false);
					}
				}
				
				//sratring from 0-5
				if(hasCompletedMenu.length == 1 && hasLastVisitSubTeb != null){
					var equipTabContainer = dijit.byId("mainTabContainer_admin");
					equipTabContainer.selectChild("adminTab"+hasLastVisitSubTeb );
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= hasLastVisitSubTeb; i++){
							addIconClassOnLeftTab("adminTab" + i);
							addLeftTabDisabledTrueOrFalse("adminTab" + i,false);
						}
					}
				}
				
			},300);
		});
	</script>
    </head>
    <body>
    		<!-- <hr class="topLine"> -->
				<div class="contentarea" style="margin-top: 1rem; padding-left: 0.4rem;">
					<div id="mainTabContainer_admin" dojoType="xwt.widget.layout.XwtTabContainer" persist="false" class="MainTabWidthHeight" tabPosition="left">
						
						<div id="adminTab0"  iconClass="fi-checkTrans" disabled="false" dojoType="dojox.layout.ContentPane" href="adminSetting.html" tooltip="Admin Setting" title="<b>Admin Setting</b>" selected="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="adminTab1"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="adminAuthentication.html" tooltip="Authentication" title="<b>Authentication</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="adminTab2"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="adminLdapGroupMaps.html" tooltip="Admin Ldap Group Maps" title="<b>Group Maps</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="adminTab3"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="adminCallHomeSetting.html" tooltip="Call Home Setting" title="<b>Call Home Setting</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="adminTab4"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="adminCallHome.html" tooltip="Call Home Profile & Policy" title="<b>Call Home</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="adminTab5"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="adminBackupConfiguration.html" tooltip="Backup Configuration" title="<b>Backup Config</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
					</div>
				</div>
	</body>
</html>
