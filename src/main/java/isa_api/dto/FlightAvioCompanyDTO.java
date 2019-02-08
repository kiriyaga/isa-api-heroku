package isa_api.dto;

import javax.validation.constraints.NotNull;

import isa_api.beans.flight.AvioCompany;
import isa_api.beans.flight.FlightStop;

public class FlightAvioCompanyDTO {

	private AvioCompany company;
	
	private Long id;
	@NotNull(message = "Price is required!")
	private double priceForTicket;
	@NotNull(message = "Additional Price for Carry Bag is required!")
	private double additionalPriceCarryBag;
	@NotNull(message = "Additional Price for Check Bag is required!")
	private double additionalPriceCheckBag;
	@NotNull(message = "Number is required!")
	private int maxCarryBag;
	@NotNull(message = "Number is required!")
	private int maxCheckBag;
	@NotNull(message = "Start Destination is required!")
	private FlightStop startDestination;
	@NotNull(message = "End destination is required!")
	private FlightStop endDestination;
	@NotNull(message = "Travel time is required!")
	private String travelTime;
	@NotNull(message = "Travel distance is required!")
	private String travelDistance;
	
	

	public FlightAvioCompanyDTO(AvioCompany company, Long id,
			@NotNull(message = "Price is required!") double priceForTicket,
			@NotNull(message = "Additional Price for Carry Bag is required!") double additionalPriceCarryBag,
			@NotNull(message = "Additional Price for Check Bag is required!") double additionalPriceCheckBag,
			@NotNull(message = "Number is required!") int maxCarryBag,
			@NotNull(message = "Number is required!") int maxCheckBag,
			@NotNull(message = "Start Destination is required!") FlightStop startDestination,
			@NotNull(message = "End destination is required!") FlightStop endDestination,
			@NotNull(message = "Travel time is required!") String travelTime,
			@NotNull(message = "Travel distance is required!") String travelDistance) {
		super();
		this.company = company;
		this.id = id;
		this.priceForTicket = priceForTicket;
		this.additionalPriceCarryBag = additionalPriceCarryBag;
		this.additionalPriceCheckBag = additionalPriceCheckBag;
		this.maxCarryBag = maxCarryBag;
		this.maxCheckBag = maxCheckBag;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.travelTime = travelTime;
		this.travelDistance = travelDistance;
	}

	public FlightAvioCompanyDTO() {
		// TODO Auto-generated constructor stub
	}

	public AvioCompany getCompany() {
		return company;
	}

	public void setCompany(AvioCompany company) {
		this.company = company;
	}

	public double getPriceForTicket() {
		return priceForTicket;
	}

	public void setPriceForTicket(double priceForTicket) {
		this.priceForTicket = priceForTicket;
	}

	public double getAdditionalPriceCarryBag() {
		return additionalPriceCarryBag;
	}

	public void setAdditionalPriceCarryBag(double additionalPriceCaryyBag) {
		this.additionalPriceCarryBag = additionalPriceCaryyBag;
	}

	public double getAdditionalPriceCheckBag() {
		return additionalPriceCheckBag;
	}

	public void setAdditionalPriceCheckBag(double additionalPriceCheckBag) {
		this.additionalPriceCheckBag = additionalPriceCheckBag;
	}

	public FlightStop getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(FlightStop startDestination) {
		this.startDestination = startDestination;
	}

	public FlightStop getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(FlightStop endDestination) {
		this.endDestination = endDestination;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	public String getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(String travelDistance) {
		this.travelDistance = travelDistance;
	}

	public int getMaxCarryBag() {
		return maxCarryBag;
	}

	public void setMaxCarryBag(int maxCarryBag) {
		this.maxCarryBag = maxCarryBag;
	}

	public int getMaxCheckBag() {
		return maxCheckBag;
	}

	public void setMaxCheckBag(int maxCheckBag) {
		this.maxCheckBag = maxCheckBag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
