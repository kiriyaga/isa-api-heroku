package isa_api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfilePasswordDTO {

	@NotNull(message = "Password is required")
	@Size(min = 5, message = "Password must be at least 5 character long")
	private String password;

	@NotNull(message = "Re-password is required!")
	@Size(min = 5, message = "Re-password must be at least 5 character long")
	private String rePassword;

	@NotNull(message = "Current password is required!")
	private String currentPassword;

	public ProfilePasswordDTO() {
		// TODO Auto-generated constructor stub
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

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
