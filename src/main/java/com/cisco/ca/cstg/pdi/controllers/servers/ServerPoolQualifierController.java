package com.cisco.ca.cstg.pdi.controllers.servers;

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

import com.cisco.ca.cstg.pdi.pojos.ServersSPQSlotMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolQualifier;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ServerPoolQualifierController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerPoolQualifierController.class);
	private final ServersService serversService;

	@Autowired
	public ServerPoolQualifierController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversServerPoolAndQualifierConfig.html")
	public String showserversServerPoolAndQualifierConfig() {
		return "servers/serversServerPoolAndQualifierConfig";
	}

	@RequestMapping(value = "/getServerPoolConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getServerPoolConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersServerPool> serverPoolConfigList = null;

		if (projectId != null) {
			try {
				serverPoolConfigList = serversService
						.fetchServersServerPoolConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServerPoolConfigJsonList(serverPoolConfigList);
	}

	private List<Object> getServerPoolConfigJsonList(
			List<ServersServerPool> serverPoolConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(serverPoolConfigList)) {
			for (ServersServerPool sp : serverPoolConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, sp.getId());
				myMap.put(NAME, sp.getName());
				myMap.put(DESCRIPTION, sp.getDescription());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(sp.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServerPoolConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServerPoolConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersServerPool> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersServerPool> serversServerPoolList = (List<ServersServerPool>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersServerPool.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersServerPoolConfiguration(
									serversServerPoolList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersServerPool> deletedServerPoolList = (List<ServersServerPool>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersServerPool.class);
					this.serversService
							.deleteServersServerPool(deletedServerPoolList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getServerPoolConfigJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getServerPoolQualifierConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getServerPoolQualifierConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersServerPoolQualifier> serverPoolQualifierConfigList = null;

		if (projectId != null) {
			try {
				serverPoolQualifierConfigList = serversService
						.fetchServersServerPoolQualifierConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServerPoolQualifierConfigJsonList(serverPoolQualifierConfigList);
	}

	private List<Object> getServerPoolQualifierConfigJsonList(
			List<ServersServerPoolQualifier> serverPoolQualifierConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(serverPoolQualifierConfigList)) {
			for (ServersServerPoolQualifier spq : serverPoolQualifierConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, spq.getId());
				myMap.put(NAME, spq.getName());
				myMap.put(DESCRIPTION, spq.getDescription());
				myMap.put(CHASSISMINID, spq.getChassisMinId());
				myMap.put(CHASSISMAXID, spq.getChassisMaxId());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(spq.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServerPoolQualifierConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServerPoolQualifierConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersServerPoolQualifier> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersServerPoolQualifier> serversServerPoolQualifierList = (List<ServersServerPoolQualifier>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersServerPoolQualifier.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersServerPoolQualifierConfiguration(
									serversServerPoolQualifierList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersServerPoolQualifier> deletedServerPoolQualifierList = (List<ServersServerPoolQualifier>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersServerPoolQualifier.class);
					this.serversService
							.deleteServersServerPoolQualifier(deletedServerPoolQualifierList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getServerPoolQualifierConfigJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getSPQSlotMappingConfigDetails.html", method = RequestMethod.POST)
	@ResponseBody
	public List<Object> getSPQSlotMappingConfigDetails(
			@RequestParam(JSONOBJ) Integer spqId) throws IOException {
		List<ServersSPQSlotMapping> spqSlotMappingList = null;

		if (spqId != null) {
			try {
				spqSlotMappingList = serversService
						.fetchServersSPQSlotMappings(spqId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServerPoolQualifierSlotConfigJsonList(spqSlotMappingList);
	}

	private List<Object> getServerPoolQualifierSlotConfigJsonList(
			List<ServersSPQSlotMapping> spqSlotMappingList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(spqSlotMappingList)) {
			for (ServersSPQSlotMapping spqSlotMapping : spqSlotMappingList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, spqSlotMapping.getId());
				myMap.put(MINID, spqSlotMapping.getMinId());
				myMap.put(MAXID, spqSlotMapping.getMaxId());
				myMap.put(SPQID, Util.nullValidationAndReturnId(spqSlotMapping
						.getServersServerPoolQualifier()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServerPoolQualifierSlotConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServerPoolQualifierSlotConfig(
			@RequestParam(JSONOBJ) String jsonObj) throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);

		List<ServersSPQSlotMapping> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj)) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersSPQSlotMapping> serversSPQSlotMappingList = (List<ServersSPQSlotMapping>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersSPQSlotMapping.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersSPQSlotMappings(serversSPQSlotMappingList);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersSPQSlotMapping> deletedSPQSlotMappingList = (List<ServersSPQSlotMapping>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersSPQSlotMapping.class);
					this.serversService
							.deleteServersSPQSlotMappings(deletedSPQSlotMappingList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return getServerPoolQualifierSlotConfigJsonList(newlyAddedRecords);
	}

}
