package com.amazon;

import java.util.Arrays;

public class ReorderLogFiles {
	public static void main(String[] args) {
		final String[] logFile = new String[] { "a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog",
				"a8 act zoo" };
		String[] reorderedLogFile = reorderLogFiles(logFile);
		System.out.println(Arrays.toString(reorderedLogFile));
	}

	public static String[] reorderLogFiles(String[] logs) {
		Arrays.sort(logs, (log1, log2) -> {
			String[] split1 = log1.split(" ", 2);
			String[] split2 = log2.split(" ", 2);
			boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
			boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
			if (!isDigit1 && !isDigit2) {
				int cmp = split1[1].compareTo(split2[1]);
				if (cmp != 0)
					return cmp;
				return split1[0].compareTo(split2[0]);
			}
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
		});
		return logs;
	}
}
