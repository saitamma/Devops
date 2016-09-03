package com.cisco.ca.cstg.pdi.services.license;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.EnumLicenseKeyConstants;
import com.cisco.ca.cstg.pdi.pojos.License;
import com.cisco.ca.cstg.pdi.utils.AssessmentKey;
import com.cisco.ca.cstg.pdi.utils.Constants;

@Service("assessmentKeyService")
public class AssessmentKeyService extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentKeyService.class);
	private AssessmentKey key;
	private boolean isKeyActive;
	private Map<String, String> mappedParseInfo = null;

	@Autowired
	public AssessmentKeyService(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	public AssessmentKey getKey() {
		return key;
	}

	public void setKey(AssessmentKey key) {

		LOGGER.info("storing key in the KeyStore");
		this.key = key;
		// Find and update the project details record with metadata from the key
		License license = (License) findById(License.class, 1);
		if (null == license) {
			license = new License();
		}

		extractFromKey(key, license);

		if ((license.getAsPid() != null) && (!"".equals(license.getAsPid()))) {

			saveOrUpdate(license);
			isKeyActive = true;
		} else {
			LOGGER.info("ASPID:[" + license.getAsPid()
					+ "] in the system. License is inactive");

			license.clearProjectDetails();
			isKeyActive = false;
		}
	}

	public boolean isKeyActivated() {

		License license = (License) findById(License.class, 1);
		// Find project details record and see if there is an active project
		// (ASPID) in the system

		if ((license != null) && (license.getAsPid() != null)
				&& (!"".equals(license.getAsPid()))) {
			LOGGER.info("Found Active ASPID:[" + license.getAsPid()
					+ "] in the system. License is active");
			isKeyActive = true;
		} else {
			LOGGER.info("License is inactive");
			isKeyActive = false;
		}
		return isKeyActive;
	}

	/**
	 * Returns true if License is valid, otherwise false (Dataset upload is
	 * optional; upload if dataset exists, otherwise not)
	 **/

	public boolean sendEntitlementFiles() {
		return true;
	}

	public boolean isLicenseValid(byte[] licenseFileInBytes) {
		if (null == licenseFileInBytes || licenseFileInBytes.length <= 0) {
			return false;
		}

		String keyString = new String(licenseFileInBytes,
				Charset.forName(Constants.UTF8));
		if (null == keyString || keyString.isEmpty()) {
			return false;
		}

		AssessmentKey asskey = new AssessmentKey();
		asskey.setAssessmentKey(keyString);
		try {
			asskey.decrypt();
			boolean isValid = asskey.validate();
			if (!isValid) {
				return false;
			}
		} catch (Exception e) {
			LOGGER.info("exception in isLicenseValid method.",e);
			return false;
		}
		return true;
	}

	public boolean saveLicenseDetails(byte[] licenseFileInBytes) {
		AssessmentKey asskey = new AssessmentKey();
		String keyString = new String(licenseFileInBytes,
				Charset.forName(Constants.UTF8));
		asskey.setAssessmentKey(keyString);
		setKey(asskey);
		return true;
	}

	public List<List<String>> getUrlMapping() {
		List<List<String>> mainList = new ArrayList<List<String>>();
		String[] urlArray = { "credentialsProfile.list", "server.list",
				"discoveryDataset.list", "discoveryProfile.list",
				"scheduler.list", "devices.list", "davProfile.list",
				"scheduler.list", "dav.list", "collectionTemplate.list",
				"collectionProfile.list", "scheduler.list",
				"collectConfig.details" };
		String[] titleArray = { "Credential Profile", "Asset Management",
				"Network IP range", "Discovery Profile", "Job Management",
				"Manage Devices", "DAV Profile", "Job Management",
				"DAV results", "Collection Template", "Collection Profile",
				"Job Management", "Config Viewer" };
		List<String> urlList = new ArrayList<String>();

		for (int i = 1; i <= urlArray.length; i++) {
			urlList.add(i + "");
			urlList.add(urlArray[i - 1]);
			urlList.add(titleArray[i - 1]);
			mainList.add(urlList);
			urlList = new ArrayList<String>();
		}
		return mainList;
	}
	
	private void extractFromKey(AssessmentKey key, License license) {
		try {
			key.decrypt();

			LOGGER.info("Calling extractFromKey: key=[" + key + "] metadata=[" + key.getMetaData() + "]");
			// extract the fields
			parseInfo(key.getMetaData());
			// populate the values
			license.setName(getParseValue(EnumLicenseKeyConstants.PROJECTNAME));
			license.setDescription("");
			license.setAssessorId("");
			license.setAssessorName("");
			license.setAssessorType("");
			license.setPurpose("");
			license.setTheatre(getParseValue(EnumLicenseKeyConstants.THEATRE));
			license.setCustomerName(getParseValue(EnumLicenseKeyConstants.CUSTOMERNAME));
			license.setCustomerDescription("");
			license.setCustomerBusiness(getParseValue(EnumLicenseKeyConstants.CUSTOMERBUSINESS));
			license.setCustomerVertical(getParseValue(EnumLicenseKeyConstants.CUSTOMERVERTICALS));
			license.setAsPid(getParseValue(EnumLicenseKeyConstants.ASPID));
			license.setSite(getParseValue(EnumLicenseKeyConstants.SITENAME));
			license.setCreatedby(getParseValue(EnumLicenseKeyConstants.CREATEDBY));
			license.setDownloadedby(getParseValue(EnumLicenseKeyConstants.DOWNLOADEDBY));
			license.setSiteId(getParseValue(EnumLicenseKeyConstants.SITEID));

		} catch (Exception e) {
			LOGGER.error("Error occured: " + e.getMessage(), e);
		}
	}
	
	private void parseInfo(String text) {
		mappedParseInfo = new HashMap<String, String>();
		for (String keyValue : text.split(":")) {
			String[] pairs = keyValue.split("~", 2);
			mappedParseInfo.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
		}
	}
	
	private String getParseValue(EnumLicenseKeyConstants enumcontant) {
		return mappedParseInfo.get(enumcontant.toString());

	}
}
