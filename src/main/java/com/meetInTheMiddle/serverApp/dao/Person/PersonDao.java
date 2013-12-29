package com.meetInTheMiddle.serverApp.dao.Person;

import java.util.Date;
import java.util.List;

import com.meetInTheMiddle.serverApp.domain.Person.Person;
/**
 * 
 * @author Felix
 *
 */
public interface PersonDao {
	public List<Person> selectAll();
	public void create(String firstName, String lastName,Date birthday, String phone, String email,
			Integer kontaktliste, String password, String interests);
	public List<Person> validate(String email, String password);
	public Person findPersonById(Long id);
	public Person deleteByEmail(String email);
}
