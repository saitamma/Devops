package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.ADAUsers;
import com.cisco.ca.cstg.pdi.pojos.UserRoles;
import com.cisco.ca.cstg.pdi.utils.Util;


/**
 * @author Vishnu
 * 
 */

@Service("userManagementService")
public class UserManagementServiceImpl extends CommonDaoServicesImpl implements UserManagementService{
	
	
	@Autowired
	public UserManagementServiceImpl(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ADAUsers> getUserList(String activeUserId) {
		Criterion criteria = Restrictions.eq("userId", activeUserId);
		List<ADAUsers> loginUser = (List<ADAUsers>)(List<?>) listAll(ADAUsers.class, criteria);
		if(Util.listNotNull(loginUser)){
			Criterion criteria1 = Restrictions.eq("createdBy", loginUser.get(0).getId());
			return (List<ADAUsers>)(List<?>) listAll(ADAUsers.class, criteria1);
		}
		return null;
	}

	@Override
	public List<ADAUsers> addOrUpdateUsers(List<ADAUsers> userList) {
		for (ADAUsers user : userList) {
			if (user.getId() == 0) {
				user.setId(addNew(user));
			} else {
				update(user);
			}
		}
		return userList;
	}

	@Override
	public void deleteUsers(List<ADAUsers> deletedUserList) {
		deleteAll(deletedUserList);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoles> getUserRoleList() {
		return (List<UserRoles>) (List<?>)listAll(UserRoles.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ADAUsers> getUserByUserId(String userId) {
		Criterion criteria = Restrictions.eq("userId", userId);
		return (List<ADAUsers>) (List<?>) listAll(ADAUsers.class, criteria);
	}		
	
	@SuppressWarnings("unchecked")
	@Override
	public String getAdminIdByUserId(List<String> userIdList) {
		StringBuilder adminIds = new StringBuilder();
		if (Util.listNotNull(userIdList)) {
			for (String userId : userIdList) {
				Criterion criteria = Restrictions.eq("userId", userId);
				List<ADAUsers> userDetails = (List<ADAUsers>) (List<?>) listAll(
						ADAUsers.class, criteria);
				if (Util.listNotNull(userDetails)) {
					Criterion criteria1 = Restrictions.eq("id", userDetails
							.get(0).getCreatedBy());
					List<ADAUsers> adaUsersList = (List<ADAUsers>) (List<?>) listAll(
							ADAUsers.class, criteria1);
					adminIds.append(adaUsersList.get(0).getUserId());
					adminIds.append(",");
				}
			}
		}
		return adminIds.toString();
	}

}
