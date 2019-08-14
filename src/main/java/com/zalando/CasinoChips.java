package com.zalando;

public class CasinoChips {

	public static void main(String[] args) {
		// int N = 18, K = 2;
		// int N = 8, K = 0;
		int N = 10, K = 10;
		System.out.println(solution(N, K));
	}

    public static int solution(int N, int K) {
    	int rounds = 0;
        // write your code in Java SE 8
    	while(N >= 2)
    	{
    		rounds++;
    		if (N % 2 == 0 && K > 0) {
    			N = N / 2;
    			K--;
    		} else
    			N--;
    	}
    	return rounds;
    }
}
