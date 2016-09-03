package com.cisco.ca.cstg.pdi.controllers.equipment;

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

import com.cisco.ca.cstg.pdi.pojos.EquipmentServer;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ServerPortsControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	private EquipmentService equipmentServiceMock;

	@InjectMocks
	private ServerPortsController serverPortsController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serverPortsController)
				.build();
	}

	@Test
	public void showEquipmentServerConfig() throws Exception {
		mockMvc.perform(get("/equipmentServerConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("equipment/equipmentServerConfig"));
	}

	@Test
	public void fetchEquipmentServerConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/fetchEquipmentServerConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchEquipmentServerConfig"));
	}

	@Test
	public void fetchEquipmentServerConfig_returnTypeCheck() throws Exception {
		EquipmentServer equipmentServer = new EquipmentServer();
		equipmentServer.setId(1);
		equipmentServer.setFiId("B");
		equipmentServer.setPortId(2);
		equipmentServer.setSlotId(3);
		equipmentServer.setUserLabel("Test");
		List<EquipmentServer> equipmentServerList = new ArrayList<>();
		equipmentServerList.add(equipmentServer);
		String expected = "[{\"A\":[],\"B\":[\"{\\\"chassis\\\":0,\\\"id\\\":1,\\\"fiId\\\":\\\"B\\\",\\\"slotId\\\":3,\\\"portId\\\":2,\\\"userLabel\\\":\\\"Test\\\"}\"]}]";
		when(equipmentServiceMock.fetchEquipmentServerConfiguration(PROJECT_ID))
				.thenReturn(equipmentServerList);

		List<Object> jsonList = serverPortsController
				.fetchEquipmentServerConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentServerConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentServerConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serverPortsController
				.fetchEquipmentServerConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentServerConfiguration(anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentServerConfig_returnNullCheck() throws Exception {
		String expected = "[{\"A\":[],\"B\":[]}]";
		when(equipmentServiceMock.fetchEquipmentServerConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = serverPortsController
				.fetchEquipmentServerConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentServerConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentServerConfig_exception() throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentServerConfiguration(PROJECT_ID);
		serverPortsController.fetchEquipmentServerConfig(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentServerConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void manageEquipmentServerConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageEquipmentServerConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageEquipmentServerConfig"));
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentServerConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		EquipmentServer equipmentServer = new EquipmentServer();
		equipmentServer.setId(1);
		equipmentServer.setFiId("B");
		equipmentServer.setPortId(2);
		equipmentServer.setSlotId(3);
		equipmentServer.setUserLabel("Test");
		List<EquipmentServer> equipmentServerList = new ArrayList<>();
		equipmentServerList.add(equipmentServer);
		when(
				equipmentServiceMock.saveOrUpdateEquipmentServerConfiguration(
						anyList(), anyInt())).thenReturn(equipmentServerList);
		List<Object> list = serverPortsController.manageEquipmentServerConfig(
				json, PROJECT_ID);
		String expected = "[{\"chassis\":0,\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]";
		assertEquals(expected, list.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentServerConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		EquipmentServer equipmentServer = new EquipmentServer();
		equipmentServer.setId(1);
		equipmentServer.setFiId("B");
		equipmentServer.setPortId(2);
		equipmentServer.setSlotId(3);
		equipmentServer.setUserLabel("Test");
		List<Object> equipmentServerList = new ArrayList<>();
		equipmentServerList.add(equipmentServer);
		doNothing().when(equipmentServiceMock)
				.deleteEquipmentServerConfiguration(anyList());
		equipmentServerList = serverPortsController
				.manageEquipmentServerConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(1))
				.deleteEquipmentServerConfiguration(anyList());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentServerConfig_nullCheck_firstParameter()
			throws Exception {

		List<Object> equipmentServerList = serverPortsController
				.manageEquipmentServerConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentServerConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		List<Object> equipmentServerList = serverPortsController
				.manageEquipmentServerConfig(json, null);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentServerConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> equipmentServerList = serverPortsController
				.manageEquipmentServerConfig(null, null);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentServerConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		when(
				equipmentServiceMock.saveOrUpdateEquipmentServerConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> equipmentServerList = serverPortsController
				.manageEquipmentServerConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentServerConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		serverPortsController.manageEquipmentServerConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentServerConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.deleteEquipmentServerConfiguration(anyList());
		serverPortsController.manageEquipmentServerConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentServerConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(1))
				.deleteEquipmentServerConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}
}
