package com.dbms.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ProductPerformanceMetricParameters implements Serializable{

	private String productCategory;
	
	private List<String> states;

	private String startDate;

	private String endDate;
	
	private Frequency frequency;

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String[] getStatesArr() {
		String[]statesArr = new String[this.states.size()];
		for(int i = 0; i < statesArr.length; i++) {
			statesArr[i] = this.states.get(i);
		}
		return statesArr;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

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

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, frequency, productCategory, startDate, states);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPerformanceMetricParameters other = (ProductPerformanceMetricParameters) obj;
		return Objects.equals(endDate, other.endDate) && frequency == other.frequency
				&& Objects.equals(productCategory, other.productCategory) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(states, other.states);
	}

	@Override
	public String toString() {
		return "ProductPerformanceMetricParameters [productCategory=" + productCategory + ", state=" + states
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", frequency=" + frequency + "]";
	}

	public boolean isValid() {
		return StringUtils.hasLength(this.endDate) && StringUtils.hasLength(this.startDate) && this.frequency != null && !CollectionUtils.isEmpty(this.states) && StringUtils.hasLength(this.productCategory);
	}
	
	
	
}
