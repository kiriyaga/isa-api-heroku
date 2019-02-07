package isa_api.beans.car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Car {

	private Double price;
	
	private Double rate;
	
	private int countPeople;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int rateCount;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<CarResevation> carResevation;

	@OneToOne(optional = false)
	public RentaCarCompany rentaCarCompany;

	public List<CarResevation> getCarResevation() {
		if (carResevation == null)
			carResevation = new ArrayList<CarResevation>();
		return carResevation;
	}

	public Iterator<CarResevation> getIteratorCarResevation() {
		if (carResevation == null)
			carResevation = new ArrayList<CarResevation>();
		return carResevation.iterator();
	}

	public void setCarResevation(List<CarResevation> newCarResevation) {
		removeAllCarResevation();
		for (Iterator<CarResevation> iter = newCarResevation.iterator(); iter.hasNext();)
			addCarResevation((CarResevation) iter.next());
	}

	public void addCarResevation(CarResevation newCarResevation) {
		if (newCarResevation == null)
			return;
		if (this.carResevation == null)
			this.carResevation = new ArrayList<CarResevation>();
		if (!this.carResevation.contains(newCarResevation))
			this.carResevation.add(newCarResevation);
	}

	public void removeCarResevation(CarResevation oldCarResevation) {
		if (oldCarResevation == null)
			return;
		if (this.carResevation != null)
			if (this.carResevation.contains(oldCarResevation))
				this.carResevation.remove(oldCarResevation);
	}

	public void removeAllCarResevation() {
		if (carResevation != null)
			carResevation.clear();
	}

	public RentaCarCompany getRentaCarCompany() {
		return rentaCarCompany;
	}

	public void setRentaCarCompany(RentaCarCompany newRentaCarCompany) {
		if (this.rentaCarCompany == null || !this.rentaCarCompany.equals(newRentaCarCompany)) {
			if (this.rentaCarCompany != null) {
				RentaCarCompany oldRentaCarCompany = this.rentaCarCompany;
				this.rentaCarCompany = null;
				oldRentaCarCompany.removeCar(this);
			}
			if (newRentaCarCompany != null) {
				this.rentaCarCompany = newRentaCarCompany;
				this.rentaCarCompany.addCar(this);
			}
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public int getCountPeople() {
		return countPeople;
	}

	public void setCountPeople(int countPeople) {
		this.countPeople = countPeople;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

}