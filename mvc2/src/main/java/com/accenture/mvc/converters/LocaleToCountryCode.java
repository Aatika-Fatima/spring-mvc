package com.accenture.mvc.converters;

import org.springframework.core.convert.converter.Converter;

import com.accenture.mvc.model.CountryCode;

public class LocaleToCountryCode implements Converter<String, CountryCode> {

	@Override
	public CountryCode convert(String locale) {
		if (locale.equals("IN"))
			return new CountryCode("91", "INDIA", "INR");
		else if (locale.equals("US"))
			return new CountryCode("1", "INDIA", "INR");

		return null;
	}

}
