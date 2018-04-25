package com.accenture.mvc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.mvc.dao.CustomerDao;
import com.accenture.mvc.model.Address;
import com.accenture.mvc.model.CountryCode;
import com.accenture.mvc.model.Customer;
import com.accenture.mvc.model.Gender;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	public List<Customer> findAll() {
		return customerDao.findAll();
		/*
		Address address = new Address(); 
		address.setDoorNo("8-1-366");
		address.setCity("HYD");
		address.setStreet("10 A");
		address.setZipCode("5000023");
		address.setCountry("India");
		
		
		Customer c1 = new Customer();
		c1.setUsername("Aatika");
		c1.setFirstName("Aatika");
		c1.setLastName("Fatima");
		c1.setId(1);
		c1.setEmail("aatika08@gmail.com");
		c1.setDob(Calendar.getInstance().getTime());
		c1.setGender(Gender.FEMALE);
		c1.setAddress(address);
		
		Customer c2 = new Customer();
		c2.setUsername("John");
		c2.setFirstName("John");
		c2.setLastName("McCain");
		c2.setId(2);
		c2.setEmail("john@gmail.com");
		c2.setDob(Calendar.getInstance().getTime());
		c2.setGender(Gender.FEMALE);

		c2.setAddress(address);

		Customer c3 = new Customer();
		c3.setUsername("Tom");
		c3.setFirstName("Tom");
		c3.setLastName("Cruise");
		c3.setId(2);
		c3.setEmail("tom@gmail.com");
		c3.setDob(Calendar.getInstance().getTime());
		c3.setAddress(address);
		c3.setGender(Gender.FEMALE);


		List<Customer> customerList = new ArrayList<>();
		customerList.add(c1);
		customerList.add(c2);
		customerList.add(c3);

		return customerList;
	*/
	}
	
	public void update(Customer customer) {
		 customerDao.save(customer);
	}
	
	public Customer getCustomerProfile(int id) {
		return customerDao.getCustomer(id);
		/*
		Customer c = new Customer();
		c.setId(1);
		c.setFirstName("Aatika");
		c.setLastName("Fatima");
		c.setEmail("aatika08@gmail.com");
		c.setGender(Gender.FEMALE);
  		c.setCountry(new CountryCode("US", "USA", "USD"));
		Address address = new Address(); 
		address.setDoorNo("8-1-366");
		address.setCity("HYD");
		address.setStreet("10 A");
		address.setZipCode("5000023");
		address.setCountry("India");
		
		c.setAddress(address);
		return c;*/
		
	}
}
