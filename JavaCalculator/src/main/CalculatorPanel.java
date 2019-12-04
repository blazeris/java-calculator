package main;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculatorPanel extends JPanel{
	GridBagLayout gridbag;
	GridBagConstraints c;
	List<String> terms;
	Calculator calc;
	
	public CalculatorPanel() {
		super();
		terms = new LinkedList<String>();
		calc = new Calculator(terms);
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gridbag);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		JTextArea output = new JTextArea();
		gridbag.setConstraints(output, c);
		add(output);
		
		c.gridwidth = 1;
		makeButton("(");
		makeButton(")");
		makeButton("<-");
		makeButton("AC");
		makeButton("7");
		makeButton("8");
		makeButton("9");
		makeButton("/");
		makeButton("4");
		makeButton("5");
		makeButton("6");
		makeButton("*");
		makeButton("1");
		makeButton("2");
		makeButton("3");
		makeButton("-");
		makeButton("0");
		makeButton(".");
		makeButton("=");
		makeButton("+");
		}
	
	
	
	
	public void makeButton(String term) {
		Button button = new Button(term);
		gridbag.setConstraints(button, c);
		add(button);
	}
}
