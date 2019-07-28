package com.article;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArticleTestQ1 {

	public static void main(String[] args) {
		// int[] ranks = { 3, 4, 3, 0, 2, 2, 3, 0, 0 };
		// int[] ranks = { 4, 2, 0 };
		int[] ranks = { 4, 4, 3, 3, 1, 0 };
		System.out.println(solution(ranks));
	}

	public static int solution(int[] ranks) {
		// write your code in Java SE 8
		final Map<Integer, Long> rankToCountMap = Arrays.stream(ranks).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		long reportingCount = rankToCountMap.keySet().stream().filter(r -> rankToCountMap.containsKey(r + 1))
				.mapToLong(rankToCountMap::get).sum();
		return (int) reportingCount;
	}

}
