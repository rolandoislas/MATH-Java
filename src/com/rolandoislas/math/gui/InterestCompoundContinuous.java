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
public class InterestCompoundContinuous implements ApplicationState {

	private static final int ID = 6;
	private static final String DISPLAY_NAME = "Compound Interest Continuous";
	private static final int INPUT_FIELDS = 4;
	private static final int OUTPUT_FIELDS = 5;
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
		calculatePrincipal();
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
		if(panel.hasFieldsPopulated(new int[] {0, 1, 2})) {
			double finalBalance = panel.getFieldText(0) * Math.exp(panel.getFieldText(1) * panel.getFieldText(2));
			caculateInterest(finalBalance, panel.getFieldText(0));
			panel.setFieldText(8, (float)finalBalance);
		}
	}

	/**
	 * @param principal 
	 * @param finalBalance 
	 * 
	 */
	private void caculateInterest(double finalBalance, double principal) {
		panel.setFieldText(7, (float)(finalBalance - principal));
	}

	/**
	 * 
	 */
	private void calculateYears() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 3})) {
			double years = Math.log10(panel.getFieldText(3) / panel.getFieldText(0)) / (Math.log10(Math.exp(1)) * panel.getFieldText(1));
			caculateInterest(panel.getFieldText(3), panel.getFieldText(0));
			panel.setFieldText(6, (float)years);
		}
	}

	/**
	 * 
	 */
	private void calculateRate() {
		if(panel.hasFieldsPopulated(new int[] {0, 2, 3})) {
			double rate = Math.log10(panel.getFieldText(3) / panel.getFieldText(0)) / (Math.log10(Math.exp(1)) * panel.getFieldText(2));
			caculateInterest(panel.getFieldText(3), panel.getFieldText(0));
			panel.setFieldText(5, (float)rate);
		}
	}

	/**
	 * 
	 */
	private void calculatePrincipal() {
		if(panel.hasFieldsPopulated(new int[] {1, 2, 3})) {
			double principal = panel.getFieldText(3) / Math.exp(panel.getFieldText(1) * panel.getFieldText(2));
			caculateInterest(panel.getFieldText(3), principal);
			panel.setFieldText(4, (float)principal);
		}
		
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Principal");
		panel.setLabelText(1, "Rate");
		panel.setLabelText(2, "Years");
		panel.setLabelText(3, "Final Balance");
		// output
		panel.setLabelText(4, "Principal");
		panel.setLabelText(5, "Rate");
		panel.setLabelText(6, "Years");
		panel.setLabelText(7, "Interest");
		panel.setLabelText(8, "Final Balance");
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
