package com.cisco.ca.cstg.pdi.services;

import java.util.List;
import java.util.Map;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServices;
import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ProjectSettings;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ucs.mgr.UcsHandle;

public interface CommonUtilServices extends CommonDaoServices {

	/**
	 * This method returns a ProjectDetails object after populating
	 * activeProjectId
	 * 
	 * @author pavkuma2
	 * 
	 */
	ProjectDetails getProjectDetailsObject(Integer projectId);

	List<Object> rawQueryList(String query);

	void saveUCSServerLogs(Integer projectId, String ipaddress,
			List<UcsServerLogs> ucsServerLogsList);

	void putInDbLogValues(Integer projectId, Map<String, String> output,
			String ipaddress);

	Map<String, String> returnLogValue(Integer projectId, UcsHandle handle,
			String ipaddress);

	ProjectSettings fetchProjectSettings(Integer projectId, String key);

	void saveOrUpdateProjectSettings(ProjectSettings projectSettings,
			Integer projectId);

	List<AdminPrivilege> fetchRolePrivileges();

}
