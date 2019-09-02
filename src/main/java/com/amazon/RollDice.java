package com.amazon;

public class RollDice {

	public static void main(String[] args) {
		// int[] pips = new int[] { 1, 2, 3 };
		// int[] pips = new int[] { 1, 1, 6 };
		int[] pips = new int[] { 1, 6, 2, 3 };
		// int[] pips = new int[] { 1, 2, 5, 6 };
		// int[] pips = new int[] { 1, 2, 6 };
		// int[] pips = new int[] { 1, 1, 6, 6 };
		// int[] pips = new int[] { 1, 2, 3, 4, 5, 6 };
		int steps = rollDice(pips);
		System.out.println(steps);
	}

	public static int rollDice(int[] pips) {
		int numOfDices = pips.length;
		int oppositePairs = 0;
		// First sort the array. This is similar to two sum closest with value 7.
		int[] count = new int[7];

		// counting occurrences of each number in the pips array and placing in count[]
		for (int num : pips)
			count[num]++;

		for (int lo = 1, hi = count.length - 1; lo < hi;) {
			oppositePairs += Integer.min(count[lo], count[hi]);
			lo++;
			hi--;
		}

		return oppositePairs * 2 == numOfDices ? numOfDices : numOfDices - 1;
	}
}
