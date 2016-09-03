<%-- equipmentChasisConfig.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	dojo.require("xwt.xwt");
	dojo.require("dijit.form.Form");
	dojo.require("xwt.widget.form.DropDown");
	
	var euipChasisDataResponse = ajaxCallGet("fetchEquipmentChasisDetails.html", true, "json");
	try{
		var equipmentChasisParsedResult = JSON.parse(euipChasisDataResponse);
	}catch(e){
		var equipmentChasisParsedResult = "";
	}
	
		 dojo.ready(function(){
			var	selectActionArr = [],selectLinkAggregationPolicyArr = [],selectPowerSupplyArr = [];
			setTimeout(function(){
					selectActionArr.push({value: "1",	label: "1-link"});
					selectActionArr.push({value: "2",	label: "2-link"});
	
					if(infraIoModule.get("value") == "8" || (infrastructureParsedResult != undefined && infrastructureParsedResult.ioModule == "8") ) {
						selectActionArr.push({value: "4",	label: "4-link"});
						selectActionArr.push({value: "8",	label: "8-link"});
					} else if(infraIoModule.get("value") == "4" || (infrastructureParsedResult != undefined && infrastructureParsedResult.ioModule == "4") ){
						selectActionArr.push({value: "4",	label: "4-link"});	
					}
					selectAction.addOption(selectActionArr);
					
					// Add Option for Link Aggregation
					selectLinkAggregationPolicyArr.push({value: "none",	label: "None"});
					selectLinkAggregationPolicyArr.push({value: "port-channel",	label: "port-channel"});
					selectLinkAggregationPolicy.addOption(selectLinkAggregationPolicyArr);
					
					// Add Option for Power Supply
					selectPowerSupplyArr.push({value: "grid",	label: "grid"});
					selectPowerSupplyArr.push({value: "n+1",	label: "n+1",selected:true});
					selectPowerSupplyArr.push({value: "non-redundant",	label: "non-redundant"});
					selectPowerSupply.addOption(selectPowerSupplyArr);
				
					if(euipChasisDataResponse){
						selectAction.set("value", equipmentChasisParsedResult.cdpAction);
						selectLinkAggregationPolicy.set("value", equipmentChasisParsedResult.cdpLinkAgg);
						selectPowerSupply.set("value", equipmentChasisParsedResult.pspPowerSupply);
					} 
				
				 dojo.connect(dijit.byId("selectAction"),"onClick",function(){
					if( getDataStoreSize(equipServerDataStoreFiA) || getDataStoreSize(equipServerDataStoreFiB) > 0 ){
						displayNotificationAlert(MSG_CHASSIS_ACTION_OP,"information");
						return false;
					}
					
				});
				
			
			},500);
		}); 
	
	
	/* Vishnu, Call function from common.js to save data for Equip Chasis Conf*/  
	function saveEquipmentChasisConfData(){
		var euipmentChasisJsonObj =  dojo.formToJson("equipmentChasisConfForm");
		
		var response = ajaxCallPostWithJsonContent("createEquipmentChasisConfig.html" , euipmentChasisJsonObj, null, "text");
		if(response == "success"){
			var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":2,"activeStateSubMenuIndex":1});
			response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		} else{
			displayNotificationAlert(MSG_ERROR_WHILESAVE);	
			return false;
		}

		return true;
	}
		</script>
</head>
<body>
	<div id="parentDiv">
		<form id="equipmentChasisConfForm"
			data-dojo-id="equipmentChasisConfForm"
			data-dojo-type="dijit.form.Form" name="equipmentChasisConfForm">
			<div class="floatleft addCssIntreeTable" style="width: 50%;">
				<fieldset class="heightOfFieldset" style="width: 98%;">
					<legend>Chassis Discovery Policy</legend>
					
					<table style="padding: 70px 0 0 30px;" width="500px">
						<tr>
							<td height="50px">
								<div class="labelspace">
									<label style="float: left;">Action:</label>
								</div>
							</td>
							<td height="50px"><select id="selectAction" name="cdpAction"
								data-dojo-id="selectAction"
								data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
								style="width: 140px" /></td>
						</tr>
						<tr>
							<td height="50px">
								<div class="labelspace">
									<label style="float: left;">Link Aggregation Policy:</label>
								</div>
							</td>
							<td height="50px"><select id="selectLinkAggregationPolicy"
								name="cdpLinkAgg" data-dojo-id="selectLinkAggregationPolicy"
								data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
								style="width: 140px" /></td>
						</tr>
					</table>

				</fieldset>
			</div>
			<div class="floatleft" style="width:48%; padding-left: 20px;">
				<fieldset class="heightOfFieldset" style="width: 98%;">
					<legend>Power Supply Policy</legend>
					<table style="padding: 100px 0 0 30px;" width="500px">
						<tr>
							<td height="30px">
								<div class="labelspace">
									<label style="float: left;">Power Supply:</label>
								</div>
							</td>
							<td height="30px"><select id="selectPowerSupply"
								name="pspPowerSupply" data-dojo-id="selectPowerSupply"
								data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
								style="width: 140px" /></td>
						</tr>
					</table>

				</fieldset>
			</div>
		</form>
	</div>
</body>
</html>