<%-- license.jsp --%>
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
<title>UCS ADA-Upload License File</title>

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

<script type="text/javascript">
	dojo.require("xwt.xwt");
	dojo.require("dojo.parser");
	dojo.require("xwt.widget.form.TextButton");
	dojo.require("xwt.widget.CommonUtilities");
	dojo.require("xwt.widget.layout.XwtContentPane");
	dojo.require("xwt.widget._ConfigureTheme");
	dojo.require('xwt.widget.notification.Alert');
	 dojo.require("dijit.form.TextBox");
	
	dojo.ready(function(){
				
		document.getElementById("uploadBtn").onchange = function() {
			document.getElementById("uploadFile").value = this.value;
		};
	});
	
	function validate(){
		var regEx = /\.(txt|lic|TXT|LIC)$/i;
		
		if(document.getElementById("uploadBtn").value==""){
			var formDlg1 = new xwt.widget.notification.Alert({
				messageType: "critical",
				buttons: [{
					label: "OK"
				}]
			});
			var errorMsg = "Please select license file. Before clicking on Upload.";
			formDlg1.setDialogContent(errorMsg);
			return false;
		}
		if(!regEx.test(document.getElementById("uploadBtn").value)){
			var formDlg1 = new xwt.widget.notification.Alert({
				messageType: "critical",
				buttons: [{
					label: "OK"
				}]
			});
			var errorMsg = "File type is not valid. Kindly use the license file downloaded from portal.";
			formDlg1.setDialogContent(errorMsg);
			return false;
		}
		
		return true; 
	}
	
	var errorMessage = '${errorMessage}';
	dojo.addOnLoad(function() {
		if(errorMessage && errorMessage != "") {
			if(dojo.isIE) {
				showStandby("");
				alert(errorMessage);
				hideStandby();
			} else {
				new xwt.widget.notification.Alert({
		 			messageType: "critical",
	   	 		buttons: [{
	   	 			label: "OK",
	   	 			baseClass: "defaultButton",
	   	 		}]
		 		}).setDialogContent(errorMessage);
			}
		}
	});
	
</script>
</head>
<body class="prime">
	<div dojoType="xwt.widget.layout.XwtContentPane" href="header.html"></div>
	<form action="licenseUpload.html" method="post" enctype="multipart/form-data" onSubmit="return validate();">
		<div id="contentArea" style="margin-top: 100px;">
			<fieldset style="display: table;margin: 0 auto;width: 1000px;">
				<legend>Enter License Key To Activate:</legend>
				
				<div style="color: #464646; display: table; font-family: Arial; font-size: 14px; margin: 30px auto 0;width: 900px;">
					<div style="float: left;height: 50px; color: red;">*</div>
				   <span>This tool guides you in configuring UCS device, mainly in green field deployments.
				   To activate and use this tool, select and upload license file. The license
				   file is  generated at the Unified Service portal when the customer engagement information
				   is entered. If you do not have a license file, contact <a href="mailto: dcv-support@cisco.com">dcv-support@cisco.com</a> 
				   or visit <a href="http://iwe.cisco.com/web/services-analytics-solutions-group/dcaf" target="_blank">webpage</a> for more information.</span>
				</div>
				<br />
				<div class="upload_innerDiv">
					<div style="display: inline; float: left">
						<span class="browselable">File:</span>
						 <input name="uploadFile" id="uploadFile" placeholder="Choose File" class="upload_file" readonly="readonly" />
					</div>
					<div class="fileUpload btn btn-default"
						style="display: inline; float: left; margin-top: 0;">
						<span>Browse</span> 
						<input id="uploadBtn" type="file" class="upload" name="upload_license" />
					</div>
					<div style="display: inline; float: left">
						<button type="submit" id="createDevice0a" dojoType="xwt.widget.form.TextButton">Upload</button>
					</div>
				</div>
			</fieldset>
		</div>
	</form>
	<div dojoType="xwt.widget.layout.XwtContentPane"
		style="margin-top: 5px;" style="height:100px;" href="footer.html">
	</div>
</body>
</html>
