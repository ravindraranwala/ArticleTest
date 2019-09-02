package com.amazon;

public class GCD {

	public static void main(String[] args) {
		// int[] arr = new int[] { 2, 3, 4, 5, 6 };
		// int[] arr = new int[] { 2, 4, 6, 8, 10 };
		int[] arr = new int[] { 25, 125, 625 };
		System.out.println(generalizedGCD(arr));
	}

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public static int generalizedGCD(int[] arr) {
		if (arr == null || arr.length == 0)
			return -1;
		if (arr.length == 1)
			return 1;
		// WRITE YOUR CODE HERE
		int gcd = gcd(arr[0], arr[1]);

		for (int i = 2, n = arr.length; i < n; i++) {
			gcd = gcd(gcd, arr[i]);
		}
		return gcd;
	}

	private static int gcd(int n1, int n2) {
		int remainder = n2 % n1;
		if (remainder == 0)
			return n1;
		return gcd(remainder, n1);
	}
}
