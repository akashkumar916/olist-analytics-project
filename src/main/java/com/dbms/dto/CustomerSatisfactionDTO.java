package com.dbms.dto;

/*
 * @author ayushkumar
 * @created on 04/12/22
 */
public class CustomerSatisfactionDTO {

    private String stateName;
    private double avgCustomerScore;
    private String weekstart;

    public CustomerSatisfactionDTO(String stateName, double avgCustomerScore, String weekstart) {
        this.stateName = stateName;
        this.avgCustomerScore = avgCustomerScore;
        this.weekstart = weekstart;
    }

    public double getAvgCustomerScore() {
        return avgCustomerScore;
    }

    public void setAvgCustomerScore(double avgCustomerScore) {
        this.avgCustomerScore = avgCustomerScore;
    }

    public String getWeekstart() {
        return weekstart;
    }

    public void setWeekstart(String weekstart) {
        this.weekstart = weekstart;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
