package com.cisco.ca.cstg.pdi.controllers.login;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
public class LoginController implements Constants{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String showLogin() {
		return "login/login";
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public void showLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		try {
			HttpSession session = request.getSession(false);
			String logoutUrl = "";
			logoutUrl = Util.getLogoutUrl(request, logoutUrl);
			if (!logoutUrl.isEmpty()) {
				session.invalidate();
				response.sendRedirect(logoutUrl);
			}
		} catch (Exception e) {
			LOGGER.info("Exception in logout redirection. :", e);
		}

	}

}
