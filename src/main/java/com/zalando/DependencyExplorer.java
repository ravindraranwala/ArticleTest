package com.zalando;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DependencyExplorer {

	public static void main(String[] args) {
		Character[][] dependencies = new Character[][] { { 'A', 'B', 'C' }, { 'B', 'C', 'E' }, { 'C', 'G' },
				{ 'D', 'A', 'F' }, { 'E', 'F' }, { 'F', 'H' } };
		final Map<Character, Set<Character>> transitiveDependencies = exploreDependencies(dependencies);
		System.out.println(transitiveDependencies);
	}

	public static Map<Character, Set<Character>> exploreDependencies(Character[][] directDependencies) {
		final Map<Character, Set<Character>> directDependenciesMap = new HashMap<>();
		final Map<Character, Set<Character>> transitiveDependenciesMap = new HashMap<>();
		for (Character[] dependency : directDependencies) {
			Character[] arr = Arrays.copyOfRange(dependency, 1, dependency.length);
			directDependenciesMap.put(dependency[0], new HashSet<>(Arrays.asList(arr)));
		}

		for (Map.Entry<Character, Set<Character>> currEntry : directDependenciesMap.entrySet()) {
			final Set<Character> transitiveDependencies = findDeepDependencies(directDependenciesMap, currEntry.getValue());
			transitiveDependencies.addAll(currEntry.getValue());
			transitiveDependenciesMap.put(currEntry.getKey(), transitiveDependencies);
		}

		return transitiveDependenciesMap;
	}

	// This problem is inherently recursive one.
	private static Set<Character> findDeepDependencies(Map<Character, Set<Character>> map, Set<Character> values) {
		if (values.isEmpty())
			return Collections.emptySet();

		Set<Character> valuesCollected = new HashSet<>();
		for (Character character : values) {
			if (map.containsKey(character))
				valuesCollected.addAll(map.get(character));
		}
		Set<Character> newValues = findDeepDependencies(map, valuesCollected);
		valuesCollected.addAll(newValues);
		return valuesCollected;
	}
}
