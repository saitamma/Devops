/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.SanAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.SanConnectivityPolicy;
import com.cisco.ca.cstg.pdi.pojos.SanScpVhbaMapping;
import com.cisco.ca.cstg.pdi.pojos.SanVhba;
import com.cisco.ca.cstg.pdi.pojos.SanVhbaTemplate;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.pojos.SanWwnn;
import com.cisco.ca.cstg.pdi.pojos.SanWwpn;

/**
 * @author pavkuma2
 * 
 */
public interface SANService {

	List<SanVsan> fetchSanVsanConfiguration(Integer projectId);

	List<SanVsan> saveOrUpdateSanVsanConfiguration(List<SanVsan> sanVsanList,
			Integer projectId);

	void deleteSanVsan(List<SanVsan> sanVsanList);

	List<SanWwnn> fetchSanWwnnConfiguration(Integer projectId);

	List<SanWwnn> saveOrUpdateSanWwnnConfiguration(List<SanWwnn> sanWwnnList,
			Integer projectId);

	void deleteSanWwnn(List<SanWwnn> sanWwnnList);

	List<SanWwpn> fetchSanWwpnConfiguration(Integer projectId);

	List<SanWwpn> saveOrUpdateSanWwpnConfiguration(List<SanWwpn> sanWwpnList,
			Integer projectId);

	void deleteSanWwpn(List<SanWwpn> sanWwpnList);

	List<SanVhbaTemplate> fetchSanVhbaTemplateConfiguration(Integer projectId);

	List<SanVhbaTemplate> saveOrUpdateSanVhbaTemplateConfiguration(
			List<SanVhbaTemplate> sanVhbaList, Integer projectId);

	void deleteSanVhbaTemplate(List<SanVhbaTemplate> sanVhbaList);

	void deleteAllSANConfigurationDetails(Integer projectId);

	List<SanAdapterPolicies> fetchSanAdapterPolicies(Integer projectId);

	void deleteSanAdapterPolicies(List<SanAdapterPolicies> deletedAdapterPolicyList);

	List<SanAdapterPolicies> saveOrUpdateAdapterPolicies(List<SanAdapterPolicies> sanAdapterPolicyList,
			Integer projectId);

	List<SanVhba> fetchSanVhbaDetail(Integer projectId);

	List<SanVhba> saveOrUpdateSanVhba(List<SanVhba> sanVhbaList, Integer projectId);

	void deleteSanVhba(List<SanVhba> deletedSanVhbaList);

	List<SanConnectivityPolicy> fetchSanConnectivityPolicyDetail(Integer projectId);

	List<SanConnectivityPolicy> saveOrUpdateSanConnectivityPolicy(
			List<SanConnectivityPolicy> sanConnectivityPolicyList, Integer projectId);

	void deleteSanConnectivityPolicy(
			List<SanConnectivityPolicy> deletedSanConnectivityPolicyList);

	List<Object> fetchSanScpVhbaMappings(Integer scpId);

	List<SanScpVhbaMapping> saveOrUpdateSanScpVhbaMappings(
			List<SanScpVhbaMapping> sanScpVhbaMappingList, Integer scpId);

	void deleteSanScpVhbaMappings(List<SanScpVhbaMapping> sanScpVhbaMappingList);
}
