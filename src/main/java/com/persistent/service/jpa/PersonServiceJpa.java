package com.persistent.service.jpa;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.persistent.dao.PersonDAO;
import com.persistent.entity.Person;
import com.persistent.service.PersonService;


@Service("personService")
public class PersonServiceJpa implements PersonService {
   
	@Resource 
	private PersonDAO personDao; 
		
	@Transactional(readOnly = true)
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return personDao.getById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Person> getAll() {
		return personDao.getAll();
	}

	@Transactional()
	public boolean save(Person person) {
		personDao.save(person);
		return true;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean update(Person person) {
		personDao.update(person);
		return true;
	}
	
	@Transactional()
	public boolean delete(Person person) {
		personDao.delete(person);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Person findPerson(Person person) {
		
		return personDao.findPerson(person);
	}
}

