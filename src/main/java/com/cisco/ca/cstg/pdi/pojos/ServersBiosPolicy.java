package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servers_bios_policy")
public class ServersBiosPolicy implements java.io.Serializable {

	private static final long serialVersionUID = -1734122622597276065L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private String rebootOnChange;
	private String quietBoot;
	private String postErrorPause;
	private String resumeAcOnPowerLoss;
	private String frontPanelLockout;
	private String turboBoost;
	private String enhancedIntelSpeedstep;
	private String hyperThreading;
	private String coreMultiProcessing;
	private String executeDisabledBit;
	private String virtualizationTechnology;
	private String hardwarePrefetcher;
	private String adjacentCacheLinePrefetcher;
	private String dcuStreamerPrefetch;
	private String dcuIpPrefetch;
	private String directCacheAccess;
	private String processorCState;
	private String processorC1e;
	private String processorC3Report;
	private String processorC6Report;
	private String processorC7Report;
	private String cpuPerformance;
	private String maxVariableMtrrSetting;
	private String localX2Apic;
	private String powerTechnology;
	private String energyPerformance;
	private String frequencyFloorOverride;
	private String pstateCoordination;
	private String dramClockThrottling;
	private String channelInterleaving;
	private String rankInterleaving;
	private String demandScrub;
	private String patrolScrub;
	private String vtForDirectedIo;
	private String interruptRemap;
	private String coherencySupport;
	private String atsSupport;
	private String passThroughDmaSupport;
	private String memoryRasConfig;
	private String numa;
	private String lvDdrMode;
	private String dramRefreshRate;
	private String mirroringMode;
	private String sparingMode;
	private String serialPortA;
	private String makeDeviceNonBootable;
	private String legacyUsbSupport;
	private String usbSystemIdlePowerOptimizingSetting;
	private String usbFrontPanelAccessLock;
	private String port6064Emulation;
	private String usbPortFront;
	private String usbPortInternal;
	private String usbPortKvm;
	private String usbPortRear;
	private String usbPortSdCard;
	private String usbPortVMedia;
	private String allUsbDevices;
	private String maxMemoryBelow4g;
	private String memoryMappedIoAbove4gbConfig;
	private String vgaPriority;
	private String qpiLinkFrequencySelect;
	private String allOnboardLomPorts;
	private String lomPort0OptionRom;
	private String lomPort1OptionRom;
	private String lomPort2OptionRom;
	private String lomPort3OptionRom;
	private String pcieSlotSasOptionRom;
	private String pcieSlot1LinkSpeed;
	private String pcieSlot2LinkSpeed;
	private String pcieSlot3LinkSpeed;
	private String pcieSlot4LinkSpeed;
	private String pcieSlot5LinkSpeed;
	private String pcieSlot6LinkSpeed;
	private String pcieSlot7LinkSpeed;
	private String pcieSlot8LinkSpeed;
	private String pcieSlot9LinkSpeed;
	private String pcieSlot10LinkSpeed;
	private String bootOptionRetry;
	private String intelEntrySasRaid;
	private String intelEntrySasRaidModule;
	private String onboardScuStorageSupport;
	private String assertNmiOnSerr;
	private String assertNmiOnPerr;
	private String osBootWatchdogTimer;
	private String osBootWatchdogTimerTimeoutPolicy;
	private String osBootWatchdogTimerTimeout;
	private String frb2Timer;
	private String consoleRedirection;
	private String flowControl;
	private String baudRate;
	private String terminalType;
	private String legacyOsRedirect;
	private String puttyKeypad;
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>();

	public ServersBiosPolicy() {
	}

	public ServersBiosPolicy(Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@Column(name = "name", length = 24)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "reboot_on_change", length = 24)
	public String getRebootOnChange() {
		return this.rebootOnChange;
	}

	public void setRebootOnChange(String rebootOnChange) {
		this.rebootOnChange = rebootOnChange;
	}

	@Column(name = "quiet_boot", length = 24)
	public String getQuietBoot() {
		return this.quietBoot;
	}

	public void setQuietBoot(String quietBoot) {
		this.quietBoot = quietBoot;
	}

	@Column(name = "post_error_pause", length = 24)
	public String getPostErrorPause() {
		return this.postErrorPause;
	}

	public void setPostErrorPause(String postErrorPause) {
		this.postErrorPause = postErrorPause;
	}

