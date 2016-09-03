<%-- login.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- *******************************************************************
	*        Copyright (c) 2012 Cisco Systems, Inc.
	*        All rights reserved.
	******************************************************************** -->
<html>
    <head>
    <%
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <!--
        Readme
        Prototype for  Loginpage widget.
        -->
        <title>UCS ADA - Login</title>
        <!-- required: XWT Theme: -->
		<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-base.css"/>
		<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-xwt.css"/>

		<style type="text/css">
			.xwtProductName {
				font-size: 3.2rem !important;
			}
		</style>
		<script type="text/javascript">
				djConfig = {
					parseOnLoad: true
				};
		</script>
       <script type="text/javascript" src="/xwt_bundle/dojo/dojo.js"></script>
        <script type="text/javascript" src="/xwt_bundle/xwt/widget/LoginPage.js"></script>
        <script type="text/javascript">
                dojo.require("dojo.parser");
                dojo.require("xwt.widget.LoginPage");
                
                var supportedBrowser = {
					'FF': [36,37],
					'Chrome' : [41,42]
				};
               
                dojo.addOnLoad( function() {
                    var loginPage = dijit.byId("loginPage");
                    loginPage.passwordAP.focus();
    				loginPage.usernameAP.focus();

                  
                    dojo.connect(loginPage, 'onClickSubmit', function(){
                    	document.loginForm.j_username.value = loginPage.getUsername();
    					document.loginForm.j_password.value = loginPage.getPassword();

    					dojo.xhrPost({
    						// read the url: from the action="" of the <form>
    						form: document.loginForm,
    						 timeout: 10000, // give up after 3 seconds
    						sync: true,
    						// creates ?part=one&another=part with GET, Sent as POST data when using xhrPost
    						content: {
    							j_username: loginPage.getUsername(),
    							j_password: loginPage.getPassword()
    						},
    						load: function (data, ioArgs) {
    							// ioArgs is loaded with XHR information, but not useful in simple cases
    							// data is the response from the form's action="" url
    							//we can either write the response back to the same page as below
    								//document.write(data);
    							//or redirect to the index page as below
    								
    								window.location.href= "listProjects.html";
    								
								
    						},
    						error: function (err, ioArgs) {
    							if(err.status && err.status == 403) {
    								loginPage.displayMessageByID("invalid_credentials");
    								loginPage.stopLoadingIndicator();
    							} else {
    								loginPage.showMessageBox("Server returned error -> " + err);
    							}
    						}
    					});
                    });
                });
    			
        </script>
        
    </head>
    <body class="reboot2 prime" style="background:#FFFFFF;">
		<form name="loginForm" action="j_spring_security_check" method="POST" >
			<div id="loginPage" dojoType="xwt.widget.LoginPage" problemLoginURL="test_Search.html" 
				showProblemsLink="false" 
				ciscoLogoUrlHref="/UCS_ADA"
				i18nPackageName="xwt.widget.tests" 
				useBuiltin_PasswordResetDialog="true" 
				productFamily="UCS"
				productName="Accelerated Deployment Assistant"
				productVersion="Version: 3.0 Sprint 3" 
				showRememberMe="false" 
				i18nBundleName="ApplicationProperties"
				supportedBrowserList="supportedBrowser"
				copyrightLabel="© 2015 Cisco Systems, Inc. Cisco, Cisco Systems and Cisco Systems logo are registered trademarks of Cisco Systems, Inc. and/or its affiliates in the U.S. and certain other countries">
			</div>
			<input name="j_username" type="hidden"/>
			<input name="j_password" type="hidden"/>
		</form>
    </body>
</html>
