/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;

import com.rolandoislas.math.gui.panel.SolveVariable;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Rolando Islas
 *
 */
public class ExponentialGrowth implements ApplicationState {

	/**
	 * 
	 */
	private static final int ID = 2;
	private static final String DISPLAT_NAME = "Exponential Growth";
	private static final int INPUT_FIELDS = 4;
	private static final int OUTPUT_FIELDS = 4;
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
		panel = new SolveVariable(DISPLAT_NAME, INPUT_FIELDS, OUTPUT_FIELDS);
		setFieldNames();
		setFieldEvents();
		return panel;
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Principal");
		panel.setLabelText(1, "Rate");
		panel.setLabelText(2, "Interval");
		panel.setLabelText(3, "Interval Value");
		// output
		panel.setLabelText(4, "Principal");
		panel.setLabelText(5, "Rate");
		panel.setLabelText(6, "Interval");
		panel.setLabelText(7, "Interval Value");
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
		panel.setFieldsNull();
		calculatePrincipal();
		calculateRate();
		calculateInterval();
		calculateIntervalValue();
	}

	/**
	 * 
	 */
	private void calculateIntervalValue() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 2})) {
			double intervalValue = panel.getFieldText(0) * Math.pow(1 + panel.getFieldText(1), panel.getFieldText(2));
			panel.setFieldText(7, (float)intervalValue);
		}
	}

	/**
	 * 
	 */
	private void calculateInterval() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 3})) {
			double interval = Math.log10(panel.getFieldText(3) / panel.getFieldText(0)) / Math.log10(1 + panel.getFieldText(1));
			panel.setFieldText(6, (float)interval);
		}
	}

	/**
	 * 
	 */
	private void calculateRate() {
		if(panel.hasFieldsPopulated(new int[] {0, 2, 3})) {
			double rate = Math.pow(panel.getFieldText(3) / panel.getFieldText(0), 1 / panel.getFieldText(2)) - 1;
			panel.setFieldText(5, (float)rate);
		}
	}

	/**
	 * 
	 */
	private void calculatePrincipal() {
		if(panel.hasFieldsPopulated(new int[] {1, 2, 3})) {
			double principal = panel.getFieldText(3) / Math.pow(1 + panel.getFieldText(1), panel.getFieldText(2));
			panel.setFieldText(4, (float)principal);
		}
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
		return DISPLAT_NAME;
	}
}
