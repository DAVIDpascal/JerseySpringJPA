package com.captechventures.rest;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.captechventures.schema.Adresse;
import com.captechventures.schema.User;

@Component
@Path("/v1")
public class LookupService {
	
	private String welcome;
	@Autowired
	protected UserManager userManager;

	private JAXBContext jaxbContext = null;
	private Marshaller marshaller = null;
	private Unmarshaller unmarshaller = null;
	private String jaxbPath = "com.captechventures.schema";

	/**
	 * Tell the Jersey-spring integration which bean to inject into this bean
	 */
	public LookupService() {
		super();
	}
	
	@GET
	@Path("/{userId}")
	@Produces("application/json")
	public User lookup(@PathParam("userId") Integer userId,
			@Context HttpServletRequest req, 
			@Context SecurityContext sc)
			throws Exception {

		System.out.println("getting username for userId=" + userId);

		User User = userManager.lookupUser(userId);
		if (User == null) {
			throw new WebApplicationException(HttpServletResponse.SC_NOT_FOUND);
		}

		return User;
	}

	@POST
	@Path("/add")
	@Consumes("application/json")
	public Response add(User user) throws Exception {

		userManager.addUser(user);

		String result = "User saved : " + user.getUserName();
		return Response.status(201).entity(result).build();
	}


	@POST
	@Path("/adresse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAdresse(Adresse adresse) throws Exception {

		String result = "Adresse saved : " + adresse;
		return Response.status(200).entity(result).build();
	}
	
	/**
	 * Conditionally initialize the jaxb context and create the marshallers required to convert documents to Java and back again.
	 * 
	 * @throws Exception
	 */
	private void initializeJAXB() throws Exception {
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(jaxbPath);
				marshaller = jaxbContext.createMarshaller();
				unmarshaller = jaxbContext.createUnmarshaller();
			} catch (JAXBException e) {
				System.err.println("Error unmarshalling request from client:  "
						+ e.getMessage());
				throw e;
			}
		}
	}

	/**
	 * Convert a JAXB element or @XMLRootElement object into a string representation of XML.
	 * 
	 * @param o The object to convert
	 * @return The XML String representation
	 * @throws Exception
	 */
	protected String marshalObject(Object o) throws Exception {
		initializeJAXB();
		// create a Writer object for the marshaller to write the XML into
		StringWriter stringWriter = new StringWriter();
		// marshall the object into the Writer
		marshaller.marshal(o, stringWriter);
		// expose the contents of the Writer as a string
		return stringWriter.toString();
	}

	/**
	 * Unmarshall a request body into a JAXB object
	 * 
	 * @param req The HttpServletRequest who's body is to be marshalled
	 * @return
	 * @throws Exception
	 */
	protected Object unmarshallRequest(HttpServletRequest req) throws Exception {
		initializeJAXB();
		// take the body of the Http request and unmarshal it into a Java Object
		return unmarshaller.unmarshal(req.getInputStream());
	}

	protected String getJaxbPath() {
		return jaxbPath;
	}

	protected void setJaxbPath(String jaxbPath) {
		this.jaxbPath = jaxbPath;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
