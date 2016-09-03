package com.cisco.ca.cstg.pdi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.SanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.utils.Constants;


/**
 * The Class SANServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class SANServiceImplTest {
	
	/** The hibernate session factory. */
	@Autowired
	private SessionFactory hibernateSessionFactory;
	
	/** The common util service. */
	@Autowired
	private CommonUtilServices commonUtilService;
	
	/** The project id. */
	private int projectId = 1;
	
	/** The san service. */
	private SANServiceImpl sanService;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		sanService = new SANServiceImpl(hibernateSessionFactory, commonUtilService);
	}
	
	/**
	 * Fetch san vsan configuration.
	 */
	@Test
	public void fetchSanVsanConfiguration() {
		List<SanVsan> sanVsanList = sanService.fetchSanVsanConfiguration(projectId);
		assertNotNull(sanVsanList);
	}
	
	/**
	 * Fetch san vsan configuration_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanVsanConfiguration_NullParameter() {
		sanService.fetchSanVsanConfiguration(null);
	}

	/**
	 * Save or update san vsan configuration.
	 */
	@Test
	public void saveOrUpdateSanVsanConfiguration() {
		try{
			List<SanVsan> sanVsanList = createSanVsan();
			((SanVsan) sanVsanList.get(0)).setFiId("Test");
			sanService.saveOrUpdateSanVsanConfiguration(sanVsanList, projectId);
			SanVsan sanVsan = (SanVsan) sanService.findById(SanVsan.class, ((SanVsan) sanVsanList.get(0)).getId());
			assertEquals("Test", sanVsan.getFiId());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	/**
	 * Save or update san vsan configuration_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanVsanConfiguration_NullFirstParameter() {
		sanService.saveOrUpdateSanVsanConfiguration(null, projectId);
	}
	
	/**
	 * Save or update san vsan configuration_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanVsanConfiguration_NullSecondParameter() {
		List<SanVsan> sanVsanList = createSanVsan();
		((SanVsan) sanVsanList.get(0)).setFiId("Test");
		sanService.saveOrUpdateSanVsanConfiguration(sanVsanList, null);
	}


	/**
	 * Delete san vsan.
	 */
	@Test
	public void deleteSanVsan() {
		try{
			sanService.deleteSanVsan(createSanVsan());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Delete san vsan_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanVsan_NullParameter() {
		sanService.deleteSanVsan(null);
	}


	/**
	 * Fetch san wwnn configuration.
	 */
	@Test
	public void fetchSanWwnnConfiguration() {
		List<SanWwnn> sanWwnnList = sanService.fetchSanWwnnConfiguration(projectId);
		assertNotNull(sanWwnnList);
	}
	
	/**
	 * Fetch san wwnn configuration_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanWwnnConfiguration_NullParameter() {
		sanService.fetchSanWwnnConfiguration(null);
	}
	
	/**
	 * Save or update san wwnn configuration.
	 */
	@Test
	public void saveOrUpdateSanWwnnConfiguration() {
		List<SanWwnn> sanWwnnList = createSanWwnn();
		((SanWwnn) sanWwnnList.get(0)).setWwnnName("Test");
		sanService.saveOrUpdateSanWwnnConfiguration(sanWwnnList, projectId);
		SanWwnn sanWwnn = (SanWwnn) sanService.findById(SanWwnn.class, ((SanWwnn) sanWwnnList.get(0)).getId());
		assertEquals("Test", sanWwnn.getWwnnName());
	}

	/**
	 * Save or update san wwnn configuration_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanWwnnConfiguration_NullFirstParameter() {
		sanService.saveOrUpdateSanWwnnConfiguration(null, projectId);
	}
	
	/**
	 * Save or update san wwnn configuration_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanWwnnConfiguration_NullSecondParameter() {
		List<SanWwnn> sanWwnnList = createSanWwnn();
		((SanWwnn) sanWwnnList.get(0)).setWwnnName("Test");
		sanService.saveOrUpdateSanWwnnConfiguration(sanWwnnList, null);
	}

	/**
	 * Delete san wwnn.
	 */
	@Test
	public void deleteSanWwnn() {
		try{
			sanService.deleteSanWwnn(createSanWwnn());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Delete san wwnn_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanWwnn_NullParameter() {
		sanService.deleteSanWwnn(null);
	}

	/**
	 * Fetch san wwpn configuration.
	 */
	@Test
	public void fetchSanWwpnConfiguration() {
		List<SanWwpn> sanWwnnList = sanService.fetchSanWwpnConfiguration(projectId);
		assertNotNull(sanWwnnList);
	}

	/**
	 * Fetch san wwpn configuration_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanWwpnConfiguration_NullParameter() {
		sanService.fetchSanWwpnConfiguration(null);
	}

	/**
	 * Save or update san wwpn configuration.
	 */
	@Test
	public void saveOrUpdateSanWwpnConfiguration() {
		List<SanWwpn> sanWwpnList = createSanWwpn();
		((SanWwpn) sanWwpnList.get(0)).setWwpnName("Test");
		sanService.saveOrUpdateSanWwpnConfiguration(sanWwpnList, projectId);
		SanWwpn sanWwpn = (SanWwpn) sanService.findById(SanWwpn.class, ((SanWwpn) sanWwpnList.get(0)).getId());
		assertEquals("Test", sanWwpn.getWwpnName());
	}

	/**
	 * Save or update san wwpn configuration_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanWwpnConfiguration_NullFirstParameter() {
		sanService.saveOrUpdateSanWwpnConfiguration(null, projectId);
	}
	
	/**
	 * Save or update san wwpn configuration_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanWwpnConfiguration_NullSecondParameter() {
		List<SanWwpn> sanWwpnList = createSanWwpn();
		((SanWwpn) sanWwpnList.get(0)).setWwpnName("Test");
		sanService.saveOrUpdateSanWwpnConfiguration(sanWwpnList, null);
	}

	/**
	 * Delete san wwpn.
	 */
	@Test
	public void deleteSanWwpn() {
		try{
			sanService.deleteSanWwpn(createSanWwpn());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Delete san wwpn_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanWwpn_NullParameter() {
		sanService.deleteSanWwpn(null);
	}

	/**
	 * Fetch san vhba template configuration.
	 */
	@Test
	public void fetchSanVhbaTemplateConfiguration() {
		List<SanVhbaTemplate> sanVhbaList = sanService.fetchSanVhbaTemplateConfiguration(projectId);
		assertNotNull(sanVhbaList);
	}
	
	/**
	 * Fetch san vhba template configuration_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanVhbaTemplateConfiguration_NullParameter() {
		sanService.fetchSanVhbaTemplateConfiguration(null);
	}

	/**
	 * Save or update san vhba template configuration.
	 */
	@Test
	public void saveOrUpdateSanVhbaTemplateConfiguration() {
		List<SanVhbaTemplate> sanVhbaList = createSanVhbaT();
		((SanVhbaTemplate) sanVhbaList.get(0)).setVhbaName("Test");
		sanService.saveOrUpdateSanVhbaTemplateConfiguration(sanVhbaList, projectId);
		SanVhbaTemplate sanVhba = (SanVhbaTemplate) sanService.findById(SanVhbaTemplate.class, ((SanVhbaTemplate) sanVhbaList.get(0)).getId());
		assertEquals("Test", sanVhba.getVhbaName());
	}

	/**
	 * Save or update san vhba template configuration_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanVhbaTemplateConfiguration_NullFirstParameter() {
		sanService.saveOrUpdateSanVhbaTemplateConfiguration(null, projectId);
	}
	
	/**
	 * Save or update san vhba template configuration_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanVhbaTemplateConfiguration_NullSecondParameter() {
		List<SanVhbaTemplate> sanVhbaList = createSanVhbaT();
		((SanVhbaTemplate) sanVhbaList.get(0)).setVhbaName("Test");
		sanService.saveOrUpdateSanVhbaTemplateConfiguration(sanVhbaList, null);
	}

	/**
	 * Delete san vhba template.
	 */
	/*@Test
	public void deleteSanVhbaTemplate() {
		try{
			sanService.deleteSanVhbaTemplate(createSanVhbaT());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}*/
	
	/**
	 * Delete san vhba template_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanVhbaTemplate_NullParameter() {
		sanService.deleteSanVhbaTemplate(null);
	}
	
	/**
	 * Delete all san configuration details.
	 */
	@Test
	public void deleteAllSANConfigurationDetails() {
		try{
			sanService.deleteAllSANConfigurationDetails(projectId);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Delete all san configuration details_ null parameter.
	 */
	@Test
	public void deleteAllSANConfigurationDetails_NullParameter() {
		try{
		sanService.deleteAllSANConfigurationDetails(null);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * Fetch san adapter policies.
	 */
	@Test
	public void fetchSanAdapterPolicies() {
		assertNotNull(sanService.fetchSanAdapterPolicies(projectId));
	}

	/**
	 * Fetch san adapter policies_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanAdapterPolicies_NullParameter() {
		sanService.fetchSanAdapterPolicies(null);
	}
	
	/**
	 * Save or update adapter policies.
	 */
	@Test
	public void saveOrUpdateAdapterPolicies() {
		try{
			List<SanAdapterPolicies> sanAPList = createSanAP();
			SanAdapterPolicies sanAP = ((SanAdapterPolicies) sanAPList.get(0));
			sanAP.setName("Test");
			sanService.saveOrUpdateAdapterPolicies(sanAPList, projectId);
			sanAP = (SanAdapterPolicies) sanService.findById(SanAdapterPolicies.class, sanAP.getId());
			assertEquals("Test", sanAP.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	/**
	 * Save or update adapter policies_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateAdapterPolicies_NullFirstParameter() {
			sanService.saveOrUpdateAdapterPolicies(null, projectId);
	}
	
	/**
	 * Save or update adapter policies_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateAdapterPolicies_NullSecondParameter() {
			sanService.saveOrUpdateAdapterPolicies(createSanAP(), null);
	}
	
	/**
	 * Delete san adapter policies.
	 */
	@Test
	public void deleteSanAdapterPolicies() {
		try{
			List<SanAdapterPolicies> sanAPList = createSanAP();
			SanAdapterPolicies sanAP = ((SanAdapterPolicies) sanAPList.get(0));
			sanAP.setName("Test");
			sanService.saveOrUpdateAdapterPolicies(sanAPList, projectId);
			sanService.deleteSanAdapterPolicies(sanAPList);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	/**
	 * Delete san adapter policies_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanAdapterPolicies_NullParameter() {
		sanService.deleteSanAdapterPolicies(null);
	}
	
	
	/**
	 * Fetch san vhba detail.
	 */
	@Test
	public void fetchSanVhbaDetail() {
		assertNotNull(sanService.fetchSanVhbaDetail(projectId));
	}

	/**
	 * Fetch san vhba detail_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanVhbaDetail_NullParameter() {
		sanService.fetchSanVhbaDetail(null);
	}
	
	/**
	 * Save or update san vhba.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateSanVhba() {
		try{
			List<SanVhba> sanVhbaList = createSVHBA();
			SanVhba sanVhba = ((SanVhba) sanVhbaList.get(0));
			sanVhba.setName("Test");
			Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
					commonUtilService.getProjectDetailsObject(projectId));
			List<SanVhbaTemplate> sanVhbaTemplatesList = (List<SanVhbaTemplate>)(List<?>)sanService.listAll(SanVhbaTemplate.class, criteria);
			if (sanVhbaTemplatesList != null && !sanVhbaTemplatesList.isEmpty()) {				
				sanVhbaList.get(0).setSanVhbaTemplate(new SanVhbaTemplate(sanVhbaTemplatesList.get(0).getId()));
			}
			sanService.saveOrUpdateSanVhba(sanVhbaList, projectId);
			sanVhba = (SanVhba) sanService.findById(SanVhba.class, sanVhba.getId());
			assertEquals("Test", sanVhba.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	/**
	 * Save or update san vhba_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanVhba_NullFirstParameter() {
			sanService.saveOrUpdateSanVhba(null, projectId);
	}
	
	/**
	 * Save or update san vhba_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanVhba_NullSecondParameter() {
			sanService.saveOrUpdateSanVhba(createSVHBA(), null);
	}
	
	/**
	 * Delete san vhba.
	 */
	/*@Test
	public void deleteSanVhba() {
		try{
			List<SanVhba> sanVhbaList = createSVHBA();
			SanVhba sanVhba = ((SanVhba) sanVhbaList.get(0));
			sanVhba.setName("Test");
			sanService.saveOrUpdateSanVhba(sanVhbaList, projectId);
			sanService.deleteSanVhba(sanVhbaList);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
*/
	/**
	 * Delete san vhba_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanVhba_NullParameter() {
		sanService.deleteSanVhba(null);
	}

	/**
	 * Fetch san connectivity policy detail.
	 */
	@Test
	public void fetchSanConnectivityPolicyDetail() {
		assertNotNull(sanService.fetchSanConnectivityPolicyDetail(projectId));
	}

	/**
	 * Fetch san connectivity policy detail_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void fetchSanConnectivityPolicyDetail_NullParameter() {
		sanService.fetchSanConnectivityPolicyDetail(null);
	}
	
	/**
	 * Save or update san connectivity policy.
	 */
	@Test
	public void saveOrUpdateSanConnectivityPolicy() {
		try{
			List<SanConnectivityPolicy> sanCPList = createSCP();
			SanConnectivityPolicy scp = ((SanConnectivityPolicy) sanCPList.get(0));
			scp.setName("Test");
			sanService.saveOrUpdateSanConnectivityPolicy(sanCPList, projectId);
			scp = (SanConnectivityPolicy) sanService.findById(SanConnectivityPolicy.class, scp.getId());
			assertEquals("Test", scp.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	/**
	 * Save or update san connectivity policy_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanConnectivityPolicy_NullFirstParameter() {
			sanService.saveOrUpdateSanConnectivityPolicy(null, projectId);
	}
	
	/**
	 * Save or update san connectivity policy_ null second parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanConnectivityPolicy_NullSecondParameter() {
			sanService.saveOrUpdateSanConnectivityPolicy(createSCP(), null);
	}
	
	/**
	 * Delete san connectivity policy.
	 */
	@Test
	public void deleteSanConnectivityPolicy() {
		try{
			List<SanConnectivityPolicy> sanCPList = createSCP();
			SanConnectivityPolicy scp = ((SanConnectivityPolicy) sanCPList.get(0));
			scp.setName("Test");
			sanService.saveOrUpdateSanConnectivityPolicy(sanCPList, projectId);
			sanService.deleteSanConnectivityPolicy(sanCPList);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	/**
	 * Delete san connectivity policy_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanConnectivityPolicy_NullParameter() {
		sanService.deleteSanConnectivityPolicy(null);
	}

	/**
	 * Fetch san scp vhba mappings.
	 */
	@Test
	public void fetchSanScpVhbaMappings() {
		assertNotNull(sanService.fetchSanScpVhbaMappings(projectId));
	}

	/**
	 * Fetch san scp vhba mappings_ null parameter.
	 */
	@Test
	public void fetchSanScpVhbaMappings_NullParameter() {
		assertEquals(0, sanService.fetchSanScpVhbaMappings(null).size());
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateSanScpVhbaMappings() {
		try{
			List<SanScpVhbaMapping> sanCpVhbaMList = createSSVM();
			Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
					commonUtilService.getProjectDetailsObject(projectId));
			List<SanVhba> vhbaList = (List<SanVhba>)(List<?>)sanService.listAll(SanVhba.class, criteria);
			if (vhbaList != null && !vhbaList.isEmpty()) {				
				sanCpVhbaMList.get(0).setSanVhba(new SanVhba(vhbaList.get(0).getId()));
			}
			SanScpVhbaMapping sanCpVhbaM = sanCpVhbaMList.get(0);
			sanService.saveOrUpdateSanScpVhbaMappings(sanCpVhbaMList, projectId);
			sanCpVhbaM = (SanScpVhbaMapping) sanService.findById(SanScpVhbaMapping.class, sanCpVhbaM.getId());
			assertNotNull(sanCpVhbaM);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	/**
	 * Save or update san scp vhba mappings_ null first parameter.
	 */
	@Test(expected = Exception.class)
	public void saveOrUpdateSanScpVhbaMappings_NullFirstParameter() {
			sanService.saveOrUpdateSanScpVhbaMappings(null, projectId);
	}
	
	
	/*@Test(expected = Exception.class)
	public void saveOrUpdateSanScpVhbaMappings_NullSecondParameter() {
			sanService.saveOrUpdateSanScpVhbaMappings(createSSVM(), null);
	} */
	
	
	/**
	 * Delete san scp vhba mappings.
	 */
	/*@Test
	public void deleteSanScpVhbaMappings() {
		try{
			List<SanScpVhbaMapping> sanCpVhbaMList = createSSVM();
			List<SanScpVhbaMapping> list = new ArrayList<SanScpVhbaMapping>();
			SanScpVhbaMapping sanCpVhbaM = sanCpVhbaMList.get(0);
			list.add(sanCpVhbaM);
			sanService.saveOrUpdateSanScpVhbaMappings(sanCpVhbaMList, 1);
			sanService.deleteSanScpVhbaMappings(list);
			assertNotNull(sanCpVhbaM);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}*/

	/**
	 * Delete san scp vhba mappings_ null parameter.
	 */
	@Test(expected = Exception.class)
	public void deleteSanScpVhbaMappings_NullParameter() {
		sanService.deleteSanScpVhbaMappings(null);
	}

	/**
	 * Creates the san vsan.
	 *
	 * @return the list
	 */
	private List<SanVsan> createSanVsan() {
		List<SanVsan> list = new ArrayList<>();
		SanVsan sanVsan = new SanVsan();
		sanVsan.setId(0);
		sanVsan.setVsanName("Test");
		ProjectDetails pd = new ProjectDetails();
		pd.setId(1);
		sanVsan.setProjectDetails(pd);
		list.add(sanVsan);
		return list;
	}
	
	/**
	 * Creates the san wwnn.
	 *
	 * @return the list
	 */
	private List<SanWwnn> createSanWwnn() {
		List<SanWwnn> list = new ArrayList<>();
		SanWwnn sanWwnn = new SanWwnn();
		sanWwnn.setId(0);
		sanWwnn.setDescription("Test");
		Organizations org = new Organizations();
		org.setId(1);
		sanWwnn.setOrganizations(org);
		ProjectDetails pd = new ProjectDetails();
		pd.setId(1);
		sanWwnn.setProjectDetails(pd);
		list.add(sanWwnn);
		return list;
	}
	
	/**
	 * Creates the san wwpn.
	 *
	 * @return the list
	 */
	private List<SanWwpn> createSanWwpn() {
		List<SanWwpn> list = new ArrayList<>();
		SanWwpn sanWwpn = new SanWwpn();
		sanWwpn.setId(0);
		sanWwpn.setDescription("Test");
		Organizations org = new Organizations();
		org.setId(1);
		sanWwpn.setOrganizations(org);
		ProjectDetails pd = new ProjectDetails();
		pd.setId(1);
		sanWwpn.setProjectDetails(pd);
		list.add(sanWwpn);
		return list;
	}
	
	/**
	 * Creates the san vhba t.
	 *
	 * @return the list
	 */
	private List<SanVhbaTemplate> createSanVhbaT() {
		List<SanVhbaTemplate> list = new ArrayList<>();
		SanVhbaTemplate sanVhba = new SanVhbaTemplate();
		sanVhba.setId(0);
		sanVhba.setDescription("Test"); Organizations org = new Organizations();
		org.setId(1); sanVhba.setOrganizations(org);
		ProjectDetails pd = new ProjectDetails(); pd.setId(1);
		sanVhba.setProjectDetails(pd); list.add(sanVhba);
		return list;
	}
	
	/**
	 * Creates the san ap.
	 *
	 * @return the list
	 */
	private List<SanAdapterPolicies> createSanAP() {
		List<SanAdapterPolicies> list = new ArrayList<>();
		SanAdapterPolicies sanAP = new SanAdapterPolicies(0);
		sanAP.setDescription("desc"); sanAP.setFcpErrorRecovery("enabled"); sanAP.setFlogiRetries((int) 123456789L);
		sanAP.setFlogiTimeout(60); sanAP.setInterruptMode("MSI x"); sanAP.setIoThrottleCount(16); sanAP.setLinkDownTimeout(30000);
		sanAP.setLunsPerTarget(256); sanAP.setOrganizations(new Organizations(1)); sanAP.setPlogiRetries(8);
		sanAP.setPlogiTimeout(8); sanAP.setPortDownIoRetry(30000); sanAP.setPortDownTimeout(30); 
		list.add(sanAP);
		return list;
	}
	
	/**
	 * Creates the svhba.
	 *
	 * @return the list
	 */
	private List<SanVhba> createSVHBA() {
		SanVhba sanVhba = new SanVhba(0);
		sanVhba.setSanAdapterPolicies(new SanAdapterPolicies(1)); 
		sanVhba.setSanVhbaTemplate(new SanVhbaTemplate(1));
		List<SanVhba> sanVhbaList = new ArrayList<>();
		sanVhbaList.add(sanVhba);
		
		return sanVhbaList;
	}
	
	/**
	 * Creates the scp.
	 *
	 * @return the list
	 */
	private List<SanConnectivityPolicy> createSCP() {
		SanConnectivityPolicy scp = new SanConnectivityPolicy(0);
		List<SanScpVhbaMapping> list = new ArrayList<>();
		SanScpVhbaMapping scvm = new SanScpVhbaMapping(); scvm.setId(1);
		list.add(scvm); scp.setName("test"); scp.setDescription("desc"); 
		scp.setOrganizations(new Organizations(1)); scp.setSanScpVhbaMappings(list);
		List<SanConnectivityPolicy> sanCPList = new ArrayList<>(); sanCPList.add(scp);
		return sanCPList;
	}
	
	/**
	 * Creates the ssvm.
	 *
	 * @return the list
	 */
	private List<SanScpVhbaMapping> createSSVM() {
		SanScpVhbaMapping ssvm = new SanScpVhbaMapping(0);
		ssvm.setSanConnectivityPolicy(new SanConnectivityPolicy(1));
		ssvm.setSanVhba(new SanVhba(1));
		List<SanScpVhbaMapping> list = new ArrayList<SanScpVhbaMapping>();
		list.add(ssvm);
		return list;
	}	
}