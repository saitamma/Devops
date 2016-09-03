package com.cisco.ca.cstg.pdi.controllers.equipment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ChassisPolicyControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	private EquipmentService equipmentServiceMock;

	@InjectMocks
	private ChassisPolicyController chassisPolicyController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(chassisPolicyController)
				.build();
	}

	@Test
	public void showEquipmentChasisConfig() throws Exception {
		mockMvc.perform(get("/equipmentChasisConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("equipment/equipmentChasisConfig"));
	}

	@Test
	public void fetchEquipmentChasisDetails_returnTypeCheck() throws Exception {
		EquipmentChasis equipmentChasis = new EquipmentChasis();
		equipmentChasis.setId(1);
		equipmentChasis.setCdpAction("Test Cdp");
		equipmentChasis.setCdpLinkAgg("Test Link");
		equipmentChasis.setPspPowerSupply("Test Psp");
		List<EquipmentChasis> euipmentChasisList = new ArrayList<>();
		euipmentChasisList.add(equipmentChasis);
		String expected = "[{\"id\":1,\"pspPowerSupply\":\"Test Psp\",\"cdpLinkAgg\":\"Test Link\",\"cdpAction\":\"Test Cdp\"}]";
		when(equipmentServiceMock.fetchEquipmentChasisConfiguration(PROJECT_ID))
				.thenReturn(euipmentChasisList);
		List<Object> jsonList = chassisPolicyController
				.fetchEquipmentChasisDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentChasisDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = chassisPolicyController
				.fetchEquipmentChasisDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void fetchEquipmentChasisDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(equipmentServiceMock.fetchEquipmentChasisConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = chassisPolicyController
				.fetchEquipmentChasisDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentChasisDetails_exception() throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		chassisPolicyController.fetchEquipmentChasisDetails(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void isProjectChassisConfigSaved_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getEquipmentChassisDetailByProject").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("isProjectChassisConfigSaved"));
	}

	@Test
	public void isProjectChassisConfigSaved_returnTypeCheck() throws Exception {
		EquipmentChasis equipmentChasis = new EquipmentChasis();
		equipmentChasis.setId(1);
		equipmentChasis.setCdpAction("Test Cdp");
		equipmentChasis.setCdpLinkAgg("Test Link");
		equipmentChasis.setPspPowerSupply("Test Psp");
		List<EquipmentChasis> euipmentChasisList = new ArrayList<>();
		euipmentChasisList.add(equipmentChasis);
		boolean expected = true;
		when(equipmentServiceMock.fetchEquipmentChasisConfiguration(PROJECT_ID))
				.thenReturn(euipmentChasisList);
		boolean actual = chassisPolicyController
				.isProjectChassisConfigSaved(PROJECT_ID);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void isProjectChassisConfigSaved_nullCheck() throws Exception {
		boolean expected = false;
		boolean actual = chassisPolicyController
				.isProjectChassisConfigSaved(null);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(0))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void isProjectChassisConfigSaved_returnNullCheck() throws Exception {
		boolean expected = false;
		when(equipmentServiceMock.fetchEquipmentChasisConfiguration(PROJECT_ID))
				.thenReturn(null);
		boolean actual = chassisPolicyController
				.isProjectChassisConfigSaved(PROJECT_ID);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void isProjectChassisConfigSaved_exception() throws Exception {
		doThrow(new NullPointerException()).when(equipmentServiceMock)
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		chassisPolicyController.isProjectChassisConfigSaved(PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void createEquipmentChasisConfig_methodNameCheck() throws Exception {
		String json = "{\"id\":1,\"pspPowerSupply\":\"Test Psp\",\"cdpLinkAgg\":\"Test Link\",\"cdpAction\":\"Test Cdp\"}";

		this.mockMvc.perform(
				post("/createEquipmentChasisConfig.html").sessionAttr(
						"activeProjectId", PROJECT_ID).param("jsonObj", json))
				.andExpect(handler().methodName("createEquipmentChasisConfig"));
	}

	@Test
	public void createEquipmentChasisConfig_returnTypeCheck() throws Exception {
		doNothing().when(equipmentServiceMock)
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		String json = "{\"id\":1,\"pspPowerSupply\":\"Test Psp\",\"cdpLinkAgg\":\"Test Link\",\"cdpAction\":\"Test Cdp\"}";
		String expected = "success";
		String actual = chassisPolicyController.createEquipmentChasisConfig(
				json, PROJECT_ID);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void createEquipmentChasisConfig_nullCheck_firstParameter()
			throws Exception {
		String expected = "failure";
		String actual = chassisPolicyController.createEquipmentChasisConfig(
				null, PROJECT_ID);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void createEquipmentChasisConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"id\":1,\"pspPowerSupply\":\"Test Psp\",\"cdpLinkAgg\":\"Test Link\",\"cdpAction\":\"Test Cdp\"}";
		String expected = "failure";
		String actual = chassisPolicyController.createEquipmentChasisConfig(
				json, null);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void createEquipmentChasisConfig_nullCheck_bothParameters()
			throws Exception {
		String expected = "failure";
		String actual = chassisPolicyController.createEquipmentChasisConfig(
				null, null);
		assertEquals(expected, actual);
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void createEquipmentChasisConfig_exception() throws Exception {
		doThrow(new IOException()).when(equipmentServiceMock)
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		String json = "{\"id\":1,\"pspPowerSupply\":\"Test Psp\",\"cdpLinkAgg\":\"Test Link\",\"cdpAction\":\"Test Cdp\"}";
		chassisPolicyController.createEquipmentChasisConfig(json, PROJECT_ID);
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentChasisConfiguration(
						(EquipmentChasis) anyObject(), anyInt());
		verifyNoMoreInteractions(equipmentServiceMock);
	}
}
