/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfile;
import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author pavkuma2
 * 
 */

@Service("serviceTemplateService")
public class ServiceTemplateServiceImpl extends CommonDaoServicesImpl implements
		ServiceTemplateService {

	private static final String SERVERSSERVICEPROFILETEMPLATE_ID = "serversServiceProfileTemplate.id";
	private final CommonUtilServices commonUtilService;

	@Autowired
	public ServiceTemplateServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersServiceProfileTemplate> fetchServersServiceProfileTemplateConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		List<ServersServiceProfileTemplate> serviceProfileTemplatesList = (List<ServersServiceProfileTemplate>)(List<?>)listAll(ServersServiceProfileTemplate.class,
				criteria);
		return serviceProfileTemplatesList;
	}

	@Override
	public List<ServersServiceProfileTemplate> saveOrUpdateServersServiceProfileTemplateConfiguration(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplateList, Integer projectId) {
		for (ServersServiceProfileTemplate sspt : serversServiceProfileTemplateList) {			
			sspt.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sspt.getId() == 0) {
				sspt.setId(addNew(sspt));
			} else {
				update(sspt);
			}
			saveOrUpdateServersSPTVnicMappings(sspt.getServersSptVnicMappings(),
					sspt.getId());
			saveOrUpdateServersSPTVhbaMappings(sspt.getServersSptVhbaMappings(),
					sspt.getId());
		}
		return serversServiceProfileTemplateList;
	}

	@Override
	public void deleteServersServiceProfileTemplate(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplateList) {
		deleteAll(serversServiceProfileTemplateList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersServiceProfile> fetchServersServiceProfileDetails(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersServiceProfile>)(List<?>)listAll(ServersServiceProfile.class, criteria);
	}

	@Override
	public List<ServersServiceProfile> saveOrUpdateServersServiceProfileDetails(
			List<ServersServiceProfile> serversServiceProfileTemplateInstantiateList,
			Integer projectId) {
		for (ServersServiceProfile ssp : serversServiceProfileTemplateInstantiateList) {			
			ssp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (ssp.getId() == 0) {
				ssp.setId(addNew(ssp));
			} else {
				update(ssp);
			}
		}
		return serversServiceProfileTemplateInstantiateList;
	}

	@Override
	public void deleteServersServiceProfiles(
			List<ServersServiceProfile> serversServiceProfileTemplateInstantiateList) {
		deleteAll(serversServiceProfileTemplateInstantiateList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersSPTVnicMapping> fetchServersSPTVnicMappings(Integer sptId) {
		Criterion criteria = Restrictions.eq(SERVERSSERVICEPROFILETEMPLATE_ID, sptId);
		return (List<ServersSPTVnicMapping>)(List<?>)listAll(ServersSPTVnicMapping.class, criteria);
	}

	@Override
	public List<ServersSPTVnicMapping> saveOrUpdateServersSPTVnicMappings(
			List<ServersSPTVnicMapping> serversSPTVnicMappingList, Integer sptId) {
		Criterion criteria = Restrictions.eq(SERVERSSERVICEPROFILETEMPLATE_ID, sptId);
		deleteByCriteria(ServersSPTVnicMapping.class, criteria);
		for (ServersSPTVnicMapping mappingObj : serversSPTVnicMappingList) {		
			mappingObj.setServersServiceProfileTemplate(new ServersServiceProfileTemplate(sptId));			
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
		return serversSPTVnicMappingList;
	}

	@Override
	public void deleteServersSPTVnicMappings(
			List<ServersSPTVnicMapping> serversSPTVnicMappingList) {
		deleteAll(serversSPTVnicMappingList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersSPTVhbaMapping> fetchServersSPTVhbaMappings(Integer sptId) {
		Criterion criteria = Restrictions.eq(SERVERSSERVICEPROFILETEMPLATE_ID, sptId);
		return (List<ServersSPTVhbaMapping>)(List<?>)listAll(ServersSPTVhbaMapping.class, criteria);
	}

	@Override
	public List<ServersSPTVhbaMapping> saveOrUpdateServersSPTVhbaMappings(
			List<ServersSPTVhbaMapping> serversSPTVhbaMappingList, Integer sptId) {
		Criterion criteria = Restrictions.eq(SERVERSSERVICEPROFILETEMPLATE_ID, sptId);
		deleteByCriteria(ServersSPTVhbaMapping.class, criteria);
		for (ServersSPTVhbaMapping mappingObj : serversSPTVhbaMappingList) {			
			mappingObj.setServersServiceProfileTemplate(new ServersServiceProfileTemplate(sptId));			
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
		return serversSPTVhbaMappingList;
	}

	@Override
	public void deleteServersSPTVhbaMappings(
			List<ServersSPTVhbaMapping> serversSPTVhbaMappingList) {
		deleteAll(serversSPTVhbaMappingList);

	}
}
