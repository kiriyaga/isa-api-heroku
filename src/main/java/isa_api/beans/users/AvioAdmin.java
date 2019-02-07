package isa_api.beans.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import isa_api.beans.flight.AvioCompany;

@Entity
@DiscriminatorValue("AVIOADMIN")
public class AvioAdmin extends User {

	public AvioAdmin(String name, String lastName, String username, String password, String email, String telephone,
			String adress, AuthorityEnum authority) {
		super(name, lastName, username, password, email, telephone, adress, authority);
		// TODO Auto-generated constructor stub
	}

	public AvioAdmin() {

	}

	@OneToOne(optional = false)
	private AvioCompany avio;

	public AvioCompany getAvioCompany() {
		return avio;
	}

	public void setAvioCompany(AvioCompany avioCompany) {
		this.avio = avioCompany;
	}

}