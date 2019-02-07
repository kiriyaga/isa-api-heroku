package isa_api.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Company {

	private String name;
	private String promtiveDescription;
	private double rate;
	private int rateCount;

	@OneToOne(optional = false)
	private Location location;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPromtiveDescription() {
		return promtiveDescription;
	}

	public void setPromtiveDescription(String promtiveDescription) {
		this.promtiveDescription = promtiveDescription;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public void setRate(double rate) {
		this.rate = rate;
	}

}