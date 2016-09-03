package com.cisco.ca.cstg.pdi.controllers.license;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

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

import com.cisco.ca.cstg.pdi.services.ResetLicenseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ResetLicenseControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ResetLicenseService resetLicenseServiceMock;

	@InjectMocks
	private ResetLicenseController resetLicenseController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resetLicenseController)
				.build();
	}

	@Test
	public void resetLicense_methodNameCheck() throws Exception {
		doNothing().when(resetLicenseServiceMock).clearData();
		this.mockMvc.perform(get("/resetLicense.html")).andExpect(
				handler().methodName("resetLicense"));
	}

	@Test
	public void resetLicense_returnTypeCheck() throws Exception {
		doNothing().when(resetLicenseServiceMock).clearData();
		String actual = resetLicenseController.resetLicense();
		String expected = "redirect:j_spring_security_logout";
		assertEquals(expected, actual);
		verify(resetLicenseServiceMock, times(1)).clearData();
		verifyNoMoreInteractions(resetLicenseServiceMock);
	}

	@Test(expected = Exception.class)
	public void resetLicense_exception() throws Exception {
		doThrow(Exception.class).when(resetLicenseServiceMock).clearData();
		resetLicenseController.resetLicense();
		verify(resetLicenseServiceMock, times(1)).clearData();
		verifyNoMoreInteractions(resetLicenseServiceMock);
	}
}