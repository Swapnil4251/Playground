package com.interview.pratice.sorting;

public class SelectionSort {

	public static int[] sort(int[] array) { // 12, 13, 45, 73, 89
		int length = array.length; // 5
		int min_index = 0; // min_index = 5
		for (int i = 0; i < length - 1; i++) { // i = 5, 
			for (int j = i; j <= length - 1; j++) { // j = 5
				if (array[j] < array[min_index]) { // 89 < 89 = false  
					min_index = j;  // min_index = 5
				}
			}
			int min = array[min_index]; // min = 89
			array[min_index] = array[i]; // 12, 13, 45, 73, 89
			array[i] = min; // 12, 13, 45, 73, 89
			min_index = i + 1; // min_index = 5
		}
		return array;
	}
	
}