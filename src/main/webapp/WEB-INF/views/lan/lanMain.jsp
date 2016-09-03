<%-- lanMain.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
			<script type="text/javascript">
		dojo.ready(function(){
			setTimeout(function(){
				var lanSubNav = 9;  
				
				var hasCompletedMenu = getWizardStatusValueJsonObj.hasCompletedMenuIndex;
				var hasLastVisitSubTeb = getWizardStatusValueJsonObj.hasCompletedSubMenuIndex;
				var activeTaskNav = getWizardStatusValueJsonObj.activeStateMenuIndex;
				var activeLeftNav =	getWizardStatusValueJsonObj.activeStateSubMenuIndex;

				if(activeTaskNav == 3){
					var equipTabContainer = dijit.byId("mainTabContainer_lan");
					equipTabContainer.selectChild("lanTab"+activeLeftNav);
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= activeLeftNav ; i++){
							addIconClassOnLeftTab("lanTab" + i);
							addLeftTabDisabledTrueOrFalse("lanTab" + i,false);
						}
					}
				}else{
					if(activeTaskNav > 3 ){
						for(i = 0 ; i < lanSubNav ; i++){
							addIconClassOnLeftTab("lanTab" + i);
							addLeftTabDisabledTrueOrFalse("lanTab" + i,false);
						}
					}
				}
				
				if(hasCompletedMenu.length > 3 ){
					for(i = 0 ; i < lanSubNav ; i++){
						addIconClassOnLeftTab("lanTab" + i);
						addLeftTabDisabledTrueOrFalse("lanTab" + i,false);
					}
				}
				
				//sratring from 0-3
				if(hasCompletedMenu.length == 3 && hasLastVisitSubTeb != null){
					var equipTabContainer = dijit.byId("mainTabContainer_lan");
					equipTabContainer.selectChild("lanTab"+hasLastVisitSubTeb );
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= hasLastVisitSubTeb; i++){
							addIconClassOnLeftTab("lanTab" + i);
							addLeftTabDisabledTrueOrFalse("lanTab" + i,false);
						}
					}
				}
				 			
			},300);
		 
		});
		  
		</script>
    </head>
    <body>
   		<div class="contentarea" style="margin-top: 1rem; padding-left: 0.4rem;">
				<div id="mainTabContainer_lan" dojoType="xwt.widget.layout.XwtTabContainer" persist="false" class="MainTabWidthHeight" tabPosition="left">
					<div id="lanTab0" iconClass="fi-checkTrans" disabled="false" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanPortChannelConfig.html" tooltip="Port Channel Configurations" title="<b>Port Channel</b>" selected="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab1"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanVlanMacPoolConfig.html" tooltip="VLAN & MAC-Pool Configurations" title="<b>VLAN & MAC-Pool</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab2"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanNetworkControlPolicy.html" tooltip="Network Control Policy Configurations" title="<b>N/W Control Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab3"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanQosPolicy.html" tooltip="QoS Policy Configurations" title="<b>QoS Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab4"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanEthernetAdapterPolicy.html" tooltip="Ethernet Adapter Policy" title="<b>ETH Adapter Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab5"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanVnicTempConfig.html" tooltip="vNIC Template Configurations" title="<b>vNIC Template</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab6"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanVnicConfig.html" tooltip="vNIC Configurations" title="<b>vNIC</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab7"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanConnectivityPolicy.html" tooltip="LAN Connectivity Policy Configurations" title="<b>Connectivity Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>
					<div id="lanTab8"  iconClass="fi-checkTrans" disabled="true" dojoType="dojox.layout.ContentPane" refreshOnShow="true" href="lanMgmtIpPoolConfig.html" tooltip="Mgmt Ip Pool Configurations" title="<b>Mgmt IP Pool</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" >
					</div>															   
				</div>  
			</div>  
	</body>
</html>  
