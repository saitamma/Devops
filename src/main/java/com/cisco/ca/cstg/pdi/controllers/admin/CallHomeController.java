package com.cisco.ca.cstg.pdi.controllers.admin;

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

import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeAlertGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomePolicy;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfile;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileAlertGroupMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileRecipientMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeSystemInventory;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class CallHomeController implements Constants {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CallHomeController.class);

	private final AdminService adminService;

	@Autowired
	public CallHomeController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/adminCallHomeSetting.html")
	public String showAdminCallHomeSetting() {
		return "admin/adminCallHomeSetting";
	}

	@RequestMapping(value = "/adminCallHome.html")
	public String showAdminCallHome() {
		return "admin/adminCallHome";
	}

	@RequestMapping(value = "/getCallHomeGeneralSetting.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getCallHomeGeneralSetting(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getCallHomeGeneralSetting");
		List<Object> jsonList = new ArrayList<Object>();
		List<Object> generalList = new ArrayList<Object>();
		List<Object> sysInventoryList = new ArrayList<Object>();
		Map<String, Object> generalMap = new HashMap<String, Object>();
		Map<String, Object> sysInventoryMap = new HashMap<String, Object>();
		Map<String, Object> myMap = new HashMap<String, Object>();
		List<AdminCallhomeGeneral> adminCallHomeSettingList = null;
		List<AdminCallhomeSystemInventory> adminCallHomeSystemInventoryList = null;
		AdminCallhomeGeneral achg = null;
		try {
			if (projectId != null) {
				adminCallHomeSettingList = adminService
						.fetchAdminCallHomeGeneralSetting(projectId);
			}

			if (Util.listNotNull(adminCallHomeSettingList)) {
				achg = adminCallHomeSettingList.get(0);
				generalMap.put(ID, achg.getId());
				generalMap.put(STATE, achg.getState());
				generalMap.put(SWITCHPRIORITY, achg.getSwitchPriority());
				generalMap.put(THROTTLING, achg.getThrottling());
				generalMap.put(CONTACT, achg.getContact());
				generalMap.put(PHONE, achg.getPhone());
				generalMap.put(EMAIL, achg.getEmail());
				generalMap.put(ADDRESS, achg.getAddress());
				generalMap.put(CUSTOMERID, achg.getCustomerId());
				generalMap.put(CONTRACTID, achg.getContractId());
				generalMap.put(SITEID, achg.getSiteId());
				generalMap.put(EMAILFROM, achg.getEmailFrom());
				generalMap.put(REPLYTO, achg.getReplyTo());
				generalMap.put(HOST, achg.getHost());
				generalMap.put(PORT, achg.getPort());
				Util.convertMapToJson(generalMap, generalList);

				adminCallHomeSystemInventoryList = adminService
						.fetchAdminCallHomeSystemInventory(projectId);

				if (Util.listNotNull(adminCallHomeSystemInventoryList)) {
					AdminCallhomeSystemInventory achsi = adminCallHomeSystemInventoryList
							.get(0);
					sysInventoryMap.put(ID, achsi.getId());
					sysInventoryMap.put(SENDPERIODICALLY,
							achsi.getSendPeriodically());
					sysInventoryMap.put(SENDINTERVALDAYS,
							achsi.getSendIntervalDays());
					sysInventoryMap.put(SENDINTERVALHOURS,
							achsi.getSendIntervalHours());
					sysInventoryMap.put(SENDINTERVALMINUTES,
							achsi.getSendIntervalMinutes());

					Util.convertMapToJson(sysInventoryMap, sysInventoryList);
				}
				myMap.put("generalList", generalList);
				myMap.put("sysInventoryList", sysInventoryList);
				Util.convertMapToJson(myMap, jsonList);

			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageCallHomeGeneralSetting.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String manageCallHomeGeneralSetting(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageCallHomeGeneralSetting");
		LOGGER.debug(ACTIVEPROJECTID + projectId);
		List<AdminCallhomeGeneral> adminCallHomeGeneralObj = null;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode generalSettingNodes = Util.getJsonNodeByName(jsonObj,
						GENERALSETTING);
				JsonNode systemInventoryNodes = Util.getJsonNodeByName(jsonObj,
						SYSTEMINVENTORY);

				if (Util.jsonNodeNotNull(generalSettingNodes)) {
					List<AdminCallhomeGeneral> adminCallHomeSettingList = (List<AdminCallhomeGeneral>) (List<?>) Util
							.convertJsonToListOfObject(
									generalSettingNodes.toString(),
									AdminCallhomeGeneral.class);
					adminCallHomeGeneralObj = this.adminService
							.saveOrUpdateAdminCallHomeGeneralSetting(
									adminCallHomeSettingList, projectId);
				}
				if (Util.jsonNodeNotNull(systemInventoryNodes)
						&& Util.listNotNull(adminCallHomeGeneralObj)) {
					List<AdminCallhomeSystemInventory> adminCallHomeSystemInventoryList = (List<AdminCallhomeSystemInventory>) (List<?>) Util
							.convertJsonToListOfObject(
									systemInventoryNodes.toString(),
									AdminCallhomeSystemInventory.class);
					this.adminService.saveOrUpdateAdminCallHomeSystemInventory(
							adminCallHomeSystemInventoryList, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return SUCCESS;
	}

	@RequestMapping(value = "/getCallHomeAlertGroup.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getCallHomeAlertGroup(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomeAlertGroup> callHomeAlertGroup = null;
		try {
			if (projectId != null) {
				callHomeAlertGroup = this.adminService
						.fetchAdminCallHomeAlertGroup(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		if (Util.listNotNull(callHomeAlertGroup)) {
			for (AdminCallhomeAlertGroup achag : callHomeAlertGroup) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, achag.getId());
				myMap.put(NAME, achag.getName());
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/getCallHomeProfile.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getCallHomeProfile(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomeProfile> callHomeProfiles = null;

		try {
			if (projectId != null) {
				callHomeProfiles = this.adminService
						.fetchAdminCallHomeProfiles(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildCallhomeProfilesDataJson(jsonList, callHomeProfiles);

		return jsonList;
	}

	@RequestMapping(value = "/manageAdminCallHomeProfile.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminCallHomeProfile(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start manageAdminCallHomeProfile");

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomeProfile> newlyAddedRecords = null;
		List<AdminCallhomeProfile> achProfileConfigList = new ArrayList<AdminCallhomeProfile>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminCallhomeProfile achprofile = (AdminCallhomeProfile) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminCallhomeProfile.class);

						List<AdminCallhomeProfileAlertGroupMapping> mappingList = new ArrayList<AdminCallhomeProfileAlertGroupMapping>();
						if (eachNode.has(ALERTGROUPID)
								&& !eachNode.get(ALERTGROUPID).isNull()) {

							String[] split = eachNode.get(ALERTGROUPID)
									.toString().replaceAll(PATTERN, "")
									.split(",");
							for (int j = 0; j < split.length; j++) {
								AdminCallhomeProfileAlertGroupMapping achpm = new AdminCallhomeProfileAlertGroupMapping();
								achpm.setId(0);
								achpm.setAdminCallhomeProfile(new AdminCallhomeProfile(
										achprofile.getId()));
								if (!(NULL_STR.equalsIgnoreCase(split[j]))
										&& split[j].length() > 0) {
									achpm.setAdminCallhomeAlertGroup(new AdminCallhomeAlertGroup(
											Integer.parseInt(split[j])));
								}
								mappingList.add(achpm);
							}
						}
						achprofile
								.setAdminCallhomeProfileAlertGroupMappings(mappingList);
						achProfileConfigList.add(achprofile);
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateAdminCallHomeProfile(
									achProfileConfigList, projectId);
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminCallhomeProfile> aCHProfileDeletedList = (List<AdminCallhomeProfile>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminCallhomeProfile.class);
					this.adminService
							.deleteAdminCallHomeProfile(aCHProfileDeletedList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		buildCallhomeProfilesDataJson(jsonList, newlyAddedRecords);
		return jsonList;
	}

	@RequestMapping(value = "/getProfileRecipientsDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getProfileRecipientsDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			@RequestParam(JSONOBJ) Integer profileId) throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomeProfileRecipientMapping> profileRecipentsList = null;

		try {
			// Fetch Existing Profile Recipients details
			if (profileId != null) {
				profileRecipentsList = this.adminService
						.fetchProfileRecipientsDetails(profileId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildCallhomeProfileRecipientsDataJson(jsonList, profileRecipentsList);
		return jsonList;
	}

	@RequestMapping(value = "/manageCallHomeProfileRecipients.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageCallHomeProfileRecipients(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		// Retrieving name values pairs from json string
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomeProfileRecipientMapping> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				// Save/Update Call Home Recipients details with all required
				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<AdminCallhomeProfileRecipientMapping> profileRecipientsList = (List<AdminCallhomeProfileRecipientMapping>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									AdminCallhomeProfileRecipientMapping.class);
					newlyAddedRecords = this.adminService
							.saveOrUpdateProfileRecipients(profileRecipientsList);
				}

				// Delete Call Home Recipients details
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminCallhomeProfileRecipientMapping> deletedProfileRecipientsList = (List<AdminCallhomeProfileRecipientMapping>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminCallhomeProfileRecipientMapping.class);
					this.adminService
							.deleteProfileRecipients(deletedProfileRecipientsList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildCallhomeProfileRecipientsDataJson(jsonList, newlyAddedRecords);
		return jsonList;
	}

	@RequestMapping(value = "/getAdminCallHomePolicyDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getAdminCallHomePolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminCallHomePilicyDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomePolicy> adminCallHomePolicyList = null;
		try {
			if (projectId != null) {
				adminCallHomePolicyList = adminService
						.fetchAdminCallHomePolicies(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildAdminCallhomePolicyDataJson(jsonList, adminCallHomePolicyList);
		LOGGER.info("End: getAdminCallHomePilicyDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminCallHomePolicyDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminCallHomePolicyDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminCallHomePolicyDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminCallhomePolicy> newlyAddedRecords = null;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);
				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<AdminCallhomePolicy> adminCallHomePolicyList = (List<AdminCallhomePolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									AdminCallhomePolicy.class);
					newlyAddedRecords = adminService
							.saveOrUpdateAdminCallHomePolicies(
									adminCallHomePolicyList, projectId);
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminCallhomePolicy> deletedAdminCallHomePolicyList = (List<AdminCallhomePolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminCallhomePolicy.class);
					this.adminService
							.deleteAdminCallHomePolicies(deletedAdminCallHomePolicyList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildAdminCallhomePolicyDataJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminCallHomePolicyDetails");
		return jsonList;
	}

	private void buildCallhomeProfilesDataJson(List<Object> jsonList,
			List<AdminCallhomeProfile> callHomeProfiles) {

		if (Util.listNotNull(callHomeProfiles)) {

			for (Object obj : callHomeProfiles) {
				AdminCallhomeProfile achp = (AdminCallhomeProfile) obj;
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, achp.getId());
				myMap.put(NAME, achp.getName());
				myMap.put(DESCRIPTION, achp.getDescription());
				myMap.put(LEVEL, achp.getLevel());
				myMap.put(FORMAT, achp.getFormat());
				myMap.put(MAXMSGSIZE, achp.getMaxMsgSize());

				Integer[] alertGroupMappingArr = null;
				List<AdminCallhomeProfileAlertGroupMapping> profileAlertGroupMappingList = achp
						.getAdminCallhomeProfileAlertGroupMappings();

				if (Util.listNotNull(profileAlertGroupMappingList)) {
					for (int i = 0; i < profileAlertGroupMappingList.size(); i++) {
						AdminCallhomeProfileAlertGroupMapping mapping = profileAlertGroupMappingList
								.get(i);
						if (i == 0) {
							alertGroupMappingArr = new Integer[profileAlertGroupMappingList
									.size()];
						}
						alertGroupMappingArr[i] = (Integer) Util
								.nullValidationAndReturnId(mapping
										.getAdminCallhomeAlertGroup());
					}
				}
				myMap.put(ALERTGROUPID, alertGroupMappingArr);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildCallhomeProfileRecipientsDataJson(List<Object> jsonList,
			List<AdminCallhomeProfileRecipientMapping> profileRecipentsList) {
		if (Util.listNotNull(profileRecipentsList)) {
			for (AdminCallhomeProfileRecipientMapping achrm : profileRecipentsList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, achrm.getId());
				myMap.put(PROFILEID, Util.nullValidationAndReturnId(achrm
						.getAdminCallhomeProfile()));
				myMap.put(EMAIL, achrm.getEmail());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildAdminCallhomePolicyDataJson(List<Object> jsonList,
			List<AdminCallhomePolicy> adminCallHomePolicyList) {

		if (Util.listNotNull(adminCallHomePolicyList)) {
			for (AdminCallhomePolicy adminChp : adminCallHomePolicyList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, adminChp.getId());
				myMap.put(STATE, adminChp.getState());
				myMap.put(CAUSE, adminChp.getCause());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

}
