package isa_api.dto;

import isa_api.beans.hotel.HotelFastReservationOffer;
import isa_api.beans.hotel.Room;

public class HotelFastReservationRoomDTO {

	private Room room;
	private HotelFastReservationOffer hfro;
	
	public HotelFastReservationRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HotelFastReservationOffer getHfro() {
		return hfro;
	}

	public void setHfro(HotelFastReservationOffer hfro) {
		this.hfro = hfro;
	}
	
}
