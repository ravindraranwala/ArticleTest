package com.amazon;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MinCostToRepairEdges {

	public static void main(String[] args) {
		int n = 6;
		// int[][] edges = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 1, 5
		// } };
		// int[][] edgesToRepair = { { 1, 2, 12 }, { 3, 4, 30 }, { 1, 5, 8 } };
		int[][] edges = new int[][] { { 1, 2 }, { 2, 3 }, { 4, 5 }, { 3, 5 }, { 1, 6 }, { 2, 4 } };
		int[][] edgesToRepair = { { 1, 6, 410 }, { 2, 4, 800 } };
		int minCostToRepair = mstRepairEdgesKruskals(n, edges, edgesToRepair);
		System.out.println(minCostToRepair);
	}

	public static int mstRepairEdgesKruskals(int n, int[][] edges, int[][] edgesToRepair) {
		final Set<Edge> allEdges = Arrays.stream(edges).map(e -> new Edge(e[0], e[1])).collect(Collectors.toSet());
		final Set<Edge> brokenEdges = Arrays.stream(edgesToRepair).map(e -> new Edge(e[0], e[1]))
				.collect(Collectors.toSet());
		int[][] remainingEdges = allEdges.stream().filter(e -> !brokenEdges.contains(e))
				.map(e -> new int[] { e.u, e.v }).toArray(int[][]::new);
		return MinCosttoConnectAllNodes.mstKruskals(n, remainingEdges, edgesToRepair);
	}

	private static final class Edge {
		final int u;
		final int v;

		public Edge(int u, int v) {
			super();
			this.u = u;
			this.v = v;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (!(o instanceof Edge))
				return false;
			Edge e = (Edge) o;
			return e.u == u && e.v == v;
		}

		@Override
		public int hashCode() {
			int result = Integer.hashCode(u);
			result = 31 * result + Integer.hashCode(v);
			return result;
		}
	}
}
