package com.cisco.ca.cstg.pdi.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;

public class SessionTimeoutFilter extends GenericFilterBean implements
		Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SessionTimeoutFilter.class);

	private static final String SKIP_PROJ_ROOT_REQUEST = "listProjects.html";
	private static final String LOCAL_URL = "http://localhost:8080/";
	private static final String PINGPONG = "pingpong.html";
	private static final List<String> SKIP_REQUEST_LIST;

	static {
		SKIP_REQUEST_LIST = new ArrayList<>();
		SKIP_REQUEST_LIST.add("login.html");
		SKIP_REQUEST_LIST.add("license.html");
		SKIP_REQUEST_LIST.add("j_spring_security_check");
		SKIP_REQUEST_LIST.add("j_spring_security_logout");
		SKIP_REQUEST_LIST.add("listProjects.html");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		try {
			LOGGER.info("calling doFilter filter");
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			HttpSession session = request.getSession(false);
			String requestURL = "";
			String requestURI = "";
			String logoutUrl = "";
			if (null != request.getRequestURL()) {
				requestURL = request.getRequestURL().toString();
			}
			LOGGER.info("Current url: " + requestURL + ": URI: " + requestURI
					+ " Session: " + session);
			LOGGER.info("Request URI : " + requestURI);
			if (request.getRequestURL() != null
					&& !(request.getRequestURL().toString()
							.contains(PINGPONG))
					&& !(LOCAL_URL.equals(request
							.getRequestURL().toString().trim()))) {
				if ((session == null || session.getAttribute("userCec") == null)
						&& (!(requestURL.contains(SKIP_PROJ_ROOT_REQUEST)
								|| requestURI == null || requestURI.isEmpty()))) {
					LOGGER.info("Session timeout");
					LOGGER.info("geting X-Requested-With value : "
							+ request.getHeader("X-Requested-With"));
					if ("XMLHttpRequest".equals(request
							.getHeader("X-Requested-With"))) {
						LOGGER.info("Adding 403 error code for ajax call to redirect from client side");
						response.sendError(403, "SESSION_TIMEOUT");
					} else {
						LOGGER.info("Identifying the URL for redirecting from server side after session is expired..");
						if (requestURL.toLowerCase().contains(CONTAINDEV)) {
							logoutUrl = PdiConfig.getProperty("pdi.devLogout");
							response.setHeader("Access-Control-Allow-Origin",
									"*");
						} else if (requestURL.toLowerCase()
								.contains(CONTAINSTG)) {
							logoutUrl = PdiConfig.getProperty("pdi.stgLogout");
						} else if (requestURL.toLowerCase().contains(
								CONTAINPROD)) {
							logoutUrl = PdiConfig.getProperty("pdi.prodLogout");
						} else {
							LOGGER.info("Local logout");
							logoutUrl = "j_spring_security_logout";
						}
						if (!logoutUrl.isEmpty()) {
							response.sendRedirect(logoutUrl);
						}
					}
				} else {
					LOGGER.info("Ready for calling to dispatcher servlet...");
					chain.doFilter(request, response);
				}
			}
		} catch (IOException e) {
			LOGGER.info(
					"there is some problem in SessionLogoutListener to redirect.",
					e);
		} catch (Exception e) {
			LOGGER.info(
					"there is some problem in SessionLogoutListener to redirect.",
					e);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
