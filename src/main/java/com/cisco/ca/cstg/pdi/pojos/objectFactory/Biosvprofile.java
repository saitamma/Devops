package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="biosVProfile")
public class Biosvprofile implements java.io.Serializable {

	private static final long serialVersionUID = -2542049392260989844L;
	private Integer primaryKey;
	private Orgorg orgOrg;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String dn;
	@XmlAttribute
	private String intId;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String policyLevel;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String rebootOnUpdate;
	
	@XmlElement(name="biosVfUSBSystemIdlePowerOptimizingSetting")
	private Biosvfusbsystemidlepoweroptimizingsetting biosvfusbsystemidlepoweroptimizingsettings = new Biosvfusbsystemidlepoweroptimizingsetting();
	@XmlElement(name="biosVfVGAPriority")
	private Biosvfvgapriority biosvfvgapriorities = new Biosvfvgapriority();
	@XmlElement(name="biosVfUEFIOSUseLegacyVideo")
	private Biosvfuefiosuselegacyvideo biosvfuefiosuselegacyvideos = new Biosvfuefiosuselegacyvideo();
	@XmlElement(name="biosVfUSBBootConfig")
	private Biosvfusbbootconfig biosvfusbbootconfigs = new Biosvfusbbootconfig();
	@XmlElement(name="biosVfUSBFrontPanelAccessLock")
	private Biosvfusbfrontpanelaccesslock biosvfusbfrontpanelaccesslocks  = new Biosvfusbfrontpanelaccesslock();
	@XmlElement(name="biosVfUSBPortConfiguration")
	private Biosvfusbportconfiguration biosvfusbportconfigurations  = new Biosvfusbportconfiguration();
	@XmlElement(name="biosVfOSBootWatchdogTimerPolicy")
	private Biosvfosbootwatchdogtimerpolicy biosvfosbootwatchdogtimerpolicies  = new Biosvfosbootwatchdogtimerpolicy();
	@XmlElement(name="biosVfOSBootWatchdogTimer")
	private Biosvfosbootwatchdogtimer biosvfosbootwatchdogtimers  = new Biosvfosbootwatchdogtimer();
	@XmlElement(name="biosVfPackageCStateLimit")
	private Biosvfpackagecstatelimit biosvfpackagecstatelimits  = new Biosvfpackagecstatelimit();
	@XmlElement(name="biosVfOSBootWatchdogTimerTimeout")
	private Biosvfosbootwatchdogtimertimeout biosvfosbootwatchdogtimertimeouts  = new Biosvfosbootwatchdogtimertimeout();
	@XmlElement(name="biosVfUCSMBootOrderRuleControl")
	private Biosvfucsmbootorderrulecontrol biosvfucsmbootorderrulecontrols  = new Biosvfucsmbootorderrulecontrol();
	@XmlElement(name="biosVfNUMAOptimized")
	private Biosvfnumaoptimized biosvfnumaoptimizeds  = new Biosvfnumaoptimized();
	@XmlElement(name="biosVfMirroringMode")
	private Biosvfmirroringmode biosvfmirroringmodes  = new Biosvfmirroringmode();
	@XmlElement(name="biosVfOnboardStorage")
	private Biosvfonboardstorage biosvfonboardstorages  = new Biosvfonboardstorage();
	@XmlElement(name="biosVfOnboardSATAController")
	private Biosvfonboardsatacontroller biosvfonboardsatacontrollers  = new Biosvfonboardsatacontroller();
	@XmlElement(name="biosVfOptionROMLoad")
	private Biosvfoptionromload biosvfoptionromloads  = new Biosvfoptionromload();
	@XmlElement(name="biosVfOptionROMEnable")
	private Biosvfoptionromenable biosvfoptionromenables  = new Biosvfoptionromenable();
	@XmlElement(name="biosVfMaxVariableMTRRSetting")
	private Biosvfmaxvariablemtrrsetting biosvfmaxvariablemtrrsettings  = new Biosvfmaxvariablemtrrsetting();
	@XmlElement(name="biosVfMaximumMemoryBelow4GB")
	private Biosvfmaximummemorybelow4gb biosvfmaximummemorybelow4gbs  = new Biosvfmaximummemorybelow4gb();
	@XmlElement(name="biosVfLvDIMMSupport")
	private Biosvflvdimmsupport biosvflvdimmsupports  = new Biosvflvdimmsupport();
	@XmlElement(name="biosVfLOMPortsConfiguration")
	private Biosvflomportsconfiguration biosvflomportsconfigurations  = new Biosvflomportsconfiguration();
	@XmlElement(name="biosVfMemoryMappedIOAbove4GB")
	private Biosvfmemorymappedioabove4gb biosvfmemorymappedioabove4gbs  = new Biosvfmemorymappedioabove4gb();
	@XmlElement(name="biosVfIntelTurboBoostTech")
	private Biosvfintelturboboosttech biosvfintelturboboostteches  = new Biosvfintelturboboosttech();
	@XmlElement(name="biosVfLocalX2Apic")
	private Biosvflocalx2apic biosvflocalx2apics  = new Biosvflocalx2apic();
	@XmlElement(name="biosVfInterleaveConfiguration")
	private Biosvfinterleaveconfiguration biosvfinterleaveconfigurations  = new Biosvfinterleaveconfiguration();
	@XmlElement(name="biosVfIntelVTForDirectedIO")
	private Biosvfintelvtfordirectedio biosvfintelvtfordirectedios  = new Biosvfintelvtfordirectedio();
	@XmlElement(name="biosVfIntelVirtualizationTechnology")
	private Biosvfintelvirtualizationtechnology biosvfintelvirtualizationtechnologies  = new Biosvfintelvirtualizationtechnology();
	@XmlElement(name="biosVfDirectCacheAccess")
	private Biosvfdirectcacheaccess biosvfdirectcacheaccesses  = new Biosvfdirectcacheaccess();
	@XmlElement(name="biosVfDRAMClockThrottling")
	private Biosvfdramclockthrottling biosvfdramclockthrottlings  = new Biosvfdramclockthrottling();
	@XmlElement(name="biosVfDramRefreshRate")
	private Biosvfdramrefreshrate biosvfdramrefreshrates  = new Biosvfdramrefreshrate();
	@XmlElement(name="biosVfEnhancedIntelSpeedStepTech")
	private Biosvfenhancedintelspeedsteptech biosvfenhancedintelspeedstepteches  = new Biosvfenhancedintelspeedsteptech();
	@XmlElement(name="biosVfUCSMBootModeControl")
	private Biosvfucsmbootmodecontrol biosvfucsmbootmodecontrols  = new Biosvfucsmbootmodecontrol();
	@XmlElement(name="biosVfSriovConfig")
	private Biosvfsriovconfig biosvfsriovconfigs  = new Biosvfsriovconfig();
	@XmlElement(name="biosVfSelectMemoryRASConfiguration")
	private Biosvfselectmemoryrasconfiguration biosvfselectmemoryrasconfigurations = new Biosvfselectmemoryrasconfiguration();
	@XmlElement(name="biosVfIntelEntrySASRAIDModule")
	private Biosvfintelentrysasraidmodule biosvfintelentrysasraidmodules  = new Biosvfintelentrysasraidmodule();
	@XmlElement(name="biosVfScrubPolicies")
	private Biosvfscrubpolicies biosvfscrubpolicieses  = new Biosvfscrubpolicies();
	@XmlElement(name="biosVfIntelHyperThreadingTech")
	private Biosvfintelhyperthreadingtech biosvfintelhyperthreadingteches  = new Biosvfintelhyperthreadingtech();
	@XmlElement(name="biosVfSparingMode")
	private Biosvfsparingmode biosvfsparingmodes  = new Biosvfsparingmode();
	@XmlElement(name="biosVfSerialPortAEnable")
	private Biosvfserialportaenable biosvfserialportaenables  = new Biosvfserialportaenable();
	@XmlElement(name="biosVfQPILinkFrequencySelect")
	private Biosvfqpilinkfrequencyselect biosvfqpilinkfrequencyselects  = new Biosvfqpilinkfrequencyselect();
	@XmlElement(name="biosVfExecuteDisableBit")
	private Biosvfexecutedisablebit biosvfexecutedisablebits  = new Biosvfexecutedisablebit();
	@XmlElement(name="biosVfPSTATECoordination")
	private Biosvfpstatecoordination biosvfpstatecoordinations  = new Biosvfpstatecoordination();
	@XmlElement(name="biosVfFRB2Timer")
	private Biosvffrb2timer biosvffrb2timers  = new Biosvffrb2timer();
	@XmlElement(name="biosVfResumeOnACPowerLoss")
	private Biosvfresumeonacpowerloss biosvfresumeonacpowerlosses  = new Biosvfresumeonacpowerloss();
	@XmlElement(name="biosVfFrequencyFloorOverride")
	private Biosvffrequencyflooroverride biosvffrequencyflooroverrides  = new Biosvffrequencyflooroverride();
	@XmlElement(name="biosVfQuietBoot")
	private Biosvfquietboot biosvfquietboots  = new Biosvfquietboot();
	@XmlElement(name="biosVfFrontPanelLockout")
	private Biosvffrontpanellockout biosvffrontpanellockouts  = new Biosvffrontpanellockout();
	@XmlElement(name="biosVfACPI10Support")
	private Biosvfacpi10support biosvfacpi10supports  = new Biosvfacpi10support();
	@XmlElement(name="biosVfProcessorPrefetchConfig")
	private Biosvfprocessorprefetchconfig biosvfprocessorprefetchconfigs  = new Biosvfprocessorprefetchconfig();
	@XmlElement(name="biosVfProcessorEnergyConfiguration")
	private Biosvfprocessorenergyconfiguration biosvfprocessorenergyconfigurations  = new Biosvfprocessorenergyconfiguration();
	@XmlElement(name="biosVfProcessorCState")
	private Biosvfprocessorcstate biosvfprocessorcstates  = new Biosvfprocessorcstate();
	@XmlElement(name="biosVfCPUPerformance")
	private Biosvfcpuperformance biosvfcpuperformances  = new Biosvfcpuperformance();
	@XmlElement(name="biosVfProcessorC7Report")
	private Biosvfprocessorc7report biosvfprocessorc7reports  = new Biosvfprocessorc7report();
	@XmlElement(name="biosVfProcessorC6Report")
	private Biosvfprocessorc6report biosvfprocessorc6reports  = new Biosvfprocessorc6report();
	@XmlElement(name="biosVfConsoleRedirection")
	private Biosvfconsoleredirection biosvfconsoleredirections  = new Biosvfconsoleredirection();
	@XmlElement(name="biosVfProcessorC3Report")
	private Biosvfprocessorc3report biosvfprocessorc3reports  = new Biosvfprocessorc3report();
	@XmlElement(name="biosVfCoreMultiProcessing")
	private Biosvfcoremultiprocessing biosvfcoremultiprocessings  = new Biosvfcoremultiprocessing();
	@XmlElement(name="biosVfProcessorC1E")
	private Biosvfprocessorc1e biosvfprocessorc1es  = new Biosvfprocessorc1e();
	@XmlElement(name="biosVfAssertNMIOnSERR")
	private Biosvfassertnmionserr biosvfassertnmionserrs  = new Biosvfassertnmionserr();
	@XmlElement(name="biosVfPOSTErrorPause")
	private Biosvfposterrorpause biosvfposterrorpauses  = new Biosvfposterrorpause();
	@XmlElement(name="biosVfBootOptionRetry")
	private Biosvfbootoptionretry biosvfbootoptionretries  = new Biosvfbootoptionretry();
	@XmlElement(name="biosVfPCISlotOptionROMEnable")
	private Biosvfpcislotoptionromenable biosvfpcislotoptionromenables  = new Biosvfpcislotoptionromenable();
	@XmlElement(name="biosVfAllUSBDevices")
	private Biosvfallusbdevices biosvfallusbdeviceses  = new Biosvfallusbdevices();
	@XmlElement(name="biosVfPCISlotLinkSpeed")
	private Biosvfpcislotlinkspeed biosvfpcislotlinkspeeds  = new Biosvfpcislotlinkspeed();
	@XmlElement(name="biosVfAssertNMIOnPERR")
	private Biosvfassertnmionperr biosvfassertnmionperrs  = new Biosvfassertnmionperr();

