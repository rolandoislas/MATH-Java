/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import java.math.BigDecimal;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Rolando Islas
 *
 */
public class Logarithm implements ApplicationState {

	/**
	 * 
	 */
	private static final int ID = 3;
	private static final String DISPLAY_NAME = "Logarithms";
	private static final int INPUT_FIELDS = 3;
	private static final int OUTPUT_FIELDS = 3;
	private SolveVariable panel;

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#getID()
	 */
	@Override
	public int getID() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#initialize(com.rolandoislas.math.util.state.StateBasedApplication, javax.swing.JFrame)
	 */
	@Override
	public Container initialize(StateBasedApplication sba, JFrame frame) {
		panel = new SolveVariable(DISPLAY_NAME, INPUT_FIELDS, OUTPUT_FIELDS);
		setFieldNames();
		setFieldEvents();
		return panel;
	}

	/**
	 * 
	 */
	private void setFieldEvents() {
		for(int i = 0; i < INPUT_FIELDS; i++) {
			panel.getField(i).addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					doFieldComputations();
				}
			});
		}
	}

	/**
	 * 
	 */
	private void doFieldComputations() {
		calculateExponentlessSide();
		calculateBase();
		calculateExponent();
	}

	/**
	 * 
	 */
	private void calculateExponent() {
		if(panel.hasFieldsPopulated(new int[] {0, 1})) {
			double exponent = Math.log10(panel.getFieldText(0) / Math.log10(panel.getFieldText(1)));
			panel.setFieldText(5, (float)exponent);
		}
	}

	/**
	 * 
	 */
	private void calculateBase() {
		if(panel.hasFieldsPopulated(new int[] {0, 2})) {
			double base;
			if(panel.getFieldText(2) == 3) {
				base = Math.cbrt(panel.getFieldText(0));
			} else {
				BigDecimal one = new BigDecimal(1);
				BigDecimal exponent = new BigDecimal(panel.getFieldText(2));
				base = Math.pow(panel.getFieldText(0), one.divide(exponent, 11, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			panel.setFieldText(4, (float)base);
		}
	}

	/**
	 * 
	 */
	private void calculateExponentlessSide() {
		if(panel.hasFieldsPopulated(new int[] {1, 2})) {
			double exponentlessSide = Math.pow(panel.getFieldText(1), panel.getFieldText(2));
			panel.setFieldText(3, (float)exponentlessSide);
		}
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Exponentless Side");
		panel.setLabelText(1, "Base");
		panel.setLabelText(2, "Exponent");
		// output
		panel.setLabelText(3, "Exponentless Side");
		panel.setLabelText(4, "Base");
		panel.setLabelText(5, "Exponent");
	}

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#isListItem()
	 */
	@Override
	public boolean isListItem() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#getListDisplayName()
	 */
	@Override
	public String getListDisplayName() {
		return DISPLAY_NAME;
	}

}
