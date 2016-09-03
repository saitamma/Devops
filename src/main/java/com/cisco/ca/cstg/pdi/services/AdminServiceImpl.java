package com.cisco.ca.cstg.pdi.services;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.AdminAuthenticationDomain;
import com.cisco.ca.cstg.pdi.pojos.AdminBackupConfiguration;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeAlertGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomePolicy;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfile;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileAlertGroupMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileRecipientMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeSystemInventory;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMap;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapLocalesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapRolesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocalesOrgMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRole;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRolesPrivilegesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminSettings;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.Dns;
import com.cisco.ca.cstg.pdi.pojos.Ntp;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.OrganizationsView;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.TimeZone;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

/**
 * @author pavkuma2
 * 
 */

@Service("adminService")
public class AdminServiceImpl extends CommonDaoServicesImpl implements
		AdminService {

	private static final String ADMINLDAPPROVIDERGROUP = "adminLdapProviderGroup";
	private static final String ADMINLDAPGROUPMAP = "adminLdapGroupMap";
	private static final String ADMINRADIUSPROVIDERGROUP = "adminRadiusProviderGroup";
	private static final String ADMINTACACSPROVIDERGROUP = "adminTacacsProviderGroup";
	private static final String ADMINLDAPROLE = "adminLdapRole";
	private static final String ADMINLDAPLOCALE = "adminLdapLocale";
	private static final String COUNTRYTIMEZONE = "countryTimeZone";
	private static final String ADMINCALLHOMEPROFILE_ID = "adminCallhomeProfile.id";

	private CommonUtilServices commonUtilServices;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminServiceImpl.class);

	@Autowired
	public AdminServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilServices) {
		this.commonUtilServices = commonUtilServices;
		setSessionFactory(hibernateSessionFactory);
	}

	@Override
	public List<Organizations> saveOrUpdateOrganization(
			List<Organizations> orgList, Integer projectId) {
		List<Organizations> newlyAddedDbRecords = new ArrayList<Organizations>();

		for (Organizations orgObj : orgList) {
			orgObj.setProjectDetails(new ProjectDetails(projectId));

			if (orgObj.getId() == 0) {
				addNew(orgObj);
				newlyAddedDbRecords.add(orgObj);
			} else {
				update(orgObj);
			}
		}
		return newlyAddedDbRecords;
	}

	@Override
	public boolean deleteOrganization(List<Organizations> orgList) {

		if (Util.listNotNull(orgList) && !isDependantOrganization(orgList)) {
			deleteAll(orgList);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dns> fetchDNSDetails(Integer projectId) {
		Criterion whereClause = Restrictions.eq(Constants.PROJECTDETAILS_ID,
				projectId);
		return (List<Dns>) (List<?>) listAll(Dns.class, whereClause);
	}

	@Override
	public List<Dns> saveOrUpdateDNSConfiguration(List<Dns> dnsObjList,
			Integer projectId) {
		ProjectDetails projectDetails = commonUtilServices
				.getProjectDetailsObject(projectId);
		List<Dns> newlyAddedDbRecords = new ArrayList<Dns>();

		for (Dns dnsObj : dnsObjList) {
			dnsObj.setProjectDetails(projectDetails);

			if (dnsObj.getId() == 0) {
				dnsObj.setId(addNew(dnsObj));
				newlyAddedDbRecords.add(dnsObj);
			} else {
				update(dnsObj);
			}
		}
		return newlyAddedDbRecords;
	}

	@Override
	public void deleteDNSConfiguration(List<Dns> dnsIdList) {
		deleteAll(dnsIdList);
	}

	@Override
	public List<Ntp> saveOrUpdateNTPConfiguration(List<Ntp> ntpObjList,
			Integer projectId) {
		ProjectDetails projectDetails = commonUtilServices
				.getProjectDetailsObject(projectId);
		List<Ntp> newlyAddedDbRecords = new ArrayList<Ntp>();

		for (Ntp ntpObj : ntpObjList) {
			ntpObj.setProjectDetails(projectDetails);

			if (ntpObj.getId() == 0) {
				ntpObj.setId(addNew(ntpObj));
				newlyAddedDbRecords.add(ntpObj);
			} else {
				update(ntpObj);
			}
		}
		return newlyAddedDbRecords;
	}

	@Override
	public void deleteNTPConfiguration(List<Ntp> ntpIdList) {
		deleteAll(ntpIdList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ntp> fetchNTPDetails(Integer projectId) {
		Criterion whereClause = Restrictions.eq(Constants.PROJECTDETAILS_ID,
				projectId);
		return (List<Ntp>) (List<?>) listAll(Ntp.class, whereClause);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer fetchTimezoneDetails(Integer projectId) {
		AdminSettings adminSetting;
		Integer timezone = 0;

		Criterion whereClause = Restrictions.eq(Constants.PROJECTDETAILS_ID,
				projectId);
		List<AdminSettings> adminSettingsList = (List<AdminSettings>) (List<?>) listAll(
				AdminSettings.class, whereClause);
		if (adminSettingsList != null && !adminSettingsList.isEmpty()) {

			if (adminSettingsList.size() > 1) {
				throw new IllegalStateException(
						"More than one Admin Settings for a given project");
			}

			adminSetting = adminSettingsList.get(0);
			timezone = (Integer) Util.nullValidationAndReturnId(adminSetting
					.getTimeZone());
		}
		return timezone;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateTimeZone(Integer timeZone, Integer projectId) {
		Criterion whereClause = Restrictions.eq(Constants.PROJECTDETAILS_ID,
				projectId);
		List<AdminSettings> adminSettingsList = (List<AdminSettings>) (List<?>) listAll(
				AdminSettings.class, whereClause);
		AdminSettings adminSetting;
		if (adminSettingsList != null && adminSettingsList.size() > 1) {
			throw new IllegalStateException(
					"More than one Admin Settings for a given project");
		}
		if (adminSettingsList == null || adminSettingsList.size() == 0) {
			ProjectDetails projectDetails = this.commonUtilServices
					.getProjectDetailsObject(projectId);
			adminSetting = new AdminSettings();
			adminSetting.setProjectDetails(projectDetails);
		} else {
			adminSetting = adminSettingsList.get(0);
		}

		if (timeZone != null) {
			adminSetting.setTimeZone((TimeZone) findById(TimeZone.class,
					timeZone));
		} else {
			adminSetting.setTimeZone(null);
		}

		saveOrUpdate(adminSetting);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Organizations fetchRootOrganization(Integer projectId) {

		Criterion whereClause = Restrictions.and(
				Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId),
				Restrictions.eq("name", "root"));
		List<Organizations> orgList = (List<Organizations>) (List<?>) listAll(
				Organizations.class, whereClause);

		if (orgList.size() > 1 || orgList.isEmpty()) {
			throw new IllegalStateException(
					"Illegal number of root organization.");
		}

		return orgList.get(0);
	}

	/**
	 * json Format
	 * 
	 * { id:'1',parentId:"0",name:'Africa', children:[ {id:'2',
	 * parentId:"1",name:'Egypt'}, { id:'3',parentId:"1",name:'Kenya',
	 * children:[ { id:'4',parentId:"3",name:'Nairobi'}, {
	 * id:'5',parentId:"1",name:'Mombasa'} ] }, {
	 * id:'6',parentId:"0",name:'Sudan', children: {
	 * id:'7',parentId:"6",name:'Khartoum'} } ] }
	 *
	 */
	@Override
	public String convertOrganizationsDetailsInJSonFormat(Organizations org) {

		return convertOrganizationsStringToJson(createJSonFormatForOrg(org, 0,
				true));
	}

	private String createJSonFormatForOrg(Organizations org, int depth,
			boolean isFirstChild) {
		int depthCount = depth;
		org.setDepth(depthCount);

		StringBuilder sb = new StringBuilder();
		sb.append(isFirstChild ? "{" : ",{");
		sb.append(organizatonInJsonFormat(org));
		boolean firstOccurance = true;

		Iterator<Organizations> orgIt = org.getOrganizationses().iterator();

		while (orgIt.hasNext()) {

			if (firstOccurance) {
				sb.append(", \"children\":[");
			}
			sb.append(createJSonFormatForOrg(orgIt.next(),
					firstOccurance ? ++depthCount : depthCount,
					firstOccurance ? true : false));
			firstOccurance = false;
		}

		if (!firstOccurance) {
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}

	private String organizatonInJsonFormat(Organizations org) {
		return "\"id\":" + org.getId() + ", \"parentId\":" + org.getParentId()
				+ ", \"name\": \"" + org.getName() + "\", \"depth\": "
				+ org.getDepth();

	}

	private String convertOrganizationsStringToJson(String organizationsString) {
		Writer strWriter = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		String pmDataJSON = "";
		try {
			mapper.writeValue(strWriter, organizationsString);
			pmDataJSON = strWriter.toString();

		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return pmDataJSON;
	}

	private boolean isDependantOrganization(List<Organizations> orgList) {

		for (Organizations org : orgList) {
			OrganizationsView organizationsView = (OrganizationsView) findById(
					OrganizationsView.class, org.getId());

			if (organizationsView.getCount() != 0) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TimeZone> fetchTimeZoneList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(TimeZone.class)
				.addOrder(Order.asc(COUNTRYTIMEZONE));
		return (List<TimeZone>) (List<?>) listAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminAuthenticationDomain> fetchAdminAuthDomain(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminAuthenticationDomain>) (List<?>) listAll(
				AdminAuthenticationDomain.class, criteria);
	}

	@Override
	public List<AdminAuthenticationDomain> saveOrUpdateAuthDomain(
			List<AdminAuthenticationDomain> adminAuthDomainList,
			Integer projectId) {
		List<AdminAuthenticationDomain> newlyAddedDbRecords = new ArrayList<AdminAuthenticationDomain>();
		for (AdminAuthenticationDomain aad : adminAuthDomainList) {
			aad.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if ("Ldap".equals(aad.getRealm()) && aad.getProviderGroup() != null) {
				aad.setAdminLdapProviderGroup(new AdminLdapProviderGroup(aad
						.getProviderGroup()));
			} else if ("Radius".equals(aad.getRealm())
					&& aad.getProviderGroup() != null) {
				aad.setAdminRadiusProviderGroup(new AdminRadiusProviderGroup(
						aad.getProviderGroup()));
			} else if ("Tacacs".equals(aad.getRealm())
					&& aad.getProviderGroup() != null) {
				aad.setAdminTacacsProviderGroup(new AdminTacacsProviderGroup(
						aad.getProviderGroup()));
			}
			if (aad.getId() == 0) {
				aad.setId(addNew(aad));
				newlyAddedDbRecords.add(aad);
			} else {
				update(aad);
			}
		}
		return newlyAddedDbRecords;
	}

	@Override
	public void deleteAdminAuthDomain(
			List<AdminAuthenticationDomain> deletedAdminAuthDomainList) {
		deleteAll(deletedAdminAuthDomainList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminLdapProvider> fetchLdapProvider(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminLdapProvider>) (List<?>) listAll(
				AdminLdapProvider.class, criteria);
	}

	@Override
	public List<AdminLdapProvider> saveOrUpdateLdapProvider(
			List<AdminLdapProvider> ldapProviderList, Integer projectId) {
		List<AdminLdapProvider> newlyAddedDbRecords = new ArrayList<AdminLdapProvider>();
		for (AdminLdapProvider lp : ldapProviderList) {
			lp.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (lp.getId() == 0) {
				lp.setId(addNew(lp));
				newlyAddedDbRecords.add(lp);
			} else {
				update(lp);
			}
		}
		return newlyAddedDbRecords;
	}

	@Override
	public void deleteLdapProvider(
			List<AdminLdapProvider> deletedLdapProviderList) {
		deleteAll(deletedLdapProviderList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public AdminLdapGeneral fetchLdapGeneral(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		List<AdminLdapGeneral> list = (List<AdminLdapGeneral>) (List<?>) listAll(
				AdminLdapGeneral.class, criteria);

		if (list != null && !list.isEmpty()) {

			if (list.size() > 1) {
				throw new IllegalStateException(
						"More than one General Ldap Settings.");
			}
			return list.get(0);
		}

		return null;
	}

	@Override
	public Integer saveOrUpdateLdapGeneral(AdminLdapGeneral adminLdapGeneral,
			Integer projectId) {
		adminLdapGeneral.setProjectDetails(commonUtilServices
				.getProjectDetailsObject(projectId));
		if (adminLdapGeneral.getId() == 0) {
			addNew(adminLdapGeneral);
		} else {
			update(adminLdapGeneral);
		}
		return adminLdapGeneral.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminLdapProviderGroup> fetchAdminLdapProviderGroup(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminLdapProviderGroup>) (List<?>) listAll(
				AdminLdapProviderGroup.class, criteria);
	}

	@Override
	public List<AdminLdapProviderGroup> saveOrUpdateAdminLdapProviderGroup(
			List<AdminLdapProviderGroup> adminLdapProviderGroupList,
			Integer projectId) {
		for (AdminLdapProviderGroup adminLdapProviderGroup : adminLdapProviderGroupList) {
			adminLdapProviderGroup.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminLdapProviderGroup.getId() == 0) {
				addNew(adminLdapProviderGroup);
			} else {
				update(adminLdapProviderGroup);
			}

			saveOrUpdateAdminLdapProviderGroupMappings(
					adminLdapProviderGroup.getAdminLdapGroupProviderMappings(),
					adminLdapProviderGroup.getId());
		}
		return adminLdapProviderGroupList;
	}

	@Override
	public void deleteAdminLdapProviderGroup(
			List<AdminLdapProviderGroup> adminLdapProviderGroupList,
			Integer projectId) {
		deleteAll(adminLdapProviderGroupList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminLdapGroupProviderMapping> fetchAdminLdapGroupProviderMapping(
			Integer id) {
		Criterion criteria = Restrictions.eq(ADMINLDAPPROVIDERGROUP,
				new AdminLdapProviderGroup(id));
		return (List<AdminLdapGroupProviderMapping>) (List<?>) listAll(
				AdminLdapGroupProviderMapping.class, criteria);
	}

	private void saveOrUpdateAdminLdapProviderGroupMappings(
			List<AdminLdapGroupProviderMapping> adminLdapProviderMappingList,
			Integer adminLdapProviderGroupId) {
		Criterion criteria = Restrictions.eq(ADMINLDAPPROVIDERGROUP,
				new AdminLdapProviderGroup(adminLdapProviderGroupId));
		deleteByCriteria(AdminLdapGroupProviderMapping.class, criteria);
		int order = 1;

		for (AdminLdapGroupProviderMapping mappingObj : adminLdapProviderMappingList) {
			mappingObj.setAdminLdapProviderGroup(new AdminLdapProviderGroup(
					adminLdapProviderGroupId));
			mappingObj.setProviderOrder(order);

			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
			order++;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLdapGroupMap> fetchLdapGroupMapsInfo(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminLdapGroupMap>) (List<?>) listAll(
				AdminLdapGroupMap.class, criteria);
	}

	@Override
	public List<AdminLdapGroupMap> saveOrUpdateAdminLdapGroupMaps(
			List<AdminLdapGroupMap> ldapGroupMapsList, Integer projectId) {
		for (AdminLdapGroupMap adminLdapGroupMap : ldapGroupMapsList) {
			adminLdapGroupMap.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminLdapGroupMap.getId() == 0) {
				addNew(adminLdapGroupMap);
			} else {
				update(adminLdapGroupMap);
			}

			saveOrUpdateAdminLdapGroupRolesMapping(
					adminLdapGroupMap.getAdminLdapGroupMapRolesMappings(),
					adminLdapGroupMap.getId());
			saveOrUpdateAdminLdapGroupLocalesMapping(
					adminLdapGroupMap.getAdminLdapGroupMapLocalesMappings(),
					adminLdapGroupMap.getId());
		}
		return ldapGroupMapsList;
	}

	@Override
	public void deleteLdapGroupMaps(List<AdminLdapGroupMap> ldapGroupMapsList) {
		deleteAll(ldapGroupMapsList);
	}

	private void saveOrUpdateAdminLdapGroupRolesMapping(
			List<AdminLdapGroupMapRolesMapping> adminLdapGroupMapRolesMapping,
			Integer adminLdapGroupMapId) {
		Criterion criteria = Restrictions.eq(ADMINLDAPGROUPMAP,
				new AdminLdapGroupMap(adminLdapGroupMapId));
		deleteByCriteria(AdminLdapGroupMapRolesMapping.class, criteria);

		for (AdminLdapGroupMapRolesMapping mappingObj : adminLdapGroupMapRolesMapping) {
			mappingObj.setAdminLdapGroupMap(new AdminLdapGroupMap(
					adminLdapGroupMapId));

			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
	}

	private void saveOrUpdateAdminLdapGroupLocalesMapping(
			List<AdminLdapGroupMapLocalesMapping> adminLdapGroupMapLocalesMappingList,
			Integer adminLdapGroupMapId) {
		Criterion criteria = Restrictions.eq(ADMINLDAPGROUPMAP,
				new AdminLdapGroupMap(adminLdapGroupMapId));
		deleteByCriteria(AdminLdapGroupMapLocalesMapping.class, criteria);

		for (AdminLdapGroupMapLocalesMapping mappingObj : adminLdapGroupMapLocalesMappingList) {
			mappingObj.setAdminLdapGroupMap(new AdminLdapGroupMap(
					adminLdapGroupMapId));

			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public AdminRadiusGeneral fetchRadiusGeneral(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		List<AdminRadiusGeneral> list = (List<AdminRadiusGeneral>) (List<?>) listAll(
				AdminRadiusGeneral.class, criteria);

		if (Util.listNotNull(list)) {

			if (list.size() > 1) {
				throw new IllegalStateException(
						"More than one General Radius Settings.");
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer saveOrUpdateRadiusGeneral(AdminRadiusGeneral radiusGeneral,
			Integer projectId) {
		radiusGeneral.setProjectDetails(commonUtilServices
				.getProjectDetailsObject(projectId));
		if (radiusGeneral.getId() == 0) {
			addNew(radiusGeneral);
		} else {
			update(radiusGeneral);
		}
		return radiusGeneral.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminRadiusProvider> fetchRadiusProvider(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminRadiusProvider>) (List<?>) listAll(
				AdminRadiusProvider.class, criteria);
	}

	@Override
	public List<AdminRadiusProvider> saveOrUpdateRadiusProvider(
			List<AdminRadiusProvider> radiusProviderList, Integer projectId) {
		List<AdminRadiusProvider> radiusProvidersAdded = new ArrayList<AdminRadiusProvider>();
		for (AdminRadiusProvider lp : radiusProviderList) {
			lp.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (lp.getId() == 0) {
				addNew(lp);
				radiusProvidersAdded.add(lp);
			} else {
				update(lp);
			}
		}
		return radiusProvidersAdded;
	}

	@Override
	public void deleteRadiusProvider(
			List<AdminRadiusProvider> deleteRadiusProviderList) {
		deleteAll(deleteRadiusProviderList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminRadiusProviderGroup> fetchRadiusProviderGroup(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminRadiusProviderGroup>) (List<?>) listAll(
				AdminRadiusProviderGroup.class, criteria);
	}

	@Override
	public List<AdminRadiusProviderGroup> saveOrUpdateRadiusProviderGroup(
			List<AdminRadiusProviderGroup> radiusProviderGroupList,
			Integer projectId) {
		for (AdminRadiusProviderGroup adminRadiusProviderGroup : radiusProviderGroupList) {
			adminRadiusProviderGroup.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminRadiusProviderGroup.getId() == 0) {
				addNew(adminRadiusProviderGroup);
			} else {
				update(adminRadiusProviderGroup);
			}

			saveOrUpdateRadiusProviderGroupMapping(
					adminRadiusProviderGroup
							.getAdminRadiusGroupProviderMappings(),
					adminRadiusProviderGroup.getId());
		}
		return radiusProviderGroupList;
	}

	@Override
	public void deleteRadiusProviderGroup(
			List<AdminRadiusProviderGroup> deleteRadiusProviderGroupList,
			Integer projectId) {
		deleteAll(deleteRadiusProviderGroupList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminRadiusGroupProviderMapping> fetchRadiusProviderGroupMapping(
			Integer id) {
		Criterion criteria = Restrictions.eq(ADMINRADIUSPROVIDERGROUP,
				new AdminRadiusProviderGroup(id));
		return (List<AdminRadiusGroupProviderMapping>) (List<?>) listAll(
				AdminRadiusGroupProviderMapping.class, criteria);
	}

	@Override
	public void deleteRadiusProviderGroupMapping(
			List<AdminRadiusGroupProviderMapping> deleteRadiusProviderGroupMappingList) {
		deleteAll(deleteRadiusProviderGroupMappingList);
	}

	private void saveOrUpdateRadiusProviderGroupMapping(
			List<AdminRadiusGroupProviderMapping> radiusProviderGroupMappingList,
			Integer adminRadiusProviderGroupId) {
		Criterion criteria = Restrictions.eq(ADMINRADIUSPROVIDERGROUP,
				new AdminRadiusProviderGroup(adminRadiusProviderGroupId));
		deleteByCriteria(AdminRadiusGroupProviderMapping.class, criteria);
		int order = 1;

		for (AdminRadiusGroupProviderMapping mappingObj : radiusProviderGroupMappingList) {
			mappingObj
					.setAdminRadiusProviderGroup(new AdminRadiusProviderGroup(
							adminRadiusProviderGroupId));
			mappingObj.setRadiusProviderOrder(order);

			if (mappingObj.getId() == 0) {
				mappingObj.setId(addNew(mappingObj));
			}
			order++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminTacacsProvider> fetchTacacsProvider(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminTacacsProvider>) (List<?>) listAll(
				AdminTacacsProvider.class, criteria);
	}

	@Override
	public List<AdminTacacsProvider> saveOrUpdateTacacsProvider(
			List<AdminTacacsProvider> tacacsProviderList, Integer projectId) {
		List<AdminTacacsProvider> newlyAddedDbRecords = new ArrayList<AdminTacacsProvider>();
		for (AdminTacacsProvider lp : tacacsProviderList) {
			lp.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (lp.getId() == 0) {
				addNew(lp);
				newlyAddedDbRecords.add(lp);
			} else {
				update(lp);
			}
		}
		return newlyAddedDbRecords;
	}

	@Override
	public void deleteTacacsProvider(
			List<AdminTacacsProvider> deletedTacacsProviderList) {
		deleteAll(deletedTacacsProviderList);
	}

	@Override
	public AdminTacacsGeneral fetchTacacsGeneral(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		@SuppressWarnings("unchecked")
		List<AdminTacacsGeneral> list = (List<AdminTacacsGeneral>) (List<?>) listAll(
				AdminTacacsGeneral.class, criteria);

		if (Util.listNotNull(list)) {

			if (list.size() > 1) {
				throw new IllegalStateException(
						"More than one General Tacacs Settings.");
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer saveOrUpdateTacacsGeneral(
			AdminTacacsGeneral adminTacacsGeneral, Integer projectId) {
		adminTacacsGeneral.setProjectDetails(commonUtilServices
				.getProjectDetailsObject(projectId));

		if (adminTacacsGeneral.getId() == 0) {
			addNew(adminTacacsGeneral);
		} else {
			update(adminTacacsGeneral);
		}
		return adminTacacsGeneral.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminTacacsProviderGroup> fetchAdminTacacsProviderGroup(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminTacacsProviderGroup>) (List<?>) listAll(
				AdminTacacsProviderGroup.class, criteria);
	}

	@Override
	public List<AdminTacacsProviderGroup> saveOrUpdateAdminTacacsProviderGroup(
			List<AdminTacacsProviderGroup> adminTacacsProviderGroupList,
			Integer projectId) {
		for (AdminTacacsProviderGroup adminTacacsProviderGroup : adminTacacsProviderGroupList) {
			adminTacacsProviderGroup.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminTacacsProviderGroup.getId() == 0) {
				addNew(adminTacacsProviderGroup);
			} else {
				update(adminTacacsProviderGroup);
			}

			saveOrUpdateAdminTacacsProviderGroupMappings(
					adminTacacsProviderGroup
							.getAdminTacacsGroupProviderMappings(),
					adminTacacsProviderGroup.getId());
		}
		return adminTacacsProviderGroupList;
	}

	@Override
	public void deleteAdminTacacsProviderGroup(
			List<AdminTacacsProviderGroup> adminTacacsProviderGroupList,
			Integer projectId) {
		deleteAll(adminTacacsProviderGroupList);
	}

	private void saveOrUpdateAdminTacacsProviderGroupMappings(
			List<AdminTacacsGroupProviderMapping> adminTacacsProviderMappingList,
			Integer adminTacacsProviderGroupId) {
		Criterion criteria = Restrictions.eq(ADMINTACACSPROVIDERGROUP,
				new AdminTacacsProviderGroup(adminTacacsProviderGroupId));
		deleteByCriteria(AdminTacacsGroupProviderMapping.class, criteria);
		int order = 1;

		for (AdminTacacsGroupProviderMapping mappingObj : adminTacacsProviderMappingList) {
			mappingObj
					.setAdminTacacsProviderGroup(new AdminTacacsProviderGroup(
							adminTacacsProviderGroupId));
			mappingObj.setProviderOrder(order);

			if (mappingObj.getId() == 0) {
				mappingObj.setId(addNew(mappingObj));
			}
			order++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminLdapRole> fetchAdminRoles(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminLdapRole>) (List<?>) listAll(AdminLdapRole.class,
				criteria);
	}

	@Override
	public List<AdminLdapRole> saveOrUpdateAdminRoles(
			List<AdminLdapRole> adminRolesList, Integer projectId) {
		for (AdminLdapRole adminLdapRole : adminRolesList) {
			adminLdapRole.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminLdapRole.getId() == 0) {
				addNew(adminLdapRole);
			} else {
				update(adminLdapRole);
			}

			saveOrUpdateAdminLdapRolePrevilegesMapping(
					adminLdapRole.getAdminLdapRolesPrivilegesMappings(),
					adminLdapRole.getId());
		}
		return adminRolesList;
	}

	private void saveOrUpdateAdminLdapRolePrevilegesMapping(
			List<AdminLdapRolesPrivilegesMapping> adminLdapRolesPrivilegesMappingsList,
			Integer id) {
		Criterion criteria = Restrictions.eq(ADMINLDAPROLE, new AdminLdapRole(
				id));
		deleteByCriteria(AdminLdapRolesPrivilegesMapping.class, criteria);

		for (AdminLdapRolesPrivilegesMapping mappingObj : adminLdapRolesPrivilegesMappingsList) {
			mappingObj.setAdminLdapRole(new AdminLdapRole(id));
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
	}

	@Override
	public void deleteAdminRoles(List<AdminLdapRole> deleteAdminRolesList) {
		deleteAll(deleteAdminRolesList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminLdapLocale> fetchAdminLocales(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminLdapLocale>) (List<?>) listAll(AdminLdapLocale.class,
				criteria);
	}

	@Override
	public List<AdminLdapLocale> saveOrUpdateAdminLocales(
			List<AdminLdapLocale> adminLocalesList, Integer projectId) {
		for (AdminLdapLocale adminLdapLocale : adminLocalesList) {
			adminLdapLocale.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminLdapLocale.getId() == 0) {
				addNew(adminLdapLocale);
			} else {
				update(adminLdapLocale);
			}

			saveOrUpdateAdminLdapLocaleOrgsMapping(
					adminLdapLocale.getAdminLdapLocalesOrgMappings(),
					adminLdapLocale.getId());
		}
		return adminLocalesList;
	}

	private void saveOrUpdateAdminLdapLocaleOrgsMapping(
			List<AdminLdapLocalesOrgMapping> adminLdapLocalesOrgMappingsList,
			Integer id) {
		Criterion criteria = Restrictions.eq(ADMINLDAPLOCALE,
				new AdminLdapLocale(id));
		deleteByCriteria(AdminLdapLocalesOrgMapping.class, criteria);

		for (AdminLdapLocalesOrgMapping mappingObj : adminLdapLocalesOrgMappingsList) {
			mappingObj.setAdminLdapLocale(new AdminLdapLocale(id));
			if (mappingObj.getId() == 0) {
				mappingObj.setId(addNew(mappingObj));
			}
		}
	}

	@Override
	public void deleteAdminLocales(List<AdminLdapLocale> deleteAdminLocalesList) {
		deleteAll(deleteAdminLocalesList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AdminCallhomeGeneral> fetchAdminCallHomeGeneralSetting(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminCallhomeGeneral>) (List<?>) listAll(
				AdminCallhomeGeneral.class, criteria);
	}

	@Override
	public List<AdminCallhomeGeneral> saveOrUpdateAdminCallHomeGeneralSetting(
			List<AdminCallhomeGeneral> adminCallHomeGeneralSettingList,
			Integer projectId) {
		for (AdminCallhomeGeneral acghObj : adminCallHomeGeneralSettingList) {
			acghObj.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (acghObj.getId() == 0) {
				acghObj.setId(addNew(acghObj));
			} else {
				update(acghObj);
			}
		}
		return adminCallHomeGeneralSettingList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCallhomeSystemInventory> fetchAdminCallHomeSystemInventory(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminCallhomeSystemInventory>) (List<?>) listAll(
				AdminCallhomeSystemInventory.class, criteria);
	}

	@Override
	public void saveOrUpdateAdminCallHomeSystemInventory(
			List<AdminCallhomeSystemInventory> adminCallHomeSystemInventoryList,
			Integer projectId) {
		for (AdminCallhomeSystemInventory achsiObj : adminCallHomeSystemInventoryList) {
			achsiObj.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (achsiObj.getId() == 0) {
				achsiObj.setId(addNew(achsiObj));
			} else {
				update(achsiObj);
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCallhomeAlertGroup> fetchAdminCallHomeAlertGroup(
			Integer projectId) {
		return (List<AdminCallhomeAlertGroup>) (List<?>) listAll(AdminCallhomeAlertGroup.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCallhomePolicy> fetchAdminCallHomePolicies(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminCallhomePolicy>) (List<?>) listAll(
				AdminCallhomePolicy.class, criteria);
	}

	@Override
	public List<AdminCallhomePolicy> saveOrUpdateAdminCallHomePolicies(
			List<AdminCallhomePolicy> adminCallHomePolicyList, Integer projectId) {
		for (AdminCallhomePolicy adminChpObj : adminCallHomePolicyList) {
			adminChpObj.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (adminChpObj.getId() == 0) {
				adminChpObj.setId(addNew(adminChpObj));
			} else {
				update(adminChpObj);
			}
		}
		return adminCallHomePolicyList;
	}

	@Override
	public void deleteAdminCallHomePolicies(
			List<AdminCallhomePolicy> deletedAdminCallHomePolicyList) {
		deleteAll(deletedAdminCallHomePolicyList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCallhomeProfile> fetchAdminCallHomeProfiles(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminCallhomeProfile>) (List<?>) listAll(
				AdminCallhomeProfile.class, criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCallhomeProfileAlertGroupMapping> fetchCallHomeProfileAlertGroupMappings(
			Integer profileId) {
		Criterion criteria = Restrictions
				.eq(ADMINCALLHOMEPROFILE_ID, profileId);
		return (List<AdminCallhomeProfileAlertGroupMapping>) (List<?>) listAll(
				AdminCallhomeProfileAlertGroupMapping.class, criteria);
	}

	@Override
	public List<AdminCallhomeProfile> saveOrUpdateAdminCallHomeProfile(
			List<AdminCallhomeProfile> adminCallHomeProfileList,
			Integer projectId) {
		for (AdminCallhomeProfile achp : adminCallHomeProfileList) {
			achp.setProjectDetails(commonUtilServices
					.getProjectDetailsObject(projectId));
			if (achp.getId() == 0) {
				achp.setId(addNew(achp));

			} else {
				update(achp);
			}

			saveOrUpdateCallHomeAlertGroupMappings(
					achp.getAdminCallhomeProfileAlertGroupMappings(),
					achp.getId());
		}
		return adminCallHomeProfileList;
	}

	@Override
	public List<AdminCallhomeProfileAlertGroupMapping> saveOrUpdateCallHomeAlertGroupMappings(
			List<AdminCallhomeProfileAlertGroupMapping> adminCallhomeProfileAlertGroupMapping,
			Integer profileId) {
		Criterion criteria = Restrictions
				.eq(ADMINCALLHOMEPROFILE_ID, profileId);
		deleteByCriteria(AdminCallhomeProfileAlertGroupMapping.class, criteria);
		for (Object obj : adminCallhomeProfileAlertGroupMapping) {
			AdminCallhomeProfileAlertGroupMapping mappingObj = (AdminCallhomeProfileAlertGroupMapping) obj;
			mappingObj.setAdminCallhomeProfile(new AdminCallhomeProfile(
					profileId));
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
		return adminCallhomeProfileAlertGroupMapping;

	}

	@Override
	public void deleteAdminCallHomeProfile(
			List<AdminCallhomeProfile> aCHProfileDeletedList) {
		deleteAll(aCHProfileDeletedList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCallhomeProfileRecipientMapping> fetchProfileRecipientsDetails(
			Integer profileId) {
		Criterion criteria = Restrictions
				.eq(ADMINCALLHOMEPROFILE_ID, profileId);
		return (List<AdminCallhomeProfileRecipientMapping>) (List<?>) listAll(
				AdminCallhomeProfileRecipientMapping.class, criteria);
	}

	@Override
	public List<AdminCallhomeProfileRecipientMapping> saveOrUpdateProfileRecipients(
			List<AdminCallhomeProfileRecipientMapping> profileRecipientsList) {
		for (AdminCallhomeProfileRecipientMapping achprm : profileRecipientsList) {
			if (achprm.getId() == 0) {
				achprm.setId(addNew(achprm));
			} else {
				update(achprm);
			}
		}
		return profileRecipientsList;
	}

	@Override
	public void deleteProfileRecipients(
			List<AdminCallhomeProfileRecipientMapping> deletedProfileRecipientsList) {
		deleteAll(deletedProfileRecipientsList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminBackupConfiguration> fetchAdminBackupConfig(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilServices.getProjectDetailsObject(projectId));
		return (List<AdminBackupConfiguration>) (List<?>) listAll(
				AdminBackupConfiguration.class, criteria);
	}

	@Override
	public List<AdminBackupConfiguration> saveOrUpdateBackupConfig(
			List<AdminBackupConfiguration> newBackupList, Integer projectId) {
		if (newBackupList != null) {
			for (AdminBackupConfiguration abc : newBackupList) {
				abc.setProjectDetails(commonUtilServices
						.getProjectDetailsObject(projectId));
				if (abc.getId() == 0) {
					abc.setId(addNew(abc));
				} else {
					update(abc);
				}
			}
		}
		return newBackupList;
	}

}