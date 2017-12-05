package com.example.apache.camel.endpoint;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 	Used to invoke Apache Cammel Routes
 */
@RestController
@RequestMapping("/routes")
public class TestPersonRoutesEndpoint {

	@Autowired
	private CamelContext context;
	
	@RequestMapping(value="/person", method=RequestMethod.POST)
	public String personRoute( @RequestBody String body ) {

	    ProducerTemplate template = context.createProducerTemplate();
	    template.sendBody("direct:personRoute", body);
		
		return "Running person route.";
	}
	
}
