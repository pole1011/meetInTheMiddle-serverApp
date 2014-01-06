package com.meetInTheMiddle.serverApp.dao.place;

import java.util.List;

import com.meetInTheMiddle.serverApp.domain.place.Place;
/**
 * 
 * @author Felix
 *
 */
public interface PlaceDao {
	public List<Place> selectAll();
	public void create(String stadtname, String plz);
	public Place findPlaceById(Long id);
	public Place deleteByStadtnameUndPlz(String stadtname, String plz);
	public void updatePlace(Place place);
}
