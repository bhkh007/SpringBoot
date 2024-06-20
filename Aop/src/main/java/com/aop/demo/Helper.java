package com.aop.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;


@Controller
@Aspect
public class Helper {

	
	//Only methods get called in execution not any other 
	
	@Before("execution (public void operate())")
	public void sendNotification() {
		System.out.println("Notification sent");
		
	}
	
	@After("execution (public void operate())")
	public void sendEmail() {
		System.out.println("Email sent");
		
	}
	
	@Around("execution (public void operate())")
	public void sendRedirect(ProceedingJoinPoint jp) {
		System.out.println("send Redirect Before");
			try {
				jp.proceed();
			} catch (Throwable e) {
				
				e.printStackTrace();
			}
		System.out.println("send Redirect After");
	}
	
	
	
	/*
	@Before("execution (Operations.operate())")
	public void sendData() {
		System.out.println("Data sent");
		
	}
	
	@Before("execution (public Operations.*())")
	public void sendBeer() {
		System.out.println("Beer sent");
		
	}
	
	@Before("execution (* Operations.*())")
	public void sendWater() {
		System.out.println("Water sent");
		System.out.println("It will call ccc for all the public methods on Operations class");
		
	}
	
	
	@Before("execution (public * *(..))")
	public void sendInfo() {
		System.out.println("Info sent");
		System.out.println("pick all the methods in Operations class");
		
	}
	
	@Before("execution (public Operations.set*(..))")
	public void sendInfos() {
		System.out.println("Info sent");
		System.out.println("Picks all setter methods or methods starting from letterrs set");
		
	}
	
	@Before("execution (int Operations.*(..))")
	public void sendInform() {
		System.out.println("Info sent");
		System.out.println("pick all the methods in Operations class with return type int");
		
	}
	*/
	
}
