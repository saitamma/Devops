<%-- sanAdapterPoliciesConfig.jsp --%>
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
	
	var sanAdapterPoliciesDataTable = {items:JSON.parse("["+ ajaxCallGet("getSanAdapterPoliciesDetails.html", true, "json") +"]")}; 
 
	var sanAdapterPoliciesColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 300,vAlignment: "middle",align:'center',editable: true,
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
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 300,vAlignment: "middle",align:'center',editable: true,
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
                    {label: 'Configure',attr: 'configure',width: 200,vAlignment: "middle",align:'center',formatter: formatterConfigureAdapterPolicy},
                    {label: 'transmitQueues',	attr: 'transmitQueues',	hidden:true	},
                    {label: 'transmitQueuesRingSize',	attr: 'transmitQueuesRingSize',	hidden:true	},
                    {label: 'receiveQueues',	attr: 'receiveQueues',	hidden:true	},
                    {label: 'receiveQueuesRingSize',	attr: 'receiveQueuesRingSize',	hidden:true	},
                    {label: 'scsiIoQueues',	attr: 'scsiIoQueues',	hidden:true	},
                    {label: 'scsiIoQueuesRingSize',	attr: 'scsiIoQueuesRingSize',	hidden:true	},
                    {label: 'fcpErrorRecovery',	attr: 'fcpErrorRecovery',	hidden:true	},
                    {label: 'flogiRetries',	attr: 'flogiRetries',	hidden:true	},
                    {label: 'flogiTimeout',	attr: 'flogiTimeout',	hidden:true	},
                    {label: 'plogiRetries',	attr: 'plogiRetries',	hidden:true	},
                    {label: 'plogiTimeout',	attr: 'plogiTimeout',	hidden:true	},
                    {label: 'portDownTimeout',	attr: 'portDownTimeout',	hidden:true	},
                    {label: 'portDownIoRetry',	attr: 'portDownIoRetry',	hidden:true	},
                    {label: 'linkDownTimeout',	attr: 'linkDownTimeout',	hidden:true	},
                    {label: 'ioThrottleCount',	attr: 'ioThrottleCount',	hidden:true	},
                    {label: 'lunsPerTarget',	attr: 'lunsPerTarget',	hidden:true	},
                    {label: 'interruptMode',	attr: 'interruptMode',	hidden:true	},
                    {label: 'sapDefault',	attr: 'sapDefault',	hidden:true	}
                   
                   ];		


function sanAdapterPoliciesGenerateData(){
	var sanAdapterPoliciesFormObj =  dojo.formToObject("sanAdapterPoliciesTableForm");

	if(dijit.byId("sanAdapterPoliciesTableForm").validate()==false || sanAdapterPoliciesFormObj.sanAdapterPoliciesName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(sanAdapterPoliciesDataStoreTab,"name",sanAdapterPoliciesFormObj.sanAdapterPoliciesName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	}
	var lanNCPDataTableDefItem = {
				"id":0,
				"name":	sanAdapterPoliciesFormObj.sanAdapterPoliciesName,
				"description":	sanAdapterPoliciesFormObj.sanAdapterPoliciesName,
				"organizations": parseInt(sanAdapterPoliciesFormObj.sanAdapterPoliciesOrganization),
				"transmitQueues":1,
				"transmitQueuesRingSize":64,
				"receiveQueues":1,
				"receiveQueuesRingSize":64,
				"scsiIoQueues":1,
				"scsiIoQueuesRingSize":512,
				"fcpErrorRecovery":"disabled",
				"flogiRetries":8,
				"flogiTimeout":4000,
				"plogiRetries":8,
				"plogiTimeout":20000,
				"portDownTimeout":30000,
				"portDownIoRetry":30,
				"linkDownTimeout":30000,
				"ioThrottleCount":256,
				"lunsPerTarget":256,
				"interruptMode":"msi-x",
				"sapDefault" : 0
				
	};
	sanAdapterPoliciesTable.store.newItem(lanNCPDataTableDefItem);
	
	sanAdapterPoliciesDataStoreTab.save();
	sanAdapterPoliciesTable.refresh();
 	dijit.byId("sanAdapterPoliciesName").set("value","");
}

var sanAPSelectedItem;
 function configureAdapterPolicy(e, adapterPolicyId){
		dojo.stopEvent(e);
		sanAdapterPoliciesDataStoreTab.fetch({
	    	  query: {id: adapterPolicyId},
	    	  onComplete: function(items, request){
	    		  sanAPSelectedItem = items[0]; 
	    	  }
	    	});
		sanAdapterPolicyPopupForm.set('value', sanAPSelectedItem);
		sanAdapterPoliciesConfigureDialog.show();
 }
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){   		 	
	    		// ADD dropDown val for Organization //
    		 	var selectOrganizationArr = getOgranizationDropDown();
    		 	sanAdapterPoliciesOrganization.addOption(selectOrganizationArr);
	   		 	
		   		sanAdapterPoliciesDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminAuthDomainTable1json = returnChangedDataFromDataStore(this,json);
	    			
	    			var response = ajaxCallPostWithJsonContent("manageSanAdapterPolicies.html" , adminAuthDomainTable1json, null, "json");
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
				
		    	// validation on edit row
			     sanAdapterPoliciesTable.validateRow = {
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(oldvalues.sapDefault[0] == true ){
										this.errorMessage = MSG_CANT_CHANGE_DEFAULT;
										return false;
						    		 }
									if(!checkTableFieldValueUnique(sanAdapterPoliciesDataStoreTab,"name",newitem.name)){
										this.errorMessage = MSG_DUPLICATE_NAME;
										return false;
									}
								}else if(oldvalues.organizations != newitem.organizations){
									this.errorMessage = MSG_CANT_CHANGE_DEFAULT_ORG;
									return false;
								}
							return true;
						}
					 };
		    	
			     var bootPolicySaveBtn = sanAdapterPoliciesConfigureDialog.buttonGroup.getItemAt(0);
			     dojo.connect(bootPolicySaveBtn,"onClick",function(){
			    	 
			    	 if(dijit.byId("sanAdapterPolicyPopupForm").validate()==false ){
			    			return false;
			    		}
					 var editedRowPopupVal = dojo.formToObject("sanAdapterPolicyPopupForm");
					 updateDataStoreWithJsonObj(sanAdapterPoliciesDataStoreTab, sanAPSelectedItem, editedRowPopupVal);
			    	 sanAdapterPoliciesConfigureDialog.hide();
			     });
			     
			     // default value cant be deleted.
			     dojo.connect(dijit.byId("sanAdapterPoliciesTable"),"onSelect",function(item, row){
			    	
		    		 if(item.sapDefault[0] == true){
		    			 this.deselect(item);
		    		 }
		    	 });
		    
	    	 },1000);
	 
	     });

