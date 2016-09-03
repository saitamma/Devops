package com.cisco.ca.cstg.pdi.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author padmkris Edited On : Aug 29, 2013
 */
public interface CommonDaoServices {

	Integer addNew(Object o);
	
	void addList(List<? extends Object> listObject);
	
	void update(Object o);

	void bulkUpdate(String query);

	void saveOrUpdate(Object o);
	
	void delete(Object o);

	@SuppressWarnings("rawtypes")
	void deleteMultiple(Class c, String ids);
	
	@SuppressWarnings("rawtypes")
	void deleteRecords(Class c);

	void deleteAll(List<? extends Object> listObject) ;
	
	@SuppressWarnings("rawtypes")
	void deleteByCriteria(Class c, Criterion whereClause) ;

	@SuppressWarnings("rawtypes")
	Object findById(Class c, Integer id);
	
	List<Object> find(String queryString);

	@SuppressWarnings("rawtypes")
	List<Object> listAll(Class c);
	
	@SuppressWarnings("rawtypes")
	List<Object> listAll(Class c, Criterion whereClause);
	
	List<Object> listAll(DetachedCriteria criteria);

	SessionFactory getDcafSessionFactory();
	
	String executeStoredProcedure(String procedureName, int projectId);
}
