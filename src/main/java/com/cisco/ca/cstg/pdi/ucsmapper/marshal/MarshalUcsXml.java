package com.cisco.ca.cstg.pdi.ucsmapper.marshal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForBiosVProfile;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForCallhome;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForEquipment;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForFabricEp;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForLAN;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForMetaData;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForOrg;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForSAN;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForServers;
import com.cisco.ca.cstg.pdi.ucsmapper.services.GenerateXMLForTopSystem;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.OrgOrg;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.TopRoot;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.TopSystem;

@Component("marshalUcsXml")
public class MarshalUcsXml{
	@Autowired
	private GenerateXMLForFabricEp generateXMLForFabricEp;
	@Autowired
	private GenerateXMLForOrg generateXMLForOrg;
	@Autowired
	private GenerateXMLForEquipment generateXMLForEquipment;
	@Autowired
	private GenerateXMLForLAN generateXMLForLAN;
	@Autowired
	private GenerateXMLForSAN generateXMLForSAN;
	@Autowired
	private GenerateXMLForServers generateXMLForServers;
	@Autowired
	private GenerateXMLForBiosVProfile generateXMLForBiosVProfile;
	@Autowired
	private GenerateXMLForTopSystem generateXMLForTopSystem;
	@Autowired
	private GenerateXMLForMetaData generateXMLForMetaData; 
	@Autowired
	private GenerateXMLForCallhome generateXMLForCallhome;
	
	private JAXBContext context;
	private static final Logger LOGGER = LoggerFactory.getLogger(MarshalUcsXml.class);
	
