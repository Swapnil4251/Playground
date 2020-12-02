package com.interview.pratice.sorting;

import org.junit.Test;

public class SortingAlgorithmTest {
	
	@Test
	public void testSelectionSort() {
		int[] array = new int[] { 45, 13, 12, 89, 73, 65, 54, 88, 43, 103, 1 };
		
		int[] sortedArray = SelectionSort.sort(array);
		for (int i = 0; i < sortedArray.length; i++) {
			System.out.print(sortedArray[i] + ", ");
		}
		
		array = new int[] { 5, 43, 55, 6, 78, 4, 6, 3, 2, 65, 7 };
		sortedArray = SelectionSort.sort(array);
		for (int i = 0; i < sortedArray.length; i++) {
			System.out.print(sortedArray[i] + ", ");
		}
	}
	
}
