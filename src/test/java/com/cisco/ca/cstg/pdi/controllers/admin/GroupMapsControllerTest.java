package com.cisco.ca.cstg.pdi.controllers.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

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

import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMap;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapLocalesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapRolesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRole;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class GroupMapsControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	AdminService adminServiceMock;
	
	@InjectMocks
	private GroupMapsController groupMapsController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(groupMapsController).build();
	}

	@Test
	public void getAdminLdapGroupMapDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getAdminLdapGroupMapDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminLdapGroupMapDetails"));
	}

	@Test
	public void getAdminLdapGroupMapDetails_returnTypeCheck() throws Exception {
		AdminLdapGroupMap alpg = new AdminLdapGroupMap(1);
		alpg.setName("ALGM");

		AdminLdapGroupMapLocalesMapping algmm = new AdminLdapGroupMapLocalesMapping(
				1);
		algmm.setAdminLdapGroupMap(alpg);
		algmm.setAdminLdapLocale(new AdminLdapLocale(1));
		AdminLdapGroupMapLocalesMapping algmm1 = new AdminLdapGroupMapLocalesMapping(
				2);
		algmm1.setAdminLdapGroupMap(alpg);
		algmm1.setAdminLdapLocale(new AdminLdapLocale(2));
		List<AdminLdapGroupMapLocalesMapping> algmmList = new ArrayList<>();
		algmmList.add(algmm);
		algmmList.add(algmm1);
		alpg.setAdminLdapGroupMapLocalesMappings(algmmList);

		AdminLdapGroupMapRolesMapping algmrm = new AdminLdapGroupMapRolesMapping(
				1);
		algmrm.setAdminLdapGroupMap(alpg);
		algmrm.setAdminLdapRole(new AdminLdapRole(3));
		AdminLdapGroupMapRolesMapping algmrm1 = new AdminLdapGroupMapRolesMapping(
				2);
		algmrm1.setAdminLdapGroupMap(alpg);
		algmrm1.setAdminLdapRole(new AdminLdapRole(4));
		List<AdminLdapGroupMapRolesMapping> algmrmList = new ArrayList<>();
		algmrmList.add(algmrm);
		algmrmList.add(algmrm1);
		alpg.setAdminLdapGroupMapRolesMappings(algmrmList);

		List<AdminLdapGroupMap> alpgList = new ArrayList<>();
		alpgList.add(alpg);

		String expected = "[{\"id\":1,\"ldapLocaleId\":[1,2],\"ldapRoleId\":[3,4],\"name\":\"ALGM\"}]";
		when(adminServiceMock.fetchLdapGroupMapsInfo(PROJECT_ID)).thenReturn(
				alpgList);

		List<Object> list = groupMapsController
				.getAdminLdapGroupMapDetails(PROJECT_ID);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchLdapGroupMapsInfo(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapGroupMapDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> list = groupMapsController
				.getAdminLdapGroupMapDetails(null);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).fetchLdapGroupMapsInfo(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapGroupMapDetails_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchLdapGroupMapsInfo(anyInt()))
				.thenReturn(null);
		String expected = "[]";
		List<Object> list = groupMapsController
				.getAdminLdapGroupMapDetails(PROJECT_ID);
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchLdapGroupMapsInfo(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLdapGroupMapDetails_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchLdapGroupMapsInfo(PROJECT_ID)).thenReturn(
				null);

		List<Object> jsonList = groupMapsController
				.getAdminLdapGroupMapDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchLdapGroupMapsInfo(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminLdapGroupMapDetails_methodNameCheck()
			throws Exception {

		mockMvc.perform(
				post("/manageAdminLdapGroupMapDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminLdapGroupMapDetails"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapGroupMapDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"ldapLocaleId\":[1,2],\"ldapRoleId\":[3,4],\"name\":\"ALGM\"}], \"deleted\":[]}";
		AdminLdapGroupMap alpg = new AdminLdapGroupMap(1);
		alpg.setName("ALGM");

		AdminLdapGroupMapLocalesMapping algmm = new AdminLdapGroupMapLocalesMapping(
				1);
		algmm.setAdminLdapGroupMap(alpg);
		algmm.setAdminLdapLocale(new AdminLdapLocale(1));
		AdminLdapGroupMapLocalesMapping algmm1 = new AdminLdapGroupMapLocalesMapping(
				2);
		algmm1.setAdminLdapGroupMap(alpg);
		algmm1.setAdminLdapLocale(new AdminLdapLocale(2));
		List<AdminLdapGroupMapLocalesMapping> algmmList = new ArrayList<>();
		algmmList.add(algmm);
		algmmList.add(algmm1);
		alpg.setAdminLdapGroupMapLocalesMappings(algmmList);

		AdminLdapGroupMapRolesMapping algmrm = new AdminLdapGroupMapRolesMapping(
				1);
		algmrm.setAdminLdapGroupMap(alpg);
		algmrm.setAdminLdapRole(new AdminLdapRole(3));
		AdminLdapGroupMapRolesMapping algmrm1 = new AdminLdapGroupMapRolesMapping(
				2);
		algmrm1.setAdminLdapGroupMap(alpg);
		algmrm1.setAdminLdapRole(new AdminLdapRole(4));
		List<AdminLdapGroupMapRolesMapping> algmrmList = new ArrayList<>();
		algmrmList.add(algmrm);
		algmrmList.add(algmrm1);
		alpg.setAdminLdapGroupMapRolesMappings(algmrmList);

		List<AdminLdapGroupMap> alpgList = new ArrayList<>();
		alpgList.add(alpg);
		when(
				adminServiceMock.saveOrUpdateAdminLdapGroupMaps(anyList(),
						anyInt())).thenReturn(alpgList);
		List<Object> list = groupMapsController.manageAdminLdapGroupMapDetails(
				json, PROJECT_ID);
		String expected = "[{\"id\":1,\"ldapLocaleId\":[1,2],\"ldapRoleId\":[3,4],\"name\":\"ALGM\"}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapGroupMapDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";

		doNothing().when(adminServiceMock).deleteLdapGroupMaps(anyList());

		List<Object> algpList = groupMapsController
				.manageAdminLdapGroupMapDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, algpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(1)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapGroupMapDetails_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminLdapGroupMaps(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = groupMapsController
				.manageAdminLdapGroupMapDetails(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapGroupMapDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		when(
				adminServiceMock.saveOrUpdateAdminLdapGroupMaps(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = groupMapsController
				.manageAdminLdapGroupMapDetails(json, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLdapGroupMapDetails_nullCheck_bothParameters()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminLdapGroupMaps(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = groupMapsController
				.manageAdminLdapGroupMapDetails(null, null);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLdapGroupMapDetails_returnNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"ldapLocaleId\":[1,2],\"ldapRoleId\":[3,4],\"name\":\"ALGM\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminLdapGroupMaps(anyList(),
						PROJECT_ID)).thenReturn(null);
		groupMapsController.manageAdminLdapGroupMapDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLdapGroupMapDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"ldapLocaleId\":[1,2],\"ldapRoleId\":[3,4],\"name\":\"ALGM\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminLdapGroupMaps(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		groupMapsController.manageAdminLdapGroupMapDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLdapGroupMapDetails_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteLdapGroupMaps(anyList());
		groupMapsController.manageAdminLdapGroupMapDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLdapGroupMaps(
				anyList(), anyInt());
		verify(adminServiceMock, times(1)).deleteLdapGroupMaps(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminRoleDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getAdminRoleDetails.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(
				handler().methodName("getAdminRoleDetails"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAdminRoleDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		AdminLdapRole aad = new AdminLdapRole(1);
		aad.setName("Test");
		List<AdminLdapRole> aadList = new ArrayList<>();
		aadList.add(aad);
		when(adminServiceMock.saveOrUpdateAdminRoles(anyList(), anyInt()))
				.thenReturn(aadList);
		List<Object> list = groupMapsController.manageAdminRoleDetails(json,
				PROJECT_ID);
		String expected = "[{\"id\":1,\"name\":\"Test\",\"privileges\":null}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminRoles(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminRoles(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRoleDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		AdminLdapRole aad = new AdminLdapRole(1);
		aad.setName("Test");
		List<Object> aadList = new ArrayList<>();
		aadList.add(aad);
		doNothing().when(adminServiceMock).deleteAdminRoles(anyList());
		aadList = groupMapsController.manageAdminRoleDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(1)).deleteAdminRoles(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRoleDetails_nullCheck_firstParameter()
			throws Exception {
		when(adminServiceMock.saveOrUpdateAdminRoles(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> roleList = groupMapsController.manageAdminRoleDetails(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, roleList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminRoles(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminRoles(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRoleDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		when(adminServiceMock.saveOrUpdateAdminRoles(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> roleList = groupMapsController.manageAdminRoleDetails(
				json, null);
		String expected = "[]";
		assertEquals(expected, roleList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminRoles(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminRoles(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRoleDetails_nullCheck_bothParameters()
			throws Exception {
		when(adminServiceMock.saveOrUpdateAdminRoles(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> roleList = groupMapsController.manageAdminRoleDetails(
				null, null);
		String expected = "[]";
		assertEquals(expected, roleList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminRoles(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminRoles(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminRoleDetails_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateAdminRoles(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> roleList = groupMapsController.manageAdminRoleDetails(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, roleList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminRoles(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminRoleDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateAdminRoles(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());
		groupMapsController.manageAdminRoleDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminRoles(anyList(),
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminRoleDetails_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminRoles(anyList());

		groupMapsController.manageAdminRoleDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteAdminRoles(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLocaleDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getAdminLocaleDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminLocaleDetails"));
	}

	@Test
	public void getAdminLocaleDetails_returnTypeCheck() throws Exception {
		AdminLdapLocale aad = new AdminLdapLocale(1);
		aad.setName("Test");
		List<AdminLdapLocale> aadList = new ArrayList<>();
		aadList.add(aad);
		when(adminServiceMock.fetchAdminLocales(anyInt())).thenReturn(aadList);
		List<Object> list = groupMapsController
				.getAdminLocaleDetails(PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":null,\"name\":\"Test\"}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchAdminLocales(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLocaleDetails_nullCheck() throws Exception {
		List<Object> list = groupMapsController.getAdminLocaleDetails(null);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).fetchAdminLocales(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminLocaleDetails_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchAdminLocales(anyInt())).thenReturn(null);
		List<Object> list = groupMapsController
				.getAdminLocaleDetails(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchAdminLocales(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getAdminLocaleDetails_exception() throws Exception {
		doThrow(Exception.class).when(adminServiceMock).fetchAdminLocales(
				anyInt());
		List<Object> list = groupMapsController
				.getAdminLocaleDetails(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchAdminLocales(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminLocaleDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				post("/manageAdminLocaleDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminLocaleDetails"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLocaleDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		AdminLdapLocale aad = new AdminLdapLocale(1);
		aad.setName("Test");
		List<Object> aadList = new ArrayList<>();
		aadList.add(aad);
		doNothing().when(adminServiceMock).deleteAdminLocales(anyList());
		aadList = groupMapsController
				.manageAdminLocaleDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLocales(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLocales(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLocaleDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		AdminLdapLocale aad = new AdminLdapLocale(1);
		aad.setName("Test");
		List<Object> aadList = new ArrayList<>();
		aadList.add(aad);
		doNothing().when(adminServiceMock).deleteAdminLocales(anyList());
		aadList = groupMapsController
				.manageAdminLocaleDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(1)).deleteAdminLocales(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLocaleDetails_nullCheck_firstParameter()
			throws Exception {
		when(adminServiceMock.saveOrUpdateAdminLocales(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> roleList = groupMapsController.manageAdminLocaleDetails(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, roleList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLocales(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLocales(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLocaleDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		List<Object> aadList = groupMapsController.manageAdminLocaleDetails(
				json, null);
		String expected = "[]";
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLocales(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLocales(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLocaleDetails_nullCheck_bothParameters()
			throws Exception {
		List<Object> aadList = groupMapsController.manageAdminLocaleDetails(
				null, null);
		String expected = "[]";
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(0)).deleteAdminLocales(anyList());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminLocales(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminLocaleDetails_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateAdminLocales(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> aadList = groupMapsController.manageAdminLocaleDetails(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, aadList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLocales(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteAdminLocales(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLocaleDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"Test\"}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateAdminLocales(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());
		groupMapsController.manageAdminLocaleDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminLocales(anyList(),
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminLocaleDetails_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminLocales(anyList());
		groupMapsController.manageAdminLocaleDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteAdminLocales(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

}
