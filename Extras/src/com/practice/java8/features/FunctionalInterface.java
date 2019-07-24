package com.practice.java8.features;

interface FunctionalInterface {

	abstract void functionA(int x);
	
	default void functionB() {
		System.out.println("default method implementation is interface ");
	}
	
}
