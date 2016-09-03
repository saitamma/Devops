package com.cisco.ca.cstg.pdi.controllers.san;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/** 
 * The Class FcAdapterPolicyControllerTest.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class FcAdapterPolicyControllerTest implements TestConstants{

	/** The mock mvc. */
	private MockMvc mockMvc;		
	
	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;
	
	/** The fc adapter policy controller. */
	@InjectMocks
	private FcAdapterPolicyController fcAdapterPolicyController;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	  mockMvc=MockMvcBuilders.standaloneSetup(fcAdapterPolicyController).build();
	}
	
	
	/**
	 * Show adapter policies config.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showAdapterPoliciesConfig() throws Exception{
		mockMvc.perform(get("/sanAdapterPoliciesConfig.html")).andExpect(status().isOk()).andExpect(forwardedUrl("san/sanAdapterPoliciesConfig"));
	}
	
	
	/**
	 * Gets the san adapter policies details_method name check.
	 *
	 * @return the san adapter policies details_method name check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanAdapterPoliciesDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/getSanAdapterPoliciesDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).
		andExpect(handler().methodName("getSanAdapterPoliciesDetails"));
	} 
	
	/**
	 * Gets the san adapter policies details_return type check.
	 *
	 * @return the san adapter policies details_return type check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanAdapterPoliciesDetails_returnTypeCheck() throws Exception {
		SanAdapterPolicies sanAP = new SanAdapterPolicies(1);
		sanAP.setName("Test"); sanAP.setDescription("desc"); sanAP.setFcpErrorRecovery("enabled"); sanAP.setFlogiRetries(123456789);
		sanAP.setFlogiTimeout(60); sanAP.setInterruptMode("MSI x"); sanAP.setIoThrottleCount(16); sanAP.setLinkDownTimeout(30000);
		sanAP.setLunsPerTarget(256); sanAP.setOrganizations(new Organizations(1)); sanAP.setPlogiRetries(8);
		sanAP.setPlogiTimeout(8); sanAP.setPortDownIoRetry(30000); sanAP.setPortDownTimeout(30); 
		List<SanAdapterPolicies> sanAPList = new ArrayList<>();
		sanAPList.add(sanAP);
		String expected = "[{\"interruptMode\":\"MSI x\",\"lunsPerTarget\":256,\"portDownIoRetry\":30000,\"transmitQueues\":null,\"transmitQueuesRingSize\":null,\"scsiIoQueues\":null,\"ioThrottleCount\":16,\"organizations\":1,\"plogiTimeout\":8,\"plogiRetries\":8,\"scsiIoQueuesRingSize\":null,\"id\":1,\"receiveQueues\":null,\"portDownTimeout\":30,\"flogiTimeout\":60,\"linkDownTimeout\":30000,\"description\":\"desc\",\"name\":\"Test\",\"sapDefault\":null,\"flogiRetries\":123456789,\"fcpErrorRecovery\":\"enabled\",\"receiveQueuesRingSize\":null}]";
		//String expected = "[{\"scsiIoQueuesRingSize\":null,\"linkDownTimeout\":30000,\"receiveQueues\":null,\"sapDefault\":null,\"fcpErrorRecovery\":\"enabled\",\"transmitQueues\":null,\"description\":\"desc\",\"receiveQueuesRingSize\":null,\"interruptMode\":\"MSI x\",\"portDownTimeout\":30,\"transmitQueuesRingSize\":null,\"flogiTimeout\":60,\"plogiRetries\":8,\"portDownIoRetry\":30000,\"flogiRetries\":123456789,\"ioThrottleCount\":16,\"name\":\"Test\",\"organizations\":1,\"id\":1,\"plogiTimeout\":8,\"lunsPerTarget\":256,\"scsiIoQueues\":null}]";
		when(sanServiceMock.fetchSanAdapterPolicies(PROJECT_ID)).thenReturn(sanAPList);
		List<Object> jsonList = fcAdapterPolicyController.getSanAdapterPoliciesDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanAdapterPolicies(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Gets the san adapter policies details_null check.
	 *
	 * @return the san adapter policies details_null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanAdapterPoliciesDetails_nullCheck() throws Exception {
		String expected = "[]";
		List<Object> jsonList = fcAdapterPolicyController.getSanAdapterPoliciesDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).fetchSanAdapterPolicies(Mockito.anyInt());
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san adapter policies details_return null check.
	 *
	 * @return the san adapter policies details_return null check
	 * @throws Exception the exception
	 */
	@Test
	public void getSanAdapterPoliciesDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(sanServiceMock.fetchSanAdapterPolicies(PROJECT_ID)).thenReturn(null);
		List<Object> jsonList = fcAdapterPolicyController.getSanAdapterPoliciesDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).fetchSanAdapterPolicies(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Gets the san adapter policies details_exception.
	 *
	 * @return the san adapter policies details_exception
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void getSanAdapterPoliciesDetails_exception() throws Exception {
		doThrow(Exception.class).when(sanServiceMock.fetchSanAdapterPolicies(PROJECT_ID));
		fcAdapterPolicyController.getSanAdapterPoliciesDetails(PROJECT_ID);
		verify(sanServiceMock, times(1)).fetchSanAdapterPolicies(PROJECT_ID);
        verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageSanAdapterPolicies_methodNameCheck() throws Exception {
		mockMvc.perform(post("/manageSanAdapterPolicies.html").sessionAttr("activeProjectId", PROJECT_ID))
				.andExpect(handler().methodName("manageSanAdapterPolicies"));
		
	     verifyNoMoreInteractions(sanServiceMock);
	}	
	
	/**
	 * Manage san adapter policies_return type check_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanAdapterPolicies_returnTypeCheck_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		SanAdapterPolicies sanAP = new SanAdapterPolicies(1);
		sanAP.setName("Test"); sanAP.setDescription("desc"); sanAP.setFcpErrorRecovery("enabled"); sanAP.setFlogiRetries(123456789);
		sanAP.setFlogiTimeout(60); sanAP.setInterruptMode("MSI x"); sanAP.setIoThrottleCount(16); sanAP.setLinkDownTimeout(30000);
		sanAP.setLunsPerTarget(256); sanAP.setOrganizations(new Organizations(1)); sanAP.setPlogiRetries(8);
		sanAP.setPlogiTimeout(8); sanAP.setPortDownIoRetry(30000); sanAP.setPortDownTimeout(30); 
		List<SanAdapterPolicies> sanAPList = new ArrayList<>(); sanAPList.add(sanAP);
		when(sanServiceMock.saveOrUpdateAdapterPolicies(Mockito.anyList(), Mockito.anyInt())).thenReturn(sanAPList);
		List<Object> jsonList = fcAdapterPolicyController.manageSanAdapterPolicies( json, PROJECT_ID);
	    String expected = "[{\"interruptMode\":\"MSI x\",\"lunsPerTarget\":256,\"portDownIoRetry\":30000,\"transmitQueues\":null,\"transmitQueuesRingSize\":null,\"scsiIoQueues\":null,\"ioThrottleCount\":16,\"organizations\":1,\"plogiTimeout\":8,\"plogiRetries\":8,\"scsiIoQueuesRingSize\":null,\"id\":1,\"receiveQueues\":null,\"portDownTimeout\":30,\"flogiTimeout\":60,\"linkDownTimeout\":30000,\"description\":\"desc\",\"name\":\"Test\",\"sapDefault\":null,\"flogiRetries\":123456789,\"fcpErrorRecovery\":\"enabled\",\"receiveQueuesRingSize\":null}]";
		//String expected = "[{\"scsiIoQueuesRingSize\":null,\"linkDownTimeout\":30000,\"receiveQueues\":null,\"sapDefault\":null,\"fcpErrorRecovery\":\"enabled\",\"transmitQueues\":null,\"description\":\"desc\",\"receiveQueuesRingSize\":null,\"interruptMode\":\"MSI x\",\"portDownTimeout\":30,\"transmitQueuesRingSize\":null,\"flogiTimeout\":60,\"plogiRetries\":8,\"portDownIoRetry\":30000,\"flogiRetries\":123456789,\"ioThrottleCount\":16,\"name\":\"Test\",\"organizations\":1,\"id\":1,\"plogiTimeout\":8,\"lunsPerTarget\":256,\"scsiIoQueues\":null}]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateAdapterPolicies(anyList(), Mockito.anyInt());
		verify(sanServiceMock, times(0)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_return type check_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanAdapterPolicies_returnTypeCheck_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doNothing().when(sanServiceMock).deleteSanAdapterPolicies(anyList());
		List<Object> objList = fcAdapterPolicyController.manageSanAdapterPolicies( json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_return null check.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanAdapterPolicies_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(sanServiceMock.saveOrUpdateAdapterPolicies(anyList(), anyInt())).thenReturn(null);
		List<Object> objList = fcAdapterPolicyController.manageSanAdapterPolicies(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, objList.toString());
		verify(sanServiceMock, times(1)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}

	/**
	 * Manage san adapter policies_null check_first parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanAdapterPolicies_nullCheck_firstParameter() throws Exception {
		List<Object> jsonList = fcAdapterPolicyController.manageSanAdapterPolicies(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_null check_second parameter.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanAdapterPolicies_nullCheck_secondParameter() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> jsonList = fcAdapterPolicyController.manageSanAdapterPolicies(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_null check_both parameters.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void manageSanAdapterPolicies_nullCheck_bothParameters() throws Exception {
		List<Object> jsonList = fcAdapterPolicyController.manageSanAdapterPolicies(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(sanServiceMock, times(0)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_exception_add or update data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanAdapterPolicies_exception_addOrUpdateData() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		doThrow(Exception.class).when(sanServiceMock.saveOrUpdateAdapterPolicies(anyList(), anyInt()));
		fcAdapterPolicyController.manageSanAdapterPolicies( json, PROJECT_ID);
		verify(sanServiceMock, times(1)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(0)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Manage san adapter policies_exception_delete data.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageSanAdapterPolicies_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1,2]}";
		doThrow(Exception.class).when(sanServiceMock).deleteSanAdapterPolicies(anyList());
		fcAdapterPolicyController.manageSanAdapterPolicies( json, PROJECT_ID);
		verify(sanServiceMock, times(0)).saveOrUpdateAdapterPolicies(anyList(), anyInt());
		verify(sanServiceMock, times(1)).deleteSanAdapterPolicies(anyList());
		verifyNoMoreInteractions(sanServiceMock);
	}
}
