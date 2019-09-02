package com.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubtreeWithMaximumAverage {
	static double max = 0;
	static Node maxNode = null;

	public static void main(String[] args) {
		// Creating the tree.
		Node leafOne = new Node(1, Collections.emptyList());
		Node leafTwo = new Node(2, Collections.emptyList());
		Node leafThree = new Node(4, Collections.emptyList());
		Node leafFour = new Node(-2, Collections.emptyList());

		Node intermediateOne = new Node(-5, Arrays.asList(leafOne, leafTwo));
		Node intermediateTwo = new Node(13, Arrays.asList(leafThree, leafFour));
		Node intermediateThree = new Node(4, Collections.emptyList());

		Node rootNode = new Node(1, Arrays.asList(intermediateOne, intermediateTwo, intermediateThree));

		// Node firstLeaf = new Node(-5, Collections.emptyList());
		// Node secondLeaf = new Node(21, Collections.emptyList());
		// Node thirdLeaf = new Node(5, Collections.emptyList());
		// Node fourthNode = new Node(-1, Collections.emptyList());
		//
		// Node rootNode = new Node(1, Arrays.asList(firstLeaf, secondLeaf, thirdLeaf,
		// fourthNode));

		Node maxNode = computeMaxAvgSubtree(rootNode);

		System.out.println(maxNode.val);
	}

	public static Node computeMaxAvgSubtree(Node root) {
		computeMaxAvg(root);
		return maxNode;
	}

	private static int[] computeMaxAvg(Node root) {
		if (root == null)
			throw new IllegalArgumentException("Root does not exist.");
		int val = root.val, count = 1;
		for (Node child : root.children) {
			// DFS
			int[] childResult = computeMaxAvg(child);
			val += childResult[0];
			count += childResult[1];
		}

		final double avg = val / (count + 0.0);
		if (avg > max) {
			max = avg;
			maxNode = root;
		}

		return new int[] { val, count };
	}

	public static class Node {
		final int val;
		final List<Node> children;

		public Node(int val, List<Node> children) {
			super();
			this.val = val;
			this.children = children;
		}

	}
}
