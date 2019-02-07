package isa_api.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import isa_api.beans.users.RegistredUser;

@Entity
public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private RegistredUser friendOne;

	@ManyToOne(optional = false)
	private RegistredUser friendTwo;

	private FriendshipStatusEnum firendshipStatus;

	@ManyToOne(optional = false)
	private RegistredUser owner;

	public Friendship() {
		// TODO Auto-generated constructor stub
	}

	public Friendship(RegistredUser friendOne, RegistredUser friendTwo, FriendshipStatusEnum firendshipStatus,
			RegistredUser owner) {
		super();
		this.friendOne = friendOne;
		this.friendTwo = friendTwo;
		this.firendshipStatus = firendshipStatus;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegistredUser getFriendOne() {
		return friendOne;
	}

	public void setFriendOne(RegistredUser friendOne) {
		this.friendOne = friendOne;
	}

	public RegistredUser getFriendTwo() {
		return friendTwo;
	}

	public void setFriendTwo(RegistredUser friendTwo) {
		this.friendTwo = friendTwo;
	}

	public FriendshipStatusEnum getFirendshipStatus() {
		return firendshipStatus;
	}

	public void setFirendshipStatus(FriendshipStatusEnum firendshipStatus) {
		this.firendshipStatus = firendshipStatus;
	}

	public RegistredUser getOwner() {
		return owner;
	}

	public void setOwner(RegistredUser owner) {
		this.owner = owner;
	}

}