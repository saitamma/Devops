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

import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeAlertGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomePolicy;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfile;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileAlertGroupMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileRecipientMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeSystemInventory;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class CallHomeControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	AdminService adminServiceMock;

	@InjectMocks
	private CallHomeController callHomeController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(callHomeController).build();
	}

	@Test
	public void getCallHomeGeneralSetting_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getCallHomeGeneralSetting.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getCallHomeGeneralSetting"));
	}

	@Test
	public void getCallHomeGeneralSetting_returnTypeCheck() throws Exception {
		AdminCallhomeGeneral adminChgs = new AdminCallhomeGeneral(1);
		adminChgs.setState("off");
		adminChgs.setSwitchPriority("debugging");
		adminChgs.setThrottling("on");
		adminChgs.setPort(25);
		List<AdminCallhomeGeneral> adminChgsList = new ArrayList<>();
		adminChgsList.add(adminChgs);
		AdminCallhomeSystemInventory adminChsi = new AdminCallhomeSystemInventory(
				1);
		adminChsi.setSendPeriodically("off");
		adminChsi.setSendIntervalDays(30);
		adminChsi.setSendIntervalMinutes(0);
		adminChsi.setSendIntervalHours(0);

		List<AdminCallhomeSystemInventory> adminChsiList = new ArrayList<>();
		adminChsiList.add(adminChsi);

		String expected = "[{\"sysInventoryList\":[\"{\\\"sendIntervalDays\\\":30,\\\"id\\\":1,\\\"sendPeriodically\\\":\\\"off\\\",\\\"sendIntervalHours\\\":0,\\\"sendIntervalMinutes\\\":0}\"],\"generalList\":[\"{\\\"port\\\":25,\\\"phone\\\":null,\\\"host\\\":null,\\\"state\\\":\\\"off\\\",\\\"switchPriority\\\":\\\"debugging\\\",\\\"emailFrom\\\":null,\\\"throttling\\\":\\\"on\\\",\\\"contact\\\":null,\\\"id\\\":1,\\\"replyTo\\\":null,\\\"customerId\\\":null,\\\"siteId\\\":null,\\\"email\\\":null,\\\"address\\\":null,\\\"contractId\\\":null}\"]}]";
		when(adminServiceMock.fetchAdminCallHomeGeneralSetting(PROJECT_ID))
				.thenReturn(adminChgsList);
		when(adminServiceMock.fetchAdminCallHomeSystemInventory(PROJECT_ID))
				.thenReturn(adminChsiList);
		List<Object> jsonList = callHomeController
				.getCallHomeGeneralSetting(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeGeneralSetting(
				PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchAdminCallHomeSystemInventory(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeGeneralSetting_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = callHomeController
				.getCallHomeGeneralSetting(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchAdminCallHomeGeneralSetting(
				PROJECT_ID);
		verify(adminServiceMock, times(0)).fetchAdminCallHomeSystemInventory(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeGeneralSetting_returnNullCheck1() throws Exception {
		AdminCallhomeGeneral adminChgs = new AdminCallhomeGeneral(1);
		adminChgs.setState("off");
		adminChgs.setSwitchPriority("debugging");
		adminChgs.setThrottling("on");
		adminChgs.setPort(25);
		List<AdminCallhomeGeneral> adminChgsList = new ArrayList<>();
		adminChgsList.add(adminChgs);
		String expected = "[{\"sysInventoryList\":[],\"generalList\":[\"{\\\"port\\\":25,\\\"phone\\\":null,\\\"host\\\":null,\\\"state\\\":\\\"off\\\",\\\"switchPriority\\\":\\\"debugging\\\",\\\"emailFrom\\\":null,\\\"throttling\\\":\\\"on\\\",\\\"contact\\\":null,\\\"id\\\":1,\\\"replyTo\\\":null,\\\"customerId\\\":null,\\\"siteId\\\":null,\\\"email\\\":null,\\\"address\\\":null,\\\"contractId\\\":null}\"]}]";
		when(adminServiceMock.fetchAdminCallHomeGeneralSetting(PROJECT_ID))
				.thenReturn(adminChgsList);
		when(adminServiceMock.fetchAdminCallHomeSystemInventory(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = callHomeController
				.getCallHomeGeneralSetting(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeGeneralSetting(
				anyInt());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeSystemInventory(
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeGeneralSetting_returnNullCheck2() throws Exception {
		AdminCallhomeSystemInventory adminChsi = new AdminCallhomeSystemInventory(
				1);
		adminChsi.setSendPeriodically("off");
		adminChsi.setSendIntervalDays(30);
		adminChsi.setSendIntervalMinutes(0);
		adminChsi.setSendIntervalHours(0);
		List<AdminCallhomeSystemInventory> adminChsiList = new ArrayList<>();
		adminChsiList.add(adminChsi);
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomeGeneralSetting(PROJECT_ID))
				.thenReturn(null);
		when(adminServiceMock.fetchAdminCallHomeSystemInventory(PROJECT_ID))
				.thenReturn(adminChsiList);
		List<Object> jsonList = callHomeController
				.getCallHomeGeneralSetting(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeGeneralSetting(
				PROJECT_ID);
		verify(adminServiceMock, times(0)).fetchAdminCallHomeSystemInventory(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getCallHomeGeneralSetting_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(adminServiceMock)
				.fetchAdminCallHomeGeneralSetting(anyInt());
		List<Object> jsonList = callHomeController
				.getCallHomeGeneralSetting(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeGeneralSetting(
				PROJECT_ID);
		verify(adminServiceMock, times(0)).fetchAdminCallHomeSystemInventory(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getCallHomeGeneralSetting_exception2() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(adminServiceMock)
				.fetchAdminCallHomeSystemInventory(anyInt());
		doNothing().when(adminServiceMock).fetchAdminCallHomeGeneralSetting(
				anyInt());
		List<Object> jsonList = callHomeController
				.getCallHomeGeneralSetting(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeGeneralSetting(
				PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchAdminCallHomeSystemInventory(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageCallHomeGeneralSetting_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageCallHomeGeneralSetting.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageCallHomeGeneralSetting"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeGeneralSetting_returnTypeCheck() throws Exception {
		String json = "{\"generalSetting\":[{\"id\":1}], \"systemInventory\":[{\"id\":1}]}";
		AdminCallhomeGeneral adminChgs = new AdminCallhomeGeneral(1);
		adminChgs.setState("off");
		adminChgs.setSwitchPriority("debugging");
		adminChgs.setThrottling("on");
		adminChgs.setPort(25);
		List<AdminCallhomeGeneral> adminChgsList = new ArrayList<>();
		adminChgsList.add(adminChgs);
		AdminCallhomeSystemInventory adminChsi = new AdminCallhomeSystemInventory(
				1);
		adminChsi.setSendPeriodically("off");
		adminChsi.setSendIntervalDays(30);
		adminChsi.setSendIntervalMinutes(0);
		adminChsi.setSendIntervalHours(0);
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeGeneralSetting(
						anyList(), anyInt())).thenReturn(adminChgsList);
		String status = callHomeController.manageCallHomeGeneralSetting(json,
				PROJECT_ID);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminCallHomeGeneralSetting(anyList(), anyInt());
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminCallHomeSystemInventory(anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeGeneralSetting_nullCheck_firstParameter()
			throws Exception {
		String status = callHomeController.manageCallHomeGeneralSetting(null,
				PROJECT_ID);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeGeneralSetting(anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeSystemInventory(anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeGeneralSetting_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"generalSetting\":[{\"id\":1}], \"systemInventory\":[{\"id\":1}]}";
		String status = callHomeController.manageCallHomeGeneralSetting(json,
				null);
		String expected = "success"; // TODO need to change the expected result
		assertEquals(expected, status);
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeGeneralSetting(anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeSystemInventory(anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeGeneralSetting_nullCheck_bothParameters()
			throws Exception {
		String status = callHomeController.manageCallHomeGeneralSetting(null,
				null);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeGeneralSetting(anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeSystemInventory(anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeGeneralSetting_returnNullCheck1()
			throws Exception {
		String json = "{\"generalSetting\":[{\"id\":1}], \"systemInventory\":[{\"id\":1}]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeGeneralSetting(
						anyList(), anyInt())).thenReturn(null);
		String status = callHomeController.manageCallHomeGeneralSetting(json,
				PROJECT_ID);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminCallHomeGeneralSetting(anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.saveOrUpdateAdminCallHomeSystemInventory(anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageCallHomeGeneralSetting_exception() throws Exception {
		String json = "{\"generalSetting\":[{\"id\":1,\"state\":\"off\",\"contact\":\"\",\"phone\":\"\",\"email\":\"\",\"address\":\"\",\"customerId\":\"\",\"contractId\":\"\",\"siteId\":\"\",\"from\":\"\",\"replyTo\":\"\",\"host\":\"\",\"port\":25,\"switchPriority\":\"debugging\",\"throttling\":\"on\"}],\"systemInventory\":[{\"id\":1,\"sendPeriodically\":\"off\",\"sendIntervalDays\":30,\"sendIntervalHours\":19,\"sendIntervalMinutes\":10}]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeGeneralSetting(
						anyList(), PROJECT_ID)).thenThrow(
				new NullPointerException());
		callHomeController.manageCallHomeGeneralSetting(json, PROJECT_ID);
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminCallHomeGeneralSetting(anyList(), PROJECT_ID);
		verify(adminServiceMock, times(1))
				.saveOrUpdateAdminCallHomeSystemInventory(anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeAlertGroup_methodNameCheck() throws Exception {
		when(adminServiceMock.fetchAdminCallHomeAlertGroup(PROJECT_ID))
				.thenReturn(null);
		this.mockMvc.perform(
				get("/getCallHomeAlertGroup.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getCallHomeAlertGroup"));
	}

	@Test
	public void getCallHomeAlertGroup_returnTypeCheck() throws Exception {
		AdminCallhomeAlertGroup adminChag = new AdminCallhomeAlertGroup();
		adminChag.setId(1);
		adminChag.setName("Cisco Tac");
		List<AdminCallhomeAlertGroup> adminChagList = new ArrayList<>();
		adminChagList.add(adminChag);
		String expected = "[{\"id\":1,\"name\":\"Cisco Tac\"}]";
		when(adminServiceMock.fetchAdminCallHomeAlertGroup(PROJECT_ID))
				.thenReturn(adminChagList);
		List<Object> jsonList = callHomeController
				.getCallHomeAlertGroup(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeAlertGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeAlertGroup_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = callHomeController.getCallHomeAlertGroup(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchAdminCallHomeAlertGroup(null);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeAlertGroup_returnNullCheck() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomeAlertGroup(anyInt()))
				.thenReturn(null);
		List<Object> jsonList = callHomeController
				.getCallHomeAlertGroup(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeAlertGroup(
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeAlertGroup_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomeAlertGroup(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = callHomeController
				.getCallHomeAlertGroup(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeAlertGroup(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeProfile_methodNameCheck() throws Exception {
		when(adminServiceMock.fetchAdminCallHomeProfiles(PROJECT_ID))
				.thenReturn(null);
		this.mockMvc.perform(
				get("/getCallHomeProfile.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(
				handler().methodName("getCallHomeProfile"));
	}

	@Test
	public void getCallHomeProfile_returnTypeCheck() throws Exception {
		AdminCallhomeProfile adminChp = new AdminCallhomeProfile(1);
		List<AdminCallhomeProfileAlertGroupMapping> list = new ArrayList<>();
		list.add(new AdminCallhomeProfileAlertGroupMapping(1,
				new AdminCallhomeAlertGroup(1), new AdminCallhomeProfile(1)));
		list.add(new AdminCallhomeProfileAlertGroupMapping(1,
				new AdminCallhomeAlertGroup(2), new AdminCallhomeProfile(2)));
		adminChp.setName("test1");
		adminChp.setDescription("test1");
		adminChp.setFormat("xml");
		adminChp.setLevel("debug");
		adminChp.setMaxMsgSize(25);
		adminChp.setAdminCallhomeProfileAlertGroupMappings(list);
		List<AdminCallhomeProfile> lcpList = new ArrayList<>();
		lcpList.add(adminChp);

		String expected = "[{\"maxMsgSize\":25,\"id\":1,\"alertGroupId\":[1,2],\"level\":\"debug\",\"description\":\"test1\",\"name\":\"test1\",\"format\":\"xml\"}]";
		when(adminServiceMock.fetchAdminCallHomeProfiles(PROJECT_ID))
				.thenReturn(lcpList);

		List<Object> jsonList = callHomeController
				.getCallHomeProfile(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeProfiles(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeProfile_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = callHomeController.getCallHomeProfile(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchAdminCallHomeProfiles(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeProfile_returnNullCheck() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomeProfiles(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = callHomeController
				.getCallHomeProfile(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeProfiles(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getCallHomeProfile_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomeProfiles(PROJECT_ID))
				.thenReturn(null);

		List<Object> jsonList = callHomeController
				.getCallHomeProfile(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomeProfiles(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminCallHomeProfile_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageAdminCallHomeProfile.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminCallHomeProfile"));

		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomeProfile_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"maxMsgSize\":1000000,\"id\":1,\"alertGroupId\":[1,2],\"level\":\"debug\",\"description\":\"Built-in xml format for test4  profile\",\"name\":\"test4\",\"format\":\"xml\"}], \"deleted\":[]}";
		AdminCallhomeProfile adminChp = new AdminCallhomeProfile(1);
		List<AdminCallhomeProfileAlertGroupMapping> list = new ArrayList<>();
		list.add(new AdminCallhomeProfileAlertGroupMapping(1,
				new AdminCallhomeAlertGroup(1), new AdminCallhomeProfile(1)));
		list.add(new AdminCallhomeProfileAlertGroupMapping(1,
				new AdminCallhomeAlertGroup(2), new AdminCallhomeProfile(2)));
		adminChp.setName("test4");
		adminChp.setDescription("Built-in xml format for test4  profile");
		adminChp.setFormat("xml");
		adminChp.setLevel("debug");
		adminChp.setMaxMsgSize(1000000);
		adminChp.setAdminCallhomeProfileAlertGroupMappings(list);
		List<AdminCallhomeProfile> adminChpList = new ArrayList<>();
		adminChpList.add(adminChp);
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeProfile(anyList(),
						anyInt())).thenReturn(adminChpList);
		List<Object> achplist = callHomeController.manageAdminCallHomeProfile(
				json, PROJECT_ID);
		String expected = "[{\"maxMsgSize\":1000000,\"id\":1,\"alertGroupId\":[1,2],\"level\":\"debug\",\"description\":\"Built-in xml format for test4  profile\",\"name\":\"test4\",\"format\":\"xml\"}]";
		assertEquals(expected, achplist.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminCallHomeProfile(
				anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.deleteAdminCallHomeProfile(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomeProfile_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		AdminCallhomeProfile adminChp = new AdminCallhomeProfile(1);
		List<AdminCallhomeProfileAlertGroupMapping> list = new ArrayList<>();
		list.add(new AdminCallhomeProfileAlertGroupMapping(1,
				new AdminCallhomeAlertGroup(1), new AdminCallhomeProfile(1)));
		list.add(new AdminCallhomeProfileAlertGroupMapping(1,
				new AdminCallhomeAlertGroup(1), new AdminCallhomeProfile(2)));
		adminChp.setName("test1");
		adminChp.setDescription("desc");
		adminChp.setAdminCallhomeProfileAlertGroupMappings(list);
		List<Object> adminChpList = new ArrayList<>();
		adminChpList.add(adminChp);
		doNothing().when(adminServiceMock)
				.deleteAdminCallHomeProfile(anyList());
		adminChpList = callHomeController.manageAdminCallHomeProfile(json,
				PROJECT_ID);
		String expected = "[]"; // TODO need to check it should be blank or not
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(1))
				.deleteAdminCallHomeProfile(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomeProfile_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeProfile(anyList(),
						anyInt())).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomeProfile(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminCallHomeProfile(
				anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.deleteAdminCallHomeProfile(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomeProfile_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeProfile(anyList(),
						anyInt())).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomeProfile(json, null);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminCallHomeProfile(
				anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.deleteAdminCallHomeProfile(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomeProfile_nullCheck_bothParameters()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeProfile(anyList(),
						anyInt())).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomeProfile(null, null);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminCallHomeProfile(
				anyList(), anyInt());
		verify(adminServiceMock, times(0))
				.deleteAdminCallHomeProfile(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminCallHomeProfile_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"maxMsgSize\":1000000,\"id\":1,\"alertGroupId\":[1,2],\"level\":\"debug\",\"description\":\"Built-in xml format for test4  profile\",\"name\":\"test4\",\"format\":\"xml\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeProfile(anyList(),
						PROJECT_ID)).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomeProfile(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminCallHomeProfile(
				anyList(), anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminCallHomeProfile_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"maxMsgSize\":1000000,\"id\":1,\"alertGroupId\":[1,2],\"level\":\"debug\",\"description\":\"Built-in xml format for test4  profile\",\"name\":\"test4\",\"format\":\"xml\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomeProfile(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		callHomeController.manageAdminCallHomeProfile(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminCallHomeProfile(
				anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminCallHomeProfile_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminCallHomeProfile(anyList());
		callHomeController.manageAdminCallHomeProfile(json, PROJECT_ID);
		verify(adminServiceMock, times(1))
				.deleteAdminCallHomeProfile(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/getProfileRecipientsDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getProfileRecipientsDetails"));

		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_returnTypeCheck() throws Exception {
		Integer profileId = 1;
		AdminCallhomeProfileRecipientMapping adminChprm = new AdminCallhomeProfileRecipientMapping(
				1);
		adminChprm.setId(1);
		adminChprm.setEmail("test@recipients.com");
		adminChprm.setAdminCallhomeProfile(new AdminCallhomeProfile(profileId));
		List<AdminCallhomeProfileRecipientMapping> adminChprmList = new ArrayList<>();
		adminChprmList.add(adminChprm);
		String expected = "[{\"id\":1,\"email\":\"test@recipients.com\",\"profileId\":1}]";
		when(adminServiceMock.fetchProfileRecipientsDetails(PROJECT_ID))
				.thenReturn(adminChprmList);
		List<Object> jsonList = callHomeController.getProfileRecipientsDetails(
				PROJECT_ID, profileId);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchProfileRecipientsDetails(
				profileId);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_nullCheck_firstParameter()
			throws Exception {
		Integer profileId = 1;
		AdminCallhomeProfileRecipientMapping adminChprm = new AdminCallhomeProfileRecipientMapping(
				1);
		adminChprm.setId(1);
		adminChprm.setEmail("test@recipients.com");
		adminChprm.setAdminCallhomeProfile(new AdminCallhomeProfile(profileId));
		List<AdminCallhomeProfileRecipientMapping> adminChprmList = new ArrayList<>();
		adminChprmList.add(adminChprm);
		String expected = "[{\"id\":1,\"email\":\"test@recipients.com\",\"profileId\":1}]";
		when(adminServiceMock.fetchProfileRecipientsDetails(PROJECT_ID))
				.thenReturn(adminChprmList);
		List<Object> jsonList = callHomeController.getProfileRecipientsDetails(
				null, profileId);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchProfileRecipientsDetails(
				profileId);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_nullCheck_secondParameter()
			throws Exception {
		String expected = "[]";
		List<Object> jsonList = callHomeController.getProfileRecipientsDetails(
				PROJECT_ID, null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchProfileRecipientsDetails(
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_nullCheck_bothParameters()
			throws Exception {
		String expected = "[]";
		List<Object> jsonList = callHomeController.getProfileRecipientsDetails(
				null, null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchProfileRecipientsDetails(
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_returnNullCheck() throws Exception {
		Integer profileId = 1;
		String expected = "[]";
		when(adminServiceMock.fetchProfileRecipientsDetails(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = callHomeController.getProfileRecipientsDetails(
				PROJECT_ID, profileId);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchProfileRecipientsDetails(
				profileId);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getProfileRecipientsDetails_exception() throws Exception {
		String expected = "[]";
		Integer profileId = 1;
		when(adminServiceMock.fetchProfileRecipientsDetails(profileId))
				.thenReturn(null);
		List<Object> jsonList = callHomeController.getProfileRecipientsDetails(
				PROJECT_ID, profileId);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchProfileRecipientsDetails(
				profileId);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageCallHomeProfileRecipients_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				post("/manageCallHomeProfileRecipients.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageCallHomeProfileRecipients"));

		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeProfileRecipients_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"email\":\"test@gmail.com\",\"profileId\":1}], \"deleted\":[]}";
		AdminCallhomeProfileRecipientMapping adminChprm = new AdminCallhomeProfileRecipientMapping(
				1);
		adminChprm.setId(1);
		adminChprm.setEmail("test@gmail.com");
		adminChprm.setAdminCallhomeProfile(new AdminCallhomeProfile(1));
		List<AdminCallhomeProfileRecipientMapping> adminChprmList = new ArrayList<>();
		adminChprmList.add(adminChprm);
		when(adminServiceMock.saveOrUpdateProfileRecipients(anyList()))
				.thenReturn(adminChprmList);
		List<Object> list = callHomeController.manageCallHomeProfileRecipients(
				json, PROJECT_ID);
		String expected = "[{\"id\":1,\"email\":\"test@gmail.com\",\"profileId\":1}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateProfileRecipients(
				anyList());
		verify(adminServiceMock, times(0)).deleteProfileRecipients(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeProfileRecipients_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		AdminCallhomeProfileRecipientMapping adminChprm = new AdminCallhomeProfileRecipientMapping(
				1);
		adminChprm.setId(1);
		adminChprm.setEmail("test@recipients.com");
		adminChprm.setAdminCallhomeProfile(new AdminCallhomeProfile(1));
		List<Object> adminChpList = new ArrayList<>();
		adminChpList.add(adminChprm);
		doNothing().when(adminServiceMock).deleteProfileRecipients(anyList());
		adminChpList = callHomeController.manageCallHomeProfileRecipients(json,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(1)).deleteProfileRecipients(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeProfileRecipients_nullCheck_firstParameter()
			throws Exception {
		when(adminServiceMock.saveOrUpdateProfileRecipients(anyList()))
				.thenReturn(null);
		List<Object> adminChprmList = callHomeController
				.manageCallHomeProfileRecipients(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, adminChprmList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateProfileRecipients(
				anyList());
		verify(adminServiceMock, times(0)).deleteProfileRecipients(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeProfileRecipients_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		when(adminServiceMock.saveOrUpdateProfileRecipients(anyList()))
				.thenReturn(null);
		List<Object> adminChprmList = callHomeController
				.manageCallHomeProfileRecipients(json, null);
		String expected = "[]";
		assertEquals(expected, adminChprmList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateProfileRecipients(
				anyList());
		verify(adminServiceMock, times(0)).deleteProfileRecipients(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeProfileRecipients_nullCheck_bothParameters()
			throws Exception {
		when(adminServiceMock.saveOrUpdateProfileRecipients(anyList()))
				.thenReturn(null);
		List<Object> adminChprmList = callHomeController
				.manageCallHomeProfileRecipients(null, null);
		String expected = "[]";
		assertEquals(expected, adminChprmList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateProfileRecipients(
				anyList());
		verify(adminServiceMock, times(0)).deleteProfileRecipients(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageCallHomeProfileRecipients_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"email\":\"test@cisco.com\",\"profileId\":1}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateProfileRecipients(anyList()))
				.thenReturn(null);
		List<Object> adminChprmList = callHomeController
				.manageCallHomeProfileRecipients(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, adminChprmList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateProfileRecipients(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageCallHomeProfileRecipients_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"email\":\"test@cisco.com\",\"profileId\":1}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateProfileRecipients(anyList()))
				.thenThrow(new NullPointerException());
		callHomeController.manageCallHomeProfileRecipients(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateProfileRecipients(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageCallHomeProfileRecipients_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteProfileRecipients(anyList());

		callHomeController.manageCallHomeProfileRecipients(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteProfileRecipients(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminCallHomePolicyDetails_methodNameCheck()
			throws Exception {
		when(adminServiceMock.fetchAdminCallHomePolicies(PROJECT_ID))
				.thenReturn(null);
		this.mockMvc.perform(
				get("/getAdminCallHomePolicyDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminCallHomePolicyDetails"));
	}

	@Test
	public void getAdminCallHomePolicyDetails_returnTypeCheck()
			throws Exception {
		AdminCallhomePolicy adminChp = new AdminCallhomePolicy(1);
		adminChp.setState("enabled");
		adminChp.setCause("configuration-failure");
		List<AdminCallhomePolicy> adminChpList = new ArrayList<>();
		adminChpList.add(adminChp);
		String expected = "[{\"id\":1,\"cause\":\"configuration-failure\",\"state\":\"enabled\"}]";
		when(adminServiceMock.fetchAdminCallHomePolicies(PROJECT_ID))
				.thenReturn(adminChpList);
		List<Object> jsonList = callHomeController
				.getAdminCallHomePolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomePolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminCallHomePolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = callHomeController
				.getAdminCallHomePolicyDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchAdminCallHomePolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminCallHomePolicyDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomePolicies(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = callHomeController
				.getAdminCallHomePolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomePolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminCallHomePolicyDetails_exception() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminCallHomePolicies(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = callHomeController
				.getAdminCallHomePolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminCallHomePolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminCallHomePolicyDetails_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				post("/manageAdminCallHomePolicyDetails.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminCallHomePolicyDetails"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomePolicyDetails_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"cause\":\"configuration-failure\",\"state\":\"disabled\"}], \"deleted\":[]}";
		AdminCallhomePolicy adminChp = new AdminCallhomePolicy(1);
		adminChp.setState("disabled");
		adminChp.setCause("configuration-failure");
		List<AdminCallhomePolicy> adminChpList = new ArrayList<>();
		adminChpList.add(adminChp);
		when(
				adminServiceMock.saveOrUpdateAdminCallHomePolicies(anyList(),
						anyInt())).thenReturn(adminChpList);
		List<Object> list = callHomeController
				.manageAdminCallHomePolicyDetails(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"cause\":\"configuration-failure\",\"state\":\"disabled\"}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminCallHomePolicies(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomePolicyDetails_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		AdminCallhomePolicy adminChp = new AdminCallhomePolicy(1);
		adminChp.setState("disabled");
		adminChp.setCause("configuration-failure");
		List<Object> adminChpList = new ArrayList<>();
		adminChpList.add(adminChp);
		doNothing().when(adminServiceMock).deleteAdminCallHomePolicies(
				anyList());
		adminChpList = callHomeController.manageAdminCallHomePolicyDetails(
				json, PROJECT_ID);
		String expected = "[]"; // TODO need to check it should be blank or not
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(1)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomePolicyDetails_nullCheck_firstParameter()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminCallHomePolicies(anyList(),
						anyInt())).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomePolicyDetails(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminCallHomePolicies(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomePolicyDetails_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomePolicies(anyList(),
						anyInt())).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomePolicyDetails(json, null);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminCallHomePolicies(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomePolicyDetails_nullCheck_bothParameters()
			throws Exception {
		when(
				adminServiceMock.saveOrUpdateAdminCallHomePolicies(anyList(),
						anyInt())).thenReturn(null);
		List<Object> adminChpList = callHomeController
				.manageAdminCallHomePolicyDetails(null, null);
		String expected = "[]";
		assertEquals(expected, adminChpList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateAdminCallHomePolicies(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminCallHomePolicyDetails_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"cause\":\"configuration-failure\",\"state\":\"disabled\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomePolicies(anyList(),
						anyInt())).thenReturn(null);
		List<Object> list = callHomeController
				.manageAdminCallHomePolicyDetails(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateAdminCallHomePolicies(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminCallHomePolicyDetails_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"cause\":\"voltage-problem\",\"state\":\"disabled\"}], \"deleted\":[]}";
		when(
				adminServiceMock.saveOrUpdateAdminCallHomePolicies(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		callHomeController.manageAdminCallHomePolicyDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateAdminCallHomePolicies(
				anyList(), PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminCallHomePolicyDetails_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(new NullPointerException()).when(adminServiceMock)
				.deleteAdminCallHomePolicies(anyList());
		callHomeController.manageAdminCallHomePolicyDetails(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).deleteAdminCallHomePolicies(
				anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

}
