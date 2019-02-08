package isa_api.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class EarningsDTO {
	
	private Long company;
	@NotNull(message = "Date is required!")
	private Date earnings1;
	@NotNull(message = "Date is required!")
	private Date earnings2;
	
	
	public EarningsDTO() {
		// TODO Auto-generated constructor stub
	}
	public Date getEarnings1() {
		return earnings1;
	}
	public void setEarnings1(Date earnings1) {
		this.earnings1 = earnings1;
	}
	public Date getEarnings2() {
		return earnings2;
	}
	public void setEarnings2(Date earnings2) {
		this.earnings2 = earnings2;
	}
	public Long getCompany() {
		return company;
	}
	public void setCompany(Long company) {
		this.company = company;
	}
	
	

}
