package com.cisco.ca.cstg.pdi.utils;

import java.io.File;

import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;

public final class LicenseFileValidator {

	private volatile boolean fileStatusChecked = false;
	private volatile boolean fileExists = false;
	private static volatile LicenseFileValidator instance;

	private LicenseFileValidator() {
		updateFileStatus();
	}

	public static LicenseFileValidator getInstance() {
		if (instance == null) {
			synchronized (LicenseFileValidator.class) {
				instance = (instance == null) ? new LicenseFileValidator()
						: instance;
			}
		}
		if (!instance.fileStatusChecked) {
			synchronized (LicenseFileValidator.class) {
				instance.updateFileStatus();
			}
		}

		return instance;
	}

	public boolean licenseFileExists() {
		return fileExists;
	}

	public void resetFileStatus() {
		fileStatusChecked = false;
	}

	private synchronized void updateFileStatus() {

		File licenseFile = new File(
				PdiConfig.getProperty(Constants.PDI_HOME)
						+ Constants.LICENSE_FILENAME);
		fileExists = licenseFile.exists();
		fileStatusChecked = true;
	}

}
