package isa_api.beans.hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Where(clause = "active = 1")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int number;
	private int floor;
	private double rate;
	private double nextMonthPrice;
	private double nextThreeMonthPrice;
	private double nextHalfYearPrice;
	private int numberOfBeds;
	private int rateCount;
	private boolean active;
	private boolean fastReserve;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RoomReservation> roomReservation;

	@ManyToOne(optional = false)
	@JsonBackReference
	private HotelCompany hotelCompany;

	public Room() {
		super();
		this.rate = 0.0;
		this.rateCount = 0;
		this.active = true;
	}

	public Room(int floor, int number, int numberOfBeds, double nextMonthPrice, double nextThreeMonthPrice,
				double nextHalfYearPrice, HotelCompany hotelCompany, boolean fastReserve) {
		super();
		this.nextMonthPrice = nextMonthPrice;
		this.nextThreeMonthPrice = nextThreeMonthPrice;
		this.nextHalfYearPrice = nextHalfYearPrice;
		this.number = number;
		this.floor = floor;
		this.numberOfBeds = numberOfBeds;
		this.hotelCompany = hotelCompany;
		this.fastReserve = fastReserve;
		this.active = true;
	}

	public List<RoomReservation> getRoomReservation() {
		if (roomReservation == null)
			roomReservation = new ArrayList<RoomReservation>();
		return roomReservation;
	}

	public Iterator<RoomReservation> getIteratorRoomReservation() {
		if (roomReservation == null)
			roomReservation = new ArrayList<RoomReservation>();
		return roomReservation.iterator();
	}

	public void setRoomReservation(List<RoomReservation> newRoomReservation) {
		removeAllRoomReservation();
		for (Iterator<RoomReservation> iter = newRoomReservation.iterator(); iter.hasNext();)
			addRoomReservation((RoomReservation) iter.next());
	}

	public void addRoomReservation(RoomReservation newRoomReservation) {
		if (newRoomReservation == null)
			return;
		if (this.roomReservation == null)
			this.roomReservation = new ArrayList<RoomReservation>();
		if (!this.roomReservation.contains(newRoomReservation)) {
			this.roomReservation.add(newRoomReservation);
			newRoomReservation.setRoom(this);
		}
	}

	public void removeRoomReservation(RoomReservation oldRoomReservation) {
		if (oldRoomReservation == null)
			return;
		if (this.roomReservation != null)
			if (this.roomReservation.contains(oldRoomReservation)) {
				this.roomReservation.remove(oldRoomReservation);
				oldRoomReservation.setRoom((Room) null);
			}
	}

	public void removeAllRoomReservation() {
		if (roomReservation != null) {
			RoomReservation oldRoomReservation;
			for (Iterator<?> iter = getIteratorRoomReservation(); iter.hasNext();) {
				oldRoomReservation = (RoomReservation) iter.next();
				iter.remove();
				oldRoomReservation.setRoom((Room) null);
			}
		}
	}

	public HotelCompany getHotelCompany() {
		return hotelCompany;
	}

	public void setHotelCompany(HotelCompany newHotelCompany) {
		if (this.hotelCompany == null || !this.hotelCompany.equals(newHotelCompany)) {
			if (this.hotelCompany != null) {
				HotelCompany oldHotelCompany = this.hotelCompany;
				this.hotelCompany = null;
				oldHotelCompany.removeRooms(this);
			}
			if (newHotelCompany != null) {
				this.hotelCompany = newHotelCompany;
				this.hotelCompany.addRooms(this);
			}
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isFastReserve() {
		return fastReserve;
	}

	public void setFastReserve(boolean fastReserve) {
		this.fastReserve = fastReserve;
	}

	public double getNextMonthPrice() {
		return nextMonthPrice;
	}

	public void setNextMonthPrice(double nextMonthPrice) {
		this.nextMonthPrice = nextMonthPrice;
	}

	public double getNextThreeMonthPrice() {
		return nextThreeMonthPrice;
	}

	public void setNextThreeMonthPrice(double nextThreeMonthPrice) {
		this.nextThreeMonthPrice = nextThreeMonthPrice;
	}

	public double getNextHalfYearPrice() {
		return nextHalfYearPrice;
	}

	public void setNextHalfYearPrice(double nextHalfYearPrice) {
		this.nextHalfYearPrice = nextHalfYearPrice;
	}

}