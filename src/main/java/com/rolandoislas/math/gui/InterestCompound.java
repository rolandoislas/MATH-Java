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
public class InterestCompound implements ApplicationState {

	private static final int ID = 5;
	private static final String DISPLAY_NAME = "Compound Interest";
	private static final int INPUT_FIELDS = 5;
	private static final int OUTPUT_FIELDS = 5;
	private SolveVariable icPanel;
	
	public InterestCompound() {
		
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
		icPanel = new SolveVariable(DISPLAY_NAME, INPUT_FIELDS, OUTPUT_FIELDS);
		setFieldNames();
		setFieldEvents();
		return icPanel;
	}

	/**
	 * 
	 */
	private void setFieldEvents() {
		for(int i = 0; i < INPUT_FIELDS; i++) {
			icPanel.getField(i).addCaretListener(new CaretListener() {
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
		calculatePeriod();
	}

	/**
	 * 
	 */
	private void calculateFinalBalance() {
		if(icPanel.isFieldPopulated(0) && icPanel.isFieldPopulated(1) && icPanel.isFieldPopulated(2) && icPanel.isFieldPopulated(3)) {
			double finalBalance = icPanel.getFieldText(0) * Math.pow((1 + icPanel.getFieldText(1) / icPanel.getFieldText(3)), icPanel.getFieldText(2) * icPanel.getFieldText(3));
			double interest = finalBalance - icPanel.getFieldText(0);
			icPanel.setFieldText(8, (float)interest);
			icPanel.setFieldText(9, (float)finalBalance);
		}
	}

	/**
	 * 
	 */
	private void calculatePeriod() {
		/*
		if(icPanel.isFieldPopulated(0) && icPanel.isFieldPopulated(1) && icPanel.isFieldPopulated(2) && icPanel.isFieldPopulated(4)) {
			double period = (1 / (icPanel.getFieldText(2) * Math.log10(icPanel.getFieldText(4) / icPanel.getFieldText(0)))) - icPanel.getFieldText(1);
			icPanel.setFieldText(8, (float)period);
		}
		*/
	}

	/**
	 * 
	 */
	private void calculateRate() {
		if(icPanel.isFieldPopulated(0) && icPanel.isFieldPopulated(2) && icPanel.isFieldPopulated(3) && icPanel.isFieldPopulated(4)) {
			double rate = (Math.pow((icPanel.getFieldText(4) / icPanel.getFieldText(0)), 1 / (icPanel.getFieldText(2) * icPanel.getFieldText(3))) - 1) * icPanel.getFieldText(3);
			double interest = icPanel.getFieldText(4) - icPanel.getFieldText(0);
			icPanel.setFieldText(8, (float)interest);
			icPanel.setFieldText(6, (float)rate);
		}
	}

	/**
	 * 
	 */
	private void calculateYears() {
		if(icPanel.isFieldPopulated(0) && icPanel.isFieldPopulated(1) && icPanel.isFieldPopulated(3) && icPanel.isFieldPopulated(4)) {
			double years = Math.log10(icPanel.getFieldText(4) / icPanel.getFieldText(0)) / (Math.log10(1 + icPanel.getFieldText(1) / icPanel.getFieldText(3)) * icPanel.getFieldText(3));
			double interest = icPanel.getFieldText(4) - icPanel.getFieldText(0);
			icPanel.setFieldText(8, (float)interest);
			icPanel.setFieldText(7, (float)years);
		}
	}

	/**
	 * 
	 */
	private void setFieldsNull() {
		for(int i = 5; i < 9; i++) {
			icPanel.setFieldText(i, "null");
		}
	}

	/**
	 * 
	 */
	private void calculatePrincipal() {
		if(icPanel.isFieldPopulated(1) && icPanel.isFieldPopulated(2) && icPanel.isFieldPopulated(3) && icPanel.isFieldPopulated(4)) {
			double principal = icPanel.getFieldText(4) / (Math.pow((1 + icPanel.getFieldText(1) / icPanel.getFieldText(3)), icPanel.getFieldText(2) * icPanel.getFieldText(3)));
			double interest = icPanel.getFieldText(4) - principal;
			icPanel.setFieldText(8, (float)interest);
			icPanel.setFieldText(5, (float)principal);
		}
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		icPanel.setLabelText(0, "Principal");
		icPanel.setLabelText(1, "Rate");
		icPanel.setLabelText(2, "Years");
		icPanel.setLabelText(3, "Period");
		icPanel.setLabelText(4, "Final Balance");
		// output
		icPanel.setLabelText(5, "Principal");
		icPanel.setLabelText(6, "Rate");
		icPanel.setLabelText(7, "Years");
		icPanel.setLabelText(8, "Interest");
		icPanel.setLabelText(9, "Final Balance");
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
