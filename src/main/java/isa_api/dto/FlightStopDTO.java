package isa_api.dto;

import isa_api.beans.Company;
import isa_api.beans.flight.Flight;
import isa_api.beans.flight.FlightStop;

public class FlightStopDTO {
	
	private Company company;
	private FlightStop flightStop;
	private Flight flight;
	
	public FlightStopDTO() {
		
	}
	public FlightStop getFlightStop() {
		return flightStop;
	}
	public void setFlightStop(FlightStop flightStop) {
		this.flightStop = flightStop;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	

}
