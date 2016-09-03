package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.AdminBackupConfiguration;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMap;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapLocalesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapRolesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocalesOrgMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRole;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRolesPrivilegesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapGroup;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaLocale;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaOrg;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaRole;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaUserLocale;
import com.cisco.ca.cstg.pdi.pojos.mo.AaaUserRole;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentChassis;
import com.cisco.ca.cstg.pdi.pojos.mo.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.mo.Metadata;
import com.cisco.ca.cstg.pdi.pojos.mo.Mgmtbackuppolicy;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.Constants;

@Service("unmarshalMetaData")
public class UnmarshallMetaData extends CommonDaoServicesImpl {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnmarshallMetaData.class);

	@Autowired
	private EquipmentService equipmentService;

	public UnmarshallMetaData() {
	}

	public void readMetaData(String fileName, int newProjectId)
			throws IOException, JAXBException {
		LOGGER.info("Start : UnmarshallMetaData : readMetaData: newProjectId :-"
				+ newProjectId);
		ProjectDetails projectDetails = (ProjectDetails) findById(
				ProjectDetails.class, newProjectId);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Metadata metadata = (Metadata) jaxbUnmarshaller.unmarshal(new File(
					fileName));

			readEquipmentChassisInfo(projectDetails, metadata);
			readInfrastructureInfo(projectDetails, metadata);

			LOGGER.info("Read metadata xml. Saved to DB");
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.info("End : UnmarshallMetaData : readMetaData: newProjectId :-"
				+ newProjectId);
	}

	public void readMetaDataMO(String fileName, int newProjectId)
			throws IOException, JAXBException {
		LOGGER.info("Start : UnmarshallMetaData : readMetaData: newProjectId :-"
				+ newProjectId);
		ProjectDetails projectDetails = (ProjectDetails) findById(
				ProjectDetails.class, newProjectId);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Metadata metadata = (Metadata) jaxbUnmarshaller.unmarshal(new File(
					fileName));

			readAdminLdapProvier(projectDetails, metadata);
			readAdminRadiusProvier(projectDetails, metadata);
			readAdminTacacsProvider(projectDetails, metadata);
			readAdminLdapRole(projectDetails, metadata);
			readAdminLdapLocale(projectDetails, metadata);
			readAdminLdapGroupMap(projectDetails, metadata);
			readAdminMgmtBackupPolicy(projectDetails, metadata);
			readEquipmentServer(projectDetails, metadata);
			readEquipmentMiniScalability(projectDetails, metadata);

			LOGGER.info("Read metadata xml. Saved to DB");
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		LOGGER.info("End : UnmarshallMetaData : readMetaData: newProjectId :-"
				+ newProjectId);
	}

	private void readEquipmentMiniScalability(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readEquipmentMiniScalability: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getEquipmentMiniScalabilityPorts() != null) {
			List<EquipmentServer> configuredPortsList = metadata
					.getEquipmentMiniScalabilityPorts().getEquipmentServer();
			if (configuredPortsList != null && !configuredPortsList.isEmpty()) {
				for (EquipmentServer equipmentServerMO : configuredPortsList) {
					String query = "update EquipmentMiniScalability set isConfigured = 1 where projectDetails.id = "
							+ projectDetails.getId()
							+ " and fiId = "
							+ "\'"
							+ equipmentServerMO.getFiId()
							+ "\'"
							+ " and portId = " + equipmentServerMO.getPortId();
					bulkUpdate(query);
				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readEquipmentMiniScalability");
	}

	private void readEquipmentServer(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readEquipmentServer: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getEquipmentServers() != null) {
			List<EquipmentServer> serverList = metadata.getEquipmentServers()
					.getEquipmentServer();

			if (serverList != null && !serverList.isEmpty()) {
				for (EquipmentServer equipmentServerMO : serverList) {
					com.cisco.ca.cstg.pdi.pojos.EquipmentServer equipmentServer = new com.cisco.ca.cstg.pdi.pojos.EquipmentServer();
					equipmentServer
							.setChassis(equipmentServerMO.getChassisId());
					equipmentServer.setFiId(equipmentServerMO.getFiId());
					equipmentServer.setPortId(equipmentServerMO.getPortId());
					equipmentServer.setProjectDetails(projectDetails);
					equipmentServer.setSlotId(equipmentServerMO.getSlotId());
					equipmentServer.setUserLabel(equipmentServerMO.getUsrLbl());
					addNew(equipmentServer);
				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readEquipmentServer");

	}

	private void readAdminLdapGroupMap(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapGroupMap: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getAdminLdapGroupMap() != null) {
			List<AaaLdapGroup> aaaLdapGroupList = metadata
					.getAdminLdapGroupMap().getAaaLdapGroup();

			if (aaaLdapGroupList != null && !aaaLdapGroupList.isEmpty()) {
				for (AaaLdapGroup aaaLdapGroup : aaaLdapGroupList) {
					AdminLdapGroupMap adminLdapGroupMap = new AdminLdapGroupMap();
					adminLdapGroupMap.setName(aaaLdapGroup.getName());
					adminLdapGroupMap.setProjectDetails(projectDetails);
					addNew(adminLdapGroupMap);

					readAdminLdapGroupMapLocalesMapping(aaaLdapGroup,
							adminLdapGroupMap, projectDetails);
					readAdminLdapGroupMapRolesMapping(aaaLdapGroup,
							adminLdapGroupMap, projectDetails);
				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapGroupMap");

	}

	private void readAdminLdapGroupMapRolesMapping(AaaLdapGroup aaaLdapGroup,
			AdminLdapGroupMap adminLdapGroupMap, ProjectDetails projectDetails) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapGroupMapRolesMapping: Project Name:-"
				+ projectDetails.getProjectName());
		if (aaaLdapGroup.getAaaUserRoles() != null
				&& !aaaLdapGroup.getAaaUserRoles().isEmpty()) {
			List<AaaUserRole> aaaUserRoleList = aaaLdapGroup.getAaaUserRoles();
			List<Object> groupMapRolesMappingList = new ArrayList<>();
			for (AaaUserRole aaaUserRole : aaaUserRoleList) {
				Criterion whereClause = Restrictions.and(
						Restrictions.eq("projectDetails.id",
								projectDetails.getId()),
						Restrictions.eq("name", aaaUserRole.getName()));
				List<Object> list = listAll(AdminLdapRole.class, whereClause);
				if (list != null && !list.isEmpty()) {
					AdminLdapGroupMapRolesMapping mapping = new AdminLdapGroupMapRolesMapping();
					for (Object obj : list) {
						AdminLdapRole adminLdapRole = (AdminLdapRole) obj;
						mapping.setAdminLdapGroupMap(adminLdapGroupMap);
						mapping.setAdminLdapRole(adminLdapRole);
						groupMapRolesMappingList.add(mapping);
					}
				}
			}
			addList(groupMapRolesMappingList);
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapGroupMapRolesMapping");
	}

	private void readAdminLdapGroupMapLocalesMapping(AaaLdapGroup aaaLdapGroup,
			AdminLdapGroupMap adminLdapGroupMap, ProjectDetails projectDetails) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapGroupMapLocalesMapping: Project Name:-"
				+ projectDetails.getProjectName());
		if (aaaLdapGroup.getAaaUserLocales() != null
				&& !aaaLdapGroup.getAaaUserLocales().isEmpty()) {
			List<AaaUserLocale> aaaUserLocaleList = aaaLdapGroup
					.getAaaUserLocales();
			List<Object> groupMapLocalesMappingList = new ArrayList<>();
			for (AaaUserLocale aaaUserLocale : aaaUserLocaleList) {
				Criterion whereClause = Restrictions.and(
						Restrictions.eq("projectDetails.id",
								projectDetails.getId()),
						Restrictions.eq("name", aaaUserLocale.getName()));
				List<Object> list = listAll(AdminLdapLocale.class, whereClause);
				if (list != null && !list.isEmpty()) {
					AdminLdapGroupMapLocalesMapping mapping = new AdminLdapGroupMapLocalesMapping();
					for (Object obj : list) {
						AdminLdapLocale adminLdapLocale = (AdminLdapLocale) obj;
						mapping.setAdminLdapGroupMap(adminLdapGroupMap);
						mapping.setAdminLdapLocale(adminLdapLocale);
						groupMapLocalesMappingList.add(mapping);
					}
				}
			}
			addList(groupMapLocalesMappingList);
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapGroupMapLocalesMapping");
	}

	private void readAdminLdapLocale(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapLocale: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getAdminLdapLocale() != null) {
			List<AaaLocale> aaaLdapLocaleList = metadata.getAdminLdapLocale()
					.getAaaLocale();

			if (aaaLdapLocaleList != null && !aaaLdapLocaleList.isEmpty()) {
				Criterion whereClause = Restrictions.eq("projectDetails.id",
						projectDetails.getId());
				List<Object> orgList = listAll(Organizations.class, whereClause);

				for (AaaLocale aaaLocale : aaaLdapLocaleList) {
					AdminLdapLocale adminLdapLocale = new AdminLdapLocale();
					adminLdapLocale.setName(aaaLocale.getName());
					adminLdapLocale.setProjectDetails(projectDetails);
					addNew(adminLdapLocale);
					readAdminLdapLocaleOrgMapping(aaaLocale, adminLdapLocale,
							orgList);
				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapLocale");
	}

	private void readAdminLdapLocaleOrgMapping(AaaLocale aaaLocale,
			AdminLdapLocale adminLdapLocale, List<Object> orgList) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapLocaleOrgMapping");
		if (aaaLocale.getAaaOrgs() != null && !aaaLocale.getAaaOrgs().isEmpty()) {
			List<Object> localeOrgMappingList = new ArrayList<>();
			List<AaaOrg> moOrgList = aaaLocale.getAaaOrgs();
			for (AaaOrg aaaOrg : moOrgList) {
				for (Object obj : orgList) {
					Organizations org = (Organizations) obj;
					if (aaaOrg.getName().equals(org.getName())) {
						AdminLdapLocalesOrgMapping mapping = new AdminLdapLocalesOrgMapping();
						mapping.setAdminLdapLocale(adminLdapLocale);
						mapping.setOrganizations(org);
						localeOrgMappingList.add(mapping);
					}
				}
			}
			addList(localeOrgMappingList);
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapLocaleOrgMapping");
	}

	private void readAdminLdapRole(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapRole: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getAdminLdapRole() != null) {
			List<AaaRole> aaaRoleList = metadata.getAdminLdapRole()
					.getAaaRole();

			if (aaaRoleList != null && !aaaRoleList.isEmpty()) {
				List<Object> privList = listAll(AdminPrivilege.class);

				for (AaaRole aaaRole : aaaRoleList) {
					AdminLdapRole adminLdapRole = new AdminLdapRole();
					adminLdapRole.setName(aaaRole.getName());
					adminLdapRole.setProjectDetails(projectDetails);
					addNew(adminLdapRole);
					List<Object> aRolesPrivilegesMappingList = new ArrayList<>();

					for (Object obj : privList) {
						AdminPrivilege adminPrivilege = (AdminPrivilege) obj;
						if (aaaRole.getPriv() != null
								&& Arrays.asList(aaaRole.getPriv()).contains(
										adminPrivilege.getName())) {
							AdminLdapRolesPrivilegesMapping mapping = new AdminLdapRolesPrivilegesMapping();
							mapping.setAdminLdapRole(adminLdapRole);
							mapping.setAdminPrivilege(adminPrivilege);
							aRolesPrivilegesMappingList.add(mapping);
						}
					}
					addList(aRolesPrivilegesMappingList);
				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapRole");
	}

	private void readAdminTacacsProvider(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminTacacsProvier: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getAdminTacacsProvider() != null) {
			List<AaaTacacsProvider> aaaTacacsProviderList = metadata
					.getAdminTacacsProvider().getAaaTacacsProvider();

			if (aaaTacacsProviderList != null
					&& !aaaTacacsProviderList.isEmpty()) {
				for (AaaTacacsProvider aaaTacacsProvider : aaaTacacsProviderList) {
					Criterion whereClause = Restrictions.and(
							Restrictions.eq("projectDetails.id",
									projectDetails.getId()),
							Restrictions.eq("hostname",
									aaaTacacsProvider.getName()));
					List<Object> list = listAll(
							com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider.class,
							whereClause);
					if (list != null && !list.isEmpty()) {
						for (Object obj : list) {
							com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider adminTacacsProvider = (com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider) obj;
							adminTacacsProvider
									.setEncryptedPassword(aaaTacacsProvider
											.getDecryptedKey());
							update(adminTacacsProvider);
						}
					}

				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminTacacsProvier");
	}

	private void readAdminRadiusProvier(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminRadiusProvier: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getAdminRadiusProvider() != null) {
			List<AaaRadiusProvider> aaaRadiusProviderList = metadata
					.getAdminRadiusProvider().getAaaRadiusProvider();

			if (aaaRadiusProviderList != null
					&& !aaaRadiusProviderList.isEmpty()) {
				for (AaaRadiusProvider aaaRadiusProvider : aaaRadiusProviderList) {
					Criterion whereClause = Restrictions.and(
							Restrictions.eq("projectDetails.id",
									projectDetails.getId()),
							Restrictions.eq("hostname",
									aaaRadiusProvider.getName()));
					List<Object> list = listAll(
							com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider.class,
							whereClause);
					if (list != null && !list.isEmpty()) {
						for (Object obj : list) {
							com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider adminRadiusProvider = (com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider) obj;
							adminRadiusProvider.setEncKey(aaaRadiusProvider
									.getDecryptedKey());
							update(adminRadiusProvider);
						}
					}

				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminRadiusProvier");
	}

	private void readAdminLdapProvier(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readAdminLdapProvier: Project Name:-"
				+ projectDetails.getProjectName());
		if (metadata.getAdminLdapProvider() != null) {
			List<AaaLdapProvider> aaaLdapProviderList = metadata
					.getAdminLdapProvider().getAaaLdapProvider();

			if (aaaLdapProviderList != null && !aaaLdapProviderList.isEmpty()) {
				for (AaaLdapProvider aaaLdapProvider : aaaLdapProviderList) {
					Criterion whereClause = Restrictions.and(
							Restrictions.eq("projectDetails.id",
									projectDetails.getId()),
							Restrictions.eq("hostname",
									aaaLdapProvider.getName()));
					List<Object> list = listAll(
							com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider.class,
							whereClause);
					if (list != null && !list.isEmpty()) {
						for (Object obj : list) {
							com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider adminLdapProvider = (com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider) obj;
							adminLdapProvider
									.setEncryptedPassword(aaaLdapProvider
											.getDecryptedKey());
							update(adminLdapProvider);
						}
					}

				}
			}
		}
		LOGGER.info("End : UnmarshallMetaData : readAdminLdapProvier");
	}

	private void readEquipmentChassisInfo(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readEquipmentChassisInfo: Project Name:-"
				+ projectDetails.getProjectName());
		EquipmentChasis chassis = new EquipmentChasis();
		chassis.setProjectDetails(projectDetails);
		Criterion whereClause = Restrictions.eq("projectDetails.id",
				projectDetails.getId());
		deleteByCriteria(EquipmentChasis.class, whereClause);

		if (metadata.getEquipmentChassis() != null) {
			EquipmentChassis equipmentChassisMO = metadata
					.getEquipmentChassis();
			chassis.setCdpAction(equipmentChassisMO.getCdpAction());
			chassis.setCdpLinkAgg(equipmentChassisMO.getCdpLinkAgg());
		}
		saveOrUpdate(chassis);
		LOGGER.info("End : UnmarshallMetaData : readEquipmentChassisInfo. ");
	}

	private void readInfrastructureInfo(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readEquipmentChassisInfo. ");
		Infrastructure infrastructure = new Infrastructure();
		infrastructure.setProjectDetails(projectDetails);
		Criterion whereClause = Restrictions.eq("projectDetails.id",
				projectDetails.getId());
		deleteByCriteria(Infrastructure.class, whereClause);

		if (metadata.getInfrastructure() != null) {
			com.cisco.ca.cstg.pdi.pojos.mo.Infrastructure infrastructureMO = metadata
					.getInfrastructure();
			String value = infrastructureMO.getFabricInterconnectModel();
			Criterion criteria = Restrictions.eq("description", value);
			infrastructure.setServerModel((ServerModel) listAll(
					ServerModel.class, criteria).get(0));
			infrastructure.setIoModule(infrastructureMO.getIomModel());
			infrastructure.setSoftwareVersion(infrastructureMO
					.getSoftwareVersion());
			if (Constants.SERVERMODEL6324.equals(value)) {
				equipmentService
						.insertScalabilityPortsForMini(projectDetails.getId());
			}
		}
		saveOrUpdate(infrastructure);
		LOGGER.info("End : UnmarshallMetaData : readEquipmentChassisInfo. ");
	}

	private void readAdminMgmtBackupPolicy(ProjectDetails projectDetails,
			Metadata metadata) {
		LOGGER.info("Start : UnmarshallMetaData : readMgmtBackupPolicy. ");

		if (metadata.getMgmtBackupPolicy() != null) {
			Mgmtbackuppolicy mgmtbackuppolicy = metadata.getMgmtBackupPolicy();

			AdminBackupConfiguration adminBackupConfiguration = new AdminBackupConfiguration();
			adminBackupConfiguration.setAdminState(mgmtbackuppolicy
					.getAdminState());
			adminBackupConfiguration.setBackupStatus(mgmtbackuppolicy
					.getStatus());
			adminBackupConfiguration.setBackupType("config-all");
			adminBackupConfiguration.setHostname(mgmtbackuppolicy.getHost());
			adminBackupConfiguration.setPassword(mgmtbackuppolicy
					.getDecryptedPwd());
			adminBackupConfiguration.setPreserveIdentities("no");
			adminBackupConfiguration.setProjectDetails(projectDetails);
			adminBackupConfiguration.setProtocol(mgmtbackuppolicy.getProto());
			adminBackupConfiguration.setRemoteFile(mgmtbackuppolicy
					.getRemoteFile());
			adminBackupConfiguration.setUserName(mgmtbackuppolicy.getUser());

			saveOrUpdate(adminBackupConfiguration);
		}
		LOGGER.info("End : UnmarshallMetaData : readMgmtBackupPolicy. ");
	}
}
