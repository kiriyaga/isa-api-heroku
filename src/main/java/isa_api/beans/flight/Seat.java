package isa_api.beans.flight;
/***********************************************************************
 * Module:  Seat.java
 * Author:  Kiriyaga
 * Purpose: Defines the Class Seat
 ***********************************************************************/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

/** @pdOid 5b97e4d3-46b9-474d-a65f-3bf9654d5633 */
@Entity
@Where(clause = "active = 1")
public class Seat {
	/** @pdOid 189c9c86-224c-42f4-a495-f8e318266088 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** @pdOid 0bc6e8f4-d0d6-4bc3-af8c-815a31ccce45 */
	private double additionalPriceForClass;
	/** @pdOid 05394606-c600-46fe-a7d3-bc49ee789e7c */
	@Enumerated
	private SeatTypeEnum seatType;
	/** @pdOid c03432ce-3c53-4868-8f54-e4b7766637c2 */
	@Enumerated
	private SeatClassEnum seatClass;
	
	private double discount;
	
	private String name;
	private String lastname;
	private String passport;	

	private int checkBagCount;
	
	private int carryBagCount;
	
	@Version
	private Long version;
	
	private String seatCode;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FlightStop startDestination;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FlightStop endDestination;
	
	private double priceForTicket;

	private boolean active;
	
	
	@ManyToOne(optional = false,cascade=CascadeType.ALL)
	@JsonBackReference
	private Flight flight;

	public Seat() {
		// TODO Auto-generated constructor stub\
		this.active = true;
	}
	

	public SeatTypeEnum getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatTypeEnum seatType) {
		this.seatType = seatType;
	}

	public SeatClassEnum getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(SeatClassEnum seatClass) {
		this.seatClass = seatClass;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public double getAdditionalPriceForClass() {
		return additionalPriceForClass;
	}



	public void setAdditionalPriceForClass(double additionalPriceForClass) {
		this.additionalPriceForClass = additionalPriceForClass;
	}



	public int getCheckBagCount() {
		return checkBagCount;
	}



	public void setCheckBagCount(int checkBagCount) {
		this.checkBagCount = checkBagCount;
	}



	public int getCarryBagCount() {
		return carryBagCount;
	}



	public void setCarryBagCount(int carryBagCount) {
		this.carryBagCount = carryBagCount;
	}


	public String getSeatCode() {
		return seatCode;
	}


	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
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


	public double getPriceForTicket() {
		return priceForTicket;
	}


	public void setPriceForTicket(double priceForTicket) {
		this.priceForTicket = priceForTicket;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public Long getVersion() {
		return version;
	}



}