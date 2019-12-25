package com.demo;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubLists {

	public static void main(String[] args) {
		Set<String> s = new HashSet<>(Arrays.asList("a", "b", "c"));
		System.out.println(SubLists.of(s));

		SubLists.of(s).stream().forEach(System.out::println);
		for (Set<?> subList : SubLists.of(s))
			System.out.println(subList);
	}

	static boolean contiguous(int n) {
		while (n > 0 && (n & 1) == 0)
			n >>= 1;
		while (n > 0 && (n & 1) == 1)
			n >>= 1;

		return n == 0;
	}

	boolean contiguous2(int n) {
		while (n > 0 && n % 2 == 0)
			n = n / 2;
		while (n > 0 && n % 2 == 1)
			n = n / 2;
		if (n > 0)
			return false;
		else
			return true;
	}

	public static final <E> Collection<Set<E>> of(Set<E> s) {
		final List<E> src = new ArrayList<E>(s);

		if (s.size() > Math.sqrt(Integer.MAX_VALUE))
			throw new IllegalArgumentException("Set too big: " + s);

		return new AbstractList<Set<E>>() {
			int contiguousIdx = size();

			@Override
			public Set<E> get(int index) {
				Set<E> result = new HashSet<>();

				while (!contiguous(index))
					index = contiguousIdx++;

				for (int i = 0; index != 0; i++, index >>= 1)
					if ((index & 1) == 1)
						result.add(src.get(i));

				return result;
			}

			@Override
			public int size() {
				int size = 1;
				for (int i = 0; i <= src.size(); i++)
					size += i;
				return size;
			}

			@Override
			public boolean contains(Object o) {
				return o instanceof Set && src.containsAll((Set<?>) o);
			}
		};
	}
}
