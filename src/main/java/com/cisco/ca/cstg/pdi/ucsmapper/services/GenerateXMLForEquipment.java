package com.cisco.ca.cstg.pdi.ucsmapper.services;

/*
 * @author sgogulap
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputeChassisDiscPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputePool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputePsuPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FcpoolInitiators;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.IppoolPool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.MacpoolBlock;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.MacpoolPool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.UuidpoolPool;

@Service("generateXMLForEquipment")
public class GenerateXMLForEquipment extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForEquipment.class);
	private ObjectFactory factory;

	@Autowired
	private GenerateXMLForOrg generateXMLForOrg;
	
	public GenerateXMLForEquipment() {
		this.factory = new ObjectFactory();
	}

	@SuppressWarnings("rawtypes")
	public void addComputeChassisDiscPolicyAndMgmtIpPool(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForEquipment : addComputeChassisDiscPolicyAndMgmtIpPool : "+ project);

		List<EquipmentChasis> equipChassisList = project.getEquipmentChasises();

		validateEquipmentChassis(equipChassisList);
		
		EquipmentChasis ec = equipChassisList.get(0);
		for (Serializable s : org.getContent()) {
			if (!(s instanceof String)) {
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof ComputeChassisDiscPolicy) {
					updateComputeChassisDiscPolicy(ec, obj);
				} else if (obj instanceof ComputePsuPolicy) {
					updateComputePsuPolicy(ec, obj);
				} else if (obj instanceof IppoolPool) {
					updateIpPool(project, obj);
				} else if (obj instanceof MacpoolPool) {
					updateMacPool(project, obj);
				} else if (obj instanceof UuidpoolPool) {
					updateUuidPool(project, obj);
				} else if (obj instanceof FcpoolInitiators) {
					udpateFcPoolInitiators(project, obj);
				} else if (obj instanceof ComputePool) {
					updateComputePool(project, obj);
				}
			}
		}
		LOGGER.info("End : GenerateXMLForEquipment : addComputeChassisDiscPolicyAndMgmtIpPool ");
	}

	private void updateDefaultMacpoolPoolWithDb(MacpoolPool macpoolPool, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForEquipment : updateDefaultMacpoolPoolWithDb : "+ project);
		List<LanMacpool> allLanMacPools = project.getLanMacpools();
		List<LanMacpool> lanMacPools = new ArrayList<LanMacpool>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), "root");

		updateLanMacPool(macpoolPool, allLanMacPools, lanMacPools, parentOrg);
		LOGGER.info("End : GenerateXMLForEquipment : updateDefaultMacpoolPoolWithDb ");
	}

	private void updateLanMacPool(MacpoolPool macpoolPool,
			List<LanMacpool> allLanMacPools, List<LanMacpool> lanMacPools,
			Organizations parentOrg) {
		for (LanMacpool lanMacpool : allLanMacPools) {			
				if (lanMacpool.getOrganizations() != null && validateLanMacPoolNameOrg(parentOrg, lanMacpool)) {
					lanMacPools.add(lanMacpool);
				}			
		}

		for (LanMacpool lanMacPool : lanMacPools) {
			String fromAdd = lanMacPool.getFromAddress();
			String toAdd = lanMacPool.getToAddress();
			if (fromAdd != null && toAdd != null) {
				MacpoolBlock macpoolBlock = factory.createMacpoolBlock();
				macpoolBlock.setFrom(fromAdd);
				macpoolBlock.setTo(toAdd);

				JAXBElement<MacpoolBlock> macpoolBlockJE = factory
						.createMacpoolBlock(macpoolBlock);
				macpoolPool.getContent().add(macpoolBlockJE);
				LOGGER.debug(
						"Added Default LanMacpool - MacpoolBlock, for block {} - {}",
						fromAdd, toAdd);
			}
		}
	}

	private boolean validateLanMacPoolNameOrg(Organizations parentOrg, LanMacpool lanMacpool) {
		return Constants.DEFAULT.equals(lanMacpool.getMacpoolName())
				&& lanMacpool.getOrganizations().getId().equals(parentOrg.getId());
	}

	private void updateDefaultFcpoolInitiatorsWwnnWithDb(FcpoolInitiators fcpoolInitiators, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForEquipment : updateDefaultFcpoolInitiatorsWwnnWithDb : "+ project);
		List<SanWwnn> allSanWwnnList = project.getSanWwnns();
		List<SanWwnn> sanWwnnList = new ArrayList<SanWwnn>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), "root");

		updateSanWwnn(fcpoolInitiators, allSanWwnnList, sanWwnnList, parentOrg);
		LOGGER.info("End : GenerateXMLForEquipment : updateDefaultFcpoolInitiatorsWwnnWithDb ");
	}

	private void updateSanWwnn(FcpoolInitiators fcpoolInitiators,
			List<SanWwnn> allSanWwnnList, List<SanWwnn> sanWwnnList,
			Organizations parentOrg) {
		for (SanWwnn sanWwnn : allSanWwnnList) {			
				if (sanWwnn.getOrganizations() != null && validateSanWwnnName(parentOrg, sanWwnn)) {
					sanWwnnList.add(sanWwnn);
				}			
		}

		for (SanWwnn sanWwnn : sanWwnnList) {
			String from = sanWwnn.getFromAddress();
			String to = sanWwnn.getToAddress();
			if (from != null && to != null) {
				generateXMLForOrg.addFcpoolBlock(fcpoolInitiators, from, to);
				LOGGER.debug("Added Default SanWwnn - FcpoolInitiators, for block {} - {}",from, to);
			}
		}
	}

	private boolean validateSanWwnnName(Organizations parentOrg, SanWwnn sanWwnn) {
		return Constants.DEFAULT.equals(sanWwnn.getWwnnName())
				&& sanWwnn.getOrganizations().getId().equals(parentOrg.getId());
	}
	
	private void updateDefaultUuidpoolPoolWithDb(UuidpoolPool uuidpoolPool,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForEquipment : updateDefaultUuidpoolPoolWithDb : "
				+ project);
		List<ServersUuidPool> allServersUuidPoolList = project
				.getServersUuidPools();
		List<ServersUuidPool> serversUuidPoolList = new ArrayList<ServersUuidPool>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(
				project.getOrganizationses(), "root");

		for (ServersUuidPool serversUuidPool : allServersUuidPoolList) {			
				if (serversUuidPool.getOrganizations() != null && Constants.DEFAULT.equals(serversUuidPool.getName())
						&& serversUuidPool.getOrganizations().getId()
								.equals(parentOrg.getId())) {
					serversUuidPoolList.add(serversUuidPool);
				}			
		}

		for (ServersUuidPool serversUuidPool : serversUuidPoolList) {
			uuidpoolPool.setPrefix((Util.isStringNotEmpty(serversUuidPool
					.getPrefix())) ? serversUuidPool.getPrefix() : uuidpoolPool.getPrefix());
			generateXMLForOrg.addUuidpoolBlock(uuidpoolPool, serversUuidPool);
		}
		LOGGER.info("End : GenerateXMLForEquipment : updateDefaultUuidpoolPoolWithDb");
	}

	private void updateDefaultComputePoolWithDb(ComputePool computePool,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForEquipment : updateDefaultComputePoolWithDb : "
				+ project);
		List<ServersServerPool> allServersServerPools = project
				.getServersServerPools();
		List<ServersServerPool> serversServerPools = new ArrayList<ServersServerPool>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(
				project.getOrganizationses(), "root");

		for (ServersServerPool serverPool : allServersServerPools) {			
				if (serverPool.getOrganizations() != null && Constants.DEFAULT.equals(serverPool.getName())
						&& serverPool.getOrganizations().getId()
								.equals(parentOrg.getId())) {
					serversServerPools.add(serverPool);
				}			
		}

		for (ServersServerPool serversServerPool : serversServerPools) {
			computePool.setDescr(serversServerPool.getDescription());
		}
		LOGGER.info("Start : GenerateXMLForEquipment : updateDefaultComputePoolWithDb ");
	}

	private void updateDefaultFcpoolInitiatorsWwpnWithDb(
			FcpoolInitiators fcpoolInitiators, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForEquipment : updateDefaultFcpoolInitiatorsWwpnWithDb : "
				+ project);
		List<SanWwpn> allSanWwpnList = project.getSanWwpns();
		List<SanWwpn> sanWwpnList = new ArrayList<SanWwpn>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(
				project.getOrganizationses(), "root");

		for (SanWwpn sanWwpn : allSanWwpnList) {			
				if (sanWwpn.getOrganizations() != null && Constants.DEFAULT.equals(sanWwpn.getWwpnName())
						&& sanWwpn.getOrganizations().getId()
								.equals(parentOrg.getId())) {
					sanWwpnList.add(sanWwpn);
				}			
		}

		for (SanWwpn sanWwpn : sanWwpnList) {
			String from = sanWwpn.getFromAddress();
			String to = sanWwpn.getToAddress();
			if (from != null && to != null) {
				generateXMLForOrg.addFcpoolBlock(fcpoolInitiators, from, to);
				LOGGER.debug(
						"Added Default SanWwpn - FcpoolInitiators, for block {} - {}",
						from, to);
			}
		}
		LOGGER.info("End : GenerateXMLForEquipment : updateDefaultFcpoolInitiatorsWwpnWithDb");
	}
	
	private void validateEquipmentChassis(List<EquipmentChasis> equipChassisList) {
		if (!Util.listNotNull(equipChassisList)) {
			LOGGER.error("Chassis info for this project was not found.");
			throw new IllegalStateException("Chassis info not saved");
		}
	}

	private void updateComputePool(XmlGenProjectDetails project, Object obj) {
		ComputePool computePool = (ComputePool) obj;
		if (generateXMLForOrg.isDefault(computePool.getName())) {
			updateDefaultComputePoolWithDb(computePool, project);
			LOGGER.debug("Updated default Server pool.");
		}
	}

	private void udpateFcPoolInitiators(XmlGenProjectDetails project, Object obj) {
		FcpoolInitiators fcpoolInitiators = (FcpoolInitiators) obj;
		if (generateXMLForOrg.isDefault(fcpoolInitiators.getName())) {
			updateDefaultFcpoolInitiatorsWwpnWithDb(fcpoolInitiators, project);
			LOGGER.debug("Updated default FcpoolInitiators wwpn.");
		} else if ("node-default".equalsIgnoreCase(fcpoolInitiators.getName())) {
			updateDefaultFcpoolInitiatorsWwnnWithDb(fcpoolInitiators, project);
			LOGGER.debug("Updated default FcpoolInitiators wwnn.");
		}
	}

	private void updateUuidPool(XmlGenProjectDetails project, Object obj) {
		UuidpoolPool uuidpoolPool = (UuidpoolPool) obj;
		if (generateXMLForOrg.isDefault(uuidpoolPool.getName())) {
			updateDefaultUuidpoolPoolWithDb(uuidpoolPool, project);
			LOGGER.debug("Updated default UuidpoolPool");
		}
	}

	private void updateMacPool(XmlGenProjectDetails project, Object obj) {
		MacpoolPool macpoolPool = (MacpoolPool) obj;
		if (generateXMLForOrg.isDefault(macpoolPool.getName())) {
			updateDefaultMacpoolPoolWithDb(macpoolPool, project);
			LOGGER.debug("Updated default MacpoolPool");
		}
	}

	private void updateIpPool(XmlGenProjectDetails project, Object obj) {
		IppoolPool ippool = (IppoolPool) obj;
		if ("ext-mgmt".equalsIgnoreCase(ippool.getName())) {
			ippool.setAssignmentOrder(project.getIpPoolAssignmentOrder());
			generateXMLForOrg.addMgmtIppoolPool(ippool, project);
			LOGGER.debug("Updated default ext-mgmt");
		}
	}

	private void updateComputePsuPolicy(EquipmentChasis ec, Object obj) {
		ComputePsuPolicy computePsuPolicy = (ComputePsuPolicy) obj;
		computePsuPolicy.setRedundancy(ec.getPspPowerSupply());
		LOGGER.debug("Updated EquipmentChasis - ComputePsuPolicy.");
	}

	private void updateComputeChassisDiscPolicy(EquipmentChasis ec, Object obj) {
		ComputeChassisDiscPolicy computeChassisDiscPolicy = (ComputeChassisDiscPolicy) obj;
		computeChassisDiscPolicy.setAction(ec.getCdpAction()+ "-link");
		computeChassisDiscPolicy.setLinkAggregationPref(ec.getCdpLinkAgg());
		LOGGER.debug("Updated EquipmentChasis - ComputeChassisDiscPolicy.");
	}
}
