package com.cisco.ca.cstg.pdi.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.ca.cstg.pdi.utils.LicenseFileValidator;

public class LicensePresenceInterceptor implements HandlerInterceptor {

	private static final List<String> SKIP_REQUEST_LIST;

	static {
		SKIP_REQUEST_LIST = new ArrayList<>();
		SKIP_REQUEST_LIST.add("index.html");
		SKIP_REQUEST_LIST.add("login.html");
		SKIP_REQUEST_LIST.add("home.html");
		SKIP_REQUEST_LIST.add("header.html");
		SKIP_REQUEST_LIST.add("license.html");
		SKIP_REQUEST_LIST.add("footer.html");
		SKIP_REQUEST_LIST.add("licenseUpload.html");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		boolean returnValue = false;
	
		if (request.getRequestURI() != null) {
			String requestString = 	request.getRequestURI().toString();
			requestString = requestString
					.substring(requestString.indexOf('/', 1) + 1);
			int separatorIndex = requestString.indexOf('?');
			requestString = separatorIndex > -1 ? requestString.substring(0,
					separatorIndex) : requestString;
			if (LicenseFileValidator.getInstance().licenseFileExists()
					|| SKIP_REQUEST_LIST.contains(requestString)) {
				returnValue = true;
			} else {
				response.sendRedirect("license.html");
				returnValue = false;
			}
		}
		return returnValue;
	}
	
	/**
	 * This method is used to call for postHandle operation
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		//Business requirement yet to come
	}
	
	/**
	 * This method is used to call for afterCompletion operation
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//Business requirement yet to come
	}

}
