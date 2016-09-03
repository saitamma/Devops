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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * 
 * The Class VsanControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class VsanControllerTest implements TestConstants{
	
	/** The mock mvc. */
	private MockMvc mockMvc;
		
	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;
	
	
	/** The vsan controller. */
	@InjectMocks
	private VsanController vsanController;
	
	
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vsanController).build();
		
	}
	
	
	/**
	 * Show vsan config.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showVsanConfig() throws Exception{
		mockMvc.perform(get("/sanVsanConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("san/sanVsanConfig"));
	}
	
	/**
	 * Gets the san v san config details_method name check.
	 *
	 * @return the san v san config details_method name check
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getSanVSanConfigDetails_methodNameCheck() throws Exception {
		when(sanServiceMock.fetchSanVsanConfiguration(PROJECT_ID)).thenReturn(anyList());
		mockMvc.perform(get("/getSanVSanConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("getSanVSanConfigDetails"));
	}
	
	/**
	 * Gets the san v san config details_return type check.
	 *
	 * @return the san v san config details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVSanConfigDetails_returnTypeCheck() throws Exception {
		SanVsan obj = new SanVsan();
		obj.setId(1); obj.setFcoeVsanId(1); obj.setFcoeVlanName(null); obj.setProjectDetails(new ProjectDetails(1));
		obj.setVsanName("Test"); 
		List<SanVsan> objList = new ArrayList<>();
		objList.add(obj);
	    String expected = "[{\"fcoeVlanName\":null,\"id\":1,\"fcoeVsanId\":1,\"description\":null,\"fiId\":null,\"vsanName\":\"Test\"}]";
		//String expected ="[{\"fiId\":null,\"fcoeVlanName\":null,\"vsanName\":\"Test\",\"description\":null,\"id\":1,\"fcoeVsanId\":1}]";
		when(sanServiceMock.fetchSanVsanConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = vsanController.getSanVSanConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanVsanConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san v san config details_null check.
	 *
	 * @return the san v san config details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVSanConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> actuall = vsanController.getSanVSanConfigDetails(null);
		assertEquals(expected, actuall.toString());
		verify(sanServiceMock, times(0)).fetchSanVsanConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san v san config details_return null check.
	 *
	 * @return the san v san config details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVSanConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanVsanConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> actuall = vsanController.getSanVSanConfigDetails(PROJECT_ID);
		assertEquals(expected, actuall.toString());
		verify(sanServiceMock, times(1)).fetchSanVsanConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san v san config details_exception.
	 *
	 * @return the san v san config details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanVSanConfigDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(sanServiceMock.fetchSanVsanConfiguration(PROJECT_ID));
		List<Object> actuall = vsanController.getSanVSanConfigDetails(PROJECT_ID);
		assertEquals(expected, actuall.toString());
		verify(sanServiceMock, times(1)).fetchSanVsanConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san v san config_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanVSanConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanVSanConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanVSanConfig"));
		
	     verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san v san config_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVSanConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fcoeVsanId\":1,\"fcoeVlanName\":null,\"fiId\":null,\"vsanName\":\"Test\"}], \"deleted\":[]}";
		SanVsan obj = new SanVsan();
		obj.setId(1); obj.setFcoeVsanId(1); obj.setFcoeVlanName(null); obj.setProjectDetails(new ProjectDetails(1));
		obj.setVsanName("Test");
		List<SanVsan> objList = new ArrayList<>();
		objList.add(obj);
		when(sanServiceMock.saveOrUpdateSanVsanConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = vsanController.manageSanVSanConfig( json, PROJECT_ID);
		String expected = "[{\"fcoeVlanName\":null,\"id\":1,\"fcoeVsanId\":1,\"description\":null,\"fiId\":null,\"vsanName\":\"Test\"}]";
		//String expected= "[{\"fiId\":null,\"fcoeVlanName\":null,\"vsanName\":\"Test\",\"description\":null,\"id\":1,\"fcoeVsanId\":1}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san v san config_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVSanConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(sanServiceMock).deleteSanVsan(anyList());
		List<Object> objList = vsanController.manageSanVSanConfig( json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san v san config_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVSanConfig_nullCheck_firstParameter() throws Exception {
		List<Object> objList = vsanController.manageSanVSanConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san v san config_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVSanConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fcoeVsanId\":1,\"fcoeVlanName\":null,\"fiId\":null,\"vsanName\":\"Test\"}], \"deleted\":[]}";
		List<Object> objList = vsanController.manageSanVSanConfig(json, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san v san config_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVSanConfig_nullCheck_bothParameters() throws Exception {
		List<Object> objList = vsanController.manageSanVSanConfig(null, null);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san v san config_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVSanConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fcoeVsanId\":1,\"fcoeVlanName\":null,\"fiId\":null,\"vsanName\":\"Test\"}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanVsanConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> jsonList = vsanController.manageSanVSanConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san v san config_exception_add or update.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanVSanConfig_exception_addOrUpdate() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fcoeVsanId\":1,\"fcoeVlanName\":null,\"fiId\":null,\"vsanName\":\"Test\"}], \"deleted\":[]}";
		doThrow(Exception.class).when(sanServiceMock.saveOrUpdateSanVsanConfiguration(anyList(), anyInt()));
		vsanController.manageSanVSanConfig(json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san v san config_exception_delete.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanVSanConfig_exception_delete() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(Exception.class).when(sanServiceMock).deleteSanVsan(Mockito.anyList());
		vsanController.manageSanVSanConfig(json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateSanVsanConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanVsan(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	
}
