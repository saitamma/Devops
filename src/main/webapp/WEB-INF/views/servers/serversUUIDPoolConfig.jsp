<%-- serversUUIDPoolConfig.jsp --%>
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
			
			var prefixTypeArr = [{value: "derived",label: "Derived"},{value: "other",label: "Other"}];
			
			var assignmentOrderArr = [{label:"Default",value:"default"},{label:"Sequential",value:"sequential"}];
			
			var noOfUUIDs = 0;
			var uuidConfigDataResponse = ajaxCallGet("getUUIDPoolConfigDetails.html", true, "json");
			var serversUUIDPoolDataTable = { items:JSON.parse("[" + uuidConfigDataResponse + "]") };
 
			//Calculating the max used vNIC name
			var count = uuidConfigDataResponse.length;
			if(count > 0){
				noOfUUIDs = extractNumericValueFromAlphaNumericString(JSON.parse(uuidConfigDataResponse[count-1]).name);
			}
			
			var serversUUIDPoolColumns = [
                   {label: 'dbID',	attr: 'id',	hidden:true	},
                   {label: 'Name',attr: 'name',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
                		editWidget:{
                			widget:xwt.widget.notification.ValidationTextBox,
                			options: {
                				trim: true,
                    			regExp:REG_EX_NAME,
                    			required: true,
                    			maxlength:"16",
                    			invalidMessage:MSG_NAME
  							}
                		}   
                   },
                   {label: 'Description',attr: 'description',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 	                			trim: true,
 	                			maxlength:"45",
 							}
               			}   
                   },
                   {label: 'From Address',attr: 'fromAddress',sorted: 'ascending',width: 140,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 								regExp:REG_EX_HEXADECIMAL_HYPHEN_4X12,
 								required: true,
 	                			trim: true,
 	                			invalidMessage: MSG_HEXADECIMAL_HYPHEN,
 	                			id: "UUIDPoolFromAddress"
 								}
               			} 
                   },
                   {label: 'To Address',attr: 'toAddress',width: 140,vAlignment: "middle",align:'center',editable: true,
                	  editWidget:{
               			widget:xwt.widget.notification.ValidationTextBox,
               			options: {
 								regExp:REG_EX_HEXADECIMAL_HYPHEN_4X12,
 								required: true,
 	                			trim: true,
 	                			invalidMessage: MSG_HEXADECIMAL_HYPHEN,
 	                			id: "UUIDPoolToAddress"
 								}
               			}    
                   },
                   {label: 'Assignment<br/>Order',attr: 'assignmentOrder',sorted: 'ascending',width: 100,vAlignment: "middle",align:'center',editable: true,formatter : formatterGetUUIDPoolAssignOrderName,
                	   editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: assignmentOrderArr}
      		           }
                   },
                   {label: 'Prefix<br/>Type',attr: 'prefixType',sorted: 'ascending',width: 110,vAlignment: "middle",align:'center',editable: true,formatter : formatterGetUUIDPoolPrefixTypeName,
                	   editWidget: {
      						widget: xwt.widget.form.DropDown,
      						options: {options: prefixTypeArr,id: "uuidPoolPrefixTypeTableDropdown"}
      		           }
                   },
                   {label: 'Prefix',attr: 'prefix',sorted: 'ascending',width: 120,vAlignment: "middle",align:'center',editable: true,
                	   editWidget:{
                  			widget:xwt.widget.notification.ValidationTextBox,
                  			options: {
    								regExp: REG_EX_HEXADECIMAL_HYPHEN_8X4X4,
    	                			trim: true,
    	                			//required: true,
    	                			invalidMessage: MSG_HEXADECIMAL_HYPHEN_8X4X4,
    	                			id: "uuidPoolPrefixTableRowId"
    								}
                  			}
                   },
                   {label: 'Organization',attr: 'organizations',width: 110,vAlignment: "middle",align:'center',editable: true,formatter: formatterGetOrgName,
   					editWidget: {
   						widget: xwt.widget.form.DropDown,
   						options: {options: getOgranizationDropDown()}
   		                   	  }
                      		}
                   ];		

