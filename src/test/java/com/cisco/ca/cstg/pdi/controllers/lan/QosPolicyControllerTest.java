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

import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class QosPolicyControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private QosPolicyController qosPolicyController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(qosPolicyController).build();
	}

	@Test
	public void getLanQosPolicyDetails() throws Exception {
		mockMvc.perform(get("/lanQosPolicy.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanQosPolicy"));
	}

	@Test
	public void getLanQosPolicyDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getLanQosPolicyDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanQosPolicyDetails"));
	}

	@Test
	public void getLanQosPolicyDetails_returnTypeCheck() throws Exception {
		LanQosPolicy lanQos = new LanQosPolicy();
		lanQos.setId(1);
		lanQos.setBurst(567);
		lanQos.setName("Test");
		lanQos.setOrganizations(new Organizations(1));
		lanQos.setRate("1234");
		lanQos.setHostControl("none");
		lanQos.setPriority("fc");
		List<LanQosPolicy> lanQosList = new ArrayList<>();
		lanQosList.add(lanQos);
		String expected = "[{\"id\":1,\"rate\":\"1234\",\"organizations\":1,\"priority\":\"fc\",\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}]";
		when(lanServiceMock.fetchLanQosPolicyConfiguration(PROJECT_ID))
				.thenReturn(lanQosList);
		List<Object> jsonList = qosPolicyController
				.getLanQosPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanQosPolicyConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanQosPolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = qosPolicyController
				.getLanQosPolicyDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanQosPolicyConfiguration(
				anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanQosPolicyDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanQosPolicyConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = qosPolicyController
				.getLanQosPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanQosPolicyConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanQosPolicyDetails_exception() throws Exception {
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanQosPolicyConfiguration(PROJECT_ID));
		qosPolicyController.getLanQosPolicyDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanQosPolicyConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanQosPolicyConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageLanQosPolicyConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanQosPolicyConfig"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanQosPolicyConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"rate\":\"1234\",\"priority\":\"fc\",\"organizations\":1,\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}], \"deleted\":[]}";
		LanQosPolicy lanQos = new LanQosPolicy();
		lanQos.setId(1);
		lanQos.setBurst(567);
		lanQos.setName("Test");
		lanQos.setOrganizations(new Organizations(1));
		lanQos.setRate("1234");
		lanQos.setHostControl("none");
		lanQos.setPriority("fc");
		List<LanQosPolicy> lanQosList = new ArrayList<>();
		lanQosList.add(lanQos);
		when(
				lanServiceMock.saveOrUpdateQosPolicyConfiguration(anyList(),
						anyInt())).thenReturn(lanQosList);
		List<Object> jsonList = qosPolicyController.manageLanQosPolicyConfig(
				json, PROJECT_ID);
		String expected = "[{\"id\":1,\"rate\":\"1234\",\"organizations\":1,\"priority\":\"fc\",\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateQosPolicyConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanQosPolicyConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"rate\":\"1234\",\"priority\":\"fc\",\"organizations\":1,\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}]}";
		LanQosPolicy lanQos = new LanQosPolicy();
		lanQos.setId(1);
		lanQos.setBurst(567);
		lanQos.setName("Test");
		lanQos.setOrganizations(new Organizations(1));
		lanQos.setRate("1234");
		lanQos.setHostControl("none");
		lanQos.setPriority("fc");
		List<Object> lanQosList = new ArrayList<>();
		lanQosList.add(lanQos);
		doNothing().when(lanServiceMock).deleteLanQosPolicy(anyList());
		lanQosList = qosPolicyController.manageLanQosPolicyConfig(json,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateQosPolicyConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanQosPolicyConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = qosPolicyController.manageLanQosPolicyConfig(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateQosPolicyConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanQosPolicyConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"rate\":\"1234\",\"priority\":\"fc\",\"organizations\":1,\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}], \"deleted\":[]}";
		List<Object> jsonList = qosPolicyController.manageLanQosPolicyConfig(
				json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateQosPolicyConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanQosPolicyConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = qosPolicyController.manageLanQosPolicyConfig(
				null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateQosPolicyConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanQosPolicyConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateQosPolicyConfiguration(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanQosList = qosPolicyController.manageLanQosPolicyConfig(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateQosPolicyConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanQosPolicyConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"rate\":\"1234\",\"priority\":\"fc\",\"organizations\":1,\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateQosPolicyConfiguration(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		qosPolicyController.manageLanQosPolicyConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(1)).saveOrUpdateQosPolicyConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanQosPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanQosPolicyConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"rate\":\"1234\",\"priority\":\"fc\",\"organizations\":1,\"name\":\"Test\",\"burst\":567,\"hostControl\":\"none\"}]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanQosPolicy(anyList());
		qosPolicyController.manageLanQosPolicyConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(0)).saveOrUpdateQosPolicyConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(1)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
