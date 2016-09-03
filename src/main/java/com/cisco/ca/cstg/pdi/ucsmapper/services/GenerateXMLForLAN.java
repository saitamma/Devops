package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.LanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanEthernetAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.LanLcpVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplateVlanMapping;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorEthCompQueueProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorEthFailoverProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorEthInterruptProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorEthOffloadProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorEthRecvQueueProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorEthWorkQueueProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcCdbWorkQueueProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcErrorRecoveryProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcInterruptProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcPortFLogiProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcPortPLogiProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcPortProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcRecvQueueProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorFcWorkQueueProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorHostEthIfProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorHostFcIfProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AdaptorRssProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.DpsecMac;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.EpqosDefinition;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.EpqosEgress;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.MacpoolBlock;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.MacpoolPool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.NwctrlDefinition;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicEther;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicEtherIf;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicLanConnPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicLanConnTempl;

/*
 * @author sgogulap
 * Refactor this class from GenerateXMLForOrg with functionality related,
 *  to LAN features in UCS ADA screen
 */

@Service("generateXMLForLAN")
public class GenerateXMLForLAN extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForLAN.class);
	private ObjectFactory factory;

	@Autowired
	private GenerateXMLForOrg generateXMLForOrg;

	public GenerateXMLForLAN() {
		this.factory = new ObjectFactory();
	}

	public void addMacPoolsForOrg(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForLAN : addMacPoolsForOrg : "
				+ project);
		List<LanMacpool> allLanMacPools = project.getLanMacpools();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(
				project.getOrganizationses(), org.getName());
		List<LanMacpool> lanMacPools = new ArrayList<LanMacpool>();
		updateLanMacPools(allLanMacPools, parentOrg, lanMacPools);
		addMacPoolsToOrg(org, lanMacPools);
		LOGGER.info("End : GenerateXMLForLAN : addMacPoolsForOrg ");
	}

	public void addVnicLanConnTemplForOrg(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForLAN : addVnicLanConnTemplForOrg : "+ project);
		List<LanVnicTemplate> allLanVnicList = project.getLanVnicTemplates();
		List<LanVnicTemplate> lanVnicList = new ArrayList<LanVnicTemplate>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		updateLANVnic(allLanVnicList, lanVnicList, parentOrg);
		addLANVnicTemplateToOrg(org, lanVnicList);
		LOGGER.info("End : GenerateXMLForLAN : addVnicLanConnTemplForOrg");
	}
	
	public void addNWControlPolicy(OrgOrg org, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForLAN : addNWControlPolicy : " + project);
		List<LanNetworkControlPolicy> allLanNetworkControlPolicyList = project.getLanNetworkControlPolicies();
		List<LanNetworkControlPolicy> lanNetworkControlPolicyList = new ArrayList<LanNetworkControlPolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		updateLANNetworkPolicy(allLanNetworkControlPolicyList, lanNetworkControlPolicyList, parentOrg);
		addLANNetworkCtrlPolicyToOrg(org, lanNetworkControlPolicyList);
		LOGGER.info("End : GenerateXMLForLAN : addNWControlPolicy");
	}
	
	public void addAdaptorHostFcProfile(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForLAN : addAdaptorHostFcProfile : " + project);
		List<SanAdapterPolicies> allAdaptorHostProfileList = project.getSanAdapterPolicieses();
		List<SanAdapterPolicies> adaptorHostProfileList =  new ArrayList<SanAdapterPolicies>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for(SanAdapterPolicies sanAdapterPolicies : allAdaptorHostProfileList) {			
				if(sanAdapterPolicies.getOrganizations() != null && sanAdapterPolicies.getOrganizations().getId().equals(parentOrg.getId())) {
					adaptorHostProfileList.add(sanAdapterPolicies);
				}			
		}
		
		for(SanAdapterPolicies sanAdapterPolicies : adaptorHostProfileList) {
			createAdaptorHostFcIfProfile(org, sanAdapterPolicies);
		}
		LOGGER.info("End : GenerateXMLForLAN : addAdaptorHostFcProfile");
	}
	
	public void addQOSPolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForLAN : addQOSPolicy : " + project);
		List<LanQosPolicy> allQosPolicyList = project.getLanQosPolicies();
		List<LanQosPolicy> qosPolicyList = new ArrayList<LanQosPolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		updateLanQosPolicy(allQosPolicyList, qosPolicyList, parentOrg);
		addLanQosPolicyToOrg(org, qosPolicyList);
		LOGGER.info("End : GenerateXMLForLAN : addQOSPolicy");
	}
	
	public void addVnicLanConnPolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForLAN : addVnicLanConnPolicy : " + project);
		List<LanConnectivityPolicy> allLanConnectivityPolicyList = project.getLanConnectivityPolicies();
		List<LanConnectivityPolicy> lanConnectivityPolicyList = new ArrayList<LanConnectivityPolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		updateLanConnPolicy(org, allLanConnectivityPolicyList, lanConnectivityPolicyList, parentOrg);
		LOGGER.info("End : GenerateXMLForLAN : addVnicLanConnPolicy");
	}
	
	public void addAdaptorHostEthIfProfile(OrgOrg org, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForOrg : addAdaptorHostEthIfProfile: " + project);
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		List<LanEthernetAdapterPolicies> adaptorhostethifprofileList = new ArrayList<LanEthernetAdapterPolicies>();
		
		for (LanEthernetAdapterPolicies lanEthernetAdapterPolicies : project.getLanEthernetAdapterPolicieses()) {
			if (validateLanEthernetAdptr(parentOrg, lanEthernetAdapterPolicies)) {
				adaptorhostethifprofileList.add(lanEthernetAdapterPolicies);
			}
		}
		
		for(LanEthernetAdapterPolicies lanAdapterPolicies : adaptorhostethifprofileList) {
			createAdptrHostEthIfProfile(org, lanAdapterPolicies);
			LOGGER.debug("Added LanEthernetAdapterPolicies - AdaptorHostEthIfProfile for {}");
		}
		LOGGER.info("End : GenerateXMLForOrg : adaptorHostEthIfProfile");
	}

	private boolean validateLanEthernetAdptr(Organizations parentOrg,
			LanEthernetAdapterPolicies lanEthernetAdapterPolicies) {
		return lanEthernetAdapterPolicies.getOrganizations() != null
				&& lanEthernetAdapterPolicies.getOrganizations().getId()
						.equals(parentOrg.getId());
	}

	private String createAdptrHostEthIfProfile(OrgOrg org,
			LanEthernetAdapterPolicies lanAdapterPolicies) {
		String name = lanAdapterPolicies.getName();
		AdaptorHostEthIfProfile adaptorHostEthIfProfile = factory.createAdaptorHostEthIfProfile();
		adaptorHostEthIfProfile.setDescr(lanAdapterPolicies.getDescription());
		adaptorHostEthIfProfile.setName(name);
		adaptorHostEthIfProfile.setPolicyOwner(Constants.LOCAL);
		
		createAdptrEthFailover(lanAdapterPolicies, adaptorHostEthIfProfile);
		createAdptrIthOffload(lanAdapterPolicies, adaptorHostEthIfProfile);
		createAdptrEthWorkQueue(lanAdapterPolicies, adaptorHostEthIfProfile);
		createAdptrEthCompQueue(lanAdapterPolicies, adaptorHostEthIfProfile);
		createAdptrEthRecvQueue(lanAdapterPolicies, adaptorHostEthIfProfile);
		createAdptrEthInterrupt(lanAdapterPolicies, adaptorHostEthIfProfile);
		createAdptrRss(lanAdapterPolicies, adaptorHostEthIfProfile);			

		JAXBElement<AdaptorHostEthIfProfile> adaptorHostEthIfProfileElement = factory.createAdaptorHostEthIfProfile(adaptorHostEthIfProfile);
		org.getContent().add(adaptorHostEthIfProfileElement);
		return name;
	}

	private void createAdptrRss(LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorRssProfile adaptorRssProfile = factory.createAdaptorRssProfile();
		adaptorRssProfile.setReceiveSideScaling(lanAdapterPolicies.getReceiveSideScaling());
		JAXBElement<AdaptorRssProfile> adaptorRssProfileElement = factory.createAdaptorRssProfile(adaptorRssProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorRssProfileElement);
	}

	private void createAdptrEthInterrupt(
			LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorEthInterruptProfile adaptorEthInterruptProfile = factory.createAdaptorEthInterruptProfile();
		adaptorEthInterruptProfile.setCoalescingTime(lanAdapterPolicies.getInterruptTimer().toString());
		adaptorEthInterruptProfile.setCoalescingType(lanAdapterPolicies.getInterruptCoalescingType());
		adaptorEthInterruptProfile.setCount(lanAdapterPolicies.getCompletionQueuesInterrupts().toString());
		adaptorEthInterruptProfile.setMode(lanAdapterPolicies.getInterruptMode());
		JAXBElement<AdaptorEthInterruptProfile> adaptorEthInterruptProfileElement = factory.createAdaptorEthInterruptProfile(adaptorEthInterruptProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorEthInterruptProfileElement);
	}

	private void createAdptrEthRecvQueue(
			LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorEthRecvQueueProfile adaptorEthRecvQueueProfile = factory.createAdaptorEthRecvQueueProfile();
		adaptorEthRecvQueueProfile.setCount(lanAdapterPolicies.getReceiveQueues().toString());
		adaptorEthRecvQueueProfile.setRingSize(lanAdapterPolicies.getReceiveQueuesRingSize().toString());
		JAXBElement<AdaptorEthRecvQueueProfile> adaptorEthRecvQueueProfileElement = factory.createAdaptorEthRecvQueueProfile(adaptorEthRecvQueueProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorEthRecvQueueProfileElement);
	}

	private void createAdptrEthCompQueue(
			LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorEthCompQueueProfile adaptorEthCompQueueProfile = factory.createAdaptorEthCompQueueProfile();
		adaptorEthCompQueueProfile.setCount(lanAdapterPolicies.getCompletionQueues().toString());
		JAXBElement<AdaptorEthCompQueueProfile> adaptorEthCompQueueProfileElement = factory.createAdaptorEthCompQueueProfile(adaptorEthCompQueueProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorEthCompQueueProfileElement);
	}

	private void createAdptrEthWorkQueue(
			LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorEthWorkQueueProfile adaptorEthWorkQueueProfile = factory.createAdaptorEthWorkQueueProfile();
		adaptorEthWorkQueueProfile.setCount(lanAdapterPolicies.getTransmitQueues().toString());
		adaptorEthWorkQueueProfile.setRingSize(lanAdapterPolicies.getTransmitQueuesRingSize().toString());
		JAXBElement<AdaptorEthWorkQueueProfile> adaptorEthWorkQueueProfileElement = factory.createAdaptorEthWorkQueueProfile(adaptorEthWorkQueueProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorEthWorkQueueProfileElement);
	}

	private void createAdptrIthOffload(
			LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorEthOffloadProfile adaptorEthOffloadProfile = factory.createAdaptorEthOffloadProfile();
		adaptorEthOffloadProfile.setTcpRxChecksum(lanAdapterPolicies.getReceiveChecksumOffload());
		adaptorEthOffloadProfile.setTcpSegment(lanAdapterPolicies.getTcpSegmentationOffload());
		adaptorEthOffloadProfile.setTcpTxChecksum(lanAdapterPolicies.getTransmitChecksumOffload());
		adaptorEthOffloadProfile.setLargeReceive(lanAdapterPolicies.getTcpLargeReceiveOffload());
		JAXBElement<AdaptorEthOffloadProfile> adaptorEthOffloadProfileElement = factory.createAdaptorEthOffloadProfile(adaptorEthOffloadProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorEthOffloadProfileElement);
	}

	private void createAdptrEthFailover(
			LanEthernetAdapterPolicies lanAdapterPolicies,
			AdaptorHostEthIfProfile adaptorHostEthIfProfile) {
		AdaptorEthFailoverProfile adaptorEthFailoverProfile = factory.createAdaptorEthFailoverProfile();
		adaptorEthFailoverProfile.setTimeout(lanAdapterPolicies.getFailbackTimeout().toString());
		JAXBElement<AdaptorEthFailoverProfile> adaptorEthFailoverProfileElement = factory.createAdaptorEthFailoverProfile(adaptorEthFailoverProfile);
		adaptorHostEthIfProfile.getContent().add(adaptorEthFailoverProfileElement);
	}

	private void updateLanConnPolicy(OrgOrg org,
			List<LanConnectivityPolicy> allLanConnectivityPolicyList,
			List<LanConnectivityPolicy> lanConnectivityPolicyList,
			Organizations parentOrg) {
		for (LanConnectivityPolicy lanConnectivityPolicy : allLanConnectivityPolicyList) {
			if (validateLanConnPolicy(parentOrg, lanConnectivityPolicy)) {
				lanConnectivityPolicyList.add(lanConnectivityPolicy);
			}
		}

		for (LanConnectivityPolicy lanConnectivityPolicy : lanConnectivityPolicyList) {
			String name = lanConnectivityPolicy.getName();
			VnicLanConnPolicy vnicLanConnPolicy = prepareVnicLanConnPolicy(lanConnectivityPolicy, name);
			JAXBElement<VnicLanConnPolicy> vnicLanConnPolicyElement = factory.createVnicLanConnPolicy(vnicLanConnPolicy);
			org.getContent().add(vnicLanConnPolicyElement);
			LOGGER.debug("Added LanConnectivityPolicy - VnicLanConnPolicy for {}", name);
		}
	}

	private boolean validateLanConnPolicy(Organizations parentOrg,
			LanConnectivityPolicy lanConnectivityPolicy) {
		return null !=lanConnectivityPolicy.getOrganizations() && parentOrg.getId().equals(lanConnectivityPolicy.getOrganizations().getId());
	}

	private VnicLanConnPolicy prepareVnicLanConnPolicy(
			LanConnectivityPolicy lanConnectivityPolicy, String name) {
		VnicLanConnPolicy vnicLanConnPolicy = factory.createVnicLanConnPolicy();
		vnicLanConnPolicy.setDescr(lanConnectivityPolicy.getDescription());
		vnicLanConnPolicy.setName(name);
		vnicLanConnPolicy.setPolicyOwner(Constants.LOCAL);
		int i=0;
		
		List<LanLcpVnicMapping> lanLcpVnicMappingList = lanConnectivityPolicy.getLanLcpVnicMappings();
		for(LanLcpVnicMapping lanLcpVnicMapping : lanLcpVnicMappingList) {
			i++;
			VnicEther vnicEther = prepateVnicEtherIf(i, lanLcpVnicMapping);
			
			JAXBElement<VnicEther> vnicEtherElement = factory.createVnicEther(vnicEther);
			vnicLanConnPolicy.getContent().add(vnicEtherElement);
		}
		return vnicLanConnPolicy;
	}

	private VnicEther prepateVnicEtherIf(int i,
			LanLcpVnicMapping lanLcpVnicMapping) {
		VnicEther vnicEther= factory.createVnicEther();
		LanVnic lanVnic =  (LanVnic) findById(LanVnic.class, lanLcpVnicMapping.getLanVnic().getId());
		vnicEther.setAdaptorProfileName(lanVnic.getLanEthernetAdapterPolicies() != null ? lanVnic.getLanEthernetAdapterPolicies().getName() : "");
		
		LanMacpool macPool = null;
		LanNetworkControlPolicy nwcontrPolicy = null;
		LanQosPolicy qosPolicy = null;
		
		if(lanVnic.getLanVnicTemplate() != null){
		
			if(lanVnic.getLanVnicTemplate().getLanMacpool() != null){
				macPool = lanVnic.getLanVnicTemplate().getLanMacpool();
				
				if(macPool != null){
					vnicEther.setIdentPoolName(macPool.getMacpoolName());
				} else {
					vnicEther.setIdentPoolName("");
				}
			}
			if(lanVnic.getLanVnicTemplate().getLanNetworkControlPolicy() != null){
				nwcontrPolicy = lanVnic.getLanVnicTemplate().getLanNetworkControlPolicy();
				
				if(nwcontrPolicy != null){
					vnicEther.setNwCtrlPolicyName(nwcontrPolicy.getNcpName());
				}else {
					vnicEther.setNwCtrlPolicyName("");
				}
			}
			if(lanVnic.getLanVnicTemplate().getLanQosPolicy() != null){
				qosPolicy =  lanVnic.getLanVnicTemplate().getLanQosPolicy();
			
				if(qosPolicy != null) {
					vnicEther.setQosPolicyName(qosPolicy.getName());
				} else {
					vnicEther.setQosPolicyName(""); 
				}
			}
			
			List<LanVnicTemplateVlanMapping> vlanList = lanVnic.getLanVnicTemplate().getLanVnictVlanMappings();
			
			for(LanVnicTemplateVlanMapping obj: vlanList){
				LanVlan lanVlan = obj.getLanVlan();
				VnicEtherIf vnicEtherIf = factory.createVnicEtherIf();
				vnicEtherIf.setDefaultNet(Constants.NO);
				vnicEtherIf.setName(lanVlan.getVlanName());
				
				JAXBElement<VnicEtherIf> vnicEtherIfJE = factory.createVnicEtherIf(vnicEtherIf);
				vnicEther.getContent().add(vnicEtherIfJE);
			}
			
			vnicEther.setNwTemplName(lanVnic.getLanVnicTemplate().getVnictName());
		}
		vnicEther.setName(lanVnic.getName());
		
		vnicEther.setAddr(Constants.DERIVED);
		vnicEther.setAdminVcon(Constants.ANY);
		vnicEther.setOrder(Integer.toString(i));
		vnicEther.setPinToGroupName("");
		vnicEther.setStatsPolicyName(Constants.DEFAULT);
		vnicEther.setSwitchId(lanVnic.getLanVnicTemplate().getSwitchId());
		vnicEther.setMtu("1500");
		return vnicEther;
	}

	private void addLanQosPolicyToOrg(OrgOrg org, List<LanQosPolicy> qosPolicyList) {
		for(LanQosPolicy lanQosPolicy : qosPolicyList) {
			createEpqosDefinition(org, lanQosPolicy);
			LOGGER.debug("Added LanQosPolicy - EpqosDefinition for {}");
		}
	}

	private String createEpqosDefinition(OrgOrg org, LanQosPolicy lanQosPolicy) {
		EpqosDefinition epqosDefinition = factory.createEpqosDefinition();
		String name = lanQosPolicy.getName();
		epqosDefinition.setName(name);
		epqosDefinition.setDescr("");
		epqosDefinition.setPolicyOwner(Constants.LOCAL);
		createEpqosEgress(lanQosPolicy, epqosDefinition);
		
		JAXBElement<EpqosDefinition> epqosDefinitionElement = factory.createEpqosDefinition(epqosDefinition);
		org.getContent().add(epqosDefinitionElement);
		return name;
	}

	private void createEpqosEgress(LanQosPolicy lanQosPolicy,
			EpqosDefinition epqosDefinition) {
		EpqosEgress epqosEgress = factory.createEpqosEgress();
		epqosEgress.setBurst(lanQosPolicy.getBurst().toString());
		epqosEgress.setHostControl(lanQosPolicy.getHostControl());
		epqosEgress.setName("");
		epqosEgress.setPrio(lanQosPolicy.getPriority());
		epqosEgress.setRate(lanQosPolicy.getRate());
		JAXBElement<EpqosEgress> epqosEgressElement = factory.createEpqosEgress(epqosEgress);
		epqosDefinition.getContent().add(epqosEgressElement);
	}

	private void updateLanQosPolicy(List<LanQosPolicy> allQosPolicyList,
			List<LanQosPolicy> qosPolicyList, Organizations parentOrg) {
		for(LanQosPolicy lanQosPolicy : allQosPolicyList) {			
				if(lanQosPolicy.getOrganizations() != null && lanQosPolicy.getOrganizations().getId().equals(parentOrg.getId())) {
					qosPolicyList.add(lanQosPolicy);
				}
			}		
	}

	private void createAdaptorHostFcIfProfile(OrgOrg org, SanAdapterPolicies sanAdapterPolicies) {
		AdaptorHostFcIfProfile adaptorHostFcIfProfile = createAdaptorHostFcIfProfile(sanAdapterPolicies);
		createChildstoAdaptorHost(sanAdapterPolicies, adaptorHostFcIfProfile);
		JAXBElement<AdaptorHostFcIfProfile> adaptorHostFcIfProfileElement = factory.createAdaptorHostFcIfProfile(adaptorHostFcIfProfile);
		org.getContent().add(adaptorHostFcIfProfileElement);
	}

	private void createChildstoAdaptorHost(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		createAdaptorFcCdbWorkQProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcPortPLogiProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcPortFLogiProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcErrorRecoverProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcWorkQProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcRecvQProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcPortProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
		createAdaptorFcInterruptProfile(sanAdapterPolicies, adaptorHostFcIfProfile);
	}

	private void createAdaptorFcInterruptProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcInterruptProfile adaptorFcInterruptProfile = factory.createAdaptorFcInterruptProfile();
		adaptorFcInterruptProfile.setMode(sanAdapterPolicies.getInterruptMode());
		adaptorFcInterruptProfile.setRn("fc-int");
		JAXBElement<AdaptorFcInterruptProfile> adaptorFcInterruptProfileElement = factory.createAdaptorFcInterruptProfile(adaptorFcInterruptProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcInterruptProfileElement);
	}

	private void createAdaptorFcPortProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcPortProfile adaptorFcPortProfile = factory.createAdaptorFcPortProfile();
		adaptorFcPortProfile.setIoThrottleCount(sanAdapterPolicies.getIoThrottleCount().toString());
		adaptorFcPortProfile.setLunsPerTarget(sanAdapterPolicies.getLunsPerTarget().toString());
		adaptorFcPortProfile.setRn("fc-port");
		JAXBElement<AdaptorFcPortProfile> adaptorFcPortProfileElement = factory.createAdaptorFcPortProfile(adaptorFcPortProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcPortProfileElement);
	}

	private void createAdaptorFcRecvQProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcRecvQueueProfile adaptorFcRecvQueueProfile = factory.createAdaptorFcRecvQueueProfile();
		adaptorFcRecvQueueProfile.setRingSize(sanAdapterPolicies.getReceiveQueuesRingSize().toString());
		adaptorFcRecvQueueProfile.setRn("fc-rcv-q");
		JAXBElement<AdaptorFcRecvQueueProfile> adaptorFcRecvQueueProfileElement = factory.createAdaptorFcRecvQueueProfile(adaptorFcRecvQueueProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcRecvQueueProfileElement);
	}

	private void createAdaptorFcWorkQProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcWorkQueueProfile adaptorFcWorkQueueProfile = factory.createAdaptorFcWorkQueueProfile();
		adaptorFcWorkQueueProfile.setRingSize(sanAdapterPolicies.getTransmitQueuesRingSize().toString());
		adaptorFcWorkQueueProfile.setRn("fc-work-q");
		JAXBElement<AdaptorFcWorkQueueProfile> adaptorFcWorkQueueProfileElement = factory.createAdaptorFcWorkQueueProfile(adaptorFcWorkQueueProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcWorkQueueProfileElement);
	}

	private void createAdaptorFcErrorRecoverProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcErrorRecoveryProfile adaptorFcErrorRecoveryProfile = factory.createAdaptorFcErrorRecoveryProfile();
		adaptorFcErrorRecoveryProfile.setFcpErrorRecovery(sanAdapterPolicies.getFcpErrorRecovery());
		adaptorFcErrorRecoveryProfile.setLinkDownTimeout(sanAdapterPolicies.getLinkDownTimeout().toString());
		adaptorFcErrorRecoveryProfile.setPortDownIoRetryCount(sanAdapterPolicies.getPortDownIoRetry().toString());
		adaptorFcErrorRecoveryProfile.setPortDownTimeout(sanAdapterPolicies.getPortDownTimeout().toString());
		adaptorFcErrorRecoveryProfile.setRn("fc-err-rec");
		JAXBElement<AdaptorFcErrorRecoveryProfile> adaptorFcErrorRecoveryProfileElement = factory.createAdaptorFcErrorRecoveryProfile(adaptorFcErrorRecoveryProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcErrorRecoveryProfileElement);
	}

	private void createAdaptorFcPortFLogiProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcPortFLogiProfile adaptorFcPortFLogiProfile = factory.createAdaptorFcPortFLogiProfile();
		adaptorFcPortFLogiProfile.setRetries(sanAdapterPolicies.getFlogiRetries().toString());
		adaptorFcPortFLogiProfile.setTimeout(sanAdapterPolicies.getFlogiTimeout().toString());
		adaptorFcPortFLogiProfile.setRn("fc-port-flogi");
		JAXBElement<AdaptorFcPortFLogiProfile> adaptorFcPortFLogiProfileElement = factory.createAdaptorFcPortFLogiProfile(adaptorFcPortFLogiProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcPortFLogiProfileElement);
	}

	private void createAdaptorFcPortPLogiProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcPortPLogiProfile adaptorFcPortPLogiProfile = factory.createAdaptorFcPortPLogiProfile();
		adaptorFcPortPLogiProfile.setRetries(sanAdapterPolicies.getPlogiRetries().toString());
		adaptorFcPortPLogiProfile.setTimeout(sanAdapterPolicies.getPlogiTimeout().toString());
		adaptorFcPortPLogiProfile.setRn("fc-port-plogi");
		JAXBElement<AdaptorFcPortPLogiProfile> adaptorFcPortPLogiProfileElement = factory.createAdaptorFcPortPLogiProfile(adaptorFcPortPLogiProfile);
		adaptorHostFcIfProfile.getContent().add(adaptorFcPortPLogiProfileElement);
	}

	private void createAdaptorFcCdbWorkQProfile(
			SanAdapterPolicies sanAdapterPolicies,
			AdaptorHostFcIfProfile adaptorHostFcIfProfile) {
		AdaptorFcCdbWorkQueueProfile adaptorFcCdbWorkQueueProfile = factory.createAdaptorFcCdbWorkQueueProfile();
		adaptorFcCdbWorkQueueProfile.setCount(sanAdapterPolicies.getScsiIoQueues().toString());
		adaptorFcCdbWorkQueueProfile.setRingSize(sanAdapterPolicies.getScsiIoQueuesRingSize().toString());
		adaptorFcCdbWorkQueueProfile.setRn("fc-cdb-work-q");
		JAXBElement<AdaptorFcCdbWorkQueueProfile> epqosDefinitionElement = factory.createAdaptorFcCdbWorkQueueProfile(adaptorFcCdbWorkQueueProfile);
		adaptorHostFcIfProfile.getContent().add(epqosDefinitionElement);
	}

	private AdaptorHostFcIfProfile createAdaptorHostFcIfProfile(
			SanAdapterPolicies sanAdapterPolicies) {
		String name = sanAdapterPolicies.getName();
		AdaptorHostFcIfProfile adaptorHostFcIfProfile = factory.createAdaptorHostFcIfProfile();
		adaptorHostFcIfProfile.setDescr(sanAdapterPolicies.getDescription());
		adaptorHostFcIfProfile.setName(name);
		adaptorHostFcIfProfile.setPolicyOwner(Constants.LOCAL);
		return adaptorHostFcIfProfile;
	}

	private void addLANNetworkCtrlPolicyToOrg(OrgOrg org,
			List<LanNetworkControlPolicy> lanNetworkControlPolicyList) {
		for(LanNetworkControlPolicy networkControlPolicy : lanNetworkControlPolicyList){
			String name = addNwCtrlDefinition(org, networkControlPolicy);
			LOGGER.debug("Added LanNetworkControlPolicy - NwctrlDefinition for {}", name);
		}
	}

	private String addNwCtrlDefinition(OrgOrg org, LanNetworkControlPolicy networkControlPolicy) {
		String name = networkControlPolicy.getNcpName();
		NwctrlDefinition nwctrlDefinition = createNwCtrlDef(networkControlPolicy, name);
		DpsecMac dpsecMac = createDpSecMac(networkControlPolicy);
		JAXBElement<DpsecMac> dpsecMacElement = factory.createDpsecMac(dpsecMac);
		nwctrlDefinition.getContent().add(dpsecMacElement);
			
		JAXBElement<NwctrlDefinition> nwCtrlDefElement = factory.createNwctrlDefinition(nwctrlDefinition);
		org.getContent().add(nwCtrlDefElement);
		return name;
	}

	private NwctrlDefinition createNwCtrlDef(
			LanNetworkControlPolicy networkControlPolicy, String name) {
		NwctrlDefinition nwctrlDefinition = factory.createNwctrlDefinition();
		nwctrlDefinition.setCdp(networkControlPolicy.getCdp());
		nwctrlDefinition.setDescr(networkControlPolicy.getDescription());
		nwctrlDefinition.setMacRegisterMode(networkControlPolicy.getMacRegisterMode());
		nwctrlDefinition.setName(name);
		nwctrlDefinition.setUplinkFailAction(networkControlPolicy.getUplinkFailAction());
		nwctrlDefinition.setPolicyOwner(Constants.LOCAL);
		return nwctrlDefinition;
	}

	private DpsecMac createDpSecMac(LanNetworkControlPolicy networkControlPolicy) {
		DpsecMac dpsecMac = factory.createDpsecMac();
		dpsecMac.setForge(networkControlPolicy.getForge());
		dpsecMac.setDescr("");
		dpsecMac.setName("");
		dpsecMac.setPolicyOwner(Constants.LOCAL);
		return dpsecMac;
	}

	private void updateLANNetworkPolicy(List<LanNetworkControlPolicy> allLanNetworkControlPolicyList,
			List<LanNetworkControlPolicy> lanNetworkControlPolicyList,
			Organizations parentOrg) {
		for(LanNetworkControlPolicy lanNetworkControlPolicy : allLanNetworkControlPolicyList) {			
				if(lanNetworkControlPolicy.getOrganizations() != null && lanNetworkControlPolicy.getOrganizations().getId().equals(parentOrg.getId())){
					lanNetworkControlPolicyList.add(lanNetworkControlPolicy);
				}
			}		
	}

	private void addLANVnicTemplateToOrg(OrgOrg org, List<LanVnicTemplate> lanVnicList) {
		for (LanVnicTemplate lanVnict : lanVnicList) {
			String name = lanVnict.getVnictName();
			VnicLanConnTempl vnicLanConnTempl = factory.createVnicLanConnTempl();
			udpateVnicLanTemplate(lanVnict, name, vnicLanConnTempl);
			updateLanVnicTemplateMappings(lanVnict, vnicLanConnTempl);

			JAXBElement<VnicLanConnTempl> vnicLanConnTemplJE = factory.createVnicLanConnTempl(vnicLanConnTempl);
			org.getContent().add(vnicLanConnTemplJE);
			LOGGER.debug("Added LanVnic - VnicLanConnTempl for {}", name);
		}
	}

	private void updateLanVnicTemplateMappings(LanVnicTemplate lanVnict,
			VnicLanConnTempl vnicLanConnTempl) {
		List<LanVnicTemplateVlanMapping> mapping = lanVnict.getLanVnictVlanMappings();
		for (LanVnicTemplateVlanMapping lanVnicTemplateVlanMapping : mapping) {
			VnicEtherIf vnicEtherIf = factory.createVnicEtherIf();
			vnicEtherIf.setDefaultNet(Constants.NO);
			vnicEtherIf.setName(lanVnicTemplateVlanMapping.getLanVlan().getVlanName());

			JAXBElement<VnicEtherIf> vnicEtherIfJE = factory.createVnicEtherIf(vnicEtherIf);
			vnicLanConnTempl.getContent().add(vnicEtherIfJE);
		}
	}

	private void udpateVnicLanTemplate(LanVnicTemplate lanVnict, String name,
			VnicLanConnTempl vnicLanConnTempl) {
		vnicLanConnTempl.setDescr((Util.isStringNotEmpty(lanVnict
				.getDescription())) ? lanVnict.getDescription() : "");

		if (lanVnict.getLanMacpool() != null) {
			LanMacpool lanMacpool = (LanMacpool) findById(LanMacpool.class,
					lanVnict.getLanMacpool().getId());
			vnicLanConnTempl
					.setIdentPoolName((lanMacpool != null) ? lanMacpool
							.getMacpoolName() : "");
		}

		vnicLanConnTempl.setMtu("1500");
		vnicLanConnTempl.setName(name);
		vnicLanConnTempl.setPinToGroupName("");
		vnicLanConnTempl.setPolicyOwner(Constants.LOCAL);
		vnicLanConnTempl.setStatsPolicyName(Constants.DEFAULT);
		vnicLanConnTempl.setSwitchId(lanVnict.getSwitchId());
		vnicLanConnTempl.setTarget(lanVnict.getTarget());
		vnicLanConnTempl.setTemplType(lanVnict.getTemplateType().trim());

		LanNetworkControlPolicy networkControlPolicy = lanVnict
				.getLanNetworkControlPolicy();
		LanQosPolicy lanQosPolicy = lanVnict.getLanQosPolicy();

		vnicLanConnTempl
				.setNwCtrlPolicyName((networkControlPolicy == null) ? ""
						: networkControlPolicy.getNcpName());
		vnicLanConnTempl.setQosPolicyName((lanQosPolicy == null) ? ""
				: lanQosPolicy.getName());
	}

	private void updateLANVnic(List<LanVnicTemplate> allLanVnicList,
			List<LanVnicTemplate> lanVnicList, Organizations parentOrg) {
		if (allLanVnicList != null) {
			for (LanVnicTemplate lanVnicTemplate : allLanVnicList) {
				if (lanVnicTemplate.getOrganizations() != null
						&& (lanVnicTemplate.getOrganizations().getId()
								.equals(parentOrg.getId()))) {
					lanVnicList.add(lanVnicTemplate);
				}
			}
		}
	}

	private void addMacPoolsToOrg(OrgOrg org, List<LanMacpool> lanMacPools) {
		for (LanMacpool lanMacPool : lanMacPools) {
			if (!generateXMLForOrg.isDefault(lanMacPool.getMacpoolName())) {
				MacpoolPool macpoolPool = factory.createMacpoolPool();
				String name = lanMacPool.getMacpoolName();
				macpoolPool.setAssignmentOrder(lanMacPool.getAssignmentOrder());
				if (lanMacPool.getMacpoolDescription() != null
						&& !lanMacPool.getMacpoolDescription().isEmpty()) {
					macpoolPool.setDescr(lanMacPool.getMacpoolDescription());
				}
				macpoolPool.setName(name);
				macpoolPool.setPolicyOwner(Constants.LOCAL);

				String fromAdd = lanMacPool.getFromAddress();
				String toAdd = lanMacPool.getToAddress();
				if (fromAdd != null && toAdd != null) {
					MacpoolBlock macpoolBlock = factory.createMacpoolBlock();
					macpoolBlock.setFrom(fromAdd);
					macpoolBlock.setTo(toAdd);

					JAXBElement<MacpoolBlock> macpoolBlockJE = factory
							.createMacpoolBlock(macpoolBlock);
					macpoolPool.getContent().add(macpoolBlockJE);
				}

				JAXBElement<MacpoolPool> macpoolPoolJE = factory
						.createMacpoolPool(macpoolPool);
				org.getContent().add(macpoolPoolJE);
				LOGGER.debug("Added LanMacpool - MacpoolPool for {}", name);
			}
		}
	}

	private void updateLanMacPools(List<LanMacpool> allLanMacPools,
			Organizations parentOrg, List<LanMacpool> lanMacPools) {
		if (allLanMacPools != null) {
			for (LanMacpool lanMacpool : allLanMacPools) {				
					if (lanMacpool.getOrganizations() != null && lanMacpool.getOrganizations().getId()
							.equals(parentOrg.getId())) {
						lanMacPools.add(lanMacpool);
					}
				}			
		}
	}
}
