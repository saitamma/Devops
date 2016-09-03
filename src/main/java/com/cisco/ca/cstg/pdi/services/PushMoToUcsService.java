package com.cisco.ca.cstg.pdi.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentChassis;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentMiniScalabilityPorts;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServers;
import com.cisco.ca.cstg.pdi.pojos.mo.Mgmtbackuppolicy;
import com.cisco.ucs.mgr.GenericMo;
import com.cisco.ucs.mgr.MethodFactory;
import com.cisco.ucs.mgr.UcsBase;
import com.cisco.ucs.mgr.UcsHandle;
import com.cisco.ucs.mgr.enums.YesOrNo;
import com.cisco.ucs.mgr.method.ConfigConfMos;
import com.cisco.ucs.mgr.method.helper.ConfigMap;
import com.cisco.ucs.mgr.method.helper.Pair;
import com.cisco.ucs.mgr.mo.AaaLdapGroup;
import com.cisco.ucs.mgr.mo.AaaLdapProvider;
import com.cisco.ucs.mgr.mo.AaaLocale;
import com.cisco.ucs.mgr.mo.AaaOrg;
import com.cisco.ucs.mgr.mo.AaaRadiusProvider;
import com.cisco.ucs.mgr.mo.AaaRole;
import com.cisco.ucs.mgr.mo.AaaTacacsPlusProvider;
import com.cisco.ucs.mgr.mo.AaaUserLocale;
import com.cisco.ucs.mgr.mo.AaaUserRole;
import com.cisco.ucs.mgr.mo.ComputeChassisDiscPolicy;
import com.cisco.ucs.mgr.mo.FabricDceSrv;
import com.cisco.ucs.mgr.mo.FabricDceSwSrv;
import com.cisco.ucs.mgr.mo.FabricDceSwSrvEp;
import com.cisco.ucs.mgr.mo.MgmtBackup;
import com.cisco.ucs.mgr.util.ValidationException;

