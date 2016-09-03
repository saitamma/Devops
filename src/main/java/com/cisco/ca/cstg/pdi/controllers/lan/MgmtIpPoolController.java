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

import com.cisco.ca.cstg.pdi.pojos.LanMgmtIppool;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.GenerateIPAddressUtil;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class MgmtIpPoolController implements Constants {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MgmtIpPoolController.class);
	private final LANService lanService;
	

	@Autowired
	public MgmtIpPoolController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanMgmtIpPoolConfig.html")
	public String showLanMgmtIpPool() {
		return "lan/lanMgmtIpPoolConfig";
	}

	@RequestMapping(value = "/getLanMgmtIPpoolConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanMgmtIPpoolConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<LanMgmtIppool> mgmtIPpoolConfigList = null;

		try {
			if (projectId != null) {
				mgmtIPpoolConfigList = lanService
						.fetchLanMgmtIPpoolConfiguration(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getLanMgmtIPpoolConfigDetailsJsonList(mgmtIPpoolConfigList);
	}

	private List<Object> getLanMgmtIPpoolConfigDetailsJsonList(
			List<LanMgmtIppool> mgmtIPpoolConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(mgmtIPpoolConfigList)) {
			for (LanMgmtIppool ipPool : mgmtIPpoolConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, ipPool.getId());
				myMap.put(NAME, ipPool.getName());
				myMap.put(DESCRIPTION, ipPool.getDescription());
				myMap.put(FROMADDRESS, ipPool.getFromAddress());
				myMap.put(TOADDRESS, ipPool.getToAddress());
				myMap.put(DEFAULTGATEWAY, ipPool.getDefaultGateway());
				myMap.put(SUBNETMASK, ipPool.getSubnetMask());
				myMap.put(DNS, ipPool.getDns());
				if (ipPool.getOrganizations() != null) {
					myMap.put(ORGANIZATIONS, ipPool.getOrganizations().getId());
				}

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanMgmtIPpoolConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanMgmtIPpoolConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanMgmtIppool> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<LanMgmtIppool> lanMgmtIPpoolList = (List<LanMgmtIppool>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									LanMgmtIppool.class);

					if (Util.listNotNull(lanMgmtIPpoolList)) {
						LanMgmtIppool ipPool = lanMgmtIPpoolList
								.get(0);
						if (ipPool.getNoOfAddresses() != null) {
							lanMgmtIPpoolList.get(0)
									.setToAddress(GenerateIPAddressUtil
											.addToAddressValue(
													ipPool.getFromAddress(),
													ipPool.getNoOfAddresses()));
						}
					}
					newlyAddedRecords = this.lanService
							.saveOrUpdateLanMgmtIPpoolConfiguration(
									lanMgmtIPpoolList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanMgmtIppool> deletedMgmtIPpoolList = (List<LanMgmtIppool>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanMgmtIppool.class);
					this.lanService.deleteLanMgmtIPpool(deletedMgmtIPpoolList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getLanMgmtIPpoolConfigDetailsJsonList(newlyAddedRecords);
	}
}
