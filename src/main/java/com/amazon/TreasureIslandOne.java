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
		Queue<int[]> queue = new ArrayDeque<>();
		// For possible moving directions.
		int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		// Starting point, top left corner.
		int[] bigin = new int[] { 0, 0 };

		queue.offer(bigin);
		visited[0][0] = true;

		// Since we skip the last step increment when an 'X' is encountered.
		int steps = 1;
		while (!queue.isEmpty()) {
			for (int i = 0, n = queue.size(); i < n; ++i) {
				int[] coordinate = queue.poll();
				// Notice that the four direction is the implicit adjacency list.
				for (int[] direction : directions) {
					int x = coordinate[0] + direction[0], y = coordinate[1] + direction[1];
					if (x < 0 || x >= rows || y < 0 || y >= columns || island[x][y] == 'D' || visited[x][y])
						continue;

					if (island[x][y] == 'X')
						return steps;
					/*
					 * Mark the node as grey since we have already visited that. We don't need to
					 * revisit this node again.
					 */
					queue.offer(new int[] { x, y });
					visited[x][y] = true;

				}
			}
			steps++;
		}
		return steps;
	}

	// private static int minDistToTreasureIsland(char[][] island) {
	//
	// int step = 0;
	// if (island == null || island.length == 0)
	// return 0;
	// int row = island.length, col = island[0].length;
	// int[] bigin = new int[] { 0, 0 };
	// int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }
	// };
	// boolean[][] visited = new boolean[row][col];
	// Queue<int[]> queue = new LinkedList<>();
	// queue.offer(bigin);
	// while (!queue.isEmpty()) {
	// int size = queue.size();
	// while (size > 0) {
	// int[] coor = queue.poll();
	// int x = coor[0], y = coor[1];
	// if (island[x][y] == 'X') {
	// return step;
	// }
	// for (int i = 0; i < directions.length; i++) {
	// int dx = x + directions[i][0];
	// int dy = y + directions[i][1];
	// if (dx >= 0 && dx < row && dy >= 0 && dy < col && !visited[dx][dy]
	// && (island[dx][dy] == 'O' || island[dx][dy] == 'X')) {
	// visited[dx][dy] = true;
	// queue.offer(new int[] { dx, dy });
	// }
	// }
	// size--;
	// }
	// step++;
	// }
	// return -1;
	// }

	// public static int treasureIsland(char[][] islands) {
	// final int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	// if (islands.length == 0 || islands[0].length == 0 || islands[0][0] == 'D')
	// return -1;
	// if (islands[0][0] == 'X')
	// return 0;
	// int R = islands.length, C = islands[0].length;
	// Queue<int[]> queue = new LinkedList<>();
	// int steps = 1;
	// queue.add(new int[] { 0, 0 });
	// islands[0][0] = 'D';
	// while (!queue.isEmpty()) {
	// int size = queue.size();
	// for (int i = 0; i < size; ++i) {
	// int[] pos = queue.poll();
	// for (int[] dir : dirs) {
	// int x = pos[0] + dir[0], y = pos[1] + dir[1];
	// if (x < 0 || x >= R || y < 0 || y >= C || islands[x][y] == 'D')
	// continue;
	// if (islands[x][y] == 'X')
	// return steps;
	// queue.add(new int[] { x, y });
	// islands[x][y] = 'D';
	// }
	// }
	// ++steps;
	// }
	// return -1;
	// }
}