function formatterGetUUIDPoolAssignOrderName(data, item, store){
	return returnFormatterDropDownLabel(assignmentOrderArr,data, item, store);
}
function formatterGetUUIDPoolPrefixTypeName(data, item, store){
	return returnFormatterDropDownLabel(prefixTypeArr,data, item, store);
}


//
function serversUUIDPoolGenerateDataConfirm(){
	var formObject2 =  dojo.formToObject("serversUUIDPoolTableForm");
	if(dijit.byId("serversUUIDPoolTableForm").validate()==false || formObject2.noOfUUIDPool == ""){
		displayNotificationAlert(MSG_FILL_MANDATORY_FIELDS);
		return false;
	}
	if( formObject2.prefix == UUIDPOOL_PREFIX_VALUE){
		displayConfirmationDialog(MSG_UUIDPOOL_PREFIX_ISDEFAULT, serversUUIDPoolGenerateData);
		return false;
	}else{
		serversUUIDPoolGenerateData();
	}
}

function serversUUIDPoolGenerateData(){
	var formObject2 =  dojo.formToObject("serversUUIDPoolTableForm");

	var selectedOrg = parseInt(formObject2.serversUUIDPoolOrganization);
 	var noOfUUIDPool = parseInt(formObject2.noOfUUIDPool);

 	if(formObject2.prefix == UUIDPOOL_PREFIX_VALUE){
 		formObject2.prefix = "";formObject2.prefixType = "derived";
 	}
	noOfUUIDs =  noOfUUIDs + 1;
	var name = "UUID_"+noOfUUIDs;
	if(checkTableFieldValueUnique(serversUUIDPoolDataStoreTab,"macpoolName",name)){
		var serversUUIDPoolDataGenreate = {
				"id":0,
				"name":	name,
				"description": "UUID Pool "+noOfUUIDs,
				"prefix": formObject2.prefix,
				"prefixType": formObject2.prefixType,
				"assignmentOrder": formObject2.assignmentOrder,
				"fromAddress": formObject2.uuidPoolFrom,
				"toAddress":0,
				"organizations":selectedOrg,
				"size":noOfUUIDPool
			};
		serversUUIDPoolTable.store.newItem(serversUUIDPoolDataGenreate);
 	}
 	serversUUIDPoolDataStoreTab.save();
 	serversUUIDPoolTable.refresh();
 	dijit.byId("serversUUIDPoolTableForm").reset();
 	
 }
 
 
