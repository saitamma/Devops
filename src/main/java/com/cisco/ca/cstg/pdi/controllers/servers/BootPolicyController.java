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

import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySanTarget;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class BootPolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BootPolicyController.class);
	private final ServersService serversService;

	@Autowired
	public BootPolicyController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversBootPolicyConfig.html")
	public String showBootPolicyConfig() {
		return "servers/serversBootPolicyConfig";
	}

	@RequestMapping(value = "/getBootPolicyConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getBootPolicyConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersBootPolicy> bootPolicyConfigList = null;

		if (projectId != null) {

			try {
				bootPolicyConfigList = serversService
						.fetchServersBootPolicyConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getBootPolicyConfigJsonList(bootPolicyConfigList);
	}

	private List<Object> getBootPolicyConfigJsonList(
			List<ServersBootPolicy> bootPolicyConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(bootPolicyConfigList)) {
			for (ServersBootPolicy bp : bootPolicyConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, bp.getId());
				myMap.put(NAME, bp.getName());
				myMap.put(DESCRIPTION, bp.getDescription());
				myMap.put(ENFORCEVNICNAME, bp.getEnforceVnicName());
				myMap.put(REBOOTONUPDATE, bp.getRebootOnUpdate());
				myMap.put(BOOTMODE, bp.getBootMode());
				myMap.put(SECUREBOOT, bp.getSecureBoot());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(bp.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageBootPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageBootPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersBootPolicy> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersBootPolicy> serversBootPolicyList = (List<ServersBootPolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersBootPolicy.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersBootPolicyConfiguration(
									serversBootPolicyList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersBootPolicy> deletedBootPolicyList = (List<ServersBootPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersBootPolicy.class);
					this.serversService
							.deleteServersBootPolicy(deletedBootPolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getBootPolicyConfigJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getBootPolicyLanConfigDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getBootPolicyLanConfigDetails(
			@RequestParam(JSONOBJ) Integer bootPolicyId) throws IOException {
		List<ServersBootPolicyLan> bootPolicyLanConfigList = null;

		if (bootPolicyId != null) {
			try {
				bootPolicyLanConfigList = serversService
						.fetchBootPolicyLanConfigDetails(bootPolicyId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getBootPolicyLanConfigJsonList(bootPolicyLanConfigList);
	}

	private List<Object> getBootPolicyLanConfigJsonList(
			List<ServersBootPolicyLan> bootPolicyLanConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(bootPolicyLanConfigList)) {
			for (ServersBootPolicyLan bpl : bootPolicyLanConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, bpl.getId());
				myMap.put(NAME, bpl.getName());
				myMap.put(DESCRIPTION, bpl.getDescription());
				myMap.put(ORDER, bpl.getOrder());
				myMap.put(LANVNIC,
						Util.nullValidationAndReturnId(bpl.getLanVnic()));
				myMap.put(TYPE, bpl.getType());
				myMap.put(SERVERSBOOTPOLICY, Util.nullValidationAndReturnId(bpl
						.getServersBootPolicy()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageBootPolicyLanConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageBootPolicyLanConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersBootPolicyLan> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersBootPolicyLan> serversBootPolicayLanList = (List<ServersBootPolicyLan>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersBootPolicyLan.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersBootPolicyLanConfiguration(
									serversBootPolicayLanList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersBootPolicyLan> deletedBootPolicyLanList = (List<ServersBootPolicyLan>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersBootPolicyLan.class);
					this.serversService
							.deleteServersBootPolicyLan(deletedBootPolicyLanList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getBootPolicyLanConfigJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getBootPolicySanConfigDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getBootPolicySanConfigDetails(
			@RequestParam(JSONOBJ) Integer bootPolicyId) throws IOException {
		List<ServersBootPolicySan> bootPolicySanConfigList = null;

		if (bootPolicyId != null) {
			try {
				bootPolicySanConfigList = serversService
						.fetchBootPolicySanConfigDetails(bootPolicyId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getBootPolicySanConfigJsonList(bootPolicySanConfigList);
	}

	private List<Object> getBootPolicySanConfigJsonList(
			List<ServersBootPolicySan> bootPolicySanConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(bootPolicySanConfigList)) {
			for (ServersBootPolicySan bps : bootPolicySanConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, bps.getId());
				myMap.put(NAME, bps.getName());
				myMap.put(DESCRIPTION, bps.getDescription());
				myMap.put(ORDER, bps.getOrder());
				myMap.put(SANVHBA,
						Util.nullValidationAndReturnId(bps.getSanVhba()));
				myMap.put(TYPE, bps.getType());
				myMap.put(SERVERSBOOTPOLICY, Util.nullValidationAndReturnId(bps
						.getServersBootPolicy()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageBootPolicySanConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageBootPolicySanConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersBootPolicySan> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersBootPolicySan> serversBootPolicaySanList = (List<ServersBootPolicySan>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersBootPolicySan.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersBootPolicySanConfiguration(
									serversBootPolicaySanList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersBootPolicySan> deletedBootPolicySanList = (List<ServersBootPolicySan>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersBootPolicySan.class);
					this.serversService
							.deleteServersBootPolicySan(deletedBootPolicySanList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getBootPolicySanConfigJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getBootPolicySanTargetConfigDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getBootPolicySanTargetConfigDetails(
			@RequestParam(JSONOBJ) Integer bootPolicyId) throws IOException {
		List<ServersBootPolicySanTarget> bootPolicySanTargetConfigList = null;

		if (bootPolicyId != null) {

			try {
				bootPolicySanTargetConfigList = serversService
						.fetchServersBootPolicySanTargetConfiguration(bootPolicyId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getBootPolicySanTargetConfigJsonList(bootPolicySanTargetConfigList);
	}

	private List<Object> getBootPolicySanTargetConfigJsonList(
			List<ServersBootPolicySanTarget> bootPolicySanTargetConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(bootPolicySanTargetConfigList)) {
			for (ServersBootPolicySanTarget bpst : bootPolicySanTargetConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, bpst.getId());
				myMap.put(NAME, bpst.getName());
				myMap.put(TYPE, bpst.getType());
				myMap.put(LUNID, bpst.getLunId());
				myMap.put(WWPNADDRESS, bpst.getWwpnAddress());
				myMap.put(SERVERSBOOTPOLICYSAN, Util
						.nullValidationAndReturnId(bpst
								.getServersBootPolicySan()));
				myMap.put(SERVERSBOOTPOLICY, Util
						.nullValidationAndReturnId(bpst.getServersBootPolicy()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageBootPolicySanTargetConfig.html", method = RequestMethod.POST)
	@ResponseBody
	public List<Object> manageBootPolicySanTargetConfig(
			@RequestParam(JSONOBJ) String jsonObj) throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);

		List<ServersBootPolicySanTarget> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj)) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersBootPolicySanTarget> serversBootPolicaySanTargetList = (List<ServersBootPolicySanTarget>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersBootPolicySanTarget.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersBootPolicySanTargetConfiguration(serversBootPolicaySanTargetList);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersBootPolicySanTarget> deletedBootPolicySanTargetList = (List<ServersBootPolicySanTarget>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersBootPolicySanTarget.class);
					this.serversService
							.deleteServersBootPolicySanTarget(deletedBootPolicySanTargetList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getBootPolicySanTargetConfigJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getBootPolicyLocalDiscConfigDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getBootPolicyLocalDiscConfigDetails(
			@RequestParam(JSONOBJ) Integer bootPolicyId) throws IOException {
		List<ServersBootPolicyLocalDisc> bootPolicySanConfigList = null;

		if (bootPolicyId != null) {
			try {
				bootPolicySanConfigList = serversService
						.fetchBootPolicyLocalDiscConfigDetails(bootPolicyId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getBootPolicyLocalDiscConfigJsonList(bootPolicySanConfigList);
	}

	private List<Object> getBootPolicyLocalDiscConfigJsonList(
			List<ServersBootPolicyLocalDisc> bootPolicySanConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(bootPolicySanConfigList)) {
			for (ServersBootPolicyLocalDisc bpld : bootPolicySanConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, bpld.getId());
				myMap.put(DEVICE, bpld.getDevice());
				myMap.put(BOOTORDER, bpld.getBootOrder());
				myMap.put(SERVERSBOOTPOLICY, Util
						.nullValidationAndReturnId(bpld.getServersBootPolicy()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageBootPolicyLocalDiscConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageBootPolicyLocalDiscConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersBootPolicyLocalDisc> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode readTree = Util.getJsonNodeByJson(jsonObj);

				if (Util.jsonNodeNotNull(readTree)) {
					List<ServersBootPolicyLocalDisc> serversBootPolicayLocalDiscList = (List<ServersBootPolicyLocalDisc>) (List<?>) Util
							.convertJsonToListOfObject(readTree.toString(),
									ServersBootPolicyLocalDisc.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersBootPolicyLocalDiscConfiguration(
									serversBootPolicayLocalDiscList, projectId);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getBootPolicyLocalDiscConfigJsonList(newlyAddedRecords);
	}
}
