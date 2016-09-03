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

import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class NetworkControlPolicyControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private NetworkControlPolicyController networkControlPolicyController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(
				networkControlPolicyController).build();
	}

	@Test
	public void showLanNetworkControlPolicy() throws Exception {
		mockMvc.perform(get("/lanNetworkControlPolicy.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanNetworkControlPolicy"));
	}

	@Test
	public void getLanNetworkControlPolicyDetails_methodNameCheck()
			throws Exception {
		this.mockMvc.perform(
				get("/getLanNetworkControlPolicyDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanNetworkControlPolicyDetails"));
	}

	@Test
	public void getLanNetworkControlPolicyDetails_returnTypeCheck()
			throws Exception {
		LanNetworkControlPolicy lanNCP = new LanNetworkControlPolicy();
		lanNCP.setId(1);
		lanNCP.setCdp("disabled");
		lanNCP.setNcpName("Name");
		lanNCP.setOrganizations(new Organizations(1));
		lanNCP.setForge("allow");
		lanNCP.setMacRegisterMode("native");
		lanNCP.setDescription("DESC");
		lanNCP.setUplinkFailAction("warning");
		List<LanNetworkControlPolicy> lanNcpList = new ArrayList<>();
		lanNcpList.add(lanNCP);
		String expected = "[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}]";
		when(lanServiceMock.fetchLanNetworkControlPolicyConfig(PROJECT_ID))
				.thenReturn(lanNcpList);
		List<Object> jsonList = networkControlPolicyController
				.getLanNetworkControlPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanNetworkControlPolicyConfig(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanNetworkControlPolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = networkControlPolicyController
				.getLanNetworkControlPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanNetworkControlPolicyConfig(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanNetworkControlPolicyDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanNetworkControlPolicyConfig(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = networkControlPolicyController
				.getLanNetworkControlPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanNetworkControlPolicyConfig(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanNetworkControlPolicyDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanNetworkControlPolicyConfig(PROJECT_ID));
		List<Object> jsonList = networkControlPolicyController
				.getLanNetworkControlPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanNetworkControlPolicyConfig(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanNetworkcontrolPolicyConfig_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				post("/manageLanNetworkControlPolicyConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanNetworkcontrolPolicyConfig"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanNetworkcontrolPolicyConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}], \"deleted\":[]}";
		LanNetworkControlPolicy lanNCP = new LanNetworkControlPolicy();
		lanNCP.setId(1);
		lanNCP.setCdp("disabled");
		lanNCP.setNcpName("Name");
		lanNCP.setOrganizations(new Organizations(1));
		lanNCP.setForge("allow");
		lanNCP.setMacRegisterMode("native");
		lanNCP.setDescription("DESC");
		lanNCP.setUplinkFailAction("warning");
		List<LanNetworkControlPolicy> lanNcpList = new ArrayList<>();
		lanNcpList.add(lanNCP);
		when(
				lanServiceMock.saveOrUpdateNetworkControlPolicyConfig(
						anyList(), anyInt())).thenReturn(lanNcpList);
		List<Object> jsonList = networkControlPolicyController
				.manageLanNetworkcontrolPolicyConfig(json, PROJECT_ID);
		String expected = "[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanNetworkcontrolPolicyConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}]}";
		LanNetworkControlPolicy lanNCP = new LanNetworkControlPolicy();
		lanNCP.setId(1);
		lanNCP.setCdp("disabled");
		lanNCP.setNcpName("Name");
		lanNCP.setOrganizations(new Organizations(1));
		lanNCP.setForge("allow");
		lanNCP.setMacRegisterMode("native");
		lanNCP.setDescription("DESC");
		lanNCP.setUplinkFailAction("warning");
		List<Object> lanNcpList = new ArrayList<>();
		lanNcpList.add(lanNCP);
		doNothing().when(lanServiceMock).deleteLanNetworkControlPolicy(
				anyList());
		lanNcpList = networkControlPolicyController
				.manageLanNetworkcontrolPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanNcpList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanNetworkcontrolPolicyConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = networkControlPolicyController
				.manageLanNetworkcontrolPolicyConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanNetworkcontrolPolicyConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}], \"deleted\":[]}";
		List<Object> jsonList = networkControlPolicyController
				.manageLanNetworkcontrolPolicyConfig(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanNetworkcontrolPolicyConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = networkControlPolicyController
				.manageLanNetworkcontrolPolicyConfig(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanNetworkcontrolPolicyConfig_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateNetworkControlPolicyConfig(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanQosList = networkControlPolicyController
				.manageLanNetworkcontrolPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanQosList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanNetworkcontrolPolicyConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateNetworkControlPolicyConfig(
						anyList(), PROJECT_ID)).thenThrow(
				new NullPointerException());
		networkControlPolicyController.manageLanNetworkcontrolPolicyConfig(
				json, PROJECT_ID);
		verify(lanServiceMock, times(1))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanNetworkcontrolPolicyConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"ncpName\":\"Name\",\"id\":1,\"cdp\":\"disabled\",\"uplinkFailAction\":\"warning\",\"macRegisterMode\":\"native\",\"organizations\":1,\"description\":\"DESC\",\"forge\":\"allow\"}]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanNetworkControlPolicy(anyList());
		networkControlPolicyController.manageLanNetworkcontrolPolicyConfig(
				json, PROJECT_ID);
		verify(lanServiceMock, times(0))
				.saveOrUpdateNetworkControlPolicyConfig(anyList(), PROJECT_ID);
		verify(lanServiceMock, times(1)).deleteLanNetworkControlPolicy(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
