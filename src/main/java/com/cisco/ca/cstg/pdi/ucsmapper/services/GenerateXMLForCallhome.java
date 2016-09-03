package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeAlertGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomePolicy;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfile;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileAlertGroupMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeProfileRecipientMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminCallhomeSystemInventory;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeDest;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomePeriodicSystemInventory;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomePolicy;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeProfile;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeSmtp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeSource;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.CallhomeTestAlert;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;

@Service("generateXMLForCallhome")
public class GenerateXMLForCallhome extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenerateXMLForCallhome.class);
	private static final String NO = "no";
	private static final String UNKNOWN = "unknown";

	private ObjectFactory factory;

	public GenerateXMLForCallhome() {
		this.factory = new ObjectFactory();
	}

	public void updateCallhomeEpProperties(CallhomeEp callhomeEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForCallhome : updateCallhomeEpProperties : "
				+ project);
		List<AdminCallhomeGeneral> callhomeGeneralList = project
				.getAdminCallhomeGenerals();

		if (!callhomeGeneralList.isEmpty()) {
			AdminCallhomeGeneral callHome = callhomeGeneralList.get(0);
			callhomeEp.setAdminState(callHome.getState());
			callhomeEp.setAlertThrottlingAdminState(callHome.getThrottling());
		}
		LOGGER.info("End : GenerateXMLForCallhome : updateCallhomeEpProperties : "
				+ project);
	}

	public void addCallhomePolicies(CallhomeEp callhomeEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForCallhome : addCallhomePolicies : "
				+ project);
		List<AdminCallhomePolicy> policiesList = project
				.getAdminCallhomePolicies();

		for (AdminCallhomePolicy callhomePolicy : policiesList) {
			CallhomePolicy policy = factory.createCallhomePolicy();
			policy.setAdminState(callhomePolicy.getState());
			policy.setCause(callhomePolicy.getCause());
			policy.setName("");
			policy.setDescr("");
			JAXBElement<CallhomePolicy> jaxbCallhomePolicy = factory
					.createCallhomePolicy(policy);
			callhomeEp.getContent().add(jaxbCallhomePolicy);
		}
		LOGGER.info("End : GenerateXMLForCallhome : addCallhomePolicies : "
				+ project);
	}

	public void addCallhomeProfiles(CallhomeEp callhomeEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForCallhome : addCallhomeProfiles : "
				+ project);
		StringBuilder allAlertGroups = null;
		List<AdminCallhomeProfile> profilesList = project
				.getAdminCallhomeProfiles();

		for (AdminCallhomeProfile callhomeProfile : profilesList) {
			allAlertGroups = new StringBuilder();
			CallhomeProfile profile = factory.createCallhomeProfile();
			profile.setName(callhomeProfile.getName());
			profile.setMaxSize(String.valueOf(callhomeProfile.getMaxMsgSize()));
			profile.setLevel(callhomeProfile.getLevel());
			profile.setFormat(callhomeProfile.getFormat());
			profile.setDescr(callhomeProfile.getDescription());
			List<AdminCallhomeProfileAlertGroupMapping> alertgrpsList = callhomeProfile
					.getAdminCallhomeProfileAlertGroupMappings();

			populateAlertGroups(allAlertGroups, alertgrpsList);

			profile.setAlertGroups(allAlertGroups.length() == 0 ? ""
					: allAlertGroups.toString());

			addRecipientsToEachProfile(callhomeProfile, profile);

			JAXBElement<CallhomeProfile> jaxbCallhomeProfile = factory
					.createCallhomeProfile(profile);
			callhomeEp.getContent().add(jaxbCallhomeProfile);
		}
		LOGGER.info("End : GenerateXMLForCallhome : addCallhomeProfiles : "
				+ project);
	}

	private void addRecipientsToEachProfile(
			AdminCallhomeProfile callhomeProfile, CallhomeProfile profile) {
		List<AdminCallhomeProfileRecipientMapping> recipientsList = callhomeProfile
				.getAdminCallhomeProfileRecipientMappings();

		for (AdminCallhomeProfileRecipientMapping recipient : recipientsList) {
			CallhomeDest callhomeDest = factory.createCallhomeDest();
			callhomeDest.setEmail(recipient.getEmail());

			JAXBElement<CallhomeDest> jaxbCallhomeDest = factory
					.createCallhomeDest(callhomeDest);
			profile.getContent().add(jaxbCallhomeDest);
		}
	}

	private void populateAlertGroups(StringBuilder allAlertGroups,
			List<AdminCallhomeProfileAlertGroupMapping> alertgrpsList) {
		if (alertgrpsList != null && !alertgrpsList.isEmpty()) {
			for (AdminCallhomeProfileAlertGroupMapping mapping : alertgrpsList) {
				if (mapping.getAdminCallhomeAlertGroup() != null) {
					AdminCallhomeAlertGroup alerGrpObj = mapping
							.getAdminCallhomeAlertGroup();

					if (allAlertGroups.length() == 0) {
						if (alertgrpsList.size() >= 3) {
							allAlertGroups.append("all,");
						}
						allAlertGroups.append(alerGrpObj.getXmlValue());
					} else {
						allAlertGroups.append(",");
						allAlertGroups.append(alerGrpObj.getXmlValue());
					}
				}
			}
		}
	}

	public void addCallhomeSystemInventory(CallhomeEp callhomeEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForCallhome : addCallhomeSystemInventory : "
				+ project);
		List<AdminCallhomeSystemInventory> systemInventory = project
				.getAdminCallhomeSystemInventories();
		for (AdminCallhomeSystemInventory callhomeSystemInventory : systemInventory) {
			CallhomePeriodicSystemInventory sysInventory = factory
					.createCallhomePeriodicSystemInventory();
			sysInventory.setAdminState(callhomeSystemInventory
					.getSendPeriodically());
			sysInventory.setIntervalDays(String.valueOf(callhomeSystemInventory
					.getSendIntervalDays()));
			sysInventory.setMaximumRetryCount("0");
			sysInventory.setMinimumSendNowIntervalSeconds("5");
			sysInventory.setPollIntervalSeconds("300");
			sysInventory.setRetryDelayMinutes("10");
			sysInventory.setSendNow(NO);
			sysInventory.setTimeOfDayHour(String
					.valueOf(callhomeSystemInventory.getSendIntervalHours()));
			sysInventory.setTimeOfDayMinute(String
					.valueOf(callhomeSystemInventory.getSendIntervalMinutes()));

			JAXBElement<CallhomePeriodicSystemInventory> jaxbCallhomePeriodicSystemInventory = factory
					.createCallhomePeriodicSystemInventory(sysInventory);
			callhomeEp.getContent().add(jaxbCallhomePeriodicSystemInventory);
		}
		LOGGER.info("End : GenerateXMLForCallhome : addCallhomeSystemInventory : "
				+ project);
	}

	public void addCallhomeTestAlert(CallhomeEp callhomeEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForCallhome : addCallhomeTestAlert : "
				+ project);
		CallhomeTestAlert alert = factory.createCallhomeTestAlert();
		alert.setDescription("");
		alert.setGroup(UNKNOWN);
		alert.setLevel(UNKNOWN);
		alert.setMessageSubtype(UNKNOWN);
		alert.setMessageType(UNKNOWN);
		alert.setSendNow(NO);

		JAXBElement<CallhomeTestAlert> jaxbCallhomeTestAlert = factory
				.createCallhomeTestAlert(alert);
		callhomeEp.getContent().add(jaxbCallhomeTestAlert);

		LOGGER.info("End : GenerateXMLForCallhome : addCallhomeTestAlert : "
				+ project);
	}

	public void addCallhomeSourceAndSmtp(CallhomeEp callhomeEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForCallhome : addCallhomeSourceAndSmtp : "
				+ project);
		List<AdminCallhomeGeneral> callhomeGeneralList = project
				.getAdminCallhomeGenerals();

		for (AdminCallhomeGeneral callhomeGeneral : callhomeGeneralList) {

			CallhomeSource callhomeSource = factory.createCallhomeSource();
			callhomeSource.setAddr(callhomeGeneral.getAddress());
			callhomeSource.setContact(callhomeGeneral.getContact());
			callhomeSource.setContract(callhomeGeneral.getContractId());
			callhomeSource.setCustomer(callhomeGeneral.getCustomerId());
			callhomeSource.setEmail(callhomeGeneral.getEmail());
			callhomeSource.setFrom(callhomeGeneral.getEmailFrom());
			callhomeSource.setPhone(callhomeGeneral.getPhone());
			callhomeSource.setReplyTo(callhomeGeneral.getReplyTo());
			callhomeSource.setSite(callhomeGeneral.getSiteId());
			callhomeSource.setUrgency(callhomeGeneral.getSwitchPriority());
			JAXBElement<CallhomeSource> jaxbCallhomeSource = factory
					.createCallhomeSource(callhomeSource);
			callhomeEp.getContent().add(jaxbCallhomeSource);

			CallhomeSmtp callhomeSmtp = factory.createCallhomeSmtp();
			callhomeSmtp.setHost(callhomeGeneral.getHost());
			callhomeSmtp.setPort(String.valueOf(callhomeGeneral.getPort()));
			JAXBElement<CallhomeSmtp> jaxbCallhomeSmtp = factory
					.createCallhomeSmtp(callhomeSmtp);
			callhomeEp.getContent().add(jaxbCallhomeSmtp);

		}
		LOGGER.info("End : GenerateXMLForCallhome : addCallhomeSourceAndSmtp : "
				+ project);
	}
}
