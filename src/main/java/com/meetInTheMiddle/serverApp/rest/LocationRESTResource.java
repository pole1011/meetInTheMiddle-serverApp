package com.meetInTheMiddle.serverApp.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;

import com.meetInTheMiddle.serverApp.dao.location.LocationDao;
import com.meetInTheMiddle.serverApp.dao.location.LocationDatabaseDao;
import com.meetInTheMiddle.serverApp.domain.location.Location;
import com.meetInTheMiddle.serverApp.domain.location.LocationList;
import com.sun.jersey.api.NotFoundException;

/**
 * Interface fuer dieRessource, die ueber eine URL erreichbar ist und 
 * auf welcher verschiedene Operationen durchgefuehrt werden koennen sollen.
 * 
 */
@Path("/locations")
@Produces({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
public class LocationRESTResource {
	private Map<String, Location> locations = new HashMap<>();
	private LocationDao dao = new LocationDatabaseDao(); // TODO: Mocking abschalten mit = new LocationDatabaseDao() //new LocationMockDao(); 

	/**
	 * Mit der URL /locations/{id} eine Lokalitaet ermitteln
	 * @param id ID des Orts
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Ortsdaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public Location findLocationById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {

		return dao.findLocationById(id);
	}
	
	/**
	 * Mit der URL /locations alle Lokalitaeten ermitteln
	 * 
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return	Personliste
	 * @throws Exception 
	 */
	@GET
	public LocationList findAlleLocations(@Context UriInfo uriInfo) {
		
		LocationList list = new LocationList();
		list.setList(dao.selectAll());
		return list;
	}
	
	/**
	 * Aktualisiert eine Lokalitaet
	 * 
	 * @param person Das zu akualisierende Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response updateLocation(Location location,
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers) {
				// Vorhandenen Ort ermitteln
				final Location origLocation = dao.findLocationById(location.getId());
				if (origLocation == null) {
					final String msg ="KEINEN_ORT_GEFUNDEN_MIT_ID "+ location.getId();
					throw new NotFoundException(msg);
				}				
//				LOGGER.tracef("%s", origKunde);

//				final List<Locale> locales = headers.getAcceptableLanguages();
//				final Locale locale = locales.isEmpty() ? Locale.getDefault() : locales.get(0);
				
				// Update durchfuehren
				dao.updateLocation(location);
				return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Eine neue Lokalitaet abspeichern.
	 * 
	 * @param person Das Person-Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @param headers
	 * @return 
	 * @return
	 * @throws URISyntaxException 
	 */
	@POST
    @XmlElement(type = Location.class)
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces
	public Response createLocation(Location location, 
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers)
			{
		dao.create(location.getBeschreibung());

		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Loescht einen Ort unter angabe des Stadtnamens und der plz.
	 * 
	 * @param id ID des Orts
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@DELETE
	@Path("/delete/{id}")
	public void deleteLocation(@PathParam("id") Long id) {
		if(id==null)
		      throw new RuntimeException("Delete: Lokalitaet mit id: " +id + "nicht gefunden");
		dao.deleteById(id);
	}
}
