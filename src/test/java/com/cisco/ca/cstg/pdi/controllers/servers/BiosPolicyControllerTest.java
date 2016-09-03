package com.cisco.ca.cstg.pdi.controllers.servers;

import static org.junit.Assert.*;
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
import com.cisco.ca.cstg.pdi.pojos.ServersBiosPolicy;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class BiosPolicyControllerTest implements TestConstants{
	
	private MockMvc mockMvc;	
	
	@Mock
	private ServersService serversServiceMock;
	
	@InjectMocks
	private BiosPolicyController serversController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serversController).build();
	}
	
	@Test
	public void showServersBiosPolicyConfig()  throws Exception {
		mockMvc.perform(get("/serversBiosPolicyConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("servers/serversBiosPolicyConfig"));
	}
	
	@Test
	public void getserversBiosPolicyDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getserversBiosPolicyDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getserversBiosPolicyDetails"));
	}

	@Test
	public void getserversBiosPolicyDetails_returnTypeCheck() throws Exception {
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		List<ServersBiosPolicy> smpList = new ArrayList<>(); smpList.add(smp);
		when(serversServiceMock.fetchBiosPolicyDetail(anyInt())).thenReturn(smpList);
		List<Object> jsonList = serversController.getserversBiosPolicyDetails(PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getserversBiosPolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getserversBiosPolicyDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getserversBiosPolicyDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchBiosPolicyDetail(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getserversBiosPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getserversBiosPolicyDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchBiosPolicyDetail(PROJECT_ID));
		List<Object> jsonList = serversController.getserversBiosPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServersBiosPolicyTableSave_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServersBiosPolicyTableSave.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServersBiosPolicyTableSave"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}], \"deleted\":[]}";
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		List<ServersBiosPolicy> smpList = new ArrayList<>(); smpList.add(smp);
		when(serversServiceMock.saveOrUpdateBiosPolicyDetails(anyList(), anyInt())).thenReturn(smpList);
		when(serversServiceMock.fetchSingleBiosPolicyDetail(anyInt())).thenReturn(smp);
		List<Object> jsonList = serversController.manageServersBiosPolicyTableSave(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(1)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersBiosPolicyDetails(anyList());
		List<Object> objList = serversController.manageServersBiosPolicyTableSave( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageServersBiosPolicyTableSave(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageServersBiosPolicyTableSave(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageServersBiosPolicyTableSave(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_returnNullCheck_fetchSingleBiosPolicyDetail() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.fetchSingleBiosPolicyDetail(anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServersBiosPolicyTableSave(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicyTableSave_returnNullCheck_saveOrUpdateBiosPolicyDetails() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		when(serversServiceMock.fetchSingleBiosPolicyDetail(anyInt())).thenReturn(smp);
		when(serversServiceMock.saveOrUpdateBiosPolicyDetails(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServersBiosPolicyTableSave(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(1)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersBiosPolicyTableSave_exception_fetchSingleBiosPolicyDetail() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		doThrow(Exception.class).when(serversServiceMock.fetchSingleBiosPolicyDetail(anyInt()));
		serversController.manageServersBiosPolicyTableSave(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersBiosPolicyTableSave_exception_saveOrUpdateBiosPolicyDetails() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		when(serversServiceMock.fetchSingleBiosPolicyDetail(anyInt())).thenReturn(smp);
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateBiosPolicyDetails(anyList(), anyInt()));
		serversController.manageServersBiosPolicyTableSave(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(1)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersBiosPolicyTableSave_exception_deleteServersBiosPolicyDetails() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersBiosPolicyDetails(anyList());
		serversController.manageServersBiosPolicyTableSave(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).fetchSingleBiosPolicyDetail(anyInt());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void manageServersBiosPolicy_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageServersBiosPolicy.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageServersBiosPolicy"));
	     verifyNoMoreInteractions(serversServiceMock);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicy_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}], \"deleted\":[]}";
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		List<ServersBiosPolicy> smpList = new ArrayList<>(); smpList.add(smp);
		when(serversServiceMock.saveOrUpdateBiosPolicyDetails(anyList(), anyInt())).thenReturn(smpList);
		List<Object> jsonList = serversController.manageServersBiosPolicy(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":1,\"description\":\"TestDesc\",\"name\":\"Test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicy_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(serversServiceMock).deleteServersBiosPolicyDetails(anyList());
		List<Object> objList = serversController.manageServersBiosPolicy( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicy_nullCheck_firstParameter() throws Exception {
		List<Object> objList = serversController.manageServersBiosPolicy(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicy_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> objList = serversController.manageServersBiosPolicy(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicy_nullCheck_bothParameters() throws Exception {
		List<Object> objList = serversController.manageServersBiosPolicy(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void manageServersBiosPolicy_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(serversServiceMock.saveOrUpdateBiosPolicyDetails(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = serversController.manageServersBiosPolicy(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersBiosPolicy_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(serversServiceMock.saveOrUpdateBiosPolicyDetails(anyList(), anyInt()));
		serversController.manageServersBiosPolicy(json, PROJECT_ID);
		verify(serversServiceMock, times(1)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(0)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageServersBiosPolicy_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(serversServiceMock).deleteServersBiosPolicyDetails(anyList());
		serversController.manageServersBiosPolicy(json, PROJECT_ID);
		verify(serversServiceMock, times(0)).saveOrUpdateBiosPolicyDetails(anyList(), anyInt());
		verify(serversServiceMock, times(1)).deleteServersBiosPolicyDetails(anyList());
		verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test
	public void getSingleBiosPolicy_methodNameCheck() throws Exception {
		this.mockMvc.perform(post("/getSingleBiosPolicy.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getSingleBiosPolicy"));
	}

	@Test
	public void getSingleBiosPolicy_returnTypeCheck() throws Exception {
		ServersBiosPolicy smp = new ServersBiosPolicy(1);
		smp.setName("Test"); smp.setDescription("TestDesc");
		smp.setOrganizations(new Organizations(1));
		smp.setAdjacentCacheLinePrefetcher("preFetch"); smp.setBaudRate("rate");
		smp.setFlowControl("flow"); smp.setQuietBoot("true");
		when(serversServiceMock.fetchSingleBiosPolicyDetail(anyInt())).thenReturn(smp);
		List<Object> jsonList = serversController.getSingleBiosPolicy(PROJECT_ID);
		assertTrue(jsonList.toString().indexOf("\"name\":\"Test\"")!=-1);
		assertTrue(jsonList.toString().indexOf("\"id\":1")!=-1);
		assertTrue(jsonList.toString().indexOf("\"organizations\":1")!=-1);
		assertTrue(jsonList.toString().indexOf("\"adjacentCacheLinePrefetcher\":\"preFetch\"")!=-1);
		assertTrue(jsonList.toString().indexOf("\"baudRate\":\"rate\"")!=-1);
		assertTrue(jsonList.toString().indexOf("\"flowControl\":\"flow\"")!=-1);
		assertTrue(jsonList.toString().indexOf("\"quietBoot\":\"true\"")!=-1);
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getSingleBiosPolicy_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = serversController.getSingleBiosPolicy(null);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(0)).fetchSingleBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}

	@Test
	public void getSingleBiosPolicy_returnNullCheck() throws Exception {
		String expected = "[]";
		when(serversServiceMock.fetchSingleBiosPolicyDetail(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = serversController.getSingleBiosPolicy(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
	@Test(expected = Exception.class)
	public void getSingleBiosPolicy_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(serversServiceMock.fetchSingleBiosPolicyDetail(PROJECT_ID));
		List<Object> jsonList = serversController.getSingleBiosPolicy(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(serversServiceMock, times(1)).fetchSingleBiosPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(serversServiceMock);
	}
	
}
