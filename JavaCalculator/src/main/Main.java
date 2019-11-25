package main;

import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> terms = new LinkedList<String>();
		terms.add("5");
		terms.add("*");
		terms.add("(");
		terms.add("5");
		terms.add("+");
		terms.add("7");
		terms.add(")");
		Calculator calc = new Calculator(terms);
		System.out.println(calc.calculate());
	}

}
