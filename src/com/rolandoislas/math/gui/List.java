/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;
import com.rolandoislas.math.worker.ListWorker;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

/**
 * @author Rolando Islas
 *
 */
public class List extends JPanel implements ApplicationState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID = 0;
	private JPanel panel;
	private boolean hasInit = false;
	
	public List() {
		createCompnenets();
	}

	/**
	 * 
	 */
	private void createCompnenets() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblSelectATopic = new JLabel("Select a Topic");
		lblSelectATopic.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblSelectATopic, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblSelectATopic);
		
		panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, lblSelectATopic);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 256, SpringLayout.SOUTH, lblSelectATopic);
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, this);
		add(panel);
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
		if(!hasInit) {
			ListWorker listWorker = new ListWorker(panel, sba);
			listWorker.execute();
			hasInit = true;
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#isListItem()
	 */
	@Override
	public boolean isListItem() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#getListDisplayName()
	 */
	@Override
	public String getListDisplayName() {
		return null;
	}
}
