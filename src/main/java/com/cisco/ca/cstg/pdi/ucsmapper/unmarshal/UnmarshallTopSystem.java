package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.controllers.project.ProjectController;
import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaLdapGroup;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaLocale;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaOrg;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaRadiusEp;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaRole;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaTacacsPlusEp;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaTacacsPlusProvider;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaUserEp;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaUserLocale;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.AaaUserRole;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaaauthrealm;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaadomain;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaadomainauth;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaaldapep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaaldapgrouprule;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaaldapprovider;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaaprovidergroup;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Aaaproviderref;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Commdatetime;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Commdns;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Commdnsprovider;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Commntpprovider;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Commsvcep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Topsystem;

@Service("unmarshallTopSystem")
public class UnmarshallTopSystem extends CommonDaoServicesImpl {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectController.class);

	@Autowired
	public UnmarshallTopSystem(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}
	
	public void unmarshallTopSystemTag(Toproot topRoot){
		try {
			Topsystem topSystem = topRoot.getTopSystem();
			topSystem.setTopRoot(topRoot);
			addNew(topSystem);
			unmarshallCommSvcEp(topSystem);
			unmarshallAaaauthrealms(topSystem);
			unmarshallAaaLdapEp(topSystem);
			unmarshallAaaUserEp(topSystem);
			unmarshallAaaRadiusEp(topSystem);
			unmarshallAaaTacacsPlusEp(topSystem);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}
	
	private void unmarshallAaaTacacsPlusEp(Topsystem topSystem){
		List<AaaTacacsPlusEp> aaaTacacsPlusEpList = topSystem.getAaaTacacsPlusEp();
		for (AaaTacacsPlusEp aaaTacacsPlusEp : aaaTacacsPlusEpList) {
			aaaTacacsPlusEp.setTopSystem(topSystem);
			addNew(aaaTacacsPlusEp);
			unmarshallAaaTacacsPlusProviders(aaaTacacsPlusEp);
			unmarshallAaaprovidergroups(aaaTacacsPlusEp);
		}
	}
	
	private void unmarshallAaaprovidergroups(AaaTacacsPlusEp aaaTacacsPlusEp){
		List<Aaaprovidergroup> aaaprovidergroupsList = aaaTacacsPlusEp.getAaaprovidergroups();
		for (Aaaprovidergroup aaaprovidergroup : aaaprovidergroupsList) {
			aaaprovidergroup.setAaaTacacsPlusEp(aaaTacacsPlusEp);
			addNew(aaaprovidergroup);
			unmarshallAaaproviderrefs(aaaprovidergroup);
		}
	}
	
	private void unmarshallAaaTacacsPlusProviders(AaaTacacsPlusEp aaaTacacsPlusEp){
		List<AaaTacacsPlusProvider> aaaTacacsPlusProvidersList = aaaTacacsPlusEp.getAaaTacacsPlusProviders();
		for (AaaTacacsPlusProvider aaaTacacsPlusProvider : aaaTacacsPlusProvidersList) {
			aaaTacacsPlusProvider.setAaaTacacsPlusEp(aaaTacacsPlusEp);
			addNew(aaaTacacsPlusProvider);
		}
	}
	
	private void unmarshallAaaRadiusEp(Topsystem topSystem){
		List<AaaRadiusEp> aaaRadiusEpList = topSystem.getAaaRadiusEp();
		for (AaaRadiusEp aaaRadiusEp : aaaRadiusEpList) {
			aaaRadiusEp.setTopSystem(topSystem);
			addNew(aaaRadiusEp);
			unmarshallAaaRadiusProviders(aaaRadiusEp);
			unmarshallAaaprovidergroups(aaaRadiusEp);
		}
	}
	
	private void unmarshallAaaprovidergroups(AaaRadiusEp aaaRadiusEp){
		List<Aaaprovidergroup> aaaprovidergroupsList = aaaRadiusEp.getAaaprovidergroups();
		for (Aaaprovidergroup aaaprovidergroup : aaaprovidergroupsList) {
			aaaprovidergroup.setAaaRadiusEp(aaaRadiusEp);
			addNew(aaaprovidergroup);
			unmarshallAaaproviderrefs(aaaprovidergroup);
		}
	}
	
	private void unmarshallAaaRadiusProviders(AaaRadiusEp aaaRadiusEp){
		List<AaaRadiusProvider> aaaRadiusProvidersList = aaaRadiusEp.getAaaRadiusProviders();
		for (AaaRadiusProvider aaaRadiusProvider : aaaRadiusProvidersList) {
			aaaRadiusProvider.setAaaRadiusEp(aaaRadiusEp);
			addNew(aaaRadiusProvider);
		}
	}
	
	private void unmarshallAaaUserEp(Topsystem topSystem){
		List<AaaUserEp> aaaUserEpList = topSystem.getAaaUserEp();
		for (AaaUserEp aaaUserEp : aaaUserEpList) {
			aaaUserEp.setTopSystem(topSystem);
			addNew(aaaUserEp);
			unmarshallAaaRoles(aaaUserEp);
			unmarshallAaaLocales(aaaUserEp);
		}
	}
	
	private void unmarshallAaaLocales(AaaUserEp aaaUserEp){
		List<AaaLocale> aaaLocalesList = aaaUserEp.getAaaLocales();
		for (AaaLocale aaaLocale : aaaLocalesList) {
			aaaLocale.setAaaUserEp(aaaUserEp);
			addNew(aaaLocale);
			unmarshallAaaOrgs(aaaLocale);
		}
	}
	
	private void unmarshallAaaOrgs(AaaLocale aaaLocale){
		List<AaaOrg> aaaOrgsList = aaaLocale.getAaaOrgs();
		for (AaaOrg aaaOrg : aaaOrgsList) {
			aaaOrg.setAaaLocale(aaaLocale);
			addNew(aaaOrg);
		}
	}
	
	private void unmarshallAaaRoles(AaaUserEp aaaUserEp){
		List<AaaRole> aaaRolesList = aaaUserEp.getAaaRoles();
		for (AaaRole aaaRole : aaaRolesList) {
			aaaRole.setAaaUserEp(aaaUserEp);
			addNew(aaaRole);
		}
	}
	
	private void unmarshallAaaLdapEp(Topsystem topSystem){
		List<Aaaldapep> aaaLdapEpList = topSystem.getAaaLdapEp();
		for (Aaaldapep aaaldapep : aaaLdapEpList) {
			aaaldapep.setTopSystem(topSystem);
			addNew(aaaldapep);
			unmarshallAaaldapproviders(aaaldapep);
			unmarshallAaaprovidergroups(aaaldapep);
			unmarshallAaaldapgrouprules(aaaldapep);
			unmarshallAaaLdapGroups(aaaldapep);
		}
		
	}
	
	private void unmarshallAaaLdapGroups(Aaaldapep aaaldapep){
		List<AaaLdapGroup> aaaLdapGroupsList = aaaldapep.getAaaLdapGroups();
		for (AaaLdapGroup aaaLdapGroup : aaaLdapGroupsList) {
			aaaLdapGroup.setAaaldapep(aaaldapep);
			addNew(aaaLdapGroup);
			unmarshallAaaUserRoles(aaaLdapGroup);
			unmarshallAaaUserLocales(aaaLdapGroup);
		}
	}
	
	private void unmarshallAaaUserRoles(AaaLdapGroup aaaLdapGroup){
		List<AaaUserRole> aaaUserRolesList = aaaLdapGroup.getAaaUserRoles();
		for (AaaUserRole aaaUserRole : aaaUserRolesList) {
			aaaUserRole.setAaaLdapGroup(aaaLdapGroup);
			addNew(aaaUserRole);
		}
		
	}
	private void unmarshallAaaUserLocales(AaaLdapGroup aaaLdapGroup){
		List<AaaUserLocale> aaaUserLocalesList = aaaLdapGroup.getAaaUserLocales();
		for (AaaUserLocale aaaUserLocale : aaaUserLocalesList) {
			aaaUserLocale.setAaaLdapGroup(aaaLdapGroup);
			addNew(aaaUserLocale);
		}
		
	}
	private void unmarshallAaaldapproviders(Aaaldapep aaaldapep){
		List<Aaaldapprovider> aaaldapprovidersList = aaaldapep.getAaaldapproviders();
		for (Aaaldapprovider aaaldapprovider : aaaldapprovidersList) {
			aaaldapprovider.setAaaldapep(aaaldapep);
			addNew(aaaldapprovider);
			unmarshallAaaldapgrouprules(aaaldapprovider);
		}
	}
	
	private void unmarshallAaaldapgrouprules(Aaaldapprovider aaaldapprovider){
		List<Aaaldapgrouprule> aaaldapgrouprulesList = aaaldapprovider.getAaaldapgrouprules();
		for (Aaaldapgrouprule aaaldapgrouprule : aaaldapgrouprulesList) {
			aaaldapgrouprule.setAaaldapprovider(aaaldapprovider);
			addNew(aaaldapgrouprule);
		}
	}
	
	private void unmarshallAaaprovidergroups(Aaaldapep aaaldapep){
		List<Aaaprovidergroup> aaaprovidergroupsList = aaaldapep.getAaaprovidergroups();
		for (Aaaprovidergroup aaaprovidergroup : aaaprovidergroupsList) {
			aaaprovidergroup.setAaaldapep(aaaldapep);
			addNew(aaaprovidergroup);
			unmarshallAaaproviderrefs(aaaprovidergroup);
		}
	}
	
	private void unmarshallAaaproviderrefs(Aaaprovidergroup aaaprovidergroup){
		List<Aaaproviderref> aaaproviderrefsList = aaaprovidergroup.getAaaproviderrefs();
		for (Aaaproviderref aaaproviderref : aaaproviderrefsList) {
			aaaproviderref.setAaaprovidergroup(aaaprovidergroup);
			addNew(aaaproviderref);
		}
	}

	private void unmarshallAaaldapgrouprules(Aaaldapep aaaldapep){
		List<Aaaldapgrouprule> aaaldapgrouprulesList = aaaldapep.getAaaldapgrouprules();
		for (Aaaldapgrouprule aaaldapgrouprule : aaaldapgrouprulesList) {
			aaaldapgrouprule.setAaaldapep(aaaldapep);
			addNew(aaaldapgrouprule);
		}
	}
	
	private void unmarshallCommSvcEp(Topsystem topSystem){
		List<Commsvcep> commSvcEp = topSystem.getCommSvcEp();
		for(Commsvcep commSvcEpObj : commSvcEp){
			commSvcEpObj.setTopSystem(topSystem);
			addNew(commSvcEpObj);
			
			unmarshallCommDateTime(commSvcEpObj);
			unmarshallCommDns(commSvcEpObj);
		}
	}
	
	private void unmarshallCommDateTime(Commsvcep commSvcEpObj) {
		List<Commdatetime> commDateTime = commSvcEpObj.getCommDateTime();
		for(Commdatetime commDateTimeObj : commDateTime){
			commDateTimeObj.setCommSvcEp(commSvcEpObj);
			addNew(commDateTimeObj);
			
			unmarshallCommNtpProvider(commDateTimeObj);
			
		}
		
	}

	private void unmarshallCommNtpProvider(Commdatetime commDateTimeObj) {
		List<Commntpprovider> commNtpProvider = commDateTimeObj.getCommNtpProvider();
		for(Commntpprovider commNtpProviderObj : commNtpProvider){
			commNtpProviderObj.setCommDateTime(commDateTimeObj);
			addNew(commNtpProviderObj);
		}
	}

	private void unmarshallCommDns(Commsvcep commSvcEpObj) {
		List<Commdns> commDns = commSvcEpObj.getCommDns();
		for(Commdns commDnsObj : commDns){
			commDnsObj.setCommSvcEp(commSvcEpObj);
			addNew(commDnsObj);
			
			unmarshallCommDnsProvider(commDnsObj);
		}
	}

	private void unmarshallCommDnsProvider(Commdns commDnsObj) {
		List<Commdnsprovider> commDnsProvider = commDnsObj.getCommDnsProvider();
		for(Commdnsprovider commDnsProviderObj : commDnsProvider){
			commDnsProviderObj.setCommDns(commDnsObj);
			addNew(commDnsProviderObj);
		}
	}
	
	private void unmarshallAaaauthrealms(Topsystem topSystem){
		List<Aaaauthrealm> aaaAuthRealmList = topSystem.getAaaAuthRealm();
		for(Aaaauthrealm aaaAuthRealmObj : aaaAuthRealmList){
			aaaAuthRealmObj.setTopSystem(topSystem);
			addNew(aaaAuthRealmObj);
			
			unmarshallAaaDomain(aaaAuthRealmObj);
		}
	}

	private void unmarshallAaaDomain(Aaaauthrealm aaaAuthRealmObj) {
		List<Aaadomain> aaaDomain = aaaAuthRealmObj.getAaaDomain();
		for(Aaadomain aaaDomainObj : aaaDomain){
			aaaDomainObj.setAaaAuthRealm(aaaAuthRealmObj);
			addNew(aaaDomainObj);
			
			unmarshallAaaDomainAuth(aaaDomainObj);
		}
		
	}

	private void unmarshallAaaDomainAuth(Aaadomain aaaDomainObj) {
		List<Aaadomainauth> aaaDomainAuth = aaaDomainObj.getAaaDomainAuth();
		for(Aaadomainauth aaaDomainAuthObj : aaaDomainAuth){
			aaaDomainAuthObj.setAaaDomain(aaaDomainObj);
			addNew(aaaDomainAuthObj);
		}
		
	}
}
