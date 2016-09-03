<%-- serversBiosPolicyConfig.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#serversBiosPolicyConfigureDialog .xwtAccordionStatusContainer, #serversBiosPolicyConfigureDialog .buttonPane{
	display: none;
}
</style>
<script type="text/javascript">
	dojo.require("xwt.widget.table.Table");
	dojo.require("xwt.widget.table.Toolbar");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.Form");
	dojo.require("xwt.widget.notification.ValidationTextBox");
	dojo.require("xwt.widget.notification.Form");
	dojo.require("xwt.widget.layout.AccordionContainer");
	dojo.require("xwt.widget.layout.AccordionPane");
	dojo.require("xwt.widget.form.DropDown");
	dojo.require('dojox.fx.scroll');
	
	var serversBiosPolicyDataTable = {items:JSON.parse("["+ ajaxCallGet("getserversBiosPolicyDetails.html", true, "json") +"]")}; 
 
	var serversBiosPolicyColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 250,vAlignment: "middle",align:'center',editable: true,
                   	editWidget: {widget:xwt.widget.notification.ValidationTextBox,
                   		options: {
                   			trim: true,
                   			regExp:REG_EX_NAME,
                   			required: true,
                   			maxlength:"16",
                   			invalidMessage: MSG_NAME
       					}
         				}	
                   },
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 250,vAlignment: "middle",align:'center',editable: true,
	             		editWidget:{
                 			widget:xwt.widget.notification.ValidationTextBox,
                 			options: {
  	                			trim: true,
  	                			maxlength:"256",
     						}
	                   	}	
                  },
                  {label: 'Organization',attr: 'organizations',width: 250,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
      					editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: getOgranizationDropDown()}
      		                   	  }
                    },
                    {label: 'Configure',attr: 'configure',width: 170,vAlignment: "middle",align:'center',formatter: formatterConfigureBiosPolicy}
                   
                   ];		


