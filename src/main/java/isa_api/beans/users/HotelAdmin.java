package isa_api.beans.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import isa_api.beans.hotel.HotelCompany;

@Entity
@DiscriminatorValue("HOTELADMIN")
public class HotelAdmin extends User {

	public HotelAdmin(String name, String lastName, String username, String password, String email, String telephone,
			String adress, AuthorityEnum authority) {
		super(name, lastName, username, password, email, telephone, adress, authority);
		// TODO Auto-generated constructor stub
	}

	@OneToOne(optional = false)
	private HotelCompany hotel;

	public HotelAdmin() {

	}

	public HotelCompany getHotelCompany() {
		return hotel;
	}

	public void setHotelCompany(HotelCompany hotelCompany) {
		this.hotel = hotelCompany;
	}

}