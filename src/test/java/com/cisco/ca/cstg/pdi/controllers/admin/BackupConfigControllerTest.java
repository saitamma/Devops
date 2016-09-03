package com.cisco.ca.cstg.pdi.controllers.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
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

import com.cisco.ca.cstg.pdi.pojos.AdminBackupConfiguration;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class BackupConfigControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	AdminService adminServiceMock;

	@InjectMocks
	private BackupConfigController backupConfigController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(backupConfigController)
				.build();
	}

	@Test
	public void getAdminBackupConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getAdminBackupConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getAdminBackupConfig"));
	}

	@Test
	public void getAdminBackupConfig_returnTypeCheck() throws Exception {
		AdminBackupConfiguration abc = new AdminBackupConfiguration(1);
		abc.setAdminState("disabled");
		abc.setBackupStatus("enabled");
		abc.setBackupType("config-all");
		abc.setHostname("1.2.3.4");
		abc.setPreserveIdentities("yes");
		abc.setProtocol("tftp");
		abc.setRemoteFile("test");
		List<AdminBackupConfiguration> abcList = new ArrayList<>();
		abcList.add(abc);
		String expected = "[{\"id\":1,\"protocol\":\"tftp\",\"userName\":null,\"hostname\":\"1.2.3.4\",\"backupType\":\"config-all\",\"backupStatus\":\"enabled\",\"password\":null,\"preserveIdentities\":\"yes\",\"adminState\":\"disabled\",\"remoteFile\":\"test\"}]";
		when(adminServiceMock.fetchAdminBackupConfig(PROJECT_ID)).thenReturn(
				abcList);
		List<Object> jsonList = backupConfigController
				.getAdminBackupConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminBackupConfig(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminBackupConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = backupConfigController
				.getAdminBackupConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(0)).fetchAdminBackupConfig(null);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void getAdminBackupConfig_returnNullCheck() throws Exception {
		String expected = "[]";
		when(adminServiceMock.fetchAdminBackupConfig(anyInt()))
				.thenReturn(null);
		List<Object> jsonList = backupConfigController
				.getAdminBackupConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(adminServiceMock, times(1)).fetchAdminBackupConfig(anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test(expected = Exception.class)
	public void getAdminBackupConfig_exception() throws Exception {
		doThrow(Exception.class).when(adminServiceMock).fetchAdminBackupConfig(
				PROJECT_ID);
		backupConfigController.getAdminBackupConfig(PROJECT_ID);
		verify(adminServiceMock, times(1)).fetchAdminBackupConfig(PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

	@Test
	public void manageAdminBackupConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageAdminBackupConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageAdminBackupConfig"));
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminBackupConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "[{\"id\":1,\"backupStatus\":\"enabled\",\"adminState\":\"disabled\",\"backupType\":\"config-all\",\"preserveIdentities\":\"yes\",\"protocol\":\"tftp\",\"hostname\":\"1.2.3.5\",\"remoteFile\":\"test.txt\",\"userName\":\"\",\"password\":\"\"}]";
		AdminBackupConfiguration abc = new AdminBackupConfiguration(1);
		abc.setAdminState("disabled");
		abc.setBackupStatus("enabled");
		abc.setBackupType("config-all");
		abc.setHostname("1.2.3.4");
		abc.setPreserveIdentities("yes");
		abc.setProtocol("tftp");
		abc.setRemoteFile("test");
		List<AdminBackupConfiguration> abcList = new ArrayList<>();
		abcList.add(abc);
		when(adminServiceMock.saveOrUpdateBackupConfig(anyList(), anyInt()))
				.thenReturn(abcList);
		String status = backupConfigController.manageAdminBackupConfig(json,
				PROJECT_ID);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(1)).saveOrUpdateBackupConfig(anyList(),
				anyInt());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminBackupConfig_nullCheck_firstParameter()
			throws Exception {
		String status = backupConfigController.manageAdminBackupConfig(null,
				PROJECT_ID);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(0)).saveOrUpdateBackupConfig(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminBackupConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "[{\"id\":1,\"backupStatus\":\"enabled\",\"adminState\":\"disabled\",\"backupType\":\"config-all\",\"preserveIdentities\":\"yes\",\"protocol\":\"tftp\",\"hostname\":\"1.2.3.5\",\"remoteFile\":\"test.txt\",\"userName\":\"\",\"password\":\"\"}]";
		String status = backupConfigController.manageAdminBackupConfig(json,
				null);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(0)).saveOrUpdateBackupConfig(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminBackupConfig_nullCheck_bothParameters()
			throws Exception {
		String status = backupConfigController.manageAdminBackupConfig(null,
				null);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(0)).saveOrUpdateBackupConfig(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageAdminBackupConfig_returnNullCheck() throws Exception {
		String json = "[{\"id\":1,\"backupStatus\":\"enabled\",\"adminState\":\"disabled\",\"backupType\":\"config-all\",\"preserveIdentities\":\"yes\",\"protocol\":\"tftp\",\"hostname\":\"1.2.3.5\",\"remoteFile\":\"test.txt\",\"userName\":\"\",\"password\":\"\"}]";
		when(adminServiceMock.saveOrUpdateBackupConfig(anyList(), anyInt()))
				.thenReturn(null);
		String status = backupConfigController.manageAdminBackupConfig(json,
				null);
		String expected = "success";
		assertEquals(expected, status);
		verify(adminServiceMock, times(0)).saveOrUpdateBackupConfig(anyList(),
				anyInt());
		verifyNoMoreInteractions(adminServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageAdminBackupConfig_exception() throws Exception {
		String json = "[{\"id\":1,\"backupStatus\":\"enabled\",\"adminState\":\"disabled\",\"backupType\":\"config-all\",\"preserveIdentities\":\"yes\",\"protocol\":\"tftp\",\"hostname\":\"1.2.3.5\",\"remoteFile\":\"test.txt\",\"userName\":\"\",\"password\":\"\"}]";
		when(adminServiceMock.saveOrUpdateBackupConfig(anyList(), PROJECT_ID))
				.thenReturn(null);
		backupConfigController.manageAdminBackupConfig(json, PROJECT_ID);
		verify(adminServiceMock, times(1)).saveOrUpdateBackupConfig(anyList(),
				PROJECT_ID);
		verifyNoMoreInteractions(adminServiceMock);
	}

}
