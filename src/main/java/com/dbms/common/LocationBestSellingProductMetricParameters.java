package com.dbms.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class LocationBestSellingProductMetricParameters implements Serializable {

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LocationBestSellingProductMetricParameters)) return false;
		LocationBestSellingProductMetricParameters that = (LocationBestSellingProductMetricParameters) o;
		return getStates().equals(that.getStates()) && getStartDate().equals(that.getStartDate()) && getEndDate().equals(that.getEndDate()) && getFrequency() == that.getFrequency();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStates(), getStartDate(), getEndDate(), getFrequency());
	}

	public boolean isValid() {
		return StringUtils.hasLength(this.endDate) && StringUtils.hasLength(this.startDate) && this.frequency != null && !CollectionUtils.isEmpty(this.states);
	}

	@Override
	public String toString() {
		return "LocationBestSellingProductMetricParameters{" +
				"states=" + states +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", frequency=" + frequency +
				'}';
	}
}
