package isa_api.beans.hotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class HotelFastReservationOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date checkInDate;
	private Date checkOutDate;

	@ManyToOne(optional = false)
	@JsonBackReference
	private Room room;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<HotelAdditionalService> additionalServices;
	
	private double additionalServicesDiscount;
	
	private double price;
	
	private boolean active;

	public HotelFastReservationOffer() {
		super();
		this.setActive(true);
		// TODO Auto-generated constructor stub
	}

	public HotelFastReservationOffer(Date checkInDate, Date checkOutDate, Room room) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		this.setActive(true);
		
	}
	
	public void setDiscountAndPrice(Room room, Date checkInDate, List<HotelAdditionalService> additionalServices) {
		
		this.additionalServicesDiscount = 0.05 * additionalServices.size();
		
		double asPrice = 0.0;
		
		for (HotelAdditionalService has : additionalServices) {
			asPrice += has.getPrice();
		}
		
		Calendar ci = Calendar.getInstance();
		ci.setTime(checkInDate);
		
		Calendar today = Calendar.getInstance();
		Date now = new Date();
		today.setTime(now);
		
	    long start = today.getTimeInMillis();
	    long end = ci.getTimeInMillis();
	    long dana = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
		double roomPrice = 0.0;
		
		dana++;
		
	    if(dana <= 31.0) {
	    	roomPrice = room.getNextMonthPrice();
	    } else if (dana <= 92.0) {
	    	roomPrice = room.getNextThreeMonthPrice();
	    } else {
	    	roomPrice = room.getNextHalfYearPrice();
	    }

		this.price = 0.8 * roomPrice * dana + asPrice * (1 - this.additionalServicesDiscount);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<HotelAdditionalService> getAdditionalServices() {
		
		if (this.additionalServices==null) {
			this.additionalServices = new ArrayList<>();
		}
		
		return additionalServices;
	}

	public void setAdditionalServices(List<HotelAdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getAdditionalServicesDiscount() {
		return additionalServicesDiscount;
	}

	public void setAdditionalServicesDiscount(double additionalServicesDiscount) {
		this.additionalServicesDiscount = additionalServicesDiscount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
