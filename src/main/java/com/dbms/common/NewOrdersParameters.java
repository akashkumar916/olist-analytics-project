package com.dbms.common;

import org.springframework.util.StringUtils;

import java.util.Objects;

/*
 * @author ayushkumar
 * @created on 04/12/22
 */
public class NewOrdersParameters {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewOrdersParameters)) return false;
        NewOrdersParameters that = (NewOrdersParameters) o;
        return getStartDate().equals(that.getStartDate()) && getEndDate().equals(that.getEndDate()) && getFrequency() == that.getFrequency();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate(), getFrequency());
    }

    @Override
    public String toString() {
        return "NewOrdersParameters{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    public boolean isValid() {
        return StringUtils.hasLength(this.endDate) && StringUtils.hasLength(this.startDate) && this.frequency != null;
    }
}
