package com.meetInTheMiddle.serverApp.dao.location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.meetInTheMiddle.serverApp.domain.location.Location;
import com.meetInTheMiddle.serverApp.util.Constants;

public class LocationDatabaseDao implements LocationDao{
	
	public class LocationMapper implements RowMapper<Location> {
	     /*implement abstract method for declaring mapping
	     *between POJO attributes and relational table attributes
	     */
	    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Location location=new Location();
	        location.setId(rs.getLong("ID"));
	        location.setBeschreibung(rs.getString("BESCHREIBUNG"));
	        return location;
	    }
	}
	
	DataSource dataSource;
	
	@Autowired(required = true)
	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	private JdbcTemplate jdbcTemplate;
	
	public LocationDatabaseDao() {
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
	public List<Location> selectAll() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		
		List<Location> list = select.query("select * from LOKALITAET", new LocationMapper());
		Logger.getGlobal().fine("LocationDao.selectAll(): " + list.size() + " Lokalitaeten gefunden.");
		return list;
	}

	@Override
	public void create(String beschreibung) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		insert.update(
				"INSERT INTO LOKALITAET (ID,BESCHREIBUNG) VALUES(SEQUENCE_LOKALITAET_PK.NEXTVAL,?)",
				new Object[] { beschreibung});
	}

	@Override
	public Location findLocationById(Long id) {
		JdbcTemplate select = new JdbcTemplate(dataSource);

		return (Location) select.queryForObject("Select * from LOKALITAET where id=?", 
				new Object[] { id },
	            new LocationMapper());
	}

	@Override
	public Location deleteById(Long id) {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("Delete from LOKALITAET where id=?", new Object[] {id});
		return null;
	}

	@Override
	public void updateLocation(Location location) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update("update LOKALITAET set beschreibung = ? where id= ?", new Object[] {location.getId(),location.getBeschreibung()});
	}

}
