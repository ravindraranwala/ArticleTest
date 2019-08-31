package com.demo;

import java.util.Arrays;

public class MissingInteger {

	public static void main(String[] args) {
		// int[] A = new int[] { 1, 3, 6, 4, 1, 2 };
		// int[] A = new int[] { 1, 2, 3 };
		int[] A = new int[] { -1, -3 };
		System.out.println(solution(A));
		System.out.println(Arrays.toString(A));
	}

	public static int solution(int[] A) {
		// write your code in Java SE 8
		final int arrLength = A.length - 1;
		MergeSort.sort(A, 0, arrLength);
		int missingInteger = 1;

		for (int i = 0, n = A.length; i < n; i++) {
			if (A[i] > 0 && A[i] == missingInteger)
				missingInteger++;
			if (A[i] > 0 && A[i] > missingInteger)
				return missingInteger;
		}
		return missingInteger;
	}
}
