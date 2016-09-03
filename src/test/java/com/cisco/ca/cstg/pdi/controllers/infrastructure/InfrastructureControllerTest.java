package com.cisco.ca.cstg.pdi.controllers.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class InfrastructureControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private InfrastructureService infrastructureServiceMock;

	@InjectMocks
	private InfrastructureController infrastructureController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(infrastructureController)
				.build();
	}

	@Test
	public void showInfrastructure() throws Exception {
		mockMvc.perform(get("/infrastructure.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("infrastructure/infrastructureMain"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getInfrastructureDetails_mehtodNameCheck() throws Exception {
		when(infrastructureServiceMock.fetchInfrastructureDetails(PROJECT_ID))
				.thenReturn(anyList());
		mockMvc.perform(
				get("/getInfrastructureDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getInfrastructureDetails"));
	}

	@Test
	public void getInfrastructureDetails_returnTypeCheck() throws Exception {
		List<Infrastructure> infrastructureData = new ArrayList<>();
		Infrastructure infraStructure = new Infrastructure();
		infraStructure.setId(1);
		infraStructure.setIoModule("TestModule");
		infraStructure.setServerModel(new ServerModel());
		infraStructure.getServerModel().setDescription("TestServer");
		infraStructure.setSoftwareVersion("2.0");
		infrastructureData.add(infraStructure);
		when(infrastructureServiceMock.fetchInfrastructureDetails(PROJECT_ID))
				.thenReturn(infrastructureData);
		List<Object> jsonList = infrastructureController
				.getInfrastructureDetails(PROJECT_ID);
		String expected = "[{\"id\":1,\"ioModule\":\"TestModule\",\"softwareVersion\":\"2.0\",\"serverModel\":\"TestServer\"}]";
		assertEquals(expected, jsonList.toString());
		verify(infrastructureServiceMock, times(1)).fetchInfrastructureDetails(
				PROJECT_ID);
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void getInfrastructureDetails_nullCheck() throws Exception {
		List<Object> jsonList = infrastructureController
				.getInfrastructureDetails(null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(infrastructureServiceMock, times(0)).fetchInfrastructureDetails(
				PROJECT_ID);
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void getInfrastructureDetails_returnNullCheck() throws Exception {
		when(infrastructureServiceMock.fetchInfrastructureDetails(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = infrastructureController
				.getInfrastructureDetails(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(infrastructureServiceMock, times(1)).fetchInfrastructureDetails(
				PROJECT_ID);
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test(expected = Exception.class)
	public void getInfrastructureDetails_exceptionCheck() throws Exception {
		doThrow(new IOException()).when(
				infrastructureServiceMock
						.fetchInfrastructureDetails(PROJECT_ID));
		infrastructureController.getInfrastructureDetails(PROJECT_ID);
		verify(infrastructureServiceMock, times(1)).fetchInfrastructureDetails(
				PROJECT_ID);
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void saveInfrastructure_methodNameCheck() throws Exception {
		String json = "{\"serverModel\":\"TestServer\",\"softwareVersion\":\"2.0\",\"ioModule\":\"TestModule\"}";
		mockMvc.perform(
				post("/saveInfrastructure.html").sessionAttr("activeProjectId",
						PROJECT_ID).param("jsonObj", json)).andExpect(
				handler().methodName("saveInfrastructure"));
	}

	@Test
	public void saveInfrastructure_returnTypeCheck() throws Exception {
		String json = "{\"serverModel\":\"TestServer\",\"softwareVersion\":\"2.0\",\"ioModule\":\"TestModule\"}";
		ServerModel sm = new ServerModel(1);
		sm.setDescription("test");
		sm.setSoftwareVersion("2.2");
		List<ServerModel> smList = new ArrayList<>();
		smList.add(sm);
		when(infrastructureServiceMock.fetchServerModelDetails(anyString()))
				.thenReturn(smList);
		MockHttpServletRequest req = new MockHttpServletRequest();
		doNothing().when(infrastructureServiceMock).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		String actual = infrastructureController.saveInfrastructure(json,
				PROJECT_ID, req);
		String expected = "success";
		assertEquals(expected, actual);
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(1)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void saveInfrastructure_nullCheck_firstParameter() throws Exception {
		MockHttpServletRequest req = new MockHttpServletRequest();
		String actual = infrastructureController.saveInfrastructure(null,
				PROJECT_ID, req);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(infrastructureServiceMock, times(0)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void saveInfrastructure_nullCheck_secondParameter() throws Exception {
		String json = "{\"serverModel\":\"TestServer\",\"softwareVersion\":\"2.0\",\"ioModule\":\"TestModule\"}";
		MockHttpServletRequest req = new MockHttpServletRequest();
		String actual = infrastructureController.saveInfrastructure(json, null,
				req);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(infrastructureServiceMock, times(0)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void saveInfrastructure_nullCheck_bothParameters() throws Exception {
		MockHttpServletRequest req = new MockHttpServletRequest();
		String actual = infrastructureController.saveInfrastructure(null, null,
				req);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(infrastructureServiceMock, times(0)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void saveInfrastructure_returnNullCheck() throws Exception {
		String json = "{\"serverModel\":\"TestServer\",\"softwareVersion\":\"2.0\",\"ioModule\":\"TestModule\"}";
		when(infrastructureServiceMock.fetchServerModelDetails(anyString()))
				.thenReturn(null);
		doNothing().when(infrastructureServiceMock).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		String actual = infrastructureController.saveInfrastructure(json,
				PROJECT_ID, req);
		String expected = "success";
		assertEquals(expected, actual);
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(1)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test(expected = Exception.class)
	public void saveInfrastructure_exceptionCheck1() throws Exception {
		String json = "{\"serverModel\":\"TestServer\",\"softwareVersion\":\"2.0\",\"ioModule\":\"TestModule\"}";
		doThrow(Exception.class).when(
				infrastructureServiceMock.fetchServerModelDetails(anyString()));
		MockHttpServletRequest req = new MockHttpServletRequest();
		infrastructureController.saveInfrastructure(json, PROJECT_ID, req);
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test(expected = Exception.class)
	public void saveInfrastructure_exceptionCheck2() throws Exception {
		String json = "{\"serverModel\":\"TestServer\",\"softwareVersion\":\"2.0\",\"ioModule\":\"TestModule\"}";
		doThrow(Exception.class).when(infrastructureServiceMock)
				.saveInfrastructureDetails((Infrastructure) anyObject(),
						anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		infrastructureController.saveInfrastructure(json, PROJECT_ID, req);
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(1)).saveInfrastructureDetails(
				(Infrastructure) anyObject(), anyInt());
		verifyNoMoreInteractions(infrastructureServiceMock);
	}

	@Test
	public void checkIfSessionExist_mehtodNameCheck() throws Exception {
		mockMvc.perform(get("/checkIfSessionExist.html")).andExpect(
				handler().methodName("checkIfSessionExist"));
	}
}