	@Column(name = "resume_ac_on_power_loss", length = 24)
	public String getResumeAcOnPowerLoss() {
		return this.resumeAcOnPowerLoss;
	}

	public void setResumeAcOnPowerLoss(String resumeAcOnPowerLoss) {
		this.resumeAcOnPowerLoss = resumeAcOnPowerLoss;
	}

	@Column(name = "front_panel_lockout", length = 24)
	public String getFrontPanelLockout() {
		return this.frontPanelLockout;
	}

	public void setFrontPanelLockout(String frontPanelLockout) {
		this.frontPanelLockout = frontPanelLockout;
	}

	@Column(name = "turbo_boost", length = 24)
	public String getTurboBoost() {
		return this.turboBoost;
	}

	public void setTurboBoost(String turboBoost) {
		this.turboBoost = turboBoost;
	}

	@Column(name = "enhanced_intel_speedstep", length = 24)
	public String getEnhancedIntelSpeedstep() {
		return this.enhancedIntelSpeedstep;
	}

	public void setEnhancedIntelSpeedstep(String enhancedIntelSpeedstep) {
		this.enhancedIntelSpeedstep = enhancedIntelSpeedstep;
	}

	@Column(name = "hyper_threading", length = 24)
	public String getHyperThreading() {
		return this.hyperThreading;
	}

	public void setHyperThreading(String hyperThreading) {
		this.hyperThreading = hyperThreading;
	}

	@Column(name = "core_multi_processing", length = 24)
	public String getCoreMultiProcessing() {
		return this.coreMultiProcessing;
	}

	public void setCoreMultiProcessing(String coreMultiProcessing) {
		this.coreMultiProcessing = coreMultiProcessing;
	}

	@Column(name = "execute_disabled_bit", length = 24)
	public String getExecuteDisabledBit() {
		return this.executeDisabledBit;
	}

	public void setExecuteDisabledBit(String executeDisabledBit) {
		this.executeDisabledBit = executeDisabledBit;
	}

	@Column(name = "virtualization_technology", length = 24)
	public String getVirtualizationTechnology() {
		return this.virtualizationTechnology;
	}

	public void setVirtualizationTechnology(String virtualizationTechnology) {
		this.virtualizationTechnology = virtualizationTechnology;
	}

	@Column(name = "hardware_prefetcher", length = 24)
	public String getHardwarePrefetcher() {
		return this.hardwarePrefetcher;
	}

	public void setHardwarePrefetcher(String hardwarePrefetcher) {
		this.hardwarePrefetcher = hardwarePrefetcher;
	}

	@Column(name = "adjacent_cache_line_prefetcher", length = 24)
	public String getAdjacentCacheLinePrefetcher() {
		return this.adjacentCacheLinePrefetcher;
	}

	public void setAdjacentCacheLinePrefetcher(
			String adjacentCacheLinePrefetcher) {
		this.adjacentCacheLinePrefetcher = adjacentCacheLinePrefetcher;
	}

	@Column(name = "dcu_streamer_prefetch", length = 24)
	public String getDcuStreamerPrefetch() {
		return this.dcuStreamerPrefetch;
	}

	public void setDcuStreamerPrefetch(String dcuStreamerPrefetch) {
		this.dcuStreamerPrefetch = dcuStreamerPrefetch;
	}

	@Column(name = "dcu_ip_prefetch", length = 24)
	public String getDcuIpPrefetch() {
		return this.dcuIpPrefetch;
	}

	public void setDcuIpPrefetch(String dcuIpPrefetch) {
		this.dcuIpPrefetch = dcuIpPrefetch;
	}

	@Column(name = "direct_cache_access", length = 24)
	public String getDirectCacheAccess() {
		return this.directCacheAccess;
	}

	public void setDirectCacheAccess(String directCacheAccess) {
		this.directCacheAccess = directCacheAccess;
	}

	@Column(name = "processor_c_state", length = 24)
	public String getProcessorCState() {
		return this.processorCState;
	}

	public void setProcessorCState(String processorCState) {
		this.processorCState = processorCState;
	}

	@Column(name = "processor_c1e", length = 24)
	public String getProcessorC1e() {
		return this.processorC1e;
	}

	public void setProcessorC1e(String processorC1e) {
		this.processorC1e = processorC1e;
	}

