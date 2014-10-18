/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rolandoislas.math.gui.panel.SolveVariable;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Rolando Islas
 *
 */
public class InterestSimple extends JPanel implements ApplicationState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DISPLAY_NAME = "Simple Interest";
	private static final int ID = 4;
	private static final int INPUT_FIELDS = 5;
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
		panel.setFieldsNull();
		calculatePrincipal();
		calculateRate();
		calculateInterest();
		calculateTime();
		calculateEndAmount();
	}

	/**
	 * 
	 */
	private void calculateEndAmount() {
		// A = P(1 + rt)
		if(panel.hasFieldsPopulated(new int[] {0, 1, 3})) {
			double endAmount = panel.getFieldText(0) * (1 + panel.getFieldText(1) * panel.getFieldText(3));
			panel.setFieldText(9, (float)endAmount);
		}
	}

	/**
	 * 
	 */
	private void calculateTime() {
		// t = I / (Pr)
		if(panel.hasFieldsPopulated(new int[] {0, 1, 2})) {
			double time = panel.getFieldText(2) / (panel.getFieldText(0) * panel.getFieldText(1));
			panel.setFieldText(8, (float)time);
		}
	}

	/**
	 * 
	 */
	private void calculateInterest() {
		// I = Prt
		if(panel.hasFieldsPopulated(new int[] {0, 1, 3})) {
			double interest = panel.getFieldText(0) * panel.getFieldText(1) * panel.getFieldText(3);
			panel.setFieldText(7, (float)interest);
		}
	}

	/**
	 * 
	 */
	private void calculateRate() {
		// r = I / (Pt)
		if(panel.hasFieldsPopulated(new int[] {0, 2, 3})) {
			double rate = panel.getFieldText(2) / (panel.getFieldText(0) * panel.getFieldText(3));
			panel.setFieldText(6, (float)rate);
		}
	}

	/**
	 * 
	 */
	private void calculatePrincipal() {
		// P = I / (rt)
		if(panel.hasFieldsPopulated(new int[] {1, 2, 3})) {
			double principal = panel.getFieldText(2) / (panel.getFieldText(1) * panel.getFieldText(3));
			panel.setFieldText(5, (float)principal);
		}
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Principal");
		panel.setLabelText(1, "Rate");
		panel.setLabelText(2, "Interest");
		panel.setLabelText(3, "Time");
		panel.setLabelText(4, "End Amount");
		// output
		panel.setLabelText(5, "Principal");
		panel.setLabelText(6, "Rate");
		panel.setLabelText(7, "Interest");
		panel.setLabelText(8, "Time");
		panel.setLabelText(9, "End Amount");
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
