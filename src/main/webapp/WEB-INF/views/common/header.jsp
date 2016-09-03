<%-- header.jsp --%>
<script>
var userCec = '${userCec}';
var activeUserID = '${activeUserID}';
var userRole = '${userRole}';
</script>
<script type="text/javascript" src="js/header.js"></script>
<script type="text/javascript">

var headerLayer = [ "xwt/xwt" ];
require(headerLayer, function() {
	require(["dojo/parser",
	         "dojo/aspect",
	         "dojo/ready",
	         "dijit/registry",
	        "xwt/widget/AboutPage",
	        "xwt/widget/uishell/Header",
	        "dojo/domReady!"
	        ],
		function(parser, aspect, ready, registry, AboutPage, Header) {
		// business logic goes here, we may simply instantiate component object here.
		// var aboutPage = aboutPage = new AboutPage();
		});
});
	var aboutPage ;
	var getProperty = JSON.parse(ajaxCallGet("getPDIProperties.html", true, "json"));
	dojo.ready(function () {
		aboutPage = new xwt.widget.AboutPage({
			i18nPackageName: "xwt.widget.tests",
			i18nBundleName: "ApplicationProperties",
			applicationName: "UCS",
			applicationSubtitle : "Accelerated Deployment Assistant",
			versionNumber: "Version: "+getProperty.pdiVersion,
			additionalInfo: {
				items: [ {
					key: "Release Type:",
					value: getProperty.pdiReleaseType
				},{
					key: "Release Date:",
					value: getProperty.pdiReleaseDate
				}, {
					key: "Support:",
					value: "dcv-support@cisco.com"
				}]
			},
			logoImagealt: "applicationLogoImageAlt",
			copyrightInfo: "© 2015 Cisco Systems, Inc. All rights reserved.",
			targetNode: "aboutTarget"
		}, dojo.byId("aboutW"));
		aboutPage.startup();
	
		setTimeout(function(){
			dojo.query("#header .headerLeft a").onclick(function(e){
				dojo.stopEvent(e);
				window.location.href = location.protocol+"//"+window.location.host;
			});
		},1000)
		
	
	});

	function about(){
		aboutPage.show();
	}
/* function checkNAndCalluserMgmtPage(){
	if(userRole == "admin" || userRole == "ucsada_admin"){
		window.location.href = 'userManagement.html';
	}else{
		displayNotificationAlert("You are not authorize to access this link.");
	}
} */
	</script>

<div dojoType="xwt/widget/uishell/Header"
		id = "header"
		ciscoPrime="true"
		requireDomain="false"
		applicationTitle="UCS Accelerated Deployment Assistant"
		i18nPackageName="xwt" productFamilyName="UCS ADA" i18nBundleName="XWTProperties"
		headerItems="headerItems" headerLogoURL="/"
		headerAppNameURL="/"
		requireSettingsMenu="true" menuItems="headerItems"  disableGlobalNavigation="true" ></div>
		
		<div id="aboutW"></div>