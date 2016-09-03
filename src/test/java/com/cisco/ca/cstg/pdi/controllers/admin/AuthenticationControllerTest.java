package com.cisco.ca.cstg.pdi.controllers.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.AdminAuthenticationDomain;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProviderGroup;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class AuthenticationControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	AdminService adminServiceMock;

	@InjectMocks
	private AuthenticationController authenticationController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
				.build();
	}

	@Test
	public void showAdminAuthentication() throws Exception {
		mockMvc.perform(get("/adminAuthentication.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("admin/adminAuthentication"));
	}

	@Test
	public void getAdminAuthenticationDomainDetails_methodNameCheck()
			throws Exception {
		this.mockMvc.perform(
				get("/getAdminAuthenticationDomainDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminAuthenticationDomainDetails"));
	}

	@Test
	public void getAdminAuthenticationDomainDetails_returnTypeCheck()
			throws Exception {
		AdminAuthenticationDomain aad = new AdminAuthenticationDomain(1);
		aad.setName("Test");
		aad.setProviderGroup(null);
		aad.setRealm("local");
		aad.setRefreshPeriod(60);
		aad.setSessionTimeout(17200);
		List<AdminAuthenticationDomain> aadList = new ArrayList<>();
		aadList.add(aad);

		String expected = "[{\"id\":1,\"providerGroup\":null,\"realm\":\"local\",\"name\":\"Test\",\"twoFactor\":null,\"refreshPeriod\":60,\"sessionTimeout\":17200}]";
		when(adminServiceMock.fetchAdminAuthDomain(PROJECT_ID)).thenReturn(
				aadList);

		List<Object> jsonList = authenticationController
				.getAdminAuthenticationDomainDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminAuthDomain(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminAuthenticationDomainDetails_nullCheck()
			throws Exception {
		String expected = "[]";
		List<Object> jsonList = authenticationController
				.getAdminAuthenticationDomainDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchAdminAuthDomain(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminAuthenticationDomainDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminAuthDomain(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = authenticationController
				.getAdminAuthenticationDomainDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminAuthDomain(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminAuthenticationDomainDetails_exception()
			throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminAuthDomain(PROJECT_ID))
				.thenReturn(null);

		List<Object> jsonList = authenticationController
				.getAdminAuthenticationDomainDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminAuthDomain(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminAuthenticationDomain_methodNameCheck()
			throws Exception {

		mockMvc.perform(
				post("/manageAdminAuthenticationDomain.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminAuthenticationDomain"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminAuthenticationDomain_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"providerGroup\":null,\"realm\":\"local\",\"name\":\"Test\",\"twoFactor\":null,\"refreshPeriod\":60,\"sessionTimeout\":17200}], \"deleted\":[]}";
		AdminAuthenticationDomain aad = new AdminAuthenticationDomain(1);
		aad.setName("Test");
		aad.setProviderGroup(null);
		aad.setRealm("local");
		aad.setRefreshPeriod(60);
		aad.setSessionTimeout(17200);
		List<AdminAuthenticationDomain> aadList = new ArrayList<>();
		aadList.add(aad);

		when(adminServiceMock.saveOrUpdateAuthDomain(anyList(), anyInt()))
				.thenReturn(aadList);

		List<Object> list = authenticationController
				.manageAdminAuthenticationDomain(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"providerGroup\":null,\"realm\":\"local\",\"name\":\"Test\",\"twoFactor\":null,\"refreshPeriod\":60,\"sessionTimeout\":17200}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAuthDomain(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminAuthenticationDomain_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		AdminAuthenticationDomain aad = new AdminAuthenticationDomain(1);
		aad.setName("Test");
		aad.setProviderGroup(null);
		aad.setRealm("local");
		aad.setRefreshPeriod(60);
		aad.setSessionTimeout(17200);
		List<Object> aadList = new ArrayList<>();
		aadList.add(aad);

		doNothing().when(adminServiceMock).deleteAdminAuthDomain(anyList());

		aadList = authenticationController.manageAdminAuthenticationDomain(
				json, PROJECT_ID);
		String expected = "[]"; // TODO need to check it should be blank or not
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(1)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminAuthenticationDomain_nullCheck_firstParameter()
			throws Exception {

		when(adminServiceMock.saveOrUpdateAuthDomain(anyList(), anyInt()))
				.thenReturn(null);

		List<Object> lanQosList = authenticationController
				.manageAdminAuthenticationDomain(null, PROJECT_ID);
		String expected = "[]";
		// TODO does it should return blank?
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAuthDomain(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminAuthenticationDomain_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		when(adminServiceMock.saveOrUpdateAuthDomain(anyList(), anyInt()))
				.thenReturn(null);

		List<Object> lanQosList = authenticationController
				.manageAdminAuthenticationDomain(json, null);
		String expected = "[]";
		// TODO does it should return blank?
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAuthDomain(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminAuthenticationDomain_nullCheck_bothParameters()
			throws Exception {

		when(adminServiceMock.saveOrUpdateAuthDomain(anyList(), anyInt()))
				.thenReturn(null);

		List<Object> lanQosList = authenticationController
				.manageAdminAuthenticationDomain(null, null);
		String expected = "[]";
		// TODO does it should return blank?
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAuthDomain(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminAuthenticationDomain_returnNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"providerGroup\":null,\"realm\":\"local\",\"name\":\"Test\",\"twoFactor\":null,\"refreshPeriod\":60,\"sessionTimeout\":17200}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateAuthDomain(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> list = authenticationController
				.manageAdminAuthenticationDomain(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAuthDomain(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminAuthenticationDomain_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"providerGroup\":null,\"realm\":\"local\",\"name\":\"Test\",\"twoFactor\":null,\"refreshPeriod\":60,\"sessionTimeout\":17200}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateAuthDomain(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());

		authenticationController.manageAdminAuthenticationDomain(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAuthDomain(anyList(),
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminAuthenticationDomain_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminAuthDomain(anyList());

		authenticationController.manageAdminAuthenticationDomain(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteAdminAuthDomain(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapGeneralConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getLdapGeneralConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLdapGeneralConfig"));
	}

	@Test
	public void getLdapGeneralConfig_returnTypeCheck() throws Exception {
		AdminLdapGeneral alg = new AdminLdapGeneral(1);
		alg.setAttribute("testAttr");
		alg.setBaseDn("baseDn");
		alg.setFilter("filter");
		alg.setTimeout("20");

		String expected = "{\"id\":1,\"baseDn\":\"baseDn\",\"attribute\":\"testAttr\",\"timeout\":\"20\",\"filter\":\"filter\"}";
		when(adminServiceMock.fetchLdapGeneral(PROJECT_ID)).thenReturn(alg);

		String actual = authenticationController
				.getLdapGeneralConfig(PROJECT_ID);
		assertEquals(expected, actual);
		verify(adminServiceMock, times(1)).fetchLdapGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapGeneralConfig_nullCheck() throws Exception {
		String actual = authenticationController.getLdapGeneralConfig(null);
		assertEquals(null, actual);
		verify(adminServiceMock, times(0)).fetchLdapGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapGeneralConfig_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchLdapGeneral(anyInt())).thenReturn(null);
		String actual = authenticationController
				.getLdapGeneralConfig(PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).fetchLdapGeneral(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapGeneralConfig_exception() throws Exception {
		when(adminServiceMock.fetchLdapGeneral(PROJECT_ID)).thenReturn(null);
		String actual = authenticationController
				.getLdapGeneralConfig(PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).fetchLdapGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapGeneralConfig_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageLdapGeneralConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLdapGeneralConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapGeneralConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"id\":1,\"baseDn\":\"baseDn\",\"attribute\":\"testAttr\",\"timeout\":\"20\",\"filter\":\"filter\"}";
		AdminLdapGeneral alg = new AdminLdapGeneral(1);
		alg.setAttribute("testAttr");
		alg.setBaseDn("baseDn");
		alg.setFilter("filter");
		alg.setTimeout("20");
		when(
				adminServiceMock.saveOrUpdateLdapGeneral(
						any(AdminLdapGeneral.class), anyInt())).thenReturn(1);
		Integer actual = authenticationController.manageLdapGeneralConfig(json,
				PROJECT_ID);
		Integer expected = new Integer(1);
		assertEquals(expected, actual);
		verify(adminServiceMock, times(1)).saveOrUpdateLdapGeneral(
				any(AdminLdapGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapGeneralConfig_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateLdapGeneral(
						any(AdminLdapGeneral.class), anyInt()))
				.thenReturn(null);
		Integer actual = authenticationController.manageLdapGeneralConfig(null,
				PROJECT_ID);
		Integer expected = 0;
		assertEquals(expected, actual);
		verify(adminServiceMock, times(0)).saveOrUpdateLdapGeneral(
				any(AdminLdapGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapGeneralConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"id\":1,\"baseDn\":\"baseDn\",\"attribute\":\"testAttr\",\"timeout\":\"20\",\"filter\":\"filter\"}";
		Integer actual = authenticationController.manageLdapGeneralConfig(json,
				null);
		Integer expected = new Integer(0);
		assertEquals(expected, actual);
		verify(adminServiceMock, times(0)).saveOrUpdateLdapGeneral(
				any(AdminLdapGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapGeneralConfig_nullCheck_bothParameters()
			throws Exception {
		Integer actual = authenticationController.manageLdapGeneralConfig(null,
				null);
		Integer expected = new Integer(0);
		assertEquals(expected, actual);
		verify(adminServiceMock, times(0)).saveOrUpdateLdapGeneral(
				any(AdminLdapGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapGeneralConfig_returnNullCheck() throws Exception {
		String json = "{\"id\":1,\"baseDn\":\"baseDn\",\"attribute\":\"testAttr\",\"timeout\":\"20\",\"filter\":\"filter\"}";
		when(
				adminServiceMock.saveOrUpdateLdapGeneral(
						any(AdminLdapGeneral.class), anyInt()))
				.thenReturn(null);
		Integer actual = authenticationController.manageLdapGeneralConfig(json,
				PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).saveOrUpdateLdapGeneral(
				any(AdminLdapGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void manageLdapGeneralConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"id\":1,\"baseDn\":\"baseDn\",\"attribute\":\"testAttr\",\"timeout\":\"20\",\"filter\":\"filter\"}";
		when(
				adminServiceMock.saveOrUpdateLdapGeneral(
						any(AdminLdapGeneral.class), PROJECT_ID)).thenThrow(
				new NullPointerException());

		authenticationController.manageLdapGeneralConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateLdapGeneral(
				any(AdminLdapGeneral.class), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapProviderConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getLdapProviderConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLdapProviderConfig"));
	}

	@Test
	public void getLdapProviderConfig_returnTypeCheck() throws Exception {
		AdminLdapProvider alp = new AdminLdapProvider(1);
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
		List<AdminLdapProvider> alpList = new ArrayList<>();
		alpList.add(alp);
		String expected = "[{\"port\":230,\"groupAuthorization\":\"enable\",\"bindDn\":\"bindDn\",\"enableSsl\":\"enable\",\"baseDn\":\"baseDn\",\"vendor\":\"open-ldap\",\"hostname\":\"hostName\",\"attribute\":\"attr\",\"targetAttribute\":\"\",\"id\":1,\"usePrimaryGroup\":\"yes\",\"groupRecursion\":\"recursive\",\"providerPassword\":null,\"filter\":\"filter\",\"timeout\":23,\"providerOrder\":1}]";
		when(adminServiceMock.fetchLdapProvider(PROJECT_ID))
				.thenReturn(alpList);
		List<Object> jsonList = authenticationController
				.getLdapProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchLdapProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapProviderConfig_nullCheck() throws Exception {
		List<AdminLdapProvider> alpList = new ArrayList<>();
		String expected = "[]";
		when(adminServiceMock.fetchLdapProvider(PROJECT_ID))
				.thenReturn(alpList);
		List<Object> jsonList = authenticationController
				.getLdapProviderConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchLdapProvider(null);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapProviderConfig_returnNullCheck() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchLdapProvider(anyInt())).thenReturn(null);
		List<Object> jsonList = authenticationController
				.getLdapProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchLdapProvider(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getLdapProviderConfig_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchLdapProvider(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = authenticationController
				.getLdapProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchLdapProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageLdapProviderConfig_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageLdapProviderConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLdapProviderConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLdapProviderConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"groupAuthorization\":\"enable\",\"bindDn\":\"bindDn\",\"enableSsl\":\"enable\",\"baseDn\":\"baseDn\",\"vendor\":\"open-ldap\",\"hostname\":\"hostName\",\"attribute\":\"attr\",\"targetAttribute\":\"\",\"id\":1,\"usePrimaryGroup\":\"yes\",\"groupRecursion\":\"recursive\",\"providerPassword\":null,\"filter\":\"filter\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		AdminLdapProvider alp = new AdminLdapProvider(1);
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
		List<AdminLdapProvider> alpList = new ArrayList<>();
		alpList.add(alp);
		when(adminServiceMock.saveOrUpdateLdapProvider(anyList(), anyInt()))
				.thenReturn(alpList);
		List<Object> list = authenticationController.manageLdapProviderConfig(
				json, PROJECT_ID);
		String expected = "[{\"port\":230,\"groupAuthorization\":\"enable\",\"bindDn\":\"bindDn\",\"enableSsl\":\"enable\",\"baseDn\":\"baseDn\",\"vendor\":\"open-ldap\",\"hostname\":\"hostName\",\"attribute\":\"attr\",\"targetAttribute\":\"\",\"id\":1,\"usePrimaryGroup\":\"yes\",\"groupRecursion\":\"recursive\",\"providerPassword\":null,\"filter\":\"filter\",\"timeout\":23,\"providerOrder\":1}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateLdapProvider(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLdapProviderConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		AdminLdapProvider alp = new AdminLdapProvider(1);
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
		List<Object> alpList = new ArrayList<>();
		alpList.add(alp);
		doNothing().when(adminServiceMock).deleteLdapProvider(anyList());
		alpList = authenticationController.manageLdapProviderConfig(json,
				PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, alpList.toString());
		verify(adminServiceMock, times(1)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLdapProviderConfig_nullCheck_firstParameter()
			throws Exception {
		when(adminServiceMock.saveOrUpdateLdapProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageLdapProviderConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateLdapProvider(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLdapProviderConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		List<Object> lanQosList = authenticationController
				.manageLdapProviderConfig(json, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateLdapProvider(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLdapProviderConfig_nullCheck_bothParameters()
			throws Exception {
		when(adminServiceMock.saveOrUpdateLdapProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageLdapProviderConfig(null, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateLdapProvider(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLdapProviderConfig_returnNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"groupAuthorization\":\"enable\",\"bindDn\":\"bindDn\",\"enableSsl\":\"enable\",\"baseDn\":\"baseDn\",\"vendor\":\"open-ldap\",\"hostname\":\"hostName\",\"attribute\":\"attr\",\"targetAttribute\":\"\",\"id\":1,\"usePrimaryGroup\":\"yes\",\"groupRecursion\":\"recursive\",\"providerPassword\":null,\"filter\":\"filter\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateLdapProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> list = authenticationController.manageLdapProviderConfig(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateLdapProvider(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLdapProviderConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"groupAuthorization\":\"enable\",\"bindDn\":\"bindDn\",\"enableSsl\":\"enable\",\"baseDn\":\"baseDn\",\"vendor\":\"open-ldap\",\"hostname\":\"hostName\",\"attribute\":\"attr\",\"targetAttribute\":\"\",\"id\":1,\"usePrimaryGroup\":\"yes\",\"groupRecursion\":\"recursive\",\"providerPassword\":null,\"filter\":\"filter\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateLdapProvider(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());
		authenticationController.manageLdapProviderConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateLdapProvider(anyList(),
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLdapProviderConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteLdapProvider(anyList());
		authenticationController.manageLdapProviderConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteLdapProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapProviderGroupDetails_methodNameCheck()
			throws Exception {
		this.mockMvc.perform(
				get("/getAdminLdapProviderGroupDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminLdapProviderGroupDetails"));
	}

	@Test
	public void getAdminLdapProviderGroupDetails_returnTypeCheck()
			throws Exception {
		AdminLdapProviderGroup alpg = new AdminLdapProviderGroup(1);
		alpg.setName("ALPG");
		AdminLdapGroupProviderMapping algpm = new AdminLdapGroupProviderMapping();
		algpm.setAdminLdapProviderGroup(alpg);
		algpm.setId(1);
		algpm.setProviderOrder(1);
		algpm.setAdminLdapProvider(new AdminLdapProvider(1));

		AdminLdapGroupProviderMapping algpm1 = new AdminLdapGroupProviderMapping();
		algpm1.setAdminLdapProviderGroup(alpg);
		algpm1.setId(1);
		algpm1.setProviderOrder(1);
		algpm1.setAdminLdapProvider(new AdminLdapProvider(2));

		List<AdminLdapGroupProviderMapping> algpmList = new ArrayList<>();
		algpmList.add(algpm);
		algpmList.add(algpm1);
		alpg.setAdminLdapGroupProviderMappings(algpmList);
		List<AdminLdapProviderGroup> alpgList = new ArrayList<>();
		alpgList.add(alpg);
		String expected = "[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}]";
		when(adminServiceMock.fetchAdminLdapProviderGroup(PROJECT_ID))
				.thenReturn(alpgList);
		List<Object> list = authenticationController
				.getAdminLdapProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchAdminLdapProviderGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapProviderGroupDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> list = authenticationController
				.getAdminLdapProviderGroupDetails(null);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).fetchAdminLdapProviderGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapProviderGroupDetails_returnNullCheck()
			throws Exception {
		when(adminServiceMock.fetchAdminLdapProviderGroup(anyInt()))
				.thenReturn(null);
		String expected = "[]";
		List<Object> list = authenticationController
				.getAdminLdapProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1))
				.fetchAdminLdapProviderGroup(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapProviderGroupDetails_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminLdapProviderGroup(PROJECT_ID))
				.thenReturn(null);

		List<Object> jsonList = authenticationController
				.getAdminLdapProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminLdapProviderGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminLdapProviderGroupDetails_methodNameCheck()
			throws Exception {

		mockMvc.perform(
				post("/manageAdminLdapProviderGroupDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminLdapProviderGroupDetails"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapProviderGroupDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}], \"deleted\":[]}";

		AdminLdapProviderGroup alpg = new AdminLdapProviderGroup(1);
		alpg.setName("ALPG");
		AdminLdapGroupProviderMapping algpm = new AdminLdapGroupProviderMapping();
		algpm.setAdminLdapProviderGroup(alpg);
		algpm.setId(1);
		algpm.setProviderOrder(1);
		algpm.setAdminLdapProvider(new AdminLdapProvider(1));

		AdminLdapGroupProviderMapping algpm1 = new AdminLdapGroupProviderMapping();
		algpm1.setAdminLdapProviderGroup(alpg);
		algpm1.setId(1);
		algpm1.setProviderOrder(1);
		algpm1.setAdminLdapProvider(new AdminLdapProvider(2));
		List<AdminLdapGroupProviderMapping> algpmList = new ArrayList<>();
		algpmList.add(algpm);
		algpmList.add(algpm1);
		alpg.setAdminLdapGroupProviderMappings(algpmList);
		List<AdminLdapProviderGroup> alpgList = new ArrayList<>();
		alpgList.add(alpg);
		when(
				adminServiceMock.saveOrUpdateAdminLdapProviderGroup(anyList(),
						anyInt())).thenReturn(alpgList);
		List<Object> list = authenticationController
				.manageAdminLdapProviderGroupDetails(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLdapProviderGroup(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapProviderGroupDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doNothing().when(adminServiceMock).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		List<Object> alpgList = authenticationController
				.manageAdminLdapProviderGroupDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, alpgList.toString());
		verify(adminServiceMock, times(1)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapProviderGroupDetails_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminLdapProviderGroup(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageAdminLdapProviderGroupDetails(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapProviderGroup(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapProviderGroupDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		when(
				adminServiceMock.saveOrUpdateAdminLdapProviderGroup(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageAdminLdapProviderGroupDetails(json, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapProviderGroup(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapProviderGroupDetails_nullCheck_bothParameters()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminLdapProviderGroup(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageAdminLdapProviderGroupDetails(null, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapProviderGroup(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLdapProviderGroupDetails_returnNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminLdapProviderGroup(anyList(),
						anyInt())).thenReturn(null);
		authenticationController.manageAdminLdapProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLdapProviderGroup(
				anyList(), PROJECT_ID);
		verify(adminServiceMock, times(0)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLdapProviderGroupDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminLdapProviderGroup(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		authenticationController.manageAdminLdapProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLdapProviderGroup(
				anyList(), PROJECT_ID);
		verify(adminServiceMock, times(0)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLdapProviderGroupDetails_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminLdapProviderGroup(anyList(), anyInt());
		authenticationController.manageAdminLdapProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteAdminLdapProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getRadiusGeneralConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getRadiusGeneralConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getRadiusGeneralConfig"));
	}

	@Test
	public void getRadiusGeneralConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		AdminRadiusGeneral arg = new AdminRadiusGeneral(1);
		arg.setTimeout("5");
		when(adminServiceMock.fetchRadiusGeneral(PROJECT_ID)).thenReturn(arg);
		authenticationController.getRadiusGeneralConfig(PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchRadiusGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getRadiusGeneralConfig_nullCheck() throws Exception {
		String actual = authenticationController.getRadiusGeneralConfig(null);
		assertEquals(null, actual);
		verify(adminServiceMock, times(0)).fetchRadiusGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getRadiusGeneralConfig_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchRadiusGeneral(anyInt())).thenReturn(null);
		String actual = authenticationController
				.getRadiusGeneralConfig(PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).fetchRadiusGeneral(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getRadiusGeneralConfig_exception() throws Exception {
		doThrow(Exception.class).when(adminServiceMock).fetchRadiusGeneral(
				anyInt());
		String actual = authenticationController
				.getRadiusGeneralConfig(PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).fetchRadiusGeneral(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusGeneralConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageRadiusGeneralConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageRadiusGeneralConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusGeneralConfig_returnTypeCheck() throws Exception {
		String json = "{\"id\":1,\"retries\":5}";
		when(
				adminServiceMock.saveOrUpdateRadiusGeneral(
						(AdminRadiusGeneral) anyObject(), anyInt()))
				.thenReturn(1);
		int id = authenticationController.manageRadiusGeneralConfig(json,
				PROJECT_ID);
		int expectedId = 1;
		assertEquals(expectedId, id);
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusGeneral(
				(AdminRadiusGeneral) anyObject(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusGeneralConfig_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateRadiusGeneral(
						any(AdminRadiusGeneral.class), anyInt())).thenReturn(
				null);
		int id = authenticationController.manageRadiusGeneralConfig(null,
				PROJECT_ID);
		int expectedId = 0;
		assertEquals(expectedId, id);
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusGeneral(
				any(AdminRadiusGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusGeneralConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"id\":1,\"retries\":5}";
		int id = authenticationController.manageRadiusGeneralConfig(json, null);
		int expectedId = 0;
		assertEquals(expectedId, id);
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusGeneral(
				(AdminRadiusGeneral) anyObject(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusGeneralConfig_nullCheck_bothParameters()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateRadiusGeneral(
						any(AdminRadiusGeneral.class), anyInt())).thenReturn(
				null);
		int id = authenticationController.manageRadiusGeneralConfig(null, null);
		int expectedId = 0;
		assertEquals(expectedId, id);
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusGeneral(
				any(AdminRadiusGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusGeneralConfig_returnNullCheck() throws Exception {
		String json = "{\"id\":1,\"retries\":5}";
		when(
				adminServiceMock.saveOrUpdateRadiusGeneral(
						(AdminRadiusGeneral) anyObject(), anyInt()))
				.thenReturn(null);
		Integer id = authenticationController.manageRadiusGeneralConfig(json,
				PROJECT_ID);
		assertEquals(null, id);
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusGeneral(
				(AdminRadiusGeneral) anyObject(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void manageRadiusGeneralConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"id\":1,\"retries\":5}";
		when(
				adminServiceMock.saveOrUpdateRadiusGeneral(
						any(AdminRadiusGeneral.class), anyInt())).thenThrow(
				new NullPointerException());
		authenticationController.manageRadiusGeneralConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusGeneral(
				any(AdminRadiusGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getRadiusProviderConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/getRadiusProviderConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getRadiusProviderConfig"));
	}

	@Test
	public void getRadiusProviderConfig_returnTypeCheck() throws Exception {
		List<AdminRadiusProvider> arpList = new ArrayList<AdminRadiusProvider>();
		when(adminServiceMock.fetchRadiusProvider(PROJECT_ID)).thenReturn(
				arpList);

		List<Object> jsonList = authenticationController
				.getRadiusProviderConfig(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getRadiusProviderConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = authenticationController
				.getRadiusProviderConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchRadiusProvider(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getRadiusProviderConfig_returnNullCheck() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchRadiusProvider(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = authenticationController
				.getRadiusProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getRadiusProviderConfig_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(adminServiceMock).fetchRadiusProvider(
				PROJECT_ID);
		List<Object> jsonList = authenticationController
				.getRadiusProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageRadiusProviderConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageRadiusProviderConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageRadiusProviderConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageRadiusProviderConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doNothing().when(adminServiceMock).deleteRadiusProvider(anyList());
		List<Object> jsonList = authenticationController
				.manageRadiusProviderConfig(json, PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).deleteRadiusProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageRadiusProviderConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"retries\":5, \"timeout\":5}], \"deleted\":[]}";
		doNothing().when(adminServiceMock).deleteRadiusProvider(anyList());
		List<Object> jsonList = authenticationController
				.manageRadiusProviderConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProvider(anyList());
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusProvider(
				anyList(), anyInt());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageRadiusProviderConfig_nullCheck_firstParameter()
			throws Exception {
		when(adminServiceMock.saveOrUpdateRadiusProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> jsonList = authenticationController
				.manageRadiusProviderConfig(null, PROJECT_ID);
		String expectedId = "[]";
		assertEquals(expectedId, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageRadiusProviderConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"retries\":5, \"timeout\":5}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateRadiusProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> jsonList = authenticationController
				.manageRadiusProviderConfig(json, null);
		String expectedId = "[]";
		assertEquals(expectedId, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProvider(anyList());
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusProvider(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageRadiusProviderConfig_nullCheck_bothParameters()
			throws Exception {
		when(adminServiceMock.saveOrUpdateRadiusProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> jsonList = authenticationController
				.manageRadiusProviderConfig(null, null);
		String expectedId = "[]";
		assertEquals(expectedId, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageRadiusProviderConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"retries\":5, \"timeout\":5}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateRadiusProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> jsonList = authenticationController
				.manageRadiusProviderConfig(json, PROJECT_ID);
		String expectedId = "[]";
		assertEquals(expectedId, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProvider(anyList());
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusProvider(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageRadiusProviderConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"retries\":5, \"timeout\":5}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateRadiusProvider(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());
		authenticationController.manageRadiusProviderConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusProvider(
				anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageRadiusProviderConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteRadiusProvider(anyList());
		authenticationController.manageRadiusProviderConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteRadiusProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminRadiusProviderGroupDetails_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				get("/getAdminRadiusProviderGroupDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminRadiusProviderGroupDetails"));
	}

	@Test
	public void getAdminRadiusProviderGroupDetails_returnTypeCheck()
			throws Exception {
		List<AdminRadiusProviderGroup> arpgList = new ArrayList<>();
		when(adminServiceMock.fetchRadiusProviderGroup(PROJECT_ID)).thenReturn(
				arpgList);
		List<Object> jsonList = authenticationController
				.getAdminRadiusProviderGroupDetails(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProviderGroup(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminRadiusProviderGroupDetails_nullCheck() throws Exception {
		List<AdminRadiusProviderGroup> arpgList = new ArrayList<>();
		when(adminServiceMock.fetchRadiusProviderGroup(PROJECT_ID)).thenReturn(
				arpgList);
		List<Object> jsonList = authenticationController
				.getAdminRadiusProviderGroupDetails(null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProviderGroup(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminRadiusProviderGroupDetails_returnNullCheck()
			throws Exception {
		when(adminServiceMock.fetchRadiusProviderGroup(PROJECT_ID)).thenReturn(
				null);
		List<Object> jsonList = authenticationController
				.getAdminRadiusProviderGroupDetails(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProviderGroup(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminRadiusProviderGroupDetails_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchRadiusProviderGroup(PROJECT_ID)).thenReturn(
				null);
		List<Object> jsonList = authenticationController
				.getAdminRadiusProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchRadiusProviderGroup(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminRadiusProviderGroupDetails_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				post("/manageAdminRadiusProviderGroupDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminRadiusProviderGroupDetails"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRadiusProviderGroupDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doNothing().when(adminServiceMock).deleteRadiusProviderGroup(anyList(),
				anyInt());
		List<Object> jsonList = authenticationController
				.manageAdminRadiusProviderGroupDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRadiusProviderGroupDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		AdminRadiusProviderGroup arpg = new AdminRadiusProviderGroup(1);
		arpg.setName("Test");
		List<AdminRadiusProviderGroup> arpgList = new ArrayList<>();
		arpgList.add(arpg);
		when(
				adminServiceMock.saveOrUpdateRadiusProviderGroup(anyList(),
						anyInt())).thenReturn(arpgList);
		List<Object> jsonList = authenticationController
				.manageAdminRadiusProviderGroupDetails(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"name\":\"Test\",\"provider\":null}]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRadiusProviderGroupDetails_nullCheck_firstParameter()
			throws Exception {
		doNothing().when(adminServiceMock).deleteRadiusProviderGroup(anyList(),
				anyInt());
		List<Object> jsonList = authenticationController
				.manageAdminRadiusProviderGroupDetails(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRadiusProviderGroupDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		List<Object> jsonList = authenticationController
				.manageAdminRadiusProviderGroupDetails(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusProviderGroup(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRadiusProviderGroupDetails_nullCheck_bothParameters()
			throws Exception {
		doNothing().when(adminServiceMock).deleteRadiusProviderGroup(anyList(),
				anyInt());
		List<Object> jsonList = authenticationController
				.manageAdminRadiusProviderGroupDetails(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).saveOrUpdateRadiusProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRadiusProviderGroupDetails_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateRadiusProviderGroup(anyList(),
						anyInt())).thenReturn(null);
		List<Object> jsonList = authenticationController
				.manageAdminRadiusProviderGroupDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusProviderGroup(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminRadiusProviderGroupDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateRadiusProviderGroup(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		authenticationController.manageAdminRadiusProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateRadiusProviderGroup(
				anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminRadiusProviderGroupDetails_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteRadiusProviderGroup(anyList(), anyInt());
		authenticationController.manageAdminRadiusProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteRadiusProviderGroup(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsGeneralConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getTacacsGeneralConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getTacacsGeneralConfig"));
	}

	@Test
	public void getTacacsGeneralConfig_returnTypeCheck() throws Exception {
		AdminTacacsGeneral atg = new AdminTacacsGeneral(1);
		atg.setTimeout("20");
		String expected = "{\"id\":1,\"timeout\":\"20\"}";
		when(adminServiceMock.fetchTacacsGeneral(PROJECT_ID)).thenReturn(atg);
		String actual = authenticationController
				.getTacacsGeneralConfig(PROJECT_ID);
		assertEquals(expected, actual);
		verify(adminServiceMock, times(1)).fetchTacacsGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsGeneralConfig_nullCheck() throws Exception {
		String actual = authenticationController.getTacacsGeneralConfig(null);
		assertEquals(null, actual);
		verify(adminServiceMock, times(0)).fetchTacacsGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsGeneralConfig_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchTacacsGeneral(PROJECT_ID)).thenReturn(null);
		String actual = authenticationController
				.getTacacsGeneralConfig(PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).fetchTacacsGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getTacacsGeneralConfig_exception() throws Exception {
		doThrow(Exception.class).when(
				adminServiceMock.fetchTacacsGeneral(PROJECT_ID));
		authenticationController.getTacacsGeneralConfig(PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchTacacsGeneral(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTacacsGeneralConfig_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageTacacsGeneralConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageTacacsGeneralConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTacacsGeneralConfig_returnTypeCheck() throws Exception {
		String json = "{\"id\":1,\"timeout\":\"20\"}";
		AdminTacacsGeneral atg = new AdminTacacsGeneral(1);
		atg.setTimeout("20");
		when(
				adminServiceMock.saveOrUpdateTacacsGeneral(
						any(AdminTacacsGeneral.class), anyInt())).thenReturn(1);
		Integer actual = authenticationController.manageTacacsGeneralConfig(
				json, PROJECT_ID);
		Integer expected = new Integer(1);
		assertEquals(expected, actual);
		verify(adminServiceMock, times(1)).saveOrUpdateTacacsGeneral(
				any(AdminTacacsGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTacacsGeneralConfig_returnTypeCheck_nullParameter()
			throws Exception {
		Integer actual = authenticationController.manageTacacsGeneralConfig(
				null, PROJECT_ID);
		Integer expected = 0;
		assertEquals(expected, actual);
		verify(adminServiceMock, times(0)).saveOrUpdateTacacsGeneral(
				any(AdminTacacsGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTacacsGeneralConfig_returnNullCheck() throws Exception {
		String json = "{\"id\":1,\"timeout\":\"20\"}";
		when(
				adminServiceMock.saveOrUpdateTacacsGeneral(
						any(AdminTacacsGeneral.class), anyInt())).thenReturn(
				null);
		Integer actual = authenticationController.manageTacacsGeneralConfig(
				json, PROJECT_ID);
		assertEquals(null, actual);
		verify(adminServiceMock, times(1)).saveOrUpdateTacacsGeneral(
				any(AdminTacacsGeneral.class), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void manageTacacsGeneralConfig_exception() throws Exception {
		String json = "{\"id\":1,\"timeout\":\"20\"}";
		when(
				adminServiceMock.saveOrUpdateTacacsGeneral(
						any(AdminTacacsGeneral.class), PROJECT_ID)).thenThrow(
				new NullPointerException());
		authenticationController.manageTacacsGeneralConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateTacacsGeneral(
				any(AdminTacacsGeneral.class), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsProviderConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getTacacsProviderConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getTacacsProviderConfig"));
	}

	@Test
	public void getTacacsProviderConfig_returnTypeCheck() throws Exception {
		AdminTacacsProvider atp = new AdminTacacsProvider(1);
		atp.setHostname("hostName");
		atp.setPort(230);
		atp.setProviderOrder(1);
		atp.setTimeout(23);
		List<AdminTacacsProvider> atpList = new ArrayList<>();
		atpList.add(atp);
		String expected = "[{\"port\":230,\"id\":1,\"providerKey\":null,\"hostname\":\"hostName\",\"timeout\":23,\"providerOrder\":1}]";
		when(adminServiceMock.fetchTacacsProvider(PROJECT_ID)).thenReturn(
				atpList);
		List<Object> jsonList = authenticationController
				.getTacacsProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchTacacsProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsProviderConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = authenticationController
				.getTacacsProviderConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchTacacsProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsProviderConfig_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchTacacsProvider(anyInt())).thenReturn(null);
		String expected = "[]";
		List<Object> jsonList = authenticationController
				.getTacacsProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchTacacsProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getTacacsProviderConfig_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchTacacsProvider(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = authenticationController
				.getTacacsProviderConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchTacacsProvider(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTacacsProviderConfig_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageTacacsProviderConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageTacacsProviderConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageTacacsProviderConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"id\":1,\"providerKey\":null,\"hostname\":\"hostName\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		AdminTacacsProvider atp = new AdminTacacsProvider(1);
		atp.setHostname("hostName");
		atp.setPort(230);
		atp.setProviderOrder(1);
		atp.setTimeout(23);
		List<AdminTacacsProvider> atpList = new ArrayList<>();
		atpList.add(atp);
		when(adminServiceMock.saveOrUpdateTacacsProvider(anyList(), anyInt()))
				.thenReturn(atpList);
		List<Object> list = authenticationController
				.manageTacacsProviderConfig(json, PROJECT_ID);
		String expected = "[{\"port\":230,\"id\":1,\"providerKey\":null,\"hostname\":\"hostName\",\"timeout\":23,\"providerOrder\":1}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateTacacsProvider(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageTacacsProviderConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		AdminTacacsProvider atp = new AdminTacacsProvider(1);
		atp.setHostname("hostName");
		atp.setPort(230);
		atp.setProviderOrder(1);
		atp.setTimeout(23);
		List<Object> atpList = new ArrayList<>();
		atpList.add(atp);
		doNothing().when(adminServiceMock).deleteTacacsProvider(anyList());
		atpList = authenticationController.manageTacacsProviderConfig(json,
				PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, atpList.toString());
		verify(adminServiceMock, times(1)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageTacacsProviderConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> lanQosList = authenticationController
				.manageTacacsProviderConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateTacacsProvider(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageTacacsProviderConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"id\":1,\"providerKey\":null,\"hostname\":\"hostName\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		List<Object> lanQosList = authenticationController
				.manageTacacsProviderConfig(json, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateTacacsProvider(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageTacacsProviderConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> lanQosList = authenticationController
				.manageTacacsProviderConfig(null, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateTacacsProvider(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageTacacsProviderConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"id\":1,\"providerKey\":null,\"hostname\":\"hostName\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateTacacsProvider(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageTacacsProviderConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateTacacsProvider(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageTacacsProviderConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"port\":230,\"groupAuthorization\":\"enable\",\"bindDn\":\"bindDn\",\"enableSsl\":\"enable\",\"baseDn\":\"baseDn\",\"vendor\":\"open-ldap\",\"hostname\":\"hostName\",\"attribute\":\"attr\",\"targetAttribute\":\"\",\"id\":1,\"usePrimaryGroup\":\"yes\",\"groupRecursion\":\"recursive\",\"providerPassword\":null,\"filter\":\"filter\",\"timeout\":23,\"providerOrder\":1}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateTacacsProvider(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());

		authenticationController.manageTacacsProviderConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateTacacsProvider(
				anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageTacacsProviderConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteTacacsProvider(anyList());

		authenticationController.manageTacacsProviderConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteTacacsProvider(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminTacacsProviderGroupDetails_methodNameCheck()
			throws Exception {
		this.mockMvc.perform(
				get("/getAdminTacacsProviderGroupDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminTacacsProviderGroupDetails"));
	}

	@Test
	public void getAdminTacacsProviderGroupDetails_returnTypeCheck()
			throws Exception {
		AdminTacacsProviderGroup atpg = new AdminTacacsProviderGroup(1);
		atpg.setName("ALPG");
		AdminTacacsGroupProviderMapping atgpm = new AdminTacacsGroupProviderMapping();
		atgpm.setAdminTacacsProviderGroup(atpg);
		atgpm.setId(1);
		atgpm.setProviderOrder(1);
		atgpm.setAdminTacacsProvider(new AdminTacacsProvider(1));

		AdminTacacsGroupProviderMapping atgpm1 = new AdminTacacsGroupProviderMapping();
		atgpm1.setAdminTacacsProviderGroup(atpg);
		atgpm1.setId(1);
		atgpm1.setProviderOrder(1);
		atgpm1.setAdminTacacsProvider(new AdminTacacsProvider(2));

		List<AdminTacacsGroupProviderMapping> atgpmList = new ArrayList<>();
		atgpmList.add(atgpm);
		atgpmList.add(atgpm1);

		atpg.setAdminTacacsGroupProviderMappings(atgpmList);

		List<AdminTacacsProviderGroup> atpgList = new ArrayList<>();
		atpgList.add(atpg);

		String expected = "[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}]";
		when(adminServiceMock.fetchAdminTacacsProviderGroup(PROJECT_ID))
				.thenReturn(atpgList);

		List<Object> list = authenticationController
				.getAdminTacacsProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchAdminTacacsProviderGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminTacacsProviderGroupDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> list = authenticationController
				.getAdminTacacsProviderGroupDetails(null);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).fetchAdminTacacsProviderGroup(null);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminTacacsProviderGroupDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminTacacsProviderGroup(anyInt()))
				.thenReturn(null);
		List<Object> list = authenticationController
				.getAdminTacacsProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchAdminTacacsProviderGroup(
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminTacacsProviderGroupDetails_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminTacacsProviderGroup(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = authenticationController
				.getAdminTacacsProviderGroupDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminTacacsProviderGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminTacacsProviderGroupDetails_methodNameCheck()
			throws Exception {

		mockMvc.perform(
				post("/manageAdminTacacsProviderGroupDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminTacacsProviderGroupDetails"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminTacacsProviderGroupDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}], \"deleted\":[]}";

		AdminTacacsProviderGroup atpg = new AdminTacacsProviderGroup(1);
		atpg.setName("ALPG");
		AdminTacacsGroupProviderMapping atgpm = new AdminTacacsGroupProviderMapping();
		atgpm.setAdminTacacsProviderGroup(atpg);
		atgpm.setId(1);
		atgpm.setProviderOrder(1);
		atgpm.setAdminTacacsProvider(new AdminTacacsProvider(1));

		AdminTacacsGroupProviderMapping atgpm1 = new AdminTacacsGroupProviderMapping();
		atgpm1.setAdminTacacsProviderGroup(atpg);
		atgpm1.setId(1);
		atgpm1.setProviderOrder(1);
		atgpm1.setAdminTacacsProvider(new AdminTacacsProvider(2));

		List<AdminTacacsGroupProviderMapping> atgpmList = new ArrayList<>();
		atgpmList.add(atgpm);
		atgpmList.add(atgpm1);
		atpg.setAdminTacacsGroupProviderMappings(atgpmList);

		List<AdminTacacsProviderGroup> atpgList = new ArrayList<>();
		atpgList.add(atpg);
		when(
				adminServiceMock.saveOrUpdateAdminTacacsProviderGroup(
						anyList(), anyInt())).thenReturn(atpgList);
		List<Object> list = authenticationController
				.manageAdminTacacsProviderGroupDetails(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminTacacsProviderGroup(anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminTacacsProviderGroupDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doNothing().when(adminServiceMock).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		List<Object> atpgList = authenticationController
				.manageAdminTacacsProviderGroupDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, atpgList.toString());
		verify(adminServiceMock, times(1)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminTacacsProviderGroupDetails_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminTacacsProviderGroup(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageAdminTacacsProviderGroupDetails(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminTacacsProviderGroup(anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminTacacsProviderGroupDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		when(
				adminServiceMock.saveOrUpdateAdminTacacsProviderGroup(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageAdminTacacsProviderGroupDetails(json, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminTacacsProviderGroup(anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminTacacsProviderGroupDetails_nullCheck_bothParameters()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminTacacsProviderGroup(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanQosList = authenticationController
				.manageAdminTacacsProviderGroupDetails(null, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminTacacsProviderGroup(anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminTacacsProviderGroupDetails_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminTacacsProviderGroup(
						anyList(), anyInt())).thenReturn(null);
		List<Object> list = authenticationController
				.manageAdminTacacsProviderGroupDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminTacacsProviderGroup(anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminTacacsProviderGroupDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"ALPG\",\"provider\":[1,2]}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminTacacsProviderGroup(
						anyList(), PROJECT_ID)).thenThrow(
				new NullPointerException());
		authenticationController.manageAdminTacacsProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminTacacsProviderGroup(anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminTacacsProviderGroupDetails_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminTacacsProviderGroup(anyList(), anyInt());
		authenticationController.manageAdminTacacsProviderGroupDetails(json,
				PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteAdminTacacsProviderGroup(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}
}
