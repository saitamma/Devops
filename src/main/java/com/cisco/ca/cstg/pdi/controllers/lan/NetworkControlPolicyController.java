package com.cisco.ca.cstg.pdi.controllers.lan;

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

import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class NetworkControlPolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NetworkControlPolicyController.class);
	private final LANService lanService;

	@Autowired
	public NetworkControlPolicyController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanNetworkControlPolicy.html")
	public String showLanNetworkControlPolicy() {
		return "lan/lanNetworkControlPolicy";
	}

	@RequestMapping(value = "/getLanNetworkControlPolicyDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanNetworkControlPolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<LanNetworkControlPolicy> lanNetworkControlPolicyList = null;
		try {
			if (projectId != null) {
				lanNetworkControlPolicyList = lanService
						.fetchLanNetworkControlPolicyConfig(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getLanNetworkControlPolicyDetailsJsonList(lanNetworkControlPolicyList);
	}

	private List<Object> getLanNetworkControlPolicyDetailsJsonList(
			List<LanNetworkControlPolicy> lanNetworkControlPolicyList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(lanNetworkControlPolicyList)) {
			for (LanNetworkControlPolicy lanNCP : lanNetworkControlPolicyList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, lanNCP.getId());
				myMap.put(NCPNAME, lanNCP.getNcpName());
				myMap.put(DESCRIPTION, lanNCP.getDescription());
				myMap.put(CDP, lanNCP.getCdp());
				myMap.put(MACREGMODE, lanNCP.getMacRegisterMode());
				myMap.put(UPLINKFAILACTION, lanNCP.getUplinkFailAction());
				myMap.put(FORGE, lanNCP.getForge());
				if (lanNCP.getOrganizations() != null) {
					myMap.put(ORGANIZATIONS, lanNCP.getOrganizations().getId());
				}
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanNetworkControlPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanNetworkcontrolPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanNetworkControlPolicy> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<LanNetworkControlPolicy> lanNetworkControlPolicyList = (List<LanNetworkControlPolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									LanNetworkControlPolicy.class);
					if (Util.listNotNull(lanNetworkControlPolicyList)) {
						newlyAddedRecords = lanService
								.saveOrUpdateNetworkControlPolicyConfig(
										lanNetworkControlPolicyList, projectId);
					}
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanNetworkControlPolicy> deletedNetworkControlPilicyList = (List<LanNetworkControlPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanNetworkControlPolicy.class);
					this.lanService
							.deleteLanNetworkControlPolicy(deletedNetworkControlPilicyList);
				}
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getLanNetworkControlPolicyDetailsJsonList(newlyAddedRecords);

	}

}
