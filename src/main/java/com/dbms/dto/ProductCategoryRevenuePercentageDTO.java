package com.dbms.dto;

import java.io.Serializable;

public class ProductCategoryRevenuePercentageDTO implements Serializable{

	private String category;
	
	private double percentage;
	
	private String weekstart;

	public ProductCategoryRevenuePercentageDTO(String category, double percentage, String weekstart) {
		super();
		this.category = category;
		this.percentage = percentage;
		this.weekstart = weekstart;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getWeekstart() {
		return weekstart;
	}

	public void setWeekstart(String weekstart) {
		this.weekstart = weekstart;
	}
	
}
