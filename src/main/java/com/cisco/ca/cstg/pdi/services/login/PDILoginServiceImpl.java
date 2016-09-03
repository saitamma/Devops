package com.cisco.ca.cstg.pdi.services.login;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.Credentials;

@Service("pdiLoginService")
public class PDILoginServiceImpl extends CommonDaoServicesImpl implements
		PDILoginService {

	@Autowired
	public PDILoginServiceImpl(SessionFactory hibernateSessionFactory) {

		setSessionFactory(hibernateSessionFactory);
	}

	@Override
	public Credentials findByUserName(String userName) {

		List<Object> users = listAll(Credentials.class, Restrictions.eq("username", userName));
		
		if(users.size() > 0) {
			return (Credentials) users.get(0);
		} 
		return null;
	}
}
