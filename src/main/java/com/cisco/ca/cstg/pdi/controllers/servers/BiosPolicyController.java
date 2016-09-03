package com.cisco.ca.cstg.pdi.controllers.servers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.ServersBiosPolicy;
import com.cisco.ca.cstg.pdi.services.ServersService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class BiosPolicyController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BiosPolicyController.class);
	private final ServersService serversService;

	@Autowired
	public BiosPolicyController(ServersService serversService) {
		this.serversService = serversService;
	}

	@RequestMapping(value = "/serversBiosPolicyConfig.html")
	public String showServersBiosPolicyConfig() {
		return "servers/serversBiosPolicyConfig";
	}

	@RequestMapping(value = "/getserversBiosPolicyDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getserversBiosPolicyDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<ServersBiosPolicy> biosPolicyList = null;

		if (projectId != null) {
			try {
				biosPolicyList = this.serversService
						.fetchBiosPolicyDetail(projectId);

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServersBiosPolicyTableSaveJsonList(biosPolicyList);
	}

	private List<Object> getServersBiosPolicyTableSaveJsonList(
			List<ServersBiosPolicy> biosPolicyList) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(biosPolicyList)) {
			for (ServersBiosPolicy sbp : biosPolicyList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, sbp.getId());
				myMap.put(NAME, sbp.getName());
				myMap.put(DESCRIPTION, sbp.getDescription());
				myMap.put(ORGANIZATIONS,
						Util.nullValidationAndReturnId(sbp.getOrganizations()));

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServersBiosPolicyTableSave.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServersBiosPolicyTableSave(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersBiosPolicy> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersBiosPolicy> serversBiosPolicyList = (List<ServersBiosPolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersBiosPolicy.class);
					ServersBiosPolicy sbp = serversBiosPolicyList.get(0);

					if (sbp.getId() != 0) {
						ServersBiosPolicy fetchSingleBp = this.serversService
								.fetchSingleBiosPolicyDetail(sbp.getId());

						if (fetchSingleBp != null) {
							fetchSingleBp.setName(sbp.getName());
							fetchSingleBp.setDescription(sbp.getDescription());
							fetchSingleBp.setOrganizations(sbp
									.getOrganizations());
							List<ServersBiosPolicy> serversBiosPolicyUpdateList = new ArrayList<>();
							serversBiosPolicyUpdateList.add(fetchSingleBp);
							newlyAddedRecords = this.serversService
									.saveOrUpdateBiosPolicyDetails(
											serversBiosPolicyUpdateList,
											projectId);
						}
					} else {
						newlyAddedRecords = this.serversService
								.saveOrUpdateBiosPolicyDetails(
										serversBiosPolicyList, projectId);
					}
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersBiosPolicy> deletedServersBiosPolicyList = (List<ServersBiosPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersBiosPolicy.class);
					this.serversService
							.deleteServersBiosPolicyDetails(deletedServersBiosPolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return getServersBiosPolicyTableSaveJsonList(newlyAddedRecords);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageServersBiosPolicy.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageServersBiosPolicy(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		LOGGER.debug(JSONSTRING + jsonObj);
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<ServersBiosPolicy> newlyAddedRecords = null;

		if (Util.isStringNotEmpty(jsonObj) && projectId != null) {

			try {
				JsonNode addOrUpdateNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdateNodes)) {
					List<ServersBiosPolicy> serversBiosPolicyList = (List<ServersBiosPolicy>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdateNodes.toString(),
									ServersBiosPolicy.class);
					newlyAddedRecords = this.serversService
							.saveOrUpdateBiosPolicyDetails(
									serversBiosPolicyList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ServersBiosPolicy> deletedServersBiosPolicyList = (List<ServersBiosPolicy>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ServersBiosPolicy.class);
					this.serversService
							.deleteServersBiosPolicyDetails(deletedServersBiosPolicyList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}

		return getServersBiosPolicyTableSaveJsonList(newlyAddedRecords);
	}

	@RequestMapping(value = "/getSingleBiosPolicy.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getSingleBiosPolicy(
			@RequestParam(JSONOBJ) Integer biosPolicyId) throws IOException {
		List<Object> jsonList = new ArrayList<Object>();

		if (biosPolicyId != null) {

			try {
				ServersBiosPolicy biosPolicy = this.serversService
						.fetchSingleBiosPolicyDetail(biosPolicyId);

				if (biosPolicy != null) {
					Map<String, Object> myMap = new HashMap<String, Object>();
					myMap.put(ID, biosPolicy.getId());
					myMap.put(NAME, biosPolicy.getName());
					myMap.put(DESCRIPTION, biosPolicy.getDescription());
					myMap.put(REBOOTONCHANGE, biosPolicy.getRebootOnChange());
					myMap.put(QUIETBOOT, biosPolicy.getQuietBoot());
					myMap.put(POSTERRORPAUSE, biosPolicy.getPostErrorPause());
					myMap.put(RESUMEACONPOWERLOSS,
							biosPolicy.getResumeAcOnPowerLoss());
					myMap.put(FRONTPANELLOCKOUT,
							biosPolicy.getFrontPanelLockout());
					myMap.put(TURBOBOOST, biosPolicy.getTurboBoost());
					myMap.put(ENHANCEDINTELSPEEDSTEP,
							biosPolicy.getEnhancedIntelSpeedstep());
					myMap.put(HYPERTHREADING, biosPolicy.getHyperThreading());
					myMap.put(COREMULTIPROCESSING,
							biosPolicy.getCoreMultiProcessing());
					myMap.put(EXECUTEDISABLEDBIT,
							biosPolicy.getExecuteDisabledBit());
					myMap.put(VIRTUALIZATIONTECHNOLOGY,
							biosPolicy.getVirtualizationTechnology());
					myMap.put(HARDWAREPREFETCHER,
							biosPolicy.getHardwarePrefetcher());
					myMap.put(ADJACENTCACHELINEPREFETCHER,
							biosPolicy.getAdjacentCacheLinePrefetcher());
					myMap.put(DCUSTREAMERPREFETCH,
							biosPolicy.getDcuStreamerPrefetch());
					myMap.put(DCUIPPREFETCH, biosPolicy.getDcuIpPrefetch());
					myMap.put(DIRECTCACHEACCESS,
							biosPolicy.getDirectCacheAccess());
					myMap.put(PROCESSORCSTATE, biosPolicy.getProcessorCState());
					myMap.put(PROCESSORC1E, biosPolicy.getProcessorC1e());
					myMap.put(PROCESSORC3REPORT,
							biosPolicy.getProcessorC3Report());
					myMap.put(PROCESSORC6REPORT,
							biosPolicy.getProcessorC6Report());
					myMap.put(PROCESSORC7REPORT,
							biosPolicy.getProcessorC7Report());
					myMap.put(CPUPERFORMANCE, biosPolicy.getCpuPerformance());
					myMap.put(MAXVARIABLEMTRRSETTING,
							biosPolicy.getMaxVariableMtrrSetting());
					myMap.put(LOCALX2APIC, biosPolicy.getLocalX2Apic());
					myMap.put(POWERTECHNOLOGY, biosPolicy.getPowerTechnology());
					myMap.put(ENERGYPERFORMANCE,
							biosPolicy.getEnergyPerformance());
					myMap.put(FREQUENCYFLOOROVERRIDE,
							biosPolicy.getFrequencyFloorOverride());
					myMap.put(PSTATECOORDINATION,
							biosPolicy.getPstateCoordination());
					myMap.put(DRAMCLOCKTHROTTLING,
							biosPolicy.getDramClockThrottling());
					myMap.put(CHANNELINTERLEAVING,
							biosPolicy.getChannelInterleaving());
					myMap.put(RANKINTERLEAVING,
							biosPolicy.getRankInterleaving());
					myMap.put(DEMANDSCRUB, biosPolicy.getDemandScrub());
					myMap.put(PATROLSCRUB, biosPolicy.getPatrolScrub());
					myMap.put(VTFORDIRECTEDIO, biosPolicy.getVtForDirectedIo());
					myMap.put(INTERRUPTREMAP, biosPolicy.getInterruptRemap());
					myMap.put(COHERENCYSUPPORT,
							biosPolicy.getCoherencySupport());
					myMap.put(ATSSUPPORT, biosPolicy.getAtsSupport());
					myMap.put(PASSTHROUGHDMASUPPORT,
							biosPolicy.getPassThroughDmaSupport());
					myMap.put(MEMORYRASCONFIG, biosPolicy.getMemoryRasConfig());
					myMap.put(NUMA, biosPolicy.getNuma());
					myMap.put(LVDDRMODE, biosPolicy.getLvDdrMode());
					myMap.put(DRAMREFRESHRATE, biosPolicy.getDramRefreshRate());
					myMap.put(MIRRORINGMODE, biosPolicy.getMirroringMode());
					myMap.put(SPARINGMODE, biosPolicy.getSparingMode());
					myMap.put(SERIALPORTA, biosPolicy.getSerialPortA());
					myMap.put(MAKEDEVICENONBOOTABLE,
							biosPolicy.getMakeDeviceNonBootable());
					myMap.put(LEGACYUSBSUPPORT,
							biosPolicy.getLegacyUsbSupport());
					myMap.put(USBSYSTEMIDLEPOWEROPTIMIZINGSETTING,
							biosPolicy.getUsbSystemIdlePowerOptimizingSetting());
					myMap.put(USBFRONTPANELACCESSLOCK,
							biosPolicy.getUsbFrontPanelAccessLock());
					myMap.put(PORT6064EMULATION,
							biosPolicy.getPort6064Emulation());
					myMap.put(USBPORTFRONT, biosPolicy.getUsbPortFront());
					myMap.put(USBPORTINTERNAL, biosPolicy.getUsbPortInternal());
					myMap.put(USBPORTKVM, biosPolicy.getUsbPortKvm());
					myMap.put(USBPORTREAR, biosPolicy.getUsbPortRear());
					myMap.put(USBPORTSDCARD, biosPolicy.getUsbPortSdCard());
					myMap.put(USBPORTVMEDIA, biosPolicy.getUsbPortVMedia());
					myMap.put(ALLUSBDEVICES, biosPolicy.getAllUsbDevices());
					myMap.put(MAXMEMORYBELOW4G,
							biosPolicy.getMaxMemoryBelow4g());
					myMap.put(MEMORYMAPPEDIOABOVE4GBCONFIG,
							biosPolicy.getMemoryMappedIoAbove4gbConfig());
					myMap.put(VGAPRIORITY, biosPolicy.getVgaPriority());
					myMap.put(QPILINKFREQUENCYSELECT,
							biosPolicy.getQpiLinkFrequencySelect());
					myMap.put(ALLONBOARDLOMPORTS,
							biosPolicy.getAllOnboardLomPorts());
					myMap.put(LOMPORT0OPTIONROM,
							biosPolicy.getLomPort0OptionRom());
					myMap.put(LOMPORT1OPTIONROM,
							biosPolicy.getLomPort1OptionRom());
					myMap.put(LOMPORT2OPTIONROM,
							biosPolicy.getLomPort2OptionRom());
					myMap.put(LOMPORT3OPTIONROM,
							biosPolicy.getLomPort3OptionRom());
					myMap.put(PCIESLOTSASOPTIONROM,
							biosPolicy.getPcieSlotSasOptionRom());
					myMap.put(PCIESLOT1LINKSPEED,
							biosPolicy.getPcieSlot1LinkSpeed());
					myMap.put(PCIESLOT2LINKSPEED,
							biosPolicy.getPcieSlot2LinkSpeed());
					myMap.put(PCIESLOT3LINKSPEED,
							biosPolicy.getPcieSlot3LinkSpeed());
					myMap.put(PCIESLOT4LINKSPEED,
							biosPolicy.getPcieSlot4LinkSpeed());
					myMap.put(PCIESLOT5LINKSPEED,
							biosPolicy.getPcieSlot5LinkSpeed());
					myMap.put(PCIESLOT6LINKSPEED,
							biosPolicy.getPcieSlot6LinkSpeed());
					myMap.put(PCIESLOT7LINKSPEED,
							biosPolicy.getPcieSlot7LinkSpeed());
					myMap.put(PCIESLOT8LINKSPEED,
							biosPolicy.getPcieSlot8LinkSpeed());
					myMap.put(PCIESLOT9LINKSPEED,
							biosPolicy.getPcieSlot9LinkSpeed());
					myMap.put(PCIESLOT10LINKSPEED,
							biosPolicy.getPcieSlot10LinkSpeed());
					myMap.put(BOOTOPTIONRETRY, biosPolicy.getBootOptionRetry());
					myMap.put(INTELENTRYSASRAID,
							biosPolicy.getIntelEntrySasRaid());
					myMap.put(INTELENTRYSASRAIDMODULE,
							biosPolicy.getIntelEntrySasRaidModule());
					myMap.put(ONBOARDSCUSTORAGESUPPORT,
							biosPolicy.getOnboardScuStorageSupport());
					myMap.put(ASSERTNMIONSERR, biosPolicy.getAssertNmiOnSerr());
					myMap.put(ASSERTNMIONPERR, biosPolicy.getAssertNmiOnPerr());
					myMap.put(OSBOOTWATCHDOGTIMER,
							biosPolicy.getOsBootWatchdogTimer());
					myMap.put(OSBOOTWATCHDOGTIMERTIMEOUTPOLICY,
							biosPolicy.getOsBootWatchdogTimerTimeoutPolicy());
					myMap.put(OSBOOTWATCHDOGTIMERTIMEOUT,
							biosPolicy.getOsBootWatchdogTimerTimeout());
					myMap.put(FRB2TIMER, biosPolicy.getFrb2Timer());
					myMap.put(CONSOLEREDIRECTION,
							biosPolicy.getConsoleRedirection());
					myMap.put(FLOWCONTROL, biosPolicy.getFlowControl());
					myMap.put(BAUDRATE, biosPolicy.getBaudRate());
					myMap.put(TERMINALTYPE, biosPolicy.getTerminalType());
					myMap.put(LEGACYOSREDIRECT,
							biosPolicy.getLegacyOsRedirect());
					myMap.put(PUTTYKEYPAD, biosPolicy.getPuttyKeypad());

					myMap.put(ORGANIZATIONS, Util
							.nullValidationAndReturnId(biosPolicy
									.getOrganizations()));

					Util.convertMapToJson(myMap, jsonList);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
		}
		return jsonList;
	}

}
