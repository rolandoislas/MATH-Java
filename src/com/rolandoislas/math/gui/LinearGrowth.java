/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rolandoislas.math.data.Numeral;
import com.rolandoislas.math.gui.asset.button.ButtomHome;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Rolando Islas
 *
 */
public class LinearGrowth extends JPanel implements ApplicationState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DISPLAY_NAME = "Linear Growth";
	private static final int ID = 1;
	private JTextField tfInitialPopulation;
	private JTextField tfCommonDifference;
	private JTextField tfInterval;
	private JLabel lblInitialPopulation;
	private JLabel lblCommonDifference;
	private JLabel lblInterval;
	private JTextField tfCommonDifferenceResult;
	private JTextField tfIntervalResult;
	private JLabel lblIntervalValue;
	private JTextField tfIntervalValue;
	private JTextField tfInitialPopulationResult;

	public LinearGrowth() {
		createComponents();
	}
	
	/**
	 * 
	 */
	private void createComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblLinearGrowth = new JLabel("Linear Growth");
		lblLinearGrowth.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblLinearGrowth, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblLinearGrowth);
		
		ButtomHome homeButton = new ButtomHome();
		add(homeButton);
		
		lblInitialPopulation = new JLabel("Initial Population (P" + Numeral.sub(0) + "):");
		add(lblInitialPopulation);
		
		tfInitialPopulation = new JTextField();
		tfInitialPopulation.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, tfInitialPopulation, 208, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblInitialPopulation, 3, SpringLayout.NORTH, tfInitialPopulation);
		springLayout.putConstraint(SpringLayout.EAST, lblInitialPopulation, -6, SpringLayout.WEST, tfInitialPopulation);
		springLayout.putConstraint(SpringLayout.NORTH, tfInitialPopulation, 58, SpringLayout.NORTH, this);
		add(tfInitialPopulation);
		tfInitialPopulation.setColumns(10);
		
		lblCommonDifference = new JLabel("Common Differnce:");
		springLayout.putConstraint(SpringLayout.EAST, lblCommonDifference, 0, SpringLayout.EAST, lblInitialPopulation);
		add(lblCommonDifference);
		
		tfCommonDifference = new JTextField();
		tfCommonDifference.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblCommonDifference, 3, SpringLayout.NORTH, tfCommonDifference);
		springLayout.putConstraint(SpringLayout.NORTH, tfCommonDifference, 84, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, tfCommonDifference, 0, SpringLayout.WEST, tfInitialPopulation);
		add(tfCommonDifference);
		tfCommonDifference.setColumns(10);
		
		lblInterval = new JLabel("Interval (P" + Numeral.subn + "):");
		springLayout.putConstraint(SpringLayout.EAST, lblInterval, 0, SpringLayout.EAST, lblInitialPopulation);
		add(lblInterval);
		
		tfInterval = new JTextField();
		tfInterval.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblInterval, 3, SpringLayout.NORTH, tfInterval);
		springLayout.putConstraint(SpringLayout.WEST, tfInterval, 208, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, tfInterval, 111, SpringLayout.NORTH, this);
		add(tfInterval);
		tfInterval.setColumns(10);
		
		JLabel lblCommonDifferenceResult = new JLabel("Common Difference:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCommonDifferenceResult, 213, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblCommonDifferenceResult, 0, SpringLayout.EAST, lblInitialPopulation);
		add(lblCommonDifferenceResult);
		
		JLabel lblIntervalResult = new JLabel("Interval (P" + Numeral.subn + "):");
		springLayout.putConstraint(SpringLayout.NORTH, lblIntervalResult, 6, SpringLayout.SOUTH, lblCommonDifferenceResult);
		springLayout.putConstraint(SpringLayout.EAST, lblIntervalResult, 0, SpringLayout.EAST, lblInitialPopulation);
		add(lblIntervalResult);
		
		tfCommonDifferenceResult = new JTextField("null");
		tfCommonDifferenceResult.setBackground(new Color(238, 238, 238));
		springLayout.putConstraint(SpringLayout.NORTH, tfCommonDifferenceResult, 0, SpringLayout.NORTH, lblCommonDifferenceResult);
		springLayout.putConstraint(SpringLayout.WEST, tfCommonDifferenceResult, 0, SpringLayout.WEST, tfInitialPopulation);
		springLayout.putConstraint(SpringLayout.EAST, tfCommonDifferenceResult, 166, SpringLayout.WEST, tfInitialPopulation);
		tfCommonDifferenceResult.setEditable(false);
		tfCommonDifferenceResult.setBorder(null);
		add(tfCommonDifferenceResult);
		tfCommonDifferenceResult.setColumns(10);
		
		tfIntervalResult = new JTextField("null");
		tfIntervalResult.setBackground(new Color(238, 238, 238));
		springLayout.putConstraint(SpringLayout.NORTH, tfIntervalResult, 0, SpringLayout.NORTH, lblIntervalResult);
		springLayout.putConstraint(SpringLayout.WEST, tfIntervalResult, 0, SpringLayout.WEST, tfInitialPopulation);
		springLayout.putConstraint(SpringLayout.EAST, tfIntervalResult, 0, SpringLayout.EAST, tfCommonDifferenceResult);
		tfIntervalResult.setEditable(false);
		tfIntervalResult.setBorder(null);
		add(tfIntervalResult);
		tfIntervalResult.setColumns(10);
		
		lblIntervalValue = new JLabel("Value:");
		springLayout.putConstraint(SpringLayout.NORTH, lblIntervalValue, 6, SpringLayout.NORTH, tfInterval);
		add(lblIntervalValue);
		
		tfIntervalValue = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, tfIntervalValue, 405, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblIntervalValue, -6, SpringLayout.WEST, tfIntervalValue);
		tfIntervalValue.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfIntervalValue, -3, SpringLayout.NORTH, lblInterval);
		add(tfIntervalValue);
		tfIntervalValue.setColumns(10);
		
		tfInitialPopulationResult = new JTextField();
		tfInitialPopulationResult.setBackground(new Color(238, 238, 238));
		springLayout.putConstraint(SpringLayout.WEST, tfInitialPopulationResult, 0, SpringLayout.WEST, tfInitialPopulation);
		springLayout.putConstraint(SpringLayout.SOUTH, tfInitialPopulationResult, -7, SpringLayout.NORTH, tfCommonDifferenceResult);
		springLayout.putConstraint(SpringLayout.EAST, tfInitialPopulationResult, 0, SpringLayout.EAST, tfCommonDifferenceResult);
		tfInitialPopulationResult.setBorder(null);
		tfInitialPopulationResult.setEditable(false);
		tfInitialPopulationResult.setText("null");
		add(tfInitialPopulationResult);
		tfInitialPopulationResult.setColumns(10);
		
		JLabel lblInitioalPopulation = new JLabel("Initial Population (P" + Numeral.sub(0) + "):");
		springLayout.putConstraint(SpringLayout.SOUTH, lblInitioalPopulation, -7, SpringLayout.NORTH, lblCommonDifferenceResult);
		springLayout.putConstraint(SpringLayout.EAST, lblInitioalPopulation, 0, SpringLayout.EAST, lblInitialPopulation);
		add(lblInitioalPopulation);
	}

	/**
	 * 
	 */
	protected void updateData() {
		/*
			private JTextField tfInitialPopulation;
			private JTextField tfCommonDifference;
			private JTextField tfInterval;
			private JTextField tfIntervalValue;
			
			tfInitialPopulationResult
			tfCommonDifferenceResult
			tfIntervalResult
		*/
		if((!tfInitialPopulation.getText().equals("")) && (!tfInterval.getText().equals("")) && 
				(!tfIntervalValue.getText().equals(""))) {
			double commonDifference = (Double.parseDouble(tfIntervalValue.getText()) - 
					Double.parseDouble(tfInitialPopulation.getText())) / 
					Double.parseDouble(tfInterval.getText());
			clearFields();
			tfCommonDifferenceResult.setText(commonDifference + "");
		}
		if((!tfInitialPopulation.getText().equals("")) && (!tfInterval.getText().equals("")) && 
				(!tfCommonDifference.getText().equals(""))) {
			double intervalValue = Double.parseDouble(tfInitialPopulation.getText()) + 
					Double.parseDouble(tfCommonDifference.getText()) * 
					Double.parseDouble(tfInterval.getText());
			clearFields();
			tfIntervalResult.setText(intervalValue + "");
		}
		if((!tfInitialPopulation.getText().equals("")) && (!tfCommonDifference.getText().equals("")) && 
				(!tfIntervalValue.getText().equals(""))) {
			double interval = (Double.parseDouble(tfIntervalValue.getText()) - 
					Double.parseDouble(tfInitialPopulation.getText())) / 
					Double.parseDouble(tfCommonDifference.getText());
			clearFields();
			tfIntervalResult.setText(interval + "");
		}
		if((!tfIntervalValue.getText().equals("")) && (!tfCommonDifference.getText().equals("")) && 
				(!tfInterval.getText().equals(""))) {
			double initialPopulation = Double.parseDouble(tfIntervalValue.getText()) -
					(Double.parseDouble(tfCommonDifference.getText()) * 
							Double.parseDouble(tfInterval.getText()));
			clearFields();
			tfInitialPopulationResult.setText(initialPopulation + "");
		}
	}

	/**
	 * 
	 */
	private void clearFields() {
		tfInitialPopulationResult.setText("null");
		tfCommonDifferenceResult.setText("null");
		tfIntervalResult.setText("null");
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
		return this;
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
