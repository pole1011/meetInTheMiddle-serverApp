package com.meetInTheMiddle.serverApp.domain.place;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author Felix
 *
 */
@XmlRootElement(name = "places")
public class PlaceList {

	@XmlElementRef
	private List<Place> list;

	public List<Place> getList() {
		return list;
	}

	public void setList(List<Place> locations) {
		this.list = locations;
	}
	
	
}
