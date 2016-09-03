package com.cisco.ca.cstg.pdi.controllers.license;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.ca.cstg.pdi.exceptions.LicenseParsingException;
import com.cisco.ca.cstg.pdi.pojos.License;
import com.cisco.ca.cstg.pdi.services.license.LicenseKeyService;
import com.cisco.ca.cstg.pdi.services.login.PDILoginService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.LicenseFileValidator;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
public class LicenseController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LicenseController.class);

	private final LicenseKeyService licenseKeyService;
	private final PDILoginService pdiLoginService;

	@Autowired
	public LicenseController(LicenseKeyService licenseKeyService,
			PDILoginService pdiLoginService) {
		this.licenseKeyService = licenseKeyService;
		this.pdiLoginService = pdiLoginService;
	}

	@RequestMapping(value = "/license.html", method = RequestMethod.GET)
	public String uploadLicense() {
		return "license/license";
	}

	@RequestMapping(value = "/licenseUpload.html", method = RequestMethod.POST)
	public ModelAndView licenseUpload(
			@RequestParam("upload_license") MultipartFile file) {
		ModelAndView modelAndView = null;

		if (file == null) {
			modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
					KEY_ERROR_MESSAGE, "The file is null.");
		} else {

			String fileName = file.getOriginalFilename();
			LOGGER.info("File Name is " + fileName);
			if ((fileName == null) || (fileName.trim().length() <= 0)) {
				modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
						KEY_ERROR_MESSAGE,
						"No File Uploaded. Please select a file and upload.");
			} else if (!fileName.toLowerCase().matches(".*\\.lic$|.*\\.txt$")) {
				modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
						KEY_ERROR_MESSAGE,
						"The uploaded file is not an text file ending with .lic or .txt extension.");
			} else if (file.isEmpty()) {
				modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
						KEY_ERROR_MESSAGE,
						"The uploaded file does not contain any data.");
			} else {

				try {
					byte[] fileContent = file.getBytes();

					String key = new String(fileContent,
							Charset.defaultCharset());

					licenseKeyService.setKey(key);

					try {
						Util.writeFile(
								PdiConfig.getProperty(Constants.PDI_HOME),
								fileContent, Constants.LICENSE_FILENAME);
						LicenseFileValidator.getInstance().resetFileStatus();
					} catch (FileNotFoundException fnfe) {
						LOGGER.error("Could not locate file.", fnfe);
					} catch (IOException e) {
						LOGGER.error("Error writing file.", e);
					}

					modelAndView = new ModelAndView(
							"redirect: listProjects.html");

				} catch (LicenseParsingException lpe) {
					LOGGER.error("Exception: ", lpe);
					modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
							KEY_ERROR_MESSAGE, lpe.getMessage() + ".");
				} catch (IOException ioe) {
					LOGGER.error("Exception: ", ioe);
					modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
							KEY_ERROR_MESSAGE,
							"Error reading uploaded file. Please try upload again.");
				} catch (Exception exception) {
					LOGGER.error("Exception: ", exception);
					modelAndView = new ModelAndView(RESPONSE_LICENSE_UPLOAD,
							KEY_ERROR_MESSAGE,
							"Unknown error occurred. Please try upload again.");
				}
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/getLicenseDetails.html", method = RequestMethod.POST)
	@ResponseBody
	public String getLicenseDetails() {
		License license = (License) pdiLoginService.findById(License.class, 1);

		Map<String, Object> myMap = new LinkedHashMap<String, Object>();

		if (license != null) {
			myMap.put(CAM_NAME, StringUtils.defaultString(license.getName()));
			myMap.put(CAM_THEATRE,
					StringUtils.defaultString(license.getTheatre()));
			myMap.put(CUSTOMER_NAME,
					StringUtils.defaultString(license.getCustomerName()));
			myMap.put(CAM_SITE, StringUtils.defaultString(license.getSite()));
			myMap.put(AS_PDI, StringUtils.defaultString(license.getAsPid()));
			myMap.put(CREATED_BY,
					StringUtils.defaultString(license.getCreatedby()));
		}
		return Util.convertMapToJson(myMap);
	}
}