function serversBiosPolicyGenerateData(){
	var serversBiosPolicyFormObj =  dojo.formToObject("serversBiosPolicyTableForm");

	if(dijit.byId("serversBiosPolicyTableForm").validate()==false || serversBiosPolicyFormObj.serversBiosPolicyName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(serversBiosPolicyDataStoreTab,"name",serversBiosPolicyFormObj.serversBiosPolicyName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var TableDefItem = {
				"id":0,
				"name":	serversBiosPolicyFormObj.serversBiosPolicyName,
				"description":	serversBiosPolicyFormObj.serversBiosPolicyName,
				"organizations": parseInt(serversBiosPolicyFormObj.serversBiosPolicyOrganization),
	};
	serversBiosPolicyTable.store.newItem(TableDefItem);
	
	serversBiosPolicyDataStoreTab.save();
	serversBiosPolicyTable.refresh();
 	dijit.byId("serversBiosPolicyName").set("value","");
}

 function configureBiosPolicy(e, biosPolicyId){
		dojo.stopEvent(e);
	var getBiosPolicy = ajaxCallPostWithJsonContent("getSingleBiosPolicy.html",parseInt(biosPolicyId), null, "json");
	var biosPolicyJsonObj = JSON.parse(getBiosPolicy[0]);
	accordionPanePopupForm.set("value",biosPolicyJsonObj);
	dojo.byId("placeNameOfBiosPolicy").innerHTML = dijit.byId("hiddenBiosPolicyName").get("value");
	if(biosPolicyJsonObj.rebootOnChange == "yes"){
		serversBiosRebootOnChange.set("checked",true);
	}else{
		serversBiosRebootOnChange.set("checked",false);
	}
	baseAccordionJsId.startup()
	for(i=1; i<=11; i++){
		dijit.byId("accordionpane"+i).removeChild(dijit.byId("accordionpane"+i+"_cancelButton"));
		dijit.byId("accordionpane"+i+"_finishButton").set("disabled",false);
	}
	serversBiosPolicyConfigureDialog.show();
 }
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){
	    		
	    		dojo.connect(dijit.byId("accordionpane1"),"beforeNext",function(){setTimeout(function(){scrollToLast(1);},1000);});
	    		dojo.connect(dijit.byId("accordionpane2"),"beforeNext",function(){setTimeout(function(){scrollToLast(2);},1000);});
	    		dojo.connect(dijit.byId("accordionpane3"),"beforeNext",function(){setTimeout(function(){scrollToLast(3);},1000);});
	    		dojo.connect(dijit.byId("accordionpane4"),"beforeNext",function(){setTimeout(function(){scrollToLast(4);},1000);});
	    		dojo.connect(dijit.byId("accordionpane5"),"beforeNext",function(){setTimeout(function(){scrollToLast(5);},1000);});
	    		dojo.connect(dijit.byId("accordionpane6"),"beforeNext",function(){setTimeout(function(){scrollToLast(6);},1000);});
	    		dojo.connect(dijit.byId("accordionpane7"),"beforeNext",function(){setTimeout(function(){scrollToLast(7);},1000);});
	    		dojo.connect(dijit.byId("accordionpane8"),"beforeNext",function(){setTimeout(function(){scrollToLast(8);},1000);});
	    		dojo.connect(dijit.byId("accordionpane9"),"beforeNext",function(){setTimeout(function(){scrollToLast(9);},1000);});
	    		dojo.connect(dijit.byId("accordionpane10"),"beforeNext",function(){setTimeout(function(){scrollToLast(10);},1000);});
	    		dojo.connect(dijit.byId("accordionpane11"),"beforeNext",function(){setTimeout(function(){scrollToLast(11);},1000);});
	    		// ADD dropDown val for Organization //
    		 	var selectOrganizationArr = getOgranizationDropDown();
    		 	serversBiosPolicyOrganization.addOption(selectOrganizationArr);
	   		 	
		   		serversBiosPolicyDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var serversBiosPolicyTable1json = returnChangedDataFromDataStore(this,json);
	    			console.log(serversBiosPolicyTable1json);
	    			var response = ajaxCallPostWithJsonContent("manageServersBiosPolicyTableSave.html" , serversBiosPolicyTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     serversBiosPolicyTable.validateRow = {
			    			errorMessage: MSG_DUPLICATE_NAME,
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversBiosPolicyDataStoreTab,"name",newitem.name)){
										return false;
									}
								}
							return true;
						}
					 };
		    	//RAS Options code
		    	dojo.connect(serversBiosMemoryRasConfig,"onChange",function(){
		    		if(this.value == "sparing"){
		    			serversBiosSparingMode.set("readOnly",false);
		    			serversBiosMirroringMode.set("readOnly",true);
		    			serversBiosMirroringMode.reset();
		    		}else if(this.value == "mirroring"){
		    			serversBiosMirroringMode.set("readOnly",false);
		    			serversBiosSparingMode.set("readOnly",true);
		    			serversBiosSparingMode.reset();
		    		}else{
		    			serversBiosMirroringMode.set("readOnly",true);
		    			serversBiosSparingMode.set("readOnly",true);
		    			serversBiosSparingMode.reset();
		    			serversBiosMirroringMode.reset();
		    		}
		    	});
		 		//Boot Options code
			    dojo.connect(serversBiosIntelEntrySasRaid,"onChange",function(){
			    	if(this.value=="disabled"){
			    		serversBiosIntelEntrySasRaidModule.set("readOnly",true);
			    		serversBiosIntelEntrySasRaidModule.reset();
			    	}else{
			    		serversBiosIntelEntrySasRaidModule.set("readOnly",false);
			    	}
			    });
			    // Server Management code
			    dojo.connect(serversBiosOsBootWatchdogTimer,"onChange",function(){
			    	if(this.value != "enabled"){
			    		serversBiosOsBootWatchdogTimerTimeoutPolicy.set("readOnly",true);
			    		serversBiosOsBootWatchdogTimerTimeout.set("readOnly",true);
			    		serversBiosOsBootWatchdogTimerTimeoutPolicy.reset();
				    	serversBiosOsBootWatchdogTimerTimeout.reset();
			    	}else{
			    		serversBiosOsBootWatchdogTimerTimeoutPolicy.set("readOnly",false);
			    		serversBiosOsBootWatchdogTimerTimeout.set("readOnly",false);
			    	}
			    	
			    });
			    dojo.connect(serversBiosConsoleRedirection,"onChange",function(){
			    	if(this.value == "disabled"){
			    		serversBiosBaudRate.set("readOnly",true);
			    		serversBiosBaudRate.reset();
			    	}else{
			    		serversBiosBaudRate.set("readOnly",false);
			    	}
			    });
			    
	    	 },1000);
	 
	     });

function saveBiosPolicyEachStageOnFinish(){
	var formObj =  {"addOrUpdate":[dojo.formToObject("accordionPanePopupForm")]};
	formObj["addOrUpdate"][0].id = parseInt(formObj["addOrUpdate"][0].id);
	formObj["addOrUpdate"][0].organizations = parseInt(formObj["addOrUpdate"][0].organizations);
	var response = ajaxCallPostWithJsonContent("manageServersBiosPolicy.html" , JSON.stringify(formObj), null, "json");
	serversBiosPolicyConfigureDialog.hide();
}

function scrollToLast(accordionFoucs) {
    dojox.fx.smoothScroll({
        node: dojo.query('#accordionpane'+accordionFoucs+" .dijitTitlePaneTitle")[0],
        win: dojo.byId('baseAccordionId')
    }).play();
}

