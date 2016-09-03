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

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ServersSPQSlotMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolQualifier;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ServerPoolQualifierControllerTest implements TestConstants{
	
	private MockMvc mockMvc;
	
	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private ServerPoolQualifierController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showserversServerPoolAndQualifierConfig()  throws Exception {
		mockMvc.perform(get("/serversServerPoolAndQualifierConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversServerPoolAndQualifierConfig"));
	}
	
	@Test
	public void getServerPoolConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/getServerPoolConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getServerPoolConfigDetails"));
	}
	
	@Test
	public void getServerPoolConfigDetails_returnTypeCheck() throws Exception {
		ServersServerPool obj = new ServersServerPool();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST");
		obj.setOrganizations(new Organizations(2)); 
		List<ServersServerPool> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\"}]";
		when(serversServiceMock.fetchServersServerPoolConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversController.getServerPoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getServerPoolConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getServerPoolConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersServerPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getServerPoolConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersServerPoolConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getServerPoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getServerPoolConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchServersServerPoolConfiguration(PROJECT_ID));
		List<Object> jsonList = serversController.getServerPoolConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServerPoolConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServerPoolConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServerPoolConfig"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersServerPool obj = new ServersServerPool();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST");
		obj.setOrganizations(new Organizations(2)); 
		List<ServersServerPool> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\"}]";
		when(serversServiceMock.saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageServerPoolConfig( json, PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersServerPool(anyList());
		List<Object> objList = serversController.manageServerPoolConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageServerPoolConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageServerPoolConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageServerPoolConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServerPoolConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt()));
		serversController.manageServerPoolConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersServerPool(anyList());
		serversController.manageServerPoolConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersServerPool(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getServerPoolQualifierConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/getServerPoolQualifierConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getServerPoolQualifierConfigDetails"));
	}
	
	@Test
	public void getServerPoolQualifierConfigDetails_returnTypeCheck() throws Exception {
		ServersServerPoolQualifier obj = new ServersServerPoolQualifier();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setChassisMaxId(10);
		obj.setOrganizations(new Organizations(2)); obj.setChassisMinId(5);
		List<ServersServerPoolQualifier> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"chassisMinId\":5,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\",\"chassisMaxId\":10}]";
		when(serversServiceMock.fetchServersServerPoolQualifierConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversController.getServerPoolQualifierConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolQualifierConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getServerPoolQualifierConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getServerPoolQualifierConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersServerPoolQualifierConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getServerPoolQualifierConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersServerPoolQualifierConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getServerPoolQualifierConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolQualifierConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getServerPoolQualifierConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchServersServerPoolQualifierConfiguration(PROJECT_ID));
		List<Object> jsonList = serversController.getServerPoolQualifierConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersServerPoolQualifierConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServerPoolQualifierConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServerPoolQualifierConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServerPoolQualifierConfig"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersServerPoolQualifier obj = new ServersServerPoolQualifier();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setChassisMaxId(10);
		obj.setOrganizations(new Organizations(2)); obj.setChassisMinId(5);
		List<ServersServerPoolQualifier> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"chassisMinId\":5,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\",\"chassisMaxId\":10}]";
		when(serversServiceMock.saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversController.manageServerPoolQualifierConfig( json, PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersServerPoolQualifier(anyList());
		List<Object> objList = serversController.manageServerPoolQualifierConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageServerPoolQualifierConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageServerPoolQualifierConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageServerPoolQualifierConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServerPoolQualifierConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolQualifierConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt()));
		serversController.manageServerPoolQualifierConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolQualifierConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersServerPoolQualifier(anyList());
		serversController.manageServerPoolQualifierConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateServersServerPoolQualifierConfiguration(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersServerPoolQualifier(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getSPQSlotMappingConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(post("/getSPQSlotMappingConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getSPQSlotMappingConfigDetails"));
	}
	
	@Test
	public void getSPQSlotMappingConfigDetails_returnTypeCheck() throws Exception {
		ServersSPQSlotMapping obj1 = new ServersSPQSlotMapping(1);
		obj1.setMaxId(10); obj1.setMinId(5);
		ServersSPQSlotMapping obj2 = new ServersSPQSlotMapping(1);
		obj2.setMaxId(12); obj2.setMinId(8);
		List<ServersSPQSlotMapping> objList = new ArrayList<>(); 
		objList.add(obj1); objList.add(obj2);
		String expected = "[{\"id\":1,\"serversServerPoolQualifier\":null,\"maxId\":10,\"minId\":5}, {\"id\":1,\"serversServerPoolQualifier\":null,\"maxId\":12,\"minId\":8}]";
		when(serversServiceMock.fetchServersSPQSlotMappings(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversController.getSPQSlotMappingConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersSPQSlotMappings(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getSPQSlotMappingConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getSPQSlotMappingConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchServersSPQSlotMappings(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getSPQSlotMappingConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchServersSPQSlotMappings(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getSPQSlotMappingConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersSPQSlotMappings(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getSPQSlotMappingConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchServersSPQSlotMappings(PROJECT_ID));
		List<Object> jsonList = serversController.getSPQSlotMappingConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchServersSPQSlotMappings(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServerPoolQualifierSlotConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServerPoolQualifierSlotConfig.html"))
				.andExpect(handler().methodName("manageServerPoolQualifierSlotConfig"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierSlotConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersSPQSlotMapping obj1 = new ServersSPQSlotMapping(1);
		obj1.setMaxId(10); obj1.setMinId(5);
		ServersSPQSlotMapping obj2 = new ServersSPQSlotMapping(1);
		obj2.setMaxId(12); obj2.setMinId(8);
		List<ServersSPQSlotMapping> objList = new ArrayList<>(); 
		objList.add(obj1); objList.add(obj2);
		String expected = "[{\"id\":1,\"serversServerPoolQualifier\":null,\"maxId\":10,\"minId\":5}, {\"id\":1,\"serversServerPoolQualifier\":null,\"maxId\":12,\"minId\":8}]";
		when(serversServiceMock.saveOrUpdateServersSPQSlotMappings(anyList())).thenReturn(objList);
		List<Object> jsonList = serversController.manageServerPoolQualifierSlotConfig( json);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersSPQSlotMappings(anyList());
		verify(serversServiceMock, times(0)).deleteServersSPQSlotMappings(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierSlotConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersSPQSlotMappings(anyList());
		List<Object> objList = serversController.manageServerPoolQualifierSlotConfig( json);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersSPQSlotMappings(anyList());
		verify(serversServiceMock, times(1)).deleteServersSPQSlotMappings(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierSlotConfig_nullCheck() throws Exception {
		List<Object> objList = serversController.manageServerPoolQualifierSlotConfig(null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateServersSPQSlotMappings(anyList());
		verify(serversServiceMock, times(0)).deleteServersSPQSlotMappings(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServerPoolQualifierSlotConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateServersSPQSlotMappings(anyList())).thenReturn(null);
		List<Object> jsonList = serversController.manageServerPoolQualifierSlotConfig(json);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateServersSPQSlotMappings(anyList());
		verify(serversServiceMock, times(0)).deleteServersSPQSlotMappings(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolQualifierSlotConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateServersSPQSlotMappings(anyList()));
		serversController.manageServerPoolQualifierSlotConfig(json);
		verify(serversServiceMock, times(1)).saveOrUpdateServersSPQSlotMappings(anyList());
		verify(serversServiceMock, times(0)).deleteServersSPQSlotMappings(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServerPoolQualifierSlotConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersSPQSlotMappings(anyList());
		serversController.manageServerPoolQualifierSlotConfig(json);
		verify(serversServiceMock, times(0)).saveOrUpdateServersSPQSlotMappings(anyList());
		verify(serversServiceMock, times(1)).deleteServersSPQSlotMappings(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}	

}
