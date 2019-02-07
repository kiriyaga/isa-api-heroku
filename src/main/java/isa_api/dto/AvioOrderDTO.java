package isa_api.dto;

import java.util.List;

import isa_api.beans.Company;
import isa_api.beans.flight.Flight;
import isa_api.beans.flight.Seat;

public class AvioOrderDTO {

	private List<Seat> seats;
	private Flight flight;
	private String mode;
	private Company company;
	private List<UserLoginDTO> friends;
	private UserLoginDTO owner;

	public AvioOrderDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<UserLoginDTO> getFriends() {
		return friends;
	}

	public void setFriends(List<UserLoginDTO> friends) {
		this.friends = friends;
	}

	public UserLoginDTO getOwner() {
		return owner;
	}

	public void setOwner(UserLoginDTO owner) {
		this.owner = owner;
	}

}
