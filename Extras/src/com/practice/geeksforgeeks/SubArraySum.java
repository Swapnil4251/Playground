package com.practice.geeksforgeeks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/subarray-with-given-sum/0
 */
public class SubArraySum {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Test> list = new ArrayList<Test>();

		int noOfTests = sc.nextInt();
		for (int i = 0; i < noOfTests; i++) {
			int size = sc.nextInt();
			int expectedSum = sc.nextInt();
			int[] array = new int[size];
			for (int j = 0; j < size; j++) {
				array[j] = sc.nextInt();
			}
			list.add(new Test(size, expectedSum, array));
		}

		list.forEach(test -> printSubArrayIndexesMatchingGivenSum(test));
	}

	public static void printSubArrayIndexesMatchingGivenSum(Test test) {
		int start_index = 0;
		int end_index = start_index + 1;
		int sum = test.array[start_index];
		boolean isFound = false;
		for (int i = 0; i < test.array.length && end_index < test.array.length; i++) {
			sum += test.array[end_index];
			if (sum == test.expectedSum) {
				isFound = true;
				break;
			}
			if (sum < test.expectedSum) {
				end_index++;
			}
			if (sum > test.expectedSum) {
				start_index++;
				i = start_index;
				end_index = start_index + 1;
				sum = test.array[start_index];
			}
		}

		System.out.println(isFound ? (++start_index + " " + ++end_index) : -1);
	}

	public static class Test {
		int size;
		int expectedSum;
		int[] array;

		Test(int s, int sum, int[] arr) {
			this.size = s;
			this.expectedSum = sum;
			this.array = arr;
		}
	}
}
