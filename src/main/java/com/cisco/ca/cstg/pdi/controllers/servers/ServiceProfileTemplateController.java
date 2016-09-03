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

import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.services.ServiceTemplateService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ServiceProfileTemplateController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProfileTemplateController.class);

	private final ServiceTemplateService serviceTemplateService;

	@Autowired
	public ServiceProfileTemplateController(
			ServiceTemplateService serviceTemplateService) {
		this.serviceTemplateService = serviceTemplateService;
	}


	@RequestMapping(value = "/serversServiceTempConfig.html")
	public String showserversServiceTempConfig() {
		return "servers/serversServiceTempConfig";
	}

	@RequestMapping(value = "/getServiceTemplateConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getServiceTemplateConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersServiceProfileTemplate> serviceTemplateConfigList = null;

		if (projectId != null) {
			try {
				serviceTemplateConfigList = serviceTemplateService
						.fetchServersServiceProfileTemplateConfiguration(projectId);
			} catch (Exception e) {
				LOGGER.error(
						"Exception in getServiceTemplateConfigDetails method.",
						e);
				throw e;
			}
		}
		return getServiceTemplateJsonList(serviceTemplateConfigList);
	}

	private List<Object> getServiceTemplateJsonList(
			List<ServersServiceProfileTemplate> serviceTemplateConfigList) {
		List<Object> jsonList = new ArrayList<Object>();
		Integer[] vnicArray;
		Integer[] vhbaArray;

		if (Util.listNotNull(serviceTemplateConfigList)) {
			for (ServersServiceProfileTemplate spt : serviceTemplateConfigList) {
				vhbaArray = null;
				vnicArray = null;
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, spt.getId());
				myMap.put(NAME, spt.getName());
				myMap.put(DESCRIPTION, spt.getDescription());
				myMap.put(TYPE, spt.getType());
				myMap.put(SERVERSBOOTPOLICY, Util.nullValidationAndReturnId(spt
						.getServersBootPolicy()));
				myMap.put(SERVERSSERVERPOOL, Util.nullValidationAndReturnId(spt
						.getServersServerPool()));
				myMap.put(SERVERSUUIDPOOL, Util.nullValidationAndReturnId(spt
						.getServersUuidPool()));
				myMap.put(SERVERSLOCALDISC, Util.nullValidationAndReturnId(spt
						.getServersLocalDisc()));
				myMap.put(HOSTFIRMWAREPACKAGE,
						Util.nullValidationAndReturnId(spt
								.getServersHostFirmware()));
				myMap.put(BIOS_POLICY, Util.nullValidationAndReturnId(spt
						.getServersBiosPolicy()));
				myMap.put(MAINTENANCEPOLICY, Util.nullValidationAndReturnId(spt
						.getServersMaintenancePolicy()));

				if (spt.getSanConnectivityPolicy() != null) {
					myMap.put(SANCONNECTIVITYPOLICY, spt
							.getSanConnectivityPolicy().getId());
					myMap.put(SANWWNN, null);
				} else {
					myMap.put(SANCONNECTIVITYPOLICY, null);
					myMap.put(SANWWNN,
							Util.nullValidationAndReturnId(spt.getSanWwnn()));
					List<ServersSPTVhbaMapping> ssptVhbaMappingList = spt
							.getServersSptVhbaMappings();

					if (Util.listNotNull(ssptVhbaMappingList)) {
						for (int i = 0; i < ssptVhbaMappingList.size(); i++) {
							ServersSPTVhbaMapping mapping = ssptVhbaMappingList
									.get(i);
							if (i == 0) {
								vhbaArray = new Integer[ssptVhbaMappingList
										.size()];
							}
							vhbaArray[i] = (Integer) Util
									.nullValidationAndReturnId(mapping
											.getSanVhba());
						}
					}
				}
				myMap.put(SANVHBA, vhbaArray);

				if (spt.getLanConnectivityPolicy() != null) {
					myMap.put(LANCONNECTIVITYPOLICY, spt
							.getLanConnectivityPolicy().getId());
				} else {
					myMap.put(LANCONNECTIVITYPOLICY, null);
					List<ServersSPTVnicMapping> ssptVnicMappingList = spt
							.getServersSptVnicMappings();

					if (Util.listNotNull(ssptVnicMappingList)) {
						for (int i = 0; i < ssptVnicMappingList.size(); i++) {
							ServersSPTVnicMapping mapping = ssptVnicMappingList
									.get(i);
							if (i == 0) {
								vnicArray = new Integer[ssptVnicMappingList
										.size()];
							}
							vnicArray[i] = (Integer) Util
									.nullValidationAndReturnId(mapping
											.getLanVnic());
						}
					}
				}
				myMap.put(LANVNIC, vnicArray);
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(spt.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServiceTemplateConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServiceTemplateConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersServiceProfileTemplate> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						Constants.ADDORUPDATE);
				JsonNode deletedNodes = Util.getJsonNodeByName(jsonObj,
						Constants.DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersServiceProfileTemplate> serversServiceTemplateList = new ArrayList<ServersServiceProfileTemplate>();

					for (int i = 0; i < addOrUpdateNodes.size(); i++) {
						JsonNode eachNode = addOrUpdateNodes.get(i);
						ServersServiceProfileTemplate sspt = (ServersServiceProfileTemplate) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										ServersServiceProfileTemplate.class);

						List<ServersSPTVhbaMapping> vhbaMappingList = new ArrayList<>();

						if (eachNode.get(SANVHBA) != null
								&& !eachNode.get(SANVHBA).isNull()) {
							String[] split = eachNode.get(SANVHBA).toString()
									.replaceAll("\\[|\\]", "").split(",");
							for (int j = 0; j < split.length; j++) {
								ServersSPTVhbaMapping ssptvm = new ServersSPTVhbaMapping();
								ssptvm.setId(0);
								Integer vhbaId = ("null"
										.equalsIgnoreCase(split[j])) ? null
										: Integer.parseInt(split[j]);
								ssptvm.setSanVhba(new SanVhba(vhbaId));
								ssptvm.setServersServiceProfileTemplate(new ServersServiceProfileTemplate(
										sspt.getId()));
								vhbaMappingList.add(ssptvm);
							}

						}
						sspt.setServersSptVhbaMappings(vhbaMappingList);

						List<ServersSPTVnicMapping> mappingList = new ArrayList<>();

						if (eachNode.get(LANVNIC) != null
								&& !eachNode.get(LANVNIC).isNull()) {
							String[] split = eachNode.get(LANVNIC).toString()
									.replaceAll("\\[|\\]", "").split(",");

							for (int j = 0; j < split.length; j++) {
								ServersSPTVnicMapping ssptvm = new ServersSPTVnicMapping();
								ssptvm.setId(0);
								Integer vnicId = ("null"
										.equalsIgnoreCase(split[j])) ? null
										: Integer.parseInt(split[j]);
								ssptvm.setLanVnic(new LanVnic(vnicId));
								ssptvm.setServersServiceProfileTemplate(new ServersServiceProfileTemplate(
										sspt.getId()));
								mappingList.add(ssptvm);
							}
						}
						sspt.setServersSptVnicMappings(mappingList);
						serversServiceTemplateList.add(sspt);

					}
					newlyAddedRecords = this.serviceTemplateService
							.saveOrUpdateServersServiceProfileTemplateConfiguration(
									serversServiceTemplateList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersServiceProfileTemplate> deletedServiceTemplateList = (List<ServersServiceProfileTemplate>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersServiceProfileTemplate.class);
					this.serviceTemplateService
							.deleteServersServiceProfileTemplate(deletedServiceTemplateList);
				}
			} catch (Exception e) {
				LOGGER.error(
						"Exception in manageServiceTemplateConfig method.", e);
				throw e;
			}
		}

		return getServiceTemplateJsonList(newlyAddedRecords);
	}
}