	@Column(name = "processor_c3_report", length = 24)
	public String getProcessorC3Report() {
		return this.processorC3Report;
	}

	public void setProcessorC3Report(String processorC3Report) {
		this.processorC3Report = processorC3Report;
	}

	@Column(name = "processor_c6_report", length = 24)
	public String getProcessorC6Report() {
		return this.processorC6Report;
	}

	public void setProcessorC6Report(String processorC6Report) {
		this.processorC6Report = processorC6Report;
	}

	@Column(name = "processor_c7_report", length = 24)
	public String getProcessorC7Report() {
		return this.processorC7Report;
	}

	public void setProcessorC7Report(String processorC7Report) {
		this.processorC7Report = processorC7Report;
	}

	@Column(name = "cpu_performance", length = 24)
	public String getCpuPerformance() {
		return this.cpuPerformance;
	}

	public void setCpuPerformance(String cpuPerformance) {
		this.cpuPerformance = cpuPerformance;
	}

	@Column(name = "max_variable_mtrr_setting", length = 24)
	public String getMaxVariableMtrrSetting() {
		return this.maxVariableMtrrSetting;
	}

	public void setMaxVariableMtrrSetting(String maxVariableMtrrSetting) {
		this.maxVariableMtrrSetting = maxVariableMtrrSetting;
	}

	@Column(name = "local_x2_apic", length = 24)
	public String getLocalX2Apic() {
		return this.localX2Apic;
	}

	public void setLocalX2Apic(String localX2Apic) {
		this.localX2Apic = localX2Apic;
	}

	@Column(name = "power_technology", length = 24)
	public String getPowerTechnology() {
		return this.powerTechnology;
	}

	public void setPowerTechnology(String powerTechnology) {
		this.powerTechnology = powerTechnology;
	}

	@Column(name = "energy_performance", length = 24)
	public String getEnergyPerformance() {
		return this.energyPerformance;
	}

	public void setEnergyPerformance(String energyPerformance) {
		this.energyPerformance = energyPerformance;
	}

	@Column(name = "frequency_floor_override", length = 24)
	public String getFrequencyFloorOverride() {
		return this.frequencyFloorOverride;
	}

	public void setFrequencyFloorOverride(String frequencyFloorOverride) {
		this.frequencyFloorOverride = frequencyFloorOverride;
	}

	@Column(name = "pstate_coordination", length = 24)
	public String getPstateCoordination() {
		return this.pstateCoordination;
	}

	public void setPstateCoordination(String pstateCoordination) {
		this.pstateCoordination = pstateCoordination;
	}

	@Column(name = "dram_clock_throttling", length = 24)
	public String getDramClockThrottling() {
		return this.dramClockThrottling;
	}

	public void setDramClockThrottling(String dramClockThrottling) {
		this.dramClockThrottling = dramClockThrottling;
	}

	@Column(name = "channel_interleaving", length = 24)
	public String getChannelInterleaving() {
		return this.channelInterleaving;
	}

	public void setChannelInterleaving(String channelInterleaving) {
		this.channelInterleaving = channelInterleaving;
	}

	@Column(name = "rank_interleaving", length = 24)
	public String getRankInterleaving() {
		return this.rankInterleaving;
	}

	public void setRankInterleaving(String rankInterleaving) {
		this.rankInterleaving = rankInterleaving;
	}

	@Column(name = "demand_scrub", length = 24)
	public String getDemandScrub() {
		return this.demandScrub;
	}

	public void setDemandScrub(String demandScrub) {
		this.demandScrub = demandScrub;
	}

	@Column(name = "patrol_scrub", length = 24)
	public String getPatrolScrub() {
		return this.patrolScrub;
	}

	public void setPatrolScrub(String patrolScrub) {
		this.patrolScrub = patrolScrub;
	}

	@Column(name = "vt_for_directed_io", length = 24)
	public String getVtForDirectedIo() {
		return this.vtForDirectedIo;
	}

	public void setVtForDirectedIo(String vtForDirectedIo) {
		this.vtForDirectedIo = vtForDirectedIo;
	}

	@Column(name = "interrupt_remap", length = 24)
	public String getInterruptRemap() {
		return this.interruptRemap;
	}

	public void setInterruptRemap(String interruptRemap) {
		this.interruptRemap = interruptRemap;
	}

