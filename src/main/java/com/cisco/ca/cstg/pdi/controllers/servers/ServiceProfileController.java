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

import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfile;
import com.cisco.ca.cstg.pdi.services.ServiceTemplateService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ServiceProfileController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProfileController.class);

	private final ServiceTemplateService serviceTemplateService;

	@Autowired
	public ServiceProfileController(
			ServiceTemplateService serviceTemplateService) {
		this.serviceTemplateService = serviceTemplateService;
	}

	@RequestMapping(value = "/serversServiceProfileConfig.html")
	public String showserversServiceTempProfileConfig() {
		return "servers/serversServiceTempProfileConfig";
	}

	@RequestMapping(value = "/getServiceProfileDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getServiceProfileDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersServiceProfile> serviceProfilesList = null;

		if (projectId != null) {
			try {
				serviceProfilesList = serviceTemplateService
						.fetchServersServiceProfileDetails(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServiceProfileDetailsJsonList(serviceProfilesList);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServiceProfile.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServiceProfile(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersServiceProfile> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						Constants.ADDORUPDATE);
				JsonNode deletedNodes = Util.getJsonNodeByName(jsonObj,
						Constants.DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersServiceProfile> serviceProfilesList = (List<ServersServiceProfile>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersServiceProfile.class);

					newlyAddedRecords = this.serviceTemplateService
							.saveOrUpdateServersServiceProfileDetails(
									serviceProfilesList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersServiceProfile> deletedServiceProfilesList = (List<ServersServiceProfile>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersServiceProfile.class);
					this.serviceTemplateService
							.deleteServersServiceProfiles(deletedServiceProfilesList);
				}
			} catch (Exception e) {
				LOGGER.error("Exception in manageServiceProfile method.", e);
				throw e;
			}
		}
		return getServiceProfileDetailsJsonList(newlyAddedRecords);
	}

	private List<Object> getServiceProfileDetailsJsonList(
			List<ServersServiceProfile> serviceProfilesList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(serviceProfilesList)) {
			for (ServersServiceProfile serversServiceProfile : serviceProfilesList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, serversServiceProfile.getId());
				myMap.put(NAME, serversServiceProfile.getName());
				myMap.put(DESCRIPTION, serversServiceProfile.getDescription());
				myMap.put(SERVERSSERVICEPROFILETEMPLATE, Util
						.nullValidationAndReturnId(serversServiceProfile
								.getServersServiceProfileTemplate()));
				myMap.put(ORGANIZATIONS, Util
						.nullValidationAndReturnId(serversServiceProfile
								.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}
