package isa_api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUserDTO {

	@NotNull(message = "Username is required")
	private String username;

	@NotNull(message = "Password is required")
	@Size(min = 5, message = "Password must be at least 5 character long")
	private String password;

	@NotNull(message = "Re-password is required!")
	@Size(min = 5, message = "Re-password must be at least 5 character long")
	private String rePassword;

	@NotNull(message = "First name is required")
	private String name;

	@NotNull(message = "Last name is required")
	private String lastName;

	@NotNull(message = "Email is required")
	@Email(message = "Email must be a valid email address")
	private String email;

	public RegisterUserDTO() {
		// TODO Auto-generated constructor stub
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

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
