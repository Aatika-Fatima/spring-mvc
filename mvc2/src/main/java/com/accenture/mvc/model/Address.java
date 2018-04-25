package com.accenture.mvc.model;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Embeddable
public class Address {
	private String doorNo;
	private String street;
	private String city;
	private String country;
	private String zipCode;

	
	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String county) {
		this.country = county;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String street, String city, String county, String zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.country = county;
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", country=" + country + ", zipCode=" + zipCode + "]";
	}

}
