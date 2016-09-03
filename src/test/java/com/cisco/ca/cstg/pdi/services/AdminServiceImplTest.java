package com.cisco.ca.cstg.pdi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.ca.cstg.pdi.pojos.AdminAuthenticationDomain;
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
import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.Dns;
import com.cisco.ca.cstg.pdi.pojos.Ntp;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.TimeZone;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class AdminServiceImplTest implements TestConstants {

	private AdminServiceImpl adminService;	

	@Autowired
	private CommonUtilServices commonUtilServices;

	@Autowired
	private SessionFactory hibernateSessionFactory;

	@Before
	public void setUp() {

		adminService = new AdminServiceImpl(hibernateSessionFactory,
				commonUtilServices);
	}

	@Test
	@Transactional
	public void saveOrUpdateOrganization() {
		List<Organizations> orgList = createOrganizationList();
		List<Object> beforeOrgListEntry = adminService
				.listAll(Organizations.class);
		adminService.saveOrUpdateOrganization(orgList, PROJECT_ID);

		List<Object> afterOrgListEntry = adminService
				.listAll(Organizations.class);
		assertEquals((beforeOrgListEntry.size() + orgList.size()),
				afterOrgListEntry.size());

	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateOrganization_NullFirstParameter() {

		adminService.saveOrUpdateOrganization(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateOrganization_NullSecondParameter() {
		List<Organizations> orgList = createOrganizationList();
		adminService.saveOrUpdateOrganization(orgList, null);
	}

	@Test
	public void fetchRootOrganization() {
		Organizations org = adminService.fetchRootOrganization(PROJECT_ID);
		assertNotNull(org);
	}

	@Test(expected = Exception.class)
	public void fetchRootOrganizations_NullProjectId() {
		adminService.fetchRootOrganization(null);
	}

	@Ignore
	@Test
	public void deleteOrganization() {

		try {
			List<Organizations> orgList = createOrganizationList();
			adminService.deleteOrganization(orgList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void deleteOrganization_NullList() {

		boolean value = adminService.deleteOrganization(null);
		assertEquals(false, value);
	}

	@Test
	public void saveOrUpdateDNSConfiguration() {

		List<Dns> dnsList = createDNSList();

		List<Dns> beforeDNSListEntry = adminService.fetchDNSDetails(PROJECT_ID);

		adminService.saveOrUpdateDNSConfiguration(dnsList, PROJECT_ID);

		List<Dns> afterDNSListEntry = adminService.fetchDNSDetails(PROJECT_ID);

		assertEquals((beforeDNSListEntry.size() + dnsList.size()),
				afterDNSListEntry.size());

	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateDNSConfiguration_NullFirstParameter() {

		adminService.saveOrUpdateDNSConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateDNSConfiguration_NullSecondParameter() {
		// TODO not throwing null pointer exception
		List<Dns> dnsList = createDNSList();

		adminService.saveOrUpdateDNSConfiguration(dnsList, null);
	}

	@Test
	public void fetchDNSDetails() {
		List<Dns> dnsList = adminService.fetchDNSDetails(PROJECT_ID);
		assertNotNull(dnsList);
		assertTrue(dnsList.size() >= 0);

	}

	@Test
	public void fetchDNSDetails_NullProjectId() {
		List<Dns> dnsList = adminService.fetchDNSDetails(null);
		assertEquals(dnsList.size(), 0);

	}

	@Test
	public void deleteDNSConfiguration() {

		try {
			List<Dns> dnsList = createDNSList();
			adminService.deleteDNSConfiguration(dnsList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = NullPointerException.class)
	public void deleteDNSConfiguration_NullList() {

		adminService.deleteDNSConfiguration(null);
	}

	@Test
	public void saveOrUpdateNTPConfiguration() {

		List<Ntp> ntpList = createNTPList();

		List<Ntp> beforeNTPListEntry = adminService.fetchNTPDetails(PROJECT_ID);

		adminService.saveOrUpdateNTPConfiguration(ntpList, PROJECT_ID);

		List<Ntp> afterNTPListEntry = adminService.fetchNTPDetails(PROJECT_ID);

		assertEquals((beforeNTPListEntry.size() + ntpList.size()),
				afterNTPListEntry.size());

	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateNTPConfiguration_NullFirstParameter() {

		adminService.saveOrUpdateNTPConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateNTPConfiguration_NullSecondParameter() {
		// TODO not throwing null pointer exception
		List<Ntp> ntpList = createNTPList();

		adminService.saveOrUpdateNTPConfiguration(ntpList, null);
	}

	@Test
	public void fetchNTPDetails() {
		List<Ntp> ntpList = adminService.fetchNTPDetails(PROJECT_ID);
		assertNotNull(ntpList);
		assertTrue(ntpList.size() >= 0);

	}

	@Test
	public void fetchNTPDetails_NullProjectId() {
		List<Ntp> ntpList = adminService.fetchNTPDetails(null);
		assertEquals(ntpList.size(), 0);

	}

	@Test
	public void deleteNTPConfiguration() {

		try {
			List<Ntp> ntpList = createNTPList();
			adminService.deleteNTPConfiguration(ntpList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = NullPointerException.class)
	public void deleteNTPConfiguration_NullList() {

		adminService.deleteNTPConfiguration(null);
	}

	@Test
	public void saveOrUpdateTimeZone() {
		adminService.saveOrUpdateTimeZone(3, PROJECT_ID);
		assertEquals(3, (int) adminService.fetchTimezoneDetails(PROJECT_ID));
	}

	@Test
	public void fetchTimezoneDetails() {
		assertNotNull(adminService.fetchTimezoneDetails(PROJECT_ID));
	}

	@Test
	public void convertOrganizationsDetailsInJSonFormat() {

		Organizations org = new Organizations(2);
		org.setName("parent");
		org.setParentId(1);

		Organizations childOrg1 = new Organizations(3);
		childOrg1.setName("child");
		childOrg1.setParentId(2);

		Organizations childOrg2 = new Organizations(4);
		childOrg2.setName("grandchild");
		childOrg2.setParentId(3);

		List<Organizations> list = new ArrayList<Organizations>();
		list.add(childOrg1);

		org.setOrganizationses(list);

		List<Organizations> set1 = new ArrayList<Organizations>();
		set1.add(childOrg2);

		childOrg1.setOrganizationses(set1);

		String expectedValue = "\"{\\\"id\\\":2, \\\"parentId\\\":1, \\\"name\\\": \\\"parent\\\", \\\"depth\\\": 0, \\\"children\\\":[{\\\"id\\\":3, \\\"parentId\\\":2, \\\"name\\\": \\\"child\\\", \\\"depth\\\": 1, \\\"children\\\":[{\\\"id\\\":4, \\\"parentId\\\":3, \\\"name\\\": \\\"grandchild\\\", \\\"depth\\\": 2}]}]}\"";

		String jsonString = adminService
				.convertOrganizationsDetailsInJSonFormat(org);
		assertNotNull(jsonString);
		assertEquals(expectedValue, jsonString);
	}

	@Test(expected = NullPointerException.class)
	public void convertOrganizationsDetailsInJSonFormat_NullList() {

		adminService.convertOrganizationsDetailsInJSonFormat(null);
	}

	@Test
	public void fetchTimeZoneList_NotNull() {
		List<TimeZone> list = adminService.fetchTimeZoneList();
		assertNotNull(list);
	}

	@Test
	public void fetchTimeZoneList_SizeCheck() {
		List<TimeZone> list = adminService.fetchTimeZoneList();
		assertEquals(383, list.size());
	}

	@Test
	public void fetchAdminAuthDomain() {
		assertNotNull(adminService.fetchAdminAuthDomain(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchAdminAuthDomain_NullParameter() {
		adminService.fetchAdminAuthDomain(null);
	}

	@Test
	public void saveOrUpdateAuthDomain() {
		try {
			List<AdminAuthenticationDomain> aadList = createAAD();
			AdminAuthenticationDomain aad = aadList.get(0);
			aad.setName("Test");
			adminService.saveOrUpdateAuthDomain(aadList, PROJECT_ID);
			aad = (AdminAuthenticationDomain) adminService.findById(
					AdminAuthenticationDomain.class, aad.getId());
			assertEquals("Test", aad.getName());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAuthDomain_NullFirstParameter() {
		adminService.saveOrUpdateAuthDomain(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAuthDomain_NullSecondParameter() {
		adminService.saveOrUpdateAuthDomain(createAAD(), null);
	}

	@Test
	public void deleteAdminAuthDomain() {
		try {
			List<AdminAuthenticationDomain> aadList = createAAD();
			AdminAuthenticationDomain aad = aadList.get(0);
			aad.setName("Test");
			adminService.saveOrUpdateAuthDomain(aadList, PROJECT_ID);
			adminService.deleteAdminAuthDomain(aadList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteAdminAuthDomain_NullParameter() {
		adminService.deleteAdminAuthDomain(null);
	}

	private List<Dns> createDNSList() {

		List<Dns> dnsList = new ArrayList<>();

		Dns dns = new Dns(0);
		dns.setIpAddress("1.2.3.4");

		dnsList.add(dns);

		return dnsList;
	}

	private List<Ntp> createNTPList() {

		List<Ntp> ntpList = new ArrayList<>();

		Ntp ntp = new Ntp(0);
		ntp.setIpAddress("1.2.3.4");

		ntpList.add(ntp);

		return ntpList;
	}

	private List<Organizations> createOrganizationList() {

		List<Organizations> orgList = new ArrayList<>();

		Organizations org = new Organizations(0);
		org.setName("ParentTest");
		org.setParentId(1);

		orgList.add(org);

		return orgList;
	}

	private List<AdminAuthenticationDomain> createAAD() {
		AdminAuthenticationDomain aad = new AdminAuthenticationDomain(0);
		aad.setName("Test");
		aad.setProviderGroup(null);
		aad.setRealm("local");
		aad.setRefreshPeriod(60);
		aad.setSessionTimeout(17200);
		List<AdminAuthenticationDomain> aadList = new ArrayList<>();
		aadList.add(aad);
		return aadList;
	}

	@Test
	public void saveOrUpdateAdminRoles() {
		try {
			List<AdminLdapRole> ldapRoles = createAR();
			AdminLdapRole adminLdapRole = ldapRoles.get(0);
			adminService.saveOrUpdateAdminRoles(ldapRoles, PROJECT_ID);
			adminLdapRole = (AdminLdapRole) adminService.findById(
					AdminLdapRole.class, adminLdapRole.getId());
			assertEquals("Test", adminLdapRole.getName());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminRoles_NullFirstParameter() {
		adminService.saveOrUpdateAdminRoles(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminRoles_NullSecondParameter() {
		AdminLdapRole adminLdapRole = new AdminLdapRole();
		adminLdapRole.setName("Test");
		List<AdminLdapRole> ldapRoles = new ArrayList<AdminLdapRole>();
		ldapRoles.add(adminLdapRole);
		adminService.saveOrUpdateAdminRoles(ldapRoles, null);
	}

	@Test
	public void deleteAdminRoles() {
		try {
			List<AdminLdapRole> ldapRoles = createAR();
			adminService.saveOrUpdateAdminRoles(ldapRoles, PROJECT_ID);
			adminService.deleteAdminRoles(ldapRoles);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	private List<AdminLdapRole> createAR() {
		AdminLdapRole ar = new AdminLdapRole(0);
		ar.setName("Test");
		AdminLdapRolesPrivilegesMapping alrpm = new AdminLdapRolesPrivilegesMapping(
				0);
		alrpm.setAdminLdapRole(adminService.fetchAdminRoles(PROJECT_ID).get(0));
		AdminPrivilege ap = new AdminPrivilege(0);
		ap.setName("Test");
		adminService.addNew(ap);
		alrpm.setAdminPrivilege(ap);
		List<AdminLdapRolesPrivilegesMapping> alrpmList = new ArrayList<>();
		alrpmList.add(alrpm);
		ar.setAdminLdapRolesPrivilegesMappings(alrpmList);
		List<AdminLdapRole> arList = new ArrayList<AdminLdapRole>();
		arList.add(ar);
		return arList;
	}

	@Test
	public void saveOrUpdateAdminLocale() {
		try {
			List<AdminLdapLocale> ldapLocales = createAL();
			AdminLdapLocale adminLdapLocale = ldapLocales.get(0);
			adminService.saveOrUpdateAdminLocales(ldapLocales, PROJECT_ID);
			adminLdapLocale = (AdminLdapLocale) adminService.findById(
					AdminLdapLocale.class, adminLdapLocale.getId());
			assertEquals("Test", adminLdapLocale.getName());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminLocale_NullFirstParameter() {
		adminService.saveOrUpdateAdminLocales(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminLocales_NullSecondParameter() {
		AdminLdapLocale adminLdapLocale = new AdminLdapLocale();
		adminLdapLocale.setName("Test");
		List<AdminLdapLocale> ldapLocales = new ArrayList<AdminLdapLocale>();
		ldapLocales.add(adminLdapLocale);
		adminService.saveOrUpdateAdminLocales(ldapLocales, null);
	}

	@Test
	public void deleteAdminLocales() {
		try {
			List<AdminLdapLocale> ldapLocales = createAL();
			adminService.saveOrUpdateAdminLocales(ldapLocales, PROJECT_ID);
			adminService.deleteAdminLocales(ldapLocales);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	private List<AdminLdapLocale> createAL() {
		AdminLdapLocale adminLdapLocale = new AdminLdapLocale(0);
		adminLdapLocale.setName("Test");
		AdminLdapLocalesOrgMapping allom = new AdminLdapLocalesOrgMapping(0);
		allom.setOrganizations(adminService.fetchRootOrganization(PROJECT_ID));
		List<AdminLdapLocalesOrgMapping> allomList = new ArrayList<>();
		allomList.add(allom);
		adminLdapLocale.setAdminLdapLocalesOrgMappings(allomList);
		List<AdminLdapLocale> adminLdapLocaleList = new ArrayList<AdminLdapLocale>();
		adminLdapLocaleList.add(adminLdapLocale);
		return adminLdapLocaleList;
	}

	@Test
	public void saveOrUpdateLdapGeneral() {
		try {
			AdminLdapGeneral alg = new AdminLdapGeneral(0);
			alg.setAttribute("testAttr");
			alg.setBaseDn("baseDn");
			alg.setFilter("filter");
			alg.setTimeout("20");

			adminService.saveOrUpdateLdapGeneral(alg, PROJECT_ID);
			alg = (AdminLdapGeneral) adminService.findById(
					AdminLdapGeneral.class, alg.getId());
			assertEquals("20", alg.getTimeout());
			try {
				adminService.fetchLdapGeneral(PROJECT_ID);
			} catch (Exception e) {
				adminService.delete(alg);
			}
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void fetchLdapGeneral() {
		assertNotNull(adminService.fetchLdapGeneral(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLdapGeneral_NullParameter() {
		adminService.fetchLdapGeneral(null);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLdapGeneral_NullFirstParameter() {
		adminService.saveOrUpdateLdapGeneral(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLdapGeneral_NullSecondParameter() {
		AdminLdapGeneral alg = new AdminLdapGeneral(0);
		adminService.saveOrUpdateLdapGeneral(alg, null);
	}

	@Test
	public void fetchLdapProvider() {
		assertNotNull(adminService.fetchLdapProvider(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLdapProvider_NullParameter() {
		adminService.fetchLdapProvider(null);
	}

	@Test
	public void saveOrUpdateLdapProvider() {
		try {
			List<AdminLdapProvider> alpList = createALP();
			AdminLdapProvider alp = (alpList.get(0));
			adminService.saveOrUpdateLdapProvider(alpList, PROJECT_ID);
			alp = (AdminLdapProvider) adminService.findById(
					AdminLdapProvider.class, alp.getId());
			assertEquals(new Integer(23), alp.getTimeout());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLdapProvider_NullFirstParameter() {
		adminService.saveOrUpdateLdapProvider(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLdapProvider_NullSecondParameter() {
		adminService.saveOrUpdateLdapProvider(createALP(), null);
	}

	@Test
	public void deleteLdapProvider() {
		try {
			List<AdminLdapProvider> alpList = createALP();
			adminService.saveOrUpdateLdapProvider(alpList, PROJECT_ID);
			adminService.deleteLdapProvider(alpList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLdapProvider_NullParameter() {
		adminService.deleteLdapProvider(null);
	}	

	@Test(expected = Exception.class)
	public void fetchAdminLdapProviderGroup_NullParameter() {
		adminService.fetchAdminLdapProviderGroup(null);
	}

	@Test
	public void saveOrUpdateAdminLdapProviderGroup() {
		try {
			List<AdminLdapProviderGroup> alpgList = createALPG();
			AdminLdapProviderGroup alpg = (alpgList.get(0));
			adminService.saveOrUpdateAdminLdapProviderGroup(alpgList,
					PROJECT_ID);
			alpg = (AdminLdapProviderGroup) adminService.findById(
					AdminLdapProviderGroup.class, alpg.getId());
			assertNotNull(alpg);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchAdminLdapProviderGroup() {
		assertNotNull(adminService.fetchAdminLdapProviderGroup(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminLdapProviderGroup_NullFirstParameter() {
		adminService.saveOrUpdateAdminLdapProviderGroup(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminLdapProviderGroup_NullSecondParameter() {
		adminService.saveOrUpdateAdminLdapProviderGroup(createALPG(), null);
	}

	@Test
	public void deleteAdminLdapProviderGroup() {
		try {
			List<AdminLdapProviderGroup> alpgList = createALPG();
			adminService.saveOrUpdateAdminLdapProviderGroup(alpgList,
					PROJECT_ID);
			adminService.deleteAdminLdapProviderGroup(alpgList, PROJECT_ID);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteAdminLdapProviderGroup_NullFirstParameter() {
		adminService.deleteAdminLdapProviderGroup(null, PROJECT_ID);
	}

	@Ignore
	@Test(expected = Exception.class)
	// second parameter is not in use.
	public void deleteAdminLdapProviderGroup_NullSecondParameter() {
		List<AdminLdapProviderGroup> alpgList = createALPG();
		adminService.deleteAdminLdapProviderGroup(alpgList, null);
	}

	private List<AdminLdapProviderGroup> createALPG() {
		AdminLdapProviderGroup alpg = new AdminLdapProviderGroup(0);
		alpg.setName("ALPG");
		AdminLdapGroupProviderMapping algpm = new AdminLdapGroupProviderMapping();
		algpm.setAdminLdapProviderGroup(alpg);
		algpm.setId(0);
		algpm.setProviderOrder(1);

		List<AdminLdapProvider> alpList = createALP();
		AdminLdapProvider alp = (alpList.get(0));
		adminService.saveOrUpdateLdapProvider(alpList, PROJECT_ID);
		alp = (AdminLdapProvider) adminService.findById(
				AdminLdapProvider.class, alp.getId());
		algpm.setAdminLdapProvider(alp);

		List<AdminLdapGroupProviderMapping> algpmList = new ArrayList<>();
		algpmList.add(algpm);

		alpg.setAdminLdapGroupProviderMappings(algpmList);

		List<AdminLdapProviderGroup> alpgList = new ArrayList<AdminLdapProviderGroup>();
		alpgList.add(alpg);

		return alpgList;
	}

	private List<AdminLdapProvider> createALP() {
		AdminLdapProvider alp = new AdminLdapProvider(0);
		List<AdminLdapProvider> list = new ArrayList<AdminLdapProvider>();
		alp.setAttribute("attr");
		alp.setBaseDn("baseDn");
		alp.setBindDn("bindDn");
		alp.setEnableSsl("enable");
		alp.setFilter("filter");
		alp.setGroupAuthorization("enable");
		alp.setGroupRecursion("recursive");
		alp.setHostname("hostName");
		alp.setPort(230);
		alp.setProviderOrder(1);
		alp.setTargetAttribute("");
		alp.setTimeout(23);
		alp.setVendor("open-ldap");
		alp.setUsePrimaryGroup("yes");

		list.add(alp);
		return list;
	}

	@Test
	public void saveOrUpdateTacacsGeneral() {
		try {
			AdminTacacsGeneral atp = new AdminTacacsGeneral(0);
			atp.setTimeout("23");

			adminService.saveOrUpdateTacacsGeneral(atp, PROJECT_ID);
			atp = (AdminTacacsGeneral) adminService.findById(
					AdminTacacsGeneral.class, atp.getId());
			assertEquals("23", atp.getTimeout());
			try {
				adminService.fetchTacacsGeneral(PROJECT_ID);
			} catch (Exception e) {
				adminService.delete(atp);
			}
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void fetchTacacsGeneral() {
		assertNotNull(adminService.fetchTacacsGeneral(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchTacacsGeneral_NullParameter() {
		adminService.fetchTacacsGeneral(null);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateTacacsGeneral_NullFirstParameter() {
		adminService.saveOrUpdateTacacsGeneral(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateTacacsGeneral_NullSecondParameter() {
		AdminTacacsGeneral atp = new AdminTacacsGeneral(0);
		adminService.saveOrUpdateTacacsGeneral(atp, null);
	}

	@Test
	public void fetchTacacsProvider() {
		assertNotNull(adminService.fetchTacacsProvider(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchTacacsProvider_NullParameter() {
		adminService.fetchTacacsProvider(null);
	}

	@Test
	public void saveOrUpdateTacacsProvider() {
		try {
			List<AdminTacacsProvider> alpList = createATP();
			AdminTacacsProvider alp = (alpList.get(0));
			adminService.saveOrUpdateTacacsProvider(alpList, PROJECT_ID);
			alp = (AdminTacacsProvider) adminService.findById(
					AdminTacacsProvider.class, alp.getId());
			assertEquals(new Integer(23), alp.getTimeout());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateTacacsProvider_NullFirstParameter() {
		adminService.saveOrUpdateTacacsProvider(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateTacacsProvider_NullSecondParameter() {
		adminService.saveOrUpdateTacacsProvider(createATP(), null);
	}

	@Test
	public void deleteTacacsProvider() {
		try {
			List<AdminTacacsProvider> alpList = createATP();
			adminService.saveOrUpdateTacacsProvider(alpList, PROJECT_ID);
			adminService.deleteTacacsProvider(alpList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteTacacsProvider_NullParameter() {
		adminService.deleteTacacsProvider(null);
	}

	@Test
	public void fetchAdminTacacsProviderGroup() {
		assertNotNull(adminService.fetchAdminTacacsProviderGroup(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchAdminTacacsProviderGroup_NullParameter() {
		adminService.fetchAdminTacacsProviderGroup(null);
	}

	@Test
	public void saveOrUpdateAdminTacacsProviderGroup() {
		try {
			List<AdminTacacsProviderGroup> alpgList = createATPG();
			AdminTacacsProviderGroup alpg = (alpgList.get(0));
			adminService.saveOrUpdateAdminTacacsProviderGroup(alpgList,
					PROJECT_ID);
			alpg = (AdminTacacsProviderGroup) adminService.findById(
					AdminTacacsProviderGroup.class, alpg.getId());
			assertNotNull(alpg);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminTacacsProviderGroup_NullFirstParameter() {
		adminService.saveOrUpdateAdminTacacsProviderGroup(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminTacacsProviderGroup_NullSecondParameter() {
		adminService.saveOrUpdateAdminTacacsProviderGroup(createATPG(), null);
	}

	@Test
	public void deleteAdminTacacsProviderGroup() {
		try {
			List<AdminTacacsProviderGroup> alpgList = createATPG();
			adminService.saveOrUpdateAdminTacacsProviderGroup(alpgList,
					PROJECT_ID);
			adminService.deleteAdminTacacsProviderGroup(alpgList, PROJECT_ID);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteAdminTacacsProviderGroup_NullFirstParameter() {
		adminService.deleteAdminTacacsProviderGroup(null, PROJECT_ID);
	}

	@Ignore
	@Test(expected = Exception.class)
	// second parameter is not in use
	public void deleteAdminTacacsProviderGroup_NullSecondParameter() {
		List<AdminTacacsProviderGroup> alpgList = createATPG();
		adminService.deleteAdminTacacsProviderGroup(alpgList, null);
	}

	private List<AdminTacacsProviderGroup> createATPG() {
		AdminTacacsProviderGroup atpg = new AdminTacacsProviderGroup(0);
		atpg.setName("ATPG");
		AdminTacacsGroupProviderMapping atppm = new AdminTacacsGroupProviderMapping();
		atppm.setAdminTacacsProviderGroup(atpg);
		atppm.setId(0);
		atppm.setProviderOrder(1);

		List<AdminTacacsProvider> alpList = createATP();
		AdminTacacsProvider alp = alpList.get(0);
		adminService.saveOrUpdateTacacsProvider(alpList, PROJECT_ID);
		alp = (AdminTacacsProvider) adminService.findById(
				AdminTacacsProvider.class, alp.getId());
		atppm.setAdminTacacsProvider(alp);
		List<AdminTacacsGroupProviderMapping> atppmList = new ArrayList<>();
		atppmList.add(atppm);

		atpg.setAdminTacacsGroupProviderMappings(atppmList);
		List<AdminTacacsProviderGroup> atpgList = new ArrayList<AdminTacacsProviderGroup>();
		atpgList.add(atpg);

		return atpgList;
	}

	private List<AdminTacacsProvider> createATP() {
		AdminTacacsProvider atp = new AdminTacacsProvider(0);
		List<AdminTacacsProvider> list = new ArrayList<AdminTacacsProvider>();
		atp.setHostname("hostName");
		atp.setPort(230);
		atp.setProviderOrder(1);
		atp.setTimeout(23);

		list.add(atp);
		return list;
	}

	@Test
	public void fetchLdapGroupMapsInfo() {
		assertNotNull(adminService.fetchLdapGroupMapsInfo(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLdapGroupMapsInfo_NullParameter() {
		adminService.fetchLdapGroupMapsInfo(null);
	}

	@Test
	public void saveOrUpdateAdminLdapGroupMaps() {
		try {
			List<AdminLdapGroupMap> algmList = createALGM();
			AdminLdapGroupMap algm = algmList.get(0);
			adminService.saveOrUpdateAdminLdapGroupMaps(algmList, PROJECT_ID);
			algm = (AdminLdapGroupMap) adminService.findById(
					AdminLdapGroupMap.class, algm.getId());
			assertNotNull(algm);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminLdapGroupMaps_NullFirstParameter() {
		adminService.saveOrUpdateAdminLdapGroupMaps(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateAdminLdapGroupMaps_NullSecondParameter() {
		adminService.saveOrUpdateAdminLdapGroupMaps(createALGM(), null);
	}

	@Test
	public void deleteLdapGroupMaps() {
		try {
			List<AdminLdapGroupMap> algmList = createALGM();
			adminService.saveOrUpdateAdminLdapGroupMaps(algmList, PROJECT_ID);
			adminService.deleteLdapGroupMaps(algmList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLdapGroupMaps_NullFirstParameter() {
		adminService.deleteLdapGroupMaps(null);
	}

	private List<AdminLdapGroupMap> createALGM() {
		AdminLdapGroupMap algm = new AdminLdapGroupMap(0);
		algm.setName("Test");

		AdminLdapGroupMapLocalesMapping algmlm = new AdminLdapGroupMapLocalesMapping(
				0);
		algmlm.setAdminLdapLocale(adminService.fetchAdminLocales(PROJECT_ID)
				.get(0));

		List<AdminLdapGroupMapLocalesMapping> algmlmList = new ArrayList<>();
		algmlmList.add(algmlm);

		algm.setAdminLdapGroupMapLocalesMappings(algmlmList);

		AdminLdapGroupMapRolesMapping algmrm = new AdminLdapGroupMapRolesMapping(
				0);
		algmrm.setAdminLdapRole(adminService.fetchAdminRoles(PROJECT_ID).get(0));

		List<AdminLdapGroupMapRolesMapping> algmrmList = new ArrayList<>();
		algmrmList.add(algmrm);

		algm.setAdminLdapGroupMapRolesMappings(algmrmList);
		List<AdminLdapGroupMap> list = new ArrayList<AdminLdapGroupMap>();
		list.add(algm);

		return list;
	}
}