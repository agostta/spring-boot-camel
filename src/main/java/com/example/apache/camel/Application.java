/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.example.apache.camel;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application{
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
<<<<<<< HEAD:src/main/java/com/example/apache/camel/Application.java
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
	    ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/camel/*");
	    servlet.setName("CamelServlet");
	    return servlet;
	}
    
=======

    @Override
    public void configure() throws Exception {
        from("timer://foo?period=5000")
            .setBody().constant("Hello World2")
            .log(">>> ${body}");
    }
>>>>>>> 96cde76d2c941834ad27d3b9d927e7ad3ec50454:src/main/java/io/fabric8/quickstarts/camel/Application.java
}