	public Biosvprofile() {
	}

	public Biosvprofile(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.setOrgOrg(orgorg);
	}

	public Biosvprofile(Orgorg orgorg, String childAction, String descr, String dn,
			String intId, String name, String policyLevel, String policyOwner,
			String rebootOnUpdate, int fkOrgOrg,
			Biosvfusbsystemidlepoweroptimizingsetting biosvfusbsystemidlepoweroptimizingsettings, 
			Biosvfvgapriority biosvfvgapriorities, 
			Biosvfuefiosuselegacyvideo biosvfuefiosuselegacyvideos, 
			Biosvfusbbootconfig biosvfusbbootconfigs, 
			Biosvfusbfrontpanelaccesslock biosvfusbfrontpanelaccesslocks, 
			Biosvfusbportconfiguration biosvfusbportconfigurations, 
			Biosvfosbootwatchdogtimerpolicy biosvfosbootwatchdogtimerpolicies, 
			Biosvfosbootwatchdogtimer biosvfosbootwatchdogtimers, 
			Biosvfpackagecstatelimit biosvfpackagecstatelimits, 
			Biosvfosbootwatchdogtimertimeout biosvfosbootwatchdogtimertimeouts, 
			Biosvfucsmbootorderrulecontrol biosvfucsmbootorderrulecontrols, 
			Biosvfnumaoptimized biosvfnumaoptimizeds, 
			Biosvfmirroringmode biosvfmirroringmodes, 
			Biosvfonboardstorage biosvfonboardstorages, 
			Biosvfonboardsatacontroller biosvfonboardsatacontrollers, 
			Biosvfoptionromload biosvfoptionromloads, 
			Biosvfoptionromenable biosvfoptionromenables, 
			Biosvfmaxvariablemtrrsetting biosvfmaxvariablemtrrsettings, 
			Biosvfmaximummemorybelow4gb biosvfmaximummemorybelow4gbs, 
			Biosvflvdimmsupport biosvflvdimmsupports, 
			Biosvflomportsconfiguration biosvflomportsconfigurations, 
			Biosvfmemorymappedioabove4gb biosvfmemorymappedioabove4gbs, 
			Biosvfintelturboboosttech biosvfintelturboboostteches, 
			Biosvflocalx2apic biosvflocalx2apics, 
			Biosvfinterleaveconfiguration biosvfinterleaveconfigurations, 
			Biosvfintelvtfordirectedio biosvfintelvtfordirectedios, 
			Biosvfintelvirtualizationtechnology biosvfintelvirtualizationtechnologies, 
			Biosvfdirectcacheaccess biosvfdirectcacheaccesses, 
			Biosvfdramclockthrottling biosvfdramclockthrottlings, 
			Biosvfdramrefreshrate biosvfdramrefreshrates, 
			Biosvfenhancedintelspeedsteptech biosvfenhancedintelspeedstepteches, 
			Biosvfucsmbootmodecontrol biosvfucsmbootmodecontrols, 
			Biosvfsriovconfig biosvfsriovconfigs, 
			Biosvfselectmemoryrasconfiguration biosvfselectmemoryrasconfigurations, 
			Biosvfintelentrysasraidmodule biosvfintelentrysasraidmodules, 
			Biosvfscrubpolicies biosvfscrubpolicieses, 
			Biosvfintelhyperthreadingtech biosvfintelhyperthreadingteches, 
			Biosvfsparingmode biosvfsparingmodes, 
			Biosvfserialportaenable biosvfserialportaenables, 
			Biosvfqpilinkfrequencyselect biosvfqpilinkfrequencyselects, 
			Biosvfexecutedisablebit biosvfexecutedisablebits, 
			Biosvfpstatecoordination biosvfpstatecoordinations, 
			Biosvffrb2timer biosvffrb2timers, 
			Biosvfresumeonacpowerloss biosvfresumeonacpowerlosses, 
			Biosvffrequencyflooroverride biosvffrequencyflooroverrides, 
			Biosvfquietboot biosvfquietboots, 
			Biosvffrontpanellockout biosvffrontpanellockouts, 
			Biosvfacpi10support biosvfacpi10supports, 
			Biosvfprocessorprefetchconfig biosvfprocessorprefetchconfigs, 
			Biosvfprocessorenergyconfiguration biosvfprocessorenergyconfigurations, 
			Biosvfprocessorcstate biosvfprocessorcstates, 
			Biosvfcpuperformance biosvfcpuperformances, 
			Biosvfprocessorc7report biosvfprocessorc7reports, 
			Biosvfprocessorc6report biosvfprocessorc6reports, 
			Biosvfconsoleredirection biosvfconsoleredirections, 
			Biosvfprocessorc3report biosvfprocessorc3reports, 
			Biosvfcoremultiprocessing biosvfcoremultiprocessings, 
			Biosvfprocessorc1e biosvfprocessorc1es, 
			Biosvfassertnmionserr biosvfassertnmionserrs, 
			Biosvfposterrorpause biosvfposterrorpauses, 
			Biosvfbootoptionretry biosvfbootoptionretries, 
			Biosvfpcislotoptionromenable biosvfpcislotoptionromenables, 
			Biosvfallusbdevices biosvfallusbdeviceses, 
			Biosvfpcislotlinkspeed biosvfpcislotlinkspeeds, 
			Biosvfassertnmionperr biosvfassertnmionperrs ) {
		this.orgOrg = orgorg;
		this.childAction = childAction;
		this.descr = descr;
		this.dn = dn;
		this.intId = intId;
		this.name = name;
		this.policyLevel = policyLevel;
		this.policyOwner = policyOwner;
		this.rebootOnUpdate = rebootOnUpdate;
		this.biosvfusbsystemidlepoweroptimizingsettings = biosvfusbsystemidlepoweroptimizingsettings;
		this.biosvfvgapriorities = biosvfvgapriorities;
		this.biosvfuefiosuselegacyvideos = biosvfuefiosuselegacyvideos;
		this.biosvfusbbootconfigs = biosvfusbbootconfigs;
		this.biosvfusbfrontpanelaccesslocks = biosvfusbfrontpanelaccesslocks;
		this.biosvfusbportconfigurations = biosvfusbportconfigurations;
		this.biosvfosbootwatchdogtimerpolicies = biosvfosbootwatchdogtimerpolicies;
		this.biosvfosbootwatchdogtimers = biosvfosbootwatchdogtimers;
		this.biosvfpackagecstatelimits = biosvfpackagecstatelimits;
		this.biosvfosbootwatchdogtimertimeouts = biosvfosbootwatchdogtimertimeouts;
		this.biosvfucsmbootorderrulecontrols = biosvfucsmbootorderrulecontrols;
		this.biosvfnumaoptimizeds = biosvfnumaoptimizeds;
		this.biosvfmirroringmodes = biosvfmirroringmodes;
		this.biosvfonboardstorages = biosvfonboardstorages;
		this.biosvfonboardsatacontrollers = biosvfonboardsatacontrollers;
		this.biosvfoptionromloads = biosvfoptionromloads;
		this.biosvfoptionromenables = biosvfoptionromenables;
		this.biosvfmaxvariablemtrrsettings = biosvfmaxvariablemtrrsettings;
		this.biosvfmaximummemorybelow4gbs = biosvfmaximummemorybelow4gbs;
		this.biosvflvdimmsupports = biosvflvdimmsupports;
		this.biosvflomportsconfigurations = biosvflomportsconfigurations;
		this.biosvfmemorymappedioabove4gbs = biosvfmemorymappedioabove4gbs;
		this.biosvfintelturboboostteches = biosvfintelturboboostteches;
		this.biosvflocalx2apics = biosvflocalx2apics;
		this.biosvfinterleaveconfigurations = biosvfinterleaveconfigurations;
		this.biosvfintelvtfordirectedios = biosvfintelvtfordirectedios;
		this.biosvfintelvirtualizationtechnologies = biosvfintelvirtualizationtechnologies;
		this.biosvfdirectcacheaccesses = biosvfdirectcacheaccesses;
		this.biosvfdramclockthrottlings = biosvfdramclockthrottlings;
		this.biosvfdramrefreshrates = biosvfdramrefreshrates;
		this.biosvfenhancedintelspeedstepteches = biosvfenhancedintelspeedstepteches;
		this.biosvfucsmbootmodecontrols = biosvfucsmbootmodecontrols;
		this.biosvfsriovconfigs = biosvfsriovconfigs;
		this.biosvfselectmemoryrasconfigurations = biosvfselectmemoryrasconfigurations;
		this.biosvfintelentrysasraidmodules = biosvfintelentrysasraidmodules;
		this.biosvfscrubpolicieses = biosvfscrubpolicieses;
		this.biosvfintelhyperthreadingteches = biosvfintelhyperthreadingteches;
		this.biosvfsparingmodes = biosvfsparingmodes;
		this.biosvfserialportaenables = biosvfserialportaenables;
		this.biosvfqpilinkfrequencyselects = biosvfqpilinkfrequencyselects;
		this.biosvfexecutedisablebits = biosvfexecutedisablebits;
		this.biosvfpstatecoordinations = biosvfpstatecoordinations;
		this.biosvffrb2timers = biosvffrb2timers;
		this.biosvfresumeonacpowerlosses = biosvfresumeonacpowerlosses;
		this.biosvffrequencyflooroverrides = biosvffrequencyflooroverrides;
		this.biosvfquietboots = biosvfquietboots;
		this.biosvffrontpanellockouts = biosvffrontpanellockouts;
		this.biosvfacpi10supports = biosvfacpi10supports;
		this.biosvfprocessorprefetchconfigs = biosvfprocessorprefetchconfigs;
		this.biosvfprocessorenergyconfigurations = biosvfprocessorenergyconfigurations;
		this.biosvfprocessorcstates = biosvfprocessorcstates;
		this.biosvfcpuperformances = biosvfcpuperformances;
		this.biosvfprocessorc7reports = biosvfprocessorc7reports;
		this.biosvfprocessorc6reports = biosvfprocessorc6reports;
		this.biosvfconsoleredirections = biosvfconsoleredirections;
		this.biosvfprocessorc3reports = biosvfprocessorc3reports;
		this.biosvfcoremultiprocessings = biosvfcoremultiprocessings;
		this.biosvfprocessorc1es = biosvfprocessorc1es;
		this.biosvfassertnmionserrs = biosvfassertnmionserrs;
		this.biosvfposterrorpauses = biosvfposterrorpauses;
		this.biosvfbootoptionretries = biosvfbootoptionretries;
		this.biosvfpcislotoptionromenables = biosvfpcislotoptionromenables;
		this.biosvfallusbdeviceses = biosvfallusbdeviceses;
		this.biosvfpcislotlinkspeeds = biosvfpcislotlinkspeeds;
		this.biosvfassertnmionperrs = biosvfassertnmionperrs;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgOrg() {
		return orgOrg;
	}

	public void setOrgOrg(Orgorg orgOrg) {
		this.orgOrg = orgOrg;
	}

	public String getChildAction() {
		return this.childAction;
	}

	public void setChildAction(String childAction) {
		this.childAction = childAction;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDn() {
		return this.dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getIntId() {
		return this.intId;
	}

	public void setIntId(String intId) {
		this.intId = intId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPolicyLevel() {
		return this.policyLevel;
	}

	public void setPolicyLevel(String policyLevel) {
		this.policyLevel = policyLevel;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getRebootOnUpdate() {
		return this.rebootOnUpdate;
	}

	public void setRebootOnUpdate(String rebootOnUpdate) {
		this.rebootOnUpdate = rebootOnUpdate;
	}

	public Biosvfusbsystemidlepoweroptimizingsetting getBiosvfusbsystemidlepoweroptimizingsettings() {
		return biosvfusbsystemidlepoweroptimizingsettings;
	}

	public void setBiosvfusbsystemidlepoweroptimizingsettings(
			Biosvfusbsystemidlepoweroptimizingsetting biosvfusbsystemidlepoweroptimizingsettings) {
		this.biosvfusbsystemidlepoweroptimizingsettings = biosvfusbsystemidlepoweroptimizingsettings;
	}

	public Biosvfvgapriority getBiosvfvgapriorities() {
		return biosvfvgapriorities;
	}

	public void setBiosvfvgapriorities(Biosvfvgapriority biosvfvgapriorities) {
		this.biosvfvgapriorities = biosvfvgapriorities;
	}

	public Biosvfuefiosuselegacyvideo getBiosvfuefiosuselegacyvideos() {
		return biosvfuefiosuselegacyvideos;
	}

	public void setBiosvfuefiosuselegacyvideos(
			Biosvfuefiosuselegacyvideo biosvfuefiosuselegacyvideos) {
		this.biosvfuefiosuselegacyvideos = biosvfuefiosuselegacyvideos;
	}

	public Biosvfusbbootconfig getBiosvfusbbootconfigs() {
		return biosvfusbbootconfigs;
	}

	public void setBiosvfusbbootconfigs(Biosvfusbbootconfig biosvfusbbootconfigs) {
		this.biosvfusbbootconfigs = biosvfusbbootconfigs;
	}

	public Biosvfusbfrontpanelaccesslock getBiosvfusbfrontpanelaccesslocks() {
		return biosvfusbfrontpanelaccesslocks;
	}

	public void setBiosvfusbfrontpanelaccesslocks(
			Biosvfusbfrontpanelaccesslock biosvfusbfrontpanelaccesslocks) {
		this.biosvfusbfrontpanelaccesslocks = biosvfusbfrontpanelaccesslocks;
	}

	public Biosvfusbportconfiguration getBiosvfusbportconfigurations() {
		return biosvfusbportconfigurations;
	}

	public void setBiosvfusbportconfigurations(
			Biosvfusbportconfiguration biosvfusbportconfigurations) {
		this.biosvfusbportconfigurations = biosvfusbportconfigurations;
	}

	public Biosvfosbootwatchdogtimerpolicy getBiosvfosbootwatchdogtimerpolicies() {
		return biosvfosbootwatchdogtimerpolicies;
	}

	public void setBiosvfosbootwatchdogtimerpolicies(
			Biosvfosbootwatchdogtimerpolicy biosvfosbootwatchdogtimerpolicies) {
		this.biosvfosbootwatchdogtimerpolicies = biosvfosbootwatchdogtimerpolicies;
	}

	public Biosvfosbootwatchdogtimer getBiosvfosbootwatchdogtimers() {
		return biosvfosbootwatchdogtimers;
	}

	public void setBiosvfosbootwatchdogtimers(
			Biosvfosbootwatchdogtimer biosvfosbootwatchdogtimers) {
		this.biosvfosbootwatchdogtimers = biosvfosbootwatchdogtimers;
	}

	public Biosvfpackagecstatelimit getBiosvfpackagecstatelimits() {
		return biosvfpackagecstatelimits;
	}

	public void setBiosvfpackagecstatelimits(
			Biosvfpackagecstatelimit biosvfpackagecstatelimits) {
		this.biosvfpackagecstatelimits = biosvfpackagecstatelimits;
	}

	public Biosvfosbootwatchdogtimertimeout getBiosvfosbootwatchdogtimertimeouts() {
		return biosvfosbootwatchdogtimertimeouts;
	}

	public void setBiosvfosbootwatchdogtimertimeouts(
			Biosvfosbootwatchdogtimertimeout biosvfosbootwatchdogtimertimeouts) {
		this.biosvfosbootwatchdogtimertimeouts = biosvfosbootwatchdogtimertimeouts;
	}

	public Biosvfucsmbootorderrulecontrol getBiosvfucsmbootorderrulecontrols() {
		return biosvfucsmbootorderrulecontrols;
	}

	public void setBiosvfucsmbootorderrulecontrols(
			Biosvfucsmbootorderrulecontrol biosvfucsmbootorderrulecontrols) {
		this.biosvfucsmbootorderrulecontrols = biosvfucsmbootorderrulecontrols;
	}

	public Biosvfnumaoptimized getBiosvfnumaoptimizeds() {
		return biosvfnumaoptimizeds;
	}

	public void setBiosvfnumaoptimizeds(Biosvfnumaoptimized biosvfnumaoptimizeds) {
		this.biosvfnumaoptimizeds = biosvfnumaoptimizeds;
	}

	public Biosvfmirroringmode getBiosvfmirroringmodes() {
		return biosvfmirroringmodes;
	}

	public void setBiosvfmirroringmodes(Biosvfmirroringmode biosvfmirroringmodes) {
		this.biosvfmirroringmodes = biosvfmirroringmodes;
	}

	public Biosvfonboardstorage getBiosvfonboardstorages() {
		return biosvfonboardstorages;
	}

	public void setBiosvfonboardstorages(Biosvfonboardstorage biosvfonboardstorages) {
		this.biosvfonboardstorages = biosvfonboardstorages;
	}

	public Biosvfonboardsatacontroller getBiosvfonboardsatacontrollers() {
		return biosvfonboardsatacontrollers;
	}

	public void setBiosvfonboardsatacontrollers(
			Biosvfonboardsatacontroller biosvfonboardsatacontrollers) {
		this.biosvfonboardsatacontrollers = biosvfonboardsatacontrollers;
	}

	public Biosvfoptionromload getBiosvfoptionromloads() {
		return biosvfoptionromloads;
	}

	public void setBiosvfoptionromloads(Biosvfoptionromload biosvfoptionromloads) {
		this.biosvfoptionromloads = biosvfoptionromloads;
	}

	public Biosvfoptionromenable getBiosvfoptionromenables() {
		return biosvfoptionromenables;
	}

	public void setBiosvfoptionromenables(
			Biosvfoptionromenable biosvfoptionromenables) {
		this.biosvfoptionromenables = biosvfoptionromenables;
	}

	public Biosvfmaxvariablemtrrsetting getBiosvfmaxvariablemtrrsettings() {
		return biosvfmaxvariablemtrrsettings;
	}

	public void setBiosvfmaxvariablemtrrsettings(
			Biosvfmaxvariablemtrrsetting biosvfmaxvariablemtrrsettings) {
		this.biosvfmaxvariablemtrrsettings = biosvfmaxvariablemtrrsettings;
	}

	public Biosvfmaximummemorybelow4gb getBiosvfmaximummemorybelow4gbs() {
		return biosvfmaximummemorybelow4gbs;
	}

	public void setBiosvfmaximummemorybelow4gbs(
			Biosvfmaximummemorybelow4gb biosvfmaximummemorybelow4gbs) {
		this.biosvfmaximummemorybelow4gbs = biosvfmaximummemorybelow4gbs;
	}

	public Biosvflvdimmsupport getBiosvflvdimmsupports() {
		return biosvflvdimmsupports;
	}

	public void setBiosvflvdimmsupports(Biosvflvdimmsupport biosvflvdimmsupports) {
		this.biosvflvdimmsupports = biosvflvdimmsupports;
	}

	public Biosvflomportsconfiguration getBiosvflomportsconfigurations() {
		return biosvflomportsconfigurations;
	}

	public void setBiosvflomportsconfigurations(
			Biosvflomportsconfiguration biosvflomportsconfigurations) {
		this.biosvflomportsconfigurations = biosvflomportsconfigurations;
	}

	public Biosvfmemorymappedioabove4gb getBiosvfmemorymappedioabove4gbs() {
		return biosvfmemorymappedioabove4gbs;
	}

	public void setBiosvfmemorymappedioabove4gbs(
			Biosvfmemorymappedioabove4gb biosvfmemorymappedioabove4gbs) {
		this.biosvfmemorymappedioabove4gbs = biosvfmemorymappedioabove4gbs;
	}

	public Biosvfintelturboboosttech getBiosvfintelturboboostteches() {
		return biosvfintelturboboostteches;
	}

	public void setBiosvfintelturboboostteches(
			Biosvfintelturboboosttech biosvfintelturboboostteches) {
		this.biosvfintelturboboostteches = biosvfintelturboboostteches;
	}

	public Biosvflocalx2apic getBiosvflocalx2apics() {
		return biosvflocalx2apics;
	}

	public void setBiosvflocalx2apics(Biosvflocalx2apic biosvflocalx2apics) {
		this.biosvflocalx2apics = biosvflocalx2apics;
	}

	public Biosvfinterleaveconfiguration getBiosvfinterleaveconfigurations() {
		return biosvfinterleaveconfigurations;
	}

	public void setBiosvfinterleaveconfigurations(
			Biosvfinterleaveconfiguration biosvfinterleaveconfigurations) {
		this.biosvfinterleaveconfigurations = biosvfinterleaveconfigurations;
	}

	public Biosvfintelvtfordirectedio getBiosvfintelvtfordirectedios() {
		return biosvfintelvtfordirectedios;
	}

	public void setBiosvfintelvtfordirectedios(
			Biosvfintelvtfordirectedio biosvfintelvtfordirectedios) {
		this.biosvfintelvtfordirectedios = biosvfintelvtfordirectedios;
	}

	public Biosvfintelvirtualizationtechnology getBiosvfintelvirtualizationtechnologies() {
		return biosvfintelvirtualizationtechnologies;
	}

	public void setBiosvfintelvirtualizationtechnologies(
			Biosvfintelvirtualizationtechnology biosvfintelvirtualizationtechnologies) {
		this.biosvfintelvirtualizationtechnologies = biosvfintelvirtualizationtechnologies;
	}

	public Biosvfdirectcacheaccess getBiosvfdirectcacheaccesses() {
		return biosvfdirectcacheaccesses;
	}

	public void setBiosvfdirectcacheaccesses(
			Biosvfdirectcacheaccess biosvfdirectcacheaccesses) {
		this.biosvfdirectcacheaccesses = biosvfdirectcacheaccesses;
	}

	public Biosvfdramclockthrottling getBiosvfdramclockthrottlings() {
		return biosvfdramclockthrottlings;
	}

	public void setBiosvfdramclockthrottlings(
			Biosvfdramclockthrottling biosvfdramclockthrottlings) {
		this.biosvfdramclockthrottlings = biosvfdramclockthrottlings;
	}

	public Biosvfdramrefreshrate getBiosvfdramrefreshrates() {
		return biosvfdramrefreshrates;
	}

	public void setBiosvfdramrefreshrates(
			Biosvfdramrefreshrate biosvfdramrefreshrates) {
		this.biosvfdramrefreshrates = biosvfdramrefreshrates;
	}

	public Biosvfenhancedintelspeedsteptech getBiosvfenhancedintelspeedstepteches() {
		return biosvfenhancedintelspeedstepteches;
	}

	public void setBiosvfenhancedintelspeedstepteches(
			Biosvfenhancedintelspeedsteptech biosvfenhancedintelspeedstepteches) {
		this.biosvfenhancedintelspeedstepteches = biosvfenhancedintelspeedstepteches;
	}

	public Biosvfucsmbootmodecontrol getBiosvfucsmbootmodecontrols() {
		return biosvfucsmbootmodecontrols;
	}

	public void setBiosvfucsmbootmodecontrols(
			Biosvfucsmbootmodecontrol biosvfucsmbootmodecontrols) {
		this.biosvfucsmbootmodecontrols = biosvfucsmbootmodecontrols;
	}

	public Biosvfsriovconfig getBiosvfsriovconfigs() {
		return biosvfsriovconfigs;
	}

	public void setBiosvfsriovconfigs(Biosvfsriovconfig biosvfsriovconfigs) {
		this.biosvfsriovconfigs = biosvfsriovconfigs;
	}

	public Biosvfselectmemoryrasconfiguration getBiosvfselectmemoryrasconfigurations() {
		return biosvfselectmemoryrasconfigurations;
	}

	public void setBiosvfselectmemoryrasconfigurations(
			Biosvfselectmemoryrasconfiguration biosvfselectmemoryrasconfigurations) {
		this.biosvfselectmemoryrasconfigurations = biosvfselectmemoryrasconfigurations;
	}

	public Biosvfintelentrysasraidmodule getBiosvfintelentrysasraidmodules() {
		return biosvfintelentrysasraidmodules;
	}

	public void setBiosvfintelentrysasraidmodules(
			Biosvfintelentrysasraidmodule biosvfintelentrysasraidmodules) {
		this.biosvfintelentrysasraidmodules = biosvfintelentrysasraidmodules;
	}

	public Biosvfscrubpolicies getBiosvfscrubpolicieses() {
		return biosvfscrubpolicieses;
	}

	public void setBiosvfscrubpolicieses(Biosvfscrubpolicies biosvfscrubpolicieses) {
		this.biosvfscrubpolicieses = biosvfscrubpolicieses;
	}

	public Biosvfintelhyperthreadingtech getBiosvfintelhyperthreadingteches() {
		return biosvfintelhyperthreadingteches;
	}

	public void setBiosvfintelhyperthreadingteches(
			Biosvfintelhyperthreadingtech biosvfintelhyperthreadingteches) {
		this.biosvfintelhyperthreadingteches = biosvfintelhyperthreadingteches;
	}

	public Biosvfsparingmode getBiosvfsparingmodes() {
		return biosvfsparingmodes;
	}

	public void setBiosvfsparingmodes(Biosvfsparingmode biosvfsparingmodes) {
		this.biosvfsparingmodes = biosvfsparingmodes;
	}

	public Biosvfserialportaenable getBiosvfserialportaenables() {
		return biosvfserialportaenables;
	}

	public void setBiosvfserialportaenables(
			Biosvfserialportaenable biosvfserialportaenables) {
		this.biosvfserialportaenables = biosvfserialportaenables;
	}

	public Biosvfqpilinkfrequencyselect getBiosvfqpilinkfrequencyselects() {
		return biosvfqpilinkfrequencyselects;
	}

	public void setBiosvfqpilinkfrequencyselects(
			Biosvfqpilinkfrequencyselect biosvfqpilinkfrequencyselects) {
		this.biosvfqpilinkfrequencyselects = biosvfqpilinkfrequencyselects;
	}

	public Biosvfexecutedisablebit getBiosvfexecutedisablebits() {
		return biosvfexecutedisablebits;
	}

	public void setBiosvfexecutedisablebits(
			Biosvfexecutedisablebit biosvfexecutedisablebits) {
		this.biosvfexecutedisablebits = biosvfexecutedisablebits;
	}

	public Biosvfpstatecoordination getBiosvfpstatecoordinations() {
		return biosvfpstatecoordinations;
	}

	public void setBiosvfpstatecoordinations(
			Biosvfpstatecoordination biosvfpstatecoordinations) {
		this.biosvfpstatecoordinations = biosvfpstatecoordinations;
	}

	public Biosvffrb2timer getBiosvffrb2timers() {
		return biosvffrb2timers;
	}

	public void setBiosvffrb2timers(Biosvffrb2timer biosvffrb2timers) {
		this.biosvffrb2timers = biosvffrb2timers;
	}

	public Biosvfresumeonacpowerloss getBiosvfresumeonacpowerlosses() {
		return biosvfresumeonacpowerlosses;
	}

	public void setBiosvfresumeonacpowerlosses(
			Biosvfresumeonacpowerloss biosvfresumeonacpowerlosses) {
		this.biosvfresumeonacpowerlosses = biosvfresumeonacpowerlosses;
	}

	public Biosvffrequencyflooroverride getBiosvffrequencyflooroverrides() {
		return biosvffrequencyflooroverrides;
	}

	public void setBiosvffrequencyflooroverrides(
			Biosvffrequencyflooroverride biosvffrequencyflooroverrides) {
		this.biosvffrequencyflooroverrides = biosvffrequencyflooroverrides;
	}

	public Biosvfquietboot getBiosvfquietboots() {
		return biosvfquietboots;
	}

	public void setBiosvfquietboots(Biosvfquietboot biosvfquietboots) {
		this.biosvfquietboots = biosvfquietboots;
	}

	public Biosvffrontpanellockout getBiosvffrontpanellockouts() {
		return biosvffrontpanellockouts;
	}

	public void setBiosvffrontpanellockouts(
			Biosvffrontpanellockout biosvffrontpanellockouts) {
		this.biosvffrontpanellockouts = biosvffrontpanellockouts;
	}

	public Biosvfacpi10support getBiosvfacpi10supports() {
		return biosvfacpi10supports;
	}

	public void setBiosvfacpi10supports(Biosvfacpi10support biosvfacpi10supports) {
		this.biosvfacpi10supports = biosvfacpi10supports;
	}

	public Biosvfprocessorprefetchconfig getBiosvfprocessorprefetchconfigs() {
		return biosvfprocessorprefetchconfigs;
	}

	public void setBiosvfprocessorprefetchconfigs(
			Biosvfprocessorprefetchconfig biosvfprocessorprefetchconfigs) {
		this.biosvfprocessorprefetchconfigs = biosvfprocessorprefetchconfigs;
	}

	public Biosvfprocessorenergyconfiguration getBiosvfprocessorenergyconfigurations() {
		return biosvfprocessorenergyconfigurations;
	}

	public void setBiosvfprocessorenergyconfigurations(
			Biosvfprocessorenergyconfiguration biosvfprocessorenergyconfigurations) {
		this.biosvfprocessorenergyconfigurations = biosvfprocessorenergyconfigurations;
	}

	public Biosvfprocessorcstate getBiosvfprocessorcstates() {
		return biosvfprocessorcstates;
	}

	public void setBiosvfprocessorcstates(
			Biosvfprocessorcstate biosvfprocessorcstates) {
		this.biosvfprocessorcstates = biosvfprocessorcstates;
	}

	public Biosvfcpuperformance getBiosvfcpuperformances() {
		return biosvfcpuperformances;
	}

	public void setBiosvfcpuperformances(Biosvfcpuperformance biosvfcpuperformances) {
		this.biosvfcpuperformances = biosvfcpuperformances;
	}

	public Biosvfprocessorc7report getBiosvfprocessorc7reports() {
		return biosvfprocessorc7reports;
	}

	public void setBiosvfprocessorc7reports(
			Biosvfprocessorc7report biosvfprocessorc7reports) {
		this.biosvfprocessorc7reports = biosvfprocessorc7reports;
	}

	public Biosvfprocessorc6report getBiosvfprocessorc6reports() {
		return biosvfprocessorc6reports;
	}

	public void setBiosvfprocessorc6reports(
			Biosvfprocessorc6report biosvfprocessorc6reports) {
		this.biosvfprocessorc6reports = biosvfprocessorc6reports;
	}

	public Biosvfconsoleredirection getBiosvfconsoleredirections() {
		return biosvfconsoleredirections;
	}

	public void setBiosvfconsoleredirections(
			Biosvfconsoleredirection biosvfconsoleredirections) {
		this.biosvfconsoleredirections = biosvfconsoleredirections;
	}

	public Biosvfprocessorc3report getBiosvfprocessorc3reports() {
		return biosvfprocessorc3reports;
	}

	public void setBiosvfprocessorc3reports(
			Biosvfprocessorc3report biosvfprocessorc3reports) {
		this.biosvfprocessorc3reports = biosvfprocessorc3reports;
	}

	public Biosvfcoremultiprocessing getBiosvfcoremultiprocessings() {
		return biosvfcoremultiprocessings;
	}

	public void setBiosvfcoremultiprocessings(
			Biosvfcoremultiprocessing biosvfcoremultiprocessings) {
		this.biosvfcoremultiprocessings = biosvfcoremultiprocessings;
	}

	public Biosvfprocessorc1e getBiosvfprocessorc1es() {
		return biosvfprocessorc1es;
	}

	public void setBiosvfprocessorc1es(Biosvfprocessorc1e biosvfprocessorc1es) {
		this.biosvfprocessorc1es = biosvfprocessorc1es;
	}

	public Biosvfassertnmionserr getBiosvfassertnmionserrs() {
		return biosvfassertnmionserrs;
	}

	public void setBiosvfassertnmionserrs(
			Biosvfassertnmionserr biosvfassertnmionserrs) {
		this.biosvfassertnmionserrs = biosvfassertnmionserrs;
	}

	public Biosvfposterrorpause getBiosvfposterrorpauses() {
		return biosvfposterrorpauses;
	}

	public void setBiosvfposterrorpauses(Biosvfposterrorpause biosvfposterrorpauses) {
		this.biosvfposterrorpauses = biosvfposterrorpauses;
	}

	public Biosvfbootoptionretry getBiosvfbootoptionretries() {
		return biosvfbootoptionretries;
	}

	public void setBiosvfbootoptionretries(
			Biosvfbootoptionretry biosvfbootoptionretries) {
		this.biosvfbootoptionretries = biosvfbootoptionretries;
	}

	public Biosvfpcislotoptionromenable getBiosvfpcislotoptionromenables() {
		return biosvfpcislotoptionromenables;
	}

	public void setBiosvfpcislotoptionromenables(
			Biosvfpcislotoptionromenable biosvfpcislotoptionromenables) {
		this.biosvfpcislotoptionromenables = biosvfpcislotoptionromenables;
	}

	public Biosvfallusbdevices getBiosvfallusbdeviceses() {
		return biosvfallusbdeviceses;
	}

	public void setBiosvfallusbdeviceses(Biosvfallusbdevices biosvfallusbdeviceses) {
		this.biosvfallusbdeviceses = biosvfallusbdeviceses;
	}

	public Biosvfpcislotlinkspeed getBiosvfpcislotlinkspeeds() {
		return biosvfpcislotlinkspeeds;
	}

	public void setBiosvfpcislotlinkspeeds(
			Biosvfpcislotlinkspeed biosvfpcislotlinkspeeds) {
		this.biosvfpcislotlinkspeeds = biosvfpcislotlinkspeeds;
	}

	public Biosvfassertnmionperr getBiosvfassertnmionperrs() {
		return biosvfassertnmionperrs;
	}

	public void setBiosvfassertnmionperrs(
			Biosvfassertnmionperr biosvfassertnmionperrs) {
		this.biosvfassertnmionperrs = biosvfassertnmionperrs;
	}

}
