package com.dbms.dto;

public class ProductCategoryLogisticPerformanceDTO {

	private String category;

	private String weekstart;
	
	private double averageTimeTaken;
	
	public ProductCategoryLogisticPerformanceDTO(String category, String weekstart, double averageTimeTaken) {
		super();
		this.category = category;
		this.weekstart = weekstart;
		this.averageTimeTaken = averageTimeTaken;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWeekstart() {
		return weekstart;
	}

	public void setWeekstart(String weekstart) {
		this.weekstart = weekstart;
	}

	public double getAverageTimeTaken() {
		return averageTimeTaken;
	}

	public void setAverageTimeTaken(double averageTimeTaken) {
		this.averageTimeTaken = averageTimeTaken;
	}
	
}
