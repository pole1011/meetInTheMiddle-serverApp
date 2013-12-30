package com.meetInTheMiddle.serverApp.domain.person;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
public class Person {
	public Person(String firstName, String lastName, Date birthday,
			String phone, String email, Long kontaktliste_fk, String password,
			String interests) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		Kontaktliste_fk = kontaktliste_fk;
		this.password = password;
		this.interests = interests;
	}
	public Person() {
	}
@XmlElement
private Long id;
@XmlElement(required=true)
private String firstName;
@XmlElement(required=true)
private String lastName;
@XmlElement
private Date birthday;
@XmlElement
private String phone;
@XmlElement(required = true)
private String email;
@XmlElement
private Long Wohnort_fk;
@XmlElement
private Long privateinstellungen_fk;
@XmlElement (required = true)
private Long Kontaktliste_fk = 1L;
@XmlElement(required=true)
private String password;
@XmlElement
private String interests;

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}

public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public Long getWohnort_fk() {
	return Wohnort_fk;
}

public void setWohnort_fk(Long wohnort_fk) {
	Wohnort_fk = wohnort_fk;
}

public Long getPrivateinstellungen_fk() {
	return privateinstellungen_fk;
}
public void setPrivateinstellungen_fk(Long privateinstellungen_fk) {
	this.privateinstellungen_fk = privateinstellungen_fk;
}

public Long getKontaktliste_fk() {
	return Kontaktliste_fk;
}
public void setKontaktliste_fk(Long kontaktliste_fk) {
	Kontaktliste_fk = kontaktliste_fk;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getInterests() {
	return interests;
}
public void setInterests(String interests) {
	this.interests = interests;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Person other = (Person) obj;
	if (Kontaktliste_fk != other.Kontaktliste_fk)
		return false;
	if (Wohnort_fk != other.Wohnort_fk)
		return false;
	if (birthday == null) {
		if (other.birthday != null)
			return false;
	} else if (!birthday.equals(other.birthday))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (interests == null) {
		if (other.interests != null)
			return false;
	} else if (!interests.equals(other.interests))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (phone == null) {
		if (other.phone != null)
			return false;
	} else if (!phone.equals(other.phone))
		return false;
	if (privateinstellungen_fk != other.privateinstellungen_fk)
		return false;
	return true;
}
@Override
public String toString() {
	return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
			+ lastName + ", birthday=" + birthday + ", phone=" + phone
			+ ", email=" + email + ", Wohnort_fk=" + Wohnort_fk
			+ ", privateinstellungen_fk=" + privateinstellungen_fk
			+ ", Kontaktliste_fk=" + Kontaktliste_fk + ", password=" + password
			+ ", interests=" + interests + "]";
}

}