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
import com.cisco.ca.cstg.pdi.pojos.ServersHostFirmware;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class HostFirmwareControllerTest implements TestConstants{
	
	private MockMvc mockMvc;
		
	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private HostFirmwareController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showServersHostFirmware()  throws Exception {
		mockMvc.perform(get("/serversHostFirmware.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversHostFirmware"));
	}
	
	@Test
	public void getserversHostFirmwareDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getserversHostFirmwareDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getserversHostFirmwareDetails"));
	}

	@Test
	public void getserversHostFirmwareDetails_returnTypeCheck() throws Exception {
		ServersHostFirmware shf = new ServersHostFirmware(1);
		shf.setName("Test"); shf.setDescription("TestDesc");
		shf.setOrganizations(new Organizations(1));
		List<ServersHostFirmware> shfList = new ArrayList<>(); shfList.add(shf);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}]";
		when(serversServiceMock.fetchHostFirmwareDetail(PROJECT_ID)).thenReturn(shfList);
		List<Object> jsonList = serversController.getserversHostFirmwareDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchHostFirmwareDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getserversHostFirmwareDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getserversHostFirmwareDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchHostFirmwareDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getserversHostFirmwareDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchHostFirmwareDetail(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getserversHostFirmwareDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchHostFirmwareDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getserversHostFirmwareDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchHostFirmwareDetail(PROJECT_ID));
		List<Object> jsonList = serversController.getserversHostFirmwareDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchHostFirmwareDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageHostFirmwareConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageHostFirmwareConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageHostFirmwareConfig"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageHostFirmwareConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersHostFirmware shf = new ServersHostFirmware(1);
		shf.setName("Test"); shf.setDescription("TestDesc");
		shf.setOrganizations(new Organizations(1));
		List<ServersHostFirmware> shfList = new ArrayList<>(); shfList.add(shf);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}]";
		when(serversServiceMock.saveOrUpdateHostFirmwareDetails(anyList(), anyInt())).thenReturn(shfList);
		List<Object> jsonList = serversController.manageHostFirmwareConfig( json, PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageHostFirmwareConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersHostFirmwareDetails(anyList());
		List<Object> objList = serversController.manageHostFirmwareConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageHostFirmwareConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageHostFirmwareConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageHostFirmwareConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageHostFirmwareConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageHostFirmwareConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageHostFirmwareConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageHostFirmwareConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateHostFirmwareDetails(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageHostFirmwareConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageHostFirmwareConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateHostFirmwareDetails(anyList(), anyInt()));
		serversController.manageHostFirmwareConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageHostFirmwareConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersHostFirmwareDetails(anyList());
		serversController.manageHostFirmwareConfig(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateHostFirmwareDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersHostFirmwareDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	
}
