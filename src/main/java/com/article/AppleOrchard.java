package com.article;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppleOrchard {
	public static void main(String[] args) {
		int[] A = { 6, 1, 4, 6, 3, 2, 7, 4 };
		// int[] A = { 10, 19, 15 };
		// int[] A = { 6, 1, 4, 6, 3, 4, 7, 2 };
		System.out.println(solution(A, 3, 2));
	}

	public static int solution(int[] A, int K, int L) {
		final int length = A.length;
		if (length < K + L)
			return -1;
		// Max value from left to each index. The value just below the index is the max.
		int[] maxFromLeft = new int[length - L + 1];
		/*
		 * Max value from right to each index. The value just above the index is the
		 * max.
		 */
		int[] maxFromRight = new int[length - L + 1];

		final TreeMap<Integer, List<Integer>> sumOfKValuesToStartIdxMap = IntStream.rangeClosed(0, length - K).boxed()
				.collect(Collectors.groupingBy(i -> IntStream.range(i, i + K).map(j -> A[j]).sum(), TreeMap::new,
						Collectors.mapping(Function.identity(), Collectors.toList())));

		for (int i = 0, n = length - L + 1; i < n; i++) {
			int currentMaxFrmLeft = 0, currentMaxFrmRight = 0;
			for (int j = 0; j < L; j++) {
				currentMaxFrmLeft += A[i + j];
				currentMaxFrmRight += A[n - j - i];
			}

			maxFromLeft[i] = (i == 0 || maxFromLeft[i - 1] < currentMaxFrmLeft) ? currentMaxFrmLeft
					: maxFromLeft[i - 1];
			final int currentRightPosition = n - i - 1;
			maxFromRight[currentRightPosition] = (i == 0 || currentMaxFrmRight > maxFromRight[currentRightPosition + 1])
					? currentMaxFrmRight
					: maxFromRight[currentRightPosition + 1];

		}

		final Entry<Integer, List<Integer>> highestSumToIdxEntry = sumOfKValuesToStartIdxMap.lastEntry();
		int maxSum = 0;
		for (int i : highestSumToIdxEntry.getValue()) {
			int currentSum = 0;
			if (i == 0)
				currentSum = maxFromRight[i + K];
			else if (i == length - K)
				currentSum = maxFromLeft[i - 1];
			else
				currentSum = Integer.max(maxFromLeft[i - 1], maxFromRight[i + K]);
			maxSum = currentSum > maxSum ? currentSum : maxSum;
		}
		return maxSum + highestSumToIdxEntry.getKey();
	}
}
