//Pass DropDown value according to formatter then will return label
function returnLabelForDropDown(dropDownArr,dataStoreFieldValue){
	var returnLabel = "";
	dojo.forEach(dropDownArr,function(val,ind){
		if(dataStoreFieldValue == val.value){
			returnLabel = val.label;
			return false;
		}
	});
	return returnLabel;
}
//example custom formatter returning iconfont 
function formatterGetOrgName(data, item, store){
	return returnLabelForDropDown(getOgranizationDropDown(),data);
}

// Common function for return dropdown label value
function returnFormatterDropDownLabel(dropDownList,data, item, store){
	return returnLabelForDropDown(dropDownList,data);
}

// Return multi select dropdown labels 
function formatterGetVlanMutipleName(data, item, store){
	var returnVlanName = "";
	dojo.forEach(vlanDropdownList,function(val,ind){
		dojo.forEach(item.vlanId,function(v,i){
			if(v == val.value){
				returnVlanName += val.label+",";
				return false;
			}
		});
	});
	if( returnVlanName != ""){
		return returnVlanName.slice(0, - 1);//substr(0,returnVlanName.length-1);
	}else return LABEL_SELECT;
}

//example custom formatter returning VnicTemplate Name 
function formatterGetVnicTemplateName(data, item, store){
	var returnVnicName = "";
	dojo.forEach(vnicValues,function(nicVal,ind){
		dojo.forEach(item.lanVnic,function(v,i){
			if(v == nicVal.value){
				returnVnicName += nicVal.label+",";
				return false;
			}
		});
	});
	if( returnVnicName != ""){
		return returnVnicName.slice(0, - 1);
	}else return LABEL_SELECT;
	
}

//example custom formatter returning multiple vhba Names 
function formatterGetMultipleVhbaNames(data, item, store){
	var returnVhbaName = "";
	dojo.forEach(vhbaValuesList,function(vhbaVal,ind){
		dojo.forEach(item.sanVhba,function(v,i){
			if(v == vhbaVal.value){
				returnVhbaName += vhbaVal.label+",";
				return false;
			}
		});
	});
	if( returnVhbaName != ""){
		return returnVhbaName.slice(0, - 1);
	}else return LABEL_SELECT;
}

//example custom formatter returning multiple Roles Names 
function formatterGetLdapRolesName(data, item, store){
	var returnRolesName = "";
	dojo.forEach(ldapRolesList,function(roleVal,ind){
		dojo.forEach(item.ldapRoleId,function(v,i){
			if(v == roleVal.value){
				returnRolesName += roleVal.label+",";
				return false;
			}
		});
	});
	if( returnRolesName != ""){
		return returnRolesName.slice(0, - 1);
	}else return LABEL_SELECT;
}

//example custom formatter returning multiple Locales Names 
function formatterGetLdapLocalesName(data, item, store){
	var returnLocalesName = "";
	dojo.forEach(ldapLocalesList,function(localeVal,ind){
		dojo.forEach(item.ldapLocaleId,function(v,i){
			if(v == localeVal.value){
				returnLocalesName += localeVal.label+",";
				return false;
			}
		});
	});
	if( returnLocalesName != ""){
		return returnLocalesName.slice(0, - 1);
	}else return LABEL_SELECT;
}

//example custom formatter returning Service Profile Template Name 
function formatterGetServiceProfileTemplateName(data, item, store){
	return returnLabelForDropDown(profileTemplateValues,data);
}

function formatterYesNoDropdown(data, item, store){
	var content="";
	if ( (data === 1 || data === "1" ) || (data === "yes") || (data === "enabled")) {
		content = '<span class="alert_normal"></span>';
	}
	else {
		content ='<span class="alert_critical"></span>';
	}
	return content;
}

function formatterGetSANParentName(data, item, store){
		var returnValue; 
		bootPolicyForSanDataStore.fetch({
			 query: {id:data},
			 onComplete: function(items, request){
				 if(items && items.length > 0){
					 returnValue = items[0].name[0];
				 }
			 }
		});
	 return returnValue;
}

