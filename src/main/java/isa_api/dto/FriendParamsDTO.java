package isa_api.dto;

import javax.validation.constraints.NotNull;

public class FriendParamsDTO {

	private String username;
	@NotNull(message = "Name is required!")
	private String name;
	@NotNull(message = "Lastname is required!")
	private String lastname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
