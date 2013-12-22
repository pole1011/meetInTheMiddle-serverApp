package com.meetInTheMiddle.serverApp.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meetInTheMiddle.serverApp.domain.Person;

public class PersonMockDao implements PersonDao {
	
	private Map<String, Person> persons = new HashMap<>();
	
	public PersonMockDao() {
		persons.put("dieter@furgison.de", new Person("Dieter", "Furgison", new Date(new java.util.Date().getTime()), null, "dieter@furgison.de", 0, null, null));
		persons.put("dasfdf@xscvcsd.de", new Person("Ralf", "Bauer", new Date(new java.util.Date().getTime()), null, "dasfdf@xscvcsd.de", 0, null, null));
		persons.put("sdafsf@ycsafadf.de", new Person("Leo", "Podiev", new Date(new java.util.Date().getTime()), null, "sdafsf@ycsafadf.de", 0, null, null));
	}

	@Override
	public List<Person> selectAll() {
		return new ArrayList<Person>(persons.values());
	}

	@Override
	public void create(String firstName, String lastName, Date birthday,
			String phone, String email, String kontaktliste, String password,
			String interests) {
		persons.put(email, new Person(firstName, lastName, birthday, phone, email, 0, password, kontaktliste)); // TODO: kontaktliste_fk = 0???
	}

	@Override
	public List<Person> validate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
