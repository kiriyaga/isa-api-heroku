package isa_api.dto;

import isa_api.beans.hotel.HotelFastReservationOffer;
import isa_api.beans.users.RegistredUser;

public class HotelFastReserveOfferToReservationDTO {

	private RegistredUser owner;
	private HotelFastReservationOffer hfro;
	
	public HotelFastReserveOfferToReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistredUser getOwner() {
		return owner;
	}

	public void setOwner(RegistredUser owner) {
		this.owner = owner;
	}

	public HotelFastReservationOffer getHfro() {
		return hfro;
	}

	public void setHfro(HotelFastReservationOffer hfro) {
		this.hfro = hfro;
	}
	
}
