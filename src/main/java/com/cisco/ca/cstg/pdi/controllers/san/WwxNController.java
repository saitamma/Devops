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

import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.GenerateHexadecimalValues;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class WwxNController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WwxNController.class);
	private final SANService sanService;

	@Autowired
	public WwxNController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/sanWwxNConfig.html")
	public String showWwxNConfig() {
		return "san/sanWwxNConfig";
	}

	@RequestMapping(value = "/getSanWwnnConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getSanWwnnConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();

		if (projectId != null) {

			try {
				List<SanWwnn> wwnnConfigList = sanService
						.fetchSanWwnnConfiguration(projectId);

				if (Util.listNotNull(wwnnConfigList)) {
					for (SanWwnn sanWwnn : wwnnConfigList) {
						Map<String, Object> myMap = getSanWwnnMap(sanWwnn);
						Util.convertMapToJson(myMap, jsonList);
					}
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageSanWwnnConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageSanWwnnConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);
				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<SanWwnn> sanWwnnConfigList = (List<SanWwnn>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), SanWwnn.class);
					if (Util.listNotNull(sanWwnnConfigList)
							&& sanWwnnConfigList.size() == 1) {
						SanWwnn wwnnPool = sanWwnnConfigList.get(0);

						if (wwnnPool.getNoOfAddresses() != null) {
							sanWwnnConfigList.get(0).setToAddress(
									GenerateHexadecimalValues.addToPoolValue(
											wwnnPool.getFromAddress(),
											wwnnPool.getNoOfAddresses()));
						}
					}
					List<SanWwnn> newlyAddedRecords = this.sanService
							.saveOrUpdateSanWwnnConfiguration(
									sanWwnnConfigList, projectId);

					if (Util.listNotNull(newlyAddedRecords)) {
						for (SanWwnn sanWwnn : newlyAddedRecords) {
							Map<String, Object> myMap = getSanWwnnMap(sanWwnn);
							Util.convertMapToJson(myMap, jsonList);
						}
					}
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanWwnn> deletedSanWwnnList = (List<SanWwnn>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanWwnn.class);
					this.sanService.deleteSanWwnn(deletedSanWwnnList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/getSanWwpnConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getSanWwpnConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanWwpn> wwpnConfigList = null;

		if (projectId != null) {

			try {
				wwpnConfigList = sanService
						.fetchSanWwpnConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getSanWwpnConfigDetailsJsonList(wwpnConfigList);
	}

	private List<Object> getSanWwpnConfigDetailsJsonList(
			List<SanWwpn> wwpnConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(wwpnConfigList)) {
			for (SanWwpn wwpn : wwpnConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, wwpn.getId());
				myMap.put(WWPNNAME, wwpn.getWwpnName());
				myMap.put(DESCRIPTION, wwpn.getDescription());
				myMap.put(ASSIGNMENTORDER, wwpn.getAssignmentOrder());
				myMap.put(FROMADDRESS, wwpn.getFromAddress());
				myMap.put(TOADDRESS, wwpn.getToAddress());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(wwpn.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageSanWwpnConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageSanWwpnConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanWwpn> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);
				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<SanWwpn> sanWwpnConfigList = (List<SanWwpn>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), SanWwpn.class);
					if (Util.listNotNull(sanWwpnConfigList)
							&& sanWwpnConfigList.size() == 1) {
						SanWwpn wwpnPool = sanWwpnConfigList.get(0);

						if (wwpnPool.getNoOfAddresses() != null) {
							sanWwpnConfigList.get(0).setToAddress(
									GenerateHexadecimalValues.addToPoolValue(
											wwpnPool.getFromAddress(),
											wwpnPool.getNoOfAddresses()));
						}
					}
					newlyAddedRecords = this.sanService
							.saveOrUpdateSanWwpnConfiguration(
									sanWwpnConfigList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanWwpn> deletedSanWwpnList = (List<SanWwpn>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanWwpn.class);
					this.sanService.deleteSanWwpn(deletedSanWwpnList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getSanWwpnConfigDetailsJsonList(newlyAddedRecords);
	}

	private Map<String, Object> getSanWwnnMap(SanWwnn wwnn) {
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put(ID, wwnn.getId());
		myMap.put(WWNNNAME, wwnn.getWwnnName());
		myMap.put(DESCRIPTION, wwnn.getDescription());
		myMap.put(ASSIGNMENTORDER, wwnn.getAssignmentOrder());
		myMap.put(FROMADDRESS, wwnn.getFromAddress());
		myMap.put(TOADDRESS, wwnn.getToAddress());
		myMap.put(ORGANIZATIONS,
				Util.nullValidationAndReturnId(wwnn.getOrganizations()));

		return myMap;
	}
}
