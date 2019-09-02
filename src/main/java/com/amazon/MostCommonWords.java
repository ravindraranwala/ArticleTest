package com.amazon;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MostCommonWords {
	private static final Pattern DELIMITER = Pattern.compile("[^A-Za-z]+");

	public static void main(String[] args) {
		final String literatureSText = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack’s and Jill’s favorite food.";
		String[] wordsToExclude = new String[] { "and", "he", "the", "to", "is", "Jack", "Jill" };
		String[] freequentWords = mostFreequentWords(literatureSText, wordsToExclude);
		System.out.println(Arrays.toString(freequentWords));
	}

	public static String[] mostFreequentWordsJava8(String paragraph, String[] banned) {
		String lowercaseLiteratureText = paragraph.toLowerCase();
		final Set<String> bannedSet = Arrays.stream(banned).map(String::toLowerCase).collect(Collectors.toSet());
		final Map<String, Long> wordFreequencyMap = DELIMITER.splitAsStream(lowercaseLiteratureText)
				.filter(Objects::nonNull).filter(w -> !bannedSet.contains(w))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		long highestFreequency = wordFreequencyMap.values().stream().max(Comparator.naturalOrder())
				.orElseThrow(IllegalStateException::new);
		return wordFreequencyMap.entrySet().stream().filter(e -> e.getValue() == highestFreequency)
				.map(Map.Entry::getKey).toArray(String[]::new);
	}

	public static String[] mostFreequentWords(String paragraph, String[] banned) {
		int highestFreequency = 0;
		Set<String> banset = new HashSet<>();
		for (String bann : banned)
			banset.add(bann.toLowerCase());

		String[] words = DELIMITER.split(paragraph);
		Collection<String> mostFreequentWords = null;
		Map<String, Integer> wordFreequencyMap = new HashMap<>();
		for (String word : words) {
			if (word != null) {
				final String lowerCaseWord = word.toLowerCase();
				if (!banset.contains(lowerCaseWord)) {
					final int currentFreequency = wordFreequencyMap.merge(lowerCaseWord, 1, (a, b) -> a + b);
					if (currentFreequency > highestFreequency) {
						highestFreequency = currentFreequency;
						mostFreequentWords = new HashSet<>();
						mostFreequentWords.add(lowerCaseWord);
					} else if (currentFreequency == highestFreequency)
						mostFreequentWords.add(lowerCaseWord);
				}
			}
		}

		return mostFreequentWords.toArray(new String[0]);
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		paragraph += ".";

		Set<String> banset = new HashSet<>();
		for (String word : banned)
			banset.add(word);
		Map<String, Integer> count = new HashMap<>();

		String ans = "";
		int ansfreq = 0;

		StringBuilder word = new StringBuilder();
		for (char c : paragraph.toCharArray()) {
			if (Character.isLetter(c)) {
				word.append(Character.toLowerCase(c));
			} else if (word.length() > 0) {
				String finalword = word.toString();
				if (!banset.contains(finalword)) {
					count.put(finalword, count.getOrDefault(finalword, 0) + 1);
					if (count.get(finalword) > ansfreq) {
						ans = finalword;
						ansfreq = count.get(finalword);
					}
				}
				word = new StringBuilder();
			}
		}

		return ans;
	}
}
