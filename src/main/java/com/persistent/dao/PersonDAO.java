package com.persistent.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.persistent.entity.Person;

@Repository("personDao")
public class PersonDAO {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public Person getById(int id) {
		
		// TODO Auto-generated method stub
		return entityManager.find(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAll() {
		Query query = entityManager.createNamedQuery("Person.findAll");
		List<Person> persons = null;
		persons = query.getResultList();
		return persons;
	}

	public boolean save(Person person) {

		entityManager.persist(person);
		entityManager.flush();

		return true;
	}

	public boolean update(Person person) {
		entityManager.merge(person);
		entityManager.flush();
		return true;
	}

	public boolean delete(Person person) {
		person = entityManager.getReference(Person.class, person.getId());
		if (person == null)
			return false;
		entityManager.remove(person);
		entityManager.flush();
		return true;
	}

	@SuppressWarnings("unchecked")
	public Person findPerson(Person person) {
		Person result = null;
		Query queryFindPerson = entityManager
				.createNamedQuery("Person.findPerson");
		queryFindPerson.setParameter("name", person.getName());
		queryFindPerson.setParameter("age", person.getAge());
		List<Person> persons = queryFindPerson.getResultList();
		if (persons.size() > 0) {
			result = persons.get(0);
		}
		return result;
	}
}
