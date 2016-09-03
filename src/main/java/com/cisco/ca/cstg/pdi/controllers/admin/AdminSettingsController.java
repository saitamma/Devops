package com.cisco.ca.cstg.pdi.controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
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

import com.cisco.ca.cstg.pdi.pojos.Dns;
import com.cisco.ca.cstg.pdi.pojos.Ntp;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.TimeZone;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class AdminSettingsController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminSettingsController.class);

	private final AdminService adminService;

	@Autowired
	public AdminSettingsController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/adminSetting.html")
	public String showAdminSetting() {
		return "admin/adminSetting";
	}

	@RequestMapping(value = "/fetchDnsData.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> fetchDnsData(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();
		List<Dns> dnsDetailsObjList = null;

		try {
			if (projectId != null) {
				dnsDetailsObjList = this.adminService
						.fetchDNSDetails(projectId);
			}
		} catch (Exception e) {
			LOGGER.error("Error came while fetching DNS data :", e.getMessage());
			throw e;
		}
		buildDnsDataJson(returnJsonList, dnsDetailsObjList);
		return returnJsonList;
	}

	@RequestMapping(value = "/fetchNtpData.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> fetchNtpData(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();

		if (projectId != null) {
			List<Ntp> ntpDetailsObjList = this.adminService
					.fetchNTPDetails(projectId);
			buildNtpDataJson(returnJsonList, ntpDetailsObjList);
		}
		return returnJsonList;
	}

	@RequestMapping(value = "/fetchTimezoneData.html", method = RequestMethod.GET)
	@ResponseBody
	public Integer fetchTimezoneData(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		if (projectId != null) {
			return this.adminService.fetchTimezoneDetails(projectId);
		}
		return 0;
	}

	@RequestMapping(value = "/manageDnsData.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageDnsData(@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();
		List<Dns> newlySavedRecords = null;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<Dns> addOrUpdateRecords = (List<Dns>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), Dns.class);
					newlySavedRecords = adminService
							.saveOrUpdateDNSConfiguration(addOrUpdateRecords,
									projectId);
					buildDnsDataJson(returnJsonList, newlySavedRecords);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<Dns> toBeDeletedRecords = (List<Dns>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									Dns.class);
					adminService.deleteDNSConfiguration(toBeDeletedRecords);

					if (!Util.listNotNull(newlySavedRecords)) {
						returnJsonList.add(SUCCESS);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return returnJsonList;
	}

	@RequestMapping(value = "/manageNtpData.html", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageNtpData(@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();
		List<Ntp> newlySavedRecords = null;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<Ntp> addOrUpdateRecords = (List<Ntp>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), Ntp.class);
					newlySavedRecords = adminService
							.saveOrUpdateNTPConfiguration(addOrUpdateRecords,
									projectId);
					buildNtpDataJson(returnJsonList, newlySavedRecords);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<Ntp> toBeDeletedRecords = (List<Ntp>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									Ntp.class);
					adminService.deleteNTPConfiguration(toBeDeletedRecords);

					if (!Util.listNotNull(newlySavedRecords)) {
						returnJsonList.add(SUCCESS);
					}
				}
			}

		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return returnJsonList;
	}

	@RequestMapping(value = "/manageTimeZone.html", method = RequestMethod.POST)
	@ResponseBody
	public String manageTimeZone(@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		JsonNode readTree = Util.getJsonNodeByJson(jsonObj);

		if (Util.jsonNodeNotNull(readTree) && projectId != null) {
			this.adminService.saveOrUpdateTimeZone(readTree.get(TIMEZONE)
					.isNull() ? null : readTree.get(TIMEZONE).getIntValue(),
					projectId);
			return SUCCESS;
		}
		return null;
	}

	@RequestMapping(value = "/updateOrganization.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> updateOrganization(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws NumberFormatException, JsonProcessingException, IOException {

		LOGGER.info("AdminController :- updateOrganization");

		List<Object> returnJsonList = new ArrayList<Object>();
		List<Organizations> newlySavedRecords = null;
		List<Organizations> toBeDeletedRecords = null;
		boolean isOrgDeleted = false;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<Organizations> addOrUpdateRecords = (List<Organizations>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									Organizations.class);
					newlySavedRecords = adminService.saveOrUpdateOrganization(
							addOrUpdateRecords, projectId);

					if (Util.listNotNull(newlySavedRecords)) {

						for (Organizations updatedOrg : newlySavedRecords) {
							Map<String, Object> myMap = new HashMap<String, Object>();
							myMap.put(ID, updatedOrg.getId());
							myMap.put(PARENTID, updatedOrg.getParentId());
							myMap.put(NAME, updatedOrg.getName());
							Util.convertMapToJson(myMap, returnJsonList);
						}
					}
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					toBeDeletedRecords = (List<Organizations>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									Organizations.class);
					isOrgDeleted = adminService
							.deleteOrganization(toBeDeletedRecords);

					if (!isOrgDeleted && toBeDeletedRecords.size() > 0) {
						returnJsonList.add(FAILURE);
					} else {
						returnJsonList.add(SUCCESS);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return returnJsonList;
	}

	@RequestMapping(value = "/getOrganizations.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String fetchOrganizationsDetail(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId) {

		try {
			if (projectId != null) {
				Organizations organizations = adminService
						.fetchRootOrganization(projectId);
				if (organizations != null) {
					return adminService
							.convertOrganizationsDetailsInJSonFormat(organizations);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return "[]";
	}

	@RequestMapping(value = "/fetchListOfTimeZones.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> fetchListOfTimeZones() {

		List<Object> returnJsonList = new ArrayList<Object>();
		List<TimeZone> timeZoneList = this.adminService.fetchTimeZoneList();

		if (Util.listNotNull(timeZoneList)) {
			for (TimeZone timeZone : timeZoneList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, timeZone.getId());
				myMap.put(TIMEZONE, timeZone.getCountryTimeZone());
				myMap.put(COMMENTS, timeZone.getComments());
				Util.convertMapToJson(myMap, returnJsonList);
			}
		}
		return returnJsonList;
	}

	private void buildDnsDataJson(List<Object> returnJsonList,
			List<Dns> dnsDetailsObjList) {
		if (Util.listNotNull(dnsDetailsObjList)) {
			for (Dns updatedDns : dnsDetailsObjList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, updatedDns.getId());
				myMap.put(IPADDRESS, updatedDns.getIpAddress());
				Util.convertMapToJson(myMap, returnJsonList);
			}
		}
	}

	private void buildNtpDataJson(List<Object> returnJsonList,
			List<Ntp> newlySavedRecords) {
		if (Util.listNotNull(newlySavedRecords)) {
			for (Ntp updatedNtp : newlySavedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, updatedNtp.getId());
				myMap.put(IPADDRESS, updatedNtp.getIpAddress());
				Util.convertMapToJson(myMap, returnJsonList);
			}
		}
	}
}
