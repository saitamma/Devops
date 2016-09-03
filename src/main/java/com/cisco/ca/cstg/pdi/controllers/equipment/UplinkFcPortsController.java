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

import com.cisco.ca.cstg.pdi.pojos.EquipmentFcport;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class UplinkFcPortsController implements Constants {

	private final EquipmentService equipmentService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UplinkFcPortsController.class);

	@Autowired
	public UplinkFcPortsController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/equipmentUplinkConfig.html")
	public String showEquipmentUplinkConfig() {
		return "equipment/equipmentUplinkConfig";
	}

	@RequestMapping(value = "/fetchEquipmentUplinkForPortChannel.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getEquipmentUplinkPortForPortChannel(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId) {

		List<Object> returnJsonList = new ArrayList<Object>();

		if (projectId != null) {
			List<Object> returnJsonListForFiA = new ArrayList<Object>();
			List<Object> returnJsonListForFiB = new ArrayList<Object>();

			try {
				List<EquipmentUplink> euipmentUplinkList = equipmentService
						.fetchEquipmentUplinkConfiguration(projectId);

				if (Util.listNotNull(euipmentUplinkList)) {

					for (EquipmentUplink eu : euipmentUplinkList) {
						Map<String, Object> myMap = new HashMap<String, Object>();
						getEquipmentUplinkPortJsonList(eu, myMap);

						if ("A".equalsIgnoreCase(eu.getFiId())) {
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
			Map<String, Object> fiMap = new HashMap<String, Object>();
			fiMap.put("A", returnJsonListForFiA);
			fiMap.put("B", returnJsonListForFiB);
			Util.convertMapToJson(fiMap, returnJsonList);
		}
		return returnJsonList;
	}

	private void getEquipmentUplinkPortJsonList(EquipmentUplink eu,
			Map<String, Object> myMap) {
		myMap.put(ID, eu.getId());
		myMap.put(FIID, eu.getFiId());
		myMap.put(PORTID, eu.getPortId());
		myMap.put(SLOTID, eu.getSlotId());
		myMap.put(USERLABEL, eu.getUserLabel());
	}

	@RequestMapping(value = "/fetchEquipmentUplinkConfig.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentUplinkConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();

		try {
			if (projectId != null) {
				List<EquipmentUplink> equipmentUplinkList = equipmentService
						.fetchEquipmentUplinkConfiguration(projectId);

				if (Util.listNotNull(equipmentUplinkList)) {

					for (EquipmentUplink eu : equipmentUplinkList) {
						Map<String, Object> myMap = new HashMap<String, Object>();
						getEquipmentUplinkPortJsonList(eu, myMap);
						Util.convertMapToJson(myMap, returnJsonList);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return returnJsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageEquipmentUplinkConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageEquipmentUplinkConfig(
			@RequestParam(JSON_OBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageEquipmentUplinkConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		// Retrieving name values pairs from json string
		List<Object> jsonList = new ArrayList<Object>();
		List<EquipmentUplink> newlySavedRecords = null;

		try {

			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<EquipmentUplink> uplinkConfigList = (List<EquipmentUplink>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									EquipmentUplink.class);
					newlySavedRecords = this.equipmentService
							.saveOrUpdateEquipmentUplinkConfiguration(
									uplinkConfigList, projectId);

					if (Util.listNotNull(newlySavedRecords)) {

						for (EquipmentUplink eu : newlySavedRecords) {
							Map<String, Object> myMap = new HashMap<String, Object>();
							getEquipmentUplinkPortJsonList(eu, myMap);
							Util.convertMapToJson(myMap, jsonList);
						}
					}
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<EquipmentUplink> deletesUplinkConfigList = (List<EquipmentUplink>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									EquipmentUplink.class);
					this.equipmentService
							.deleteEquipmentUplinkConfiguration(deletesUplinkConfigList);
				}
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: manageEquipmentUplinkConfig");
		return jsonList;
	}

	@RequestMapping(value = "/fetchEquipmentFCPortConfig.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentFCPortConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<EquipmentFcport> equipmentFCPortsList = null;

		try {
			if (projectId != null) {
				equipmentFCPortsList = equipmentService
						.fetchEquipmentFCPortConfiguration(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return fetchEquipmentFCPortConfigJsonList(equipmentFCPortsList);
	}

	private List<Object> fetchEquipmentFCPortConfigJsonList(
			List<EquipmentFcport> equipmentFCPortsList) {
		List<Object> returnJsonList = new ArrayList<Object>();

		if (Util.listNotNull(equipmentFCPortsList)) {
			for (EquipmentFcport fcPort : equipmentFCPortsList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, fcPort.getId());
				myMap.put(FIID, fcPort.getFiId());
				myMap.put(PORTID, fcPort.getPortId());
				myMap.put(SLOTID, fcPort.getSlotId());
				myMap.put(USERLABEL, fcPort.getUserLabel());
				Util.convertMapToJson(myMap, returnJsonList);
			}
		}
		return returnJsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageEquipmentFCPortConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageEquipmentFCPortConfig(
			@RequestParam(JSON_OBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<EquipmentFcport> newlySavedRecords = null;

		try {

			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<EquipmentFcport> equipmentFCPortList = (List<EquipmentFcport>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									EquipmentFcport.class);
					newlySavedRecords = this.equipmentService
							.saveOrUpdateEquipmentFCPortConfiguration(
									equipmentFCPortList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<EquipmentFcport> deletesFcportConfigList = (List<EquipmentFcport>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									EquipmentFcport.class);
					this.equipmentService
							.deleteEquipmentFCPortConfiguration(deletesFcportConfigList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return fetchEquipmentFCPortConfigJsonList(newlySavedRecords);
	}

}