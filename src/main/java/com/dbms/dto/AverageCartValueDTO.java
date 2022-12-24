package com.dbms.dto;

public class AverageCartValueDTO {

    private double avgExpenditure;
    private String weekstart;

    public double getAvgExpenditure() {
        return avgExpenditure;
    }

    public void setAvgExpenditure(double avgExpenditure) {
        this.avgExpenditure = avgExpenditure;
    }

    public String getWeekstart() {
        return weekstart;
    }

    public void setWeekstart(String weekstart) {
        this.weekstart = weekstart;
    }

    public AverageCartValueDTO(double avgExpenditure, String weekstart) {
        this.avgExpenditure = avgExpenditure;
        this.weekstart = weekstart;
    }

    @Override
    public String toString() {
        return "AverageCartValueDTO{" +
                "avgExpenditure=" + avgExpenditure +
                ", weekstart='" + weekstart + '\'' +
                '}';
    }
}
