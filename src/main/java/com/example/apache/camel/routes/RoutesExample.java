package com.example.apache.camel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.management.mbean.ManagedRoute;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.apache.camel.model.Product;

@Component
public class RoutesExample extends RouteBuilder {

	@Autowired
	CamelContext camelContext;
	
	@Autowired
	ModelCamelContext modelCamelContext;
	
	public RoutesExample() {
		//restSetup();
	}
	

	
	@Override
    public void configure() throws Exception {

		
		
		
		
			//.log(">> ${person:firstName}");
    }
	
	
	
	private void restSimple() {
		from("rest:get:hello:/{me}").transform().simple("Hi ${header.me}");
	}

	private void restSetup() {
		 restConfiguration().contextPath("/camel/*") //
         .port(8081)
         .enableCORS(true)
         .component("servlet")
         .bindingMode(RestBindingMode.json)
         .apiProperty("api.title", "Test REST API")
         .apiProperty("api.version", "v1")
         .apiProperty("cors", "true")
         .apiContextPath("/api-doc")	//doc
         .apiContextRouteId("doc-api")  //doc
         .dataFormatProperty("prettyPrint", "true");
	}

	/**
	 * 	Hello
	 * 	http://localhost:8080/camel/say/hello
	 * 
	 */
	private void restHelloBye() {
		rest("/say")
	        .get("/hello").to("direct:hello")
	        .get("/bye").consumes("application/json").to("direct:bye")
	        .post("/bye").to("mock:update");
	
	    from("direct:hello").transform().constant("Hello World");
	    from("direct:bye").transform().constant("Bye World");
	}

	private void helloWorldTimer() {
		from("timer://foo?period=5000").routeId("Route Hello")
            .setBody().constant("Hello World")
            .log(">>> ${body}");
	}

	private void printRouteXml() {
		from("timer://bar?period=2000").routeId("Print XML")
	        .process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
				
					Route route = camelContext.getRoute("Route Hello");
					String xml = new ManagedRoute(modelCamelContext, route).dumpRouteAsXml();
					
					System.out.println("################################");
					System.out.println(xml);
					System.out.println("################################");
				}
			});
	}
	
	
	

}
