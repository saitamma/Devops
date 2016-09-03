package com.cisco.ca.cstg.pdi.controllers.san;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;


/**
 * The Class WwxNControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class WwxNControllerTest implements TestConstants{

	/** The mock mvc. */
	private MockMvc mockMvc;
		
	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;
	
	/** The wwx n controller. */
	@InjectMocks
	private WwxNController wwxNController;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(wwxNController).build();
		
	}
	
	/**
	 * Show wwx n config.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showWwxNConfig() throws Exception{
		mockMvc.perform(get("/sanWwxNConfig.html")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.forwardedUrl("san/sanWwxNConfig"));
	}
	
	/**
	 * Gets the san wwnn config details_method name check.
	 *
	 * @return the san wwnn config details_method name check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwnnConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/getSanWwnnConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).
		andExpect(handler().methodName("getSanWwnnConfigDetails"));
	}
	
	/**
	 * Gets the san wwnn config details_return type check.
	 *
	 * @return the san wwnn config details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwnnConfigDetails_returnTypeCheck() throws Exception {
		SanWwnn obj = new SanWwnn();
		obj.setId(1); obj.setFromAddress("1.2.3.4"); obj.setToAddress("1.2.3.6"); obj.setProjectDetails(new ProjectDetails(1));
		obj.setWwnnName("Test"); obj.setNoOfAddresses("2");
		List<SanWwnn> objList = new ArrayList<>();
		objList.add(obj);
		String expected = "[{\"id\":1,\"wwnnName\":\"Test\",\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}]";
		//String expected = "[{\"wwnnName\":\"Test\",\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"fromAddress\":\"1.2.3.4\",\"id\":1,\"toAddress\":\"1.2.3.6\"}]";
		when(sanServiceMock.fetchSanWwnnConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = wwxNController.getSanWwnnConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanWwnnConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san wwnn config details_null check.
	 *
	 * @return the san wwnn config details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwnnConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = wwxNController.getSanWwnnConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).fetchSanWwnnConfiguration(anyInt());
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san wwnn config details_return null check.
	 *
	 * @return the san wwnn config details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwnnConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanWwnnConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = wwxNController.getSanWwnnConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanWwnnConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san wwnn config details_exception.
	 *
	 * @return the san wwnn config details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanWwnnConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(sanServiceMock.fetchSanWwnnConfiguration(PROJECT_ID));
		wwxNController.getSanWwnnConfigDetails(PROJECT_ID);
		verify(sanServiceMock, times(1)).fetchSanWwnnConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwnn config_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanWwnnConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanWwnnConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanWwnnConfig"));
		
	     verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san wwnn config_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwnnConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwnnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		SanWwnn obj = new SanWwnn();
		obj.setId(1); obj.setFromAddress("1.2.3.4"); obj.setToAddress("1.2.3.6"); obj.setProjectDetails(new ProjectDetails(1));
		obj.setWwnnName("Test"); obj.setNoOfAddresses("2");
		List<SanWwnn> objList = new ArrayList<>(); objList.add(obj);
		when(sanServiceMock.saveOrUpdateSanWwnnConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = wwxNController.manageSanWwnnConfig(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"wwnnName\":\"Test\",\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}]";
		//String expected = "[{\"wwnnName\":\"Test\",\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"fromAddress\":\"1.2.3.4\",\"id\":1,\"toAddress\":\"1.2.3.6\"}]";
		Assert.assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwnn config_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwnnConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(sanServiceMock).deleteSanWwnn(anyList());
		List<Object> objList = wwxNController.manageSanWwnnConfig(json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(1)).deleteSanWwnn(anyList());
	}
	
	/**
	 * Manage san wwnn config_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwnnConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwnnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanWwnnConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = wwxNController.manageSanWwnnConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwnn config_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwnnConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = wwxNController.manageSanWwnnConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san wwnn config_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwnnConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwnnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[1,2]}";
		List<Object> objList = wwxNController.manageSanWwnnConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwnn config_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwnnConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = wwxNController.manageSanWwnnConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwnn config_exception_add or update.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanWwnnConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwnnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		doThrow(Exception.class).when(sanServiceMock).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		wwxNController.manageSanWwnnConfig(json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwnn config_exception_delete.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanWwnnConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(Exception.class).when(sanServiceMock).deleteSanWwnn(anyList());
		wwxNController.manageSanWwnnConfig(json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwnnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanWwnn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san wwpn config details_method name check.
	 *
	 * @return the san wwpn config details_method name check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwpnConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/getSanWwpnConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getSanWwpnConfigDetails"));
	}
	
	/**
	 * Gets the san wwpn config details_return type check.
	 *
	 * @return the san wwpn config details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwpnConfigDetails_returnTypeCheck() throws Exception {
		SanWwpn obj = new SanWwpn();
		obj.setId(1); obj.setFromAddress("1.2.3.4"); obj.setToAddress("1.2.3.6"); obj.setProjectDetails(new ProjectDetails(1));
		obj.setWwpnName("Test"); obj.setNoOfAddresses("2");
		List<SanWwpn> objList = new ArrayList<>();
		objList.add(obj);
		String expected = "[{\"id\":1,\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"fromAddress\":\"1.2.3.4\",\"wwpnName\":\"Test\",\"toAddress\":\"1.2.3.6\"}]";
		//String expected ="[{\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"wwpnName\":\"Test\",\"fromAddress\":\"1.2.3.4\",\"id\":1,\"toAddress\":\"1.2.3.6\"}]";
		when(sanServiceMock.fetchSanWwpnConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = wwxNController.getSanWwpnConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanWwpnConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san wwpn config details_null check.
	 *
	 * @return the san wwpn config details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwpnConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = wwxNController.getSanWwpnConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).fetchSanWwpnConfiguration(anyInt());
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san wwpn config details_return null check.
	 *
	 * @return the san wwpn config details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanWwpnConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanWwpnConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = wwxNController.getSanWwpnConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanWwpnConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san wwpn config details_exception.
	 *
	 * @return the san wwpn config details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanWwpnConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(sanServiceMock.fetchSanWwpnConfiguration(PROJECT_ID));
		wwxNController.getSanWwpnConfigDetails(PROJECT_ID);
		verify(sanServiceMock, times(1)).fetchSanWwpnConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanWwpnConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanWwpnConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanWwpnConfig"));
		
	     verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san wwpn config_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwpnConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwpnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		SanWwpn obj = new SanWwpn();
		obj.setId(1); obj.setFromAddress("1.2.3.4"); obj.setToAddress("1.2.3.6"); obj.setProjectDetails(new ProjectDetails(1));
		obj.setWwpnName("Test"); obj.setNoOfAddresses("2");
		List<SanWwpn> objList = new ArrayList<>(); objList.add(obj);
		when(sanServiceMock.saveOrUpdateSanWwpnConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = wwxNController.manageSanWwpnConfig( json, PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"fromAddress\":\"1.2.3.4\",\"wwpnName\":\"Test\",\"toAddress\":\"1.2.3.6\"}]";
		//String expected = "[{\"organizations\":null,\"description\":null,\"assignmentOrder\":null,\"wwpnName\":\"Test\",\"fromAddress\":\"1.2.3.4\",\"id\":1,\"toAddress\":\"1.2.3.6\"}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwpnConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(sanServiceMock).deleteSanWwpn(anyList());
		List<Object> objList = wwxNController.manageSanWwpnConfig( json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwpnConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwpnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanWwpnConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> objList = wwxNController.manageSanWwpnConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san wwpn config_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwpnConfig_nullCheck_firstParameter() throws Exception {
		List<Object> jsonList = wwxNController.manageSanWwpnConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwpnConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwpnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		List<Object> jsonList = wwxNController.manageSanWwpnConfig(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanWwpnConfig_nullCheck_bothParameters() throws Exception {
		List<Object> jsonList = wwxNController.manageSanWwpnConfig(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_exception_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanWwpnConfig_exception_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"wwpnName\":\"Test\",\"description\":null,\"fromAddress\":\"1.2.3.4\",\"toAddress\":\"1.2.3.6\"}], \"deleted\":[]}";
		doThrow(Exception.class).when(sanServiceMock.saveOrUpdateSanWwpnConfiguration(anyList(), anyInt()));
		wwxNController.manageSanWwpnConfig( json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san wwpn config_exception_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanWwpnConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(Exception.class).when(sanServiceMock).deleteSanWwpn(anyList());
		wwxNController.manageSanWwpnConfig( json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateSanWwpnConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanWwpn(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
}
