package isa_api.beans.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date lastPasswordReset;

	@Column(nullable = false)
	private String name;

	@Enumerated
	private AuthorityEnum authority;

	@Column(nullable = false)
	private String lastName;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	private String telephone;

	private String adress;

	private Boolean firstLogin;

	private Boolean isVerified;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String lastName, String username, String password, String email, String telephone,
			String adress, AuthorityEnum authority) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.adress = adress;
		this.authority = authority;
		this.isVerified = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public AuthorityEnum getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityEnum authority) {
		this.authority = authority;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

}