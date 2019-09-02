package com.amazon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActiveInactiveCells {

	public static void main(String[] args) {
		int[] cells = { 1, 0, 1, 1 };
		int k = 2;
		List<Integer> cellStatus = cellCompete(cells, k);
		System.out.println(cellStatus);
	}

	public static List<Integer> cellCompete(int[] states, int days) {
		final int length = states.length;
		int[] previousState = new int[length];
		System.arraycopy(states, 0, previousState, 0, length);
		for (int d = 1; d <= days; d++) {
			int[] currentState = new int[length];
			for (int i = 0; i < length; i++) {
				if (i > 0 && i < length - 1)
					currentState[i] = previousState[i - 1] != previousState[i + 1] ? 1 : 0;
				else if (i == 0 && previousState[i + 1] == 1) {
					currentState[i] = 1;
				} else if (i == length - 1 && previousState[i - 1] == 1) {
					currentState[i] = 1;
				}
			}
			previousState = currentState;
		}
		return Arrays.stream(previousState).boxed().collect(Collectors.toList());
	}
}
