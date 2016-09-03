package com.cisco.ca.cstg.pdi.services;

import java.util.List;

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
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRole;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.Dns;
import com.cisco.ca.cstg.pdi.pojos.Ntp;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.TimeZone;

/**
 * @author pavkuma2
 * 
 */
public interface AdminService {

	/**
	 * This method saves/creates new Organizations details to the database
	 * 
	 * @param orgList
	 *            is a list of organizations
	 * @param projectId
	 *            active project id for which records need to be saved
	 * @return returns a List of Objects which were added to the database with
	 *         updated ids.
	 * 
	 */
	List<Organizations> saveOrUpdateOrganization(List<Organizations> orgList,
			Integer projectId);

	/**
	 * This method deletes Organizations details from the database
	 * 
	 * @param orgList
	 *            list of organizations to be deleted
	 * 
	 */
	boolean deleteOrganization(List<Organizations> orgList);

	/**
	 * This method fetches the root organizations related to project
	 * 
	 * @param projectId
	 *            active project id for which records need to be fetched
	 * @return organizations fetched from database
	 */
	Organizations fetchRootOrganization(Integer projectId);

	/**
	 * This method converts the list of organizations in json format string
	 * 
	 * @param list
	 *            active list of organizations of project needs to be converted
	 *            to json format
	 * @return json string from list of organizations
	 */
	String convertOrganizationsDetailsInJSonFormat(Organizations org);

	/**
	 * This method retrieves existing Dns details from the database
	 * 
	 * @param projectId
	 *            is the project id for which Dns details need to be fetched
	 * @return returns the list of Dns
	 */
	List<Dns> fetchDNSDetails(Integer projectId);

	/**
	 * This method adds new Dns details to the database
	 * 
	 * @param dnsObjList
	 *            is an List of dns pojo created to handle all Dns details
	 * @param projectId
	 *            active project id for which the record needs to be saved
	 * @return returns a List of Objects which were added to the databse with
	 *         updated ids.
	 */
	List<Dns> saveOrUpdateDNSConfiguration(List<Dns> dnsObjList,
			Integer projectId);

	/**
	 * This method deletes Dns details from the database
	 * 
	 * @param dnsObjList
	 *            is a list of DNS pojos to be deleted.
	 * 
	 */
	void deleteDNSConfiguration(List<Dns> dnsObjList);

	/**
	 * This method retrieves existing Ntp details from the database
	 * 
	 * @param projectId
	 *            is the project id for which Ntp details need to be fetched
	 * @return returns the list of Ntp
	 */
	List<Ntp> fetchNTPDetails(Integer projectId);

	/**
	 * This method updates Ntp details to the database
	 * 
	 * @param ntpObjList
	 *            is a list of pojos to be created or updated
	 * @param projectId
	 *            is the active project id.
	 */
	List<Ntp> saveOrUpdateNTPConfiguration(List<Ntp> ntpObjList,
			Integer projectId);

	/**
	 * This method deletes Ntp details to the database
	 * 
	 * @param ntpObjList
	 *            is a list of pojos to be deleted
	 */
	void deleteNTPConfiguration(List<Ntp> ntpObjList);

	/**
	 * This method retrieves existing Timezone details from the database
	 * 
	 * @param projectId
	 *            is the project id for which Timezone details need to be
	 *            fetched
	 * @return returns the timezone saved in database
	 */
	Integer fetchTimezoneDetails(Integer projectId);

	/**
	 * This method saves/creates time zone to the database. It also creates a
	 * new AdminSettings pojo if already not present
	 * 
	 * @param timeZone
	 *            is the timeZone selected
	 * @param projectId
	 *            is the active project id
	 * 
	 */
	void saveOrUpdateTimeZone(Integer timeZone, Integer projectId);

	/**
	 * This method returns time zone list from database.
	 * 
	 */
	List<TimeZone> fetchTimeZoneList();

	List<AdminAuthenticationDomain> fetchAdminAuthDomain(Integer projectId);

	List<AdminAuthenticationDomain> saveOrUpdateAuthDomain(List<AdminAuthenticationDomain> adminAuthDomainList,
			Integer projectId);

	void deleteAdminAuthDomain(List<AdminAuthenticationDomain> deletedAdminAuthDomainList);

	List<AdminLdapProvider> fetchLdapProvider(Integer projectId);

	List<AdminLdapProvider> saveOrUpdateLdapProvider(List<AdminLdapProvider> ldapProviderList,
			Integer projectId);

	void deleteLdapProvider(List<AdminLdapProvider> deletedLdapProviderList);

	AdminLdapGeneral fetchLdapGeneral(Integer projectId);

	Integer saveOrUpdateLdapGeneral(AdminLdapGeneral adminLdapGeneral,
			Integer projectId);

	List<AdminLdapProviderGroup> fetchAdminLdapProviderGroup(Integer projectId);

	List<AdminLdapProviderGroup> saveOrUpdateAdminLdapProviderGroup(
			List<AdminLdapProviderGroup> adminLdapProviderGroupList, Integer projectId);

	void deleteAdminLdapProviderGroup(List<AdminLdapProviderGroup> adminLdapProviderGroupList,
			Integer projectId);

	List<AdminLdapGroupProviderMapping> fetchAdminLdapGroupProviderMapping(Integer id);

	List<AdminLdapGroupMap> fetchLdapGroupMapsInfo(Integer projectId);

	List<AdminLdapGroupMap> saveOrUpdateAdminLdapGroupMaps(List<AdminLdapGroupMap> ldapGroupMapsList,
			Integer projectId);

