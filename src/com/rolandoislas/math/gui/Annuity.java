/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

/**
 * @author Rolando Islas
 *
 */
public class Annuity implements ApplicationState {

	private static final int ID = 7;
	private static final String DISPLAY_NAME = "Annuity";
	private static final int INPUT_FIELDS = 5;
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
		setFieldsNull();
		calculateDeposit();
		calculateRate();
		calculateYears();
		calculateFinalBalance();
	}

	/**
	 * 
	 */
	private void setFieldsNull() {
		for(int i = INPUT_FIELDS; i < INPUT_FIELDS + OUTPUT_FIELDS; i++) {
			panel.setFieldText(i, "null");
		}
	}

	/**
	 * 
	 */
	private void calculateFinalBalance() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 2, 3})) {
			double finalBalance = (panel.getFieldText(0) * (Math.pow(1 + panel.getFieldText(1) / panel.getFieldText(3), panel.getFieldText(2) * panel.getFieldText(3)) - 1)) / (panel.getFieldText(1) / panel.getFieldText(3));
			panel.setFieldText(7, (float)finalBalance);
		}
	}

	/**
	 * 
	 */
	private void calculateYears() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 3, 4})) {
			double years = Math.log10((panel.getFieldText(4) * (panel.getFieldText(1) / panel.getFieldText(3))) / panel.getFieldText(0) + 1) / (Math.log10(1 + panel.getFieldText(1) / panel.getFieldText(3)) * panel.getFieldText(3));
			panel.setFieldText(6, (float)years);
		}
	}

	/**
	 * 
	 */
	private void calculateRate() {
		/*
		if(panel.hasFieldsPopulated(new int[] {0, 2, 3, 4})) {
			double rate = (panel.getFieldText(0) * (Math.pow(1 + panel.getFieldText(1) / panel.getFieldText(3), panel.getFieldText(2) * panel.getFieldText(3)) - 1)) / panel.getFieldText(4) * panel.getFieldText(3);
			panel.setFieldText(6, (float)rate);
		}
		*/
	}

	/**
	 * 
	 */
	private void calculateDeposit() {
		if(panel.hasFieldsPopulated(new int[] {1, 2, 3, 4})) {
			double deposit = (panel.getFieldText(4) * (panel.getFieldText(1) / panel.getFieldText(3))) / (Math.pow(1 + (panel.getFieldText(1) / panel.getFieldText(3)), panel.getFieldText(2) * panel.getFieldText(3)) - 1);
			panel.setFieldText(5, (float)deposit);
		}
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Deposit");
		panel.setLabelText(1, "Rate");
		panel.setLabelText(2, "Years");
		panel.setLabelText(3, "Period");
		panel.setLabelText(4, "Final Balance");
		// output
		panel.setLabelText(5, "Deposit");
		//panel.setLabelText(6, "Rate");
		panel.setLabelText(6, "Years");
		panel.setLabelText(7, "Final Balance");
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
