package com.amazon;

import java.util.Arrays;
import java.util.Comparator;

public class Kclosest {
	public static void main(String[] args) {
		// System.out.println(Arrays.toString(nearestPostOffices));
		int[][] points = new int[][] { { -16, 5 }, { -1, 2 }, { 4, 3 }, { 10, -2 }, { 0, 3 }, { -5, -9 } };
		// kClosestJava8(points, 3);
		int[][] kClosest = kClosest(points, 3);
		System.out.println(Arrays.deepToString(kClosest));

	}

	public static int[][] kClosestJava8(int[][] points, int K) {
		return Arrays.stream(points).sorted(Comparator.comparingInt(a -> Math.abs(a[0]) + Math.abs(a[1]))).limit(K)
				.toArray(int[][]::new);
	}

	/**
	 * You may return the answer in any order. The answer is guaranteed to be unique
	 * (except for the order that it is in.)
	 * 
	 * @param points
	 * @param K
	 * @return
	 */
	public static int[][] kClosest(int[][] points, int K) {
		int N = points.length;
		int[] dists = new int[N];
		for (int i = 0; i < N; ++i)
			dists[i] = dist(points[i]);

		Arrays.sort(dists);
		// Get the threshold distance first.
		int distK = dists[K - 1];

		int[][] ans = new int[K][2];
		int t = 0;
		for (int i = 0; i < N; ++i)
			if (dist(points[i]) <= distK)
				ans[t++] = points[i];
		return ans;
	}

	public static int dist(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}
}
