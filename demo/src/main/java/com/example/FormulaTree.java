package com.example;
/**
 * FormulaTree - дерево - формула
 * @param head - начало дерева
 * @param i - переменная для проверки правильности составления примера
 */
public class FormulaTree {
	private Node head;
	private static int i = 0;
	/**
	 * конструктор
	 * @param str строка с примером
	 * @throws Exception при неправильном составлении примера выходит ошибка
	 */
	public FormulaTree(String str) throws Exception{
		this.head = buildTree(str);
		if(i != str.length()){
			throw new Exception("Неправильно составлен пример!");
		}
	}
	//рекурсивное постоение дерева
	/**
	 * @param str строка с примером
	 * @return построенное дерево
	 */
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

	/**
	 * метод решения примера, которая доступна пользователю
	 * @return результат
	 * @throws Exception при делении на ноль вызывается исключение
	 */
	public int calculate() throws Exception{
		return calculate_rec(head);
	}

	/**
	 * рекурсивное решение примера приватное
	 * @param tree дерево для подсчета
	 * @return результат подсчета
	 * @throws Exception при делении на ноль вызывается исключение
	 */
	private int calculate_rec(Node tree) throws Exception{
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
