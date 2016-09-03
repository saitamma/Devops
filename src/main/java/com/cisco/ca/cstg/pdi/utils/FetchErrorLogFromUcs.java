package com.cisco.ca.cstg.pdi.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
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
import com.cisco.ucs.mgr.util.ResponseException;

@SessionAttributes("activeProjectId")
public class FetchErrorLogFromUcs {

	private static final String ACTIVE_PROJECT_ID = "activeProjectId";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FetchErrorLogFromUcs.class);
	
	private FetchErrorLogFromUcs() {		
	}
	
	public static Map<String, String> returnLogValue(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			UcsHandle handle, String ipaddress){

		Map<String, String> output = new HashMap<String, String>();
		try {
			ClassIdSet arg2 = new ClassIdSet();

			ClassId id = new ClassId();
			id.setValue(NamingClassId.FSM_STATUS);

			id = new ClassId();
			id.setValue(NamingClassId.MGMT_IMPORTER_FSM);

			id = new ClassId();
			id.setValue(NamingClassId.MGMT_IMPORTER);

			arg2.addChild(id);

			id = new ClassId();
			id.setValue(NamingClassId.MGMT_IMPORTER);

			arg2.addChild(id);

			ConfigResolveClasses mgmtImporter1 = CustomMethodFactory
					.getConfigResolveClasses(handle, YesOrNo.FALSE, arg2);

			ConfigSet configConfig = mgmtImporter1.getOutConfigs();

			List<UcsBase> configConfigchild = (List<UcsBase>) configConfig
					.getChild();
			if(configConfigchild.size() > 0){
				MgmtImporter mgmtImporterfinal = (MgmtImporter) configConfigchild
						.get(0);
				
				output.put("Status: fsmRmtInvErrCode", "Status: fsmRmtInvErrCode: "
						+ (String) mgmtImporterfinal.getattr("fsmRmtInvErrCode"));
				output.put("Status: fsmRmtInvErrDescr", "Status: fsmRmtInvErrDescr: "
						+ (String) mgmtImporterfinal.getattr("fsmRmtInvErrDescr"));
				return output;
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured in returnLogValue method.", e);			
		}		
		return null;
	}

	public static List<UcsServerLogs> fetchFsmStepSequenceStatus(Integer projectId,
			UcsHandle handle, String ipaddress) {
		
		LOGGER.info("Begin -FetchErrorLogFromUcs -  fetchFsmStepSequenceStatus()");
		List<UcsServerLogs> serverLogsList = new ArrayList<UcsServerLogs>();
		
		try {
			ClassId id = new ClassId();
			ClassIdSet arg3 = new ClassIdSet();
			id.setValue(NamingClassId.MGMT_IMPORTER_FSM_STAGE);

			arg3.addChild(id);

			ConfigResolveClasses fsmstage = CustomMethodFactory
					.getConfigResolveClasses(handle, YesOrNo.FALSE, arg3);

			if (fsmstage != null) {
				ConfigSet configConfig = fsmstage.getOutConfigs();

				List<UcsBase> fsmstagechild = (List<UcsBase>) configConfig
						.getChild();
				int startValue = 0;

				if (fsmstagechild != null && !fsmstagechild.isEmpty()) {
					startValue = fsmstagechild.size() - 3;
				
					for (; startValue < fsmstagechild.size(); startValue++) {
		
						MgmtImporterFsmStage mgmtImporterFsmStage = (MgmtImporterFsmStage) fsmstagechild
								.get(startValue);
		
						serverLogsList.add(constructUcsServerLogsObject(projectId,
								ipaddress,
								(String) mgmtImporterFsmStage.getattr("name"),
								(String) mgmtImporterFsmStage.getattr("descr"),
								(String) mgmtImporterFsmStage.getattr("stageStatus")));
					}
				}

				Map<String, String> output = returnLogValue(projectId, handle,
						ipaddress);

				if(output != null){
					serverLogsList.add(constructUcsServerLogsObject(projectId,
							ipaddress, output.get("Status: fsmRmtInvErrCode"), "", ""));
					serverLogsList
					.add(constructUcsServerLogsObject(projectId, ipaddress,
							output.get("Status: fsmRmtInvErrDescr"), "", ""));
					return serverLogsList;
				} else {
					return null;
				}
				
			}
		} catch(ResponseException ex) {
			LOGGER.info("ResponseException in fetchFsmStepSequenceStatus.",ex);
		} catch(Exception ex) {
			LOGGER.info("Exception in fetchFsmStepSequenceStatus.",ex);
		}
		LOGGER.info("END -FetchErrorLogFromUcs -  fetchFsmStepSequenceStatus()");
		return serverLogsList;
	}

	public static UcsServerLogs constructUcsServerLogsObject(Integer projectId,
			String ipaddress, String name, String desc, String status) {
		UcsServerLogs obj = new UcsServerLogs();

		ProjectDetails pd = new ProjectDetails();
		if (projectId != null) {
			pd.setId(projectId);
		}
		obj.setProjectDetails(pd);
		obj.setIpAddress(ipaddress);
		obj.setName(name);
		obj.setDescription(desc);
		obj.setStatus(status);

		return obj;
	}
}
