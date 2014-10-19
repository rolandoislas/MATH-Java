/**
 * 
 */
package com.rolandoislas.math.gui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.rolandoislas.math.util.state.StateBasedApplication;

/**
 * @author Rolando Islas
 *
 */
public class ButtonHome extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static StateBasedApplication sba;
	
	public ButtonHome() {
		this.setText("Home");
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sba.setState(0);
			}
		});
	}

	/**
	 * @param sba 
	 * 
	 */
	public static void setSba(StateBasedApplication sba) {
		ButtonHome.sba = sba;
	}

}
