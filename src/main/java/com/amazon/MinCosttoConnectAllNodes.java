package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinCosttoConnectAllNodes {

	public static void main(String[] args) {
		int[][] edges = new int[][] { { 1, 4 }, { 4, 5 }, { 2, 3 } };
		int[][] newEdges = { { 1, 2, 5 }, { 1, 3, 10 }, { 1, 6, 2 }, { 5, 6, 5 } };
		int minCost = mstKruskals(6, edges, newEdges);
		System.out.println(minCost);
	}

	public static int mstKruskals(int n, int[][] edges, int[][] newEdges) {
		int cost = 0;

		List<Set<Integer>> treesInTheForrest = makeSet(n, edges);
		/*
		 * Sort the edges of G.E int nondecreasing order by weight.
		 */
		Arrays.sort(newEdges, (e1, e2) -> Integer.compare(e1[2], e2[2]));

		for (int[] newEdge : newEdges) {
			final int subTreeOne = findSet(newEdge[0], treesInTheForrest);
			final int subTreeTwo = findSet(newEdge[1], treesInTheForrest);
			if (subTreeOne != subTreeTwo) {
				// Add the edge, compute the cost.
				cost += newEdge[2];
				union(treesInTheForrest, subTreeOne, subTreeTwo);
			} // We have completed building the MCST
			if (treesInTheForrest.size() == 1)
				break;
		}

		return cost;
	}

	private static void union(List<Set<Integer>> treesInTheForrest, int subTreeOne, int subTreeTwo) {
		Set<Integer> buildingTree = new HashSet<>();
		// Merge two trees into one using Union operator.
		buildingTree.addAll(treesInTheForrest.get(subTreeOne));
		buildingTree.addAll(treesInTheForrest.get(subTreeTwo));
		treesInTheForrest
				.removeIf(s -> s == treesInTheForrest.get(subTreeOne) || s == treesInTheForrest.get(subTreeTwo));
		treesInTheForrest.add(buildingTree);
	}

	// A utility function to find set of an element i
	// (uses path compression technique)
	private static int findSet(int k, List<Set<Integer>> trees) {
		int setNum = -1;
		for (int i = 0, n = trees.size(); i < n; i++) {
			if (trees.get(i).contains(k))
				setNum = i;
		}
		return setNum;
	}

	private static List<Set<Integer>> makeSet(int n, int[][] edges) {
		Set<Integer> nodesInTheForrest = new HashSet<>();
		Set<Integer> tree = new HashSet<>();
		tree.add(edges[0][0]);
		tree.add(edges[0][1]);
		List<Set<Integer>> treesInTheForrest = new ArrayList<>();
		treesInTheForrest.add(tree);

		for (int i = 1, size = edges.length; i < size; i++) {
			int[] edge = edges[i];
			if (tree.contains(edge[0]))
				tree.add(edge[1]);
			else if (tree.contains(edge[1]))
				tree.add(edge[0]);
			else {
				Set<Integer> newSet = new HashSet<>();
				newSet.add(edge[0]);
				newSet.add(edge[1]);
				treesInTheForrest.add(newSet);
				nodesInTheForrest.addAll(newSet);
			}

		}

		nodesInTheForrest.addAll(tree);

		Set<Integer> lastTree = new HashSet<>();
		for (int i = 1; i < n + 1; i++) {
			if (!nodesInTheForrest.contains(i))
				lastTree.add(i);
		}

		treesInTheForrest.add(lastTree);

		return treesInTheForrest;
	}
}
