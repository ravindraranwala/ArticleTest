package com.amazon;

import java.util.ArrayDeque;
import java.util.Queue;

public final class TreasureIsland {
	public static void main(String[] args) {
		final char[][] island = new char[][] { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'X', 'D', 'D', 'O' } };

//		char[][] map2 = new char[][] { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
//				{ 'O', 'D', 'D', 'O' }, { 'O', 'D', 'X', 'O' } };
		final int minSteps = findMinStepsToGetTreasure(island);
		System.out.println(String.format("The minimum route takes %d steps.", minSteps));
	}

	public static int findMinStepsToGetTreasure(char[][] island) {
		final int numOfCols = island[0].length;
		final int numOfRows = island.length;

		final boolean[][] discoveryMatrix = new boolean[numOfRows][numOfCols];

		// start at top left position.
		final Queue<Vertex> queue = new ArrayDeque<>();
		final Vertex s = new Vertex(0, 0, 0, null);

		discoveryMatrix[0][0] = true;
		queue.add(s);

		while (!queue.isEmpty()) {
			final Vertex u = queue.poll();

			// traverse over u's adjacency list.
			final int row = u.getRow();
			final int col = u.getCol();

			if (island[row][col] == 'X')
				return u.getD();

			final int currentD = u.getD() + 1;
			// move left.
			if (col - 1 >= 0 && island[row][col - 1] != 'D' && !discoveryMatrix[row][col - 1])
				discover(queue, discoveryMatrix, new Vertex(row, col - 1, currentD, u));
			// move right
			if (col + 1 < numOfCols && island[row][col + 1] != 'D' && !discoveryMatrix[row][col + 1])
				discover(queue, discoveryMatrix, new Vertex(row, col + 1, currentD, u));
			// move up.
			if (row - 1 >= 0 && island[row - 1][col] != 'D' && !discoveryMatrix[row - 1][col])
				discover(queue, discoveryMatrix, new Vertex(row - 1, col, currentD, u));
			// move down
			if (row + 1 < numOfRows && island[row + 1][col] != 'D' && !discoveryMatrix[row + 1][col])
				discover(queue, discoveryMatrix, new Vertex(row + 1, col, currentD, u));
		}
		return 0;
	}

	private static void discover(Queue<Vertex> queue, final boolean[][] discoveryMatrix, Vertex vertex) {
		queue.offer(vertex);
		discoveryMatrix[vertex.getRow()][vertex.getCol()] = true;
	}
}
