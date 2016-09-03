<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Management</title>
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
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/formatter.js"></script>
<script type="text/javascript">
var userRoleDropDown = [];
var userRoleList = { items:JSON.parse("[" + ajaxCallGet("getUserRoleList.html", true, "json") + "]") };

var userMgmtDataTable = { items:JSON.parse("[" + ajaxCallGet("getUserList.html", true, "json") + "]") };
	
require(["xwt/xwt","dojo/parser", 
			'dojo/_base/declare',
			"dojo/on", 
			"dojo/aspect",
			"dojo/dom-class",
			"dijit/registry",
			"dojo/domReady!",
			"dojo/_base/json"
		], function (xwt,parser, declare, on, aspect, domClass, dijit,ready,json) {
	
	setTimeout(function(){
		// Adding Role in DD
		dojo.forEach(userRoleList.items,function(eachRole,i){
			 if(userRole == "product_owner"){
				 if(eachRole.name == "super_admin" ){
					 userRoleDropDown.push({value: eachRole.id,	label: (eachRole.name).toUpperCase()});
				 }
			 }else if(userRole == "super_admin"){
				 if(eachRole.name == "admin" || eachRole.name == "user" ){
					 userRoleDropDown.push({value: eachRole.id,	label: (eachRole.name).toUpperCase()});
				 }
			 }else if(userRole == "admin"){
				 if(eachRole.name == "user" ){
					 userRoleDropDown.push({value: eachRole.id,	label: (eachRole.name).toUpperCase()});
				 }
			 }
		});
		userMgmtRole.addOption(userRoleDropDown);
		
		userMgmtDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		var userMgmtTablejson = returnChangedDataFromDataStore(this,json);		
		var response = ajaxCallPostWithJsonContent("manageUsers.html" , userMgmtTablejson, null, "json");
		saveComplete();
		updateZeroIdsInDataStore(response, this);
		};
		
	},1000);
	
});

