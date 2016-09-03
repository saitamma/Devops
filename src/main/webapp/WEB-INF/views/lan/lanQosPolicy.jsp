<%-- lanQosPolicy.jsp --%>
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
			dojo.require("xwt.widget.form.DropDown");
			
			var qosPolicyDataResponse = ajaxCallGet("getLanQosPolicyDetails.html", true, "json");
			var lanQosPolicyDataTable = {items:JSON.parse("["+qosPolicyDataResponse+"]")};
			
			var priorityArr = [], hostControlArr = [];
			
			priorityArr.push({value:"fc",	label:"FC"});
			priorityArr.push({value:"platinum",	label:"Platinum"});
			priorityArr.push({value:"gold",	label:"Gold"});
			priorityArr.push({value:"silver",	label:"Silver"});
			priorityArr.push({value:"bronze",	label:"Bronze"});
			priorityArr.push({value:"best-effort",	label:"Best Effort", selected: true});
			
			hostControlArr.push({value:"none",	label:"None"});
			hostControlArr.push({value:"full",	label:"Full"});
			
var lanQosPolicyColumns = [
                   {label: 'dbID', attr: 'id',	hidden:true	},
                   {label: 'Name', attr: 'name', sorted: 'ascending', width: 150, vAlignment: "middle", align:'center', editable: true,
	            	   editWidget: {
							widget: xwt.widget.notification.ValidationTextBox,
	                   		options: {
	                   			regExp: REG_EX_NAME,
								required: true,
	                   			trim: true,
 	                			maxlength:"16",
	                   			invalidMessage: MSG_NAME
	       					}
         				}
                   },
	               {label: 'Priority', attr: 'priority', width: 150, vAlignment: "middle", align:'center', editable: true, formatter: formatterGetQosPriority,
      					editWidget: {
       						widget: xwt.widget.form.DropDown,
       						options: {options: priorityArr}
       		                }
               		},
                   {label: 'Burst (Bytes)', attr: 'burst', sorted: 'ascending', width: 200, vAlignment: "middle", align:'center', editable: true,
                	   editWidget:{
                  			widget: xwt.widget.notification.ValidationTextBox,
                  			options: {
    							regExp: REG_EX_NUMBER_0_TO_65535,
    							required: true,
    	                		trim: true,
    	                		invalidMessage: MSG_BET_0_TO_65535
    							}
                  			}
	               }, 
	               {label: 'Rate (Kbps)', attr: 'rate', sorted: 'ascending', width: 200, vAlignment: "middle", align:'center', editable: true,
                	   editWidget:{
                  			widget: xwt.widget.notification.ValidationTextBox,
                  			options: {
    							regExp: REG_EX_QOS_RATE,
    							required: true,
    	                		trim: true,
    	                		invalidMessage: MSG_QOS_RATE
    							}
                  			}
	               },
	               {label: 'Host Control', attr: 'hostControl', width: 200, vAlignment: "middle", align:'center', editable: true, formatter: formatterGetHostControl,
      					editWidget: {
       						widget: xwt.widget.form.DropDown,
       						options: {options: hostControlArr}
       		                }
                    },
                   {label: 'Organization', attr: 'organizations', width: 150, vAlignment: "middle", align:'center', editable: true, formatter: formatterGetOrgName,
	   					editWidget: {
			   				widget: xwt.widget.form.DropDown,
			   				options: {options: getOgranizationDropDown()}
			   		        }
	                    }
                   ];		