	@Column(name = "coherency_support", length = 24)
	public String getCoherencySupport() {
		return this.coherencySupport;
	}

	public void setCoherencySupport(String coherencySupport) {
		this.coherencySupport = coherencySupport;
	}

	@Column(name = "ats_support", length = 24)
	public String getAtsSupport() {
		return this.atsSupport;
	}

	public void setAtsSupport(String atsSupport) {
		this.atsSupport = atsSupport;
	}

	@Column(name = "pass_through_dma_support", length = 24)
	public String getPassThroughDmaSupport() {
		return this.passThroughDmaSupport;
	}

	public void setPassThroughDmaSupport(String passThroughDmaSupport) {
		this.passThroughDmaSupport = passThroughDmaSupport;
	}

	@Column(name = "memory_ras_config", length = 24)
	public String getMemoryRasConfig() {
		return this.memoryRasConfig;
	}

	public void setMemoryRasConfig(String memoryRasConfig) {
		this.memoryRasConfig = memoryRasConfig;
	}

	@Column(name = "numa", length = 24)
	public String getNuma() {
		return this.numa;
	}

	public void setNuma(String numa) {
		this.numa = numa;
	}

	@Column(name = "lv_ddr_mode", length = 24)
	public String getLvDdrMode() {
		return this.lvDdrMode;
	}

	public void setLvDdrMode(String lvDdrMode) {
		this.lvDdrMode = lvDdrMode;
	}

	@Column(name = "dram_refresh_rate", length = 24)
	public String getDramRefreshRate() {
		return this.dramRefreshRate;
	}

	public void setDramRefreshRate(String dramRefreshRate) {
		this.dramRefreshRate = dramRefreshRate;
	}

	@Column(name = "mirroring_mode", length = 24)
	public String getMirroringMode() {
		return this.mirroringMode;
	}

	public void setMirroringMode(String mirroringMode) {
		this.mirroringMode = mirroringMode;
	}

	@Column(name = "sparing_mode", length = 24)
	public String getSparingMode() {
		return this.sparingMode;
	}

	public void setSparingMode(String sparingMode) {
		this.sparingMode = sparingMode;
	}

	@Column(name = "serial_port_a", length = 24)
	public String getSerialPortA() {
		return this.serialPortA;
	}

	public void setSerialPortA(String serialPortA) {
		this.serialPortA = serialPortA;
	}

	@Column(name = "make_device_non_bootable", length = 24)
	public String getMakeDeviceNonBootable() {
		return this.makeDeviceNonBootable;
	}

	public void setMakeDeviceNonBootable(String makeDeviceNonBootable) {
		this.makeDeviceNonBootable = makeDeviceNonBootable;
	}

	@Column(name = "legacy_usb_support", length = 24)
	public String getLegacyUsbSupport() {
		return this.legacyUsbSupport;
	}

	public void setLegacyUsbSupport(String legacyUsbSupport) {
		this.legacyUsbSupport = legacyUsbSupport;
	}

	@Column(name = "usb_system_idle_power_optimizing_setting", length = 24)
	public String getUsbSystemIdlePowerOptimizingSetting() {
		return this.usbSystemIdlePowerOptimizingSetting;
	}

	public void setUsbSystemIdlePowerOptimizingSetting(
			String usbSystemIdlePowerOptimizingSetting) {
		this.usbSystemIdlePowerOptimizingSetting = usbSystemIdlePowerOptimizingSetting;
	}

	@Column(name = "usb_front_panel_access_lock", length = 24)
	public String getUsbFrontPanelAccessLock() {
		return this.usbFrontPanelAccessLock;
	}

	public void setUsbFrontPanelAccessLock(String usbFrontPanelAccessLock) {
		this.usbFrontPanelAccessLock = usbFrontPanelAccessLock;
	}

	@Column(name = "port_6064_emulation", length = 24)
	public String getPort6064Emulation() {
		return this.port6064Emulation;
	}

	public void setPort6064Emulation(String port6064Emulation) {
		this.port6064Emulation = port6064Emulation;
	}

	@Column(name = "usb_port_front", length = 24)
	public String getUsbPortFront() {
		return this.usbPortFront;
	}

	public void setUsbPortFront(String usbPortFront) {
		this.usbPortFront = usbPortFront;
	}

