package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptoretharfsprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorethcompqueueprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorethfailoverprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorethinterruptprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorethoffloadprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorethrecvqueueprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorethworkqueueprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorextipv6rsshashprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfccdbworkqueueprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcerrorrecoveryprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcinterruptprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcportflogiprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcportplogiprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcportprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcrecvqueueprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorfcworkqueueprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorhostethifprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorhostfcifprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptoripv4rsshashprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptoripv6rsshashprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Adaptorrssprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Biosvprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Computechassisqual;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Computepool;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Computepoolingpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Computepsupolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Computequal;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Computeslotqual;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Dpsecmac;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Epqosdefinition;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Epqosegress;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricvcon;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fcpoolblock;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fcpoolinitiator;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fcpoolinitiators;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.FirmwareComputeHostPack;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Ippoolblock;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Ippoolpool;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.LsbootDefaultLocalImage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootbootsecurity;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootlan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootlanimagepath;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootlocalhddimage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootlocalstorage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootsan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootsancatsanimage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootsancatsanimagepath;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootstorage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootusbexternalimage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootusbflashstorageimage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsbootusbinternalimage;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.LsmaintMaintPolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lspower;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsrequirement;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsserver;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsserverextension;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsvconassign;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Lsversionbeh;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Macpoolblock;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Macpoolpool;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Nwctrldefinition;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Orgorg;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Storagelocaldiskconfigpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Uuidpoolblock;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Uuidpoolpool;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicconndef;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicether;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicetherif;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicfc;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicfcif;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicfcnode;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vniclanconnpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vniclanconntempl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicsanconnpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Vnicsanconntempl;


