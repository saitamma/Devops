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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ServersMaintenancePolicy;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class MaintenancePolicyControllerTest implements TestConstants{
	
	private MockMvc mockMvc;
	
	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private MaintenancePolicyController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showServersMaintenancePolicyConfig()  throws Exception {
		mockMvc.perform(get("/serversMaintenancePolicyConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversMaintenancePolicy"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getMaintenancePolicyDetails_methodNameCheck() throws Exception {
		when(serversServiceMock.fetchMaintenancePolicyDetail(PROJECT_ID)).thenReturn(Matchers.anyList());
		mockMvc.perform(get("/getMaintenancePolicyDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getMaintenancePolicyDetails"));
	}
	
	@Test
	public void getMaintenancePolicyDetails_returnTypeCheck() throws Exception {
		ServersMaintenancePolicy smp = new ServersMaintenancePolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		List<ServersMaintenancePolicy> smpList = new ArrayList<>(); smpList.add(smp);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\",\"rebootPolicy\":null}]";
		when(serversServiceMock.fetchMaintenancePolicyDetail(PROJECT_ID)).thenReturn(smpList);
		List<Object> jsonList = serversController.getMaintenancePolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchMaintenancePolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getMaintenancePolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getMaintenancePolicyDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchMaintenancePolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getMaintenancePolicyDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchMaintenancePolicyDetail(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getMaintenancePolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchMaintenancePolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getMaintenancePolicyDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchMaintenancePolicyDetail(PROJECT_ID));
		List<Object> jsonList = serversController.getMaintenancePolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchMaintenancePolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServersMaintenancePolicyConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServersMaintenancePolicyConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServersMaintenancePolicyConfig"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersMaintenancePolicyConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersMaintenancePolicy smp = new ServersMaintenancePolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		List<ServersMaintenancePolicy> smpList = new ArrayList<>(); smpList.add(smp);
		when(serversServiceMock.saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt())).thenReturn(smpList);
		List<Object> jsonList = serversController.manageServersMaintenancePolicyConfig(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\",\"rebootPolicy\":null}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersMaintenancePolicyConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersMaintenancePolicyDetails(anyList());
		List<Object> objList = serversController.manageServersMaintenancePolicyConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersMaintenancePolicyConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageServersMaintenancePolicyConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersMaintenancePolicyConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageServersMaintenancePolicyConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersMaintenancePolicyConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageServersMaintenancePolicyConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersMaintenancePolicyConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServersMaintenancePolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersMaintenancePolicyConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt()));
		serversController.manageServersMaintenancePolicyConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersMaintenancePolicyConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersMaintenancePolicyDetails(anyList());
		serversController.manageServersMaintenancePolicyConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateMaintenancePolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersMaintenancePolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}	
}