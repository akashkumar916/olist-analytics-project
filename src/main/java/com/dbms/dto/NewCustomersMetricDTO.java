package com.dbms.dto;

public class NewCustomersMetricDTO {

	private int newCustomers;
	
	private String frequencyStart;

	public NewCustomersMetricDTO(int newCustomers, String frequencyStart) {
		super();
		this.newCustomers = newCustomers;
		this.frequencyStart = frequencyStart;
	}

	public int getNewCustomers() {
		return newCustomers;
	}

	public void setNewCustomers(int newCustomers) {
		this.newCustomers = newCustomers;
	}

	public String getFrequencyStart() {
		return frequencyStart;
	}

	public void setFrequencyStart(String frequencyStart) {
		this.frequencyStart = frequencyStart;
	}

	
}
