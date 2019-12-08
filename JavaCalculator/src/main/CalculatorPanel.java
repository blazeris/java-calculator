package main;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The panel that is using calculator
 * @author Nathan Lai nlai274@gmail.com
 *
 */
public class CalculatorPanel extends JPanel{
	GridBagLayout gridbag;
	GridBagConstraints c;
	List<String> terms;
	Calculator calc;
	JTextArea answerText;
	
	/**
	 * Initializes a new panel that works as a calculator
	 */
	public CalculatorPanel() {
		super();
		terms = new LinkedList<String>();
		calc = new Calculator(terms);
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		
		setLayout(gridbag);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.ipady = 90;
		
		c.gridwidth = GridBagConstraints.REMAINDER - 1;
		c.gridy = 0;
		JTextArea output = new JTextArea();
		gridbag.setConstraints(output, c);
		output.setEditable(false);
		add(output);
		
		c.gridwidth = 1;
		answerText = new JTextArea();
		gridbag.setConstraints(answerText, c);
		answerText.setEditable(false);
		answerText.append("=");
		add(answerText);
		
		makeButton("(");
		makeButton(")");
		makeButton("<-");
		makeButton("AC");
		c.gridy = 2;
		makeButton("7");
		makeButton("8");
		makeButton("9");
		makeButton("/");
		c.gridy = 3;
		makeButton("4");
		makeButton("5");
		makeButton("6");
		makeButton("*");
		c.gridy = 4;
		makeButton("1");
		makeButton("2");
		makeButton("3");
		makeButton("-");
		c.gridy = 5;
		makeButton("0");
		makeButton(".");
		makeButton("=");
		makeButton("+");
		}
	
	
	
	/**
	 * Makes a button based on a selected term
	 * @param term
	 */
	public void makeButton(String term) {
		Button button = new Button(term);
		gridbag.setConstraints(button, c);
		final String numbers = "0123456789.";
		if(!term.equals("=")) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					terms.add(term);
				}
			});
		}
		else {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					terms.add(term);
				}
			});
		}
		add(button);
	}
}
