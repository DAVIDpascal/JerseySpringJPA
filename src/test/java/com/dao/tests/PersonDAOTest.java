package com.dao.tests;

import javax.annotation.Resource;



import org.apache.log4j.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.persistent.dao.PersonDAO;
import com.persistent.entity.Person;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={"/com/dao/tests/applicationContext-test.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class PersonDAOTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired 
	private PersonDAO personDao; 
	private Logger logger = Logger.getLogger(this.getClass().getName());
    
	
	@Test
    public void testUpdatePersonn() {
		logger.debug("testing updatingPerson method");
		Person pers = new Person();
		pers.setId(0);
		pers.setName("Durand");
		pers.setAge(45);
		Assert.isTrue(personDao.update(pers));
		
    }


}
