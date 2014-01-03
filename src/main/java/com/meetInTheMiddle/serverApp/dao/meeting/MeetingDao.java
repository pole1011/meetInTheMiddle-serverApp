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
	public void create(Long pers1_fk, Long pers2_fk, Date uhrzeit, Long lokalitaet_fk, Long ort_fk, String bewertung,Long verkehrsmittel_fk,String kommentar);
	public Meeting findMeetingById(Long id);
	public Meeting deleteById(Long id);
	public void updateMeeting(Meeting meeting);
}
