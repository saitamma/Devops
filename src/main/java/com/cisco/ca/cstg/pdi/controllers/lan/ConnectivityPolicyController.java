package com.cisco.ca.cstg.pdi.controllers.lan;

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

import com.cisco.ca.cstg.pdi.pojos.LanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanLcpVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ConnectivityPolicyController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConnectivityPolicyController.class);
	private final LANService lanService;	

	@Autowired
	public ConnectivityPolicyController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanConnectivityPolicy.html")
	public String showLanConnectivityPolicy() {
		return "lan/lanConnectivityPolicy";
	}

	@RequestMapping(value = "/getLanConnectivityPolicyDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanConnectivityPolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getLanConnectivityPolicyDetails");
		List<LanConnectivityPolicy> lanConnectivityPolicyConfigList = null;

		try {
			if (projectId != null) {
				lanConnectivityPolicyConfigList = lanService
						.fetchLanConnectivityPolicyDetail(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: getLanConnectivityPolicyDetails");
		return getLanConnectivityPolicyDetailsJsonList(lanConnectivityPolicyConfigList);
	}

	private List<Object> getLanConnectivityPolicyDetailsJsonList(
			List<LanConnectivityPolicy> lanConnectivityPolicyConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(lanConnectivityPolicyConfigList)) {
			for (LanConnectivityPolicy lcp : lanConnectivityPolicyConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, lcp.getId());
				myMap.put(NAME, lcp.getName());
				myMap.put(DESCRIPTION, lcp.getDescription());
				if (lcp.getOrganizations() != null) {
					myMap.put(ORGANIZATIONS, lcp.getOrganizations().getId());
				}

				Integer[] vnicArray = null;
				List<LanLcpVnicMapping> lcpVnicMappingList = lcp
						.getLanLcpVnicMappings();
				if (Util.listNotNull(lcpVnicMappingList)) {
					for (int i = 0; i < lcpVnicMappingList.size(); i++) {
						LanLcpVnicMapping mapping = lcpVnicMappingList.get(i);
						if (i == 0) {
							vnicArray = new Integer[lcpVnicMappingList.size()];
						}
						vnicArray[i] = (Integer) Util
								.nullValidationAndReturnId(mapping.getLanVnic());
					}
				}
				myMap.put(VNICID, vnicArray);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanConnectivityPolicyConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanConnectivityPolicyConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageLanConnectivityPolicyConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanConnectivityPolicy> newlyAddedRecords = null;
		List<LanConnectivityPolicy> lcpConfigList = new ArrayList<LanConnectivityPolicy>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					for (int i = 0; i < addOrUpdateNodes.size(); i++) {
						JsonNode eachNode = addOrUpdateNodes.get(i);
						LanConnectivityPolicy lcp = (LanConnectivityPolicy) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										LanConnectivityPolicy.class);

						List<LanLcpVnicMapping> mappingList = new ArrayList<LanLcpVnicMapping>();
						if (eachNode.has(VNICID)
								&& !eachNode.get(VNICID).isNull()) {
							String[] split = eachNode.get(VNICID).toString()
									.replaceAll(PATTERN, "").split(",");
							for (int j = 0; j < split.length; j++) {
								LanLcpVnicMapping llvm = new LanLcpVnicMapping();
								llvm.setId(0);
								llvm.setLanConnectivityPolicy(new LanConnectivityPolicy(
										lcp.getId()));
								if (!(NULL_STR.equalsIgnoreCase(split[j]))) {
									llvm.setLanVnic(new LanVnic(Integer
											.parseInt(split[j])));
								}
								mappingList.add(llvm);
							}
						}
						lcp.setLanLcpVnicMappings(mappingList);
						lcpConfigList.add(lcp);
					}
					newlyAddedRecords = this.lanService
							.saveOrUpdateLanConnectivityPolicy(lcpConfigList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanConnectivityPolicy> deletedLcpList = (List<LanConnectivityPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanConnectivityPolicy.class);
					this.lanService.deleteLanConnectivityPolicy(deletedLcpList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: manageLanConnectivityPolicyConfig");
		return getLanConnectivityPolicyDetailsJsonList(newlyAddedRecords);
	}
}
