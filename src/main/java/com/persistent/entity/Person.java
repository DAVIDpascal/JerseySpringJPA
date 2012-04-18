package com.persistent.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
@NamedQueries( { @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
				@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
	})
public class Person {
	private int id;
	private String name;
	private int age;
	private Address address;

	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToOne(mappedBy="locataire")
	public Address getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}

