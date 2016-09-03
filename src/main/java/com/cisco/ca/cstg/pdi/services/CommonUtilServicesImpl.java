package com.cisco.ca.cstg.pdi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ProjectSettings;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ucs.mgr.CustomMethodFactory;
import com.cisco.ucs.mgr.UcsBase;
import com.cisco.ucs.mgr.UcsHandle;
import com.cisco.ucs.mgr.enums.NamingClassId;
import com.cisco.ucs.mgr.enums.YesOrNo;
import com.cisco.ucs.mgr.method.ConfigResolveClasses;
import com.cisco.ucs.mgr.method.helper.ClassId;
import com.cisco.ucs.mgr.method.helper.ClassIdSet;
import com.cisco.ucs.mgr.method.helper.ConfigSet;
import com.cisco.ucs.mgr.mo.MgmtImporter;
import com.cisco.ucs.mgr.mo.MgmtImporterFsmStage;

@Service("commonUtilServices")
public class CommonUtilServicesImpl extends CommonDaoServicesImpl implements
		CommonUtilServices {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommonUtilServicesImpl.class);

	private static final String IPADDRESS = "ipAddress";
	private static final String CHASSISCOUNT = "CHASSIS_COUNT";

	@Autowired
	public CommonUtilServicesImpl(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	@Override
	public ProjectDetails getProjectDetailsObject(Integer projectId) {
		ProjectDetails pd = new ProjectDetails();
		if (projectId != null) {
			pd.setId(projectId);
		}
		return pd;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object> rawQueryList(String query) {
		Session metaSession = getSessionFactory().openSession();
		Transaction metaTrans = metaSession.beginTransaction();
		List<Object> result = null;
		try {
			SQLQuery nodesQuery = metaSession.createSQLQuery(query);
			result = nodesQuery.list();

		} catch (HibernateException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			metaSession.flush();
			metaTrans.commit();
			metaSession.close();
		}

		return result;
	}

	@Override
	public void saveUCSServerLogs(Integer projectId, String ipaddress,
			List<UcsServerLogs> ucsServerLogsList) {

		if (ucsServerLogsList != null && !ucsServerLogsList.isEmpty()) {
			LOGGER.info("CommonUtilServiceImpl: saveUCSServerLogs : ucsServerLogsList not null");
			Criterion whereClause = Restrictions.and(
					Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId),
					Restrictions.eq(IPADDRESS, ipaddress));
			deleteByCriteria(UcsServerLogs.class, whereClause);
			addList(ucsServerLogsList);
		}
	}

	@Override
	public Map<String, String> returnLogValue(Integer projectId,
			UcsHandle handle, String ipaddress) {

		Map<String, String> output = new HashMap<String, String>();
		List<UcsServerLogs> serverLogsList = new ArrayList<UcsServerLogs>();

		try {
			ClassIdSet arg2 = new ClassIdSet();

			ClassId id = new ClassId();
			id.setValue(NamingClassId.FSM_STATUS);

			id = new ClassId();
			id.setValue(NamingClassId.MGMT_IMPORTER_FSM);

			id = new ClassId();
			id.setValue(NamingClassId.MGMT_IMPORTER);

			arg2.addChild(id);

			ClassIdSet arg3 = new ClassIdSet();
			id = new ClassId();
			id.setValue(NamingClassId.MGMT_IMPORTER_FSM_STAGE);

			arg3.addChild(id);

			ConfigResolveClasses fsmstage = CustomMethodFactory
					.getConfigResolveClasses(handle, YesOrNo.FALSE, arg3);

			if (fsmstage != null) {

				ConfigSet configConfig = fsmstage.getOutConfigs();

				List<UcsBase> fsmstagechild = configConfig.getChild();
				int startValue = 0;

				if (fsmstagechild != null) {
					startValue = fsmstagechild.size() - 3;

					for (; startValue < fsmstagechild.size(); startValue++) {

						MgmtImporterFsmStage mgmtImporterFsmStage = (MgmtImporterFsmStage) fsmstagechild
								.get(startValue);

						serverLogsList.add(constructUcsServerLogsObject(
								projectId, ipaddress,
								(String) mgmtImporterFsmStage.getattr("name"),
								(String) mgmtImporterFsmStage.getattr("descr"),
								(String) mgmtImporterFsmStage
										.getattr("stageStatus")));
						output.put((String) mgmtImporterFsmStage
								.getattr("name"), (String) mgmtImporterFsmStage
								.getattr("stageStatus"));
					}
				}
			}

			ConfigResolveClasses mgmtImporter1 = CustomMethodFactory
					.getConfigResolveClasses(handle, YesOrNo.FALSE, arg2);

			ConfigSet configConfig1 = mgmtImporter1.getOutConfigs();

			List<UcsBase> configConfigchild = configConfig1.getChild();
			MgmtImporter mgmtImporterfinal = (MgmtImporter) configConfigchild
					.get(0);

			serverLogsList.add(constructUcsServerLogsObject(projectId,
					ipaddress, "FSM Status",
					(String) mgmtImporterfinal.getattr("fsmStatus"),
					(String) mgmtImporterfinal.getattr("fsmStatus")));
			serverLogsList.add(constructUcsServerLogsObject(projectId,
					ipaddress, "Description",
					(String) mgmtImporterfinal.getattr("fsmDescr"), ""));
			serverLogsList.add(constructUcsServerLogsObject(projectId,
					ipaddress, "Current FSM Name",
					(String) mgmtImporterfinal.getattr("name"), ""));
			serverLogsList.add(constructUcsServerLogsObject(projectId,
					ipaddress, "Completed at",
					(String) mgmtImporterfinal.getattr("fsmStamp"), ""));
			serverLogsList.add(constructUcsServerLogsObject(projectId,
					ipaddress, "Remote Invocation Result",
					(String) mgmtImporterfinal.getattr("fsmRmtInvRslt"), ""));
			serverLogsList
					.add(constructUcsServerLogsObject(projectId, ipaddress,
							"Remote Invocation Error Code",
							(String) mgmtImporterfinal
									.getattr("fsmRmtInvErrCode"), ""));
			serverLogsList
					.add(constructUcsServerLogsObject(projectId, ipaddress,
							"Remote Invocation Description",
							(String) mgmtImporterfinal
									.getattr("fsmRmtInvErrDescr"), ""));

			saveUCSServerLogs(projectId, ipaddress, serverLogsList);
		} catch (Exception e) {
			LOGGER.error("Exception occured for returnLogValue method.", e);
		}
		return output;
	}

	public UcsServerLogs constructUcsServerLogsObject(Integer projectId,
			String ipaddress, String name, String desc, String status) {
		UcsServerLogs obj = new UcsServerLogs();

		obj.setProjectDetails(getProjectDetailsObject(projectId));
		obj.setIpAddress(ipaddress);
		obj.setName(name);
		obj.setDescription(desc);
		obj.setStatus(status);

		return obj;
	}

	@Override
	public void putInDbLogValues(Integer projectId, Map<String, String> output,
			String ipaddress) {
		List<UcsServerLogs> ucsServerLogsList = new ArrayList<UcsServerLogs>();

		Iterator<Entry<String, String>> itr = output.entrySet().iterator();
		while (itr.hasNext()) {
			int dbId = 1;
			@SuppressWarnings("rawtypes")
			Map.Entry mapEntry = itr.next();
			String key = mapEntry.getKey().toString();
			UcsServerLogs ucsServerLog = new UcsServerLogs();
			ucsServerLog.setId(dbId);
			ucsServerLog.setIpAddress(ipaddress);
			ucsServerLog.setDescription(output.get(key));
			ucsServerLog.setName(key.replaceAll("\\d+", ""));
			ucsServerLog.setProjectDetails(getProjectDetailsObject(projectId));
			ucsServerLogsList.add(ucsServerLog);
		}

		saveUCSServerLogs(projectId, ipaddress, ucsServerLogsList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ProjectSettings fetchProjectSettings(Integer projectId, String key) {
		Criterion criteria = Restrictions.and(Restrictions.eq(
				Constants.PROJECTDETAILS, getProjectDetailsObject(projectId)),
				Restrictions.eq(Constants.PROJECT_KEY, key));
		List<ProjectSettings> listProjectSettings = (List<ProjectSettings>) (List<?>) listAll(
				ProjectSettings.class, criteria);

		if (!Util.listNotNull(listProjectSettings)) {
			return null;
		}
		return listProjectSettings.get(0);
	}

	@Override
	public void saveOrUpdateProjectSettings(ProjectSettings projectSettings,
			Integer projectId) {
		ProjectSettings fetchProjectSetting = fetchProjectSettings(projectId,
				CHASSISCOUNT);
		if (fetchProjectSetting != null) {
			projectSettings.setId(fetchProjectSetting.getId());
		}
		projectSettings.setProjectDetails(getProjectDetailsObject(projectId));
		if (projectSettings.getId() == 0) {
			addNew(projectSettings);
		} else {
			update(projectSettings);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminPrivilege> fetchRolePrivileges() {
		return (List<AdminPrivilege>) (List<?>) listAll(AdminPrivilege.class);
	}
}