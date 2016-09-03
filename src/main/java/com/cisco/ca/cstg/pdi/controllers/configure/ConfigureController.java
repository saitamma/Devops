package com.cisco.ca.cstg.pdi.controllers.configure;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import com.cisco.ca.cstg.pdi.pojos.Configuration;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServerDetails;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.services.CommonUtilServices;
import com.cisco.ca.cstg.pdi.services.ConfigurationService;
import com.cisco.ca.cstg.pdi.services.ProjectDetailsService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ucs.mgr.util.ResponseException;

@Controller
@SessionAttributes("activeProjectId")
public class ConfigureController implements Constants, ServletContextAware {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConfigureController.class);
	private final ConfigurationService configurationService;
	private final CommonUtilServices commonUtilServices;
	private final ProjectDetailsService projectDetailsService;
	private static final String UTILITY_JAR_NAME = "ucsada_config_utility.zip";
	private static final String UTILITY_JAR_PATH = "/resources/ucs_utility/";
	private static final String PUSH_CONF = "Push_Conf";
	private static final String UCSADA_UTILITY_HELP_DOC_PATH = "ucsada_utility_help_doc/ucsada_config_utility.docx";

	private ServletContext servletContext;

	@Autowired
	public ConfigureController(ConfigurationService configurationService,
			CommonUtilServices commonUtilServices,
			ProjectDetailsService projectDetailsService) {
		this.configurationService = configurationService;
		this.commonUtilServices = commonUtilServices;
		this.projectDetailsService = projectDetailsService;
	}

	@RequestMapping(value = "/configure.html")
	public String showConfigureMain(Principal principal) {
		LOGGER.info("Congigure Page");
		return "configure/configureMain";
	}

	@RequestMapping(value = "/verifyUCSCredentials.html", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUCSConnection(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			@RequestParam(JSON_OBJ) String jsonObj) throws IOException {
		LOGGER.info("Start - Verifying UCS Manager Credentials.");
		ObjectMapper mapper = new ObjectMapper();
		boolean pingStatus = false;
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				ServerDetails serverDetails = mapper.readValue(jsonObj,
						ServerDetails.class);
				Configuration config = new Configuration(serverDetails);
				config.setPingVerified(false);
				config.setProjectDetails(commonUtilServices
						.getProjectDetailsObject(projectId));
				pingStatus = this.configurationService
						.verifyConnection(serverDetails);
				config.setPingVerified(pingStatus);
				this.configurationService.saveOrUpdateConfiguration(config);
			}
		} catch (IOException | ResponseException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return Boolean.toString(pingStatus);
	}

	@RequestMapping(value = "/processDownloadWithSession.html", method = RequestMethod.POST)
	@ResponseBody
	public String processDownloadWithSession(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info(
				"Start - Processing UCS Manager configuration for downloading project id: {}",
				projectId);

		if (projectId != null) {
			XmlGenProjectDetails project = (XmlGenProjectDetails) commonUtilServices
					.findById(XmlGenProjectDetails.class, projectId);

			if (project != null) {
				Map<String, Object> processDownLoadStatus = this.configurationService
						.processUcsPdiConfiguration(project);

				if (processDownLoadStatus.get("Conf_Creation").equals(true)) {
					ProjectDetails pd = this.projectDetailsService
							.fetchProjectDetails(projectId);
					String returnPath = Util.getPdiConfDataFolderPath(pd);
					boolean zipFlag = this.configurationService
							.processToZip(returnPath);
					processDownLoadStatus.put("Zip_Creation", zipFlag);
				}
				return Util.convertMapToJson(processDownLoadStatus);
			}
		}
		return FAILURE;
	}

	@RequestMapping(value = "/processDownload.html", method = RequestMethod.POST)
	@ResponseBody
	public String processDownload(ModelMap model,
			@RequestParam(value = "projectId") Integer downloadProjectId)
			throws IOException {
		model.addAttribute("activeProjectId", downloadProjectId);
		return processDownloadWithSession(downloadProjectId);
	}

	@RequestMapping(value = "/configureUcs.html", method = RequestMethod.POST)
	@ResponseBody
	public String configureUcs(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			@RequestParam(JSON_OBJ) String jsonObj) throws IOException {

		LOGGER.info("Start - Configuring UCS Manager for project id: {}",
				projectId);
		Map<String, Object> processUcsConfStatus = new HashMap<>();
		processUcsConfStatus.put(PUSH_CONF, false);

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			ObjectMapper mapper = new ObjectMapper();
			ServerDetails serverDetails = mapper.readValue(jsonObj,
					ServerDetails.class);
			processUcsConfStatus = this.configurationService
					.processAndPushUcsPdiConfiguration(serverDetails, projectId);
			Configuration config = new Configuration(serverDetails);
			config.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			config.setPushStatus((Boolean) (processUcsConfStatus.get(PUSH_CONF) == null ? false
					: processUcsConfStatus.get(PUSH_CONF)));
			this.configurationService.saveOrUpdateConfiguration(config);
		}
		return Util.convertMapToJson(processUcsConfStatus);
	}

	@RequestMapping(value = "/downloadUcsData.html", method = RequestMethod.GET)
	@ResponseBody
	public void downloadUcsData(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOGGER.info("Start - Zipping download file for project id: {}",
				projectId);
		if (projectId != null) {
			ProjectDetails pd = this.projectDetailsService
					.fetchProjectDetails(projectId);
			File f1 = new File(Util.getPdiConfFolderPath(pd));

			if (f1.isDirectory()) {
				File archiveFile = new File(Util.getPdiConfDataFolderPath(pd)
						+ ".zip");
				Util.downloadArchiveFile(response, archiveFile);
			}
		}
	}

	@RequestMapping(value = "/downloadUcsAdaUtility.html", method = RequestMethod.GET)
	@ResponseBody
	public void downloadUcsAdaUtility(HttpServletRequest request,
			HttpServletResponse response) {
		String fullPath = servletContext.getRealPath(UTILITY_JAR_PATH);
		LOGGER.info(fullPath);
		File f1 = new File(fullPath);
		if (f1.isDirectory()) {
			File archiveFile = new File(fullPath + "/" + UTILITY_JAR_NAME);
			Util.downloadArchiveFile(response, archiveFile);
		}
	}

	@RequestMapping(value = "/getPushConfigLogs.html", method = RequestMethod.POST)
	@ResponseBody
	public List<Object> getPushConfigLogs(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			@RequestParam(JSON_OBJ) String jsonObj) throws IOException {

		List<Object> returnJsonList = new ArrayList<Object>();

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			ObjectMapper mapper = new ObjectMapper();
			ServerDetails serverDetails = mapper.readValue(jsonObj,
					ServerDetails.class);
			List<UcsServerLogs> processUcsConfigLogs = this.configurationService
					.getUCSConfigLogs(serverDetails, projectId);

			if (Util.listNotNull(processUcsConfigLogs)) {

				for (UcsServerLogs ucsServerLogs : processUcsConfigLogs) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					myMap.put(ID, ucsServerLogs.getId());
					myMap.put(NAME, ucsServerLogs.getName());
					myMap.put(DESCRIPTION, ucsServerLogs.getDescription());
					myMap.put(STATUS, ucsServerLogs.getStatus());
					Util.convertMapToJson(myMap, returnJsonList);
				}
			}
		}
		return returnJsonList;
	}

	@RequestMapping(value = "/downloadUcsUtilityHelpDocument.html", method = RequestMethod.GET)
	@ResponseBody
	public void downloadUcsUtilityHelpDocument(HttpServletRequest request,
			HttpServletResponse response) {
		String fullPath = servletContext.getRealPath(UTILITY_JAR_PATH);
		LOGGER.info(fullPath);
		File f1 = new File(fullPath);
		if (f1.isDirectory()) {
			File archiveFile = new File(fullPath + "/"
					+ UCSADA_UTILITY_HELP_DOC_PATH);
			Util.downloadArchiveFile(response, archiveFile);
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}