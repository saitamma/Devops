package com.cisco.ca.cstg.pdi.controllers.license;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.ca.cstg.pdi.pojos.License;
import com.cisco.ca.cstg.pdi.services.login.PDILoginService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * 
 * @author tapdash Created On : May 07, 2014
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class LicenseControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private PDILoginService pdiLoginServiceMock;

	@InjectMocks
	private LicenseController licenseController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(licenseController).build();
	}

	@Test
	public void uploadLicense() throws Exception {
		mockMvc.perform(get("/license.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("license/license"));
	}

	@Test
	public void licenseUpload_methodNameCheck() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		this.mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/licenseUpload.html").file(
						file)).andExpect(handler().methodName("licenseUpload"));
	}

	@Test
	public void licenseUpload_returnTypeCheck() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		ModelAndView mv = licenseController.licenseUpload(file);
		assertEquals("license/license", mv.getViewName());
	}

	@Test
	public void licenseUpload_nullCheck() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", null,
				"text/plain", "some xml".getBytes());
		ModelAndView mv = licenseController.licenseUpload(file);
		assertEquals("license/license", mv.getViewName());
		assertNotNull(mv.getModel());
		String expected = "No File Uploaded. Please select a file and upload.";
		assertEquals(expected, mv.getModel().get("errorMessage"));
	}

	/*@Test
	public void licenseUpload_differnetFileUpload() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.xml",
				"text/plain", "some xml".getBytes());
		ModelAndView mv = licenseController.licenseUpload(file);
		assertEquals("license/license", mv.getViewName());
		assertNotNull(mv.getModel());
		String expected = "The uploaded file is not an text file ending with .lic or .txt extension.";
		assertEquals(expected, mv.getModel().get("errorMessage"));
	}*/

	@Test
	public void getLicenseDetails_methodNameCheck() throws Exception {
		License obj = new License();
		obj.setId(1);
		obj.setAsPid("pid");
		obj.setCreatedby("dev");
		obj.setCustomerName("name");
		obj.setName("license");
		obj.setTheatre("theatre");
		when(pdiLoginServiceMock.findById(License.class, 1)).thenReturn(obj);
		this.mockMvc.perform(
				post("/getLicenseDetails.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(
				handler().methodName("getLicenseDetails"));
	}

	@Test
	public void getLicenseDetails_returnTypeCheck() throws Exception {
		License obj = new License();
		obj.setId(1);
		obj.setAsPid("pid");
		obj.setCreatedby("dev");
		obj.setCustomerName("name");
		obj.setName("license");
		obj.setTheatre("theatre");
		when(pdiLoginServiceMock.findById(License.class, 1)).thenReturn(obj);
		String expected = "{\"Name\":\"license\",\"Theatre\":\"theatre\",\"Customer Name\":\"name\",\"Site\":\"\",\"AS PID\":\"pid\",\"Created by\":\"dev\"}";
		String actual = licenseController.getLicenseDetails();
		assertEquals(expected, actual);
	}

	@Test
	public void getLicenseDetails_nullReturnCheck() throws Exception {
		String expected = "{}";
		when(pdiLoginServiceMock.findById(License.class, 1)).thenReturn(null);
		String actual = licenseController.getLicenseDetails();
		assertEquals(expected, actual);
		verify(pdiLoginServiceMock, times(1)).findById(License.class, 1);
		verifyNoMoreInteractions(pdiLoginServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLicenseDetails_exception() throws Exception {
		String expected = "{}";
		doThrow(Exception.class).when(
				pdiLoginServiceMock.findById(License.class, 1));
		String actual = licenseController.getLicenseDetails();
		assertEquals(expected, actual);
	}
}
