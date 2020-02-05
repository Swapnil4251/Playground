package com.app.util.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.app.util.NumberUtil;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class NumberUtilTest {

	@DataProvider
	public static Object[][] testDataForPrimeNumbers() {
		return new Object[][] {
			{ 1, false },
			{ 2, true },
			{ 3, true },
			{ 6, false },
			{ 7, true },
			{ 12, false },
			{ 17, true },
			{ 23, true },
			{ 25, false },
			{ 2477, true }
		};
	}
	
	@Test
	@UseDataProvider("testDataForPrimeNumbers")
	public void testIsPrimeNumber(int number, boolean result) {
		assertThat(NumberUtil.isPrimeNumber(number)).isEqualTo(result);
	}

	@Test
	public void testNumberOfDigits() {
		//System.out.println(NumberUtil.numberOfDigits(1256754));
	}
	
	@Test
	public void testIsArmstrongNumber() {
		System.out.println("Armstrong numbers between range 0 to 99999");
		for (int i = 0; i <= 99999; i++) {
			if (NumberUtil.isArmstrongNumber(i)) System.out.print(i + ",");
		}
		System.out.println();
	}
	
	@Test
	public void testIsPerfectNumber() {
		System.out.println("Perfect numbers between range 0 to 99999");
		for (int i = 0; i <= 99999; i++) {
			if (NumberUtil.isPerfectNumber(i)) System.out.print(i+ ", ");
		}
		System.out.println();
	}
	
	@Test
	public void testIsPalindromeNumber() {
		System.out.println("Palindrome numbers between range 0 to 99999");
		for (int i = 0; i <= 99999; i++) {
			if (NumberUtil.isPalindromeNumber(i)) System.out.print(i+ ", ");
		}
		System.out.println();
	}
}
