package com.amazon;

import java.util.Arrays;

import com.demo.MergeSort;

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

	/**
	 * It's two sum closest problem. Time complexity: O(nlogn). Space complexity:
	 * O(1)
	 * 
	 * @param movie_duration
	 * @param duration
	 * @return
	 */
	public static int[] moviesWatched(int[] movie_duration, int duration) {
		final int effectiveDuration = duration - 30;
		MergeSort.sort(movie_duration, 0, movie_duration.length - 1);
		// To store the indexes of the result pair.
		int resL = 0, resR = 0;
		// Initialize left, right indexes and the difference between the pair sum and x.
		int minimumDiff = Integer.MAX_VALUE;
		for (int l = 0, r = movie_duration.length - 1; l < r;) {
			// Check if this pair is closer than the closest pair so far.
			final int currentDiff = effectiveDuration - (movie_duration[l] + movie_duration[r]);
			if (currentDiff > 0) {
				if (currentDiff < minimumDiff) {
					resL = l;
					resR = r;
					minimumDiff = currentDiff;
				}
				l++; // Move to larger values.
			}

			// If this pair has more sum, move to smaller values.
			else if (currentDiff < 0)
				r--;
			else if (Integer.max(movie_duration[l], movie_duration[r]) > Integer.max(movie_duration[resL],
					movie_duration[resR])) {
				resL = l;
				resR = r;
			}
		}
		return new int[] { movie_duration[resL], movie_duration[resR] };
	}
}
