package com.meetInTheMiddle.serverApp.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meetInTheMiddle.serverApp.domain.Person;

public class PersonMockDao implements PersonDao {
	
	private Map<String, Person> persons = new HashMap<>();
	
	public PersonMockDao() {
		persons.put("dieter@furgison.de", new Person("Dieter", "Furgison", new Date(new java.util.Date().getTime()), null, "dieter@furgison.de", 0, "test", null));
		persons.put("dasfdf@xscvcsd.de", new Person("Ralf", "Bauer", new Date(new java.util.Date().getTime()), null, "dasfdf@xscvcsd.de", 0, null, null));
		persons.put("sdafsf@ycsafadf.de", new Person("Leo", "Podiev", new Date(new java.util.Date().getTime()), null, "sdafsf@ycsafadf.de", 0, null, null));
	}

	@Override
	public List<Person> selectAll() {
		return new ArrayList<Person>(persons.values());
	}

	@Override
	public void create(String firstName, String lastName, Date birthday,
			String phone, String email, Integer kontaktliste, String password,
			String interests) {
		persons.put(email, new Person(firstName, lastName, birthday, phone, email, 1, password, interests)); // TODO: kontaktliste_fk = 0???
	}
	
	public void create(Person person){
		persons.put(person.getEmail(), new Person(person.getFirstName(), person.getLastName(), person.getBirthday(), person.getPhone(), person.getEmail(), person.getKontaktliste_fk(), person.getPassword(), person.getInterests()));
	}

	@Override
	public List<Person> validate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findPersonById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person deleteByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