function lanQosPolicyGenerateData(){
	var formObject2 =  dojo.formToObject("lanQosPolicyTableForm");
	
	if(dijit.byId("lanQosPolicyTableForm").validate()==false || formObject2.lanQosPolicyName == "" || formObject2.lanQosPolicyBurst == "" || formObject2.lanQosPolicyRate == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}  
	if(!checkTableFieldValueUnique(lanQosPolicyDataStoreTable,"name",formObject2.lanQosPolicyName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}

 	var lanQosPolicyDataGenreate = {
			"id":0,
			"name": formObject2.lanQosPolicyName,
			"priority":formObject2.lanQosPolicyPriority,
			"burst":formObject2.lanQosPolicyBurst,
			"rate" :formObject2.lanQosPolicyRate,
			"hostControl": formObject2.lanQosPolicyHostControl,
			"organizations": parseInt(formObject2.lanQosPolicyOrganization)
		};
	lanQosPolicyTable.store.newItem(lanQosPolicyDataGenreate);
	
	lanQosPolicyDataStoreTable.save();
 	lanQosPolicyTable.refresh();
 	dijit.byId("lanQosPolicyName").set("value","");
 	dijit.byId("lanQosPolicyPriority").reset();
 	dijit.byId("lanQosPolicyBurst").set("value",10240);
 	dijit.byId("lanQosPolicyRate").set("value","line-rate");
 	dijit.byId("lanQosPolicyHostControl").reset();
 	dijit.byId("lanQosPolicyOrganization").reset();
 	
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){
	    		// ADD dropDown val for Organization //
	   		 	var selectOrganizationArr = getOgranizationDropDown();
	   		 	lanQosPolicyOrganization.addOption(selectOrganizationArr);
	   		 	lanQosPolicyPriority.addOption(priorityArr);
	   		 	lanQosPolicyHostControl.addOption(hostControlArr);
	   		 	
	   		 	lanQosPolicyDataStoreTable._saveCustom = function(saveComplete, saveFailed){
	    			var lanQosPolicyTablejson = returnChangedDataFromDataStore(this,json);
	    			console.log(lanQosPolicyTablejson);
	    			var response = ajaxCallPostWithJsonContent("manageLanQosPolicyConfig.html" , lanQosPolicyTablejson, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
		     	};
				
		     	//Inline row edit validation.
	     		lanQosPolicyTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(lanQosPolicyDataStoreTable,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
		     	
	    	 },1000);
	 
	     });

// function for Save data to servere
function saveLanQosPolicyOnNext(){
	// Save Wizard Status
	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":4});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	return true;
	
	 console.log("LocalDiskPolicy Save");
}


</script>
</head>
<body class="prime">
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1130px;margin: 0px 0px 0px 10px;">
				<legend>QoS Policy Configuration</legend>
				<div class="commonclassForFormFields">
					<form id="lanQosPolicyTableForm"
						data-dojo-id="lanQosPolicyTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td height="36px;">
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="lanQosPolicyOrganization"
									name="lanQosPolicyOrganization"
									data-dojo-id="lanQosPolicyOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 155px; border: 1px solid #b4b4b4;" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Name:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanQosPolicyName" name="lanQosPolicyName"
										data-dojo-id="lanQosPolicyName"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px"
										data-dojo-props='regExp:REG_EX_NAME,trim:"true", maxlength:"14", invalidMessage:MSG_NAME'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Priority:</label>
									</div>
								</td>
								<td><select id="lanQosPolicyPriority"
									name="lanQosPolicyPriority" data-dojo-id="lanQosPolicyPriority"
									data-dojo-type="xwt.widget.form.DropDown" style="width: 140px" />
								</td>
							</tr>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Burst (Bytes):<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanQosPolicyBurst" name="lanQosPolicyBurst"
										data-dojo-id="lanQosPolicyBurst"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px" value="10240"
										data-dojo-props='regExp:REG_EX_NUMBER_0_TO_65535,trim:"true", maxlength:"5", invalidMessage:MSG_BET_0_TO_65535'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Rate (Kbps):<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="lanQosPolicyRate" name="lanQosPolicyRate"
										data-dojo-id="lanQosPolicyRate"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										style="width: 140px" value="line-rate"
										data-dojo-props='regExp:REG_EX_QOS_RATE,trim:"true", maxlength:"9", invalidMessage:MSG_QOS_RATE'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Host Control:</label>
									</div>
								</td>
								<td><select id="lanQosPolicyHostControl"
									name="lanQosPolicyHostControl"
									data-dojo-id="lanQosPolicyHostControl"
									data-dojo-type="xwt.widget.form.DropDown" style="width: 140px" />
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="lanQosPolicyGenerateDataBtn"
										onClick="lanQosPolicyGenerateData();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft"
					style="padding-top: 10px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1130px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="lanQosPolicyDataStoreTable" data=lanQosPolicyDataTable></span>
						<div style="width: 1130px !important;"
							id="lanQosPolicyTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="lanQosPolicyTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="lanQosPolicyTable" data-dojo-id="lanQosPolicyTable"
							dojoType="xwt.widget.table.Table"
							store="lanQosPolicyDataStoreTable"
							structure="lanQosPolicyColumns"
							style="width: 1130px; height: 220px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>