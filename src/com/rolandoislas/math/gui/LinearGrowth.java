/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;
import javax.swing.JFrame;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Rolando Islas
 *
 */
public class LinearGrowth implements ApplicationState {

	private static final String DISPLAY_NAME = "Linear Growth";
	private static final int ID = 1;
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
		panel = new SolveVariable(DISPLAY_NAME, INPUT_FIELDS, OUTPUT_FIELDS);;
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
		calculateInitialPopulation();
		calculateCommonDifference();
		calculateInterval();
		calculateIntervalValue();
	}

	/**
	 * 
	 */
	private void calculateIntervalValue() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 2})) {
			double intervalValue = panel.getFieldText(0) + panel.getFieldText(1) * panel.getFieldText(2);
			panel.setFieldText(7, (float)intervalValue);
		}
	}

	/**
	 * 
	 */
	private void calculateInterval() {
		if(panel.hasFieldsPopulated(new int[] {0, 1, 3})) {
			double interval = (panel.getFieldText(3) - panel.getFieldText(0)) / panel.getFieldText(1);
			panel.setFieldText(6, (float)interval);
		}
	}

	/**
	 * 
	 */
	private void calculateCommonDifference() {
		if(panel.hasFieldsPopulated(new int[] {0, 2, 3})) {
			double difference = (panel.getFieldText(3) - panel.getFieldText(0)) / panel.getFieldText(2);
			panel.setFieldText(5, (float)difference);
		}
	}

	/**
	 * 
	 */
	private void calculateInitialPopulation() {
		if(panel.hasFieldsPopulated(new int[] {1, 2, 3})) {
			double population = panel.getFieldText(3) - panel.getFieldText(1) * panel.getFieldText(2);
			panel.setFieldText(4, (float)population);
		}
	}

	/**
	 * 
	 */
	private void setFieldNames() {
		// input
		panel.setLabelText(0, "Initial Population");
		panel.setLabelText(1, "Common Difference");
		panel.setLabelText(2, "Interval");
		panel.setLabelText(3, "Interval Value");
		// output
		panel.setLabelText(4, "Initial Population");
		panel.setLabelText(5, "Common Difference");
		panel.setLabelText(6, "Interval");
		panel.setLabelText(7, "Interval Value");
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
