package com.accenture.mvc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		ServletRegistration.Dynamic registration = 
				servletContext.addServlet("dispatcher", new DispatcherServlet());
		registration.setInitParameter("contextConfigLocation", "com.accenture.mvc.config.WebConfig");
		registration.setInitParameter("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext" );
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		/*
		 ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());
		 servlet.setInitParameter("-webAllowOthers", "true");
		 servlet.setLoadOnStartup(2);
		 servlet.addMapping("/console/h2");*/
 
	}
	
 }
