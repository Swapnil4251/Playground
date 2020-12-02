package com.practice.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SockMerchant {

	// Complete the sockMerchant function below.
	static int sockMerchant(int n, int[] ar) {
    		int numberOfMatchingPairs = 0;
    		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    		for (int i = 0; i < ar.length; i++) {
    			map.put(ar[i], map.getOrDefault(ar[i], 0) + 1);
    		}
    		for (int i : map.values()) {
    			numberOfMatchingPairs += i/2;
    		}
    		return numberOfMatchingPairs;
    }

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = scanner.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			int arItem = scanner.nextInt();
			ar[i] = arItem;
		}

		int result = sockMerchant(n, ar);
		System.out.println(result);
		//bufferedWriter.write(String.valueOf(result));
		//bufferedWriter.newLine();

		//bufferedWriter.close();

		scanner.close();
	}
}
