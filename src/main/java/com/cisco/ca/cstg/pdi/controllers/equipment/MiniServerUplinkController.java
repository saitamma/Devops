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

import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniServerUplink;
import com.cisco.ca.cstg.pdi.pojos.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class MiniServerUplinkController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MiniServerUplinkController.class);

	private final EquipmentService equipmentService;
	private static final String SERVER = "server";
	private static final String UPLINK = "uplink";

	@Autowired
	public MiniServerUplinkController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/equipmentMiniServerUplinkConfig.html")
	public String showEquipmentMiniServerUplinkConfig() {
		return "equipment/equipmentMiniServerUplinkConfig";
	}

	private List<EquipmentMiniServerUplink> createServerUplinkPorts() {
		List<EquipmentMiniServerUplink> eminiservUplinkPortList = new ArrayList<EquipmentMiniServerUplink>();
		int j = 1;
		for (int i = 1; i <= 8; i++) {
			EquipmentMiniServerUplink eminiservUplinkPort = new EquipmentMiniServerUplink();
			eminiservUplinkPort.setId(0);
			eminiservUplinkPort.setChassis(1);
			eminiservUplinkPort.setSlotId(1);
			eminiservUplinkPort.setIsConfigured(0);
			eminiservUplinkPort.setConfigureAs("");
			eminiservUplinkPort.setConfigureAsOldValue("");
			eminiservUplinkPortList.add(eminiservUplinkPort);
			if (i <= 4) {
				eminiservUplinkPort.setPortId(i);
				eminiservUplinkPort.setFiId("A");
				eminiservUplinkPort.setUserLabel("Chassis 1 Slot 1 Port " + i);
			} else {
				int portIdForFIB = j++;
				eminiservUplinkPort.setPortId(portIdForFIB);
				eminiservUplinkPort.setFiId("B");
				eminiservUplinkPort.setUserLabel("Chassis 1 Slot 1 Port "
						+ portIdForFIB);
			}
		}
		return eminiservUplinkPortList;
	}

	@RequestMapping(value = "/fetchEquipmentMiniServerUplinkConfig.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentMiniServerUplinkConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> returnJsonList = new ArrayList<Object>();
		List<EquipmentServer> equipmentServer = null;
		List<EquipmentUplink> equipmentUplink = null;

		List<EquipmentMiniServerUplink> createdServerUplinkPortsList = createServerUplinkPorts();

		if (projectId != null) {
			List<Object> returnJsonListForFiA = new ArrayList<Object>();
			List<Object> returnJsonListForFiB = new ArrayList<Object>();

			try {

				equipmentServer = equipmentService
						.fetchEquipmentServerConfiguration(projectId);
				equipmentUplink = equipmentService
						.fetchEquipmentUplinkConfiguration(projectId);

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}

			if (Util.listNotNull(equipmentServer)) {
				for (EquipmentServer eqServer : equipmentServer) {
					for (EquipmentMiniServerUplink equipmentMiniServerUplink : createdServerUplinkPortsList) {
						if (equipmentMiniServerUplink.getFiId().equals(
								eqServer.getFiId())
								&& equipmentMiniServerUplink.getPortId()
										.equals(eqServer.getPortId())) {
							equipmentMiniServerUplink.setConfigureAs(SERVER);
							equipmentMiniServerUplink.setIsConfigured(1);
							equipmentMiniServerUplink
									.setConfigureAsOldValue(SERVER);
							equipmentMiniServerUplink.setId(eqServer.getId());
						}
					}
				}
			}
			if (Util.listNotNull(equipmentUplink)) {
				for (EquipmentUplink eqUplink : equipmentUplink) {
					for (EquipmentMiniServerUplink equipmentMiniServerUplink : createdServerUplinkPortsList) {
						if (equipmentMiniServerUplink.getFiId().equals(
								eqUplink.getFiId())
								&& equipmentMiniServerUplink.getPortId()
										.equals(eqUplink.getPortId())) {
							equipmentMiniServerUplink.setConfigureAs(UPLINK);
							equipmentMiniServerUplink.setIsConfigured(1);
							equipmentMiniServerUplink
									.setConfigureAsOldValue(UPLINK);
							equipmentMiniServerUplink.setId(eqUplink.getId());
						}
					}
				}
			}

			if (Util.listNotNull(createdServerUplinkPortsList)) {
				for (EquipmentMiniServerUplink es : createdServerUplinkPortsList) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					equipmentMiniServerUplinkConfigJsonList(es, myMap);

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

	@RequestMapping(value = "/fetchEquipmentMiniUplinkPortsForFc.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentMiniUplinkPortsForFc(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<EquipmentMiniServerUplink> fetchEquipmentMiniServerUplinkForFc = null;
		if (projectId != null) {
			List<Object> jsonListA = new ArrayList<Object>();
			List<Object> jsonListB = new ArrayList<Object>();
			try {
				fetchEquipmentMiniServerUplinkForFc = this.equipmentService
						.fetchEquipmentMiniUplinPortsForFc(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}

			if (Util.listNotNull(fetchEquipmentMiniServerUplinkForFc)) {
				for (EquipmentMiniServerUplink emsup : fetchEquipmentMiniServerUplinkForFc) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					equipmentMiniServerUplinkConfigJsonList(emsup, myMap);
					if ("A".equalsIgnoreCase(emsup.getFiId())) {
						Util.convertMapToJson(myMap, jsonListA);
					} else {
						Util.convertMapToJson(myMap, jsonListB);
					}
				}
			}
			Map<String, Object> finalMap = new HashMap<String, Object>();
			finalMap.put("A", jsonListA);
			finalMap.put("B", jsonListB);
			Util.convertMapToJson(finalMap, jsonList);
		}

		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageEquipmentMiniServerUplinkConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageEquipmentMiniServerUplinkConfig(
			@RequestParam(JSON_OBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<Object> jsonList = new ArrayList<Object>();
		List<EquipmentMiniServerUplink> serverUplinkConfigList = null;

		try {

			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					String configureAsOld;
					serverUplinkConfigList = (List<EquipmentMiniServerUplink>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									EquipmentMiniServerUplink.class);

					if (UPLINK.equals(serverUplinkConfigList.get(0)
							.getConfigureAs())) {
						configureAsOld = serverUplinkConfigList.get(0)
								.getConfigureAsOldValue();
						if (SERVER.equals(configureAsOld)) {
							List<EquipmentServer> serverObjectList = getServerObjectList(serverUplinkConfigList
									.get(0));
							this.equipmentService
									.deleteEquipmentServerConfiguration(serverObjectList);
						}
						List<EquipmentUplink> uplinkObjectList = getUplinkObjectList(serverUplinkConfigList
								.get(0));
						uplinkObjectList.get(0).setId(0);
						List<EquipmentUplink> equipmentUplinkList = this.equipmentService
								.saveOrUpdateEquipmentUplinkConfiguration(
										uplinkObjectList, projectId);
						serverUplinkConfigList.get(0).setId(
								equipmentUplinkList.get(0).getId());
					} else if (SERVER.equals(serverUplinkConfigList.get(0)
							.getConfigureAs())) {
						configureAsOld = serverUplinkConfigList.get(0)
								.getConfigureAsOldValue();
						if (UPLINK.equals(configureAsOld)) {
							List<EquipmentUplink> uplinkObjectList = getUplinkObjectList(serverUplinkConfigList
									.get(0));
							this.equipmentService
									.deleteEquipmentUplinkConfiguration(uplinkObjectList);
						}
						List<EquipmentServer> serverObjectList = getServerObjectList(serverUplinkConfigList
								.get(0));
						serverObjectList.get(0).setId(0);
						List<EquipmentServer> equipmentServerList = this.equipmentService
								.saveOrUpdateEquipmentServerConfiguration(
										serverObjectList, projectId);
						serverUplinkConfigList.get(0).setId(
								equipmentServerList.get(0).getId());
					} else {
						configureAsOld = serverUplinkConfigList.get(0)
								.getConfigureAsOldValue();
						if (UPLINK.equals(configureAsOld)) {
							List<EquipmentUplink> uplinkObjectList = getUplinkObjectList(serverUplinkConfigList
									.get(0));
							this.equipmentService
									.deleteEquipmentUplinkConfiguration(uplinkObjectList);
						} else {
							List<EquipmentServer> serverObjectList = getServerObjectList(serverUplinkConfigList
									.get(0));
							this.equipmentService
									.deleteEquipmentServerConfiguration(serverObjectList);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		if (Util.listNotNull(serverUplinkConfigList)) {
			for (EquipmentMiniServerUplink es : serverUplinkConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				equipmentMiniServerUplinkConfigJsonList(es, myMap);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	private List<EquipmentUplink> getUplinkObjectList(
			EquipmentMiniServerUplink equipmentServerUplinkConfig) {
		List<EquipmentUplink> equipmentUplinkList = new ArrayList<EquipmentUplink>();
		EquipmentUplink equipmentUplink = new EquipmentUplink();
		equipmentUplink.setId(equipmentServerUplinkConfig.getId());
		equipmentUplink.setFiId(equipmentServerUplinkConfig.getFiId());
		equipmentUplink.setPortId(equipmentServerUplinkConfig.getPortId());
		equipmentUplink.setSlotId(equipmentServerUplinkConfig.getSlotId());
		equipmentUplinkList.add(equipmentUplink);
		return equipmentUplinkList;
	}

	private List<EquipmentServer> getServerObjectList(
			EquipmentMiniServerUplink equipmentServerUplinkConfig) {
		List<EquipmentServer> equipmentServerList = new ArrayList<EquipmentServer>();
		EquipmentServer equipmentServer = new EquipmentServer();
		equipmentServer.setId(equipmentServerUplinkConfig.getId());
		equipmentServer.setFiId(equipmentServerUplinkConfig.getFiId());
		equipmentServer.setPortId(equipmentServerUplinkConfig.getPortId());
		equipmentServer.setSlotId(equipmentServerUplinkConfig.getSlotId());
		equipmentServer.setChassis(equipmentServerUplinkConfig.getChassis());
		equipmentServer
				.setUserLabel(equipmentServerUplinkConfig.getUserLabel());
		equipmentServerList.add(equipmentServer);
		return equipmentServerList;
	}

	private void equipmentMiniServerUplinkConfigJsonList(
			EquipmentMiniServerUplink es, Map<String, Object> myMap) {
		myMap.put(ID, es.getId());
		myMap.put(FIID, es.getFiId());
		myMap.put(PORTID, es.getPortId());
		myMap.put(SLOTID, es.getSlotId());
		myMap.put(CHASSIS, es.getChassis());
		myMap.put(IS_CONFIGURED, es.getIsConfigured());
		myMap.put(CONFIGURE_AS, es.getConfigureAs());
		myMap.put(CONFIGURE_AS_OLD_VALUE, es.getConfigureAsOldValue());
		myMap.put(USERLABEL, es.getUserLabel());
	}

}
