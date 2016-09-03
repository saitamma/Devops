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

import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class VnicTemplateControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private VnicTemplateController vnicTemplateController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vnicTemplateController)
				.build();
	}

	@Test
	public void showVnicTemp() throws Exception {
		mockMvc.perform(get("/lanVnicTempConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanVnicTempConfig"));
	}

	@Test
	public void getLanVnicTemplateConfigDetails_methodNameCheck()
			throws Exception {
		when(lanServiceMock.fetchLanVnicConfiguration(PROJECT_ID)).thenReturn(
				null);
		this.mockMvc.perform(
				get("/getLanVnicTemplateConfigDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanVnicTemplateConfigDetails"));
	}

	@Test
	public void getLanVnicTemplateConfigDetails_returnTypeCheck()
			throws Exception {
		LanVnicTemplate lanVnic = new LanVnicTemplate();
		lanVnic.setId(1);
		lanVnic.setLanMacpool(new LanMacpool(101));
		lanVnic.setVnictName("TestVnic");
		lanVnic.setTemplateType("Template");
		lanVnic.setSwitchId("SwitchId");
		lanVnic.setTarget("Target");
		lanVnic.setOrganizations(new Organizations());
		lanVnic.getOrganizations().setId(3);
		lanVnic.setLanNetworkControlPolicy(new LanNetworkControlPolicy(2));
		lanVnic.setLanQosPolicy(new LanQosPolicy(3));
		List<LanVnicTemplate> lanVnicList = new ArrayList<>();
		lanVnicList.add(lanVnic);
		String expected = "[{\"id\":1,\"vnictName\":\"TestVnic\",\"templateType\":\"Template\",\"lanMacpool\":101,\"organizations\":3,\"description\":null,\"target\":\"Target\",\"lanQosPolicy\":3,\"osType\":null,\"vlanId\":null,\"lanNetworkControlPolicy\":2,\"switchId\":\"SwitchId\"}]";
		when(lanServiceMock.fetchLanVnicConfiguration(PROJECT_ID)).thenReturn(
				lanVnicList);
		List<Object> jsonList = vnicTemplateController
				.getLanVnicTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanVnicConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanVnicTemplateConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = vnicTemplateController
				.getLanVnicTemplateConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanVnicConfiguration(anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanVnicTemplateConfigDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanVnicConfiguration(PROJECT_ID)).thenReturn(
				null);
		List<Object> jsonList = vnicTemplateController
				.getLanVnicTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanVnicConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanVnicTemplateConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanVnicConfiguration(PROJECT_ID));
		vnicTemplateController.getLanVnicTemplateConfigDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanVnicConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanVnicConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageLanVnicConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanVnicConfig"));
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnicConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		LanVnicTemplate lanVnic = new LanVnicTemplate();
		lanVnic.setId(1);
		lanVnic.setLanMacpool(new LanMacpool(101));
		lanVnic.setVnictName("TestVnic");
		lanVnic.setTemplateType("Template");
		lanVnic.setSwitchId("SwitchId");
		lanVnic.setTarget("Target");
		lanVnic.setOrganizations(new Organizations());
		lanVnic.getOrganizations().setId(3);
		lanVnic.setLanNetworkControlPolicy(new LanNetworkControlPolicy(2));
		lanVnic.setLanQosPolicy(new LanQosPolicy(3));
		List<LanVnicTemplate> lanVnicList = new ArrayList<>();
		lanVnicList.add(lanVnic);
		when(
				lanServiceMock.saveOrUpdateLanVnicConfiguration(anyList(),
						anyInt())).thenReturn(lanVnicList);
		List<Object> jsonList = vnicTemplateController.manageLanVnicConfig(
				json, PROJECT_ID);
		String expected = "[{\"id\":1,\"vnictName\":\"TestVnic\",\"templateType\":\"Template\",\"lanMacpool\":101,\"organizations\":3,\"description\":null,\"target\":\"Target\",\"lanQosPolicy\":3,\"osType\":null,\"vlanId\":null,\"lanNetworkControlPolicy\":2,\"switchId\":\"SwitchId\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanVnicConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnicConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(lanServiceMock).deleteLanVnicTemplate(anyList());
		List<Object> jsonList = vnicTemplateController.manageLanVnicConfig(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnicConfig_nullCheck_firstParameter() throws Exception {
		List<Object> jsonList = vnicTemplateController.manageLanVnicConfig(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnicConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnicConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"lanNetworkControlPolicy\":2,\"templateType\":\"Template\",\"lanMacpool\":101,\"organizations\":3,\"description\":null,\"vnictName\":\"TestVnic\",\"target\":\"Target\",\"lanQosPolicy\":3,\"osType\":null,\"vlanId\":null,\"switchId\":\"SwitchId\"}], \"deleted\":[]}";
		List<Object> jsonList = vnicTemplateController.manageLanVnicConfig(
				json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnicConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnicConfig_nullCheck_bothParameters() throws Exception {
		List<Object> jsonList = vnicTemplateController.manageLanVnicConfig(
				null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnicConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnicConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vlanName\":\"Test\",\"target\":\"Target\",\"vnicName\":\"TestVnic\",\"description\":\"desc\",\"macpoolId\":101,\"switchId\":\"SwitchId\",\"templateType\":\"Template\",\"osType\":null,\"organizations\":3}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanVnicConfiguration(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanVnicList = vnicTemplateController.manageLanVnicConfig(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanVnicList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanVnicConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanVnicConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vlanName\":\"Test\",\"target\":\"Target\",\"vnicName\":\"TestVnic\",\"description\":\"desc\",\"macpoolId\":101,\"switchId\":\"SwitchId\",\"templateType\":\"Template\",\"osType\":null,\"organizations\":3}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanVnicConfiguration(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		vnicTemplateController.manageLanVnicConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(1)).saveOrUpdateLanVnicConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanVnicConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"vlanName\":\"Test\",\"id\":1,\"vlanId\":\"vLan\"}]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanVnicTemplate(anyList());
		vnicTemplateController.manageLanVnicConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnicConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(1)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
