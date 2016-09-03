package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.ServersBiosPolicy;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfAssertNMIOnPERR;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfAssertNMIOnSERR;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfBootOptionRetry;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfCPUPerformance;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfConsoleRedirection;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfCoreMultiProcessing;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfDirectCacheAccess;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfDramRefreshRate;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfEnhancedIntelSpeedStepTech;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfExecuteDisableBit;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfFrontPanelLockout;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfIntelEntrySASRAIDModule;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfIntelHyperThreadingTech;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfIntelTurboBoostTech;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfIntelVTForDirectedIO;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfIntelVirtualizationTechnology;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfLocalX2Apic;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfLvDIMMSupport;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfMaxVariableMTRRSetting;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfMaximumMemoryBelow4GB;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfMemoryMappedIOAbove4GB;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfMirroringMode;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfNUMAOptimized;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfOSBootWatchdogTimer;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfOSBootWatchdogTimerPolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfOSBootWatchdogTimerTimeout;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfOnboardStorage;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfPOSTErrorPause;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfProcessorC1E;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfProcessorC3Report;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfProcessorC6Report;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfProcessorC7Report;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfProcessorCState;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfQuietBoot;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfResumeOnACPowerLoss;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfSelectMemoryRASConfiguration;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfSerialPortAEnable;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfSparingMode;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfUSBBootConfig;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfUSBFrontPanelAccessLock;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.BiosVfUSBSystemIdlePowerOptimizingSetting;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;

