package com.cisco.ca.cstg.pdi.controllers.license;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.ca.cstg.pdi.services.ResetLicenseService;
import com.cisco.ca.cstg.pdi.utils.LicenseFileValidator;

@Controller
public class ResetLicenseController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ResetLicenseController.class);

	private final ResetLicenseService resetLicenseService;

	@Autowired
	public ResetLicenseController(ResetLicenseService resetLicenseService) {
		this.resetLicenseService = resetLicenseService;
	}

	@RequestMapping(value = "/resetLicense.html", method = RequestMethod.GET)
	public String resetLicense() {
		try {

			resetLicenseService.clearData();
			LicenseFileValidator.getInstance().resetFileStatus();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return "redirect:j_spring_security_logout";
	}
}
