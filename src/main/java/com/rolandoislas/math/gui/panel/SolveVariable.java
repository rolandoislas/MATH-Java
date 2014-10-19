/**
 * Creates a panel designed to allow for input output of basic algebraic equations.
 */
package com.rolandoislas.math.gui.panel;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FontMetrics;

import com.rolandoislas.math.gui.button.ButtonHome;
import com.rolandoislas.math.util.Field;
import com.rolandoislas.math.util.OS;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

/**
 * @author Rolando Islas
 *
 */
public class SolveVariable extends JPanel {

	private static final long serialVersionUID = 1L;
	private Map<Integer, JLabel> mapLabel = new HashMap<Integer, JLabel>();
	private Map<Integer, JTextField> mapTextField = new HashMap<Integer, JTextField>();
	private SpringLayout springLayout;
	private int inputFields;
	private int outputFields;

	/**
	 * @param title title shown at top of panel
	 * @param inputFields number of input fields to generate
	 * @param outputFields number rof output fields to generate
	 */
	public SolveVariable(String title, int inputFields, int outputFields) {
		this.inputFields = inputFields;
		this.outputFields = outputFields;
		createComponents(title, inputFields, outputFields);
	}

	/**
     * Creates the input an output fields.
     * <p>
     * Fields are saved to maps.
     *
     * @param title title to give label
	 * @param inputFields number of input fields to generate
	 * @param outputFields number of output fields to generate
	 */
	private void createComponents(String title, int inputFields, int outputFields) {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		ButtonHome homeButton = new ButtonHome();
		add(homeButton);
		
		// Title
		JLabel lblTitle = new JLabel(title);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblTitle);
		
		new OS();
		
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
				springLayout.putConstraint(SpringLayout.NORTH, label, OS.isMac() ? 5 : 0, SpringLayout.NORTH, textField);
				springLayout.putConstraint(SpringLayout.EAST, label, -6, SpringLayout.WEST, textField);
			} else {
				springLayout.putConstraint(SpringLayout.NORTH, textField, 25 - topMargin, SpringLayout.NORTH, mapTextField.get(i - 1));
				springLayout.putConstraint(SpringLayout.WEST, textField, leftMargin, SpringLayout.WEST, mapTextField.get(i - 1));
				springLayout.putConstraint(SpringLayout.NORTH, label, 25 - topMargin, SpringLayout.NORTH, mapLabel.get(i - 1));
				springLayout.putConstraint(SpringLayout.WEST, label, leftMargin, SpringLayout.WEST, mapLabel.get(i - 1));
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
				firstFieldMargin = OS.isMac() ? 5 : 0;
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
     * Sets the text of a label.
     *
	 * @param key map index
	 * @param name text to apply to label
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
     * Returns the value of a field as a double.
     *
	 * @param key map index
	 * @return value of the field as a double
	 */
	public double getFieldText(int key) {
		return Field.gd(mapTextField.get(key));
	}

	/**
     * Return true if field is null.
     *
	 * @param key map index
	 * @return true if field is null
	 */
	public boolean isFieldNull(int key) {
		return Field.isFieldNull(mapTextField.get(key));
	}

	/**
     * Sets the text of a field.
     *
	 * @param key map index
     * @param text text to set for field
	 */
	public void setFieldText(int key, String text) {
		mapTextField.get(key).setText(text);
	}

	/**
     * Convince method. Sets field text from a double value.
     *
	 * @param key map index
	 * @param number value to set field text
	 */
	public void setFieldText(int key, double number) {
		setFieldText(key, number + "");
	}

	/**
     * Checks if field is populated.
     *
	 * @param key map index
	 * @return true if field is not null
	 */
	public boolean isFieldPopulated(int key) {
		return !isFieldNull(key);
	}

	/**
     * Returns the field from a map.
     *
	 * @param key map index
	 * @return JTextField
	 */
	public JTextField getField(int key) {
		return mapTextField.get(key);
	}

	/**
     * Checks if the given fields are populated.
     *
	 * @param fields integer array of fields to be checks
	 * @return true if all fields provided are populated
	 */
	public boolean hasFieldsPopulated(int[] fields) {
		for(int i = 0; i < fields.length; i++) {
			if(isFieldNull(fields[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sets all field values to "null"
	 */
	public void setFieldsNull() {
		for(int i = inputFields; i < inputFields + outputFields; i++) {
			setFieldText(i, "null");
		}
	}
}
