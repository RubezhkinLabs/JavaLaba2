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

	public int calculate(){
		return calculate_rec(head);
	}

	private int calculate_rec(Node tree){
		int result=0;
		if(tree.getLeft() == null && tree.getRight()==null){
			result = tree.getInfo() - '0';
		}
		else{
			int left = calculate_rec(tree.getLeft());
			int right = calculate_rec(tree.getRight());
			switch (tree.getInfo()) {
				case '+':
					result = left+right;
					break;
				case '-':
					result = left-right;
					break;
				case '*':
					result = left*right;
					break;
				case '/':
					result = left/right;
				default:
					break;
			}
		}
		return result;
	}
}
