package com.meetInTheMiddle.serverApp.dao.meeting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.meetInTheMiddle.serverApp.domain.meeting.Meeting;
import com.meetInTheMiddle.serverApp.util.Constants;

/**
 * 
 * @author Felix
 *
 */
public class MeetingDatabaseDao implements MeetingDao{
	
	public class MeetingMapper implements RowMapper<Meeting> {
	     /*implement abstract method for declaring mapping
	     *between POJO attributes and relational table attributes
	     */
	    public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Meeting meeting=new Meeting();
	        meeting.setId(rs.getLong("ID"));
	        meeting.setPers1_fk(rs.getLong("PERS1_FK"));
	        meeting.setPers2_fk(rs.getLong("PERS2_FK"));
	        meeting.setUhrzeit(rs.getDate("UHRZEIT"));
	        meeting.setLokalitaet_fk(rs.getLong("LOKALITAET_FK"));
	        meeting.setOrt_fk(rs.getLong("ORT_FK"));
	        meeting.setBewertung(rs.getInt("BEWERTUNG"));
	        meeting.setVerkehrsmittel_pers1_fk(rs.getLong("VERKEHRSMITTEL_PERS1_FK"));
	        meeting.setKommentar(rs.getString("KOMMENTAR"));
	        meeting.setVerkehrsmittel_pers2_fk(rs.getLong("VERKEHRSMITTEL_PERS2_FK"));
	        meeting.setaIdEmpfaenger(rs.getString("AID_EMPFAENGER"));
	        meeting.setaIdSender(rs.getString("AID_SENDER"));
	        return meeting;
	    }
	}
	
	DataSource dataSource;
	
	@Autowired(required = true)
	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	private JdbcTemplate jdbcTemplate;  
	
	public MeetingDatabaseDao() {
		try {
			Class.forName(oracle.jdbc.driver.OracleDriver.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
		source.setUrl(Constants.uri);
		source.setUsername(Constants.user);
		source.setPassword(Constants.password);
		
		dataSource = source;
	}

	@Override
	public List<Meeting> selectAll() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		
		List<Meeting> list = select.query("select * from TREFFEN", new MeetingMapper());
		Logger.getGlobal().fine("MeetingDao.selectAll(): " + list.size() + " Treffen gefunden.");
		return list;
	}

	@Override
	public void create(Long pers1_fk, Long pers2_fk, Date uhrzeit,
			Long lokalitaet_fk, Long ort_fk, int bewertung,
			Long verkehrsmittel_pers1_fk, String kommentar, Long verkehrsmittel_pers2_fk, String aIdEmpfaenger, String aIdSender, String locationPers1, String locationPers2) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		insert.update(
				"INSERT INTO TREFFEN (ID,PERS1_FK,PERS2_FK,UHRZEIT,LOKALITAET_FK,ORT_FK,BEWERTUNG,VERKEHRSMITTEL_PERS1_FK,KOMMENTAR, VERKEHRSMITTEL_PERS2_FK, AID_SENDER, AID_EMPFAENGER, LOCATION_PERS1, LOCATION_PERS2) VALUES(SEQUENCE_TREFFEN_PK.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { pers1_fk, pers2_fk,uhrzeit,lokalitaet_fk,ort_fk,bewertung,verkehrsmittel_pers1_fk,kommentar,verkehrsmittel_pers2_fk, aIdEmpfaenger, aIdSender, locationPers1, locationPers2});
	}

	@Override
	public Meeting findMeetingById(Long id) {
		JdbcTemplate select = new JdbcTemplate(dataSource);

		return (Meeting) select.queryForObject("Select * from TREFFEN where id=?", 
				new Object[] { id },
	            new MeetingMapper());
	}

	@Override
	public Meeting deleteById(Long id) {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("Delete from TREFFEN where id=?", new Object[] {id});
		return null;
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update("update TREFFEN set PERS1_FK = ?,PERS2_FK = ?,UHRZEIT = ?,LOKALITAET_FK = ?,ORT_FK = ?,BEWERTUNG = ?,VERKEHRSMITTEL_PERS1_FK = ?, KOMMENTAR = ?, VERKEHRSMITTEL_PERS2_FK where id= ?", 
				new Object[] {meeting.getPers1_fk(),meeting.getPers2_fk(),meeting.getUhrzeit(),meeting.getLokalitaet_fk(),meeting.getOrt_fk(),meeting.getBewertung(),meeting.getVerkehrsmittel_pers1_fk(),meeting.getKommentar(),meeting.getVerkehrsmittel_pers2_fk(),meeting.getId()});
	}

}
