<%-- serversServerPoolPolicyConfig.jsp --%>
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
		dojo.require("xwt.widget.form.DropDown");
		dojo.require("xwt.widget.notification.ValidationTextBox");
		dojo.require("xwt.widget.notification.Form");

		var serverPoolQualifierConfigDataResponse = ajaxCallGet("getServerPoolQualifierConfigDetails.html", true, "json");
		var serversServerQualifierDataTable = { items:JSON.parse("[" + serverPoolQualifierConfigDataResponse + "]") };
		
		var serverPoolPolicyConfigDataResponse = ajaxCallGet("getServerPoolPolicyConfigDetails.html", true, "json");
		var serversServerPoolPolicyDataTable = { items:JSON.parse("[" + serverPoolPolicyConfigDataResponse + "]") };
		
		var serverPoolConfigDataResponse = ajaxCallGet("getServerPoolConfigDetails.html", true, "json");
		var serversServerPoolDataTable = { items:JSON.parse("[" + serverPoolConfigDataResponse + "]") };
		
		var qualifierNames=[], serverPoolValues=[];
		qualifierNames.push({value:null,label: LABEL_SELECT});
		dojo.forEach(serversServerQualifierDataTable.items,function(obj , i){
			qualifierNames.push({value:obj.id,	label:obj.name});
		});
		
		serverPoolValues.push({value:null,	label: LABEL_SELECT});
		dojo.forEach(serversServerPoolDataTable.items,function(obj , i){
			serverPoolValues.push({value:obj.id,	label:obj.name});
		});

		// Calculating the max used ServersPool Policy name
		var serverPoolPolicyMax = 0; 
		var count = serverPoolPolicyConfigDataResponse.length;
		if(count > 0){
			var lastNameUsed = JSON.parse(serverPoolPolicyConfigDataResponse[count-1]).name;
			serverPoolPolicyMax = extractNumericValueFromAlphaNumericString(lastNameUsed);
		}
		
