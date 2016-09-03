package com.cisco.ca.cstg.pdi.services.license;

import com.cisco.ca.cstg.pdi.pojos.License;

public interface LicenseKeyService {

	License setKey(String keyString);
	String getKey();
}
