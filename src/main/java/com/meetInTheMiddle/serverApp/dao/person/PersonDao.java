package com.meetInTheMiddle.serverApp.dao.person;

import java.util.Date;
import java.util.List;

import com.meetInTheMiddle.serverApp.domain.person.Person;
/**
 * 
 * @author Felix
 *
 */
public interface PersonDao {
	public List<Person> selectAll();
	public void create(String firstName, String lastName,Date birthday, String phone, String email,
			 String password, String interests, String androidId);
	public List<Person> validate(String email, String password);
	public Person findPersonById(Long id);
	public Person deleteByEmail(String email);
	void updatePerson(Person person);
	public void createContact(Long person_1_id, Long person_2_id);
	public List<Person> findContactsById(Long id);
	Person findPersonByFirstLastName(String firstName, String lastName);
}
