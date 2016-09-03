package com.cisco.ca.cstg.pdi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * The Class WizardConfigurationServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class WizardConfigurationServiceImplTest implements TestConstants{

	/** The wizard configuration serviece. */
	private WizardConfigurationService wizardConfigurationServiece;
	
	/** The common util services. */
	@Autowired
	private CommonUtilServices commonUtilServices;
	
	/** The hibernate session factory. */
	@Autowired
	private SessionFactory hibernateSessionFactory;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		
		wizardConfigurationServiece = new WizardConfigurationServiceImpl(hibernateSessionFactory, commonUtilServices);
	}
	
	/**
	 * Creates the wizard status.
	 */
	@Test
	public void createWizardStatus() {
		wizardConfigurationServiece.createWizardStatus(PROJECT_ID);
		assertNotNull(wizardConfigurationServiece.fetchWizardStatus(PROJECT_ID));
	}
	
	/*@Test(expected = Exception.class)
	public void createWizardStatus_NullParameter() {
		wizardConfigurationServiece.createWizardStatus(null);
	}*/
	
	
	/**
	 * Fetch wizard status.
	 */
	@Test
	public void fetchWizardStatus() {
		assertNotNull(wizardConfigurationServiece.fetchWizardStatus(PROJECT_ID));
	}
	
	/**
	 * Fetch wizard status_ null parameter.
	 */
	@Test
	public void fetchWizardStatus_NullParameter() {
		List<Object> wizardList = wizardConfigurationServiece.fetchWizardStatus(null);
		assertTrue(wizardList.size() == 0);
	}
	
	/**
	 * Update wizard status.
	 */
	/*@Test
	public void updateWizardStatus() {
		String str = "{\"projectId\": "+ projectId +", \"activeStateMenuIndex\": 1 , \"activeStateSubMenuIndex\": -1}";
		
		try {
			wizardConfigurationServiece.updateWizardStatus(str);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}*/
	
	/**
	 * Update wizard status_ null parameter.
	 *
	 * @throws JsonProcessingException the json processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test(expected = Exception.class)
	public void updateWizardStatus_NullParameter() throws JsonProcessingException, IOException {
			wizardConfigurationServiece.updateWizardStatus(null);
	}
	

	/**
	 * Gets the active wizard status.
	 *
	 * @return the active wizard status
	 */
	/*@Test
	public void getActiveWizardStatus() {
		WizardStatus wizardStatus = null;
		try {
			wizardStatus = wizardConfigurationServiece.getActiveWizardStatus(projectId);
		} catch(Exception e) {
			fail("should not throw exception");
		}
		assertEquals(true, wizardStatus.getIsActive());		
	}*/
	
	/**
	 * Gets the active wizard status_ null parameter.
	 *
	 * @return the active wizard status_ null parameter
	 */
	@Test(expected = Exception.class)
	public void getActiveWizardStatus_NullParameter() {
			wizardConfigurationServiece.getActiveWizardStatus(null);
	}
	
	
	/**
	 * Gets the completed main menu wizard status.
	 *
	 * @return the completed main menu wizard status
	 */
	@Test
	public void getCompletedMainMenuWizardStatus() {
		
		assertNotNull(wizardConfigurationServiece.getCompletedMainMenuWizardStatus(PROJECT_ID));		
	}
	
	/**
	 * Gets the completed main menu wizard status_ null parameter.
	 *
	 * @return the completed main menu wizard status_ null parameter
	 */
	@Test//(expected = Exception.class)
	public void getCompletedMainMenuWizardStatus_NullParameter() {
		
	assertEquals(0, wizardConfigurationServiece.getCompletedMainMenuWizardStatus(null).size());		
	}
}