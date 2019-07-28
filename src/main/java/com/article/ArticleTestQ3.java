package com.article;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

public class ArticleTestQ3 {

	public static void main(String[] args) {
		int[] A = { 6, 1, 4, 6, 3, 2, 7, 4 };
		// int[] A = { 10, 19, 15 };
		System.out.println(solution(A, 3, 2));
	}

	public static int solution(int[] A, int K, int L) {
		// write your code in Java SE 8
		// No disjoint sub sets for given K and L
		if (A.length < K + L)
			return -1;
		// First create sub sets of size 3 given the row.
		List<Entry<Integer, Integer>> maxSegmentsOfSizeThree = findMaxSequences(A, 0, A.length, 3);
		int leftMax = 0, rightMax = 0;
		for (Entry<Integer, Integer> segment : maxSegmentsOfSizeThree) {
			// If left half exists, then send it down.

			if (segment.getKey() - 2 >= 0) {
				List<Entry<Integer, Integer>> maxSequencesLft = findMaxSequences(A, 0, segment.getKey(), 2);
				final int currentLeftMax = IntStream
						.rangeClosed(maxSequencesLft.get(0).getKey(), maxSequencesLft.get(0).getValue()).map(i -> A[i])
						.sum();
				leftMax = Integer.max(currentLeftMax, leftMax);
			}
			// If right half exists, then pass it down.
			if (segment.getValue() + 2 < A.length) {
				List<Entry<Integer, Integer>> maxSeqRght = findMaxSequences(A, segment.getValue() + 1, A.length, 2);
				final int currentRIghtMax = IntStream
						.rangeClosed(maxSeqRght.get(0).getKey(), maxSeqRght.get(0).getValue()).map(i -> A[i]).sum();
				rightMax = Integer.max(currentRIghtMax, rightMax);
			}
		}
		int maxThreeConsecutiveSum = IntStream
				.rangeClosed(maxSegmentsOfSizeThree.get(0).getKey(), maxSegmentsOfSizeThree.get(0).getValue())
				.map(i -> A[i]).sum();
		int maxTwoConsecutiveSum = Integer.max(leftMax, rightMax);
		return maxThreeConsecutiveSum + maxTwoConsecutiveSum;
	}

	private static List<Map.Entry<Integer, Integer>> findMaxSequences(int[] A, int start, int end, int size) {
		int max = 0;
		List<Map.Entry<Integer, Integer>> maxSegmentsOfSize = Collections.emptyList();
		for (int i = start; i <= end - size; i++) {
			int currentSeqSum = IntStream.range(i, i + size).map(n -> A[n]).sum();
			if (currentSeqSum > max) {
				maxSegmentsOfSize = new ArrayList<>();
				maxSegmentsOfSize.add(new AbstractMap.SimpleEntry<>(i, i + size - 1));
				max = currentSeqSum;
			} else if (currentSeqSum == max)
				maxSegmentsOfSize.add(new AbstractMap.SimpleEntry<>(i, i + size - 1));

		}

		return maxSegmentsOfSize;
	}
}
