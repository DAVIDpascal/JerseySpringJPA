package com.persistent.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.persistent.entity.Person;
import com.persistent.service.PersonService;

// The Java class will be hosted at the URI path "/myresource"
@Path("/v2")
@Component
@Scope("request")
public class MyResource {

	@Autowired
	PersonService personService;

	// The Java method will process HTTP GET requests
	@GET
	@Path("/person")
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces("text/plain")
	public String getIt() {
		Person person = new Person();
		person.setName("David Sells");
		person.setAge(99);
		
		addIfDoesNotExist(person);
		
		StringBuffer buffer = new StringBuffer();
		
		List<Person> persons = personService.getAll();
		for (Person person2 : persons) {
			buffer.append(person2.getName()).append(":").append(person2.getAge())
					.append("\n");
		}

		return "Hi there: "+buffer.toString();
	}
	
	
	
	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	public void lookup(Person person) throws Exception {
		boolean isUpdate = personService.update(person);
		if (!isUpdate) {
			throw new WebApplicationException(HttpServletResponse.SC_NOT_MODIFIED);
		}
		
	}
	
	private void addIfDoesNotExist(Person person) {
		if(personService.findPerson(person) == null) {
			personService.save(person);
		}
	}
		
}

