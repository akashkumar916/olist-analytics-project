package com.dbms.dto;

public class LocationDTO {

	private String state;

	public LocationDTO(String state) {
		super();
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
