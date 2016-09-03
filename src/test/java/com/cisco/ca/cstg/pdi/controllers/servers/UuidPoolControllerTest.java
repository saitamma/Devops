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

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class UuidPoolControllerTest implements TestConstants{
	
	private MockMvc mockMvc;
	
	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private UuidPoolController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showServersUUIDPoolConfig()  throws Exception {
		mockMvc.perform(get("/serversUUIDPoolConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversUUIDPoolConfig"));
	}
	
	@Test
	public void getUUIDPoolConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getUUIDPoolConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getUUIDPoolConfigDetails"));
	}

	@Test
	public void getUUIDPoolConfigDetails_returnTypeCheck() throws Exception {
		ServersUuidPool obj = new ServersUuidPool();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setPrefix("pre");
		obj.setFromAddress("from"); obj.setToAddress("to"); obj.setOrganizations(new Organizations()); obj.getOrganizations().setId(2);
		List<ServersUuidPool> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"prefixType\":null,\"organizations\":2,\"description\":\"Description\",\"prefix\":\"pre\",\"assignmentOrder\":null,\"name\":\"TEST\",\"fromAddress\":\"from\",\"toAddress\":\"to\"}]";
		when(serversServiceMock.fetchServersUuidPoolConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversController.getUUIDPoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersUuidPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getUUIDPoolConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getUUIDPoolConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersUuidPoolConfiguration(anyInt());
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getUUIDPoolConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersUuidPoolConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getUUIDPoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersUuidPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test(expected = Exception.class)
	public void getUUIDPoolConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(serversServiceMock.fetchServersUuidPoolConfiguration(PROJECT_ID));
		serversController.getUUIDPoolConfigDetails(PROJECT_ID);
		verify(serversServiceMock, times(1)).fetchServersUuidPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void manageUUIDPoolConfig_methodNameCheck() throws Exception {
		
		mockMvc.perform(post("/manageUUIDPoolConfig.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageUUIDPoolConfig"));
		
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageUUIDPoolConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersUuidPool obj = new ServersUuidPool();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setPrefix("pre"); obj.setToAddress("0000-000000000003");
		obj.setFromAddress("0000-000000000001"); obj.setSize("3"); obj.setOrganizations(new Organizations()); obj.getOrganizations().setId(2);
		List<ServersUuidPool> objList = new ArrayList<>(); objList.add(obj);
		when(serversServiceMock.saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageUUIDPoolConfig(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"prefixType\":null,\"organizations\":2,\"description\":\"Description\",\"prefix\":\"pre\",\"assignmentOrder\":null,\"name\":\"TEST\",\"fromAddress\":\"0000-000000000001\",\"toAddress\":\"0000-000000000003\"}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageUUIDPoolConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersUuidPool(anyList());
		List<Object> objList = serversController.manageUUIDPoolConfig(json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageUUIDPoolConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> objList = serversController.manageUUIDPoolConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageUUIDPoolConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageUUIDPoolConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageUUIDPoolConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[\"id\":1], \"deleted\":[]}";
		List<Object> objList = serversController.manageUUIDPoolConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageUUIDPoolConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageUUIDPoolConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageUUIDPoolConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[\"id\":1], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		serversController.manageUUIDPoolConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageUUIDPoolConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersUuidPool(anyList());
		serversController.manageUUIDPoolConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersUuidPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersUuidPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);	
	}	
}
