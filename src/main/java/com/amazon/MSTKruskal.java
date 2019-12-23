package com.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MSTKruskal {

	public static void main(String[] args) {
		// Min Cost to Connect All Nodes (Minimum Spanning Tree I)
		int n = 6;
		int[][] edges = new int[][] { { 1, 4 }, { 4, 5 }, { 2, 3 } };
		int[][] newEdges = { { 1, 2, 5 }, { 1, 3, 10 }, { 1, 6, 2 }, { 5, 6, 5 } };
		int cost = minCostToConnectAllNodes(n, edges, newEdges);

		System.out.println(String.format("Min Cost to Connect All Nodes: %d", cost));

		// Min Cost to Repair Edges (Minimum Spanning Tree II)
		// Usecase One
		n = 5;
		edges = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 1, 5 } };
		int[][] edgesToRepair = { { 1, 2, 12 }, { 3, 4, 30 }, { 1, 5, 8 } };
		cost = minCostToRepairEdges(n, edges, edgesToRepair);
		System.out.println(String.format("Min Cost to Repair Edges: %d", cost));

		n = 6;
		edges = new int[][] { { 1, 2 }, { 2, 3 }, { 4, 5 }, { 3, 5 }, { 1, 6 }, { 2, 4 } };
		edgesToRepair = new int[][] { { 1, 6, 410 }, { 2, 4, 800 } };
		cost = minCostToRepairEdges(n, edges, edgesToRepair);
		System.out.println(String.format("Min Cost to Repair Edges: %d", cost));

		n = 6;
		edges = new int[][] { { 1, 2 }, { 2, 3 }, { 4, 5 }, { 5, 6 }, { 1, 5 }, { 2, 4 }, { 3, 4 } };
		edgesToRepair = new int[][] { { 1, 5, 110 }, { 2, 4, 84 }, { 3, 4, 79 } };
		cost = minCostToRepairEdges(n, edges, edgesToRepair);
		System.out.println(String.format("Min Cost to Repair Edges: %d", cost));
	}

	private static int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair) {
		Map<List<Integer>, Integer> edgeToWeightMap = new HashMap<>();
		for (int[] edge : edges)
			edgeToWeightMap.put(Arrays.asList(edge[0], edge[1]), 0);

		for (int[] edgeToRepair : edgesToRepair)
			edgeToWeightMap.put(Arrays.asList(edgeToRepair[0], edgeToRepair[1]), edgeToRepair[2]);

		final int[][] weightedEdges = new int[edges.length][3];
		int counter = 0;
		for (Entry<List<Integer>, Integer> edgeToWeight : edgeToWeightMap.entrySet())
			weightedEdges[counter++] = new int[] { edgeToWeight.getKey().get(0), edgeToWeight.getKey().get(1),
					edgeToWeight.getValue() };

		return mstKruskal(n, weightedEdges);
	}

	private static int minCostToConnectAllNodes(int n, int[][] edges, int[][] newEdges) {
		final int newEdgesLen = newEdges.length;
		int edgesLen = edges.length;
		int[][] weightedEdges = new int[edgesLen + newEdgesLen][3];
		System.arraycopy(newEdges, 0, weightedEdges, 0, newEdgesLen);
		for (int i = 0; i < edgesLen; i++)
			weightedEdges[i + newEdgesLen] = new int[] { edges[i][0], edges[i][1], 0 };

		return mstKruskal(n, weightedEdges);
	}

	private static int mstKruskal(int n, int[][] edges) {
		Node[] vertices = new Node[n + 1];

		for (int i = 1; i <= n; i++) {
			vertices[i] = new Node();
			makeSet(vertices[i]);
		}

		// Sort the edges of G.E into nondecreasing order by weight w.
		Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

		int cost = 0;
		for (int[] edge : edges) {
			Node u = vertices[edge[0]];
			Node v = vertices[edge[1]];
			if (findSet(u) != findSet(v)) {
				cost += edge[2]; // A safe, light edge is found. This becomes a part of our MST.
				union(u, v);
			}
		}
		return cost;
	}

	// Disjoint set operations are implemented here.
	private static void makeSet(Node x) {
		x.p = x;
		x.rank = 0;
	}

	private static void union(Node x, Node y) {
		link(findSet(x), findSet(y));
	}

	private static void link(Node x, Node y) {
		// union by rank heuristic.
		if (x.rank > y.rank)
			y.p = x;
		else {
			x.p = y;
			if (x.rank == y.rank)
				y.rank += 1;
		}
	}

	private static Node findSet(Node x) {
		if (x != x.p)
			x.p = findSet(x.p);// Path compression.

		return x.p;
	}

	static class Node {
		private int rank;
		private Node p;
	}
}
