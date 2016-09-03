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

import com.cisco.ca.cstg.pdi.pojos.LanEthernetAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class VnicControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private VnicController vnicController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vnicController).build();
	}

	@Test
	public void showLanVnicConfig() throws Exception {
		mockMvc.perform(get("/lanVnicConfig.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanVnicConfig"));
	}

	@Test
	public void getLanVnicDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getLanVnicDetails.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(
				handler().methodName("getLanVnicDetails"));
	}

	@Test
	public void getLanVnicDetails_returnTypeCheck() throws Exception {
		LanVnic lanVnic = new LanVnic(1);
		lanVnic.setName("test");
		lanVnic.setLanEthernetAdapterPolicies(new LanEthernetAdapterPolicies(2));
		lanVnic.setLanVnicTemplate(new LanVnicTemplate(3));
		List<LanVnic> lanVnicList = new ArrayList<>();
		lanVnicList.add(lanVnic);
		String expected = "[{\"id\":1,\"name\":\"test\",\"lanVnicTemplate\":3,\"lanEthernetAdapterPolicies\":2}]";
		when(lanServiceMock.fetchLanVnic(PROJECT_ID)).thenReturn(lanVnicList);
		List<Object> jsonList = vnicController.getLanVnicDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanVnic(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanVnicDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = vnicController.getLanVnicDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanVnic(anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanVnicDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanVnic(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = vnicController.getLanVnicDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanVnic(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanVnicDetails_exception() throws Exception {
		doThrow(Exception.class).when(lanServiceMock.fetchLanVnic(PROJECT_ID));
		vnicController.getLanVnicDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanVnic(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanVnic_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageLanVnic.html").accept(MediaType.APPLICATION_JSON)
						.sessionAttr("activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanVnic"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnic_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"test\",\"lanVnicTemplate\":3,\"lanEthernetAdapterPolicies\":2}], \"deleted\":[]}";
		LanVnic lanVnic = new LanVnic(1);
		lanVnic.setName("test");
		lanVnic.setLanEthernetAdapterPolicies(new LanEthernetAdapterPolicies(2));
		lanVnic.setLanVnicTemplate(new LanVnicTemplate(3));
		List<LanVnic> lanVnicList = new ArrayList<>();
		lanVnicList.add(lanVnic);
		when(lanServiceMock.saveOrUpdateLanVnic(anyList(), anyInt()))
				.thenReturn(lanVnicList);
		List<Object> jsonList = vnicController.manageLanVnic(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"name\":\"test\",\"lanVnicTemplate\":3,\"lanEthernetAdapterPolicies\":2}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicProfile(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnic_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doNothing().when(lanServiceMock).deleteLanVnicProfile(anyList());
		List<Object> jsonList = vnicController.manageLanVnic(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(1)).deleteLanVnicProfile(anyList());
		verifyNoMoreInteractions(lanServiceMock);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnic_nullCheck_firstParameter() throws Exception {
		List<Object> jsonList = vnicController.manageLanVnic(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicProfile(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnic_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"test\",\"lanVnicTemplate\":3,\"lanEthernetAdapterPolicies\":2}], \"deleted\":[]}";
		List<Object> jsonList = vnicController.manageLanVnic(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicProfile(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnic_nullCheck_bothParameters() throws Exception {
		List<Object> jsonList = vnicController.manageLanVnic(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicProfile(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVnic_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"test\",\"lanVnicTemplate\":3,\"lanEthernetAdapterPolicies\":2}], \"deleted\":[]}";
		when(lanServiceMock.saveOrUpdateLanVnic(anyList(), anyInt()))
				.thenReturn(null);
		List<Object> lanVnicList = vnicController.manageLanVnic(json,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanVnicList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanVnic_exception_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"test\",\"lanVnicTemplate\":3,\"lanEthernetAdapterPolicies\":2}], \"deleted\":[]}";
		when(lanServiceMock.saveOrUpdateLanVnic(anyList(), PROJECT_ID))
				.thenThrow(new NullPointerException());
		vnicController.manageLanVnic(json, PROJECT_ID);
		verify(lanServiceMock, times(1)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(0)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanVnic_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[],\"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanVnicProfile(anyList());
		vnicController.manageLanVnic(json, PROJECT_ID);
		verify(lanServiceMock, times(0)).saveOrUpdateLanVnic(anyList(),
				anyInt());
		verify(lanServiceMock, times(1)).deleteLanVnicTemplate(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
