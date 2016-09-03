package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FcpoolInitiators;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicFc;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicFcIf;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicFcNode;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicSanConnPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicSanConnTempl;

@Service("generateXMLForSAN")
public class GenerateXMLForSAN extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForLAN.class);
	private ObjectFactory factory;
	
	@Autowired
	private GenerateXMLForOrg generateXMLForOrg;

	public GenerateXMLForSAN() {
		this.factory = new ObjectFactory();
	}
	
	public void addVnicSanConnTemplForOrg(OrgOrg org, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForSAN : addVnicSanConnTemplForOrg : " + project);
		List<SanVhbaTemplate> sanVhbaList = new ArrayList<SanVhbaTemplate>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		if(project.getSanVhbaTemplates() != null){
			for(SanVhbaTemplate sanVhbaTemplate : project.getSanVhbaTemplates()){				
					if(null != sanVhbaTemplate.getOrganizations() && sanVhbaTemplate.getOrganizations().getId().equals(parentOrg.getId())) {
				 		sanVhbaList.add(sanVhbaTemplate);
				 	}
				}			
		}
		
		for(SanVhbaTemplate sanVhbaTemplate : sanVhbaList){
			LanQosPolicy lanQosPolicy = sanVhbaTemplate.getLanQosPolicy();
			
			VnicSanConnTempl vnicSanConnTempl = createVniSanConnTemp(sanVhbaTemplate, lanQosPolicy);
			JAXBElement<VnicSanConnTempl> vnicSanConnTemplJE = factory.createVnicSanConnTempl(vnicSanConnTempl);
			org.getContent().add(vnicSanConnTemplJE);
		}
		LOGGER.info("End : GenerateXMLForSAN : addVnicSanConnTemplForOrg");
	}
	
	public void addFcpoolInitiatorsForWwnn(OrgOrg org, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForSAN : addFcpoolInitiatorsForWwnn :" + project);
		List<SanWwnn> allsanWwnnList = project.getSanWwnns();
		List<SanWwnn> sanWwnnList =  new ArrayList<SanWwnn>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		if (allsanWwnnList != null) {
			for (SanWwnn sanWwnn : allsanWwnnList) {
				if (sanWwnn.getOrganizations() != null
						&& sanWwnn.getOrganizations().getId()
								.equals(parentOrg.getId())) {
					sanWwnnList.add(sanWwnn);
				}
			}
		}
		
		for(SanWwnn sanWwnn : sanWwnnList){
			String name = sanWwnn.getWwnnName();
			addFcpoolInitiators(org, name, sanWwnn.getDescription(), sanWwnn.getFromAddress(), sanWwnn.getToAddress(), "node-wwn-assignment",sanWwnn.getAssignmentOrder());
			LOGGER.debug("Added SanWwnn - FcpoolInitiators for {}",name);
		}
		LOGGER.info("End : GenerateXMLForSAN : addFcpoolInitiatorsForWwnn");
	}
	
	public void addFcpoolInitiatorsForWwpn(OrgOrg org, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForSAN : addFcpoolInitiatorsForWwpn :" + project);
		List<SanWwpn> allsanWwpnList = project.getSanWwpns();
		List<SanWwpn> sanWwpnList =  new ArrayList<SanWwpn>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		if(allsanWwpnList != null){
			for(SanWwpn sanWwpn : allsanWwpnList) {				
					if(sanWwpn.getOrganizations() != null && sanWwpn.getOrganizations().getId().equals(parentOrg.getId())){
						sanWwpnList.add(sanWwpn);
					}
				}			
		}
		
		for(SanWwpn sanWwpn : sanWwpnList){
			String name = sanWwpn.getWwpnName();
			addFcpoolInitiators(org, name, sanWwpn.getDescription(), sanWwpn.getFromAddress(), sanWwpn.getToAddress(), "port-wwn-assignment", sanWwpn.getAssignmentOrder());
			LOGGER.debug("Added SanWwpn - FcpoolInitiators for {}",name);
		}
		LOGGER.info("End : GenerateXMLForSAN : addFcpoolInitiatorsForWwpn");
	}
	
	public void addVnicSanConnPolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForSAN : addVnicSanConnPolicy : " + project);
		List<SanConnectivityPolicy> sanConnectivityPolicyList = new ArrayList<SanConnectivityPolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		 		
		 for(SanConnectivityPolicy sanConnectivityPolicy : project.getSanConnectivityPolicies()){			 
				 if(sanConnectivityPolicy.getOrganizations() != null && sanConnectivityPolicy.getOrganizations().getId().equals(parentOrg.getId())) {
				 		sanConnectivityPolicyList.add(sanConnectivityPolicy);
				 	}			 
		 }
		 
		for(SanConnectivityPolicy sanConnectivityPolicy : sanConnectivityPolicyList) {			
			createVnicSanConnPolicy(org, sanConnectivityPolicy);
		}
		LOGGER.info("End : GenerateXMLForSAN : addVnicSanConnPolicy");
	}

	private void createVnicSanConnPolicy(OrgOrg org, SanConnectivityPolicy sanConnectivityPolicy) {
		VnicSanConnPolicy vnicSanConnPolicy = factory.createVnicSanConnPolicy();
		String name = sanConnectivityPolicy.getName();
		vnicSanConnPolicy.setDescr(sanConnectivityPolicy.getDescription());
		vnicSanConnPolicy.setName(name);
		vnicSanConnPolicy.setPolicyOwner(Constants.LOCAL);
		int i=0;
		
		List<SanScpVhbaMapping> sanScpVhbaMappingList = sanConnectivityPolicy.getSanScpVhbaMappings();
		for(SanScpVhbaMapping sanScpVhbaMapping : sanScpVhbaMappingList) {
			i++;
			createVnicFc(vnicSanConnPolicy, i, sanScpVhbaMapping);
		}
		
		createVnicFcNode(sanConnectivityPolicy, vnicSanConnPolicy);
		JAXBElement<VnicSanConnPolicy> vnicSanConnPolicyElement = factory.createVnicSanConnPolicy(vnicSanConnPolicy);
		org.getContent().add(vnicSanConnPolicyElement);
		LOGGER.debug("Added SanConnectivityPolicy - VnicSanConnPolicy for {}", name);
	}

	private void createVnicFcNode(SanConnectivityPolicy sanConnectivityPolicy,
			VnicSanConnPolicy vnicSanConnPolicy) {
		VnicFcNode vnicFcNode = factory.createVnicFcNode();
		vnicFcNode.setAddr("pool-derived");
		vnicFcNode.setIdentPoolName(sanConnectivityPolicy == null ? "":sanConnectivityPolicy.getSanWwnn() == null?"":sanConnectivityPolicy.getSanWwnn().getWwnnName());
		JAXBElement<VnicFcNode> vnicFcNodeElement = factory.createVnicFcNode(vnicFcNode);
		vnicSanConnPolicy.getContent().add(vnicFcNodeElement);
	}

	private void createVnicFc(VnicSanConnPolicy vnicSanConnPolicy, int i,
			SanScpVhbaMapping sanScpVhbaMapping) {
		VnicFc vnicFc = factory.createVnicFc();
		VnicFcIf vnicFcIf = factory.createVnicFcIf();
		SanVhba sanVhba =  sanScpVhbaMapping.getSanVhba();
		LanQosPolicy qosPolicy = null;
		
		if(sanVhba.getSanAdapterPolicies() != null){
			vnicFc.setAdaptorProfileName(sanVhba.getSanAdapterPolicies().getName());
		}
		
		if(sanVhba.getSanVhbaTemplate() != null) {
			qosPolicy =  sanVhba.getSanVhbaTemplate().getLanQosPolicy();
			if(qosPolicy != null) {
				vnicFc.setQosPolicyName(qosPolicy.getName());
			} else {
				vnicFc.setQosPolicyName(""); 
			}
			
			SanVsan sanVsan =  sanVhba.getSanVhbaTemplate().getSanVsan();
			vnicFcIf.setName(sanVsan.getVsanName());
			SanWwpn sanwwPn =  sanVhba.getSanVhbaTemplate().getSanWwpn();
			
			if(sanwwPn != null) {
				vnicFc.setIdentPoolName(sanwwPn.getWwpnName());
			} else {
				vnicFc.setIdentPoolName(""); 
			}
			
			vnicFc.setNwTemplName(sanVhba.getSanVhbaTemplate().getVhbaName());
		}

		vnicFc.setName(sanVhba.getName());
		vnicFc.setAdaptorProfileName(sanVhba.getSanAdapterPolicies() == null ? "":sanVhba.getSanAdapterPolicies().getName());
		vnicFc.setAddr(Constants.DERIVED);
		vnicFc.setAdminVcon(Constants.ANY);
		vnicFc.setMaxDataFieldSize("2048");
		vnicFc.setOrder(Integer.toString(i));
		vnicFc.setPersBind(Constants.DISABLED);
		vnicFc.setPersBindClear(Constants.NO);
		vnicFc.setPinToGroupName("");
		vnicFc.setStatsPolicyName(Constants.DEFAULT);
		vnicFc.setSwitchId("A");
		vnicFcIf.setRn("if-default");
		JAXBElement<VnicFcIf> vnicFcIfElement = factory.createVnicFcIf(vnicFcIf);
		vnicFc.getContent().add(vnicFcIfElement);
		JAXBElement<VnicFc> vnicFcElement = factory.createVnicFc(vnicFc);
		vnicSanConnPolicy.getContent().add(vnicFcElement);
	}
	
	private void addFcpoolInitiators(OrgOrg org, String name, String description, String from, String to, String purpose, String assignmentOrder){
		LOGGER.info("Start : GenerateXMLForSAN : addFcpoolInitiators");
		if(!generateXMLForOrg.isDefault(name)){
			FcpoolInitiators fcpoolInitiators = factory.createFcpoolInitiators();
			fcpoolInitiators.setAssignmentOrder(assignmentOrder);
			fcpoolInitiators.setDescr((Util.isStringNotEmpty(description))?description:"");
			fcpoolInitiators.setMaxPortsPerNode("upto3");
			fcpoolInitiators.setName(name);
			fcpoolInitiators.setPolicyOwner(Constants.LOCAL);
			fcpoolInitiators.setPurpose(purpose);
			
			if(from != null && to != null){
				generateXMLForOrg.addFcpoolBlock(fcpoolInitiators, from, to);
			}
			
			JAXBElement<FcpoolInitiators> fcpoolInitiatorsJE = factory.createFcpoolInitiators(fcpoolInitiators);
			org.getContent().add(fcpoolInitiatorsJE);
		}
		LOGGER.info("End : GenerateXMLForSAN : addFcpoolInitiators");
	}
	
	private VnicSanConnTempl createVniSanConnTemp(SanVhbaTemplate sanVhbaTemplate, LanQosPolicy lanQosPolicy) {
		String name = sanVhbaTemplate.getVhbaName(); 
		VnicSanConnTempl vnicSanConnTempl = factory.createVnicSanConnTempl();
		vnicSanConnTempl.setDescr((Util.isStringNotEmpty(sanVhbaTemplate.getDescription()))?sanVhbaTemplate.getDescription():"");
		SanWwpn sanWwpn = sanVhbaTemplate.getSanWwpn();
		vnicSanConnTempl.setIdentPoolName((sanWwpn != null)?sanWwpn.getWwpnName():"");
		vnicSanConnTempl.setMaxDataFieldSize("2048");
		vnicSanConnTempl.setName(name);
		vnicSanConnTempl.setPinToGroupName("");
		vnicSanConnTempl.setPolicyOwner(Constants.LOCAL);
		vnicSanConnTempl.setQosPolicyName((lanQosPolicy==null)?"":lanQosPolicy.getName());
		vnicSanConnTempl.setStatsPolicyName(Constants.DEFAULT);
		vnicSanConnTempl.setSwitchId(sanVhbaTemplate.getSwitchId());
		vnicSanConnTempl.setTemplType(sanVhbaTemplate.getTemplateType());
    
		if(sanVhbaTemplate.getSanVsan() != null){
			VnicFcIf vnicFcIf = factory.createVnicFcIf();
			SanVsan sv = sanVhbaTemplate.getSanVsan();
			vnicFcIf.setName(sv.getVsanName());
			
			JAXBElement<VnicFcIf> vnicFcIfJE = factory.createVnicFcIf(vnicFcIf);
			vnicSanConnTempl.getContent().add(vnicFcIfJE);
		}
		return vnicSanConnTempl;
	}

}
