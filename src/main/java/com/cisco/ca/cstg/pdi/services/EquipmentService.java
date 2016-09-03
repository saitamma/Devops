/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.EquipmentChasis;
import com.cisco.ca.cstg.pdi.pojos.EquipmentFcport;
import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniScalability;
import com.cisco.ca.cstg.pdi.pojos.EquipmentMiniServerUplink;
import com.cisco.ca.cstg.pdi.pojos.EquipmentServer;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;

/**
 * @author pavkuma2
 * 
 */
public interface EquipmentService {

	List<EquipmentChasis> fetchEquipmentChasisConfiguration(Integer projectId);

	Integer createEquipmentChasisConfiguration(Object equipmentChasis);

	void saveOrUpdateEquipmentChasisConfiguration(
			EquipmentChasis equipmentChasis, Integer projectId);

	void deleteEquipmentChasis(EquipmentChasis equipmentChasis);

	List<EquipmentServer> fetchEquipmentServerConfiguration(Integer projectId);

	Integer createEquipmentServerConfiguration(Object equipmentServer);

	List<EquipmentServer> saveOrUpdateEquipmentServerConfiguration(
			List<EquipmentServer> equipmentServerList, Integer projectId);

	void deleteEquipmentServerConfiguration(
			List<EquipmentServer> equipmentServerList);

	List<EquipmentUplink> fetchEquipmentUplinkConfiguration(Integer projectId);

	Integer createEquipmentUplinkConfiguration(Object equipmentUplink);

	List<EquipmentUplink> saveOrUpdateEquipmentUplinkConfiguration(
			List<EquipmentUplink> equipmentUplinkList, Integer projectId);

	void deleteEquipmentUplinkConfiguration(
			List<EquipmentUplink> equipmentUplinkList);

	List<EquipmentFcport> fetchEquipmentFCPortConfiguration(Integer projectId);

	List<EquipmentFcport> saveOrUpdateEquipmentFCPortConfiguration(
			List<EquipmentFcport> equipmentFCPortList, Integer projectId);

	void deleteEquipmentFCPortConfiguration(
			List<EquipmentFcport> equipmentFCPortList);

	List<EquipmentMiniScalability> fetchEquipmentMiniScalabilityConfiguration(
			Integer projectId);

	List<EquipmentMiniScalability> saveOrUpdateEquipmentMiniScalabilityConfiguration(
			List<EquipmentMiniScalability> equipmentScalabilityList,
			Integer projectId);

	List<EquipmentMiniServerUplink> fetchEquipmentMiniUplinPortsForFc(
			Integer projectId);
	
	void insertScalabilityPortsForMini(Integer projectId);
}