@Service("unmarshallOrgOrg")
public class UnmarshallOrgOrg extends CommonDaoServicesImpl {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnmarshallOrgOrg.class);
	
	@Autowired
	private UnmarshallBiosPolicyChilds unmarshallBiosPolicyChilds;
	
	@Autowired
	public UnmarshallOrgOrg(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	public void unmarshallOrgOrgTag(Toproot topRoot) {
		try {
			Orgorg orgOrg = topRoot.getOrgOrg();
			orgOrg.setToproot(topRoot);
			addNew(orgOrg);
			unmarshallOrgElemment(orgOrg);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		
	}
	
	private void unmarshallOrgElemment(Orgorg orgOrg) {
		unmarshallAdaptorHostFcIfProfile(orgOrg);
		unmarshallEpqosDefinition(orgOrg);
		unmarshallFcpoolInitiators(orgOrg);
		unmarshallIppoolPool(orgOrg);
		
		unmarshallUuidpoolPool(orgOrg);
		unmarshallMacpoolPool(orgOrg);
		unmarshallVnicSanConnTempl(orgOrg);
		unmarshallLsServer(orgOrg);
		
		unmarshallComputepool(orgOrg);
		unmarshallNwctrlDefinition(orgOrg);
		unmarshallVnicLanConnTempl(orgOrg);
		unmarshallVnicSanConnPolicy(orgOrg);
		unmarshallComputeQual(orgOrg);
		
		unmarshallComputepsupolicy(orgOrg);
		unmarshallStoragelocaldiskconfigpolicy(orgOrg);
		unmarshallComputepoolingpolicy(orgOrg);
		unmarshallLsbootpolicy(orgOrg);
		unmarshallFirmwareComputeHostPack(orgOrg);
		unmarshallLsmaintMaintPolicy(orgOrg);
		unmarshallBiosVProfile(orgOrg);
		unmarshallAdaptorHostEthIfProfile(orgOrg);
		unmarshallVnicLanConnPolicy(orgOrg);		
		
		unmarshallSubOrgs(orgOrg);
	}
	
	private void unmarshallSubOrgs(Orgorg parentOrg) {
		List<Orgorg> subOrgList = parentOrg.getOrgorg();
		for (Orgorg subOrg : subOrgList) {
			subOrg.setParentOrg(parentOrg);
			addNew(subOrg);
			unmarshallOrgElemment(subOrg);
		}
	}		
	
	private void unmarshallVnicLanConnPolicy(Orgorg orgOrg) {
		List<Vniclanconnpolicy> vnicLanConnPolicies = orgOrg.getVnicLanConnPolicy();
		for (Vniclanconnpolicy vniclanconnpolicy : vnicLanConnPolicies) {
			vniclanconnpolicy.setOrgOrg(orgOrg);
			addNew(vniclanconnpolicy);
			unmarshallVnicEther(vniclanconnpolicy);
		}
	}
	
	private void unmarshallVnicEther(Vniclanconnpolicy vniclanconnpolicy) {
		List<Vnicether> vnicEthers = vniclanconnpolicy.getVnicEther();
		for (Vnicether vnicether : vnicEthers) {
			vnicether.setVnicLanConnPolicy(vniclanconnpolicy);
			addNew(vnicether);
			unmarshallVnicEtherIf(vnicether);
		}
	}
	
	private void unmarshallAdaptorHostEthIfProfile(Orgorg orgOrg) {
		List<Adaptorhostethifprofile> adaptorHostEthIfProfiles = orgOrg.getAdaptorHostEthIfProfile();
		for (Adaptorhostethifprofile adaptorhostethifprofile : adaptorHostEthIfProfiles) {
			adaptorhostethifprofile.setOrgorg(orgOrg);
			addNew(adaptorhostethifprofile);
			unmarshallAdaptorEthArfsProfile(adaptorhostethifprofile);
			unmarshallAdaptorEthCompQueueProfile(adaptorhostethifprofile);
			unmarshallAdaptorEthFailoverProfile(adaptorhostethifprofile);
			unmarshallAdaptorEthInterruptProfile(adaptorhostethifprofile);
			unmarshallAdaptorEthOffloadProfile(adaptorhostethifprofile);
			unmarshallAdaptorEthRecvQueueProfile(adaptorhostethifprofile);
			unmarshallAdaptorEthWorkQueueProfile(adaptorhostethifprofile);
			unmarshallAdaptorExtIpV6RssHashProfile(adaptorhostethifprofile);
			unmarshallAdaptorIpV4RssHashProfile(adaptorhostethifprofile);
			unmarshallAdaptorIpV6RssHashProfile(adaptorhostethifprofile);
			unmarshallAdaptorRssProfile(adaptorhostethifprofile);
		}
	}
	
	private void unmarshallAdaptorRssProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorrssprofile> adaptorRssProfiles = adaptorhostethifprofile.getAdaptorRssProfile();
		for (Adaptorrssprofile adaptorrssprofile : adaptorRssProfiles) {
			adaptorrssprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorrssprofile);
		}
	}
	
	private void unmarshallAdaptorIpV6RssHashProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptoripv6rsshashprofile> adaptorIpV6RssHashProfiles = adaptorhostethifprofile.getAdaptorIpV6RssHashProfile();
		for (Adaptoripv6rsshashprofile adaptoripv6rsshashprofile : adaptorIpV6RssHashProfiles) {
			adaptoripv6rsshashprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptoripv6rsshashprofile);
		}
	}
	
	private void unmarshallAdaptorIpV4RssHashProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptoripv4rsshashprofile> adaptorIpV4RssHashProfiles = adaptorhostethifprofile.getAdaptorIpV4RssHashProfile();
		for (Adaptoripv4rsshashprofile adaptoripv4rsshashprofile : adaptorIpV4RssHashProfiles) {
			adaptoripv4rsshashprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptoripv4rsshashprofile);
		}
	}
	
	private void unmarshallAdaptorExtIpV6RssHashProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorextipv6rsshashprofile> adaptorExtIpV6RssHashProfiles = adaptorhostethifprofile.getAdaptorExtIpV6RssHashProfile();
		for (Adaptorextipv6rsshashprofile adaptorextipv6rsshashprofile : adaptorExtIpV6RssHashProfiles) {
			adaptorextipv6rsshashprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorextipv6rsshashprofile);
		}
	}
	
	private void unmarshallAdaptorEthWorkQueueProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorethworkqueueprofile> adaptorEthWorkQueueProfiles = adaptorhostethifprofile.getAdaptorEthWorkQueueProfile();
		for (Adaptorethworkqueueprofile adaptorethworkqueueprofile : adaptorEthWorkQueueProfiles) {
			adaptorethworkqueueprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorethworkqueueprofile);
		}
	}
	
	private void unmarshallAdaptorEthRecvQueueProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorethrecvqueueprofile> adaptorEthRecvQueueProfiles = adaptorhostethifprofile.getAdaptorEthRecvQueueProfile();
		for (Adaptorethrecvqueueprofile adaptorethrecvqueueprofile : adaptorEthRecvQueueProfiles) {
			adaptorethrecvqueueprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorethrecvqueueprofile);
		}
	}
	
	private void unmarshallAdaptorEthOffloadProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorethoffloadprofile> adaptorEthOffloadProfiles = adaptorhostethifprofile.getAdaptorEthOffloadProfile();
		for (Adaptorethoffloadprofile adaptorethoffloadprofile : adaptorEthOffloadProfiles) {
			adaptorethoffloadprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorethoffloadprofile);
		}
	}
	
	private void unmarshallAdaptorEthInterruptProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorethinterruptprofile> adaptorEthInterruptProfiles = adaptorhostethifprofile.getAdaptorEthInterruptProfile();
		for (Adaptorethinterruptprofile adaptorethinterruptprofile : adaptorEthInterruptProfiles) {
			adaptorethinterruptprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorethinterruptprofile);
		}
	}
	
	private void unmarshallAdaptorEthFailoverProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorethfailoverprofile> adaptorEthFailoverProfiles = adaptorhostethifprofile.getAdaptorEthFailoverProfile();
		for (Adaptorethfailoverprofile adaptorethfailoverprofile : adaptorEthFailoverProfiles) {
			adaptorethfailoverprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorethfailoverprofile);
		}
	}
	
	private void unmarshallAdaptorEthCompQueueProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptorethcompqueueprofile> adaptorEthCompQueueProfiles = adaptorhostethifprofile.getAdaptorEthCompQueueProfile();
		for (Adaptorethcompqueueprofile adaptorethcompqueueprofile : adaptorEthCompQueueProfiles) {
			adaptorethcompqueueprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptorethcompqueueprofile);
		}
	}
	
	private void unmarshallAdaptorEthArfsProfile(Adaptorhostethifprofile adaptorhostethifprofile) {
		List<Adaptoretharfsprofile> adaptorEthArfsProfiles = adaptorhostethifprofile.getAdaptorEthArfsProfile();
		for (Adaptoretharfsprofile adaptoretharfsprofile : adaptorEthArfsProfiles) {
			adaptoretharfsprofile.setAdaptorhostethifprofile(adaptorhostethifprofile);
			addNew(adaptoretharfsprofile);
		}
	}
	
	private void unmarshallBiosVProfile(Orgorg orgOrg) {
		List<Biosvprofile> biosVProfiles = orgOrg.getBiosVProfile();
		for (Biosvprofile biosvprofile : biosVProfiles) {
			biosvprofile.setOrgOrg(orgOrg);
			addNew(biosvprofile);
			unmarshallBiosPolicyChilds.unmarshallBiosPolicyChilds(biosvprofile);
		}
	}
	
	private void unmarshallLsmaintMaintPolicy(Orgorg orgOrg) {
		List<LsmaintMaintPolicy> lsmaintMaintPolicies = orgOrg.getLsmaintMaintPolicy();
		for (LsmaintMaintPolicy lsmaintMaintPolicy : lsmaintMaintPolicies) {
			lsmaintMaintPolicy.setOrgOrg(orgOrg);
			addNew(lsmaintMaintPolicy);
		}
	}
	
	private void unmarshallFirmwareComputeHostPack(Orgorg orgOrg) {
		List<FirmwareComputeHostPack> firmwareComputeHostPackList = orgOrg.getFirmwareComputeHostPack();
		for (FirmwareComputeHostPack firmwareComputeHostPack : firmwareComputeHostPackList) {
			firmwareComputeHostPack.setOrgorg(orgOrg);
			addNew(firmwareComputeHostPack);
		}
	}
	
	private void unmarshallIppoolPool(Orgorg orgOrg) {
		List<Ippoolpool> ippoolPool = orgOrg.getIppoolPool();
		for(Ippoolpool ippoolPoolObj : ippoolPool){
			ippoolPoolObj.setOrgorg(orgOrg);
			addNew(ippoolPoolObj);
			unmarshallIppoolBlock(ippoolPoolObj);
			
		}
		
	}

	private void unmarshallIppoolBlock(Ippoolpool ippoolPoolObj) {
		List<Ippoolblock> ippoolBlock = ippoolPoolObj.getIppoolBlock();
		for(Ippoolblock ippoolBlockObj : ippoolBlock){
			ippoolBlockObj.setIppoolpool(ippoolPoolObj);
			addNew(ippoolBlockObj);
		}
	}

	private void unmarshallFcpoolInitiators(Orgorg orgOrg) {
		List<Fcpoolinitiators> fcpoolInitiators = orgOrg.getFcpoolInitiators();
		for(Fcpoolinitiators fcpoolInitiatorsObj : fcpoolInitiators){
			fcpoolInitiatorsObj.setOrgorg(orgOrg);
			addNew(fcpoolInitiatorsObj);
			unmarshallFcpoolBlock(fcpoolInitiatorsObj);
			unmarshallFcpoolInitiator(fcpoolInitiatorsObj);
		}
		
	}

	private void unmarshallFcpoolInitiator(Fcpoolinitiators fcpoolInitiatorsObj) {
		List<Fcpoolinitiator> fcpoolInitiator = fcpoolInitiatorsObj.getFcpoolInitiator();
		for(Fcpoolinitiator fcpoolInitiatorObj : fcpoolInitiator){
			fcpoolInitiatorObj.setFcpoolinitiators(fcpoolInitiatorsObj);
			addNew(fcpoolInitiatorObj);
		}
		
	}

	private void unmarshallFcpoolBlock(Fcpoolinitiators fcpoolInitiatorsObj) {
		List<Fcpoolblock> fcpoolBlock = fcpoolInitiatorsObj.getFcpoolBlock();
		for(Fcpoolblock fcpoolBlockObj : fcpoolBlock){
			fcpoolBlockObj.setFcpoolinitiators(fcpoolInitiatorsObj);
			addNew(fcpoolBlockObj);
		}
		
	}

	private void unmarshallEpqosDefinition(Orgorg orgOrg) {
		List<Epqosdefinition> epqosDefinition = orgOrg.getEpqosDefinition();
		for(Epqosdefinition epqosDefinitionObj : epqosDefinition){
			epqosDefinitionObj.setOrgorg(orgOrg);
			addNew(epqosDefinitionObj);
			unmarshallEpqosEgress(epqosDefinitionObj);
		}
		
	}

	private void unmarshallEpqosEgress(Epqosdefinition epqosDefinitionObj) {
		List<Epqosegress> epqosEgress = epqosDefinitionObj.getEpqosEgress();
		for(Epqosegress epqosEgressObj : epqosEgress){
			epqosEgressObj.setEpqosdefinition(epqosDefinitionObj);
			addNew(epqosEgressObj);
		}
		
	}

	private void unmarshallAdaptorHostFcIfProfile(Orgorg orgOrg) {
		List<Adaptorhostfcifprofile> adaptorHostFcIfProfiles = orgOrg
				.getAdaptorHostFcIfProfile();
		for (Adaptorhostfcifprofile ahp : adaptorHostFcIfProfiles) {
			ahp.setOrgorg(orgOrg);
			addNew(ahp);
			unmarshallAdaptorFcCdbWorkQueueProfile(ahp);
			unmarshallAdaptorFcPortPLogiProfile(ahp);
			UnMarshallAdaptorFcPortFLogiProfile(ahp);
			unmarshallAdaptorFcErrorRecoveryProfile(ahp);
			unmarshallAdaptorFcWorkQueueProfile(ahp);
			unmarshallAdaptorFcRecvQueueProfile(ahp);
			unmarshalladAptorFcPortProfile(ahp);
			unmarshallAdaptorFcInterruptProfile(ahp);
			
		}

	}
	
	private void unmarshallAdaptorFcInterruptProfile(Adaptorhostfcifprofile ahp) {
		List<Adaptorfcinterruptprofile> adaptorFcInterruptProfile = ahp.getAdaptorFcInterruptProfile();
		for(Adaptorfcinterruptprofile adaptorFcInterruptProfileObj : adaptorFcInterruptProfile){
			adaptorFcInterruptProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcInterruptProfileObj);
		}
		
	}

	private void unmarshalladAptorFcPortProfile(Adaptorhostfcifprofile ahp) {
		List<Adaptorfcportprofile> adaptorFcPortProfile = ahp.getAdaptorFcPortProfile();
		for(Adaptorfcportprofile adaptorFcPortProfileObj : adaptorFcPortProfile){
			adaptorFcPortProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcPortProfileObj);
		}
		
	}

	private void unmarshallAdaptorFcRecvQueueProfile(Adaptorhostfcifprofile ahp) {
		List<Adaptorfcrecvqueueprofile> adaptorFcRecvQueueProfile = ahp.getAdaptorFcRecvQueueProfile();
		for(Adaptorfcrecvqueueprofile adaptorFcRecvQueueProfileObj : adaptorFcRecvQueueProfile){
			adaptorFcRecvQueueProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcRecvQueueProfileObj);
		}
		
	}

	private void unmarshallAdaptorFcWorkQueueProfile(Adaptorhostfcifprofile ahp) {
		List<Adaptorfcworkqueueprofile> adaptorFcWorkQueueProfile = ahp.getAdaptorFcWorkQueueProfile();
		for(Adaptorfcworkqueueprofile adaptorFcWorkQueueProfileObj : adaptorFcWorkQueueProfile){
			adaptorFcWorkQueueProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcWorkQueueProfileObj);
		}
		
	}

	private void unmarshallAdaptorFcErrorRecoveryProfile(
			Adaptorhostfcifprofile ahp) {
		List<Adaptorfcerrorrecoveryprofile> adaptorFcErrorRecoveryProfile = ahp.getAdaptorFcErrorRecoveryProfile();
		for(Adaptorfcerrorrecoveryprofile adaptorFcErrorRecoveryProfileObj : adaptorFcErrorRecoveryProfile){
			adaptorFcErrorRecoveryProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcErrorRecoveryProfileObj);
		}
		
	}

	private void UnMarshallAdaptorFcPortFLogiProfile(Adaptorhostfcifprofile ahp) {
		List<Adaptorfcportflogiprofile> adaptorFcPortFLogiProfile = ahp.getAdaptorFcPortFLogiProfile();
		for(Adaptorfcportflogiprofile adaptorFcPortFLogiProfileObj : adaptorFcPortFLogiProfile){
			adaptorFcPortFLogiProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcPortFLogiProfileObj);
		}
		
	}

	private void unmarshallAdaptorFcPortPLogiProfile(Adaptorhostfcifprofile ahp) {
		List<Adaptorfcportplogiprofile> adaptorFcPortPLogiProfile = ahp.getAdaptorFcPortPLogiProfile();
		for(Adaptorfcportplogiprofile adaptorFcPortPLogiProfileObj : adaptorFcPortPLogiProfile){
			adaptorFcPortPLogiProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcPortPLogiProfileObj);
		}
		
	}

	private void unmarshallAdaptorFcCdbWorkQueueProfile(
			Adaptorhostfcifprofile ahp) {
		List<Adaptorfccdbworkqueueprofile> adaptorFcCdbWorkQueueProfile = ahp.getAdaptorFcCdbWorkQueueProfile();
		for(Adaptorfccdbworkqueueprofile adaptorFcCdbWorkQueueProfileObj : adaptorFcCdbWorkQueueProfile){
			adaptorFcCdbWorkQueueProfileObj.setAdaptorhostfcifprofile(ahp);
			addNew(adaptorFcCdbWorkQueueProfileObj);
		}
		
	}
	
	private void unmarshallLsServer(Orgorg orgOrg) {
		List<Lsserver> lsServerList = orgOrg.getLsServer();
		for (Lsserver lsserver : lsServerList) {
			lsserver.setOrgorg(orgOrg);
			addNew(lsserver);
			unmarshallFabricVCon(lsserver);
			unmarshallLsPower(lsserver);
			unmarshallLsRequirement(lsserver);
			unmarshallLsServerExtension(lsserver);
			unmarshallLsVConAssign(lsserver);
			unmarshallLsVersionBeh(lsserver);
			unmarshallVnicConnDef(lsserver);
			unmarshallVnicEther(lsserver);
			unmarshallVnicFc(lsserver);
			unmarshallVnicFcNode(lsserver);
		}
	}
	
	private void unmarshallVnicFcNode(Lsserver lsserver) {
		List<Vnicfcnode> vnicFcNodeList = lsserver.getVnicFcNode();
		for (Vnicfcnode vnicfcnode : vnicFcNodeList) {
			vnicfcnode.setLsserver(lsserver);
			addNew(vnicfcnode);
		}
	}
	
	private void unmarshallVnicFc(Lsserver lsserver) {
		List<Vnicfc> vnicFcList = lsserver.getVnicFc();
		for (Vnicfc vnicfc : vnicFcList) {
			vnicfc.setLsserver(lsserver);
			addNew(vnicfc);
			unmarshallVnicFcIf(vnicfc);
		}
	}
	
	private void unmarshallVnicFcIf(Vnicfc vnicfc) {
		List<Vnicfcif> vnicFcIfList = vnicfc.getVnicFcIf();
		for (Vnicfcif vnicfcif : vnicFcIfList) {
			vnicfcif.setVnicfc(vnicfc);
			addNew(vnicfcif);
		}
	}
	
	private void unmarshallVnicEther(Lsserver lsserver) {
		List<Vnicether> vnicEtherList = lsserver.getVnicEther();
		for (Vnicether vnicether : vnicEtherList) {
			vnicether.setLsserver(lsserver);
			addNew(vnicether);
			unmarshallVnicEtherIf(vnicether);
		}
	}
	
	private void unmarshallVnicEtherIf(Vnicether vnicether) {
		List<Vnicetherif> vnicEtherIfList = vnicether.getVnicEtherIf();
		for (Vnicetherif vnicetherif : vnicEtherIfList) {
			vnicetherif.setVnicether(vnicether);
			addNew(vnicetherif);
		}
	}
	
	private void unmarshallVnicConnDef(Lsserver lsserver) {
		List<Vnicconndef> vnicConnDefList = lsserver.getVnicConnDef();
		for (Vnicconndef vnicconndef : vnicConnDefList) {
			vnicconndef.setLsserver(lsserver);
			addNew(vnicconndef);
		}
	}
	
	private void unmarshallLsVersionBeh(Lsserver lsserver) {
		List<Lsversionbeh> lsVersionBehList = lsserver.getLsVersionBeh();
		for (Lsversionbeh lsversionbeh : lsVersionBehList) {
			lsversionbeh.setLsserver(lsserver);
			addNew(lsversionbeh);
		}
	}
	
	private void unmarshallLsVConAssign(Lsserver lsserver) {
		List<Lsvconassign> lsVConAssignList = lsserver.getLsVConAssign();
		for (Lsvconassign lsvconassign : lsVConAssignList) {
			lsvconassign.setLsserver(lsserver);
			addNew(lsvconassign);
		}
	}
	
	private void unmarshallLsServerExtension(Lsserver lsserver) {
		List<Lsserverextension> lsServerExtensionList = lsserver.getLsServerExtension();
		for (Lsserverextension lsserverextension : lsServerExtensionList) {
			lsserverextension.setLsserver(lsserver);
			addNew(lsserverextension);
		}
	}
	
	private void unmarshallLsRequirement(Lsserver lsserver) {
		List<Lsrequirement> lsRequirementList = lsserver.getLsRequirement();
		for (Lsrequirement lsrequirement : lsRequirementList) {
			lsrequirement.setLsserver(lsserver);
			addNew(lsrequirement);
		}
	}
	
	private void unmarshallLsPower(Lsserver lsserver) {
		List<Lspower> lsPowerList = lsserver.getLsPower();
		for (Lspower lspower : lsPowerList) {
			lspower.setLsserver(lsserver);
			addNew(lspower);
		}
	}
	
	private void unmarshallFabricVCon(Lsserver lsserver) {
		List<Fabricvcon> fabricVConList = lsserver.getFabricVCon();
		for (Fabricvcon fabricvcon : fabricVConList) {
			fabricvcon.setLsserver(lsserver);
			addNew(fabricvcon);
		}
	}
	
	private void unmarshallVnicSanConnTempl(Orgorg orgOrg) {
		List<Vnicsanconntempl> vnicSanConnTemplList = orgOrg.getVnicSanConnTempl();
		for (Vnicsanconntempl vnicsanconntempl : vnicSanConnTemplList) {
			vnicsanconntempl.setOrgOrg(orgOrg);
			addNew(vnicsanconntempl);
			unmarshallVnicFcIf(vnicsanconntempl);
		}
	}
	
	private void unmarshallVnicFcIf(Vnicsanconntempl vnicsanconntempl) {
		List<Vnicfcif> vnicFcIfList = vnicsanconntempl.getVnicFcIf();
		for (Vnicfcif vnicfcif : vnicFcIfList) {
			vnicfcif.setVnicsanconntempl(vnicsanconntempl);
			addNew(vnicfcif);
		}
	}
	
	private void unmarshallMacpoolPool(Orgorg orgOrg) {
		List<Macpoolpool> macpoolPoolList = orgOrg.getMacpoolPool();
		for (Macpoolpool macpoolpool : macpoolPoolList) {
			macpoolpool.setOrgorg(orgOrg);
			addNew(macpoolpool);
			unmarshallMacpoolBlock(macpoolpool);
		}
	}
	
	private void unmarshallMacpoolBlock(Macpoolpool macpoolpool) {
		List<Macpoolblock> macpoolBlockList = macpoolpool.getMacpoolBlock();
		for (Macpoolblock macpoolblock : macpoolBlockList) {
			macpoolblock.setMacpoolpool(macpoolpool);
			addNew(macpoolblock);
		}
	}
	
	private void unmarshallUuidpoolPool(Orgorg orgOrg) {
		List<Uuidpoolpool> uuidpoolPoolList = orgOrg.getUuidpoolPool();
		for (Uuidpoolpool uuidpoolpool : uuidpoolPoolList) {
			uuidpoolpool.setOrgorg(orgOrg);
			addNew(uuidpoolpool);
			unmarshallUuidpoolBlock(uuidpoolpool);
		}
	}
	
	private void unmarshallUuidpoolBlock(Uuidpoolpool uuidpoolpool) {
		List<Uuidpoolblock> uuidpoolBlockList = uuidpoolpool.getUuidpoolBlock();
		for (Uuidpoolblock uuidpoolblock : uuidpoolBlockList) {
			uuidpoolblock.setUuidpoolpool(uuidpoolpool);
			addNew(uuidpoolblock);
		}
	}
	
	private void unmarshallComputeQual(Orgorg orgOrg) {
		List<Computequal> computeQualList = orgOrg.getComputeQual();
		
		for (Computequal computequal : computeQualList) {
			computequal.setOrgorg(orgOrg);
			addNew(computequal);
			unmarshallComputeChassisQual(computequal);
		}
	}

	private void unmarshallComputeChassisQual(Computequal computequal) {
		List<Computechassisqual> computeChassisQualList = computequal.getComputeChassisQual();
		for (Computechassisqual computechassisqual : computeChassisQualList) {
			computechassisqual.setComputequal(computequal);
			addNew(computechassisqual);
			unmarshallComputeSlotQual(computechassisqual);
		}
	}
	
	private void unmarshallComputeSlotQual(Computechassisqual computechassisqual) {
		List<Computeslotqual> computeSlotQualList = computechassisqual.getComputeSlotQual();
		for (Computeslotqual computeslotqual : computeSlotQualList) {
			computeslotqual.setComputechassisqual(computechassisqual);
			addNew(computeslotqual);
		}
	}
	
	private void unmarshallVnicSanConnPolicy(Orgorg orgOrg) {
		List<Vnicsanconnpolicy> vnicSanConnPolicyList = orgOrg.getVnicSanConnPolicy();
		
		for (Vnicsanconnpolicy vnicsanconnpolicy : vnicSanConnPolicyList) {
			vnicsanconnpolicy.setOrgOrg(orgOrg);
			addNew(vnicsanconnpolicy);
			unmarshallVnicFc(vnicsanconnpolicy);
			unmarshallVnicFcNode(vnicsanconnpolicy);
		}
	}
	
	private void unmarshallVnicFcNode(Vnicsanconnpolicy vnicsanconnpolicy) {
		List<Vnicfcnode> vnicfcnodeList = vnicsanconnpolicy.getVnicFcNode();
		
		for (Vnicfcnode vnicfcnode : vnicfcnodeList) {
			vnicfcnode.setVnicsanconnpolicy(vnicsanconnpolicy);
			addNew(vnicfcnode);
		}
	}
	
	private void unmarshallVnicFc(Vnicsanconnpolicy vnicsanconnpolicy) {
		List<Vnicfc> vnicfcList = vnicsanconnpolicy.getVnicFc();
		
		for (Vnicfc vnicfc : vnicfcList) {
			vnicfc.setVnicSanConnPolicy(vnicsanconnpolicy);
			addNew(vnicfc);
			unmarshallVnicFcIf(vnicfc);
		}
	}
	
	private void unmarshallVnicLanConnTempl(Orgorg orgOrg) {
		List<Vniclanconntempl> vnicLanConnTemplList = orgOrg.getVnicLanConnTempl();
		
		for (Vniclanconntempl vniclanconntempl : vnicLanConnTemplList) {
			vniclanconntempl.setOrgOrg(orgOrg);
			addNew(vniclanconntempl);
			unmarshallVnicEtherIf(vniclanconntempl);
		}
	}
	
	private void unmarshallVnicEtherIf(Vniclanconntempl vniclanconntempl) {
		List<Vnicetherif> vnicetherifList = vniclanconntempl.getVnicEtherIf();
		
		for (Vnicetherif vnicetherif : vnicetherifList) {
			vnicetherif.setVniclanconntempl(vniclanconntempl);
			addNew(vnicetherif);
		}
	}
	
	private void unmarshallComputepool(Orgorg orgOrg) {
		List<Computepool> computepoolList = orgOrg.getComputepool();
		
		for (Computepool computepool : computepoolList) {
			computepool.setOrgOrg(orgOrg);
			addNew(computepool);
		}
	}
	
	private void unmarshallNwctrlDefinition(Orgorg orgOrg) {
		List<Nwctrldefinition> nwctrlDefinitionList = orgOrg.getNwctrlDefinition();
		
		for (Nwctrldefinition nwctrldefinition : nwctrlDefinitionList) {
			nwctrldefinition.setOrgOrg(orgOrg);
			addNew(nwctrldefinition);
			unmarshallDpsecMac(nwctrldefinition);
		}
	}
	
	private void unmarshallDpsecMac(Nwctrldefinition nwctrldefinition) {
		List<Dpsecmac> dpsecmacList = nwctrldefinition.getDpsecMac();
		
		for (Dpsecmac dpsecmac : dpsecmacList) {
			dpsecmac.setNwctrldefinition(nwctrldefinition);
			addNew(dpsecmac);
		}
	}

	
	private void unmarshallComputepsupolicy(Orgorg orgOrg) {
		List<Computepsupolicy> computepsupolicyList = orgOrg
				.getComputePsuPolicy();
		for (Computepsupolicy computepsupolicy : computepsupolicyList) {
			computepsupolicy.setOrgorg(orgOrg);
			addNew(computepsupolicy);
		}
	}

	private void unmarshallStoragelocaldiskconfigpolicy(Orgorg orgOrg) {
		List<Storagelocaldiskconfigpolicy> storagelocaldiskconfigpolicyList = orgOrg
				.getStorageLocalDiskConfigPolicy();
		for (Storagelocaldiskconfigpolicy storagelocaldiskconfigpolicy : storagelocaldiskconfigpolicyList) {
			storagelocaldiskconfigpolicy.setOrgorg(orgOrg);
			addNew(storagelocaldiskconfigpolicy);
		}
	}

	private void unmarshallComputepoolingpolicy(Orgorg orgOrg) {
		List<Computepoolingpolicy> computepoolingpolicyList = orgOrg
				.getComputePoolingPolicy();
		for (Computepoolingpolicy computepoolingpolicy : computepoolingpolicyList) {
			computepoolingpolicy.setOrgorg(orgOrg);
			addNew(computepoolingpolicy);
		}
	}

	private void unmarshallLsbootpolicy(Orgorg orgOrg) {
		List<Lsbootpolicy> lsbootpolicyList = orgOrg.getLsbootPolicy();
		for (Lsbootpolicy lsbootpolicy : lsbootpolicyList) {
			lsbootpolicy.setOrgorg(orgOrg);
			addNew(lsbootpolicy);

			unmarshallLsbootstorage(lsbootpolicy);
			unmarshallLsbootbootsecurity(lsbootpolicy);
			unmarshallLsbootlan(lsbootpolicy);
			unmarshallLsbootsan(lsbootpolicy);
		}

	}

	private void unmarshallLsbootstorage(Lsbootpolicy lsbootpolicy) {
		List<Lsbootstorage> lsbootstorageList = lsbootpolicy.getLsbootStorage();
		for (Lsbootstorage lsbootstorage : lsbootstorageList) {
			lsbootstorage.setLsbootpolicy(lsbootpolicy);
			addNew(lsbootstorage);
			unmarshallLsbootLocalStorage(lsbootstorage);
		}
	}

	private void unmarshallLsbootbootsecurity(Lsbootpolicy lsbootpolicy) {
		List<Lsbootbootsecurity> lsbootbootsecurityList = lsbootpolicy
				.getLsbootBootSecurity();
		for (Lsbootbootsecurity lsbootbootsecurity : lsbootbootsecurityList) {
			lsbootbootsecurity.setLsbootpolicy(lsbootpolicy);
			addNew(lsbootbootsecurity);
		}
	}

	private void unmarshallLsbootlan(Lsbootpolicy lsbootpolicy) {
		List<Lsbootlan> lsbootlanList = lsbootpolicy.getLsbootLan();
		for (Lsbootlan lsbootlan : lsbootlanList) {
			lsbootlan.setLsbootpolicy(lsbootpolicy);
			addNew(lsbootlan);
			unmarshallLsbootlanimagepath(lsbootlan);
		}
	}

	private void unmarshallLsbootsan(Lsbootpolicy lsbootpolicy) {
		List<Lsbootsan> lsbootsanList = lsbootpolicy.getLsbootSan();
		for (Lsbootsan lsbootsan : lsbootsanList) {
			lsbootsan.setLsbootpolicy(lsbootpolicy);
			addNew(lsbootsan);
			unmarshalllsbootSanCatSanImage(lsbootsan);
		}
	}


	private void unmarshallLsbootLocalStorage(Lsbootstorage lsbootstorage){
		List<Lsbootlocalstorage> lsbootLocalStorageList = lsbootstorage.getLsbootLocalStorage();
		for (Lsbootlocalstorage lsbootlocalstorage : lsbootLocalStorageList) {
			lsbootlocalstorage.setLsbootstorage(lsbootstorage);
			addNew(lsbootlocalstorage);
			unmarshallLsbootDefaultLocalImage(lsbootlocalstorage);
			unmarshallLsbootLocalHddImage(lsbootlocalstorage);
			unmarshallLsbootUsbExternalImage(lsbootlocalstorage);
			unmarshallLsbootUsbFlashStorageImage(lsbootlocalstorage);
			unmarshallLsbootUsbInternalImage(lsbootlocalstorage);
		}
	}
	
	private void unmarshallLsbootDefaultLocalImage(Lsbootlocalstorage lsbootlocalstorage){
		List<LsbootDefaultLocalImage> lsbootDefaultLocalImageList = lsbootlocalstorage.getLsbootDefaultLocalImage();
		for (LsbootDefaultLocalImage lsbootDefaultLocalImage : lsbootDefaultLocalImageList) {
			lsbootDefaultLocalImage.setLsbootlocalstorage(lsbootlocalstorage);
			addNew(lsbootDefaultLocalImage);
		}
	}
	private void unmarshallLsbootUsbInternalImage(Lsbootlocalstorage lsbootlocalstorage){
		List<Lsbootusbinternalimage> lsbootUsbInternalImageList = lsbootlocalstorage.getLsbootUsbInternalImage();
		for (Lsbootusbinternalimage lsbootusbinternalimage : lsbootUsbInternalImageList) {
			lsbootusbinternalimage.setLsbootlocalstorage(lsbootlocalstorage);
			addNew(lsbootusbinternalimage);
		}
	}
	
	private void unmarshallLsbootUsbFlashStorageImage(Lsbootlocalstorage lsbootlocalstorage){
		List<Lsbootusbflashstorageimage> lsbootUsbFlashStorageImageList = lsbootlocalstorage.getLsbootUsbFlashStorageImage();
		for (Lsbootusbflashstorageimage lsbootusbflashstorageimage : lsbootUsbFlashStorageImageList) {
			lsbootusbflashstorageimage.setLsbootlocalstorage(lsbootlocalstorage);
			addNew(lsbootusbflashstorageimage);
		}
	}
	
	private void unmarshallLsbootUsbExternalImage(Lsbootlocalstorage lsbootlocalstorage){
		List<Lsbootusbexternalimage> lsbootUsbExternalImageList = lsbootlocalstorage.getLsbootUsbExternalImage();
		for (Lsbootusbexternalimage lsbootusbexternalimage : lsbootUsbExternalImageList) {
			lsbootusbexternalimage.setLsbootlocalstorage(lsbootlocalstorage);
			addNew(lsbootusbexternalimage);
		}
	}
	
	private void unmarshallLsbootLocalHddImage(Lsbootlocalstorage lsbootlocalstorage){
		List<Lsbootlocalhddimage> lsbootLocalHddImageList = lsbootlocalstorage.getLsbootLocalHddImage();
		for (Lsbootlocalhddimage lsbootlocalhddimage : lsbootLocalHddImageList) {
			lsbootlocalhddimage.setLsbootlocalstorage(lsbootlocalstorage);
			addNew(lsbootlocalhddimage);
		}
	}
	
	private void unmarshallLsbootlanimagepath(Lsbootlan lsbootlan){
		List<Lsbootlanimagepath> lsbootlanimagepathList = lsbootlan.getLsbootLanImagePath();
		for(Lsbootlanimagepath lsbootlanimagepath : lsbootlanimagepathList){
			lsbootlanimagepath.setLsbootlan(lsbootlan);
			addNew(lsbootlanimagepath);
		}
	}
		
	private void unmarshalllsbootSanCatSanImage(Lsbootsan lsbootsan) {
		List<Lsbootsancatsanimage> lsbootSanCatSanImageList = lsbootsan.getLsbootSanCatSanImage();
		for (Lsbootsancatsanimage lsbootsancatsanimage : lsbootSanCatSanImageList) {
			lsbootsancatsanimage.setLsbootsan(lsbootsan);
			addNew(lsbootsancatsanimage);
			unmarshallLsbootSanCatSanImagePath(lsbootsancatsanimage);
		}
	
	}

	private void unmarshallLsbootSanCatSanImagePath(Lsbootsancatsanimage lsbootsancatsanimage) {
		List<Lsbootsancatsanimagepath> lsbootSanCatSanImagePathList = lsbootsancatsanimage.getLsbootSanCatSanImagePath();
		for (Lsbootsancatsanimagepath lsbootsancatsanimagepath : lsbootSanCatSanImagePathList) {
			lsbootsancatsanimagepath.setLsbootsancatsanimage(lsbootsancatsanimage);
			addNew(lsbootsancatsanimagepath);
		}
	}
	
	
}
