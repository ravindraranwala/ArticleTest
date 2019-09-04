package com.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class KruskalMST {

	public static void main(String[] args) {
		// Min Cost to Connect All Nodes (Minimum Spanning Tree I)
		int n = 6;
		int[][] edges = new int[][] { { 1, 4 }, { 4, 5 }, { 2, 3 } };
		int[][] newEdges = { { 1, 2, 5 }, { 1, 3, 10 }, { 1, 6, 2 }, { 5, 6, 5 } };
		int cost = minCostToConnectAllNodes(6, edges, newEdges);

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

	private static int minCostToConnectAllNodes(int n, int[][] edges, int[][] newEdges) {
		int vertices = n;
		Graph graph = new Graph(vertices);
		for (int[] edge : edges)
			graph.addEgde(edge[0], edge[1], 0);
		for (int[] newEdge : newEdges)
			graph.addEgde(newEdge[0], newEdge[1], newEdge[2]);

		return graph.kruskalMST();
	}

	private static int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair) {
		int vertices = n;
		Set<Edge> newEdgesToRepair = new HashSet<>();
		for (int[] newEdge : edgesToRepair)
			newEdgesToRepair.add(new Edge(newEdge[0], newEdge[1], newEdge[2]));
		Set<Edge> allEdges = new HashSet<>();
		for (int[] edge : edges)
			allEdges.add(new Edge(edge[0], edge[1], 0));

		newEdgesToRepair.addAll(allEdges);

		Graph graph = new Graph(vertices);
		for (Edge edge : newEdgesToRepair) 
			graph.addEgde(edge.source, edge.destination, edge.weight);
		

		return graph.kruskalMST();
	}

	static class Edge {
		final int source;
		final int destination;
		final int weight;

		public Edge(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (!(o instanceof Edge))
				return false;
			Edge e = (Edge) o;
			return e.source == source && e.destination == destination;
		}

		@Override
		public int hashCode() {
			int result = Integer.hashCode(source);
			result = 31 * result + Integer.hashCode(destination);
			return result;
		}
	}

	static class Graph {
		private final int vertices;
		private final List<Edge> allEdges = new ArrayList<>();

		Graph(int vertices) {
			this.vertices = vertices;
		}

		public int kruskalMST() {
			PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

			// add all the edges to priority queue, //sort the edges on weights
			for (int i = 0; i < allEdges.size(); i++) {
				pq.add(allEdges.get(i));
			}

			// create a parent []
			int[] parent = new int[vertices + 1];

			// makeset
			makeSet(parent);

			List<Edge> mst = new ArrayList<>();

			int cost = 0;
			// process vertices - 1 edges

			for (int index = 0; index < vertices - 1;) {
				Edge edge = pq.remove();
				// check if adding this edge creates a cycle
				int x_set = find(parent, edge.source);
				int y_set = find(parent, edge.destination);

				if (x_set != y_set) {
					// add it to our final result
					mst.add(edge);
					// update the total cost
					cost += edge.weight;
					union(parent, x_set, y_set);
					index++;
				}
			}

			return cost;
		}

		public void addEgde(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			allEdges.add(edge); // add to total edges
		}

		public void makeSet(int[] parent) {
			// Make set- creating a new element with a parent pointer to itself.
			for (int i = 1; i <= vertices; i++)
				parent[i] = i;

		}

		public int find(int[] parent, int vertex) {
			// chain of parent pointers from x upwards through the tree
			// until an element is reached whose parent is itself
			if (parent[vertex] != vertex)
				return find(parent, parent[vertex]);

			return vertex;
		}

		public void union(int[] parent, int x, int y) {
			int x_set_parent = find(parent, x);
			int y_set_parent = find(parent, y);
			// make x as parent of y
			parent[y_set_parent] = x_set_parent;
		}
	}
}
