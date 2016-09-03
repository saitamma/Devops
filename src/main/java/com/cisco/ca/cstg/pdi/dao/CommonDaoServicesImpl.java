package com.cisco.ca.cstg.pdi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.engine.SessionFactoryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author padmkris edited On : Aug 29, 2013
 */
@Scope("prototype")
@Repository("commonDao")
public abstract class CommonDaoServicesImpl extends HibernateDaoSupport implements CommonDaoServices {
	private static final int ONE = 1;
	private static final int FIFTY = 50;
	private static final String UNCHECKED = "unchecked";
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoServicesImpl.class);
	
	private SessionFactory hibernateSessionFactory;

	public SessionFactory getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}
	
	@Autowired
	public void setHibernateSessionFactory(SessionFactory hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
		setSessionFactory(hibernateSessionFactory);
	}
	
	@Override
	public Integer addNew(Object o) {

		getHibernateTemplate().flush();

		return (Integer) getHibernateTemplate().save(o);
	}

	@Override
	public void addList(List<? extends Object> listObject) {
		
		Session session = null;
		
		try {
			session = getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			   
			for ( int i = 0; i < listObject.size(); i++ ) {
				session.save(listObject.get(i));
			    if ( i % FIFTY == 0 ) { 
			        session.flush();
			        session.clear();
			    }
			}
			transaction.commit();
		} catch(HibernateException ex) { 
			LOGGER.error(ex.getMessage(), ex);
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public void update(Object o) {

		getHibernateTemplate().update(o);

	}

	@Override
	public void delete(Object o) {
		try{
			getHibernateTemplate().delete(o);
		}catch(DataAccessException ex){
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	@Override
	public void deleteAll(List<? extends Object> listObject) {
		for(Object obj: listObject){
			delete(obj);
		}

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteByCriteria(Class c, Criterion whereClause) {
		List<Object> objects = listAll(c, whereClause);
		deleteAll(objects);
	}
	
	@Override
	public void bulkUpdate(String query) {

		getHibernateTemplate().bulkUpdate(query);
	}

	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Object> find(String queryString) {

		return (List<Object>) getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object findById(Class c, Integer id) {

		return getHibernateTemplate().get(c.getName(), id);

	}

	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Object> listAll(@SuppressWarnings("rawtypes") Class c) {
		return (List<Object>) getHibernateTemplate().find("from " + c.getName() + " order by id desc");
	}

	@SuppressWarnings(UNCHECKED)
	public List<Object> listAll(@SuppressWarnings("rawtypes") Class c, Criterion whereClause) {
		DetachedCriteria criteria = DetachedCriteria.forClass(c);
		criteria.add(whereClause);
		return (List<Object>) getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings(UNCHECKED)
	public List<Object> listAll(DetachedCriteria criteria) {
		return (List<Object>) getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Override
	public SessionFactory getDcafSessionFactory() {

		return getSessionFactory();
	}

	@Override
	public void saveOrUpdate(Object o) {

		getHibernateTemplate().saveOrUpdate(o);

		getHibernateTemplate().flush();

	}

	/**
	 * @param c
	 * @param Ids
	 */
	@SuppressWarnings("rawtypes")
	private void deleteBulk(Class c, String ids) {

		String hql = "delete from " + c.getSimpleName() + " where id in (" + ids + ")";
		
		deleteRecordsByHqlQuery(hql);
	}
		
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteRecords(Class c) {
		String hql = "delete from " + c.getSimpleName();
		
		deleteRecordsByHqlQuery(hql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteMultiple(Class c, String ids) {
		if (null == ids) {
			return;
		}

		StringTokenizer tokenizer = new StringTokenizer(ids, ",");
		int noOfRecords = tokenizer.countTokens();

		if (noOfRecords > Constants.DELETE_MAX_LENGTH) {
			String deviceIds = null;
			String tempIds = null;
			int count = 0;
			int totalCount = 0;
			while (tokenizer.hasMoreTokens()) {
				count++;
				totalCount++;
				tempIds = tokenizer.nextToken();
				if (ONE == count) {
					deviceIds = tempIds;
				} else {
					deviceIds = deviceIds + "," + tempIds;
				}
				if (count == Constants.DELETE_MAX_LENGTH || totalCount == noOfRecords) {

					deleteBulk(c, deviceIds);

					count = 0;

					deviceIds = null;
				}
			}
		} else {

			deleteBulk(c, ids);
		}
	}
	
	@Override
	public String executeStoredProcedure(String procedureName, int projectId) {
		//Format: procedure(IN params..., OUT errorMsg)
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) getSessionFactory();
		Connection con = null;
		CallableStatement cs = null;
		String message = null;
		try {
			con = (Connection) sfi.getConnectionProvider().getConnection();
			cs = con.prepareCall("CALL "+ procedureName +" (?, ?)");
			cs.setInt(1, projectId); 
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			message = (String) cs.getString(2);
		} catch (SQLException e) {
			message = e.getMessage();
			LOGGER.info("exception occured in executeStoredProcedure method.",e);
		} finally {
			try {
				if(cs != null) {
					cs.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.info("exception in executeStoredProcedure method.",e);
			}
		}
		return message;
	}
	
	private void deleteRecordsByHqlQuery(String hqlQuery) {
		SessionFactory fact = null;
		Session session = null;
		
		try {
			fact = getSessionFactory();			
	
			session = fact.openSession();
	
			Query query = session.createQuery(hqlQuery);
	
			query.executeUpdate();
	
			session.flush();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			if(session != null) {
				session.close();
			}
			
			if(fact != null) {
				fact.close();
			}
		}
	}	
}