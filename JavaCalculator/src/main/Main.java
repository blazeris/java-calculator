package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 600);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		window.add(panel);
		window.setVisible(true);
		
		
		
		JTextField display = new JTextField();
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