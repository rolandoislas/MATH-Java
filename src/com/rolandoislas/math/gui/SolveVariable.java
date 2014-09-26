/**
 * 
 */
package com.rolandoislas.math.gui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FontMetrics;

import com.rolandoislas.math.gui.asset.button.ButtomHome;
import com.rolandoislas.math.util.Field;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

/**
 * @author Rolando Islas
 *
 */
public class SolveVariable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, JLabel> mapLabel = new HashMap<Integer, JLabel>();
	private Map<Integer, JTextField> mapTextField = new HashMap<Integer, JTextField>();
	private SpringLayout springLayout;

	/**
	 * @param displayName 
	 * @param inputFields
	 * @param outputFields
	 */
	public SolveVariable(String title, int inputFields, int outputFields) {
		creatComponents(title, inputFields, outputFields);
	}

	/**
	 * @param inputFields
	 * @param outputFields
	 */
	private void creatComponents(String title, int inputFields, int outputFields) {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		ButtomHome homeButton = new ButtomHome();
		add(homeButton);
		
		// Title
		JLabel lblTitle = new JLabel(title);
		springLayout.putConstraint(SpringLayout.WEST, lblTitle, 401, SpringLayout.EAST, homeButton);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblTitle);
		
		// Add input components to map relative to initial
		for(int i = 0; i < inputFields; i++) {
			JLabel label = new JLabel();
			JTextField textField = new JTextField();
			
			int leftMargin = 0;
			int topMargin = 0;
			if(i != 0 && i % 5 == 0) {
				leftMargin = 200;
				topMargin = 125;
			}
			if(i == 0) {
				springLayout.putConstraint(SpringLayout.NORTH, textField, 58, SpringLayout.NORTH, this);
				springLayout.putConstraint(SpringLayout.WEST, textField, 160, SpringLayout.WEST, this);
				springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, textField);
				springLayout.putConstraint(SpringLayout.EAST, label, -6, SpringLayout.WEST, textField);
			} else {
				springLayout.putConstraint(SpringLayout.NORTH, textField, 25 - topMargin, SpringLayout.NORTH, mapTextField.get(i - 1));
				springLayout.putConstraint(SpringLayout.WEST, textField, 0 + leftMargin, SpringLayout.WEST, mapTextField.get(i - 1));
				springLayout.putConstraint(SpringLayout.NORTH, label, 25 - topMargin, SpringLayout.NORTH, mapLabel.get(i - 1));
				springLayout.putConstraint(SpringLayout.WEST, label, 0 + leftMargin, SpringLayout.WEST, mapLabel.get(i - 1));
			}
			
			textField.setColumns(10);
			
			mapLabel.put(i, label);
			mapTextField.put(i, textField);
		}
		
		// Add remaining components to map relative to previous
		for(int i = inputFields; i < inputFields + outputFields; i++) {
			JLabel label = new JLabel();
			JTextField textField = new JTextField();
			
			int firstItemMargin = 0;
			int firstFieldMargin = 0;
			if(i == inputFields) {
				firstItemMargin = 25;
				firstFieldMargin = 0;
			}
			springLayout.putConstraint(SpringLayout.NORTH, textField, 25 + firstItemMargin + firstFieldMargin, SpringLayout.NORTH, mapTextField.get(i - 1));
			springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, mapTextField.get(i - 1));
			springLayout.putConstraint(SpringLayout.NORTH, label, 25 + firstItemMargin, SpringLayout.NORTH, mapLabel.get(i - 1));
			springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, mapLabel.get(i - 1));
			
			textField.setColumns(200);
			textField.setBackground(new Color(238, 238, 238));
			textField.setEditable(false);
			textField.setBorder(null);
			textField.setText("null");
			
			mapLabel.put(i, label);
			mapTextField.put(i, textField);
		}
		
		// Add input components to frame
		for(int i = 0; i < inputFields; i++) {
			add(mapLabel.get(i));
			add(mapTextField.get(i));
		}
		
		// Add output components to frame
		for(int i = inputFields; i < inputFields + outputFields; i++) {
			add(mapLabel.get(i));
			add(mapTextField.get(i));
		}
	}

	/**
	 * @param key
	 * @param name
	 */
	public void setLabelText(int key, String name) {
		String text = name + ":";
		Font font = new Font("Tahoma", Font.PLAIN, 11);
		FontMetrics fontMetrics = this.getFontMetrics(font);
		int offset = -fontMetrics.stringWidth(text) - 20;
		springLayout.putConstraint(SpringLayout.WEST, mapLabel.get(key), offset, SpringLayout.WEST, mapTextField.get(key));
		mapLabel.get(key).setText(text);
	}

	/**
	 * @param i
	 * @return
	 */
	public double getFieldText(int key) {
		return Field.gd(mapTextField.get(key));
	}

	/**
	 * @param i
	 * @return
	 */
	public boolean isFieldNull(int key) {
		return Field.isFieldNull(mapTextField.get(key));
	}

	/**
	 * @param text
	 */
	public void setFieldText(int key, String text) {
		mapTextField.get(key).setText(text);
	}

	/**
	 * @param key
	 * @param principal
	 */
	public void setFieldText(int key, double number) {
		setFieldText(key, number + "");
	}

	/**
	 * @param i
	 * @return
	 */
	public boolean isFieldPopulated(int key) {
		return !isFieldNull(key);
	}

	/**
	 * @param key
	 * @return JTextField
	 */
	public JTextField getField(int key) {
		return mapTextField.get(key);
	}
}
