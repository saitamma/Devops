package com.cisco.ca.cstg.pdi.services;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class InfrastructureServiceImplTest implements TestConstants {

	@Autowired
	private CommonUtilServices commonUtilServices;

	@Autowired
	private SessionFactory hibernateSessionFactory;

	private InfrastructureServiceImpl infrastructureService;

	@Before
	public void setUp() {

		infrastructureService = new InfrastructureServiceImpl(
				hibernateSessionFactory, commonUtilServices);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void fetchInfrastructureDetails() {
		List<Infrastructure> infraList = infrastructureService
				.fetchInfrastructureDetails(PROJECT_ID);
		assertNotNull(infraList);
		assertTrue(infraList.size() >= 0);
	}

	@Test(expected = Exception.class)
	public void fetchInfrastructureDetails_NullParameter() {
		infrastructureService.fetchInfrastructureDetails(null);
	}

	@Test
	public void saveInfrastructureDetails() {
		try {
			infrastructureService.saveInfrastructureDetails(
					createInfrastucture(), PROJECT_ID);
		} catch (Exception e) {
			fail("should not throw any error");
		}
	}

	@Test(expected = Exception.class)
	public void saveInfrastructureDetails_NullFirstParameter() {
		infrastructureService.saveInfrastructureDetails(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveInfrastructureDetails_NullSecondParameter() {
		infrastructureService.saveInfrastructureDetails(createInfrastucture(),
				null);
	}

	private Infrastructure createInfrastucture() {
		Infrastructure infrastructure = new Infrastructure();
		ServerModel serverModel = new ServerModel();
		serverModel.setId(1);
		ProjectDetails pd = new ProjectDetails();
		pd.setId(PROJECT_ID);
		infrastructure.setProjectDetails(pd);
		infrastructure.setServerModel(serverModel);

		return infrastructure;
	}

}
