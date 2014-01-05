package com.meetInTheMiddle.serverApp.dao.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.meetInTheMiddle.serverApp.domain.person.Person;
import com.meetInTheMiddle.serverApp.util.Constants;
/**
 * 
 * @author Felix
 *
 */
public class PersonDatabaseDao implements PersonDao {
	
	public class PersonMapper implements RowMapper<Person> {
	     /*implement abstract method for declaring mapping
	     *between POJO attributes and relational table attributes
	     */
	    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Person person=new Person();
	        person.setId(rs.getLong("ID"));
	        person.setFirstName(rs.getString("VORNAME"));
	        person.setLastName(rs.getString("NACHNAME"));
	        person.setBirthday(rs.getDate("GEBURTSDATUM"));
	        person.setPhone(rs.getString("TELEFONNR"));
	        person.setEmail(rs.getString("EMAIL"));
	        person.setWohnort_fk(rs.getLong("WOHNORT_FK"));
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
		source.setUsername(Constants.user);
		source.setPassword(Constants.password);
		
		dataSource = source;
	}
	  
	
	
	@Override
	public List<Person> selectAll() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		
		List<Person> list = select.query("select * from PERSON", new PersonMapper());
		Logger.getGlobal().fine("PersonDao.selectAll(): " + list.size() + " Personen gefunden.");
		return list;
	}

	public void create(String firstName, String lastName,Date test, String phone, String email,
			String password, String interests) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);

SimpleDateFormat birthday = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try {
			 test = birthday.parse(test.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test.toString());
		insert.update(
				"INSERT INTO PERSON (ID, VORNAME, NACHNAME,GEBURTSDATUM,TELEFONNR,EMAIL, WOHNORT_FK,PASSWORD,INTERESSEN) VALUES(SEQUENCE_PERSON_PK.NEXTVAL,?,?,?,?,?,SEQUENCE_WOHNORT_FK.NEXTVAL,?,?)",
				new Object[] { firstName, lastName, test, phone, email, 
						password, interests });
	}

	@Override
	public List<Person> validate(String email, String password) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select
				.query("Select EMAIL, PASSWORD from Person where EMAIL = ? AND PASSWORD = ?;",
						new PersonMapper());
	}



	@Override
	public Person findPersonById(Long id) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return (Person) select.queryForObject("Select * from Person where id=?", 
				new Object[] { id },
	            new PersonMapper());
	}

	@Override
	public void updatePerson(Person person) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update("update Person set VORNAME=?, NACHNAME = ?, GEBURTSDATUM = ?, TELEFONNR = ?, EMAIL = ?, WOHNORT_FK = ?, PASSWORD = ?, INTERESSEN = ? where id= ?", new Object[] {person.getFirstName(),person.getLastName(),person.getBirthday(),person.getPhone(),person.getEmail(),person.getPassword(),person.getInterests(),person.getId()});
	}

	@Override
	public Person deleteByEmail(String email) {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("Delete from Person where email= ?", new Object[] {email});
		return null;
	}



	@Override
	public void createContact(Long person_1_id, Long person_2_id) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		insert.update(
				"INSERT INTO KONTAKTLISTE (ID, BELONGSTO, KONTAKT_FK) VALUES(SEQUENCE_KONTAKTLISTE_PK.NEXTVAL,?,?)",
				new Object[] { person_1_id, person_2_id});
	}



	@Override
	public List<Person> findContactsById(Long id) {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			return  select.query("SELECT * from Person where id in (select K.KONTAKT_FK from KONTAKTLISTE K where K.belongsto = ?)", 
					new Object[] { id },
		            new PersonMapper());
	}
}
