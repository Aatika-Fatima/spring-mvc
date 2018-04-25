package com.accenture.mvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class StringToGenderConverterFactory implements ConverterFactory<String, Enum> {

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		// TODO Auto-generated method stub
		return new StringToEnumConverter<>(targetType);
	}

	private final class StringToEnumConverter<T extends Enum> implements Converter<String, T> {

		private Class<T> enumType;

		public StringToEnumConverter(Class<T> enumType) {
			super();
			this.enumType = enumType;
		}

		@Override
		public T convert(String source) {
			return (T) Enum.valueOf(this.enumType, source.trim());
		}

	}

}
