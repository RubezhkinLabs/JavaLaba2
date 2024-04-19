package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * FormulaTree - дерево - формула
 * @param head - начало дерева
 * @param i - переменная для проверки правильности составления примера
 * @param params - map с переменными и их значениями
 */
public class FormulaTree {
	private Node head;
	private static int i = 0;
	private Map<String, Integer> params = new HashMap<>();
	/**
	 * конструктор
	 * @param str строка с примером
	 * @throws Exception при неправильном составлении примера выходит ошибка
	 */
	public FormulaTree(String[] str) throws Exception{
		this.head = buildTree(str);
		if(i != str.length){
			throw new Exception("Неправильно составлен пример!");
		}
	}
	//рекурсивное постоение дерева
	/**
	 * @param str строка с примером
	 * @return построенное дерево
	 */
	public Node buildTree(String[] strArr){
		String str = strArr[i];
		i++;
		Node root = new Node(" ", null, null);
		if(str.matches("-?\\d+(\\.\\d+)?")){
			root.setInfo(str);
		}
		else if(Pattern.compile("[a-zA-Z]+").matcher(str).matches()){
			root.setInfo(str);
			if(!params.containsKey(str))
				params.put(str, 0);
		}
		else{
			root.setLeft(buildTree(strArr));
			root.setInfo(strArr[i]);
			i++;
			root.setRight(buildTree(strArr));
			i++;
		}
		return root;
	}

	public void setParams(Scanner in){
		int num;
		for(Map.Entry<String, Integer> entry: params.entrySet()){
			System.out.println("Введите значение для " + entry.getKey());
			num = in.nextInt();
			entry.setValue(num);
		}
	}

	/**
	 * метод решения примера, которая доступна пользователю
	 * @return результат
	 * @throws Exception при делении на ноль вызывается исключение
	 */
	public double calculate() throws Exception{
		return calculate_rec(head);
	}

	/**
	 * рекурсивное решение примера приватное
	 * @param tree дерево для подсчета
	 * @return результат подсчета
	 * @throws Exception при делении на ноль вызывается исключение
	 */
	private double calculate_rec(Node tree) throws Exception{
		double result=0;
		if(tree.getLeft() == null && tree.getRight()==null){
			if(tree.getInfo().matches("-?\\d+(\\.\\d+)?"))
				result = Double.parseDouble(tree.getInfo());
			else
				result = params.get(tree.getInfo());
		}
		else{
			double left = calculate_rec(tree.getLeft());
			double right = calculate_rec(tree.getRight());
			switch (tree.getInfo()) {
				case "+":
					result = left+right;
					break;
				case "-":
					result = left-right;
					break;
				case "*":
					result = left*right;
					break;
				case "^":
					result = Math.pow(left, right);
					break;
				case "/":
					if(right == 0)
						throw new Exception("Деление на 0");
					else
						result = left/right;
				default:
					break;
			}
		}
		return result;
	}
}
