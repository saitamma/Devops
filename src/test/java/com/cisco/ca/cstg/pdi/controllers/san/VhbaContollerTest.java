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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;


/**
 * The Class VhbaContollerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class VhbaContollerTest implements TestConstants{

	/** The mock mvc. */
	private MockMvc mockMvc;	
	
	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;
	
	/** The vhba controller. */
	@InjectMocks
	private VhbaController vhbaController;
	
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(vhbaController).build();
		
	}
	
	/**
	 * Show san vhba config.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showSanVHBAConfig() throws Exception{
		mockMvc.perform(get("/sanVHBAConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("san/sanVHBAConfig"));
	}
	
	/**
	 * Gets the san vhba details_method name check.
	 *
	 * @return the san vhba details_method name check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getSanVhbaDetails.html").sessionAttr("activeProjectId", PROJECT_ID))
		.andExpect(handler().methodName("getSanVhbaDetails"));
	}

	/**
	 * Gets the san vhba details_return type check.
	 *
	 * @return the san vhba details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaDetails_returnTypeCheck() throws Exception {
		SanVhba sanVhba = new SanVhba(1);
		sanVhba.setName("test"); sanVhba.setSanAdapterPolicies(new SanAdapterPolicies(2)); 
		sanVhba.setSanVhbaTemplate(new SanVhbaTemplate(3));
		List<SanVhba> sanVhbaList = new ArrayList<>();
		sanVhbaList.add(sanVhba);
		String expected = "[{\"id\":1,\"name\":\"test\",\"sanAdapterPolicies\":2,\"sanVhbaTemplate\":3}]";
		//String expected= "[{\"sanVhbaTemplate\":3,\"name\":\"test\",\"id\":1,\"sanAdapterPolicies\":2}]";
		when(sanServiceMock.fetchSanVhbaDetail(PROJECT_ID)).thenReturn(sanVhbaList);
		List<Object> jsonList = vhbaController.getSanVhbaDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanVhbaDetail(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san vhba details_null check.
	 *
	 * @return the san vhba details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = vhbaController.getSanVhbaDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).fetchSanVhbaDetail(anyInt());
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san vhba details_return null check.
	 *
	 * @return the san vhba details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanVhbaDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanVhbaDetail(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = vhbaController.getSanVhbaDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanVhbaDetail(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san vhba details_exception.
	 *
	 * @return the san vhba details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanVhbaDetails_exception() throws Exception {
		Mockito.doThrow(Exception.class).when(sanServiceMock.fetchSanVhbaDetail(PROJECT_ID));
		vhbaController.getSanVhbaDetails(PROJECT_ID);
		verify(sanServiceMock, times(1)).fetchSanVhbaDetail(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanVhba_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanVhba.html").accept(MediaType.APPLICATION_JSON)
				.sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanVhba"));
	}	
	
	/**
	 * Manage san vhba_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhba_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"name\":\"test\",\"sanAdapterPolicies\":2,\"sanVhbaTemplate\":3}], \"deleted\":[]}";
		SanVhba sanVhba = new SanVhba(1);
		sanVhba.setName("test"); sanVhba.setSanAdapterPolicies(new SanAdapterPolicies(2)); 
		sanVhba.setSanVhbaTemplate(new SanVhbaTemplate(3));
		List<SanVhba> sanVhbaList = new ArrayList<>();
		sanVhbaList.add(sanVhba);
		when(sanServiceMock.saveOrUpdateSanVhba(anyList(), anyInt())).thenReturn(sanVhbaList);
		List<Object> jsonList = vhbaController.manageSanVhba(json, PROJECT_ID);
		String expected = "[{\"id\":1,\"name\":\"test\",\"sanAdapterPolicies\":2,\"sanVhbaTemplate\":3}]";
		//String expected = "[{\"sanVhbaTemplate\":3,\"name\":\"test\",\"id\":1,\"sanAdapterPolicies\":2}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhba_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		SanVhba sanVhba = new SanVhba(1);
		sanVhba.setName("test"); sanVhba.setSanAdapterPolicies(new SanAdapterPolicies(2)); 
		sanVhba.setSanVhbaTemplate(new SanVhbaTemplate(3));
		List<Object> sanVhbaList = new ArrayList<>();
		sanVhbaList.add(sanVhba);
		doNothing().when(sanServiceMock).deleteSanVhba(anyList());
		sanVhbaList = vhbaController.manageSanVhba(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, sanVhbaList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhba_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateSanVhba(anyList(), anyInt())).thenReturn(null);
		List<Object> objList = vhbaController.manageSanVhba(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san vhba_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhba_nullCheck_firstParameter() throws Exception {
		List<Object> jsonList = vhbaController.manageSanVhba(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhba_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> jsonList = vhbaController.manageSanVhba(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanVhba_nullCheck_bothParameters() throws Exception {
		List<Object> jsonList = vhbaController.manageSanVhba(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_exception_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanVhba_exception_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		Mockito.doThrow(Exception.class).when(sanServiceMock.saveOrUpdateSanVhba(anyList(), anyInt()));
		vhbaController.manageSanVhba( json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san vhba_exception_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanVhba_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(Exception.class).when(sanServiceMock).deleteSanVhba(anyList());
		vhbaController.manageSanVhba( json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateSanVhba(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanVhba(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}



}