// function for Save data to servere
function saveSanAdapterPoliciesOnNext(){

	var saveNavState = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":4,"activeStateSubMenuIndex":3});
	response = ajaxCallPostWithJsonContent("setWizardStatus.html" , saveNavState, null, "text");
	return true;
}
</script>
</head>
<body>
	<div id="parentDiv">
		<div class="floatleft">
			<div class="floatleft">
				<fieldset class="heightOfFieldset" style="width: 1130px;">
					<legend>Fibre Channel Adapter Policies Configuration</legend>
					<div class="commonclassForFormFields">
						<form id="sanAdapterPoliciesTableForm"
							data-dojo-id="sanAdapterPoliciesTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td>
								<select id="sanAdapterPoliciesOrganization"
									name="sanAdapterPoliciesOrganization"
									data-dojo-id="sanAdapterPoliciesOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" />
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="sanAdapterPoliciesName"
											name="sanAdapterPoliciesName" style="width: 135px;"
											data-dojo-id="sanAdapterPoliciesName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="sanAdapterPoliciesGenerateDataBtn"
											onClick="sanAdapterPoliciesGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1130px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="sanAdapterPoliciesDataStoreTab"
								data="sanAdapterPoliciesDataTable"></span>
							<div style="width: 1130px !important;"
								id="sanAdapterPoliciesTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="sanAdapterPoliciesTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="sanAdapterPoliciesTable"
								data-dojo-id="sanAdapterPoliciesTable"
								dojoType="xwt.widget.table.Table"
								store="sanAdapterPoliciesDataStoreTab"
								structure="sanAdapterPoliciesColumns"
								style="width: 1130px; height: 260px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	
	<div id="sanAdapterPoliciesConfigureDialog" button2Label="Close" button1Label="Save"
              data-dojo-id="sanAdapterPoliciesConfigureDialog" title=""
              dojoType="xwt.widget.layout.Dialog" closable="true"
              style="display: none;">
              <div style="height: 35rem;">
              	<form id="sanAdapterPolicyPopupForm" data-dojo-id="sanAdapterPolicyPopupForm"  data-dojo-type="xwt.widget.notification.Form" name="tableForm">
                     <fieldset style="height: 330px; width: 800px; margin: 0px; margin-bottom: 0px;">
                           <legend>Resources / Options</legend>

                           <div class="sanAdapterPolicyPop">
                                         <table style="">
                                                <tr style="height: 50px;">
                                                       <td style="width: 156px;">
                                                              <div class="labelspace">
                                                                     <label style="float: left;">Transmit Queues:</label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                              <div id="sanTransmitQueues" name="transmitQueues" style="width:79px"data-dojo-id="sanTransmitQueues"
                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox" readOnly=true></div>
                                                       </td>
                                                        <td>
                                                              <div class="labelspace">
                                                                     <label style="float: left;">Receive Queues:</label>
                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanReceiveQueues" name="receiveQueues" style="width:79px"data-dojo-id="sanReceiveQueues"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox" readOnly=true ></div>
	                                                  </td>
	                                                   <td>
                                                              <div class="labelspace">
                                                                     <label style="float: left;">SCSI I/O Queues<em>*</em>:</label>
                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanScsiIoQueues" name="scsiIoQueues" style="width:79px"data-dojo-id="sanScsiIoQueues"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_8,trim:"true", maxlength:"1", invalidMessage:MSG_BET_1_8,required:true'></div>
	                                                       </td>
                                              </tr>
                                              <tr  style="height: 50px;">
                                              			<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Transmit Ring Size<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanTransmitQueuesRingSize" name="transmitQueuesRingSize" style="width:79px"
	                                                                     data-dojo-id="sanTransmitQueuesRingSize"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_64_TO_128,trim:"true", maxlength:"3", invalidMessage:MSG_BET_64_TO_128,required:true'></div>
	                                                       </td>
                                                  		<td style="width: 166px;">
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Receive Ring Size<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanReceiveQueuesRingSize" name="receiveQueuesRingSize" style="width:79px"
	                                                                     data-dojo-id="sanReceiveQueuesRingSize"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_64_TO_128,trim:"true", maxlength:"3", invalidMessage:MSG_BET_64_TO_128,required:true'></div>
	                                                       </td>
	                                                       <td style="width: 166px;">
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">SCSI Ring Size<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanScsiIoQueuesRingSize" name="scsiIoQueuesRingSize" style="width:79px"
	                                                                     data-dojo-id="sanScsiIoQueuesRingSize"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_64_TO_512,trim:"true", maxlength:"3", invalidMessage:MSG_BET_64_TO_512,required:true'></div>
	                                                       </td>
                                                  </tr>
                                         </table>
                                         <div style="border-top: 1px solid #CCC;"></div>
                                         <table >
                                        		 <tr  style="height: 50px;">
                                              			<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">FCP Error Recovery:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                       		<select id="sanFcpErrorRecovery" name="fcpErrorRecovery"
																	data-dojo-id="sanFcpErrorRecovery" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>
	                                                       </td>
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Flogi Retries<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanFlogiRetries" name="flogiRetries" style="width:79px"
	                                                                     data-dojo-id="sanFlogiRetries"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER,trim:"true", maxlength:"9", invalidMessage:MSG_NUMBER,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Flogi Timeout(ms)<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanFlogiTimeout" name="flogiTimeout" style="width:79px"
	                                                                     data-dojo-id="sanFlogiTimeout"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1000_TO_255000,trim:"true", maxlength:"6", invalidMessage:MSG_BET_1000_TO_255000,required:true'></div>
	                                                       </td>
                                                  </tr>
                                                   <tr  style="height: 50px;">
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Plogi Retries<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanPlogiRetries" name="plogiRetries" style="width:79px"
	                                                                     data-dojo-id="sanPlogiRetries"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_UPTO255,trim:"true", maxlength:"3", invalidMessage:MSG_UPTO255,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Plogi Timeout(ms)<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanPlogiTimeout" name="plogiTimeout" style="width:79px"
	                                                                     data-dojo-id="sanPlogiTimeout"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1000_TO_255000,trim:"true", maxlength:"6", invalidMessage:MSG_BET_1000_TO_255000,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Port Down Timeout(ms)<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanPortDownTimeout" name="portDownTimeout" style="width:79px"
	                                                                     data-dojo-id="sanPortDownTimeout"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_0_TO_240000,trim:"true", maxlength:"6", invalidMessage:MSG_BET_0_TO_240000,required:true'></div>
	                                                       </td>
                                                  </tr>
                                                  <tr  style="height: 50px;">
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Port Down I/O Retry<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanPortDownIoRetry" name="portDownIoRetry" style="width:79px"
	                                                                     data-dojo-id="sanPortDownIoRetry"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_UPTO255,trim:"true", maxlength:"3", invalidMessage:MSG_UPTO255,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Link Down Timeout(ms)<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanLinkDownTimeout" name="linkDownTimeout" style="width:79px"
	                                                                     data-dojo-id="sanLinkDownTimeout"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_0_TO_240000,trim:"true", maxlength:"6", invalidMessage:MSG_BET_0_TO_240000,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">IO Throttle Count<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanIoThrottleCount" name="ioThrottleCount" style="width:79px"
	                                                                     data-dojo-id="sanIoThrottleCount"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_256_TO_1024,trim:"true", maxlength:"4", invalidMessage:MSG_BET_256_TO_1024,required:true'></div>
	                                                       </td>
                                                  </tr>
                                                  <tr  style="height: 50px;">
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">MAX LUNs Per Target<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="sanLunsPerTarget" name="lunsPerTarget" style="width:79px"
	                                                                     data-dojo-id="sanLunsPerTarget"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_1024,trim:"true", maxlength:"4", invalidMessage:MSG_BET_1_TO_1024,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Interrupt Mode:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="sanInterruptMode" name="interruptMode"
																	data-dojo-id="sanInterruptMode" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 79px">
																	<option value="msi-x">MSI X&nbsp;&nbsp;&nbsp;&nbsp;</option>
																	<option value="msi">MSI</option>
																	<option value="intx">IN Tx</option>
																	</select>
	                                                       </td>
                                                  </tr>
                                         
                                         </table>
                           </div>
                     </fieldset> 
                    </form>           
              </div>
       </div>
	
	
</body>
</html>
