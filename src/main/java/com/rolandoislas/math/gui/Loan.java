/**
 * 
 */
package com.rolandoislas.math.gui;

/**
 * @author Rolando Islas
 *
 */
public class Loan extends AnnuityPayout {

	/**
	 * @param displayName
	 */
	public Loan(String displayName) {
		super(displayName);
	}

	private static final int ID = 9;
	private static final String DISPLAY_NAME = "Loan";
	
	public Loan() {
		super(DISPLAY_NAME);
	}

	/* (non-Javadoc)
	 * @see com.rolandoislas.math.util.state.ApplicationState#getID()
	 */
	@Override
	public int getID() {
		return ID;
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
