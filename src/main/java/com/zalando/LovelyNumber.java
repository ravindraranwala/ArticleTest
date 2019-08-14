package com.zalando;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LovelyNumber {

	public static void main(String[] args) {
		int start = 1, end = 111;
		System.out.println(solution(start, end));
	}
	
	public static int solution(int A, int B) {
        // write your code in Java SE 8
        return IntStream.rangeClosed(A, B)
			.filter(n -> String.valueOf(n).chars().map(Character::getNumericValue).boxed()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().stream()
					.allMatch(v -> v < 3))
			.map(n -> 1).sum();
    }

}
