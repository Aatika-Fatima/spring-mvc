package com.accenture.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.mvc.editors.PhoneNumberEditor;
import com.accenture.mvc.exception.CustomerNotFoundException;
import com.accenture.mvc.model.Account;
import com.accenture.mvc.model.Address;
import com.accenture.mvc.model.Countries;
import com.accenture.mvc.model.CountryCode;
import com.accenture.mvc.model.Customer;
import com.accenture.mvc.service.CustomerService;

@Controller
public class CustomerController {

	//customer.json
	//customer.xlsx
	//customer.pdf
	@RequestMapping(value="/customer", method=RequestMethod.GET)
	public String getCustomer(ModelMap modelMap) {
		Customer c = new Customer();
		c.setId(1);
		c.setFirstName("Tom");
		modelMap.addAttribute("customer", c);
		return "customer";
		
	}
	
	@Autowired
	CustomerService customerService;

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(String.class, "phoneNumber", new PhoneNumberEditor());
 	}
	@RequestMapping(value = "signin")
	public ModelAndView sigin(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {
		System.out.println(email + " " + password);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public  ModelAndView registerCustomerForm() {
		System.out.println("say helo");
		ModelAndView mv = new ModelAndView();
		mv.addObject("account", new Account());
 		return mv;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute("account") Account account, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("error");
			ModelAndView mv = new ModelAndView(); 
			mv.setViewName("register");
			return mv;
		}
		System.out.println(account.getFirstName() + " registered" + " phone number--" + account.getPhoneNumber());
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ModelAndView  getUserProfile() {
		Customer c = customerService.getCustomerProfile(1);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile");
		mv.addObject("customer", c);
		mv.addObject("countryCodes", Countries.values());
 		return mv;
	}
	
	
	@RequestMapping(value="/exception", method=RequestMethod.GET)
	public void  generateException() {
		System.out.println("Customer not found exception");
		 throw new CustomerNotFoundException("cusstomer not found exception");
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String updateUserProfile(@Valid @ModelAttribute("customer")Customer c,final RedirectAttributes redirectAttributes,
			BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("errors detected");
		}
		System.out.println(c.getPassword());
 		customerService.update(c);
		
		
		//adding customer object to redirect attributes
		//addAttribute will pass the value as key /value parameter 
		redirectAttributes.addFlashAttribute("customer", c);
		
		//addFlashAttribute stores value as an object in users session mem, data is destroyed after redirection
		redirectAttributes.addFlashAttribute("msg", "Profile updated successfuly");
	//	ModelAndView mv = new ModelAndView();
	//	mv.setViewName("redirect:show");
	//	mv.addObject("msg", "Profile Updated successfully"); 
		return "redirect:show";
	}
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String show(@ModelAttribute("customer") Customer c,
			@ModelAttribute("msg") String msg) {
		return "profile";
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getCustomer(Locale locale, HttpServletRequest request,
			@RequestParam(name = "locale", defaultValue = "IN", required = false) CountryCode country,
			@RequestParam(name = "date", required = false) Date date, Model model) {
		/*
		 * System.out.println("Current Locale : " + locale.getLanguage() + " _ " +
		 * locale.getCountry());
		 * System.out.println(request.getHeader("Accept-Language"));
		 * System.out.println(request.getLocale());
		 */

		Customer customer = new Customer();
		Address address = new Address();
		address.setCity("HYD");
		address.setCountry("INDIA");
		address.setStreet("STREET");
		address.setZipCode("123");
		customer.setAddress(address);

		customer.setDob(new Date());
		System.out.println(country);
		System.out.println(date);
		model.addAttribute("customer", customer);
		HttpSession session = request.getSession(false);
		if (session != null) {
			Enumeration<String> attributeNames = session.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				String name = attributeNames.nextElement();
				System.out.println(session.getAttribute(name));
			}
		}

		return "index";
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		List<Customer> customerList = customerService.findAll();
		mv.addObject("customers", customerList);
		mv.setViewName("customers/list");
		return mv;

	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String getCustomers(ModelMap modelMap ) {
 		List<Customer> customerList = customerService.findAll();
		modelMap.addAttribute("customers", customerList);
 		return  "reports";
	}

	//Exception  Handler
	private void defaultCustomerIdException() {
		
	}
	

	@RequestMapping(value="/profile/name", method=RequestMethod.GET)
	public ModelAndView getProfileName(@RequestParam(name="firstName") String firstName) {
		Customer c = customerService.getCustomerProfile(100);
		c.setFirstName(firstName);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile");
		mv.addObject("customer", c);
		mv.addObject("countryCodes", Countries.values());
 		return mv;
	}
}
