package com.dbms.dto;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */
public class LocationBestSellerDTO {

    int numOrders;
    String category;
    String startDate;

    public LocationBestSellerDTO(int numOrders, String category, String startDate) {
        this.numOrders = numOrders;
        this.category = category;
        this.startDate = startDate;
    }

    public int getNumOrders() {
        return numOrders;
    }

    public void setNumOrders(int numOrders) {
        this.numOrders = numOrders;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
