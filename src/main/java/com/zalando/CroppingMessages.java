package com.zalando;

import java.util.StringJoiner;
import java.util.regex.Pattern;

public class CroppingMessages {
	private static final Pattern SPACE = Pattern.compile(" ");

	public static void main(String[] args) {
		// String message = "Codility We test coders";
		// int K = 14;
		// String message = "Why not";
		// int K = 100;
//		String message = "The quick brown fox jumps over the lazy dog";
//		int K = 39;
		String message = "The quick brown fox jumps over the lazy dog";
		int K = 2;
		System.out.println(solution(message, K));
	}

	public static String solution(String message, int K) {
		// write your code in Java SE 8
		int currentMsgLength = 0;
		final String[] words = SPACE.split(message);
		final StringJoiner croppedMessage = new StringJoiner(" ");
		for (int i = 0, n = words.length; i < n; i++) {
			if (i == 0) {
				if (words[i].length() <= K) {
					croppedMessage.add(words[i]);
					currentMsgLength += words[i].length();
				} else
					break;
			} else if (currentMsgLength + words[i].length() + 1 <= K) {
				// Notice the white space char is taken in to account here.
				croppedMessage.add(words[i]);
				currentMsgLength += words[i].length() + 1;
			} else
				break;
		}
		return croppedMessage.toString();
	}
}
