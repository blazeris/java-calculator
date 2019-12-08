package main;

import java.awt.Button;
import java.awt.Font;
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
	private GridBagLayout gridbag;
	private GridBagConstraints c;
	private List<String> terms;
	private Calculator calc;
	private JTextArea answerText;
	private JTextArea output;
	
	public final static Font CALCULATOR_FONT = new Font("Verdana", Font.PLAIN, 20);
	
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
		output = new JTextArea();
		gridbag.setConstraints(output, c);
		output.setEditable(false);
		output.setFont(CALCULATOR_FONT);
		add(output);
		
		c.gridwidth = 1;
		answerText = new JTextArea();
		gridbag.setConstraints(answerText, c);
		answerText.setEditable(false);
		answerText.append("=");
		answerText.setFont(CALCULATOR_FONT);
		add(answerText);
		
		c.gridy = 1;
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

		if(term.equals("=")) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					calc.update(terms);
					answerText.setText("= " + calc.calculate());
				}
			});
		}
		else if(term.equals("AC")) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					terms.clear();
					calc.update(terms);
					output.setText(calc.toString());
				}
			});
		}
		else if(term.equals("<-")) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(terms.size() > 0) {
						terms.remove(terms.size() - 1);
					}
					calc.update(terms);
					output.setText(calc.toString());
				}
			});
		}
		else {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					terms.add(term);
					calc.update(terms);
					output.setText(calc.toString());
				}
			});
		}
		button.setFont(CALCULATOR_FONT);
		add(button);
	}
}
