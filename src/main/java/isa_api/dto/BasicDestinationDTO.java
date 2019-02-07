package isa_api.dto;

import isa_api.beans.Location;

public class BasicDestinationDTO {

	private Location location;
	private Long company;

	public BasicDestinationDTO() {
		// TODO Auto-generated constructor stub
	}

	public BasicDestinationDTO(Location location, Long company) {
		this.location = location;
		this.company = company;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

}
