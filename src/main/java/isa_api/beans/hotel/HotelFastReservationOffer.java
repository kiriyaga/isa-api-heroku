package isa_api.beans.hotel;

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

@Entity
public class HotelFastReservationOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date checkIn;
	private Date checkOut;

	@ManyToOne(optional = false)
	private Room room;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<HotelAdditionalService> additionalServices;
	
	private double additionalServicesDiscount;
	
	private double price;

	public HotelFastReservationOffer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelFastReservationOffer(Date checkIn, Date checkOut, Room room,
			List<HotelAdditionalService> additionalServices) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.room = room;
		this.additionalServices = additionalServices;
		this.setDiscountAndPrice(this.room, this.checkIn, this.additionalServices);
	}
	
	private void setDiscountAndPrice(Room room, Date checkIn, List<HotelAdditionalService> additionalServices) {
		
		this.additionalServicesDiscount = 0.05 * additionalServices.size();
		
		double asPrice = 0.0;
		
		for (HotelAdditionalService has : additionalServices) {
			asPrice += has.getPrice();
		}
		
		Calendar ci = Calendar.getInstance();
		ci.setTime(checkIn);
		
		Calendar today = Calendar.getInstance();
		Date now = new Date();
		today.setTime(now);
		
	    long start = today.getTimeInMillis();
	    long end = ci.getTimeInMillis();
	    long razlika = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
		double roomPrice = 0.0;
		
	    if(razlika <= 31.0) {
	    	roomPrice = room.getNextMonthPrice();
	    } else if (razlika <= 92.0) {
	    	roomPrice = room.getNextThreeMonthPrice();
	    } else {
	    	roomPrice = room.getNextHalfYearPrice();
	    }

		this.price = 0.8 * roomPrice + asPrice * (1 - this.additionalServicesDiscount);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<HotelAdditionalService> getAdditionalServices() {
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
	
}
