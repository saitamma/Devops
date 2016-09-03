<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/xwt_bundle/dojo/dojo.js"></script>
<script type="text/javascript">
dojo.ready(function(){
	var projectId = '${projectId}';
	var projectName = '${projectName}';
	 document.getElementById("submitProjectId").value = projectId;
	document.getElementById("submitProjectName").value = projectName;
	document.getElementById("submitProjectFormToWizard").submit();
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redirecting to wizard</title>
</head>
<body>
<div id="actionFormForWizardSubmit">
		<form id="submitProjectFormToWizard" action="wizard.html" name="submitFormToWizard" method="post">
			<input id="submitProjectId" name="projectId" type="hidden"></input>
			<input id="submitProjectName" name="projectName" type="hidden"></input>
		</form>
	</div>
</body>
</html>