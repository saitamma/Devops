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
            
			var equipScalabilityConfigDataResponse = ajaxCallGet("fetchEquipmentMiniScalabilityConfig.html", true, "json");
			var parsedInputEquipScalabilityConfigDataResponse = JSON.parse(equipScalabilityConfigDataResponse);						
			
			var dataForFIAEquipScalabilityConfig = { items:JSON.parse("[" + parsedInputEquipScalabilityConfigDataResponse.A + "]") };
			var dataForFIBEquipScalabilityConfig = { items:JSON.parse("[" + parsedInputEquipScalabilityConfigDataResponse.B + "]") };						
						
			var equipScalabilityColumnsFIA = [
			              			     	{ label: 'dbID', attr: 'id', hidden:true },
			              			     	{ label: 'Configure',	attr: 'isConfigured',	sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
				                               	 editWidget: {
				                						widget: xwt.widget.form.DropDown,
				                						options: {options: [ {label: "Yes",value: "1"}, {label: "No",value: "0"}] , id: "configureScalabilityFIADroupDownId"}
				                		                   	  }
				                            },
				                            { label: 'Configure As', attr: 'configureAs', hidden:true },
			              					{ label: 'ID', attr: 'fiId', sorted: 'ascending', vAlignment: "middle", align:'center', hidden:true },
			              					{ label: 'Chassis', attr: 'chassis', sorted: 'ascending', width: 110, vAlignment: "middle", align:'center'},
			              					{ label: 'Slot Id', attr: 'slotId', sorted: 'ascending', width: 110, vAlignment: "middle", align:'center'},
			              					{ label: 'Port Id', attr: 'portId', sorted: 'ascending', width: 110, vAlignment: "middle", align:'center'}		              								                                
			              					]; 
			
			var equipScalabilityColumnsFIB = [
			                 			     	{ label: 'dbID', attr: 'id', hidden:true },
			                 			     	{ label: 'Configure',	attr: 'isConfigured',	sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,formatter: formatterYesNoDropdown,
					                               	 editWidget: {
					                						widget: xwt.widget.form.DropDown,
					                						options: {options: [ {label: "Yes",value: "1"}, {label: "No",value: "0"}] , id: "configureScalabilityFIBDroupDownId"}
					                		                   	  }
					                            },
					                            { label: 'Configure As', attr: 'configureAs', hidden:true },
			                 					{ label: 'ID', attr: 'fiId', sorted: 'ascending', vAlignment: "middle", align:'center', hidden:true },
			                 					{ label: 'Chassis', attr: 'chassis', sorted: 'ascending', width: 110, vAlignment: "middle", align:'center'},			                 						
			                 					{ label: 'Slot Id', attr: 'slotId', sorted: 'ascending', width: 110, vAlignment: "middle", align:'center'},			                 						
			                 					{ label: 'Port Id', attr: 'portId', sorted: 'ascending', width: 110, vAlignment: "middle", align:'center'}			           					                            
			                 					];						
			
			 require(["dojo/ready", "dojo/_base/json"], function(ready, json){
				 	setTimeout(function(){
				 		 
			    		equipScalabilityDataStoreFiA._saveCustom = function(saveComplete, saveFailed){
			    			var equipmentTableAJson = returnChangedDataFromDataStore(this,json);			    			
			    			var response = ajaxCallPostWithJsonContent("manageEquipmentMiniScalabilityConfig.html" , equipmentTableAJson, null, "json");
			    			saveComplete();
			    			updateZeroIdsInDataStore(response, this);
					     }; 

					    equipScalabilityDataStoreFiB._saveCustom = function(saveComplete, saveFailed){
					    	 var equipmentTableBJson = returnChangedDataFromDataStore(this,json);					    	 
					    	 var response = ajaxCallPostWithJsonContent("manageEquipmentMiniScalabilityConfig.html" , equipmentTableBJson , null, "json");
			    			 saveComplete();
			    			 updateZeroIdsInDataStore(response, this);
					     };      		   
				    },1000);
			    });
 

/* Vishnu, Call function from common.js to save data for Equip Uplink Conf*/  
	function saveEquipmentUplinkConfData(){
			var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":3,"activeStateSubMenuIndex":0});
			response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
    		return true;
		
	} 
   </script>
</head>
<body>
	<div id="parentDiv" class="">
 		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 100%;">
				<legend>Scalability Ports</legend>
			         <div class="floatleft" style="padding-left:20px;padding-top:15px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
				        	<span dojoType="dojo.data.ItemFileWriteStore" jsId="equipScalabilityDataStoreFiA" data="dataForFIAEquipScalabilityConfig"></span>
							<div style="width:500px !important;"  id="equipmentScalabilityToolBarA" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentScalabilityConfigTableA" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="none"></div>
									<div style="padding-top:12px;"><label>FI A</label></div>
							</div>
							<div id="equipmentScalabilityConfigTableA" 
								data-dojo-id="equipmentScalabilityConfigTableA" 
								dojoType="xwt.widget.table.Table"
								store="equipScalabilityDataStoreFiA" 
								structure="equipScalabilityColumnsFIA" 
								style="width:500px; height:240px;"							
								showIndex="false" 								
								filterOnServer=false></div>
						</div>
			        </div>
			        <div class="floatleft" style="padding-left:50px;padding-top:15px;padding-right:16px;">
			        	 <div dojotype="dijit.layout.ContentPane" region="left" style="width:500px; overflow:hidden;" splitter="true">
					        <span dojoType="dojo.data.ItemFileWriteStore" jsId="equipScalabilityDataStoreFiB" data="dataForFIBEquipScalabilityConfig"></span>
							<div style="width:500px !important;"  id="equipmentScalabilityToolBarB" dojoType="xwt.widget.table.ContextualToolbar" tableId="equipmentScalabilityConfigTableB" quickFilter="false">
									<div  dojoType="xwt.widget.table.ContextualButtonGroup" showButtons="none"></div>
									<div style="padding-top:12px;"><label>FI B</label></div>
							</div>
							<div id="equipmentScalabilityConfigTableB" 
								data-dojo-id="equipmentScalabilityConfigTableB" 
								dojoType="xwt.widget.table.Table" 
								store="equipScalabilityDataStoreFiB" 
								structure="equipScalabilityColumnsFIB" 
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

