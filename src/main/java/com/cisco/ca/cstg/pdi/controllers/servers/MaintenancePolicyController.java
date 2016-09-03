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

import com.cisco.ca.cstg.pdi.pojos.ServersMaintenancePolicy;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class MaintenancePolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MaintenancePolicyController.class);
	private final ServersService serversService;

	@Autowired
	public MaintenancePolicyController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversMaintenancePolicyConfig.html")
	public String showServersMaintenancePolicyConfig() {
		return "servers/serversMaintenancePolicy";
	}

	@RequestMapping(value = "/getMaintenancePolicyDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getMaintenancePolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersMaintenancePolicy> mPolicyList = null;

		if (projectId != null) {
			try {
				mPolicyList = this.serversService
						.fetchMaintenancePolicyDetail(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServersMaintenancePolicyConfigJsonList(mPolicyList);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServersMaintenancePolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServersMaintenancePolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersMaintenancePolicy> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersMaintenancePolicy> serversServersMaintenancePolicyList = (List<ServersMaintenancePolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersMaintenancePolicy.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateMaintenancePolicyDetails(
									serversServersMaintenancePolicyList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersMaintenancePolicy> deletedServersMaintenancePolicyList = (List<ServersMaintenancePolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersMaintenancePolicy.class);
					this.serversService
							.deleteServersMaintenancePolicyDetails(deletedServersMaintenancePolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getServersMaintenancePolicyConfigJsonList(newlyAddedRecords);
	}

	private List<Object> getServersMaintenancePolicyConfigJsonList(
			List<ServersMaintenancePolicy> mPolicyList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(mPolicyList)) {
			for (ServersMaintenancePolicy smp : mPolicyList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, smp.getId());
				myMap.put(NAME, smp.getName());
				myMap.put(DESCRIPTION, smp.getDescription());
				myMap.put(REBOOTPOLICY, smp.getRebootPolicy());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(smp.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}
