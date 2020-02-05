package com.mettl.solutions;

import java.math.BigInteger;

public class AdamsCharity {

	public static void main(String[] args) {
		System.out.println(charity(5));
		System.out.println(lastDigit(3, 2));
		System.out.println(lastDigit(4, 2));
		System.out.println(lastDigit(142555, 241));
	}
	
	public static int charity(int input1) {
		int totalCoins = 0;
		for (int i = 1; i <= input1; i++) {
			totalCoins += i * i;
		}
		return totalCoins;
	}
	
	public static int lastDigit(int input1, int input2) {
		BigInteger power = BigInteger.valueOf(input1).pow(input2);
		return power.mod(BigInteger.TEN).intValue();
	}
}
