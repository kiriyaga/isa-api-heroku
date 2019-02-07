package isa_api.beans.flight;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import isa_api.beans.Company;
import isa_api.beans.Location;

@Entity
@DiscriminatorValue("AVIO")
public class AvioCompany extends Company {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "avioCompany", cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<Flight> flights;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	public List<Location> destinations;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Earnings> earnings;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<AvioCompanyUsers> users;

	public AvioCompany() {
		super();
	}

	public List<Flight> getFlights() {
		if (flights == null)
			flights = new ArrayList<Flight>();
		return flights;
	}

	public void addFlights(Flight newFlight) {
		if (newFlight == null)
			return;
		if (this.flights == null)
			this.flights = new ArrayList<Flight>();
		if (!this.flights.contains(newFlight)) {
			this.flights.add(newFlight);
			newFlight.setAvioCompany(this);
		}
	}

	public void removeFlights(Flight oldFlight) {
		if (oldFlight == null)
			return;
		if (this.flights != null)
			if (this.flights.contains(oldFlight)) {
				this.flights.remove(oldFlight);
				oldFlight.setAvioCompany((AvioCompany) null);
			}
	}

	public List<Location> getDestinations() {
		return destinations;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void setDestinations(List<Location> destinations) {
		this.destinations = destinations;
	}

	public List<Earnings> getEarnings() {
		return earnings;
	}

	public void setEarnings(List<Earnings> earnings) {
		this.earnings = earnings;
	}

	public List<AvioCompanyUsers> getUsers() {
		return users;
	}

	public void setUsers(List<AvioCompanyUsers> users) {
		this.users = users;
	}

}