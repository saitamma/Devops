/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.EquipmentFcport;
import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability;
import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniServerUplink;
import com.cisco.ca.cstg.pdi.pojos.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author pavkuma2
 * 
 */

@Service("equipmentService")
public class EquipmentServiceImpl extends CommonDaoServicesImpl implements
		EquipmentService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EquipmentServiceImpl.class);


	private final CommonUtilServices commonUtilService;

	@Autowired
	public EquipmentServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentChasis> fetchEquipmentChasisConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<EquipmentChasis>) (List<?>) listAll(EquipmentChasis.class,
				criteria);
	}

	@Override
	public Integer createEquipmentChasisConfiguration(Object equipmentChasis) {
		return addNew(equipmentChasis);
	}

	@Override
	public void saveOrUpdateEquipmentChasisConfiguration(
			EquipmentChasis equipmentChasis, Integer projectId) {
		List<EquipmentChasis> euipmentChasisList = fetchEquipmentChasisConfiguration(projectId);
		if (euipmentChasisList != null && euipmentChasisList.size() == 1) {
			equipmentChasis.setId(euipmentChasisList.get(0).getId());
		}
		equipmentChasis.setProjectDetails(commonUtilService
				.getProjectDetailsObject(projectId));
		saveOrUpdate(equipmentChasis);
	}

	@Override
	public void deleteEquipmentChasis(EquipmentChasis equipmentChasis) {
		delete(equipmentChasis);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentServer> fetchEquipmentServerConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<EquipmentServer>) (List<?>) listAll(EquipmentServer.class,
				criteria);
	}

	@Override
	public Integer createEquipmentServerConfiguration(Object equipmentServer) {
		return addNew(equipmentServer);
	}

	@Override
	public List<EquipmentServer> saveOrUpdateEquipmentServerConfiguration(
			List<EquipmentServer> equipmentServerList, Integer projectId) {
		for (EquipmentServer es : equipmentServerList) {
			es.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (es.getId() == 0) {
				es.setId(addNew(es));
			} else {
				update(es);
			}
		}
		return equipmentServerList;
	}

	@Override
	public void deleteEquipmentServerConfiguration(
			List<EquipmentServer> equipmentServerList) {
		deleteAll(equipmentServerList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentUplink> fetchEquipmentUplinkConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<EquipmentUplink>) (List<?>) listAll(EquipmentUplink.class,
				criteria);
	}

	@Override
	public Integer createEquipmentUplinkConfiguration(Object equipmentUplink) {
		return addNew(equipmentUplink);
	}

	@Override
	public List<EquipmentUplink> saveOrUpdateEquipmentUplinkConfiguration(
			List<EquipmentUplink> equipmentUplinkList, Integer projectId) {
		for (EquipmentUplink eu : equipmentUplinkList) {
			eu.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (eu.getId() == 0) {
				eu.setId(addNew(eu));
			} else {
				update(eu);
			}
		}
		return equipmentUplinkList;
	}

	@Override
	public void deleteEquipmentUplinkConfiguration(
			List<EquipmentUplink> equipmentUplinkList) {
		deleteAll(equipmentUplinkList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentFcport> fetchEquipmentFCPortConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<EquipmentFcport>) (List<?>) listAll(EquipmentFcport.class,
				criteria);
	}

	@Override
	public List<EquipmentFcport> saveOrUpdateEquipmentFCPortConfiguration(
			List<EquipmentFcport> equipmentFCPortList, Integer projectId) {
		for (EquipmentFcport efcp : equipmentFCPortList) {
			efcp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (efcp.getId() == 0) {
				efcp.setId(addNew(efcp));
			} else {
				update(efcp);
			}
		}
		return equipmentFCPortList;
	}

	@Override
	public void deleteEquipmentFCPortConfiguration(
			List<EquipmentFcport> equipmentFCPortList) {
		deleteAll(equipmentFCPortList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentMiniScalability> fetchEquipmentMiniScalabilityConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<EquipmentMiniScalability>) (List<?>) listAll(
				EquipmentMiniScalability.class, criteria);
	}

	@Override
	public List<EquipmentMiniScalability> saveOrUpdateEquipmentMiniScalabilityConfiguration(
			List<EquipmentMiniScalability> equipmentScalabilityList,
			Integer projectId) {
		for (EquipmentMiniScalability es : equipmentScalabilityList) {
			es.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (es.getId() == 0) {
				es.setId(addNew(es));
			} else {
				update(es);
			}
		}
		return equipmentScalabilityList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentMiniServerUplink> fetchEquipmentMiniUplinPortsForFc(
			Integer projectId) {
		Criterion whereClause = Restrictions.and(Restrictions.eq(
				Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId)),
				Restrictions.and(Restrictions.eq("isConfigured", 1),
						Restrictions.eq("configureAs", "uplink")));
		return (List<EquipmentMiniServerUplink>) (List<?>) listAll(
				EquipmentMiniServerUplink.class, whereClause);
	}
	
	@Override
	public void insertScalabilityPortsForMini(Integer projectId) {
		LOGGER.info("Start : insertScalabilityPortsForMini");
		createScalabilityPort("A", projectId);
		createScalabilityPort("B", projectId);
		LOGGER.info("End : insertScalabilityPortsForMini");
	}

	private void createScalabilityPort(String fiId, Integer projectId) {
		for (int i = 1; i <= 4; i++) {
			EquipmentMiniScalability eminiscalPort = new EquipmentMiniScalability();
			eminiscalPort.setChassis(1);
			eminiscalPort.setSlotId(1);
			eminiscalPort.setPortId(i);
			eminiscalPort.setIsConfigured(0);
			eminiscalPort.setConfigureAs("server");
			eminiscalPort.setFiId(fiId);
			eminiscalPort.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			addNew(eminiscalPort);
		}
	}

}
