<%-- lanEthernetAdapterPolicy.jsp --%>
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
  
var lanEthernetAdapterPoliciesDataTable = {items:JSON.parse("["+ ajaxCallGet("getLanEthernetAdapterPoliciesDetails.html", true, "json") +"]")};

var lanEthernetAdapterPoliciesColumns = [
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
                                  {label: 'completionQueues',	attr: 'completionQueues',	hidden:true	},
                                  {label: 'completionQueuesInterrupts',	attr: 'completionQueuesInterrupts',	hidden:true	},
                                  {label: 'transmitChecksumOffload',	attr: 'transmitChecksumOffload',	hidden:true	},
                                  {label: 'receiveChecksumOffload',	attr: 'receiveChecksumOffload',	hidden:true	},
                                  {label: 'tcpSegmentationOffload',	attr: 'tcpSegmentationOffload',	hidden:true	},
                                  {label: 'tcpLargeReceiveOffload',	attr: 'tcpLargeReceiveOffload',	hidden:true	},
                                  {label: 'receiveSideScaling',	attr: 'receiveSideScaling',	hidden:true	},
                                  {label: 'acceleratedReceiveFlowSteering',	attr: 'acceleratedReceiveFlowSteering',	hidden:true	},
                                  {label: 'failbackTimeout',	attr: 'failbackTimeout',	hidden:true	},
                                  {label: 'interruptMode',	attr: 'interruptMode',	hidden:true	},
                                  {label: 'interruptCoalescingType',	attr: 'interruptCoalescingType',	hidden:true	},
                                  {label: 'interruptTimer',	attr: 'interruptTimer',	hidden:true	},
                                  {label: 'leapDefault',	attr: 'leapDefault',	hidden:true	}
                                  
                                 ];

