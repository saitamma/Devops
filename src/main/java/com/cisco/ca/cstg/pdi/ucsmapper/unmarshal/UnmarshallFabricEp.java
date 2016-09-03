package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.controllers.project.ProjectController;
import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Dpsecmac;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricbhvlan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricdcesrv;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricdceswsrv;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricdceswsrvep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricdceswsrvpc;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricdceswsrvpcep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethestc;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethestccloud;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethlan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethlanep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethlanpc;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethlanpcep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricethlinkprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricfcestc;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricfcestccloud;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricfcsan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricfcsanep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricfcvsanportep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabriclancloud;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricsancloud;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricsubgroup;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricudldlinkpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricvlan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Fabricvsan;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Flowctrldefinition;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Flowctrlitem;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Mgmtinbandprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Nwctrldefinition;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Qosclassdefinition;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Qosclassethbe;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Qosclassethclassified;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Statsthresholdpolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;

@Service("unmarshallFabricEp")
public class UnmarshallFabricEp extends CommonDaoServicesImpl {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectController.class);
	
	@Autowired
	public UnmarshallFabricEp(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}
	
	public void unmarshallFabricEpTag(Toproot topRoot){
		try {
			Fabricep fabricEp = topRoot.getFabricEp();
			fabricEp.setToproot(topRoot);
			addNew(fabricEp);
			unmarshallFabricDceSrv(fabricEp);
			unmarshallFabricEthEstcCloud(fabricEp);
			unmarshallFabricFcEstcCloud(fabricEp);
			unmarshallFabricLanCloud(fabricEp);
			unmarshallFabricSanCloud(fabricEp);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}
	
	private void unmarshallFabricDceSrv(Fabricep fabricEp){
		List<Fabricdcesrv> fabricDceSrvList = fabricEp.getFabricDceSrv();
		for(Fabricdcesrv fabricDceSrv: fabricDceSrvList){
			fabricDceSrv.setFabricep(fabricEp);
			addNew(fabricDceSrv);
			unmarshallFabricdceswsrvs(fabricDceSrv);
			unmarshallStatsthresholdpolicies(fabricDceSrv);
		}
	}
	
	private void unmarshallFabricEthEstcCloud(Fabricep fabricEp){
		List<Fabricethestccloud> fabricEthEstcCloudList = fabricEp.getFabricEthEstcCloud();
		for (Fabricethestccloud fabricethestccloud : fabricEthEstcCloudList) {
			fabricethestccloud.setFabricep(fabricEp);
			addNew(fabricethestccloud);
			unmarshallFabricEthEstc(fabricethestccloud);
			unmarshallFabricVlan(fabricethestccloud);
			unmarshallNwctrlDefinition(fabricethestccloud);
			unmarshallThresholdPolicy(fabricethestccloud);
		}
	}
	
	private void unmarshallFabricFcEstcCloud(Fabricep fabricEp){
		List<Fabricfcestccloud> fabricFcEstcCloudList = fabricEp.getFabricFcEstcCloud();
		for (Fabricfcestccloud fabricfcestccloud : fabricFcEstcCloudList) {
			fabricfcestccloud.setFabricep(fabricEp);
			addNew(fabricfcestccloud);
			unmarshallFabricbhvlans(fabricfcestccloud);
			unmarshallFabricfcestcs(fabricfcestccloud);
			unmarshallFabricvsans(fabricfcestccloud);
			unmarshallStatsthresholdpolicies(fabricfcestccloud);
		}
	}
	private void unmarshallFabricdceswsrvs(Fabricdcesrv fabricDceSrv){
		List<Fabricdceswsrv> fabricdceswsrvsList = fabricDceSrv.getFabricdceswsrvs();
		for(Fabricdceswsrv fabricdceswsrvs : fabricdceswsrvsList){
			fabricdceswsrvs.setFabricdcesrv(fabricDceSrv);
			addNew(fabricdceswsrvs);
			unmarshallFabricdceswsrveps(fabricdceswsrvs);
			unmarshallFabricdceswsrvpcs(fabricdceswsrvs);
			unmarshallFabricdSubGroups(fabricdceswsrvs);
		}
	}
	
	private void unmarshallFabricdSubGroups(Fabricdceswsrv fabricdceswsrv) {
		List<Fabricsubgroup> fabricsubgroups = fabricdceswsrv.getFabricsubgroups();
		for (Fabricsubgroup fabricsubgroup : fabricsubgroups) {
			fabricsubgroup.setFabricdceswsrv(fabricdceswsrv);
			addNew(fabricsubgroup);
			unmarshallFabricdceswsrveps(fabricsubgroup);
		}
		
	}

	private void unmarshallFabricdceswsrveps(Fabricsubgroup fabricsubgroup) {
		List<Fabricdceswsrvep> fabricdceswsrveps = fabricsubgroup.getFabricdceswsrveps();
		for (Fabricdceswsrvep fabricdceswsrvep : fabricdceswsrveps) {
			fabricdceswsrvep.setFabricsubgroup(fabricsubgroup);
			addNew(fabricdceswsrvep);
		}
		
	}

	private void unmarshallStatsthresholdpolicies(Fabricdcesrv fabricDceSrv){
		List<Statsthresholdpolicy> statsthresholdpolicies = fabricDceSrv.getStatsthresholdpolicies();
		for(Statsthresholdpolicy statsthresholdpolicy : statsthresholdpolicies){
			statsthresholdpolicy.setFabricdcesrv(fabricDceSrv);
			addNew(statsthresholdpolicy);
		}
	}
	
	private void unmarshallFabricdceswsrvpcs(Fabricdceswsrv fabricdceswsrvs){
		List<Fabricdceswsrvpc> fabricdceswsrvpcsList = fabricdceswsrvs.getFabricdceswsrvpcs();
		for(Fabricdceswsrvpc fabricdceswsrvpc : fabricdceswsrvpcsList){
			fabricdceswsrvpc.setFabricdceswsrv(fabricdceswsrvs);
			addNew(fabricdceswsrvpc);
			unmarshallFabricdceswsrvpceps(fabricdceswsrvpc);
		}
	}
	
	private void unmarshallFabricdceswsrvpceps(Fabricdceswsrvpc fabricdceswsrvpc){
		List<Fabricdceswsrvpcep> fabricdceswsrvpceps = fabricdceswsrvpc.getFabricdceswsrvpceps();
		for (Fabricdceswsrvpcep fabricdceswsrvpcep : fabricdceswsrvpceps) {
			fabricdceswsrvpcep.setFabricdceswsrvpc(fabricdceswsrvpc);
			addNew(fabricdceswsrvpcep);
		}
	}
	
	private void unmarshallFabricdceswsrveps(Fabricdceswsrv fabricdceswsrvs){
		List<Fabricdceswsrvep> fabricdceswsrvepsList = fabricdceswsrvs.getFabricdceswsrveps();
		for(Fabricdceswsrvep fabricdceswsrveps : fabricdceswsrvepsList){
			fabricdceswsrveps.setFabricdceswsrv(fabricdceswsrvs);
			addNew(fabricdceswsrveps);
		}
	}
	
	private void unmarshallFabricEthEstc(Fabricethestccloud fabricethestccloud){
		List<Fabricethestc> fabricEthEstcList = fabricethestccloud.getFabricEthEstc();
		for (Fabricethestc fabricethestc : fabricEthEstcList) {
			fabricethestc.setFabricethestccloud(fabricethestccloud);
			addNew(fabricethestc);
		}
	}
	
	private void unmarshallFabricVlan(Fabricethestccloud fabricethestccloud){
		List<Fabricvlan> fabricVlanList = fabricethestccloud.getFabricVlan();
		for (Fabricvlan fabricvlan : fabricVlanList) {
			fabricvlan.setFabricethestccloud(fabricethestccloud);
			addNew(fabricvlan);
		}
	}
	
	private void unmarshallNwctrlDefinition(Fabricethestccloud fabricethestccloud){
		List<Nwctrldefinition> nwctrlDefinitionList = fabricethestccloud.getNwctrlDefinition();
		for (Nwctrldefinition nwctrldefinition : nwctrlDefinitionList) {
			nwctrldefinition.setFabricEthEstcCloud(fabricethestccloud);
			addNew(nwctrldefinition);
			unmarshallDpsecMac(nwctrldefinition);
		}
	}
	
	private void unmarshallThresholdPolicy(Fabricethestccloud fabricethestccloud){
		List<Statsthresholdpolicy> statsThresholdPolicies = fabricethestccloud.getStatsThresholdPolicy();
		for (Statsthresholdpolicy statsthresholdpolicy : statsThresholdPolicies) {
			statsthresholdpolicy.setFabricethestccloud(fabricethestccloud);
			addNew(statsthresholdpolicy);
		}
	}
	private void unmarshallDpsecMac(Nwctrldefinition nwctrldefinition){
		List<Dpsecmac> dpsecMacList = nwctrldefinition.getDpsecMac();
		for (Dpsecmac dpsecmac : dpsecMacList) {
			dpsecmac.setNwctrldefinition(nwctrldefinition);
			addNew(dpsecmac);
		}
	}
	
	private void unmarshallFabricbhvlans(Fabricfcestccloud fabricfcestccloud){
		List<Fabricbhvlan> fabricbhvlansList = fabricfcestccloud.getFabricbhvlans();
		for (Fabricbhvlan fabricbhvlan : fabricbhvlansList) {
			fabricbhvlan.setFabricfcestccloud(fabricfcestccloud);
			addNew(fabricbhvlan);
		}
	} 
	
	private void unmarshallFabricfcestcs(Fabricfcestccloud fabricfcestccloud){
		List<Fabricfcestc> fabricfcestcsList = fabricfcestccloud.getFabricfcestcs();
		for (Fabricfcestc fabricfcestc : fabricfcestcsList) {
			fabricfcestc.setFabricfcestccloud(fabricfcestccloud);
			addNew(fabricfcestc);
		}
	} 
	
	private void unmarshallFabricvsans(Fabricfcestccloud fabricfcestccloud){
		List<Fabricvsan> fabricvsansList = fabricfcestccloud.getFabricvsans();
		for (Fabricvsan fabricvsan : fabricvsansList) {
			fabricvsan.setFabricfcestccloud(fabricfcestccloud);
			addNew(fabricvsan);
			unmarshallFabricfcvsanporteps(fabricvsan);
		}
	}
	private void unmarshallStatsthresholdpolicies(Fabricfcestccloud fabricfcestccloud){
		List<Statsthresholdpolicy> statsthresholdpolicies = fabricfcestccloud.getStatsthresholdpolicies();
		for (Statsthresholdpolicy statsthresholdpolicy : statsthresholdpolicies) {
			statsthresholdpolicy.setFabricfcestccloud(fabricfcestccloud);
			addNew(statsthresholdpolicy);
		}
	}
	
	private void unmarshallFabricfcvsanporteps(Fabricvsan fabricvsan){
		List<Fabricfcvsanportep> fabricfcvsanportepsList = fabricvsan.getFabricfcvsanporteps();
		for (Fabricfcvsanportep fabricfcvsanportep : fabricfcvsanportepsList) {
			fabricfcvsanportep.setFabricvsan(fabricvsan);
			addNew(fabricfcvsanportep);
		}
	}
	
	private void unmarshallFabricLanCloud(Fabricep fabricEp){
		List<Fabriclancloud> fabricLanCloudList = fabricEp.getFabricLanCloud();
		for (Fabriclancloud fabriclancloud : fabricLanCloudList) {
			fabriclancloud.setFabricep(fabricEp);
			addNew(fabriclancloud);
			unmarshallFabricEthLan(fabriclancloud);
			unmarshallFabricEthLinkProfile(fabriclancloud);
			unmarshallFabricUdldLinkPolicy(fabriclancloud);
			unmarshallFabricVlan(fabriclancloud);
			unmarshallFlowctrlDefinition(fabriclancloud);
			unmarshallMgmtInbandProfile(fabriclancloud);
			unmarshallQosclassDefinition(fabriclancloud);
			unmarshallStatsThresholdPolicy(fabriclancloud);
		}
	}
	
	private void unmarshallStatsThresholdPolicy(Fabriclancloud fabriclancloud) {
		List<Statsthresholdpolicy> statsthresholdpolicyList = fabriclancloud.getStatsThresholdPolicy();
		
		for (Statsthresholdpolicy statsthresholdpolicy : statsthresholdpolicyList) {
			statsthresholdpolicy.setFabriclancloud(fabriclancloud);
			addNew(statsthresholdpolicy);
		}
	}
	
	private void unmarshallQosclassDefinition(Fabriclancloud fabriclancloud) {
		List<Qosclassdefinition> qosclassdefinitionList = fabriclancloud.getQosclassDefinition();
		
		for (Qosclassdefinition qosclassdefinition : qosclassdefinitionList) {
			qosclassdefinition.setFabriclancloud(fabriclancloud);
			addNew(qosclassdefinition);
			unmarshallQosclassEthBE(qosclassdefinition);
			unmarshallQosclassEthClassified(qosclassdefinition);
		}
	}
	
	private void unmarshallQosclassEthClassified(Qosclassdefinition qosclassdefinition) {
		List<Qosclassethclassified> qosclassethclassifiedList = qosclassdefinition.getQosclassEthClassified();
		
		for (Qosclassethclassified qosclassethclassified : qosclassethclassifiedList) {
			qosclassethclassified.setQosclassdefinition(qosclassdefinition);
			addNew(qosclassethclassified);
		}
	}
	
	private void unmarshallQosclassEthBE(Qosclassdefinition qosclassdefinition) {
		List<Qosclassethbe> qosclassethbeList = qosclassdefinition.getQosclassEthBE();
		
		for (Qosclassethbe qosclassethbe : qosclassethbeList) {
			qosclassethbe.setQosclassdefinition(qosclassdefinition);
			addNew(qosclassethbe);
		}
	}
	
	private void unmarshallMgmtInbandProfile(Fabriclancloud fabriclancloud) {
		List<Mgmtinbandprofile> mgmtinbandprofileList = fabriclancloud.getMgmtInbandProfile();
		
		for (Mgmtinbandprofile mgmtinbandprofile : mgmtinbandprofileList) {
			mgmtinbandprofile.setFabriclancloud(fabriclancloud);
			addNew(mgmtinbandprofile);
		}
	}
	
	private void unmarshallFlowctrlDefinition(Fabriclancloud fabriclancloud) {
		List<Flowctrldefinition> flowctrldefinitionList = fabriclancloud.getFlowctrlDefinition();
		
		for (Flowctrldefinition flowctrldefinition : flowctrldefinitionList) {
			flowctrldefinition.setFabriclancloud(fabriclancloud);
			addNew(flowctrldefinition);
			unmarshallFlowctrlItem(flowctrldefinition);
		}
	}
	
	private void unmarshallFlowctrlItem(Flowctrldefinition flowctrldefinition) {
		List<Flowctrlitem> flowctrlitemList = flowctrldefinition.getFlowctrlItem();
		
		for (Flowctrlitem flowctrlitem : flowctrlitemList) {
			flowctrlitem.setFlowctrldefinition(flowctrldefinition);
			addNew(flowctrlitem);
		}
	}
	
	private void unmarshallFabricVlan(Fabriclancloud fabriclancloud) {
		List<Fabricvlan> fabricvlanList = fabriclancloud.getFabricVlan();
		
		for (Fabricvlan fabricvlan : fabricvlanList) {
			fabricvlan.setFabriclancloud(fabriclancloud);
			addNew(fabricvlan);
		}
	}
	
	private void unmarshallFabricEthLan(Fabriclancloud fabriclancloud) {
		List<Fabricethlan> fabricethlanList = fabriclancloud.getFabricEthLan();
		
		for (Fabricethlan fabricethlan : fabricethlanList) {
			fabricethlan.setFabriclancloud(fabriclancloud);
			addNew(fabricethlan);
			unmarshallFabricEthLanEp(fabricethlan);
			unmarshallFabricEthLanPc(fabricethlan);
		}
	}
	
	private void unmarshallFabricEthLanEp(Fabricethlan fabricethlan) {
		List<Fabricethlanep> fabricethlanepList = fabricethlan.getFabricEthLanEp();
		
		for (Fabricethlanep fabricethlanep : fabricethlanepList) {
			fabricethlanep.setFabricethlan(fabricethlan);
			addNew(fabricethlanep);
		}
	}
	
	private void unmarshallFabricEthLanPc(Fabricethlan fabricethlan){
		List<Fabricethlanpc> fabricEthLanPcList = fabricethlan.getFabricEthLanPc();
		
		for (Fabricethlanpc fabricethlanpc : fabricEthLanPcList) {
			fabricethlanpc.setFabricethlan(fabricethlan);
			addNew(fabricethlanpc);
			unmarshallFabricethlanpcep(fabricethlanpc);
		}
	}
	
	private void unmarshallFabricethlanpcep(Fabricethlanpc fabricethlanpc) {
		List<Fabricethlanpcep> fabricethlanpcepList = fabricethlanpc.getFabricethlanpcep();
		
		for (Fabricethlanpcep fabricethlanpcep : fabricethlanpcepList) {
			fabricethlanpcep.setFabricethlanpc(fabricethlanpc);
			addNew(fabricethlanpcep);
		}
	}
	
	private void unmarshallFabricEthLinkProfile(Fabriclancloud fabriclancloud) {
		List<Fabricethlinkprofile> fabricethlinkprofileList = fabriclancloud.getFabricEthLinkProfile();
		
		for (Fabricethlinkprofile fabricethlinkprofile : fabricethlinkprofileList) {
			fabricethlinkprofile.setFabriclancloud(fabriclancloud);
			addNew(fabricethlinkprofile);
		}
	}
	
	private void unmarshallFabricUdldLinkPolicy(Fabriclancloud fabriclancloud) {
		List<Fabricudldlinkpolicy> fabricudldlinkpolicyList = fabriclancloud.getFabricUdldLinkPolicy();
		
		for (Fabricudldlinkpolicy fabricudldlinkpolicy : fabricudldlinkpolicyList) {
			fabricudldlinkpolicy.setFabriclancloud(fabriclancloud);
			addNew(fabricudldlinkpolicy);
		}
	}
	
	private void unmarshallFabricSanCloud(Fabricep fabricEp){
		List<Fabricsancloud> fabricSanCloudList = fabricEp.getFabricSanCloud();
		
		for(Fabricsancloud fabricsancloud : fabricSanCloudList) {
			fabricsancloud.setFabricep(fabricEp);
			addNew(fabricsancloud);
			unmarshallStatsThresholdPolicy(fabricsancloud);
			unmarshallFabricVsan(fabricsancloud);
			unmarshallFabricFcSan(fabricsancloud);
		}
	}
	
	private void unmarshallStatsThresholdPolicy(Fabricsancloud fabricsancloud){
		List<Statsthresholdpolicy> statsthresholdpolicyList = fabricsancloud.getStatsThresholdPolicy();
		
		for(Statsthresholdpolicy statsthresholdpolicy : statsthresholdpolicyList) {
			statsthresholdpolicy.setFabricsancloud(fabricsancloud);
			addNew(statsthresholdpolicy);
		}
	}
	
	private void unmarshallFabricVsan(Fabricsancloud fabricsancloud) {
		List<Fabricvsan> fabricVsanList = fabricsancloud.getFabricVsan();
		
		for(Fabricvsan fabricvsan : fabricVsanList) {
			fabricvsan.setFabricsancloud(fabricsancloud);
			addNew(fabricvsan);
			unmarshallFabricFcVsanPortEp(fabricvsan);
		}		
	}
	
	private void unmarshallFabricFcSan(Fabricsancloud fabricsancloud) {
		List<Fabricfcsan> fabricfcsanList = fabricsancloud.getFabricFcSan();
		
		for(Fabricfcsan fabricfcsan : fabricfcsanList) {
			fabricfcsan.setFabricsancloud(fabricsancloud);
			addNew(fabricfcsan);
			unmarshallFabricVsan(fabricfcsan);
			unmarshallFabricFcSanEp(fabricfcsan);
		}
	}
	
	private void unmarshallFabricVsan(Fabricfcsan fabricfcsan) {
		List<Fabricvsan> fabricvsanList = fabricfcsan.getFabricVsan();
		
		for(Fabricvsan fabricvsan : fabricvsanList) {
			fabricvsan.setFabricfcsan(fabricfcsan);
			addNew(fabricvsan);
		}
	}
	
	private void unmarshallFabricFcVsanPortEp(Fabricvsan fabricvsan) {
		List<Fabricfcvsanportep> fabricfcvsanportepList = fabricvsan.getFabricfcvsanporteps();
		
		for(Fabricfcvsanportep fabricfcvsanportep : fabricfcvsanportepList) {
			fabricfcvsanportep.setFabricvsan(fabricvsan);
			addNew(fabricfcvsanportep);
		}
	}
	
	
	private void unmarshallFabricFcSanEp(Fabricfcsan fabricfcsan) {
		List<Fabricfcsanep> fabricfcsanepList = fabricfcsan.getFabricFcSanEp();
		
		for(Fabricfcsanep fabricfcsanep : fabricfcsanepList) {
			fabricfcsanep.setFabricfcsan(fabricfcsan);
			addNew(fabricfcsanep);
		}
	}
}
