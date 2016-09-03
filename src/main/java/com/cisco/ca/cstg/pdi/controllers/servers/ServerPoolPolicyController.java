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

import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolPolicy;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ServerPoolPolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerPoolPolicyController.class);
	private final ServersService serversService;

	@Autowired
	public ServerPoolPolicyController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversServerPoolPolicyConfig.html")
	public String showserversServerPoolPolicyConfig() {
		return "servers/serversServerPoolPolicyConfig";
	}

	@RequestMapping(value = "/getServerPoolPolicyConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getServerPoolPolicyConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersServerPoolPolicy> serverPoolPolicyConfigList = null;

		if (projectId != null) {
			try {
				serverPoolPolicyConfigList = serversService
						.fetchServersServerPoolPolicyConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServerPoolPolicyConfigJsonList(serverPoolPolicyConfigList);
	}

	private List<Object> getServerPoolPolicyConfigJsonList(
			List<ServersServerPoolPolicy> serverPoolPolicyConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(serverPoolPolicyConfigList)) {
			for (ServersServerPoolPolicy spp : serverPoolPolicyConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, spp.getId());
				myMap.put(NAME, spp.getName());
				myMap.put(DESCRIPTION, spp.getDescription());
				myMap.put(SERVERSSERVERPOOL, Util.nullValidationAndReturnId(spp
						.getServersServerPool()));
				myMap.put(SERVERSSERVERPOOLQUALIFIER, Util
						.nullValidationAndReturnId(spp
								.getServersServerPoolQualifier()));
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(spp.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServerPoolPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServerPoolPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersServerPoolPolicy> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersServerPoolPolicy> serversServerPoolPolicyList = (List<ServersServerPoolPolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersServerPoolPolicy.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersServerPoolPolicyConfiguration(
									serversServerPoolPolicyList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersServerPoolPolicy> deletedServerPoolPolicyList = (List<ServersServerPoolPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersServerPoolPolicy.class);
					this.serversService
							.deleteServersServerPoolPolicy(deletedServerPoolPolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getServerPoolPolicyConfigJsonList(newlyAddedRecords);
	}

}
