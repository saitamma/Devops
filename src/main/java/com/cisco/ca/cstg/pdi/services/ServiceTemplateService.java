/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.ServersSPTVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersSPTVnicMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfile;
import com.cisco.ca.cstg.pdi.pojos.ServersServiceProfileTemplate;

/**
 * @author pavkuma2
 * 
 */
public interface ServiceTemplateService {

	List<ServersServiceProfileTemplate> fetchServersServiceProfileTemplateConfiguration(
			Integer projectId);

	List<ServersServiceProfileTemplate> saveOrUpdateServersServiceProfileTemplateConfiguration(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplateList, Integer projectId);

	void deleteServersServiceProfileTemplate(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplateList);

	List<ServersServiceProfile> fetchServersServiceProfileDetails(
			Integer projectId);

	List<ServersServiceProfile> saveOrUpdateServersServiceProfileDetails(
			List<ServersServiceProfile> serversServiceProfileTemplateInstantiateList,
			Integer projectId);

	void deleteServersServiceProfiles(
			List<ServersServiceProfile> serversServiceProfileTemplateInstantiateList);
	
	List<ServersSPTVnicMapping> fetchServersSPTVnicMappings(Integer sptId);
	
	List<ServersSPTVnicMapping> saveOrUpdateServersSPTVnicMappings(List<ServersSPTVnicMapping> serversSPTVnicMappingList, Integer sptId);
	
	void deleteServersSPTVnicMappings(List<ServersSPTVnicMapping> serversSPTVnicMappingList);
	
	List<ServersSPTVhbaMapping> fetchServersSPTVhbaMappings(Integer sptId);
	
	List<ServersSPTVhbaMapping> saveOrUpdateServersSPTVhbaMappings(List<ServersSPTVhbaMapping> serversSPTVhbaMappingList, Integer sptId);
	
	void deleteServersSPTVhbaMappings(List<ServersSPTVhbaMapping> serversSPTVhbaMappingList);
}
