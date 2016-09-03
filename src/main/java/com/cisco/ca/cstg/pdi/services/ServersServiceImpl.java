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
import com.cisco.ca.cstg.pdi.pojos.ServersBiosPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicyLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySan;
import com.cisco.ca.cstg.pdi.pojos.ServersBootPolicySanTarget;
import com.cisco.ca.cstg.pdi.pojos.ServersHostFirmware;
import com.cisco.ca.cstg.pdi.pojos.ServersLocalDisc;
import com.cisco.ca.cstg.pdi.pojos.ServersMaintenancePolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersSPQSlotMapping;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPool;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolPolicy;
import com.cisco.ca.cstg.pdi.pojos.ServersServerPoolQualifier;
import com.cisco.ca.cstg.pdi.pojos.ServersUuidPool;
import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author pavkuma2
 * 
 */

@Service("serversService")
public class ServersServiceImpl extends CommonDaoServicesImpl implements
		ServersService {

	private static final String BOOTPOLICYID = "serversBootPolicy.id";
	private final CommonUtilServices commonUtilService;
	private static final String SPQID = "serversServerPoolQualifier.id";

	@Autowired
	public ServersServiceImpl(SessionFactory hibernateSessionFactory,
			CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersUuidPool> fetchServersUuidPoolConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersUuidPool>) (List<?>) listAll(ServersUuidPool.class, criteria);
	}

	@Override
	public List<ServersUuidPool> saveOrUpdateServersUuidPoolConfiguration(
			List<ServersUuidPool> serversUuidPoolList, Integer projectId) {
		for (ServersUuidPool uuidPool : serversUuidPoolList) {
			uuidPool.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (uuidPool.getId() == 0) {
				uuidPool.setId(addNew(uuidPool));
			} else {
				update(uuidPool);
			}
		}
		return serversUuidPoolList;
	}

	@Override
	public void deleteServersUuidPool(List<ServersUuidPool> serversUuidPoolList) {
		deleteAll(serversUuidPoolList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicy> fetchServersBootPolicyConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersBootPolicy>)(List<?>) listAll(ServersBootPolicy.class, criteria);
	}

	@Override
	public List<ServersBootPolicy> saveOrUpdateServersBootPolicyConfiguration(
			List<ServersBootPolicy> serversBootPolicyList, Integer projectId) {
		for (ServersBootPolicy bootPolicy : serversBootPolicyList) {
			bootPolicy.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (bootPolicy.getId() == 0) {
				bootPolicy.setId(addNew(bootPolicy));
			} else {
				update(bootPolicy);
			}
		}
		return serversBootPolicyList;
	}

	@Override
	public void deleteServersBootPolicy(List<ServersBootPolicy> serversBootPolicyList) {
		deleteAll(serversBootPolicyList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicyLan> fetchServersBootPolicyLanConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersBootPolicyLan>)(List<?>) listAll(ServersBootPolicyLan.class, criteria);
	}

	@Override
	public List<ServersBootPolicyLan> saveOrUpdateServersBootPolicyLanConfiguration(
			List<ServersBootPolicyLan> serversBootPolicyLanList, Integer projectId) {
		for (ServersBootPolicyLan bpl : serversBootPolicyLanList) {
			bpl.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (bpl.getId() == 0) {
				bpl.setId(addNew(bpl));
			} else {
				update(bpl);
			}
		}
		return serversBootPolicyLanList;
	}

	@Override
	public void deleteServersBootPolicyLan(List<ServersBootPolicyLan> serversBootPolicyLanList) {
		deleteAll(serversBootPolicyLanList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicySan> fetchServersBootPolicySanConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersBootPolicySan>)(List<?>) listAll(ServersBootPolicySan.class, criteria);
	}

	@Override
	public List<ServersBootPolicySan> saveOrUpdateServersBootPolicySanConfiguration(
			List<ServersBootPolicySan> serversBootPolicySanList, Integer projectId) {
		for (Object obj : serversBootPolicySanList) {
			ServersBootPolicySan bps = (ServersBootPolicySan) obj;
			bps.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (bps.getId() == 0) {
				bps.setId(addNew(bps));
			} else {
				update(bps);
			}
		}
		return serversBootPolicySanList;
	}

	@Override
	public void deleteServersBootPolicySan(List<ServersBootPolicySan> serversBootPolicySanList) {
		deleteAll(serversBootPolicySanList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicySanTarget> fetchServersBootPolicySanTargetConfiguration(
			Integer bootPolicyId) {
		Criterion criteria = Restrictions.eq(BOOTPOLICYID, bootPolicyId);
		return (List<ServersBootPolicySanTarget>)(List<?>) listAll(ServersBootPolicySanTarget.class, criteria);
	}

	@Override
	public List<ServersBootPolicySanTarget> saveOrUpdateServersBootPolicySanTargetConfiguration(
			List<ServersBootPolicySanTarget> serversBootPolicySanTargetList) {
		for (ServersBootPolicySanTarget bpst : serversBootPolicySanTargetList) {
			if (bpst.getId() == 0) {
				bpst.setId(addNew(bpst));
			} else {
				update(bpst);
			}
		}
		return serversBootPolicySanTargetList;
	}

	@Override
	public void deleteServersBootPolicySanTarget(
			List<ServersBootPolicySanTarget> serversBootPolicySanTargetList) {
		deleteAll(serversBootPolicySanTargetList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersLocalDisc> fetchServersLocalDiscConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersLocalDisc>)(List<?>) listAll(ServersLocalDisc.class, criteria);
	}

	@Override
	public List<ServersLocalDisc> saveOrUpdateServersLocalDiscConfiguration(
			List<ServersLocalDisc> serversLocalDiscList, Integer projectId) {
		for (ServersLocalDisc ld : serversLocalDiscList) {
			ld.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (ld.getId() == 0) {
				ld.setId(addNew(ld));
			} else {
				update(ld);
			}
		}
		return serversLocalDiscList;
	}

	@Override
	public void deleteServersLocalDisc(List<ServersLocalDisc> serversLocalDiscList) {
		deleteAll(serversLocalDiscList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersServerPool> fetchServersServerPoolConfiguration(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersServerPool>)(List<?>) listAll(ServersServerPool.class, criteria);
	}

	@Override
	public List<ServersServerPool> saveOrUpdateServersServerPoolConfiguration(
			List<ServersServerPool> serversServerPoolList, Integer projectId) {
		for (ServersServerPool sp : serversServerPoolList) {
			sp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (sp.getId() == 0) {
				sp.setId(addNew(sp));
			} else {
				update(sp);
			}
		}
		return serversServerPoolList;
	}

	@Override
	public void deleteServersServerPool(List<ServersServerPool> serversServerPoolList) {
		deleteAll(serversServerPoolList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersServerPoolPolicy> fetchServersServerPoolPolicyConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersServerPoolPolicy>)(List<?>) listAll(ServersServerPoolPolicy.class, criteria);
	}

	@Override
	public List<ServersServerPoolPolicy> saveOrUpdateServersServerPoolPolicyConfiguration(
			List<ServersServerPoolPolicy> serversServerPoolPolicyList, Integer projectId) {
		for (ServersServerPoolPolicy spp : serversServerPoolPolicyList) {
			spp.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (spp.getId() == 0) {
				spp.setId(addNew(spp));
			} else {
				update(spp);
			}
		}
		return serversServerPoolPolicyList;
	}

	@Override
	public void deleteServersServerPoolPolicy(
			List<ServersServerPoolPolicy> serversServerPoolPolicyList) {
		deleteAll(serversServerPoolPolicyList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersServerPoolQualifier> fetchServersServerPoolQualifierConfiguration(
			Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersServerPoolQualifier>)(List<?>) listAll(ServersServerPoolQualifier.class,
				criteria);
	}

	@Override
	public List<ServersServerPoolQualifier> saveOrUpdateServersServerPoolQualifierConfiguration(
			List<ServersServerPoolQualifier> serversServerPoolQualifierList, Integer projectId) {
		for (ServersServerPoolQualifier spq : serversServerPoolQualifierList) {
			spq.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));
			if (spq.getId() == 0) {
				spq.setId(addNew(spq));
			} else {
				update(spq);
			}
		}
		return serversServerPoolQualifierList;
	}

	@Override
	public void deleteServersServerPoolQualifier(
			List<ServersServerPoolQualifier> serversServerPoolQualifierList) {
		deleteAll(serversServerPoolQualifierList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicyLan> fetchBootPolicyLanConfigDetails(Integer bootPolicyId) {
		Criterion criteria = Restrictions.eq(BOOTPOLICYID, bootPolicyId);
		return (List<ServersBootPolicyLan>)(List<?>) listAll(ServersBootPolicyLan.class, criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicySan> fetchBootPolicySanConfigDetails(Integer bootPolicyId) {
		Criterion criteria = Restrictions.eq(BOOTPOLICYID, bootPolicyId);
		return (List<ServersBootPolicySan>)(List<?>) listAll(ServersBootPolicySan.class, criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBootPolicyLocalDisc> fetchBootPolicyLocalDiscConfigDetails(
			Integer bootPolicyId) {
		Criterion criteria = Restrictions.eq(BOOTPOLICYID, bootPolicyId);
		return (List<ServersBootPolicyLocalDisc>)(List<?>) listAll(ServersBootPolicyLocalDisc.class, criteria);
	}

	@Override
	public List<ServersBootPolicyLocalDisc> saveOrUpdateServersBootPolicyLocalDiscConfiguration(
			List<ServersBootPolicyLocalDisc> serversBootPolicyLocalDiscList, Integer projectId) {
		boolean flag = false;

		for (ServersBootPolicyLocalDisc bpld : serversBootPolicyLocalDiscList) {
			// Deleting all existing LocalDisc info. and Creating new entries
			if (!flag) {
				Criterion criteria = Restrictions.eq(BOOTPOLICYID,
						bpld.getServersBootPolicy().getId());
				deleteByCriteria(ServersBootPolicyLocalDisc.class, criteria);
				flag = true;
			}
			if (bpld.getDevice() == null) {
				return null;
			}
			bpld.setProjectDetails(commonUtilService
					.getProjectDetailsObject(projectId));

			if (bpld.getId() == 0) {
				bpld.setId(addNew(bpld));
			} else {
				update(bpld);
			}
		}
		return serversBootPolicyLocalDiscList;
	}

	@Override
	public void deleteServersBootPolicyLocalDisc(Integer bootPolicyId) {
		Criterion criteria = Restrictions.eq(BOOTPOLICYID, bootPolicyId);
		deleteByCriteria(ServersBootPolicyLocalDisc.class, criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersSPQSlotMapping> fetchServersSPQSlotMappings(Integer spqId) {
		Criterion criteria = Restrictions.eq(SPQID, spqId);
		return (List<ServersSPQSlotMapping>)(List<?>) listAll(ServersSPQSlotMapping.class, criteria);
	}

	@Override
	public List<ServersSPQSlotMapping> saveOrUpdateServersSPQSlotMappings(
			List<ServersSPQSlotMapping> serversSPQSlotMappingList) {
		for (ServersSPQSlotMapping mappingObj : serversSPQSlotMappingList) {
			if (mappingObj.getId() == 0) {
				addNew(mappingObj);
			} else {
				update(mappingObj);
			}
		}
		return serversSPQSlotMappingList;
	}

	@Override
	public void deleteServersSPQSlotMappings(
			List<ServersSPQSlotMapping> serversSPQSlotMappingList) {
		deleteAll(serversSPQSlotMappingList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersHostFirmware> fetchHostFirmwareDetail(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersHostFirmware>)(List<?>) listAll(ServersHostFirmware.class, criteria);
	}

	@Override
	public List<ServersHostFirmware> saveOrUpdateHostFirmwareDetails(List<ServersHostFirmware> serversHostFirmwareList , Integer projectId) {
		for (ServersHostFirmware shf : serversHostFirmwareList) {
			shf.setProjectDetails(commonUtilService.getProjectDetailsObject(projectId));
			if (shf.getId() == 0) {
				addNew(shf);
			} else {
				update(shf);
			}
		}
		return serversHostFirmwareList;
	}

	@Override
	public void deleteServersHostFirmwareDetails(
			List<ServersHostFirmware> serversHostFirmwareList) {
		deleteAll(serversHostFirmwareList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersBiosPolicy> fetchBiosPolicyDetail(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersBiosPolicy>)(List<?>) listAll(ServersBiosPolicy.class, criteria);
	}

	@Override
	public List<ServersBiosPolicy> saveOrUpdateBiosPolicyDetails(
			List<ServersBiosPolicy> serversBiosPolicyList, Integer projectId) {
		for(ServersBiosPolicy sbp : serversBiosPolicyList){
			sbp.setProjectDetails(commonUtilService.getProjectDetailsObject(projectId));
			if(sbp.getId() == 0){
				addNew(sbp);
			}else{
				update(sbp);
			}
		}
		return serversBiosPolicyList;
	}

	@Override
	public void deleteServersBiosPolicyDetails(
			List<ServersBiosPolicy> deletedServersBiosPolicyList) {
		deleteAll(deletedServersBiosPolicyList);
	}

	@Override
	public ServersBiosPolicy fetchSingleBiosPolicyDetail(Integer biosPolicyId) {
		return (ServersBiosPolicy) findById(ServersBiosPolicy.class, biosPolicyId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServersMaintenancePolicy> fetchMaintenancePolicyDetail(Integer projectId) {
		Criterion criteria = Restrictions.eq(Constants.PROJECTDETAILS,
				commonUtilService.getProjectDetailsObject(projectId));
		return (List<ServersMaintenancePolicy>)(List<?>) listAll(ServersMaintenancePolicy.class, criteria);
	}

	@Override
	public List<ServersMaintenancePolicy> saveOrUpdateMaintenancePolicyDetails(
			List<ServersMaintenancePolicy> serversMaintenancePolicyList, Integer projectId) {
		for (ServersMaintenancePolicy smp : serversMaintenancePolicyList) {
			smp.setProjectDetails(commonUtilService.getProjectDetailsObject(projectId));
			if (smp.getId() == 0) {
				addNew(smp);
			} else {
				update(smp);
			}
		}
		return serversMaintenancePolicyList;
	}

	@Override
	public void deleteServersMaintenancePolicyDetails(
			List<ServersMaintenancePolicy> deletedServersMaintenancePolicyList) {
		deleteAll(deletedServersMaintenancePolicyList);
	}

}
