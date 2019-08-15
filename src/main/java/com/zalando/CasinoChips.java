package com.zalando;

public class CasinoChips {

	public static void main(String[] args) {
		// int N = 18, K = 2;
		// int N = 8, K = 0;
		int N = 10, K = 10;
		// int N = 2, K = 10;
		System.out.println(solution(N, K));
	}

	public static int solution(int N, int K) {
		// write your code in Java SE 8
		int rounds = 0;
		while (N >= 2) {
			if (K > 0) {
				rounds++;
				if (N % 2 == 0 && N > 2) {
					N = N / 2;
					K--;
				} else
					N--;

			} else {
				rounds += N - 1;
				break;
			}

		}
		return rounds;
	}
}
