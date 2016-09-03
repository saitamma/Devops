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

import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class VsanController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VsanController.class);
	private final SANService sanService;

	@Autowired
	public VsanController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/sanVsanConfig.html")
	public String showVsanConfig() {
		return "san/sanVsanConfig";
	}

	@RequestMapping(value = "/getSanVSanConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getSanVSanConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();

		if (projectId != null) {

			try {
				List<SanVsan> fetchSanVsanConfiguration = sanService
						.fetchSanVsanConfiguration(projectId);

				if (Util.listNotNull(fetchSanVsanConfiguration)) {
					for (SanVsan sanVsan : fetchSanVsanConfiguration) {
						Map<String, Object> myMap = getSanVsanMap(sanVsan);
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

	@RequestMapping(value = "/manageSanVSanConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageSanVSanConfig(
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
					List<SanVsan> sanVsanConfigList = (List<SanVsan>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), SanVsan.class);
					List<SanVsan> newlyAddedRecords = this.sanService
							.saveOrUpdateSanVsanConfiguration(
									sanVsanConfigList, projectId);

					if (Util.listNotNull(newlyAddedRecords)) {
						for (SanVsan sanVsan : newlyAddedRecords) {
							Map<String, Object> myMap = getSanVsanMap(sanVsan);
							Util.convertMapToJson(myMap, jsonList);
						}
					}
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanVsan> deletedSanVsanList = (List<SanVsan>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanVsan.class);
					this.sanService.deleteSanVsan(deletedSanVsanList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return jsonList;
	}

	/* Added as part of sonar fixes: duplicate code */
	private Map<String, Object> getSanVsanMap(SanVsan sVsan) {
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put(ID, sVsan.getId());
		myMap.put(FIID, sVsan.getFiId());
		myMap.put(VSANNAME, sVsan.getVsanName());
		myMap.put(DESCRIPTION, sVsan.getDescription());
		myMap.put(FCOEVSANID, sVsan.getFcoeVsanId());
		myMap.put(FCOEVLANNAME, sVsan.getFcoeVlanName());

		return myMap;
	}

}
