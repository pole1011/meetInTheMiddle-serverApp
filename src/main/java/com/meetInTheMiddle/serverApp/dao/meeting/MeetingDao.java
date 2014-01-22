package com.meetInTheMiddle.serverApp.dao.meeting;

import java.util.Date;
import java.util.List;

import com.meetInTheMiddle.serverApp.domain.meeting.Meeting;
/**
 * 
 * @author Felix
 *
 */
public interface MeetingDao {
	public List<Meeting> selectAll();
	public void create(Long pers1_fk, Long pers2_fk, Date uhrzeit, Long lokalitaet_fk, Long ort_fk, int bewertung,Long verkehrsmittel_pers1_fk,String kommentar, Long verkehrsmittel_pers2_fk, String aIdEmpfaenger, String aIdSender, String locationPers1, String locationPers2);
	public Meeting findMeetingById(Long id);
	public Meeting deleteById(Long id);
	public void updateMeeting(Meeting meeting);
	public List<Meeting> selectMeetingByPers1Fk(Long id);
	public List<Meeting> selectMeetingByPers2Fk(Long id);
	public Meeting selectMeetingByPers1Fk_uhrzeit(Long id, int hours, int minutes);
	Meeting selectMeetingByPers2Fk_uhrzeit(Long id, int hours, int minutes);

}
