package com.meetInTheMiddle.serverApp.rest;

// Web Service imports
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

import com.meetInTheMiddle.serverApp.dao.place.PlaceDao;
import com.meetInTheMiddle.serverApp.dao.place.PlaceDatabaseDao;
import com.meetInTheMiddle.serverApp.domain.place.Place;
import com.meetInTheMiddle.serverApp.domain.place.PlaceList;
import com.sun.jersey.api.NotFoundException;

/**
 * Interface fuer dieRessource, die ueber eine URL erreichbar ist und 
 * auf welcher verschiedene Operationen durchgefuehrt werden koennen sollen.
 * 
 */
@Path("/places")
@Produces({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
public class PlaceRESTResource {
//	public Logger logger = new Logger(PersonenRESTResource.class.getName());
	private Map<String, Place> places = new HashMap<>();
	private PlaceDao dao = new PlaceDatabaseDao(); // TODO: Mocking abschalten mit = new LocationDatabaseDao() //new LocationMockDao(); 

	/**
	 * Mit der URL /locations/{id} einen Ort ermitteln
	 * @param id ID des Orts
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Ortsdaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public Place findLocationById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {

		return dao.findPlaceById(id);
	}
	
	/**
	 * Mit der URL /persons alle Person ermitteln
	 * 
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return	Personliste
	 * @throws Exception 
	 */
	@GET
	public PlaceList findAlleLocations(@Context UriInfo uriInfo) {
		
		PlaceList list = new PlaceList();
		list.setList(dao.selectAll());
		return list;
	}
	
	/**
	 * Aktualisiert einen Ort
	 * 
	 * @param person Das zu akualisierende Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response updateLocation(Place place,
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers) {
				// Vorhandenen Ort ermitteln
				final Place origPlace = dao.findPlaceById(place.getId());
				if (origPlace == null) {
					final String msg ="KEINEN_ORT_GEFUNDEN_MIT_ID "+ place.getId();
					throw new NotFoundException(msg);
				}				
//				LOGGER.tracef("%s", origKunde);

//				final List<Locale> locales = headers.getAcceptableLanguages();
//				final Locale locale = locales.isEmpty() ? Locale.getDefault() : locales.get(0);
				
				// Update durchfuehren
				dao.updatePlace(place);
				return Response.created(uriInfo.getAbsolutePath()).build();
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
	@POST
    @XmlElement(type = Place.class)
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces
	public Response createPlace(Place place, 
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers)
			{
		dao.create(place.getStadtname(), place.getPlz());

		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Loescht einen Ort unter angabe des Stadtnamens und der plz.
	 * 
	 * @param id ID des Orts
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@DELETE
	@Path("/delete/{stadtname}/{plz}")
	public void deleteLocation(@PathParam("stadtname") String stadtname,
			@PathParam("plz") String plz) {
		if(stadtname==null)
		      throw new RuntimeException("Delete: Ort " + stadtname +  " not found");
		if(plz==null)
		      throw new RuntimeException("Delete: Ort mit plz " + plz +  " not found");
		dao.deleteByStadtnameUndPlz(stadtname,plz);
	}


}
