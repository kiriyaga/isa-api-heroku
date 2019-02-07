package isa_api.beans.flight;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import isa_api.beans.Reservation;
import isa_api.beans.users.RegistredUser;

@Entity
public class FlightReservation extends Reservation {

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Seat> seats;

	@ManyToOne(optional = false)
	@JsonBackReference
	public Flight flight;

	public FlightReservation() {
		super();
	}
	
	public FlightReservation(RegistredUser owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight newFlight) {
		if (this.flight == null || !this.flight.equals(newFlight)) {
			if (this.flight != null) {
				Flight oldFlight = this.flight;
				this.flight = null;
				oldFlight.removeFlightReservation(this);
			}
			if (newFlight != null) {
				this.flight = newFlight;
				this.flight.addFlightReservation(this);
			}
		}
	}

	public List<Seat> getSeats() {
		if(this.seats==null)
		{
			this.seats= new ArrayList<>();
		}
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}