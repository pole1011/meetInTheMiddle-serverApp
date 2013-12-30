package com.meetInTheMiddle.serverApp.dao.location;

import java.util.Date;
import java.util.List;

import com.meetInTheMiddle.serverApp.domain.location.Location;
/**
 * 
 * @author Felix
 *
 */
public interface LocationDao {
	public List<Location> selectAll();
	public void create(String stadtname, String plz);
	public Location findLocationById(Long id);
	public Location deleteByStadtnameUndPlz(String stadtname, String plz);
	public void updateLocation(Location location);
}
