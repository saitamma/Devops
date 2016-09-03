<%-- serversServiceTempProfileConfig.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
			dojo.require("xwt.widget.table.Table");
			dojo.require("xwt.widget.table.Toolbar");
			dojo.require("dojo.data.ItemFileReadStore");
			dojo.require("dijit.form.Button");
			dojo.require("dijit.form.Form");
			dojo.require("xwt.widget.notification.ValidationTextBox");
			dojo.require("xwt.widget.notification.Form");

			//var noOfTemplates = 0;
			
			var serviceProfileTemplateConfigDataResponse = ajaxCallGet("getServiceTemplateConfigDetails.html", true, "json");
			var serversServiceProfileTempDataTable = JSON.parse("[" + serviceProfileTemplateConfigDataResponse + "]");
			
			var templateInstantiateConfigDataResponse = ajaxCallGet("getServiceProfileDetails.html", true, "json");
			var serversServiceTempInstDataTable = { items:JSON.parse("[" + templateInstantiateConfigDataResponse + "]") };
			
			// Calculating the max used Profile Template name
			/* var count = templateInstantiateConfigDataResponse.length;
			if(count > 0){
				noOfTemplates = extractNumericValueFromAlphaNumericString(JSON.parse(templateInstantiateConfigDataResponse[count-1]).name);
			} */
			
			// Populating template DropDown values
			var profileTemplateValues=[],profileTemplateValuesOnEdit=[];
			if(serviceProfileTemplateConfigDataResponse){
				profileTemplateValues.push({value: "", label: LABEL_SELECT,selected:true}); 
				dojo.forEach(JSON.parse("["+serviceProfileTemplateConfigDataResponse+"]"),function(eachProfileTemplate,i){
					profileTemplateValues.push({value:eachProfileTemplate.id,	label:eachProfileTemplate.name});
					profileTemplateValuesOnEdit.push({value:eachProfileTemplate.id,	label:eachProfileTemplate.name});
				});
			}
			
 
			var serversServiceTempInstColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 230,vAlignment: "middle",align:'center',editable: true,
                		editWidget:{
                			widget:xwt.widget.notification.ValidationTextBox,
                			options: {
                				regExp:REG_EX_NAME,
  								required: true,
  	                			trim: true,
  	                			maxlength:"16",
  	                			invalidMessage: MSG_NAME
  								}
                		}   
                   },
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 230,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
                			widget:xwt.widget.notification.ValidationTextBox,
                			options: {
                   			trim: true,
                   			maxlength:"45"
   							}
                		}   
                   },
                   {label: 'Profile Template',attr: 'serversServiceProfileTemplate',width: 250,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetServiceProfileTemplateName,
					 editWidget: {
						widget: xwt.widget.form.DropDown,
						options: {options: profileTemplateValuesOnEdit }
		                   	  }
                   },
                   {label: 'Organization',attr: 'organizations',width: 220,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
						editWidget: {
							widget: xwt.widget.form.DropDown,
							options: {options: getOgranizationDropDown()}
			                   	  }
	                  }
                ];		


