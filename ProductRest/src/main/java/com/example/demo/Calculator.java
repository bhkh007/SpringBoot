package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	
	public int sum(int n1, int n2) {
		return n1+n2;
	}
	
	public int multiply(int n1, int n2) {
		return n1*n2;
	}
	
	public boolean compare(int c1 , int c2) {
		return c1==c2;
	}
}
