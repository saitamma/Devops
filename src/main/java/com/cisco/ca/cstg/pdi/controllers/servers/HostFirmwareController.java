package com.cisco.ca.cstg.pdi.controllers.servers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.ServersHostFirmware;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class HostFirmwareController implements Constants{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HostFirmwareController.class);
	private final ServersService serversService;

	@Autowired
	public HostFirmwareController(ServersService serversService) {
		this.serversService = serversService;
	}
	
	@RequestMapping(value = "/serversHostFirmware.html")
	public String showServersHostFirmware(){
		return "servers/serversHostFirmware";
	}
	
	@RequestMapping(value = "/getserversHostFirmwareDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getserversHostFirmwareDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {		
		List<ServersHostFirmware> hostFirmwareList = null;
		
		if(projectId != null) {
			try {
				hostFirmwareList = this.serversService.fetchHostFirmwareDetail(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(),e);
				throw e;
			}		
		}
		return getHostFirmwareConfigJsonList(hostFirmwareList);

	}

	private List<Object> getHostFirmwareConfigJsonList(List<ServersHostFirmware> hostFirmwareList) {
		List<Object> jsonList = new ArrayList<Object>();
	
		if(Util.listNotNull(hostFirmwareList)){
			for(ServersHostFirmware shfw : hostFirmwareList){
				Map<String, Object> myMap = new HashMap<String,Object>();
				myMap.put(ID, shfw.getId());
				myMap.put(NAME, shfw.getName());
				myMap.put(DESCRIPTION, shfw.getDescription());
				myMap.put(ORGANIZATIONS, Util.nullValidationAndReturnId(shfw.getOrganizations()));
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageHostFirmwareConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageHostFirmwareConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);
		
		List<ServersHostFirmware> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes =Util.getJsonNodeByName(jsonObj, ADDORUPDATE);
				JsonNode deletedNodes = Util.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersHostFirmware> serversServersHostFirmwareList = (List<ServersHostFirmware>)(List<?>)
							Util.convertJsonToListOfObject(addOrUpdateNodes.toString(),ServersHostFirmware.class);
					newlyAddedRecords = this.serversService.saveOrUpdateHostFirmwareDetails(serversServersHostFirmwareList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersHostFirmware> deletedServersHostFirmwareList = (List<ServersHostFirmware>)(List<?>) 
							Util.convertJsonToListOfObject(deletedNodes.toString(),ServersHostFirmware.class);
					this.serversService.deleteServersHostFirmwareDetails(deletedServersHostFirmwareList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getHostFirmwareConfigJsonList(newlyAddedRecords);
	}

}
