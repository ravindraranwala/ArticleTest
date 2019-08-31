package com.amazon;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * You are on a flight and wanna watch two movies during this flight. You are
 * given int[] movie_duration which includes all the movie durations. You are
 * also given the duration of the flight which is d in minutes. Now, you need to
 * pick two movies and the total duration of the two movies is less than or
 * equal to (d - 30min). Find the pair of movies with the longest total
 * duration. If multiple found, return the pair with the longest movie.
 * 
 * e.g. Input movie_duration: [90, 85, 75, 60, 120, 150, 125] d: 250
 * 
 * Output from aonecode.com [90, 125] 90min + 125min = 215 is the maximum number
 * within 220 (250min - 30min)
 * 
 * @author URANWRA
 *
 */
public class MoviesOnFlight {

	public static void main(String[] args) {
		final int[] movie_duration = { 90, 85, 75, 60, 120, 150, 125 };
		final int d = 250;
		int[] candidateMovies = moviesWatched(movie_duration, d);
		System.out.println(Arrays.toString(candidateMovies));
	}

	public static int[] moviesWatched(int[] movie_duration, int duration) {
		final int effectiveDuration = duration - 30;
		/*
		 * First get all the combinations of 2 movies with total duration is < duration
		 * - 30.
		 */
		int[][] candidateMoviePairs = IntStream.range(0, movie_duration.length)
				.mapToObj(i -> IntStream.range(i + 1, movie_duration.length)
						.filter(j -> movie_duration[i] + movie_duration[j] <= effectiveDuration)
						.mapToObj(j -> new int[] { movie_duration[i], movie_duration[j] }))
				.flatMap(Function.identity()).toArray(int[][]::new);

		// Then get the highest element among the candidates.
		int maxIdx = 0;

		for (int k = 0, n = candidateMoviePairs.length; k < n; k++) {
			int[] currentPair = candidateMoviePairs[k];
			int[] currentMaxPair = candidateMoviePairs[maxIdx];
			if (currentPair[0] + currentPair[1] > currentMaxPair[0] + currentMaxPair[1])
				maxIdx = k;
			// Handling a tie.
			if (currentPair[0] + currentPair[1] == currentMaxPair[0] + currentMaxPair[1])
				// If multiple found, return the pair with the longest movie.
				maxIdx = Integer.max(currentPair[0], currentPair[1]) >= Integer.max(currentMaxPair[0],
						currentMaxPair[1]) ? k : maxIdx;
		}

		return candidateMoviePairs[maxIdx];
	}
}
