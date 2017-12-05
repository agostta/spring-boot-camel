package com.example.apache.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.example.apache.camel.model.Person;

@Component
public class PersonRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("direct:personRoute")
			.log(">> body: ${body}")
			.unmarshal().json(JsonLibrary.Jackson, Person.class)
			
			.choice()
				.when().simple("${body.country} == 'Brazil'")
					.log(">>  Bom dia")
				.when().simple("${body.country} == 'Egipt'")
					.log(">> صباح الخير.")	
				.otherwise()
					.log("country=${body.country} Good Morning")
		.end();
	}
	
}
