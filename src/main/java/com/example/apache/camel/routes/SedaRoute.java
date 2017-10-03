package com.example.apache.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
//Staged event-driven architecture
public class SedaRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		nonConcurrent();
	}
	
	private void nonConcurrent() {
		 // From Timer to SEDA
        from("timer://threadSeda?period=5s&delay=1s").routeId("# Timer for SEDA #")
            .log(">> Timer thread : ${threadName}")
            .to("seda:thread");

        from("seda:thread")
             .log(">> Seda thread : ${threadName}");
	}
	
	private void concurrent() {
		// From Timer to SEDA + Concurrent
        from("timer://threadSedaConcurrent?period=5s&delay=1s").routeId("# Timer for SEDA Concurrent #")
                .log(">> Timer thread : ${threadName}")
                .multicast().parallelProcessing()
                //.to("seda:thread-concurrent?concurrentConsumers=3","seda:thread-concurrent?concurrentConsumers=3","seda:thread-concurrent?concurrentConsumers=3");
                .to("seda:thread-concurrent","seda:thread-concurrent","seda:thread-concurrent");

        from("seda:thread-concurrent?concurrentConsumers=3")
                .log(">> Seda concurrent thread : ${threadName}");
	}
	
}
