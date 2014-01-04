package com.meetInTheMiddle.serverApp.dao.person;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meetInTheMiddle.serverApp.domain.person.Person;

public class PersonMockDao implements PersonDao {
	
	private Map<String, Person> persons = new HashMap<>();
	
	public PersonMockDao() {
		persons.put("dieter@furgison.de", new Person("Dieter", "Furgison", new Date(new java.util.Date().getTime()), null, "dieter@furgison.de", "test", null));
		persons.put("dasfdf@xscvcsd.de", new Person("Ralf", "Bauer", new Date(new java.util.Date().getTime()), null, "dasfdf@xscvcsd.de", null, null));
		persons.put("sdafsf@ycsafadf.de", new Person("Leo", "Podiev", new Date(new java.util.Date().getTime()), null, "sdafsf@ycsafadf.de", null, null));
	}

	@Override
	public List<Person> selectAll() {
		return new ArrayList<Person>(persons.values());
	}

	@Override
	public void create(String firstName, String lastName, Date birthday,
			String phone, String email, String password,
			String interests) {
		persons.put(email, new Person(firstName, lastName, birthday, phone, email, password, interests)); // TODO: kontaktliste_fk = 0???
	}
	
	public void create(Person person){
		persons.put(person.getEmail(), new Person(person.getFirstName(), person.getLastName(), person.getBirthday(), person.getPhone(), person.getEmail(), person.getPassword(), person.getInterests()));
	}

	@Override
	public List<Person> validate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPersonById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person deleteByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createContact(Long person_1_id, Long person_2_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Person> findContactsById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
