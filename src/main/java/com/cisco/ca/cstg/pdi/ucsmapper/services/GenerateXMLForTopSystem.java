package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.AdminAuthenticationDomain;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminSettings;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.Dns;
import com.cisco.ca.cstg.pdi.pojos.Ntp;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaAuthRealm;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaDomain;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaDomainAuth;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaLdapEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaLdapGroupRule;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaLdapProvider;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaProviderGroup;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaProviderRef;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaRadiusEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaRadiusProvider;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaTacacsPlusEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.AaaTacacsPlusProvider;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CommDateTime;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CommDns;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CommDnsProvider;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CommNtpProvider;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CommSvcEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.TopSystem;

@Service("generateXMLForTopSystem")
@SessionAttributes("activeProjectId")
public class GenerateXMLForTopSystem extends CommonDaoServicesImpl implements Constants {
	
	private ObjectFactory factory; 
	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateXMLForTopSystem.class);
	
	public GenerateXMLForTopSystem() {
		this.factory = new ObjectFactory();
	}

	public void addCommDateTime(CommDateTime commDateTime, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addCommDateTime: "+project);
		List<AdminSettings> commDateTimeList = project.getAdminSettingses();
		
		for(AdminSettings adminSettings : commDateTimeList){

			if( adminSettings.getTimeZone() != null){
				commDateTime.setTimezone(adminSettings.getTimeZone().getCountryTimeZone());
			}
			addCommNtpProvider(commDateTime, project);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addCommDateTime: "+project);
	}
	
	public void addCommNtpProvider(CommDateTime commDateTime, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addCommNtpProvider: "+project);
		List<Ntp> ntpList = project.getNtps();
		
		for(Ntp ntp : ntpList){
			String ipAdd = ntp.getIpAddress();
			CommNtpProvider commNtpProvider = factory.createCommNtpProvider();
			commNtpProvider.setDescr("");
			commNtpProvider.setName(ipAdd);
			
			JAXBElement<CommNtpProvider> commNtpProviderJE = factory.createCommNtpProvider(commNtpProvider);
			commDateTime.getContent().add(commNtpProviderJE);
			LOGGER.debug("Added Ntp - CommNtpProvider for {}", ipAdd);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addCommNtpProvider: "+project);
	}
	
	public void addCommDns(CommDns commDns, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addCommDns: "+project);
		List<Dns> dnsList = project.getDnses();
		
		for(Dns dns : dnsList){
			String ipAdd = dns.getIpAddress();
			addCommDnsProviderToCommDns(commDns, ipAdd);
			LOGGER.debug("Added Dns - CommDnsProvider for {}", ipAdd);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addCommDns: "+project);
	}

	private void addCommDnsProviderToCommDns(CommDns commDns, String ipAdd) {
		CommDnsProvider commDnsProvider = factory.createCommDnsProvider();
		commDnsProvider.setDescr("");
		commDnsProvider.setName(ipAdd);
		
		JAXBElement<CommDnsProvider> commDnsProviderJE = factory.createCommDnsProvider(commDnsProvider);
		commDns.getContent().add(commDnsProviderJE);
	}		

	@SuppressWarnings("rawtypes")
	public void addTimezoneDnsNtp(TopSystem topSystem, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addTimezoneDnsNtp: "+project);
		for( Serializable s: topSystem.getContent() ){
	    	if( !(s instanceof String )){
		        Object obj = ((JAXBElement)s).getValue();
		        if( obj instanceof CommSvcEp){
		        	CommSvcEp commSvcEp = (CommSvcEp)obj;
		        	for( Serializable s1: commSvcEp.getContent()){
		        		if( !(s1 instanceof String )){
		    		        Object obj1 = ((JAXBElement)s1).getValue();
		    		        if( obj1 instanceof CommDns){
		    		        	CommDns commDns = (CommDns)obj1;
		    		        	addCommDns(commDns,project);
		        			}else if( obj1 instanceof CommDateTime){
		        				CommDateTime commDateTime = (CommDateTime)obj1;
		        				addCommDateTime(commDateTime,project);
		        			}
		    		    }
		        	}
		        }
	        }
	    }
		LOGGER.info("End : GenerateXMLForTopSystem : addTimezoneDnsNtp: "+project);
	}
	
	public void addAdminAuthRealm(TopSystem topSystem, XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForTopSystem : addAdminAuthRealm: "+project);
		AaaAuthRealm aaaAuthRealm = factory.createAaaAuthRealm();
		aaaAuthRealm.setConLogin(LOCAL);
		aaaAuthRealm.setDefLogin(LOCAL);
		aaaAuthRealm.setDefRolePolicy("no-login");
		aaaAuthRealm.setDescr("");
		aaaAuthRealm.setName("");
		aaaAuthRealm.setPolicyOwner(LOCAL);
		
		List<AdminAuthenticationDomain> localDomainList = project.getAdminAuthenticationDomains();
		
		for(AdminAuthenticationDomain adminAuthenticationDomain : localDomainList) {
			String name = adminAuthenticationDomain.getName();
			AaaDomain aaaDomain = factory.createAaaDomain();
			
			JAXBElement<AaaDomain> aaaDomainLocalElement = factory.createAaaDomain(populateAdminAuthToAaaDoamin(adminAuthenticationDomain, name,aaaDomain));
			aaaAuthRealm.getContent().add(aaaDomainLocalElement);
			LOGGER.debug("Added AdminAuthenticationDomain - AaaDomain for {}", name);
		}
		JAXBElement<AaaAuthRealm> aaaAuthRealmElement = factory.createAaaAuthRealm(aaaAuthRealm);
		topSystem.getContent().add(aaaAuthRealmElement);
		LOGGER.info("End : GenerateXMLForTopSystem : addAdminAuthRealm: "+project);
	}

	
	public void addLdapEp(TopSystem topSystem, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addLdapEp: "+project);
		AaaLdapEp aaaLdapEp = factory.createAaaLdapEp();
		List<AdminLdapGeneral> adminLdapGeneralList = project.getAdminLdapGenerals();
		
		if(!Util.listNotNull(adminLdapGeneralList)){
			return;
		}
		AdminLdapGeneral ldapGeneral = adminLdapGeneralList.get(0);
		aaaLdapEp.setAttribute(ldapGeneral.getAttribute());
		aaaLdapEp.setBasedn(ldapGeneral.getBaseDn());
		aaaLdapEp.setFilter(ldapGeneral.getFilter());
		aaaLdapEp.setDescr("");
		aaaLdapEp.setName("");
		aaaLdapEp.setPolicyOwner(LOCAL);
		aaaLdapEp.setRetries("1");
		aaaLdapEp.setTimeout(ldapGeneral.getTimeout());
		
		addAaaLdapProvider(aaaLdapEp, project);
		addAaaProviderGroup(aaaLdapEp, project);
		
		JAXBElement<AaaLdapEp> aaaLdapEpElement = factory.createAaaLdapEp(aaaLdapEp);
		topSystem.getContent().add(aaaLdapEpElement);
		LOGGER.info("End : GenerateXMLForTopSystem : addLdapEp: "+project);
	}
	private void addAaaLdapProvider(AaaLdapEp aaaLdapEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addAaaLdapProvider: "+project);
		List<AdminLdapProvider> ldapProvidersList = project.getAdminLdapProviders();
		
		for (AdminLdapProvider ldapProvider : ldapProvidersList) {
			String name = ldapProvider.getHostname();
			AaaLdapProvider aaaLdapProvider = factory.createAaaLdapProvider();
			aaaLdapProvider.setAttribute(ldapProvider.getAttribute());
			aaaLdapProvider.setBasedn(ldapProvider.getBaseDn());
			aaaLdapProvider.setEnableSSL(ldapProvider.getEnableSsl().toString());
			aaaLdapProvider.setFilter(ldapProvider.getFilter());
			aaaLdapProvider.setDescr("");
			aaaLdapProvider.setEncKey("");
			aaaLdapProvider.setKey("");
			aaaLdapProvider.setName(name);
			aaaLdapProvider.setOrder(ldapProvider.getProviderOrder().toString());
			aaaLdapProvider.setPort(ldapProvider.getPort().toString());
			aaaLdapProvider.setRetries("1");
			aaaLdapProvider.setRootdn(ldapProvider.getBindDn());
			aaaLdapProvider.setTimeout(ldapProvider.getTimeout().toString());
			aaaLdapProvider.setVendor(ldapProvider.getVendor());
			
			AaaLdapGroupRule aaaLdapGroupRule = factory.createAaaLdapGroupRule();
			aaaLdapGroupRule.setAuthorization(ldapProvider.getGroupAuthorization());
			aaaLdapGroupRule.setDescr("");
			aaaLdapGroupRule.setName("");
			aaaLdapGroupRule.setTargetAttr(ldapProvider.getTargetAttribute());
			aaaLdapGroupRule.setTraversal(ldapProvider.getGroupRecursion());
			aaaLdapGroupRule.setUsePrimaryGroup(ldapProvider.getUsePrimaryGroup());
			
			JAXBElement<AaaLdapGroupRule> createAaaLdapGroupRule = factory.createAaaLdapGroupRule(aaaLdapGroupRule);
			aaaLdapProvider.getContent().add(createAaaLdapGroupRule);
			LOGGER.debug("Added AdminLdapProvider - AaaLdapGroupRule.");
			
			JAXBElement<AaaLdapProvider> createAaaLdapProvider = factory.createAaaLdapProvider(aaaLdapProvider);
			aaaLdapEp.getContent().add(createAaaLdapProvider);
			LOGGER.debug("Added AdminLdapProvider - AaaLdapProvider for {}", name);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addAaaLdapProvider: "+project);
	}
	
	private void addAaaProviderGroup(AaaLdapEp aaaLdapEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addAaaProviderGroup: "+project);
		List<AdminLdapProviderGroup> ldapProvidersGroupsList = project.getAdminLdapProviderGroups();
		
		for (AdminLdapProviderGroup ldapProvidersGroup : ldapProvidersGroupsList) {
			String name = ldapProvidersGroup.getName();
			AaaProviderGroup createAaaProviderGroup = factory.createAaaProviderGroup();
			createAaaProviderGroup.setDescr("");
			createAaaProviderGroup.setName(name);
			
			List<AdminLdapGroupProviderMapping> groupProvidersMappingList = ldapProvidersGroup.getAdminLdapGroupProviderMappings();
			for (AdminLdapGroupProviderMapping groupProviderMapping : groupProvidersMappingList) {
				AaaProviderRef createAaaProviderRef = factory.createAaaProviderRef();
				createAaaProviderRef.setName(groupProviderMapping.getAdminLdapProvider().getHostname());
				createAaaProviderRef.setOrder(groupProviderMapping.getProviderOrder().toString());
				
				JAXBElement<AaaProviderRef> aaaProviderRefElement = factory.createAaaProviderRef(createAaaProviderRef);
				createAaaProviderGroup.getContent().add(aaaProviderRefElement);
			}
			
			JAXBElement<AaaProviderGroup> aaaProviderGroupElement = factory.createAaaProviderGroup(createAaaProviderGroup);
			aaaLdapEp.getContent().add(aaaProviderGroupElement);
			LOGGER.debug("Added AdminLdapProviderGroup - AaaProviderGroup for {}", name);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addAaaProviderGroup: "+project);
	}
		
	public void addTacacsPlusEp(TopSystem topSystem, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addTacacsPlusEp: "+project);
		AaaTacacsPlusEp aaaTacacsPlusEp = factory.createAaaTacacsPlusEp();
		List<AdminTacacsGeneral> adminTacacsGeneralList = project.getAdminTacacsGenerals();
		
		if(!Util.listNotNull(adminTacacsGeneralList)){
			return;
		}
		AdminTacacsGeneral adminTacacsGeneral = adminTacacsGeneralList.get(0);
		aaaTacacsPlusEp.setDescr("");
		aaaTacacsPlusEp.setName("");
		aaaTacacsPlusEp.setPolicyOwner(LOCAL);
		aaaTacacsPlusEp.setRetries("1");
		aaaTacacsPlusEp.setTimeout(adminTacacsGeneral.getTimeout());
		
		addTacacsProvider(aaaTacacsPlusEp, project);
		addTacacsProviderGroup(aaaTacacsPlusEp, project);
		
		JAXBElement<AaaTacacsPlusEp> aaaTacacsEpElement = factory.createAaaTacacsPlusEp(aaaTacacsPlusEp);
		topSystem.getContent().add(aaaTacacsEpElement);
		LOGGER.info("End : GenerateXMLForTopSystem : addTacacsPlusEp: "+project);
	}
	
	private void addTacacsProvider(AaaTacacsPlusEp aaaTacacsPlusEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addTacacsProvider: "+project);
		List<AdminTacacsProvider> tacacsProvidersList = project.getAdminTacacsProviders();
				
		for (AdminTacacsProvider tacacsProvider : tacacsProvidersList) {
			String name = tacacsProvider.getHostname();
			AaaTacacsPlusProvider aaaTacacsPlusProvider = factory.createAaaTacacsPlusProvider();
			aaaTacacsPlusProvider.setDescr("");
			aaaTacacsPlusProvider.setEncKey("");
			aaaTacacsPlusProvider.setKey("");
			aaaTacacsPlusProvider.setName(name);
			aaaTacacsPlusProvider.setOrder(tacacsProvider.getProviderOrder().toString());
			aaaTacacsPlusProvider.setPort(tacacsProvider.getPort().toString());
			aaaTacacsPlusProvider.setRetries("1");
			aaaTacacsPlusProvider.setTimeout(tacacsProvider.getTimeout().toString());

			JAXBElement<AaaTacacsPlusProvider> createAaaTacacsProvider = factory.createAaaTacacsPlusProvider(aaaTacacsPlusProvider);
			aaaTacacsPlusEp.getContent().add(createAaaTacacsProvider);
			LOGGER.debug("Added AdminTacacsProvider - AaaTacacsPlusProvider for {}", name);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addTacacsProvider: "+project);
	}
	
	private void addTacacsProviderGroup(AaaTacacsPlusEp aaaTacacsPlusEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addTacacsProviderGroup: "+project);
		List<AdminTacacsProviderGroup> tacacsProvidersGroupsList = project.getAdminTacacsProviderGroups();
		
		for (AdminTacacsProviderGroup tacacsProvidersGroup : tacacsProvidersGroupsList) {
			String name = tacacsProvidersGroup.getName();
			LOGGER.debug("Adding AdminTacacsProviderGroup - AaaProviderGroup for {}", name);
			AaaProviderGroup createAaaProviderGroup = factory.createAaaProviderGroup();
			createAaaProviderGroup.setDescr("");
			createAaaProviderGroup.setName(name);
			
			List<AdminTacacsGroupProviderMapping> groupProvidersMappingList = tacacsProvidersGroup.getAdminTacacsGroupProviderMappings();
			
			for (AdminTacacsGroupProviderMapping groupProviderMapping : groupProvidersMappingList) {
				String providerName = groupProviderMapping.getAdminTacacsProvider().getHostname();
				AaaProviderRef createAaaProviderRef = factory.createAaaProviderRef();
				createAaaProviderRef.setName(providerName);
				createAaaProviderRef.setOrder(groupProviderMapping.getProviderOrder().toString());
				
				JAXBElement<AaaProviderRef> aaaProviderRefElement = factory.createAaaProviderRef(createAaaProviderRef);
				createAaaProviderGroup.getContent().add(aaaProviderRefElement);
				LOGGER.debug("Added AaaProviderRef for {}", providerName);
			}
			
			JAXBElement<AaaProviderGroup> aaaProviderGroupElement = factory.createAaaProviderGroup(createAaaProviderGroup);
			aaaTacacsPlusEp.getContent().add(aaaProviderGroupElement);
			LOGGER.debug("Completed AdminTacacsProviderGroup for {}", name);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addTacacsProviderGroup: "+project);
	}

	public void addRadiusEp(TopSystem topSystem, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addRadiusEp: "+project);
		AaaRadiusEp aaaradiusPlusEp = factory.createAaaRadiusEp();
		addRadiusGeneralSettings(aaaradiusPlusEp, project);
		addRadiusProvider(aaaradiusPlusEp, project);
		addRadiusProviderGroup(aaaradiusPlusEp, project);
		
		JAXBElement<AaaRadiusEp> aaaradiusPlusEpElement = factory.createAaaRadiusEp(aaaradiusPlusEp);
		topSystem.getContent().add(aaaradiusPlusEpElement);
		LOGGER.info("End : GenerateXMLForTopSystem : addRadiusEp: "+project);
	}
	
	private void addRadiusGeneralSettings(AaaRadiusEp aaaradiusPlusEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addRadiusGeneralSettings: "+project);
		List<AdminRadiusGeneral> adminRadiusGeneralList = project.getAdminRadiusGenerals();
		
		if(!Util.listNotNull(adminRadiusGeneralList)){
			return;
		}
		
		AdminRadiusGeneral radiusGeneral = adminRadiusGeneralList.get(0);
		aaaradiusPlusEp.setDescr("");
		aaaradiusPlusEp.setName("");
		aaaradiusPlusEp.setPolicyOwner(LOCAL);
		aaaradiusPlusEp.setRetries(radiusGeneral.getRetries()!=null?radiusGeneral.getRetries().toString():"");
		aaaradiusPlusEp.setTimeout(radiusGeneral.getTimeout()!=null?radiusGeneral.getTimeout().toString():"");
		LOGGER.info("End : GenerateXMLForTopSystem : addRadiusGeneralSettings: "+project);
	}
	
	private void addRadiusProvider(AaaRadiusEp aaaradiusPlusEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addRadiusProvider: "+project);
		List<AdminRadiusProvider> radiusProvidersList = project.getAdminRadiusProviders();
		
		for (AdminRadiusProvider radiusProvider : radiusProvidersList) {
			String name = radiusProvider.getHostname();
			AaaRadiusProvider aaaRadiusProvider = factory.createAaaRadiusProvider();
			aaaRadiusProvider.setDescr("");
			aaaRadiusProvider.setEncKey("");
			aaaRadiusProvider.setKey("");
			aaaRadiusProvider.setName(name);
			aaaRadiusProvider.setOrder(String.valueOf(radiusProvider.getRadiusOrder()));
			aaaRadiusProvider.setAuthPort(String.valueOf(radiusProvider.getAuthorizationPort()));
			aaaRadiusProvider.setRetries(String.valueOf(radiusProvider.getRetries()));
			aaaRadiusProvider.setTimeout(String.valueOf(radiusProvider.getTimeout()));

			JAXBElement<AaaRadiusProvider> aaaRadiusProviderElement = factory.createAaaRadiusProvider(aaaRadiusProvider);
			aaaradiusPlusEp.getContent().add(aaaRadiusProviderElement);
			LOGGER.debug("Added AdminRadiusProvider - AaaRadiusProvider for {}", name);
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addRadiusProvider: "+project);
	}
	
	private void addRadiusProviderGroup(AaaRadiusEp aaaradiusPlusEp, XmlGenProjectDetails project){
		LOGGER.info("Start : GenerateXMLForTopSystem : addRadiusProviderGroup: "+project);
		List<AdminRadiusProviderGroup> radiusProvidersGroupsList = project.getAdminRadiusProviderGroups();
		
		for (AdminRadiusProviderGroup radiusProvidersGroup : radiusProvidersGroupsList) {
			String name = radiusProvidersGroup.getName();
			LOGGER.debug("Adding AdminRadiusProviderGroup - AaaProviderGroup for {}", name);
			
			AaaProviderGroup createAaaProviderGroup = factory.createAaaProviderGroup();
			createAaaProviderGroup.setDescr("");
			createAaaProviderGroup.setName(name);
			
			List<AdminRadiusGroupProviderMapping> groupProvidersMappingList = radiusProvidersGroup.getAdminRadiusGroupProviderMappings();
			
			for (AdminRadiusGroupProviderMapping groupProviderMapping : groupProvidersMappingList) {
				String providerName = groupProviderMapping.getAdminRadiusProvider().getHostname();
				AaaProviderRef createAaaProviderRef = factory.createAaaProviderRef();
				createAaaProviderRef.setName(providerName);
				createAaaProviderRef.setOrder(groupProviderMapping.getRadiusProviderOrder().toString());
				
				JAXBElement<AaaProviderRef> aaaProviderRefElement = factory.createAaaProviderRef(createAaaProviderRef);
				createAaaProviderGroup.getContent().add(aaaProviderRefElement);
				LOGGER.debug("Added RadiusProvider {}", providerName);
			}
			
			JAXBElement<AaaProviderGroup> aaaProviderGroupElement = factory.createAaaProviderGroup(createAaaProviderGroup);
			aaaradiusPlusEp.getContent().add(aaaProviderGroupElement);
			LOGGER.debug("Completed AdminRadiusProviderGroup");
		}
		LOGGER.info("End : GenerateXMLForTopSystem : addRadiusProviderGroup: "+project);
	}
	
	private AaaDomain populateAdminAuthToAaaDoamin(
			AdminAuthenticationDomain adminAuthenticationDomain, String name,
			AaaDomain aaaDomain) {
		aaaDomain.setDescr("");
		aaaDomain.setName(name);
		aaaDomain.setRefreshPeriod(adminAuthenticationDomain.getRefreshPeriod().toString());
		aaaDomain.setSessionTimeout(adminAuthenticationDomain.getSessionTimeout().toString());
		
		AaaDomainAuth aaaDomainAuth = factory.createAaaDomainAuth();
		aaaDomainAuth.setDescr("");
		aaaDomainAuth.setName("");
		aaaDomainAuth.setRealm(adminAuthenticationDomain.getRealm().toLowerCase());
		
		if ("Ldap".equals(adminAuthenticationDomain.getRealm()) && adminAuthenticationDomain.getAdminLdapProviderGroup() != null) {
			aaaDomainAuth.setProviderGroup(adminAuthenticationDomain.getAdminLdapProviderGroup().getName());
		} else if ("Radius".equals(adminAuthenticationDomain.getRealm()) && adminAuthenticationDomain.getAdminRadiusProviderGroup() !=null) {
			aaaDomainAuth.setProviderGroup(adminAuthenticationDomain.getAdminRadiusProviderGroup().getName());
		} else if ("Tacacs".equals(adminAuthenticationDomain.getRealm()) && adminAuthenticationDomain.getAdminTacacsProviderGroup() !=null) {
			aaaDomainAuth.setProviderGroup(adminAuthenticationDomain.getAdminTacacsProviderGroup().getName());
		} else {
			aaaDomainAuth.setProviderGroup("");
		}
		
		aaaDomainAuth.setUse2Factor(adminAuthenticationDomain.getTwoFactor());
		JAXBElement<AaaDomainAuth> aaaDomainAuthLocalElement = factory.createAaaDomainAuth(aaaDomainAuth);
		aaaDomain.getContent().add(aaaDomainAuthLocalElement);
		return aaaDomain;
	}
}