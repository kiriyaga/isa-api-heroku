package isa_api.beans.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SYSADMIN")
public class SystemAdmin extends User {

	public SystemAdmin() {
		// TODO Auto-generated constructor stub
	}

	public SystemAdmin(String name, String lastName, String username, String password, String email, String telephone,
			String adress, AuthorityEnum authority) {
		super(name, lastName, username, password, email, telephone, adress, authority);
		// TODO Auto-generated constructor stub
	}

}