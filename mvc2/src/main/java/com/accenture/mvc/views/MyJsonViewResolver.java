package com.accenture.mvc.views;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class MyJsonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		 System.out.println("View Name " + viewName);
		 MappingJackson2JsonView jv = new MappingJackson2JsonView();
 		 jv.setPrettyPrint(true);
		return jv;
	}

}