function serversServiceTempInstGenerateData(){
	var formObject2 =  dojo.formToObject("serversServiceTempInstTableForm");
	if(dijit.byId("serversServiceTempInstTableForm").validate()==false || formObject2.noOfServiceProTempInst == "" || formObject2.serversServiceProfileTemplate == "" ){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	
 	var selectedOrg = parseInt(formObject2.serversServiceTempInstOrganization);
 	var noOfServiceProTempInst = parseInt(formObject2.noOfServiceProTempInst);
 	var slctdProfileTemplate = parseInt(formObject2.serversServiceProfileTemplate);
	
 	var spPrefix = formObject2.namingPrefix;
 	var spSuffix = parseInt(formObject2.namingSuffix);
 	
 	for(i = 1 ; i <= noOfServiceProTempInst ; i++){
 		//noOfTemplates =  noOfTemplates + 1;
 		var profileName = spPrefix+""+spSuffix++;
 		
 		if(!checkTableFieldValueUnique(serversServiceTempInstDataStoreTab,"name",profileName)){
 			continue;
 		}
 		var serversServiceTempInstDataGenreate = {
 				"id":0,
 				"name":	profileName,
 				"description": profileName,
 				"serversServiceProfileTemplate":slctdProfileTemplate,
 				"organizations":selectedOrg
 			};
 		serversServiceTempInstTable.store.newItem(serversServiceTempInstDataGenreate);
 	}
 	
 	serversServiceTempInstDataStoreTab.save();
 	serversServiceTempInstTable.refresh();
 	serversServiceTempInstTableForm.reset();
 	
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 
	    		 	// ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	serversServiceTempInstOrganization.addOption(selectOrganizationArr);
	    		 	
					// ADD dropdown values for Profile Template
					serversServiceProfileTemplate.addOption(profileTemplateValues);

	    		 	serversServiceTempInstDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var serversServiceTempInstTablejson = returnChangedDataFromDataStore(this,json);
	    			console.log(serversServiceTempInstTablejson);
	    			var response = ajaxCallPostWithJsonContent("manageServiceProfile.html" , serversServiceTempInstTablejson, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     	};
			     	
			     	dojo.addOnLoad(function () {
			     		serversServiceTempInstTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversServiceTempInstDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
								return true;
							}
						};
						
					});

	    	 },1000);
	 
	     });

// function for Save data to servere
function saveServersServiceTempInstantiateConfig(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":6,"activeStateSubMenuIndex":-1});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	 console.log("LocalDiskPolicy Save");
}

</script>
</head>
<body class="prime">
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1050px;">
				<legend>Service Profile Configuration</legend>

				<div class="vnicTemplateConfig fieldLeftPadding">
					<form id="serversServiceTempInstTableForm"
						data-dojo-id="serversServiceTempInstTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversServiceTempInstOrganization"
									name="serversServiceTempInstOrganization"
									data-dojo-id="serversServiceTempInstOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 120px" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Naming Prefix:<em>*</em></label>
									</div>
								</td>
								<td style="height: 30px;" colspan="4">
									<div id="serversServiceTempPrefix"
										data-dojo-id="serversServiceTempPrefix"
										name="namingPrefix" style="width: 100px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"8", required:"true",value:"SP", invalidMessage:MSG_NAME'></div>
								</td>
								
								</tr>
								
								<tr>
								<td style="height: 30px;">
									<div class="labelspace">
										<label style="float: left;">Naming Suffix Starting No.:<em>*</em></label>
									</div>
								</td>
								<td style="height: 30px;">
									<div id="serversServiceTempSuffix"
										data-dojo-id="serversServiceTempSuffix"
										name="namingSuffix" style="width: 103px;"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER, trim:"true", maxlength:"5", required:"true", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of Service Profiles:<em>*</em></label>
									</div>
								</td>
								
								<td>
									<div id="noOfServiceProTempInst" name="noOfServiceProTempInst"
										style="width: 100px" data-dojo-id="noOfServiceProTempInst"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Profile Template:<em>*</em></label>
									</div>
								</td>
								<td><select id="serversServiceProfileTemplate"
									name="serversServiceProfileTemplate"
									data-dojo-id="serversServiceProfileTemplate"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 120px" /></td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversServiceTempInstGenerateDataBtn"
										onClick="serversServiceTempInstGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft"
					style="padding-left: 10px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1028px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversServiceTempInstDataStoreTab"
							data="serversServiceTempInstDataTable"></span>
						<div style="width: 1025px !important;"
							id="serversServiceTempInstTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversServiceTempInstTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversServiceTempInstTable"
							data-dojo-id="serversServiceTempInstTable"
							dojoType="xwt.widget.table.Table"
							store="serversServiceTempInstDataStoreTab"
							structure="serversServiceTempInstColumns"
							style="width: 1025px; height: 205px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>