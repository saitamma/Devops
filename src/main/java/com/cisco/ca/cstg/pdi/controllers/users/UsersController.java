package com.cisco.ca.cstg.pdi.controllers.users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.ca.cstg.pdi.pojos.ADAUsers;
import com.cisco.ca.cstg.pdi.pojos.UserRoles;
import com.cisco.ca.cstg.pdi.services.UserManagementService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.LDAPUtil;
import com.cisco.ca.cstg.pdi.utils.Util;

/**
 * @author Vishnu
 * 
 */

@Controller
public class UsersController implements Constants {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);	
	private final UserManagementService userManagementService;

	@Autowired
	public UsersController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@RequestMapping(value = "/userManagement.html")
	public String userManagement(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String userRole = (String) req.getSession().getAttribute(USER_ROLE);
			if (!(ADMIN.equals(userRole) || SUPER_ADMIN.equals(userRole) || PRODUCT_OWNER
					.equals(userRole))) {
				res.sendRedirect("error.html");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "users/userManagement";
	}

	@RequestMapping(value = "/getUserRoleList.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getUserRoleList() throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<UserRoles> userRoleList = null;
		try {
			userRoleList = userManagementService.getUserRoleList();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (Util.listNotNull(userRoleList)) {
			for (UserRoles ur : userRoleList) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, ur.getId());
				myMap.put(NAME, ur.getUserRole());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	@RequestMapping(value = "/getUserList.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getUserList(HttpServletRequest request)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<ADAUsers> usersList = new ArrayList<ADAUsers>();
		HttpSession session = request.getSession();
		String activeUserId = (String) session.getAttribute("userCec");
		int activeUserIntId = (int) session.getAttribute(ACTIVE_USERID);
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		try {
			usersList = userManagementService.getUserList(activeUserId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		returnUserJsonList(jsonList, usersList, activeUserIntId, df);
		return jsonList;
	}

	public void returnUserJsonList(List<Object> jsonList,
			List<ADAUsers> usersList, int activeUserIntId, DateFormat df) {
		if (Util.listNotNull(usersList)) {
			for (ADAUsers u : usersList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, u.getId());
				myMap.put(NAME, u.getName());
				myMap.put(USER_ID, u.getUserId());
				myMap.put(MAIL_ID, u.getMailId());
				myMap.put(USER_ROLES, (u.getUserRoles() != null) ? u
						.getUserRoles().getId() : null);
				if (u.getUserRolesName() != null) {
					myMap.put(USER_ROLES_NAME, u.getUserRolesName()
							.toUpperCase());
				} else {
					myMap.put(USER_ROLES_NAME, (u.getUserRoles() != null) ? u
							.getUserRoles().getUserRole().toUpperCase() : null);
				}
				myMap.put(CREATEDBY, activeUserIntId);
				myMap.put(
						CREATED_DATE,
						(u.getCreatedDate() != null) ? df.format(u
								.getCreatedDate()) : null);
				myMap.put(IS_ACTIVE, u.getIsActive());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manageUsers.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> manageUsers(@RequestParam(JSONOBJ) String jsonObj,
			HttpServletRequest req) throws IOException {
		List<ADAUsers> newlyAddedUser = null;
		List<Object> jsonList = new ArrayList<Object>();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		HttpSession session = req.getSession();
		int activeUserIntId = (int) session.getAttribute(ACTIVE_USERID);
		if (Util.isStringNotEmpty(jsonObj)) {
			try {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);
				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<ADAUsers> userList = (List<ADAUsers>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									ADAUsers.class);
					newlyAddedUser = userManagementService
							.addOrUpdateUsers(userList);
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<ADAUsers> deletedUserList = (List<ADAUsers>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									ADAUsers.class);
					userManagementService.deleteUsers(deletedUserList);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		returnUserJsonList(jsonList, newlyAddedUser, activeUserIntId, df);
		return jsonList;
	}

	@RequestMapping(value = "/validateUserCec.html", method = RequestMethod.POST)
	@ResponseBody
	public String validateUserCec(@RequestParam(JSONOBJ) String jsonObj)
			throws IOException {
		List<String> ldapInvalidUserId = new ArrayList<String>();
		List<String> existDeactivateUserId = new ArrayList<String>();
		List<String> existActivateUserId = new ArrayList<String>();
		StringBuilder invalidIds = new StringBuilder();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ADAUsers userObj = mapper.readValue(jsonObj, ADAUsers.class);
			if (userObj != null) {
				String[] userIdArr = userObj.getUserId().split(",");
				for (int i = 0; i < userIdArr.length; i++) {
					boolean isValid = LDAPUtil.isUserIdValid(userIdArr[i]);
					if (!isValid) {
						ldapInvalidUserId.add(userIdArr[i]);
						invalidIds.append(userIdArr[i]);
						invalidIds.append(",");
					}
				}
				if (ldapInvalidUserId.isEmpty()) {
					for (int i = 0; i < userIdArr.length; i++) {
						List<ADAUsers> userList = userManagementService
								.getUserByUserId(userIdArr[i]);
						if (Util.listNotNull(userList)) {
							if (userList.get(0).getIsActive() == 0) {
								existDeactivateUserId.add(userIdArr[i]);
							} else if (userList.get(0).getIsActive() == 1) {
								existActivateUserId.add(userIdArr[i]);
							}
						}
					}
					if (!existDeactivateUserId.isEmpty()) {
						convertCommaSepratedString(existDeactivateUserId,
								invalidIds);
						invalidIds
								.append("EXIST is/are already exist.Please change his status to active.");
						return invalidIds.toString();
					}
					if (!existActivateUserId.isEmpty()) {
						convertCommaSepratedString(existActivateUserId,
								invalidIds);
						invalidIds.append("EXIST is/are already exist.");
						return invalidIds.toString();
					}
				} else {
					invalidIds.append("LDAP");
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return (invalidIds.length() == 0) ? SUCCESS : invalidIds.toString();
	}
	
	private void convertCommaSepratedString(List<String> list, StringBuilder sb) {
		for (String userId : list) {
			sb.append(userId);
			sb.append(",");
		}
	}
}
