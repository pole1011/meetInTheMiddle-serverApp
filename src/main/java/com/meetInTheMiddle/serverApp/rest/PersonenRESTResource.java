package com.meetInTheMiddle.serverApp.rest;

// Web Service imports
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
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

import com.meetInTheMiddle.serverApp.dao.person.PersonDao;
import com.meetInTheMiddle.serverApp.dao.person.PersonDatabaseDao;
import com.meetInTheMiddle.serverApp.dao.person.PersonMockDao;
import com.meetInTheMiddle.serverApp.domain.person.Person;
import com.meetInTheMiddle.serverApp.domain.person.PersonList;
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
	 * @param id ID der Person
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Personendaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public Person findPersonById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {
		return dao.findPersonById(id);
	}
	
	@GET
	@Path("/{firstname}/{lastname}")
	@Produces(MediaType.APPLICATION_XML)
	public Person findPersonByFirstLastName(@PathParam("firstname") String firstName, @PathParam("lastname") String lastName,
			@Context UriInfo uriInfo) {
		return dao.findPersonByFirstLastName(firstName, lastName);
	}
	
	/**
	 * Mit der URL /persons/{id}/contacts alle Kontakte zu einer Person ermitteln.
	 * @param id ID der Person
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Personendaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}/contacts")
	@Produces(MediaType.APPLICATION_XML)
	public PersonList findContactsById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {
		PersonList list = new PersonList();
		list.setList(dao.findContactsById(id));
		return list;
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
//	@Path("/create")
	@POST
    @XmlElement(type = Person.class)
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces
	public Response createPerson(Person person, 
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers)
			{
		System.out.println(person.getFirstName() + person.getLastName() + person.getBirthday() + person.getPhone() + person.getEmail() + person.getPassword() + person.getInterests());
		dao.create(person.getFirstName(), person.getLastName(),  person.getBirthday(), person.getPhone(), person.getEmail(), person.getPassword(), person.getInterests());
		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Einen neuen Kontakt abspeichern.
	 * 
	 * @param person Das Person-Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @param headers
	 * @return 
	 * @return
	 * @throws URISyntaxException 
	 */
	@Path("/contacts/{firstid}/{lastid}")
	@POST
    @XmlElement(type = Person.class)
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces
	public Response createContact(@PathParam("firstid") Long person_1_id, @PathParam("lastid") Long person_2_id,
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers)
			{
		dao.createContact(person_1_id,person_2_id);

		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Aktualisiert eine Person
	 * 
	 * @param person Das zu akualisierende Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@PUT
	@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML})
	public Response updatePerson(Person person,
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers) {
		// Vorhandene Person ermitteln
				final Person origPerson = dao.findPersonById(person.getId());
				if (origPerson == null) {
					final String msg = "KEINE_PERSON_GEFUNDEN_MIT_ID "+ person.getId();
					throw new NotFoundException(msg);
				}
				
//				LOGGER.tracef("%s", origKunde);

//				final List<Locale> locales = headers.getAcceptableLanguages();
//				final Locale locale = locales.isEmpty() ? Locale.getDefault() : locales.get(0);
				
				// Update durchfuehren
				System.out.println(person);
				System.out.println(origPerson);
				dao.updatePerson(person);
				return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Loescht eine Person unter angabe der E-Mail-Adresse.
	 * 
	 * @param id ID des Persons
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@DELETE
	@Path("/{email}")
	public void deletePerson(@PathParam("email") String email) {
		if(email==null)
		      throw new RuntimeException("Delete: Person with " + email +  " not found");
		dao.deleteByEmail(email);
	}


}
