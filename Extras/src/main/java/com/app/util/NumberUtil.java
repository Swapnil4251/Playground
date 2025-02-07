package com.app.util;

import java.math.BigInteger;

public class NumberUtil {

	/**
	 * A prime number is a whole number greater than 1 whose only factors are 1 and itself. 
	 * A factor is a whole numbers that can be divided evenly into another number. 
	 * The first few prime numbers are 2, 3, 5, 7, 11, 13, 17, 19, 23 and 29. Numbers
	 * */
	public static boolean isPrimeNumber(int number) {
		if (number == 0 || number == 1) {
			return false;
		}
		
		for (int i = number/2; i > 1; i--) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * In recreational number theory, an Armstrong Number, also called as <b>Narcissistic number</b>, is a number that is the sum of its own digits 
	 * each raised to the power of the number of digits.
	 * This definition depends on the base b of the number system used, 
	 * e.g., b = 10 for the decimal system or b = 2 for the binary system.
	 * */
	
	public static boolean isArmstrongNumber(int number) {
		
		double sumOfPowers = 0;
		int numberOfDigits = numberOfDigits(number);
		
		for (int i = number; i > 0; i = i / 10) {
			sumOfPowers += Math.pow(i % 10, numberOfDigits);
		}
		
		return sumOfPowers == number;
	}
	
	/**
	 * In number theory, a perfect number is a positive integer that is equal to the sum of its positive divisors, 
	 * excluding the number itself. 
	 * For instance, 6 has divisors 1, 2 and 3, and 1 + 2 + 3 = 6, so 6 is a perfect number.
	 * */
	public static boolean isPerfectNumber(int number) {
		int sum = 0;
		for (int i = 1; i <= number / 2; i++) {
			if (number % i == 0) {
				sum += i;
			}
		}
		return sum == number;
	}
	
	/**
	 * A palindromic number is a number that remains the same when its digits are reversed. 
	 * Like 16461, for example, it is "symmetrical". 
	 * The term palindromic is derived from palindrome, which refers to a word 
	 * whose spelling is unchanged when its letters are reversed.
	 * */
	public static boolean isPalindromeNumber(int number) {
		return isPalindromeString(String.valueOf(number));
	}
	
	public static boolean isPalindromeString(String string) {
		for (int i = 0, j = string.length() - 1; i <= string.length() / 2; i++, j--) {
			if (string.charAt(i) != string.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	public static BigInteger getFactorialOf(int number) {
		//TODO
		return BigInteger.ZERO;
	}
	
	public static int[] getFibonacciSeries(int upto) {
		//TODO
		return new int[] { upto };
	}
	
	public static int numberOfDigits(int number) {
		int count = 0;
		for (int i = number; i > 0; i= i / 10) {
			count++;
		}
		return count;
	}
}