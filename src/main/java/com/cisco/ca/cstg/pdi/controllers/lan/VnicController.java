package com.cisco.ca.cstg.pdi.controllers.lan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
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

import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class VnicController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VnicController.class);
	private final LANService lanService;
	

	@Autowired
	public VnicController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanVnicConfig.html")
	public String showLanVnicConfig() {
		return "lan/lanVnicConfig";
	}

	@RequestMapping(value = "/getLanVnicDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getLanVnicDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<LanVnic> lanVnicD = null;

		try {
			if (projectId != null) {
				lanVnicD = lanService.fetchLanVnic(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return getLanVnicDetailsJsonList(lanVnicD);
	}

	private List<Object> getLanVnicDetailsJsonList(List<LanVnic> lanVnicD) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(lanVnicD)) {
			for (LanVnic lvnic : lanVnicD) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, lvnic.getId());
				myMap.put(NAME, lvnic.getName());
				if (lvnic.getLanVnicTemplate() != null) {
					myMap.put(LANVNICTEMPLATE, lvnic.getLanVnicTemplate()
							.getId());
				} else {
					myMap.put(LANVNICTEMPLATE, null);
				}
				if (lvnic.getLanEthernetAdapterPolicies() != null) {
					myMap.put(LANETHERNETADAPTERPOLICIES, lvnic
							.getLanEthernetAdapterPolicies().getId());
				} else {
					myMap.put(LANETHERNETADAPTERPOLICIES, null);
				}
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanVnic.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanVnic(@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<LanVnic> newlyAddedRecords = null;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode readTree = mapper.readTree(jsonObj);
				JsonNode addOrUpdateNodes = readTree.get(ADDORUPDATE);
				JsonNode deletedNodes = readTree.get(DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<LanVnic> lanVnicList = (List<LanVnic>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(), LanVnic.class);
					newlyAddedRecords = lanService.saveOrUpdateLanVnic(
							lanVnicList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanVnic> deletedVnicList = (List<LanVnic>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanVnic.class);
					this.lanService.deleteLanVnicProfile(deletedVnicList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getLanVnicDetailsJsonList(newlyAddedRecords);
	}

}
