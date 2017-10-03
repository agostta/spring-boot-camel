package com.example.apache.camel.model;

import org.apache.camel.Exchange;

public class HelloWorldBean{
	
	public String hello(Exchange exchange) {
		String body = (String)exchange.getIn().getBody();
		return "Hello " + body;
	}
	
}