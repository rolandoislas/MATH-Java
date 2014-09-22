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
import java.math.BigDecimal;

import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Rolando Islas
 *
 */
public class Logarithm extends JPanel implements ApplicationState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID = 3;
	private static final String DISPLAY_NAME = "Logarithms";
	private JTextField tfNoExponent;
	private JTextField tfBase;
	private JTextField tfExponent;
	private JTextField tfNoExponentResult;
	private JTextField tfBaseResult;
	private JTextField tfExponentResult;
	
	public Logarithm() {
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
		
		JLabel lblLog = new JLabel("Logarithms");
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblLog, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblLog);
		
		tfNoExponent = new JTextField();
		tfNoExponent.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfNoExponent, 23, SpringLayout.SOUTH, lblLog);
		springLayout.putConstraint(SpringLayout.WEST, tfNoExponent, 179, SpringLayout.WEST, this);
		add(tfNoExponent);
		tfNoExponent.setColumns(10);
		
		tfBase = new JTextField();
		tfBase.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfBase, 6, SpringLayout.SOUTH, tfNoExponent);
		springLayout.putConstraint(SpringLayout.WEST, tfBase, 0, SpringLayout.WEST, tfNoExponent);
		add(tfBase);
		tfBase.setColumns(10);
		
		tfExponent = new JTextField();
		tfExponent.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfExponent, 6, SpringLayout.SOUTH, tfBase);
		springLayout.putConstraint(SpringLayout.WEST, tfExponent, 0, SpringLayout.WEST, tfNoExponent);
		add(tfExponent);
		tfExponent.setColumns(10);
		
		JLabel lblNoExponent = new JLabel("Exponentless side:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblNoExponent, 0, SpringLayout.SOUTH, tfNoExponent);
		springLayout.putConstraint(SpringLayout.EAST, lblNoExponent, -6, SpringLayout.WEST, tfNoExponent);
		add(lblNoExponent);
		
		JLabel lblBase = new JLabel("Base:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblBase, 0, SpringLayout.SOUTH, tfBase);
		springLayout.putConstraint(SpringLayout.EAST, lblBase, -6, SpringLayout.WEST, tfBase);
		add(lblBase);
		
		JLabel lblExponent = new JLabel("Exponent:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblExponent, 0, SpringLayout.SOUTH, tfExponent);
		springLayout.putConstraint(SpringLayout.EAST, lblExponent, -6, SpringLayout.WEST, tfExponent);
		add(lblExponent);
		
		tfNoExponentResult = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, tfNoExponentResult, 200, SpringLayout.WEST, tfNoExponent);
		tfNoExponentResult.setBorder(null);
		tfNoExponentResult.setText("null");
		tfNoExponentResult.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, tfNoExponentResult, 43, SpringLayout.SOUTH, tfExponent);
		springLayout.putConstraint(SpringLayout.WEST, tfNoExponentResult, 0, SpringLayout.WEST, tfNoExponent);
		add(tfNoExponentResult);
		tfNoExponentResult.setColumns(10);
		
		tfBaseResult = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, tfBaseResult, 0, SpringLayout.WEST, tfNoExponent);
		springLayout.putConstraint(SpringLayout.EAST, tfBaseResult, 0, SpringLayout.EAST, tfNoExponentResult);
		tfBaseResult.setText("null");
		tfBaseResult.setEditable(false);
		tfBaseResult.setBorder(null);
		add(tfBaseResult);
		tfBaseResult.setColumns(10);
		
		tfExponentResult = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, tfBaseResult, -6, SpringLayout.NORTH, tfExponentResult);
		springLayout.putConstraint(SpringLayout.NORTH, tfExponentResult, 207, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, tfExponentResult, 0, SpringLayout.EAST, tfNoExponentResult);
		tfExponentResult.setText("null");
		tfExponentResult.setEditable(false);
		tfExponentResult.setBorder(null);
		springLayout.putConstraint(SpringLayout.WEST, tfExponentResult, 0, SpringLayout.WEST, tfNoExponent);
		add(tfExponentResult);
		tfExponentResult.setColumns(10);
		
		JLabel lblNoExponentResult = new JLabel("Exponentless  side:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNoExponentResult, 0, SpringLayout.NORTH, tfNoExponentResult);
		springLayout.putConstraint(SpringLayout.EAST, lblNoExponentResult, 0, SpringLayout.EAST, lblNoExponent);
		add(lblNoExponentResult);
		
		JLabel lblBaseResult = new JLabel("Base:");
		springLayout.putConstraint(SpringLayout.EAST, lblBaseResult, 0, SpringLayout.EAST, lblNoExponent);
		add(lblBaseResult);
		
		JLabel lblExponentResult = new JLabel("Exponent:");
		springLayout.putConstraint(SpringLayout.NORTH, lblExponentResult, 207, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblBaseResult, -6, SpringLayout.NORTH, lblExponentResult);
		springLayout.putConstraint(SpringLayout.EAST, lblExponentResult, 0, SpringLayout.EAST, lblNoExponent);
		add(lblExponentResult);
	}

	/**
	 * 
	 */
	protected void updateData() {
		if((!tfBase.getText().equals("")) && (!tfExponent.getText().equals(""))) {
			double noExponent = Math.pow(Double.parseDouble(tfBase.getText()),Double.parseDouble(tfExponent.getText()));
			clearFields();
			tfNoExponentResult.setText(noExponent + "");
		}
		if((!tfNoExponent.getText().equals("")) && (!tfExponent.getText().equals(""))) {
			double base;
			if(!tfExponent.getText().equals("3")) {
				BigDecimal one = new BigDecimal(1);
				BigDecimal exponent = new BigDecimal(Double.parseDouble(tfExponent.getText()));
				base = Math.pow(Double.parseDouble(tfNoExponent.getText()), one.divide(exponent, 11, BigDecimal.ROUND_HALF_UP).doubleValue());
			} else {
				base = Math.cbrt(Double.parseDouble(tfNoExponent.getText()));
			}
			clearFields();
			tfBaseResult.setText(base + "");
		}
		if((!tfNoExponent.getText().equals("")) && (!tfBase.getText().equals(""))) {
			double exponent = Math.log10(Double.parseDouble(tfNoExponent.getText())) / Math.log10(Double.parseDouble(tfBase.getText()));
			clearFields();
			tfExponentResult.setText(exponent + "");
		}
	}

	/**
	 * 
	 */
	private void clearFields() {
		tfNoExponentResult.setText("null");
		tfBaseResult.setText("null");
		tfExponentResult.setText("null");
		
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
