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

import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplateVlanMapping;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class VnicTemplateController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VnicTemplateController.class);
	private final LANService lanService;

	@Autowired
	public VnicTemplateController(LANService lanService) {
		this.lanService = lanService;
	}

	@RequestMapping(value = "/lanVnicTempConfig.html")
	public String showVnicTemp() {
		return "lan/lanVnicTempConfig";
	}

	@RequestMapping(value = "/getLanVnicTemplateConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getLanVnicTemplateConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getLanVnicTemplateConfigDetails");
		List<LanVnicTemplate> vnicTemplateConfigList = null;

		try {
			if (projectId != null) {
				vnicTemplateConfigList = lanService
						.fetchLanVnicConfiguration(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: getLanVnicTemplateConfigDetails");
		return getLanVnicTemplateConfigDetailsJsonList(vnicTemplateConfigList);
	}

	private List<Object> getLanVnicTemplateConfigDetailsJsonList(
			List<LanVnicTemplate> vnicTemplateConfigList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(vnicTemplateConfigList)) {
			for (LanVnicTemplate lvnic : vnicTemplateConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, lvnic.getId());
				myMap.put(VNICTNAME, lvnic.getVnictName());
				myMap.put(DESCRIPTION, lvnic.getDescription());
				myMap.put(LANMACPOOL,
						Util.nullValidationAndReturnId(lvnic.getLanMacpool()));
				myMap.put(LANNETWORKCONTROLPOLICY, Util
						.nullValidationAndReturnId(lvnic
								.getLanNetworkControlPolicy()));
				myMap.put(LANQOSPOLICY,
						Util.nullValidationAndReturnId(lvnic.getLanQosPolicy()));
				myMap.put(SWITCHID, lvnic.getSwitchId());
				myMap.put(TARGET, lvnic.getTarget());
				myMap.put(TEMPLATETYPE, lvnic.getTemplateType());
				myMap.put(OSTYPE, lvnic.getOsType());
				if (lvnic.getOrganizations() != null) {
					myMap.put(ORGANIZATIONS, lvnic.getOrganizations().getId());
				}

				Integer[] vlanArray = null;
				List<LanVnicTemplateVlanMapping> vnicVlanMappingList = lvnic
						.getLanVnictVlanMappings();

				if (Util.listNotNull(vnicVlanMappingList)) {
					for (int i = 0; i < vnicVlanMappingList.size(); i++) {
						LanVnicTemplateVlanMapping mapping = vnicVlanMappingList
								.get(i);
						if (i == 0) {
							vlanArray = new Integer[vnicVlanMappingList.size()];
						}
						vlanArray[i] = (Integer) Util
								.nullValidationAndReturnId(mapping.getLanVlan());
					}
				}
				myMap.put(VLANID, vlanArray);
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/manageLanVnicConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLanVnicConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageLanVnicConfig");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<LanVnicTemplate> newlyAddedRecords = null;
		List<LanVnicTemplate> vnicConfigList = new ArrayList<LanVnicTemplate>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					for (int i = 0; i < addOrUpdateNodes.size(); i++) {
						JsonNode eachNode = addOrUpdateNodes.get(i);
						LanVnicTemplate lanVnicT = (LanVnicTemplate) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										LanVnicTemplate.class);

						if (lanVnicT != null) {
							List<LanVnicTemplateVlanMapping> mappingList = new ArrayList<LanVnicTemplateVlanMapping>();
							if (eachNode.has(VLANID)
									&& !eachNode.get(VLANID).isNull()) {
								String[] split = eachNode.get(VLANID)
										.toString().replaceAll(PATTERN, "")
										.split(",");
								for (int j = 0; j < split.length; j++) {
									LanVnicTemplateVlanMapping lvvm = new LanVnicTemplateVlanMapping();
									lvvm.setId(0);

									lvvm.setLanVnicTemplate(new LanVnicTemplate(
											lanVnicT.getId()));
									if (!NULL_STR.equalsIgnoreCase(split[j])) {
										lvvm.setLanVlan(new LanVlan(Integer
												.parseInt(split[j])));
									}
									mappingList.add(lvvm);
								}
							}
							lanVnicT.setLanVnictVlanMappings(mappingList);
							vnicConfigList.add(lanVnicT);
						}
					}
					newlyAddedRecords = this.lanService
							.saveOrUpdateLanVnicConfiguration(vnicConfigList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<LanVnicTemplate> deletedVnicList = (List<LanVnicTemplate>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									LanVnicTemplate.class);
					this.lanService.deleteLanVnicTemplate(deletedVnicList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		LOGGER.info("End: manageLanVnicConfig");
		return getLanVnicTemplateConfigDetailsJsonList(newlyAddedRecords);
	}

}
