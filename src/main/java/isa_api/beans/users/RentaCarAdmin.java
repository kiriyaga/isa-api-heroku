package isa_api.beans.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import isa_api.beans.car.RentaCarCompany;

@Entity
@DiscriminatorValue("CARADMIN")
public class RentaCarAdmin extends User {

	@OneToOne(optional = false)
	private RentaCarCompany carCompany;

	public RentaCarAdmin(String name, String lastName, String username, String password, String email, String telephone,
			String adress, AuthorityEnum authority) {
		super(name, lastName, username, password, email, telephone, adress, authority);
		// TODO Auto-generated constructor stub
	}

	public RentaCarCompany getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(RentaCarCompany carCompany) {
		this.carCompany = carCompany;
	}

}