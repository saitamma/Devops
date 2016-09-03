package com.cisco.ca.cstg.pdi.services;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.License;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;

@Service
public class ResetLicenseServiceImpl extends CommonDaoServicesImpl implements ResetLicenseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResetLicenseServiceImpl.class);
	private final CommonUtilServices commonUtilService;
	
	@Autowired
	public ResetLicenseServiceImpl(SessionFactory hibernateSessionFactory,CommonUtilServices commonUtilService) {
		setSessionFactory(hibernateSessionFactory);
		this.commonUtilService = commonUtilService;
	}
	
	@Override
	public void clearData() {
		commonUtilService.deleteRecords(ProjectDetails.class);
		commonUtilService.deleteRecords(License.class);
		commonUtilService.deleteRecords(UcsServerLogs.class);
		commonUtilService.deleteRecords(Toproot.class);
		this.deleteLicenseFile();
	}

	private boolean deleteLicenseFile(){
		FileUtils.deleteQuietly(new File(
				PdiConfig.getProperty(Constants.PDI_HOME)
				+ Constants.LICENSE_FILENAME));
		LOGGER.info("License File is being deleted");
		return true;
	}

}
