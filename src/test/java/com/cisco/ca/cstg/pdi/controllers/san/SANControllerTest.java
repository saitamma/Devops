package com.cisco.ca.cstg.pdi.controllers.san;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * 
 * The Class SANControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class SANControllerTest implements TestConstants{

	/** The mock mvc. */
	private MockMvc mockMvc;
		
	/** The san service mock. */
	@Mock
	private SANService sanServiceMock;

	/** The san controller. */
	@InjectMocks
	private SANController sanController;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(sanController).build();
	}

	/**
	 * Show san.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void showSan() throws Exception {
		mockMvc.perform(get("/san.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("san/sanMain"));
	}

	/**
	 * Delete all san config details_method name check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteAllSANConfigDetails_methodNameCheck() throws Exception {
		mockMvc.perform(get("/deleteAllSANConfigDetails.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("deleteAllSANConfigDetails"));
	}

	/**
	 * Delete all san config details_return type check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteAllSANConfigDetails_returnTypeCheck() throws Exception {
		doNothing().when(sanServiceMock).deleteAllSANConfigurationDetails(PROJECT_ID);
		String actual = sanController.deleteAllSANConfigDetails(PROJECT_ID);
		assertEquals("success", actual);
		verify(sanServiceMock, times(1)).deleteAllSANConfigurationDetails(PROJECT_ID);
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	/**
	 * Delete all san config details_null check.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteAllSANConfigDetails_nullCheck() throws Exception {
		String actual = sanController.deleteAllSANConfigDetails(null);
		assertEquals("failure", actual);
		verify(sanServiceMock, times(0)).deleteAllSANConfigurationDetails(PROJECT_ID);
		verifyNoMoreInteractions(sanServiceMock);
	}
	
	
	/**
	 * Delete all San config details_exception check.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected=Exception.class)
	public void deleteAllSANConfigDetails_exceptionCheck() throws Exception {
		doThrow(new NullPointerException()).when(sanServiceMock).deleteAllSANConfigurationDetails(PROJECT_ID);
		sanController.deleteAllSANConfigDetails(PROJECT_ID);
		verify(sanServiceMock,times(1)).deleteAllSANConfigurationDetails(PROJECT_ID);
		verifyNoMoreInteractions(sanServiceMock);		
	}
		

}
