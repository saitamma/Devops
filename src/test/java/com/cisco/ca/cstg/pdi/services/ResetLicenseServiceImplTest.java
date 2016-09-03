package com.cisco.ca.cstg.pdi.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.ca.cstg.pdi.pojos.License;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class ResetLicenseServiceImplTest {

	@Autowired
	private SessionFactory hibernateSessionFactory;

	private ResetLicenseServiceImpl resetLicenseServiceImpl;

	@Mock
	private CommonUtilServices commonUtilServices;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		resetLicenseServiceImpl = new ResetLicenseServiceImpl(
				hibernateSessionFactory, commonUtilServices);
	}

	@Test
	public void clearData() throws Exception {
		doNothing().when(commonUtilServices)
				.deleteRecords(ProjectDetails.class);
		doNothing().when(commonUtilServices).deleteRecords(License.class);

		resetLicenseServiceImpl.clearData();

		verify(commonUtilServices, times(1))
				.deleteRecords(ProjectDetails.class);
		verify(commonUtilServices, times(1)).deleteRecords(License.class);
	}
}
