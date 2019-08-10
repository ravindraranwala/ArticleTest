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
	 * @param height
	 *            the height of the bars
	 * @return amount of water trapped in units.
	 */
	public int collectWater(int[] height) {
		int ans = 0;
		if (height == null || height.length == 0)
			return ans;
		final int size = height.length;
		int[] leftMax = new int[size], rightMax = new int[size];
		leftMax[0] = height[0];
		for (int i = 1; i < size; i++)
			leftMax[i] = Integer.max(leftMax[i - 1], height[i]);

		rightMax[size - 1] = height[size - 1];
		for (int i = size - 2; i >= 0; i--)
			rightMax[i] = Integer.max(height[i], rightMax[i + 1]);

		for (int i = 0; i < size; i++)
			ans += Integer.min(rightMax[i], leftMax[i]) - height[i];
		return ans;
	}
}
