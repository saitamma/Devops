package com.cisco.ca.cstg.pdi.controllers.equipment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.utils.Constants;

@Controller
@SessionAttributes("activeProjectId")
public class EquipmentController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EquipmentController.class);

	@RequestMapping(value = "/equipment.html")
	public String showEquipment(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			HttpServletRequest req, ModelMap map) {
		HttpSession session = req.getSession();
		boolean isMini = false;
		if (session.getAttribute("ISMINISERVERMODEL") != null) {
			isMini = (boolean) session.getAttribute("ISMINISERVERMODEL");
			LOGGER.info("Project Selected by user for : UCS MINI");
		}
		map.addAttribute("ISMINISERVERMODEL", isMini);

		return "equipment/equipmentMain";
	}

}