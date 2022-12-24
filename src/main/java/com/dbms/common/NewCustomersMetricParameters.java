package com.dbms.common;

import java.util.Objects;

import org.springframework.util.StringUtils;

public class NewCustomersMetricParameters {

	private String startDate;

	private String endDate;
	
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

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "NewCustomersMetricParameters [startDate=" + startDate + ", endDate=" + endDate + ", frequency="
				+ frequency + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, frequency, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewCustomersMetricParameters other = (NewCustomersMetricParameters) obj;
		return Objects.equals(endDate, other.endDate) && frequency == other.frequency
				&& Objects.equals(startDate, other.startDate);
	}

	public boolean isValid() {
		return StringUtils.hasLength(this.endDate) && StringUtils.hasLength(this.startDate) && this.frequency != null;
	}
	
}
