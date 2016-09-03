package com.cisco.ca.cstg.pdi.controllers.lan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LANController {

	@RequestMapping(value = "/lan.html")
	public String showLan() {
		return "lan/lanMain";
	}
}