	@Column(name = "usb_port_internal", length = 24)
	public String getUsbPortInternal() {
		return this.usbPortInternal;
	}

	public void setUsbPortInternal(String usbPortInternal) {
		this.usbPortInternal = usbPortInternal;
	}

	@Column(name = "usb_port_kvm", length = 24)
	public String getUsbPortKvm() {
		return this.usbPortKvm;
	}

	public void setUsbPortKvm(String usbPortKvm) {
		this.usbPortKvm = usbPortKvm;
	}

	@Column(name = "usb_port_rear", length = 24)
	public String getUsbPortRear() {
		return this.usbPortRear;
	}

	public void setUsbPortRear(String usbPortRear) {
		this.usbPortRear = usbPortRear;
	}

	@Column(name = "usb_port_sd_card", length = 24)
	public String getUsbPortSdCard() {
		return this.usbPortSdCard;
	}

	public void setUsbPortSdCard(String usbPortSdCard) {
		this.usbPortSdCard = usbPortSdCard;
	}

	@Column(name = "usb_port_v_media", length = 24)
	public String getUsbPortVMedia() {
		return this.usbPortVMedia;
	}

	public void setUsbPortVMedia(String usbPortVMedia) {
		this.usbPortVMedia = usbPortVMedia;
	}

	@Column(name = "all_usb_devices", length = 24)
	public String getAllUsbDevices() {
		return this.allUsbDevices;
	}

	public void setAllUsbDevices(String allUsbDevices) {
		this.allUsbDevices = allUsbDevices;
	}

	@Column(name = "max_memory_below_4g", length = 24)
	public String getMaxMemoryBelow4g() {
		return this.maxMemoryBelow4g;
	}

	public void setMaxMemoryBelow4g(String maxMemoryBelow4g) {
		this.maxMemoryBelow4g = maxMemoryBelow4g;
	}

	@Column(name = "memory_mapped_io_above_4gb_config", length = 24)
	public String getMemoryMappedIoAbove4gbConfig() {
		return this.memoryMappedIoAbove4gbConfig;
	}

	public void setMemoryMappedIoAbove4gbConfig(
			String memoryMappedIoAbove4gbConfig) {
		this.memoryMappedIoAbove4gbConfig = memoryMappedIoAbove4gbConfig;
	}

	@Column(name = "vga_priority", length = 24)
	public String getVgaPriority() {
		return this.vgaPriority;
	}

	public void setVgaPriority(String vgaPriority) {
		this.vgaPriority = vgaPriority;
	}

	@Column(name = "qpi_link_frequency_select", length = 24)
	public String getQpiLinkFrequencySelect() {
		return this.qpiLinkFrequencySelect;
	}

	public void setQpiLinkFrequencySelect(String qpiLinkFrequencySelect) {
		this.qpiLinkFrequencySelect = qpiLinkFrequencySelect;
	}

	@Column(name = "all_onboard_lom_ports", length = 24)
	public String getAllOnboardLomPorts() {
		return this.allOnboardLomPorts;
	}

	public void setAllOnboardLomPorts(String allOnboardLomPorts) {
		this.allOnboardLomPorts = allOnboardLomPorts;
	}

	@Column(name = "lom_port0_option_rom", length = 24)
	public String getLomPort0OptionRom() {
		return this.lomPort0OptionRom;
	}

	public void setLomPort0OptionRom(String lomPort0OptionRom) {
		this.lomPort0OptionRom = lomPort0OptionRom;
	}

	@Column(name = "lom_port1_option_rom", length = 24)
	public String getLomPort1OptionRom() {
		return this.lomPort1OptionRom;
	}

	public void setLomPort1OptionRom(String lomPort1OptionRom) {
		this.lomPort1OptionRom = lomPort1OptionRom;
	}

	@Column(name = "lom_port2_option_rom", length = 24)
	public String getLomPort2OptionRom() {
		return this.lomPort2OptionRom;
	}

	public void setLomPort2OptionRom(String lomPort2OptionRom) {
		this.lomPort2OptionRom = lomPort2OptionRom;
	}

	@Column(name = "lom_port3_option_rom", length = 24)
	public String getLomPort3OptionRom() {
		return this.lomPort3OptionRom;
	}

	public void setLomPort3OptionRom(String lomPort3OptionRom) {
		this.lomPort3OptionRom = lomPort3OptionRom;
	}