	void deleteLdapGroupMaps(List<AdminLdapGroupMap> ldapGroupMapsList);

	// Radius_general
	AdminRadiusGeneral fetchRadiusGeneral(Integer projectId);

	Integer saveOrUpdateRadiusGeneral(AdminRadiusGeneral radiusGeneral,
			Integer projectId);

	// Radius_Provider
	List<AdminRadiusProvider> fetchRadiusProvider(Integer projectId);

	List<AdminRadiusProvider> saveOrUpdateRadiusProvider(List<AdminRadiusProvider> radiusProviderList,
			Integer projectId);

	void deleteRadiusProvider(List<AdminRadiusProvider> deleteRadiusProviderList);

	// Radius_Provider_Group
	List<AdminRadiusProviderGroup> fetchRadiusProviderGroup(Integer projectId);

	List<AdminRadiusProviderGroup> saveOrUpdateRadiusProviderGroup(
			List<AdminRadiusProviderGroup> radiusProviderGroupList, Integer projectId);

	void deleteRadiusProviderGroup(List<AdminRadiusProviderGroup> deleteRadiusProviderGroupList,
			Integer projectId);

	// Radius_Provider_Group
	List<AdminRadiusGroupProviderMapping> fetchRadiusProviderGroupMapping(Integer projectId);

	void deleteRadiusProviderGroupMapping(
			List<AdminRadiusGroupProviderMapping> deleteRadiusProviderGroupMappingList);

	List<AdminTacacsProvider> fetchTacacsProvider(Integer projectId);

	List<AdminTacacsProvider> saveOrUpdateTacacsProvider(List<AdminTacacsProvider> tacacsProviderList,
			Integer projectId);

	void deleteTacacsProvider(List<AdminTacacsProvider> deletedTacacsProviderList);

	AdminTacacsGeneral fetchTacacsGeneral(Integer projectId);

	Integer saveOrUpdateTacacsGeneral(AdminTacacsGeneral adminTacacsGeneral,
			Integer projectId);

	List<AdminTacacsProviderGroup> fetchAdminTacacsProviderGroup(Integer projectId);

	List<AdminTacacsProviderGroup> saveOrUpdateAdminTacacsProviderGroup(
			List<AdminTacacsProviderGroup> adminTacacsProviderGroupList, Integer projectId);

	void deleteAdminTacacsProviderGroup(
			List<AdminTacacsProviderGroup> adminTacacsProviderGroupList, Integer projectId);	

	List<AdminLdapRole> fetchAdminRoles(Integer projectId);

	List<AdminLdapRole> saveOrUpdateAdminRoles(List<AdminLdapRole> adminRolesList,
			Integer projectId);

	void deleteAdminRoles(List<AdminLdapRole> deleteAdminRolesList);

	List<AdminLdapLocale> fetchAdminLocales(Integer projectId);

	List<AdminLdapLocale> saveOrUpdateAdminLocales(List<AdminLdapLocale> adminLocalesList,
			Integer projectId);

	void deleteAdminLocales(List<AdminLdapLocale> deleteAdminLocalesList);

	List<AdminCallhomeGeneral> fetchAdminCallHomeGeneralSetting(Integer projectId);

	List<AdminCallhomeGeneral> saveOrUpdateAdminCallHomeGeneralSetting(
			List<AdminCallhomeGeneral> adminCallHomeGeneralSettingList, Integer projectId);

	List<AdminCallhomeSystemInventory> fetchAdminCallHomeSystemInventory(Integer generalId);

	void saveOrUpdateAdminCallHomeSystemInventory(
			List<AdminCallhomeSystemInventory> adminCallHomeSystemInventoryList, Integer projectId);

	List<AdminCallhomeAlertGroup> fetchAdminCallHomeAlertGroup(Integer projectId);

	List<AdminCallhomeProfile> fetchAdminCallHomeProfiles(Integer generalId);

	List<AdminCallhomeProfileAlertGroupMapping> fetchCallHomeProfileAlertGroupMappings(Integer profileId);

	List<AdminCallhomePolicy> fetchAdminCallHomePolicies(Integer projectId);

	List<AdminCallhomePolicy> saveOrUpdateAdminCallHomePolicies(
			List<AdminCallhomePolicy> adminCallHomePolicyList, Integer projectId);

	void deleteAdminCallHomePolicies(List<AdminCallhomePolicy> deletedAdminCallHomePolicyList);

	List<AdminCallhomeProfile> saveOrUpdateAdminCallHomeProfile(
			List<AdminCallhomeProfile> adminCallHomeProfileList, Integer projectId);

	List<AdminCallhomeProfileAlertGroupMapping> saveOrUpdateCallHomeAlertGroupMappings(
			List<AdminCallhomeProfileAlertGroupMapping> adminCallhomeProfileAlertGroupMapping,
			Integer profileId);

	void deleteAdminCallHomeProfile(List<AdminCallhomeProfile> aCHProfileDeletedList);

	List<AdminCallhomeProfileRecipientMapping> fetchProfileRecipientsDetails(Integer profileId);
	
	List<AdminCallhomeProfileRecipientMapping> saveOrUpdateProfileRecipients(
			List<AdminCallhomeProfileRecipientMapping> profileRecipientsList);
			
	void deleteProfileRecipients(List<AdminCallhomeProfileRecipientMapping> deletedProfileRecipientsList);

	List<AdminBackupConfiguration> fetchAdminBackupConfig(Integer projectId);

	List<AdminBackupConfiguration> saveOrUpdateBackupConfig(List<AdminBackupConfiguration> newBackupList,
			Integer projectId);
}