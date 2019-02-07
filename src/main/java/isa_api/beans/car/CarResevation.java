package isa_api.beans.car;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import isa_api.beans.Reservation;
import isa_api.beans.users.RegistredUser;

@Entity
public class CarResevation extends Reservation {

	private Date rentIn;
	private Date rentOut;

	@OneToOne(optional = false)
	public Car car;

	public CarResevation(RegistredUser owner, Date rentIn, Date rentOut) {
		super(owner);
		this.rentIn = rentIn;
		this.rentOut = rentOut;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car newCar) {
		if (this.car == null || !this.car.equals(newCar)) {
			if (this.car != null) {
				Car oldCar = this.car;
				this.car = null;
				oldCar.removeCarResevation(this);
			}
			if (newCar != null) {
				this.car = newCar;
				this.car.addCarResevation(this);
			}
		}
	}

	public Date getRentIn() {
		return rentIn;
	}

	public void setRentIn(Date rentIn) {
		this.rentIn = rentIn;
	}

	public Date getRentOut() {
		return rentOut;
	}

	public void setRentOut(Date rentOut) {
		this.rentOut = rentOut;
	}

}