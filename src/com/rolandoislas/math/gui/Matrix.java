/**
 * 
 */
package com.rolandoislas.math.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;
import com.rolandoislas.math.worker.MatrixWorker;

import javax.swing.SpringLayout;

import java.awt.Font;

/**
 * @author Rolando Islas
 *
 */
public class Matrix extends JPanel implements ApplicationState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID = 10;
	private static final String DISPLAY_NAME = "Matrix";
	private static final String EXIT = "Exit";
	private JLabel label;
	private MatrixWorker worker;
	private JFrame f;
	
	public Matrix() {
		//createFullscreenFrame();
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
		createFullscreenFrame();
		worker = new MatrixWorker(sba, label);
		worker.execute();
		return new JPanel();
	}

	/**
	 * 
	 */
	private void createFullscreenFrame() {
		f = new JFrame();
		f.getContentPane().setBackground(Color.BLACK);
	    @SuppressWarnings("serial")
		Action exit = new AbstractAction("") {

            @Override
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
            	worker.cancel(true);
            }
	     };
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), EXIT);
        this.getActionMap().put(EXIT, exit);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice dev = env.getDefaultScreenDevice();
        setBackground(Color.BLACK);
        f.setResizable(false);
        f.setUndecorated(true);
        f.getContentPane().add(this);
        f.pack();
        dev.setFullScreenWindow(f);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        label = new JLabel();
        label.setFont(new Font("Monospaced", Font.PLAIN, 24));
        springLayout.putConstraint(SpringLayout.NORTH, label, 50, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, label, 50, SpringLayout.WEST, this);
        label.setForeground(Color.WHITE);
        add(label);
        
        hideMouseCursor();
	}

	/**
	 * 
	 */
	private void hideMouseCursor() {
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		f.getContentPane().setCursor(blankCursor);
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
