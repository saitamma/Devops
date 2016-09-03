package com.cisco.ca.cstg.pdi.controllers.equipment;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class ChassisPolicyController implements Constants {

	private final EquipmentService equipmentService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChassisPolicyController.class);

	@Autowired
	public ChassisPolicyController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/equipmentChasisConfig.html")
	public String showEquipmentChasisConfig(Principal principal) {
		return "equipment/equipmentChasisConfig";
	}

	@RequestMapping(value = "/fetchEquipmentChasisDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchEquipmentChasisDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		List<Object> jsonList = new ArrayList<Object>();

		if (projectId != null) {
			try {
				List<EquipmentChasis> euipmentChasisList = equipmentService
						.fetchEquipmentChasisConfiguration(projectId);

				if (Util.listNotNull(euipmentChasisList)) {
					for (EquipmentChasis ec : euipmentChasisList) {
						Map<String, Object> myMap = new HashMap<String, Object>();
						myMap.put(ID, ec.getId());
						myMap.put(CDPACTION, ec.getCdpAction());
						myMap.put(CDPLINKAGG, ec.getCdpLinkAgg());
						myMap.put(PSPPOWERSUPPLY, ec.getPspPowerSupply());

						Util.convertMapToJson(myMap, jsonList);
					}
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/getEquipmentChassisDetailByProject", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isProjectChassisConfigSaved(
			@RequestParam("projectId") Integer projectId) throws IOException {
		boolean isChassisSaved = false;

		if (projectId != null) {
			try {
				List<EquipmentChasis> eqChassisList = this.equipmentService
						.fetchEquipmentChasisConfiguration(projectId);

				if (Util.listNotNull(eqChassisList)) {
					isChassisSaved = true;
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return isChassisSaved;
	}

	@RequestMapping(value = "/createEquipmentChasisConfig.html", method = RequestMethod.POST)
	@ResponseBody
	public String createEquipmentChasisConfig(
			@RequestParam(JSON_OBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			ObjectMapper mapper = new ObjectMapper();
			EquipmentChasis ec = mapper.readValue(jsonObj,
					EquipmentChasis.class);

			try {
				this.equipmentService.saveOrUpdateEquipmentChasisConfiguration(
						ec, projectId);
				return SUCCESS;
			} catch (DataIntegrityViolationException dive) {
				LOGGER.error(dive.getMessage(), dive);
				throw dive;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return FAILURE;
	}
}