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
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class VlanMacPoolControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private VlanMacPoolController vlanMacPoolController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vlanMacPoolController)
				.build();
	}

	@Test
	public void showVlanMacPool() throws Exception {
		mockMvc.perform(get("/lanVlanMacPoolConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanVlanMacPoolConfig"));
	}

	@Test
	public void getLanVlanConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getLanVlanConfigDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanVlanConfigDetails"));
	}

	@Test
	public void getLanVlanConfigDetails_returnTypeCheck() throws Exception {
		LanVlan lanVlan = new LanVlan();
		lanVlan.setId(1);
		lanVlan.setVlanId("vLan");
		lanVlan.setVlanName("Test");
		List<LanVlan> lanVlanList = new ArrayList<>();
		lanVlanList.add(lanVlan);
		String expected = "[{\"vlanDefault\":null,\"vlanName\":\"Test\",\"id\":1,\"description\":null,\"vlanId\":\"vLan\"}]";
		when(lanServiceMock.fetchLanVlanConfiguration(PROJECT_ID)).thenReturn(
				lanVlanList);
		List<Object> jsonList = vlanMacPoolController
				.getLanVlanConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanVlanConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanVlanConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = vlanMacPoolController
				.getLanVlanConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanVlanConfiguration(anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanVlanConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanVlanConfiguration(PROJECT_ID)).thenReturn(
				null);
		List<Object> jsonList = vlanMacPoolController
				.getLanVlanConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanVlanConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void getLanVlanConfigDetails_exception() throws Exception {
		when(lanServiceMock.fetchLanVlanConfiguration(PROJECT_ID)).thenThrow(
				Exception.class);
		vlanMacPoolController.getLanVlanConfigDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanVlanConfiguration(PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanVlanConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageLanVlanConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanVlanConfig"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVlanConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vlanName\":\"Test\",\"vlanId\":\"vLan\"}], \"deleted\":[]}";
		LanVlan lanVlan = new LanVlan();
		lanVlan.setId(1);
		lanVlan.setVlanId("vLan");
		lanVlan.setVlanName("Test");
		List<LanVlan> lanVlanList = new ArrayList<>();
		lanVlanList.add(lanVlan);
		when(
				lanServiceMock.saveOrUpdateLanVlanConfiguration(anyList(),
						anyInt())).thenReturn(lanVlanList);
		List<Object> jsonList = vlanMacPoolController.manageLanVlanConfig(json,
				PROJECT_ID);
		String expected = "[{\"vlanDefault\":null,\"vlanName\":\"Test\",\"id\":1,\"description\":null,\"vlanId\":\"vLan\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVlanConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"vlanName\":\"Test\",\"id\":1,\"vlanId\":\"vLan\"}]}";
		LanVlan lanVlan = new LanVlan();
		lanVlan.setId(1);
		lanVlan.setVlanId("vLan");
		lanVlan.setVlanName("Test");
		List<Object> lanVlanList = new ArrayList<>();
		lanVlanList.add(lanVlan);
		doNothing().when(lanServiceMock).deleteLanVlan(anyList());
		lanVlanList = vlanMacPoolController.manageLanVlanConfig(json,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanVlanList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVlanConfig_returnNullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = vlanMacPoolController.manageLanVlanConfig(null,
				PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVlanConfig_returnNullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vlanName\":\"Test\",\"vlanId\":\"vLan\"}], \"deleted\":[]}";
		List<Object> jsonList = vlanMacPoolController.manageLanVlanConfig(json,
				null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVlanConfig_returnNullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = vlanMacPoolController.manageLanVlanConfig(null,
				null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanVlanConfig_returnTypeCheck_returnNullParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanVlanConfiguration(anyList(),
						anyInt())).thenReturn(null);
		List<Object> lanVlanList = vlanMacPoolController.manageLanVlanConfig(
				json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanVlanList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanVlanConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vlanName\":\"Test\",\"vlanId\":\"vLan\"}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanVlanConfiguration(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		vlanMacPoolController.manageLanVlanConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(1)).saveOrUpdateLanVlanConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanVlan(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanVlanConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"vlanName\":\"Test\",\"id\":1,\"vlanId\":\"vLan\"}]}";
		doThrow(new NullPointerException()).when(lanServiceMock).deleteLanVlan(
				anyList());
		vlanMacPoolController.manageLanVlanConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(0)).saveOrUpdateLanVlanConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanMacpoolConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getLanMacpoolConfigDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanMacpoolConfigDetails"));
	}

	@Test
	public void getLanMacpoolConfigDetails_returnTypeCheck() throws Exception {
		LanMacpool macPool = new LanMacpool();
		macPool.setId(1);
		macPool.setMacpoolName("MacPool");
		macPool.setMacpoolDescription("Test");
		macPool.setAssignmentOrder("2");
		macPool.setFromAddress("from");
		macPool.setToAddress("to");
		macPool.setOrganizations(new Organizations());
		macPool.getOrganizations().setId(3);
		List<LanMacpool> macPoolList = new ArrayList<>();
		macPoolList.add(macPool);
		String expected = "[{\"id\":1,\"macpoolName\":\"MacPool\",\"organizations\":3,\"assignmentOrder\":\"2\",\"fromAddress\":\"from\",\"toAddress\":\"to\",\"macpoolDescription\":\"Test\"}]";
		when(lanServiceMock.fetchLanMacpoolConfiguration(PROJECT_ID))
				.thenReturn(macPoolList);
		List<Object> jsonList = vlanMacPoolController
				.getLanMacpoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanMacpoolConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanMacpoolConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = vlanMacPoolController
				.getLanMacpoolConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanMacpoolConfiguration(anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanMacpoolConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanMacpoolConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = vlanMacPoolController
				.getLanMacpoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanMacpoolConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanMacpoolConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(lanServiceMock)
				.fetchLanMacpoolConfiguration(PROJECT_ID);
		vlanMacPoolController.getLanMacpoolConfigDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanMacpoolConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanMacpoolConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/manageLanMacpoolConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanMacpoolConfig"));
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMacpoolConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		LanMacpool macPool = new LanMacpool();
		macPool.setId(1);
		macPool.setMacpoolName("MacPool");
		macPool.setMacpoolDescription("Test");
		macPool.setAssignmentOrder("2");
		macPool.setFromAddress("from");
		macPool.setToAddress("to");
		macPool.setOrganizations(new Organizations());
		macPool.getOrganizations().setId(3);
		List<LanMacpool> macPoolList = new ArrayList<>();
		macPoolList.add(macPool);
		when(
				lanServiceMock.saveOrUpdateLanMacpoolConfiguration(anyList(),
						anyInt())).thenReturn(macPoolList);
		List<Object> jsonList = vlanMacPoolController.manageLanMacpoolConfig(
				json, PROJECT_ID);
		String expected = "[{\"id\":1,\"macpoolName\":\"MacPool\",\"organizations\":3,\"assignmentOrder\":\"2\",\"fromAddress\":\"from\",\"toAddress\":\"to\",\"macpoolDescription\":\"Test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMacpoolConfig_nullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = vlanMacPoolController.manageLanMacpoolConfig(
				null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	/*@SuppressWarnings("unchecked")
	@Test
	public void manageLanMacpoolConfig_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[\"id\":1,\"macpoolName\":\"MacPool\",\"organizations\":3,\"assignmentOrder\":\"2\",\"fromAddress\":\"from\",\"toAddress\":\"to\",\"macpoolDescription\":\"Test\"], \"deleted\":[]}";
		List<Object> jsonList = vlanMacPoolController.manageLanMacpoolConfig(
				json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}*/

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMacpoolConfig_nullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = vlanMacPoolController.manageLanMacpoolConfig(
				null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMacpoolConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(lanServiceMock).deleteLanMacpool(anyList());
		List<Object> macPoolList = vlanMacPoolController
				.manageLanMacpoolConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, macPoolList.toString());
		verify(lanServiceMock, times(1)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanMacpoolConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanMacpoolConfiguration(anyList(),
						anyInt())).thenReturn(null);
		List<Object> macPoolList = vlanMacPoolController
				.manageLanMacpoolConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, macPoolList.toString());
		verify(lanServiceMock, times(1)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanMacpoolConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanMacpoolConfiguration(anyList(),
						PROJECT_ID)).thenThrow(new NullPointerException());
		vlanMacPoolController.manageLanMacpoolConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(1)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanMacpoolConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanMacpool(anyList());
		vlanMacPoolController.manageLanMacpoolConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(0)).saveOrUpdateLanMacpoolConfiguration(
				anyList(), PROJECT_ID);
		verify(lanServiceMock, times(1)).deleteLanMacpool(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

}
