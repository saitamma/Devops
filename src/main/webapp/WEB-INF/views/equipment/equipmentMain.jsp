<%-- equipmentMain.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
		<script type="text/javascript">

		dojo.ready(function(){
			setTimeout(function(){
				var equipTotalSubNav = 3;
				var hasCompletedMenu = getWizardStatusValueJsonObj.hasCompletedMenuIndex;
				var hasLastVisitSubTeb = getWizardStatusValueJsonObj.hasCompletedSubMenuIndex;
				var activeTaskNav = getWizardStatusValueJsonObj.activeStateMenuIndex;
				var activeLeftNav =	getWizardStatusValueJsonObj.activeStateSubMenuIndex;
				if(activeTaskNav == 2){
					var equipTabContainer = dijit.byId("mainTabContainer_equipment");
					equipTabContainer.selectChild("equipmentTab"+activeLeftNav);
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= activeLeftNav ; i++){
							addIconClassOnLeftTab("equipmentTab" + i);
							addLeftTabDisabledTrueOrFalse("equipmentTab" + i,false);
						}
					}
				}else{
					if(activeTaskNav > 2  || (hasCompletedMenu.length > 3) ){
						for(i = 0 ; i < equipTotalSubNav ; i++){
							addIconClassOnLeftTab("equipmentTab" + i);
							addLeftTabDisabledTrueOrFalse("equipmentTab" + i,false);
						}
					}
				}
				
				if(hasCompletedMenu.length > 2 ){
					for(i = 0 ; i < equipTotalSubNav ; i++){
						addIconClassOnLeftTab("equipmentTab" + i);
						addLeftTabDisabledTrueOrFalse("equipmentTab" + i,false);
					}
				}
				
				//sratring from 0-2
				if(hasCompletedMenu.length == 2 && hasLastVisitSubTeb != null ){
					var equipTabContainer = dijit.byId("mainTabContainer_equipment");
					equipTabContainer.selectChild("equipmentTab"+hasLastVisitSubTeb );
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= hasLastVisitSubTeb; i++){
							addIconClassOnLeftTab("equipmentTab" + i);
							addLeftTabDisabledTrueOrFalse("equipmentTab" + i,false);
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
			<div id="mainTabContainer_equipment" dojoType="xwt.widget.layout.XwtTabContainer" persist="false" class="MainTabWidthHeight" tabPosition="left">
				<div id="equipmentTab0"  iconClass="fi-checkTrans" disabled="false" dojoType="dojox.layout.ContentPane" href="equipmentChasisConfig.html" tooltip="Chassis Policy" title="<b>Chassis Policy</b>" preload="false" selected="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
				</div>
				<c:choose>
					<c:when test="${ISMINISERVERMODEL}">
						<div id="equipmentTab1"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="equipmentMiniServerUplinkConfig.html" tooltip="Configure Server &amp; Uplink Ports" title="<b>Server &amp; Uplink Ports</b>" preload="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="equipmentTab2"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="equipmentMiniScalabilityConfig.html" tooltip="Configure Scalability Ports" title="<b>Scalability Ports</b>" preload="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>					
					</c:when>
					<c:otherwise>
						<div id="equipmentTab1"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="equipmentServerConfig.html" tooltip="Server Ports" title="<b>Server Ports</b>" preload="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
						<div id="equipmentTab2"  iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="equipmentUplinkConfig.html" tooltip="Configure Uplink &amp; FC Ports" title="<b>Uplink &amp; FC Ports</b>" preload="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>
