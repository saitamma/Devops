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

import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class QosPolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QosPolicyController.class);
	private final LANService lanService;
	

	@Autowired
	public QosPolicyController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanQosPolicy.html")
	public String showLanqosPolicy() {
		return "lan/lanQosPolicy";
	}

	@RequestMapping(value = "/getLanQosPolicyDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanQosPolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getLanQosPolicyDetails");
		List<LanQosPolicy> qosConfigList = null;

		try {
			if (projectId != null) {
				qosConfigList = lanService
						.fetchLanQosPolicyConfiguration(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: getLanQosPolicyDetails");
		return getLanQosPolicyDetailsJsonList(qosConfigList);
	}

	private List<Object> getLanQosPolicyDetailsJsonList(
			List<LanQosPolicy> qosConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(qosConfigList)) {
			for (LanQosPolicy lqos : qosConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, lqos.getId());
				myMap.put(NAME, lqos.getName());
				myMap.put(BURST, lqos.getBurst());
				myMap.put(HOSTCONTROL, lqos.getHostControl());
				myMap.put(PRIORITY, lqos.getPriority());
				myMap.put(RATE, lqos.getRate());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(lqos.getOrganizations()));
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanQosPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanQosPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageLanQosPolicyConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanQosPolicy> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<LanQosPolicy> qosConfigList = (List<LanQosPolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									LanQosPolicy.class);
					newlyAddedRecords = this.lanService
							.saveOrUpdateQosPolicyConfiguration(qosConfigList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanQosPolicy> deletedQosList = (List<LanQosPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanQosPolicy.class);
					this.lanService.deleteLanQosPolicy(deletedQosList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: manageLanQosPolicyConfig");
		return getLanQosPolicyDetailsJsonList(newlyAddedRecords);
	}
}