@Service("generateXMLForBiosVProfile")
public class GenerateXMLForBiosVProfile extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForBiosVProfile.class);
	private ObjectFactory factory;
	
	@Autowired
	private GenerateXMLForOrg generateXMLForOrg;
	
	public GenerateXMLForBiosVProfile() {
		this.factory = new ObjectFactory();
	}
	
	public void addBiosVProfile(OrgOrg org, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosVProfile : " + project);
		List<ServersBiosPolicy> biosPolicyList = new ArrayList<ServersBiosPolicy>();
		Organizations parentOrg = generateXMLForOrg.getOrgnaization(project.getOrganizationses(), org.getName());
		
		for(ServersBiosPolicy serversBiosPolicy : project.getServersBiosPolicies()) {			
				if(serversBiosPolicy.getOrganizations() != null && serversBiosPolicy.getOrganizations().getId().equals(parentOrg.getId())) {
					biosPolicyList.add(serversBiosPolicy);
				}			
		}
		
		for(ServersBiosPolicy biosPolicy : biosPolicyList) {
			updateOrgWithBiosVProfile(org, biosPolicy);
		}
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosVProfile");
	}

	private void updateOrgWithBiosVProfile(OrgOrg org, ServersBiosPolicy biosPolicy) {
		BiosVProfile biosVProfile = factory.createBiosVProfile();
		biosVProfile.setName(biosPolicy.getName());
		biosVProfile.setDescr(biosPolicy.getDescription());
		biosVProfile.setPolicyOwner(Constants.LOCAL);
		biosVProfile.setRebootOnUpdate(biosPolicy.getRebootOnChange());
		updateBiosVProfileWithChilds(biosPolicy, biosVProfile);
		
		JAXBElement<BiosVProfile> biosVProfileEle = factory.createBiosVProfile(biosVProfile);
		org.getContent().add(biosVProfileEle);
	}

	private void updateBiosVProfileWithChilds(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile) {
		updateBiosVfQuietBoot(biosPolicy, biosVProfile);
		updateBiosVfPOSTErrorPause(biosPolicy, biosVProfile);
		updateBiosVfResumeOnACPowerLoss(biosPolicy, biosVProfile);
		updateBiosVfFronpanelLockout(biosPolicy, biosVProfile);
		addBiosPolicyProcessor(biosPolicy, biosVProfile);
		addBiosPolicyIntelDirectedIO(biosPolicy, biosVProfile);
		addBiosPolicyRASmemory(biosPolicy, biosVProfile);
		updateBiosVvfSerialPort(biosPolicy, biosVProfile);
		addBiosPolicyUSB(biosPolicy, biosVProfile);
		addBiosPolicyPCI(biosPolicy, biosVProfile);
		addBiosPolicyBootOption(biosPolicy, biosVProfile);
		addBiosPolicyServerManagement(biosPolicy, biosVProfile);
	}
	
	private void addBiosPolicyServerManagement(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyServerManagement");
		BiosVfAssertNMIOnSERR biosVfAssertNMIOnSERR = factory.createBiosVfAssertNMIOnSERR();
		biosVfAssertNMIOnSERR.setVpAssertNMIOnSERR(biosPolicy.getAssertNmiOnSerr());
		JAXBElement<BiosVfAssertNMIOnSERR> biosVfAssertNMIOnSERREle = factory.createBiosVfAssertNMIOnSERR(biosVfAssertNMIOnSERR);
		biosVProfile.getContent().add(biosVfAssertNMIOnSERREle);
		
		BiosVfAssertNMIOnPERR biosVfAssertNMIOnPERR = factory.createBiosVfAssertNMIOnPERR();
		biosVfAssertNMIOnPERR.setVpAssertNMIOnPERR(biosPolicy.getAssertNmiOnPerr());
		JAXBElement<BiosVfAssertNMIOnPERR> biosVfAssertNMIOnPERREle = factory.createBiosVfAssertNMIOnPERR(biosVfAssertNMIOnPERR);
		biosVProfile.getContent().add(biosVfAssertNMIOnPERREle);
		
		BiosVfOSBootWatchdogTimer biosVfOSBootWatchdogTimer = factory.createBiosVfOSBootWatchdogTimer();
		biosVfOSBootWatchdogTimer.setVpOSBootWatchdogTimer(biosPolicy.getOsBootWatchdogTimer());
		JAXBElement<BiosVfOSBootWatchdogTimer> biosVfOSBootWatchdogTimerEle = factory.createBiosVfOSBootWatchdogTimer(biosVfOSBootWatchdogTimer);
		biosVProfile.getContent().add(biosVfOSBootWatchdogTimerEle);
		
		BiosVfOSBootWatchdogTimerPolicy biosVfOSBootWatchdogTimerPolicy = factory.createBiosVfOSBootWatchdogTimerPolicy();
		biosVfOSBootWatchdogTimerPolicy.setVpOSBootWatchdogTimerPolicy(biosPolicy.getOsBootWatchdogTimerTimeoutPolicy());
		JAXBElement<BiosVfOSBootWatchdogTimerPolicy> biosVfOSBootWatchdogTimerPolicyEle = factory.createBiosVfOSBootWatchdogTimerPolicy(biosVfOSBootWatchdogTimerPolicy);
		biosVProfile.getContent().add(biosVfOSBootWatchdogTimerPolicyEle);
		
		BiosVfOSBootWatchdogTimerTimeout biosVfOSBootWatchdogTimerTimeout = factory.createBiosVfOSBootWatchdogTimerTimeout();
		biosVfOSBootWatchdogTimerTimeout.setVpOSBootWatchdogTimerTimeout(biosPolicy.getOsBootWatchdogTimerTimeout());
		JAXBElement<BiosVfOSBootWatchdogTimerTimeout> biosVfOSBootWatchdogTimerTimeoutEle = factory.createBiosVfOSBootWatchdogTimerTimeout(biosVfOSBootWatchdogTimerTimeout);
		biosVProfile.getContent().add(biosVfOSBootWatchdogTimerTimeoutEle);
		
		BiosVfConsoleRedirection biosVfConsoleRedirection = factory.createBiosVfConsoleRedirection();
		biosVfConsoleRedirection.setVpConsoleRedirection(biosPolicy.getConsoleRedirection());
		biosVfConsoleRedirection.setVpFlowControl(biosPolicy.getFlowControl());
		biosVfConsoleRedirection.setVpLegacyOSRedirection(biosPolicy.getLegacyOsRedirect());
		biosVfConsoleRedirection.setVpTerminalType(biosPolicy.getTerminalType());
		biosVfConsoleRedirection.setVpBaudRate(biosPolicy.getBaudRate());
		JAXBElement<BiosVfConsoleRedirection> biosVfConsoleRedirectionEle = factory.createBiosVfConsoleRedirection(biosVfConsoleRedirection);
		biosVProfile.getContent().add(biosVfConsoleRedirectionEle);
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyServerManagement");
	}
	
	private void addBiosPolicyBootOption(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyBootOption");
		BiosVfBootOptionRetry biosVfBootOptionRetry = factory.createBiosVfBootOptionRetry();
		biosVfBootOptionRetry.setVpBootOptionRetry(biosPolicy.getBootOptionRetry());
		JAXBElement<BiosVfBootOptionRetry> biosVfBootOptionRetryEle = factory.createBiosVfBootOptionRetry(biosVfBootOptionRetry);
		biosVProfile.getContent().add(biosVfBootOptionRetryEle);
	
		BiosVfIntelEntrySASRAIDModule biosVfIntelEntrySASRAIDModule = factory.createBiosVfIntelEntrySASRAIDModule();
		biosVfIntelEntrySASRAIDModule.setVpSASRAID(biosPolicy.getIntelEntrySasRaid());
		biosVfIntelEntrySASRAIDModule.setVpSASRAIDModule(biosPolicy.getIntelEntrySasRaidModule());
		JAXBElement<BiosVfIntelEntrySASRAIDModule> biosVfIntelEntrySASRAIDModuleEle = factory.createBiosVfIntelEntrySASRAIDModule(biosVfIntelEntrySASRAIDModule);
		biosVProfile.getContent().add(biosVfIntelEntrySASRAIDModuleEle);
			
		BiosVfOnboardStorage biosVfOnboardStorage = factory.createBiosVfOnboardStorage();
		biosVfOnboardStorage.setVpOnboardSCUStorageSupport(biosPolicy.getOnboardScuStorageSupport());
		JAXBElement<BiosVfOnboardStorage> biosVfOnboardStorageEle = factory.createBiosVfOnboardStorage(biosVfOnboardStorage);
		biosVProfile.getContent().add(biosVfOnboardStorageEle);
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyBootOption");
	}
	
	private void addBiosPolicyPCI(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyPCI");
		BiosVfMaximumMemoryBelow4GB biosVfMaximumMemoryBelow4GB = factory.createBiosVfMaximumMemoryBelow4GB();
		biosVfMaximumMemoryBelow4GB.setVpMaximumMemoryBelow4GB(biosPolicy.getMaxMemoryBelow4g());
		JAXBElement<BiosVfMaximumMemoryBelow4GB> biosVfMaximumMemoryBelow4GBEle = factory.createBiosVfMaximumMemoryBelow4GB(biosVfMaximumMemoryBelow4GB);
		biosVProfile.getContent().add(biosVfMaximumMemoryBelow4GBEle);
	
		BiosVfMemoryMappedIOAbove4GB biosVfMemoryMappedIOAbove4GB = factory.createBiosVfMemoryMappedIOAbove4GB();
		biosVfMemoryMappedIOAbove4GB.setVpMemoryMappedIOAbove4GB(biosPolicy.getMemoryMappedIoAbove4gbConfig());
		JAXBElement<BiosVfMemoryMappedIOAbove4GB> biosVfMemoryMappedIOAbove4GBEle = factory.createBiosVfMemoryMappedIOAbove4GB(biosVfMemoryMappedIOAbove4GB);
		biosVProfile.getContent().add(biosVfMemoryMappedIOAbove4GBEle);
	
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyPCI");
	}
	
	private void addBiosPolicyUSB(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyUSB");
		BiosVfUSBBootConfig biosVfUSBBootConfig = factory.createBiosVfUSBBootConfig();
		biosVfUSBBootConfig.setVpMakeDeviceNonBootable(biosPolicy.getMakeDeviceNonBootable());
		biosVfUSBBootConfig.setVpLegacyUSBSupport(biosPolicy.getLegacyUsbSupport());
		JAXBElement<BiosVfUSBBootConfig> biosVfUSBBootConfigEle = factory.createBiosVfUSBBootConfig(biosVfUSBBootConfig);
		biosVProfile.getContent().add(biosVfUSBBootConfigEle);
		
		BiosVfUSBSystemIdlePowerOptimizingSetting biosVfUSBSystemIdlePowerOptimizingSetting = factory.createBiosVfUSBSystemIdlePowerOptimizingSetting();
		biosVfUSBSystemIdlePowerOptimizingSetting.setVpUSBIdlePowerOptimizing(biosPolicy.getUsbSystemIdlePowerOptimizingSetting());
		JAXBElement<BiosVfUSBSystemIdlePowerOptimizingSetting> biosVfUSBSystemIdlePowerOptimizingSettingEle = factory.createBiosVfUSBSystemIdlePowerOptimizingSetting(biosVfUSBSystemIdlePowerOptimizingSetting);
		biosVProfile.getContent().add(biosVfUSBSystemIdlePowerOptimizingSettingEle);
		
		BiosVfUSBFrontPanelAccessLock biosVfUSBFrontPanelAccessLock = factory.createBiosVfUSBFrontPanelAccessLock();
		biosVfUSBFrontPanelAccessLock.setVpUSBFrontPanelLock(biosPolicy.getUsbFrontPanelAccessLock());
		JAXBElement<BiosVfUSBFrontPanelAccessLock> biosVfUSBFrontPanelAccessLockEle = factory.createBiosVfUSBFrontPanelAccessLock(biosVfUSBFrontPanelAccessLock);
		biosVProfile.getContent().add(biosVfUSBFrontPanelAccessLockEle);
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyUSB");
	}
	
	private void addBiosPolicyRASmemory(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyRASmemory");
		BiosVfSelectMemoryRASConfiguration biosVfSelectMemoryRASConfiguration = factory.createBiosVfSelectMemoryRASConfiguration();
		biosVfSelectMemoryRASConfiguration.setVpSelectMemoryRASConfiguration(biosPolicy.getMemoryRasConfig());
		JAXBElement<BiosVfSelectMemoryRASConfiguration> biosVfSelectMemoryRASConfigurationEle = factory.createBiosVfSelectMemoryRASConfiguration(biosVfSelectMemoryRASConfiguration);
		biosVProfile.getContent().add(biosVfSelectMemoryRASConfigurationEle);
		
		BiosVfNUMAOptimized biosVfNUMAOptimized = factory.createBiosVfNUMAOptimized();
		biosVfNUMAOptimized.setVpNUMAOptimized(biosPolicy.getNuma());
		JAXBElement<BiosVfNUMAOptimized> biosVfNUMAOptimizedEle = factory.createBiosVfNUMAOptimized(biosVfNUMAOptimized);
		biosVProfile.getContent().add(biosVfNUMAOptimizedEle);
		
		BiosVfLvDIMMSupport biosVfLvDIMMSupport = factory.createBiosVfLvDIMMSupport();
		biosVfLvDIMMSupport.setVpLvDDRMode(biosPolicy.getLvDdrMode());
		JAXBElement<BiosVfLvDIMMSupport> biosVfLvDIMMSupportEle = factory.createBiosVfLvDIMMSupport(biosVfLvDIMMSupport);
		biosVProfile.getContent().add(biosVfLvDIMMSupportEle);
		
		BiosVfDramRefreshRate biosVfDramRefreshRate = factory.createBiosVfDramRefreshRate();
		biosVfDramRefreshRate.setVpDramRefreshRate(biosPolicy.getDramRefreshRate());
		JAXBElement<BiosVfDramRefreshRate> biosVfDramRefreshRateEle = factory.createBiosVfDramRefreshRate(biosVfDramRefreshRate);
		biosVProfile.getContent().add(biosVfDramRefreshRateEle);
		
		BiosVfMirroringMode biosVfMirroringMode = factory.createBiosVfMirroringMode();
		biosVfMirroringMode.setVpMirroringMode(biosPolicy.getMirroringMode());
		JAXBElement<BiosVfMirroringMode> biosVfMirroringModeEle = factory.createBiosVfMirroringMode(biosVfMirroringMode);
		biosVProfile.getContent().add(biosVfMirroringModeEle);
		
		BiosVfSparingMode biosVfSparingMode = factory.createBiosVfSparingMode();
		biosVfSparingMode.setVpSparingMode(biosPolicy.getSparingMode());
		JAXBElement<BiosVfSparingMode> biosVfSparingModeEle = factory.createBiosVfSparingMode(biosVfSparingMode);
		biosVProfile.getContent().add(biosVfSparingModeEle);
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyRASmemory");
	}
	
	private void addBiosPolicyProcessor(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyProcessor");
		BiosVfIntelTurboBoostTech biosVfIntelTurboBoostTech = factory.createBiosVfIntelTurboBoostTech();
		biosVfIntelTurboBoostTech.setVpIntelTurboBoostTech(biosPolicy.getTurboBoost());
		JAXBElement<BiosVfIntelTurboBoostTech> biosVfIntelTurboBoostTechElement = factory.createBiosVfIntelTurboBoostTech(biosVfIntelTurboBoostTech);
		biosVProfile.getContent().add(biosVfIntelTurboBoostTechElement);
		
		BiosVfEnhancedIntelSpeedStepTech biosVfEnhancedIntelSpeedStepTech = factory.createBiosVfEnhancedIntelSpeedStepTech();
		biosVfEnhancedIntelSpeedStepTech.setVpEnhancedIntelSpeedStepTech(biosPolicy.getEnhancedIntelSpeedstep());
		JAXBElement<BiosVfEnhancedIntelSpeedStepTech> biosVfEnhancedIntelSpeedStepTechElement = factory.createBiosVfEnhancedIntelSpeedStepTech(biosVfEnhancedIntelSpeedStepTech);
		biosVProfile.getContent().add(biosVfEnhancedIntelSpeedStepTechElement);
		
		BiosVfIntelHyperThreadingTech biosVfIntelHyperThreadingTech = factory.createBiosVfIntelHyperThreadingTech();
		biosVfIntelHyperThreadingTech.setVpIntelHyperThreadingTech(biosPolicy.getHyperThreading());
		JAXBElement<BiosVfIntelHyperThreadingTech> biosVfIntelHyperThreadingTechElement = factory.createBiosVfIntelHyperThreadingTech(biosVfIntelHyperThreadingTech);
		biosVProfile.getContent().add(biosVfIntelHyperThreadingTechElement);
		
		BiosVfCoreMultiProcessing biosVfCoreMultiProcessing = factory.createBiosVfCoreMultiProcessing();
		biosVfCoreMultiProcessing.setVpCoreMultiProcessing(biosPolicy.getCoreMultiProcessing());
		JAXBElement<BiosVfCoreMultiProcessing> biosVfCoreMultiProcessingEle = factory.createBiosVfCoreMultiProcessing(biosVfCoreMultiProcessing);
		biosVProfile.getContent().add(biosVfCoreMultiProcessingEle);
		
		BiosVfExecuteDisableBit biosVfExecuteDisableBit = factory.createBiosVfExecuteDisableBit();
		biosVfExecuteDisableBit.setVpExecuteDisableBit(biosPolicy.getExecuteDisabledBit());
		JAXBElement<BiosVfExecuteDisableBit> biosVfExecuteDisableBitEle = factory.createBiosVfExecuteDisableBit(biosVfExecuteDisableBit);
		biosVProfile.getContent().add(biosVfExecuteDisableBitEle);
		
		BiosVfIntelVirtualizationTechnology biosVfIntelVirtualizationTechnology = factory.createBiosVfIntelVirtualizationTechnology();
		biosVfIntelVirtualizationTechnology.setVpIntelVirtualizationTechnology(biosPolicy.getVirtualizationTechnology());
		JAXBElement<BiosVfIntelVirtualizationTechnology> biosVfIntelVirtualizationTechnologyEle = factory.createBiosVfIntelVirtualizationTechnology(biosVfIntelVirtualizationTechnology);
		biosVProfile.getContent().add(biosVfIntelVirtualizationTechnologyEle);
		
		BiosVfDirectCacheAccess biosVfDirectCacheAccess = factory.createBiosVfDirectCacheAccess();
		biosVfDirectCacheAccess.setVpDirectCacheAccess(biosPolicy.getDirectCacheAccess());
		JAXBElement<BiosVfDirectCacheAccess> biosVfDirectCacheAccessEle = factory.createBiosVfDirectCacheAccess(biosVfDirectCacheAccess);
		biosVProfile.getContent().add(biosVfDirectCacheAccessEle);
		
		BiosVfProcessorCState biosVfProcessorCState = factory.createBiosVfProcessorCState();
		biosVfProcessorCState.setVpProcessorCState(biosPolicy.getProcessorCState());
		JAXBElement<BiosVfProcessorCState> biosVfProcessorCStateElement = factory.createBiosVfProcessorCState(biosVfProcessorCState);
		biosVProfile.getContent().add(biosVfProcessorCStateElement);
		
		BiosVfProcessorC1E biosVfProcessorC1E = factory.createBiosVfProcessorC1E();
		biosVfProcessorC1E.setVpProcessorC1E(biosPolicy.getProcessorC1e());
		JAXBElement<BiosVfProcessorC1E> biosVfProcessorC1EEle = factory.createBiosVfProcessorC1E(biosVfProcessorC1E);
		biosVProfile.getContent().add(biosVfProcessorC1EEle);
		
		BiosVfProcessorC3Report biosVfProcessorC3Report = factory.createBiosVfProcessorC3Report();
		biosVfProcessorC3Report.setVpProcessorC3Report(biosPolicy.getProcessorC3Report());
		JAXBElement<BiosVfProcessorC3Report> biosVfProcessorC3ReportEle = factory.createBiosVfProcessorC3Report(biosVfProcessorC3Report);
		biosVProfile.getContent().add(biosVfProcessorC3ReportEle);
		
		BiosVfProcessorC7Report biosVfProcessorC7Report = factory.createBiosVfProcessorC7Report();
		biosVfProcessorC7Report.setVpProcessorC7Report(biosPolicy.getProcessorC7Report());
		JAXBElement<BiosVfProcessorC7Report> biosVfProcessorC7ReportEle = factory.createBiosVfProcessorC7Report(biosVfProcessorC7Report);
		biosVProfile.getContent().add(biosVfProcessorC7ReportEle);
		
		BiosVfProcessorC6Report biosVfProcessorC6Report = factory.createBiosVfProcessorC6Report();
		biosVfProcessorC6Report.setVpProcessorC6Report(biosPolicy.getProcessorC6Report());
		JAXBElement<BiosVfProcessorC6Report> biosVfProcessorC6ReportEle = factory.createBiosVfProcessorC6Report(biosVfProcessorC6Report);
		biosVProfile.getContent().add(biosVfProcessorC6ReportEle);
		
		BiosVfCPUPerformance biosVfCPUPerformance =factory.createBiosVfCPUPerformance();
		biosVfCPUPerformance.setVpCPUPerformance(biosPolicy.getCpuPerformance());
		JAXBElement<BiosVfCPUPerformance> biosVfCPUPerformanceEle = factory.createBiosVfCPUPerformance(biosVfCPUPerformance);
		biosVProfile.getContent().add(biosVfCPUPerformanceEle);
		
		BiosVfMaxVariableMTRRSetting biosVfMaxVariableMTRRSetting = factory.createBiosVfMaxVariableMTRRSetting();
		biosVfMaxVariableMTRRSetting.setVpProcessorMtrr(biosPolicy.getMaxVariableMtrrSetting());
		JAXBElement<BiosVfMaxVariableMTRRSetting> biosVfMaxVariableMTRRSettingEle = factory.createBiosVfMaxVariableMTRRSetting(biosVfMaxVariableMTRRSetting);
		biosVProfile.getContent().add(biosVfMaxVariableMTRRSettingEle);
		
		BiosVfLocalX2Apic biosVfLocalX2Apic = factory.createBiosVfLocalX2Apic();
		biosVfLocalX2Apic.setVpLocalX2Apic(biosPolicy.getLocalX2Apic());
		JAXBElement<BiosVfLocalX2Apic> biosVfLocalX2ApicEle = factory.createBiosVfLocalX2Apic(biosVfLocalX2Apic);
		biosVProfile.getContent().add(biosVfLocalX2ApicEle);
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyProcessor");
	}
	
	private void addBiosPolicyIntelDirectedIO(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile){
		LOGGER.info("Start : GenerateXMLForBiosVProfile : addBiosPolicyIntelDirectedIO ");
		BiosVfIntelVTForDirectedIO biosVfIntelVTForDirectedIO = factory.createBiosVfIntelVTForDirectedIO();
		biosVfIntelVTForDirectedIO.setVpIntelVTForDirectedIO(biosPolicy.getVtForDirectedIo());
		biosVfIntelVTForDirectedIO.setVpIntelVTDInterruptRemapping(biosPolicy.getInterruptRemap());
		biosVfIntelVTForDirectedIO.setVpIntelVTDCoherencySupport(biosPolicy.getCoherencySupport());
		biosVfIntelVTForDirectedIO.setVpIntelVTDATSSupport(biosPolicy.getAtsSupport());
		biosVfIntelVTForDirectedIO.setVpIntelVTDCoherencySupport(biosPolicy.getCoherencySupport());
		biosVfIntelVTForDirectedIO.setVpIntelVTDPassThroughDMASupport(biosPolicy.getPassThroughDmaSupport());
		
		JAXBElement<BiosVfIntelVTForDirectedIO> biosVfIntelVTForDirectedIOEle = factory.createBiosVfIntelVTForDirectedIO(biosVfIntelVTForDirectedIO);
		biosVProfile.getContent().add(biosVfIntelVTForDirectedIOEle);
		LOGGER.info("End : GenerateXMLForBiosVProfile : addBiosPolicyIntelDirectedIO");
	}

	private void updateBiosVvfSerialPort(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile) {
		BiosVfSerialPortAEnable biosVfSerialPortAEnable = factory.createBiosVfSerialPortAEnable();
		biosVfSerialPortAEnable.setVpSerialPortAEnable(biosPolicy.getSerialPortA());
		JAXBElement<BiosVfSerialPortAEnable> biosVfSerialPortAEnableElement = factory.createBiosVfSerialPortAEnable(biosVfSerialPortAEnable);
		biosVProfile.getContent().add(biosVfSerialPortAEnableElement);
	}

	private void updateBiosVfFronpanelLockout(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile) {
		BiosVfFrontPanelLockout biosVfFrontPanelLockout = factory.createBiosVfFrontPanelLockout();
		biosVfFrontPanelLockout.setVpFrontPanelLockout(biosPolicy.getFrontPanelLockout());
		JAXBElement<BiosVfFrontPanelLockout> biosVfFrontPanelLockoutElement = factory.createBiosVfFrontPanelLockout(biosVfFrontPanelLockout);
		biosVProfile.getContent().add(biosVfFrontPanelLockoutElement);
	}

	private void updateBiosVfResumeOnACPowerLoss(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile) {
		BiosVfResumeOnACPowerLoss biosVfResumeOnACPowerLoss = factory.createBiosVfResumeOnACPowerLoss();
		biosVfResumeOnACPowerLoss.setVpResumeOnACPowerLoss(biosPolicy.getResumeAcOnPowerLoss());
		JAXBElement<BiosVfResumeOnACPowerLoss> biosVfResumeOnACPowerLossElement = factory.createBiosVfResumeOnACPowerLoss(biosVfResumeOnACPowerLoss);
		biosVProfile.getContent().add(biosVfResumeOnACPowerLossElement);
	}

	private void updateBiosVfPOSTErrorPause(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile) {
		BiosVfPOSTErrorPause biosVfPOSTErrorPause = factory.createBiosVfPOSTErrorPause();
		biosVfPOSTErrorPause.setVpPOSTErrorPause(biosPolicy.getPostErrorPause());
		JAXBElement<BiosVfPOSTErrorPause> biosVfPOSTErrorPauseElement = factory.createBiosVfPOSTErrorPause(biosVfPOSTErrorPause);
		biosVProfile.getContent().add(biosVfPOSTErrorPauseElement);
	}

	private void updateBiosVfQuietBoot(ServersBiosPolicy biosPolicy, BiosVProfile biosVProfile) {
		BiosVfQuietBoot biosVfQuietBoot = factory.createBiosVfQuietBoot();
		biosVfQuietBoot.setVpQuietBoot(biosPolicy.getQuietBoot());
		JAXBElement<BiosVfQuietBoot> biosVfQuietBootElement = factory.createBiosVfQuietBoot(biosVfQuietBoot);
		biosVProfile.getContent().add(biosVfQuietBootElement);
	}

}
