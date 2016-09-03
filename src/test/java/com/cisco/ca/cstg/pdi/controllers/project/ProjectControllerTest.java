package com.cisco.ca.cstg.pdi.controllers.project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.services.ConfigurationService;
import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.ProjectDetailsService;
import com.cisco.ca.cstg.pdi.services.WizardConfigurationService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * 
 * @author tapdash Created On : May 07, 2014
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class ProjectControllerTest implements TestConstants {

	private MockMvc mockMvc;	

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private ProjectDetailsService projectDetailsServiceMock;

	@Mock
	private WizardConfigurationService wizardServiceMock;

	@Mock
	private ConfigurationService configurationServiceMock;

	@Mock
	private InfrastructureService infrastructureServiceMock;

	@Mock
	private EquipmentService equipmentServiceMock;

	@InjectMocks
	private ProjectController projectController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
	}

	/*
	 * @Test public void showProjects() throws Exception {
	 * mockMvc.perform(get("/listProjects.html")).andExpect(status().isOk())
	 * .andExpect(forwardedUrl("project/project")); }
	 */

	@Test
	public void showProjects_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/listProjects.html").accept(
						MediaType.APPLICATION_JSON_VALUE)).andExpect(
				handler().methodName("showProjects"));
	}

	@Test
	public void listProjects_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/listProjectData.html").accept(
						MediaType.APPLICATION_JSON_VALUE)).andExpect(
				handler().methodName("listProjects"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void listProjects_returnTypeCheck() throws Exception {
		ProjectDetails pd = new ProjectDetails();
		pd.setId(1);
		pd.setCreatedDate(new Date(114, 6, 7));
		pd.setSkipSan(false);
		pd.setTheatre("theatre");
		pd.setProjectName("test");
		List<ProjectDetails> projectList = new ArrayList<>();
		projectList.add(pd);
		when(projectDetailsServiceMock.getProjects(anyString())).thenReturn(
				projectList);
		MockHttpServletRequest req = new MockHttpServletRequest();
		HttpSession session = req.getSession();
		session.setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		List<Object> jsonList = projectController.listProjects(req);
		String expected = "[{\"id\":1,\"createdBy\":null,\"ipPoolAssignmentOrder\":null,\"theatre\":\"theatre\",\"skipSan\":false,\"createdDate\":\"2014-07-07\",\"projectName\":\"test\"}]";
		assertEquals(expected, jsonList.toString());
		verify(projectDetailsServiceMock, times(1)).getProjects(anyString());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void listProjects_returnNullCheck() throws Exception {
		when(projectDetailsServiceMock.getProjects(anyString())).thenReturn(
				null);
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, "invalid");
		List<Object> jsonList = projectController.listProjects(req);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(projectDetailsServiceMock, times(1)).getProjects(anyString());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void listProjects_exception() throws Exception {
		when(projectDetailsServiceMock.getProjects(anyString())).thenThrow(
				new NullPointerException());
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		projectController.listProjects(req);
		verify(projectDetailsServiceMock, times(1)).getProjects(anyString());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void createProject_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/createProject.html").accept(MediaType.APPLICATION_JSON))
				.andExpect(handler().methodName("createProject"));
	}

	@Test
	public void createProject_returnTypeCheck() throws Exception {
		String json = "{\"id\":1,\"theatre\":\"theatre\",\"createdDate\":\"2014-07-07\",\"skipSan\":false,\"projectName\":\"test\"}";
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenReturn(1);
		doNothing().when(wizardServiceMock).createWizardStatus(anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		String actual = projectController.createProject(json, req);
		assertEquals("1", actual);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@Test
	public void createProject_nullCheck() throws Exception {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		String actual = projectController.createProject(null, req);
		assertEquals("0", actual);
		verify(projectDetailsServiceMock, times(0)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(0)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@Test(expected = Exception.class)
	public void createProject_exception_addNewProject() throws Exception {
		String json = "{\"id\":1}";
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenThrow(new NullPointerException());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.createProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(0)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@Test(expected = Exception.class)
	public void createProject_exception_createWizardStatus() throws Exception {
		String json = "{\"id\":1}";
		doThrow(new NullPointerException()).when(wizardServiceMock)
				.createWizardStatus(anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.createProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@Test
	public void updateProject_methodNameCheck() throws Exception {
		mockMvc.perform(post("/updateProject.html")).andExpect(
				handler().methodName("updateProject"));
	}

	@Test
	public void updateProject_returnTypeCheck() throws Exception {
		String json = "{\"id\":1}";
		doNothing().when(projectDetailsServiceMock).updateProjectDetails(
				any(ProjectDetails.class));
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		String actual = projectController.updateProject(json, req);
		assertEquals("success", actual);
		verify(projectDetailsServiceMock, times(1)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateProject_nullCheck() throws Exception {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		String actual = projectController.updateProject(null, req);
		assertEquals("failure", actual);
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void updateProject_exception() throws Exception {
		String json = "{\"id\":1}";
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.updateProjectDetails(any(ProjectDetails.class));
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.updateProject(json, req);
		verify(projectDetailsServiceMock, times(1)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void deleteProject_methodNameCheck() throws Exception {
		mockMvc.perform(get("/deleteProject.html")).andExpect(
				handler().methodName("deleteProject"));
	}

	@Test
	public void deleteProject_returnTypeCheck() throws Exception {
		doNothing().when(projectDetailsServiceMock).deleteProject(anyInt());
		String actual = projectController.deleteProject(PROJECT_ID);
		assertEquals("success", actual);
		verify(projectDetailsServiceMock, times(1)).deleteProject(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void deleteProject_nullCheck() throws Exception {
		String actual = projectController.deleteProject(null);
		assertEquals("failure", actual);
		verify(projectDetailsServiceMock, times(0)).deleteProject(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void deleteProject_exception() throws Exception {
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.deleteProject(anyInt());
		projectController.deleteProject(PROJECT_ID);
		verify(projectDetailsServiceMock, times(1)).deleteProject(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateSkipSanDetail_methodNameCheck() throws Exception {
		mockMvc.perform(post("/updateSkipSanDetail.html")).andExpect(
				handler().methodName("updateSkipSanDetail"));
	}

	@Test
	public void updateSkipSanDetail_returnTypeCheck() throws Exception {
		String json = "{\"id\":1,\"skipSan\":0}";
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doNothing().when(projectDetailsServiceMock).updateProjectDetails(
				any(ProjectDetails.class));
		String actual = projectController.updateSkipSanDetail(json);
		assertEquals("success", actual);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateSkipSanDetail_nullCheck() throws Exception {
		String actual = projectController.updateSkipSanDetail(null);
		assertEquals("failure", actual);
		verify(projectDetailsServiceMock, times(0)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateSkipSanDetail_returnNullCheck() throws Exception {
		String json = "{\"id\":1,\"skipSan\":0}";
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(null);
		String actual = projectController.updateSkipSanDetail(json);
		assertEquals("failure", actual);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void updateSkipSanDetail_exception_fetchProjectDetails()
			throws Exception {
		String json = "{\"id\":1,\"skipSan\":0}";
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.fetchProjectDetails(anyInt());
		projectController.updateSkipSanDetail(json);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void updateSkipSanDetail_exception_updateProjectDetails()
			throws Exception {
		String json = "{\"id\":1,\"skipSan\":0}";
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.updateProjectDetails(any(ProjectDetails.class));
		projectController.updateSkipSanDetail(json);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateIpPoolAssignmentOrder_methodNameCheck() throws Exception {
		mockMvc.perform(post("/updateIpPoolAssignmentOrder.html")).andExpect(
				handler().methodName("updateIpPoolAssignmentOrder"));
	}

	@Test
	public void updateIpPoolAssignmentOrder_returnTypeCheck() throws Exception {
		String json = "{\"id\":1,\"ipPoolAssignmentOrder\":0}";
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doNothing().when(projectDetailsServiceMock).updateProjectDetails(
				any(ProjectDetails.class));
		String actual = projectController.updateIpPoolAssignmentOrder(json);
		assertEquals("success", actual);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateIpPoolAssignmentOrder_nullCheck() throws Exception {
		String actual = projectController.updateIpPoolAssignmentOrder(null);
		assertEquals("failure", actual);
		verify(projectDetailsServiceMock, times(0)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void updateIpPoolAssignmentOrder_returnNullCheck() throws Exception {
		String json = "{\"id\":1,\"ipPoolAssignmentOrder\":0}";
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(null);
		String actual = projectController.updateIpPoolAssignmentOrder(json);
		assertEquals("failure", actual);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void updateIpPoolAssignmentOrder_exception_fetchProjectDetails()
			throws Exception {
		String json = "{\"id\":1,\"ipPoolAssignmentOrder\":0}";
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.fetchProjectDetails(anyInt());
		projectController.updateIpPoolAssignmentOrder(json);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(0)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void updateIpPoolAssignmentOrder_exception_updateProjectDetails()
			throws Exception {
		String json = "{\"id\":1,\"ipPoolAssignmentOrder\":0}";
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.updateProjectDetails(any(ProjectDetails.class));
		projectController.updateIpPoolAssignmentOrder(json);
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).updateProjectDetails(
				any(ProjectDetails.class));
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void fetchProjectDetailIfNameAlreadyExist_methodNameCheck()
			throws Exception {
		mockMvc.perform(post("/fetchProjectDetailIfNameAlreadyExist.html"))
				.andExpect(
						handler().methodName(
								"fetchProjectDetailIfNameAlreadyExist"));
	}

	@Test
	public void fetchProjectDetailIfNameAlreadyExist_returnTypeCheck()
			throws Exception {
		String json = "{\"id\":1}";
		when(
				projectDetailsServiceMock.isProjectExits(
						any(ProjectDetails.class), anyString())).thenReturn(
				true);
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		boolean actual = projectController
				.fetchProjectDetailIfNameAlreadyExist(json, req);
		assertEquals(true, actual);
		verify(projectDetailsServiceMock, times(1)).isProjectExits(
				any(ProjectDetails.class), anyString());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void fetchProjectDetailIfNameAlreadyExist_nullCheck()
			throws Exception {
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		boolean actual = projectController
				.fetchProjectDetailIfNameAlreadyExist(null, req);
		assertEquals(false, actual);
		verify(projectDetailsServiceMock, times(0)).isProjectExits(
				any(ProjectDetails.class), anyString());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchProjectDetailIfNameAlreadyExist_exception()
			throws Exception {
		String json = "{\"id\":1}";
		doThrow(new NullPointerException()).when(projectDetailsServiceMock)
				.isProjectExits(any(ProjectDetails.class), anyString());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.fetchProjectDetailIfNameAlreadyExist(json, req);
		verify(projectDetailsServiceMock, times(1)).isProjectExits(
				any(ProjectDetails.class), anyString());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void cloningSelectedProject_methodNameCheck() throws Exception {
		mockMvc.perform(post("/cloningSelectedProject.html")).andExpect(
				handler().methodName("cloningSelectedProject"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void cloningSelectedProject_returnTypeCheck() throws Exception {
		String jsonObj = "{\"id\":1}";
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		pd.setCreatedDate(new Date(115, 7, 28));
		pd.setTheatre("theatre");
		pd.setSkipSan(true);
		pd.setIpPoolAssignmentOrder("0");
		when(
				projectDetailsServiceMock
						.createCloneProject(any(ProjectDetails.class)))
				.thenReturn(pd);
		doNothing().when(wizardServiceMock).createWizardStatus(anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		List<Object> projectList = projectController.cloningSelectedProject(
				jsonObj, req);
		String expected = "[{\"id\":1,\"createdBy\":null,\"ipPoolAssignmentOrder\":\"0\",\"theatre\":\"theatre\",\"skipSan\":true,\"createdDate\":\"2015-08-28\",\"projectName\":\"test\"}]";
		assertEquals(expected, projectList.toString());
		verify(projectDetailsServiceMock, times(1)).createCloneProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@Test
	public void cloningSelectedProject_nullCheck() throws Exception {
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, "invalid");
		List<Object> projectList = projectController.cloningSelectedProject(
				null, req);
		String expected = "[]";
		assertEquals(expected, projectList.toString());
		verify(projectDetailsServiceMock, times(0)).createCloneProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(0)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void cloningSelectedProject_exception_createCloneProject()
			throws Exception {
		String jsonObj = "{\"id\":1}";
		doThrow(Exception.class).when(projectDetailsServiceMock)
				.createCloneProject(any(ProjectDetails.class));
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.cloningSelectedProject(jsonObj, req);
		verify(projectDetailsServiceMock, times(1)).createCloneProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(0)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@SuppressWarnings("deprecation")
	@Test(expected = Exception.class)
	public void cloningSelectedProject_exception_createWizardStatus()
			throws Exception {
		String jsonObj = "{\"id\":1}";
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		pd.setCreatedDate(new Date(115, 7, 28));
		pd.setTheatre("theatre");
		pd.setSkipSan(true);
		pd.setIpPoolAssignmentOrder("0");
		when(
				projectDetailsServiceMock
						.createCloneProject(any(ProjectDetails.class)))
				.thenReturn(pd);
		doThrow(Exception.class).when(wizardServiceMock).createWizardStatus(
				anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.cloningSelectedProject(jsonObj, req);
		verify(projectDetailsServiceMock, times(1)).createCloneProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
	}

	@Test
	public void fillDataForClonedProject_methodNameCheck() throws Exception {
		mockMvc.perform(get("/fillDataForClonedProject.html")).andExpect(
				handler().methodName("fillDataForClonedProject"));
	}

	@Test
	public void fillDataForClonedProject_returnTypeCheck() throws Exception {
		doNothing().when(configurationServiceMock).cloneProject(anyInt(),
				anyInt());
		String actual = projectController.fillDataForClonedProject(1, 2);
		String expected = "success";
		assertEquals(expected, actual);
		verify(configurationServiceMock, times(1)).cloneProject(anyInt(),
				anyInt());
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void fillDataForClonedProject_nullCheck_firstParameter()
			throws Exception {
		doNothing().when(configurationServiceMock).cloneProject(anyInt(),
				anyInt());
		String actual = projectController.fillDataForClonedProject(null, 2);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(configurationServiceMock, times(0)).cloneProject(anyInt(),
				anyInt());
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void fillDataForClonedProject_nullCheck_secondParameter()
			throws Exception {
		doNothing().when(configurationServiceMock).cloneProject(anyInt(),
				anyInt());
		String actual = projectController.fillDataForClonedProject(1, null);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(configurationServiceMock, times(0)).cloneProject(anyInt(),
				anyInt());
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void fillDataForClonedProject_nullCheck_bothParameters()
			throws Exception {
		doNothing().when(configurationServiceMock).cloneProject(anyInt(),
				anyInt());
		String actual = projectController.fillDataForClonedProject(null, null);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(configurationServiceMock, times(0)).cloneProject(anyInt(),
				anyInt());
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test(expected = Exception.class)
	public void fillDataForClonedProject_exception() throws Exception {
		doThrow(Exception.class).when(configurationServiceMock).cloneProject(
				anyInt(), anyInt());
		projectController.fillDataForClonedProject(1, 2);
		verify(configurationServiceMock, times(1)).cloneProject(anyInt(),
				anyInt());
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void importProject_methodNameCheck() throws Exception {
		mockMvc.perform(post("/importProject.html")).andExpect(
				handler().methodName("importProject"));
	}

	@Test
	public void importProject_returnTypeCheck() throws Exception {
		String json = "{\"projectData\":{\"id\":1,\"projectName\":\"test\",\"theatre\":\"theatre\",\"createdDate\":\"2015-7-28\",\"isUploaded\":\"true\"},\"infraData\":{\"serverModel\":\"2.2\",\"softwareVersion\":\"2\",\"ioModule\":\"\"},\"equipmentData\":{\"cdpAction\":\"\",\"cdpLinkAgg\":\"\"}}";
		ServerModel sm = new ServerModel(2);
		sm.setDescription("testServer");
		sm.setSoftwareVersion("2.2");
		List<ServerModel> smList = new ArrayList<>();
		smList.add(sm);
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenReturn(1);
		doNothing().when(wizardServiceMock).createWizardStatus(anyInt());
		when(infrastructureServiceMock.fetchServerModelDetails(anyString()))
				.thenReturn(smList);
		doNothing().when(infrastructureServiceMock).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		doNothing().when(equipmentServiceMock)
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		Integer actual = projectController.importProject(json, req);
		Integer expected = 1;
		assertEquals(expected, actual);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(1)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void importProject_nullCheck() throws Exception {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		Integer actual = projectController.importProject(null, req);
		Integer expected = 0;
		assertEquals(expected, actual);
		verify(projectDetailsServiceMock, times(0)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(0)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(0)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void importProject_exception_addNewProject() throws Exception {
		String json = "{\"projectData\":{\"id\":1,\"projectName\":\"test\",\"theatre\":\"theatre\",\"createdDate\":\"2015-7-28\",\"isUploaded\":\"true\"},\"infraData\":{\"serverModel\":\"2.2\",\"softwareVersion\":\"2\",\"ioModule\":\"\"},\"equipmentData\":{\"cdpAction\":\"\",\"cdpLinkAgg\":\"\"}}";
		doThrow(Exception.class).when(projectDetailsServiceMock).addNewProject(
				any(ProjectDetails.class));
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.importProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(0)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(0)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void importProject_exception_createWizardStatus() throws Exception {
		String json = "{\"projectData\":{\"id\":1,\"projectName\":\"test\",\"theatre\":\"theatre\",\"createdDate\":\"2015-7-28\",\"isUploaded\":\"true\"},\"infraData\":{\"serverModel\":\"2.2\",\"softwareVersion\":\"2\",\"ioModule\":\"\"},\"equipmentData\":{\"cdpAction\":\"\",\"cdpLinkAgg\":\"\"}}";
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenReturn(1);
		doThrow(Exception.class).when(wizardServiceMock).createWizardStatus(
				anyInt());
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		projectController.importProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(0)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void importProject_exception_fetchServerModelDetails()
			throws Exception {
		String json = "{\"projectData\":{\"id\":1,\"projectName\":\"test\",\"theatre\":\"theatre\",\"createdDate\":\"2015-7-28\",\"isUploaded\":\"true\"},\"infraData\":{\"serverModel\":\"2.2\",\"softwareVersion\":\"2\",\"ioModule\":\"\"},\"equipmentData\":{\"cdpAction\":\"\",\"cdpLinkAgg\":\"\"}}";
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenReturn(1);
		doNothing().when(wizardServiceMock).createWizardStatus(anyInt());
		doThrow(Exception.class).when(
				infrastructureServiceMock.fetchServerModelDetails(anyString()));
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.importProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(0)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void importProject_exception_saveInfrastructureDetails()
			throws Exception {
		String json = "{\"projectData\":{\"id\":1,\"projectName\":\"test\",\"theatre\":\"theatre\",\"createdDate\":\"2015-7-28\",\"isUploaded\":\"true\"},\"infraData\":{\"serverModel\":\"2.2\",\"softwareVersion\":\"2\",\"ioModule\":\"\"},\"equipmentData\":{\"cdpAction\":\"\",\"cdpLinkAgg\":\"\"}}";
		ServerModel sm = new ServerModel(2);
		sm.setDescription("testServer");
		sm.setSoftwareVersion("2.2");
		List<ServerModel> smList = new ArrayList<>();
		smList.add(sm);
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenReturn(1);
		doNothing().when(wizardServiceMock).createWizardStatus(anyInt());
		when(infrastructureServiceMock.fetchServerModelDetails(anyString()))
				.thenReturn(smList);
		doThrow(Exception.class).when(infrastructureServiceMock)
				.saveInfrastructureDetails(any(Infrastructure.class), anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.importProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(1)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(0))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test(expected = Exception.class)
	public void importProject_exception_saveOrUpdateEquipmentChasisConfiguration()
			throws Exception {
		String json = "{\"projectData\":{\"id\":1,\"projectName\":\"test\",\"theatre\":\"theatre\",\"createdDate\":\"2015-7-28\",\"isUploaded\":\"true\"},\"infraData\":{\"serverModel\":\"2.2\",\"softwareVersion\":\"2\",\"ioModule\":\"\"},\"equipmentData\":{\"cdpAction\":\"\",\"cdpLinkAgg\":\"\"}}";
		ServerModel sm = new ServerModel(2);
		sm.setDescription("testServer");
		sm.setSoftwareVersion("2.2");
		List<ServerModel> smList = new ArrayList<>();
		smList.add(sm);
		when(projectDetailsServiceMock.addNewProject(any(ProjectDetails.class)))
				.thenReturn(1);
		doNothing().when(wizardServiceMock).createWizardStatus(anyInt());
		when(infrastructureServiceMock.fetchServerModelDetails(anyString()))
				.thenReturn(smList);
		doNothing().when(infrastructureServiceMock).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		doThrow(Exception.class).when(equipmentServiceMock)
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.getSession().setAttribute(LOGIN_USER_CEC, LOGIN_USER_CEC_VALUE);
		projectController.importProject(json, req);
		verify(projectDetailsServiceMock, times(1)).addNewProject(
				any(ProjectDetails.class));
		verify(wizardServiceMock, times(1)).createWizardStatus(anyInt());
		verify(infrastructureServiceMock, times(1)).fetchServerModelDetails(
				anyString());
		verify(infrastructureServiceMock, times(1)).saveInfrastructureDetails(
				any(Infrastructure.class), anyInt());
		verify(equipmentServiceMock, times(1))
				.saveOrUpdateEquipmentChasisConfiguration(
						any(EquipmentChasis.class), anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(wizardServiceMock);
		verifyNoMoreInteractions(infrastructureServiceMock);
		verifyNoMoreInteractions(equipmentServiceMock);
	}

	@Test
	public void postImportedProjectFile_methodNameCheck() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		this.mockMvc.perform(
				MockMvcRequestBuilders
						.fileUpload("/postImportedProjectFile.html").file(file)
						.param("projectid", Integer.toString(PROJECT_ID)))
				.andExpect(handler().methodName("postImportedProjectFile"));
	}

	@Test
	public void postImportedProjectFile_returnTypeCheck() throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doNothing().when(configurationServiceMock).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		doNothing().when(configurationServiceMock).checkMetadataVersion(
				anyInt());
		doNothing().when(configurationServiceMock).importXMlToADAProject(
				anyInt());
		ModelAndView mv = projectController.postImportedProjectFile(file,
				PROJECT_ID);
		assertEquals("project/postImportedProjectFile", mv.getViewName());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(configurationServiceMock, times(1)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(1)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(1)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void postImportedProjectFile_nullCheck_firstParameter()
			throws Exception {
		ModelAndView mv = projectController.postImportedProjectFile(null,
				PROJECT_ID);
		assertEquals(null, mv);
		verify(projectDetailsServiceMock, times(0)).fetchProjectDetails(
				anyInt());
		verify(configurationServiceMock, times(0)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(0)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(0)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void postImportedProjectFile_nullCheck_secondParameter()
			throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		ModelAndView mv = projectController.postImportedProjectFile(file, null);
		assertEquals(null, mv);
		verify(projectDetailsServiceMock, times(0)).fetchProjectDetails(
				anyInt());
		verify(configurationServiceMock, times(0)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(0)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(0)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void postImportedProjectFile_nullCheck_bothParameters()
			throws Exception {
		ModelAndView mv = projectController.postImportedProjectFile(null, null);
		assertEquals(null, mv);
		verify(projectDetailsServiceMock, times(0)).fetchProjectDetails(
				anyInt());
		verify(configurationServiceMock, times(0)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(0)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(0)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test(expected = Exception.class)
	public void postImportedProjectFile_exception_fetchProjectDetails()
			throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		projectController.postImportedProjectFile(file, PROJECT_ID);
		doThrow(Exception.class).when(
				projectDetailsServiceMock.fetchProjectDetails(anyInt()));
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(configurationServiceMock, times(0)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(0)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(0)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void postImportedProjectFile_exception_validateAndCopyFile()
			throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doThrow(Exception.class).when(configurationServiceMock)
				.validateAndCopyFile(any(MultipartFile.class), anyInt());
		ModelAndView mv = projectController.postImportedProjectFile(file,
				PROJECT_ID);
		String expected = "project/project";
		assertEquals(expected, mv.getViewName());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).deleteProject(anyInt());
		verify(configurationServiceMock, times(1)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(0)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(0)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void postImportedProjectFile_exception_checkMetadataVersion()
			throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doNothing().when(configurationServiceMock).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		doThrow(Exception.class).when(configurationServiceMock)
				.checkMetadataVersion(anyInt());
		ModelAndView mv = projectController.postImportedProjectFile(file,
				PROJECT_ID);
		String expected = "project/project";
		assertEquals(expected, mv.getViewName());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).deleteProject(anyInt());
		verify(configurationServiceMock, times(1)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(1)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(0)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}

	@Test
	public void postImportedProjectFile_exception_importXMLToADAProject()
			throws Exception {
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt",
				"text/plain", "some xml".getBytes());
		ProjectDetails pd = new ProjectDetails(1);
		pd.setProjectName("test");
		when(projectDetailsServiceMock.fetchProjectDetails(anyInt()))
				.thenReturn(pd);
		doNothing().when(configurationServiceMock).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		doNothing().when(configurationServiceMock).checkMetadataVersion(
				anyInt());
		doThrow(Exception.class).when(configurationServiceMock)
				.importXMlToADAProject(anyInt());
		ModelAndView mv = projectController.postImportedProjectFile(file,
				PROJECT_ID);
		String expected = "project/project";
		assertEquals(expected, mv.getViewName());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verify(projectDetailsServiceMock, times(1)).deleteProject(anyInt());
		verify(configurationServiceMock, times(1)).validateAndCopyFile(
				any(MultipartFile.class), anyInt());
		verify(configurationServiceMock, times(1)).checkMetadataVersion(
				anyInt());
		verify(configurationServiceMock, times(1)).importXMlToADAProject(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
		verifyNoMoreInteractions(configurationServiceMock);
	}
}