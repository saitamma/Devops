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

import com.cisco.ca.cstg.pdi.pojos.LanPortchannel;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class PortChannelController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PortChannelController.class);
	private final LANService lanService;
	

	@Autowired
	public PortChannelController(LANService lanService) {
		this.lanService = lanService;
	}
	
	@RequestMapping(value = "/lanPortChannelConfig.html")
	public String showPortChannel() {
		return "lan/lanPortChannelConfig";
	}
	
	@RequestMapping(value = "/getPortChannelConfigDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getPortChannelConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getPortChannelConfigDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<Object> returnJsonListForFiA = new ArrayList<Object>();
		List<Object> returnJsonListForFiB = new ArrayList<Object>();

		if (projectId != null) {
			try {
				List<LanPortchannel> portChannelConfigList = lanService
						.fetchLanPortChannelConfiguration(projectId);

				if (Util.listNotNull(portChannelConfigList)) {
					for (LanPortchannel lpc : portChannelConfigList) {
						Map<String, Object> myMap = getPortChannelConfigJsonList(lpc);

						if ("A".equalsIgnoreCase(lpc.getFiId())) {
							Util.convertMapToJson(myMap, returnJsonListForFiA);
						} else {
							Util.convertMapToJson(myMap, returnJsonListForFiB);
						}
					}
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		Map<String, Object> fiMap = new HashMap<String, Object>();
		fiMap.put("A", returnJsonListForFiA);
		fiMap.put("B", returnJsonListForFiB);
		Util.convertMapToJson(fiMap, jsonList);
		LOGGER.info("End: getPortChannelConfigDetails");
		return jsonList;
	}

	private Map<String, Object> getPortChannelConfigJsonList(LanPortchannel lpc) {
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put(ID, lpc.getId());
		myMap.put(FIID, lpc.getFiId());
		myMap.put(FINAME, lpc.getFiName());
		myMap.put(DESCRIPTION, lpc.getDescription());
		if (lpc.getEquipmentUplink() != null) {
			myMap.put(PORTID, lpc.getEquipmentUplink().getPortId());
		} else {
			myMap.put(PORTID, null);
		}
		if (lpc.getEquipmentUplink() != null) {
			myMap.put(SLOTID, lpc.getEquipmentUplink().getSlotId());
		} else {
			myMap.put(SLOTID, null);
		}
		if (lpc.getEquipmentUplink() != null) {
			myMap.put(EQUIPMENTUPLINK, lpc.getEquipmentUplink().getId());
		} else {
			myMap.put(EQUIPMENTUPLINK, null);
		}

		return myMap;
	}

	@RequestMapping(value = "/managePortChannelConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> managePortChannelConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: managePortChannelConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<LanPortchannel> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<LanPortchannel> portChannelConfigList = (List<LanPortchannel>) (List<?>) Util
							.jsonToListWithIgnoringField(
									addOrUpdateNodes.toString(),
									LanPortchannel.class);
					newlyAddedRecords = this.lanService
							.saveOrUpdateLanPortChannelConfiguration(
									portChannelConfigList, projectId);

					if (Util.listNotNull(newlyAddedRecords)) {
						for (LanPortchannel lpc : newlyAddedRecords) {
							Map<String, Object> myMap = getPortChannelConfigJsonList(lpc);

							Util.convertMapToJson(myMap, jsonList);
						}
					}
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanPortchannel> deletedPortChannelList = (List<LanPortchannel>) (List<?>) Util
							.jsonToListWithIgnoringField(
									deletedNodes.toString(),
									LanPortchannel.class);
					this.lanService
							.deleteLanPortChannel(deletedPortChannelList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: managePortChannelConfig");
		return jsonList;
	}

}
