package com.cisco.ca.cstg.pdi.dao;

import org.hibernate.SessionFactory;

/**
 * 
 * @author tapdash, Created on May 09, 2014
 * 
 * DaoServicesImpl is the concrete class for CommonDaoServicesImpl, Created for JUnit
 *
 */
public class DaoServicesImpl extends CommonDaoServicesImpl {
	

	public DaoServicesImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);

	}

}
