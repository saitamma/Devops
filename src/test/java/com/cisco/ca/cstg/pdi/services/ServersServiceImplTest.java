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
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySanTarget;
import com.cisco.ca.cstg.pdi.pojos.ServersHostFirmware;
import com.cisco.ca.cstg.pdi.pojos.ServersLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolQualifier;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.utils.Constants;


/**
 * 
 * @author tapdash Created On : June 13, 2014
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class ServersServiceImplTest {

	@Autowired
	private CommonUtilServices commonUtilService;
	
	@Autowired
	private SessionFactory hibernateSessionFactory;
	
	private ServersServiceImpl srverService;
	private final int projectId = 1;
	private final int bootPolicySanId = 1;
	
	
	@Before
	public void setUp(){
		srverService = new ServersServiceImpl(hibernateSessionFactory, commonUtilService);
	}		

	@Test(expected = Exception.class)
	public void fetchServersUuidPoolConfiguration_NullParameter() {
		srverService.fetchServersUuidPoolConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersUuidPoolConfiguration() {
		try{
			List<ServersUuidPool> list = createSUP();
			list.get(0).setDescription("TEST");
			srverService.saveOrUpdateServersUuidPoolConfiguration(list, projectId);
			ServersUuidPool obj = (ServersUuidPool) srverService.findById(ServersUuidPool.class, ((ServersUuidPool) list.get(0)).getId());
			assertEquals("TEST", obj.getDescription());
		} catch(Exception e) {
			fail("should not throw exception");
		}
		
	}
	
	@Test
	public void fetchServersUuidPoolConfiguration() {
		List<ServersUuidPool> list= srverService.fetchServersUuidPoolConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersUuidPoolConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersUuidPoolConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersUuidPoolConfiguration_NullSecondParameter() {
		List<ServersUuidPool> list = createSUP();
		list.get(0).setDescription("TEST");
		srverService.saveOrUpdateServersUuidPoolConfiguration(list, null);
	}
	
	
	@Test
	public void deleteServersUuidPool() {
		try{
			srverService.deleteServersUuidPool(createSUP());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = Exception.class)
	public void deleteServersUuidPool_NullParameter() {
		srverService.deleteServersUuidPool(null);
	}

	@Test(expected = Exception.class)
	public void fetchServersBootPolicyConfiguration_NullParameter() {
		srverService.fetchServersBootPolicyConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersBootPolicyConfiguration() {
		try{
			List<ServersBootPolicy> list = createSBP();
			((ServersBootPolicy) list.get(0)).setDescription("TEST");
			srverService.saveOrUpdateServersBootPolicyConfiguration(list, projectId);
			ServersBootPolicy obj = (ServersBootPolicy) srverService.findById(ServersBootPolicy.class, ((ServersBootPolicy) list.get(0)).getId());
			assertEquals("TEST", obj.getDescription());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchServersBootPolicyConfiguration() {
		List<ServersBootPolicy> list= srverService.fetchServersBootPolicyConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicyConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersBootPolicyConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicyConfiguration_NullSecondParameter() {
		List<ServersBootPolicy> list = createSBP();
		list.get(0).setDescription("TEST");
		srverService.saveOrUpdateServersBootPolicyConfiguration(list, null);
	}
	
	
	@Test
	public void deleteServersBootPolicy() {
		try{
			srverService.deleteServersBootPolicy(createSBP());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = Exception.class)
	public void deleteServersBootPolicy_NullParameter() {
		srverService.deleteServersBootPolicy(null);
	}

	@Test(expected = Exception.class)
	public void fetchServersBootPolicyLanConfiguration_NullParameter() {
		srverService.fetchServersBootPolicyLanConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersBootPolicyLanConfiguration() {
		List<ServersBootPolicyLan> list = createSBPL();
		((ServersBootPolicyLan) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersBootPolicyLanConfiguration(list, projectId);
		ServersBootPolicyLan obj = (ServersBootPolicyLan) srverService.fetchServersBootPolicyLanConfiguration(projectId).get(0);
		assertEquals("TEST", obj.getName());
	}
	
	@Test
	public void fetchServersBootPolicyLanConfiguration() {
		List<ServersBootPolicyLan> list= srverService.fetchServersBootPolicyLanConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicyLanConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersBootPolicyLanConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicyLanConfiguration_NullSecondParameter() {
		List<ServersBootPolicyLan> list = createSBPL();
		list.get(0).setName("TEST");
		srverService.saveOrUpdateServersBootPolicyLanConfiguration(list, null);
	}
	
	
	/*@Test
	public void deleteServersBootPolicyLan() {
		try{
			srverService.deleteServersBootPolicyLan(createSBPL());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}*/
	
	@Test(expected = Exception.class)
	public void deleteServersBootPolicyLan_NullParameter() {
		srverService.deleteServersBootPolicyLan(null);
	}	

	@Test(expected = Exception.class)
	public void fetchServersBootPolicySanConfiguration_NullParameter() {
		srverService.fetchServersBootPolicySanConfiguration(null);
	}


	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateServersBootPolicySanConfiguration() {
		List<ServersBootPolicySan> list = createSBPS();
		list.get(0).setName("TEST");		
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		List<SanVhba> vhbaList = (List<SanVhba>)(List<?>)srverService.listAll(SanVhba.class, criteria);
		if (vhbaList != null && !vhbaList.isEmpty()) {				
			list.get(0).setSanVhba(new SanVhba(vhbaList.get(0).getId()));
		}
		//list.get(0).setServersBootPolicy(new ServersBootPolicy(1));
		srverService.saveOrUpdateServersBootPolicySanConfiguration(list, projectId);
		ServersBootPolicySan obj = (ServersBootPolicySan) srverService.fetchServersBootPolicySanConfiguration(projectId).get(0);
		assertEquals("TEST", obj.getName());
	}
	
	@Test
	public void fetchServersBootPolicySanConfiguration() {
		List<ServersBootPolicySan> list= srverService.fetchServersBootPolicySanConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicySanConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersBootPolicySanConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicySanConfiguration_NullSecondParameter() {
		List<ServersBootPolicySan> list = createSBPS();
		((ServersBootPolicySan) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersBootPolicySanConfiguration(list, null);
	}
	
	
	/*@Test
	public void deleteServersBootPolicySan() {
		try{
			srverService.deleteServersBootPolicySan(createSBPS());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}*/
	
	@Test(expected = Exception.class)
	public void deleteServersBootPolicySan_NullParameter() {
		srverService.deleteServersBootPolicySan(null);
	}	

	/*@Test(expected = Exception.class)
	public void fetchServersBootPolicySanTargetConfiguration_NullParameter() {
		srverService.fetchServersBootPolicySanTargetConfiguration(null);
	}*/
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveOrUpdateServersBootPolicySanTargetConfiguration() {
		List<ServersBootPolicySanTarget> list = createSBPST();
		list.get(0).setName("TEST");
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		List<ServersBootPolicySan> serversBootPolicySansList = (List<ServersBootPolicySan>)(List<?>)srverService.listAll(ServersBootPolicySan.class, criteria);
		if (serversBootPolicySansList != null && !serversBootPolicySansList.isEmpty()) {
			list.get(0).setServersBootPolicySan(new ServersBootPolicySan(serversBootPolicySansList.get(0).getId()));
		}
		srverService.saveOrUpdateServersBootPolicySanTargetConfiguration(list);
		ServersBootPolicySanTarget obj = (ServersBootPolicySanTarget) srverService.fetchServersBootPolicySanTargetConfiguration(bootPolicySanId).get(0);
		assertEquals("TEST", obj.getName());
	}
	
	/*@Test
	public void fetchServersBootPolicySanTargetConfiguration() {
		List<ServersBootPolicySanTarget> list= srverService.fetchServersBootPolicySanTargetConfiguration(anyInt());
		assertNotNull(list);
	}*/
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicySanTargetConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersBootPolicySanTargetConfiguration(null);
	}
	
	/*@Test(expected = Exception.class)
	public void saveOrUpdateServersBootPolicySanTargetConfiguration_NullSecondParameter() {
		List<ServersBootPolicySanTarget> list = createSBPST();
		((ServersBootPolicySanTarget) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersBootPolicySanTargetConfiguration(list);
	}*/
	
	/*@Test
	public void deleteServersBootPolicySanTarget() {
		try{
			srverService.deleteServersBootPolicySanTarget(createSBPST());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}*/
	
	@Test(expected = Exception.class)
	public void deleteServersBootPolicySanTarget_NullParameter() {
		srverService.deleteServersBootPolicySanTarget(null);
	}		

	@Test(expected = Exception.class)
	public void fetchServersLocalDiscConfiguration_NullParameter() {
		srverService.fetchServersLocalDiscConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersLocalDiscConfiguration() {
		try{
			List<ServersLocalDisc> list = createSLD();
			((ServersLocalDisc) list.get(0)).setName("TEST");
			srverService.saveOrUpdateServersLocalDiscConfiguration(list, projectId);
			ServersLocalDisc obj = (ServersLocalDisc) srverService.findById(ServersLocalDisc.class, ((ServersLocalDisc) list.get(0)).getId());
			assertEquals("TEST", obj.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchServersLocalDiscConfiguration() {
		List<ServersLocalDisc> list= srverService.fetchServersLocalDiscConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersLocalDiscConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersLocalDiscConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersLocalDiscConfiguration_NullSecondParameter() {
		List<ServersLocalDisc> list = createSLD();
		((ServersLocalDisc) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersLocalDiscConfiguration(list, null);
	}
	
	
	@Test
	public void deleteServersLocalDisc() {
		try{
			srverService.deleteServersLocalDisc(createSLD());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = Exception.class)
	public void deleteServersLocalDisc_NullParameter() {
		srverService.deleteServersLocalDisc(null);
	}		

	@Test(expected = Exception.class)
	public void fetchServersServerPoolConfiguration_NullParameter() {
		srverService.fetchServersServerPoolConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersServerPoolConfiguration() {
		try{
			List<ServersServerPool> list = createSSP();
			((ServersServerPool) list.get(0)).setName("TEST");
			srverService.saveOrUpdateServersServerPoolConfiguration(list, projectId);
			ServersServerPool obj = (ServersServerPool) srverService.findById(ServersServerPool.class, ((ServersServerPool) list.get(0)).getId());
			assertEquals("TEST", obj.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchServersServerPoolConfiguration() {
		List<ServersServerPool> list= srverService.fetchServersServerPoolConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServerPoolConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersServerPoolConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServerPoolConfiguration_NullSecondParameter() {
		List<ServersServerPool> list = createSSP();
		((ServersServerPool) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersServerPoolConfiguration(list, null);
	}
	
	
	@Test
	public void deleteServersServerPool() {
		try{
			srverService.deleteServersServerPool(createSSP());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = Exception.class)
	public void deleteServersServerPool_NullParameter() {
		srverService.deleteServersServerPool(null);
	}	

	@Test(expected = Exception.class)
	public void fetchServersServerPoolPolicyConfiguration_NullParameter() {
		srverService.fetchServersServerPoolPolicyConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersServerPoolPolicyConfiguration() {
		try{
			List<ServersServerPoolPolicy> list = createSSPP();
			((ServersServerPoolPolicy) list.get(0)).setName("TEST");
			srverService.saveOrUpdateServersServerPoolPolicyConfiguration(list, projectId);
			ServersServerPoolPolicy obj = (ServersServerPoolPolicy) srverService.findById(ServersServerPoolPolicy.class,((ServersServerPoolPolicy)list.get(0)).getId());
			assertEquals("TEST", obj.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchServersServerPoolPolicyConfiguration() {
		List<ServersServerPoolPolicy> list= srverService.fetchServersServerPoolPolicyConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServerPoolPolicyConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersServerPoolPolicyConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServerPoolPolicyConfiguration_NullSecondParameter() {
		List<ServersServerPoolPolicy> list = createSSPP();
		((ServersServerPoolPolicy) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersServerPoolPolicyConfiguration(list, null);
	}
	
	
	@Test
	public void deleteServersServerPoolPolicy() {
		try{
			srverService.deleteServersServerPoolPolicy(createSSPP());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = Exception.class)
	public void deleteServersServerPoolPolicy_NullParameter() {
		srverService.deleteServersServerPoolPolicy(null);
	}	

	@Test(expected = Exception.class)
	public void fetchServersServerPoolQualifierConfiguration_NullParameter() {
		srverService.fetchServersServerPoolQualifierConfiguration(null);
	}


	@Test
	public void saveOrUpdateServersServerPoolQualifierConfiguration() {
		try{
			List<ServersServerPoolQualifier> list = createSSPQ();
			((ServersServerPoolQualifier) list.get(0)).setName("TEST");
			srverService.saveOrUpdateServersServerPoolQualifierConfiguration(list, projectId);
			ServersServerPoolQualifier obj = (ServersServerPoolQualifier) srverService.findById(ServersServerPoolQualifier.class,((ServersServerPoolQualifier)list.get(0)).getId());
			assertEquals("TEST", obj.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchServersServerPoolQualifierConfiguration() {
		List<ServersServerPoolQualifier> list= srverService.fetchServersServerPoolQualifierConfiguration(projectId);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServerPoolQualifierConfiguration_NullFirstParameter() {
		srverService.saveOrUpdateServersServerPoolQualifierConfiguration(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateServersServerPoolQualifierConfiguration_NullSecondParameter() {
		List<ServersServerPoolQualifier> list = createSSPQ();
		((ServersServerPoolQualifier) list.get(0)).setName("TEST");
		srverService.saveOrUpdateServersServerPoolQualifierConfiguration(list, null);
	}
	
	
	@Test
	public void deleteServersServerPoolQualifier() {
		try{
			srverService.deleteServersServerPoolQualifier(createSSPQ());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = Exception.class)
	public void deleteServersServerPoolQualifier_NullParameter() {
		srverService.deleteServersServerPoolQualifier(null);
	}		

	@Test(expected = Exception.class)
	public void fetchHostFirmwareDetail_NullParameter() {
		srverService.fetchHostFirmwareDetail(null);
	}
	
	@Test
	public void saveOrUpdateHostFirmwareDetails() {
		try{
			List<ServersHostFirmware> shfList = createSHF();
			ServersHostFirmware shf = ((ServersHostFirmware) shfList.get(0));
			shf.setName("Test");
			srverService.saveOrUpdateHostFirmwareDetails(shfList, projectId);
			shf = (ServersHostFirmware) srverService.findById(ServersHostFirmware.class, shf.getId());
			assertEquals("Test", shf.getName());
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}
	
	@Test
	public void fetchHostFirmwareDetail() {
		assertNotNull(srverService.fetchHostFirmwareDetail(projectId));
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateHostFirmwareDetails_NullFirstParameter() {
			srverService.saveOrUpdateHostFirmwareDetails(null, projectId);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateHostFirmwareDetails_NullSecondParameter() {
			srverService.saveOrUpdateHostFirmwareDetails(createSHF(), null);
	}
	
	@Test
	public void deleteServersHostFirmwareDetails() {
		try{
			List<ServersHostFirmware> shfList = createSHF();
			ServersHostFirmware shf = ((ServersHostFirmware) shfList.get(0));
			shf.setName("Test");
			srverService.saveOrUpdateHostFirmwareDetails(shfList, projectId);
			srverService.deleteServersHostFirmwareDetails(shfList);
		} catch(Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteServersHostFirmwareDetails_NullParameter() {
		srverService.deleteServersHostFirmwareDetails(null);
	}

	
	private List<ServersHostFirmware> createSHF() {
		ServersHostFirmware shf = new ServersHostFirmware(0);
		shf.setName("Test"); shf.setProjectDetails(new ProjectDetails(projectId));
		
		List<ServersHostFirmware> shfList = new ArrayList<ServersHostFirmware>();
		shfList.add(shf);
		
		return shfList;
	}
	


	private List<ServersUuidPool> createSUP() {
		ServersUuidPool obj = new ServersUuidPool();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); obj.setFromAddress("from"); obj.setToAddress("to");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersUuidPool> list = new ArrayList<ServersUuidPool>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersBootPolicy> createSBP() {
		ServersBootPolicy obj = new ServersBootPolicy();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); 
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersBootPolicy> list = new ArrayList<ServersBootPolicy>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersBootPolicyLan> createSBPL() {
		ServersBootPolicyLan obj = new ServersBootPolicyLan();
		obj.setName("Test"); obj.setId(0); 
		//obj.setOrganizations(new Organizations());
		//obj.getOrganizations().setId(2); 
		//obj.setBootPolicyId(1);
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersBootPolicyLan> list = new ArrayList<ServersBootPolicyLan>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersBootPolicySan> createSBPS() {
		ServersBootPolicySan obj = new ServersBootPolicySan();
		obj.setName("Test"); obj.setId(0); //obj.setOrganizations(new Organizations());
		//obj.getOrganizations().setId(2); 
		//obj.setBootPolicyId(1);
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		obj.setSanVhba(new SanVhba(1));
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersBootPolicySan> list = new ArrayList<ServersBootPolicySan>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersBootPolicySanTarget> createSBPST() {
		ServersBootPolicySanTarget obj = new ServersBootPolicySanTarget();
		obj.setId(0); 
		obj.setName("TEST"); 
		obj.setType("TT");
		obj.setLunId(1);
		//obj.setWwpnId(1);
		//obj.setBootPolicySanId(1);
		obj.setServersBootPolicySan(new ServersBootPolicySan(1));
		//obj.setServersBootPolicySan(new ServersBootPolicySan(1));
		obj.setServersBootPolicy(new ServersBootPolicy(1));
		List<ServersBootPolicySanTarget> list = new ArrayList<ServersBootPolicySanTarget>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersLocalDisc> createSLD() {
		ServersLocalDisc obj = new ServersLocalDisc();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); 
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersLocalDisc> list = new ArrayList<ServersLocalDisc>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersServerPool> createSSP() {
		ServersServerPool obj = new ServersServerPool();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); 
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersServerPool> list = new ArrayList<ServersServerPool>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersServerPoolPolicy> createSSPP() {
		ServersServerPoolPolicy obj = new ServersServerPoolPolicy();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); 
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersServerPoolPolicy> list = new ArrayList<ServersServerPoolPolicy>();
		list.add(obj);
	
		return list;
	}
	
	private List<ServersServerPoolQualifier> createSSPQ() {
		ServersServerPoolQualifier obj = new ServersServerPoolQualifier();
		obj.setName("Test"); obj.setId(0); obj.setOrganizations(new Organizations());
		obj.getOrganizations().setId(2); 
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(projectId);
		obj.setProjectDetails(projectDetails);

		List<ServersServerPoolQualifier> list = new ArrayList<ServersServerPoolQualifier>();
		list.add(obj);
	
		return list;
	}	
}