package isa_api.beans.hotel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Where(clause = "active = 1")
public class HotelAdditionalService {

	private Double price;
	
	@Enumerated
	private HotelAdditionalServiceEnum additionalServiceType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false,cascade=CascadeType.ALL)
	@JsonBackReference
	private HotelCompany hotelCompany;
	
	private boolean active;


	public HotelAdditionalService() {
		super();
		this.setActive(true);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public HotelAdditionalServiceEnum getAdditionalServiceType() {
		
		return additionalServiceType;
	}

	public void setAdditionalServiceType(HotelAdditionalServiceEnum additionalServiceType) {
		this.additionalServiceType = additionalServiceType;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HotelCompany getHotelCompany() {
		return hotelCompany;
	}

	public void setHotelCompany(HotelCompany hotelCompany) {
		this.hotelCompany = hotelCompany;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}