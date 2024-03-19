package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception { // ((1*2)+((2+3)/1)) пример примера (простите за тафтологию)
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        FormulaTree tree = new FormulaTree(str);
        System.out.println(tree.calculate());
    }
}