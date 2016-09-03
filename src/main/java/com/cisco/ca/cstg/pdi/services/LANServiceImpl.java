/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
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
import com.cisco.ca.cstg.pdi.utils.Constants;
/**
 * @author pavkuma2
 * 
 */

@Service("lanService")
public class LANServiceImpl extends CommonDaoServicesImpl implements LANService {
	
	private static final String LANVNICTEMPLATE_ID = "lanVnicTemplate.id";
	private static final String ID = "id";
	private static final String VLANDEFAULT = "vlanDefault";
	private static final String LANCONNECTIVITYPOLICY_ID = "lanConnectivityPolicy.id";
	private final CommonUtilServices commonUtilService;	

	@Autowired
	public LANServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LanPortchannel> fetchLanPortChannelConfiguration(Integer projectId) {		
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanPortchannel>)(List<?>)listAll(LanPortchannel.class, criteria);
	}

	@Override
	public List<LanPortchannel> saveOrUpdateLanPortChannelConfiguration(
			List<LanPortchannel> lanPortChannelList, Integer projectId) {
		for (LanPortchannel es : lanPortChannelList) {			
			es.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (es.getId() == 0) {
				es.setId(addNew(es));
			} else {
				update(es);
			}
		}
		return lanPortChannelList;
	}

	@Override
	public void deleteLanPortChannel(List<LanPortchannel> lanPortChannelList) {
		deleteAll(lanPortChannelList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanVlan> fetchLanVlanConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
		commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanVlan>)(List<?>)listAll(LanVlan.class, criteria);

	}

	@Override
	public List<LanVlan> saveOrUpdateLanVlanConfiguration(
			List<LanVlan> lanVlanList, Integer projectId) {
		for (LanVlan lvlan : lanVlanList) {			
			lvlan.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (lvlan.getId() == 0) {
				lvlan.setId(addNew(lvlan));
			} else {
				update(lvlan);
			}
		}
		return lanVlanList;
	}

	@Override
	public void deleteLanVlan(List<LanVlan> lanVlanList) {
		for(LanVlan vlan : lanVlanList){			
			Criterion criteria = Restrictions.and(Restrictions.eq(ID, vlan.getId()),Restrictions.eq(VLANDEFAULT, false));
			deleteByCriteria(LanVlan.class, criteria);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanMacpool> fetchLanMacpoolConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanMacpool>)(List<?>)listAll(LanMacpool.class, criteria);
	}

	@Override
	public List<LanMacpool> saveOrUpdateLanMacpoolConfiguration(
			List<LanMacpool> lanMacpoolList, Integer projectId) {
		for (LanMacpool lmp : lanMacpoolList) {			
			lmp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (lmp.getId() == 0) {
				lmp.setId(addNew(lmp));
			} else {
				update(lmp);
			}
		}
		return lanMacpoolList;
	}

	@Override
	public void deleteLanMacpool(List<LanMacpool> lanMacpoolList) {
		deleteAll(lanMacpoolList);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LanVnicTemplate> fetchLanVnicConfiguration(Integer projectId) {		
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));				
		return (List<LanVnicTemplate>)(List<?>)listAll(LanVnicTemplate.class, criteria);
	}

	@Override
	public List<LanVnicTemplate> saveOrUpdateLanVnicConfiguration(
			List<LanVnicTemplate> lanVnicTemplateList, Integer projectId) {
		for (LanVnicTemplate lanVnicTemplate : lanVnicTemplateList) {			
			lanVnicTemplate.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (lanVnicTemplate.getId() == 0) {
				lanVnicTemplate.setId(addNew(lanVnicTemplate));
			} else {
				update(lanVnicTemplate);
			}
			
			// Creating entries for Vnic-Vlan mappings
			saveOrUpdateLanVlanVnicMappings(lanVnicTemplate.getLanVnictVlanMappings(),lanVnicTemplate.getId());
		}
		return lanVnicTemplateList;
	}

	@Override
	public void deleteLanVnicTemplate(List<LanVnicTemplate> lanVnicList) {
		deleteAll(lanVnicList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanMgmtIppool> fetchLanMgmtIPpoolConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanMgmtIppool>)(List<?>)listAll(LanMgmtIppool.class, criteria);
	}

	@Override
	public List<LanMgmtIppool> saveOrUpdateLanMgmtIPpoolConfiguration(
			List<LanMgmtIppool> lanMgmtIPpoolList, Integer projectId) {
		for (LanMgmtIppool ippool : lanMgmtIPpoolList) {			
			ippool.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (ippool.getId() == 0) {
				ippool.setId(addNew(ippool));
			} else {
				update(ippool);
			}
		}
		return lanMgmtIPpoolList;
	}

	@Override
	public void deleteLanMgmtIPpool(List<LanMgmtIppool> lanMgmtIPpoolList) {
		deleteAll(lanMgmtIPpoolList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanVnicTemplateVlanMapping> fetchLanVlanVnicMappings(Integer vnicId) {
		Criterion criteria = Restrictions.eq(LANVNICTEMPLATE_ID,vnicId);
		return (List<LanVnicTemplateVlanMapping>)(List<?>)listAll(LanVnicTemplateVlanMapping.class, criteria);
	}

	@Override
	public List<LanVnicTemplateVlanMapping> saveOrUpdateLanVlanVnicMappings(
			List<LanVnicTemplateVlanMapping> vlanVnicMappingsList, Integer vnicId) {
		Criterion criteria = Restrictions.eq(LANVNICTEMPLATE_ID,vnicId);
		deleteByCriteria(LanVnicTemplateVlanMapping.class, criteria);
		LanVnicTemplate lanVnicTemplate = new LanVnicTemplate();
		lanVnicTemplate.setId(vnicId);
		for (LanVnicTemplateVlanMapping lanVnicTemplateVlanMapping : vlanVnicMappingsList) {			
			lanVnicTemplateVlanMapping.setLanVnicTemplate(lanVnicTemplate);
			if (lanVnicTemplateVlanMapping.getId() == 0) {
				lanVnicTemplateVlanMapping.setId(addNew(lanVnicTemplateVlanMapping));
			}
		}
		return vlanVnicMappingsList;
	}

	@Override
	public void deleteLanVlanVnicMappings(List<LanVnicTemplateVlanMapping> vlanVnicMappingsList) {
		deleteAll(vlanVnicMappingsList);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LanQosPolicy> fetchLanQosPolicyConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanQosPolicy>)(List<?>)listAll(LanQosPolicy.class, criteria);
	}

	@Override
	public List<LanQosPolicy> saveOrUpdateQosPolicyConfiguration(
			List<LanQosPolicy> lanQosList, Integer projectId) {
		for (LanQosPolicy qosPolicy : lanQosList) {			
			qosPolicy.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (qosPolicy.getId() == 0) {
				addNew(qosPolicy);
			} else {
				update(qosPolicy);
			}
		}
		return lanQosList;
	}

	@Override
	public void deleteLanQosPolicy(List<LanQosPolicy> lanQosList) {
		deleteAll(lanQosList);		
	}

	@Override
	public List<LanNetworkControlPolicy> saveOrUpdateNetworkControlPolicyConfig(
			List<LanNetworkControlPolicy> lanNetworkControlPolicyList, Integer projectId) {
			for(LanNetworkControlPolicy lanNCP : lanNetworkControlPolicyList){				
				lanNCP.setProjectDetails(commonUtilService.getProjectDetailsObject(projectId));
				if(lanNCP.getId() == 0){
					lanNCP.setId(addNew(lanNCP));
				}else{
					update(lanNCP);
				}
			}
		return lanNetworkControlPolicyList;
	}

	@Override
	public void deleteLanNetworkControlPolicy(
			List<LanNetworkControlPolicy> deletedNetworkControlPilicyList) {
		deleteAll(deletedNetworkControlPilicyList);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanNetworkControlPolicy> fetchLanNetworkControlPolicyConfig(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS, commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanNetworkControlPolicy>)(List<?>)listAll(LanNetworkControlPolicy.class, criteria);
	}

	@Override
	public List<LanEthernetAdapterPolicies> saveOrUpdateLanEthernetAdapterPolicies(
			List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicyList, Integer projectId) {		
		for(LanEthernetAdapterPolicies lanEapObj : lanEthernetAdapterPolicyList){			
			lanEapObj.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (lanEapObj.getId() == 0) {
				lanEapObj.setId(addNew(lanEapObj));
			} else {
				update(lanEapObj);
			}
		}		
		return lanEthernetAdapterPolicyList;
	}

	@Override
	public void deleteLanEthernetAdapterPolicies(
			List<LanEthernetAdapterPolicies> deletedLanEthernetAdapterPolicyList) {		
		deleteAll(deletedLanEthernetAdapterPolicyList);  
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanEthernetAdapterPolicies> fetchLanEthernetAdapterPolicies(Integer projectId) {		
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanEthernetAdapterPolicies>)(List<?>)listAll(LanEthernetAdapterPolicies.class, criteria);	  	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LanVnic> fetchLanVnic(Integer projectId) {		
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<LanVnic>)(List<?>)listAll(LanVnic.class, criteria);
	}

	@Override
	public List<LanVnic> saveOrUpdateLanVnic(List<LanVnic> lanVnicList,
			Integer projectId) {
		for (LanVnic lvnic : lanVnicList) {		
			lvnic.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (lvnic.getId() == 0) {
				lvnic.setId(addNew(lvnic));
			} else {
				update(lvnic);
			}
		}
		return lanVnicList;
	}
	
	@Override
	public void deleteLanVnicProfile(List<LanVnic> lanVnicList) {
		deleteAll(lanVnicList);
	}

	@Override
	public List<LanConnectivityPolicy> fetchLanConnectivityPolicyDetail(Integer projectId) {		
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		@SuppressWarnings("unchecked")
		List<LanConnectivityPolicy> lanConnectivityPolicyList = (List<LanConnectivityPolicy>)(List<?>)listAll(LanConnectivityPolicy.class, criteria);
		
		return lanConnectivityPolicyList;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanLcpVnicMapping> fetchLanLcpVnicMappings(Integer lcpId) {
		Criterion criteria = Restrictions.eq(LANCONNECTIVITYPOLICY_ID, lcpId);    
		return (List<LanLcpVnicMapping>)(List<?>)listAll(LanLcpVnicMapping.class, criteria);		
	}

	@Override
	public List<LanConnectivityPolicy> saveOrUpdateLanConnectivityPolicy(
			List<LanConnectivityPolicy> lanConnectivityPolicyList, Integer projectId) {
		for (LanConnectivityPolicy lcp : lanConnectivityPolicyList) {			
			lcp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (lcp.getId() == 0) {
				lcp.setId(addNew(lcp));
			} else {
				update(lcp);
			}
			saveOrUpdateLanLcpVnicMappings(lcp.getLanLcpVnicMappings(),
					lcp.getId());
	}
		return lanConnectivityPolicyList;		
	}

	@Override
	public List<LanLcpVnicMapping> saveOrUpdateLanLcpVnicMappings(
			List<LanLcpVnicMapping> lanLcpVnicMappingList, Integer lcpId) {
		Criterion criteria = Restrictions.eq(LANCONNECTIVITYPOLICY_ID, lcpId);
		deleteByCriteria(LanLcpVnicMapping.class, criteria);
		LanConnectivityPolicy lanConnectivityPolicy = new LanConnectivityPolicy();
		lanConnectivityPolicy.setId(lcpId);
		for (LanLcpVnicMapping mappingObj : lanLcpVnicMappingList) {			
			mappingObj.setLanConnectivityPolicy(lanConnectivityPolicy);
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
		return lanLcpVnicMappingList;		
	}

	@Override
	public void deleteLanConnectivityPolicy(
			List<LanConnectivityPolicy> deletedLanConnectivityPolicyList) {
		deleteAll(deletedLanConnectivityPolicyList);		
	}
}
