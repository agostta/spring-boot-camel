package com.example.apache.camel.converter;

import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.camel.support.TypeConverterSupport;
import org.springframework.stereotype.Component;

import com.example.apache.camel.model.MyNumber;

@Component
public class MyNumberConverter extends TypeConverterSupport {

	@SuppressWarnings("unchecked")
	@Override
	//@Converter
	public <T> T convertTo(Class<T> type, Exchange exchange, final Object value) throws TypeConversionException {
		MyNumber number = (MyNumber)value;
		switch (number.number) {
			case 1: return (T) "One";
			case 2: return (T) "Two";
			case 3: return (T) "Three";
			case 4: return (T) "Four";
		}
		throw new TypeConversionException(value, type, null);
	}

	
	
}
