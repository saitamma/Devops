package com.cisco.ca.cstg.pdi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.ca.cstg.pdi.pojos.License;

/**
 * 
 * @author tapdash, Created On : May 12, 2014
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class CommonDaoServicesImplTest {
	
	private DaoServicesImpl daoService;
	
	@Autowired
	private SessionFactory hibernateSessionFactory;
	
	@Before
	public void setUp(){
		daoService = new DaoServicesImpl(hibernateSessionFactory);
	}
	
	@Test
	@Transactional
	public void addNew(){
		int id = daoService.addNew(getLicense());
		assertNotNull(id);
	}
	
	@Test(expected = Exception.class)
	public void addNew_nullObject(){
		daoService.addNew(null);
	}

	@Test
	public void addList(){
		List<Object> list = new ArrayList<>();
		list.add(getLicense());
		daoService.addList(list);
		assertNotNull(((License) list.get(0)).getId());
		daoService.deleteAll(list);
	}
	
	@Test(expected = Exception.class)
	public void addList_nullObject(){
		daoService.addList(null);
	}
	
	@Test
	@Transactional
	public void update(){
		License license = getLicense();
		daoService.addNew(license);
		license.setName("NameUpdated");
		daoService.update(license);
		license = (License) daoService.findById(License.class, license.getId());
		assertEquals("NameUpdated", license.getName());
	}
	
	@Test(expected = Exception.class)
	public void update_nullObject(){
		daoService.update(null);
	}
	
	@Test
	public void delete(){
		License license = getLicense();
		daoService.addNew(license);
		assertNotNull(daoService.findById(License.class, license.getId()));
		daoService.delete(license);
		assertNull(daoService.findById(License.class, license.getId()));
	}
	
	@Test(expected = Exception.class)
	public void delete_nullObject(){
		daoService.delete(null);
	}
	
	@Test
	public void deleteAll(){
		ArrayList<Object> list = new ArrayList<>();
		list.add(getLicense());
		daoService.addList(list);
		int id = ((License)list.get(0)).getId();
		assertNotNull(daoService.findById(License.class, id));
		daoService.deleteAll(list);
		assertNull(daoService.findById(License.class, id));
	}
	
	@Test
	public void deleteByCriteria() {
		License license = getLicense();
		daoService.addNew(license);
		Criterion criterion = Restrictions.eq("id", license.getId());
		daoService.deleteByCriteria(License.class, criterion);
		assertNull(daoService.findById(license.getClass(), license.getId()));
	}
	
	
	@Test(expected = Exception.class)
	public void deleteByCriteria_nullClass() {
		Criterion criterion = Restrictions.eq("id", 1);
		daoService.deleteByCriteria(null, criterion);
	}
	
	@Test(expected = Exception.class)
	public void deleteByCriteria_nullCriteria() {
		daoService.deleteByCriteria(License.class, null);
	}
	
	@Test
	public void bulkUpdate() {
		License license = getLicense();
		daoService.addNew(license);
		daoService.bulkUpdate("update License set customerDescription = 'TESTTEST' where id = " + license.getId());
		license = (License) daoService.findById(License.class, license.getId());
		assertEquals("TESTTEST", license.getCustomerDescription());
		daoService.delete(license);
		
	}
	
	@Test(expected = Exception.class)
	public void bulkUpdate_nullCheck() {
		daoService.bulkUpdate(null);
	}
	
	@Test
	@Transactional
	public void find(){
		License license = getLicense();
		daoService.addNew(license);
		List<Object> list = daoService.find("from License");
		assertNotNull(list);
	}	
	
	@Test(expected = Exception.class)
	public void find_nullObject(){
		daoService.find(null);
	}
	
	@Test
	@Transactional
	public void findById() {
		License newLicense = getLicense();
		daoService.addNew(newLicense);
		License fetchedLicense = (License) daoService.findById(License.class, newLicense.getId());
		assertEquals(fetchedLicense.getName(), newLicense.getName());
	}

	@Test(expected = Exception.class)
	public void findById_nullClass() {
		daoService.findById(null, 1);
	}

	@Test(expected = Exception.class)
	public void findById_nullId() {
		daoService.findById(License.class, null);
	}

	@Test
	@Transactional
	public void listAll(){
		License license = getLicense();
		daoService.addNew(license);
		List<Object> list = daoService.listAll(License.class);
		assertNotNull(list);
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = Exception.class)
	public void listAll_nullClass(){
		Class c = null;
		daoService.listAll(c);
	}

	
	@Test
	@Transactional
	public void listAllWithCriteria() {
		License license = getLicense();
		daoService.addNew(license);
		Criterion criterion = Restrictions.eq("id", license.getId());
		assertEquals(1, daoService.listAll(License.class, criterion).size());
	}
	
	@Test(expected = Exception.class)
	public void listAllWithCriteria_nullClass() {
		Criterion criterion = Restrictions.eq("id", 1);
		daoService.listAll(null, criterion);
	}
	
	@Test(expected = Exception.class)
	public void listAllWithCriteria_nullId() {
		daoService.listAll(License.class, null);
	}
	
	@Test
	@Transactional
	public void listAllWithDetachedCriteria(){
		License license = getLicense();
		daoService.addNew(license);
		DetachedCriteria criteria = DetachedCriteria.forClass(License.class).addOrder(Order.asc("id"));
		List<Object> list = daoService.listAll(criteria);
		assertNotNull(list);
	}
	
	@Test(expected = Exception.class)
	public void listAllWithDetachedCriteria_nullCriteria(){
		DetachedCriteria criteria = null;
		daoService.listAll(criteria);
	}
	
	@Test
	public void saveOrUpdate(){
		License license = getLicense();
		daoService.addNew(license);
		license.setName("NameUpdated");
		daoService.saveOrUpdate(license);
		License fetchedLicense = (License) daoService.findById(License.class, license.getId());
		assertEquals(license.getName(), fetchedLicense.getName());
		daoService.delete(fetchedLicense);
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdate_nullObject(){
		daoService.saveOrUpdate(null);
	}

	@Test
	public void deleteMultiple(){
		License license = getLicense();
		daoService.addNew(license);
		daoService.deleteMultiple(License.class, license.getId() + "," + license.getId());
		assertNull(daoService.findById(License.class, license.getId()));
	}
	
	@Test(expected = Exception.class)
	public void deleteMultiple_nullClass(){
		daoService.deleteMultiple(null, 1 + "," + 2);
	}
	
	@Test
	public void deleteRecords(){
		List<Object> list = daoService.listAll(License.class);
		daoService.deleteRecords(License.class);
		assertEquals(0, daoService.listAll(License.class).size());
		daoService.addList(list);
	}
	
	@Test(expected = Exception.class)
	public void deleteRecords_nullClass(){
		daoService.deleteRecords(null);
	}
	
	private License getLicense(){
		License license = new License();
		license.setName("Test1");
		license.setDescription("Test Description1");
		license.setCustomerName("Customer1");
		license.setServiceType("UCSHC-UCSM;VCENTER1");
		return license;
	}
}