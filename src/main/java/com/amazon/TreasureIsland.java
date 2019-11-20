package com.amazon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringJoiner;

public final class TreasureIsland {
	public static void main(String[] args) {
		final char[][] map = new char[][] { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'X', 'D', 'D', 'O' } };

//		final char[][] map2 = new char[][] { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
//				{ 'O', 'D', 'D', 'O' }, { 'O', 'D', 'X', 'O' } };
		final String minStepsAndRoute = findMinStepsToTreasure(map);
		System.out.println(minStepsAndRoute);
	}

	public static String printRoute(Vertex v) {
		if (v.parent == null)
			return v.printBlock();

		return printRoute(v.parent) + ", " + v.printBlock();
	}

	public static String findMinStepsToTreasure(char[][] mapArea) {
		final int numOfCols = mapArea[0].length;
		final int numOfRows = mapArea.length;

		final boolean[][] discoveryMatrix = new boolean[numOfRows][numOfCols];
		final int[][] moves = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

		// start at top left position.
		final Queue<Vertex> queue = new ArrayDeque<>();
		final Vertex s = new Vertex(0, 0, 0, null);

		discoveryMatrix[0][0] = true;
		queue.add(s);

		while (!queue.isEmpty()) {
			final Vertex u = queue.poll();
			for (int[] move : moves) {
				final int currentD = u.d + 1;
				final int newRow = u.row + move[0];
				final int newCol = u.col + move[1];

				if ((0 <= newRow && newRow < numOfRows) && (0 <= newCol && newCol < numOfCols)
						&& mapArea[newRow][newCol] != 'D' && !discoveryMatrix[newRow][newCol]) {
					final Vertex v = new Vertex(newRow, newCol, currentD, u);
					if (mapArea[newRow][newCol] == 'X')
						return new StringBuilder().append(String.format("The minimum route takes %d steps. ", v.d))
								.append("Route is: " + printRoute(v)).toString();

					queue.offer(v);
					discoveryMatrix[newRow][newCol] = true;
				}
			}
		}
		return "";
	}

	static class Vertex {
		private final int row;
		private final int col;
		private final int d;
		private final Vertex parent;

		Vertex(int row, int col, int d, Vertex parent) {
			super();
			this.row = row;
			this.col = col;
			this.d = d;
			this.parent = parent;
		}

		public String printBlock() {
			return new StringJoiner(", ", "(", ")").add(String.valueOf(row)).add(String.valueOf(col)).toString();
		}
	}
}