require(["dojo/ready", "dojo/_base/json"], 
		 function(ready, json){
	 	
	    	 setTimeout(function(){
	    		 
	    		 dojo.connect(dijit.byId("uuidPoolFrom"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("uuidPoolFrom"));
	    		 });
	    		 dojo.connect(dijit.byId("uuidPoolPrefixvalue"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("uuidPoolPrefixvalue"));
	    		 });
	    		 dojo.connect(dijit.byId("uuidPoolPrefixTableRowId"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("uuidPoolPrefixTableRowId"));
	    		 });
	    		 dojo.connect(dijit.byId("UUIDPoolFromAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("UUIDPoolFromAddress"));
	    		 });
	    		 dojo.connect(dijit.byId("UUIDPoolToAddress"),"onKeyUp",function(){
	    			 macAddressValidUpperCase(dijit.byId("UUIDPoolToAddress"));
	    		 });
	    		 
	    		 // ADD dropDown val for Organization //
	    		 	var selectOrganizationArr = getOgranizationDropDown();
	    		 	serversUUIDPoolOrganization.addOption(selectOrganizationArr);
	    		 	
	    		 	// Add Dropdown values for Prefix Type
	    		 	uuidPoolPrefixType.addOption(prefixTypeArr);
	    		 	// Add Dropdown values for Assignment Order
	    		 	uuidPoolAssignmentOrder.addOption(assignmentOrderArr);
	    		 	dojo.connect(uuidPoolPrefixType,"onChange",function(){
		   		 		if(this.value == "other"){
		   		 			uuidPoolPrefixvalue.set("value",UUIDPOOL_PREFIX_VALUE);
		   		 			uuidPoolPrefixvalue.set("disabled",false);uuidPoolPrefixvalue.set("required",true);
		   		 		}
		   		 		else{
		   		 			uuidPoolPrefixvalue.set("disabled",true);uuidPoolPrefixvalue.set("required",false);
		   		 			uuidPoolPrefixvalue.set("value","");
		   		 		}
		   		 	});

	    		 	serversUUIDPoolDataStoreTab._saveCustom = function(saveComplete, saveFailed){
		    			var serversUUIDPoolTablejson = returnChangedDataFromDataStore(this,json);
		    			console.log(serversUUIDPoolTablejson);
		    			var response = ajaxCallPostWithJsonContent("manageUUIDPoolConfig.html" , serversUUIDPoolTablejson, null, "json");
		    			saveComplete();
		    			updateZeroIdsInDataStore(response, this);
		    			updateToInDataStore(response, this);
			     	};
			     	
			     	dojo.addOnLoad(function () {
			     		serversUUIDPoolTable.validateRow = {
								isValid: function (oldvalues, newitem) {
									
								if(oldvalues.name != newitem.name){
									if(!checkTableFieldValueUnique(serversUUIDPoolDataStoreTab,"name",newitem.name)){
										this.errorMessage = MSG_DUPLICATE_NAME;
										return false;
									}
								}
								if( newitem.prefixType == "other" && (newitem.prefix == "" || newitem.prefix == undefined) ){
									this.errorMessage = MSG_HEXADECIMAL_HYPHEN;
									return false;
								}
								if(newitem.prefix == UUIDPOOL_PREFIX_VALUE){
									this.errorMessage = MSG_UUIDPOOL_TABLE_PREFIX_ERROR;
									return false;
								}
								return true;
							}
						};
						
					});
			     	
			     	dojo.connect(serversUUIDPoolTable,"onEdit",function(item){
			     		var inputObj = dijit.byId("uuidPoolPrefixTableRowId");
			     		if(item.prefixType == "other"){
			     			inputObj.set("disabled",false);
		   		 		}else{
			   		 		inputObj.set("disabled",true);
			   		 		inputObj.set("value","");
		   		 		}
			     	
			     	});
			     	dojo.connect(dijit.byId("uuidPoolPrefixTypeTableDropdown"),"onChange",function(){
			     		var inputObj = dijit.byId("uuidPoolPrefixTableRowId");
			     		if(this.value == "other"){
			     			inputObj.set("value",UUIDPOOL_PREFIX_VALUE);
			     			inputObj.set("disabled",false);//inputObj.set("required",true);
		   		 		}else{
			   		 		inputObj.set("disabled",true);//inputObj.set("required",false);
			   		 		inputObj.set("value","");
		   		 		}
		    		 });
			     	

	    	 },1000);
	 
	     });

function updateToInDataStore(jsonResponse, dataStoreObj ){
	if(jsonResponse != "success"){
		 for( var i = 0; i < jsonResponse.length; i++) {
			 var OneByOneResp = JSON.parse(jsonResponse[i]);
			 dataStoreObj.fetch({
			    	  query: { toAddress: 0},
			    	  onComplete: function(items, request){
			    		  if(items && items.length > 0){
					    		var item = items[0];
					    		dataStoreObj.setValue(item, "toAddress", OneByOneResp.toAddress);
					    		dataStoreObj._pending = {
					  				_newItems:{},
					  				_modifiedItems:{},
					  				_deletedItems:{}
					  			};
					    	}	  
			    	  }
			    	});
			}
	 }
}

// function for Save data to servere
function saveServersUUIDPoolConfigData(){
	 var jsonOfNavSaved = JSON.stringify({"projectId":${activeProjectId},"activeStateMenuIndex":5,"activeStateSubMenuIndex":1});
		response = ajaxCallPostWithJsonContent("setWizardStatus.html" , jsonOfNavSaved, null, "text");
		return true;
	 console.log("UUID Save");
}

