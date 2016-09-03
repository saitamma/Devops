package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfintelturboboosttech;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfinterleaveconfiguration;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvflocalx2apic;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvflomportsconfiguration;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvflvdimmsupport;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfmaximummemorybelow4gb;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfmaxvariablemtrrsetting;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfmemorymappedioabove4gb;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfmirroringmode;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfnumaoptimized;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfonboardsatacontroller;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfonboardstorage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfoptionromenable;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfoptionromload;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfosbootwatchdogtimer;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfosbootwatchdogtimerpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfosbootwatchdogtimertimeout;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfpackagecstatelimit;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfucsmbootorderrulecontrol;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfuefiosuselegacyvideo;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfusbbootconfig;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfusbfrontpanelaccesslock;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfusbportconfiguration;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfusbsystemidlepoweroptimizingsetting;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfvgapriority;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfacpi10support;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfallusbdevices;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfassertnmionperr;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfassertnmionserr;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfbootoptionretry;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfconsoleredirection;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfcoremultiprocessing;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfcpuperformance;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfdirectcacheaccess;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfdramclockthrottling;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfdramrefreshrate;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfenhancedintelspeedsteptech;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfexecutedisablebit;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvffrb2timer;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvffrequencyflooroverride;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvffrontpanellockout;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfintelentrysasraidmodule;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfintelhyperthreadingtech;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfintelvirtualizationtechnology;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfintelvtfordirectedio;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfpcislotlinkspeed;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfpcislotoptionromenable;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfposterrorpause;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorc1e;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorc3report;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorc6report;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorc7report;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorcstate;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorenergyconfiguration;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfprocessorprefetchconfig;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfpstatecoordination;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfqpilinkfrequencyselect;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfquietboot;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfresumeonacpowerloss;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfscrubpolicies;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfselectmemoryrasconfiguration;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfserialportaenable;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfsparingmode;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfsriovconfig;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvfucsmbootmodecontrol;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvprofile;