function lanEthernetAdapterPoliciesGenerateData(){	
	var lanEthernetAdapterPoliciesFormObj =  dojo.formToObject("lanEthernetAdapterPoliciesTableForm");	
	if(dijit.byId("lanEthernetAdapterPoliciesTableForm").validate()==false || lanEthernetAdapterPoliciesFormObj.lanEthernetAdapterPoliciesName == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if(!checkTableFieldValueUnique(lanEthernetAdapterPoliciesDataStoreTab,"name",lanEthernetAdapterPoliciesFormObj.lanEthernetAdapterPoliciesName)){
		displayNotificationAlert(MSG_DUPLICATE_NAME,"warning");
		return false;
	} 	
	var lanEthernetAdapterPolociesDataTableDefItem = {
			"id":0,
			"name":	lanEthernetAdapterPoliciesFormObj.lanEthernetAdapterPoliciesName,
			"description":	lanEthernetAdapterPoliciesFormObj.lanEthernetAdapterPoliciesName,
			"organizations": parseInt(lanEthernetAdapterPoliciesFormObj.lanEthernetAdapterPoliciesOrganization),
			"transmitQueues":1,
			"transmitQueuesRingSize":256,   
			"receiveQueues":1,
			"receiveQueuesRingSize":512,
			"completionQueues":2,
			"completionQueuesInterrupts":4,
			"transmitChecksumOffload":"enabled",
			"receiveChecksumOffload":"enabled",
			"tcpSegmentationOffload":"enabled",
			"tcpLargeReceiveOffload":"enabled",
			"receiveSideScaling":"disabled",
			"acceleratedReceiveFlowSteering":"disabled",
			"failbackTimeout":5,
			"interruptMode":"msi-x",  
			"interruptCoalescingType":"min",
			"interruptTimer":125,
			"leapDefault":0
	};	
	lanEthernetAdapterPoliciesTable.store.newItem(lanEthernetAdapterPolociesDataTableDefItem);
	lanEthernetAdapterPoliciesDataStoreTab.save();
	lanEthernetAdapterPoliciesTable.refresh();	
 	dijit.byId("lanEthernetAdapterPoliciesName").set("value","");    
}
var lanEAPSelectedItem;
function configureAdapterPolicy(e, ethernetAdapterPolicyId){
		dojo.stopEvent(e);
		lanEthernetAdapterPoliciesDataStoreTab.fetch({
	    	  query: {id: ethernetAdapterPolicyId},
	    	  onComplete: function(items, request){  
	    		  lanEAPSelectedItem = items[0]; 
	    	  }
	    	});
		lanEthernetAdapterPolicyPopupForm.set('value', lanEAPSelectedItem);
		lanEthernetAdapterPoliciesConfigureDialog.show();
}
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	setTimeout(function(){
	    		// ADD dropDown val for Organization //
    		 	var selectOrganizationArr = getOgranizationDropDown();
    		 	lanEthernetAdapterPoliciesOrganization.addOption(selectOrganizationArr);    		 	
    		 	lanEthernetAdapterPoliciesDataStoreTab._saveCustom = function(saveComplete, saveFailed){
	    			var adminAuthDomainTable1json = returnChangedDataFromDataStore(this,json);	    			
	    			var response = ajaxCallPostWithJsonContent("manageLanEthernetAdapterPolicies.html" , adminAuthDomainTable1json, null, "json");	    			
	    			saveComplete();
	    			updateZeroIdsInDataStore(response, this);
			     };
			     
			 	 // validation on edit row			 
			     lanEthernetAdapterPoliciesTable.validateRow = {
							isValid: function (oldvalues, newitem) {
								if(oldvalues.name != newitem.name){
									if(oldvalues.leapDefault[0] == true ){
										this.errorMessage = MSG_CANT_CHANGE_DEFAULT;
										return false;
						    		 }
									else if(!checkTableFieldValueUnique(lanEthernetAdapterPoliciesDataStoreTab,"name",newitem.name)){
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
			     var bootPolicySaveBtn = lanEthernetAdapterPoliciesConfigureDialog.buttonGroup.getItemAt(0);			     
			     dojo.connect(bootPolicySaveBtn,"onClick",function(){			    	 
			    	 if(dijit.byId("lanEthernetAdapterPolicyPopupForm").validate()==false ){
			    			return false;
			    		}
					 var editedRowPopupVal = dojo.formToObject("lanEthernetAdapterPolicyPopupForm");  
					 updateDataStoreWithJsonObj(lanEthernetAdapterPoliciesDataStoreTab, lanEAPSelectedItem, editedRowPopupVal);
			    	 lanEthernetAdapterPoliciesConfigureDialog.hide();
			     });
			  	 // default value can not be deleted.
			     dojo.connect(dijit.byId("lanEthernetAdapterPoliciesTable"),"onSelect",function(item, row){			    	 
			    	  if(item.leapDefault[0] == true){        
		    			 this.deselect(item);
		    		 }  
		    	 });
			  	 
	    	 },1000); 
	    });
//function for Save data to server
function saveLanEthernetAdapterPoliciesOnNext(){
	var saveNavState = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":5});
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
					<legend>Ethernet Adapter Policies Configuration</legend>
					<div class="commonclassForFormFields">
						<form id="lanEthernetAdapterPoliciesTableForm"
							data-dojo-id="lanEthernetAdapterPoliciesTableForm"
							data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<table>
								<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td>
								<select id="lanEthernetAdapterPoliciesOrganization"
									name="lanEthernetAdapterPoliciesOrganization"
									data-dojo-id="lanEthernetAdapterPoliciesOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" />
									</td>
									<td>
										<div class="labelspace">
											<label style="float: left;">Name:<em>*</em></label>
										</div>
									</td>
									<td>
										<div id="lanEthernetAdapterPoliciesName"
											name="lanEthernetAdapterPoliciesName" style="width: 135px;"
											data-dojo-id="lanEthernetAdapterPoliciesName"
											data-dojo-type="xwt.widget.notification.ValidationTextBox"
											data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
									</td>
									<td style="padding-left: 10px;">
										<button data-dojo-type="dijit/form/Button"
											data-dojo-id="lanEthernetAdapterPoliciesGenerateDataBtn"  
											onClick="lanEthernetAdapterPoliciesGenerateData();" type="button">Add</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="floatleft" style="padding-top: 10px;">
						<div dojotype="dijit.layout.ContentPane" region="left"
							style="width: 1130px; overflow: hidden;" splitter="true">
							<span dojoType="dojo.data.ItemFileWriteStore"
								jsId="lanEthernetAdapterPoliciesDataStoreTab"
								data="lanEthernetAdapterPoliciesDataTable"></span>
							<div style="width: 1130px !important;"
								id="lanEthernetAdapterPoliciesTableTollBar"
								dojoType="xwt.widget.table.ContextualToolbar"
								tableId="lanEthernetAdapterPoliciesTable" quickFilter="false">
								<div dojoType="xwt.widget.table.ContextualButtonGroup"
									showButtons="delete"></div>
							</div>
							<div id="lanEthernetAdapterPoliciesTable"
								data-dojo-id="lanEthernetAdapterPoliciesTable"
								dojoType="xwt.widget.table.Table"
								store="lanEthernetAdapterPoliciesDataStoreTab"
								structure="lanEthernetAdapterPoliciesColumns"
								style="width: 1130px; height: 260px;" selectMultiple="true"
								selectAllOption="true" showIndex="false" selectModel="input"
								filterOnServer=false></div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	
	<div id="lanEthernetAdapterPoliciesConfigureDialog" button2Label="Close" button1Label="Save"
              data-dojo-id="lanEthernetAdapterPoliciesConfigureDialog" title=""
              dojoType="xwt.widget.layout.Dialog" closable="true"
              style="display: none;">
              <div style="height: 35rem;">
              	<form id="lanEthernetAdapterPolicyPopupForm" data-dojo-id="lanEthernetAdapterPolicyPopupForm"  data-dojo-type="xwt.widget.notification.Form" name="tableForm">
                     <fieldset style="height: 330px; width: 800px; margin: 0px; margin-bottom: 0px;">
                           <legend>Resources / Options</legend>

                           <div class="lanEthernetAdapterPolicyPop">
                                         <table style="">
                                                <tr style="height: 50px;">
                                                       <td style="width: 156px;">
                                                              <div class="labelspace">
                                                                     <label style="float: left;">Transmit Queues<em>*</em>:</label>
                                                              </div>
                                                       </td>
                                                       <td>
                                                              <div id="TransmitQueues" name="transmitQueues" style="width:79px"data-dojo-id="TransmitQueues"
                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                 data-dojo-props='regExp:REG_EX_NUMBER_1_TO_256,trim:"true", maxlength:"3", invalidMessage:MSG_BET_1_TO_256,required:true'></div>
                                                       </td>
                                                        <td>
                                                              <div class="labelspace">
                                                                     <label style="float: left;">Receive Queues<em>*</em>:</label>
                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="ReceiveQueues" name="receiveQueues" style="width:79px"data-dojo-id="ReceiveQueues"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_256,trim:"true", maxlength:"3", invalidMessage:MSG_BET_1_TO_256,required:true'></div>
	                                                  </td>
	                                                   <td>
                                                              <div class="labelspace">  
                                                                     <label style="float: left;">Completion Queues<em>*</em>:</label>  
                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="lanCompletionQueues" name=completionQueues style="width:79px"data-dojo-id="lanCompletionQueues"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_512,trim:"true", maxlength:"3", invalidMessage:MSG_BET_1_TO_512,required:true'></div>
	                                                       </td>
                                              </tr>
                                              <tr  style="height: 50px;">
                                              			<td>  
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Transmit Ring Size<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="TransmitQueuesRingSize" name="transmitQueuesRingSize" style="width:79px"
	                                                                     data-dojo-id="TransmitQueuesRingSize"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_64_TO_4096,trim:"true", maxlength:"4", invalidMessage:MSG_BET_64_TO_4096,required:true'></div>
	                                                       </td>
                                                  		<td style="width: 166px;">
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Receive Ring Size<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="ReceiveQueuesRingSize" name="receiveQueuesRingSize" style="width:79px"
	                                                                     data-dojo-id="ReceiveQueuesRingSize"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_64_TO_4096,trim:"true", maxlength:"4", invalidMessage:MSG_BET_64_TO_4096,required:true'></div>
	                                                       </td>
	                                                       <td style="width: 166px;">
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Completion Queues Interrupts<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="lanCompletionQueuesInterrupts" name="completionQueuesInterrupts" style="width:79px"
	                                                                     data-dojo-id="lanCompletionQueuesInterrupts"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_1_TO_514,trim:"true", maxlength:"3", invalidMessage:MSG_BET_1_TO_514,required:true'></div>
	                                                       </td>
                                                  </tr>
                                         </table>
                                         <div style="border-top: 1px solid #CCC;"></div>
                                         <table >
                                        		 <tr  style="height: 50px;">
                                              			<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Transmit Checksum Offload:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                       		<select id="lanTransmitChecksumOffload" name="transmitChecksumOffload"
																	data-dojo-id="lanTransmitChecksumOffload" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>
	                                                       </td>
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Receive Checksum Offload:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanReceiveChecksumOffload" name="receiveChecksumOffload"
																	data-dojo-id="lanReceiveChecksumOffload" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>	                                                              
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">TCP Segmentation Offload:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanTcpSegmentationOffload" name="tcpSegmentationOffload"
																	data-dojo-id="lanTcpSegmentationOffload" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>	                                                             
	                                                       </td>
                                                  </tr>
                                                   <tr  style="height: 50px;">
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">TCP Large Receive Offload:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanTcpLargeReceiveOffload" name="tcpLargeReceiveOffload"
																	data-dojo-id="lanTcpLargeReceiveOffload" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>	                                                             
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Receive Side Scaling(RSS):</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanReceiveSideScaling" name="receiveSideScaling"
																	data-dojo-id="lanReceiveSideScaling" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>	                                                              
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Accelerated Receive Flow Steering:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanAcceleratedReceiveFlowSteering" name="acceleratedReceiveFlowSteering"
																	data-dojo-id="lanAcceleratedReceiveFlowSteering" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="disabled">Disabled</option>
																	<option value="enabled">Enabled</option>
																	</select>	                                                             
	                                                       </td>
                                                  </tr>
                                                  <tr  style="height: 50px;">
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Failback Timeout(Seconds)<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="lanFailbackTimeout" name="failbackTimeout" style="width:79px"
	                                                                     data-dojo-id="lanFailbackTimeout"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_0_TO_600,trim:"true", maxlength:"3", invalidMessage:MSG_BET_0_TO_600,required:true'></div>
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Interrupt Mode:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanInterruptMode" name="interruptMode"
																	data-dojo-id="lanInterruptMode" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="msi-x">MSI X</option>
																	<option value="msi">MSI</option>
																	<option value="intx">IN Tx</option>  
																	</select>	                                                              
	                                                       </td>
	                                                       <td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Interrupt Coalescing Type:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <select id="lanInterruptCoalescingType" name="interruptCoalescingType"
																	data-dojo-id="lanInterruptCoalescingType" data-dojo-type="xwt.widget.form.DropDown" 
																	maxHeight="100" style="width: 68px">
																	<option value="min">Min</option>
																	<option value="idle">Idle</option>
																	</select>		                                                              
	                                                       </td>
                                                  </tr>
                                                  <tr  style="height: 50px;">
                                                  		<td>
	                                                              <div class="labelspace">
	                                                                     <label style="float: left;">Interrupt Timer(us)<em>*</em>:</label>
	                                                              </div>
	                                                       </td>
	                                                       <td>
	                                                              <div id="lanInterruptTimer" name="interruptTimer" style="width:79px"
	                                                                     data-dojo-id="lanInterruptTimer"
	                                                                     data-dojo-type="xwt.widget.notification.ValidationTextBox"
	                                                                     data-dojo-props='regExp:REG_EX_NUMBER_0_TO_65535,trim:"true", maxlength:"5", invalidMessage:MSG_BET_0_TO_65535,required:true'></div>
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