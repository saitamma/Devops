package com.cisco.ca.cstg.pdi.controllers.san;

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

import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class FcAdapterPolicyController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FcAdapterPolicyController.class);
	private final SANService sanService;

	@Autowired
	public FcAdapterPolicyController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/sanAdapterPoliciesConfig.html")
	public String showAdapterPoliciesConfig() {
		return "san/sanAdapterPoliciesConfig";
	}

	@RequestMapping(value = "/getSanAdapterPoliciesDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getSanAdapterPoliciesDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanAdapterPolicies> adapterPolicyList = null;

		if (projectId != null) {
			try {
				adapterPolicyList = sanService
						.fetchSanAdapterPolicies(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getSanAdapterPolicyJsonList(adapterPolicyList);
	}

	@RequestMapping(value = "/manageSanAdapterPolicies.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageSanAdapterPolicies(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanAdapterPolicies> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);
				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<SanAdapterPolicies> sanAdapterPolicyList = (List<SanAdapterPolicies>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									SanAdapterPolicies.class);
					newlyAddedRecords = sanService.saveOrUpdateAdapterPolicies(
							sanAdapterPolicyList, projectId);

				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanAdapterPolicies> deletedAdapterPolicyList = (List<SanAdapterPolicies>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanAdapterPolicies.class);
					this.sanService
							.deleteSanAdapterPolicies(deletedAdapterPolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getSanAdapterPolicyJsonList(newlyAddedRecords);
	}

	private List<Object> getSanAdapterPolicyJsonList(
			List<SanAdapterPolicies> newlyAddedRecords) {
		List<Object> jsonList = new ArrayList<>();

		if (Util.listNotNull(newlyAddedRecords)) {
			for (SanAdapterPolicies sap : newlyAddedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, sap.getId());
				myMap.put(NAME, sap.getName());
				myMap.put(DESCRIPTION, sap.getDescription());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(sap.getOrganizations()));
				myMap.put(TRANSMITQUEUES, sap.getTransmitQueues());
				myMap.put(TRANSMITQUEUESRINGSIZE,
						sap.getTransmitQueuesRingSize());
				myMap.put(RECEIVEQUEUES, sap.getReceiveQueues());
				myMap.put(RECEIVEQUEUESRINGSIZE, sap.getReceiveQueuesRingSize());
				myMap.put(SCSIIOQUEUES, sap.getScsiIoQueues());
				myMap.put(SCSIIOQUEUESRINGSIZE, sap.getScsiIoQueuesRingSize());
				myMap.put(FCPERRORRECOVERY, sap.getFcpErrorRecovery());
				myMap.put(FLOGIRETRIES, sap.getFlogiRetries());
				myMap.put(FLOGITIMEOUT, sap.getFlogiTimeout());
				myMap.put(PLOGIRETRIES, sap.getPlogiRetries());
				myMap.put(PLOGITIMEOUT, sap.getPlogiTimeout());
				myMap.put(PORTDOWNTIMEOUT, sap.getPortDownTimeout());
				myMap.put(PORTDOWNIORETRY, sap.getPortDownIoRetry());
				myMap.put(LINKDOWNTIMEOUT, sap.getLinkDownTimeout());
				myMap.put(IOTHROTTLECOUNT, sap.getIoThrottleCount());
				myMap.put(LUNSPERTERGET, sap.getLunsPerTarget());
				myMap.put(INTERRUPTMODE, sap.getInterruptMode());
				myMap.put(SAPDEFAULT, sap.getSapDefault());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}