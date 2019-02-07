package isa_api.dto;

import isa_api.beans.flight.Flight;

public class SearchedFlightsDTO {

	private String company;
	private Flight flight;

	public SearchedFlightsDTO() {
		// TODO Auto-generated constructor stub
	}

	public SearchedFlightsDTO(String company, Flight flight) {
		super();
		this.company = company;
		this.flight = flight;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
