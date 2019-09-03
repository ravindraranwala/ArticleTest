package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimalUtilization {

	public static void main(String[] args) {
		final int deviceCapacity = 7;
		List<List<Integer>> foregroundAppList = new ArrayList<>();
		foregroundAppList.add(Arrays.asList(1, 2));
		foregroundAppList.add(Arrays.asList(3, 6));
		foregroundAppList.add(Arrays.asList(2, 4));

		System.out.println("before sorting: " + foregroundAppList);

		List<List<Integer>> backgroundAppList = new ArrayList<>();
		backgroundAppList.add(Arrays.asList(1, 2));

		List<List<Integer>> pair = optimalUtilization(deviceCapacity, foregroundAppList, backgroundAppList);
		System.out.println(pair);
	}

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	static List<List<Integer>> optimalUtilization(int deviceCapacity, List<List<Integer>> foregroundAppList,
			List<List<Integer>> backgroundAppList) {
		List<Integer> foregroundPair = new ArrayList<>();
		Collections.sort(foregroundAppList, (l1, l2) -> Integer.compare(l1.get(1), l2.get(1)));
		Collections.sort(backgroundAppList, (l1, l2) -> Integer.compare(l1.get(1), l2.get(1)));
		System.out.println("after sorting: " + foregroundAppList);

		int maxForegroundCap = 0;

		for (int lo = 0, hi = foregroundAppList.size() - 1; lo < hi;) {
			int curentCap = foregroundAppList.get(lo).get(1) + foregroundAppList.get(hi).get(1);
			if (curentCap <= deviceCapacity) {
				if (curentCap > maxForegroundCap) {
					curentCap = maxForegroundCap;
					foregroundPair = new ArrayList<>();
					foregroundPair.add(lo);
					foregroundPair.add(hi);
					lo++;
				}
			} else
				hi--;
		}

		int maxBackgroundCap = 0;
		List<Integer> backgroundPair = new ArrayList<>();
		for (int lo = 0, hi = backgroundAppList.size() - 1; lo < hi;) {
			int curentCap = backgroundAppList.get(lo).get(1) + backgroundAppList.get(hi).get(1);
			if (curentCap <= deviceCapacity) {
				if (curentCap > maxBackgroundCap) {
					curentCap = maxBackgroundCap;
					backgroundPair = new ArrayList<>();
					backgroundPair.add(lo);
					backgroundPair.add(hi);
					lo++;
				}
			} else
				hi--;
		}

		int maxCombinationCap = 0;
		List<Integer> combinationPair = new ArrayList<>();
		for (int i = 0, n = foregroundAppList.size(), j = backgroundAppList.size() - 1; i < n && j >= 0; i++) {
			int currentMixCap = foregroundAppList.get(i).get(1) + backgroundAppList.get(j).get(1);
			if (currentMixCap <= deviceCapacity) {
				if (currentMixCap > maxCombinationCap) {
					maxCombinationCap = currentMixCap;
					combinationPair = new ArrayList<>();
					combinationPair.add(i);
					combinationPair.add(j);
				}
			} else
				j--;
		}

		System.out.println("Max foreground pair: " + foregroundPair);
		System.out.println("Max background pair: " + backgroundPair);
		System.out.println("Max mixground pair: " + combinationPair);

		int mixCap = 0;
		if (foregroundAppList.size() > 0 && backgroundAppList.size() > 0) {
			mixCap = foregroundAppList.get(combinationPair.get(0)).get(1)
					+ backgroundAppList.get(combinationPair.get(1)).get(1);
		}

		int foreCap = 0;
		if (foregroundAppList.size() > 1) {
			foreCap = foregroundAppList.get(foregroundPair.get(0)).get(1)
					+ foregroundAppList.get(foregroundPair.get(1)).get(1);
		}

		int backCap = 0;
		if (backgroundAppList.size() > 1) {
			foreCap = backgroundAppList.get(foregroundPair.get(0)).get(1)
					+ backgroundAppList.get(foregroundPair.get(1)).get(1);
		}

		List<List<Integer>> res = new ArrayList<>();
		if (foreCap > mixCap && foreCap > backCap) {
			List<Integer> temp = new ArrayList<>();
			temp.add(foregroundAppList.get(foregroundPair.get(0)).get(0));
			temp.add(foregroundAppList.get(foregroundPair.get(1)).get(0));
			res.add(temp);
		}
		if (backCap > mixCap && backCap > foreCap) {
			List<Integer> temp = new ArrayList<>();
			temp.add(backgroundAppList.get(backgroundPair.get(0)).get(0));
			temp.add(backgroundAppList.get(backgroundPair.get(1)).get(0));
			res.add(temp);
		} else {
			List<Integer> temp = new ArrayList<>();
			temp.add(foregroundAppList.get(combinationPair.get(0)).get(0));
			temp.add(backgroundAppList.get(combinationPair.get(1)).get(0));
			res.add(temp);
		}
		return res;
		// WRITE YOUR CODE HERE
	}
	// METHOD SIGNATURE ENDS
}
