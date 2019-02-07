package isa_api.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa_api.beans.users.RegistredUser;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private RegistredUser owner;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(RegistredUser owner) {
		super();
		this.owner = owner;
	}

	public RegistredUser getOwner() {
		return owner;
	}

	public void setOwner(RegistredUser newRegistredUser) {
		if (this.owner == null || !this.owner.equals(newRegistredUser)) {
			if (this.owner != null) {
				RegistredUser oldRegistredUser = this.owner;
				this.owner = null;
				oldRegistredUser.removeReservation(this);
			}
			if (newRegistredUser != null) {
				this.owner = newRegistredUser;
				this.owner.addReservation(this);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}