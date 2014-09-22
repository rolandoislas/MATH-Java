/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rolandoislas.math.gui.asset.button.ButtomHome;
import com.rolandoislas.math.util.Field;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.SystemColor;

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
	private JTextField tfPrincipal;
	private JTextField tfRate;
	private JTextField tfInterest;
	private JTextField tfEndAmount;
	private JTextField tfPrincipalResult;
	private JTextField tfRateResult;
	private JTextField tfInterestResult;
	private JTextField tfEndAmountResult;
	private JTextField tfTime;
	private JTextField tfTimeResult;
	
	public InterestSimple() {
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
		
		JLabel lblSimpleInterest = new JLabel("Simple Interest");
		lblSimpleInterest.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblSimpleInterest, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblSimpleInterest);
		
		tfPrincipal = new JTextField();
		tfPrincipal.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfPrincipal, 16, SpringLayout.SOUTH, lblSimpleInterest);
		springLayout.putConstraint(SpringLayout.WEST, tfPrincipal, 181, SpringLayout.WEST, this);
		add(tfPrincipal);
		tfPrincipal.setColumns(10);
		
		tfRate = new JTextField();
		tfRate.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfRate, 6, SpringLayout.SOUTH, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, tfRate, 0, SpringLayout.EAST, tfPrincipal);
		add(tfRate);
		tfRate.setColumns(10);
		
		tfInterest = new JTextField();
		tfInterest.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfInterest, 6, SpringLayout.SOUTH, tfRate);
		springLayout.putConstraint(SpringLayout.WEST, tfInterest, 0, SpringLayout.WEST, tfPrincipal);
		add(tfInterest);
		tfInterest.setColumns(10);
		
		tfEndAmount = new JTextField();
		tfEndAmount.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, tfEndAmount, 6, SpringLayout.SOUTH, tfInterest);
		springLayout.putConstraint(SpringLayout.WEST, tfEndAmount, 0, SpringLayout.WEST, tfPrincipal);
		add(tfEndAmount);
		tfEndAmount.setColumns(10);
		
		JLabel lblPrincipal = new JLabel("Principal:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrincipal, 3, SpringLayout.NORTH, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, lblPrincipal, -6, SpringLayout.WEST, tfPrincipal);
		add(lblPrincipal);
		
		JLabel lblRate = new JLabel("Rate:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRate, 3, SpringLayout.NORTH, tfRate);
		springLayout.putConstraint(SpringLayout.EAST, lblRate, -6, SpringLayout.WEST, tfRate);
		add(lblRate);
		
		JLabel lblInterest = new JLabel("Interest:");
		springLayout.putConstraint(SpringLayout.NORTH, lblInterest, 3, SpringLayout.NORTH, tfInterest);
		springLayout.putConstraint(SpringLayout.EAST, lblInterest, -6, SpringLayout.WEST, tfInterest);
		add(lblInterest);
		
		JLabel lblEndAmount = new JLabel("End Amount:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndAmount, 3, SpringLayout.NORTH, tfEndAmount);
		springLayout.putConstraint(SpringLayout.EAST, lblEndAmount, -6, SpringLayout.WEST, tfEndAmount);
		add(lblEndAmount);
		
		tfPrincipalResult = new JTextField();
		tfPrincipalResult.setBackground(SystemColor.window);
		springLayout.putConstraint(SpringLayout.EAST, tfPrincipalResult, -69, SpringLayout.EAST, this);
		tfPrincipalResult.setBorder(null);
		tfPrincipalResult.setEditable(false);
		tfPrincipalResult.setText("null");
		add(tfPrincipalResult);
		tfPrincipalResult.setColumns(10);
		
		tfRateResult = new JTextField();
		tfRateResult.setBackground(SystemColor.window);
		springLayout.putConstraint(SpringLayout.NORTH, tfRateResult, 203, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, tfPrincipalResult, -3, SpringLayout.NORTH, tfRateResult);
		springLayout.putConstraint(SpringLayout.EAST, tfRateResult, 0, SpringLayout.EAST, tfPrincipalResult);
		springLayout.putConstraint(SpringLayout.WEST, tfRateResult, 0, SpringLayout.WEST, tfPrincipal);
		tfRateResult.setBorder(null);
		tfRateResult.setEditable(false);
		tfRateResult.setText("null");
		add(tfRateResult);
		tfRateResult.setColumns(10);
		
		tfInterestResult = new JTextField();
		tfInterestResult.setBackground(SystemColor.window);
		springLayout.putConstraint(SpringLayout.NORTH, tfInterestResult, 6, SpringLayout.SOUTH, tfRateResult);
		springLayout.putConstraint(SpringLayout.WEST, tfInterestResult, 0, SpringLayout.WEST, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, tfInterestResult, 0, SpringLayout.EAST, tfPrincipalResult);
		tfInterestResult.setBorder(null);
		tfInterestResult.setEditable(false);
		tfInterestResult.setText("null");
		add(tfInterestResult);
		tfInterestResult.setColumns(10);
		
		tfEndAmountResult = new JTextField();
		tfEndAmountResult.setBackground(SystemColor.window);
		springLayout.putConstraint(SpringLayout.WEST, tfEndAmountResult, 0, SpringLayout.WEST, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, tfEndAmountResult, 0, SpringLayout.EAST, tfPrincipalResult);
		tfEndAmountResult.setBorder(null);
		tfEndAmountResult.setEditable(false);
		tfEndAmountResult.setText("null");
		add(tfEndAmountResult);
		tfEndAmountResult.setColumns(10);
		
		JLabel lblPrincipalResult = new JLabel("Principal:");
		springLayout.putConstraint(SpringLayout.WEST, tfPrincipalResult, 6, SpringLayout.EAST, lblPrincipalResult);
		springLayout.putConstraint(SpringLayout.NORTH, lblPrincipalResult, 0, SpringLayout.NORTH, tfPrincipalResult);
		springLayout.putConstraint(SpringLayout.EAST, lblPrincipalResult, 0, SpringLayout.EAST, lblPrincipal);
		add(lblPrincipalResult);
		
		JLabel lblRateResult = new JLabel("Rate:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRateResult, 0, SpringLayout.NORTH, tfRateResult);
		springLayout.putConstraint(SpringLayout.EAST, lblRateResult, 0, SpringLayout.EAST, lblPrincipal);
		add(lblRateResult);
		
		JLabel lblInterestResult = new JLabel("Interest:");
		springLayout.putConstraint(SpringLayout.NORTH, lblInterestResult, 6, SpringLayout.SOUTH, lblRateResult);
		springLayout.putConstraint(SpringLayout.EAST, lblInterestResult, 0, SpringLayout.EAST, lblPrincipal);
		add(lblInterestResult);
		
		JLabel lblEndAmountResult = new JLabel("End Amount:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndAmountResult, 0, SpringLayout.NORTH, tfEndAmountResult);
		springLayout.putConstraint(SpringLayout.EAST, lblEndAmountResult, 0, SpringLayout.EAST, lblPrincipal);
		add(lblEndAmountResult);
		
		JLabel lblTime = new JLabel("Time:");
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 3, SpringLayout.NORTH, tfPrincipal);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 6, SpringLayout.EAST, tfPrincipal);
		add(lblTime);
		
		tfTime = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tfTime, 0, SpringLayout.NORTH, tfPrincipal);
		springLayout.putConstraint(SpringLayout.WEST, tfTime, 6, SpringLayout.EAST, lblTime);
		tfTime.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateData();
			}
		});
		add(tfTime);
		tfTime.setColumns(10);
		
		tfTimeResult = new JTextField();
		tfTimeResult.setBackground(SystemColor.window);
		springLayout.putConstraint(SpringLayout.NORTH, tfEndAmountResult, 6, SpringLayout.SOUTH, tfTimeResult);
		springLayout.putConstraint(SpringLayout.NORTH, tfTimeResult, 6, SpringLayout.SOUTH, tfInterestResult);
		springLayout.putConstraint(SpringLayout.WEST, tfTimeResult, 0, SpringLayout.WEST, tfPrincipal);
		springLayout.putConstraint(SpringLayout.EAST, tfTimeResult, 0, SpringLayout.EAST, tfPrincipalResult);
		tfTimeResult.setEditable(false);
		tfTimeResult.setBorder(null);
		tfTimeResult.setText("null");
		add(tfTimeResult);
		tfTimeResult.setColumns(10);
		
		JLabel lblTimeResult = new JLabel("Time:");
		springLayout.putConstraint(SpringLayout.NORTH, lblTimeResult, 0, SpringLayout.NORTH, tfTimeResult);
		springLayout.putConstraint(SpringLayout.EAST, lblTimeResult, 0, SpringLayout.EAST, lblPrincipal);
		add(lblTimeResult);
	}

	/**
	 * 
	 */
	protected void updateData() {
		clearFields();
		solvePrincipal();
		solveRate();
		solveInterest();
		solveTime();
		solveEndAmount();
	}

	/**
	 * 
	 */
	private void solveEndAmount() {
		// A = P + I
		if(!Field.isFieldNull(tfPrincipal) && !Field.isFieldNull(tfInterest)) {
			double endAmount = Field.gd(tfPrincipal) + Field.gd(tfInterest);
			tfEndAmountResult.setText(endAmount + "");
		}
	}

	/**
	 * 
	 */
	private void clearFields() {
		tfPrincipalResult.setText("null");
		tfRateResult.setText("null");
		tfInterestResult.setText("null");
		tfEndAmountResult.setText("null");
		tfTimeResult.setText("null");
	}

	/**
	 * 
	 */
	private void solveTime() {
		// t = I / (Pr)
		if(!Field.isFieldNull(tfInterest) && !Field.isFieldNull(tfPrincipal) && !Field.isFieldNull(tfRate)) {
			double time = Field.gd(tfInterest) / (Field.gd(tfPrincipal) * Field.gd(tfRate));
			double endAmount = Field.gd(tfPrincipal) + Field.gd(tfInterest);
			tfTimeResult.setText(time + "");
			tfEndAmountResult.setText(endAmount + "");
		}
	}

	/**
	 * 
	 */
	private void solveInterest() {
		// I = Prt
		if(!Field.isFieldNull(tfPrincipal) && !Field.isFieldNull(tfRate)) {
			double time = Field.isFieldNull(tfTime) ? 1 : Field.gd(tfTime);
			double interest = Field.gd(tfPrincipal) * Field.gd(tfRate) * time;
			double endAmount = Field.gd(tfPrincipal) + interest;
			tfInterestResult.setText(interest + "");
			tfEndAmountResult.setText(endAmount + "");
		}
		
		// I = A - P
		if(!Field.isFieldNull(tfEndAmount) && !Field.isFieldNull(tfPrincipal)) {
			double endAmount = Field.gd(tfEndAmount) - Field.gd(tfPrincipal);
			tfInterestResult.setText(endAmount + "");
		}
	}

	/**
	 * 
	 */
	private void solveRate() {
		// r = I / (Pt)
		if(!Field.isFieldNull(tfInterest) && !Field.isFieldNull(tfPrincipal)) {
			double time = Field.isFieldNull(tfTime) ? 1 : Field.gd(tfTime);
			double rate = Field.gd(tfInterest) / (Field.gd(tfPrincipal) * time);
			double endAmount = Field.gd(tfPrincipal) + Field.gd(tfInterest);
			tfRateResult.setText(rate + "");
			tfEndAmountResult.setText(endAmount + "");
		}
	}

	/**
	 * 
	 */
	private void solvePrincipal() {
		// P = I / (rt)
		if(!Field.isFieldNull(tfInterest) && !Field.isFieldNull(tfInterest)) {
			double time = Field.isFieldNull(tfTime) ? 1 : Field.gd(tfTime);
			double principal = Field.gd(tfInterest) / (Field.gd(tfRate) * time);
			double endAmount = principal + Field.gd(tfInterest);
			tfPrincipalResult.setText(principal + "");
			tfEndAmountResult.setText(endAmount + "");
		}
		
		// P = A - I
		if(!Field.isFieldNull(tfEndAmount) && !Field.isFieldNull(tfInterest)) {
			double endAmount = Field.gd(tfEndAmount) - Field.gd(tfInterest);
			tfPrincipalResult.setText(endAmount + "");
		}
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
