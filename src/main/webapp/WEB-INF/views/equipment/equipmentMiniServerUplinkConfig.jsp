<%-- equipmentServerConfig.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
			dojo.require("xwt.widget.table.Table");
			dojo.require("xwt.widget.table.Toolbar");
			dojo.require("dojo.data.ItemFileReadStore");
			dojo.require("dijit.form.Button");
			dojo.require("dijit.form.Form");
			dojo.require("xwt.widget.form.DropDown");
			dojo.require("xwt.widget.table.GlobalToolbar");
            
			var equipServerUplinkConfigDataResponse = ajaxCallGet("fetchEquipmentMiniServerUplinkConfig.html", true, "json");
			var parsedInputEquipServerUplinkConfigDataResponse = JSON.parse(equipServerUplinkConfigDataResponse);						
			
			var dataForFIAEquipServerUplinkConfig = { items:JSON.parse("[" + parsedInputEquipServerUplinkConfigDataResponse.A + "]") };
			var dataForFIBEquipServerUplinkConfig = { items:JSON.parse("[" + parsedInputEquipServerUplinkConfigDataResponse.B + "]") };
			
			var	equipmentMiniSerUplinkConfigureAsList = [];
			equipmentMiniSerUplinkConfigureAsList.push({value: "",	label: LABEL_SELECT});
			equipmentMiniSerUplinkConfigureAsList.push({value: "server",	label: "SERVER"});
			equipmentMiniSerUplinkConfigureAsList.push({value: "uplink",	label: "UPLINK"});
			
						
			var equipServerUplinkColumnsFIA = [
			              			     	{ label: 'dbID', attr: 'id', hidden:true },
			              			     	{ label: 'configureAsOldValue', attr: 'configureAsOldValue', hidden:true },
			              					{ label: 'ID', attr: 'fiId', sorted: 'ascending', vAlignment: "middle", align:'center', hidden:true },
			              					{ label: 'Configure',	attr: 'isConfigured',	sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
				                               	 editWidget: {
				                						widget: xwt.widget.form.DropDown,
				                						options: {options: [ {label: "Yes",value: "1"}, {label: "No",value: "0"}] , id: "configureServerUplinkFIADroupDownId"}
				                		                   	  }
				                            },
				                            {label: 'Configure As', attr: 'configureAs',sorted: 'ascending', width: 120, vAlignment: "middle", align:'center', editable: true,formatter: formatterGetServerUplinkMiniConfigureAsName,
				            						editWidget: {
				            			 				widget: xwt.widget.form.DropDown,
				            			 				options: { options: equipmentMiniSerUplinkConfigureAsList, id:"configureAsMiniDropDownFIA", maxHeight:200 }
				            			 					}
				            				},
			              					{ label: 'Chassis', attr: 'chassis', sorted: 'ascending', width: 90, vAlignment: "middle", align:'center'},
			              					{ label: 'Slot Id', attr: 'slotId', sorted: 'ascending', width: 75, vAlignment: "middle", align:'center'},
			              					{label: 'User Label', attr: 'userLabel', align:'center', hidden:true},
			              					{ label: 'Port Id', attr: 'portId', sorted: 'ascending', width: 75, vAlignment: "middle", align:'center'}			              					
			              					]; 
			
			var equipServerUplinkColumnsFIB = [
			                 			     	{ label: 'dbID', attr: 'id', hidden:true },
			                 			     	{ label: 'configureAsOldValue', attr: 'configureAsOldValue', hidden:true },
			                 					{ label: 'ID', attr: 'fiId', sorted: 'ascending', vAlignment: "middle", align:'center', hidden:true },
			                 					{ label: 'Configure',	attr: 'isConfigured',	sorted: 'ascending',width: 90,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
					                               	 editWidget: {
					                						widget: xwt.widget.form.DropDown,
					                						options: {options: [ {label: "Yes",value: "1"}, {label: "No",value: "0"}] , id: "configureServerUplinkFIBDroupDownId"}
					                		                   	  }
					                            },
					                            {label: 'Configure As', attr: 'configureAs', sorted: 'ascending', width: 120, vAlignment: "middle", align:'center', editable: true,formatter: formatterGetServerUplinkMiniConfigureAsName,
				            						editWidget: {
				            			 				widget: xwt.widget.form.DropDown,
				            			 				options: { options: equipmentMiniSerUplinkConfigureAsList, id:"configureAsMiniDropDownFIB", maxHeight:200 }
				            			 					}
				            					},
			                 					{ label: 'Chassis', attr: 'chassis', sorted: 'ascending', width: 90, vAlignment: "middle", align:'center'},			                 						
			                 					{ label: 'Slot Id', attr: 'slotId', sorted: 'ascending', width: 75, vAlignment: "middle", align:'center'},
			                 					{label: 'User Label', attr: 'userLabel', align:'center', hidden:true},
			                 					{ label: 'Port Id', attr: 'portId', sorted: 'ascending', width: 75, vAlignment: "middle", align:'center'}			                 					
			                 					];
			
			function formatterGetServerUplinkMiniConfigureAsName(data, item, store){
				return returnFormatterDropDownLabel(equipmentMiniSerUplinkConfigureAsList,data, item, store);
			}
			
			 require(["dojo/ready", "dojo/_base/json"], function(ready, json){
				 	setTimeout(function(){
				 		 						 						 		
			    		equipServerUplinkDataStoreFiA._saveCustom = function(saveComplete, saveFailed){
			    			var equipmentTableAJson = returnChangedDataFromDataStore(this,json);			    			
			    			var response = ajaxCallPostWithJsonContent("manageEquipmentMiniServerUplinkConfig.html" , equipmentTableAJson, null, "json");
			    			saveComplete();
			    			callDataStoreForUpdateZeroIdOfThisRow(response, this);
			    			//updateZeroIdsInDataStore(response, this);
					     }; 

					    equipServerUplinkDataStoreFiB._saveCustom = function(saveComplete, saveFailed){
					    	 var equipmentTableBJson = returnChangedDataFromDataStore(this,json);					    	 
					    	 var response = ajaxCallPostWithJsonContent("manageEquipmentMiniServerUplinkConfig.html" , equipmentTableBJson , null, "json");
			    			 saveComplete();
			    			 callDataStoreForUpdateZeroIdOfThisRow(response, this);
			    			 //updateZeroIdsInDataStore(response, this);
					     };
					     
					     equipmentServerUplinkConfigTableA.validateRow = {
									isValid: function (oldvalues, newitem) {
										if( (newitem.isConfigured == "1" || newitem.isConfigured ==  1) && (newitem.configureAs == "") ){
											this.errorMessage = MSG_EQUP_MINICONF_AS_YES;
											return false;
										}
										if( (newitem.isConfigured == "0" || newitem.isConfigured ==  0) && (newitem.configureAs != "") ){
											this.errorMessage = MSG_EQUP_MINICONF_AS_IF_NOT_NULL;
											newitem.configureAsOldValue = oldvalues.configureAsOldValue;
											//newitem.configureAs = "";
											return false;
										}
									return true;
								}
							 };
					     equipmentServerUplinkConfigTableB.validateRow = {
									isValid: function (oldvalues, newitem) {
										if( (newitem.isConfigured == "1" || newitem.isConfigured ==  1) && (newitem.configureAs == "") ){
											this.errorMessage = MSG_EQUP_MINICONF_AS_YES;
											return false;
										}
										if( (newitem.isConfigured == "0" || newitem.isConfigured ==  0) && (newitem.configureAs != "") ){
											this.errorMessage = MSG_EQUP_MINICONF_AS_IF_NOT_NULL;
											newitem.configureAsOldValue = oldvalues.configureAsOldValue;
											//newitem.configureAs = "";
											return false;
										}
									return true;
								}
							 };
					     
					     
				    },1000);
			    });
    
 function saveEquipmentServerConfData(){
    	var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":2,"activeStateSubMenuIndex":2});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		
		return true;
    }
   </script>
