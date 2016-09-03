package com.cisco.ca.cstg.pdi.controllers.san;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.services.SANService;
import com.cisco.ca.cstg.pdi.utils.Constants;

@Controller
@SessionAttributes("activeProjectId")
public class SANController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VsanController.class);
	private final SANService sanService;

	@Autowired
	public SANController(SANService sanService) {
		this.sanService = sanService;
	}

	@RequestMapping(value = "/san.html")
	public String showSan() {
		return "san/sanMain";
	}

	@RequestMapping(value = "/deleteAllSANConfigDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAllSANConfigDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {

		if (projectId != null) {

			try {
				this.sanService.deleteAllSANConfigurationDetails(projectId);

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			}
			return SUCCESS;
		}
		return FAILURE;
	}
}
