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

import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanPortchannel;
import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class LANServiceImplTest implements TestConstants {

	@Autowired
	private CommonUtilServices commonUtilService;

	@Autowired
	private SessionFactory hibernateSessionFactory;

	private LANServiceImpl lanService;

	@Before
	public void setUp() {

		lanService = new LANServiceImpl(hibernateSessionFactory,
				commonUtilService);
	}

	@Test
	public void fetchLanPortChannelConfiguration() {
		List<LanPortchannel> portChannelList = lanService
				.fetchLanPortChannelConfiguration(PROJECT_ID);
		assertNotNull(portChannelList);
	}

	@Test(expected = Exception.class)
	public void fetchLanPortChannelConfiguration_NullParameter() {
		lanService.fetchLanPortChannelConfiguration(null);
	}

	@Test
	public void saveOrUpdateLanPortChannelConfiguration() {
		List<LanPortchannel> lanPortChannelList = createLPC();
		LanPortchannel lanPortChannel = lanPortChannelList.get(0);
		lanPortChannel.setFiName("Test");
		lanService.saveOrUpdateLanPortChannelConfiguration(lanPortChannelList,
				PROJECT_ID);
		lanPortChannel = (LanPortchannel) lanService.findById(
				LanPortchannel.class, lanPortChannel.getId());
		assertEquals("Test", lanPortChannel.getFiName());
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanPortChannelConfiguration_NullFirstParameter() {
		lanService.saveOrUpdateLanPortChannelConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanPortChannelConfiguration_NullSecondParameter() {
		List<LanPortchannel> lanPortChannelList = createLPC();
		lanPortChannelList.get(0).setFiName("Test");
		lanService.saveOrUpdateLanPortChannelConfiguration(lanPortChannelList,
				null);
	}

	@Test
	public void deleteLanPortChannel() {
		try {
			lanService.deleteLanPortChannel(createLPC());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = Exception.class)
	public void deleteLanPortChannel_NullParameter() {
		lanService.deleteLanPortChannel(null);
	}

	@Test
	public void fetchLanVlanConfiguration() {
		assertNotNull(lanService.fetchLanVlanConfiguration(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLanVlanConfiguration_NullParameter() {
		lanService.fetchLanVlanConfiguration(null);
	}

	@Test
	public void saveOrUpdateLanVlanConfiguration() {
		try {
			List<LanVlan> lanVlanList = createLVN();
			LanVlan lanVlan = (lanVlanList.get(0));
			lanVlan.setVlanId("Test");
			lanService
					.saveOrUpdateLanVlanConfiguration(lanVlanList, PROJECT_ID);
			lanVlan = (LanVlan) lanService.findById(LanVlan.class,
					lanVlan.getId());
			assertEquals("Test", lanVlan.getVlanId());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanVlanConfiguration_NullFirstParameter() {
		lanService.saveOrUpdateLanVlanConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanVlanConfiguration_NullSecondParameter() {
		lanService.saveOrUpdateLanVlanConfiguration(createLVN(), null);
	}

	@Test
	public void deleteLanVlan() {
		try {
			List<LanVlan> lanVlanList = createLVN();
			LanVlan lanVlan = (lanVlanList.get(0));
			lanVlan.setVlanId("Test");
			lanService
					.saveOrUpdateLanVlanConfiguration(lanVlanList, PROJECT_ID);
			lanService.deleteLanVlan(lanVlanList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLanVlan_NullParameter() {
		lanService.deleteLanVlan(null);
	}

	@Test
	public void fetchLanMacpoolConfiguration() {
		assertNotNull(lanService.fetchLanMacpoolConfiguration(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLanMacpoolConfiguration_NullParameter() {
		lanService.fetchLanMacpoolConfiguration(null);
	}

	@Test
	public void saveOrUpdateLanMacpoolConfiguration() {
		try {
			List<LanMacpool> lanMacpoolsList = createLMP();
			LanMacpool lanMacPool = (lanMacpoolsList.get(0));
			lanMacPool.setMacpoolName("TesT");
			lanService.saveOrUpdateLanMacpoolConfiguration(lanMacpoolsList,
					PROJECT_ID);
			lanMacPool = (LanMacpool) lanService.findById(LanMacpool.class,
					lanMacPool.getId());
			assertEquals("TesT", lanMacPool.getMacpoolName());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanMacpoolConfiguration_NullFirstParameter() {
		lanService.saveOrUpdateLanMacpoolConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanMacpoolConfiguration_NullSecondParameter() {
		LanMacpool lanMacPool = (createLMP().get(0));
		lanMacPool.setMacpoolName("TesT");
		lanService.saveOrUpdateLanMacpoolConfiguration(createLMP(), null);
	}

	@Test
	public void deleteLanMacpool() {
		try {
			lanService.deleteLanMacpool(createLMP());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLanMacpool_NullParameter() {
		lanService.deleteLanMacpool(null);
	}

	@Test
	public void fetchLanVnicConfiguration() {
		assertNotNull(lanService.fetchLanVnicConfiguration(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLanVnicConfiguration_NullParameter() {
		lanService.fetchLanVnicConfiguration(null);
	}

	@Test
	public void saveOrUpdateLanVnicConfiguration() {
		try {
			List<LanVnicTemplate> lanVnicList = createLVNI();
			LanVnicTemplate lanVnic = (lanVnicList.get(0));
			lanVnic.setOsType("windows");
			lanService
					.saveOrUpdateLanVnicConfiguration(lanVnicList, PROJECT_ID);
			lanVnic = (LanVnicTemplate) lanService.findById(
					LanVnicTemplate.class, lanVnic.getId());
			assertEquals("windows", lanVnic.getOsType());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanVnicConfiguration_NullFirstParameter() {
		lanService.saveOrUpdateLanVnicConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateLanVnicConfiguration_NullSecondParameter() {
		LanVnicTemplate lanVnic = (createLVNI().get(0));
		lanVnic.setOsType("windows");
		lanService.saveOrUpdateLanVnicConfiguration(createLVNI(), null);
	}

	@Test
	public void deleteLanVnic() {
		try {
			lanService.deleteLanVnicTemplate(createLVNI());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLanVnic_NullParameter() {
		lanService.deleteLanVnicTemplate(null);
	}

	@Test
	public void fetchLanQosPolicyConfiguration() {
		assertNotNull(lanService.fetchLanQosPolicyConfiguration(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLanQosPolicyConfiguration_NullParameter() {
		lanService.fetchLanQosPolicyConfiguration(null);
	}

	@Test
	public void saveOrUpdateQosPolicyConfiguration() {
		try {
			List<LanQosPolicy> lanQosList = createLQOS();
			LanQosPolicy lanQos = (lanQosList.get(0));
			lanQos.setName("Test");
			lanService.saveOrUpdateQosPolicyConfiguration(lanQosList,
					PROJECT_ID);
			lanQos = (LanQosPolicy) lanService.findById(LanQosPolicy.class,
					lanQos.getId());
			assertEquals("Test", lanQos.getName());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateQosPolicyConfiguration_NullFirstParameter() {
		lanService.saveOrUpdateQosPolicyConfiguration(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateQosPolicyConfiguration_NullSecondParameter() {
		lanService.saveOrUpdateQosPolicyConfiguration(createLQOS(), null);
	}

	@Test
	public void deleteLanQosPolicy() {
		try {
			List<LanQosPolicy> lanQosList = createLQOS();
			LanQosPolicy lanQos = (lanQosList.get(0));
			lanQos.setName("Test");
			lanService.saveOrUpdateQosPolicyConfiguration(lanQosList,
					PROJECT_ID);
			lanService.deleteLanQosPolicy(lanQosList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLanQosPolicy_NullParameter() {
		lanService.deleteLanQosPolicy(null);
	}

	@Test
	public void fetchLanNetworkControlPolicyConfig() {
		assertNotNull(lanService.fetchLanNetworkControlPolicyConfig(PROJECT_ID));
	}

	@Test(expected = Exception.class)
	public void fetchLanNetworkControlPolicyConfig_NullParameter() {
		lanService.fetchLanNetworkControlPolicyConfig(null);
	}

	@Test
	public void saveOrUpdateNetworkControlPolicyConfig() {
		try {
			List<LanNetworkControlPolicy> lanNcpList = createLNCP();
			LanNetworkControlPolicy lanQos = (lanNcpList.get(0));
			lanQos.setNcpName("Test");
			lanService.saveOrUpdateNetworkControlPolicyConfig(lanNcpList,
					PROJECT_ID);
			lanQos = (LanNetworkControlPolicy) lanService.findById(
					LanNetworkControlPolicy.class, lanQos.getId());
			assertEquals("Test", lanQos.getNcpName());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateNetworkControlPolicyConfig_NullFirstParameter() {
		lanService.saveOrUpdateNetworkControlPolicyConfig(null, PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateNetworkControlPolicyConfig_NullSecondParameter() {
		lanService.saveOrUpdateNetworkControlPolicyConfig(createLNCP(), null);
	}

	@Test
	public void deleteLanNetworkControlPolicy() {
		try {
			List<LanNetworkControlPolicy> lanNcpList = createLNCP();
			LanNetworkControlPolicy lanQos = (lanNcpList.get(0));
			lanQos.setNcpName("Test");
			lanService.saveOrUpdateNetworkControlPolicyConfig(lanNcpList,
					PROJECT_ID);
			lanService.deleteLanNetworkControlPolicy(lanNcpList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteLanNetworkControlPolicy_NullParameter() {
		lanService.deleteLanNetworkControlPolicy(null);
	}

	private List<LanPortchannel> createLPC() {
		LanPortchannel lanPortChannel = new LanPortchannel(0);
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		lanPortChannel.setProjectDetails(projectDetails);
		List<LanPortchannel> lanPortChannelList = new ArrayList<LanPortchannel>();
		lanPortChannelList.add(lanPortChannel);

		return lanPortChannelList;
	}

	private List<LanVlan> createLVN() {
		LanVlan lanVlan = new LanVlan(0);
		lanVlan.setVlanName("TestVLan");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		lanVlan.setProjectDetails(projectDetails);
		List<LanVlan> lanVlanList = new ArrayList<>();
		lanVlanList.add(lanVlan);

		return lanVlanList;
	}

	private List<LanMacpool> createLMP() {
		LanMacpool lanMacPool = new LanMacpool(0);
		lanMacPool.setMacpoolDescription("Test");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		lanMacPool.setProjectDetails(projectDetails);
		List<LanMacpool> lanMacPoolList = new ArrayList<>();
		lanMacPoolList.add(lanMacPool);

		return lanMacPoolList;
	}

	private List<LanVnicTemplate> createLVNI() {
		LanVnicTemplate lanVnic = new LanVnicTemplate(0);
		lanVnic.setOsType("windows");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		lanVnic.setProjectDetails(projectDetails);
		List<LanVnicTemplate> lanVnicList = new ArrayList<LanVnicTemplate>();
		lanVnicList.add(lanVnic);

		return lanVnicList;
	}

	private List<LanQosPolicy> createLQOS() {
		LanQosPolicy lanQos = new LanQosPolicy();
		lanQos.setId(0);
		lanQos.setBurst(567);
		lanQos.setName("Name");
		lanQos.setOrganizations(new Organizations(1));
		lanQos.setRate("1234");
		lanQos.setHostControl("none");
		lanQos.setPriority("fc");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		lanQos.setProjectDetails(projectDetails);
		List<LanQosPolicy> lanQosList = new ArrayList<>();
		lanQosList.add(lanQos);

		return lanQosList;
	}

	private List<LanNetworkControlPolicy> createLNCP() {
		LanNetworkControlPolicy lanNCP = new LanNetworkControlPolicy();
		lanNCP.setId(0);
		lanNCP.setCdp("disabled");
		lanNCP.setNcpName("Name");
		lanNCP.setOrganizations(new Organizations(1));
		lanNCP.setForge("allow");
		lanNCP.setMacRegisterMode("native");
		lanNCP.setDescription("DESC");
		lanNCP.setUplinkFailAction("warning");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId(PROJECT_ID);
		lanNCP.setProjectDetails(projectDetails);
		List<LanNetworkControlPolicy> lanNCPList = new ArrayList<LanNetworkControlPolicy>();
		lanNCPList.add(lanNCP);

		return lanNCPList;
	}
}