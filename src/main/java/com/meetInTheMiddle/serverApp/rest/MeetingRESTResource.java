package com.meetInTheMiddle.serverApp.rest;

// Web Service imports
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
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








import com.meetInTheMiddle.serverApp.GCMBroadcast;
import com.meetInTheMiddle.serverApp.dao.meeting.MeetingDao;
import com.meetInTheMiddle.serverApp.dao.meeting.MeetingDatabaseDao;
import com.meetInTheMiddle.serverApp.domain.meeting.Meeting;
import com.meetInTheMiddle.serverApp.domain.meeting.MeetingList;
import com.sun.jersey.api.NotFoundException;

/**
 * Interface fuer die Ressource, die ueber eine URL erreichbar ist und 
 * auf welcher verschiedene Operationen durchgefuehrt werden koennen sollen.
 * 
 */
@Path("/meetings")
@Produces({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
public class MeetingRESTResource {
//	public Logger logger = new Logger(PersonenRESTResource.class.getName());
	private Map<String, Meeting> meetings = new HashMap<>();
	private MeetingDao dao = new MeetingDatabaseDao(); // TODO: Mocking abschalten mit = new LocationDatabaseDao() //new LocationMockDao(); 

	/**
	 * Mit der URL /meetings/{id} ein Treffen ermitteln
	 * @param id ID des Orts
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return Objekt mit Ortsdaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public Meeting findMeetingById(@PathParam("id") Long id, 
			@Context UriInfo uriInfo) {

		return dao.findMeetingById(id);
	}
	
	/**
	 * Mit der URL /meetings alle Treffen ermitteln
	 * 
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @return	Treffenliste
	 * @throws Exception 
	 */
	@GET
	public MeetingList findAlleMeetings(@Context UriInfo uriInfo,
			@QueryParam("stadtname") @DefaultValue("") String name) {
		
		MeetingList list = new MeetingList();
		list.setList(dao.selectAll());
		return list;
	}
	
	/**
	 * Aktualisiert ein Treffen
	 * 
	 * @param person Das zu akualisierende Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response updateMeeting(Meeting meeting,
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers) {
				// Vorhandenes Treffen ermitteln
				final Meeting origMeeting = dao.findMeetingById(meeting.getId());
				if (origMeeting == null) {
					final String msg ="KEIN_TREFFEN_GEFUNDEN_MIT_ID "+ meeting.getId();
					throw new NotFoundException(msg);
				}				
//				LOGGER.tracef("%s", origKunde);

//				final List<Locale> locales = headers.getAcceptableLanguages();
//				final Locale locale = locales.isEmpty() ? Locale.getDefault() : locales.get(0);
				
				// Update durchfuehren
				dao.updateMeeting(meeting);
				return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Ein neues Treffen abspeichern.
	 * 
	 * @param treffen Das Treffen-Objekt
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 * @param headers
	 * @return 
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws URISyntaxException 
	 */
//	@Path("/create")
	@POST
    @XmlElement(type = Meeting.class)
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	@Produces
	public Response createMeeting(Meeting meeting, 
			@Context UriInfo uriInfo, 
			@Context HttpHeaders headers) throws ServletException, IOException
			{
		System.out.println(meeting);
		GCMBroadcast gcm = new GCMBroadcast(meeting.getaIdEmpfaenger());
		gcm.doPost(meeting.getMessage());
		dao.create(meeting.getPers1_fk(), meeting.getPers2_fk(), meeting.getUhrzeit(), meeting.getLokalitaet_fk(), meeting.getOrt_fk(), meeting.getBewertung(), meeting.getVerkehrsmittel_pers1_fk(),meeting.getKommentar(),meeting.getVerkehrsmittel_pers2_fk(),meeting.getaIdSender(),meeting.getaIdEmpfaenger(),meeting.getLocationPers1(),meeting.getLocationPers2());

		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	/**
	 * Loescht ein Treffen nach id.
	 * 
	 * @param id ID des Orts
	 * @param uriInfo Info-Objekt zur aufgerufenen URI
	 */
	@DELETE
	@Path("/{id}")
	public void deleteMeeting(@PathParam("id") Long id) {
		if(id==null)
		      throw new RuntimeException("Delete: Treffen " + id +  " not found");
		dao.deleteById(id);
	}


}