	@Column(name = "pcie_slot_sas_option_rom", length = 24)
	public String getPcieSlotSasOptionRom() {
		return this.pcieSlotSasOptionRom;
	}

	public void setPcieSlotSasOptionRom(String pcieSlotSasOptionRom) {
		this.pcieSlotSasOptionRom = pcieSlotSasOptionRom;
	}

	@Column(name = "pcie_slot1_link_speed", length = 24)
	public String getPcieSlot1LinkSpeed() {
		return this.pcieSlot1LinkSpeed;
	}

	public void setPcieSlot1LinkSpeed(String pcieSlot1LinkSpeed) {
		this.pcieSlot1LinkSpeed = pcieSlot1LinkSpeed;
	}

	@Column(name = "pcie_slot2_link_speed", length = 24)
	public String getPcieSlot2LinkSpeed() {
		return this.pcieSlot2LinkSpeed;
	}

	public void setPcieSlot2LinkSpeed(String pcieSlot2LinkSpeed) {
		this.pcieSlot2LinkSpeed = pcieSlot2LinkSpeed;
	}

	@Column(name = "pcie_slot3_link_speed", length = 24)
	public String getPcieSlot3LinkSpeed() {
		return this.pcieSlot3LinkSpeed;
	}

	public void setPcieSlot3LinkSpeed(String pcieSlot3LinkSpeed) {
		this.pcieSlot3LinkSpeed = pcieSlot3LinkSpeed;
	}

	@Column(name = "pcie_slot4_link_speed", length = 24)
	public String getPcieSlot4LinkSpeed() {
		return this.pcieSlot4LinkSpeed;
	}

	public void setPcieSlot4LinkSpeed(String pcieSlot4LinkSpeed) {
		this.pcieSlot4LinkSpeed = pcieSlot4LinkSpeed;
	}

	@Column(name = "pcie_slot5_link_speed", length = 24)
	public String getPcieSlot5LinkSpeed() {
		return this.pcieSlot5LinkSpeed;
	}

	public void setPcieSlot5LinkSpeed(String pcieSlot5LinkSpeed) {
		this.pcieSlot5LinkSpeed = pcieSlot5LinkSpeed;
	}

	@Column(name = "pcie_slot6_link_speed", length = 24)
	public String getPcieSlot6LinkSpeed() {
		return this.pcieSlot6LinkSpeed;
	}

	public void setPcieSlot6LinkSpeed(String pcieSlot6LinkSpeed) {
		this.pcieSlot6LinkSpeed = pcieSlot6LinkSpeed;
	}

	@Column(name = "pcie_slot7_link_speed", length = 24)
	public String getPcieSlot7LinkSpeed() {
		return this.pcieSlot7LinkSpeed;
	}

	public void setPcieSlot7LinkSpeed(String pcieSlot7LinkSpeed) {
		this.pcieSlot7LinkSpeed = pcieSlot7LinkSpeed;
	}

	@Column(name = "pcie_slot8_link_speed", length = 24)
	public String getPcieSlot8LinkSpeed() {
		return this.pcieSlot8LinkSpeed;
	}

	public void setPcieSlot8LinkSpeed(String pcieSlot8LinkSpeed) {
		this.pcieSlot8LinkSpeed = pcieSlot8LinkSpeed;
	}

	@Column(name = "pcie_slot9_link_speed", length = 24)
	public String getPcieSlot9LinkSpeed() {
		return this.pcieSlot9LinkSpeed;
	}

	public void setPcieSlot9LinkSpeed(String pcieSlot9LinkSpeed) {
		this.pcieSlot9LinkSpeed = pcieSlot9LinkSpeed;
	}

	@Column(name = "pcie_slot10_link_speed", length = 24)
	public String getPcieSlot10LinkSpeed() {
		return this.pcieSlot10LinkSpeed;
	}

	public void setPcieSlot10LinkSpeed(String pcieSlot10LinkSpeed) {
		this.pcieSlot10LinkSpeed = pcieSlot10LinkSpeed;
	}

	@Column(name = "boot_option_retry", length = 24)
	public String getBootOptionRetry() {
		return this.bootOptionRetry;
	}

	public void setBootOptionRetry(String bootOptionRetry) {
		this.bootOptionRetry = bootOptionRetry;
	}

