<%-- sanWwxNConfig.jsp --%>
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
	dojo.require("dojo.fx");

	var noOfWWNN = 0; 
	var noOfWWPN = 0; 
	
	var WWxNAssignmentOrderList = [{label:"Default",value:"default"},{label:"Sequential",value:"sequential"}];
	
	var wwnnConfigDataResponse = ajaxCallGet("getSanWwnnConfigDetails.html", true, "json");
	var sanWWNNPoolDataTable = { items:JSON.parse("[" + wwnnConfigDataResponse + "]") };
	var wwpnConfigDataResponse = ajaxCallGet("getSanWwpnConfigDetails.html", true, "json");
	var sanWWPNPoolDataTable = { items:JSON.parse("[" + wwpnConfigDataResponse + "]") };
	
	// Calculating the max used WWNN
	var count = wwnnConfigDataResponse.length;
	if(count > 0){
		noOfWWNN = extractNumericValueFromAlphaNumericString(JSON.parse(wwnnConfigDataResponse[count-1]).wwnnName);
	}
	
	// Calculating the max used WWPN
	var count1 = wwpnConfigDataResponse.length;
	if(count1 > 0){
		noOfWWPN = extractNumericValueFromAlphaNumericString(JSON.parse(wwpnConfigDataResponse[count1-1]).wwpnName);
	}
	
	var WWNNPoolColumns = [
	                      {label: 'dbID',	attr: 'id',	hidden:true	},
	                      {label: 'Name',attr: 'wwnnName',sortable: true,width: 150,vAlignment: "middle",align:'center',editable: true,
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
	                      {label: 'Description',attr: 'description',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center',editable: true,
			            	   editWidget:{
		                   			widget:xwt.widget.notification.ValidationTextBox,
		                   			options: {
		       	                			trim: true,
		       	                			maxlength:"45",
		       							}
			                   		}   
	                      },
	                      {label: 'From Address',attr: 'fromAddress',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true,
	                    	  editWidget: {
	                         		widget:xwt.widget.notification.ValidationTextBox,
	         							options: {
	         								regExp:REG_EX_WWXN,
	         								required: true,
	         	                			trim: true,
	         								invalidMessage:MSG_WWXN,
	         								id: "wwNNTableRowFromAddress"
	         								}
	                         			}
	                      },
	                      {label: 'To Address',attr: 'toAddress',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true,
	                    	  editWidget: {
	                         		widget:xwt.widget.notification.ValidationTextBox,
	         							options: {
	         								regExp:REG_EX_WWXN,
	         								required: true,
	         	                			trim: true,
	         								invalidMessage:MSG_WWXN,
	         								id: "wwNNTableRowToAddress"
	         								}
	                         			}
	                      },
	                      {label: 'Assignment Order',attr: 'assignmentOrder',width: 150,vAlignment: "middle",align:'center',editable: true,formatter : formatterGetWWxNAssignOrderName,
	         					editWidget: {
	         						widget: xwt.widget.form.DropDown,
	         						options: {options: WWxNAssignmentOrderList}
	         		                   	  }
	                       },
	                      {label: 'Organization',attr: 'organizations',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
	   						editWidget: {
	   						widget: xwt.widget.form.DropDown,
	   						options: {options: getOgranizationDropDown()}
	   		                   	  }
	                      		}
	                      ];
	
	var WWPNPoolColumns = [
	                      {label: 'dbID',	attr: 'id',	hidden:true	},
	                      {label: 'Name',attr: 'wwpnName',sortable: true,width: 150,vAlignment: "middle",align:'center',editable: true,
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
	                      {label: 'Description',attr: 'description',sorted: 'ascending',width: 150,vAlignment: "middle",align:'center',editable: true,
			            	  editWidget:{
		                   			widget:xwt.widget.notification.ValidationTextBox,
		                   			options: {
		       	                			trim: true,
		       	                			maxlength:"45",
		       							}
			                   		}  
	                      },
	                      {label: 'From Address',attr: 'fromAddress',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true,
	                    	editWidget: {
	                         		widget:xwt.widget.notification.ValidationTextBox,
	         							options: {
	         								regExp:REG_EX_WWXN,
	         								required: true,
	         	                			trim: true,
	         								invalidMessage:MSG_WWXN,
	         								id: "wwPNTableRowFromAddress"
	         								}
	                         			} 
	                      },
	                      {label: 'To Address',attr: 'toAddress',sorted: 'ascending',width: 200,vAlignment: "middle",align:'center',editable: true,
	                    	  editWidget: {
	                         		widget:xwt.widget.notification.ValidationTextBox,
	         							options: {
	         								regExp:REG_EX_WWXN,
	         								required: true,
	         	                			trim: true,
	         								invalidMessage:MSG_WWXN,
	         								id: "wwPNTableRowToAddress"
	         								}
	                         			}
	                      },
	                      {label: 'Assignment Order',attr: 'assignmentOrder',width: 150,vAlignment: "middle",align:'center',editable: true,formatter : formatterGetWWxNAssignOrderName,
	         					editWidget: {
	         						widget: xwt.widget.form.DropDown,
	         						options: {options: WWxNAssignmentOrderList}
	         		                   	  }
	                       },
	                      {label: 'Organization',attr: 'organizations',width: 150,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
	   						editWidget: {
	   						widget: xwt.widget.form.DropDown,
	   						options: {options: getOgranizationDropDown()}
	   		                   	  }
	                      		}
	                      ];

function formatterGetWWxNAssignOrderName(data, item, store){
	return returnFormatterDropDownLabel(WWxNAssignmentOrderList,data, item, store);
}
function WWNNPoolGenerateData(){
	var sanWWNNPoolFormObj =  dojo.formToObject("WWNNPoolTableForm");
	var fromAddress =  sanWWNNPoolFormObj.wwnnFromAddress;
	if(dijit.byId("WWNNPoolTableForm").validate()==false || sanWWNNPoolFormObj.noOfWWNNPool == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	// return dataGeneration when skipSan Is checked
	if(dijit.byId("skipSanConfigDataBtn").get("value")){
		displayNotificationAlert(MSG_ALERT_CHECKED_SKIPSAN);
		return false;
	}
	noOfWWNN =  noOfWWNN + 1;
	var wwnnName = "WWNN"+noOfWWNN;
	if(checkTableFieldValueUnique(sanWWNNPoolDataStoreTab,"wwnnName",wwnnName)){
		var defItemx = {
		"id":0,
		"wwnnName":	wwnnName,
		"description": wwnnName,
		"fromAddress": fromAddress,
		"toAddress": 0,
		"noOfAddresses" : parseInt(sanWWNNPoolFormObj.noOfWWNNPool),
		"assignmentOrder":sanWWNNPoolFormObj.assignmentOrder,
		"organizations":parseInt(sanWWNNPoolFormObj.WWNNPoolOrganization)
		};
		sanWWNNPoolTable.store.newItem(defItemx);
	}
	
 	sanWWNNPoolDataStoreTab.save();
 	sanWWNNPoolTable.refresh();
 	
 }
 
function WWPNPoolGenerateData(){
	var sanWWPNPoolFormObj =  dojo.formToObject("WWPNPoolTableForm");
	var fromAddress =  sanWWPNPoolFormObj.wwpnfromAddress;
	if(dijit.byId("WWPNPoolTableForm").validate()==false || sanWWPNPoolFormObj.noOfWWPNPool == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	// return dataGeneration when skipSan Is checked
	if(dijit.byId("skipSanConfigDataBtn").get("value")){
		displayNotificationAlert(MSG_ALERT_CHECKED_SKIPSAN);
		return false;
	}
	noOfWWPN =  noOfWWPN + 1;
	var wwpnName = "WWPN"+noOfWWPN;
	if(checkTableFieldValueUnique(sanWWPNPoolDataStoreTab,"wwpnName",wwpnName)){
		var defItemx = {
		"id":0,
		"wwpnName":	wwpnName,
		"description": wwpnName,
		"fromAddress": fromAddress,
		"toAddress":0,
		"noOfAddresses" : parseInt(sanWWPNPoolFormObj.noOfWWPNPool),
		"assignmentOrder":sanWWPNPoolFormObj.assignmentOrder,
		"organizations":parseInt(sanWWPNPoolFormObj.WWPNPoolOrganization)
		};
		sanWWPNPoolTable.store.newItem(defItemx);
	}
	
 	sanWWPNPoolDataStoreTab.save();
 	sanWWPNPoolTable.refresh();
 	
 }
 
 function slideAccordionForWWxN(clickedDivId,slideId){
	 dojo.query(".hideDiveBeforeSave").style("display", "none");
	 dojo.query(".addRemoveClassToOpnClose").removeClass("divOpenCss").addClass("divClosedCss");
	 dojo.query(".addRemoveClassToOpnClose div").removeClass("fi-accordionOpenIcon").addClass("fi-accordionCloseIcon");
	 
	 dojo.query("#"+clickedDivId).removeClass("divClosedCss").addClass("divOpenCss");
	 dojo.query("#"+clickedDivId+"_title").removeClass("fi-accordionCloseIcon").addClass("fi-accordionOpenIcon");
	 
	 if(dojo.style(dojo.byId(slideId), "display") == "block"){
		 dojo.fx.wipeOut({
		      node: dojo.byId(slideId), 
		      duration: 800, 
		      onEnd: function() {
		    	 // dojo.query("#"+clickedDivId).removeClass("divOpenCss").addClass("divClosedCss");
		      } 
		    }).play();
	 }else{
		 
		 dojo.fx.wipeIn({ 
		      node: dojo.byId(slideId), 
		      duration: 800, 
		      onEnd: function() {
		    	 // dojo.query("#"+clickedDivId).removeClass("divClosedCss").addClass("divOpenCss");
		      } 
		    }).play();
	 }
 }
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 //===========WWNN============
	    		 dojo.connect(dijit.byId("wwnnFromAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("wwnnFromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("wwNNTableRowFromAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("wwNNTableRowFromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("wwNNTableRowToAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("wwNNTableRowToAddress"));
	    		 });
	    		 // ===============WWPN===========
	    		 dojo.connect(dijit.byId("wwpnfromAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("wwpnfromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("wwPNTableRowFromAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("wwPNTableRowFromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("wwPNTableRowToAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("wwPNTableRowToAddress"));
	    		 });
	    		// ======================END=================
	    		 
	    		 slideAccordionForWWxN('clickOnSanWWNNPool','slideUpDownSanWWNNPool');
	    		 // ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	WWNNPoolOrganization.addOption(selectOrganizationArr);
	    		 	WWPNPoolOrganization.addOption(selectOrganizationArr);

	    		 	sanWWNNPoolDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			 var WWNNPoolTablejson = returnChangedDataFromDataStore(this,json);
		    			 var response = ajaxCallPostWithJsonContent("manageSanWwnnConfig.html" , WWNNPoolTablejson, null, "json");
		    			 saveComplete();
		    			 updateZeroIdsInDataStore(response, this);
		    			 updateToAddressInDataStore(response, this);
			     	};

			     	sanWWPNPoolDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			 var WWPNPoolTablejson = returnChangedDataFromDataStore(this,json);
		    			 var response = ajaxCallPostWithJsonContent("manageSanWwpnConfig.html" , WWPNPoolTablejson, null, "json");
		    			 saveComplete();
		    			 updateZeroIdsInDataStore(response, this);
		    			 updateToAddressInDataStore(response, this);
			     	};
			     	
			     	dojo.addOnLoad(function () {
						
			     		sanWWNNPoolTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.wwnnName != newitem.wwnnName){
									if(!checkTableFieldValueUnique(sanWWNNPoolDataStoreTab,"wwnnName",newitem.wwnnName)){
										return false;
									}
								}
								return true;
							}
						};
			     		
			     		sanWWPNPoolTable.validateRow = {
								errorMessage: MSG_DUPLICATE_NAME,
								isValid: function (oldvalues, newitem) {
								if(oldvalues.wwpnName != newitem.wwpnName){
									if(!checkTableFieldValueUnique(sanWWPNPoolDataStoreTab,"wwpnName",newitem.wwpnName)){
										return false;
									}
								}
								return true;
							}
						};
						
					});

	    	 },300);
	 
	     });
 
 // function for Save data to servere
 function saveWWxNConfigData(){
	 if(dojo.style(dojo.byId("slideUpDownSanWWPNPool"), "display") != "block"){
		 document.getElementById("clickOnSanWWPNPool").click();
		 return false;
	 }
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":4,"activeStateSubMenuIndex":2});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
 }
