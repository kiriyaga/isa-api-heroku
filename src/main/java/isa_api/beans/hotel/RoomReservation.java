package isa_api.beans.hotel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import isa_api.beans.Reservation;
import isa_api.beans.users.RegistredUser;

@Entity
public class RoomReservation extends Reservation {

	private Date checkIn;
	private Date checkOut;

	@ManyToOne(optional = false)
	@JsonBackReference
	private Room room;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<HotelAdditionalService> additionalServices;

	private double additionalServicesDiscount;

	private double price;

	public RoomReservation() {

	}

	public RoomReservation(RegistredUser owner) {
		super(owner);
	}
	
	public RoomReservation(RegistredUser owner, HotelFastReservationOffer hfro) {
		super(owner);
		this.setId(hfro.getId());
		this.setCheckIn(hfro.getCheckIn());
		this.setCheckOut(hfro.getCheckOut());
		this.setRoom(hfro.getRoom());
		this.setAdditionalServices(hfro.getAdditionalServices());
		this.setAdditionalServicesDiscount(hfro.getAdditionalServicesDiscount());
		this.setPrice(hfro.getPrice());
		
	}

	public void setDiscountAndPrice(Room room, Date checkIn, List<HotelAdditionalService> additionalServices) {
		
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

		this.price = roomPrice + asPrice * (1 - this.additionalServicesDiscount);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room newRoom) {
		if (this.room == null || !this.room.equals(newRoom)) {
			if (this.room != null) {
				Room oldRoom = this.room;
				this.room = null;
				oldRoom.removeRoomReservation(this);
			}
			if (newRoom != null) {
				this.room = newRoom;
				this.room.addRoomReservation(this);
			}
		}
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

	public List<HotelAdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<HotelAdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAdditionalServicesDiscount() {
		return additionalServicesDiscount;
	}

	public void setAdditionalServicesDiscount(double additionalServicesDiscount) {
		this.additionalServicesDiscount = additionalServicesDiscount;
	}

}