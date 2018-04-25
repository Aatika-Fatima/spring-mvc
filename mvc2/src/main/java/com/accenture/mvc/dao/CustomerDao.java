package com.accenture.mvc.dao;

 
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.mvc.model.Address;
import com.accenture.mvc.model.CountryCode;
import com.accenture.mvc.model.Customer;
import com.accenture.mvc.model.Gender;

@Repository
public class CustomerDao {
	@Autowired
	SessionFactory sessionFactory;
	 
	@PostConstruct
	public void initializeDatabase() {
		insertData();
 	}

	@Transactional
	public void save(Customer customer) {
		Session session = sessionFactory.openSession(); 
 		session.saveOrUpdate(customer);
		//session.saveOrUpdate(customer);
 		session.flush();
		
	}
	
	public Customer getCustomer(Integer id) {
		Session session = sessionFactory.openSession(); 
		Customer customer  = (Customer) session.get(Customer.class, id);
		return customer;
	}
	
	public List<Customer> findAll() {
		Session session = sessionFactory.openSession(); 
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> list =criteria.list();
		return list;
	}
	
	
	@Transactional
	private void insertData() {
		Address address = new Address(); 
		address.setDoorNo("8-1-366");
		address.setCity("HYD");
		address.setStreet("10 A");
		address.setZipCode("5000023");
		address.setCountry("India");
		
		CountryCode cc = new CountryCode("IN", "INDIA", "INR");
		
		Customer c1 = new Customer();
 		c1.setFirstName("Aatika");
		c1.setLastName("Fatima");
		c1.setId(1);
		c1.setEmail("aatika08@gmail.com");
		c1.setDob(Calendar.getInstance().getTime());
		c1.setGender(Gender.FEMALE);
		c1.setCountry(cc);
		c1.setPassword("secret");
		c1.setAddress(address);
		
		Customer c2 = new Customer();
 		c2.setFirstName("John");
		c2.setLastName("McCain");
		c2.setId(2);
		c2.setEmail("john@gmail.com");
		c2.setDob(Calendar.getInstance().getTime());
		c2.setGender(Gender.MALE);
		c2.setCountry(cc);
		c2.setPassword("secret");
		c2.setAddress(address);

		Customer c3 = new Customer();
 		c3.setFirstName("Tom");
		c3.setLastName("Cruise");
		c3.setId(2);
		c3.setEmail("tom@gmail.com");
		c3.setDob(Calendar.getInstance().getTime());
		c3.setAddress(address);
		c3.setGender(Gender.MALE);
		c3.setCountry(cc);
		c3.setPassword("secret");
		Session session = sessionFactory.openSession(); 
		session.save(c1);session.save(c2);session.save(c3);

	}
}
