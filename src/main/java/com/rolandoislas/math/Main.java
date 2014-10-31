/**
 * M.A.T.H - Mathematics and Thought Helper is a program that contains multiple algebraic
 * expressions that help the user come to a solution for their problem. Occasionally some
 * elaboration is included, but the main purpose is to enable easy checking of ones work.
 */
package com.rolandoislas.math;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import com.rolandoislas.math.data.Constants;
import com.rolandoislas.math.gui.*;
import com.rolandoislas.math.gui.button.ButtonHome;
import com.rolandoislas.math.util.state.StateBasedApplication;

/**
 * @author Rolando Islas
 *
 */
public class Main extends StateBasedApplication {

	private static final String APP_NAME = "MATH";
	private static final int WIDTH = 1148;
	private static final int HEIGHT = 720;


	/**
	 * 
	 */
	public Main() {
		super(APP_NAME + " " + Constants.VERSION);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();
		setPosition(screenWidth / 2 - WIDTH / 2, screenHeight / 2 - HEIGHT / 2);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		initialize();
		addStates();
		setState(0);
		ButtonHome.setSba(this);
	}

	/**
	 * @param args
     * No arguments expected
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					URL test = getClass().getResource("/images/Icon512.jpg");
					Image image = new ImageIcon(test).getImage();
					window.frame.setIconImage(image);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void addStates() {
		addState(new List());
		addState(new LinearGrowth());
		addState(new ExponentialGrowth());
		addState(new Logarithm());
		addState(new InterestSimple());
		addState(new InterestCompound()); // 5
		addState(new InterestCompoundContinuous());
		addState(new Annuity());
		addState(new AnnuityPayout());
		addState(new Loan());
		addState(new Matrix()); // 10
        addState(new MeanMedianModeQuartile());
	}
}
