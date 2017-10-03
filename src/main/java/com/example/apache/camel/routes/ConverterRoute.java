package com.example.apache.camel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.apache.camel.converter.MyNumberConverter;
import com.example.apache.camel.model.MyNumber;

//@Component
public class ConverterRoute extends RouteBuilder {

	@Autowired
	private CamelContext context;
	
	@Override
	public void configure() throws Exception {
		
		//register
		context.getTypeConverterRegistry().addTypeConverter(String.class, MyNumber.class, new MyNumberConverter());

        onException(NoTypeConversionAvailableException.class)
        	.log(">> Ops Exception!");
        
        from("direct:testConverter")
        	.convertBodyTo(String.class)
        	.log(">> after: ${body}");
        
        //call producer
        from("timer://demo?delay=2000&repeatCount=1").bean(this, "producer");
	}

	
	public void producer() {
	    ProducerTemplate template = context.createProducerTemplate();
	    template.sendBody("direct:testConverter", new MyNumber(2));
	}
	

}
