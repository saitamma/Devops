package com.cisco.ca.cstg.pdi.controllers.project;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.ca.cstg.pdi.pojos.ADAUsers;
import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.services.ConfigurationService;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.ProjectDetailsService;
import com.cisco.ca.cstg.pdi.services.WizardConfigurationService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
public class ProjectController implements Constants {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectController.class);

	private final ProjectDetailsService projectDetailsService;
	private final WizardConfigurationService wizardConfigurationService;
	private final ConfigurationService configurationService;
	private final InfrastructureService infrastructureService;
	private final EquipmentService equipmentService;

	@Autowired
	public ProjectController(ProjectDetailsService projectDetailsService,
			WizardConfigurationService wizardConfigurationService,
			ConfigurationService configurationService,
			InfrastructureService infrastructureService,
			EquipmentService equipmentService) {
		this.projectDetailsService = projectDetailsService;
		this.wizardConfigurationService = wizardConfigurationService;
		this.configurationService = configurationService;
		this.infrastructureService = infrastructureService;
		this.equipmentService = equipmentService;
	}

	@RequestMapping(value = "/listProjects.html", method = RequestMethod.GET)
	public String showProjects(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		HttpSession session = request.getSession();
		String userCec = request.getHeader("AUTH_USER");

		/*Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String userCec = authentication.getName();*/

		List<ADAUsers> userDetail = projectDetailsService
				.getUserDetailByUserCec(userCec);
		try {
			if (Util.listNotNull(userDetail)) {
				ADAUsers currUser = userDetail.get(0);
				int id = currUser.getId();
				model.addAttribute(ACTIVE_USERID, id);
				session.setAttribute(ACTIVE_USERID, id);
				if (currUser.getUserRoles() != null) {
					session.setAttribute(USER_ROLE, currUser.getUserRoles()
							.getUserRole());
					model.addAttribute(USER_ROLE, currUser.getUserRoles()
							.getUserRole());
				}
				session.setAttribute(LOGIN_USER_CEC, userCec);
				model.addAttribute(LOGIN_USER_CEC, userCec);
			} else {
				model.addAttribute(ACTIVE_USERID, "");
				model.addAttribute(USER_ROLE, "");
				model.addAttribute(LOGIN_USER_CEC, userCec);
				session.setAttribute(LOGIN_USER_CEC, userCec);
				response.sendRedirect("accessDenied.html");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "project/project";
	}

	@RequestMapping(value = "/listProjectData.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> listProjects(HttpServletRequest request)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<ProjectDetails> projectList = null;
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);

		HttpSession session = request.getSession();
		String userCec = (String) session.getAttribute(LOGIN_USER_CEC);

		try {
			projectList = projectDetailsService.getProjects(userCec);
			if (projectList != null) {
				Collections.reverse(projectList);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		if (projectList != null) {
			for (ProjectDetails pm : projectList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, pm.getId());
				myMap.put(PROJECTNAME, pm.getProjectName());
				myMap.put(THEATRE, pm.getTheatre());
				myMap.put(
						CREATEDDATETIME,
						(pm.getCreatedDate() != null) ? df.format(pm
								.getCreatedDate()) : pm.getCreatedDate());
				myMap.put(CREATEDBY, pm.getCreatedBy());
				myMap.put(SKIP_SAN, pm.getSkipSan());
				myMap.put(IP_POOL_ASSIGNMENT_ORDER,
						pm.getIpPoolAssignmentOrder());
				List<Infrastructure> infrastructures = pm.getInfrastructures();
				for (Infrastructure infra : infrastructures) {
					myMap.put(FABRICINTERCONNECT,
							infra.getServerModel() != null ? infra
									.getServerModel().getDescription() : "");
					myMap.put(SOFTWARERELEASE, infra.getSoftwareVersion());
					if (infra.getIoModule() != null
							&& !infra.getIoModule().isEmpty()) {
						myMap.put(IOMODULE, 220 + "" + infra.getIoModule());
					} else {
						myMap.put(IOMODULE, "");
					}
				}

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/createProject.html", method = RequestMethod.POST)
	@ResponseBody
	public String createProject(@RequestParam(JSONOBJ) String jsonObj,
			HttpServletRequest request) throws IOException {
		LOGGER.debug(Constants.JSONSTRING + jsonObj);

		Integer addedProjectId = 0;

		if (Util.isStringNotEmpty(jsonObj)) {
			ObjectMapper mapper = new ObjectMapper();
			HttpSession session = request.getSession();
			ProjectDetails pd = mapper.readValue(jsonObj, ProjectDetails.class);
			String createdBy = (String) session.getAttribute(LOGIN_USER_CEC);
			pd.setCreatedBy(createdBy);
			try {
				addedProjectId = this.projectDetailsService.addNewProject(pd);
				this.wizardConfigurationService.createWizardStatus(pd.getId());
			} catch (DataIntegrityViolationException dive) {
				LOGGER.error(dive.getMessage(), dive);
				throw dive;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return Integer.toString(addedProjectId);
	}

	@RequestMapping(value = "/updateProject.html", method = RequestMethod.POST)
	@ResponseBody
	public String updateProject(@RequestParam("jsonObj") String jsonObj,
			HttpServletRequest request) throws IOException {
		LOGGER.debug(Constants.JSONSTRING + jsonObj);
		if (Util.isStringNotEmpty(jsonObj)) {
			ObjectMapper mapper = new ObjectMapper();
			HttpSession session = request.getSession();
			String createdBy = (String) session.getAttribute(LOGIN_USER_CEC);
			ProjectDetails pd = mapper.readValue(jsonObj, ProjectDetails.class);
			pd.setCreatedBy(createdBy);
			pd.setUpdatedBy(createdBy);
			try {
				this.projectDetailsService.updateProjectDetails(pd);
			} catch (DataIntegrityViolationException dive) {
				LOGGER.error(dive.getMessage(), dive);
				throw dive;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
			return SUCCESS;
		}
		return FAILURE;
	}

	@RequestMapping(value = "/deleteProject.html", method = RequestMethod.GET)
	@ResponseBody
	public String deleteProject(@RequestParam("projectId") Integer projectId)
			throws IOException {
		LOGGER.debug("ProjectId..." + projectId);

		if (projectId != null) {
			try {
				this.projectDetailsService.deleteProject(projectId);
			} catch (DataIntegrityViolationException dive) {
				LOGGER.error(dive.getMessage(), dive);
				throw dive;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}

			return SUCCESS;
		}
		return FAILURE;
	}

	@RequestMapping(value = "/updateSkipSanDetail.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateSkipSanDetail(@RequestParam(JSONOBJ) String jsonObj)
			throws IOException {

		if (Util.isStringNotEmpty(jsonObj)) {
			ObjectMapper mapper = new ObjectMapper();
			ProjectDetails pd = mapper.readValue(jsonObj, ProjectDetails.class);
			boolean skipSan = pd.getSkipSan();
			try {
				pd = this.projectDetailsService.fetchProjectDetails(pd.getId());

				if (pd != null) {
					pd.setSkipSan(skipSan);
					this.projectDetailsService.updateProjectDetails(pd);
					return SUCCESS;
				}
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

	@RequestMapping(value = "/updateIpPoolAssignmentOrder.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateIpPoolAssignmentOrder(
			@RequestParam(JSONOBJ) String jsonObj) throws IOException {
		if (Util.isStringNotEmpty(jsonObj)) {
			ObjectMapper mapper = new ObjectMapper();
			ProjectDetails pd = mapper.readValue(jsonObj, ProjectDetails.class);
			String ipPoolAssignmentOrder = pd.getIpPoolAssignmentOrder();
			try {
				pd = this.projectDetailsService.fetchProjectDetails(pd.getId());

				if (pd != null) {
					pd.setIpPoolAssignmentOrder(ipPoolAssignmentOrder);
					this.projectDetailsService.updateProjectDetails(pd);
					return SUCCESS;
				}
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

	@RequestMapping(value = "/fetchProjectDetailIfNameAlreadyExist.html", method = RequestMethod.POST)
	@ResponseBody
	public boolean fetchProjectDetailIfNameAlreadyExist(
			@RequestParam(JSONOBJ) String projectData,
			HttpServletRequest request) throws IOException {
		boolean isExits = false;
		if (Util.isStringNotEmpty(projectData)) {
			HttpSession session = request.getSession();
			String userCec = (String) session.getAttribute(LOGIN_USER_CEC);
			ObjectMapper mapper = new ObjectMapper();
			ProjectDetails pd = mapper.readValue(projectData,
					ProjectDetails.class);
			try {
				isExits = this.projectDetailsService
						.isProjectExits(pd, userCec);
			} catch (DataIntegrityViolationException dive) {
				LOGGER.error(dive.getMessage(), dive);
				throw dive;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return isExits;
	}

	@RequestMapping(value = "/cloningSelectedProject.html", method = RequestMethod.POST)
	@ResponseBody
	public List<Object> cloningSelectedProject(
			@RequestParam(JSONOBJ) String jsonObj, HttpServletRequest request)
			throws IOException {

		List<Object> jsonList = new ArrayList<Object>();
		if (Util.isStringNotEmpty(jsonObj)) {
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			ProjectDetails sourceProject = mapper.readValue(jsonObj,
					ProjectDetails.class);
			HttpSession session = request.getSession();
			String loginUserCec = (String) session.getAttribute(LOGIN_USER_CEC);
			if (sourceProject != null) {
				try {
					sourceProject.setCreatedBy(loginUserCec);
					ProjectDetails cloningPD = this.projectDetailsService
							.createCloneProject(sourceProject);
					this.wizardConfigurationService
							.createWizardStatus(cloningPD.getId());
					Map<String, Object> myMap = new HashMap<String, Object>();
					myMap.put(ID, cloningPD.getId());
					myMap.put(PROJECTNAME, cloningPD.getProjectName());
					myMap.put(THEATRE, cloningPD.getTheatre());
					myMap.put(CREATEDDATETIME,
							df.format(cloningPD.getCreatedDate()));
					myMap.put(CREATEDBY, cloningPD.getCreatedBy());
					myMap.put(SKIP_SAN, cloningPD.getSkipSan());
					myMap.put(IP_POOL_ASSIGNMENT_ORDER,
							cloningPD.getIpPoolAssignmentOrder());
					Util.convertMapToJson(myMap, jsonList);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					throw e;
				}
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/fillDataForClonedProject.html", method = RequestMethod.GET)
	@ResponseBody
	public String fillDataForClonedProject(
			@RequestParam("sourceId") Integer sourceProjectId,
			@RequestParam("targetId") Integer targetProjectId)
			throws IOException {

		if (sourceProjectId != null && targetProjectId != null) {
			try {
				this.configurationService.cloneProject(sourceProjectId,
						targetProjectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new IOException(e);
			}
			return SUCCESS;
		}
		return FAILURE;
	}

	@RequestMapping(value = "/importProject.html", method = RequestMethod.POST)
	@ResponseBody
	public Integer importProject(@RequestParam(JSONOBJ) String jsonObj,
			HttpServletRequest request) throws IOException {
		Integer addNewProjectId = 0;

		if (Util.isStringNotEmpty(jsonObj)) {
			HttpSession session = request.getSession();
			String createdBy = (String) session.getAttribute(LOGIN_USER_CEC);
			try {
				ObjectMapper mapper = new ObjectMapper();

				JsonNode addProjectNode = Util.getJsonNodeByName(jsonObj,
						PROJECTDATA);
				JsonNode addInfraNode = Util.getJsonNodeByName(jsonObj,
						INFRADATA);
				JsonNode addEquipmentNode = Util.getJsonNodeByName(jsonObj,
						EQUIPMENTDATA);

				if (Util.jsonNodeNotNull(addProjectNode)) {
					ProjectDetails pd = mapper.readValue(addProjectNode,
							ProjectDetails.class);
					pd.setCreatedBy(createdBy);
					addNewProjectId = this.projectDetailsService
							.addNewProject(pd);
					this.wizardConfigurationService
							.createWizardStatus(addNewProjectId);
				}
				if (Util.jsonNodeNotNull(addInfraNode)) {
					Infrastructure inf = new Infrastructure();
					ServerModel sm = null;

					String serverModel = addInfraNode.get(SERVERMODEL)
							.getTextValue();
					if (serverModel != null) {
						List<ServerModel> objList = this.infrastructureService
								.fetchServerModelDetails(serverModel);
						if (Util.listNotNull(objList)) {
							sm = objList.get(0);
							inf.setServerModel(sm);
						}
					}
					inf.setSoftwareVersion(addInfraNode.get(SOFTWAREVERSION)
							.getTextValue());
					inf.setIoModule(addInfraNode.get(IOMODULE).getTextValue());

					this.infrastructureService.saveInfrastructureDetails(inf,
							addNewProjectId);
					if (SERVERMODEL6324.equals(serverModel)) {
						equipmentService
								.insertScalabilityPortsForMini(addNewProjectId);
					}
				}
				if (Util.jsonNodeNotNull(addEquipmentNode)) {
					EquipmentChasis equipmentChassis = mapper.readValue(
							addEquipmentNode, EquipmentChasis.class);
					this.equipmentService
							.saveOrUpdateEquipmentChasisConfiguration(
									equipmentChassis, addNewProjectId);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return addNewProjectId;
	}

	@RequestMapping(value = "/postImportedProjectFile.html", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postImportedProjectFile(
			@RequestParam("upload_project") MultipartFile file,
			@RequestParam("projectid") Integer projectId) throws IOException {

		ModelAndView modelAndView = null;

		if (file != null && projectId != null) {
			String errorMsg = "Error while uploading file. Please try again or contact dcv-support@cisco.com for further assistance.";
			try {
				ProjectDetails pd = this.projectDetailsService
						.fetchProjectDetails(projectId);
				modelAndView = new ModelAndView(
						"project/postImportedProjectFile");
				modelAndView.addObject("projectId", projectId);
				modelAndView.addObject("projectName", pd.getProjectName());

				this.configurationService.validateAndCopyFile(file, projectId);
				try {
					this.configurationService.checkMetadataVersion(projectId);
				} catch (IllegalStateException e) {
					errorMsg = "Incompatible Metadata xml in zip file. Please try again with updated files or Upload the Configuration xml alone. Please contact dcv-support@cisco.com for any further assistance.";
					throw e;
				}
				this.configurationService.importXMlToADAProject(projectId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				modelAndView = new ModelAndView(RESPONSE_FILE_UPLOAD,
						KEY_ERROR_MESSAGE, errorMsg);
				projectDetailsService.deleteProject(projectId);
			}
		}
		return modelAndView;
	}
}