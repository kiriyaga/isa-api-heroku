package isa_api.dto;

import isa_api.beans.hotel.HotelAdditionalServiceEnum;
import isa_api.beans.hotel.HotelCompany;

public class HotelAdditionalServiceDTO {

	private Double price;
	private HotelAdditionalServiceEnum additionalServiceType;
	private long id;
	private HotelCompany hotelCompany;

	public HotelAdditionalServiceDTO() {

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

}
