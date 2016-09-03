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
import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.SanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;
import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author pavkuma2
 * 
 */

@Service("sanService")
public class SANServiceImpl extends CommonDaoServicesImpl implements SANService {

	private final CommonUtilServices commonUtilService;
	private static final String DEFAULT = "default";	
	private static final String SANCONNECTIVITYPOLICY_ID = "sanConnectivityPolicy.id";

	@Autowired
	public SANServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanVsan> fetchSanVsanConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<SanVsan>)(List<?>)listAll(SanVsan.class, criteria);
	}

	@Override
	public List<SanVsan> saveOrUpdateSanVsanConfiguration(
			List<SanVsan> sanVsanList, Integer projectId) {
		for (SanVsan sanVsan : sanVsanList) {
			sanVsan.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sanVsan.getId() == 0) {
				sanVsan.setId(addNew(sanVsan));
			} else {
				update(sanVsan);
			}
		}
		return sanVsanList;
	}

	@Override
	public void deleteSanVsan(List<SanVsan> sanVsanList) {
		deleteAll(sanVsanList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanWwnn> fetchSanWwnnConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<SanWwnn>)(List<?>)listAll(SanWwnn.class, criteria);
	}

	@Override
	public List<SanWwnn> saveOrUpdateSanWwnnConfiguration(
			List<SanWwnn> sanWwnnList, Integer projectId) {
		for (SanWwnn sanWwnn : sanWwnnList) {
			sanWwnn.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sanWwnn.getId() == 0) {
				sanWwnn.setId(addNew(sanWwnn));
			} else {
				update(sanWwnn);
			}
		}
		return sanWwnnList;
	}

	@Override
	public void deleteSanWwnn(List<SanWwnn> sanWwnnList) {
		deleteAll(sanWwnnList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanWwpn> fetchSanWwpnConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<SanWwpn>)(List<?>)listAll(SanWwpn.class, criteria);
	}

	@Override
	public List<SanWwpn> saveOrUpdateSanWwpnConfiguration(
			List<SanWwpn> sanWwpnList, Integer projectId) {
		for (SanWwpn sanWwpn : sanWwpnList) {
			sanWwpn.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sanWwpn.getId() == 0) {
				sanWwpn.setId(addNew(sanWwpn));
			} else {
				update(sanWwpn);
			}
		}
		return sanWwpnList;
	}

	@Override
	public void deleteSanWwpn(List<SanWwpn> sanWwpnList) {
		deleteAll(sanWwpnList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanVhbaTemplate> fetchSanVhbaTemplateConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<SanVhbaTemplate>)(List<?>)listAll(SanVhbaTemplate.class, criteria);
	}

	@Override
	public List<SanVhbaTemplate> saveOrUpdateSanVhbaTemplateConfiguration(
			List<SanVhbaTemplate> sanVhbaList, Integer projectId) {
		for (SanVhbaTemplate sanVhbaTemplate : sanVhbaList) {
			sanVhbaTemplate.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sanVhbaTemplate.getId() == 0) {
				sanVhbaTemplate.setId(addNew(sanVhbaTemplate));
			} else {
				update(sanVhbaTemplate);
			}
		}
		return sanVhbaList;
	}

	@Override
	public void deleteSanVhbaTemplate(List<SanVhbaTemplate> sanVhbaList) {
		deleteAll(sanVhbaList);
	}

	@Override
	public void deleteAllSANConfigurationDetails(Integer projectId) {

		Criterion vsanCriteria = Restrictions.and(
				Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId),
				Restrictions.ne("vsanName", DEFAULT));
		deleteByCriteria(SanVsan.class, vsanCriteria);

		Criterion wwnnCriteria = Restrictions.and(
				Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId),
				Restrictions.ne("wwnnName", DEFAULT));
		deleteByCriteria(SanWwnn.class, wwnnCriteria);

		Criterion wwpnCriteria = Restrictions.and(
				Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId),
				Restrictions.ne("wwpnName", DEFAULT));
		deleteByCriteria(SanWwpn.class, wwpnCriteria);

		Criterion vhbaCriteria = Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId);
		deleteByCriteria(SanVhbaTemplate.class, vhbaCriteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanAdapterPolicies> fetchSanAdapterPolicies(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<SanAdapterPolicies>)(List<?>)listAll(SanAdapterPolicies.class, criteria);
	}

	@Override
	public void deleteSanAdapterPolicies(List<SanAdapterPolicies> deletedAdapterPolicyList) {
		deleteAll(deletedAdapterPolicyList);

	}

	@Override
	public List<SanAdapterPolicies> saveOrUpdateAdapterPolicies(
			List<SanAdapterPolicies> sanAdapterPolicyList, Integer projectId) {
		for (SanAdapterPolicies sapObj : sanAdapterPolicyList) {
			sapObj.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sapObj.getId() == 0) {
				sapObj.setId(addNew(sapObj));
			} else {
				update(sapObj);
			}
		}
		return sanAdapterPolicyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanVhba> fetchSanVhbaDetail(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<SanVhba>)(List<?>)listAll(SanVhba.class, criteria);
	}

	@Override
	public List<SanVhba> saveOrUpdateSanVhba(List<SanVhba> sanVhbaList,
			Integer projectId) {
		for (SanVhba svhba : sanVhbaList) {
			svhba.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (svhba.getId() == 0) {
				svhba.setId(addNew(svhba));
			} else {
				update(svhba);
			}
		}
		return sanVhbaList;
	}

	@Override
	public void deleteSanVhba(List<SanVhba> deletedSanVhbaList) {
		deleteAll(deletedSanVhbaList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanConnectivityPolicy> fetchSanConnectivityPolicyDetail(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));		
		return (List<SanConnectivityPolicy>)(List<?>)listAll(SanConnectivityPolicy.class, criteria);
	}

	@Override
	public List<SanConnectivityPolicy> saveOrUpdateSanConnectivityPolicy(
			List<SanConnectivityPolicy> sanConnectivityPolicyList, Integer projectId) {
		for (SanConnectivityPolicy scp : sanConnectivityPolicyList) {
			scp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (scp.getId() == 0) {
				scp.setId(addNew(scp));
			} else {
				update(scp);
			}
			saveOrUpdateSanScpVhbaMappings(scp.getSanScpVhbaMappings(),
					scp.getId());
		}
		return sanConnectivityPolicyList;
	}

	@Override
	public void deleteSanConnectivityPolicy(
			List<SanConnectivityPolicy> deletedSanConnectivityPolicyList) {
		deleteAll(deletedSanConnectivityPolicyList);
	}

	@Override
	public List<Object> fetchSanScpVhbaMappings(Integer scpId) {
		Criterion criteria = Restrictions.eq(SANCONNECTIVITYPOLICY_ID, scpId);
		return listAll(SanScpVhbaMapping.class, criteria);
	}

	@Override
	public List<SanScpVhbaMapping> saveOrUpdateSanScpVhbaMappings(
			List<SanScpVhbaMapping> sanScpVhbaMappingList, Integer scpId) {
		Criterion criteria = Restrictions.eq(SANCONNECTIVITYPOLICY_ID, scpId);
		deleteByCriteria(SanScpVhbaMapping.class, criteria);
		SanConnectivityPolicy scp = new SanConnectivityPolicy();
		scp.setId(scpId);
		for (SanScpVhbaMapping mappingObj : sanScpVhbaMappingList) {			
			mappingObj.setSanConnectivityPolicy(scp);
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			}
		}
		return sanScpVhbaMappingList;
	}

	@Override
	public void deleteSanScpVhbaMappings(List<SanScpVhbaMapping> sanScpVhbaMappingList) {
		deleteAll(sanScpVhbaMappingList);
	}

}