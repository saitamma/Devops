package com.cisco.ca.cstg.pdi.services.license;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.exceptions.LicenseParsingException;
import com.cisco.ca.cstg.pdi.pojos.License;
import com.cisco.ca.cstg.pdi.utils.Constants;

@Service("licenseKeyService")
public class LicenseKeyServiceImpl extends CommonDaoServicesImpl implements
		LicenseKeyService {
	private LicenseCryptoService licenseCryptoService;
	private static final Logger LOGGER = LoggerFactory.getLogger(LicenseKeyServiceImpl.class);

	@Autowired
	public LicenseKeyServiceImpl(SessionFactory hibernateSessionFactory,
			LicenseCryptoService licenseCryptoService) {
		setSessionFactory(hibernateSessionFactory);
		this.licenseCryptoService = licenseCryptoService;
	}

	public License setKey(String key)
			throws LicenseParsingException {
		License license = null;
		String licenseValues = null;
		String licenseParseMessageString = "Invalid license file";

		try {
			licenseValues = licenseCryptoService.decrypt(key);
		} catch (NoSuchAlgorithmException nsae) {
			LOGGER.info("NoSuchAlgorithmException exception in setKey method.",nsae);
		} catch (BadPaddingException bpe) {
			LOGGER.info("BadPaddingException exception in setKey method.",bpe);
		} catch (IllegalBlockSizeException ibse) {
			LOGGER.info("IllegalBlockSizeException exception in setKey method.",ibse);
		} catch (InvalidKeyException ike) {
			LOGGER.info("InvalidKeyException exception in setKey method.",ike);
		} catch (NoSuchPaddingException nspe) {
			LOGGER.info("NoSuchPaddingException exception in setKey method.",nspe);
		} catch (NumberFormatException nfe) {
			LOGGER.info("NumberFormatException exception in setKey method.",nfe);
		}

		if (licenseValues != null) {
			license = (License) findById(License.class, 1);
			if (license == null) {
				license = new License();
			}

			String licenseValueString = licenseValues;
			StringTokenizer licenseValueTokenizer = new StringTokenizer(
					licenseValueString, ",");

			Map<String, String> licenseValuesMap = new HashMap<>();
			while (licenseValueTokenizer.hasMoreElements()) {
				String token = licenseValueTokenizer.nextToken();
				String[] tokenValues = token.split(":");
				if (tokenValues.length == 2) {
					licenseValuesMap.put(tokenValues[0], tokenValues[1]);
				}
			}

			if (licenseValuesMap
					.containsKey(Constants.LICENSE_KEY_PROJECT_NAME)) {
				license.setName(licenseValuesMap
						.get(Constants.LICENSE_KEY_PROJECT_NAME));
			}

			if (licenseValuesMap
					.containsKey(Constants.LICENSE_KEY_CUSTOMER_NAME)) {
				license.setCustomerName(licenseValuesMap
						.get(Constants.LICENSE_KEY_CUSTOMER_NAME));
			}

			if (licenseValuesMap
					.containsKey(Constants.LICENSE_KEY_CUSTOMER_DESCRIPTION)) {
				license.setDescription(licenseValuesMap
						.get(Constants.LICENSE_KEY_CUSTOMER_DESCRIPTION));

			}

			if (licenseValuesMap
					.containsKey(Constants.LICENSE_KEY_CUSTOMER_BUSINESS)) {
				license.setCustomerBusiness(licenseValuesMap
						.get(Constants.LICENSE_KEY_CUSTOMER_BUSINESS));
			}

			if (licenseValuesMap.containsKey(Constants.LICENSE_KEY_THEATRE)) {
				license.setTheatre(licenseValuesMap
						.get(Constants.LICENSE_KEY_THEATRE));
			}

			if (licenseValuesMap
					.containsKey(Constants.LICENSE_KEY_CUSTOMER_VERTICALS)) {
				license.setCustomerVertical(licenseValuesMap
						.get(Constants.LICENSE_KEY_CUSTOMER_VERTICALS));
			}
			if (licenseValuesMap.containsKey(Constants.LICENSE_KEY_ASPID)) {
				license.setAsPid(licenseValuesMap
						.get(Constants.LICENSE_KEY_ASPID));
			}

			if (licenseValuesMap.containsKey(Constants.LICENSE_KEY_SITEID)) {
				license.setSiteId(licenseValuesMap
						.get(Constants.LICENSE_KEY_SITEID));
			}

			if (licenseValuesMap.containsKey(Constants.LICENSE_KEY_CREATED_BY)) {
				license.setCreatedby(licenseValuesMap
						.get(Constants.LICENSE_KEY_CREATED_BY));
			}
			
			if (licenseValuesMap.containsKey(Constants.LICENSE_KEY_SITENAME)) {
				license.setSite(licenseValuesMap
						.get(Constants.LICENSE_KEY_SITENAME));
			}

			String serviceTypeValue = null;
			if (licenseValuesMap
					.containsKey(Constants.LICENSE_KEY_SERVICE_TYPE)) {
				serviceTypeValue = licenseValuesMap
						.get(Constants.LICENSE_KEY_SERVICE_TYPE);
			}

			if (serviceTypeValue != null
					&& serviceTypeValue.matches("UCSPDI")) {
				license.setServiceType(serviceTypeValue);
				saveOrUpdate(license);
			} else {
				license = null;
				licenseParseMessageString = "The License file is invalid. Kindly upload the valid License file.";
			}
		}

		if (license == null) {
			throw new LicenseParsingException(licenseParseMessageString);
		}
		return license;
	}

	public String getKey() {
		License license = null;
		license = (License) findById(License.class, 1);
		if (null == license) {
			license = new License();
		}
		String serviceType = "";
		serviceType = license.getServiceType();

		return serviceType;
	}
}