var userMgmtColumns = [
                      {label: 'dbID',	attr: 'id',	hidden: true},
                      {label: 'Name',attr: 'name',sorted: 'ascending',width: 250,vAlignment: "middle",align:'center',hidden:true},
                      {label: 'User Id',attr: 'userId',sorted: 'ascending',width: 250,vAlignment: "middle",align:'center'},
                      {label: 'Mail Id',attr: 'mailId',hidden: true},
                      {label: 'Role Id',attr: 'userRoles',hidden: true},
                      {label: 'Role',attr: 'userRolesName',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center'},
                      {label: 'Created By',attr: 'createdBy',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',hidden : true},
                      {label: 'Created Date',attr: 'createdDate',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center'},
                      {label: 'Status',attr: 'isActive',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
                    	  filterType: "boolean",
                    	  editWidget: {
       						widget: xwt.widget.form.DropDown,
       						options: {
       							options: [{label: "Active",value: "1"}, {label: "Inactive",value: "0"}]
    		   						}
    		   					}
                       	},
                     ];

function userMgmtGenerateData(){
	  var formObject2 =  dojo.formToObject("userMgmtTableForm");
		if(dijit.byId("userMgmtTableForm").validate()==false){
			return false;
		}		
		formObject2.userRoles = parseInt(formObject2.userRoles);
		var response = ajaxCallPostWithJsonContent("validateUserCec.html" , JSON.stringify(formObject2), null, "text");
		var errorMessage = "";
		if(response != "success"){
			if(response.indexOf("LDAP") > -1 ){
				errorMessage = "\" "+response.substring(0,response.length-5)+" \" "+MSG_USER_MGMT_INVALID_USERID;
				displayNotificationAlert(errorMessage);
			}else{							
				var usersId = response.substring(0,response.indexOf("EXIST")-1)				
				var message = response.substring(response.indexOf("EXIST")+6,response.length)				
				var errorMessage = "Users "+"\" "+usersId+" \" "+message;
				displayNotificationAlert(errorMessage);
			}
			return false;			
		}
		var splitId = (formObject2.userId).split(",");
		for (i = 0; i< splitId.length; i++){
			var userMgmtDataGenreate = {
	 				"id":0,
	 				"userId": splitId[i],
	 				"name" : "",
	 				"mailId" : "",
	 				"userRoles": parseInt(formObject2.userRoles),
	 				"userRolesName": getUserRolesNameById(formObject2.userRoles), 
	 				"createdBy" : activeUserID,
	 				"createdDate" : getTodayDate(),
	 				"isActive" : 1
	 			};
	 		userMgmtTable.store.newItem(userMgmtDataGenreate);
		}
		
 		userMgmtDataStoreTab.save();
 		userMgmtTable.refresh();
	 	
	 	dijit.byId("userMgmtTableForm").reset();
}

function getTodayDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	return yyyy+'-'+mm+'-'+dd;
}

function getUserRolesNameById(userRolesId){
	var n = "";
	dojo.forEach(userRoleDropDown,function(obj,i){
		if(userRolesId == obj.value){
			n = obj.label;
			return false;
		}
	});
return n;
}
</script>
</head>
<body class="prime">
<div dojoType="xwt/widget/layout/XwtContentPane" href="header.html"></div>
<div style="height: 50px;"></div>
<div id="userManagementContainer">
	<div class="userMgmtPageHeader"><h2>User Management <span><a href="listProjects.html" >Home</a></span></h2></div>
		<div id="pageFieldContainer">
			<form id="userMgmtTableForm"
							data-dojo-id="userMgmtTableForm"
							data-dojo-type="xwt/widget/notification/Form" name="tableForm">
					<table>
						<tr>
						  <td>
							<div class="labelspace">
									<label style="float: left;">Enter User ID:<em>*</em></label>
								</div>
							</td>
							<td>
								<div id="userCecId" name="userId"
									data-dojo-id="userCecId" style="width: 400px;"
									data-dojo-type="xwt/widget/notification/ValidationTextBox"
									data-dojo-props='required:"true",regExp:REG_EX_NAME_WITH_COMMA,trim:"true", invalidMessage:MSG_USERID,placeHolder:MSG_USER_MGMT_INFO'></div>
							</td>
							<td>
								<div class="labelspace">
									<label style="float: left;">Select Role:</label>
								</div>
							</td>
							<td>
							<select id="userMgmtRole"
								name="userRoles"
								data-dojo-id="userMgmtRole"
								data-dojo-type="xwt/widget/form/DropDown" maxHeight="100"
								style="width: 140px"></select>
							</td>
							<td style="padding-left: 10px;">
								<button data-dojo-type="dijit/form/Button"
									data-dojo-id="userMgmtGenerateDataBtn"
									onClick="userMgmtGenerateData();" type="button">Add</button>
							</td>
						</tr>
					</table>
			</form>
		</div>
		<div id="tableContainer">
			<div dojotype="dijit/layout/ContentPane" region="left"
						style="width: 100%; overflow: hidden;" splitter="true">
						<span dojoType="dojo/data/ItemFileWriteStore"
							jsId="userMgmtDataStoreTab"
							data="userMgmtDataTable"></span>
						<div style="width: 100% !important;"
							id="userMgmtTableTollBar"
							dojoType="xwt/widget/table/ContextualToolbar"
							tableId="userMgmtTable" quickFilter="false">
							<!-- <div dojoType="xwt/widget/table/ContextualButtonGroup"
								showButtons=""></div> -->
						</div>
						<div id="userMgmtTable"
							data-dojo-id="userMgmtTable"
							dojoType="xwt/widget/table/Table"
							store="userMgmtDataStoreTab"
							structure="userMgmtColumns"
							style="width: 100%; height: 450px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
			</div>
		</div>
</div>
<div dojoType="xwt/widget/layout/XwtContentPane" href="footer.html"></div>
</body>
</html>