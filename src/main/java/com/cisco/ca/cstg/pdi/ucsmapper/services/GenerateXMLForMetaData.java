package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.AdminBackupConfiguration;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMap;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapLocalesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapRolesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocalesOrgMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRole;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRolesPrivilegesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.Configuration;
import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.EquipmentFcport;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.InfrastructureEthernetFCMode;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapGroup;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaLocale;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaOrg;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaRole;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaUserLocale;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaUserRole;
import com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapLocale;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentChassis;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentMiniScalabilityPorts;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServers;
import com.cisco.ca.cstg.pdi.pojos.mo.FCPorts;
import com.cisco.ca.cstg.pdi.pojos.mo.Metadata;
import com.cisco.ca.cstg.pdi.pojos.mo.Mgmtbackuppolicy;
import com.cisco.ca.cstg.pdi.pojos.mo.SanVHBA;
import com.cisco.ca.cstg.pdi.pojos.mo.UcsDetails;
import com.cisco.ca.cstg.pdi.pojos.mo.Vhba;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;
import com.cisco.ca.cstg.pdi.utils.Util;

@Service("generateXMLForMetaData")
public class GenerateXMLForMetaData extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenerateXMLForMetaData.class);

	public GenerateXMLForMetaData() {
	}

	public boolean writeProjectMetaData(String pdiConfDataFolderPath,
			XmlGenProjectDetails project) throws IOException, JAXBException {
		LOGGER.info("Start : GenerateXMLForMetaData : writeProjectMetaData: "
				+ project);

		StringBuilder returnPath = new StringBuilder(pdiConfDataFolderPath);
		returnPath.append(File.separator).append(
				Constants.PDI_META_DATA_FILE_NAME);
		String filePath = returnPath.toString();
		boolean fileWritten = true;

		Metadata metadata = new Metadata();

		metadata.setProjectName(project.getProjectName());
		metadata.setVersion(Float.parseFloat(PdiConfig
				.getProperty("metadata.version")));
		addInfraDetails(metadata, project);
		addMgmtBackupPolicy(metadata, project);
		addConfigurationDetails(metadata, project);
		addvHBA(metadata, project);
		addChassisDiscPolicy(metadata, project);

		updateFCPorts(metadata, project);
		addEquipmentServer(metadata, project);
		addLdapProvider(metadata, project);
		addRadiusProvider(metadata, project);
		addTacacsProvider(metadata, project);
		addLdapRole(metadata, project);
		addLdapLocale(metadata, project);
		addLdapGroupMap(metadata, project);
		addEquipmentMiniScalabilityPorts(metadata, project);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(metadata, new File(filePath));

		} catch (JAXBException e) {
			LOGGER.error("Eror occured while creating metadata xml ", e);
			fileWritten = false;
			throw e;
		}
		LOGGER.info("End : GenerateXMLForMetaData : writeProjectMetaData: "
				+ project);
		return fileWritten;
	}

	private void addEquipmentServer(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addEquipmentServer: "
				+ project);
		List<com.cisco.ca.cstg.pdi.pojos.EquipmentServer> serverportlist = project
				.getEquipmentServers();

		if (Util.listNotNull(serverportlist)) {
			EquipmentServers equipmentServers = new EquipmentServers();
			List<EquipmentServer> serverList = new ArrayList<>();
			for (Object obj : serverportlist) {
				com.cisco.ca.cstg.pdi.pojos.EquipmentServer equipmentServer = (com.cisco.ca.cstg.pdi.pojos.EquipmentServer) obj;
				EquipmentServer equipmentServerMO = new EquipmentServer();
				equipmentServerMO.setFiId(equipmentServer.getFiId());
				equipmentServerMO.setPortId(equipmentServer.getPortId());
				equipmentServerMO.setSlotId(equipmentServer.getSlotId());
				equipmentServerMO.setUsrLbl(equipmentServer.getUserLabel());
				equipmentServerMO.setChassisId(equipmentServer.getChassis());
				serverList.add(equipmentServerMO);
			}
			equipmentServers.setEquipmentServer(serverList);
			metadata.setEquipmentServers(equipmentServers);
		}
		LOGGER.info("End : GenerateXMLForMetaData : addEquipmentServer ");
	}

	private void updateFCPorts(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : updateFCPorts: "
				+ project);
		List<EquipmentFcport> fcPortList = project.getEquipmentFcports();
		FCPorts fcPorts = new FCPorts();
		fcPorts.setAvailable(false);

		if (Util.listNotNull(fcPortList)) {
			fcPorts.setAvailable(true);
		}
		metadata.setFcPorts(fcPorts);
		LOGGER.info("End : GenerateXMLForMetaData : addConfigurationDetails");
	}

	private void addConfigurationDetails(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addConfigurationDetails: "
				+ project);
		List<Configuration> configList = project.getConfigurations();

		if (Util.listNotNull(configList)) {
			Configuration config = configList.get(0);
			UcsDetails ucsDetails = new UcsDetails();
			ucsDetails.setHostName(config.getIpAddress());
			ucsDetails.setUsername(config.getUserName());
			ucsDetails.setPassword(config.getPassword());
			ucsDetails.setPingStatus(config.getPingVerified() == null ? "null"
					: config.getPingVerified().toString());
			ucsDetails.setPushStatus(config.getPushStatus() == null ? "null"
					: config.getPushStatus().toString());
			metadata.setUcsDetails(ucsDetails);
		}
		LOGGER.info("End : GenerateXMLForMetaData : addConfigurationDetails: "
				+ project);
	}

	private void addInfraDetails(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addInfraDetails: "
				+ project);
		List<Infrastructure> infrastructureList = project.getInfrastructures();

		if (Util.listNotNull(infrastructureList)) {
			Infrastructure infrastructure = infrastructureList.get(0);
			com.cisco.ca.cstg.pdi.pojos.mo.Infrastructure infraStructureMO = new com.cisco.ca.cstg.pdi.pojos.mo.Infrastructure();
			infraStructureMO.setFabricInterconnectModel(infrastructure
					.getServerModel().getDescription());
			infraStructureMO.setSoftwareVersion(infrastructure
					.getSoftwareVersion());
			infraStructureMO.setIomModel(infrastructure.getIoModule());

			List<InfrastructureEthernetFCMode> infrastructureEthernetFCModeList = project
					.getInfrastructureEthernetFCMode();

			if (Util.listNotNull(infrastructureEthernetFCModeList)) {
				InfrastructureEthernetFCMode infrastructureEthernetFCMode = infrastructureEthernetFCModeList
						.get(0);
				infraStructureMO.setEthernetMode(infrastructureEthernetFCMode
						.getEthernetMode());
				infraStructureMO.setFcMode(infrastructureEthernetFCMode
						.getFcMode());
			}
			metadata.setInfrastructure(infraStructureMO);
			LOGGER.info("Added Infrastructure element to metadata xml");
		}

		LOGGER.info("End : GenerateXMLForMetaData : addInfraDetails: "
				+ project);
	}

	private void addvHBA(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addvHBA: " + project);
		List<SanVhba> sanVhbaList = project.getSanVhbas();

		if (Util.listNotNull(sanVhbaList)) {
			SanVHBA sanVhba = new SanVHBA();
			List<Vhba> vhbaList = new ArrayList<>();

			for (SanVhba sanV : sanVhbaList) {
				Vhba vhba = new Vhba();
				vhba.setName(sanV.getName());
				vhba.setAdapterPolicy((sanV.getSanAdapterPolicies() != null) ? sanV
						.getSanAdapterPolicies().getName() : "");
				vhba.setVhbaTemplate(sanV.getSanVhbaTemplate() != null ? sanV
						.getSanVhbaTemplate().getVhbaName() : "");
				vhbaList.add(vhba);
				LOGGER.info("Added SanVhba element to metadata xml");
			}
			sanVhba.setVhba(vhbaList);
			metadata.setSanVHBA(sanVhba);
		}
		LOGGER.info("End : GenerateXMLForMetaData : addvHBA: " + project);
	}

	private void addChassisDiscPolicy(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addChassisDiscPolicy: "
				+ project);
		List<EquipmentChasis> equipChassisList = project.getEquipmentChasises();

		if (Util.listNotNull(equipChassisList)) {
			for (EquipmentChasis equipmentChasis : equipChassisList) {
				EquipmentChassis equipmentChasisMo = new EquipmentChassis();
				equipmentChasisMo.setCdpAction(equipmentChasis.getCdpAction());
				equipmentChasisMo
						.setCdpLinkAgg(equipmentChasis.getCdpLinkAgg());
				metadata.setEquipmentChassis(equipmentChasisMo);
				LOGGER.info("Added SanVhba element to metadata xml");
			}
		}
		LOGGER.info("End : GenerateXMLForMetaData : addChassisDiscPolicy: "
				+ project);
	}

	private void addLdapProvider(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addLdapProvider: "
				+ project);
		List<AdminLdapProvider> ldapProviderList = project
				.getAdminLdapProviders();

		if (Util.listNotNull(ldapProviderList)) {
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapProvider adminLdapProviderMO = new com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapProvider();
			List<AaaLdapProvider> providerList = new ArrayList<>();

			for (AdminLdapProvider alp : ldapProviderList) {
				AaaLdapProvider aaaLdapProvider = new AaaLdapProvider();
				aaaLdapProvider.setEncKey(alp.getEncryptedPassword());
				aaaLdapProvider.setFilter(alp.getFilter());
				aaaLdapProvider.setName(alp.getHostname());
				providerList.add(aaaLdapProvider);
			}
			adminLdapProviderMO.setAaaLdapProvider(providerList);
			metadata.setAdminLdapProvider(adminLdapProviderMO);
		}

		LOGGER.info("End : GenerateXMLForMetaData : addLdapProvider");
	}

	private void addTacacsProvider(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addTacacsProvider: "
				+ project);
		List<AdminTacacsProvider> ldapProviderList = project
				.getAdminTacacsProviders();

		if (Util.listNotNull(ldapProviderList)) {
			com.cisco.ca.cstg.pdi.pojos.mo.AdminTacacsProvider adminTacacsProviderMO = new com.cisco.ca.cstg.pdi.pojos.mo.AdminTacacsProvider();
			List<AaaTacacsProvider> providerList = new ArrayList<>();

			for (AdminTacacsProvider atp : ldapProviderList) {
				AaaTacacsProvider aaaTacacsProvider = new AaaTacacsProvider();
				aaaTacacsProvider.setEncKey(atp.getEncryptedPassword());
				aaaTacacsProvider.setName(atp.getHostname());
				providerList.add(aaaTacacsProvider);
			}
			adminTacacsProviderMO.setAaaTacacsProvider(providerList);
			metadata.setAdminTacacsProvider(adminTacacsProviderMO);
		}

		LOGGER.info("End : GenerateXMLForMetaData : addTacacsProvider");
	}

	private void addRadiusProvider(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addRadiusProvider: "
				+ project);
		List<AdminRadiusProvider> radiusProviderList = project
				.getAdminRadiusProviders();

		if (Util.listNotNull(radiusProviderList)) {
			com.cisco.ca.cstg.pdi.pojos.mo.AdminRadiusProvider adminRadiusProviderMO = new com.cisco.ca.cstg.pdi.pojos.mo.AdminRadiusProvider();
			List<AaaRadiusProvider> providerList = new ArrayList<>();

			for (AdminRadiusProvider arp : radiusProviderList) {
				AaaRadiusProvider aaaRadiusProvider = new AaaRadiusProvider();
				aaaRadiusProvider.setEncKey(arp.getEncKey());
				aaaRadiusProvider.setName(arp.getHostname());
				providerList.add(aaaRadiusProvider);
			}
			adminRadiusProviderMO.setAaaRadiusProvider(providerList);
			metadata.setAdminRadiusProvider(adminRadiusProviderMO);
		}

		LOGGER.info("End : GenerateXMLForMetaData : addRadiusProvider");
	}

	private void addLdapRole(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addLdapRole: " + project);
		List<AdminLdapRole> ldapRoleList = project.getAdminLdapRoles();

		if (Util.listNotNull(ldapRoleList)) {
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapRole adminLdapRoleMO = new com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapRole();
			List<AaaRole> providerList = new ArrayList<>();

			for (AdminLdapRole alr : ldapRoleList) {
				AaaRole aaaRole = new AaaRole();
				aaaRole.setDescr(alr.getName());
				aaaRole.setName(alr.getName());
				List<AdminLdapRolesPrivilegesMapping> adminLdapRolesPrivileges = alr
						.getAdminLdapRolesPrivilegesMappings();

				if (Util.listNotNull(adminLdapRolesPrivileges)) {
					List<String> privList = new ArrayList<String>();

					for (AdminLdapRolesPrivilegesMapping adminLdapRolesPrivilegesMapping : adminLdapRolesPrivileges) {
						AdminPrivilege adminPrivilege = adminLdapRolesPrivilegesMapping
								.getAdminPrivilege();

						if (adminPrivilege != null) {
							privList.add(adminPrivilege.getName());
						}
					}
					aaaRole.setPriv(privList.toArray(new String[privList.size()]));
				}
				providerList.add(aaaRole);
			}
			adminLdapRoleMO.setAaaRole(providerList);
			metadata.setAdminLdapRole(adminLdapRoleMO);
		}

		LOGGER.info("End : GenerateXMLForMetaData : addLdapRole");
	}

	private void addLdapLocale(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addLdapLocale: "
				+ project);
		List<com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale> ldapLocaleList = project
				.getAdminLdapLocales();

		if (ldapLocaleList != null && !ldapLocaleList.isEmpty()) {
			AdminLdapLocale adminLdapLocale = new AdminLdapLocale();
			List<AaaLocale> aaaLocaleList = new ArrayList<>();

			for (Object obj : ldapLocaleList) {
				com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale alp = (com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale) obj;
				AaaLocale aaaLocale = new AaaLocale();
				aaaLocale.setName(alp.getName());
				List<AdminLdapLocalesOrgMapping> allomList = alp
						.getAdminLdapLocalesOrgMappings();

				if (Util.listNotNull(allomList)) {
					List<AaaOrg> aaaOrgList = new ArrayList<>();

					for (AdminLdapLocalesOrgMapping allom : allomList) {
						String globalOrgDn = "";
						AaaOrg aaaOrg = new AaaOrg();
						aaaOrg.setName(allom.getOrganizations() != null ? allom
								.getOrganizations().getName() : "");
						String orgdn = orginizationDn(allom.getOrganizations(),
								globalOrgDn);
						aaaOrg.setOrgDn(orgdn);
						aaaOrgList.add(aaaOrg);
					}
					aaaLocale.setAaaOrgs(aaaOrgList);
				}
				aaaLocaleList.add(aaaLocale);
			}
			adminLdapLocale.setAaaLocale(aaaLocaleList);
			metadata.setAdminLdapLocale(adminLdapLocale);
		}
		LOGGER.info("End : GenerateXMLForMetaData : addLdapLocale");
	}

	private String orginizationDn(Organizations org, String globalOrgDn) {
		LOGGER.info("Start : GenerateXMLForMetaData : orginizationDn ");
		String returnGlobalOrgDn = null;
		if (org != null) {
			Integer parentOrgId = org.getParentId();
			if (parentOrgId != null && parentOrgId > 1) {
				String newGlobalOrgDn = "/org-" + org.getName() + globalOrgDn;
				Organizations parentOrg = (Organizations) findById(
						Organizations.class, parentOrgId);
				returnGlobalOrgDn = orginizationDn(parentOrg, newGlobalOrgDn);
			} else {
				returnGlobalOrgDn = "org-" + org.getName() + globalOrgDn;
			}
		}
		LOGGER.info("End : GenerateXMLForMetaData : orginizationDn ");
		return returnGlobalOrgDn;
	}

	private void addLdapGroupMap(Metadata metadata, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addLdapGroupMap: "
				+ project);
		List<AdminLdapGroupMap> algmList = project.getAdminLdapGroupMaps();

		if (Util.listNotNull(algmList)) {
			com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapGroupMap adminLdapGroupMap = new com.cisco.ca.cstg.pdi.pojos.mo.AdminLdapGroupMap();
			List<AaaLdapGroup> aaaLdapGroupList = new ArrayList<>();

			for (AdminLdapGroupMap algm : algmList) {
				AaaLdapGroup aaaLdapGroup = new AaaLdapGroup();
				aaaLdapGroup.setName(algm.getName());
				addGroupMapRoles(aaaLdapGroup, algm);
				addGroupMapLocales(aaaLdapGroup, algm);
				aaaLdapGroupList.add(aaaLdapGroup);
			}
			adminLdapGroupMap.setAaaLdapGroup(aaaLdapGroupList);
			metadata.setAdminLdapGroupMap(adminLdapGroupMap);
		}
		LOGGER.info("End : GenerateXMLForMetaData : addLdapGroupMap ");
	}

	private void addGroupMapRoles(AaaLdapGroup aaaLdapGroup,
			AdminLdapGroupMap algm) {
		LOGGER.info("Start : GenerateXMLForMetaData : addGroupMapRoles :- GroupMap Id: "
				+ algm);
		List<AdminLdapGroupMapRolesMapping> aaaLdapGroupMapRolesList = algm
				.getAdminLdapGroupMapRolesMappings();
		List<AaaUserRole> aaaUserRoleList = new ArrayList<>();

		if (Util.listNotNull(aaaLdapGroupMapRolesList)) {

			for (AdminLdapGroupMapRolesMapping adminLdapGroupMapRolesMapping : aaaLdapGroupMapRolesList) {
				AaaUserRole aaaUserRole = new AaaUserRole();
				String name = adminLdapGroupMapRolesMapping.getAdminLdapRole()
						.getName();
				aaaUserRole.setName(name);
				aaaUserRoleList.add(aaaUserRole);
			}
		}
		aaaLdapGroup.setAaaUserRoles(aaaUserRoleList);
		LOGGER.info("End : GenerateXMLForMetaData : addGroupMapRoles. Added AdminLdapGroupMapRolesMapping - AaaUserRole");
	}

	private void addGroupMapLocales(AaaLdapGroup aaaLdapGroup,
			AdminLdapGroupMap algm) {
		LOGGER.info("Start : GenerateXMLForMetaData : addGroupMapLocales :- GroupMap Id: "
				+ algm);
		List<AdminLdapGroupMapLocalesMapping> aaaLdapGroupMapLocalesList = algm
				.getAdminLdapGroupMapLocalesMappings();
		List<AaaUserLocale> aaaUserLocaleList = new ArrayList<>();

		if (Util.listNotNull(aaaLdapGroupMapLocalesList)) {

			for (AdminLdapGroupMapLocalesMapping adminLdapGroupMapLocalesMapping : aaaLdapGroupMapLocalesList) {
				AaaUserLocale aaaUserLocale = new AaaUserLocale();
				String name = adminLdapGroupMapLocalesMapping
						.getAdminLdapLocale().getName();
				aaaUserLocale.setName(name);
				aaaUserLocaleList.add(aaaUserLocale);
			}
		}
		aaaLdapGroup.setAaaUserLocales(aaaUserLocaleList);
		LOGGER.info("End : GenerateXMLForMetaData : addGroupMapLocales. Added AdminLdapGroupMapLocalesMapping - AaaUserLocale ");
	}

	private void addMgmtBackupPolicy(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addMgmtBackupPolicy: "
				+ project);
		List<AdminBackupConfiguration> mgmtBackupPolicyList = project
				.getAdminBackupConfigurations();

		if (Util.listNotNull(mgmtBackupPolicyList)) {
			Mgmtbackuppolicy mgmtbackuppolicyMo = new Mgmtbackuppolicy();

			AdminBackupConfiguration conf = mgmtBackupPolicyList.get(0);

			mgmtbackuppolicyMo.setAdminState(conf.getAdminState());
			mgmtbackuppolicyMo.setDescr("Database Backup Policy");
			mgmtbackuppolicyMo.setHost(conf.getHostname());
			mgmtbackuppolicyMo.setName("default");
			mgmtbackuppolicyMo.setProto(conf.getProtocol());
			mgmtbackuppolicyMo.setPwd(conf.getEncPassword());
			mgmtbackuppolicyMo.setRemoteFile(conf.getRemoteFile());
			mgmtbackuppolicyMo.setSchedule("1day");
			mgmtbackuppolicyMo.setStatus(conf.getBackupStatus());
			mgmtbackuppolicyMo.setUser(conf.getUserName());
			mgmtbackuppolicyMo.setBackupType(conf.getBackupType());
			mgmtbackuppolicyMo.setPreserveIdentities(conf
					.getPreserveIdentities());

			metadata.setMgmtBackupPolicy(mgmtbackuppolicyMo);
		}
		LOGGER.info("End : GenerateXMLForMetaData : addMgmtBackupPolicy ");
	}

	private void addEquipmentMiniScalabilityPorts(Metadata metadata,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForMetaData : addEquipmentMiniScalabilityPorts: "
				+ project);
		List<com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability> emsp = project
				.getEquipmentMiniScalabilityPorts();

		if (Util.listNotNull(emsp)) {
			EquipmentMiniScalabilityPorts sp = new EquipmentMiniScalabilityPorts();
			sp.setName("");
			sp.setSlotId("1");
			sp.setAggrPortId("5");
			List<EquipmentServer> serverList = new ArrayList<>();
			for (Object obj : emsp) {
				com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability scalabilityPort = (com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability) obj;
				if (scalabilityPort.getIsConfigured() == 1) {
					EquipmentServer equipmentServerMO = new EquipmentServer();
					equipmentServerMO.setFiId(scalabilityPort.getFiId());
					equipmentServerMO.setPortId(scalabilityPort.getPortId());
					equipmentServerMO.setSlotId(scalabilityPort.getSlotId());
					equipmentServerMO
							.setChassisId(scalabilityPort.getChassis());
					serverList.add(equipmentServerMO);
				}
			}
			if (serverList.size() > 0) {
				sp.setEquipmentServer(serverList);
				metadata.setEquipmentMiniScalabilityPorts(sp);
			}
		}
		LOGGER.info("End : GenerateXMLForMetaData : addEquipmentMiniScalabilityPorts ");
	}

}