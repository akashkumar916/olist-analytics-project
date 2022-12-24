package com.dbms.common;

import java.util.Objects;

import org.springframework.util.StringUtils;

public class ProductCategoryLogisticPerformanceParameters {

	private String startDate;

	private String endDate;
	
	private String state;
	
	private String productCategory;
	
	private Frequency frequency;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productCategory, endDate, frequency, startDate, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategoryLogisticPerformanceParameters other = (ProductCategoryLogisticPerformanceParameters) obj;
		return Objects.equals(productCategory, other.productCategory) && Objects.equals(endDate, other.endDate)
				&& frequency == other.frequency && Objects.equals(startDate, other.startDate)
				&& Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		return "ProductCategoryLogisticPerformanceParameters [startDate=" + startDate + ", endDate=" + endDate
				+ ", state=" + state + ", category=" + productCategory + ", frequency=" + frequency + "]";
	}
	
	public boolean isValid() {
		return StringUtils.hasLength(this.state)  && StringUtils.hasLength(this.productCategory) && StringUtils.hasLength(this.endDate) && StringUtils.hasLength(this.startDate) && this.frequency != null;
	}
	
	
}
