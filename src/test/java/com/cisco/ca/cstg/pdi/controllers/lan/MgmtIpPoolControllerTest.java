package com.cisco.ca.cstg.pdi.controllers.lan;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.LanMgmtIppool;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

public class MgmtIpPoolControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private MgmtIpPoolController mgmtIpPoolController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mgmtIpPoolController).build();
	}

	@Test
	public void showLanMgmtIpPool() throws Exception {
		mockMvc.perform(get("/lanMgmtIpPoolConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanMgmtIpPoolConfig"));
	}

	@Test
	public void getLanMgmtIPpoolConfigDetails_methodNameCheck()
			throws Exception {
		when(lanServiceMock.fetchLanVnicConfiguration(PROJECT_ID)).thenReturn(
				null);
		this.mockMvc.perform(
				get("/getLanMgmtIPpoolConfigDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanMgmtIPpoolConfigDetails"));
	}

	@Test
	public void getLanMgmtIPpoolConfigDetails_returnTypeCheck()
			throws Exception {
		LanMgmtIppool lanMgmtIppool = new LanMgmtIppool();
		lanMgmtIppool.setId(1);
		lanMgmtIppool.setDefaultGateway("default");
		lanMgmtIppool.setDescription("desc");
		lanMgmtIppool.setDns("dns");
		lanMgmtIppool.setFromAddress("1.2.3.4");
		lanMgmtIppool.setToAddress("2.3.4.5");
		lanMgmtIppool.setOrganizations(new Organizations());
		lanMgmtIppool.getOrganizations().setId(3);
		List<LanMgmtIppool> lanMgmtIppoolList = new ArrayList<>();
		lanMgmtIppoolList.add(lanMgmtIppool);
		String expected = "[{\"id\":1,\"defaultGateway\":\"default\",\"organizations\":3,\"description\":\"desc\",\"name\":null,\"dns\":\"dns\",\"fromAddress\":\"1.2.3.4\",\"subnetMask\":null,\"toAddress\":\"2.3.4.5\"}]";
		when(lanServiceMock.fetchLanMgmtIPpoolConfiguration(PROJECT_ID))
				.thenReturn(lanMgmtIppoolList);
		List<Object> jsonList = mgmtIpPoolController
				.getLanMgmtIPpoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanMgmtIPpoolConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanMgmtIPpoolConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = mgmtIpPoolController
				.getLanMgmtIPpoolConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanMgmtIPpoolConfiguration(
				anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanMgmtIPpoolConfigDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanMgmtIPpoolConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = mgmtIpPoolController
				.getLanMgmtIPpoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanMgmtIPpoolConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanMgmtIPpoolConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanMgmtIPpoolConfiguration(PROJECT_ID));
		mgmtIpPoolController.getLanMgmtIPpoolConfigDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanMgmtIPpoolConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanMgmtIPpoolConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageLanMgmtIPpoolConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanMgmtIPpoolConfig"));
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMgmtIPpoolConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"defaultGateway\":\"default\",\"organizations\":3,\"description\":\"desc\",\"name\":null,\"dns\":\"dns\",\"fromAddress\":\"1.2.3.4\",\"subnetMask\":null,\"toAddress\":\"2.3.4.5\"}], \"deleted\":[]}";
		LanMgmtIppool lanMgmtIppool = new LanMgmtIppool();
		lanMgmtIppool.setId(1);
		lanMgmtIppool.setDefaultGateway("default");
		lanMgmtIppool.setDescription("desc");
		lanMgmtIppool.setDns("dns");
		lanMgmtIppool.setFromAddress("1.2.3.4");
		lanMgmtIppool.setToAddress("2.3.4.5");
		lanMgmtIppool.setOrganizations(new Organizations());
		lanMgmtIppool.getOrganizations().setId(3);
		List<LanMgmtIppool> lanMgmtIppoolList = new ArrayList<>();
		lanMgmtIppoolList.add(lanMgmtIppool);
		when(
				lanServiceMock.saveOrUpdateLanMgmtIPpoolConfiguration(
						anyList(), anyInt())).thenReturn(lanMgmtIppoolList);
		List<Object> jsonList = mgmtIpPoolController.manageLanMgmtIPpoolConfig(
				json, PROJECT_ID);
		String expected = "[{\"id\":1,\"defaultGateway\":\"default\",\"organizations\":3,\"description\":\"desc\",\"name\":null,\"dns\":\"dns\",\"fromAddress\":\"1.2.3.4\",\"subnetMask\":null,\"toAddress\":\"2.3.4.5\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMgmtIPpoolConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1}]}";
		LanMgmtIppool lanMgmtIppool = new LanMgmtIppool();
		lanMgmtIppool.setId(1);
		lanMgmtIppool.setDefaultGateway("default");
		lanMgmtIppool.setDescription("desc");
		lanMgmtIppool.setDns("dns");
		lanMgmtIppool.setFromAddress("1.2.3.4");
		lanMgmtIppool.setToAddress("2.3.4.5");
		lanMgmtIppool.setOrganizations(new Organizations());
		lanMgmtIppool.getOrganizations().setId(3);
		List<Object> lanMgmtIppoolList = new ArrayList<>();
		lanMgmtIppoolList.add(lanMgmtIppool);
		doNothing().when(lanServiceMock).deleteLanMgmtIPpool(anyList());
		lanMgmtIppoolList = mgmtIpPoolController.manageLanMgmtIPpoolConfig(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanMgmtIppoolList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMgmtIPpoolConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = mgmtIpPoolController.manageLanMgmtIPpoolConfig(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMgmtIPpoolConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"defaultGateway\":\"default\",\"organizations\":3,\"description\":\"desc\",\"name\":null,\"dns\":\"dns\",\"fromAddress\":\"1.2.3.4\",\"subnetMask\":null,\"toAddress\":\"2.3.4.5\"}], \"deleted\":[]}";
		List<Object> jsonList = mgmtIpPoolController.manageLanMgmtIPpoolConfig(
				json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMgmtIPpoolConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = mgmtIpPoolController.manageLanMgmtIPpoolConfig(
				null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMgmtIPpoolConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"defaultGateway\":\"default\",\"organizations\":3,\"description\":\"desc\",\"name\":null,\"dns\":\"dns\",\"fromAddress\":\"1.2.3.4\",\"subnetMask\":null,\"toAddress\":\"2.3.4.5\"}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanMgmtIPpoolConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanVnicList = mgmtIpPoolController
				.manageLanMgmtIPpoolConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanVnicList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanMgmtIPpoolConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"defaultGateway\":\"default\",\"organizations\":3,\"description\":\"desc\",\"name\":null,\"dns\":\"dns\",\"fromAddress\":\"1.2.3.4\",\"subnetMask\":null,\"toAddress\":\"2.3.4.5\"}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanMgmtIPpoolConfiguration(
						anyList(), PROJECT_ID)).thenThrow(
				new NullPointerException());
		mgmtIpPoolController.manageLanMgmtIPpoolConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanMgmtIPpoolConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1}]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanMgmtIPpool(anyList());
		mgmtIpPoolController.manageLanMgmtIPpoolConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanMgmtIPpoolConfiguration(anyList(), PROJECT_ID);
		verify(lanServiceMock, times(1)).deleteLanMgmtIPpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
