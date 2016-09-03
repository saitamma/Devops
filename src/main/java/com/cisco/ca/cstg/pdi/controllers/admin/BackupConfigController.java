package com.cisco.ca.cstg.pdi.controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cisco.ca.cstg.pdi.pojos.AdminBackupConfiguration;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class BackupConfigController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BackupConfigController.class);
	private final AdminService adminService;

	@Autowired
	public BackupConfigController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/adminBackupConfiguration.html")
	public String showAdminBackupConfiguration() {
		return "admin/adminBackupConfiguration";
	}

	@RequestMapping(value = "/getAdminBackupConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getAdminBackupConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("project Id=" + projectId);
		LOGGER.info("Start : getAdminBackupConfig");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminBackupConfiguration> backupConfigList = null;
		try {
			if (projectId != null) {
				backupConfigList = this.adminService
						.fetchAdminBackupConfig(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		if (Util.listNotNull(backupConfigList)) {
			for (AdminBackupConfiguration abc : backupConfigList) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, abc.getId());
				myMap.put(BACKUPSTATUS, abc.getBackupStatus());
				myMap.put(ADMINSTATE, abc.getAdminState());
				myMap.put(BACKUPTYPE, abc.getBackupType());
				myMap.put(PRESERVEIDENTITIES, abc.getPreserveIdentities());
				myMap.put(PROTOCOL, abc.getProtocol());
				myMap.put(HOSTNAME, abc.getHostname());
				myMap.put(REMOTEFILE, abc.getRemoteFile());
				myMap.put(USERNAME, abc.getUserName());
				myMap.put(PASSWORD, abc.getPassword());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		LOGGER.info("End : getAdminBackupConfig");
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageAdminBackupConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String manageAdminBackupConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start : manageAdminBackupConfig");
		LOGGER.info("project Id=" + projectId);
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				List<AdminBackupConfiguration> newBackupList = (List<AdminBackupConfiguration>) (List<?>) Util
						.convertJsonToListOfObject(jsonObj,
								AdminBackupConfiguration.class);
				this.adminService.saveOrUpdateBackupConfig(newBackupList,
						projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		LOGGER.info("End : manageAdminBackupConfig");
		return SUCCESS;
	}

}
