/**
 * 
 */
package com.rolandoislas.math.util;

import javax.swing.JTextField;

/**
 * @author Rolando Islas
 *
 */
public class Field {

	/**
	 * @param field
	 * @return true if filed value is empty
	 */
	public static boolean isFieldNull(JTextField field) {
		return field.getText().equals("");
	}

	/**
	 * @param field
	 * @return
	 */
	public static double getDouble(JTextField field) {
		double value;
		try {
			value = Double.parseDouble(field.getText());
		} catch (NumberFormatException e) {
			value = 0;
		}
		return value;
	}
	
	public static double gd(JTextField field) {
		return getDouble(field);
	}

}
