package com.meetInTheMiddle.serverApp.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class PersonList {

	@XmlElementRef
	private List<Person> list;

	public List<Person> getList() {
		return list;
	}

	public void setList(List<Person> persons) {
		this.list = persons;
	}
	
	
}
