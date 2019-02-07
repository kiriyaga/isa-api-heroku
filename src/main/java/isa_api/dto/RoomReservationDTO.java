package isa_api.dto;

import java.util.Date;
import java.util.List;

import isa_api.beans.hotel.HotelAdditionalService;
import isa_api.beans.hotel.Room;
import isa_api.beans.users.RegistredUser;

public class RoomReservationDTO {

	private Date checkInDate;
	private Date checkOutDate;
	private Room room;
	public List<HotelAdditionalService> additionalServices;
	private UserLoginDTO owner;

	public RoomReservationDTO() {
		super();
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

	public UserLoginDTO getOwner() {
		return owner;
	}

	public void setOwner(UserLoginDTO owner) {
		this.owner = owner;
	}



}
