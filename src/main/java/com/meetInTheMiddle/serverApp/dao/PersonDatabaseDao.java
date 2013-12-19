package com.meetInTheMiddle.serverApp.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.meetInTheMiddle.serverApp.domain.Person;
import com.meetInTheMiddle.serverApp.util.Constants;

public class PersonDatabaseDao implements PersonDao {
	
	public class PersonMapper implements RowMapper<Person> {
	     /*implement abstract method for declaring mapping
	     *between POJO attributes and relational table attributes
	     */
	    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Person person=new Person();
	        person.setFirstName(rs.getString("VORNAME"));
	        person.setLastName(rs.getString("NACHNAME"));
	        person.setBirthday(rs.getDate("GEBURTSDATUM"));
	        person.setPhone(rs.getString("TELEFONNR"));
	        person.setEmail(rs.getString("EMAIL"));
	        person.setPassword(rs.getString("PASSWORD"));
	        person.setInterests(rs.getString("INTERESSEN"));
	        return person;
	    }
	}
	
	DataSource dataSource;
	
	@Autowired(required = true)
	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	private JdbcTemplate jdbcTemplate;  
	
	public PersonDatabaseDao() {
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
		source.setUsername("eBW13Db02");
		source.setPassword("eBW13Db");
		
		dataSource = source;
	}
	  
	@Override
	public List<Person> selectAll() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		
		List<Person> list = select.query("select * from PERSON", new PersonMapper());
		Logger.getGlobal().fine("PersonDao.selectAll(): " + list.size() + " Personen gefunden.");
		return list;
	}

	@Override
	public void create(String firstName, String lastName,Date birthday, String phone, String email,
			String kontaktliste, String password, String interests) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		insert.update(
				"INSERT INTO PERSON (ID, VORNAME, NACHNAME,GEBURTSDATUM,TELEFONNR,EMAIL,KONTAKTLISTE_FK,PASSWORD,INTERESSEN) VALUES(SEQUENCE_PERSON_PK.NEXTVAL,?,?,?,?,?,?,?,?)",
				new Object[] { firstName, lastName, birthday, phone, email, kontaktliste, 
						password, interests });
	}

	@Override
	public List<Person> validate(String email, String password) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select
				.query("Select EMAIL, PASSWORD from Person where EMAIL = ? AND PASSWORD = ?);",
						new PersonMapper());
	}
}
