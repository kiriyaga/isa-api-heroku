package isa_api.dto;

import isa_api.beans.flight.AvioCompany;
import isa_api.beans.flight.Flight;

public class DeleteFlightDTO {

	private AvioCompany company;
	private Flight flight;
	
	public DeleteFlightDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public AvioCompany getCompany() {
		return company;
	}


	public void setCompany(AvioCompany company) {
		this.company = company;
	}


	
	
}
