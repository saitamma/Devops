package com.cisco.ca.cstg.pdi.controllers.equipment;

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

import com.cisco.ca.cstg.pdi.pojos.EquipmentServer;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ServerPortsController implements Constants {

	private final EquipmentService equipmentService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerPortsController.class);

	@Autowired
	public ServerPortsController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/equipmentServerConfig.html")
	public String showEquipmentServerConfig() {
		return "equipment/equipmentServerConfig";
	}

	@RequestMapping(value = "/fetchEquipmentServerConfig.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentServerConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();

		if (projectId != null) {
			List<Object> returnJsonListForFiA = new ArrayList<Object>();
			List<Object> returnJsonListForFiB = new ArrayList<Object>();
			List<EquipmentServer> euipmentServerList = null;

			try {
				euipmentServerList = equipmentService
						.fetchEquipmentServerConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}

			if (Util.listNotNull(euipmentServerList)) {
				for (EquipmentServer es : euipmentServerList) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					equipmentServerConfigJsonList(es, myMap);

					if ("A".equalsIgnoreCase(es.getFiId())) {
						Util.convertMapToJson(myMap, returnJsonListForFiA);
					} else {
						Util.convertMapToJson(myMap, returnJsonListForFiB);
					}
				}
			}
			Map<String, Object> fiMap = new HashMap<String, Object>();
			fiMap.put("A", returnJsonListForFiA);
			fiMap.put("B", returnJsonListForFiB);
			Util.convertMapToJson(fiMap, returnJsonList);
		}
		return returnJsonList;
	}

	private void equipmentServerConfigJsonList(EquipmentServer es,
			Map<String, Object> myMap) {
		myMap.put(ID, es.getId());
		myMap.put(FIID, es.getFiId());
		myMap.put(PORTID, es.getPortId());
		myMap.put(SLOTID, es.getSlotId());
		myMap.put(CHASSIS, es.getChassis());
		myMap.put(USERLABEL, es.getUserLabel());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageEquipmentServerConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageEquipmentServerConfig(
			@RequestParam(JSON_OBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<Object> jsonList = new ArrayList<Object>();
		List<EquipmentServer> newlySavedRecords = null;
		try {

			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<EquipmentServer> serverConfigList = (List<EquipmentServer>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									EquipmentServer.class);
					newlySavedRecords = this.equipmentService
							.saveOrUpdateEquipmentServerConfiguration(
									serverConfigList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<EquipmentServer> deleteserverConfigList = (List<EquipmentServer>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									EquipmentServer.class);
					this.equipmentService
							.deleteEquipmentServerConfiguration(deleteserverConfigList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		if (Util.listNotNull(newlySavedRecords)) {
			for (EquipmentServer es : newlySavedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				equipmentServerConfigJsonList(es, myMap);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
}