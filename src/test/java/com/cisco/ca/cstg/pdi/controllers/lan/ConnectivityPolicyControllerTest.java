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

import com.cisco.ca.cstg.pdi.pojos.LanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanLcpVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ConnectivityPolicyControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private ConnectivityPolicyController connectivityPolicyController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(connectivityPolicyController)
				.build();
	}

	@Test
	public void showLanConnectivityPolicy() throws Exception {
		mockMvc.perform(get("/lanConnectivityPolicy.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanConnectivityPolicy"));
	}

	@Test
	public void getLanConnectivityPolicyDetails_methodNameCheck()
			throws Exception {
		when(lanServiceMock.fetchLanConnectivityPolicyDetail(PROJECT_ID))
				.thenReturn(null);
		this.mockMvc.perform(
				get("/getLanConnectivityPolicyDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanConnectivityPolicyDetails"));
	}

	@Test
	public void getLanConnectivityPolicyDetails_returnTypeCheck()
			throws Exception {
		LanConnectivityPolicy lcp = new LanConnectivityPolicy(1);
		List<LanLcpVnicMapping> list = new ArrayList<>();
		list.add(new LanLcpVnicMapping(new LanVnic(1), lcp));
		list.add(new LanLcpVnicMapping(new LanVnic(2), lcp));
		lcp.setName("test");
		lcp.setDescription("desc");
		lcp.setOrganizations(new Organizations(2));
		lcp.setLanLcpVnicMappings(list);
		List<LanConnectivityPolicy> lcpList = new ArrayList<>();
		lcpList.add(lcp);
		String expected = "[{\"id\":1,\"vnicId\":[1,2],\"organizations\":2,\"description\":\"desc\",\"name\":\"test\"}]";
		when(lanServiceMock.fetchLanConnectivityPolicyDetail(PROJECT_ID))
				.thenReturn(lcpList);
		List<Object> jsonList = connectivityPolicyController
				.getLanConnectivityPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanConnectivityPolicyDetail(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanConnectivityPolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = connectivityPolicyController
				.getLanConnectivityPolicyDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanConnectivityPolicyDetail(
				anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanConnectivityPolicyDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanConnectivityPolicyDetail(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = connectivityPolicyController
				.getLanConnectivityPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanConnectivityPolicyDetail(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanConnectivityPolicyDetails_exception() throws Exception {
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanConnectivityPolicyDetail(PROJECT_ID));
		connectivityPolicyController
				.getLanConnectivityPolicyDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanConnectivityPolicyDetail(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanConnectivityPolicyConfig_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				post("/manageLanConnectivityPolicyConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanConnectivityPolicyConfig"));
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanConnectivityPolicyConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vnicId\":[1,2]}], \"deleted\":[]}";
		LanConnectivityPolicy lcp = new LanConnectivityPolicy(1);
		List<LanLcpVnicMapping> list = new ArrayList<>();
		list.add(new LanLcpVnicMapping(new LanVnic(1), lcp));
		list.add(new LanLcpVnicMapping(new LanVnic(2), lcp));
		lcp.setName("test");
		lcp.setDescription("desc");
		lcp.setOrganizations(new Organizations(2));
		lcp.setLanLcpVnicMappings(list);
		List<LanConnectivityPolicy> lcpList = new ArrayList<>();
		lcpList.add(lcp);
		when(
				lanServiceMock.saveOrUpdateLanConnectivityPolicy(anyList(),
						anyInt())).thenReturn(lcpList);
		List<Object> jsonList = connectivityPolicyController
				.manageLanConnectivityPolicyConfig(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"vnicId\":[1,2],\"organizations\":2,\"description\":\"desc\",\"name\":\"test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanConnectivityPolicyConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = connectivityPolicyController
				.manageLanConnectivityPolicyConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanConnectivityPolicyConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vnicId\":[1,2]}], \"deleted\":[]}";
		List<Object> jsonList = connectivityPolicyController
				.manageLanConnectivityPolicyConfig(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanConnectivityPolicyConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = connectivityPolicyController
				.manageLanConnectivityPolicyConfig(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanConnectivityPolicyConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(lanServiceMock).deleteLanConnectivityPolicy(anyList());
		List<Object> jsonList = connectivityPolicyController
				.manageLanConnectivityPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanConnectivityPolicyConfig_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vnicId\":[1,2]}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanConnectivityPolicy(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lcpList = connectivityPolicyController
				.manageLanConnectivityPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lcpList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanConnectivityPolicyConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vnicId\":[1,2]}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanConnectivityPolicy(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		connectivityPolicyController.manageLanConnectivityPolicyConfig(json,
				PROJECT_ID);
		verify(lanServiceMock, times(1)).saveOrUpdateLanConnectivityPolicy(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanConnectivityPolicyConfig_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanConnectivityPolicy(anyList());
		connectivityPolicyController.manageLanConnectivityPolicyConfig(json,
				PROJECT_ID);
		verify(lanServiceMock, times(0)).saveOrUpdateLanConnectivityPolicy(
				anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
