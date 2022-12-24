package com.dbms.dto;

public class NewSellersMetricDTO {

	private int newSellers;
	
	private String frequencyStart;

	public NewSellersMetricDTO(int newSellers, String frequencyStart) {
		super();
		this.newSellers = newSellers;
		this.frequencyStart = frequencyStart;
	}

	public int getNewSellers() {
		return newSellers;
	}

	public void setNewSellers(int newSellers) {
		this.newSellers = newSellers;
	}

	public String getFrequencyStart() {
		return frequencyStart;
	}

	public void setFrequencyStart(String frequencyStart) {
		this.frequencyStart = frequencyStart;
	}
	
	
}
