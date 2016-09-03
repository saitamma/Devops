package com.cisco.ca.cstg.pdi.controllers.common;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ProjectSettings;
import com.cisco.ca.cstg.pdi.pojos.WizardStatus;
import com.cisco.ca.cstg.pdi.services.CommonUtilServices;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.ProjectDetailsService;
import com.cisco.ca.cstg.pdi.services.WizardConfigurationService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class CommonController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommonController.class);

	private final WizardConfigurationService wizardConfigurationService;
	private final ProjectDetailsService projectDetailsService;
	private final CommonUtilServices commonUtilService;
	private final InfrastructureService infrastructureService;

	@Autowired
	public CommonController(
			WizardConfigurationService wizardConfigurationService,
			ProjectDetailsService projectDetailsService,
			CommonUtilServices commonUtilService,
			InfrastructureService infrastructureService) {
		this.wizardConfigurationService = wizardConfigurationService;
		this.projectDetailsService = projectDetailsService;
		this.commonUtilService = commonUtilService;
		this.infrastructureService = infrastructureService;
	}

	@RequestMapping(value = "/header.html", method = RequestMethod.GET)
	public String header(ModelMap model, Principal principal) {
		return "common/header";
	}

	@RequestMapping(value = "/footer.html", method = RequestMethod.GET)
	public String footer(ModelMap model, Principal principal) {
		return "common/footer";
	}

	@RequestMapping(value = "/error.html")
	public String onError() {
		return "error";
	}

	@RequestMapping(value = "/wizard.html", method = RequestMethod.POST)
	public String showWizard(ModelMap model,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("projectName") String projectName, HttpServletRequest req) {
		LOGGER.info("Start: showWizard");
		LOGGER.debug(Constants.ACTIVEPROJECTID + projectId);
		
		HttpSession session = req.getSession();
		boolean isMini = false;
		try {
			List<Infrastructure> infrastructureDetails = infrastructureService
					.fetchInfrastructureDetails(projectId);
			if (Util.listNotNull(infrastructureDetails)) {

				if (SERVERMODEL6324.equals((infrastructureDetails.get(0))
						.getServerModel().getDescription())) {
					isMini = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		session.setAttribute("ISMINISERVERMODEL", isMini);

		model.addAttribute("activeProjectId", projectId);
		model.addAttribute("activeProjectName", projectName);
		LOGGER.info("End: showWizard");
		return "common/wizardTemplate";
	}

	@RequestMapping(value = "/getWizardStatus.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> fetchActiveWizardStatus(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId) {

		List<Object> jsonList = new ArrayList<Object>();
		try {
			if (projectId != null) {
				WizardStatus wizardStatus = wizardConfigurationService
						.getActiveWizardStatus(projectId);
				List<Object> completedMainMenuWizardStatus = wizardConfigurationService
						.getCompletedMainMenuWizardStatus(projectId);
				List<Object> completedSubMenuWizardStatus = wizardConfigurationService
						.getCompletedSubMenuWizardStatus(projectId,
								completedMainMenuWizardStatus);

				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put("activeStateMenuIndex", wizardStatus
						.getMainMenuState().getId());
				myMap.put("activeStateSubMenuIndex",
						wizardStatus.getSubMenuId());
				myMap.put("hasCompletedMenuIndex",
						completedMainMenuWizardStatus);

				if (completedSubMenuWizardStatus.size() >= 1) {
					myMap.put(
							"hasCompletedSubMenuIndex",
							completedSubMenuWizardStatus
									.get(completedSubMenuWizardStatus.size() - 1));
				} else {
					myMap.put("hasCompletedSubMenuIndex", null);
				}

				Util.convertMapToJson(myMap, jsonList);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return jsonList;
	}

	@RequestMapping(value = "/setWizardStatus.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateWizardStatus(@RequestParam(JSONOBJ) String jsonObj)
			throws IOException {

		try {
			if (Util.isStringNotEmpty(jsonObj)) {
				wizardConfigurationService.updateWizardStatus(jsonObj);
				return "{success}";
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return "{}";
	}

	@RequestMapping(value = "/getPDIProperties.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getPDIProperty() {
		List<Object> jsonList = new ArrayList<Object>();
		Map<String, Object> pdiPropertyJson = new HashMap<String, Object>();
		String version = PdiConfig.getProperty(PDI_VERSION);
		String releaseDate = PdiConfig.getProperty(PDI_RELEASEDATE);
		String releaseType = PdiConfig.getProperty(PDI_RELEASETYPE);
		pdiPropertyJson.put(PDIVERSION, version);
		pdiPropertyJson.put(PDIRELEASEDATE, releaseDate);
		pdiPropertyJson.put(PDIRELEASETYPE, releaseType);
		Util.convertMapToJson(pdiPropertyJson, jsonList);
		return jsonList;
	}

	@RequestMapping(value = "/skipSanUpdateWizardStatus.html", method = RequestMethod.GET)
	@ResponseBody
	public void skipSanUpdateWizardStatus(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId) {

		try {
			if (projectId != null) {
				WizardStatus wizardStatus = wizardConfigurationService
						.getActiveWizardStatus(projectId);
				wizardConfigurationService
						.updateMainMenuStatusCompleted(wizardStatus);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/fetchCurrentProjectDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchCurrentProjectDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		ProjectDetails pd = null;

		try {
			// Fetch Existing Skip SAN details Configuration details
			if (projectId != null) {
				pd = this.projectDetailsService.fetchProjectDetails(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		if (pd != null) {
			Map<String, Object> myMap = new HashMap<String, Object>();
			myMap.put(ID, pd.getId());
			myMap.put(PROJECTNAME, pd.getProjectName());
			myMap.put(SKIP_SAN, pd.getSkipSan());
			myMap.put(IP_POOL_ASSIGNMENT_ORDER, pd.getIpPoolAssignmentOrder());
			Util.convertMapToJson(myMap, jsonList);
		} else {
			LOGGER.error("Skip SAN details not present.");
		}
		return jsonList;
	}

	@RequestMapping(value = "/fetchSelectedChassisInfo.html", method = RequestMethod.GET)
	@ResponseBody
	public String fetchSelectedChassisInfo(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		String chassis = "0";
		try {
			// Fetch Existing Chassis count details
			if (projectId != null) {
				ProjectSettings fetchProjectSettings = this.commonUtilService
						.fetchProjectSettings(projectId, CHASSIS_COUNT);
				if (fetchProjectSettings != null) {
					chassis = fetchProjectSettings.getProjectValue();
				}
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return chassis;
	}

	@RequestMapping(value = "/updateSelectedChassisInfo.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateSelectedChassisInfo(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		// Converting json object

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				ObjectMapper mapper = new ObjectMapper();
				ProjectSettings ps = mapper.readValue(jsonObj,
						ProjectSettings.class);
				this.commonUtilService.saveOrUpdateProjectSettings(ps,
						projectId);
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return FAILURE;
	}

	@RequestMapping(value = "/fetchRolePrivileges.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> fetchRolePrivileges() throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminPrivilege> rolePrivilegesList = null;

		try {
			rolePrivilegesList = this.commonUtilService.fetchRolePrivileges();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		if (rolePrivilegesList != null) {
			for (AdminPrivilege adminPrivilege : rolePrivilegesList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, adminPrivilege.getId());
				myMap.put(NAME, adminPrivilege.getName());
				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}
	
	/**
	 * This method is used to handle the request which is coming from LAE side to test the server status 
	 * @return Success message
	 */
	@RequestMapping(value = "/pingpong.html", method = RequestMethod.HEAD)
	@ResponseBody
	public String getServerStatus() {
		return "Success";
	}
	
	/**
	 * This method is used to check the application status 
	 * @return Success
	 */
	@RequestMapping(value = "/test.html",method = RequestMethod.GET)
	@ResponseBody
	public String getApplicationStatus() {
		return "Success";
	}
	
	@RequestMapping(value = "/accessDenied.html", method = RequestMethod.GET)
	public String accessDenied(HttpServletRequest req){
		LOGGER.info("cec=="+req.getSession().getAttribute("userCec"));
		return "accessDenied";
	}
	
}
