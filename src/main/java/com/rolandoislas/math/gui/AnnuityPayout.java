/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.rolandoislas.math.gui.panel.SolveVariable;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

/**
 * @author Rolando Islas
 *
 */
public class AnnuityPayout implements ApplicationState {

	private static final int ID = 8;
	private String displayName = "Annuity Payout";
	private static final int INPUT_FIELDS = 5;
	private static final int OUTPUT_FIELDS = 2;
	protected SolveVariable panel;
	
	/**
	 * 
	 */
	public AnnuityPayout() {
	}

	/**
	 * @param string
	 */
	public AnnuityPayout(String displayName) {
		this.displayName = displayName;
	}

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
		panel = new SolveVariable(displayName, INPUT_FIELDS, OUTPUT_FIELDS);
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
		calculateYears();
		calculatePrincipal();
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
	private void calculatePrincipal() {
		if(panel.hasFieldsPopulated(new int[] {1, 2, 3, 4})) {
			double numerator = panel.getFieldText(1) * (1 - Math.pow(1 + (panel.getFieldText(2) / panel.getFieldText(4)), -panel.getFieldText(3) * panel.getFieldText(4)));
			double denominator = panel.getFieldText(2) / panel.getFieldText(4);
			double principal = numerator / denominator;
			panel.setFieldText(5, (float)principal);
		}
	}

	/**
	 * 
	 */
	private void calculateYears() {
		/*
		if(panel.hasFieldsPopulated(new int[] {0, 1, 2, 4})) {
			double numerator =  (panel.getFieldText(0) * (panel.getFieldText(2) / panel.getFieldText(4))) / panel.getFieldText(1) - 1;
			double denominator = -(1 + (panel.getFieldText(2) / panel.getFieldText(4)));
			double years = Math.log10(numerator) / (Math.log10(denominator) * panel.getFieldText(4));
			panel.setFieldText(7, (float)(years / -1));
		}
		*/
	}

	/**
	 * 
	 */
	private void calculateDeposit() {
		if(panel.hasFieldsPopulated(new int[] {0, 2, 3, 4})) {
			double numerator = panel.getFieldText(0) * (panel.getFieldText(2) / panel.getFieldText(4));
			double denominator = 1 - Math.pow(1 + panel.getFieldText(2) / panel.getFieldText(4), -panel.getFieldText(3) * panel.getFieldText(4));
			double deposit = numerator / denominator;
			panel.setFieldText(6, (float)deposit);
		}
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Principal");
		panel.setLabelText(1, "Deposit");
		panel.setLabelText(2, "Rate");
		panel.setLabelText(3, "Years");
		panel.setLabelText(4, "Period");
		// output
		panel.setLabelText(5, "Principal");
		panel.setLabelText(6, "Deposit");
		//panel.setLabelText(7, "Years");
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
		return displayName;
	}

}
