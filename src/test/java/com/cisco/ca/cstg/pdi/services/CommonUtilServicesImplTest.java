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
public class CommonUtilServicesImplTest implements TestConstants {

	CommonUtilServices commonUtilServices;

	@Autowired
	private SessionFactory hibernateSessionFactory;

	@Before
	public void setUp() {
		commonUtilServices = new CommonUtilServicesImpl(hibernateSessionFactory);
	}

	@Test
	public void getProjectDetailsObject() {
		assertNotNull(commonUtilServices.getProjectDetailsObject(PROJECT_ID));
	}

	@Ignore
	@Test(expected = Exception.class)
	public void getProjectDetailsObject_NullParameter() {
		commonUtilServices.getProjectDetailsObject(null);
	}
}