	public MarshalUcsXml(){
		try{
			context = JAXBContext.newInstance(TopRoot.class);
		} catch (javax.xml.bind.JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public boolean exportUcsPdiConfiguration(String pdiConfDataFolderPath, XmlGenProjectDetails project){
		LOGGER.info("Start : MarshalUcsXml : exportUcsPdiConfiguration :- Project Id: "+project);
		try{
			TopRoot topRoot = loadDefaultUcsXMLTemplate(project);
			createUcsFile(pdiConfDataFolderPath,topRoot);
			this.generateXMLForMetaData.writeProjectMetaData(pdiConfDataFolderPath,project);
		}catch( JAXBException | URISyntaxException | IOException e){
			LOGGER.info("exception in exportUcsPdiConfiguration method.",e);
			return false;
		}
		LOGGER.info("End : MarshalUcsXml : exportUcsPdiConfiguration :- Project Id: "+project);
		return true;
	}
	
	private void createUcsFile(String pdiConfDataFolderPath, TopRoot topRoot) throws javax.xml.bind.JAXBException, IOException {
		LOGGER.info("Start : MarshalUcsXml : createUcsFile.");		
		try{
			
			Marshaller m = context.createMarshaller();
			ObjectFactory factory = new ObjectFactory();
			JAXBElement<TopRoot> topRootJE = factory.createTopRoot(topRoot);
			
			StringBuilder returnPath = new StringBuilder(pdiConfDataFolderPath);
			returnPath.append(File.separator).append(Constants.PDI_CONFIG_FILE_NAME);
			File pdiConfFile = new File(returnPath.toString());
			if (!pdiConfFile.exists() && !pdiConfFile.createNewFile()) {
				LOGGER.error("Error in creating empty pdi configuration file"+returnPath);
			}
			if(pdiConfFile.isFile()){
				try (FileOutputStream fos = new FileOutputStream(pdiConfFile)) {
					m.marshal(topRootJE, fos);
				    LOGGER.info("Configuration file to be pushed to UCS is ready in {}",pdiConfDataFolderPath);
				    fos.flush();
				}							    
			}
		} catch (javax.xml.bind.JAXBException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		LOGGER.info("End : MarshalUcsXml : createUcsFile.");
	}
	
	public TopRoot loadDefaultUcsXMLTemplate(XmlGenProjectDetails  project) throws JAXBException, FileNotFoundException, URISyntaxException{
		LOGGER.info("Start : MarshalUcsXml : loadDefaultUcsXMLTemplate.");
		TopRoot topRoot = null;
		try {
		    Unmarshaller um = context.createUnmarshaller();

		    URL pathPattern = MarshalUcsXml.class.getResource(Constants.UCS_XML_SKELETON_PATH); 
			LOGGER.debug("Reading template file from location {}", pathPattern);
		    File file = new File(pathPattern.getFile());
		    Source source = new StreamSource(new FileReader(file));
		    JAXBElement<TopRoot> toprootElement = um.unmarshal(source, TopRoot.class);
		    topRoot = toprootElement.getValue();

		    marhsalUcsXmlWithDbValues(topRoot, project);
		    LOGGER.info("Top root element ready.");
		} catch (javax.xml.bind.JAXBException | FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		LOGGER.info("End : MarshalUcsXml : loadDefaultUcsXMLTemplate.");
		return topRoot;
	}
	
	@SuppressWarnings("rawtypes")
	private void marhsalUcsXmlWithDbValues(TopRoot topRoot, XmlGenProjectDetails project) {
		LOGGER.info("Start : MarshalUcsXml : marhsalUcsXmlWithDbValues: Project id :-"+project);
	    
	    for( Serializable s: topRoot.getContent() ){
	    	if(!( s instanceof String )){
		        Object obj = ((JAXBElement)s).getValue();
		        if( obj instanceof OrgOrg){
		        	marhsalOrgOrgWithDbValues((OrgOrg)obj, project);
		        	LOGGER.info("Org element ready.");
		        }else if( obj instanceof TopSystem){
		        	marhsalTopSystemWithDbValues((TopSystem)obj, project);
		        	LOGGER.info("TopSystem element ready.");
		        }else if( obj instanceof FabricEp){
		        	marhsalFabricEpWithDbValues((FabricEp)obj, project);
		        	LOGGER.info("FabricEp element ready.");
		        }else if( obj instanceof CallhomeEp){
		        	marhsalCallhomeEpWithDbValues((CallhomeEp)obj, project);
		        	LOGGER.info("CallhomeEp element ready.");
		        }
	    	}
	    }
	    
	    LOGGER.info("End : MarshalUcsXml : marhsalUcsXmlWithDbValues: Project id :-"+project);
	}

	public void marhsalOrgOrgWithDbValues(OrgOrg org, XmlGenProjectDetails project) {
		LOGGER.info("Start : MarshalUcsXml : marhsalOrgOrgWithDbValues: Project id :-"+project);
		String name = org.getName();
		if(Constants.ROOT.equalsIgnoreCase(name)){
			this.generateXMLForEquipment.addComputeChassisDiscPolicyAndMgmtIpPool(org,project);
		}
		
		generateXMLForMarshalling(org, project);
		
		List<Organizations> dbsuborgsUnderOrg =  this.generateXMLForOrg.getSubOrganizations(org, project);

		for(Object obj: dbsuborgsUnderOrg){
			OrgOrg subOrg = this.generateXMLForOrg.createSubOrgForOrg(obj);
			marhsalOrgOrgWithDbValues(subOrg, project);
			this.generateXMLForOrg.addSubOrgToOrg(org, subOrg);
		}
		LOGGER.info("End : MarshalUcsXml : marhsalOrgOrgWithDbValues: Project id :-"+project);
	}

	private void generateXMLForMarshalling(OrgOrg org, XmlGenProjectDetails project) {
		this.generateXMLForLAN.addMacPoolsForOrg(org, project);
		this.generateXMLForLAN.addVnicLanConnTemplForOrg(org, project);
		this.generateXMLForLAN.addNWControlPolicy(org, project);
		this.generateXMLForLAN.addQOSPolicy(org, project);
		this.generateXMLForLAN.addAdaptorHostFcProfile(org, project);
		this.generateXMLForLAN.addVnicLanConnPolicy(org, project);
		this.generateXMLForLAN.addAdaptorHostEthIfProfile(org, project);
		this.generateXMLForSAN.addVnicSanConnTemplForOrg(org, project); 
		this.generateXMLForSAN.addFcpoolInitiatorsForWwnn(org, project); 
		this.generateXMLForSAN.addFcpoolInitiatorsForWwpn(org, project);
		this.generateXMLForSAN.addVnicSanConnPolicy(org, project);
		this.generateXMLForServers.addUuidpoolPool(org, project);
		this.generateXMLForServers.addStorageLocalDiskConfigPolicy(org, project);
		this.generateXMLForServers.addComputePool(org, project);
		this.generateXMLForServers.addComputeQual(org, project);
		this.generateXMLForServers.addComputePoolingPolicy(org, project);
		this.generateXMLForServers.addServiceProfile(org, project);
		this.generateXMLForServers.addServiceProfileTemplate(org, project);
		this.generateXMLForServers.addLsbootPolicy(org, project);
		this.generateXMLForServers.addHostFirmware(org, project);
		this.generateXMLForServers.addServersMaintenancePolicy(org, project);
		this.generateXMLForBiosVProfile.addBiosVProfile(org, project);
	}
	
	public void marhsalTopSystemWithDbValues(TopSystem topSystem, XmlGenProjectDetails project) {
		LOGGER.info("Start : MarshalUcsXml : marhsalTopSystemWithDbValues: Project id :-"+project);
		this.generateXMLForTopSystem.addTimezoneDnsNtp(topSystem, project);
		this.generateXMLForTopSystem.addAdminAuthRealm(topSystem, project);
		this.generateXMLForTopSystem.addLdapEp(topSystem, project);
		this.generateXMLForTopSystem.addTacacsPlusEp(topSystem, project);
		this.generateXMLForTopSystem.addRadiusEp(topSystem, project);
		LOGGER.info("End : MarshalUcsXml : marhsalTopSystemWithDbValues: Project id :-"+project);
	}
	
	public void marhsalFabricEpWithDbValues(FabricEp fabricEp, XmlGenProjectDetails project) {
		LOGGER.info("Start : MarshalUcsXml : marhsalFabricEpWithDbValues: Project id :-"+project);
		this.generateXMLForFabricEp.addFabricLanCloud(fabricEp, project);
		this.generateXMLForFabricEp.addFabricSanCloud(fabricEp, project);
		this.generateXMLForFabricEp.addFabricEthLanUplinkPorts(fabricEp, project);
		this.generateXMLForFabricEp.addFabricEthLanPortChannels(fabricEp, project);
		this.generateXMLForFabricEp.addFabricFcSanEpFabricPorts(fabricEp,project);
		LOGGER.info("End : MarshalUcsXml : marhsalFabricEpWithDbValues: Project id :-"+project);
	}
	
	private void marhsalCallhomeEpWithDbValues(CallhomeEp callhomeEp, XmlGenProjectDetails project) {
		LOGGER.info("Start : MarshalUcsXml : marhsalCallhomeEpWithDbValues: Project id :-"+project);
		this.generateXMLForCallhome.updateCallhomeEpProperties(callhomeEp, project);
		this.generateXMLForCallhome.addCallhomeSourceAndSmtp(callhomeEp, project);
		this.generateXMLForCallhome.addCallhomePolicies(callhomeEp, project);
		this.generateXMLForCallhome.addCallhomeProfiles(callhomeEp, project);
		this.generateXMLForCallhome.addCallhomeSystemInventory(callhomeEp, project);
		this.generateXMLForCallhome.addCallhomeTestAlert(callhomeEp, project);
		LOGGER.info("End : MarshalUcsXml : marhsalCallhomeEpWithDbValues: Project id :-"+project);
	}
}
