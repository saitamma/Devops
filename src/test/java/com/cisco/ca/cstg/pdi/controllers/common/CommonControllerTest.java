package com.cisco.ca.cstg.pdi.controllers.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.MainMenuState;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ProjectSettings;
import com.cisco.ca.cstg.pdi.pojos.WizardStatus;
import com.cisco.ca.cstg.pdi.services.CommonUtilServices;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.ProjectDetailsService;
import com.cisco.ca.cstg.pdi.services.WizardConfigurationService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * 
 * @author tapdash, Created On : May 07, 2014
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class CommonControllerTest implements TestConstants {

	private MockMvc mockMvc;

	@Mock
	private WizardConfigurationService wizardConfMock;

	@Mock
	private ProjectDetailsService projectDetailsServiceMock;

	@Mock
	private CommonUtilServices commonUtilServiceMock;

	@Mock
	private InfrastructureService infrastructureService;

	@InjectMocks
	private CommonController commonController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(commonController).build();
	}

	@Test
	public void header() throws Exception {
		this.mockMvc.perform(get("/header.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("common/header"));
	}

	@Test
	public void footer() throws Exception {
		this.mockMvc.perform(get("/footer.html")).andExpect(status().isOk())
				.andExpect(forwardedUrl("common/footer"));
	}

	@Test
	public void showWizard() throws Exception {
		this.mockMvc.perform(
				post("/wizard.html").param("projectId",
						Integer.toString(PROJECT_ID)).param("projectName",
						"test")).andExpect(
				forwardedUrl("common/wizardTemplate"));
	}

	@Test
	public void error() throws Exception {
		this.mockMvc.perform(get("/error.html")).andExpect(
				forwardedUrl("error"));
	}

	@Test
	public void fetchActiveWizardStatus_methodNameCheck() throws Exception {
		WizardStatus wizardStatus = new WizardStatus();
		wizardStatus.setId(1);
		wizardStatus.setMainMenuState(new MainMenuState());
		wizardStatus.getMainMenuState().setId(2);
		wizardStatus.setSubMenuId(1);

		List<Object> listOfCompletedWizardStatus = new ArrayList<>();
		listOfCompletedWizardStatus.add(0);
		listOfCompletedWizardStatus.add(1);

		when(wizardConfMock.getActiveWizardStatus(anyInt())).thenReturn(
				wizardStatus);
		when(wizardConfMock.getCompletedMainMenuWizardStatus(anyInt()))
				.thenReturn(listOfCompletedWizardStatus);

		this.mockMvc.perform(
				get("/getWizardStatus.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(
				handler().methodName("fetchActiveWizardStatus"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void fetchActiveWizardStatus_returnTypeCheck() throws Exception {
		WizardStatus wizardStatus = new WizardStatus();
		wizardStatus.setId(1);
		wizardStatus.setMainMenuState(new MainMenuState());
		wizardStatus.getMainMenuState().setId(2);
		wizardStatus.setSubMenuId(1);

		List<Object> listOfCompletedWizardStatus = new ArrayList<>();
		listOfCompletedWizardStatus.add(0);
		listOfCompletedWizardStatus.add(1);

		when(wizardConfMock.getActiveWizardStatus(PROJECT_ID)).thenReturn(
				wizardStatus);
		when(wizardConfMock.getCompletedMainMenuWizardStatus(PROJECT_ID))
				.thenReturn(listOfCompletedWizardStatus);

		List<Object> jsonList = commonController
				.fetchActiveWizardStatus(PROJECT_ID);
		String expected = "[{\"activeStateMenuIndex\":2,\"hasCompletedSubMenuIndex\":null,\"activeStateSubMenuIndex\":1,\"hasCompletedMenuIndex\":[0,1]}]";

		assertEquals(expected, jsonList.toString());
		verify(wizardConfMock, times(1)).getActiveWizardStatus(anyInt());
		verify(wizardConfMock, times(1)).getCompletedMainMenuWizardStatus(
				anyInt());
		verify(wizardConfMock, times(1)).getCompletedSubMenuWizardStatus(
				anyInt(), anyList());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void fetchActiveWizardStatus_nullCheck() throws Exception {
		List<Object> jsonList = commonController.fetchActiveWizardStatus(null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(wizardConfMock, times(0)).getActiveWizardStatus(anyInt());
		verify(wizardConfMock, times(0)).getCompletedMainMenuWizardStatus(
				anyInt());
		verify(wizardConfMock, times(0)).getCompletedSubMenuWizardStatus(
				anyInt(), anyList());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void fetchActiveWizardStatus_exception1() throws Exception {
		doThrow(Exception.class).when(wizardConfMock).getActiveWizardStatus(
				anyInt());

		List<Object> jsonList = commonController
				.fetchActiveWizardStatus(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(wizardConfMock, times(1)).getActiveWizardStatus(anyInt());
		verify(wizardConfMock, times(0)).getCompletedMainMenuWizardStatus(
				anyInt());
		verify(wizardConfMock, times(0)).getCompletedSubMenuWizardStatus(
				anyInt(), anyList());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void fetchActiveWizardStatus_exception2() throws Exception {
		WizardStatus wizardStatus = new WizardStatus();
		wizardStatus.setId(1);
		wizardStatus.setMainMenuState(new MainMenuState());
		wizardStatus.getMainMenuState().setId(2);
		wizardStatus.setSubMenuId(1);
		when(wizardConfMock.getActiveWizardStatus(PROJECT_ID)).thenReturn(
				wizardStatus);

		doThrow(Exception.class).when(wizardConfMock)
				.getCompletedMainMenuWizardStatus(PROJECT_ID);

		List<Object> jsonList = commonController
				.fetchActiveWizardStatus(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(wizardConfMock, times(1)).getActiveWizardStatus(anyInt());
		verify(wizardConfMock, times(1)).getCompletedMainMenuWizardStatus(
				anyInt());
		verify(wizardConfMock, times(0)).getCompletedSubMenuWizardStatus(
				anyInt(), anyList());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void fetchActiveWizardStatus_exception3() throws Exception {
		WizardStatus wizardStatus = new WizardStatus();
		wizardStatus.setId(1);
		wizardStatus.setMainMenuState(new MainMenuState());
		wizardStatus.getMainMenuState().setId(2);
		wizardStatus.setSubMenuId(1);

		List<Object> listOfCompletedWizardStatus = new ArrayList<>();
		listOfCompletedWizardStatus.add(0);
		listOfCompletedWizardStatus.add(1);

		when(wizardConfMock.getActiveWizardStatus(PROJECT_ID)).thenReturn(
				wizardStatus);
		when(wizardConfMock.getCompletedMainMenuWizardStatus(PROJECT_ID))
				.thenReturn(listOfCompletedWizardStatus);
		doThrow(Exception.class).when(wizardConfMock)
				.getCompletedSubMenuWizardStatus(anyInt(), anyList());
		List<Object> jsonList = commonController
				.fetchActiveWizardStatus(PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(wizardConfMock, times(1)).getActiveWizardStatus(anyInt());
		verify(wizardConfMock, times(1)).getCompletedMainMenuWizardStatus(
				anyInt());
		verify(wizardConfMock, times(1)).getCompletedSubMenuWizardStatus(
				anyInt(), anyList());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@Test
	public void updateWizardStatus_methodNameCheck() throws Exception {
		this.mockMvc
				.perform(post("/setWizardStatus.html").param("jsonObj", ""))
				.andExpect(handler().methodName("updateWizardStatus"));
	}

	@Test
	public void updateWizardStatus_returnTypeCheck() throws Exception {
		String jsonObj = "[{\"projectId\":1,\"activeStateMenuIndex\":2,\"activeStateSubMenuIndex\":3}]";
		doNothing().when(wizardConfMock).updateWizardStatus(anyString());
		String message = commonController.updateWizardStatus(jsonObj);
		String expected = "{success}";

		assertEquals(expected, message);
		verify(wizardConfMock, times(1)).updateWizardStatus(anyString());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@Test
	public void updateWizardStatus_nullCheck() throws Exception {
		String message = commonController.updateWizardStatus(null);
		String expected = "{}";

		assertEquals(expected, message);
		verify(wizardConfMock, times(0)).updateWizardStatus(anyString());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@Test(expected = Exception.class)
	public void updateWizardStatus_exception() throws Exception {
		doThrow(Exception.class).when(wizardConfMock).updateWizardStatus(
				anyString());
		commonController.updateWizardStatus(anyString());

		verify(wizardConfMock, times(1)).updateWizardStatus(anyString());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@Test
	public void getPDIProperty_methodNameCheck() throws Exception {
		this.mockMvc.perform(get("/getPDIProperties.html")).andExpect(
				handler().methodName("getPDIProperty"));
	}

	@Test
	public void getPDIProperty_returnTypeCheck() throws Exception {
		List<Object> message = commonController.getPDIProperty();
		String expected = "[{\"pdiReleaseDate\":\"11-Jun-2015\",\"pdiReleaseType\":\"PROD Release\",\"pdiVersion\":\"4.0\"}]";
		String newExpected = expected.replace("11-Jun-2015", RELEASE_DATE);
		assertEquals(newExpected, message.toString());
		verifyNoMoreInteractions(wizardConfMock);
	}

	@Test
	public void skipSanUpdateWizardStatus_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/skipSanUpdateWizardStatus.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("skipSanUpdateWizardStatus"));
	}

	@Test
	public void skipSanUpdateWizardStatus_nullCheck() throws Exception {

		commonController.skipSanUpdateWizardStatus(null);
		verify(wizardConfMock, times(0)).getActiveWizardStatus(anyInt());
		verify(wizardConfMock, times(0)).getActiveWizardStatus(anyInt());
		verifyNoMoreInteractions(wizardConfMock);

	}

	@Test(expected = Exception.class)
	public void skipSanUpdateWizardStatus_getActiveWizardStatus_exception()
			throws Exception {

		doThrow(new IllegalStateException()).when(wizardConfMock)
				.getActiveWizardStatus(PROJECT_ID);
		commonController.skipSanUpdateWizardStatus(PROJECT_ID);
		verify(wizardConfMock, times(1)).getActiveWizardStatus(PROJECT_ID);
		verify(wizardConfMock, times(0)).getActiveWizardStatus(PROJECT_ID);
		verifyNoMoreInteractions(wizardConfMock);

	}

	@Test(expected = Exception.class)
	public void skipSanUpdateWizardStatus_updateMainMenuStatusCompleted_exception()
			throws Exception {
		WizardStatus wizardStatus = new WizardStatus();
		wizardStatus.setId(1);
		wizardStatus.setMainMenuState(new MainMenuState());
		wizardStatus.getMainMenuState().setId(2);
		wizardStatus.setSubMenuId(1);

		when(wizardConfMock.getActiveWizardStatus(PROJECT_ID)).thenReturn(
				wizardStatus);
		doThrow(new NullPointerException()).when(wizardConfMock)
				.updateMainMenuStatusCompleted(wizardStatus);
		commonController.skipSanUpdateWizardStatus(PROJECT_ID);

		verify(wizardConfMock, times(1)).getActiveWizardStatus(PROJECT_ID);
		verify(wizardConfMock, times(1)).getActiveWizardStatus(PROJECT_ID);
		verifyNoMoreInteractions(wizardConfMock);
	}

	@Test
	public void fetchCurrentProjectDetails_methodNameCheck() throws Exception {
		ProjectDetails project = new ProjectDetails();
		when(projectDetailsServiceMock.fetchProjectDetails(PROJECT_ID))
				.thenReturn(project);
		mockMvc.perform(
				get("/fetchCurrentProjectDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchCurrentProjectDetails"));
	}

	@Test
	public void fetchCurrentProjectDetails_returnTypeCheck() throws Exception {
		ProjectDetails obj = new ProjectDetails();
		obj.setId(1);
		obj.setProjectName("TEST");
		obj.setSkipSan(false);
		String expected = "[{\"id\":1,\"ipPoolAssignmentOrder\":null,\"skipSan\":false,\"projectName\":\"TEST\"}]";
		when(projectDetailsServiceMock.fetchProjectDetails(PROJECT_ID))
				.thenReturn(obj);

		List<Object> jsonList = commonController
				.fetchCurrentProjectDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				PROJECT_ID);
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void fetchCurrentProjectDetails_nullCheck() throws Exception {
		String expected = "[]";

		List<Object> jsonList = commonController
				.fetchCurrentProjectDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(projectDetailsServiceMock, times(0)).fetchProjectDetails(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test
	public void fetchCurrentProjectDetails_returnNullCheck() throws Exception {
		String expected = "[]";
		when(projectDetailsServiceMock.fetchProjectDetails(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = commonController
				.fetchCurrentProjectDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				PROJECT_ID);
		verifyNoMoreInteractions(projectDetailsServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchCurrentProjectDetails_exception() throws Exception {
		String expected = "[]";
		doThrow(Exception.class).when(projectDetailsServiceMock)
				.fetchProjectDetails(anyInt());

		List<Object> jsonList = commonController
				.fetchCurrentProjectDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(projectDetailsServiceMock, times(1)).fetchProjectDetails(
				anyInt());
		verifyNoMoreInteractions(projectDetailsServiceMock);

	}

	@Test
	public void fetchSelectedChassisInfo_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/fetchSelectedChassisInfo.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("fetchSelectedChassisInfo"));
	}

	@Test
	public void fetchSelectedChassisInfo_retrunTypeCheck() throws Exception {
		ProjectSettings ps = new ProjectSettings();
		ps.setProjectValue("4");
		when(commonUtilServiceMock.fetchProjectSettings(anyInt(), anyString()))
				.thenReturn(ps);
		String expected = "4";
		String actual = commonController.fetchSelectedChassisInfo(PROJECT_ID);
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(1)).fetchProjectSettings(anyInt(),
				anyString());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void fetchSelectedChassisInfo_nullCheck() throws Exception {
		String expected = "0";
		String actual = commonController.fetchSelectedChassisInfo(null);
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(0)).fetchProjectSettings(anyInt(),
				anyString());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void fetchSelectedChassisInfo_returnNullCheck() throws Exception {
		String expected = "0";
		when(commonUtilServiceMock.fetchProjectSettings(anyInt(), anyString()))
				.thenReturn(null);
		String actual = commonController.fetchSelectedChassisInfo(PROJECT_ID);
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(1)).fetchProjectSettings(anyInt(),
				anyString());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchSelectedChassisInfo_exception() throws Exception {
		doThrow(Exception.class).when(commonUtilServiceMock)
				.fetchProjectSettings(anyInt(), anyString());
		commonController.fetchSelectedChassisInfo(PROJECT_ID);
		verify(commonUtilServiceMock, times(1)).fetchProjectSettings(anyInt(),
				anyString());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void updateSelectedChassisInfo_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/updateSelectedChassisInfo.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("updateSelectedChassisInfo"));
	}

	@Test
	public void updateSelectedChassisInfo_retrunTypeCheck() throws Exception {
		String jsonObj = "{\"id\":1,\"projectValue\":\"4\"}";
		doNothing().when(commonUtilServiceMock).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		String expected = "success";
		String actual = commonController.updateSelectedChassisInfo(jsonObj,
				PROJECT_ID);
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(1)).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void updateSelectedChassisInfo_nullCheck_firstParameter()
			throws Exception {
		doNothing().when(commonUtilServiceMock).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		String actual = commonController.updateSelectedChassisInfo(null,
				PROJECT_ID);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(0)).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void updateSelectedChassisInfo_nullCheck_secondParameter()
			throws Exception {
		String jsonObj = "[{\"id\":1,\"projectValue\":4}]";
		doNothing().when(commonUtilServiceMock).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		String actual = commonController.updateSelectedChassisInfo(jsonObj,
				null);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(0)).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void updateSelectedChassisInfo_nullCheck_bothParameters()
			throws Exception {
		doNothing().when(commonUtilServiceMock).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		String actual = commonController.updateSelectedChassisInfo(null, null);
		String expected = "failure";
		assertEquals(expected, actual);
		verify(commonUtilServiceMock, times(0)).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test(expected = Exception.class)
	public void updateSelectedChassisInfo_exception() throws Exception {
		String jsonObj = "[{\"id\":1,\"projectValue\":4}]";
		doThrow(Exception.class).when(commonUtilServiceMock)
				.saveOrUpdateProjectSettings(any(ProjectSettings.class),
						anyInt());
		commonController.updateSelectedChassisInfo(jsonObj, PROJECT_ID);
		verify(commonUtilServiceMock, times(1)).saveOrUpdateProjectSettings(
				any(ProjectSettings.class), anyInt());
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void fetchRolePrivileges_methodNameCheck() throws Exception {
		mockMvc.perform(
				get("/fetchRolePrivileges.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(
				handler().methodName("fetchRolePrivileges"));
	}

	@Test
	public void fetchRolePrivileges_retrunTypeCheck() throws Exception {
		List<AdminPrivilege> listAP = new ArrayList<>();
		AdminPrivilege ap = new AdminPrivilege(1);
		ap.setName("testAP");
		listAP.add(ap);
		when(commonUtilServiceMock.fetchRolePrivileges()).thenReturn(listAP);
		String expected = "[{\"id\":1,\"name\":\"testAP\"}]";
		List<Object> actual = commonController.fetchRolePrivileges();
		assertEquals(expected, actual.toString());
		verify(commonUtilServiceMock, times(1)).fetchRolePrivileges();
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test
	public void fetchRolePrivileges_retrunNullCheck() throws Exception {
		when(commonUtilServiceMock.fetchRolePrivileges()).thenReturn(null);
		String expected = "[]";
		List<Object> actual = commonController.fetchRolePrivileges();
		assertEquals(expected, actual.toString());
		verify(commonUtilServiceMock, times(1)).fetchRolePrivileges();
		verifyNoMoreInteractions(commonUtilServiceMock);
	}

	@Test(expected = Exception.class)
	public void fetchRolePrivileges_exception() throws Exception {
		doThrow(Exception.class).when(commonUtilServiceMock)
				.fetchRolePrivileges();
		commonController.fetchRolePrivileges();
		verify(commonUtilServiceMock, times(1)).fetchRolePrivileges();
		verifyNoMoreInteractions(commonUtilServiceMock);
	}
}