// function for Save data to servere
function saveserversBiosPolicyOnNext(){

	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":7});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
	return true;
}
</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft">
			<div class="floatleft">
				<fieldset class="heightOfFieldset" style="width: 1050px;">
					<legend>BIOS Policies Configuration</legend>
					<div style="margin: 5px;">
					<div class="commonclassForFormFields">
						<form id="serversBiosPolicyTableForm"
							data-dojo-id="serversBiosPolicyTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td>
								<select id="serversBiosPolicyOrganization"
									name="serversBiosPolicyOrganization"
									data-dojo-id="serversBiosPolicyOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="160"
									style="width: 140px" />
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="serversBiosPolicyName"
											name="serversBiosPolicyName" style="width: 135px;"
											data-dojo-id="serversBiosPolicyName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="serversBiosPolicyGenerateDataBtn"
											onClick="serversBiosPolicyGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-top: 20px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1040px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="serversBiosPolicyDataStoreTab"
								data="serversBiosPolicyDataTable"></span>
							<div style="width: 1040px !important;"
								id="serversBiosPolicyTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="serversBiosPolicyTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="serversBiosPolicyTable"
								data-dojo-id="serversBiosPolicyTable"
								dojoType="xwt.widget.table.Table"
								store="serversBiosPolicyDataStoreTab"
								structure="serversBiosPolicyColumns"
								style="width: 1040px; height: 240px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	
	<div id="serversBiosPolicyConfigureDialog" button2Label="Close" button1Label="Save"
              data-dojo-id="serversBiosPolicyConfigureDialog" title=""
              dojoType="xwt.widget.layout.Dialog" closable="true"
              style="display: none;">
              <div style="height: 57rem;">
              		<div class="pageHeader"><h2><span id="placeNameOfBiosPolicy"></span></h2></div>
                        <div class="serversBiosPolicyPop" id="mainAccordionContent" style="padding-top: 10px;overflow: hidden;">
                           	<form style="padding: 0px;" class="commonclassForFormFields" id="accordionPanePopupForm" data-dojo-id="accordionPanePopupForm"  data-dojo-type="xwt.widget.notification.Form" name="accordionTableForm">
                                 <input id="hiddenBiosPolicyId" name="id" data-dojo-id="hiddenBiosPolicyId" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
                                 <input id="hiddenBiosPolicyName" name="name" data-dojo-id="hiddenBiosPolicyName" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
                                 <input id="hiddenBiosPolicyDesc" name="description" data-dojo-id="hiddenBiosPolicyDesc" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
                                 <input id="hiddenBiosPolicyOrg" name="organizations" data-dojo-id="hiddenBiosPolicyOrg" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
                                 
								<div id="baseAccordionId" data-dojo-id="baseAccordionJsId" dojoType="xwt.widget.layout.AccordionContainer" style="height: 480px; min-width: 1200px; overflow: auto;"
									parentId="mainAccordionContent"
									doDeploy="saveBiosPolicyEachStageOnFinish();">
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane1" title="Main">
										<jsp:include page="biosMain.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane2" title="Processor">
										<jsp:include page="biosProcessor.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane3" title="Intel Directed IO">
										<jsp:include page="biosIntelDirectedIO.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane4" title="RAS Memory">
											<jsp:include page="biosRasMemory.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane5" title="Serial Port">
										<div class="accordionFieldsBorder">
										<table>
												<tr>
													<td>
														<div class="labelspace">
															<label style="float: left;padding-left:0px;">Serial Port A:</label>
														</div>
													</td>
													<td>
														<select id="serversBiosSerialPortA" name="serialPortA"
														data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
															<option value="platform-default">Platform Default</option>
															<option value="disabled">Disabled</option>
															<option value="enabled">Enabled</option>
														</select>
														</td>
													</tr>
											</table>
										</div>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane6" title="USB">
										<jsp:include page="biosUsbStage.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane7" title="PCI">
										<jsp:include page="biosPciStage.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane8" title="QPI">
										<div class="accordionFieldsBorder">
											<table>
												<tr>
													<td>
														<div class="labelspace">
															<label style="float: left;padding-left:0px;">QPI Link Frequency Select:</label>
														</div>
													</td>
													<td>
													<select id="serversBiosQpiLinkFreq" name="qpiLinkFrequencySelect"
														data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
															<option value="platform-default">Platform Default</option>
															<option value="64">64</option>
															<option value="72">72</option>
															<option value="80">80</option>
															<option value="auto">Auto</option>
														</select>
														</td>
													</tr>
											</table>
										</div>	
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane9" title="LOM and PCIe Slots">
										<jsp:include page="biosLomAndPcieSlotsStage.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane10" title="Boot Options">
										<jsp:include page="biosBootOptionsStage.jsp"></jsp:include>
									</div>
									<div dojoType="xwt.widget.layout.AccordionPane" id="accordionpane11" title="Server Management">
										<jsp:include page="biosServerManagementStage.jsp"></jsp:include>
									</div>
								</div>
							</form>
                      </div>
              </div>
       </div>
	
	
</body>
</html>
