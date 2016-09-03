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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * 
 * The Class VhbaTemplateControllerTest.
 */
public class VhbaTemplateControllerTest implements TestConstants{

	/** The mock mvc. */
	private MockMvc mockMvc;
		
	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;
	
	/** The vhba template controller. */
	@InjectMocks
	private VhbaTemplateController vhbaTemplateController;
	
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(vhbaTemplateController).build();
	}
	
	/**
	 * Show vhba temp config.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showVHBATempConfig() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/sanVHBATempConfig.html")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.forwardedUrl("san/sanVHBATempConfig"));
		
	}
	
	/**
	 * Gets the san vhba template config details_method name check.
	 *
	 * @return the san vhba template config details_method name check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaTemplateConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/getSanVhbaTemplateConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).
		andExpect(handler().methodName("getSanVhbaTemplateConfigDetails"));
	}
	
	/**
	 * Gets the san vhba template config details_return type check.
	 *
	 * @return the san vhba template config details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaTemplateConfigDetails_returnTypeCheck() throws Exception {
		SanVhbaTemplate obj = new SanVhbaTemplate();
		obj.setId(1);  obj.setProjectDetails(new ProjectDetails(1)); obj.setTemplateType("template");
		obj.setVhbaName("Test"); obj.setSanVsan(new SanVsan(1)); obj.setDescription("desc"); obj.setSanWwpn(new SanWwpn(10)); 
		obj.setSwitchId("switch"); obj.setLanQosPolicy(new LanQosPolicy(3)); 
		List<SanVhbaTemplate> objList = new ArrayList<>();
		objList.add(obj);
		String expected = "[{\"id\":1,\"vhbaName\":\"Test\",\"templateType\":\"template\",\"organizations\":null,\"description\":\"desc\",\"sanWwpn\":10,\"lanQosPolicy\":3,\"sanVsan\":1,\"switchId\":\"switch\"}]";
		 //String expected = "[{\"templateType\":\"template\",\"sanVsan\":1,\"sanWwpn\":10,\"vhbaName\":\"Test\",\"switchId\":\"switch\",\"organizations\":null,\"description\":\"desc\",\"id\":1,\"lanQosPolicy\":3}]";
		when(sanServiceMock.fetchSanVhbaTemplateConfiguration(PROJECT_ID)).thenReturn(objList);
		List<Object> jsonList = vhbaTemplateController.getSanVhbaTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanVhbaTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san vhba template config details_null check.
	 *
	 * @return the san vhba template config details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaTemplateConfigDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = vhbaTemplateController.getSanVhbaTemplateConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).fetchSanVhbaTemplateConfiguration(anyInt());
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san vhba template config details_return null check.
	 *
	 * @return the san vhba template config details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaTemplateConfigDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanVhbaTemplateConfiguration(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = vhbaTemplateController.getSanVhbaTemplateConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanVhbaTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san vhba template config details_exception.
	 *
	 * @return the san vhba template config details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanVhbaTemplateConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(sanServiceMock.fetchSanVhbaTemplateConfiguration(PROJECT_ID));
		vhbaTemplateController.getSanVhbaTemplateConfigDetails(PROJECT_ID);
		verify(sanServiceMock, times(1)).fetchSanVhbaTemplateConfiguration(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanVhbaTemplateConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanVhbaTemplateConfig.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanVhbaTemplateConfig"));
		
	     verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san vhba template config_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhbaTemplateConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vhbaName\":\"Test\",\"templateType\":\"template\",\"description\":\"desc\",\"sanWwpn\":10,\"lanQosPolicy\":3,\"sanVsan\":1,\"switchId\":\"switch\"}], \"deleted\":[]}";
		SanVhbaTemplate obj = new SanVhbaTemplate();
		obj.setId(1);  obj.setProjectDetails(new ProjectDetails(1)); obj.setTemplateType("template");
		obj.setVhbaName("Test"); obj.setSanVsan(new SanVsan(1)); obj.setDescription("desc"); obj.setSanWwpn(new SanWwpn(10)); 
		obj.setSwitchId("switch"); obj.setLanQosPolicy(new LanQosPolicy(3)); 
		List<SanVhbaTemplate> objList = new ArrayList<>(); objList.add(obj);
		when(sanServiceMock.saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt())).thenReturn(objList);
		List<Object> jsonList = vhbaTemplateController.manageSanVhbaTemplateConfig( json, PROJECT_ID);
		String expected = "[{\"id\":1,\"vhbaName\":\"Test\",\"templateType\":\"template\",\"organizations\":null,\"description\":\"desc\",\"sanWwpn\":10,\"lanQosPolicy\":3,\"sanVsan\":1,\"switchId\":\"switch\"}]";
		//String expected = "[{\"templateType\":\"template\",\"sanVsan\":1,\"sanWwpn\":10,\"vhbaName\":\"Test\",\"switchId\":\"switch\",\"organizations\":null,\"description\":\"desc\",\"id\":1,\"lanQosPolicy\":3}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhbaTemplateConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(sanServiceMock).deleteSanVhbaTemplate(anyList());
		List<Object> objList = vhbaTemplateController.manageSanVhbaTemplateConfig( json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhbaTemplateConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vhbaName\":\"Test\",\"templateType\":\"template\",\"description\":\"desc\",\"sanWwpn\":10,\"lanQosPolicy\":3,\"sanVsan\":1,\"switchId\":\"switch\"}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt())).thenReturn(null);
		List<Object> objList = vhbaTemplateController.manageSanVhbaTemplateConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san vhba template config_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhbaTemplateConfig_nullCheck_firstParameter() throws Exception {
		List<Object> jsonList = vhbaTemplateController.manageSanVhbaTemplateConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhbaTemplateConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vhbaName\":\"Test\",\"templateType\":\"template\",\"description\":\"desc\",\"sanWwpn\":10,\"lanQosPolicy\":3,\"sanVsan\":1,\"switchId\":\"switch\"}], \"deleted\":[]}";
		List<Object> jsonList = vhbaTemplateController.manageSanVhbaTemplateConfig(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhbaTemplateConfig_nullCheck_bothParameters() throws Exception {
		List<Object> jsonList = vhbaTemplateController.manageSanVhbaTemplateConfig(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_exception_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanVhbaTemplateConfig_exception_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"vhbaName\":\"Test\",\"templateType\":\"template\",\"description\":\"desc\",\"sanWwpn\":10,\"lanQosPolicy\":3,\"sanVsan\":1,\"switchId\":\"switch\"}], \"deleted\":[]}";
		doThrow(Exception.class).when(sanServiceMock.saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt()));
		vhbaTemplateController.manageSanVhbaTemplateConfig( json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba template config_exception_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanVhbaTemplateConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(Exception.class).when(sanServiceMock).deleteSanVhbaTemplate(anyList());
		vhbaTemplateController.manageSanVhbaTemplateConfig( json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhbaTemplateConfiguration(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanVhbaTemplate(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
}
