package isa_api.beans.hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import isa_api.beans.Company;

@Entity
@DiscriminatorValue("HOTEL")
public class HotelCompany extends Company {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotelCompany", cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<Room> rooms;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotelCompany", cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<HotelAdditionalService> additionalServices;

	
	public HotelCompany() {
		super();
		
	}

	public List<Room> getRooms() {
		if (rooms == null)
			rooms = new ArrayList<Room>();
		return rooms;
	}

	public Iterator<Room> getIteratorRooms() {
		if (rooms == null)
			rooms = new ArrayList<Room>();
		return rooms.iterator();
	}

	public void setRooms(List<Room> newRooms) {
		removeAllRooms();
		for (Iterator<Room> iter = newRooms.iterator(); iter.hasNext();)
			addRooms((Room) iter.next());
	}

	public void addRooms(Room newRoom) {
		if (newRoom == null)
			return;
		if (this.rooms == null)
			this.rooms = new ArrayList<Room>();
		if (!this.rooms.contains(newRoom)) {
			this.rooms.add(newRoom);
			newRoom.setHotelCompany(this);
		}
	}

	public void removeRooms(Room oldRoom) {
		if (oldRoom == null)
			return;
		if (this.rooms != null)
			if (this.rooms.contains(oldRoom)) {
				this.rooms.remove(oldRoom);
				oldRoom.setHotelCompany((HotelCompany) null);
			}
	}

	public void removeAllRooms() {
		if (rooms != null) {
			Room oldRoom;
			for (Iterator<Room> iter = getIteratorRooms(); iter.hasNext();) {
				oldRoom = (Room) iter.next();
				iter.remove();
				oldRoom.setHotelCompany((HotelCompany) null);
			}
		}
	}

	public List<HotelAdditionalService> getAdditionalServices() {
		if (additionalServices == null)
			additionalServices = new ArrayList<HotelAdditionalService>();
		return additionalServices;
	}

	public Iterator<HotelAdditionalService> getIteratorAdditionalServices() {
		if (additionalServices == null)
			additionalServices = new ArrayList<HotelAdditionalService>();
		return additionalServices.iterator();
	}

	public void setAdditionalServices(List<HotelAdditionalService> newAdditionalServices) {
		removeAllAdditionalServices();
		for (Iterator<HotelAdditionalService> iter = newAdditionalServices.iterator(); iter.hasNext();)
			addAdditionalServices((HotelAdditionalService) iter.next());
	}

	public void addAdditionalServices(HotelAdditionalService newHotelAdditionalService) {
		if (newHotelAdditionalService == null)
			return;
		if (this.additionalServices == null)
			this.additionalServices = new ArrayList<HotelAdditionalService>();
		if (!this.additionalServices.contains(newHotelAdditionalService))
			this.additionalServices.add(newHotelAdditionalService);
	}

	public void removeAdditionalServices(HotelAdditionalService oldHotelAdditionalService) {
		if (oldHotelAdditionalService == null)
			return;
		if (this.additionalServices != null)
			if (this.additionalServices.contains(oldHotelAdditionalService))
				this.additionalServices.remove(oldHotelAdditionalService);
	}

	public void removeAllAdditionalServices() {
		if (additionalServices != null)
			additionalServices.clear();
	}
	
}