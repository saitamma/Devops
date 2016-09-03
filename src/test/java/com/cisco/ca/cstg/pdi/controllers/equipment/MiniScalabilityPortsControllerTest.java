package com.cisco.ca.cstg.pdi.controllers.equipment;

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

import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class MiniScalabilityPortsControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private EquipmentService equipmentServiceMock;

	@InjectMocks
	private MiniScalabilityPortsController miniScalabilityPortsController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(
				miniScalabilityPortsController).build();
	}

	@Test
	public void showEquipmentMiniScalabilityConfig() throws Exception {		
		mockMvc.perform(get("/equipmentMiniScalabilityConfig.html"))
				.andExpect(status().isOk())
				.andExpect(
						forwardedUrl("equipment/equipmentMiniScalabilityConfig"));
	}
	
	@Test
	public void fetchEquipmentMiniScalabilityConfig_methodNameCheck() throws Exception {		
		this.mockMvc.perform(
				get("/fetchEquipmentMiniScalabilityConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchEquipmentMiniScalabilityConfig"));
	}
	
	@Test
	public void fetchEquipmentMiniScalabilityConfig_returnTypeCheck() throws Exception {
		EquipmentMiniScalability ems = new EquipmentMiniScalability();
		ems.setId(1);
		ems.setChassis(1);
		ems.setFiId("B");
		ems.setSlotId(1);
		ems.setPortId(1);
		ems.setIsConfigured(1);
		
		List<EquipmentMiniScalability> emcList = new ArrayList<EquipmentMiniScalability>();
		emcList.add(ems);
		
		String expected = "[{\"A\":[],\"B\":[\"{\\\"chassis\\\":1,\\\"id\\\":1,\\\"isConfigured\\\":1,\\\"fiId\\\":\\\"B\\\",\\\"slotId\\\":1,\\\"configureAs\\\":null,\\\"portId\\\":1}\"]}]";
		when(equipmentServiceMock.fetchEquipmentMiniScalabilityConfiguration(PROJECT_ID)).thenReturn(emcList);
		List<Object> jsonList = miniScalabilityPortsController.fetchEquipmentMiniScalabilityConfig(PROJECT_ID);		
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1)).fetchEquipmentMiniScalabilityConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}
	
	@Test
	public void fetchEquipmentMiniScalabilityConfig_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = miniScalabilityPortsController
				.fetchEquipmentMiniScalabilityConfig(null);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentMiniScalabilityConfiguration(anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentMiniScalabilityConfig_returnNullCheck() throws Exception {
		String expected = "[{\"A\":[],\"B\":[]}]";
		when(equipmentServiceMock.fetchEquipmentServerConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = miniScalabilityPortsController
				.fetchEquipmentMiniScalabilityConfig(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentMiniScalabilityConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentMiniScalabilityConfig_exception() throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentMiniScalabilityConfiguration(PROJECT_ID);
		miniScalabilityPortsController.fetchEquipmentMiniScalabilityConfig(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentMiniScalabilityConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void manageEquipmentServerConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageEquipmentMiniScalabilityConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageEquipmentMiniScalabilityConfig"));
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentMiniScalabilityConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":1,\"portId\":2,\"isConfigured\":1}], \"deleted\":[]}";
		EquipmentMiniScalability emc = new EquipmentMiniScalability();
		emc.setId(1);		
		emc.setFiId("B");
		emc.setSlotId(1);
		emc.setPortId(2);
		emc.setIsConfigured(1);
		List<EquipmentMiniScalability> emcList = new ArrayList<EquipmentMiniScalability>();
		emcList.add(emc);
		when(
				equipmentServiceMock.saveOrUpdateEquipmentMiniScalabilityConfiguration(
						anyList(), anyInt())).thenReturn(emcList);
		List<Object> list = miniScalabilityPortsController.manageEquipmentMiniScalabilityConfig(
				json, PROJECT_ID);
		String expected = "[{\"chassis\":0,\"id\":1,\"isConfigured\":1,\"fiId\":\"B\",\"slotId\":1,\"configureAs\":null,\"portId\":2}]";
		assertEquals(expected, list.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());		
		verifyNoMoreInteractions(equipmentServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentMiniScalabilityConfig_nullCheck_firstParameter()
			throws Exception {

		List<Object> equipmentServerList = miniScalabilityPortsController
				.manageEquipmentMiniScalabilityConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());		
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentMiniScalabilityConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":1,\"portId\":2,\"isConfigured\":1}], \"deleted\":[]}";
		List<Object> equipmentServerList = miniScalabilityPortsController
				.manageEquipmentMiniScalabilityConfig(json, null);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());		
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentMiniScalabilityConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> equipmentServerList = miniScalabilityPortsController
				.manageEquipmentMiniScalabilityConfig(null, null);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());		
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageEquipmentMiniScalabilityConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":1,\"portId\":2,\"isConfigured\":1}], \"deleted\":[]}";
		when(
				equipmentServiceMock.saveOrUpdateEquipmentMiniScalabilityConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> equipmentServerList = miniScalabilityPortsController
				.manageEquipmentMiniScalabilityConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, equipmentServerList.toString());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());		
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageEquipmentMiniScalabilityConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiId\":\"B\",\"slotId\":1,\"portId\":2,\"isConfigured\":1}], \"deleted\":[]}";
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());
		miniScalabilityPortsController.manageEquipmentMiniScalabilityConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(anyList(), anyInt());		
		verifyNoMoreInteractions(equipmentServiceMock);
	}		
}
