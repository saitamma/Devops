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

import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class MiniScalabilityPortsController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MiniScalabilityPortsController.class);

	private final EquipmentService equipmentService;

	@Autowired
	public MiniScalabilityPortsController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/equipmentMiniScalabilityConfig.html")
	public String showEquipmentMiniScalabilityConfig() {
		return "equipment/equipmentMiniScalabilityConfig";
	}

	@RequestMapping(value = "/fetchEquipmentMiniScalabilityConfig.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentMiniScalabilityConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();
		List<EquipmentMiniScalability> equipmentScalabilityList = null;

		if (projectId != null) {
			List<Object> returnJsonListForFiA = new ArrayList<Object>();
			List<Object> returnJsonListForFiB = new ArrayList<Object>();

			try {
				equipmentScalabilityList = equipmentService
						.fetchEquipmentMiniScalabilityConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}

			if (Util.listNotNull(equipmentScalabilityList)) {
				for (EquipmentMiniScalability es : equipmentScalabilityList) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					equipmentMiniScalabilityConfigJsonList(es, myMap);

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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageEquipmentMiniScalabilityConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageEquipmentMiniScalabilityConfig(
			@RequestParam(JSON_OBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<Object> jsonList = new ArrayList<Object>();
		List<EquipmentMiniScalability> newlySavedRecords = null;

		try {

			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<EquipmentMiniScalability> scalabilityConfigList = (List<EquipmentMiniScalability>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									EquipmentMiniScalability.class);
					newlySavedRecords = this.equipmentService
							.saveOrUpdateEquipmentMiniScalabilityConfiguration(
									scalabilityConfigList, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		if (Util.listNotNull(newlySavedRecords)) {
			for (EquipmentMiniScalability es : newlySavedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				equipmentMiniScalabilityConfigJsonList(es, myMap);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	private void equipmentMiniScalabilityConfigJsonList(
			EquipmentMiniScalability es, Map<String, Object> myMap) {
		myMap.put(ID, es.getId());
		myMap.put(FIID, es.getFiId());
		myMap.put(PORTID, es.getPortId());
		myMap.put(SLOTID, es.getSlotId());
		myMap.put(CHASSIS, es.getChassis());
		myMap.put(IS_CONFIGURED, es.getIsConfigured());
		myMap.put(CONFIGURE_AS, es.getConfigureAs());
	}
}
