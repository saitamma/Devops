<%-- sanMain.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
	<script type="text/javascript">
	dojo.require("dijit.form.CheckBox");
	
		var getProjectDetails = JSON.parse("["+ ajaxCallGet("fetchCurrentProjectDetails.html", true, "json") +"]");

		dojo.ready(function(){
			setTimeout(function(){
				if(getProjectDetails.length > 0 && getProjectDetails[0].skipSan == true ){
					dijit.byId("skipSanConfigDataBtn").set("checked",true);
				}
				
				dojo.connect(dijit.byId("skipSanConfigDataBtn"),"onClick",function(){
					if(dijit.byId("skipSanConfigDataBtn").get("value")){
						dijit.byId("skipSanConfigDataBtn").set("checked",false);
						displayConfirmationDialog(MSG_SKIP_SAN,skipSanDeleteExistingSAN);
					}
					else{
						saveSkipSanStatus();
					}
				});
				
				var sanSubNav = 6;
				
				var hasCompletedMenu = getWizardStatusValueJsonObj.hasCompletedMenuIndex;
				var hasLastVisitSubTeb = getWizardStatusValueJsonObj.hasCompletedSubMenuIndex;
				var activeTaskNav = getWizardStatusValueJsonObj.activeStateMenuIndex;
				var activeLeftNav =	getWizardStatusValueJsonObj.activeStateSubMenuIndex;
				
				if(activeTaskNav == 4){
					var equipTabContainer = dijit.byId("mainTabContainer_san");
					equipTabContainer.selectChild("sanTab"+activeLeftNav);
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= activeLeftNav ; i++){
							addIconClassOnLeftTab("sanTab" + i);
							addLeftTabDisabledTrueOrFalse("sanTab" + i,false);
						}
					}
				}else{
					if(activeTaskNav > 4){
						for(i = 0 ; i < sanSubNav ; i++){
							addIconClassOnLeftTab("sanTab" + i);
							addLeftTabDisabledTrueOrFalse("sanTab" + i,false);
						}
					}
				}
				
				if(hasCompletedMenu.length > 4 ){
					for(i = 0 ; i < sanSubNav ; i++){
						addIconClassOnLeftTab("sanTab" + i);
						addLeftTabDisabledTrueOrFalse("sanTab" + i,false);
					}
				}
				
				//sratring from 0-4
				if(hasCompletedMenu.length == 4 && hasLastVisitSubTeb != null){
					var equipTabContainer = dijit.byId("mainTabContainer_san");
					equipTabContainer.selectChild("sanTab"+hasLastVisitSubTeb );
					
					if(activeLeftNav > -1){
						for(i = 0 ; i <= hasLastVisitSubTeb; i++){
							addIconClassOnLeftTab("sanTab" + i);
							addLeftTabDisabledTrueOrFalse("sanTab" + i,false);
						}
					}
				}
				
				dojo.connect(dijit.byId("sanTab0"),"onShow",function(){
					 dijit.byId("sanTab0").refresh();
				 });
				 dojo.connect(dijit.byId("sanTab1"),"onShow",function(){
					 dijit.byId("sanTab1").refresh();
				 });
				 dojo.connect(dijit.byId("sanTab2"),"onShow",function(){
					 dijit.byId("sanTab2").refresh();
				 });
				 dojo.connect(dijit.byId("sanTab3"),"onShow",function(){
					 dijit.byId("sanTab3").refresh();
				 });
				dojo.connect(dijit.byId("sanTab4"),"onShow",function(){
					 dijit.byId("sanTab4").refresh();
				 });
				dojo.connect(dijit.byId("sanTab5"),"onShow",function(){
					 dijit.byId("sanTab5").refresh();
				 });
				// leftTabAddProperties("mainTabContainer_san");
			
			},300);
		});
		
	function saveSkipSanStatus(){
		var projectId = ${activeProjectId};
		if(dijit.byId("skipSanConfigDataBtn").get("value") == 1){
			var SkipsanArr = {id:projectId,skipSan:1};
		}
		else{
			var SkipsanArr = {id:projectId,skipSan:0};
		}
		response = ajaxCallPostWithJsonContent("updateSkipSanDetail.html" , JSON.stringify(SkipsanArr), null, "text");
	}
	
	function skipSanDeleteExistingSAN(){
		dijit.byId("skipSanConfigDataBtn").set("checked",true);
		saveSkipSanStatus();
		var response = ajaxCallGet("deleteAllSANConfigDetails.html", true, "text");
		if(response == 'success'){
			console.log(dojo.byId("nextButtonForWizard"));
			document.getElementById("nextButtonForWizard").click();
		}
	}
	
	</script>
    </head>
    <body>
	 	<div style="position: absolute; right: 52px;top:15px;">
			<input id="skipSanConfigDataBtn" dojoType="dijit.form.CheckBox" value="1" />
			<label for="skipSanConfigDataBtn" style="margin-right:30px;">Skip SAN Configuration</label>
		</div>
		<div class="contentarea" style="margin-top: 1rem; padding-left: 0.4rem;">
			<div id="mainTabContainer_san" dojoType="xwt.widget.layout.XwtTabContainer" persist="false" class="MainTabWidthHeight" tabPosition="left">
				<div id="sanTab0" iconClass="fi-checkTrans" disabled="false" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="sanVsanConfig.html" tooltip="VSAN Configurations" title="<b>VSAN</b>" selected="true" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" ></div>
				<div id="sanTab1" iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="sanWwxNConfig.html" tooltip="WWxN Configurations" title="<b>WWxN</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" ></div>
				<div id="sanTab2" iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="sanAdapterPoliciesConfig.html" tooltip="Fibre Channel Adapter Policy Configurations" title="<b>FC Adapter Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" ></div>
				<div id="sanTab3" iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="sanVHBATempConfig.html" tooltip="vHBA Template Configurations" title="<b>vHBA Template</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" ></div>
				<div id="sanTab4" iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="sanVHBAConfig.html" tooltip="vHBA Configurations" title="<b>vHBA</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" ></div>
				<div id="sanTab5" iconClass="fi-checkTrans" disabled="true" refreshOnShow="true" dojoType="dojox.layout.ContentPane" href="sanConnectivityPolicy.html" tooltip="Connectivity Policy Configurations" title="<b>Connectivity Policy</b>" ioArgs="{error : function(error, ioargs) {dealWithError(error, ioargs);}}" ></div>
			</div>
		</div>
	</body>
</html>  
