package com.cisco.ca.cstg.pdi.controllers.servers;

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

import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySanTarget;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class BootPolicyControllerTest implements TestConstants{
	
	private MockMvc mockMvc;
	
	private Integer bootPolicyId = 1;
	
	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private BootPolicyController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showBootPolicyConfig()  throws Exception {
		mockMvc.perform(get("/serversBootPolicyConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversBootPolicyConfig"));
	}
	
	/*@Test
	public void showLanSanPolicyConfig()  throws Exception {
		mockMvc.perform(get("/serversLanSanPolicyConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversLanSanPolicyConfig"));
	}*/

	
	@Test
	public void getBootPolicyConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getBootPolicyConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getBootPolicyConfigDetails"));
	}

	@Test
	public void getBootPolicyConfigDetails_returnTypeCheck() throws Exception {
		ServersBootPolicy obj = new ServersBootPolicy();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setEnforceVnicName(1);
		obj.setRebootOnUpdate(0); obj.setOrganizations(new Organizations()); obj.getOrganizations().setId(2);
		obj.setBootMode("bootMode"); obj.setSecureBoot("true");
		List<ServersBootPolicy> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"rebootOnUpdate\":0,\"id\":1,\"bootMode\":\"bootMode\",\"organizations\":2,\"description\":\"Description\",\"enforceVnicName\":1,\"name\":\"TEST\",\"secureBoot\":\"true\"}]";
		when(serversServiceMock.fetchServersBootPolicyConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversController.getBootPolicyConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersBootPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicyConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getBootPolicyConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersBootPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicyConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersBootPolicyConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getBootPolicyConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersBootPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getBootPolicyConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchServersBootPolicyConfiguration(PROJECT_ID));
		List<Object> jsonList = serversController.getBootPolicyConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersBootPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageBootPolicyConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageBootPolicyConfig.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageBootPolicyConfig"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"rebootOnUpdate\":0,\"id\":1,\"bootMode\":\"bootMode\",\"organizations\":2,\"description\":\"Description\",\"enforceVnicName\":1,\"name\":\"TEST\",\"secureBoot\":\"true\"}], \"deleted\":[]}";
		ServersBootPolicy obj = new ServersBootPolicy();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setEnforceVnicName(1);
		obj.setRebootOnUpdate(0); obj.setOrganizations(new Organizations()); obj.getOrganizations().setId(2);
		obj.setBootMode("bootMode"); obj.setSecureBoot("true");
		List<ServersBootPolicy> objList = new ArrayList<>(); objList.add(obj);
		when(serversServiceMock.saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageBootPolicyConfig(json, PROJECT_ID);
		String expected = "[{\"rebootOnUpdate\":0,\"id\":1,\"bootMode\":\"bootMode\",\"organizations\":2,\"description\":\"Description\",\"enforceVnicName\":1,\"name\":\"TEST\",\"secureBoot\":\"true\"}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doNothing().when(serversServiceMock).deleteServersBootPolicy(anyList());
		List<Object> objList = serversController.manageBootPolicyConfig(json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageBootPolicyConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageBootPolicyConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageBootPolicyConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageBootPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicyConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt()));
		serversController.manageBootPolicyConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicyConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersBootPolicy(anyList());
		serversController.manageBootPolicyConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBootPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicyLanConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(post("/getBootPolicyLanConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getBootPolicyLanConfigDetails"));
	}
	
	@Test
	public void getBootPolicyLanConfigDetails_returnTypeCheck() throws Exception {
		ServersBootPolicyLan obj = new ServersBootPolicyLan();
		obj.setId(1); obj.setOrder(3);obj.setName("TEST"); obj.setLanVnic(new LanVnic(11));
		obj.setType("TT"); obj.setDescription("TEST"); obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicyLan> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"lanVnic\":11,\"order\":3,\"description\":\"TEST\",\"name\":\"TEST\",\"type\":\"TT\",\"serversBootPolicy\":1}]";
		when(serversServiceMock.fetchBootPolicyLanConfigDetails(bootPolicyId)).thenReturn(objList);
		List<Object> jsonList = serversController.getBootPolicyLanConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicyLanConfigDetails(anyInt());
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getBootPolicyLanConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getBootPolicyLanConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchBootPolicyLanConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicyLanConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchBootPolicyLanConfigDetails(bootPolicyId)).thenReturn(null);
		List<Object> jsonList = serversController.getBootPolicyLanConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicyLanConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getBootPolicyLanConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchBootPolicyLanConfigDetails(bootPolicyId));
		List<Object> jsonList = serversController.getBootPolicyLanConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicyLanConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	
	@Test
	public void manageBootPolicyLanConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageBootPolicyLanConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageBootPolicyLanConfig"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLanConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersBootPolicyLan obj = new ServersBootPolicyLan();
		obj.setId(1); obj.setOrder(3);obj.setName("TEST"); obj.setLanVnic(new LanVnic(11));
		obj.setType("TT"); obj.setDescription("TEST"); obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicyLan> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"lanVnic\":11,\"order\":3,\"description\":\"TEST\",\"name\":\"TEST\",\"type\":\"TT\",\"serversBootPolicy\":1}]";
		when(serversServiceMock.saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageBootPolicyLanConfig( json, PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLanConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersBootPolicyLan(anyList());
		List<Object> objList = serversController.manageBootPolicyLanConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLanConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageBootPolicyLanConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLanConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageBootPolicyLanConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLanConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageBootPolicyLanConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLanConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageBootPolicyLanConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicyLanConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt()));
		serversController.manageBootPolicyLanConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicyLanConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersBootPolicyLan(anyList());
		serversController.manageBootPolicyLanConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBootPolicyLan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicySanConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(post("/getBootPolicySanConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getBootPolicySanConfigDetails"));
	}
	
	@Test
	public void getBootPolicySanConfigDetails_returnTypeCheck() throws Exception {
		ServersBootPolicySan obj = new ServersBootPolicySan();
		obj.setId(1); obj.setOrder(2);obj.setName("TEST"); obj.setSanVhba(new SanVhba(13));
		obj.setType("TT"); obj.setDescription("TEST");
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicySan> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"sanVhba\":13,\"order\":2,\"description\":\"TEST\",\"name\":\"TEST\",\"type\":\"TT\",\"serversBootPolicy\":1}]";
		when(serversServiceMock.fetchBootPolicySanConfigDetails(bootPolicyId)).thenReturn(objList);
		List<Object> jsonList = serversController.getBootPolicySanConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicySanConfigDetails(anyInt());
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getBootPolicySanConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getBootPolicySanConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchBootPolicySanConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicySanConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchBootPolicySanConfigDetails(bootPolicyId)).thenReturn(null);
		List<Object> jsonList = serversController.getBootPolicySanConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicySanConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getBootPolicySanConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchBootPolicySanConfigDetails(bootPolicyId));
		List<Object> jsonList = serversController.getBootPolicySanConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicySanConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	
	@Test
	public void manageBootPolicySanConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageBootPolicySanConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageBootPolicySanConfig"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersBootPolicySan obj = new ServersBootPolicySan();
		obj.setId(1); obj.setOrder(2);obj.setName("TEST"); obj.setSanVhba(new SanVhba(13));
		obj.setType("TT"); obj.setDescription("TEST");
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicySan> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"sanVhba\":13,\"order\":2,\"description\":\"TEST\",\"name\":\"TEST\",\"type\":\"TT\",\"serversBootPolicy\":1}]";
		when(serversServiceMock.saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageBootPolicySanConfig( json, PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersBootPolicySan(anyList());
		List<Object> objList = serversController.manageBootPolicySanConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageBootPolicySanConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageBootPolicySanConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageBootPolicySanConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageBootPolicySanConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicySanConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt()));
		serversController.manageBootPolicySanConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicySanConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersBootPolicySan(anyList());
		serversController.manageBootPolicySanConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBootPolicySan(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicySanTargetConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(post("/getBootPolicySanTargetConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getBootPolicySanTargetConfigDetails"));
	}
	
	@Test
	public void getBootPolicySanTargetConfigDetails_returnTypeCheck() throws Exception {
		ServersBootPolicySanTarget obj = new ServersBootPolicySanTarget();
		obj.setId(1); obj.setName("TEST"); obj.setType("TT"); obj.setLunId(1);
		obj.setWwpnAddress("00:00:00:00:00:00:00:00");
		obj.setServersBootPolicySan(new ServersBootPolicySan(1));
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicySanTarget> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"lunId\":1,\"wwpnAddress\":\"00:00:00:00:00:00:00:00\",\"name\":\"TEST\",\"type\":\"TT\",\"serversBootPolicy\":1,\"serversBootPolicySan\":1}]";
		when(serversServiceMock.fetchServersBootPolicySanTargetConfiguration(bootPolicyId)).thenReturn(objList);
		List<Object> jsonList = serversController.getBootPolicySanTargetConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersBootPolicySanTargetConfiguration(anyInt());
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getBootPolicySanTargetConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getBootPolicySanTargetConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersBootPolicySanTargetConfiguration(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicySanTargetConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersBootPolicySanTargetConfiguration(bootPolicyId)).thenReturn(null);
		List<Object> jsonList = serversController.getBootPolicySanTargetConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersBootPolicySanTargetConfiguration(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getBootPolicySanTargetConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchServersBootPolicySanTargetConfiguration(bootPolicyId));
		List<Object> jsonList = serversController.getBootPolicySanTargetConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersBootPolicySanTargetConfiguration(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void manageBootPolicySanTargetConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageBootPolicySanTargetConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageBootPolicySanTargetConfig"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanTargetConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersBootPolicySanTarget obj = new ServersBootPolicySanTarget();
		obj.setId(1); obj.setName("TEST"); obj.setType("TT"); obj.setLunId(1);
		obj.setWwpnAddress("00:00:00:00:00:00:00:00");
		obj.setServersBootPolicySan(new ServersBootPolicySan(1));
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicySanTarget> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"lunId\":1,\"wwpnAddress\":\"00:00:00:00:00:00:00:00\",\"name\":\"TEST\",\"type\":\"TT\",\"serversBootPolicy\":1,\"serversBootPolicySan\":1}]";
		when(serversServiceMock.saveOrUpdateServersBootPolicySanTargetConfiguration(anyList())).thenReturn(objList);
		List<Object> jsonList = serversController.manageBootPolicySanTargetConfig( json);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicySanTargetConfiguration(anyList());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySanTarget(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanTargetConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersBootPolicySanTarget(anyList());
		List<Object> objList = serversController.manageBootPolicySanTargetConfig( json);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanTargetConfiguration(anyList());
		verify(serversServiceMock, times(1)).deleteServersBootPolicySanTarget(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanTargetConfig_nullCheck() throws Exception {
		List<Object> objList = serversController.manageBootPolicySanTargetConfig(null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanTargetConfiguration(anyList());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySanTarget(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicySanTargetConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersBootPolicySanTargetConfiguration(anyList())).thenReturn(null);
		List<Object> jsonList = serversController.manageBootPolicySanTargetConfig(json);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicySanTargetConfiguration(anyList());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySanTarget(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicySanTargetConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersBootPolicySanTargetConfiguration(anyList()));
		serversController.manageBootPolicySanTargetConfig(json);
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicySanTargetConfiguration(anyList());
		verify(serversServiceMock, times(0)).deleteServersBootPolicySanTarget(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicySanTargetConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersBootPolicySanTarget(anyList());
		serversController.manageBootPolicySanTargetConfig(json);
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicySanTargetConfiguration(anyList());
		verify(serversServiceMock, times(1)).deleteServersBootPolicySanTarget(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

		
	@Test
	public void getBootPolicyLocalDiscConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(post("/getBootPolicyLocalDiscConfigDetails.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("getBootPolicyLocalDiscConfigDetails"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getBootPolicyLocalDiscConfigDetails_returnTypeCheck() throws Exception {
		ServersBootPolicyLocalDisc obj = new ServersBootPolicyLocalDisc();
		obj.setId(1); obj.setDevice("SD Card");obj.setBootOrder(1);
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicyLocalDisc> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"bootOrder\":1,\"device\":\"SD Card\",\"serversBootPolicy\":1}]";
		when(serversServiceMock.fetchBootPolicyLocalDiscConfigDetails(anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.getBootPolicyLocalDiscConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicyLocalDiscConfigDetails(anyInt());
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicyLocalDiscConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getBootPolicyLocalDiscConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchBootPolicyLocalDiscConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getBootPolicyLocalDiscConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchBootPolicyLocalDiscConfigDetails(bootPolicyId)).thenReturn(null);
		List<Object> jsonList = serversController.getBootPolicyLocalDiscConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicyLocalDiscConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getBootPolicyLocalDiscConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchBootPolicyLocalDiscConfigDetails(bootPolicyId));
		List<Object> jsonList = serversController.getBootPolicyLocalDiscConfigDetails(bootPolicyId);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBootPolicyLocalDiscConfigDetails(bootPolicyId);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void manageBootPolicyLocalDiscConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageBootPolicyLocalDiscConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageBootPolicyLocalDiscConfig"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLocalDiscConfig_returnTypeCheck() throws Exception {
		String json = "{\"id\":1,\"bootOrder\":1,\"device\":\"SD Card\",\"serversBootPolicy\":1}";
		ServersBootPolicyLocalDisc obj = new ServersBootPolicyLocalDisc();
		obj.setId(1); obj.setDevice("SD Card");obj.setBootOrder(1);
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicyLocalDisc> objList = new ArrayList<>(); objList.add(obj);
		when(serversServiceMock.saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageBootPolicyLocalDiscConfig( json, PROJECT_ID);
		String expected = "[{\"id\":1,\"bootOrder\":1,\"device\":\"SD Card\",\"serversBootPolicy\":1}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLocalDiscConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageBootPolicyLocalDiscConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLocalDiscConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"id\":1}";
		List<Object> objList = serversController.manageBootPolicyLocalDiscConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLocalDiscConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageBootPolicyLocalDiscConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageBootPolicyLocalDiscConfig_returnNullCheck() throws Exception {
		String json = "{\"id\":1}";
		when(serversServiceMock.saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageBootPolicyLocalDiscConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageBootPolicyLocalDiscConfig_exception() throws Exception {
		String json = "{\"id\":1}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt()));
		serversController.manageBootPolicyLocalDiscConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersBootPolicyLocalDiscConfiguration(anyList(), anyInt());
		verifyNoMoreInteractions(serversServiceMock);
	}

}
