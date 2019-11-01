package com.amazon;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You have a map that marks the location of a treasure island. Some of the map
 * area has jagged rocks and dangerous reefs. Other areas are safe to sail in.
 * There are other explorers trying to find the treasure. So you must figure out
 * a shortest route to the treasure island. Assume the map area is a two
 * dimensional grid, represented by a matrix of characters. You must start from
 * the top-left corner of the map and can move one block up, down, left or right
 * at a time. The treasure island is marked as ‘X’ in a block of the matrix. ‘X’
 * will not be at the top-left corner. Any block with dangerous rocks or reefs
 * will be marked as ‘D’. You must not enter dangerous blocks. You cannot leave
 * the map area. Other areas ‘O’ are safe to sail in. The top-left corner is
 * always safe. Output the minimum number of steps to get to the treasure.
 * 
 * e.g. Input [ [‘O’, ‘O’, ‘O’, ‘O’], [‘D’, ‘O’, ‘D’, ‘O’], [‘O’, ‘O’, ‘O’,
 * ‘O’], [‘X’, ‘D’, ‘D’, ‘O’], ]
 * 
 * Output from aonecode.com Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3,
 * 0) The minimum route takes 5 steps.
 * 
 * @author URANWRA
 *
 */
public class TreasureIslandOne {
	private static int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) {
		char[][] map = { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'X', 'D', 'D', 'O' } };
		char[][] map2 = new char[][] { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'O', 'D', 'D', 'O' }, { 'O', 'D', 'X', 'O' } };
		int minSteps = explore(map);
		System.out.println(String.format("The minimum route takes %d steps.", minSteps));
	}

	public static int explore(char[][] island) {
		// Doing the validation first.
		if (island == null || island.length == 0 || island[0].length == 0 || island[0][0] != 'O')
			throw new IllegalArgumentException("Invalid island passed in.");
		int rows = island.length, columns = island[0].length;
		boolean[][] visited = new boolean[rows][columns];
		Queue<Point> queue = new ArrayDeque<>();
		// For possible moving directions.

		// Starting point, top left corner.
		Point bigin = new Point(0, 0);

		queue.offer(bigin);
		visited[0][0] = true;// mark as visited

		// Since we skip the last step increment when an 'X' is encountered.
		for (int steps = 1; !queue.isEmpty(); steps++) {
			for (int i = 0, n = queue.size(); i < n; i++) {
				Point point = queue.poll();
				// Notice that the four direction is the implicit adjacency list.
				for (int[] direction : directions) {
					int r = point.r + direction[0], c = point.c + direction[1];
					if (r >= 0 && r < rows && c >= 0 && c < columns && island[r][c] != 'D' && !visited[r][c]) {
						if (island[r][c] == 'X')
							return steps;
						/*
						 * Mark the node as gray since we have already visited that. We don't need to
						 * revisit this node again.
						 */
						queue.offer(new Point(r, c));
						visited[r][c] = true;
					}

				}
			}
		}
		return -1;
	}
	
	public static class Point {
		final int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
