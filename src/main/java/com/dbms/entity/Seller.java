package com.dbms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Seller")
@Getter
@Setter
public class Seller {

	@Id
	@Column(name="seller_id")
	private String sellerId;
	
	@Column(name="zipcode")
	private Integer zipcode;

	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", zipcode=" + zipcode + "]";
	}
}
