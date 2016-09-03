package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.LanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanEthernetAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.LanLcpVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.LanMgmtIppool;
import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanPortchannel;
import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplateVlanMapping;

/**
 * @author pavkuma2
 * 
 */
public interface LANService {

	List<LanPortchannel> fetchLanPortChannelConfiguration(Integer projectId);

	List<LanPortchannel> saveOrUpdateLanPortChannelConfiguration(
			List<LanPortchannel> lanPortChannelList, Integer projectId);

	void deleteLanPortChannel(List<LanPortchannel> lanPortChannelList);

	List<LanVlan> fetchLanVlanConfiguration(Integer projectId);

	List<LanVlan> saveOrUpdateLanVlanConfiguration(List<LanVlan> lanVlanList,
			Integer projectId);

	void deleteLanVlan(List<LanVlan> lanVlanList);

	List<LanMacpool> fetchLanMacpoolConfiguration(Integer projectId);

	List<LanMacpool> saveOrUpdateLanMacpoolConfiguration(
			List<LanMacpool> lanMacpoolList, Integer projectId);

	void deleteLanMacpool(List<LanMacpool> lanMacpoolList);

	List<LanVnicTemplate> fetchLanVnicConfiguration(Integer projectId);

	List<LanVnicTemplate> saveOrUpdateLanVnicConfiguration(List<LanVnicTemplate> lanVnicList,
			Integer projectId);

	void deleteLanVnicTemplate(List<LanVnicTemplate> lanVnicList);

	List<LanMgmtIppool> fetchLanMgmtIPpoolConfiguration(Integer projectId);

	List<LanMgmtIppool> saveOrUpdateLanMgmtIPpoolConfiguration(
			List<LanMgmtIppool> lanMgmtIPpoolList, Integer projectId);

	void deleteLanMgmtIPpool(List<LanMgmtIppool> lanMgmtIPpoolList);
	
	List<LanVnicTemplateVlanMapping> fetchLanVlanVnicMappings(Integer vnicId);

	List<LanVnicTemplateVlanMapping> saveOrUpdateLanVlanVnicMappings(List<LanVnicTemplateVlanMapping> vlanVnicMappingsList, Integer vnicId);

	void deleteLanVlanVnicMappings(List<LanVnicTemplateVlanMapping> vlanVnicMappingsList);
	
	List<LanQosPolicy> fetchLanQosPolicyConfiguration(Integer projectId);

	List<LanQosPolicy> saveOrUpdateQosPolicyConfiguration(List<LanQosPolicy> lanQosList,
			Integer projectId);

	void deleteLanQosPolicy(List<LanQosPolicy> lanQosList);

	List<LanNetworkControlPolicy> saveOrUpdateNetworkControlPolicyConfig(
			List<LanNetworkControlPolicy> lanNetworkControlPolicyList, Integer projectId);

	void deleteLanNetworkControlPolicy(
			List<LanNetworkControlPolicy> deletedNetworkControlPilicyList);

	List<LanNetworkControlPolicy> fetchLanNetworkControlPolicyConfig(Integer projectId);
	
	List<LanEthernetAdapterPolicies> saveOrUpdateLanEthernetAdapterPolicies(List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicyList,
			Integer projectId);
	
	void deleteLanEthernetAdapterPolicies(List<LanEthernetAdapterPolicies> deletedLanEthernetAdapterPolicyList);
	
	List<LanEthernetAdapterPolicies> fetchLanEthernetAdapterPolicies(Integer projectId);
	
	List<LanVnic> fetchLanVnic(Integer projectId);
	
	List<LanVnic> saveOrUpdateLanVnic(List<LanVnic> lanVnicList,
			Integer projectId);
	
	void deleteLanVnicProfile(List<LanVnic> lanVnicList);
	
	public List<LanConnectivityPolicy> fetchLanConnectivityPolicyDetail(Integer projectId);
	   
	public List<LanLcpVnicMapping> fetchLanLcpVnicMappings(Integer lcpId);
	
	List<LanConnectivityPolicy> saveOrUpdateLanConnectivityPolicy(
			List<LanConnectivityPolicy> lanConnectivityPolicyList, Integer projectId);
	
	List<LanLcpVnicMapping> saveOrUpdateLanLcpVnicMappings(
			List<LanLcpVnicMapping> lanLcpVnicMappingList, Integer lcpId);
	
	void deleteLanConnectivityPolicy(
			List<LanConnectivityPolicy> deletedLanConnectivityPolicyList);
}
