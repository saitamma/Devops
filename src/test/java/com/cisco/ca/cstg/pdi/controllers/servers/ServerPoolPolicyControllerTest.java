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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolQualifier;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ServerPoolPolicyControllerTest implements TestConstants{
	
	private MockMvc mockMvc;

	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private ServerPoolPolicyController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showserversServerPoolPolicyConfig()  throws Exception {
		mockMvc.perform(get("/serversServerPoolPolicyConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversServerPoolPolicyConfig"));
	}

	@Test
	public void getServerPoolPolicyConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getServerPoolPolicyConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getServerPoolPolicyConfigDetails"));
	}

	@Test
	public void getServerPoolPolicyConfigDetails_returnTypeCheck() throws Exception {
		ServersServerPoolPolicy obj = new ServersServerPoolPolicy();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); 
		obj.setServersServerPoolQualifier(new ServersServerPoolQualifier(1));
		obj.setServersServerPool(new ServersServerPool(1));
		List<ServersServerPoolPolicy> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"organizations\":null,\"description\":\"Description\",\"name\":\"TEST\",\"serversServerPoolQualifier\":1,\"serversServerPool\":1}]";
		when(serversServiceMock.fetchServersServerPoolPolicyConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversController.getServerPoolPolicyConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getServerPoolPolicyConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getServerPoolPolicyConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersServerPoolPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getServerPoolPolicyConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersServerPoolPolicyConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getServerPoolPolicyConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getServerPoolPolicyConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchServersServerPoolPolicyConfiguration(PROJECT_ID));
		List<Object> jsonList = serversController.getServerPoolPolicyConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolPolicyConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServerPoolPolicyConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServerPoolPolicyConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServerPoolPolicyConfig"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolPolicyConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersServerPoolPolicy obj = new ServersServerPoolPolicy();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); 
		obj.setServersServerPoolQualifier(new ServersServerPoolQualifier(1));
		obj.setServersServerPool(new ServersServerPool(1));
		List<ServersServerPoolPolicy> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"organizations\":null,\"description\":\"Description\",\"name\":\"TEST\",\"serversServerPoolQualifier\":1,\"serversServerPool\":1}]";
		when(serversServiceMock.saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageServerPoolPolicyConfig( json, PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolPolicyConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersServerPoolPolicy(anyList());
		List<Object> objList = serversController.manageServerPoolPolicyConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolPolicyConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageServerPoolPolicyConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolPolicyConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageServerPoolPolicyConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolPolicyConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageServerPoolPolicyConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolPolicyConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServerPoolPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolPolicyConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt()));
		serversController.manageServerPoolPolicyConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolPolicyConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersServerPoolPolicy(anyList());
		serversController.manageServerPoolPolicyConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolPolicyConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersServerPoolPolicy(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
}
