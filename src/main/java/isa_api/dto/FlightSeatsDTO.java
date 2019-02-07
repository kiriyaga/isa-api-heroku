package isa_api.dto;

import java.util.ArrayList;

import isa_api.beans.flight.Seat;

public class FlightSeatsDTO {

	private Long id;
	private ArrayList<Seat> seats;

	public FlightSeatsDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

}
