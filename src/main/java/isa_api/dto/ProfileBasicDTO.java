package isa_api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ProfileBasicDTO {

	@NotNull(message = "First name is required")
	private String name;

	@NotNull(message = "Last name is required")
	private String lastName;

	@NotNull(message = "Email is required")
	@Email(message = "Email must be a valid email address")
	private String email;

	private String adress;

	private String telephone;

	public ProfileBasicDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
