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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<title>UCS ADA</title>

<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-base.css" />
<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-xwt.css" />
<link rel="stylesheet" type="text/css" href="/xwt_bundle/xwt/themes/prime/prime-explorer.css" />

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
var standByDijitObj;
</script>

<script type="text/javascript" src="/xwt_bundle/dojo/dojo.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/formatter.js"></script>

<script type="text/javascript">
	
	require(["xwt/xwt","dojo/parser", 
				'dojo/_base/declare',
				"dojo/on", 
				"dojo/aspect",
				"dojo/dom-class",
				"dijit/registry",
				"dojo/domReady!"
			], function (xwt,parser, declare, on, aspect, domClass, dijit) {
		
	});

	var resultData = ajaxCallGet("listProjectData.html", true, "json");
	var serverData = "[" + resultData + "]";
	var data = {
		items : JSON.parse(serverData)
	};

	//var table;
	var columns = [ {
		label : 'ID',
		attr : 'id',
		sortable : true,
		sorted : 'ascending',
		width : 100,
		vAlignment : "middle",
		align : 'center',
		locked : true,
		hidden : true
	}, {
		label : 'Name',
		attr : 'projectName',
		width : 340,
		vAlignment : "middle",
		editable : false,
		align : 'center',
		formatter : addHref
	}, {
		label : 'Theatre',
		attr : 'theatre',
		width : 150,
		vAlignment : "middle",
		editable : false,
		align : 'center'
	},{
		label: 'Fabric Interconnect',
		attr : 'fabricInterconnect',
		width : 200,
		vAlignment : "middle",
	},{
		label: 'Software Release',
		attr : 'softwareRelease',
		width : 200,
		vAlignment : "middle",
	},{
		label: 'IO Module',
		attr : 'ioModule',
		width : 150,
		vAlignment : "middle",
	}, {
		label : 'Creation Date',
		attr : 'createdDate',
		width : 150,
		vAlignment : "middle",
		editable : false,
		sortable : true
	}, 
	{
		label : 'Created By',
		attr : 'createdBy',
		width : 100,
		vAlignment : "middle",
		editable : false,
		sortable : true
	}
	];

	function addDownloadButton(data,item,store) {
		return '<button id="downloadProjectDetail_'+item.id[0]+'" title="Download Project Data" class="btn btn-primary" onClick="downloadProjectDetail(event,'+item.id[0]+');" type="button">Download</button>';
	}

	function addHref(data, item, store) {
		return "<a href=\"javascript:;\" onClick=\"sendIntoWizardFunc('"+item.id+"','"+item.projectName+"');\">" + item.projectName + "</a>";
		//return "<a href='wizard.html?projectId=" + item.id + "&projectName=" + item.projectName + "'>" + item.projectName + "</a>";
	}
	function sendIntoWizardFunc(projectId,projectName){
		document.getElementById("submitProjectId").value = projectId;
		document.getElementById("submitProjectName").value = projectName;
		document.getElementById("submitProjectFormToWizard").submit();
	}
	function downloadProjectDetail() {
		if(selectedItem != undefined && selectedItem.id[0] != null){
			var projectId = selectedItem.id[0];
			var isChassisSaved = ajaxCallGet("getEquipmentChassisDetailByProject.html?projectId="+projectId, true, "json");
			if(isChassisSaved){
				standByDijitObj.show();
		        
				setTimeout( function() {
						response = JSON.parse(ajaxCallPostWithJsonContent( "processDownload.html?projectId="+projectId, null, null,"text"));
		
						if (response.Zip_Creation != undefined
								&& response.Zip_Creation == true) {
							var iframe = document.getElementById("downloadFrame");
							iframe.src = "downloadUcsData.html";
						} else {
							displayNotificationAlert(MSG_ERROR_ZIPPING, "critical");
						}
					standByDijitObj.hide();
				}, 1000);
			}else{
				displayNotificationAlert(MSG_CHASSIS_CONF_ISSAVED,"warning");
			}
		}
		
	}
	
	function startTopologyGenaration() {
		if(selectedItem != undefined && selectedItem.id[0] != null){
			var projectId = selectedItem.id[0];								
				standByDijitObj.show();
				
				setTimeout( function() {
					response = JSON.parse(ajaxCallPostWithJsonContent( "processTopologyGeneration.html?projectId="+projectId, null, null,"text"));
					console.log(response);
					if (response.Topology_Status != undefined
							&& response.Topology_Status == "success") {
						var iframe = document.getElementById("downloadFrame");						
						iframe.src = "downloadTopologyData.html";
					} else {						
						console.log(response.Topology_Status);
						displayNotificationAlert(response.Topology_Status, "critical");
					}
				standByDijitObj.hide();
			}, 1000);
		}		
	}
	
	function showDialog(obj) {
		if (obj && obj.id == "editProject") {
			projectid.set('value', selectedItem.id);
			projectName.set('value', selectedItem.projectName);
			projectTheatre.set('value', selectedItem.theatre);
		} else {
			projectForm.reset();
			projectid.set('value', "0");
		}
		dojo.query("#hideShowProjectImport").style("display", "none");
		dojo.query( "#hideShowProjectImportExtraFields").style("display","none");
		projectDialog.set("style", "width:400px;left: 510px;top: 155px;");
		projectDialog.set("title", "Add/Edit Project");
		projectDialog.show();
		setTimeout(function() {
			dijit.byId("projectName").focus();
		}, 500);

	}

	function addNewProject(defItemx) {
		try {
			if (defItemx.id == "0") {
				//new item to be saved to DB
				//change projectid based on db id of the row 
				if( dojo.query( "#hideShowProjectImport").style("display") != "none" ){
					projectDialog.hide();
					standByDijitObj.show();
			        
					var importingJsonData = {
							projectData : {
								id : defItemx.id,
								projectName : defItemx.projectName,
								theatre : defItemx.theatre,
								createdDate : defItemx.createdDate,
								createdBy : userCec,
								isUploaded : true
							},
							infraData : {
								serverModel : infraServerModelImporting
										.get("value"),
								softwareVersion : infraSoftwareReleaseImporting
										.get("value"),
								ioModule : infraIoModuleImporting.get("value")
							},
							equipmentData : {
								cdpAction : selectActionImporting.get("value"),
								cdpLinkAgg : selectLinkAggregationPolicyImporting.get("value")
							}
						};
					setTimeout(function(){
						var response = ajaxCallPostWithJsonContent( "importProject.html", dojo.toJson(importingJsonData), null, "text");
						if(response != null && parseInt(response) > 0){
							dijit.byId("projectid").set("value",response);
							document.getElementById("projectForm").submit();
							//standByDijitObj.hide();
							
						}else{
							standByDijitObj.hide();
							console.error("project not created");
						}
					},1000);
					return false;
					
				} else {
					var response = ajaxCallPostWithJsonContent( "createProject.html", dojo.toJson(defItemx), null, "text");
				}

				if (response == "error") {
					console.error("error while saving the record in the DB");
				} else {
					defItemx.id = response;
					projectTable.store.newItem(defItemx);
					dataStore.save();
					projectTable.refresh();

					// redirect to wizard when project created.
					document.getElementById("submitProjectId").value = defItemx.id;
					document.getElementById("submitProjectName").value = defItemx.projectName;
					document.getElementById("submitProjectFormToWizard").submit();
					//window.location.href = 'wizard.html?projectId='+ defItemx.id + '&projectName='+ defItemx.projectName;
				}
			} else {
				//save edited item
				dataStore.setValue(selectedItem, "id", defItemx.id);
				dataStore.setValue(selectedItem, "projectName",
						defItemx.projectName);
				dataStore.setValue(selectedItem, "theatre", defItemx.theatre);

				var response = ajaxCallPostWithJsonContent(
						"updateProject.html", dojo.toJson(defItemx), null,
						"text");
				if (response == "success") {
					// code to convert to Json and update it to DB.
					dataStore.save();
					projectTable.refresh();
				} else {
					console.error("error while updating the record in the DB");
				}
			}
		} catch (e) {
			console.error("error in project creating.:="+e.message);
			//alert(e.message);
		}
		return;
	}

	function fetchFailed(errorData, request) {
		//alert("error");
	}

	function deleteProject() {
		displayConfirmationDialog(MSG_DELETED, deleteProjectConfirm);
		return false;
	}
	function deleteProjectConfirm() {
		var response = ajaxCallGet("deleteProject.html?projectId="
				+ selectedItem.id, true, "text");
		if (response == "success") {
			projectTable.store.deleteItem(selectedItem);
			dataStore.save();
			projectTable.refresh();
		} else {
			console.error("error in deleting the selected item"
					+ selectedItem.name);
		}
	}

	var selectedItem;
	dojo.ready(function() {

				dijit.byId("editProject").set("disabled", true);
				dijit.byId("deleteProject").set("disabled", true);
				dijit.byId("cloningProject").set("disabled", true);
				dijit.byId("downloadProject").set("disabled",true);
				dijit.byId("generateTopology").set("disabled",true);
				dojo.connect(projectTable, "onSelect", function(item, row) {
					selectedItem = item;
					dijit.byId("editProject").set("disabled", false);
					dijit.byId("deleteProject").set("disabled", false);
					dijit.byId("cloningProject").set("disabled", false);
					dijit.byId("downloadProject").set("disabled",false);
					dijit.byId("generateTopology").set("disabled",false);

				});

				dojo.connect(projectTable, "onDeselect", function(item, row) {
					dijit.byId("editProject").set("disabled", true);
					dijit.byId("deleteProject").set("disabled", true);
					dijit.byId("cloningProject").set("disabled", true);
					dijit.byId("downloadProject").set("disabled",true);
					dijit.byId("generateTopology").set("disabled",true);
					selectedItem = null;
				});

				var saveBtn = projectDialog.buttonGroup.getItemAt(0);
				var cancelBtn = projectDialog.buttonGroup.getItemAt(1);

				dojo.connect(saveBtn,"onClick",function() {
							
									if (dijit.byId("projectForm").validate() == false || dijit.byId("projectName").get( 'value') == ""
											|| (dojo.query( "#hideShowProjectImport").style("display") != "none" && document.getElementById("projectImport").value == "") 
											|| (dojo.query( "#hideShowProjectImportExtraFields").style("display") != "none" && (dijit.byId("infraServerModelImporting").get("value") == "" 
											|| dijit.byId("infraSoftwareReleaseImporting").get("value") == ""))
											|| (dojo.query( "#hideShowProjectImportExtraFields").style("display") != "none" && ( dijit.byId("infraIoModuleImporting").get("value") == "" && dijit.byId("infraServerModelImporting").get("value") != "Cisco UCS 6324") ) ) {

										displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
										return false;
									}
									var projectName = dijit.byId("projectName").get('value');
									var projectTheatre = dijit.byId("projectTheatre").get('value');
									var projectid = dijit.byId("projectid").get('value');
									var todaysDate = new Date();
									var format = "yyyy-MM-dd";
									var creationDate = dojo.date.locale.format(todaysDate, {selector : "date",datePattern : format});
									//Unique validation on name
									if (ajaxCallPostWithJsonContent("fetchProjectDetailIfNameAlreadyExist.html",dojo.toJson({id:parseInt(projectid),projectName:projectName}), null, "json")) {
										displayNotificationAlert(MSG_DUPLICATE_NAME);
										return false;
									}

									var defItemx = {
										"id" : projectid,
										"projectName" : projectName,
										"theatre" : projectTheatre,
										"createdDate" : creationDate,
										"createdBy" : userCec,
										"isUploaded" : false
									};

									addNewProject(defItemx);
									projectDialog.hide();
								});
				dojo.connect(cancelBtn, "onClick", function() {
					//alert("hh");
				});
			});

	dojo.ready(function() {
		
		standByDijitObj = dijit.byId("progressBarDialogId");
		var title = dojo.query('.progressBarDialog .dijitDialogTitleBar').style('display', 'none');
        var button = dojo.query('.progressBarDialog .xwtTextButtonGroup').style('display', 'none');
        
				var intfNameList = [];
				intfNameList.push({value : "Americas",label : "Americas"});
				intfNameList.push({	value : "APJC",	label : "APJC"});
				intfNameList.push({	value : "EMEAR",label : "EMEAR"});
				intfNameList.push({	value : "Global Enterprise",label : "Global Enterprise"});
				var jir = projectTheatre.addOption(intfNameList);

				var serverModelImportArr = [];
				serverModelImportArr.push({	value : "",	label : LABEL_SELECT});
				serverModelImportArr.push({	value : "Cisco UCS 6248",label : "Cisco UCS 6248"});
				serverModelImportArr.push({	value : "Cisco UCS 6296",label : "Cisco UCS 6296"});
				serverModelImportArr.push({value: "Cisco UCS 6324",	label: "Cisco UCS 6324"});
				infraServerModelImporting.addOption(serverModelImportArr);

				var softwareReleaseImportArr = [];
				softwareReleaseImportArr.push({	value : "",	label : LABEL_SELECT});
				softwareReleaseImportArr.push({value: "2.2 (2c)",	label: "2.2 (2c)"});
				softwareReleaseImportArr.push({value: "2.2 (3d)",	label: "2.2 (3d)"});
				softwareReleaseImportArr.push({value: "2.2 (5b)",	label: "2.2 (5b)"});
				softwareReleaseImportArr.push({value: "3.1 (1e)",	label: "3.1 (1e)"});
				infraSoftwareReleaseImporting.addOption(softwareReleaseImportArr);
				
				var	softwareReleaseMiniImportingArr = [];
				softwareReleaseMiniImportingArr.push({value: "",	label: LABEL_SELECT});
				softwareReleaseMiniImportingArr.push({value:"3.0 (2c)",label:"3.0 (2c)"});
				softwareReleaseMiniImportingArr.push({value:"3.1 (1e)",label:"3.1 (1e)"});

				var ioModuleImportArr = [];
				ioModuleImportArr.push({value : "",	label : LABEL_SELECT});
				ioModuleImportArr.push({value : "4",label : "2204"});
				ioModuleImportArr.push({value : "8",label : "2208"});
				infraIoModuleImporting.addOption(ioModuleImportArr);

				// EquipMent Info
				var selectActionImportArr = [], selectLinkAggregationPolicyImportArr = [], selectPowerSupplyImportArr = [];
				//selectActionImportArr.push({value : "1",label : "1-link"});
				//selectActionImportArr.push({value : "2",label : "2-link"});
				//selectActionImporting.addOption(selectActionImportArr);
				
				// mini dropdown changes...
				dojo.connect(infraServerModelImporting,"onChange",function(){
						 if(this.value == "Cisco UCS 6324"){
							 infraSoftwareReleaseImporting.removeOption(infraSoftwareReleaseImporting.getOptions());
							 infraSoftwareReleaseImporting.addOption(softwareReleaseMiniImportingArr);
							 infraIoModuleImporting.set("value","");
							 infraIoModuleImporting.set("disabled",true);
							 //
							 	selectActionImporting.removeOption(selectActionImporting.getOptions());
							 	selectActionImportArr = [];
								selectActionImportArr.push({value : "1",label : "1-link"});
								selectActionImportArr.push({value : "2",label : "2-link"});
								selectActionImporting.addOption(selectActionImportArr);
						}else{
							infraSoftwareReleaseImporting.removeOption(infraSoftwareReleaseImporting.getOptions());
							infraSoftwareReleaseImporting.addOption(softwareReleaseImportArr);
							infraIoModuleImporting.set("value","");
							infraIoModuleImporting.set("disabled",false);
						}
					});
				
				
				dojo.connect(infraIoModuleImporting,"onChange",function(){
					selectActionImporting.removeOption(selectActionImporting.getOptions());
					selectActionImportArr = [];
						selectActionImportArr.push({value : "1",label : "1-link"});
						selectActionImportArr.push({value : "2",label : "2-link"});
					if(parseInt(this.value) == 8){
						selectActionImportArr.push({value : "4",label : "4-link"});
						selectActionImportArr.push({value : "8",label : "8-link"});
						selectActionImporting.set("disabled",false);
					}else if(parseInt(this.value) == 4){
						selectActionImportArr.push({value : "4",label : "4-link"});
						selectActionImporting.set("disabled",false);
					}else{
						//selectActionImporting.set("disabled",true);
					}
					selectActionImporting.addOption(selectActionImportArr);
				});
				
				// Add Option for Link Aggregation
				selectLinkAggregationPolicyImportArr.push({value : "none",label : "None"});
				selectLinkAggregationPolicyImportArr.push({value : "port-channel",label : "port-channel"});
				selectLinkAggregationPolicyImporting.addOption(selectLinkAggregationPolicyImportArr);

				document.getElementById("ProjectUploadBtn").onchange = function() {
					var expFile = /^.*\.(zip|xml)$/;
					var Exp_XML = /^.*\.(xml)$/;
					if (expFile.test(this.value)) {
						if (Exp_XML.test(this.value)) {
							projectDialog.set("style", "width:800px;left: 310px;top: 134px;");
							dojo.fx.wipeIn({node : dojo.byId("hideShowProjectImportExtraFields"),duration : 1000}).play();
						} else {
							projectDialog.set("style", "width:400px;left: 510px;top: 155px;");
							dojo.fx.wipeOut({node : dojo.byId("hideShowProjectImportExtraFields"),duration : 1000}).play();
						}
						document.getElementById("projectImport").value = this.value;
					} else {
						displayNotificationAlert(MSG_FILE_EXT_ERROR);
						document.getElementById("ProjectUploadBtn").value = "";
						document.getElementById("projectImport").value = "";
					}
				};

			});

	
	function cloningProjectData() {
		var projectId = selectedItem.id[0];
		var isChassisSaved = ajaxCallGet("getEquipmentChassisDetailByProject.html?projectId="+projectId, true, "json");
		if(isChassisSaved){
			displayConfirmationDialog(CLONE_CONF,cloningProjectDataProcess);
		}else{
			displayNotificationAlert(MSG_CHASSIS_CONF_ISSAVED,"warning");
		}
	}
	function cloningProjectDataProcess(){
		standByDijitObj.show();
        
		var todaysDate = new Date();
		var format = "yyyy-MM-dd";
		var creationDate = dojo.date.locale.format(todaysDate, {
			selector : "date",
			datePattern : format
		});

		var cloningProjectJson = {
			id : selectedItem.id[0],
			projectName : selectedItem.projectName[0],
			theatre : selectedItem.theatre[0],
			createdDate : creationDate,
			isUploaded : true
		};
		setTimeout(function() {
			var response = ajaxCallPostWithJsonContent("cloningSelectedProject.html", dojo.toJson(cloningProjectJson), null, "text");
			if (response != null) {
				try {
					var res = JSON.parse(JSON.parse(response)[0]);
					
					addProjectInUIWhenClone(res);

					var importedRes = ajaxCallGet("fillDataForClonedProject.html?sourceId="+ selectedItem.id[0] + "&targetId="+ res.id, true, "text");
					if (importedRes == "success") {
						standByDijitObj.hide();
						
						document.getElementById("submitProjectId").value = res.id;
						document.getElementById("submitProjectName").value = res.projectName;
						document.getElementById("submitProjectFormToWizard").submit();
						//window.location.href = 'wizard.html?projectId='+ res.id + '&projectName=' + res.projectName;

					} else {
						standByDijitObj.hide();
						displayNotificationAlert(MSG_CLONING_ERROR);
					}

				} catch (e) {
					standByDijitObj.hide();
				}

			}

		}, 1000)

	}
	function addProjectInUIWhenClone(response) {
		var cloneProject = {
			"id" : response.id,
			"projectName" : response.projectName,
			"theatre" : response.theatre,
			"createdDate" : response.createdDate,
		};
		projectTable.store.newItem(cloneProject);
		dataStore.save();
		projectTable.refresh();
	}
	
	function importingProjectXMLData() {
		displayConfirmationDialog(IMPORT_CONF,importingProjectXMLDataProcess);
	}
	function importingProjectXMLDataProcess(){
		document.getElementById("ProjectUploadBtn").value = "";
		document.getElementById("projectImport").value = "";
		projectForm.reset();
		
		dojo.query( "#hideShowProjectImportExtraFields").style("display","none");
		dojo.query("#hideShowProjectImport").style("display", "table-row");
		projectDialog.set("style", "width:400px;left: 510px;top: 155px;");
		projectDialog.set("title", "Import Project");
		//projectDialog.set("button1Label","Upload");
		
		projectDialog.show();
		setTimeout(function() {dijit.byId("projectName").focus();}, 500);
	}
	var errorMessage = '${errorMessage}';
	dojo.addOnLoad(function() {
		if(errorMessage && errorMessage != "") {
				displayNotificationAlert(errorMessage);
		}
	});
	
	
