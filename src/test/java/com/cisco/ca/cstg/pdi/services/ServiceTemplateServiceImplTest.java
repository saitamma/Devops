package com.cisco.ca.cstg.pdi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

/**
 * The Class ServiceTemplateServiceImplTest.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class ServiceTemplateServiceImplTest implements TestConstants{
	
	/** The common util service. */
	@Autowired
	private CommonUtilServices commonUtilService;
	
	/** The hibernate session factory. */
	@Autowired
	private SessionFactory hibernateSessionFactory;
	
	/** The service template service. */
	private ServiceTemplateService serviceTemplateService;			
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		serviceTemplateService = new ServiceTemplateServiceImpl(hibernateSessionFactory, commonUtilService);
	}

	/**
	 * Fetch servers service profile template configuration.
	 */
	@Test
	public void fetchServersServiceProfileTemplateConfiguration() {
		List<ServersServiceProfileTemplate> list= serviceTemplateService.fetchServersServiceProfileTemplateConfiguration(PROJECT_ID);
		assertNotNull(list);
	}

	/**
	 * Fetch servers service profile template configuration_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchServersServiceProfileTemplateConfiguration_NullParameter() {
		serviceTemplateService.fetchServersServiceProfileTemplateConfiguration(null);
	}


	/**
	 * Save or update servers service profile template configuration.
	 */
	@Test()
	public void saveOrUpdateServersServiceProfileTemplateConfiguration() {
		List<ServersServiceProfileTemplate> list = createSSPT();
		((ServersServiceProfileTemplate) list.get(0)).setName("TEST");
		serviceTemplateService.saveOrUpdateServersServiceProfileTemplateConfiguration(list, PROJECT_ID);
		ServersServiceProfileTemplate obj = (ServersServiceProfileTemplate) serviceTemplateService.fetchServersServiceProfileTemplateConfiguration(PROJECT_ID).get(0);
		assertEquals("TEST", obj.getName());
	}

	/**
	 * Save or update servers service profile template configuration_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServiceProfileTemplateConfiguration_NullFirstParameter() {
		serviceTemplateService.saveOrUpdateServersServiceProfileTemplateConfiguration(null, PROJECT_ID);
	}
	
	/**
	 * Save or update servers service profile template configuration_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServiceProfileTemplateConfiguration_NullSecondParameter() {
		List<ServersServiceProfileTemplate> list = createSSPT();
		((ServersServiceProfileTemplate) list.get(0)).setName("TEST");
		serviceTemplateService.saveOrUpdateServersServiceProfileTemplateConfiguration(list, null);
	}
	
	
	/**
	 * Delete servers service profile template.
	 */
	@Test
	public void deleteServersServiceProfileTemplate() {
		try{
			serviceTemplateService.deleteServersServiceProfileTemplate(createSSPT());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Delete servers service profile template_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteServersServiceProfileTemplate_NullParameter() {
		serviceTemplateService.deleteServersServiceProfileTemplate(null);
	}
	
	/**
	 * Creates the sspt.
	 *
	 * @return the list
	 */
	private List<ServersServiceProfileTemplate> createSSPT() {
		ServersServiceProfileTemplate obj = new ServersServiceProfileTemplate();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); 
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		obj.setProjectDetails(projectDetails);

		List<ServersServiceProfileTemplate> list = new ArrayList<>();
		list.add(obj);
	
		return list;
	}
}
