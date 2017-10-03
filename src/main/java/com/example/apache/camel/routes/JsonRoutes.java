package com.example.apache.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.example.apache.camel.model.Product;

//@Component
public class JsonRoutes extends RouteBuilder{

	private final String FILE_URL = "file:camel-files/products?noop=true";
	
	@Override
	public void configure() throws Exception {
		//http://camel.apache.org/file2.html
		
		from(FILE_URL)
		.log(">> file: ${file:name}")
		.log(">> body: ${body}")
		.unmarshal().json(JsonLibrary.Jackson, Product.class)
		.choice()
			.when().simple("${body.id} == 1")
				.log(">> Um")
			.when().simple("${body.id} == 2")
				.log(">> Dois")	
			.otherwise()
				.log("otherwise: id=${body.id}")
		.end();
	}

	
}
