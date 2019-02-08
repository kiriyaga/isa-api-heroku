package isa_api.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

	@NotNull(message="Username is required!")
	private String username;
	@NotNull(message="Password is required!")
	private String password;

	public LoginDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
