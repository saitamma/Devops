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
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.InfrastructureEthernetFCMode;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author pavkuma2
 * 
 */

@Service("infrastructureService")
public class InfrastructureServiceImpl extends CommonDaoServicesImpl implements
		InfrastructureService, Constants {
	
	private final CommonUtilServices commonUtilService;

	@Autowired
	public InfrastructureServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Infrastructure> fetchInfrastructureDetails(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<Infrastructure>) (List<?>) listAll(Infrastructure.class,
				criteria);
	}

	@Override
	public void saveInfrastructureDetails(Infrastructure infrastructure,
			Integer projectId) {
		infrastructure.setProjectDetails(commonUtilService
				.getProjectDetailsObject(projectId));
		List<Infrastructure> infrastructureList = fetchInfrastructureDetails(projectId);

		if (infrastructureList != null && infrastructureList.size() == 1) {
			Infrastructure infst = infrastructureList.get(0);
			infrastructure.setId(infst.getId());
		}
		saveOrUpdate(infrastructure);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServerModel> fetchServerModelDetails(String serverModelName) {
		Criterion criteria = Restrictions.eq("description", serverModelName);
		return (List<ServerModel>) (List<?>) listAll(ServerModel.class,
				criteria);
	}	

	// TODO need to implement this service if UCS MINI does not support default
	// usNIC value in Ethernet Adapter Policy Screen else remove this
	// implementation
	@Override
	public void deleteEthernetAdapterPolicyDefaultUsNICValue(Integer projectId,
			String ethAdapterDefaultUsNic) {
		// Criterion criteria = Restrictions.eq("projectId", projectId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfrastructureEthernetFCMode> fetchEthernetFcMode(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<InfrastructureEthernetFCMode>) (List<?>) listAll(
				InfrastructureEthernetFCMode.class, criteria);
	}

	@Override
	public void saveOrUpdateEthernetFcMode(
			InfrastructureEthernetFCMode ethFcObj, Integer projectId) {
		List<InfrastructureEthernetFCMode> fetchEthFcModeList = fetchEthernetFcMode(projectId);
		if (fetchEthFcModeList != null && fetchEthFcModeList.size() == 1) {
			ethFcObj.setId((fetchEthFcModeList.get(0)).getId());
		}
		ethFcObj.setProjectDetails(commonUtilService
				.getProjectDetailsObject(projectId));
		saveOrUpdate(ethFcObj);
	}

}
