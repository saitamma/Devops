package com.cisco.ca.cstg.pdi.controllers.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
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

import com.cisco.ca.cstg.pdi.pojos.Dns;
import com.cisco.ca.cstg.pdi.pojos.Ntp;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.TimeZone;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class AdminSettingsControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	AdminService adminServiceMock;

	@InjectMocks
	private AdminSettingsController adminSettingsController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminSettingsController)
				.build();
	}

	@Test
	public void showAdminSetting() throws Exception {
		mockMvc.perform(get("/adminSetting.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("admin/adminSetting"));
	}

	@Test
	public void fetchDnsData_methodNameCheck() throws Exception {

		mockMvc.perform(
				get("/fetchDnsData.html").accept(MediaType.APPLICATION_JSON)
						.sessionAttr("activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchDnsData"));

		verify(adminServiceMock, times(1)).fetchDNSDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchDnsData_returnTypeCheck() throws Exception {

		List<Dns> dnsDetailsObjList = new ArrayList<>();
		Dns dns1 = new Dns();
		dns1.setId(1);
		dns1.setIpAddress("1.2.3.4");
		Dns dns2 = new Dns();
		dns2.setId(2);
		dns2.setIpAddress("1.2.3.5");

		dnsDetailsObjList.add(dns1);
		dnsDetailsObjList.add(dns2);

		when(adminServiceMock.fetchDNSDetails(PROJECT_ID)).thenReturn(
				dnsDetailsObjList);
		List<Object> list = adminSettingsController.fetchDnsData(PROJECT_ID);

		assertNotNull(list);
		String expected = "[{\"id\":1,\"ipAddress\":\"1.2.3.4\"}, {\"id\":2,\"ipAddress\":\"1.2.3.5\"}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchDNSDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchDnsData_nullCheck() throws Exception {
		List<Object> list = adminSettingsController.fetchDnsData(null);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).fetchDNSDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchDnsData_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchDNSDetails(anyInt())).thenReturn(null);
		List<Object> list = adminSettingsController.fetchDnsData(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchDNSDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test(expected = Exception.class)
	public void fetchDnsData_exception() throws Exception {

		doThrow(Exception.class).when(adminServiceMock).fetchDNSDetails(
				PROJECT_ID);
		List<Object> list = adminSettingsController.fetchDnsData(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchDNSDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchNtpData_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/fetchNtpData.html").accept(MediaType.APPLICATION_JSON)
						.sessionAttr("activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchNtpData"));

		verify(adminServiceMock, times(1)).fetchNTPDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchNtpData_returnTypeCheck() throws Exception {
		List<Ntp> ntpDetailsObjList = new ArrayList<>();
		Ntp ntp1 = new Ntp();
		ntp1.setId(1);
		ntp1.setIpAddress("1.2.3.4");
		Ntp ntp2 = new Ntp();
		ntp2.setId(2);
		ntp2.setIpAddress("1.2.3.5");

		ntpDetailsObjList.add(ntp1);
		ntpDetailsObjList.add(ntp2);

		when(adminServiceMock.fetchNTPDetails(PROJECT_ID)).thenReturn(
				ntpDetailsObjList);

		List<Object> list = adminSettingsController.fetchNtpData(PROJECT_ID);

		assertNotNull(list);
		String expected = "[{\"id\":1,\"ipAddress\":\"1.2.3.4\"}, {\"id\":2,\"ipAddress\":\"1.2.3.5\"}]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchNTPDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchNtpData_nullCheck() throws Exception {
		List<Object> list = adminSettingsController.fetchNtpData(null);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).fetchNTPDetails(anyInt());
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchNtpData_returNullCheck() throws Exception {
		when(adminServiceMock.fetchNTPDetails(anyInt())).thenReturn(null);
		List<Object> list = adminSettingsController.fetchNtpData(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(1)).fetchNTPDetails(anyInt());
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test(expected = Exception.class)
	public void fetchNtpData_exception() throws Exception {

		doThrow(Exception.class).when(adminServiceMock).fetchNTPDetails(
				anyInt());
		adminSettingsController.fetchNtpData(PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchNTPDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);

	}

	@Test
	public void fetchTimezoneData_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/fetchTimezoneData.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchTimezoneData"));

		verify(adminServiceMock, times(1)).fetchTimezoneDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchTimezoneData_returnTypeCheck() throws Exception {
		when(adminServiceMock.fetchTimezoneDetails(PROJECT_ID)).thenReturn(3);

		int timeZone = adminSettingsController.fetchTimezoneData(PROJECT_ID);

		assertEquals(3, timeZone);
		verify(adminServiceMock, times(1)).fetchTimezoneDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchTimezoneData_nullCheck() throws Exception {
		int timeZone = adminSettingsController.fetchTimezoneData(null);
		assertEquals(0, timeZone);
		verify(adminServiceMock, times(0)).fetchTimezoneDetails(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchTimezoneData_retrunNullCheck() throws Exception {
		when(adminServiceMock.fetchTimezoneDetails(anyInt())).thenReturn(null);
		Integer timeZone = adminSettingsController
				.fetchTimezoneData(PROJECT_ID);
		assertEquals(null, timeZone);
		verify(adminServiceMock, times(1)).fetchTimezoneDetails(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchTimezoneData_exception() throws Exception {

		when(adminServiceMock.fetchTimezoneDetails(PROJECT_ID)).thenThrow(
				new IllegalStateException());
		adminSettingsController.fetchTimezoneData(PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchTimezoneDetails(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageDnsData_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageDnsData.html").accept(MediaType.APPLICATION_JSON)
						.sessionAttr("activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageDnsData"));

		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_returnTypeCheck_addDnsData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		Dns dns1 = new Dns();
		dns1.setId(0);
		dns1.setIpAddress("1.2.3.4");
		List<Dns> dnsDetailsObjList = new ArrayList<>();
		dnsDetailsObjList.add(dns1);

		when(adminServiceMock.saveOrUpdateDNSConfiguration(anyList(), anyInt()))
				.thenReturn(dnsDetailsObjList);
		doNothing().when(adminServiceMock).deleteDNSConfiguration(anyList());

		List<Object> dnsList = adminSettingsController.manageDnsData(json,
				PROJECT_ID);
		String expected = "[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}]";
		assertEquals(expected, dnsList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_returnTypeCheck_updateDnsData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		Dns dns1 = new Dns();
		dns1.setId(1);
		dns1.setIpAddress("1.2.3.4");
		List<Dns> dnsDetailsObjList = new ArrayList<>();
		dnsDetailsObjList.add(dns1);

		when(adminServiceMock.saveOrUpdateDNSConfiguration(anyList(), anyInt()))
				.thenReturn(dnsDetailsObjList);
		doNothing().when(adminServiceMock).deleteDNSConfiguration(anyList());

		List<Object> dnsList = adminSettingsController.manageDnsData(json,
				PROJECT_ID);
		String expected = "[{\"id\":1,\"ipAddress\":\"1.2.3.4\"}]";
		assertEquals(expected, dnsList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_returnTypeCheck_deleteDnsData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}]}";
		Dns dns1 = new Dns();
		dns1.setId(1);
		dns1.setIpAddress("1.2.3.4");
		List<Object> dnsDetailsObjList = new ArrayList<>();
		dnsDetailsObjList.add(dns1);

		when(adminServiceMock.saveOrUpdateDNSConfiguration(anyList(), anyInt()))
				.thenReturn(new ArrayList<Dns>());
		doNothing().when(adminServiceMock).deleteDNSConfiguration(anyList());

		dnsDetailsObjList = adminSettingsController.manageDnsData(json,
				PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, dnsDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(1)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_nullCheck_firstParameter() throws Exception {
		List<Object> dnsDetailsObjList = adminSettingsController.manageDnsData(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, dnsDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_nullCheck_secondParameter_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}]}";
		Dns dns1 = new Dns();
		dns1.setId(1);
		dns1.setIpAddress("1.2.3.4");
		List<Object> dnsDetailsObjList = new ArrayList<>();
		dnsDetailsObjList.add(dns1);

		dnsDetailsObjList = adminSettingsController.manageDnsData(json, null);
		String expected = "[]";
		assertEquals(expected, dnsDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_nullCheck_secondParameter_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		Dns dns1 = new Dns();
		dns1.setId(1);
		dns1.setIpAddress("1.2.3.4");
		List<Object> dnsDetailsObjList = new ArrayList<>();
		dnsDetailsObjList.add(dns1);

		dnsDetailsObjList = adminSettingsController.manageDnsData(json, null);
		String expected = "[]";
		assertEquals(expected, dnsDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_nullCheck_bothParameter() throws Exception {
		List<Object> dnsDetailsObjList = adminSettingsController.manageDnsData(
				null, null);
		String expected = "[]";
		assertEquals(expected, dnsDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_returnNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateDNSConfiguration(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> dnsDetailsObjList = adminSettingsController.manageDnsData(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, dnsDetailsObjList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageDnsData_returnTypeCheck_exception() throws Exception {
		when(adminServiceMock.saveOrUpdateDNSConfiguration(anyList(), anyInt()))
				.thenReturn(null);
		doNothing().when(adminServiceMock).deleteDNSConfiguration(anyList());

		List<Object> list = adminSettingsController.manageDnsData(null,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, list.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateDNSConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteDNSConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageNtpData_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/manageNtpData.html").accept(MediaType.APPLICATION_JSON)
						.sessionAttr("activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageNtpData"));

		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_returnTypeCheck_addNtpData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		Ntp ntp1 = new Ntp();
		ntp1.setId(0);
		ntp1.setIpAddress("1.2.3.4");
		List<Ntp> ntpDetailsObjList = new ArrayList<>();
		ntpDetailsObjList.add(ntp1);

		when(adminServiceMock.saveOrUpdateNTPConfiguration(anyList(), anyInt()))
				.thenReturn(ntpDetailsObjList);
		doNothing().when(adminServiceMock).deleteNTPConfiguration(anyList());

		List<Object> ntpList = adminSettingsController.manageNtpData(json,
				PROJECT_ID);
		String expected = "[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}]";
		assertEquals(expected, ntpList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_returnTypeCheck_updateNtpData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		Ntp ntp1 = new Ntp();
		ntp1.setId(1);
		ntp1.setIpAddress("1.2.3.4");
		List<Ntp> ntpDetailsObjList = new ArrayList<>();
		ntpDetailsObjList.add(ntp1);

		when(adminServiceMock.saveOrUpdateNTPConfiguration(anyList(), anyInt()))
				.thenReturn(ntpDetailsObjList);
		doNothing().when(adminServiceMock).deleteNTPConfiguration(anyList());

		List<Object> ntpList = adminSettingsController.manageNtpData(json,
				PROJECT_ID);
		String expected = "[{\"id\":1,\"ipAddress\":\"1.2.3.4\"}]";
		assertEquals(expected, ntpList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_returnTypeCheck_deleteNtpData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}]}";
		Ntp ntp1 = new Ntp();
		ntp1.setId(1);
		ntp1.setIpAddress("1.2.3.4");
		List<Object> ntpDetailsObjList = new ArrayList<>();
		ntpDetailsObjList.add(ntp1);

		when(adminServiceMock.saveOrUpdateNTPConfiguration(anyList(), anyInt()))
				.thenReturn(new ArrayList<Ntp>());
		doNothing().when(adminServiceMock).deleteNTPConfiguration(anyList());

		ntpDetailsObjList = adminSettingsController.manageNtpData(json,
				PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, ntpDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(1)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_nullCheck_firstParameter() throws Exception {
		List<Object> nptDetailsObjList = adminSettingsController.manageNtpData(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, nptDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_nullCheck_secondParameter_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}]}";
		List<Object> nptDetailsObjList = adminSettingsController.manageNtpData(
				json, null);
		String expected = "[]";
		assertEquals(expected, nptDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_nullCheck_secondParameter_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		Ntp npt1 = new Ntp();
		npt1.setId(1);
		npt1.setIpAddress("1.2.3.4");
		List<Object> nptDetailsObjList = new ArrayList<>();
		nptDetailsObjList.add(npt1);

		nptDetailsObjList = adminSettingsController.manageNtpData(json, null);
		String expected = "[]";
		assertEquals(expected, nptDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_nullCheck_bothParameter() throws Exception {
		List<Object> nptDetailsObjList = adminSettingsController.manageNtpData(
				null, null);
		String expected = "[]";
		assertEquals(expected, nptDetailsObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageNtpData_returnNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		when(adminServiceMock.saveOrUpdateDNSConfiguration(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> nptDetailsObjList = adminSettingsController.manageNtpData(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, nptDetailsObjList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageNtpData_returnTypeCheck_exception() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"ipAddress\":\"1.2.3.4\"}], \"deleted\":[]}";
		doThrow(Exception.class).when(adminServiceMock)
				.saveOrUpdateNTPConfiguration(anyList(), anyInt());
		doNothing().when(adminServiceMock).deleteNTPConfiguration(anyList());
		List<Object> ntpDetailsObjList = adminSettingsController.manageNtpData(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, ntpDetailsObjList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateNTPConfiguration(
				anyList(), anyInt());
		verify(adminServiceMock, times(0)).deleteNTPConfiguration(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTimeZone_methodNameCheck() throws Exception {

		doNothing().when(adminServiceMock).saveOrUpdateTimeZone(3, PROJECT_ID);

		mockMvc.perform(
				post("/manageTimeZone.html").accept(MediaType.APPLICATION_JSON)
						.sessionAttr("activeProjectId", PROJECT_ID)
						.param("jsonObj", "{\"timeZone\":3}")).andExpect(
				handler().methodName("manageTimeZone"));

		verify(adminServiceMock, times(1)).saveOrUpdateTimeZone(anyInt(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTimeZone_returnTypeCheck() throws Exception {
		String json = "{\"timeZone\":1}";

		doNothing().when(adminServiceMock).saveOrUpdateTimeZone(1, PROJECT_ID);

		String actual = adminSettingsController
				.manageTimeZone(json, PROJECT_ID);
		String expected = "success";
		assertEquals(expected, actual);
		verify(adminServiceMock, times(1)).saveOrUpdateTimeZone(1, PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void manageTimeZone_nullCheck_firstParameter() throws Exception {

		doNothing().when(adminServiceMock).saveOrUpdateTimeZone(null,
				PROJECT_ID);

		adminSettingsController.manageTimeZone(null, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateTimeZone(null,
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageTimeZone_nullCheck_secondParameter() throws Exception {
		String json = "{\"timeZone\":1}";
		String actual = adminSettingsController.manageTimeZone(json, null);
		assertEquals(null, actual);
		verify(adminServiceMock, times(0)).saveOrUpdateTimeZone(1, PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void manageTimeZone_nullCheck_bothParameter() throws Exception {
		doNothing().when(adminServiceMock).saveOrUpdateTimeZone(null, null);

		adminSettingsController.manageTimeZone(null, null);
		verify(adminServiceMock, times(1)).saveOrUpdateTimeZone(null, null);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void manageTimeZone_returnTypeCheck_exception() throws Exception {
		String json = "{\"timeZone\":1}";
		doThrow(IllegalStateException.class).when(adminServiceMock)
				.saveOrUpdateTimeZone(1, PROJECT_ID);

		adminSettingsController.manageTimeZone(json, PROJECT_ID);

		verify(adminServiceMock, times(1)).saveOrUpdateTimeZone(1, PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void updateOrganization_methodNameCheck() throws Exception {

		mockMvc.perform(
				post("/updateOrganization.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("updateOrganization"));

		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_returnTypeCheck_addOrgData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}], \"deleted\": []}";
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(2);
		org.setName("America");
		List<Organizations> orgObjList = new ArrayList<>();
		orgObjList.add(org);

		when(adminServiceMock.saveOrUpdateOrganization(anyList(), anyInt()))
				.thenReturn(orgObjList);
		when(adminServiceMock.deleteOrganization(anyList())).thenReturn(false);

		List<Object> orgList = adminSettingsController.updateOrganization(json,
				PROJECT_ID);
		String expected = "[{\"id\":1,\"parentId\":2,\"name\":\"America\"}]";
		assertEquals(expected, orgList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_returnTypeCheck_updateOrgData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}], \"deleted\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}]}";
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(2);
		org.setName("America");
		List<Object> orgObjList = new ArrayList<>();
		orgObjList.add(org);

		when(adminServiceMock.saveOrUpdateOrganization(anyList(), anyInt()))
				.thenReturn(new ArrayList<Organizations>());
		when(adminServiceMock.deleteOrganization(anyList())).thenReturn(true);

		orgObjList = adminSettingsController.updateOrganization(json,
				PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(1)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_returnTypeCheck_deleteOrgData()
			throws Exception {
		String json = "{\"addOrUpdate\":[ ], \"deleted\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}]}";
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(2);
		org.setName("America");
		List<Object> orgObjList = new ArrayList<>();
		orgObjList.add(org);
		when(adminServiceMock.deleteOrganization(anyList())).thenReturn(true);
		orgObjList = adminSettingsController.updateOrganization(json,
				PROJECT_ID);
		String expected = "[success]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(1)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_nullCheck_firstParameter() throws Exception {
		List<Object> orgObjList = adminSettingsController.updateOrganization(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[ ], \"deleted\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}]}";
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(2);
		org.setName("America");
		List<Object> orgObjList = new ArrayList<>();
		orgObjList.add(org);
		orgObjList = adminSettingsController.updateOrganization(json, null);
		String expected = "[]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_nullCheck_bothParameter() throws Exception {
		List<Object> orgObjList = adminSettingsController.updateOrganization(
				null, null);
		String expected = "[]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_retrunNullCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}], \"deleted\":[]}";
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(2);
		org.setName("America");
		List<Object> orgObjList = new ArrayList<>();
		orgObjList.add(org);
		when(adminServiceMock.saveOrUpdateOrganization(anyList(), anyInt()))
				.thenReturn(null);
		orgObjList = adminSettingsController.updateOrganization(json,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(0)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateOrganization_retrunFalseCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":0,\"parentId\":0,\"name\":\"America\"}]}";
		when(adminServiceMock.deleteOrganization(anyList())).thenReturn(false);
		List<Object> orgObjList = adminSettingsController.updateOrganization(
				json, PROJECT_ID);
		String expected = "[failure]";
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(0)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(1)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void updateOrganization_returnTypeCheck_exception() throws Exception {
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(2);
		org.setName("America");
		List<Object> orgObjList = new ArrayList<>();
		orgObjList.add(org);

		when(adminServiceMock.saveOrUpdateOrganization(anyList(), anyInt()))
				.thenReturn(null);
		doNothing().when(adminServiceMock).deleteOrganization(anyList());

		orgObjList = adminSettingsController.updateOrganization(null,
				PROJECT_ID);
		String expected = "[success]";
		// TODO does it should return success?
		assertEquals(expected, orgObjList.toString());
		verify(adminServiceMock, times(1)).saveOrUpdateOrganization(anyList(),
				anyInt());
		verify(adminServiceMock, times(1)).deleteOrganization(anyList());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchOrganizationsDetail_methodNameCheck() throws Exception {

		mockMvc.perform(
				get("/getOrganizations.html")
						.accept(MediaType.APPLICATION_JSON).sessionAttr(
								"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchOrganizationsDetail"));
	}

	@Test
	public void fetchOrganizationsDetail_returnTypeCheck() throws Exception {
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(0);
		org.setName("Test");

		when(adminServiceMock.fetchRootOrganization(PROJECT_ID))
				.thenReturn(org);
		when(
				adminServiceMock
						.convertOrganizationsDetailsInJSonFormat(any(Organizations.class)))
				.thenReturn(
						"[{\"id\":1,\"parentId\":0,\"name\":\"root\",\"depth\":0}]");

		String jsonString = adminSettingsController
				.fetchOrganizationsDetail(PROJECT_ID);

		assertNotNull(jsonString);
		String expected = "[{\"id\":1,\"parentId\":0,\"name\":\"root\",\"depth\":0}]";
		assertEquals(expected, jsonString);
		verify(adminServiceMock, times(1)).fetchRootOrganization(PROJECT_ID);
		verify(adminServiceMock, times(1))
				.convertOrganizationsDetailsInJSonFormat(
						any(Organizations.class));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchOrganizationsDetail_nullCheck() throws Exception {
		String jsonString = adminSettingsController
				.fetchOrganizationsDetail(null);

		assertNotNull(jsonString);
		String expected = "[]";
		assertEquals(expected, jsonString);
		verify(adminServiceMock, times(0)).fetchRootOrganization(anyInt());
		verify(adminServiceMock, times(0))
				.convertOrganizationsDetailsInJSonFormat(
						any(Organizations.class));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchOrganizationsDetail_returnNullCheck1() throws Exception {
		when(adminServiceMock.fetchRootOrganization(PROJECT_ID)).thenReturn(
				null);
		String jsonString = adminSettingsController
				.fetchOrganizationsDetail(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonString);
		verify(adminServiceMock, times(1)).fetchRootOrganization(anyInt());
		verify(adminServiceMock, times(0))
				.convertOrganizationsDetailsInJSonFormat(
						any(Organizations.class));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchOrganizationsDetail_returnNullCheck2() throws Exception {
		Organizations org = new Organizations();
		org.setId(1);
		org.setParentId(0);
		org.setName("Test");
		when(adminServiceMock.fetchRootOrganization(PROJECT_ID))
				.thenReturn(org);
		when(
				adminServiceMock
						.convertOrganizationsDetailsInJSonFormat(any(Organizations.class)))
				.thenReturn(null);
		String jsonString = adminSettingsController
				.fetchOrganizationsDetail(PROJECT_ID);
		assertEquals(null, jsonString);
		verify(adminServiceMock, times(1)).fetchRootOrganization(anyInt());
		verify(adminServiceMock, times(1))
				.convertOrganizationsDetailsInJSonFormat(
						any(Organizations.class));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchOrganizationsDetail_exception() throws Exception {

		doThrow(new NullPointerException()).when(adminServiceMock)
				.fetchRootOrganization(PROJECT_ID);
		adminSettingsController.fetchOrganizationsDetail(PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchRootOrganization(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchListOfTimeZones_methodNameCheck() throws Exception {

		mockMvc.perform(
				get("/fetchListOfTimeZones.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchListOfTimeZones"));
	}

	@Test
	public void fetchListOfTimeZones_returnTypeCheck() throws Exception {
		TimeZone timeZone = new TimeZone(1);
		timeZone.setCountryTimeZone("Asia");
		timeZone.setCountryCode("code");
		List<TimeZone> list = new ArrayList<>();
		list.add(timeZone);
		when(adminServiceMock.fetchTimeZoneList()).thenReturn(list);
		List<Object> jsonObject = adminSettingsController
				.fetchListOfTimeZones();
		assertNotNull(jsonObject);
		String expected = "[{\"id\":1,\"timeZone\":\"Asia\",\"comments\":null}]";
		assertEquals(expected, jsonObject.toString());
		verify(adminServiceMock, times(1)).fetchTimeZoneList();
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void fetchListOfTimeZones_returnNullCheck() throws Exception {
		when(adminServiceMock.fetchTimeZoneList()).thenReturn(null);
		List<Object> jsonObject = adminSettingsController
				.fetchListOfTimeZones();
		assertNotNull(jsonObject);
		String expected = "[]";
		assertEquals(expected, jsonObject.toString());
		verify(adminServiceMock, times(1)).fetchTimeZoneList();
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchListOfTimeZones_exception() throws Exception {

		doThrow(new NullPointerException()).when(adminServiceMock)
				.fetchTimeZoneList();
		adminSettingsController.fetchListOfTimeZones();
		verify(adminServiceMock, times(1)).fetchTimeZoneList();
		verifyNoMoreInteractions(adminServiceMock);
	}

}
