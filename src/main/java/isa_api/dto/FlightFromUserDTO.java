package isa_api.dto;

public class FlightFromUserDTO {

	private UserLoginDTO user;
	private Long index;

	public FlightFromUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserLoginDTO getUser() {
		return user;
	}

	public void setUser(UserLoginDTO user) {
		this.user = user;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

}
