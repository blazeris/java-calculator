package main;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
	List<String> terms;
	
	public Calculator(List<String> terms) {
		this.terms = terms;
	}
	
	public <E> int reverseIndexOf(List<E> list, E target) {
		for(int i = list.size() - 1, k = 0; i >= 0; i--, k++) {
			if(list.get(i).equals(target)) {
				return k;
			}
		}
		return -1;
	}
	
	private String multiply(String firstTerm, String secondTerm) {
		return Double.toString(Double.parseDouble(firstTerm) * Double.parseDouble(secondTerm));
	}
	
	private String add(String firstTerm, String secondTerm) {
		return Double.toString(Double.parseDouble(firstTerm) + Double.parseDouble(secondTerm));
	}
	
	public double calculate() {
		return calculate(terms);
	}
	
	private double calculate(List<String> terms) {
		int open = terms.indexOf("(");
		
		if(open != -1) {
			int closed = terms.indexOf(")");
			if(closed == -1) {
				closed = terms.size() - 1;
			}
			else {
				terms.remove(closed);
			}
			List<String> termsSublist = new LinkedList<>();
			for(int i = open + 1; i < closed; i++) {
				termsSublist.add(terms.get(open + 1));
				terms.remove(open + 1);
			}
			terms.set(open, Double.toString(calculate(termsSublist)));
			
		}
		
		int multiplyIndex = terms.indexOf("*");
		int divideIndex = terms.indexOf("/");
		while(multiplyIndex != -1 || divideIndex != -1) {
			if(divideIndex != - 1) {
				terms.set(divideIndex, "*");
				terms.set(divideIndex + 1, Double.toString(1 / Double.parseDouble(terms.get(divideIndex + 1))));
			}
			else if(multiplyIndex != -1) {
				terms.set(multiplyIndex, multiply(terms.get(multiplyIndex - 1), terms.get(multiplyIndex + 1)));
				terms.remove(multiplyIndex + 1);
				terms.remove(multiplyIndex - 1);
			}
			multiplyIndex = terms.indexOf("*");
			divideIndex = terms.indexOf("/");
		}
		
		int addIndex = terms.indexOf("+");
		int subtractIndex = terms.indexOf("-");
		while(addIndex != -1 || subtractIndex != -1) {
			if(subtractIndex != - 1) {
				terms.set(subtractIndex, "+");
				terms.set(subtractIndex + 1, Double.toString(- Double.parseDouble(terms.get(subtractIndex + 1))));
			}
			else if(addIndex != -1) {
				terms.set(addIndex, add(terms.get(addIndex - 1), terms.get(addIndex + 1)));
				terms.remove(addIndex + 1);
				terms.remove(addIndex - 1);
			}
			addIndex = terms.indexOf("+");
			subtractIndex = terms.indexOf("-");
		}
		return Double.parseDouble(terms.get(0));
	}
}
