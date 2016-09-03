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
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;


/**
 * 
 * The Class SanConnectivityPolicyControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class SanConnectivityPolicyControllerTest implements TestConstants{

	/** The mock mvc. */
	private MockMvc mockMvc;		

	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;

	/** The san connectivity policy controller. */
	@InjectMocks
	private SanConnectivityPolicyController sanConnectivityPolicyController;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
	
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(sanConnectivityPolicyController).build();
		
	}
	
	/**
	 * Show san connectivity policy.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showSanConnectivityPolicy() throws Exception{
		mockMvc.perform(get("/sanConnectivityPolicy.html")).andExpect(status().isOk()).andExpect(forwardedUrl("san/sanConnectivityPolicy"));
	}
	
	/**
	 * Gets the san connectivity policy details_method name check.
	 *
	 * @return the san connectivity policy details_method name check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanConnectivityPolicyDetails_methodNameCheck() throws Exception {
		
		this.mockMvc.perform(get("/getSanConnectivityPolicyDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getSanConnectivityPolicyDetails"));
	}

	/**
	 * Gets the san connectivity policy details_return type check.
	 *
	 * @return the san connectivity policy details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanConnectivityPolicyDetails_returnTypeCheck() throws Exception {
		SanConnectivityPolicy scp = new SanConnectivityPolicy(1);
		List<SanScpVhbaMapping> list = new ArrayList<>();
		SanScpVhbaMapping mapping1= new SanScpVhbaMapping(1);
		mapping1.setSanVhba(new SanVhba(1));
		SanScpVhbaMapping mapping2= new SanScpVhbaMapping(1);
		mapping2.setSanVhba(new SanVhba(2));
		list.add(mapping1); list.add(mapping2);
		scp.setName("test"); scp.setDescription("desc"); scp.setOrganizations(new Organizations(2));
		scp.setSanWwnn(new SanWwnn(3)); scp.setSanScpVhbaMappings(list);
		List<SanConnectivityPolicy> scpList = new ArrayList<>(); scpList.add(scp);
		when(sanServiceMock.fetchSanConnectivityPolicyDetail(PROJECT_ID)).thenReturn(scpList);
		List<Object> jsonList = sanConnectivityPolicyController.getSanConnectivityPolicyDetails(PROJECT_ID);
		String expected = "[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vhbaId\":[1,2],\"sanWwnn\":3}]";
		//String expected = "[{\"name\":\"test\",\"organizations\":2,\"description\":\"desc\",\"sanWwnn\":3,\"id\":1,\"vhbaId\":[1,2]}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanConnectivityPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san connectivity policy details_return null check.
	 *
	 * @return the san connectivity policy details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanConnectivityPolicyDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanConnectivityPolicyDetail(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = sanConnectivityPolicyController.getSanConnectivityPolicyDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanConnectivityPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san connectivity policy details_null check.
	 *
	 * @return the san connectivity policy details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanConnectivityPolicyDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = sanConnectivityPolicyController.getSanConnectivityPolicyDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).fetchSanConnectivityPolicyDetail(anyInt());
        verifyNoMoreInteractions(sanServiceMock);
	}

	
	/**
	 * Gets the san connectivity policy details_exception.
	 *
	 * @return the san connectivity policy details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanConnectivityPolicyDetails_exception() throws Exception {
		doThrow(Exception.class).when(sanServiceMock.fetchSanConnectivityPolicyDetail(PROJECT_ID));
		sanConnectivityPolicyController.getSanConnectivityPolicyDetails(PROJECT_ID);
		verify(sanServiceMock, times(1)).fetchSanConnectivityPolicyDetail(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san connectivity policy config_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanConnectivityPolicyConfig_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanConnectivityPolicyConfig.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanConnectivityPolicyConfig"));
	     verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san connectivity policy config_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanConnectivityPolicyConfig_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vhbaId\":[1,2],\"sanWwnn\":3}], \"deleted\":[]}";
		SanConnectivityPolicy scp = new SanConnectivityPolicy(1);
		List<SanScpVhbaMapping> list = new ArrayList<>();
		SanScpVhbaMapping mapping1= new SanScpVhbaMapping(1);
		mapping1.setSanVhba(new SanVhba(1));
		SanScpVhbaMapping mapping2= new SanScpVhbaMapping(1);
		mapping2.setSanVhba(new SanVhba(2));
		list.add(mapping1); list.add(mapping2);
		scp.setName("test"); scp.setDescription("desc"); scp.setOrganizations(new Organizations(2));
		scp.setSanWwnn(new SanWwnn(3)); scp.setSanScpVhbaMappings(list);
		List<SanConnectivityPolicy> scpList = new ArrayList<>(); scpList.add(scp);
		when(sanServiceMock.saveOrUpdateSanConnectivityPolicy(anyList(), anyInt())).thenReturn(scpList);
		List<Object> jsonList = sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(json, PROJECT_ID);
	    String expected = "[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vhbaId\":[1,2],\"sanWwnn\":3}]";
		//String expected = "[{\"name\":\"test\",\"organizations\":2,\"description\":\"desc\",\"sanWwnn\":3,\"id\":1,\"vhbaId\":[1,2]}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san connectivity policy config_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanConnectivityPolicyConfig_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(sanServiceMock).deleteSanConnectivityPolicy(anyList());
		List<Object> scpList = sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(json, PROJECT_ID);
		String expected = "[]"; 
		assertEquals(expected, scpList.toString());
		verify(sanServiceMock, times(1)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san connectivity policy config_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanConnectivityPolicyConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vhbaId\":[1,2],\"sanWwnn\":3}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanConnectivityPolicy(anyList(), anyInt())).thenReturn(null);
		List<Object> scpList = sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, scpList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san connectivity policy config_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanConnectivityPolicyConfig_nullCheck_firstParameter() throws Exception {
		List<Object> scpList = sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, scpList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}	

	/**
	 * Manage san connectivity policy config_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanConnectivityPolicyConfig_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vhbaId\":[1,2],\"sanWwnn\":3}], \"deleted\":[]}";
		List<Object> scpList = sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(json, null);
		String expected = "[]";
		assertEquals(expected, scpList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san connectivity policy config_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanConnectivityPolicyConfig_nullCheck_bothParameters() throws Exception {
		List<Object> scpList = sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(null, null);
		String expected = "[]";
		assertEquals(expected, scpList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san connectivity policy config_exception_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanConnectivityPolicyConfig_exception_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"organizations\":2,\"description\":\"desc\",\"name\":\"test\",\"vhbaId\":[1,2],\"sanWwnn\":3}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanConnectivityPolicy(anyList(), PROJECT_ID)).thenThrow(new NullPointerException());
		sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san connectivity policy config_exception_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanConnectivityPolicyConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(new NullPointerException()).when(sanServiceMock).deleteSanConnectivityPolicy(anyList());
		sanConnectivityPolicyController.manageSanConnectivityPolicyConfig(json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateSanConnectivityPolicy(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanConnectivityPolicy(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

}
