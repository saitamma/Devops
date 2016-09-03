<%-- error.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- *******************************************************************
	*        Copyright (c) 2012 Cisco Systems, Inc.
	*        All rights reserved.
	******************************************************************** -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<title>UCS ADA-Error</title>

<link rel="stylesheet" type="text/css"
	href="/xwt_bundle/xwt/themes/prime/prime-base.css" />
<link rel="stylesheet" type="text/css"
	href="/xwt_bundle/xwt/themes/prime/prime-xwt.css" />
<link rel="stylesheet" type="text/css"
	href="/xwt_bundle/xwt/themes/prime/prime-explorer.css" />

<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
body {
	margin: 10px;
}

.prime .dijitEditorIcon {
	height: 19px;
	width: 14px;
}
</style>

<script type="text/javascript">
	var djConfig = {
		parseOnLoad : true
	};
</script>

<script type="text/javascript" src="/xwt_bundle/dojo/dojo.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script type="text/javascript">
	dojo.require("xwt.xwt");
	dojo.require("dojo.parser");
	dojo.require("xwt.widget.CommonUtilities");
	dojo.require("xwt.widget.layout.XwtContentPane");
	dojo.require("xwt.widget._ConfigureTheme");
	
</script>
</head>
<body class="prime">
	<div dojoType="xwt.widget.layout.XwtContentPane" href="header.html"></div>
		<div id="contentArea" style="margin-top: 100px;">
			<fieldset>
				<legend>Error!</legend>
				<div class="upload_innerDiv">
					<div style="display: inline; float: left">
						<span class="browselable">Oops!! UCS ADA tool has run into a problem. Please contact administrator at <a href="mailto:dcv-support@cisco.com">dcv-support@cisco.com</a></span>
					</div>
				</div>
			</fieldset>
		</div>
	<div dojoType="xwt.widget.layout.XwtContentPane"
		style="margin-top: 5px;" style="height:100px;" href="footer.html">
	</div>
</body>
</html>