</head>
<body>
	<div id="parentDiv" class="">
 		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 100%;">
				<legend>Server &amp; Uplink Ports</legend>           			        				        			        
			         <div class="floatleft" style="padding-left:20px;padding-top:15px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
				        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="equipServerUplinkDataStoreFiA" data="dataForFIAEquipServerUplinkConfig"></span>
							<div style="width:500px !important;"  id="equipmentServerUplinkToolBarA" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentServerUplinkConfigTableA" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="none"></div>
									<div style="padding-top:12px;"><label>FI A</label></div>
							</div>
							<div id="equipmentServerUplinkConfigTableA" 
								data-dojo-id="equipmentServerUplinkConfigTableA" 
								dojoType="xwt.widget.table.Table"
								store="equipServerUplinkDataStoreFiA" 
								structure="equipServerUplinkColumnsFIA" 
								style="width:500px; height:240px;"
								showIndex="false" 
								filterOnServer=false></div>
						</div>
			        </div>
			        <div class="floatleft" style="padding-left:30px;padding-top:15px;padding-right:16px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
					        <span dojoType="dojo.data.ItemFileWriteStore" jsId="equipServerUplinkDataStoreFiB" data="dataForFIBEquipServerUplinkConfig"></span>
							<div style="width:500px !important;"  id="equipmentServerUplinkToolBarB" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentServerUplinkConfigTableB" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="none"></div>
									<div style="padding-top:12px;"><label>FI B</label></div>
							</div>
							<div id="equipmentServerUplinkConfigTableB" 
								data-dojo-id="equipmentServerUplinkConfigTableB" 
								dojoType="xwt.widget.table.Table" 
								store="equipServerUplinkDataStoreFiB" 
								structure="equipServerUplinkColumnsFIB" 
								style="width:500px; height:240px;" 
								showIndex="false" 								
								filterOnServer=false></div>
							</div>
			        </div>			      
			</fieldset>
		</div>
	</div>
</body>
</html>

