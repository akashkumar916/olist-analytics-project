package com.dbms.dto;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */
public class NewOrdersDTO {

    private int numNewOrders;
    private String weekstart;

    public NewOrdersDTO(int numNewOrders, String weekstart) {
        this.numNewOrders = numNewOrders;
        this.weekstart = weekstart;
    }

    public int getNumNewOrders() {
        return numNewOrders;
    }

    public void setNumNewOrders(int numNewOrders) {
        this.numNewOrders = numNewOrders;
    }

    public String getWeekstart() {
        return weekstart;
    }

    public void setWeekstart(String weekstart) {
        this.weekstart = weekstart;
    }
}
