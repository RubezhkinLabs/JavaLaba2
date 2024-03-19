package com.example;

public class Node {
	private char info;
	private Node left, right;

	public Node(char info, Node left, Node right) {
		this.info = info;
		this.left = left;
		this.right = right;
	}

	public char getInfo() {
		return info;
	}

	public void setInfo(char info) {
		this.info = info;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
