package com.cisco.ca.cstg.pdi.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping(value = "/adminMain.html")
	public String showAdmin() {
		return "admin/adminMain";
	}
}
