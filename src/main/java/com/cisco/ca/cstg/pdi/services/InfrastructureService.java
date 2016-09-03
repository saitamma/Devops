/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.InfrastructureEthernetFCMode;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;

/**
 * @author pavkuma2
 * 
 */
public interface InfrastructureService {

	List<Infrastructure> fetchInfrastructureDetails(Integer projectId);

	void saveInfrastructureDetails(Infrastructure infrastructure,
			Integer projectId);

	List<ServerModel> fetchServerModelDetails(String serverModelName);	

	void deleteEthernetAdapterPolicyDefaultUsNICValue(Integer projectId,
			String ethAdapterDefaultUsNic);

	List<InfrastructureEthernetFCMode> fetchEthernetFcMode(Integer projectId);

	void saveOrUpdateEthernetFcMode(InfrastructureEthernetFCMode ethFcObj,
			Integer projectId);
}
