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

import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class VhbaController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VhbaController.class);
	private final SANService sanService;

	@Autowired
	public VhbaController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/sanVHBAConfig.html")
	public String showSanVHBAConfig() {
		return "san/sanVHBAConfig";
	}

	@RequestMapping(value = "/getSanVhbaDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getSanVhbaDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<SanVhba> sanVhbaList = null;
		if (projectId != null) {

			try {
				sanVhbaList = sanService.fetchSanVhbaDetail(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getSanVhbaJsonList(sanVhbaList);
	}

	@RequestMapping(value = "/manageSanVhba.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageSanVhba(@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<SanVhba> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<SanVhba> sanVhbaList = (List<SanVhba>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(), SanVhba.class);
					newlyAddedRecords = sanService.saveOrUpdateSanVhba(
							sanVhbaList, projectId);
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanVhba> deletedSanVhbaList = (List<SanVhba>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanVhba.class);
					this.sanService.deleteSanVhba(deletedSanVhbaList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getSanVhbaJsonList(newlyAddedRecords);
	}

	private List<Object> getSanVhbaJsonList(List<SanVhba> newlyAddedRecords) {
		List<Object> jsonList = new ArrayList<>();

		if (Util.listNotNull(newlyAddedRecords)) {
			for (SanVhba svhba : newlyAddedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, svhba.getId());
				myMap.put(NAME, svhba.getName());
				myMap.put(SANVHBATEMPLATE, Util.nullValidationAndReturnId(svhba
						.getSanVhbaTemplate()));
				myMap.put(SANADAPTERPOLICY, Util
						.nullValidationAndReturnId(svhba
								.getSanAdapterPolicies()));
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}