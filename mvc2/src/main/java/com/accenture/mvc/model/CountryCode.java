package com.accenture.mvc.model;

import javax.persistence.Embeddable;

@Embeddable
public class CountryCode {
	String countryCode; 
	private String countryName; 
	private String currency;
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public CountryCode(String countryCode, String countryName, String currency) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.currency = currency;
	}
	public CountryCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CountryCode [countryCode=" + countryCode + ", countryName=" + countryName + ", currency=" + currency
				+ "]";
	}
	 
}
