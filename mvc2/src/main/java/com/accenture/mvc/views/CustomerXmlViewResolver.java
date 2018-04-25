package com.accenture.mvc.views;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

public class CustomerXmlViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2XmlView xmlView = new MappingJackson2XmlView();
		return xmlView;
	}

}
