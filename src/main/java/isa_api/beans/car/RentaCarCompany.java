package isa_api.beans.car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import isa_api.beans.Company;
import isa_api.beans.Location;
import isa_api.beans.users.RentaCarAdmin;

@Entity
@DiscriminatorValue("CAR")
public class RentaCarCompany extends Company {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rentaCarCompany", cascade = CascadeType.ALL)
	public List<Car> car;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "carCompany", cascade = CascadeType.ALL)
	private RentaCarAdmin admin;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Location> branch;

	public RentaCarCompany() {
		super();
	}

	public List<Car> getCar() {
		if (car == null)
			car = new ArrayList<Car>();
		return car;
	}
	
	public Iterator<Car> getIteratorCar() {
		if (car == null)
			car = new ArrayList<Car>();
		return car.iterator();
	}

	public void setCar(List<Car> newCar) {
		removeAllCar();
		for (Iterator<Car> iter = newCar.iterator(); iter.hasNext();)
			addCar((Car) iter.next());
	}
	
	public void addCar(Car newCar) {
		if (newCar == null)
			return;
		if (this.car == null)
			this.car = new ArrayList<Car>();
		if (!this.car.contains(newCar)) {
			this.car.add(newCar);
			newCar.setRentaCarCompany(this);
		}
	}

	public void removeCar(Car oldCar) {
		if (oldCar == null)
			return;
		if (this.car != null)
			if (this.car.contains(oldCar)) {
				this.car.remove(oldCar);
				oldCar.setRentaCarCompany((RentaCarCompany) null);
			}
	}

	public void removeAllCar() {
		if (car != null) {
			Car oldCar;
			for (Iterator<Car> iter = getIteratorCar(); iter.hasNext();) {
				oldCar = (Car) iter.next();
				iter.remove();
				oldCar.setRentaCarCompany((RentaCarCompany) null);
			}
		}
	}
	
	public List<Location> getBranch() {
		if (branch == null)
			branch = new ArrayList<Location>();
		return branch;
	}

	public Iterator<Location> getIteratorBranch() {
		if (branch == null)
			branch = new ArrayList<Location>();
		return branch.iterator();
	}

	public void setBranch(List<Location> newBranch) {
		removeAllBranch();
		for (Iterator<Location> iter = newBranch.iterator(); iter.hasNext();)
			addBranch((Location) iter.next());
	}

	public void addBranch(Location newLocation) {
		if (newLocation == null)
			return;
		if (this.branch == null)
			this.branch = new ArrayList<Location>();
		if (!this.branch.contains(newLocation))
			this.branch.add(newLocation);
	}

	public void removeBranch(Location oldLocation) {
		if (oldLocation == null)
			return;
		if (this.branch != null)
			if (this.branch.contains(oldLocation))
				this.branch.remove(oldLocation);
	}

	public void removeAllBranch() {
		if (branch != null)
			branch.clear();
	}

	public RentaCarAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(RentaCarAdmin admin) {
		this.admin = admin;
	}

}