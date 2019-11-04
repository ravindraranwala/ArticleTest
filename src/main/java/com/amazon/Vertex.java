package com.amazon;

import java.util.StringJoiner;

public final class Vertex {
	private final int row;
	private final int col;
	private final int d;
	private final Vertex parent;

	public Vertex(int row, int col, int d, Vertex parent) {
		super();
		this.row = row;
		this.col = col;
		this.d = d;
		this.parent = parent;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getD() {
		return d;
	}

	public Vertex getParent() {
		return parent;
	}

	public String printBlock() {
		return new StringJoiner(", ", "(", ")").add(String.valueOf(row)).add(String.valueOf(col)).toString();
	}
}
