package com.example.apache.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ThreadPoolProfile;
import org.springframework.stereotype.Component;

//@Component
public class ParallelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		parallel();
	}
	
	private void sync() {
	    // From Timer to Direct
	    from("timer://threadDirect?period=5s&delay=1s").routeId("# Timer for Direct #")
	       .log(">> Timer Direct thread : ${threadName}")
	       .to("direct:thread");

	    from("direct:thread")
	       .log(">> Direct thread : ${threadName}")
	       .to("direct:continueThread");

	    from("direct:continueThread")
	       .log(">> Direct continue thread : ${threadName}");	
	}
	
	private void parallel() {
		
        // Create a custom Thread Pool
        ThreadPoolProfile pool = new ThreadPoolProfile("my-pool");
        pool.setKeepAliveTime(20L);
        pool.setMaxPoolSize(5);
        pool.setPoolSize(1);
        pool.setDefaultProfile(true);

        // Register it
        getContext().getExecutorServiceManager().registerThreadPoolProfile(pool);
		
		
		from("timer://threadDirect?period=5s&delay=1s").routeId("# Timer for Direct #")
			.multicast()
			.parallelProcessing()
			.to("direct:threadA", "direct:threadB");
		
		 from("direct:threadA").threads().executorServiceRef("my-pool").log(">> Direct thread A : ${threadName}");
		 from("direct:threadB").threads().executorServiceRef("my-pool").log(">> Direct thread B: ${threadName}");
	       
	}

}
