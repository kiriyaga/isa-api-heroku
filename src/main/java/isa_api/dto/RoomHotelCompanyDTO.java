package isa_api.dto;

import isa_api.beans.hotel.HotelCompany;

public class RoomHotelCompanyDTO {

	private long id;
	private HotelCompany hotelCompany;
	private int floor;
	private int number;
	private int numberOfBeds;
	private double nextMonthPrice;
	private double nextThreeMonthPrice;
	private double nextHalfYearPrice;
	private boolean fastReserve;

	public RoomHotelCompanyDTO() {
		// TODO Auto-generated constructor stub
	}

	public HotelCompany getHotelCompany() {
		return hotelCompany;
	}

	public void setHotelCompany(HotelCompany hotelCompany) {
		this.hotelCompany = hotelCompany;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isFastReserve() {
		return fastReserve;
	}

	public void setFastReserve(boolean fastReserve) {
		this.fastReserve = fastReserve;
	}

	public double getNextHalfYearPrice() {
		return nextHalfYearPrice;
	}

	public void setNextHalfYearPrice(double nextHalfYearPrice) {
		this.nextHalfYearPrice = nextHalfYearPrice;
	}

	public double getNextThreeMonthPrice() {
		return nextThreeMonthPrice;
	}

	public void setNextThreeMonthPrice(double nextThreeMonthPrice) {
		this.nextThreeMonthPrice = nextThreeMonthPrice;
	}

	public double getNextMonthPrice() {
		return nextMonthPrice;
	}

	public void setNextMonthPrice(double nextMonthPrice) {
		this.nextMonthPrice = nextMonthPrice;
	}
}