//Return label name in place of value in dropdown for Boot Mode
function formatterGetBootMode(data, item, store){
	return returnLabelForDropDown(bootModes,data);
}

function formatterGetBootSecurity(data, item, store){
	return returnLabelForDropDown(bootSecurities,data);
}

function formatterGetQosPriority(data, item, store){
	return returnLabelForDropDown(priorityArr,data);
}

function formatterGetHostControl(data, item, store){
	return returnLabelForDropDown(hostControlArr,data);
}

function formatterConfigureSlots(data,item){
	return '<button id="serversSlotsConfigureBtn'+item.id[0]+'" class="btn btn-primary" onClick="configureSlots(event,'+item.id[0]+');" type="button">Config</button>';
}
function formatterConfigureAdapterPolicy(data,item){
	return '<button id="serversSlotsConfigureBtn'+item.id[0]+'" class="btn btn-primary" onClick="configureAdapterPolicy(event,'+item.id[0]+');" type="button">Config</button>';
}
function formatterConfigureBiosPolicy(data,item){
	return '<button id="serversSlotsConfigureBtn'+item.id[0]+'" class="btn btn-primary" onClick="configureBiosPolicy(event,'+item.id[0]+');" type="button">Config</button>';
}

function formatterGetQosPriority(data, item, store){
	return returnLabelForDropDown(priorityArr,data);
}

function formatterGetHostControl(data, item, store){
	return returnLabelForDropDown(hostControlArr,data);
}
 
function formatterGetCDPName(data, item, store){
	return returnLabelForDropDown(lanNetworkControlPolicyCDPArr,data);
}
function formatterGetMACRegName(data, item, store){
	return returnLabelForDropDown(LNCPMACRegisterModeArr,data);
}
function formatterGetActionUplinkFailName(data, item, store){
	if(data == "warning"){
		return returnLabelForDropDown(LNCPActionOnUplinkFailArr,data)+'&nbsp;&nbsp;<span class="fi-minor" title="If the Action on Uplink Fail is set to Warning, the fabric will not fail over if uplink connectivity is lost."></span>';
	}else{
		return returnLabelForDropDown(LNCPActionOnUplinkFailArr,data);
	}
}
function formatterGetForgeName(data, item, store){
	return returnLabelForDropDown(LNCPForgeArr,data);
}

function formatterGetRealmName(data, item, store){
	return returnLabelForDropDown(adminAuthenticationRealmArr,data);
}

function formatterGetVhbaTempName(data, item, store){
	return returnLabelForDropDown(sanVHBATemplateArr,data);
}
function formatterGetAdapterPolicyName(data, item, store){
	return returnLabelForDropDown(sanAdapterPolicyArr,data);
}

function formatterGetLanVnicTempName(data, item, store){
	return returnLabelForDropDown(lanVnicTemplateArr,data);
}

function formatterGetLanEthernetAdapterPolicyName(data, item, store){
	return returnLabelForDropDown(lanEthernetAdapterPolicyArr,data);      
}

//TODO: needs to be cleaned
function formatterGetMultipleVhbaNamesConPol(data, item, store){
	var returnVhbaName = "";
	dojo.forEach(vhbaDropdownList,function(vhbaVal,ind){
		dojo.forEach(item.vhbaId,function(v,i){
			if(v == vhbaVal.value){
				returnVhbaName += vhbaVal.label+",";
				return false;
			}
		});
	});
	if( returnVhbaName != ""){
		return returnVhbaName.slice(0, - 1);
	}else return LABEL_SELECT;
}

function formatterGetMultipleVnicNamesConPol(data, item, store){
	var returnVnicName = "";
	dojo.forEach(vnicDropdownList,function(vnicVal,ind){
		dojo.forEach(item.vnicId,function(v,i){
			if(v == vnicVal.value){
				returnVnicName += vnicVal.label+",";
				return false;
			}
		});
	});
	if( returnVnicName != ""){
		return returnVnicName.slice(0, - 1);
	}else return LABEL_SELECT;
}