	@Column(name = "intel_entry_sas_raid", length = 24)
	public String getIntelEntrySasRaid() {
		return this.intelEntrySasRaid;
	}

	public void setIntelEntrySasRaid(String intelEntrySasRaid) {
		this.intelEntrySasRaid = intelEntrySasRaid;
	}

	@Column(name = "intel_entry_sas_raid_module", length = 24)
	public String getIntelEntrySasRaidModule() {
		return this.intelEntrySasRaidModule;
	}

	public void setIntelEntrySasRaidModule(String intelEntrySasRaidModule) {
		this.intelEntrySasRaidModule = intelEntrySasRaidModule;
	}

	@Column(name = "onboard_scu_storage_support", length = 24)
	public String getOnboardScuStorageSupport() {
		return this.onboardScuStorageSupport;
	}

	public void setOnboardScuStorageSupport(String onboardScuStorageSupport) {
		this.onboardScuStorageSupport = onboardScuStorageSupport;
	}

	@Column(name = "assert_nmi_on_serr", length = 24)
	public String getAssertNmiOnSerr() {
		return this.assertNmiOnSerr;
	}

	public void setAssertNmiOnSerr(String assertNmiOnSerr) {
		this.assertNmiOnSerr = assertNmiOnSerr;
	}

	@Column(name = "assert_nmi_on_perr", length = 24)
	public String getAssertNmiOnPerr() {
		return this.assertNmiOnPerr;
	}

	public void setAssertNmiOnPerr(String assertNmiOnPerr) {
		this.assertNmiOnPerr = assertNmiOnPerr;
	}

	@Column(name = "os_boot_watchdog_timer", length = 24)
	public String getOsBootWatchdogTimer() {
		return this.osBootWatchdogTimer;
	}

	public void setOsBootWatchdogTimer(String osBootWatchdogTimer) {
		this.osBootWatchdogTimer = osBootWatchdogTimer;
	}

	@Column(name = "os_boot_watchdog_timer_timeout_policy", length = 24)
	public String getOsBootWatchdogTimerTimeoutPolicy() {
		return this.osBootWatchdogTimerTimeoutPolicy;
	}

	public void setOsBootWatchdogTimerTimeoutPolicy(
			String osBootWatchdogTimerTimeoutPolicy) {
		this.osBootWatchdogTimerTimeoutPolicy = osBootWatchdogTimerTimeoutPolicy;
	}

	@Column(name = "os_boot_watchdog_timer_timeout", length = 24)
	public String getOsBootWatchdogTimerTimeout() {
		return this.osBootWatchdogTimerTimeout;
	}

	public void setOsBootWatchdogTimerTimeout(String osBootWatchdogTimerTimeout) {
		this.osBootWatchdogTimerTimeout = osBootWatchdogTimerTimeout;
	}

	@Column(name = "frb2_timer", length = 24)
	public String getFrb2Timer() {
		return this.frb2Timer;
	}

	public void setFrb2Timer(String frb2Timer) {
		this.frb2Timer = frb2Timer;
	}

	@Column(name = "console_redirection", length = 24)
	public String getConsoleRedirection() {
		return this.consoleRedirection;
	}

	public void setConsoleRedirection(String consoleRedirection) {
		this.consoleRedirection = consoleRedirection;
	}

	@Column(name = "flow_control", length = 24)
	public String getFlowControl() {
		return this.flowControl;
	}

	public void setFlowControl(String flowControl) {
		this.flowControl = flowControl;
	}

	@Column(name = "baud_rate", length = 24)
	public String getBaudRate() {
		return this.baudRate;
	}

	public void setBaudRate(String baudRate) {
		this.baudRate = baudRate;
	}

	@Column(name = "terminal_type", length = 24)
	public String getTerminalType() {
		return this.terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	@Column(name = "legacy_os_redirect", length = 24)
	public String getLegacyOsRedirect() {
		return this.legacyOsRedirect;
	}

	public void setLegacyOsRedirect(String legacyOsRedirect) {
		this.legacyOsRedirect = legacyOsRedirect;
	}

	@Column(name = "putty_keypad", length = 24)
	public String getPuttyKeypad() {
		return this.puttyKeypad;
	}

	public void setPuttyKeypad(String puttyKeypad) {
		this.puttyKeypad = puttyKeypad;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversBiosPolicy")
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}

}
