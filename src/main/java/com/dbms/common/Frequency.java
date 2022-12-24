package com.dbms.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Frequency {
	@JsonProperty("weekly")
	WEEKLY("weekly"),
	@JsonProperty("monthly")
	MONTHLY("monthly");
	
	public String frequency;
	
	private Frequency(String frequency) {
		this.frequency = frequency;
	}
}
