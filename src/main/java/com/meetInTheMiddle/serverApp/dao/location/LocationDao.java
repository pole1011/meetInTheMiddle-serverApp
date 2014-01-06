package com.meetInTheMiddle.serverApp.dao.location;

import java.util.List;

import com.meetInTheMiddle.serverApp.domain.location.Location;
/**
 * 
 * @author Felix
 *
 */
public interface LocationDao {
	public List<Location> selectAll();
	public void create(String beschreibung);
	public Location findLocationById(Long id);
	public Location deleteById(Long id);
	public void updateLocation(Location location);
}