</script>
</head>
<body class="prime">
	<div id="parentDiv">
		<div class="floatleft addCssIntreeTable">
			<fieldset class="heightOfFieldset" style="width: 1050px;">
				<legend>UUID Pool Configuration</legend>

				<div class="vnicTemplateConfig">
					<form id="serversUUIDPoolTableForm"
						data-dojo-id="serversUUIDPoolTableForm"
						data-dojo-type="xwt.widget.notification.Form" name="tableForm">
						<table>
							<tr>
								<td height="30">
									<div class="labelspace">
										<label style="float: left;">Organization:</label>
									</div>
								</td>
								<td><select id="serversUUIDPoolOrganization"
									name="serversUUIDPoolOrganization"
									data-dojo-id="serversUUIDPoolOrganization"
									data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
									style="width: 140px" /></td>
								<td>
									<div class="labelspace">
										<label style="float: left;">From Address:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="uuidPoolFrom" name="uuidPoolFrom"
										data-dojo-id="uuidPoolFrom"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										value="0000-000000000001"
										data-dojo-props='regExp:REG_EX_HEXADECIMAL_HYPHEN_4X12,trim:"true",required:"true", maxlength:"20", invalidMessage:MSG_HEXADECIMAL_HYPHEN'></div>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Size:<em>*</em></label>
									</div>
								</td>
								<td>
									<div id="noOfUUIDPool" name="noOfUUIDPool"
										data-dojo-id="noOfUUIDPool"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_NUMBER_UPTO1000,trim:"true", maxlength:"4", invalidMessage:MSG_UPTO1000'></div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="labelspace">
										<label style="float: left;">Assignment Order:</label>
									</div>
								</td>
								<td>
									<select id="uuidPoolAssignmentOrder" data-dojo-id="uuidPoolAssignmentOrder"
										name="assignmentOrder"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 140px; border: 1px solid #b4b4b4;"/>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Prefix Type:</label>
									</div>
								</td>
								<td>
									<select id="uuidPoolPrefixType" data-dojo-id="uuidPoolPrefixType"
										name="prefixType"
										data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
										style="width: 198px; border: 1px solid #b4b4b4;"/>
								</td>
								<td>
									<div class="labelspace">
										<label style="float: left;">Prefix:</label>
									</div>
								</td>
								<td >
									<div id="uuidPoolPrefixvalue" name="prefix" 
										data-dojo-id="uuidPoolPrefixvalue"
										data-dojo-type="xwt.widget.notification.ValidationTextBox"
										data-dojo-props='regExp:REG_EX_HEXADECIMAL_HYPHEN_8X4X4,trim:"true", invalidMessage: MSG_HEXADECIMAL_HYPHEN_8X4X4, disabled:true' value=""></div>
								</td>
								<td style="padding-left: 10px;">
									<button data-dojo-type="dijit/form/Button"
										data-dojo-id="serversUUIDPoolGenerateDataBtn"
										onClick="serversUUIDPoolGenerateDataConfirm();" type="button">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="floatleft addClassForColumnHeight" style="padding-left: 25px; padding-top: 15px;">
					<div dojotype="dijit.layout.ContentPane" region="left"
						style="width: 1003px; overflow: hidden;" splitter="true">
						<span dojoType="dojo.data.ItemFileWriteStore"
							jsId="serversUUIDPoolDataStoreTab"
							data="serversUUIDPoolDataTable"></span>
						<div style="width: 1000px !important;"
							id="serversUUIDPoolTableTollBar"
							dojoType="xwt.widget.table.ContextualToolbar"
							tableId="serversUUIDPoolTable" quickFilter="false">
							<div dojoType="xwt.widget.table.ContextualButtonGroup"
								showButtons="delete"></div>
						</div>
						<div id="serversUUIDPoolTable" data-dojo-id="serversUUIDPoolTable"
							dojoType="xwt.widget.table.Table"
							store="serversUUIDPoolDataStoreTab"
							structure="serversUUIDPoolColumns"
							style="width: 1000px; height: 220px;" selectMultiple="true"
							selectAllOption="true" showIndex="false" selectModel="input"
							filterOnServer=false></div>
					</div>
				</div>
			</fieldset>
		</div>

	</div>
</body>
</html>