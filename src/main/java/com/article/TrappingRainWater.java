package com.article;

public class TrappingRainWater {

	public static void main(String[] args) {
		// final int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// final int[] height = { 2, 1, 0, 2 };
		// final int[] height = { 0, 1, 0, 2, 1, 0, 1, 3 };
		// final int[] height = { 5, 2, 1, 2, 1, 5 };
		// final int[] height = { 5, 5, 1, 7, 1, 1, 5, 2, 7, 6 };
		// final int[] height = { 4, 2, 0, 3, 2, 5 };
		// final int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// final int[] height = { 5, 2, 1, 2, 1, 5 };
		// final int[] height = { 5, 5, 1, 7, 1, 1, 5, 2, 7, 6 };
		// final int[] height = { 0, 7, 1, 4, 6 };
		// final int[] height = { 0, 5, 6, 4, 6, 1, 0, 0, 2, 7 };
		final int[] height = { 6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3 };
		int trappedWaterAmt = new TrappingRainWater().collectWater(height);
		System.out.println(trappedWaterAmt);
	}

	/**
	 * This answers the question: https://leetcode.com/problems/trapping-rain-water/
	 * 
	 * @param height the height of the bars
	 * @return amount of water trapped in units.
	 */
	public int collectWater(int[] height) {
		int lh = height[0];
		final int n = height.length;
		int rh = height[n - 1];
		int lv = 0;
		int rv = 0;
		int v = 0;

		for (int i = 0; i < n; i++) {
			if (lh <= height[i]) {
				lh = height[i];
				v = v + lv;
				lv = 0;
			}
			lv = lv + lh - height[i];
			if (rh < height[n - i - 1]) {
				rh = height[n - i - 1];
				v = v + rv;
				rv = 0;
			}
			rv = rv + rh - height[n - i - 1];
		}
		return v;
	}
}
