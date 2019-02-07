package isa_api.beans.flight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Where(clause = "active = 1")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double rate;

	private int rateCount;

	@ElementCollection
	private List<String> blackList;

	private String travelTime;

	private String travelDistance;

	private boolean active;

	private double priceForTicket;

	private double additionalPriceCarryBag;
	
	private int maxCarryBag;

	private double additionalPriceCheckBag;
	
	private int maxCheckBag;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private FlightStop startDestination;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private FlightStop endDestination;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<FlightStop> flightStop;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flight", cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<FlightReservation> flightReservation;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public AvioCompany avioCompany;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "flight", cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<Seat> seats;

	public Flight() {

		this.active = true;
	}

	public Flight(Double rate, int rateCount, AvioCompany avioCompany) {
		super();
		this.active = true;
		this.rate = rate;
		this.rateCount = rateCount;
		this.avioCompany = avioCompany;
	}

	public List<Seat> getSeats() {
		if (seats == null)
			seats = new ArrayList<Seat>();
		return seats;
	}

	public void addSeats(Seat newSeat) {
		if (newSeat == null)
			return;
		if (this.seats == null)
			this.seats = new ArrayList<Seat>();
		if (!this.seats.contains(newSeat))
			this.seats.add(newSeat);
	}

	public void removeSeats(Seat oldSeat) {
		if (oldSeat == null)
			return;
		if (this.seats != null)
			if (this.seats.contains(oldSeat))
				this.seats.remove(oldSeat);
	}

	public void removeAllSeats() {
		if (seats != null)
			seats.clear();
	}

	public List<FlightStop> getFlightStop() {
		if (flightStop == null)
			flightStop = new ArrayList<FlightStop>();
		return flightStop;
	}

	public void setFlightStop(List<FlightStop> newFlightStop) {
		removeAllFlightStop();
		for (Iterator<FlightStop> iter = newFlightStop.iterator(); iter.hasNext();)
			addFlightStop((FlightStop) iter.next());
	}

	public void addFlightStop(FlightStop newFlightStop) {
		if (newFlightStop == null)
			return;
		if (this.flightStop == null)
			this.flightStop = new ArrayList<FlightStop>();
		if (!this.flightStop.contains(newFlightStop))
			this.flightStop.add(newFlightStop);
	}

	public void removeFlightStop(FlightStop oldFlightStop) {
		if (oldFlightStop == null)
			return;
		if (this.flightStop != null)
			if (this.flightStop.contains(oldFlightStop))
				this.flightStop.remove(oldFlightStop);
	}

	public void removeAllFlightStop() {
		if (flightStop != null)
			flightStop.clear();
	}

	public List<FlightReservation> getFlightReservation() {
		if (flightReservation == null)
			flightReservation = new ArrayList<FlightReservation>();
		return flightReservation;
	}

	public void setFlightReservation(List<FlightReservation> newFlightReservation) {
		removeAllFlightReservation();
		for (Iterator<FlightReservation> iter = newFlightReservation.iterator(); iter.hasNext();)
			addFlightReservation((FlightReservation) iter.next());
	}

	public void addFlightReservation(FlightReservation newFlightReservation) {
		if (newFlightReservation == null)
			return;
		if (this.flightReservation == null)
			this.flightReservation = new ArrayList<FlightReservation>();
		if (!this.flightReservation.contains(newFlightReservation))
			this.flightReservation.add(newFlightReservation);
	}

	public void removeFlightReservation(FlightReservation oldFlightReservation) {
		if (oldFlightReservation == null)
			return;
		if (this.flightReservation != null)
			if (this.flightReservation.contains(oldFlightReservation))
				this.flightReservation.remove(oldFlightReservation);
	}

	public void removeAllFlightReservation() {
		if (flightReservation != null)
			flightReservation.clear();
	}

	public AvioCompany getAvioCompany() {
		return avioCompany;
	}

	public void setAvioCompany(AvioCompany newAvioCompany) {
		if (this.avioCompany == null || !this.avioCompany.equals(newAvioCompany)) {
			if (this.avioCompany != null) {
				AvioCompany oldAvioCompany = this.avioCompany;
				this.avioCompany = null;
				oldAvioCompany.removeFlights(this);
			}
			if (newAvioCompany != null) {
				this.avioCompany = newAvioCompany;
				this.avioCompany.addFlights(this);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public List<String> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<String> blackList) {
		this.blackList = blackList;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	public String getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(String travelDistance) {
		this.travelDistance = travelDistance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getPriceForTicket() {
		return priceForTicket;
	}

	public void setPriceForTicket(double priceForTicket) {
		this.priceForTicket = priceForTicket;
	}

	public double getAdditionalPriceCarryBag() {
		return additionalPriceCarryBag;
	}

	public void setAdditionalPriceCarryBag(double additionalPriceCarryBag) {
		this.additionalPriceCarryBag = additionalPriceCarryBag;
	}

	public double getAdditionalPriceCheckBag() {
		return additionalPriceCheckBag;
	}

	public void setAdditionalPriceCheckBag(double additionalPriceCheckBag) {
		this.additionalPriceCheckBag = additionalPriceCheckBag;
	}

	public FlightStop getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(FlightStop startDestination) {
		this.startDestination = startDestination;
	}

	public FlightStop getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(FlightStop endDestination) {
		this.endDestination = endDestination;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public int getMaxCarryBag() {
		return maxCarryBag;
	}

	public void setMaxCarryBag(int maxCarryBag) {
		this.maxCarryBag = maxCarryBag;
	}

	public int getMaxCheckBag() {
		return maxCheckBag;
	}

	public void setMaxCheckBag(int maxCheckBag) {
		this.maxCheckBag = maxCheckBag;
	}
}