</script>
</head>

<body class="prime" id="bodyUniqueIdForStandByImporting">
	<div dojoType="xwt/widget/layout/XwtContentPane" href="header.html"></div>
	    
	<div style="height: 50px;"></div>
	<div id="userManagementContainer">
		<div class="userMgmtPageHeader"><h2>Project(s) / Domain(s)</h2></div>
		
		<div id="global" dojoType="xwt/widget/table/GlobalToolbar" iconClass="" tableId="projectTable"
			displayTotalRecords="false" showButtons="none"></div>
		<span dojoType="dojo/data/ItemFileWriteStore" jsId="dataStore"
			urlPreventCache="true" data=data></span>
		<div id="context" dojoType="xwt/widget/table/ContextualToolbar"
			tableId="projectTable" quickFilter="false">
			<div dojoType="xwt/widget/table/ContextualButtonGroup"
				showButtons="none"></div>
			<!--This contains the move up,down,to actions -->
			<div dojoType="dijit/form/Button" iconClass="fi-plus"
				title="Create Project" showLabel="false"
				onclick="return showDialog();"></div>
			<div id="editProject" dojoType="dijit/form/Button"
				iconClass="fi-edit " title="Edit Project" showLabel="false"
				onclick="return showDialog(this);"></div>
			<div id="deleteProject" dojoType="dijit/form/Button"
				iconClass="fi-delete" title="Delete Project" showLabel="false"
				onclick="return deleteProject();"></div>
			<div data-dojo-type="dijit/ToolbarSeparator"></div>
			<div id="cloningProject" dojoType="dijit/form/Button"
				iconClass="xwtContextualText " title="Clone Project"
				showLabel="true" onclick="cloningProjectData();">Clone</div>
			<div id="downloadProject" dojoType="dijit/form/Button"
				iconClass="xwtContextualText " title="Download project"
				showLabel="true" onclick="downloadProjectDetail();">Download</div>
			<div id="importingProject" dojoType="dijit/form/Button"
				iconClass="xwtContextualText" title="Import Project"
				showLabel="true" onclick="importingProjectXMLData();">Import</div>
			<div id="generateTopology" dojoType="dijit/form/Button"
				iconClass="xwtContextualText" title="Generate Topology"
				showLabel="true" onclick="startTopologyGenaration();">Topology</div>	
		</div>
		<div id="projectTable" data-dojo-id="projectTable"
			dojoType="xwt/widget/table/Table" store="dataStore"
			structure="columns"
			style="height: 500px; overflow-y: auto; width: 100%;"
			selectMultiple="false" selectAllOption="false" showIndex="false"
			selectModel="input" selectAllLimit="5" rowsPerPage="10"
			filterOnServer=false></div>


		<div id="projectDialog" data-dojo-id="projectDialog"
			title="Add/Edit Project" button1Label="Save"
			dojoType="xwt/widget/layout/Dialog" closable=true
			style="width: 400px;">
			<div style="width: 100%;">
				<form class="xwtNotification" id="projectForm"
					data-dojo-id="projectForm" dojoType="xwt/widget/notification/Form"
					action="postImportedProjectFile.html" encType="multipart/form-data" name="example"
					method="post">
					<input id="projectid" name="projectid" data-dojo-id="projectid" type="hidden"
						required="true" value="0" dojoType="dijit/form/TextBox"></input>
					<table>
						<tr>
							<td height="40px;">
								<div class="labelspace">
									<label style="float: left;">Name:<em>*</em></label>
								</div>
							</td>
							<td>
								<div id="projectName" data-dojo-id="projectName" name="projectName"
									data-dojo-type="xwt/widget/notification/ValidationTextBox"
									data-dojo-props='regExp:REG_EX_NAME, trim:"true", maxlength:"44", promptMessage:"", invalidMessage:MSG_NAME, i18nPackageName:"xwt", i18nBundleName:"XWTProperties"'></div>
							</td>
						</tr>
						<tr>
							<td height="40px;">
								<div class="labelspace">
									<label style="float: left;">Theatre:</label>
								</div>
							</td>
							<td><select id="projectTheatre"
								data-dojo-id="projectTheatre"
								data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
								style="width: 198px;" /></td>
						</tr>
						
						<tr id="hideShowProjectImport" style="display: none;">
							<td height="40px;">
								<div class="labelspace">
									<label style="float: left;">Import:<em>*</em></label>
								</div>
							</td>
							<td>
							
								<div class="" style="padding: 0px;">
									<div style="display: inline; float: left; width: 195px;">
										<input name="projectImport" id="projectImport"
											style="width: 248px; padding-top: 6px; border-radius: 4px;"
											placeholder="Choose File" class="upload_file"
											readonly="readonly" />
									</div>
									<div class="fileUpload btn btn-default"
										style="display: inline; float: left; margin-top: 0;">
										<span>Browse</span> <input id="ProjectUploadBtn" type="file"
											class="upload" name="upload_project" />
									</div>
								</div>
								
							</td>
						</tr>
						
					</table>
					</form>
					
					<div id="hideShowProjectImportExtraFields" style="display: none;">
						<form class="xwtNotification" id="projectExtraFieldsForm"
							data-dojo-id="projectExtraFieldsForm" dojoType="xwt/widget/notification/Form"
							action="postImportedProjectFile.html" name="projectExtraFieldsForm">
								<table class="">
									<tr>
										<td height="40px">
											<div class="labelspace">
												<label style="float: left;">Fabric Interconnect:<em>*</em></label>
											</div>
										</td>
										<td height="40px"><select id="infraServerModelImporting"
											name="serverModel" data-dojo-id="infraServerModelImporting"
											data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
											style="width: 100px" /></td>
										<td height="40px">
											<div class="labelspace">
												<label style="float: left;padding-left:10px;">Software Release:<em>*</em></label>
											</div>
										</td>
										<td height="40px"><select
											id="infraSoftwareReleaseImporting" name="softwareVersion"
											data-dojo-id="infraSoftwareReleaseImporting"
											data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
											style="width: 100px" /></td>
										<td height="40px">
											<div class="labelspace">
												<label style="float: left;padding-left:10px;">IO Module:<em>*</em></label>
											</div>
										</td>
										<td height="40px"><select id="infraIoModuleImporting"
											name="ioModule" data-dojo-id="infraIoModuleImporting"
											data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
											style="width: 100px" /></td>
									</tr>
									<tr>
										<td height="50px">
											<div class="labelspace">
												<label style="float: left;">Action:</label>
											</div>
										</td>
										<td height="40px"><select id="selectActionImporting"
											name="cdpAction" data-dojo-id="selectActionImporting"
											data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
											style="width: 100px" data-dojo-props='' /></td>
										<td height="40px">
											<div class="labelspace">
												<label style="float: left;padding-left:10px;">Link Aggregation Policy:</label>
											</div>
										</td>
										<td height="40px" colspan="3"><select
											id="selectLinkAggregationPolicyImporting" name="cdpLinkAgg"
											data-dojo-id="selectLinkAggregationPolicyImporting"
											data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
											style="width: 100px" /></td>
										
									</tr>
								</table>
								</form>
						</div>
			</div>
		</div>
	</div>
	

		

	<div dojoType="xwt/widget/layout/XwtContentPane" href="footer.html"></div>

	<div id="actionFormForWizardSubmit">
		<form id="submitProjectFormToWizard" action="wizard.html" name="submitFormToWizard" method="post">
			<input id="submitProjectId" name="projectId" type="hidden"></input>
			<input id="submitProjectName" name="projectName" type="hidden"></input>
		</form>
	</div>
	<iframe id="downloadFrame" style="display: none;" src=""></iframe>
	
	<div class="progressBarDialog" dojoType="xwt/widget/layout/Dialog" id="progressBarDialogId" style="width: 35rem;border: 2px solid #666;">
            <div style="overflow:hidden;" dojoType='dijit/layout/ContentPane'>
               <div maximum="20" indeterminate="true" jsid="intProgress" showInformationtext=true infoText = "&lt;b&gt;Please wait for the current operation to finish. &lt;/b&gt;" showBoundingBox=false dojoType="xwt/widget/notification/ProgressBar"></div>
            </div>
    </div>
     
</body>
</html>

