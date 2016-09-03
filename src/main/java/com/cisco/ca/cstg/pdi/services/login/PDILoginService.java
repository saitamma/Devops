package com.cisco.ca.cstg.pdi.services.login;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServices;
import com.cisco.ca.cstg.pdi.pojos.Credentials;


public interface PDILoginService extends CommonDaoServices {

	Credentials findByUserName(String userName);
	
}