var serversServerPoolPolicyColumns = [
                               {label: 'dbID',	attr: 'id',	hidden:true	},
                               {label: 'Name',attr: 'name',sorted: 'ascending',width: 170,vAlignment: "middle",align:'center',editable: true,
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
                               {label: 'Description',attr: 'description',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,
                            	   editWidget:{
                           			widget:xwt.widget.notification.ValidationTextBox,
                           			options: {
               	                			trim: true,
               	                			maxlength:"45",
               							}
                           			}   
                               },
                               {label: 'Server Pool',attr: 'serversServerPool',sorted: 'ascending',width: 210,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetServerPoolName,
               					editWidget: {
            						widget: xwt.widget.form.DropDown,
            						options: {options: serverPoolValues,maxHeight:150}
            		                   	  }
                              		},
                               {label: 'Qualifier',attr: 'serversServerPoolQualifier',sorted: 'ascending',width: 180,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetServerPoolQualifierName,
                    					editWidget: {
                    						widget: xwt.widget.form.DropDown,
                    						options: {options: qualifierNames,maxHeight:150}
                    		                   	  }
                                      		},
                               {label: 'Organization',attr: 'organizations',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
                					editWidget: {
                						widget: xwt.widget.form.DropDown,
                						options: {options: getOgranizationDropDown(),maxHeight:150}
                		                   	  }
                                  		}
                               ];

function formatterGetServerPoolName(data, item, store){
	return returnFormatterDropDownLabel(serverPoolValues,data, item, store);
} 
function formatterGetServerPoolQualifierName(data, item, store){
	return returnFormatterDropDownLabel(qualifierNames,data, item, store);
} 
  
function serversServerPoolPolicyGenerateData(){
	 var formObject2 =  dojo.formToObject("serversServerPoolPolicyTableForm");
	if(dijit.byId("serversServerPoolPolicyTableForm").validate()==false || formObject2.noOfServerPoolPolicy == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	
		var selectedOrg = parseInt(formObject2.serversServerPoolPolicyOrganization);
		var noOfServerPoolPolicy = parseInt(formObject2.noOfServerPoolPolicy);
	
		for(i = 1 ; i <= noOfServerPoolPolicy ; i++){
			serverPoolPolicyMax++;
		var policyName = "SerPool_P"+serverPoolPolicyMax;
			if(!checkTableFieldValueUnique(serversServerPoolPolicyDataStoreTab,"name",policyName)){
				continue;
			}
			var serversServerPoolPolicyDataGenreate = {
					"id":0,
					"name":	policyName,
					"description": "Server pool policy",
					"serversServerPool": serverPoolValues[0].value,
					"serversServerPoolQualifier": qualifierNames[0].value,
					"organizations":selectedOrg
				};
			serversServerPoolPolicyTable.store.newItem(serversServerPoolPolicyDataGenreate);
		}
		serversServerPoolPolicyDataStoreTab.save();
		serversServerPoolPolicyTable.refresh();
		
	}
  require(["dojo/ready", "dojo/_base/json"], 
			 function(ready, json){
		 	
		    	 setTimeout(function(){
		    		 
		    		 // ADD dropDown val for Organization //
		    		 	var selectOrganizationArr = getOgranizationDropDown();
		    		 	serversServerPoolPolicyOrganization.addOption(selectOrganizationArr);

		    	     	
				     	serversServerPoolPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
			    			var serversServerPoolPolicyTablejson = returnChangedDataFromDataStore(this,json);
			    			console.log(serversServerPoolPolicyTablejson);
			    			var response = ajaxCallPostWithJsonContent("manageServerPoolPolicyConfig.html" , serversServerPoolPolicyTablejson, null, "json");
			    			saveComplete();
			    			updateZeroIdsInDataStore(response, this);
					    };
					    
					    dojo.addOnLoad(function () {
					    	serversServerPoolPolicyTable.validateRow = {
									errorMessage: MSG_DUPLICATE_NAME,
									isValid: function (oldvalues, newitem) {
									if(oldvalues.name != newitem.name){
										if(!checkTableFieldValueUnique(serversServerPoolPolicyDataStoreTab,"name",newitem.name)){
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
	function saveServersServerPoolQualifierAndPolicyConfig(){
		 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":5});
			response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
			return true;
		 console.log("UUID Save");
	}
 </script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1050px;">
				<legend>Server Pool Policy Configuration</legend>
				<div class="vnicTemplateConfig">
					<form id="serversServerPoolPolicyTableForm"
						data-dojo-id="serversServerPoolPolicyTableForm"
						data-dojo-type="xwt.widget.notification.Form"
						name="serversServerPoolPolicyTableForm">
						<table style="width: 590px">
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversServerPoolPolicyOrganization"
									data-dojo-id="serversServerPoolPolicyOrganization"
									name="serversServerPoolPolicyOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" 
									style="width: 100px;" />
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Number Of Policies:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfServerPoolPolicy" name="noOfServerPoolPolicy"
										style="width: 70px;" data-dojo-id="noOfServerPoolPolicy"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"2", promptMessage:"", invalidMessage:MSG_NUMBER'></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversServerPoolPolicyGenerateDataBtn"
										onClick="serversServerPoolPolicyGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft"
					style="padding-left: 35px; padding-top: 30px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 980px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversServerPoolPolicyDataStoreTab"
							data="serversServerPoolPolicyDataTable"></span>
						<div style="width: 980px !important;"
							id="serversServerPoolPolicyTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversServerPoolPolicyTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversServerPoolPolicyTable"
							data-dojo-id="serversServerPoolPolicyTable"
							dojoType="xwt.widget.table.Table"
							store="serversServerPoolPolicyDataStoreTab"
							structure="serversServerPoolPolicyColumns"
							style="width: 980px; height: 240px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</body>