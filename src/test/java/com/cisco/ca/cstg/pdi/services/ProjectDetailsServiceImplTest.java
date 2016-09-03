package com.cisco.ca.cstg.pdi.services;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class ProjectDetailsServiceImplTest implements TestConstants {

	private ProjectDetailsService projectDetailsService;

	@Autowired
	private SessionFactory hibernateSessionFactory;

	@Before
	public void setUp() {

		projectDetailsService = new ProjectDetailsServiceImpl(
				hibernateSessionFactory);
	}

	
	 /*@Test public void addNewProject_autoGenerateId() { 
		 ProjectDetails pd = new ProjectDetails(); 
		 pd.setId(0); pd.setProjectName("Test_Case");
		 pd.setCreatedBy(LOGIN_USER_CEC_VALUE); 
		 pd.setTheatre("Americas");
		 projectDetailsService.addNewProject(pd); 
	 }*/

	@Test
	public void getProjects() {
		assertNotNull(projectDetailsService.getProjects(LOGIN_USER_CEC_VALUE));
	}

	@Test(expected = Exception.class)
	public void addNewProject_NullParameter() {
		projectDetailsService.addNewProject(null);
	}

	@Test(expected = Exception.class)
	public void updateProjectDetails() {
		projectDetailsService.updateProjectDetails(null);
	}

	@Ignore
	@Test(expected = Exception.class)
	public void deleteProject() {
		// TODO need to fix this
		projectDetailsService.deleteProject(null);
	}

	@Test
	public void fetchProjectDetails() {
		assertNotNull(projectDetailsService.fetchProjectDetails(1));
	}
}
