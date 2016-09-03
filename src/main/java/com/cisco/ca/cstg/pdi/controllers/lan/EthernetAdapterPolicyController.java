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

import com.cisco.ca.cstg.pdi.pojos.LanEthernetAdapterPolicies;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class EthernetAdapterPolicyController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EthernetAdapterPolicyController.class);
	private final LANService lanService;
	

	@Autowired
	public EthernetAdapterPolicyController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanEthernetAdapterPolicy.html")
	public String showLanEthernetAdapterPolicies() {
		return "lan/lanEthernetAdapterPolicy";
	}

	@RequestMapping(value = "/getLanEthernetAdapterPoliciesDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getLanEthernetAdapterPoliciesDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start : getLanEthernetAdapterPoliciesDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);
		List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicyList = null;
		try {
			if (projectId != null) {
				lanEthernetAdapterPolicyList = lanService
						.fetchLanEthernetAdapterPolicies(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End : getLanEthernetAdapterPoliciesDetails");
		return getLanEthernetAdapterPoliciesDetailsJsonList(lanEthernetAdapterPolicyList);
	}

	private List<Object> getLanEthernetAdapterPoliciesDetailsJsonList(
			List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicyList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(lanEthernetAdapterPolicyList)) {
			for (LanEthernetAdapterPolicies lanEapObj : lanEthernetAdapterPolicyList) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, lanEapObj.getId());
				myMap.put(NAME, lanEapObj.getName());
				myMap.put(DESCRIPTION, lanEapObj.getDescription());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(lanEapObj
								.getOrganizations()));

				myMap.put(TRANSMITQUEUES, lanEapObj.getTransmitQueues());
				myMap.put(TRANSMITQUEUESRINGSIZE,
						lanEapObj.getTransmitQueuesRingSize());
				myMap.put(RECEIVEQUEUES, lanEapObj.getReceiveQueues());
				myMap.put(RECEIVEQUEUESRINGSIZE,
						lanEapObj.getReceiveQueuesRingSize());
				myMap.put(COMPLETIONQUEUES, lanEapObj.getCompletionQueues());
				myMap.put(COMPLETIONQUEUESINTERRUPTS,
						lanEapObj.getCompletionQueuesInterrupts());
				myMap.put(TRANSMITCHECKSUMOFFLOAD,
						lanEapObj.getTransmitChecksumOffload());
				myMap.put(RECEIVECHECKSUMOFFLOAD,
						lanEapObj.getReceiveChecksumOffload());
				myMap.put(TCPSEGMENTATIONOFFLOAD,
						lanEapObj.getTcpSegmentationOffload());
				myMap.put(TCPLARGERECEIVEOFFLOAD,
						lanEapObj.getTcpLargeReceiveOffload());
				myMap.put(RECEIVESIDESCALING, lanEapObj.getReceiveSideScaling());
				myMap.put(ACCELERATEDRECEIVEFLOWSTEERING,
						lanEapObj.getAcceleratedReceiveFlowSteering());
				myMap.put(FAILBACKTIMEOUT, lanEapObj.getFailbackTimeout());
				myMap.put(INTERRUPTMODE, lanEapObj.getInterruptMode());
				myMap.put(INTERRUPTCOALESCINGTYPE,
						lanEapObj.getInterruptCoalescingType());
				myMap.put(INTERRUPTTIMER, lanEapObj.getInterruptTimer());
				myMap.put(LEAPDEFAULT, lanEapObj.getLeapDefault());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanEthernetAdapterPolicies.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanEthernetAdapterPolicies(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start : manageLanEthernetAdapterPolicies");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);
		List<LanEthernetAdapterPolicies> newlyAddedRecords = null;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicyList = (List<LanEthernetAdapterPolicies>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									LanEthernetAdapterPolicies.class);
					newlyAddedRecords = lanService
							.saveOrUpdateLanEthernetAdapterPolicies(
									lanEthernetAdapterPolicyList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanEthernetAdapterPolicies> deletedLanEthernetAdapterPolicyList = (List<LanEthernetAdapterPolicies>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanEthernetAdapterPolicies.class);
					this.lanService
							.deleteLanEthernetAdapterPolicies(deletedLanEthernetAdapterPolicyList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End : manageLanEthernetAdapterPolicies");
		return getLanEthernetAdapterPoliciesDetailsJsonList(newlyAddedRecords);
	}
}
