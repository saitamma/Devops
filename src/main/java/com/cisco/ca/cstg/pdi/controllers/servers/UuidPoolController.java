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

import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.GenerateUUIDPoolUtil;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class UuidPoolController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UuidPoolController.class);
	private final ServersService serversService;

	@Autowired
	public UuidPoolController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversUUIDPoolConfig.html")
	public String showServersUUIDPoolConfig() {
		return "servers/serversUUIDPoolConfig";
	}

	@RequestMapping(value = "/getUUIDPoolConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getUUIDPoolConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersUuidPool> uuidPoolConfigList = null;

		if (projectId != null) {
			try {
				uuidPoolConfigList = serversService
						.fetchServersUuidPoolConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getUUIDPoolConfigJsonList(uuidPoolConfigList);
	}

	private List<Object> getUUIDPoolConfigJsonList(
			List<ServersUuidPool> uuidPoolConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(uuidPoolConfigList)) {
			for (ServersUuidPool uuid : uuidPoolConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, uuid.getId());
				myMap.put(NAME, uuid.getName());
				myMap.put(DESCRIPTION, uuid.getDescription());
				myMap.put(ASSIGNMENTORDER, uuid.getAssignmentOrder());
				myMap.put(PREFIXTYPE, uuid.getPrefixType());
				myMap.put(PREFIX, uuid.getPrefix());
				myMap.put(FROMADDRESS, uuid.getFromAddress());
				myMap.put(TOADDRESS, uuid.getToAddress());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(uuid.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageUUIDPoolConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageUUIDPoolConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersUuidPool> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersUuidPool> serversUuidPoolList = (List<ServersUuidPool>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersUuidPool.class);
					if (serversUuidPoolList != null
							&& serversUuidPoolList.size() == 1
							&& serversUuidPoolList.get(0)
									.getSize() != null) {
						ServersUuidPool serverUuidPool = serversUuidPoolList
								.get(0);

						serversUuidPoolList.get(0)
								.setToAddress(GenerateUUIDPoolUtil.addToPoolValue(
										serverUuidPool.getFromAddress(),
										serverUuidPool.getSize()));
					}
					newlyAddedRecords = this.serversService
							.saveOrUpdateServersUuidPoolConfiguration(
									serversUuidPoolList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersUuidPool> deletedUUIDpoolList = (List<ServersUuidPool>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersUuidPool.class);
					this.serversService
							.deleteServersUuidPool(deletedUUIDpoolList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getUUIDPoolConfigJsonList(newlyAddedRecords);
	}
}
