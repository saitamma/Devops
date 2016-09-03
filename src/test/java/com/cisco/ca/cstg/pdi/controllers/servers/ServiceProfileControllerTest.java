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
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfile;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.services.ServiceTemplateService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ServiceProfileControllerTest implements TestConstants{
	
	private MockMvc mockMvc;
	
	@Mock
	private ServiceTemplateService serviceTemplateService;
	
	@InjectMocks
	private ServiceProfileController serviceProfileController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serviceProfileController).build();
	}	
	
	@Test
	public void showserversServiceTempProfileConfig()  throws Exception {
		mockMvc.perform(get("/serversServiceProfileConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversServiceTempProfileConfig"));
	}
	
	@Test
	public void getServiceProfileDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getServiceProfileDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getServiceProfileDetails"));
	}

	@Test
	public void getServiceProfileDetails_returnTypeCheck() throws Exception {
		ServersServiceProfile obj = new ServersServiceProfile();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST");
		obj.setServersServiceProfileTemplate(new ServersServiceProfileTemplate(2)); 
		obj.setOrganizations(new Organizations(2));  
		List<ServersServiceProfile> objList = new ArrayList<>(); objList.add(obj);
		String expected = "[{\"id\":1,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\",\"serversServiceProfileTemplate\":2}]";
		when(serviceTemplateService.fetchServersServiceProfileDetails(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = serviceProfileController.getServiceProfileDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateService, times(1)).fetchServersServiceProfileDetails(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateService);
	}
  
	@Test
	public void getServiceProfileDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serviceProfileController.getServiceProfileDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateService, times(0)).fetchServersServiceProfileDetails(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateService);
	}

	@Test
	public void getServiceProfileDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serviceTemplateService.fetchServersServiceProfileDetails(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serviceProfileController.getServiceProfileDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateService, times(1)).fetchServersServiceProfileDetails(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@Test(expected = Exception.class)
	public void getServiceProfileDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serviceTemplateService.fetchServersServiceProfileDetails(PROJECT_ID));
		List<Object> jsonList = serviceProfileController.getServiceProfileDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateService, times(1)).fetchServersServiceProfileDetails(PROJECT_ID);
        verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@Test
	public void manageServiceProfile_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServiceProfile.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServiceProfile"));
	     verifyNoMoreInteractions(serviceTemplateService);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceProfile_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersServiceProfile obj = new ServersServiceProfile();
		obj.setId(1); obj.setDescription("Description"); obj.setName("TEST");
		obj.setServersServiceProfileTemplate(new ServersServiceProfileTemplate(2)); 
		obj.setOrganizations(new Organizations(2));  
		List<ServersServiceProfile> objList = new ArrayList<>(); objList.add(obj);
		when(serviceTemplateService.saveOrUpdateServersServiceProfileDetails(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = serviceProfileController.manageServiceProfile(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":2,\"description\":\"Description\",\"name\":\"TEST\",\"serversServiceProfileTemplate\":2}]";
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateService, times(1)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(0)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceProfile_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serviceTemplateService).deleteServersServiceProfiles(anyList());
		List<Object> objList = serviceProfileController.manageServiceProfile(json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serviceTemplateService, times(0)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(1)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceProfile_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serviceProfileController.manageServiceProfile(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serviceTemplateService, times(0)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(0)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceProfile_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serviceProfileController.manageServiceProfile(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serviceTemplateService, times(0)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(0)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceProfile_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serviceProfileController.manageServiceProfile(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serviceTemplateService, times(0)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(0)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServiceProfile_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serviceTemplateService.saveOrUpdateServersServiceProfileDetails(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serviceProfileController.manageServiceProfile(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serviceTemplateService, times(1)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(0)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServiceProfile_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serviceTemplateService.saveOrUpdateServersServiceProfileDetails(anyList(), anyInt()));
		serviceProfileController.manageServiceProfile(json, PROJECT_ID);
		verify(serviceTemplateService, times(1)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(0)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServiceProfile_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serviceTemplateService).deleteServersServiceProfiles(anyList());
		serviceProfileController.manageServiceProfile(json, PROJECT_ID);
		verify(serviceTemplateService, times(0)).saveOrUpdateServersServiceProfileDetails(anyList(), anyInt());
		verify(serviceTemplateService, times(1)).deleteServersServiceProfiles(anyList());
		verifyNoMoreInteractions(serviceTemplateService);
	}
	
	
	
}
