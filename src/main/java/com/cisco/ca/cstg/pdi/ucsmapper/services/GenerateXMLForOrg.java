package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.LanLcpVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.LanMacpool;
import com.cisco.ca.cstg.pdi.pojos.LanMgmtIppool;
import com.cisco.ca.cstg.pdi.pojos.LanNetworkControlPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanQosPolicy;
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplate;
import com.cisco.ca.cstg.pdi.pojos.LanVnicTemplateVlanMapping;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.ServersBiosPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersHostFirmware;
import com.cisco.ca.cstg.pdi.pojos.ServersMaintenancePolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.GenerateHexadecimalValues;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricVCon;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FcpoolBlock;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FcpoolInitiator;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FcpoolInitiators;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.IppoolBlock;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.IppoolPool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsPower;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsRequirement;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsServer;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsServerExtension;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsVConAssign;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsVersionBeh;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.UuidpoolBlock;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.UuidpoolPool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicConnDef;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicEther;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicEtherIf;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicFc;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicFcIf;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.VnicFcNode;

@Service("generateXMLForOrg")
public class GenerateXMLForOrg extends CommonDaoServicesImpl implements Constants {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForOrg.class);
	private ObjectFactory factory; 
	
	public GenerateXMLForOrg() {
		this.factory = new ObjectFactory();
	}
	
	public OrgOrg createSubOrgForOrg( Object obj){
		LOGGER.info("Start : GenerateXMLForOrg : createSubOrgForOrg");
		Organizations dbSubOrg = (Organizations)obj;
		OrgOrg newSubOrg = factory.createOrgOrg();
		newSubOrg.setName(dbSubOrg.getName());
		LOGGER.info("End : GenerateXMLForOrg : createSubOrgForOrg");
		return newSubOrg;
	}
	
	public void addSubOrgToOrg(OrgOrg parentOrg, OrgOrg subOrg){
		LOGGER.info("Start : GenerateXMLForOrg : addSubOrgToOrg");
		JAXBElement<OrgOrg> subOrgJE = factory.createOrgOrg(subOrg);
		parentOrg.getContent().add(subOrgJE);
		LOGGER.info("End : GenerateXMLForOrg : addSubOrgToOrg");
	}
	
	public List<Organizations> getSubOrganizations(OrgOrg parentOrg, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForOrg : getSubOrganizations : " + project);
		List<Organizations> orgList = project.getOrganizationses();
		Organizations dbParentOrg = getOrgnaization(orgList, parentOrg.getName());
		LOGGER.info("End : GenerateXMLForOrg : getSubOrganizations");
		return dbParentOrg.getOrganizationses();
	}
	
	protected Organizations getOrgnaization(List<Organizations> organizationses, String name) {
		for (Organizations org : organizationses) {
			if (org.getName().equals(name)) {
				return org;
			}
		}
		return null;
	}
	
	protected void addUuidpoolBlock(UuidpoolPool uuidpoolPool, ServersUuidPool serversUuidPool){
		LOGGER.info("Start : GenerateXMLForOrg : addUuidpoolBlock ");
		String from = serversUuidPool.getFromAddress();
		String to = serversUuidPool.getToAddress();
		if( from != null && to != null){
			UuidpoolBlock uuidpoolBlock = factory.createUuidpoolBlock();
			uuidpoolBlock.setFrom(from);
			uuidpoolBlock.setTo(to);
			
			JAXBElement<UuidpoolBlock> uuidpoolBlockJE = factory.createUuidpoolBlock(uuidpoolBlock);
			uuidpoolPool.getContent().add(uuidpoolBlockJE);
			LOGGER.debug("Added ServersUuidPool - UuidpoolBlock, for block {} - {}", from, to);
		}
		LOGGER.info("End : GenerateXMLForOrg : addUuidpoolBlock ");
	}
	
	private void createDefaultValsForLsServer(LsServer lsServer){
		LOGGER.info("Start : GenerateXMLForOrg : createDefaultValsForLsServer ");
		lsServer.setAgentPolicyName("");
		lsServer.setDynamicConPolicyName("");
		lsServer.setExtIPPoolName("ext-mgmt");
		lsServer.setExtIPState("none");
		lsServer.setLocalDiskPolicyName(DEFAULT);
		lsServer.setMgmtAccessPolicyName("");
		lsServer.setMgmtFwPolicyName("");
		lsServer.setPolicyOwner(LOCAL);
		lsServer.setPowerPolicyName(DEFAULT);
		lsServer.setResolveRemote(YES);
		lsServer.setScrubPolicyName("");
		lsServer.setSolPolicyName("");
		lsServer.setStatsPolicyName(DEFAULT);
		lsServer.setUsrLbl("");
		lsServer.setUuid("derived");
		lsServer.setVconProfileName("");
		LOGGER.info("End : GenerateXMLForOrg : createDefaultValsForLsServer");
	}
	
	private void createDefaultValsForLsVConAssign(LsServer lsServer, String order, String transport, String name){
		LOGGER.info("Start : GenerateXMLForOrg : createDefaultValsForLsVConAssign ");
			LsVConAssign lsVConAssign = factory.createLsVConAssign();
			lsVConAssign.setAdminVcon(ANY);
			lsVConAssign.setOrder(order);
			lsVConAssign.setTransport(transport);
			lsVConAssign.setVnicName((name != null)?name:"dummy_value");
			
			JAXBElement<LsVConAssign> lsVConAssignJE = factory.createLsVConAssign(lsVConAssign);
			lsServer.getContent().add(lsVConAssignJE);
			LOGGER.info("End : GenerateXMLForOrg : createDefaultValsForLsVConAssign");
	}
	
	private void createDefaultValsForFabricVCon(LsServer lsServer){
		LOGGER.info("Start : GenerateXMLForOrg : createDefaultValsForFabricVCon " );
		for(int i = 0; i < 4; i++){
			FabricVCon fabricVCon = factory.createFabricVCon();
			fabricVCon.setFabric("NONE");
			fabricVCon.setId(i+1+""); 
			fabricVCon.setInstType("auto");
			fabricVCon.setPlacement("physical");
			fabricVCon.setSelect("all");
			fabricVCon.setShare("shared");
			fabricVCon.setTransport("ethernet,fc");
			
			JAXBElement<FabricVCon> fabricVConJE = factory.createFabricVCon(fabricVCon);
			lsServer.getContent().add(fabricVConJE);
		}
		LOGGER.info("End : GenerateXMLForOrg : createDefaultValsForFabricVCon");
	}
	
	public void  addLsServer(OrgOrg org, XmlGenProjectDetails project, String description, String name, String srcTemplName, ServersServiceProfileTemplate serversServiceProfileTemplate, boolean isServiceProfile){
		LOGGER.info("Start : GenerateXMLForOrg : addLsServer : " + project);
		List<SanScpVhbaMapping> vhbaTemplateList = null;
		List<ServersSPTVhbaMapping> vhbaList = null;
		ServersUuidPool uuidPool = null;
		LanMacpool lanMacpool = null;
		SanWwnn sanwwnn = null;
		String wwpnName = null;
		String adaptorPolicyName = "";
		String etherAdaptorPolicyName = "";
		String nWContrPolicyName = "";
		String qosPolicyName = "";
		String dummyVnictName = null, dummyVhbaTemplateName = null;
		int i = 0;
		
		LsServer lsServer = factory.createLsServer();
		createDefaultValsForLsServer(lsServer);
		
		lsServer.setDescr((Util.isStringNotEmpty(description))?description:"");
		lsServer.setName(name);
		lsServer.setSrcTemplName(srcTemplName); 
		
		if(serversServiceProfileTemplate != null){
			ServersHostFirmware hostFirmware = serversServiceProfileTemplate.getServersHostFirmware();
			lsServer.setHostFwPolicyName((hostFirmware != null)?hostFirmware.getName():"");
			
			ServersMaintenancePolicy maintPolicy = serversServiceProfileTemplate.getServersMaintenancePolicy();
			lsServer.setMaintPolicyName( (maintPolicy!= null)? maintPolicy.getName():"");
			
			ServersBiosPolicy biosPolicy = serversServiceProfileTemplate.getServersBiosPolicy();
			lsServer.setBiosProfileName( (biosPolicy!= null)? biosPolicy.getName():"");
			
			uuidPool = serversServiceProfileTemplate.getServersUuidPool();
			lsServer.setIdentPoolName((uuidPool != null)?uuidPool.getName():DEFAULT);
			lsServer.setBootPolicyName((serversServiceProfileTemplate.getServersBootPolicy() != null)?serversServiceProfileTemplate.getServersBootPolicy().getName():"");
			lsServer.setLocalDiskPolicyName((serversServiceProfileTemplate.getServersLocalDisc() != null) ? serversServiceProfileTemplate.getServersLocalDisc().getName(): "");
			lsServer.setType(serversServiceProfileTemplate.getType().trim());
			createLsRequirement(lsServer, serversServiceProfileTemplate);
						
			if(serversServiceProfileTemplate.getLanConnectivityPolicy() != null){
				List<LanLcpVnicMapping> vnicList = serversServiceProfileTemplate.getLanConnectivityPolicy().getLanLcpVnicMappings();
				
				if(vnicList != null){
					for(LanLcpVnicMapping lanLcpVnicMapping : vnicList){
						i++;
						LanVnic lanVnic = lanLcpVnicMapping.getLanVnic();
						LanVnicTemplate lanVnicTemplate = lanVnic.getLanVnicTemplate();
						dummyVnictName = lanVnicTemplate.getVnictName();
						createDefaultValsForLsVConAssign(lsServer,Integer.toString(i+1),"ethernet",dummyVnictName);
						lanMacpool = lanVnicTemplate.getLanMacpool();

						LanNetworkControlPolicy nwContrPolicy = lanVnicTemplate.getLanNetworkControlPolicy();

						if(nwContrPolicy != null){
							nWContrPolicyName = nwContrPolicy.getNcpName();
						}
					
						LanQosPolicy qosPolicy =  lanVnicTemplate.getLanQosPolicy();
							
						if(qosPolicy != null) {
							qosPolicyName = qosPolicy.getName();
						}
						
						List<LanVnicTemplateVlanMapping> vlanTemplateList = lanVnicTemplate.getLanVnictVlanMappings();
						
						etherAdaptorPolicyName = (lanVnic.getLanEthernetAdapterPolicies() != null)?lanVnic.getLanEthernetAdapterPolicies().getName() : "";
						
						addVnicEther(lsServer,lanVnic.getName(), dummyVnictName, lanMacpool, vlanTemplateList, Integer.toString(i), etherAdaptorPolicyName,nWContrPolicyName,qosPolicyName, lanVnicTemplate.getSwitchId());
						
					}
				}
			}else {
				List<ServersSPTVnicMapping> vnicList = serversServiceProfileTemplate.getServersSptVnicMappings();
				
				if(vnicList != null){
					for(ServersSPTVnicMapping sptVnicMapping : vnicList){
						i++;
						LanVnic lanVnic = sptVnicMapping.getLanVnic();
						LanVnicTemplate lanVnicTemplate = lanVnic.getLanVnicTemplate();
						dummyVnictName = lanVnicTemplate.getVnictName();
						createDefaultValsForLsVConAssign(lsServer,Integer.toString(i+1),"ethernet",dummyVnictName);
						if (lanVnicTemplate.getLanMacpool() != null) {
							lanMacpool = (LanMacpool) findById(LanMacpool.class, lanVnicTemplate.getLanMacpool().getId());
						}
						if(lanVnicTemplate.getLanNetworkControlPolicy() != null){
							LanNetworkControlPolicy nwContrPolicy = lanVnicTemplate.getLanNetworkControlPolicy();
							
							if(nwContrPolicy != null){
								nWContrPolicyName = nwContrPolicy.getNcpName();
							}
						}
						
						LanQosPolicy qosPolicy =  lanVnicTemplate.getLanQosPolicy();
							
						if(qosPolicy != null) {
							qosPolicyName = qosPolicy.getName();
						}

						List<LanVnicTemplateVlanMapping> vlanTemplateList = lanVnicTemplate.getLanVnictVlanMappings();
						etherAdaptorPolicyName = (lanVnic.getLanEthernetAdapterPolicies() != null)?lanVnic.getLanEthernetAdapterPolicies().getName() : "";
						addVnicEther(lsServer,lanVnic.getName(), dummyVnictName, lanMacpool, vlanTemplateList, Integer.toString(i), etherAdaptorPolicyName,nWContrPolicyName,qosPolicyName, lanVnicTemplate.getSwitchId());
					}
				}
			}
				
			if (serversServiceProfileTemplate.getSanConnectivityPolicy() != null) {
				vhbaTemplateList = serversServiceProfileTemplate.getSanConnectivityPolicy().getSanScpVhbaMappings();
				
				if (vhbaTemplateList != null) {
					for (SanScpVhbaMapping scpVhbaMapping : vhbaTemplateList) {
						i++;
						SanVhba sanVhba = scpVhbaMapping.getSanVhba();
						SanVhbaTemplate sanVhbaTemplate = sanVhba.getSanVhbaTemplate();
						dummyVhbaTemplateName = sanVhbaTemplate.getVhbaName();
						createDefaultValsForLsVConAssign(lsServer,Integer.toString(i),"fc",dummyVhbaTemplateName);
						
						if (sanVhbaTemplate.getSanWwpn() != null) {
							wwpnName = sanVhbaTemplate.getSanWwpn().getWwpnName();
						}
						adaptorPolicyName = (sanVhba.getSanAdapterPolicies() != null)?sanVhba.getSanAdapterPolicies().getName() : "";
						addVnicFc(lsServer, dummyVhbaTemplateName, sanVhbaTemplate.getVhbaName(), wwpnName, Integer.toString(i), adaptorPolicyName);
					}
				}
				sanwwnn = serversServiceProfileTemplate.getSanConnectivityPolicy().getSanWwnn();
			} else {
				vhbaList = serversServiceProfileTemplate.getServersSptVhbaMappings();
				if (vhbaList != null) {
					for (ServersSPTVhbaMapping sptVhbaMapping : vhbaList) {
						i++;
						
						if(sptVhbaMapping.getSanVhba() != null) {
							SanVhba sanVhba = sptVhbaMapping.getSanVhba();
							SanVhbaTemplate sanVhbaTemplate = sanVhba.getSanVhbaTemplate();
							createDefaultValsForLsVConAssign(lsServer,Integer.toString(i),"fc",sanVhba.getName());

							if (sanVhbaTemplate.getSanWwpn() != null) {
								wwpnName = sanVhbaTemplate.getSanWwpn().getWwpnName();
							}
							adaptorPolicyName = (sanVhba.getSanAdapterPolicies() != null)?sanVhba.getSanAdapterPolicies().getName() : "";
							addVnicFc(lsServer, sanVhba.getName(), sanVhbaTemplate.getVhbaName(), wwpnName, Integer.toString(i), adaptorPolicyName);
						}
					}
				}
				sanwwnn = serversServiceProfileTemplate.getSanWwnn();
			}			
		}else{
			lsServer.setBootPolicyName("");
			lsServer.setType("");
		}
		
		createVnicConnDef(lsServer, serversServiceProfileTemplate);
		
		if(isServiceProfile){
			createLsVersionBehAndLsServerExtension(lsServer);
			lsServer.setType("instance");
		}
		
		createVnicFcNode(lsServer, (sanwwnn != null)?sanwwnn.getWwnnName():"");
		createDefaultValsForFabricVCon(lsServer);
		createLsPower(lsServer);
		
		JAXBElement<LsServer> lsServerJE = factory.createLsServer(lsServer);
		org.getContent().add(lsServerJE);
		LOGGER.info("End : GenerateXMLForOrg : addLsServer");
	}
	
	private void createLsPower(LsServer lsServer){
		LOGGER.info("Start : GenerateXMLForOrg : createLsPower");
		LsPower lsPower = factory.createLsPower();
		lsPower.setState("up");

		JAXBElement<LsPower> lsPowerJE = factory.createLsPower(lsPower);
		lsServer.getContent().add(lsPowerJE);
		LOGGER.info("End : GenerateXMLForOrg : createLsPower");
	}
	
	private void createVnicFcNode(LsServer lsServer, String wwnnName){
		LOGGER.info("Start : GenerateXMLForOrg : createVnicFcNode");
		VnicFcNode vnicFcNode = factory.createVnicFcNode();
		vnicFcNode.setAddr("pool-derived");
		vnicFcNode.setIdentPoolName((Util.isStringNotEmpty(wwnnName))?wwnnName:"node-default");
		
		JAXBElement<VnicFcNode> vnicFcNodeJE = factory.createVnicFcNode(vnicFcNode);
		lsServer.getContent().add(vnicFcNodeJE);
		LOGGER.info("End : GenerateXMLForOrg : createVnicFcNode");
	}
	
	private void createVnicConnDef(LsServer lsServer, ServersServiceProfileTemplate serversServiceProfileTemplate){
		LOGGER.info("Start : GenerateXMLForOrg : createVnicConnDef");
		VnicConnDef vnicConnDef = factory.createVnicConnDef();
		vnicConnDef.setLanConnPolicyName("");
		if(serversServiceProfileTemplate != null && serversServiceProfileTemplate.getSanConnectivityPolicy() != null){
			vnicConnDef.setSanConnPolicyName(serversServiceProfileTemplate.getSanConnectivityPolicy().getName());
		}
		if(serversServiceProfileTemplate != null && serversServiceProfileTemplate.getLanConnectivityPolicy() != null){
			vnicConnDef.setLanConnPolicyName(serversServiceProfileTemplate.getLanConnectivityPolicy().getName());
		}
		
		JAXBElement<VnicConnDef> vnicConnDefJE = factory.createVnicConnDef(vnicConnDef);
		lsServer.getContent().add(vnicConnDefJE);
		LOGGER.info("End : GenerateXMLForOrg : createVnicConnDef");
	}
	
	private void createLsRequirement(LsServer lsServer, ServersServiceProfileTemplate serversServiceProfileTemplate){
		LOGGER.info("Start : GenerateXMLForOrg : createLsRequirement");
		ServersServerPool serversServerPool = serversServiceProfileTemplate.getServersServerPool();
		if(serversServerPool != null){
			LsRequirement lsRequirement = factory.createLsRequirement();
			lsRequirement.setName(serversServerPool.getName());
			lsRequirement.setQualifier("");
			lsRequirement.setRestrictMigration(NO);
	
			JAXBElement<LsRequirement> lsRequirementJE = factory.createLsRequirement(lsRequirement);
			lsServer.getContent().add(lsRequirementJE);
		}
		LOGGER.info("End : GenerateXMLForOrg : createLsRequirement");
	}
	
	private void createLsVersionBehAndLsServerExtension(LsServer lsServer){
		LOGGER.info("Start : GenerateXMLForOrg : createLsVersionBehAndLsServerExtension");
		LsVersionBeh lsVersionBeh = factory.createLsVersionBeh();
		lsVersionBeh.setPciEnum("static-zero-func");
		lsVersionBeh.setVconMap("round-robin");
		lsVersionBeh.setVnicMap("physical-cap-first");
		lsVersionBeh.setVnicOrder("dynamic-all-last");
		
		JAXBElement<LsVersionBeh> lsVersionBehJE = factory.createLsVersionBeh(lsVersionBeh);
		lsServer.getContent().add(lsVersionBehJE);
		
		LsServerExtension lsServerExtension = factory.createLsServerExtension();
		lsServerExtension.setGuid("db8f8cdb-4d0b-430b-9c8f-c1c43ba17f48");
		
		JAXBElement<LsServerExtension> lsServerExtensionJE = factory.createLsServerExtension(lsServerExtension);
		lsServer.getContent().add(lsServerExtensionJE);
		LOGGER.info("End : GenerateXMLForOrg : createLsVersionBehAndLsServerExtension");
	}
	
	public void addMgmtIppoolPool(IppoolPool ippoolPool, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForOrg : addMgmtIppoolPool : " + project);
		List<LanMgmtIppool> lanMgmtIppoolList = project.getLanMgmtIppools();
		
		for(LanMgmtIppool lanMgmtIppool: lanMgmtIppoolList){
			IppoolBlock ippoolBlock = factory.createIppoolBlock();
			ippoolBlock.setDefGw(lanMgmtIppool.getDefaultGateway());
			ippoolBlock.setFrom(lanMgmtIppool.getFromAddress());
			ippoolBlock.setPrimDns(lanMgmtIppool.getDns());
			ippoolBlock.setSubnet(lanMgmtIppool.getSubnetMask());
			ippoolBlock.setTo(lanMgmtIppool.getToAddress());
			
			JAXBElement<IppoolBlock> ippoolBlockJE = factory.createIppoolBlock(ippoolBlock);
			ippoolPool.getContent().add(ippoolBlockJE);
			LOGGER.debug("Added LanMgmtIppool - IppoolBlock, for block {} - {}", lanMgmtIppool.getFromAddress(), lanMgmtIppool.getToAddress());
		}
		LOGGER.info("End : GenerateXMLForOrg : addMgmtIppoolPool");
	}
	
	private void addVnicEther(LsServer lsServer,String name, String vnicTemplateName, LanMacpool macpool,List<LanVnicTemplateVlanMapping> vlanTemplateList, String order, String etherAdaptorPolicy,String nWContrPolicyName, String qosPolicyName, String switchId){
		LOGGER.info("Start : GenerateXMLForOrg : addVnicEther");
		VnicEther vnicEther= factory.createVnicEther();
		vnicEther.setAdaptorProfileName((etherAdaptorPolicy != null)? etherAdaptorPolicy: "" );
		vnicEther.setAddr(DERIVED);
		vnicEther.setAdminVcon(ANY);
		vnicEther.setMtu("1500");
		vnicEther.setName((name != null)?name:"dummy_value");
		vnicEther.setNwCtrlPolicyName(nWContrPolicyName);
		vnicEther.setNwTemplName((vnicTemplateName != null)?vnicTemplateName:"");
		vnicEther.setOrder(order);
		vnicEther.setPinToGroupName("");
		vnicEther.setQosPolicyName(qosPolicyName);
		vnicEther.setStatsPolicyName(DEFAULT);
		vnicEther.setSwitchId(switchId);
		vnicEther.setIdentPoolName((macpool != null)?macpool.getMacpoolName():"");

		if(vlanTemplateList != null){
			for(LanVnicTemplateVlanMapping vlanMapping: vlanTemplateList){
				LanVlan vlan = vlanMapping.getLanVlan();
				
				VnicEtherIf vnicEtherIf = factory.createVnicEtherIf();
				vnicEtherIf.setDefaultNet(NO);
				vnicEtherIf.setName(vlan.getVlanName());
				
				JAXBElement<VnicEtherIf> vnicEtherIfJE = factory.createVnicEtherIf(vnicEtherIf);
				vnicEther.getContent().add(vnicEtherIfJE);
			}
		}
		JAXBElement<VnicEther> vnicEtherJE = factory.createVnicEther(vnicEther);
		lsServer.getContent().add(vnicEtherJE);
		LOGGER.info("End : GenerateXMLForOrg : addVnicEther");
	}
	
		
	private void addVnicFc(LsServer lsServer,String name, String vhbaName, String idenPoolName, String order, String adapterPolicy){
		LOGGER.info("Start : GenerateXMLForOrg : addVnicFc");
		VnicFc vnicFc= factory.createVnicFc();
		vnicFc.setAdaptorProfileName(adapterPolicy);
		vnicFc.setAdminVcon(ANY);
		vnicFc.setMaxDataFieldSize("2048");
		vnicFc.setName((name != null)?name:"");
		vnicFc.setNwTemplName((vhbaName != null)?vhbaName:"");
		vnicFc.setOrder(order);
		vnicFc.setPersBind("disabled");
		vnicFc.setPersBindClear(NO);
		vnicFc.setPinToGroupName("");
		vnicFc.setQosPolicyName("");
		vnicFc.setStatsPolicyName(DEFAULT);
		vnicFc.setSwitchId("A");
		vnicFc.setIdentPoolName((idenPoolName != null)?idenPoolName:"");
		
		VnicFcIf vnicFcIf = factory.createVnicFcIf();
		vnicFcIf.setName("vsanA");
		
		JAXBElement<VnicFcIf> vnicFcIfJE = factory.createVnicFcIf(vnicFcIf);
		vnicFc.getContent().add(vnicFcIfJE);
		
		JAXBElement<VnicFc> vnicFcJE = factory.createVnicFc(vnicFc);
		lsServer.getContent().add(vnicFcJE);
		LOGGER.info("End : GenerateXMLForOrg : addVnicFc");
	}
	
	protected void addFcpoolBlock(FcpoolInitiators fcpoolInitiators, String from, String to){
		LOGGER.info("Start : GenerateXMLForOrg : addFcpoolBlock");
		FcpoolBlock fcpoolBlock = factory.createFcpoolBlock();
		fcpoolBlock.setFrom(from);
		fcpoolBlock.setTo(to);
		
		JAXBElement<FcpoolBlock> fcpoolBlockJE = factory.createFcpoolBlock(fcpoolBlock);
		fcpoolInitiators.getContent().add(fcpoolBlockJE);

		List<String> macAddressList =  getIndividualMacAddress(from, to);
		for(String str:macAddressList){
			FcpoolInitiator fcpoolInitiator = factory.createFcpoolInitiator();
			fcpoolInitiator.setDescr("");
			fcpoolInitiator.setId(str);
			fcpoolInitiator.setName("");
			
			JAXBElement<FcpoolInitiator> fcpoolInitiatorJE = factory.createFcpoolInitiator(fcpoolInitiator);
			fcpoolInitiators.getContent().add(fcpoolInitiatorJE);
		}
		LOGGER.info("End : GenerateXMLForOrg : addFcpoolBlock");
	}
	
	private List<String> getIndividualMacAddress(String fromAddress, String toAddress){
		return GenerateHexadecimalValues.macAddressSplit(fromAddress, toAddress);
	}
	
	protected boolean isDefault(String name){
		return name.equalsIgnoreCase(DEFAULT);
	}
	
}