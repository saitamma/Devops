package com.cisco.ca.cstg.pdi.controllers.infrastructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.InfrastructureEthernetFCMode;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class InfrastructureController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(InfrastructureController.class);
	private final InfrastructureService infrastructureService;
	private final EquipmentService equipmentService;

	@Autowired
	public InfrastructureController(InfrastructureService infrastructureService, EquipmentService equipmentService) {
		this.infrastructureService = infrastructureService;
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/infrastructure.html", method = RequestMethod.GET)
	public String showInfrastructure() {
		return "infrastructure/infrastructureMain";
	}

	@RequestMapping(value = "/getInfrastructureDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getInfrastructureDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId) throws IOException {
		LOGGER.info("Start: getInfrastructureDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);
		List<Object> jsonList = new ArrayList<Object>();
		try {
			if (projectId != null) {
				List<Infrastructure> infrastructureData = infrastructureService
						.fetchInfrastructureDetails(projectId);

				if (Util.listNotNull(infrastructureData)) {

					for (Infrastructure inf : infrastructureData) {
						Map<String, Object> myMap = new HashMap<String, Object>();
						myMap.put(ID, inf.getId());
						myMap.put(SOFTWAREVERSION, inf.getSoftwareVersion());
						myMap.put(SERVERMODEL, inf.getServerModel()
								.getDescription());
						myMap.put(IOMODULE, inf.getIoModule());

						Util.convertMapToJson(myMap, jsonList);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		LOGGER.info("End: getInfrastructureDetails");
		return jsonList;
	}

	@RequestMapping(value = "/saveInfrastructure.html", method = RequestMethod.POST)
	@ResponseBody
	public String saveInfrastructure(@RequestParam("jsonObj") String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			HttpServletRequest req) throws IOException {
		LOGGER.info("Start: saveInfrastructure");
		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);
		HttpSession session = req.getSession();
		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			ObjectMapper mapper = new ObjectMapper();
			Infrastructure inf = new Infrastructure();
			ServerModel sm = null;
			JsonNode readTree = mapper.readTree(jsonObj);

			try {
				String serverModel = readTree.get(SERVERMODEL).getTextValue();

				if (serverModel != null) {
					List<ServerModel> objList = this.infrastructureService
							.fetchServerModelDetails(serverModel);
					if (Util.listNotNull(objList) && objList.size() == 1) {
						sm = objList.get(0);
						inf.setServerModel(sm);
					}
				}
				inf.setSoftwareVersion(readTree.get(SOFTWAREVERSION)
						.getTextValue());
				inf.setIoModule(readTree.get(IOMODULE).getTextValue());
				this.infrastructureService.saveInfrastructureDetails(inf,
						projectId);

				if (SERVERMODEL6324.equals(serverModel)) {
					session.setAttribute("ISMINISERVERMODEL", true);
					this.equipmentService
							.insertScalabilityPortsForMini(projectId);
					// this.infrastructureService.deleteEthernetAdapterPolicyDefaultUsNICValue(projectId,"usNIC");
				} else {
					session.setAttribute("ISMINISERVERMODEL", false);
				}
			} catch (DataIntegrityViolationException dive) {
				LOGGER.error(dive.getMessage(), dive);
				throw dive;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
			LOGGER.info("End: saveInfrastructure");
			return SUCCESS;
		}
		return FAILURE;
	}

	/**
	 * This method is not implemented yet
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkIfSessionExist.html", method = RequestMethod.GET)
	@ResponseBody
	public void checkIfSessionExist() throws IOException {
		//Business requirement yet to come.
	}

	@RequestMapping(value = "/fetchEquipmentEthernetFcMode.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentEthernetFcMode(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();

		try {
			List<InfrastructureEthernetFCMode> fetchEthernetFcMode = this.infrastructureService
					.fetchEthernetFcMode(projectId);
			if (Util.listNotNull(fetchEthernetFcMode)) {
				for (InfrastructureEthernetFCMode equpEF : fetchEthernetFcMode) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					myMap.put(ID, equpEF.getId());
					myMap.put("ethernetMode", equpEF.getEthernetMode());
					myMap.put("fcMode", equpEF.getFcMode());

					Util.convertMapToJson(myMap, jsonList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return jsonList;
	}

	@RequestMapping(value = "/saveEthernetFcMode.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveEthernetFcMode(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			@RequestParam(JSONOBJ) String jsonObj) throws IOException {
		System.out.println("Project ID=" + projectId);
		System.out.println("String Json=" + jsonObj.toString());
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				ObjectMapper mapper = new ObjectMapper();
				InfrastructureEthernetFCMode ethFcObj = mapper.readValue(jsonObj,
						InfrastructureEthernetFCMode.class);
				this.infrastructureService.saveOrUpdateEthernetFcMode(ethFcObj,
						projectId);
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return FAILURE;

	}
}
