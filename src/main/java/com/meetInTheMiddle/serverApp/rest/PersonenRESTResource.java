package com.meetInTheMiddle.serverApp.rest;

// Web Service imports
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import sun.rmi.runtime.Log;

import com.meetInTheMiddle.serverApp.dao.PersonDao;
import com.meetInTheMiddle.serverApp.dao.PersonDatabaseDao;
import com.meetInTheMiddle.serverApp.dao.PersonMockDao;
import com.meetInTheMiddle.serverApp.domain.Person;
import com.meetInTheMiddle.serverApp.domain.PersonList;
import com.sun.jersey.api.NotFoundException;

/**
 * Interface fuer dieRessource, die ueber eine URL erreichbar ist und 
 * auf welcher verschiedene Operationen durchgefuehrt werden koennen sollen.
 * 
 */
@Path("/persons")
@Produces({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
public class PersonenRESTResource {
//	public Logger logger = new Logger(PersonenRESTResource.class.getName());
	private Map<String, Person> persons = new HashMap<>();
	private PersonDao dao = new PersonDatabaseDao(); // TODO: Mocking abschalten mit = new PersonDatabaseDao() //new PersonMockDao(); 

	/**
	 * Mit der URL /persons/{id} eine Person ermitteln
	 * @param id ID der Persons
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Personendaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public Person findPersonById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {
		PersonList list = new PersonList();
		list.setList(dao.findPersonById(id));
		System.out.println("Vorname der Per ID gesuchten Person: " + list.getList().get(0).getFirstName());
		if (list.getList().get(0) == null) {
			final String msg = "KEINE_PERSON_GEFUNDEN_MIT_ID" + id;
			throw new NotFoundException(msg);
			}
		return new Person(list.getList().get(0).getFirstName(), list.getList().get(0).getLastName(), 
				list.getList().get(0).getBirthday(), list.getList().get(0).getPhone(), list.getList().get(0).getEmail(), 
				list.getList().get(0).getKontaktliste_fk(), list.getList().get(0).getPassword(), list.getList().get(0).getInterests());
	}
	
	/**
	 * Mit der URL /persons alle Person ermitteln
	 * 
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return	Personliste
	 */
	@GET
	public PersonList findAllePersonen(@Context UriInfo uriInfo,
			@QueryParam("name") @DefaultValue("") String name) {
		
		PersonList list = new PersonList();
		list.setList(dao.selectAll());
		return list;
	}
	
	/**
	 * Eine neue Person abspeichern.
	 * 
	 * @param person Das Person-Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @param headers
	 * @return 
	 * @return
	 * @throws URISyntaxException 
	 */
	@Path("/create")
	@POST
    @XmlElement(type = Person.class)
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces
	public Response createPerson(Person person, 
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers)
			{
//		PersonList list = new PersonList();
//		list.getList().add(person);
//		java.util.Date utilDate = person.getBirthday();
//		System.out.println(utilDate);
//	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//	    System.out.println(sqlDate);
//		System.out.println("Übergebenes Objekt der Person: " + person.getEmail());
System.out.println(person.getFirstName() + person.getLastName() + person.getPhone() + person.getEmail() + 1 + person.getPassword() + person.getInterests());
		dao.create(person.getFirstName(), person.getLastName(),  null, person.getPhone(), person.getEmail(), 1, person.getPassword(), person.getInterests());

		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Aktualisiert eine Person
	 * 
	 * @param person Das zu akualisierende Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void updatePerson(Person person,
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers) {
		// TODO
	}
	
	/**
	 * Loescht eine Person unter angabe der E-Mail-Adresse.
	 * 
	 * @param id ID des Persons
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@DELETE
	@Path("/delete/{email}")
	public void deletePerson(@PathParam("email") String email) {
		dao.deleteByEmail(email);
		if(email==null)
		      throw new RuntimeException("Delete: Person with " + email +  " not found");
	}


}
