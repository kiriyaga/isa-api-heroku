package isa_api.beans.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import isa_api.beans.Friendship;
import isa_api.beans.Reservation;

@Entity
@DiscriminatorValue("REGUSER")
public class RegistredUser extends User {

	@Enumerated
	private MembershipRankEnum membershipRank;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Friendship> friendship;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Reservation> reservation;

	public RegistredUser() {
		// TODO Auto-generated constructor stub
	}

	public RegistredUser(String name, String lastName, String username, String password, String email, String telephone,
			String adress) {
		super(name, lastName, username, password, email, telephone, adress, AuthorityEnum.USER);
		// TODO Auto-generated constructor stub
		membershipRank = MembershipRankEnum.PLATINA;
	}

	public List<Friendship> getFriendship() {
		if (friendship == null)
			friendship = new ArrayList<Friendship>();
		return friendship;
	}

	public Iterator<Friendship> getIteratorFriendship() {
		if (friendship == null)
			friendship = new ArrayList<Friendship>();
		return friendship.iterator();
	}

	public void setFriendship(List<Friendship> newFriendship) {
		removeAllFriendship();
		for (Iterator<Friendship> iter = newFriendship.iterator(); iter.hasNext();)
			addFriendship((Friendship) iter.next());
	}

	public void addFriendship(Friendship newFriendship) {
		if (newFriendship == null)
			return;
		if (this.friendship == null)
			this.friendship = new ArrayList<Friendship>();
		if (!this.friendship.contains(newFriendship))
			this.friendship.add(newFriendship);
	}

	public void removeFriendship(Friendship oldFriendship) {
		if (oldFriendship == null)
			return;
		if (this.friendship != null)
			if (this.friendship.contains(oldFriendship))
				this.friendship.remove(oldFriendship);
	}

	public void removeAllFriendship() {
		if (friendship != null)
			friendship.clear();
	}

	public List<Reservation> getReservation() {
		if (reservation == null)
			reservation = new ArrayList<Reservation>();
		return reservation;
	}

	public Iterator<Reservation> getIteratorReservation() {
		if (reservation == null)
			reservation = new ArrayList<Reservation>();
		return reservation.iterator();
	}

	public void setReservation(List<Reservation> newReservation) {
		removeAllReservation();
		for (Iterator<Reservation> iter = newReservation.iterator(); iter.hasNext();)
			addReservation((Reservation) iter.next());
	}

	public void addReservation(Reservation newReservation) {
		if (newReservation == null)
			return;
		if (this.reservation == null)
			this.reservation = new ArrayList<Reservation>();
		if (!this.reservation.contains(newReservation)) {
			this.reservation.add(newReservation);
			newReservation.setOwner(this);
		}
	}

	public void removeReservation(Reservation oldReservation) {
		if (oldReservation == null)
			return;
		if (this.reservation != null)
			if (this.reservation.contains(oldReservation)) {
				this.reservation.remove(oldReservation);
				oldReservation.setOwner((RegistredUser) null);
			}
	}

	public void removeAllReservation() {
		if (reservation != null) {
			Reservation oldReservation;
			for (Iterator<Reservation> iter = getIteratorReservation(); iter.hasNext();) {
				oldReservation = (Reservation) iter.next();
				iter.remove();
				oldReservation.setOwner((RegistredUser) null);
			}
		}
	}

	public MembershipRankEnum getMembershipRank() {
		return membershipRank;
	}

	public void setMembershipRank(MembershipRankEnum membershipRank) {
		this.membershipRank = membershipRank;
	}

}