</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1115px;">
				<legend>WWxN</legend>
				<div id="clickOnSanWWNNPool" onclick="slideAccordionForWWxN('clickOnSanWWNNPool','slideUpDownSanWWNNPool');" class="divClosedCss addRemoveClassToOpnClose">
					<div id="clickOnSanWWNNPool_title" class="titleforSlide fi-accordionCloseIcon">WWNN Pool</div>
				</div>
				<div id="slideUpDownSanWWNNPool" class="hideDiveBeforeSave">
					<div class="sanWWxNConfig" style="padding: 0px;">
						<form id="WWNNPoolTableForm" data-dojo-id="WWNNPoolTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table width="1100px">
								<tr>
									<td height="30px">
										<div class="labelspace">
											<label style="float: left;">Organization:</label>
										</div>
									</td>
									<td><select id="WWNNPoolOrganization"
										name="WWNNPoolOrganization" data-dojo-id="WWNNPoolOrganization"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 110px" /></td>
									<td>
										<div class="labelspace">
											<label style="float: left;">From Address:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id=wwnnFromAddress name="wwnnFromAddress"
											data-dojo-id="wwnnFromAddress" style="width: 145px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											value="20:00:00:25:B5:00:00:00"
											data-dojo-props='regExp:REG_EX_WWXN, trim:"true", required:"true",invalidMessage:MSG_WWXN'></div>
									</td>
									<td height="30px">
										<div class="labelspace">
											<label style="float: left;">Number Of Addresses:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="noOfWWNNPool" style="width: 80px;" name="noOfWWNNPool"
											data-dojo-id="noOfWWNNPool"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='regExp:REG_EX_NUMBER_UPTO1000,trim:"true", maxlength:"4", invalidMessage:MSG_UPTO1000'></div>
									</td>
									<td style="height: 40px;">
				        				<div class="labelspace">
											<label style="float:left;">Assignment Order:</label>			
										</div>
				        			</td>
				        			<td>
				        				<select id="wwnnPoolAssignmentOrder" data-dojo-id="wwnnPoolAssignmentOrder" name="assignmentOrder" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 80px">
				        					<option value="default">Default</option>
				        					<option value="sequential">Sequential</option>
				        				</select>
				        			</td>
									<td colspan="2" style="text-align:right;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="WWNNPoolGenerateDataBtn"
											onClick="WWNNPoolGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-left: 5px; padding-top: 3px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1100px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="sanWWNNPoolDataStoreTab" data="sanWWNNPoolDataTable"></span>
							<div style="width: 1100px !important;"
								id="sanWWNNPoolTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="sanWWNNPoolTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="sanWWNNPoolTable" data-dojo-id="sanWWNNPoolTable"
								dojoType="xwt.widget.table.Table" store="sanWWNNPoolDataStoreTab"
								structure="WWNNPoolColumns" style="width: 1100px; height: 160px;"
								showIndex="false" selectMultiple="true" selectAllOption="true"
								selectModel="input" filterOnServer=false></div>
						</div>
					</div>
				</div>
				<div style="clear: both;"></div>
				<!-- WWPN Table -->
				<div id="clickOnSanWWPNPool" onclick="slideAccordionForWWxN('clickOnSanWWPNPool','slideUpDownSanWWPNPool');" class="divClosedCss addRemoveClassToOpnClose" style="margin-top: 5px;">
					<div id="clickOnSanWWPNPool_title" class="titleforSlide fi-accordionCloseIcon">WWPN Pool</div>
				</div>
				<div id="slideUpDownSanWWPNPool" class="hideDiveBeforeSave">
					<div class="sanWWxNConfig" style="padding: 5px 0px 0px 0px;">
						<form id="WWPNPoolTableForm" data-dojo-id="WWPNPoolTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table width="1100px" style="padding-left: 20px;">
								<tr>
									<td height="30px">
										<div class="labelspace">
											<label style="float: left;">Organization:</label>
										</div>
									</td>
									<td><select id="WWPNPoolOrganization"
										name="WWPNPoolOrganization" data-dojo-id="WWPNPoolOrganization"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 110px" /></td>
									<td>
										<div class="labelspace">
											<label style="float: left;">From Address:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id=wwpnfromAddress name="wwpnfromAddress"
											data-dojo-id="fromAddress" style="width: 145px;"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											value="20:00:00:25:B5:00:00:00"
											data-dojo-props='regExp:REG_EX_WWXN, trim:"true",required:"true", invalidMessage:MSG_WWXN'></div>
									</td>
									<td height="30px">
										<div class="labelspace">
											<label style="float: left;">Number Of Addresses:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="noOfWWPNPool" style="width: 80px;" name="noOfWWPNPool"
											data-dojo-id="noOfWWPNPool"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='regExp:REG_EX_NUMBER_UPTO1000,trim:"true", maxlength:"4", invalidMessage:MSG_UPTO1000'></div>
									</td>
									<td style="height: 40px;">
				        				<div class="labelspace">
											<label style="float:left;">Assignment Order:</label>			
										</div>
				        			</td>
				        			<td>
				        				<select id="wwpnPoolAssignmentOrder" data-dojo-id="wwpnPoolAssignmentOrder" name="assignmentOrder" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 80px">
				        					<option value="default">Default</option>
				        					<option value="sequential">Sequential</option>
				        				</select>
				        			</td>
									<td colspan="2" style="text-align:right;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="WWPNPoolGenerateDataBtn"
											onClick="WWPNPoolGenerateData();" type="button">Add</button>
									</td>
	
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-left: 5px; padding-top: 3px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1100px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="sanWWPNPoolDataStoreTab" data="sanWWPNPoolDataTable"></span>
							<div style="width: 1100px !important;"
								id="sanWWPNPoolTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="sanWWPNPoolTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="sanWWPNPoolTable" data-dojo-id="sanWWPNPoolTable"
								dojoType="xwt.widget.table.Table" store="sanWWPNPoolDataStoreTab"
								structure="WWPNPoolColumns" style="width: 1100px; height: 180px;"
								showIndex="false" selectMultiple="true" selectAllOption="true"
								selectModel="input" filterOnServer=false></div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</body>
</html>