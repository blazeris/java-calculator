package main;

import java.util.LinkedList;
import java.util.List;

/**
 * A class to perform calculations
 * @author Nathan Lai nlai274@gmail.com
 *
 */
public class Calculator {
	List<String> terms;
	
	public Calculator(List<String> terms) {
		this.terms = new LinkedList<String>();
		update(terms);
	}
	
	public Calculator() {
		this(new LinkedList<String>());
	}
	
	/**
	 * Checks if a string is part of a number
	 * @param target the string checked
	 * @return true if is part of number, false if not part of a number
	 */
	public boolean isNumeric(String target) {
		if(target.equals(".")) {
			return true;
		}
		try {
			Double.parseDouble(target);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Updates the terms list
	 * @param inp the new list
	 */
	public void update(List<String> inp) {
		terms.clear();
		terms.addAll(inp);
		
		for(int i = 1; i < terms.size();) {
			if(isNumeric(terms.get(i)) && isNumeric(terms.get(i - 1))) {
				terms.set(i - 1, terms.get(i - 1) + terms.get(i));
				terms.remove(i);
			}
			else {
				i++;
			}
		}
	}
	
	/**
	 * Index of method, but going from right to left
	 * @param <E> is the element type
	 * @param list is list we're looking in
	 * @param target is the target value
	 * @return the index from right to left
	 */
	public <E> int reverseIndexOf(List<E> list, E target) {
		for(int i = list.size() - 1, k = 0; i >= 0; i--, k++) {
			if(list.get(i).equals(target)) {
				return k;
			}
		}
		return -1;
	}
	
	/**
	 * Basic multiplication
	 * @param firstTerm
	 * @param secondTerm
	 * @return the product
	 */
	private String multiply(String firstTerm, String secondTerm) {
		return Double.toString(Double.parseDouble(firstTerm) * Double.parseDouble(secondTerm));
	}
	
	/**
	 * Basic addition
	 * @param firstTerm
	 * @param secondTerm
	 * @return the sum
	 */
	private String add(String firstTerm, String secondTerm) {
		return Double.toString(Double.parseDouble(firstTerm) + Double.parseDouble(secondTerm));
	}
	
	/**
	 * @return the String form of terms list
	 */
	public String toString() {
		StringBuffer output = new StringBuffer();
		for(String term: terms) {
			output = output.append(term).append(" ");
		}
		return output.toString().trim();
	}
	
	/**
	 * The calculator method
	 * @return the outcome of the math in terms
	 */
	public double calculate() {
		return calculate(terms);
	}

	/**
	 * Recursive helper method for calculating from terms
	 * @param terms the list storing operators and operands
	 * @return the outcome of the math in the terms list
	 */
	private double calculate(List<String> terms) {
		if (!terms.isEmpty()) {
			int open = terms.indexOf("(");
			if (open != -1) {
				int closed = terms.indexOf(")");
				if (closed == -1) {
					closed = terms.size() - 1;
				} else {
					terms.remove(closed);
				}
				List<String> termsSublist = new LinkedList<>();
				for (int i = open + 1; i < closed; i++) {
					termsSublist.add(terms.get(open + 1));
					terms.remove(open + 1);
				}
				terms.set(open, Double.toString(calculate(termsSublist)));

			}

			int multiplyIndex = terms.indexOf("*");
			int divideIndex = terms.indexOf("/");
			while (multiplyIndex != -1 || divideIndex != -1) {
				if (divideIndex != -1) {
					terms.set(divideIndex, "*");
					terms.set(divideIndex + 1, Double.toString(1 / Double.parseDouble(terms.get(divideIndex + 1))));
				} else if (multiplyIndex != -1) {
					terms.set(multiplyIndex, multiply(terms.get(multiplyIndex - 1), terms.get(multiplyIndex + 1)));
					terms.remove(multiplyIndex + 1);
					terms.remove(multiplyIndex - 1);
				}
				multiplyIndex = terms.indexOf("*");
				divideIndex = terms.indexOf("/");
			}

			int addIndex = terms.indexOf("+");
			int subtractIndex = terms.indexOf("-");
			while (addIndex != -1 || subtractIndex != -1) {
				if (subtractIndex != -1) {
					terms.set(subtractIndex, "+");
					terms.set(subtractIndex + 1, Double.toString(-Double.parseDouble(terms.get(subtractIndex + 1))));
				} else if (addIndex != -1) {
					terms.set(addIndex, add(terms.get(addIndex - 1), terms.get(addIndex + 1)));
					terms.remove(addIndex + 1);
					terms.remove(addIndex - 1);
				}
				addIndex = terms.indexOf("+");
				subtractIndex = terms.indexOf("-");
			}
			return Double.parseDouble(terms.get(0));
		}
		return 0;
	}
	
	public void termsAdd(String term) {
		terms.add(term);
	}
}
