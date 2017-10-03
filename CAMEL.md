

# Direct

    // Lookup to find the bean in the registry
    from("direct:beanRef").beanRef("beanName", "methodName");

    // Using the Class
    from("direct:class").bean(ExampleBean.class, "anotherMethodName");
    
---