@Service("unmarshallBiosPolicyChilds")
public class UnmarshallBiosPolicyChilds extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnmarshallBiosPolicyChilds.class);
	
	@Autowired
	public UnmarshallBiosPolicyChilds(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	public void unmarshallBiosPolicyChilds(Biosvprofile biosvprofile) {

		try {
			unmarshallBiosVfUSBSystemIdlePowerOptimizingSetting(biosvprofile);
			unmarshallBiosvfvgapriority(biosvprofile);
			unmarshallBiosvfuefiosuselegacyvideo(biosvprofile);
			unmarshallBiosvfusbbootconfig(biosvprofile);
			unmarshallBiosvfusbFrontpanelAccesslock(biosvprofile);
			unmarshallBiosvfusbportConfiguration(biosvprofile);
			unmarshallBiosvfosBootwatchDogtimerPolicy(biosvprofile);
			unmarshallBiosvfosBootwatchDogtimer(biosvprofile);
			unmarshallBiosvfPackagecStateLimit(biosvprofile);
			unmarshallBiosvfosBootWatchdogtimerTimeout(biosvprofile);
			unmarshallBiosvfucsmBootorderRuleControl(biosvprofile);
			unmarshallBiosvfnumaOptimized(biosvprofile);
			unmarshallBiosvfMirroringMode(biosvprofile);
			unmarshallBiosvfonboardStorage(biosvprofile);
			unmarshallBiosvfOnboardsataController(biosvprofile);
			unmarshallBiosvfOptionromLoad(biosvprofile);
			unmarshallBiosvfOptionromEnable(biosvprofile);
			unmarshallBiosvfMaxVariablemtrrSetting(biosvprofile);
			unmarshallBiosvfMaximumMemorybelow4gb(biosvprofile);
			unmarshallBiosvflvdimmSupport(biosvprofile);
			unmarshallBiosvflomportsConfiguration(biosvprofile);
			unmarshallBiosvfMemoryMappedioabove4gb(biosvprofile);
			unmarshallBiosvfIntelTurboBoostteche(biosvprofile);
			unmarshallBiosvfLocalx2Apic(biosvprofile);
			unmarshallBiosvfinterleaveConfiguration(biosvprofile);
			unmarshallBiosvfintelvtfordirectedio(biosvprofile);
			unmarshallBiosvfintelvirtualizationtechnology(biosvprofile);
			unmarshallBiosvfdirectcacheaccess(biosvprofile);
			unmarshallBiosvfdramclockthrottling(biosvprofile);
			unmarshallBiosvfdramrefreshrate(biosvprofile);
			unmarshallBiosvfenhancedintelspeedsteptech(biosvprofile);
			unmarshallBiosvfucsmbootmodecontrol(biosvprofile);
			unmarshallBiosvfsriovconfig(biosvprofile);
			unmarshallBiosvfselectmemoryrasconfiguration(biosvprofile);
			unmarshallBiosvfintelentrysasraidmodule(biosvprofile);
			unmarshallBiosvfscrubpolicies(biosvprofile);
			unmarshallBiosvfintelhyperthreadingtech(biosvprofile);
			unmarshallBiosvfsparingmode(biosvprofile);
			unmarshallBiosvfserialportaenable(biosvprofile);
			unmarshallBiosvfqpilinkfrequencyselect(biosvprofile);
			unmarshallBiosvfexecutedisablebit(biosvprofile);
			unmarshallBiosvfpstatecoordination(biosvprofile);
			unmarshallBiosvffrb2timer(biosvprofile);
			unmarshallBiosvfresumeonacpowerloss(biosvprofile);
			unmarshallBiosvffrequencyflooroverride(biosvprofile);
			unmarshallBiosvfquietboot(biosvprofile);
			unmarshallBiosvffrontpanellockout(biosvprofile);
			unmarshallBiosvfacpi10support(biosvprofile);
			unmarshallBiosvfprocessorprefetchconfig(biosvprofile);
			unmarshallBiosvfprocessorenergyconfiguration(biosvprofile);
			unmarshallBiosvfprocessorcstate(biosvprofile);
			unmarshallBiosvfcpuperformance(biosvprofile);
			unmarshallBiosvfprocessorc7report(biosvprofile);
			unmarshallBiosvfprocessorc6report(biosvprofile);
			unmarshallBiosvfconsoleredirection(biosvprofile);
			unmarshallBiosvfprocessorc3report(biosvprofile);
			unmarshallBiosvfcoremultiprocessing(biosvprofile);
			unmarshallBiosvfprocessorc1e(biosvprofile);
			unmarshallBiosvfassertnmionserr(biosvprofile);
			unmarshallBiosvfposterrorpause(biosvprofile);
			unmarshallBiosvfbootoptionretry(biosvprofile);
			unmarshallBiosvfpcislotoptionromenable(biosvprofile);
			unmarshallBiosvfallusbdevice(biosvprofile);
			unmarshallBiosvfpcislotlinkspeed(biosvprofile);
			unmarshallBiosvfassertnmionperr(biosvprofile);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

	}

	private void unmarshallBiosvfinterleaveConfiguration(
			Biosvprofile biosvprofile) {
		Biosvfinterleaveconfiguration biosvfinterleaveconfiguration = biosvprofile
				.getBiosvfinterleaveconfigurations();
		biosvfinterleaveconfiguration.setBiosvprofile(biosvprofile);
		addNew(biosvfinterleaveconfiguration);
	}

	private void unmarshallBiosvfLocalx2Apic(Biosvprofile biosvprofile) {
		Biosvflocalx2apic biosvflocalx2apic = biosvprofile
				.getBiosvflocalx2apics();
		biosvflocalx2apic.setBiosvprofile(biosvprofile);
		addNew(biosvflocalx2apic);
	}

	private void unmarshallBiosvfIntelTurboBoostteche(Biosvprofile biosvprofile) {
		Biosvfintelturboboosttech biosvfintelturboboosttech = biosvprofile
				.getBiosvfintelturboboostteches();
		biosvfintelturboboosttech.setBiosvprofile(biosvprofile);
		addNew(biosvfintelturboboosttech);
	}

	private void unmarshallBiosvfMemoryMappedioabove4gb(
			Biosvprofile biosvprofile) {
		Biosvfmemorymappedioabove4gb biosvfmemorymappedioabove4gb = biosvprofile
				.getBiosvfmemorymappedioabove4gbs();
		biosvfmemorymappedioabove4gb.setBiosvprofile(biosvprofile);
		addNew(biosvfmemorymappedioabove4gb);
	}

	private void unmarshallBiosvflomportsConfiguration(Biosvprofile biosvprofile) {
		Biosvflomportsconfiguration biosvflomportsconfiguration = biosvprofile
				.getBiosvflomportsconfigurations();
		biosvflomportsconfiguration.setBiosvprofile(biosvprofile);
		addNew(biosvflomportsconfiguration);
	}

	private void unmarshallBiosvflvdimmSupport(Biosvprofile biosvprofile) {
		Biosvflvdimmsupport biosvflvdimmsupport = biosvprofile
				.getBiosvflvdimmsupports();
		biosvflvdimmsupport.setBiosvprofile(biosvprofile);
		addNew(biosvflvdimmsupport);
	}

	private void unmarshallBiosvfMaximumMemorybelow4gb(Biosvprofile biosvprofile) {
		Biosvfmaximummemorybelow4gb biosvfmaximummemorybelow4gb = biosvprofile
				.getBiosvfmaximummemorybelow4gbs();
		biosvfmaximummemorybelow4gb.setBiosvprofile(biosvprofile);
		addNew(biosvfmaximummemorybelow4gb);
	}

	private void unmarshallBiosvfMaxVariablemtrrSetting(
			Biosvprofile biosvprofile) {
		Biosvfmaxvariablemtrrsetting biosvfmaxvariablemtrrsetting = biosvprofile
				.getBiosvfmaxvariablemtrrsettings();
		biosvfmaxvariablemtrrsetting.setBiosvprofile(biosvprofile);
		addNew(biosvfmaxvariablemtrrsetting);
	}

	private void unmarshallBiosvfOptionromEnable(Biosvprofile biosvprofile) {
		Biosvfoptionromenable biosvfoptionromenable = biosvprofile
				.getBiosvfoptionromenables();
		biosvfoptionromenable.setBiosvprofile(biosvprofile);
		addNew(biosvfoptionromenable);
	}

	private void unmarshallBiosvfOptionromLoad(Biosvprofile biosvprofile) {
		Biosvfoptionromload biosvfoptionromload = biosvprofile
				.getBiosvfoptionromloads();
		biosvfoptionromload.setBiosvprofile(biosvprofile);
		addNew(biosvfoptionromload);
	}

	private void unmarshallBiosvfOnboardsataController(Biosvprofile biosvprofile) {
		Biosvfonboardsatacontroller biosvfonboardsatacontroller = biosvprofile
				.getBiosvfonboardsatacontrollers();
		biosvfonboardsatacontroller.setBiosvprofile(biosvprofile);
		addNew(biosvfonboardsatacontroller);
	}

	private void unmarshallBiosvfonboardStorage(Biosvprofile biosvprofile) {
		Biosvfonboardstorage biosvfonboardstorage = biosvprofile
				.getBiosvfonboardstorages();
		biosvfonboardstorage.setBiosvprofile(biosvprofile);
		addNew(biosvfonboardstorage);
	}

	private void unmarshallBiosvfMirroringMode(Biosvprofile biosvprofile) {
		Biosvfmirroringmode biosvfmirroringmode = biosvprofile
				.getBiosvfmirroringmodes();
		biosvfmirroringmode.setBiosvprofile(biosvprofile);
		addNew(biosvfmirroringmode);
	}

	private void unmarshallBiosvfnumaOptimized(Biosvprofile biosvprofile) {
		Biosvfnumaoptimized biosvfnumaoptimized = biosvprofile
				.getBiosvfnumaoptimizeds();
		biosvfnumaoptimized.setBiosvprofile(biosvprofile);
		addNew(biosvfnumaoptimized);
	}

	private void unmarshallBiosvfucsmBootorderRuleControl(
			Biosvprofile biosvprofile) {
		Biosvfucsmbootorderrulecontrol biosvfucsmbootorderrulecontrol = biosvprofile
				.getBiosvfucsmbootorderrulecontrols();
		biosvfucsmbootorderrulecontrol.setBiosvprofile(biosvprofile);
		addNew(biosvfucsmbootorderrulecontrol);
	}

	private void unmarshallBiosvfosBootWatchdogtimerTimeout(
			Biosvprofile biosvprofile) {
		Biosvfosbootwatchdogtimertimeout biosvfosbootwatchdogtimertimeout = biosvprofile
				.getBiosvfosbootwatchdogtimertimeouts();
		biosvfosbootwatchdogtimertimeout.setBiosvprofile(biosvprofile);
		addNew(biosvfosbootwatchdogtimertimeout);
	}

	private void unmarshallBiosvfPackagecStateLimit(Biosvprofile biosvprofile) {
		Biosvfpackagecstatelimit biosvfpackagecstatelimit = biosvprofile
				.getBiosvfpackagecstatelimits();
		biosvfpackagecstatelimit.setBiosvprofile(biosvprofile);
		addNew(biosvfpackagecstatelimit);
	}

	private void unmarshallBiosvfosBootwatchDogtimer(Biosvprofile biosvprofile) {
		Biosvfosbootwatchdogtimer biosvfosbootwatchdogtimers = biosvprofile
				.getBiosvfosbootwatchdogtimers();
		biosvfosbootwatchdogtimers.setBiosvprofile(biosvprofile);
		addNew(biosvfosbootwatchdogtimers);
	}

	private void unmarshallBiosvfosBootwatchDogtimerPolicy(
			Biosvprofile biosvprofile) {
		Biosvfosbootwatchdogtimerpolicy biosvfosbootwatchdogtimerpolicy = biosvprofile
				.getBiosvfosbootwatchdogtimerpolicies();
		biosvfosbootwatchdogtimerpolicy.setBiosvprofile(biosvprofile);
		addNew(biosvfosbootwatchdogtimerpolicy);
	}

	private void unmarshallBiosvfusbportConfiguration(Biosvprofile biosvprofile) {
		Biosvfusbportconfiguration biosvfusbportconfiguration = biosvprofile
				.getBiosvfusbportconfigurations();
		biosvfusbportconfiguration.setBiosvprofile(biosvprofile);
		addNew(biosvfusbportconfiguration);
	}

	private void unmarshallBiosvfusbFrontpanelAccesslock(
			Biosvprofile biosvprofile) {
		Biosvfusbfrontpanelaccesslock biosvfusbfrontpanelaccesslock = biosvprofile
				.getBiosvfusbfrontpanelaccesslocks();
		biosvfusbfrontpanelaccesslock.setBiosvprofile(biosvprofile);
		addNew(biosvfusbfrontpanelaccesslock);
	}

	private void unmarshallBiosvfusbbootconfig(Biosvprofile biosvprofile) {
		Biosvfusbbootconfig biosvfusbbootconfig = biosvprofile
				.getBiosvfusbbootconfigs();
		biosvfusbbootconfig.setBiosvprofile(biosvprofile);
		addNew(biosvfusbbootconfig);
	}

	private void unmarshallBiosvfuefiosuselegacyvideo(Biosvprofile biosvprofile) {
		Biosvfuefiosuselegacyvideo biosvfuefiosuselegacyvideo = biosvprofile
				.getBiosvfuefiosuselegacyvideos();
		biosvfuefiosuselegacyvideo.setBiosvprofile(biosvprofile);
		addNew(biosvfuefiosuselegacyvideo);
	}

	private void unmarshallBiosvfvgapriority(Biosvprofile biosvprofile) {
		Biosvfvgapriority biosvfvgapriority = biosvprofile
				.getBiosvfvgapriorities();
		biosvfvgapriority.setBiosvprofile(biosvprofile);
		addNew(biosvfvgapriority);
	}

	private void unmarshallBiosVfUSBSystemIdlePowerOptimizingSetting(
			Biosvprofile biosvprofile) {
		Biosvfusbsystemidlepoweroptimizingsetting biosvfusbsystemidlepoweroptimizingsetting = biosvprofile
				.getBiosvfusbsystemidlepoweroptimizingsettings();
		biosvfusbsystemidlepoweroptimizingsetting.setBiosvprofile(biosvprofile);
		addNew(biosvfusbsystemidlepoweroptimizingsetting);
	}

	private void unmarshallBiosvfintelvtfordirectedio(Biosvprofile biosvprofile) {
		Biosvfintelvtfordirectedio biosvfintelvtfordirectedio = biosvprofile
				.getBiosvfintelvtfordirectedios();
		biosvfintelvtfordirectedio.setBiosvprofile(biosvprofile);
		addNew(biosvfintelvtfordirectedio);
	}

	private void unmarshallBiosvfintelvirtualizationtechnology(
			Biosvprofile biosvprofile) {
		Biosvfintelvirtualizationtechnology biosvfintelvirtualizationtechnology = biosvprofile
				.getBiosvfintelvirtualizationtechnologies();
		biosvfintelvirtualizationtechnology.setBiosvprofile(biosvprofile);
		addNew(biosvfintelvirtualizationtechnology);
	}

	private void unmarshallBiosvfdirectcacheaccess(Biosvprofile biosvprofile) {
		Biosvfdirectcacheaccess biosvfdirectcacheaccess = biosvprofile
				.getBiosvfdirectcacheaccesses();
		biosvfdirectcacheaccess.setBiosvprofile(biosvprofile);
		addNew(biosvfdirectcacheaccess);
	}

	private void unmarshallBiosvfdramclockthrottling(Biosvprofile biosvprofile) {
		Biosvfdramclockthrottling biosvfdramclockthrottling = biosvprofile
				.getBiosvfdramclockthrottlings();
		biosvfdramclockthrottling.setBiosvprofile(biosvprofile);
		addNew(biosvfdramclockthrottling);
	}

	private void unmarshallBiosvfdramrefreshrate(Biosvprofile biosvprofile) {
		Biosvfdramrefreshrate biosvfdramrefreshrate = biosvprofile
				.getBiosvfdramrefreshrates();
		biosvfdramrefreshrate.setBiosvprofile(biosvprofile);
		addNew(biosvfdramrefreshrate);
	}

	private void unmarshallBiosvfenhancedintelspeedsteptech(
			Biosvprofile biosvprofile) {
		Biosvfenhancedintelspeedsteptech biosvfenhancedintelspeedsteptech = biosvprofile
				.getBiosvfenhancedintelspeedstepteches();
		biosvfenhancedintelspeedsteptech.setBiosvprofile(biosvprofile);
		addNew(biosvfenhancedintelspeedsteptech);
	}

	private void unmarshallBiosvfucsmbootmodecontrol(Biosvprofile biosvprofile) {
		Biosvfucsmbootmodecontrol biosvfucsmbootmodecontrol = biosvprofile
				.getBiosvfucsmbootmodecontrols();
		biosvfucsmbootmodecontrol.setBiosvprofile(biosvprofile);
		addNew(biosvfucsmbootmodecontrol);
	}

	private void unmarshallBiosvfsriovconfig(Biosvprofile biosvprofile) {
		Biosvfsriovconfig biosvfsriovconfig = biosvprofile
				.getBiosvfsriovconfigs();
		biosvfsriovconfig.setBiosvprofile(biosvprofile);
		addNew(biosvfsriovconfig);
	}

	private void unmarshallBiosvfselectmemoryrasconfiguration(
			Biosvprofile biosvprofile) {
		Biosvfselectmemoryrasconfiguration biosvfselectmemoryrasconfiguration = biosvprofile
				.getBiosvfselectmemoryrasconfigurations();
		biosvfselectmemoryrasconfiguration.setBiosvprofile(biosvprofile);
		addNew(biosvfselectmemoryrasconfiguration);
	}

	private void unmarshallBiosvfintelentrysasraidmodule(
			Biosvprofile biosvprofile) {
		Biosvfintelentrysasraidmodule biosvfintelentrysasraidmodule = biosvprofile
				.getBiosvfintelentrysasraidmodules();
		biosvfintelentrysasraidmodule.setBiosvprofile(biosvprofile);
		addNew(biosvfintelentrysasraidmodule);
	}

	private void unmarshallBiosvfscrubpolicies(Biosvprofile biosvprofile) {
		Biosvfscrubpolicies biosvfscrubpolicies = biosvprofile
				.getBiosvfscrubpolicieses();
		biosvfscrubpolicies.setBiosvprofile(biosvprofile);
		addNew(biosvfscrubpolicies);
	}

	private void unmarshallBiosvfintelhyperthreadingtech(
			Biosvprofile biosvprofile) {
		Biosvfintelhyperthreadingtech biosvfintelhyperthreadingtech = biosvprofile
				.getBiosvfintelhyperthreadingteches();
		biosvfintelhyperthreadingtech.setBiosvprofile(biosvprofile);
		addNew(biosvfintelhyperthreadingtech);
	}

	private void unmarshallBiosvfsparingmode(Biosvprofile biosvprofile) {
		Biosvfsparingmode biosvfsparingmode = biosvprofile
				.getBiosvfsparingmodes();
		biosvfsparingmode.setBiosvprofile(biosvprofile);
		addNew(biosvfsparingmode);
	}

	private void unmarshallBiosvfserialportaenable(Biosvprofile biosvprofile) {
		Biosvfserialportaenable biosvfserialportaenable = biosvprofile
				.getBiosvfserialportaenables();
		biosvfserialportaenable.setBiosvprofile(biosvprofile);
		addNew(biosvfserialportaenable);
	}

	private void unmarshallBiosvfqpilinkfrequencyselect(
			Biosvprofile biosvprofile) {
		Biosvfqpilinkfrequencyselect biosvfqpilinkfrequencyselect = biosvprofile
				.getBiosvfqpilinkfrequencyselects();
		biosvfqpilinkfrequencyselect.setBiosvprofile(biosvprofile);
		addNew(biosvfqpilinkfrequencyselect);
	}

	private void unmarshallBiosvfexecutedisablebit(Biosvprofile biosvprofile) {
		Biosvfexecutedisablebit biosvfexecutedisablebit = biosvprofile
				.getBiosvfexecutedisablebits();
		biosvfexecutedisablebit.setBiosvprofile(biosvprofile);
		addNew(biosvfexecutedisablebit);
	}

	private void unmarshallBiosvfpstatecoordination(Biosvprofile biosvprofile) {
		Biosvfpstatecoordination biosvfpstatecoordination = biosvprofile
				.getBiosvfpstatecoordinations();
		biosvfpstatecoordination.setBiosvprofile(biosvprofile);
		addNew(biosvfpstatecoordination);
	}

	private void unmarshallBiosvffrb2timer(Biosvprofile biosvprofile) {
		Biosvffrb2timer biosvffrb2timer = biosvprofile.getBiosvffrb2timers();
		biosvffrb2timer.setBiosvprofile(biosvprofile);
		addNew(biosvffrb2timer);
	}

	private void unmarshallBiosvfresumeonacpowerloss(Biosvprofile biosvprofile) {
		Biosvfresumeonacpowerloss biosvfresumeonacpowerloss = biosvprofile
				.getBiosvfresumeonacpowerlosses();
		biosvfresumeonacpowerloss.setBiosvprofile(biosvprofile);
		addNew(biosvfresumeonacpowerloss);
	}

	private void unmarshallBiosvffrequencyflooroverride(
			Biosvprofile biosvprofile) {
		Biosvffrequencyflooroverride biosvffrequencyflooroverride = biosvprofile
				.getBiosvffrequencyflooroverrides();
		biosvffrequencyflooroverride.setBiosvprofile(biosvprofile);
		addNew(biosvffrequencyflooroverride);
	}

	private void unmarshallBiosvfquietboot(Biosvprofile biosvprofile) {
		Biosvfquietboot biosvfquietboot = biosvprofile.getBiosvfquietboots();
		biosvfquietboot.setBiosvprofile(biosvprofile);
		addNew(biosvfquietboot);
	}

	private void unmarshallBiosvffrontpanellockout(Biosvprofile biosvprofile) {
		Biosvffrontpanellockout biosvffrontpanellockout = biosvprofile
				.getBiosvffrontpanellockouts();
		biosvffrontpanellockout.setBiosvprofile(biosvprofile);
		addNew(biosvffrontpanellockout);
	}

	private void unmarshallBiosvfacpi10support(Biosvprofile biosvprofile) {
		Biosvfacpi10support biosvfacpi10support = biosvprofile
				.getBiosvfacpi10supports();
		biosvfacpi10support.setBiosvprofile(biosvprofile);
		addNew(biosvfacpi10support);
	}

	private void unmarshallBiosvfprocessorprefetchconfig(
			Biosvprofile biosvprofile) {
		Biosvfprocessorprefetchconfig biosvfprocessorprefetchconfig = biosvprofile
				.getBiosvfprocessorprefetchconfigs();
		biosvfprocessorprefetchconfig.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorprefetchconfig);
	}

	private void unmarshallBiosvfprocessorenergyconfiguration(
			Biosvprofile biosvprofile) {
		Biosvfprocessorenergyconfiguration biosvfprocessorenergyconfiguration = biosvprofile
				.getBiosvfprocessorenergyconfigurations();
		biosvfprocessorenergyconfiguration.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorenergyconfiguration);
	}

	private void unmarshallBiosvfprocessorcstate(Biosvprofile biosvprofile) {
		Biosvfprocessorcstate biosvfprocessorcstate = biosvprofile
				.getBiosvfprocessorcstates();
		biosvfprocessorcstate.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorcstate);
	}

	private void unmarshallBiosvfcpuperformance(Biosvprofile biosvprofile) {
		Biosvfcpuperformance biosvfcpuperformance = biosvprofile
				.getBiosvfcpuperformances();
		biosvfcpuperformance.setBiosvprofile(biosvprofile);
		addNew(biosvfcpuperformance);
	}

	private void unmarshallBiosvfprocessorc7report(Biosvprofile biosvprofile) {
		Biosvfprocessorc7report biosvfprocessorc7report = biosvprofile
				.getBiosvfprocessorc7reports();
		biosvfprocessorc7report.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorc7report);
	}

	private void unmarshallBiosvfprocessorc6report(Biosvprofile biosvprofile) {
		Biosvfprocessorc6report biosvfprocessorc6report = biosvprofile
				.getBiosvfprocessorc6reports();
		biosvfprocessorc6report.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorc6report);
	}

	private void unmarshallBiosvfconsoleredirection(Biosvprofile biosvprofile) {
		Biosvfconsoleredirection biosvfconsoleredirection = biosvprofile
				.getBiosvfconsoleredirections();
		biosvfconsoleredirection.setBiosvprofile(biosvprofile);
		addNew(biosvfconsoleredirection);
	}

	private void unmarshallBiosvfprocessorc3report(Biosvprofile biosvprofile) {
		Biosvfprocessorc3report biosvfprocessorc3report = biosvprofile
				.getBiosvfprocessorc3reports();
		biosvfprocessorc3report.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorc3report);
	}

	private void unmarshallBiosvfcoremultiprocessing(Biosvprofile biosvprofile) {
		Biosvfcoremultiprocessing biosvfcoremultiprocessing = biosvprofile
				.getBiosvfcoremultiprocessings();
		biosvfcoremultiprocessing.setBiosvprofile(biosvprofile);
		addNew(biosvfcoremultiprocessing);
	}

	private void unmarshallBiosvfprocessorc1e(Biosvprofile biosvprofile) {
		Biosvfprocessorc1e biosvfprocessorc1e = biosvprofile
				.getBiosvfprocessorc1es();
		biosvfprocessorc1e.setBiosvprofile(biosvprofile);
		addNew(biosvfprocessorc1e);
	}

	private void unmarshallBiosvfassertnmionserr(Biosvprofile biosvprofile) {
		Biosvfassertnmionserr biosvfassertnmionserr = biosvprofile
				.getBiosvfassertnmionserrs();
		biosvfassertnmionserr.setBiosvprofile(biosvprofile);
		addNew(biosvfassertnmionserr);
	}

	private void unmarshallBiosvfposterrorpause(Biosvprofile biosvprofile) {
		Biosvfposterrorpause biosvfposterrorpause = biosvprofile
				.getBiosvfposterrorpauses();
		biosvfposterrorpause.setBiosvprofile(biosvprofile);
		addNew(biosvfposterrorpause);
	}

	private void unmarshallBiosvfbootoptionretry(Biosvprofile biosvprofile) {
		Biosvfbootoptionretry biosvfbootoptionretry = biosvprofile
				.getBiosvfbootoptionretries();
		biosvfbootoptionretry.setBiosvprofile(biosvprofile);
		addNew(biosvfbootoptionretry);
	}

	private void unmarshallBiosvfpcislotoptionromenable(
			Biosvprofile biosvprofile) {
		Biosvfpcislotoptionromenable biosvfpcislotoptionromenable = biosvprofile
				.getBiosvfpcislotoptionromenables();
		biosvfpcislotoptionromenable.setBiosvprofile(biosvprofile);
		addNew(biosvfpcislotoptionromenable);
	}

	private void unmarshallBiosvfallusbdevice(Biosvprofile biosvprofile) {
		Biosvfallusbdevices biosvfallusbdevices = biosvprofile
				.getBiosvfallusbdeviceses();
		biosvfallusbdevices.setBiosvprofile(biosvprofile);
		addNew(biosvfallusbdevices);
	}

	private void unmarshallBiosvfpcislotlinkspeed(Biosvprofile biosvprofile) {
		Biosvfpcislotlinkspeed biosvfpcislotlinkspeed = biosvprofile
				.getBiosvfpcislotlinkspeeds();
		biosvfpcislotlinkspeed.setBiosvprofile(biosvprofile);
		addNew(biosvfpcislotlinkspeed);
	}

	private void unmarshallBiosvfassertnmionperr(Biosvprofile biosvprofile) {
		Biosvfassertnmionperr biosvfassertnmionperr = biosvprofile
				.getBiosvfassertnmionperrs();
		biosvfassertnmionperr.setBiosvprofile(biosvprofile);
		addNew(biosvfassertnmionperr);
	}

}
