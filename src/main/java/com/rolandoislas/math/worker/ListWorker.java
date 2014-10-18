/**
 * 
 */
package com.rolandoislas.math.worker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.rolandoislas.math.util.state.StateBasedApplication;

/**
 * @author Rolando Islas
 *
 */
public class ListWorker extends SwingWorker<Void, Void> {

	private JPanel panel;
	private StateBasedApplication sba;

	/**
	 * @param panel
	 * @param sba
	 */
	public ListWorker(JPanel panel, StateBasedApplication sba) {
		this.panel = panel;
		this.sba = sba;
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		populateList();
		return null;
	}

	/**
	 * 
	 */
	private void populateList() {
		for(int i = 0; i < sba.getStateMap().size(); i++) {
			if(sba.isStateListItem(i)) {
				addButton(sba.getListDisplayName(i), i);
			}
		}
	}

	/**
	 * @param stateID 
	 * @param string 
	 * 
	 */
	private void addButton(String displayName, final int stateID) {
		JButton button = new JButton(displayName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sba.setState(stateID);
			}
		});
		panel.add(button);
	}

}
