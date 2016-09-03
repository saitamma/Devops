/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.ServersBiosPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySanTarget;
import com.cisco.ca.cstg.pdi.pojos.ServersHostFirmware;
import com.cisco.ca.cstg.pdi.pojos.ServersLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersMaintenancePolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersSPQSlotMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolQualifier;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;

/**
 * @author pavkuma2
 * 
 */
public interface ServersService {

	List<ServersUuidPool> fetchServersUuidPoolConfiguration(Integer projectId);

	List<ServersUuidPool> saveOrUpdateServersUuidPoolConfiguration(
			List<ServersUuidPool> serversUuidPoolList, Integer projectId);

	void deleteServersUuidPool(List<ServersUuidPool> serversUuidPoolList);

	List<ServersBootPolicy> fetchServersBootPolicyConfiguration(Integer projectId);

	List<ServersBootPolicy> saveOrUpdateServersBootPolicyConfiguration(
			List<ServersBootPolicy> serversBootPolicyList, Integer projectId);

	void deleteServersBootPolicy(List<ServersBootPolicy> serversBootPolicyList);

	List<ServersBootPolicyLan> fetchServersBootPolicyLanConfiguration(Integer projectId);

	List<ServersBootPolicyLan> saveOrUpdateServersBootPolicyLanConfiguration(
			List<ServersBootPolicyLan> serversBootPolicyLanList, Integer projectId);

	void deleteServersBootPolicyLan(List<ServersBootPolicyLan> serversBootPolicyLanList);

	List<ServersBootPolicySan> fetchServersBootPolicySanConfiguration(Integer projectId);

	List<ServersBootPolicySan> saveOrUpdateServersBootPolicySanConfiguration(
			List<ServersBootPolicySan> serversBootPolicySanList, Integer projectId);

	void deleteServersBootPolicySan(List<ServersBootPolicySan> serversBootPolicySanList);

	List<ServersBootPolicySanTarget> fetchServersBootPolicySanTargetConfiguration(
			Integer bootPolicyId);

	List<ServersBootPolicySanTarget> saveOrUpdateServersBootPolicySanTargetConfiguration(
			List<ServersBootPolicySanTarget> serversBootPolicySanTargetList);

	void deleteServersBootPolicySanTarget(
			List<ServersBootPolicySanTarget> serversBootPolicySanTargetList);

	List<ServersLocalDisc> fetchServersLocalDiscConfiguration(Integer projectId);

	List<ServersLocalDisc> saveOrUpdateServersLocalDiscConfiguration(
			List<ServersLocalDisc> serversLocalDiscList, Integer projectId);

	void deleteServersLocalDisc(List<ServersLocalDisc> serversLocalDiscList);

	List<ServersServerPool> fetchServersServerPoolConfiguration(Integer projectId);

	List<ServersServerPool> saveOrUpdateServersServerPoolConfiguration(
			List<ServersServerPool> serversServerPoolList, Integer projectId);

	void deleteServersServerPool(List<ServersServerPool> serversServerPoolList);

	List<ServersServerPoolPolicy> fetchServersServerPoolPolicyConfiguration(Integer projectId);

	List<ServersServerPoolPolicy> saveOrUpdateServersServerPoolPolicyConfiguration(
			List<ServersServerPoolPolicy> serversServerPoolPolicyList, Integer projectId);

	void deleteServersServerPoolPolicy(List<ServersServerPoolPolicy> serversServerPoolPolicyList);

	List<ServersServerPoolQualifier> fetchServersServerPoolQualifierConfiguration(Integer projectId);

	List<ServersServerPoolQualifier> saveOrUpdateServersServerPoolQualifierConfiguration(
			List<ServersServerPoolQualifier> serversServerPoolQualifierList, Integer projectId);

	void deleteServersServerPoolQualifier(
			List<ServersServerPoolQualifier> serversServerPoolQualifierList);

	List<ServersBootPolicyLan> fetchBootPolicyLanConfigDetails(Integer bootPolicyId);

	List<ServersBootPolicySan> fetchBootPolicySanConfigDetails(Integer bootPolicyId);

	List<ServersBootPolicyLocalDisc> fetchBootPolicyLocalDiscConfigDetails(Integer bootPolicyId);

	List<ServersBootPolicyLocalDisc> saveOrUpdateServersBootPolicyLocalDiscConfiguration(
			List<ServersBootPolicyLocalDisc> serversBootPolicyLocalDiscList, Integer projectId);

	void deleteServersBootPolicyLocalDisc(Integer bootPolicyId);

	List<ServersSPQSlotMapping> fetchServersSPQSlotMappings(Integer spqId);

	List<ServersSPQSlotMapping> saveOrUpdateServersSPQSlotMappings(
			List<ServersSPQSlotMapping> serversSPQSlotMappingList);

	void deleteServersSPQSlotMappings(List<ServersSPQSlotMapping> serversSPQSlotMappingList);

	List<ServersHostFirmware> fetchHostFirmwareDetail(Integer projectId);

	List<ServersHostFirmware> saveOrUpdateHostFirmwareDetails(
			List<ServersHostFirmware> serversHostFirmwareList, Integer projectId);

	void deleteServersHostFirmwareDetails(List<ServersHostFirmware> serversHostFirmwareList);

	List<ServersBiosPolicy> fetchBiosPolicyDetail(Integer projectId);

	List<ServersBiosPolicy> saveOrUpdateBiosPolicyDetails(
			List<ServersBiosPolicy> serversBiosPolicyList, Integer projectId);

	void deleteServersBiosPolicyDetails(
			List<ServersBiosPolicy> deletedServersBiosPolicyList);

	ServersBiosPolicy fetchSingleBiosPolicyDetail(Integer biosPolicyId);

	List<ServersMaintenancePolicy> fetchMaintenancePolicyDetail(Integer projectId);

	List<ServersMaintenancePolicy> saveOrUpdateMaintenancePolicyDetails(
			List<ServersMaintenancePolicy> serversMaintenancePolicyList, Integer projectId);

	void deleteServersMaintenancePolicyDetails(
			List<ServersMaintenancePolicy> deletedServersMaintenancePolicyList);
}
