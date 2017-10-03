package com.example.apache.camel.routes;

import org.apache.camel.builder.RouteBuilder;

import com.example.apache.camel.model.HelloWorldBean;

//@Component
public class BeanRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("timer://demo?delay=2000")
			.setBody().simple("Marcos")
			.bean(HelloWorldBean.class, "hello")
			.log("${body}")
			.stop();
	}
	
}
