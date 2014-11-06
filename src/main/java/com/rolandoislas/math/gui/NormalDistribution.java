package com.rolandoislas.math.gui;

import com.rolandoislas.math.gui.panel.SolveVariable;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.JFrame;
import java.awt.Container;

/**
 * @author Rolando Islas
 */
public class NormalDistribution implements ApplicationState {

    private static final String DISPLAY_NAME = "Normal Distribution";
    private static final int INPUT_FIELDS = 4;
    private static final int OUTPUT_FIELDS = 3;
    private int ID = 13;
    private SolveVariable panel;
    private org.apache.commons.math3.distribution.NormalDistribution nd;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Container initialize(StateBasedApplication sba, JFrame frame) {
        panel = new SolveVariable(DISPLAY_NAME, INPUT_FIELDS, OUTPUT_FIELDS);
        setFieldNames();
        setFieldEvents();
        return panel;
    }

    private void setFieldEvents() {
        for(int i = 0; i < INPUT_FIELDS; i++) {
            panel.getField(i).addCaretListener(e -> doFieldComputations());
        }
    }

    private void doFieldComputations() {
        panel.setFieldsNull();
        calculatePercent();
    }

    private void calculatePercent() {
        if(panel.hasFieldsPopulated(new int[] {0, 1})) {
            nd = new org.apache.commons.math3.distribution.NormalDistribution(panel.getFieldText(0), panel.getFieldText(1));
            if(panel.hasFieldsPopulated(new int[]{2, 3})) {
                calculateTwoPointsPercent();
            } else if(panel.isFieldPopulated(2)) {
                calculateBelowPointPercent();
            } else if(panel.isFieldPopulated(3)) {
                calculateAbovePointPercent();
            }
        }
    }

    private void calculateAbovePointPercent() {
        double above = panel.getFieldText(3);
        double[] zscore = getZscore(-1, above);
        panel.setFieldText(4, zscore[0]);
        panel.setFieldText(6, (1 - nd.cumulativeProbability(above)) * 100);
    }

    private void calculateBelowPointPercent() {
        double below = panel.getFieldText(2);
        double[] zscore = getZscore(below, -1);
        panel.setFieldText(5, zscore[1]);
        panel.setFieldText(6, nd.cumulativeProbability(below) * 100);
    }

    private void calculateTwoPointsPercent() {
        double below = panel.getFieldText(2);
        double above = panel.getFieldText(3);
        double[] zscore = getZscore(below, above);
        double lowPercent = nd.cumulativeProbability(above);
        double highPercent = nd.cumulativeProbability(below);
        panel.setFieldText(4, zscore[0]);
        panel.setFieldText(5, zscore[1]);
        panel.setFieldText(6, (highPercent - lowPercent) * 100);
    }

    private double[] getZscore(double below, double above) {
        double mean = panel.getFieldText(0);
        double deviation = panel.getFieldText(1);
        double lowZ = (above - mean) / deviation;
        double highZ = (below - mean) / deviation;
        return new double[] {lowZ, highZ};
    }

    private void setFieldNames() {
        // input
        panel.setLabelText(0, "Mean");
        panel.setLabelText(1, "Deviation");
        panel.setLabelText(2, "Below");
        panel.setLabelText(3, "Above");
        // output
        panel.setLabelText(4, "Z-Score Low");
        panel.setLabelText(5, "Z-Score High");
        panel.setLabelText(6, "Percentage");
    }

    @Override
    public boolean isListItem() {
        return true;
    }

    @Override
    public String getListDisplayName() {
        return DISPLAY_NAME;
    }
}
