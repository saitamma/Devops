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
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.services.ServiceTemplateService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ServiceProfileTemplateControllerTest implements TestConstants{
	
	private MockMvc mockMvc;	
	
	@Mock
	private ServiceTemplateService serviceTemplateServiceMock;
	
	@InjectMocks
	private ServiceProfileTemplateController serversTemplateController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversTemplateController).build();
	}
	
	@Test
	public void showserversServiceTempConfig()  throws Exception {
		mockMvc.perform(get("/serversServiceTempConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversServiceTempConfig"));
	}
	
	@Test
	public void getServiceTemplateConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getServiceTemplateConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getServiceTemplateConfigDetails"));
	}

	@Test
	public void getServiceTemplateConfigDetails_returnTypeCheck() throws Exception {
		ServersServiceProfileTemplate obj = new ServersServiceProfileTemplate();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setType("type");
		obj.setServersBootPolicy(new ServersBootPolicy(3)); obj.setServersServerPool(new ServersServerPool(4)); 
		obj.setOrganizations(new Organizations(2));  obj.setSanWwnn(new SanWwnn(5)); 
		obj.setServersUuidPool(new ServersUuidPool(6)); 
		List<ServersServiceProfileTemplate> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"serversUuidPool\":6,\"lanConnectivityPolicy\":null,\"organizations\":2,\"serversLocalDisk\":null,\"hostFirmwarePackage\":null,\"type\":\"type\",\"id\":1,\"sanVhba\":null,\"lanVnic\":null,\"description\":\"Description\",\"name\":\"TEST\",\"sanConnectivityPolicy\":null,\"serversServerPool\":4,\"biosPolicy\":null,\"maintenancePolicy\":null,\"serversBootPolicy\":3,\"sanWwnn\":5}]";
		when(serviceTemplateServiceMock.fetchServersServiceProfileTemplateConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serversTemplateController.getServiceTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateServiceMock, times(1)).fetchServersServiceProfileTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateServiceMock);
	}

	@Test
	public void getServiceTemplateConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversTemplateController.getServiceTemplateConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateServiceMock, times(0)).fetchServersServiceProfileTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateServiceMock);
	}

	@Test
	public void getServiceTemplateConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serviceTemplateServiceMock.fetchServersServiceProfileTemplateConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversTemplateController.getServiceTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateServiceMock, times(1)).fetchServersServiceProfileTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getServiceTemplateConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serviceTemplateServiceMock.fetchServersServiceProfileTemplateConfiguration(PROJECT_ID));
		List<Object> jsonList = serversTemplateController.getServiceTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateServiceMock, times(1)).fetchServersServiceProfileTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateServiceMock);
	}

	
	@Test
	public void manageServiceTemplateConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServiceTemplateConfig.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServiceTemplateConfig"));
	     verifyNoMoreInteractions(serviceTemplateServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceTemplateConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"serversUuidPool\":6,\"id\":1,\"sanVhba\":null,\"lanVnic\":null,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\",\"sanConnectivityPolicy\":null,\"lanConnectivityPolicy\":null,\"serversLocalDisk\":null,\"serversServerPool\":4,\"type\":\"type\",\"serversBootPolicy\":3,\"sanWwnn\":5}], \"deleted\":[]}";
		ServersServiceProfileTemplate obj = new ServersServiceProfileTemplate();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST"); obj.setType("type");
		obj.setServersBootPolicy(new ServersBootPolicy(3)); obj.setServersServerPool(new ServersServerPool(4)); 
		obj.setOrganizations(new Organizations(2));  obj.setSanWwnn(new SanWwnn(5)); 
		obj.setServersUuidPool(new ServersUuidPool(6)); 
		List<ServersServiceProfileTemplate> objList = new ArrayList<>(); objList.add(obj);
		when(serviceTemplateServiceMock.saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serversTemplateController.manageServiceTemplateConfig(json, PROJECT_ID);
		String expected = "[{\"serversUuidPool\":6,\"lanConnectivityPolicy\":null,\"organizations\":2,\"serversLocalDisk\":null,\"hostFirmwarePackage\":null,\"type\":\"type\",\"id\":1,\"sanVhba\":null,\"lanVnic\":null,\"description\":\"Description\",\"name\":\"TEST\",\"sanConnectivityPolicy\":null,\"serversServerPool\":4,\"biosPolicy\":null,\"maintenancePolicy\":null,\"serversBootPolicy\":3,\"sanWwnn\":5}]";
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateServiceMock, times(1)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(0)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceTemplateConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"serversUuidPool\":7,\"id\":1,\"sanVhba\":null,\"lanVnic\":null,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\",\"serversServerPool\":4,\"type\":\"type\",\"serversBootPolicy\":3,\"sanWwnn\":6}]}";
		doNothing().when(serviceTemplateServiceMock).deleteServersServiceProfileTemplate(anyList());
		List<Object> objList = serversTemplateController.manageServiceTemplateConfig(json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serviceTemplateServiceMock, times(0)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(1)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceTemplateConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversTemplateController.manageServiceTemplateConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serviceTemplateServiceMock, times(0)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(0)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceTemplateConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversTemplateController.manageServiceTemplateConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serviceTemplateServiceMock, times(0)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(0)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceTemplateConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversTemplateController.manageServiceTemplateConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serviceTemplateServiceMock, times(0)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(0)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceTemplateConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serviceTemplateServiceMock.saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversTemplateController.manageServiceTemplateConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateServiceMock, times(1)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(0)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServiceTemplateConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serviceTemplateServiceMock.saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt()));
		serversTemplateController.manageServiceTemplateConfig(json, PROJECT_ID);
		verify(serviceTemplateServiceMock, times(1)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(0)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServiceTemplateConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serviceTemplateServiceMock).deleteServersServiceProfileTemplate(anyList());
		serversTemplateController.manageServiceTemplateConfig(json, PROJECT_ID);
		verify(serviceTemplateServiceMock, times(0)).saveOrUpdateServersServiceProfileTemplateConfiguration(anyList(), anyInt());
		verify(serviceTemplateServiceMock, times(1)).deleteServersServiceProfileTemplate(anyList());
		verifyNoMoreInteractions(serviceTemplateServiceMock);
	}
}
