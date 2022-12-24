package com.dbms.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class CustomerSatisfactionMetricParameters  implements Serializable{

	private List<String> states;

	private String startDate;

	private String endDate;
	
	private Frequency frequency;

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
	public String toString() {
		return "CustomerSatisfactionMetricParameters{" +
				"states=" + states +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", frequency=" + frequency +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CustomerSatisfactionMetricParameters)) return false;
		CustomerSatisfactionMetricParameters that = (CustomerSatisfactionMetricParameters) o;
		return Objects.equals(getStates(), that.getStates()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate()) && getFrequency() == that.getFrequency();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStates(), getStartDate(), getEndDate(), getFrequency());
	}

	public boolean isValid() {
		return StringUtils.hasLength(this.endDate) && StringUtils.hasLength(this.startDate) && this.frequency != null && !CollectionUtils.isEmpty(this.states);
	}
}
