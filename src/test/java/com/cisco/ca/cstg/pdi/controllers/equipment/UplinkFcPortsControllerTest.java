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

import com.cisco.ca.cstg.pdi.pojos.EquipmentFcport;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class UplinkFcPortsControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private EquipmentService equipmentServiceMock;

	@InjectMocks
	private UplinkFcPortsController uplinkFcPortsController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(uplinkFcPortsController)
				.build();
	}

	@Test
	public void showEquipmentUplinkConfig() throws Exception {
		mockMvc.perform(get("/equipmentUplinkConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("equipment/equipmentUplinkConfig"));
	}

	@Test
	public void getEquipmentUplinkPortForPortChannel_methodNameCheck()
			throws Exception {
		this.mockMvc.perform(
				get("/fetchEquipmentUplinkForPortChannel.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getEquipmentUplinkPortForPortChannel"));
	}

	@Test
	public void getEquipmentUplinkPortForPortChannel_returnTypeCheck()
			throws Exception {
		EquipmentUplink equipmentUpLink = new EquipmentUplink();
		equipmentUpLink.setId(1);
		equipmentUpLink.setFiId("B");
		equipmentUpLink.setPortId(2);
		equipmentUpLink.setSlotId(3);
		equipmentUpLink.setUserLabel("Test");
		List<EquipmentUplink> equipmentUpLinkList = new ArrayList<>();
		equipmentUpLinkList.add(equipmentUpLink);
		String expected = "[{\"A\":[],\"B\":[\"{\\\"id\\\":1,\\\"fiId\\\":\\\"B\\\",\\\"slotId\\\":3,\\\"portId\\\":2,\\\"userLabel\\\":\\\"Test\\\"}\"]}]";
		when(equipmentServiceMock.fetchEquipmentUplinkConfiguration(PROJECT_ID))
				.thenReturn(equipmentUpLinkList);

		List<Object> jsonList = uplinkFcPortsController
				.getEquipmentUplinkPortForPortChannel(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void getEquipmentUplinkPortForPortChannel_nullCheck()
			throws Exception {
		String expected = "[]";
		List<Object> jsonList = uplinkFcPortsController
				.getEquipmentUplinkPortForPortChannel(null);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentUplinkConfiguration(anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void getEquipmentUplinkPortForPortChannel_returnNullCheck()
			throws Exception {
		String expected = "[{\"A\":[],\"B\":[]}]";
		when(equipmentServiceMock.fetchEquipmentUplinkConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = uplinkFcPortsController
				.getEquipmentUplinkPortForPortChannel(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void getEquipmentUplinkPortForPortChannel_exception()
			throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		uplinkFcPortsController
				.getEquipmentUplinkPortForPortChannel(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentUplinkConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/fetchEquipmentUplinkConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchEquipmentUplinkConfig"));
	}

	@Test
	public void fetchEquipmentUplinkConfig_returnTypeCheck() throws Exception {
		EquipmentUplink equipmentUplink = new EquipmentUplink();
		equipmentUplink.setId(1);
		equipmentUplink.setFiId("B");
		equipmentUplink.setPortId(2);
		equipmentUplink.setSlotId(3);
		equipmentUplink.setUserLabel("Test");
		List<EquipmentUplink> equipmentUplinkList = new ArrayList<>();
		equipmentUplinkList.add(equipmentUplink);
		String expected = "[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]";
		when(equipmentServiceMock.fetchEquipmentUplinkConfiguration(PROJECT_ID))
				.thenReturn(equipmentUplinkList);
		List<Object> jsonList = uplinkFcPortsController
				.fetchEquipmentUplinkConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentUplinkConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = uplinkFcPortsController
				.fetchEquipmentUplinkConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentUplinkConfiguration(anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentUplinkConfig_returnNullCheck() throws Exception {
		String expected = "[]";
		when(equipmentServiceMock.fetchEquipmentUplinkConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = uplinkFcPortsController
				.fetchEquipmentUplinkConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentUplinkConfig_exception() throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		uplinkFcPortsController.fetchEquipmentUplinkConfig(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void manageEquipmentUplinkConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageEquipmentUplinkConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageEquipmentUplinkConfig"));
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentUplinkConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		EquipmentUplink equipmentUplink = new EquipmentUplink();
		equipmentUplink.setId(1);
		equipmentUplink.setFiId("B");
		equipmentUplink.setPortId(2);
		equipmentUplink.setSlotId(3);
		equipmentUplink.setUserLabel("Test");
		List<EquipmentUplink> equipmentUplinkList = new ArrayList<>();
		equipmentUplinkList.add(equipmentUplink);
		when(
				equipmentServiceMock.saveOrUpdateEquipmentUplinkConfiguration(
						anyList(), anyInt())).thenReturn(equipmentUplinkList);
		List<Object> list = uplinkFcPortsController
				.manageEquipmentUplinkConfig(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]";
		assertEquals(expected, list.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentUplinkConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		EquipmentUplink equipmentUplink = new EquipmentUplink();
		equipmentUplink.setId(1);
		equipmentUplink.setFiId("B");
		equipmentUplink.setPortId(2);
		equipmentUplink.setSlotId(3);
		equipmentUplink.setUserLabel("Test");
		List<Object> equipmentUplinkList = new ArrayList<>();
		equipmentUplinkList.add(equipmentUplink);
		doNothing().when(equipmentServiceMock)
				.deleteEquipmentUplinkConfiguration(anyList());
		equipmentUplinkList = uplinkFcPortsController
				.manageEquipmentUplinkConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentUplinkList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(1))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentUplinkConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> equipmentUplinkList = uplinkFcPortsController
				.manageEquipmentUplinkConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentUplinkList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentUplinkConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		List<Object> equipmentUplinkList = uplinkFcPortsController
				.manageEquipmentUplinkConfig(json, null);
		String expected = "[]";
		assertEquals(expected, equipmentUplinkList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentUplinkConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> equipmentUplinkList = uplinkFcPortsController
				.manageEquipmentUplinkConfig(null, null);
		String expected = "[]";
		assertEquals(expected, equipmentUplinkList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentUplinkConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		when(
				equipmentServiceMock.saveOrUpdateEquipmentUplinkConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> equipmentUplinkList = uplinkFcPortsController
				.manageEquipmentUplinkConfig(json, null);
		String expected = "[]";
		assertEquals(expected, equipmentUplinkList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentUplinkConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		uplinkFcPortsController.manageEquipmentUplinkConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentUplinkConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentUplinkConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.deleteEquipmentUplinkConfiguration(anyList());
		uplinkFcPortsController.manageEquipmentUplinkConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.deleteEquipmentUplinkConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentFCPortConfig_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/fetchEquipmentFCPortConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchEquipmentFCPortConfig"));
	}

	@Test
	public void fetchEquipmentFCPortConfig_returnTypeCheck() throws Exception {
		EquipmentFcport equipmentFcport = new EquipmentFcport();
		equipmentFcport.setId(1);
		equipmentFcport.setFiId("B");
		equipmentFcport.setPortId(2);
		equipmentFcport.setSlotId(3);
		equipmentFcport.setUserLabel("Test");
		List<EquipmentFcport> equipmentFcportList = new ArrayList<>();
		equipmentFcportList.add(equipmentFcport);
		String expected = "[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]";
		when(equipmentServiceMock.fetchEquipmentFCPortConfiguration(PROJECT_ID))
				.thenReturn(equipmentFcportList);
		List<Object> jsonList = uplinkFcPortsController
				.fetchEquipmentFCPortConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentFCPortConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentFCPortConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = uplinkFcPortsController
				.fetchEquipmentFCPortConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentFCPortConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentFCPortConfig_returnNullCheck() throws Exception {
		String expected = "[]";
		when(equipmentServiceMock.fetchEquipmentFCPortConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = uplinkFcPortsController
				.fetchEquipmentFCPortConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentFCPortConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentFCPortConfig_exception() throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentFCPortConfiguration(PROJECT_ID);
		uplinkFcPortsController.fetchEquipmentFCPortConfig(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentFCPortConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void manageEquipmentFCPortConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageEquipmentFCPortConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageEquipmentFCPortConfig"));
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentFCPortConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		EquipmentFcport equipmentFcport = new EquipmentFcport();
		equipmentFcport.setId(1);
		equipmentFcport.setFiId("B");
		equipmentFcport.setPortId(2);
		equipmentFcport.setSlotId(3);
		equipmentFcport.setUserLabel("Test");
		List<EquipmentFcport> equipmentFcportList = new ArrayList<>();
		equipmentFcportList.add(equipmentFcport);
		when(
				equipmentServiceMock.saveOrUpdateEquipmentFCPortConfiguration(
						anyList(), anyInt())).thenReturn(equipmentFcportList);
		List<Object> jsonList = uplinkFcPortsController
				.manageEquipmentFCPortConfig(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentFCPortConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		EquipmentFcport equipmentFcport = new EquipmentFcport();
		equipmentFcport.setId(1);
		equipmentFcport.setFiId("B");
		equipmentFcport.setPortId(2);
		equipmentFcport.setSlotId(3);
		equipmentFcport.setUserLabel("Test");
		List<Object> equipmentFcportList = new ArrayList<>();
		equipmentFcportList.add(equipmentFcport);
		doNothing().when(equipmentServiceMock)
				.deleteEquipmentFCPortConfiguration(anyList());
		equipmentFcportList = uplinkFcPortsController
				.manageEquipmentFCPortConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentFcportList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(1))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentFCPortConfig_nullCheck_firstParameter()
			throws Exception {
		when(
				equipmentServiceMock.saveOrUpdateEquipmentFCPortConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> equipmentFcportList = uplinkFcPortsController
				.manageEquipmentFCPortConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentFcportList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentFCPortConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		List<Object> equipmentFcportList = uplinkFcPortsController
				.manageEquipmentFCPortConfig(json, null);
		String expected = "[]";
		assertEquals(expected, equipmentFcportList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentFCPortConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> equipmentFcportList = uplinkFcPortsController
				.manageEquipmentFCPortConfig(null, null);
		String expected = "[]";
		assertEquals(expected, equipmentFcportList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentFCPortConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		when(
				equipmentServiceMock.saveOrUpdateEquipmentFCPortConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> equipmentFcportList = uplinkFcPortsController
				.manageEquipmentFCPortConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentFcportList.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentFCPortConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}], \"deleted\":[]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		uplinkFcPortsController.manageEquipmentFCPortConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(0))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentFCPortConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":3,\"portId\":2,\"userLabel\":\"Test\"}]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.deleteEquipmentFCPortConfiguration(anyList());
		uplinkFcPortsController.manageEquipmentFCPortConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentFCPortConfiguration(anyList(), anyInt());
		verify(equipmentServiceMock, times(1))
				.deleteEquipmentFCPortConfiguration(anyList());
		verifyNoMoreInteractions(equipmentServiceMock);
	}
}
