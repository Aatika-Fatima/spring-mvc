package com.accenture.mvc.formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	private String pattern;

	public DateFormatter(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String print(Date date, Locale locale) {
		DateFormat format = new SimpleDateFormat(pattern, locale);
		return format.format(date);
	}

	@Override
	public Date parse(String date, Locale locale) throws ParseException {
		DateFormat format = getDateFormat(locale);
		return format.parse(date);

	}

	private DateFormat getDateFormat(Locale locale) {
		System.out.println("Date pattern..........." + pattern);
		DateFormat format = new SimpleDateFormat(pattern, locale);
		return format;
	}

}
