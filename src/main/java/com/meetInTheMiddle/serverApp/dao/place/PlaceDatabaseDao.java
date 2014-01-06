package com.meetInTheMiddle.serverApp.dao.place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.meetInTheMiddle.serverApp.domain.place.Place;
import com.meetInTheMiddle.serverApp.util.Constants;

/**
 * 
 * @author Felix
 *
 */
public class PlaceDatabaseDao implements PlaceDao{

	public class LocationMapper implements RowMapper<Place> {
	     /*implement abstract method for declaring mapping
	     *between POJO attributes and relational table attributes
	     */
	    public Place mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Place location=new Place();
	        location.setId(rs.getLong("ID"));
	        location.setStadtname(rs.getString("STADTNAME"));
	        location.setPlz(rs.getString("PLZ"));
	        return location;
	    }
	}
	
	DataSource dataSource;
	
	@Autowired(required = true)
	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	private JdbcTemplate jdbcTemplate;  
	
	public PlaceDatabaseDao() {
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
	public List<Place> selectAll() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		
		List<Place> list = select.query("select * from ORT", new LocationMapper());
		Logger.getGlobal().fine("LocationDao.selectAll(): " + list.size() + " Orte gefunden.");
		return list;
	}

	@Override
	public void create(String stadtname, String plz) {
			JdbcTemplate insert = new JdbcTemplate(dataSource);
			insert.update(
					"INSERT INTO ORT (ID,STADTNAME,PLZ) VALUES(SEQUENCE_ORT_PK.NEXTVAL,?,?)",
					new Object[] { stadtname, plz});
	}

	@Override
	public Place findLocationById(Long id) {
		JdbcTemplate select = new JdbcTemplate(dataSource);

		return (Place) select.queryForObject("Select * from ORT where id=?", 
				new Object[] { id },
	            new LocationMapper());
	}

	@Override
	public Place deleteByStadtnameUndPlz(String stadtname, String plz) {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("Delete from ORT where stadtname= ? and plz=?", new Object[] {stadtname,plz});
		return null;
	}

	@Override
	public void updateLocation(Place location) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update("update ort set stadtname=?, plz = ? where id= ?", new Object[] {location.getStadtname(),location.getPlz(),location.getId()});
	}

}