@Service("pushMoToUcsService")
public class PushMoToUcsService extends CommonDaoServicesImpl {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PushMoToUcsService.class);
	private static final String LOCAL = "local";
	private static final String ENABLED = "enabled";

	public void invokeChassisDiscoverypolicy(UcsHandle handle,
			EquipmentChassis equipmentChassis) {
		LOGGER.info("Start : PushMoToUcsService : invokeChassisDiscoverypolicy");

		if (equipmentChassis != null) {
			ConfigMap configMap = new ConfigMap();
			ComputeChassisDiscPolicy chasspoli = new ComputeChassisDiscPolicy();
			String dn = "org-root/chassis-discovery";
			chasspoli.setDn(dn);
			chasspoli.setLinkAggregationPref(equipmentChassis.getCdpLinkAgg());
			chasspoli.setAction(equipmentChassis.getCdpAction() + "-link");
			chasspoli.setPolicyOwner(LOCAL);
			chasspoli.setRebalance("user-acknowledged");

			try {
				chasspoli.validateProperties();
			} catch (ValidationException e) {
				LOGGER.error(e.getMessage(), e);
			}

			addPairToConfigMap(chasspoli, dn, configMap);

			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.info("chassisDiscoverypOlicy failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : invokeChassisDiscoverypolicy");
	}

	public void invokeChassisConfiguraiotnByServerPorts(UcsHandle handle,
			EquipmentServers equipmentServers, EquipmentMiniScalabilityPorts equipmentMiniScalabilityPorts) {
		LOGGER.info("Start : PushMoToUcsService : invokeChassisConfiguraiotnByServerPorts");
		if (equipmentServers != null) {
			ConfigMap configMap = new ConfigMap();

			FabricDceSrv fabricDceSrv = new FabricDceSrv();
			fabricDceSrv.setDn("fabric/server");
			fabricDceSrv.setId("A");

			fabricDceSrv.addChild(addFabricDceSwSrv("A", equipmentMiniScalabilityPorts));  // For Fabric Interconnect A
			fabricDceSrv.addChild(addFabricDceSwSrv("B", equipmentMiniScalabilityPorts));  // For Fabric Interconnect B
			

			try {
				fabricDceSrv.validateProperties();
				addPairToConfigMap(fabricDceSrv, "fabric/server", configMap);

				long errorCode = sendReqGetErrorCode(handle, configMap);
				if (errorCode != 0) {
					LOGGER.info("fabricDceSrv failed while adding. ErrorCode :"	+ errorCode);
				}
			} catch (ValidationException e) {
				LOGGER.info("Validation failed for the specified properties.",e);
			}

			if (equipmentServers.getEquipmentServer() != null
					&& !equipmentServers.getEquipmentServer().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServer> serverportlist = equipmentServers.getEquipmentServer();
				for (com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServer es : serverportlist) {
					ConfigMap configMap1 = new ConfigMap();
					FabricDceSwSrvEp fabricDceSwSrvEp = new FabricDceSwSrvEp();					
					fabricDceSwSrvEp.setSlotId(es.getSlotId());
					fabricDceSwSrvEp.setPortId(es.getPortId());
					fabricDceSwSrvEp.setUsrLbl(es.getUsrLbl());
					fabricDceSwSrvEp.setAdminState(ENABLED);
					if ("A".equals(es.getFiId())) {
						fabricDceSwSrvEp.setDn("fabric/server/sw-A/slot-" + es.getSlotId() + "-port-"+ es.getPortId());
					} else {
						fabricDceSwSrvEp.setDn("fabric/server/sw-B/slot-" + es.getSlotId() + "-port-"+ es.getPortId());
					}					

					try {
						fabricDceSwSrvEp.validateProperties();
						addPairToConfigMap(fabricDceSwSrvEp,fabricDceSwSrvEp.getDn(),configMap1);

						long errorCode = sendReqGetErrorCode(handle, configMap1);
						if (errorCode != 0) {
							LOGGER.info("fabricDceSwSrvEp failed while adding SlotId: "	+ es.getSlotId() + ", PortId:" + es.getPortId()	+ ". ErrorCode :" + errorCode);
						}
						TimeUnit.SECONDS.sleep(10);
					} catch (ValidationException e) {
						LOGGER.info("Validation Exception failed for the specified properties.",e);
					} catch (InterruptedException e) {
						LOGGER.info("Interrupted Exception happened.", e);
					} catch (Exception e) {
						LOGGER.info("Exception.", e);
					}
				}
			}
			
		}
		LOGGER.info("End : PushMoToUcsService : invokeChassisConfiguraiotnByServerPorts");
	}
	
	private FabricDceSwSrv addFabricDceSwSrv(String fi, EquipmentMiniScalabilityPorts equipmentMiniScalabilityPorts) {
		FabricDceSwSrv fabricDceSwSrv = new FabricDceSwSrv();
		fabricDceSwSrv.setDn("fabric/server/sw-"+fi);
		fabricDceSwSrv.setId(fi);
		invokeScalabilityPortsConfiguration(fi, fabricDceSwSrv, equipmentMiniScalabilityPorts);
		return fabricDceSwSrv;
	}
	
	private void invokeScalabilityPortsConfiguration(String fi, FabricDceSwSrv fabricDceSwSrv, EquipmentMiniScalabilityPorts equipmentMiniScalabilityPorts) {
		LOGGER.info("Start : PushMoToUcsService : invokeScalabilityPortsConfiguration");
		if (equipmentMiniScalabilityPorts != null) {
			GenericMo fabricSubGroup = new GenericMo("fabricSubGroup");
			fabricSubGroup.setAttribute("name", "");
			fabricSubGroup.setAttribute("slotId", equipmentMiniScalabilityPorts.getSlotId());
			fabricSubGroup.setAttribute("aggrPortId", equipmentMiniScalabilityPorts.getAggrPortId());
			Boolean flag = false;
			List<EquipmentServer> equipmentServersList = equipmentMiniScalabilityPorts.getEquipmentServer();
			for (EquipmentServer es : equipmentServersList) {
				if(fi.equalsIgnoreCase(es.getFiId())){
					FabricDceSwSrvEp fabricDceSwSrvEp = new FabricDceSwSrvEp();
					fabricDceSwSrvEp.setSlotId(es.getSlotId());
					fabricDceSwSrvEp.setPortId(es.getPortId());
					fabricDceSwSrvEp.setUsrLbl(es.getUsrLbl());
					fabricDceSwSrvEp.setAdminState(ENABLED);
					fabricDceSwSrvEp.setDn("fabric/server/sw-"+es.getFiId()+"/slot-1-aggr-port-5/slot-" + es.getSlotId() + "-port-"+ es.getPortId());
					fabricSubGroup.addChild(fabricDceSwSrvEp);
					flag = true;
				}
			}
			if(flag){
				fabricSubGroup.setDn("fabric/server/sw-"+fi+"/slot-1-aggr-port-5");
				fabricDceSwSrv.addChild(fabricSubGroup);
			}
			
		}
		LOGGER.info("End : PushMoToUcsService : invokeScalabilityPortsConfiguration");
	}

	


	public void pushMgmtBackupInfo(UcsHandle handle,
			Mgmtbackuppolicy mgmtBackupJob) {
		LOGGER.info("Start : PushMoToUcsService : pushMgmtBackupInfo");
		if (mgmtBackupJob != null) {
			ConfigMap configMap = new ConfigMap();

			if (mgmtBackupJob.getPwd() != null
					&& !mgmtBackupJob.getPwd().isEmpty()) {

				MgmtBackup mgmtBackup = new MgmtBackup();
				String dn = "sys/backup-";
				mgmtBackup
						.setAdminState("enable".equalsIgnoreCase(mgmtBackupJob
								.getStatus()) ? MgmtBackup.ADMIN_STATE_ENABLED
								: MgmtBackup.ADMIN_STATE_DISABLED);
				mgmtBackup.setUser(mgmtBackupJob.getUser());
				mgmtBackup.setRemoteFile(mgmtBackupJob.getRemoteFile());
				mgmtBackup.setProto(mgmtBackupJob.getProto());
				mgmtBackup.setName(mgmtBackupJob.getName());
				mgmtBackup.setDescr(mgmtBackupJob.getDescr());
				mgmtBackup.setPolicyOwner(LOCAL);

				String backupType = mgmtBackupJob.getBackupType();
				boolean preservePooledValues = false;
				mgmtBackup.setType(backupType);
				if (backupType.equals(MgmtBackup.TYPE_FULL_STATE)
						|| backupType.equals(MgmtBackup.TYPE_CONFIG_SYSTEM)) {
					preservePooledValues = false;
				} else {
					preservePooledValues = "true"
							.equalsIgnoreCase(mgmtBackupJob
									.getPreserveIdentities()) ? true : false;
				}
				mgmtBackup
						.setPreservePooledValues(preservePooledValues ? MgmtBackup.PRESERVE_POOLED_VALUES_YES
								: MgmtBackup.PRESERVE_POOLED_VALUES_NO);

				mgmtBackup.setHostname(mgmtBackupJob.getHost());
				mgmtBackup.setPwd(mgmtBackupJob.getDecryptedPwd());
				mgmtBackup.setDn(dn + mgmtBackupJob.getHost());

				try {
					mgmtBackup.validateProperties();

				} catch (ValidationException e) {
					LOGGER.error("mgmtBackup validation failed.", e);
				}
				addPairToConfigMap(mgmtBackup, dn, configMap);
			}
			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.info("pushMgmtBackupInfo failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : pushMgmtBackupInfo");
	}

	public void updateLdapPwdField(UcsHandle handle,
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapProvider adminLdapProvider) {
		LOGGER.info("Start : PushMoToUcsService : updateLdapPwdField");
		if (adminLdapProvider != null) {
			ConfigMap configMap = new ConfigMap();

			if (adminLdapProvider.getAaaLdapProvider() != null
					&& !adminLdapProvider.getAaaLdapProvider().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapProvider> adminLdapProviderList = adminLdapProvider
						.getAaaLdapProvider();
				// Instantiate the required Managed Object and set its
				// properties.
				for (com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapProvider aaaLdapProviderMO : adminLdapProviderList) {

					if (aaaLdapProviderMO.getEncKey() != null
							&& aaaLdapProviderMO.getEncKey().length() > 0) {
						AaaLdapProvider aaaLdapProvider = new AaaLdapProvider();
						String dn = "sys/ldap-ext/provider-"
								+ aaaLdapProviderMO.getName();
						aaaLdapProvider.setDn(dn);
						aaaLdapProvider.setEncKey(aaaLdapProviderMO
								.getDecryptedKey());
						aaaLdapProvider.setName(aaaLdapProviderMO.getName());
						aaaLdapProvider
								.setFilter(aaaLdapProviderMO.getFilter());
						aaaLdapProvider.setRootdn("sys/ldap-ext");

						try {
							aaaLdapProvider.validateProperties();

						} catch (ValidationException e) {
							LOGGER.error("aaaLdapProvider validation failed.",
									e);
						}

						addPairToConfigMap(aaaLdapProvider, dn, configMap);
					}
				}
			}
			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.info("aaaLdapProvider failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : updateLdapPwdField");
	}

	public void updateTACACSKey(
			UcsHandle handle,
			com.cisco.ca.cstg.pdi.pojos.mo.AdminTacacsProvider adminTacacsProvider) {
		LOGGER.info("Start : PushMoToUcsService : updateTACACSKey ");
		if (adminTacacsProvider != null) {
			ConfigMap configMap = new ConfigMap();

			if (adminTacacsProvider.getAaaTacacsProvider() != null
					&& !adminTacacsProvider.getAaaTacacsProvider().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.AaaTacacsProvider> tacacsProviderList = adminTacacsProvider
						.getAaaTacacsProvider();
				for (com.cisco.ca.cstg.pdi.pojos.mo.AaaTacacsProvider aaaTacacsProviderMO : tacacsProviderList) {
					if (aaaTacacsProviderMO.getEncKey() != null
							&& aaaTacacsProviderMO.getEncKey().length() > 0) {
						AaaTacacsPlusProvider aaaTacacsPlusProvider = new AaaTacacsPlusProvider();
						String dn = "sys/tacacs-ext/provider-"
								+ aaaTacacsProviderMO.getName();
						aaaTacacsPlusProvider.setDn(dn);
						aaaTacacsPlusProvider.setRn("sys/tacacs-ext");
						aaaTacacsPlusProvider.setName(aaaTacacsProviderMO
								.getName());
						aaaTacacsPlusProvider.setEncKey(aaaTacacsProviderMO
								.getDecryptedKey());

						try {
							aaaTacacsPlusProvider.validateProperties();
						} catch (ValidationException e) {
							LOGGER.error(
									"aaaTacacsPlusProvider validation failed.",
									e);
						}
						addPairToConfigMap(aaaTacacsPlusProvider, dn, configMap);
					}
				}
			}

			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.info("aaaTacacsPlusProvider failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : updateTACACSKey");
	}

	public void updateRADIUSKey(
			UcsHandle handle,
			com.cisco.ca.cstg.pdi.pojos.mo.AdminRadiusProvider adminRadiusProvider) {
		LOGGER.info("Start : PushMoToUcsService : updateRADIUSKey ");
		if (adminRadiusProvider != null) {
			ConfigMap configMap = new ConfigMap();

			if (adminRadiusProvider.getAaaRadiusProvider() != null
					&& !adminRadiusProvider.getAaaRadiusProvider().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.AaaRadiusProvider> radiusProviderList = adminRadiusProvider
						.getAaaRadiusProvider();
				for (com.cisco.ca.cstg.pdi.pojos.mo.AaaRadiusProvider aaaRadiusProviderMO : radiusProviderList) {
					if (aaaRadiusProviderMO.getEncKey() != null
							&& aaaRadiusProviderMO.getEncKey().length() > 0) {
						AaaRadiusProvider aaaRadiusProvider = new AaaRadiusProvider();
						String dn = "sys/radius-ext/provider-"
								+ aaaRadiusProviderMO.getName();
						aaaRadiusProvider.setDn(dn);
						aaaRadiusProvider.setRn("sys/radius-ext");
						aaaRadiusProvider
								.setName(aaaRadiusProviderMO.getName());
						aaaRadiusProvider.setEncKey(aaaRadiusProviderMO
								.getDecryptedKey());

						try {
							aaaRadiusProvider.validateProperties();

						} catch (ValidationException e) {
							LOGGER.error(
									"aaaRadiusProvider validation failed.", e);
						}
						addPairToConfigMap(aaaRadiusProvider, dn, configMap);
					}
				}
			}
			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.info("aaaLdapProvider failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : updateRADIUSKey ");
	}

	public void updateAdminLdapRole(UcsHandle handle,
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapRole adminLdapRole) {
		LOGGER.info("Start : PushMoToUcsService : updateAdminLdapRole ");
		if (adminLdapRole != null) {
			ConfigMap roleMap = new ConfigMap();
			String dn = "";
			if (adminLdapRole.getAaaRole() != null
					&& !adminLdapRole.getAaaRole().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.AaaRole> ldapRoleList = adminLdapRole
						.getAaaRole();
				for (com.cisco.ca.cstg.pdi.pojos.mo.AaaRole aaaRole : ldapRoleList) {
					AaaRole aaaRoleMO = new AaaRole();
					dn = "sys/user-ext/role-" + aaaRole.getName();
					aaaRoleMO.setDn(dn);
					aaaRoleMO.setRn("sys/user-ext");
					aaaRoleMO.setName(aaaRole.getName());
					aaaRoleMO.setDescr(aaaRole.getName());
					aaaRoleMO.setPolicyOwner(LOCAL);
					aaaRoleMO.setPriv(aaaRole.getPriv());

					try {
						aaaRoleMO.validateProperties();

					} catch (ValidationException e) {
						LOGGER.error("aaaRoleMO validation failed.", e);
					}
					addPairToConfigMap(aaaRoleMO, dn, roleMap);
				}
			}
			long errorCode = sendReqGetErrorCode(handle, roleMap);
			if (errorCode != 0) {
				LOGGER.info("aaaLdapRole failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : updateAdminLdapRole ");
	}

	public void updateAdminLocales(UcsHandle handle,
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapLocale adminLdapLocale) {
		LOGGER.info("Start : PushMoToUcsService : updateAdminLocales ");
		if (adminLdapLocale != null) {
			ConfigMap configMap = new ConfigMap();
			if (adminLdapLocale.getAaaLocale() != null
					&& !adminLdapLocale.getAaaLocale().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.AaaLocale> localesList = adminLdapLocale
						.getAaaLocale();
				for (com.cisco.ca.cstg.pdi.pojos.mo.AaaLocale aaaLocaleMO : localesList) {
					AaaLocale aaaLocale = new AaaLocale();
					String dn = "sys/user-ext/locale-" + aaaLocaleMO.getName();
					aaaLocale.setDn(dn);
					aaaLocale.setRn("sys/user-ext");
					aaaLocale.setName(aaaLocaleMO.getName());
					aaaLocale.setPolicyOwner(LOCAL);

					List<com.cisco.ca.cstg.pdi.pojos.mo.AaaOrg> orgsList = aaaLocaleMO
							.getAaaOrgs();
					for (com.cisco.ca.cstg.pdi.pojos.mo.AaaOrg aaaOrgMO : orgsList) {
						AaaOrg aaaOrg = new AaaOrg();
						aaaOrg.setOrgDn(aaaOrgMO.getOrgDn());
						aaaOrg.setName(aaaOrgMO.getName());
						aaaLocale.addChild(aaaOrg);
					}

					try {
						aaaLocale.validateProperties();

					} catch (ValidationException e) {
						LOGGER.error("aaaLocale validation failed.", e);
					}
					addPairToConfigMap(aaaLocale, dn, configMap);
				}
			}
			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.info("AaaLocale failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : updateAdminLocales: ");
	}

	public void pushMoForLdapGroup(UcsHandle handle,
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapGroupMap adminLdapGroupMap) {
		LOGGER.info("Start : PushMoToUcsService : pushMoForLdapGroup ");
		if (adminLdapGroupMap != null) {
			ConfigMap configMap = new ConfigMap();
			String dn = "sys/ldap-ext/ldapgroup-";

			if (adminLdapGroupMap.getAaaLdapGroup() != null
					&& !adminLdapGroupMap.getAaaLdapGroup().isEmpty()) {
				List<com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapGroup> aaaLdapGroupList = adminLdapGroupMap
						.getAaaLdapGroup();
				for (com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapGroup aaaLdapGroupMO : aaaLdapGroupList) {
					AaaLdapGroup aaaLdapGroup = new AaaLdapGroup();
					aaaLdapGroup.setName(aaaLdapGroupMO.getName());
					StringBuilder dnStr = new StringBuilder(dn)
							.append(aaaLdapGroupMO.getName());
					aaaLdapGroup.setDn(dnStr.toString());

					addGroupMapRoles(aaaLdapGroup, aaaLdapGroupMO);
					addGroupMapLocales(aaaLdapGroup, aaaLdapGroupMO);

					try {
						aaaLdapGroup.validateProperties();
					} catch (ValidationException e) {
						LOGGER.error("aaaLdapGroup validation failed.", e);
					}
					addPairToConfigMap(aaaLdapGroup, dnStr.toString(),
							configMap);
				}
			}
			long errorCode = sendReqGetErrorCode(handle, configMap);
			if (errorCode != 0) {
				LOGGER.error("aaaLdapGroup failed -- ccms.getErrorCode() :"
						+ errorCode);
			}
		}
		LOGGER.info("End : PushMoToUcsService : pushMoForLdapGroup : "
				+ adminLdapGroupMap);
	}

	private void addGroupMapRoles(AaaLdapGroup aaaLdapGroup,
			com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapGroup aaaLdapGroupMO) {
		LOGGER.info("Start : PushMoToUcsService : addGroupMapRoles ");
		if (aaaLdapGroupMO != null) {
			List<com.cisco.ca.cstg.pdi.pojos.mo.AaaUserRole> aaaLdapGroupMapRolesList = aaaLdapGroupMO
					.getAaaUserRoles();
			String locale = "locale-";
			for (com.cisco.ca.cstg.pdi.pojos.mo.AaaUserRole groupRole : aaaLdapGroupMapRolesList) {
				AaaUserRole aaaUserRole = new AaaUserRole();
				String name = groupRole.getName();
				aaaUserRole.setName(name);
				StringBuilder localeStr = new StringBuilder(locale)
						.append(name);
				aaaUserRole.setDescr("");
				aaaUserRole.setDn(localeStr.toString());
				aaaLdapGroup.addChild(aaaUserRole);
			}
		}
		LOGGER.info("End : PushMoToUcsService : addGroupMapRoles. Added AdminLdapGroupMapRolesMapping - AaaUserRole");
	}

	private void addGroupMapLocales(AaaLdapGroup aaaLdapGroup,
			com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapGroup aaaLdapGroupMO) {
		LOGGER.info("Start : PushMoToUcsService : addGroupMapLocales ");
		if (aaaLdapGroupMO != null) {
			List<com.cisco.ca.cstg.pdi.pojos.mo.AaaUserLocale> aaaLdapGroupMapLocalesList = aaaLdapGroupMO
					.getAaaUserLocales();
			String role = "role-";
			for (com.cisco.ca.cstg.pdi.pojos.mo.AaaUserLocale groupLocale : aaaLdapGroupMapLocalesList) {
				AaaUserLocale aaaUserLocale = new AaaUserLocale();
				String name = groupLocale.getName();
				aaaUserLocale.setName(name);
				StringBuilder roleStr = new StringBuilder(role).append(name);
				aaaUserLocale.setDescr("");
				aaaUserLocale.setDn(roleStr.toString());

				aaaLdapGroup.addChild(aaaUserLocale);
			}
		}
		LOGGER.info("End : PushMoToUcsService : addGroupMapLocales. Added AdminLdapGroupMapLocalesMapping - AaaUserLocale ");
	}

	private void addPairToConfigMap(UcsBase obj, String dn, ConfigMap configMap) {
		LOGGER.info("Start : PushMoToUcsService : addPairToConfigMap");
		Pair lsPair = new Pair();
		lsPair.setKey(dn);
		lsPair.addChild(obj);
		configMap.addChild(lsPair);
		LOGGER.info("End : PushMoToUcsService : addPairToConfigMap");
	}

	private long sendReqGetErrorCode(UcsHandle handle, ConfigMap configMap) {
		ConfigConfMos ccms = MethodFactory.getConfigConfMos(handle, configMap,
				YesOrNo.FALSE);
		return ccms.getErrorCode();
	}
}
