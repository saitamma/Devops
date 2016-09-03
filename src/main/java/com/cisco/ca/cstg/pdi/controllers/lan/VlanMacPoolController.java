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

import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.GenerateMacAddressesUtil;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class VlanMacPoolController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VlanMacPoolController.class);
	private final LANService lanService;
	

	@Autowired
	public VlanMacPoolController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanVlanMacPoolConfig.html")
	public String showVlanMacPool() {
		return "lan/lanVlanMacPoolConfig";
	}

	@RequestMapping(value = "/getLanVlanConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanVlanConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getLanVlanConfigDetails");
		List<LanVlan> vlanConfigList = null;

		try {
			if (projectId != null) {
				vlanConfigList = lanService
						.fetchLanVlanConfiguration(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: getLanVlanConfigDetails");
		return getLanVlanConfigDetailsJsonList(vlanConfigList);
	}

	private List<Object> getLanVlanConfigDetailsJsonList(
			List<LanVlan> vlanConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(vlanConfigList)) {
			for (LanVlan lvlan : vlanConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, lvlan.getId());
				myMap.put(VLANNAME, lvlan.getVlanName());
				myMap.put(DESCRIPTION, lvlan.getDescription());
				myMap.put(VLANID, lvlan.getVlanId());
				myMap.put(VLANDEFAULT, lvlan.getVlanDefault());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanVlanConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanVlanConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageLanVlanConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanVlan> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<LanVlan> vlanConfigList = (List<LanVlan>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), LanVlan.class);
					newlyAddedRecords = this.lanService
							.saveOrUpdateLanVlanConfiguration(vlanConfigList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanVlan> deletedVlanList = (List<LanVlan>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanVlan.class);
					this.lanService.deleteLanVlan(deletedVlanList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: manageLanVlanConfig");
		return getLanVlanConfigDetailsJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getLanMacpoolConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanMacpoolConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getLanMacpoolConfigDetails");
		List<LanMacpool> macpoolConfigList = null;

		try {
			if (projectId != null) {
				macpoolConfigList = lanService
						.fetchLanMacpoolConfiguration(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: getLanMacpoolConfigDetails");
		return getLanMacpoolConfigDetailsJsonList(macpoolConfigList);
	}

	private List<Object> getLanMacpoolConfigDetailsJsonList(
			List<LanMacpool> macpoolConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(macpoolConfigList)) {
			for (LanMacpool macpool : macpoolConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, macpool.getId());
				myMap.put(MACPOOLNAME, macpool.getMacpoolName());
				myMap.put(MACPOOLDESCRIPTION, macpool.getMacpoolDescription());
				myMap.put(ASSIGNMENTORDER, macpool.getAssignmentOrder());
				myMap.put(FROMADDRESS, macpool.getFromAddress());
				myMap.put(TOADDRESS, macpool.getToAddress());
				if (macpool.getOrganizations() != null) {
					myMap.put(ORGANIZATIONS, macpool.getOrganizations().getId());
				}

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanMacpoolConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanMacpoolConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageLanMacpoolConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanMacpool> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<LanMacpool> macpoolConfigList = (List<LanMacpool>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									LanMacpool.class);
					if (macpoolConfigList != null
							&& macpoolConfigList.size() == 1) {
						LanMacpool macPool = macpoolConfigList.get(0);
						if (macPool.getNoOfAddresses() != null) {
							macpoolConfigList.get(0).setToAddress(
									GenerateMacAddressesUtil.addToPoolValue(
											macPool.getFromAddress(),
											macPool.getNoOfAddresses()));
						}
					}
					newlyAddedRecords = this.lanService
							.saveOrUpdateLanMacpoolConfiguration(
									macpoolConfigList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanMacpool> deletedMacpoolList = (List<LanMacpool>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanMacpool.class);
					this.lanService.deleteLanMacpool(deletedMacpoolList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: manageLanMacpoolConfig");
		return getLanMacpoolConfigDetailsJsonList(newlyAddedRecords);
	}

}
