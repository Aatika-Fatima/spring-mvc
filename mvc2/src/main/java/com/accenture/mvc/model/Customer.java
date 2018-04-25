package com.accenture.mvc.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement
@Entity
@DynamicUpdate
public class Customer {
 	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
 	private String email;
 	
 	@Column(updatable=false)
 	private String password;
	
	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern="YYYY-MM-dd")
	private Date dob;
	
	@NotNull
 	private String firstName; 
	private String lastName;
	
	@XmlElement
	private Gender gender;
	
	@XmlElement
	private CountryCode country;
	
	@XmlElement
	@Embedded
	private Address address;
	
	
	public CountryCode getCountry() {
		return country;
	}


	public void setCountry(CountryCode country) {
		this.country = country;
	}


	public Gender getGender() {
		return gender;
	}

	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Customer [dob=" + dob + "]";
	}
	

}
