package com.example;

public class FormulaTree {
	private Node head;
	private static int i;

	public FormulaTree(String str){
		this.head = buildTree(str);
	}

	public Node buildTree(String str){
		char c = str.charAt(i);
		i++;
		Node root = new Node(' ', null, null);
		if(c>= '0' && c <= '9'){
			root.setInfo(c);
		}
		else{
			root.setLeft(buildTree(str));
			root.setInfo(str.charAt(i));
			i++;
			root.setRight(buildTree(str));
			c = str.charAt(i);
			i++;
		}
		return root;
	}
}
