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

import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class VhbaTemplateController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VhbaTemplateController.class);
	private final SANService sanService;

	@Autowired
	public VhbaTemplateController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/sanVHBATempConfig.html")
	public String showVHBATempConfig() {
		return "san/sanVHBATempConfig";
	}

	@RequestMapping(value = "/getSanVhbaTemplateConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getSanVhbaTemplateConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanVhbaTemplate> vhbatConfigList = null;

		if (projectId != null) {

			try {
				vhbatConfigList = sanService
						.fetchSanVhbaTemplateConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return returnSanVhbaTemplateJsonList(vhbatConfigList);
	}

	@RequestMapping(value = "/manageSanVhbaTemplateConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageSanVhbaTemplateConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanVhbaTemplate> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);
				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<SanVhbaTemplate> sanVhbaConfigList = (List<SanVhbaTemplate>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									SanVhbaTemplate.class);
					newlyAddedRecords = this.sanService
							.saveOrUpdateSanVhbaTemplateConfiguration(
									sanVhbaConfigList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanVhbaTemplate> deletedSanVhbaList = (List<SanVhbaTemplate>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanVhbaTemplate.class);
					this.sanService.deleteSanVhbaTemplate(deletedSanVhbaList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return returnSanVhbaTemplateJsonList(newlyAddedRecords);
	}

	private List<Object> returnSanVhbaTemplateJsonList(
			List<SanVhbaTemplate> newlyAddedRecords) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(newlyAddedRecords)) {
			for (SanVhbaTemplate vhbat : newlyAddedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, vhbat.getId());
				myMap.put(LANQASPOLICY,
						Util.nullValidationAndReturnId(vhbat.getLanQosPolicy()));
				myMap.put(VHBANAME, vhbat.getVhbaName());
				myMap.put(DESCRIPTION, vhbat.getDescription());
				myMap.put(SANWWPN,
						Util.nullValidationAndReturnId(vhbat.getSanWwpn()));
				myMap.put(SWITCHID, vhbat.getSwitchId());
				myMap.put(TEMPLATETYPE, vhbat.getTemplateType());
				myMap.put(SANVSAN,
						Util.nullValidationAndReturnId(vhbat.getSanVsan()));
				myMap.put(ORGANIZATIONS, Util.nullValidationAndReturnId(vhbat
						.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}