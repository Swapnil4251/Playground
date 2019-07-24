package com.practice.java8.features;

public class TestJava8Features {

	public static void main(String[] args) {
		FunctionalInterface funcI = (int x) -> System.out.println("Functional interface in lambda : " + x);
		funcI.functionB();
	}
	
}
