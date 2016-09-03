<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%
			response.setHeader("Cache-Control", "private"); 
			response.setHeader("Pragma", "private"); 
			response.setDateHeader("Expires", System.currentTimeMillis() + 2629000L);
 
          %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>UCS ADA</title>
		
		<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-base.css"/>
		<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-xwt.css"/>
		<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-explorer.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		
	<script type="text/javascript">
		document.cookie="currentProject="+${activeProjectId};
		var djConfig = {
					parseOnLoad : true
					};

		</script>
		<script type="text/javascript" src="/xwt_bundle/dojo/dojo.js"></script>
		<script type="text/javascript" src="js/constants.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/formatter.js"></script>
	<script type="text/javascript">

		require(["xwt/xwt","dojo/parser", 
					'dojo/_base/declare',
					"dojo/on", 
					"dojo/dom-class",
					"dijit/registry",
					"dojo/aspect", 
				 "dojo/domReady!"], function (xwt,parser, declare, on, domClass, dijit,aspect) {
		});
</script>
<script type="text/javascript" src="js/PDIWizard.js"></script>
<script type="text/javascript">		
		var getWizardStatusValue = ajaxCallGet("getWizardStatus.html", true, "json");
		var getWizardStatusValueJsonObj = JSON.parse(getWizardStatusValue);
		console.log(getWizardStatusValueJsonObj);
		var infrastructureResponse = ajaxCallGet("getInfrastructureDetails.html", true, "json");
		var infrastructureParsedResult;
		if(infrastructureResponse != ""){
			var infrastructureParsedResult = JSON.parse(infrastructureResponse);
		}
		
		/*ORG script*/
		var getAllOrganization = ajaxCallGet("getOrganizations.html", true, "json");
		try{
			var adminOrgStoreData = {idenifier: "id",items:JSON.parse("[" + getAllOrganization + "]")};
			var adminOrgDropDown = {items:JSON.parse("[" + getAllOrganization + "]")};
		}catch(e){
			var adminOrgStoreData = {idenifier: "id", items:[] };
			var adminOrgDropDown = "";
		}
		
		var currentSubTaskIndex = -1;
		var IndexOfLeftNav = [1,2,3,4,5,6];
		var TNData = {
				titleText: "Branch Roll-out",
				showTitle: false,
				showMiniNav: false,
				showArrows:true,
				tasks: [
					{
						taskId: "infrastructure",
						iconClass: "myownClass",
						titleText: "<b>Infrastructure</b>",
						selected: true,
						lastEnabled: true, 
						href: "infrastructure.html"
					},
					{
						taskId: "admin",
						iconClass: "myownClass",
						titleText: "<b>Admin</b>",
						href: "adminMain.html"
					},
					{
						taskId: "equipment",
						iconClass: "myownClass",
						titleText: "<b>Equipment</b>",
						href: "equipment.html"
					},
					{
						taskId: "lan",
						iconClass: "myownClass",
						titleText: "<b>LAN</b>",
						href: "lan.html"
					},
					{
						taskId: "san",
						iconClass: "myownClass",
						titleText: "<b>SAN</b>",
						href: "san.html"
					},
					{	
						taskId: "servers",
						iconClass: "myownClass",
						titleText: "<b>Servers</b>",
						href: "servers.html"
					},
					{	
						taskId: "configure",
						iconClass: "myownClass",
						titleText: "<b>Configure</b>",
						href: "configure.html"
					}
				]
			};
		
	dojo.ready(function(){
		
		dojo.forEach(getWizardStatusValueJsonObj.hasCompletedMenuIndex,function(val,i){
			var task = dijit.byId("taskNav").get("tasks")[val];
			if(task){
				task.set("progress", "completed");
			}
		});
		
		dijit.byId("taskNav")._tn.set("lastEnabledTask", (getWizardStatusValueJsonObj.hasCompletedMenuIndex.length>6)?6:getWizardStatusValueJsonObj.hasCompletedMenuIndex.length);
		dijit.byId("taskNav")._tn.set("selectedTask", getWizardStatusValueJsonObj.activeStateMenuIndex);//
		dijit.byId("taskNav")._setPane(getWizardStatusValueJsonObj.activeStateMenuIndex);
		//alert(dijit.byId("taskNav")._tn.get("tasks")[dijit.byId("taskNav")._tn.get("selectedTask")].taskId);
		
		changeButtonAccordingToTaskNav(dijit.byId("taskNav")._tn.get("tasks")[dijit.byId("taskNav")._tn.get("selectedTask")].taskId);
		
		dojo.connect(dijit.byId("taskNav"),"beforeNext",function(taskNavObj){
				
			var index = taskNavObj._tn.get("selectedTask");
			var task = taskNavObj._tn.get("tasks")[index];
			var skipSan = false;
			var taskName = task.taskId;
			changeButtonAccordingToTaskNav(taskName);
			
			if(saveDataForEveryNavBeforeNext(taskName)){
				if(taskName == "san"){
					skipSan = (dijit.byId("skipSanConfigDataBtn").get("value") == 1)?true:false;
					if(skipSan == true) {
						updateWizardForSkipSan();
					}
				}

				if(taskNavObj.leftNavIndex.indexOf(taskNavObj._tn.get("selectedTask")) > -1 && skipSan == false){
					//according to naming convention
					var currentTaskTabContainer = dijit.byId("mainTabContainer_"+taskName);
					var maxNumberOfLeftNavs = currentTaskTabContainer.getChildren().length;		
					var currentSelectedLeftNavTab = currentTaskTabContainer.get('selectedChildWidget');
					var indexOfCurrentSelectedLeftNavTab = currentTaskTabContainer.getIndexOfChild(currentSelectedLeftNavTab);
					
					addIconClassOnLeftTab(taskName+"Tab"+indexOfCurrentSelectedLeftNavTab);
					addLeftTabDisabledTrueOrFalse(taskName+"Tab"+( indexOfCurrentSelectedLeftNavTab + 1),false);
					
					if(indexOfCurrentSelectedLeftNavTab < (maxNumberOfLeftNavs - 1)){
						var nextLeftTab = dijit.byId(taskName+"Tab"+ (++indexOfCurrentSelectedLeftNavTab));
						currentTaskTabContainer.selectChild(nextLeftTab);
						return false;
					}
				}
				
				refreshContantpaneWhileTaskChange(taskNavObj.get("tasks")[taskNavObj._tn.get("selectedTask") + 1].taskId); // refresh task subTab while next
				return true;
			}else{
				//displayNotificationAlert("Error while saving the data for this stage");
				return false;
			}
		});
	
		// Data need to fetch while taskChange
		dojo.connect(dijit.byId("taskNav"),"_onTaskChange",function(index){
				refreshContantpaneWhileTaskChange(this.get("tasks")[index].taskId);
		});
		
		// Functionality for save and Exit.
		dojo.connect(dijit.byId("taskNav"),"onCancel",function(){
			var index = this._tn.get("selectedTask");
			var task = this._tn.get("tasks")[index];
			var taskName = task.taskId;
			if(saveDataForEveryNavBeforeNext(taskName)){
				window.location.href = 'listProjects.html';
				//return true;
			}
		});
	
		if(infrastructureResponse != ""){
			generateBitMapForModelNumber(infrastructureParsedResult.serverModel);
		}
	});
	
	function updateWizardForSkipSan() {
		ajaxCallGet("skipSanUpdateWizardStatus.html", true, "text");
		var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":0});
		ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	}
		
	</script>
		
	</head>
	<body class="prime" id="bodyUniqueIdForStandBy">

	<div dojoType="xwt/widget/layout/XwtContentPane" href="header.html"></div>
	<div style="height: 30px;"></div>
	<div id="contentArea" style="height: 300px">
	
		<div class="wizardWidth" style="position: relative;margin: auto;">
			<div style="position: absolute;z-index: 1;top: 27px;left: 30px;font-weight: bold;" class="icon"><a class="display-icon fi-home" href="listProjects.html"></a><p>Home: <span style="color:#5EB9E7;">${activeProjectName}</span></p></div>
			<div id="taskNav" dojoType="xwt/widget/tasknavigator/PDIWizard" config="TNData" leftNavIndex="IndexOfLeftNav" class="wizardWidth">
		</div>
			
		</div>
	</div>
	
	<div dojoType="xwt/widget/layout/XwtContentPane" href="footer.html"></div>
	
	</body>
</html>