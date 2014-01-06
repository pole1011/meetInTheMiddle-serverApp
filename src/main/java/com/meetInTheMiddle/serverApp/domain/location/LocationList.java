package com.meetInTheMiddle.serverApp.domain.location;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author Felix
 *
 */
@XmlRootElement(name = "locations")
public class LocationList {

	@XmlElementRef
	private List<Location> list;

	public List<Location> getList() {
		return list;
	}

	public void setList(List<Location> locations) {
		this.list = locations;
	}
}
