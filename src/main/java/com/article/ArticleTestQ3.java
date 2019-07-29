package com.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
		List<Integer> maxSegStartIdxForThree = findMaxSequences(A, 0, A.length, K);
		int leftMax = 0, rightMax = 0;
		for (int segment : maxSegStartIdxForThree) {
			// If left half exists, then send it down.

			if (segment - 2 >= 0) {
				List<Integer> maxSequencesLft = findMaxSequences(A, 0, segment, L);
				final int currentLeftMax = IntStream.rangeClosed(maxSequencesLft.get(0), maxSequencesLft.get(0))
						.map(i -> A[i]).sum();
				leftMax = Integer.max(currentLeftMax, leftMax);
			}
			// If right half exists, then pass it down.
			if (segment + K - 1 + L < A.length) {
				List<Integer> maxSeqRght = findMaxSequences(A, segment + K, A.length, L);
				final int currentRIghtMax = IntStream.rangeClosed(maxSeqRght.get(0), maxSeqRght.get(0) + L - 1)
						.map(i -> A[i]).sum();
				rightMax = Integer.max(currentRIghtMax, rightMax);
			}
		}
		int maxThreeConsecutiveSum = IntStream
				.rangeClosed(maxSegStartIdxForThree.get(0), maxSegStartIdxForThree.get(0) + K - 1).map(i -> A[i]).sum();
		int maxTwoConsecutiveSum = Integer.max(leftMax, rightMax);
		return maxThreeConsecutiveSum + maxTwoConsecutiveSum;
	}

	private static List<Integer> findMaxSequences(int[] A, int start, int end, int size) {
		int max = 0;
		Set<Integer> maxSegmentsStartIdx = Collections.emptySet();
		for (int i = start; i <= end - size; i++) {
			int currentSeqSum = IntStream.range(i, i + size).map(n -> A[n]).sum();
			if (currentSeqSum > max) {
				maxSegmentsStartIdx = new HashSet<>();
				maxSegmentsStartIdx.add(i);
				max = currentSeqSum;
			} else if (currentSeqSum == max)
				maxSegmentsStartIdx.add(i);

		}

		return new ArrayList<>(maxSegmentsStartIdx);
	}
}
