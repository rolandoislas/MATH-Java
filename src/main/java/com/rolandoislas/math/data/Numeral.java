/**
 * 
 */
package com.rolandoislas.math.data;

/**
 * @author Rolando Islas
 *
 */
public class Numeral {
	
	public static char subn = (char) Integer.parseInt("006E", 16);
	
	public static char sub(int number) {
		String unicode = Integer.toString(2080 + number);
		return (char) Integer.parseInt(unicode, 16);
	}
	
}
