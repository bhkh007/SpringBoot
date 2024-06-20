package com.aop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
// @EnableAspectJAutoProxy   use only if aop not working
public class AopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =   SpringApplication.run(AopApplication.class, args);
		Operations op =  context.getBean(Operations.class);
		op.operate();
		
	}

}
