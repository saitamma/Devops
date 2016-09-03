package com.cisco.ca.cstg.pdi.services;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
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

import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability;
import com.cisco.ca.cstg.pdi.pojos.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class EquipmentServiceImplTest implements TestConstants {

	@Autowired
	private SessionFactory hibernateSessionFactory;

	@Autowired
	private CommonUtilServices commonUtilService;

	private EquipmentServiceImpl equipmentService;

	@Before
	public void setUp() {
		equipmentService = new EquipmentServiceImpl(hibernateSessionFactory,
				commonUtilService);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void fetchEquipmentChasisConfiguration() {
		List<EquipmentChasis> equipmentList = equipmentService
				.fetchEquipmentChasisConfiguration(PROJECT_ID);
		assertNotNull(equipmentList);
		assertTrue(equipmentList.size() >= 0);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentChasisConfiguration_NullParameter() {
		equipmentService.fetchEquipmentChasisConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void createEquipmentChasisConfiguration() {
		assertNotNull(equipmentService
				.createEquipmentChasisConfiguration(createEquipmentChesis()));
	}

	@Test(expected = Exception.class)
	public void createEquipmentChasisConfiguration_NullParameter() {
		equipmentService.createEquipmentChasisConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void saveOrUpdateEquipmentChasisConfiguration() {
		EquipmentChasis equipment = createEquipmentChesis();
		equipment.setCdpAction("1-link");
		equipmentService.saveOrUpdateEquipmentChasisConfiguration(equipment,
				PROJECT_ID);
		equipment = (EquipmentChasis) (equipmentService.findById(
				EquipmentChasis.class, equipment.getId()));
		assertEquals("1-link", equipment.getCdpAction());
	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateEquipmentChasisConfiguration_NullFirstParameter() {
		equipmentService.saveOrUpdateEquipmentChasisConfiguration(null,
				PROJECT_ID);
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateEquipmentChasisConfiguration_NullSecondParameter() {
		EquipmentChasis equipment = createEquipmentChesis();
		equipment.setCdpAction("1-link");
		equipmentService.saveOrUpdateEquipmentChasisConfiguration(equipment,
				null);
	}

	@Test
	public void deleteEquipmentChasis() {
		EquipmentChasis equipment = createEquipmentChesis();
		try {
			equipmentService.deleteEquipmentChasis(equipment);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteEquipmentChasis_NullParameter() {
		equipmentService.deleteEquipmentChasis(null);
	}

	@Test(expected = Exception.class)
	public void fetchEquipmentServerConfiguration_NullParameter() {
		equipmentService.fetchEquipmentServerConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void createEquipmentServerConfiguration() {
		assertNotNull(equipmentService
				.createEquipmentServerConfiguration(createEquipmentServer()));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void fetchEquipmentServerConfiguration() {
		List<EquipmentServer> equipmentList = equipmentService
				.fetchEquipmentServerConfiguration(PROJECT_ID);
		assertNotNull(equipmentList);
		assertTrue(equipmentList.size() >= 0);
	}

	@Test(expected = Exception.class)
	public void createEquipmentServerConfiguration_NullParameter() {
		equipmentService.createEquipmentServerConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void saveOrUpdateEquipmentServerConfiguration() {
		EquipmentServer equipment = createEquipmentServer();
		Integer portId = new Integer(2);
		equipment.setPortId(portId);
		List<EquipmentServer> objList = new ArrayList<EquipmentServer>();
		objList.add(equipment);

		equipmentService.saveOrUpdateEquipmentServerConfiguration(objList,
				PROJECT_ID);
		equipment = (EquipmentServer) (equipmentService.findById(
				EquipmentServer.class, equipment.getId()));
		assertEquals(portId, equipment.getPortId());
	}

	@Test(expected = Exception.class)
	public void saveOrUpdateEquipmentServerConfiguration_NullSecondParameter() {
		EquipmentServer equipment = createEquipmentServer();
		Integer portId = new Integer(2);
		equipment.setPortId(portId);
		List<EquipmentServer> objList = new ArrayList<EquipmentServer>();
		objList.add(equipment);

		equipmentService
				.saveOrUpdateEquipmentServerConfiguration(objList, null);
	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateEquipmentServerConfiguration_NullFirstParameter() {
		equipmentService.saveOrUpdateEquipmentServerConfiguration(null,
				PROJECT_ID);
	}

	@Test
	public void deleteEquipmentServerConfiguration() {
		EquipmentServer equipment = createEquipmentServer();
		List<EquipmentServer> equipmentServerList = new ArrayList<EquipmentServer>();
		equipmentServerList.add(equipment);

		try {
			equipmentService
					.deleteEquipmentServerConfiguration(equipmentServerList);
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

	@Test(expected = Exception.class)
	public void deleteEquipmentServerConfiguration_NullParameter() {
		equipmentService.deleteEquipmentServerConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void createEquipmentUplinkConfiguration() {
		assertNotNull(equipmentService
				.createEquipmentUplinkConfiguration(createEquipmentUplink()));
	}

	@Test(expected = Exception.class)
	public void createEquipmentUplinkConfiguration_NullParameter() {
		equipmentService.createEquipmentUplinkConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void fetchEquipmentUplinkConfiguration() {
		List<EquipmentUplink> equipmentList = equipmentService
				.fetchEquipmentUplinkConfiguration(PROJECT_ID);
		assertNotNull(equipmentList);
		assertTrue(equipmentList.size() >= 0);

	}

	@Test(expected = Exception.class)
	public void fetchEquipmentUplinkConfiguration_NullParameter() {
		equipmentService.fetchEquipmentUplinkConfiguration(null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void saveOrUpdateEquipmentUplinkConfiguration() {
		EquipmentUplink equipment = createEquipmentUplink();
		// equipment.setId(1);
		Integer portId = new Integer(1);
		equipment.setPortId(portId);
		List<EquipmentUplink> objList = new ArrayList<EquipmentUplink>();
		objList.add(equipment);

		equipmentService.saveOrUpdateEquipmentUplinkConfiguration(objList,
				PROJECT_ID);
		equipment = (EquipmentUplink) (equipmentService.findById(
				EquipmentUplink.class, equipment.getId()));
		assertEquals(portId, equipment.getPortId());
	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateEquipmentUplinkConfiguration_NullFirstParameter() {
		equipmentService.saveOrUpdateEquipmentUplinkConfiguration(null,
				PROJECT_ID);
	}

	@Test
	public void deleteEquipmentUplinkConfiguration() {

		EquipmentUplink equipment = createEquipmentUplink();
		List<EquipmentUplink> equipmentUpLinkList = new ArrayList<>();
		equipmentUpLinkList.add(equipment);

		try {
			equipmentService
					.deleteEquipmentUplinkConfiguration(equipmentUpLinkList);
		} catch (Exception e) {
			fail("should not throw exception");
		}

	}

	@Test(expected = Exception.class)
	public void deleteEquipmentUplinkConfiguration_NullParameter() {
		equipmentService.deleteEquipmentUplinkConfiguration(null);
	}

	private EquipmentChasis createEquipmentChesis() {
		EquipmentChasis equipmentChasis = new EquipmentChasis();
		ProjectDetails pd = new ProjectDetails();
		pd.setId(PROJECT_ID);
		equipmentChasis.setProjectDetails(pd);
		return equipmentChasis;
	}

	private EquipmentServer createEquipmentServer() {
		EquipmentServer equipmentServer = new EquipmentServer(0);
		ProjectDetails pd = new ProjectDetails();
		pd.setId(PROJECT_ID);
		equipmentServer.setProjectDetails(pd);
		equipmentServer.setPortId(1);
		return equipmentServer;
	}

	private EquipmentUplink createEquipmentUplink() {
		EquipmentUplink equipmentUpLink = new EquipmentUplink(0);
		ProjectDetails pd = new ProjectDetails();
		pd.setId(PROJECT_ID);
		equipmentUpLink.setProjectDetails(pd);
		equipmentUpLink.setUserLabel("Test");
		return equipmentUpLink;
	}
		
	@Test
	public void createEquipmentMiniScalabilityConfiguration() {
		equipmentService.insertScalabilityPortsForMini(PROJECT_ID);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void fetchEquipmentMiniScalabilityConfiguration() {
		List<EquipmentMiniScalability> equipmentList = equipmentService
				.fetchEquipmentMiniScalabilityConfiguration(PROJECT_ID);
		assertNotNull(equipmentList);
		assertTrue(equipmentList.size() >= 0);
	}
	
	@Test(expected = Exception.class)
	public void createEquipmentMiniScalabilityConfiguration_NullParameter() {
		equipmentService.insertScalabilityPortsForMini(null);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void saveOrUpdateEquipmentMiniScalabilityConfiguration() {
		EquipmentMiniScalability equipment = createEquipmentMiniScalability();
		int isConfigured = 1;
		equipment.setIsConfigured(isConfigured);
		List<EquipmentMiniScalability> objList = new ArrayList<EquipmentMiniScalability>();
		objList.add(equipment);

		equipmentService.saveOrUpdateEquipmentMiniScalabilityConfiguration(objList,
				PROJECT_ID);
		equipment = (EquipmentMiniScalability) (equipmentService.findById(
				EquipmentMiniScalability.class, equipment.getId()));
		assertEquals(isConfigured, equipment.getIsConfigured());
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateEquipmentMiniScalabilityConfiguration_NullSecondParameter() {
		EquipmentMiniScalability equipment = createEquipmentMiniScalability();
		int isConfigured = 1;
		equipment.setIsConfigured(isConfigured);
		List<EquipmentMiniScalability> objList = new ArrayList<EquipmentMiniScalability>();
		objList.add(equipment);

		equipmentService
				.saveOrUpdateEquipmentMiniScalabilityConfiguration(objList, null);
	}

	@Test(expected = NullPointerException.class)
	public void saveOrUpdateEquipmentMiniScalabilityConfiguration_NullFirstParameter() {
		equipmentService.saveOrUpdateEquipmentMiniScalabilityConfiguration(null,
				PROJECT_ID);
	}
	private EquipmentMiniScalability createEquipmentMiniScalability() {
		EquipmentMiniScalability ems = new EquipmentMiniScalability(1);
		ProjectDetails pd = new ProjectDetails(1);
		pd.setId(PROJECT_ID);
		ems.setProjectDetails(pd);
		ems.setFiId("A");
		ems.setPortId(1);
		ems.setSlotId(1);
		ems.setChassis(1);
		ems.setConfigureAs("server");
		ems.setIsConfigured(0);		
		return ems;
	}

}
