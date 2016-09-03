package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.ADAUsers;
import com.cisco.ca.cstg.pdi.pojos.UserRoles;

public interface UserManagementService {

	List<ADAUsers> getUserList(String activeUserId);

	List<ADAUsers> addOrUpdateUsers(List<ADAUsers> userList);

	void deleteUsers(List<ADAUsers> deletedUserList);

	List<UserRoles> getUserRoleList();
	
	List<ADAUsers> getUserByUserId(String userId);
		
	String getAdminIdByUserId(List<String> userIdList);

}
