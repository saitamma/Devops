package com.cisco.ca.cstg.pdi.controllers.servers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServersController {
	
	@RequestMapping(value = "/servers.html")
	public String showServers() {
		return "servers/serversMain";
	}
}
