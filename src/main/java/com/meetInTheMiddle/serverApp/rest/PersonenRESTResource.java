package com.meetInTheMiddle.serverApp.rest;

// Web Service imports
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.UriInfo;

import com.meetInTheMiddle.serverApp.dao.PersonDao;
import com.meetInTheMiddle.serverApp.dao.PersonDatabaseDao;
import com.meetInTheMiddle.serverApp.dao.PersonMockDao;
import com.meetInTheMiddle.serverApp.domain.Person;
import com.meetInTheMiddle.serverApp.domain.PersonList;

/**
 * Interface fuer dieRessource, die ueber eine URL erreichbar ist und 
 * auf welcher verschiedene Operationen durchgefuehrt werden koennen sollen.
 * 
 */
@Path("/persons")
@Produces({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
public class PersonenRESTResource {
	
	private PersonDao dao = new PersonDatabaseDao(); // TODO: Mocking abschalten mit = new PersonDatabaseDao() //new PersonMockDao(); 

	/**
	 * Mit der URL /person/{id} einen Person ermitteln
	 * @param id ID des Persons
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Persondaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[0-9]+}")
	public Person findPersonById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {
		return new Person("hans", "wurst", null, null, null, 0, null, null); // TODO
	}
	
	/**
	 * Mit der URL /Person alle Person ermitteln
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
	 * Einen neuen Person abspeichern
	 * 
	 * @param person Das Person-Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @param headers
	 * @return
	 * @throws URISyntaxException 
	 */
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces
	public Response createPerson(Person person, 
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers) throws URISyntaxException {
		return Response.created(new URI("uri zur angelegten person hier")).build();
	}
	
	/**
	 * Aktualisiert einen Person
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
	 * Loescht einen Person
	 * 
	 * @param id ID des Persons
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@DELETE
	@Path("{id:[1-9][0-9]*}")
	public void deletePerson(@PathParam("id") Long id) {
		// TODO
	}


}
