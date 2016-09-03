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

import com.cisco.ca.cstg.pdi.pojos.ServersLocalDisc;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class LocalDiskPolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LocalDiskPolicyController.class);
	private final ServersService serversService;

	@Autowired
	public LocalDiskPolicyController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversLocalDiskPolicyConfig.html")
	public String showLocalDiskPolicyConfig() {
		return "servers/serversLocalDiskPolicyConfig";
	}

	@RequestMapping(value = "/getLocalDiskPolicyConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLocalDiskPolicyConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersLocalDisc> localDiskPolicyConfigList = null;

		if (projectId != null) {
			try {
				localDiskPolicyConfigList = serversService
						.fetchServersLocalDiscConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getLocalDiskPolicyConfigJsonList(localDiskPolicyConfigList);
	}

	private List<Object> getLocalDiskPolicyConfigJsonList(
			List<ServersLocalDisc> localDiskPolicyConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(localDiskPolicyConfigList)) {
			for (ServersLocalDisc sld : localDiskPolicyConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, sld.getId());
				myMap.put(NAME, sld.getName());
				myMap.put(DESCRIPTION, sld.getDescription());
				myMap.put(MODE, sld.getMode());
				myMap.put(PROTECTCONFIGURATION, sld.getProtectConfiguration());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(sld.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageLocalDiskPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageLocalDiskPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersLocalDisc> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersLocalDisc> serversLocalDiscList = (List<ServersLocalDisc>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersLocalDisc.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersLocalDiscConfiguration(
									serversLocalDiscList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersLocalDisc> deletedLocalDiskPolicyList = (List<ServersLocalDisc>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersLocalDisc.class);
					this.serversService
							.deleteServersLocalDisc(deletedLocalDiskPolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getLocalDiskPolicyConfigJsonList(newlyAddedRecords);
	}

}
