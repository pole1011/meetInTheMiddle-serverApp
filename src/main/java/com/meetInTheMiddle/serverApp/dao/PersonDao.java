package com.meetInTheMiddle.serverApp.dao;

import java.sql.Date;
import java.util.List;

import com.meetInTheMiddle.serverApp.domain.Person;

public interface PersonDao {
	public List<Person> selectAll();
	public void create(String firstName, String lastName,Date birthday, String phone, String email,
			String kontaktliste, String password, String interests);
	public List<Person> validate(String email, String password);
}
