package isa_api.dto;

import isa_api.beans.hotel.HotelCompany;
import isa_api.beans.hotel.Room;

public class SearchedRoomsDTO {

	private HotelCompany hotelCompany;
	private Room room;

	public SearchedRoomsDTO() {
		super();
	}

	public SearchedRoomsDTO(HotelCompany hotelCompany, Room room) {
		super();
		this.setHotelCompany(hotelCompany);
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HotelCompany getHotelCompany() {
		return hotelCompany;
	}

	public void setHotelCompany(HotelCompany hotelCompany) {
		this.hotelCompany = hotelCompany;
	}

}
