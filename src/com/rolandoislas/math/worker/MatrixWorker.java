/**
 * 
 */
package com.rolandoislas.math.worker;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import com.rolandoislas.math.util.state.StateBasedApplication;

/**
 * @author Rolando Islas
 *
 */
public class MatrixWorker extends SwingWorker<Void, Void> {
	
	private static String user = System.getProperty("user.name");
	private static final String LINE_ONE = "Wake up. " + user + "...";
	private static final String LINE_TWO = "The Matrix has you...";
	private static final String LINE_THREE = "Follow the white rabbit.";
	private static final String LINE_FOUR = "Knock, knock, " + user + ".";
	private StateBasedApplication sba;
	private JLabel label;

	public MatrixWorker(StateBasedApplication sba, JLabel label) {
		this.sba = sba;
		this.label = label;
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		switchStateToList();
		doMatrix();
		return null;
	}

	@Override
	protected void done() {
		if(isCancelled()) {
			label.setText("");
		}
	}

	/**
	 * @throws InterruptedException 
	 * 
	 */
	private void doMatrix() throws InterruptedException {
		doTypeString(LINE_ONE, 200, false);
		Thread.sleep(3000);
		doTypeString(LINE_TWO, 150, false);
		Thread.sleep(3000);
		doTypeString(LINE_THREE, 100, false);
		Thread.sleep(3000);
		label.setText(LINE_FOUR);
		Thread.sleep(10000);
		label.setText("");
	}

	/**
	 * @param line 
	 * @param mililis 
	 * @param append
	 * @throws InterruptedException 
	 */
	private void doTypeString(String line, long mililis, boolean append) throws InterruptedException {
		if(!append) {
			label.setText("");
		}
		for(int i = 0; i < line.length(); i++) {
			String previousString = label.getText();
			label.setText(previousString + line.substring(i, i + 1));
			Thread.sleep(mililis);
		}
	}

	/**
	 * 
	 */
	private void switchStateToList() {
		sba.setState(0);
	}

}
