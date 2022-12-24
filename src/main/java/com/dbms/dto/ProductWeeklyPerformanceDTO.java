package com.dbms.dto;

//@Entity
public class ProductWeeklyPerformanceDTO {
	
//	@Column(name="num_orders")
	private int numOrders;
	
//	@Column(name="weekstart")
	private String weekstart;
	
	public ProductWeeklyPerformanceDTO(int num_orders, String weekstart) {
		super();
		this.numOrders = num_orders;
		this.weekstart = weekstart;
	}

	public int getNumOrders() {
		return numOrders;
	}

	public void setNumOrders(int numOrders) {
		this.numOrders = numOrders;
	}

	public String getWeekstart() {
		return weekstart;
	}

	public void setWeekstart(String weekstart) {
		this.weekstart = weekstart;
	}
}
