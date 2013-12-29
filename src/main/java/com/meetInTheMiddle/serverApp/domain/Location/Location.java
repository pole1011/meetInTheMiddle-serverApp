package com.meetInTheMiddle.serverApp.domain.Location;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="location")
public class Location {
	public Location(){
		
	}
public Location(String stadtname, String plz) {
		super();
		stadtname = stadtname;
		this.plz = plz;
	}
@XmlElement
private long id;
@XmlElement(required=true)
private String stadtname;
@XmlElement(required=true)
private String plz;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getStadtname() {
	return stadtname;
}
public void setStadtname(String Stadtname) {
	stadtname = Stadtname;
}
public String getPlz() {
	return plz;
}
public void setPlz(String plz) {
	this.plz = plz;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((stadtname == null) ? 0 : stadtname.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((plz == null) ? 0 : plz.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Location other = (Location) obj;
	if (stadtname == null) {
		if (other.stadtname != null)
			return false;
	} else if (!stadtname.equals(other.stadtname))
		return false;
	if (id != other.id)
		return false;
	if (plz == null) {
		if (other.plz != null)
			return false;
	} else if (!plz.equals(other.plz))
		return false;
	return true;
}
@Override
public String toString() {
	return "Location [id=" + id + ", Stadtname=" + stadtname + ", plz=" + plz
			+ "]";
}
}
