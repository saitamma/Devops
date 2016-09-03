<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>access Denied</title>
<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-base.css" />
<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-xwt.css" />
<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-explorer.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script type="text/javascript">
	var djConfig = {
		parseOnLoad : true
	};

</script>
<script type="text/javascript" src="/xwt_bundle/dojo/dojo.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script type="text/javascript">
require(["xwt/xwt","dojo/parser", 
			'dojo/_base/declare',
			"dojo/on", 
			"dojo/aspect",
			"dojo/dom-class",
			"dijit/registry",
			"dojo/domReady!",
		], function (xwt,parser, declare, on, aspect, domClass, dijit,ready) {
	
});
</script>
</head>
<body class="prime">
<div dojoType="xwt/widget/layout/XwtContentPane" href="header.html"></div>
<div style="height: 150px;"></div>
<div id="accessDeniedContainer">
		<div class="accessDenied"><div class="accessDeniedHeadIn">Access Denied</div></div>
		<div class="accessDeniedInner">
			You do not have permission to access UCS ADA. Please contact <a href="mailto:cstge-ucs-pdi-dev@cisco.com?subject=UCS ADA Access Request from user id" onclick="location.href=this.href+' <'+userCec+'>';return false;">cstge-ucs-pdi-dev@cisco.com</a>
		</div>
</div>
<div dojoType="xwt/widget/layout/XwtContentPane" href="footer.html"></div>
</body>
</html>
</html>