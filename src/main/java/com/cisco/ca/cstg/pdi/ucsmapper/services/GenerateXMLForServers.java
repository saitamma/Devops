package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.LanVnic;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
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
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfile;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputeChassisQual;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputePool;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputePoolingPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputeQual;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ComputeSlotQual;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FirmwareComputeHostPack;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootBootSecurity;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootDefaultLocalImage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootLan;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootLanImagePath;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootLocalHddImage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootLocalStorage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootSan;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootSanCatSanImage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootSanCatSanImagePath;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootStorage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootUsbExternalImage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootUsbFlashStorageImage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsbootUsbInternalImage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.LsmaintMaintPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.StorageLocalDiskConfigPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.UuidpoolPool;

@Service("generateXMLForServers")
public class GenerateXMLForServers extends CommonDaoServicesImpl implements Constants {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForServers.class);
	private ObjectFactory factory;
	
	@Autowired
	private GenerateXMLForOrg generateXMLForOrg;
	
	public GenerateXMLForServers() {
		factory = new ObjectFactory();
	}
	
	public void addUuidpoolPool(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addUuidpoolPool : "+ project);
		List<ServersUuidPool> serversUuidPoolList = new ArrayList<ServersUuidPool>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(
				project.getOrganizationses(), org.getName());

		for (ServersUuidPool serversUuidPool : project.getServersUuidPools()) {
			if (parentOrg.getId().equals(
					serversUuidPool.getOrganizations().getId())) {
				serversUuidPoolList.add(serversUuidPool);
			}
		}
		for (ServersUuidPool serversUuidPool : serversUuidPoolList) {
			updateOrgWithUUIDPool(org, serversUuidPool);
		}
		LOGGER.info("End : GenerateXMLForServers : addUuidpoolPool");
	}
	
	public void addStorageLocalDiskConfigPolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addStorageLocalDiskConfigPolicy : " + project);
		List<ServersLocalDisc> serversLocalDiscList = new ArrayList<ServersLocalDisc>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for(ServersLocalDisc serversLocalDisc : project.getServersLocalDiscs()) {			
				if(serversLocalDisc.getOrganizations() != null && serversLocalDisc.getOrganizations().getId().equals(parentOrg.getId())) {
					serversLocalDiscList.add(serversLocalDisc);
				}			
		}
		
		for(ServersLocalDisc serversLocalDisc : serversLocalDiscList) {
			udpateOrgWithStrgLocalDskCnfgPolicy(org, serversLocalDisc);
			LOGGER.debug("Added ServersLocalDisc - StorageLocalDiskConfigPolicy for {}");
		}
		LOGGER.info("End : GenerateXMLForServers : addStorageLocalDiskConfigPolicy");
	}
	
	public void addComputePool(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addComputePool : " + project);
		List<ServersServerPool> serversServerPoolList = new ArrayList<ServersServerPool>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for(ServersServerPool serverPool : project.getServersServerPools()) {			
				if(serverPool.getOrganizations() != null && serverPool.getOrganizations().getId().equals(parentOrg.getId())) {
					serversServerPoolList.add(serverPool);
				}			
		}
		
		for(ServersServerPool serverPool : serversServerPoolList) {
			if(!generateXMLForOrg.isDefault(serverPool.getName())) {
				String name = serverPool.getName();
				ComputePool computePool = factory.createComputePool();
				computePool.setDescr((Util.isStringNotEmpty(serverPool.getDescription()))?serverPool.getDescription():"");
				computePool.setName(name);
				computePool.setPolicyOwner(Constants.LOCAL);
				
				JAXBElement<ComputePool> computePoolJE = factory.createComputePool(computePool);
				org.getContent().add(computePoolJE);
				LOGGER.debug("Added ServersServerPool - ComputePool for {}", name);
			}
		}
		LOGGER.info("End : GenerateXMLForServers : addComputePool");
	}
	
	public void addComputeQual(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addComputeQual : " + project);
		List<ServersServerPoolQualifier> serversServerPoolQualifierList = new ArrayList<ServersServerPoolQualifier>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersServerPoolQualifier serverPoolQualifier : project.getServersServerPoolQualifiers()) {
			if (validateServerPoolQalifier(parentOrg, serverPoolQualifier)) {
				serversServerPoolQualifierList.add(serverPoolQualifier);
			}
		}
		
		for(ServersServerPoolQualifier serversServerPoolQualifier : serversServerPoolQualifierList) {
			updateOrgWIthComputeQual(org, serversServerPoolQualifier);
		}
		LOGGER.info("End : GenerateXMLForServers : addComputeQual");
	}
	
	public void addComputePoolingPolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addComputePoolingPolicy : " + project);
		List<ServersServerPoolPolicy> serversServerPoolPolicyList = new ArrayList<ServersServerPoolPolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersServerPoolPolicy serverPoolPolicy : project.getServersServerPoolPolicies()) {
			if (validateServerPoolPolicy(parentOrg, serverPoolPolicy)) {
				serversServerPoolPolicyList.add(serverPoolPolicy);
			}
		}
		
		for(ServersServerPoolPolicy serversServerPoolPolicy : serversServerPoolPolicyList) {
			updateOrgWithComputePoolingPolicy(org, serversServerPoolPolicy);
		}
		LOGGER.info("End : GenerateXMLForServers : addComputePoolingPolicy");
	}
	
	public void addServiceProfile(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addServiceProfile : " + project);
		List<ServersServiceProfile> serversServiceProfileList = new ArrayList<ServersServiceProfile>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersServiceProfile serversServiceProfile : project.getServersServiceProfiles()) {
			if (validateServersServiceProfile(parentOrg, serversServiceProfile)) {
				serversServiceProfileList.add(serversServiceProfile);
			}
		}
		
		for(ServersServiceProfile serversServiceProfile : serversServiceProfileList) {
			ServersServiceProfileTemplate serversServiceProfileTemplate = serversServiceProfile.getServersServiceProfileTemplate();
			String srvcTemplateName = getSrvcProfileTemplatename(serversServiceProfileTemplate);
			generateXMLForOrg.addLsServer(org, project, serversServiceProfile.getDescription(), 
					serversServiceProfile.getName(), srvcTemplateName, serversServiceProfileTemplate , true);
			LOGGER.debug("Added ServersServiceProfile - LsServer for {}");
		}
		LOGGER.info("End : GenerateXMLForServers : addServiceProfile");
	}
	
	public void addServiceProfileTemplate(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addServiceProfileTemplate : " + project);
		List<ServersServiceProfileTemplate> serversServiceProfileTemplateList = new ArrayList<ServersServiceProfileTemplate>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersServiceProfileTemplate serversServiceProfileTemplate : project.getServersServiceProfileTemplates()) {
			if (validateSrvcProfileTemplate(parentOrg, serversServiceProfileTemplate)) {
				serversServiceProfileTemplateList.add(serversServiceProfileTemplate);
			}
		}
		
		for(ServersServiceProfileTemplate serversServiceProfileTemplate : serversServiceProfileTemplateList) {
			String name = serversServiceProfileTemplate.getName();
			generateXMLForOrg.addLsServer(org, project, serversServiceProfileTemplate.getDescription(), name, "", serversServiceProfileTemplate, false);
			LOGGER.debug("Added ServersServiceProfileTemplate - LsServer for {}", name);
		}
		LOGGER.info("End : GenerateXMLForServers : addServiceProfileTemplate");
	}
	
	public void addLsbootPolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addLsbootPolicy : " + project);
		List<ServersBootPolicy> serversBootPolicyList = new ArrayList<ServersBootPolicy>(); 
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersBootPolicy serversBootPolicy : project.getServersBootPolicies()) {
			if (validateServerBootPolicy(parentOrg, serversBootPolicy)) {
				serversBootPolicyList.add(serversBootPolicy);
			}
		}
		
		for(ServersBootPolicy serversBootPolicy : serversBootPolicyList) {
			updateOrgWithLSBootPolicy(org, serversBootPolicy);
		}
		LOGGER.info("End : GenerateXMLForServers : addLsbootPolicy");
	}
	
	public void addHostFirmware(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addHostFirmware : " + project);
		List<ServersHostFirmware> serversHostFirmwareList = new ArrayList<ServersHostFirmware>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersHostFirmware serversHostFirmware : project.getServersHostFirmwares()) {
			if (serversHostFirmware.getOrganizations() != null
					&& serversHostFirmware.getOrganizations().getId().equals(parentOrg.getId())) {
				serversHostFirmwareList.add(serversHostFirmware);
			}
		}
		
		for(ServersHostFirmware serversHostFirmware : serversHostFirmwareList) {
			updateOrgWithFirmware(org, serversHostFirmware);
		}
		LOGGER.info("End : GenerateXMLForServers : addHostFirmware");
	}
	
	public void addServersMaintenancePolicy(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForServers : addServersMaintenancePolicy : " + project);
		List<ServersMaintenancePolicy> serversMaintanencePolicyList = new ArrayList<ServersMaintenancePolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for (ServersMaintenancePolicy serversMaintenancePolicy : project.getServersMaintenancePolicies()) {
			if (serversMaintenancePolicy.getOrganizations() != null
					&& serversMaintenancePolicy.getOrganizations().getId().equals(parentOrg.getId())) {
				serversMaintanencePolicyList.add(serversMaintenancePolicy);
			}
		}
		
		for(ServersMaintenancePolicy serversMaintenancePolicy : serversMaintanencePolicyList) {
			updateOrgWithLSMaintPolicy(org, serversMaintenancePolicy);
		}
		LOGGER.info("End : GenerateXMLForServers : addServersMaintenancePolicy");
	}
	
	private void updateOrgWithLSMaintPolicy(OrgOrg org, ServersMaintenancePolicy serversMaintenancePolicy) {
		LsmaintMaintPolicy lsmaintMaintPolicy = factory.createLsmaintMaintPolicy();
		lsmaintMaintPolicy.setName(serversMaintenancePolicy.getName());
		lsmaintMaintPolicy.setDescr(serversMaintenancePolicy.getDescription());
		lsmaintMaintPolicy.setPolicyOwner(Constants.LOCAL);
		lsmaintMaintPolicy.setUptimeDisr(serversMaintenancePolicy.getRebootPolicy());
		JAXBElement<LsmaintMaintPolicy> serverMaintenancePolicyElement = factory.createLsmaintMaintPolicy(lsmaintMaintPolicy);
		org.getContent().add(serverMaintenancePolicyElement);
	}

	private void updateOrgWithFirmware(OrgOrg org, ServersHostFirmware serversHostFirmware) {
		FirmwareComputeHostPack firmwareComputeHostPack = factory.createFirmwareComputeHostPack();
		firmwareComputeHostPack.setBladeBundleVersion("");
		firmwareComputeHostPack.setDescr(serversHostFirmware.getDescription());
		firmwareComputeHostPack.setIgnoreCompCheck(YES);
		firmwareComputeHostPack.setMode(STAGED);
		firmwareComputeHostPack.setName(serversHostFirmware.getName());
		firmwareComputeHostPack.setPolicyOwner(LOCAL);
		firmwareComputeHostPack.setRackBundleVersion("");
		firmwareComputeHostPack.setStageSize(0);
		firmwareComputeHostPack.setUpdateTrigger(IMMEDIATE);
		
		JAXBElement<FirmwareComputeHostPack> firmwareComputeHostPackElement = factory.createFirmwareComputeHostPack(firmwareComputeHostPack);
		org.getContent().add(firmwareComputeHostPackElement);
		LOGGER.debug("Added ServersHostFirmware - FirmwareComputeHostPack for {}");
	}

	private void updateOrgWithLSBootPolicy(OrgOrg org,
			ServersBootPolicy serversBootPolicy) {
		LsbootPolicy lsbootPolicy = factory.createLsbootPolicy();
		
		if(serversBootPolicy.getBootMode() != null) {
			if(LEGACY.equalsIgnoreCase(serversBootPolicy.getBootMode())) {
				lsbootPolicy.setBootMode(LEGACY);
			}else{
				lsbootPolicy.setBootMode(UEFI);
				LsbootBootSecurity lsbootBootSecurity = factory.createLsbootBootSecurity();
				lsbootBootSecurity.setChildAction(DELETE_NON_PRESENT);
				lsbootBootSecurity.setRn(BOOT_SECURITY);
				lsbootBootSecurity.setSecureBoot(serversBootPolicy.getSecureBoot()); 
				
				JAXBElement<LsbootBootSecurity> lsbootBootSecurityJE = factory.createLsbootBootSecurity(lsbootBootSecurity);
				lsbootPolicy.getContent().add(lsbootBootSecurityJE);
			}
		}
		lsbootPolicy.setDescr((Util.isStringNotEmpty(serversBootPolicy.getDescription()))?serversBootPolicy.getDescription():"");
		lsbootPolicy.setEnforceVnicName((serversBootPolicy.getEnforceVnicName() == 1) ?YES:NO);
		lsbootPolicy.setName(serversBootPolicy.getName());
		lsbootPolicy.setPolicyOwner(LOCAL);
		lsbootPolicy.setPurpose(OPERATIONAL);
		lsbootPolicy.setRebootOnUpdate((serversBootPolicy.getRebootOnUpdate() == 1) ?YES:NO);

		addSanBootPolicy(lsbootPolicy, serversBootPolicy);
		addLanBootPolicy(lsbootPolicy, serversBootPolicy);
		addLocalStorageBootPolicy(lsbootPolicy, serversBootPolicy); 
		
		JAXBElement<LsbootPolicy> lsbootPolicyJE = factory.createLsbootPolicy(lsbootPolicy);
		org.getContent().add(lsbootPolicyJE);
		LOGGER.debug("Added ServersBootPolicy - LsbootPolicy for {}");
	}
	
	private void addSanBootPolicy(LsbootPolicy lsbootPolicy, ServersBootPolicy serversBootPolicy) {
		LOGGER.info("Start : GenerateXMLForServers : addSanBootPolicy ");
		Integer sanOrder = 0; 
		LsbootSan lsbootSan = factory.createLsbootSan();
		
		for(ServersBootPolicySan serversBootPolicySan : serversBootPolicy.getServersBootPolicySans()) {
			updateLsBootSanWithImage(lsbootSan, serversBootPolicySan);
			sanOrder = serversBootPolicySan.getOrder();
			LOGGER.debug("Added ServersBootPolicySan - LsbootSanCatSanImage for id {}", serversBootPolicySan.getId());
		}
		
		if (sanOrder != 0) {
			lsbootSan.setOrder(Integer.toString(sanOrder));
			JAXBElement<LsbootSan> lsbootSanJE = factory.createLsbootSan(lsbootSan);
			lsbootPolicy.getContent().add(lsbootSanJE);
		}
		LOGGER.info("End : GenerateXMLForServers : addSanBootPolicy");
	}
	
	private void addLanBootPolicy(LsbootPolicy lsbootPolicy, ServersBootPolicy serversBootPolicy) {
		LOGGER.info("Start : GenerateXMLForServers : addLanBootPolicy ");
		Integer lanOrder = 0; 
		LsbootLan lsbootLan = factory.createLsbootLan();
		
		for(ServersBootPolicyLan serversBootPolicyLan : serversBootPolicy.getServersBootPolicyLans()) {
			lanOrder = updateLSBootLanWithImage(lsbootLan, serversBootPolicyLan);
		}
		
		if (lanOrder != 0) {
			lsbootLan.setOrder(lanOrder.toString());
			lsbootLan.setProt("pxe");

			JAXBElement<LsbootLan> lsbootLanJE = factory.createLsbootLan(lsbootLan);
			lsbootPolicy.getContent().add(lsbootLanJE);
		}
		LOGGER.info("End : GenerateXMLForServers : addLanBootPolicy");
	}
	
	private void addLocalStorageBootPolicy(LsbootPolicy lsbootPolicy, ServersBootPolicy serversBootPolicy) {
		LOGGER.info("Start : GenerateXMLForOrg : addLocalStorageBootPolicy ");
		Integer localDiskOrder = 0;
		Integer lsbootLocalStorageOrder = 7;
		LsbootStorage lsbootStorage = factory.createLsbootStorage();
		LsbootLocalStorage lsbootLocalStorage = factory.createLsbootLocalStorage();
		
		for(ServersBootPolicyLocalDisc serversBootPolicyLocalDisc : serversBootPolicy.getServersBootPolicyLocalDiscs()) {
			localDiskOrder = serversBootPolicyLocalDisc.getBootOrder();
			lsbootLocalStorageOrder = (lsbootLocalStorageOrder > localDiskOrder)?localDiskOrder:lsbootLocalStorageOrder;
			String deviceName = serversBootPolicyLocalDisc.getDevice();
			addLsbootLocalStorage(lsbootLocalStorage, deviceName, localDiskOrder.toString());
		}
		
		if(lsbootLocalStorage.getContent() != null && lsbootLocalStorage.getContent().size() > 0 ) {
			JAXBElement<LsbootLocalStorage> lsbootLocalStorageJE = factory.createLsbootLocalStorage(lsbootLocalStorage);
			lsbootStorage.getContent().add(lsbootLocalStorageJE);
		}
		if(localDiskOrder != 0) {
			lsbootStorage.setOrder(lsbootLocalStorageOrder.toString());
			JAXBElement<LsbootStorage> lsbootStorageJE = factory.createLsbootStorage(lsbootStorage);
			lsbootPolicy.getContent().add(lsbootStorageJE);
		}
		LOGGER.info("End : GenerateXMLForOrg : addLocalStorageBootPolicy");
	}
	
	private void addLsbootLocalStorage(LsbootLocalStorage lsbootLocalStorage, String deviceName, String localDiskOrder) {
		LOGGER.info("Start : GenerateXMLForOrg : addLsbootLocalStorage");
		if("Local_Disk".equalsIgnoreCase(deviceName)) {
			LsbootDefaultLocalImage lsbootDefaultLocalImage = factory.createLsbootDefaultLocalImage();
			lsbootDefaultLocalImage.setOrder(localDiskOrder);
			 
			JAXBElement<LsbootDefaultLocalImage> lsbootDefaultLocalImageJE = factory.createLsbootDefaultLocalImage(lsbootDefaultLocalImage);
			lsbootLocalStorage.getContent().add(lsbootDefaultLocalImageJE);
			LOGGER.debug("Added lsbootDefaultLocalImage");
			return;
		}
		
		if("SD_Card".equalsIgnoreCase(deviceName)) {
			LsbootUsbFlashStorageImage lsbootUsbFlashStorageImage = factory.createLsbootUsbFlashStorageImage();
			lsbootUsbFlashStorageImage.setOrder(localDiskOrder);
			
			JAXBElement<LsbootUsbFlashStorageImage> lsbootUsbFlashStorageImageJE = factory.createLsbootUsbFlashStorageImage(lsbootUsbFlashStorageImage);
			lsbootLocalStorage.getContent().add(lsbootUsbFlashStorageImageJE);
			LOGGER.debug("Added lsbootUsbFlashStorageImage");
			return;
		}
		
		if("External_USB".equalsIgnoreCase(deviceName)) {
			LsbootUsbExternalImage lsbootUsbExternalImage = factory.createLsbootUsbExternalImage();
			lsbootUsbExternalImage.setOrder(localDiskOrder);
			
			JAXBElement<LsbootUsbExternalImage> lsbootUsbExternalImageJE = factory.createLsbootUsbExternalImage(lsbootUsbExternalImage);
			lsbootLocalStorage.getContent().add(lsbootUsbExternalImageJE);
			LOGGER.debug("Added lsbootUsbExternalImage");
			return;
		}
		
		if("Internal_USB".equalsIgnoreCase(deviceName)) {
			LsbootUsbInternalImage lsbootUsbInternalImage = factory.createLsbootUsbInternalImage();
			lsbootUsbInternalImage.setOrder(localDiskOrder);
			
			JAXBElement<LsbootUsbInternalImage> lsbootUsbInternalImageJE = factory.createLsbootUsbInternalImage(lsbootUsbInternalImage);
			lsbootLocalStorage.getContent().add(lsbootUsbInternalImageJE);
			LOGGER.debug("Added LsbootUsbInternalImage");
			return;
		}
		
		if("Local_LUN".equalsIgnoreCase(deviceName)) {
			LsbootLocalHddImage lsbootLocalHddImage = factory.createLsbootLocalHddImage();
			lsbootLocalHddImage.setOrder(localDiskOrder);
			
			JAXBElement<LsbootLocalHddImage> lsbootLocalHddImageJE = factory.createLsbootLocalHddImage(lsbootLocalHddImage);
			lsbootLocalStorage.getContent().add(lsbootLocalHddImageJE);
			LOGGER.debug("Added LsbootLocalHddImage");
			return;
		}
		LOGGER.info("End : GenerateXMLForOrg : addLsbootLocalStorage");
	}

	private Integer updateLSBootLanWithImage(LsbootLan lsbootLan,
			ServersBootPolicyLan serversBootPolicyLan) {
		Integer lanOrder;
		LsbootLanImagePath lsbootLanImagePath = factory.createLsbootLanImagePath();
		lsbootLanImagePath.setBootIpPolicyName("");
		lsbootLanImagePath.setISCSIVnicName("");
		lsbootLanImagePath.setImgPolicyName("");
		lsbootLanImagePath.setImgSecPolicyName("");
		lsbootLanImagePath.setProvSrvPolicyName("");
		lsbootLanImagePath.setType(serversBootPolicyLan.getType().toLowerCase());
		
		LanVnic lanVnic = serversBootPolicyLan.getLanVnic();
		
		if(lanVnic != null) {
			lsbootLanImagePath.setVnicName(lanVnic.getName());
			
			JAXBElement<LsbootLanImagePath> lsbootLanImagePathJE = factory.createLsbootLanImagePath(lsbootLanImagePath);
			lsbootLan.getContent().add(lsbootLanImagePathJE);
		}
		lanOrder = serversBootPolicyLan.getOrder();
		LOGGER.debug("Added ServersBootPolicyLan - LsbootLanImagePath for type {}", serversBootPolicyLan.getType());
		return lanOrder;
	}

	private void updateLsBootSanWithImage(LsbootSan lsbootSan, ServersBootPolicySan serversBootPolicySan) {
		LsbootSanCatSanImage lsbootSanCatSanImage = factory.createLsbootSanCatSanImage();
		lsbootSanCatSanImage.setType((serversBootPolicySan.getType() != null)?serversBootPolicySan.getType().toLowerCase(): "");
		SanVhba sanVhba = serversBootPolicySan.getSanVhba();
		lsbootSanCatSanImage.setVnicName((sanVhba != null)?sanVhba.getName():"");
		
		addServersBootPolicySanTarget(lsbootSanCatSanImage, serversBootPolicySan);
		
		JAXBElement<LsbootSanCatSanImage> lsbootSanCatSanImageJE = factory.createLsbootSanCatSanImage(lsbootSanCatSanImage);
		lsbootSan.getContent().add(lsbootSanCatSanImageJE);
	}
	
	private void addServersBootPolicySanTarget(LsbootSanCatSanImage lsbootSanCatSanImage,ServersBootPolicySan serversBootPolicySan) {
		LOGGER.info("Start : GenerateXMLForServers : addServersBootPolicySanTarget ");
		List<Object> serversBootPolicySanTargetList = find(Util.constructQueryStringForField("ServersBootPolicySanTarget",
						"serversBootPolicySan.id", serversBootPolicySan.getId()));
		
		if(serversBootPolicySanTargetList == null || serversBootPolicySanTargetList.isEmpty()) {
			LOGGER.error("There are no targets added for SAN boot policy id {}.",serversBootPolicySan.getId() );
			throw new IllegalStateException("There are no targets added for SAN boot policy");
		}
		
		for(Object objServersBootPolicySanTarget : serversBootPolicySanTargetList) {
			ServersBootPolicySanTarget serversBootPolicySanTarget = (ServersBootPolicySanTarget)objServersBootPolicySanTarget;
			addLsbootSanCatSanImagePath(
					lsbootSanCatSanImage,
					serversBootPolicySanTarget.getLunId().toString(),
					(Util.isStringNotEmpty(serversBootPolicySanTarget.getWwpnAddress())) ? serversBootPolicySanTarget.getWwpnAddress() : "",
					serversBootPolicySanTarget.getType());
		}
		LOGGER.info("End : GenerateXMLForServers : addServersBootPolicySanTarget");
	}
	
	private void addLsbootSanCatSanImagePath(LsbootSanCatSanImage lsbootSanCatSanImage, String lun, String wwpnAddress, String type) {
		LOGGER.info("Start : GenerateXMLForServers : addLsbootSanCatSanImagePath ");
		LsbootSanCatSanImagePath lsbootSanCatSanImagePath = factory.createLsbootSanCatSanImagePath();
		lsbootSanCatSanImagePath.setLun(Long.parseLong(lun));
		lsbootSanCatSanImagePath.setType(type.toLowerCase());
		lsbootSanCatSanImagePath.setWwn(wwpnAddress);
		
		JAXBElement<LsbootSanCatSanImagePath> lsbootSanCatSanImagePathJE = factory.createLsbootSanCatSanImagePath(lsbootSanCatSanImagePath);
		lsbootSanCatSanImage.getContent().add(lsbootSanCatSanImagePathJE);
		LOGGER.info("End : GenerateXMLForServers : addLsbootSanCatSanImagePath");
	}

	private boolean validateServerBootPolicy(Organizations parentOrg,
			ServersBootPolicy serversBootPolicy) {
		return serversBootPolicy.getOrganizations() != null && serversBootPolicy.getOrganizations().getId().equals(parentOrg.getId());
	}

	private boolean validateSrvcProfileTemplate(Organizations parentOrg,
			ServersServiceProfileTemplate serversServiceProfileTemplate) {
		return serversServiceProfileTemplate.getOrganizations() != null && serversServiceProfileTemplate.getOrganizations().getId().equals(parentOrg.getId());
	}

	private String getSrvcProfileTemplatename(
			ServersServiceProfileTemplate serversServiceProfileTemplate) {
		return (serversServiceProfileTemplate != null)?serversServiceProfileTemplate.getName():null;
	}

	private boolean validateServersServiceProfile(Organizations parentOrg,
			ServersServiceProfile serversServiceProfile) {
		return serversServiceProfile.getOrganizations() != null && serversServiceProfile.getOrganizations().getId().equals(parentOrg.getId());
	}

	private boolean validateServerPoolPolicy(Organizations parentOrg, ServersServerPoolPolicy serverPoolPolicy) {
		return serverPoolPolicy.getOrganizations() != null && serverPoolPolicy.getOrganizations().getId().equals(parentOrg.getId());
	}

	private void updateOrgWithComputePoolingPolicy(OrgOrg org, ServersServerPoolPolicy serversServerPoolPolicy) {
		ComputePoolingPolicy computePoolingPolicy = factory.createComputePoolingPolicy();
		computePoolingPolicy.setDescr((Util.isStringNotEmpty(serversServerPoolPolicy.getDescription()))?serversServerPoolPolicy.getDescription():"");
		computePoolingPolicy.setName(serversServerPoolPolicy.getName());
		computePoolingPolicy.setPolicyOwner(LOCAL);

		String poolPath = getPoolPath(serversServerPoolPolicy);
		computePoolingPolicy.setPoolDn(poolPath);
		computePoolingPolicy.setQualifier(getQualifierName(serversServerPoolPolicy));
		JAXBElement<ComputePoolingPolicy> computePoolingPolicyJE = factory.createComputePoolingPolicy(computePoolingPolicy);
		org.getContent().add(computePoolingPolicyJE);
		LOGGER.debug("Added ServersServerPoolPolicy - ComputePoolingPolicy for {}");
	}

	private String getPoolPath(ServersServerPoolPolicy serversServerPoolPolicy) {
		ServersServerPool sp = serversServerPoolPolicy.getServersServerPool();
		String poolPath = "";
		
		if(sp!= null) {
			poolPath = returnServerPoolPath(new StringBuilder("compute-pool-").append(sp.getName()),sp.getOrganizations().getName(), sp.getOrganizations().getParentId());
		}
		return poolPath;
	}

	private String getQualifierName(ServersServerPoolPolicy serversServerPoolPolicy) {
		return (serversServerPoolPolicy.getServersServerPoolQualifier() != null)?serversServerPoolPolicy.getServersServerPoolQualifier().getName():"";
	} 
	
	private String returnServerPoolPath(StringBuilder returnPath,String orgName, Integer parentOrgId) {
		LOGGER.info("Start : GenerateXMLForServers : returnServerPoolPath ");
		returnPath.insert(0,"/");
		returnPath.insert(0, orgName);
		returnPath.insert(0,"org-");
		
		if (!Constants.ROOT.equalsIgnoreCase(orgName)) {
			Organizations org = (Organizations) findById(Organizations.class, parentOrgId);
			returnServerPoolPath(returnPath, org.getName(), org.getParentId());
		}
		LOGGER.info("End : GenerateXMLForServers : returnServerPoolPath");
		return returnPath.toString();
	}

	private boolean validateServerPoolQalifier(Organizations parentOrg, ServersServerPoolQualifier serverPoolQualifier) {
		return null != serverPoolQualifier.getOrganizations() && parentOrg.getId().equals(serverPoolQualifier.getOrganizations().getId());
	}

	private void updateOrgWIthComputeQual(OrgOrg org, ServersServerPoolQualifier serversServerPoolQualifier) {
		String name = serversServerPoolQualifier.getName();
		ComputeQual computeQual = factory.createComputeQual();
		computeQual.setDescr((Util.isStringNotEmpty(serversServerPoolQualifier.getDescription()))?serversServerPoolQualifier.getDescription():"");
		computeQual.setName(name);
		computeQual.setPolicyOwner(Constants.LOCAL);
		updateComputeChassisQual(serversServerPoolQualifier, computeQual);
		JAXBElement<ComputeQual> computeQualJE = factory.createComputeQual(computeQual);
		org.getContent().add(computeQualJE);
		LOGGER.debug("Added ServersServerPoolQualifier - ComputeQual for {}");
	}

	private void updateComputeChassisQual(ServersServerPoolQualifier serversServerPoolQualifier, ComputeQual computeQual) {
		ComputeChassisQual computeChassisQual = updateComputeQualwithChassis(serversServerPoolQualifier, computeQual);
		
		for(ServersSPQSlotMapping spqChassisSlotobj : serversServerPoolQualifier.getServersSpqSlotMappings()) {
			ComputeSlotQual computeSlotQual = factory.createComputeSlotQual();
			computeSlotQual.setMinId((long)spqChassisSlotobj.getMinId());
			computeSlotQual.setMaxId((long)spqChassisSlotobj.getMaxId());
			JAXBElement<ComputeSlotQual> computeSlotQualJE = factory.createComputeSlotQual(computeSlotQual);
			computeChassisQual.getContent().add(computeSlotQualJE);
		}
	}

	private ComputeChassisQual updateComputeQualwithChassis(ServersServerPoolQualifier serversServerPoolQualifier, ComputeQual computeQual) {
		ComputeChassisQual computeChassisQual = factory.createComputeChassisQual();
		computeChassisQual.setMinId((long)serversServerPoolQualifier.getChassisMinId());
		computeChassisQual.setMaxId((long)serversServerPoolQualifier.getChassisMaxId());
		JAXBElement<ComputeChassisQual> computeChassisQualJE = factory.createComputeChassisQual(computeChassisQual);
		computeQual.getContent().add(computeChassisQualJE);
		return computeChassisQual;
	}

	private void udpateOrgWithStrgLocalDskCnfgPolicy(OrgOrg org, ServersLocalDisc serversLocalDisc) {
		StorageLocalDiskConfigPolicy storageLocalDiskConfigPolicy = factory.createStorageLocalDiskConfigPolicy();
		String name = serversLocalDisc.getName();
		storageLocalDiskConfigPolicy.setDescr((Util.isStringNotEmpty(serversLocalDisc.getDescription()))?serversLocalDisc.getDescription():"");
		storageLocalDiskConfigPolicy.setFlexFlashRAIDReportingState("disable");
		storageLocalDiskConfigPolicy.setFlexFlashState("disable");
		String mode = serversLocalDisc.getMode();
		storageLocalDiskConfigPolicy.setMode(mode.toLowerCase().replaceAll("\\d+\\s","").replaceAll("and ","").replaceAll(" ", "-"));
		storageLocalDiskConfigPolicy.setName(name);
		storageLocalDiskConfigPolicy.setPolicyOwner(Constants.LOCAL);
		storageLocalDiskConfigPolicy.setProtectConfig((serversLocalDisc.getProtectConfiguration()== 1)?Constants.YES:Constants.NO);
		
		JAXBElement<StorageLocalDiskConfigPolicy> storageLocalDiskConfigPolicyJE = factory.createStorageLocalDiskConfigPolicy(storageLocalDiskConfigPolicy);
		org.getContent().add(storageLocalDiskConfigPolicyJE);
	}

	private void updateOrgWithUUIDPool(OrgOrg org, ServersUuidPool serversUuidPool) {
		String name = serversUuidPool.getName();

		if(!generateXMLForOrg.isDefault(serversUuidPool.getName())) {
			UuidpoolPool uuidpoolPool = factory.createUuidpoolPool();
			uuidpoolPool.setAssignmentOrder(serversUuidPool.getAssignmentOrder());
			uuidpoolPool.setDescr((Util.isStringNotEmpty(serversUuidPool.getDescription()))?serversUuidPool.getDescription():"");
			uuidpoolPool.setName(name);
			uuidpoolPool.setPolicyOwner(Constants.LOCAL);
		
			if("derived".equals(serversUuidPool.getPrefixType())) {
				uuidpoolPool.setPrefix(serversUuidPool.getPrefixType());
			} else{
				uuidpoolPool.setPrefix(serversUuidPool.getPrefix());
			}
			
			generateXMLForOrg.addUuidpoolBlock(uuidpoolPool, serversUuidPool);
			
			JAXBElement<UuidpoolPool> uuidpoolPoolJE = factory.createUuidpoolPool(uuidpoolPool);
			org.getContent().add(uuidpoolPoolJE);
			LOGGER.debug("Added ServersUuidPool - UuidpoolPool for {}", name);
		}
	}

}
