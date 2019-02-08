package isa_api.beans.flight;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import isa_api.beans.Location;

@Entity
public class FlightStop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date takeOff;
	
	private String time;

	@OneToOne(optional = false,cascade=CascadeType.ALL)
	private Location location;
	
	public FlightStop() {
		// TODO Auto-generated constructor stub
	}
	

	public FlightStop(Long id, Date takeOff, String time, Location location) {
		super();
		this.id = id;
		this.takeOff = takeOff;
		this.time = time;
		this.location = location;
	}


	public Date getTakeOff() {
		return takeOff;
	}

	public void setTakeOff(Date takeOff) {
		this.takeOff = takeOff;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}