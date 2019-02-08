package isa_api.dto;

import javax.validation.constraints.NotNull;

import isa_api.beans.users.AuthorityEnum;

public class UserLoginDTO {

	@NotNull(message = "Username is required!")
	private String username;

	private String name;

	private String lastName;

	private String email;

	private String adress;

	private String telephone;

	private String token;

	private Boolean firstLogin;
	
	@NotNull(message = "Authority is required!")
	private AuthorityEnum authority;

	public UserLoginDTO() {

	}

	public UserLoginDTO(String username, String name, String lastname, String email, String telephone, String adress,
			String token, AuthorityEnum authority,Boolean firstTime) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastname;
		this.email = email;
		this.adress = adress;
		this.telephone = telephone;
		this.token = token;
		this.authority = authority;
		this.firstLogin =firstTime;

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
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

	public AuthorityEnum getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityEnum authority) {
		this.authority = authority;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

}
