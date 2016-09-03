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

import com.cisco.ca.cstg.pdi.pojos.SanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class SanConnectivityPolicyController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SanConnectivityPolicyController.class);
	private final SANService sanService;

	@Autowired
	public SanConnectivityPolicyController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/sanConnectivityPolicy.html")
	public String showSanConnectivityPolicy() {
		return "san/sanConnectivityPolicy";
	}

	@RequestMapping(value = "/getSanConnectivityPolicyDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getSanConnectivityPolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getSanConnectivityPolicyDetails");
		List<SanConnectivityPolicy> connectivityPolicyConfigList = null;

		if (projectId != null) {

			try {
				connectivityPolicyConfigList = sanService
						.fetchSanConnectivityPolicyDetail(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		LOGGER.info("End: getSanConnectivityPolicyDetails");
		return returnSanConnectivityPolicyJsonList(connectivityPolicyConfigList);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageSanConnectivityPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageSanConnectivityPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageSanConnectivityPolicyConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<SanConnectivityPolicy> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<SanConnectivityPolicy> scpConfigList = new ArrayList<SanConnectivityPolicy>();

					for (int i = 0; i < addOrUpdateNodes.size(); i++) {
						JsonNode eachNode = addOrUpdateNodes.get(i);
						SanConnectivityPolicy scp = (SanConnectivityPolicy) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										SanConnectivityPolicy.class);

						List<SanScpVhbaMapping> mappingList = new ArrayList<SanScpVhbaMapping>();
						if (eachNode.has(VHBAID)
								&& !eachNode.get(VHBAID).isNull()) {
							String[] split = eachNode.get(VHBAID).toString()
									.replaceAll(PATTERN, "").split(",");
							for (int j = 0; j < split.length; j++) {
								SanScpVhbaMapping ssvm = new SanScpVhbaMapping();
								ssvm.setId(0);
								ssvm.setSanConnectivityPolicy(scp);
								SanVhba sanVhba = new SanVhba();
								if (!NULL_STR.equalsIgnoreCase(split[j])) {
									sanVhba.setId(Integer.parseInt(split[j]));
								}
								ssvm.setSanVhba(sanVhba);
								mappingList.add(ssvm);
							}
						}
						scp.setSanScpVhbaMappings(mappingList);
						scpConfigList.add(scp);
					}
					newlyAddedRecords = this.sanService
							.saveOrUpdateSanConnectivityPolicy(scpConfigList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<SanConnectivityPolicy> deletedscpList = (List<SanConnectivityPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									SanConnectivityPolicy.class);
					this.sanService.deleteSanConnectivityPolicy(deletedscpList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		LOGGER.info("End: manageSanVhbaConfig");
		return returnSanConnectivityPolicyJsonList(newlyAddedRecords);
	}

	private List<Object> returnSanConnectivityPolicyJsonList(
			List<SanConnectivityPolicy> newlyAddedRecords) {
		List<Object> jsonList = new ArrayList<>();

		if (Util.listNotNull(newlyAddedRecords)) {
			for (SanConnectivityPolicy scp : newlyAddedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, scp.getId());
				myMap.put(NAME, scp.getName());
				myMap.put(DESCRIPTION, scp.getDescription());
				myMap.put(SANWWNN, scp.getSanWwnn() != null ? scp.getSanWwnn()
						.getId() : null);
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(scp.getOrganizations()));

				Integer[] vhbaArray = null;
				List<SanScpVhbaMapping> scpVhbaMappingList = scp
						.getSanScpVhbaMappings();
				if (Util.listNotNull(scpVhbaMappingList)) {
					for (int i = 0; i < scpVhbaMappingList.size(); i++) {
						SanScpVhbaMapping mapping = scpVhbaMappingList.get(i);
						if (i == 0) {
							vhbaArray = new Integer[scpVhbaMappingList.size()];
						}
						if (mapping.getSanVhba() != null) {
							vhbaArray[i] = mapping.getSanVhba().getId();
						}
					}
				}
				myMap.put(VHBAID, vhbaArray);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}