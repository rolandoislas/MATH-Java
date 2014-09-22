/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
public class ExponentialGrowth extends JPanel implements ApplicationState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID = 2;
	private static final String DISPLAT_NAME = "Exponential Growth";
	private JTextField tfPrincipal;
	private JTextField tfRate;
	private JTextField tfInterval;
	private JTextField tfPrincipalResult;
	private JTextField tfRateResult;
	private JTextField tfIntervalResult;
	private JTextField tfIntervalValue;
	
	public ExponentialGrowth() {
		createComponents();
	}

	/**
	 * 
	 */
	private void createComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		ButtomHome homeButton = new ButtomHome();
		add(homeButton);
		
		JLabel lblExponentialGrowth = new JLabel("Exponential Growth");
		lblExponentialGrowth.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblExponentialGrowth, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblExponentialGrowth);
		
		JLabel lblPrinciple = new JLabel("Principal:");
		add(lblPrinciple);
		
		tfPrincipal = new JTextField();
		tfPrincipal.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, tfPrincipal, 188, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblPrinciple, 3, SpringLayout.NORTH, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, lblPrinciple, -6, SpringLayout.WEST, tfPrincipal);
		add(tfPrincipal);
		tfPrincipal.setColumns(10);
		
		tfRate = new JTextField();
		tfRate.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, tfPrincipal, -6, SpringLayout.NORTH, tfRate);
		springLayout.putConstraint(SpringLayout.EAST, tfRate, 0, SpringLayout.EAST, tfPrincipal);
		add(tfRate);
		tfRate.setColumns(10);
		
		tfInterval = new JTextField();
		tfInterval.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfInterval, 100, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, tfRate, -6, SpringLayout.NORTH, tfInterval);
		springLayout.putConstraint(SpringLayout.EAST, tfInterval, 0, SpringLayout.EAST, tfPrincipal);
		add(tfInterval);
		tfInterval.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRate, 3, SpringLayout.NORTH, tfRate);
		springLayout.putConstraint(SpringLayout.EAST, lblRate, 0, SpringLayout.EAST, lblPrinciple);
		add(lblRate);
		
		JLabel lblInterval = new JLabel("Interval (Pn):");
		springLayout.putConstraint(SpringLayout.NORTH, lblInterval, 3, SpringLayout.NORTH, tfInterval);
		springLayout.putConstraint(SpringLayout.EAST, lblInterval, 0, SpringLayout.EAST, lblPrinciple);
		add(lblInterval);
		
		tfPrincipalResult = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, tfPrincipalResult, 0, SpringLayout.WEST, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, tfPrincipalResult, -62, SpringLayout.EAST, this);
		tfPrincipalResult.setText("null");
		tfPrincipalResult.setBorder(null);
		tfPrincipalResult.setEditable(false);
		add(tfPrincipalResult);
		tfPrincipalResult.setColumns(10);
		
		tfRateResult = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, tfPrincipalResult, -6, SpringLayout.NORTH, tfRateResult);
		springLayout.putConstraint(SpringLayout.EAST, tfRateResult, 0, SpringLayout.EAST, tfPrincipalResult);
		springLayout.putConstraint(SpringLayout.WEST, tfRateResult, 0, SpringLayout.WEST, tfPrincipal);
		tfRateResult.setEditable(false);
		tfRateResult.setBorder(null);
		tfRateResult.setText("null");
		add(tfRateResult);
		tfRateResult.setColumns(10);
		
		tfIntervalResult = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, tfRateResult, -6, SpringLayout.NORTH, tfIntervalResult);
		springLayout.putConstraint(SpringLayout.EAST, tfIntervalResult, 0, SpringLayout.EAST, tfPrincipalResult);
		springLayout.putConstraint(SpringLayout.NORTH, tfIntervalResult, 201, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, tfIntervalResult, 0, SpringLayout.WEST, tfPrincipal);
		tfIntervalResult.setBorder(null);
		tfIntervalResult.setEditable(false);
		tfIntervalResult.setText("null");
		add(tfIntervalResult);
		tfIntervalResult.setColumns(10);
		
		JLabel lblPrincipalResult = new JLabel("Principal:");
		springLayout.putConstraint(SpringLayout.WEST, lblPrincipalResult, 0, SpringLayout.WEST, lblPrinciple);
		add(lblPrincipalResult);
		
		JLabel lblRateResult = new JLabel("Rate:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblPrincipalResult, -6, SpringLayout.NORTH, lblRateResult);
		springLayout.putConstraint(SpringLayout.EAST, lblRateResult, 0, SpringLayout.EAST, lblPrinciple);
		add(lblRateResult);
		
		JLabel lblIntervalResult = new JLabel("Interval:");
		springLayout.putConstraint(SpringLayout.NORTH, lblIntervalResult, 201, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblRateResult, -6, SpringLayout.NORTH, lblIntervalResult);
		springLayout.putConstraint(SpringLayout.EAST, lblIntervalResult, 0, SpringLayout.EAST, lblPrinciple);
		add(lblIntervalResult);
		
		tfIntervalValue = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tfIntervalValue, 0, SpringLayout.NORTH, tfInterval);
		tfIntervalValue.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		add(tfIntervalValue);
		tfIntervalValue.setColumns(10);
		
		JLabel lblIntervalValue = new JLabel("Value:");
		springLayout.putConstraint(SpringLayout.WEST, tfIntervalValue, 6, SpringLayout.EAST, lblIntervalValue);
		springLayout.putConstraint(SpringLayout.NORTH, lblIntervalValue, 3, SpringLayout.NORTH, tfInterval);
		springLayout.putConstraint(SpringLayout.WEST, lblIntervalValue, 6, SpringLayout.EAST, tfInterval);
		add(lblIntervalValue);
	}

	/**
	 * 
	 */
	protected void updateData() {
		if((!tfIntervalValue.getText().equals("")) && (!tfInterval.getText().equals("")) && 
				(!tfRate.getText().equals(""))) {
			double principle = Double.parseDouble(tfIntervalValue.getText()) / 
					Math.pow(1 + Double.parseDouble(tfRate.getText()), 
							Double.parseDouble(tfInterval.getText()));
			clearFields();
			tfPrincipalResult.setText(principle + "");
		}
		if((!tfIntervalValue.getText().equals("")) && (!tfPrincipal.getText().equals("")) && 
				(!tfInterval.getText().equals(""))) {
			double rate = (Math.pow(Double.parseDouble(tfIntervalValue.getText()) / 
					Double.parseDouble(tfPrincipal.getText()), 1 / 
					Double.parseDouble(tfInterval.getText()))) - 1;
			clearFields();
			tfRateResult.setText(rate + "");
		}
		if((!tfIntervalValue.getText().equals("")) && (!tfPrincipal.getText().equals("")) && 
				(!tfRate.getText().equals(""))) {
			double interval = Math.log10(Double.parseDouble(tfIntervalValue.getText()) / 
					Double.parseDouble(tfPrincipal.getText())) / Math.log10(1 + 
							Double.parseDouble(tfRate.getText()));
			clearFields();
			tfIntervalResult.setText(interval + "");
		}
		if((!tfPrincipal.getText().equals("")) && (!tfRate.getText().equals("")) && 
				(!tfInterval.getText().equals(""))) {
			double intervalValue = Double.parseDouble(tfPrincipal.getText()) * 
					Math.pow(1 + Double.parseDouble(tfRate.getText()), 
							Double.parseDouble(tfInterval.getText()));
			clearFields();
			tfIntervalResult.setText(intervalValue + "");
		}
	}

	/**
	 * 
	 */
	private void clearFields() {
		tfPrincipalResult.setText("null");
		tfRateResult.setText("null");
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
		// TODO Auto-generated method stub
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
		return DISPLAT_NAME;
	}
}
