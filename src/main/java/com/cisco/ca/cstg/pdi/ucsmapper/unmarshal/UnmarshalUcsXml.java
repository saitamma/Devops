package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.controllers.project.ProjectController;
import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;

@Service("unmarshalUcsXml")
public class UnmarshalUcsXml extends CommonDaoServicesImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private UnmarshallOrgOrg unmarshallOrgOrg;
	@Autowired
	private UnmarshallFabricEp unmarshallFabricEp;
	@Autowired
	private UnmarshallTopSystem unmarshallTopSystem;
	@Autowired
	private UnmarshallCallhome unmarshallCallhome;
	
	@Autowired
	private UnmarshallMetaData unmarshallMetaData; 

	public UnmarshalUcsXml(){
	}

	public void unmarshalTopRootElement(String fileName) throws FileNotFoundException, JAXBException {
		LOGGER.info("Start : UnmarshalUcsXml : unmarshalTopRootElement: file Name :-"+fileName);
		try {
			JAXBContext context = JAXBContext.newInstance(Toproot.class);
			Unmarshaller um = context.createUnmarshaller();

			Source source = new StreamSource(new FileReader(fileName));
			JAXBElement<Toproot> toprootElement = um.unmarshal(source, Toproot.class);
			Toproot topRoot = toprootElement.getValue();
			addNew(topRoot);
			
			unmarshallOrgOrg.unmarshallOrgOrgTag(topRoot);
			unmarshallFabricEp.unmarshallFabricEpTag(topRoot);
			unmarshallTopSystem.unmarshallTopSystemTag(topRoot);
			unmarshallCallhome.unmarshallCallhomeTag(topRoot);
		} catch (javax.xml.bind.JAXBException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw ex;
		} catch (Exception e){
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		LOGGER.info("End : UnmarshalUcsXml : unmarshalTopRootElement: file Name :-"+fileName);
	}

	public void unmarshalMetatData(String metaDataFilePath, int projectId) throws IOException, JAXBException {
		LOGGER.info("Start : UnmarshalUcsXml : unmarshalMetatData: Project Id :-"+projectId);
		unmarshallMetaData.readMetaData(metaDataFilePath, projectId);
		LOGGER.info("End : UnmarshalUcsXml : unmarshalMetatData: Project Id :-"+projectId);
	}
	
	public void unmarshalMetatDataMO(String metaDataFilePath, int projectId) throws IOException, JAXBException {
		LOGGER.info("Start : UnmarshalUcsXml : unmarshalMetatDataMO: Project Id :-"+projectId);
		unmarshallMetaData.readMetaDataMO(metaDataFilePath, projectId);
		LOGGER.info("End : UnmarshalUcsXml : unmarshalMetatDataMO");
	}
}
