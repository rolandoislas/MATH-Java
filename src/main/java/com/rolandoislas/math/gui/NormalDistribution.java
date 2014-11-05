package com.rolandoislas.math.gui;

import com.rolandoislas.math.gui.panel.SolveVariable;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.Container;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Rolando Islas
 */
public class NormalDistribution implements ApplicationState {

    private static final String DISPLAY_NAME = "Normal Distribution";
    private static final int INPUT_FIELDS = 4;
    private static final int OUTPUT_FIELDS = 1;
    private int ID = 13;
    private SolveVariable panel;
    private List<Double> distributionList = new ArrayList<Double>();
    private static final double[] distributionPercents = {34, 13.5, 2.4, 0.1};

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
            panel.getField(i).addCaretListener(new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    doFieldComputations();
                }
            });
        }
    }

    private void doFieldComputations() {
        panel.setFieldsNull();
        calculatePercent();
    }

    private void calculatePercent() {
        if(panel.hasFieldsPopulated(new int[] {0, 1})) {
            createDistributionList();
            if(panel.hasFieldsPopulated(new int[]{2, 3})) {
                calculateTwoPointsPercent();
            } else if(panel.isFieldPopulated(2)) {
                calculateBelowPointPercent();
            } else if(panel.isFieldPopulated(3)) {
                calculateAbovePointPercent();
            }
        }
    }

    private void createDistributionList() {
        distributionList.clear();
        double mean = panel.getFieldText(0);
        double deviation = panel.getFieldText(1);
        for(int i = -3; i <= 3; i++) {
            distributionList.add(mean + (i * deviation));
        }
    }

    private void calculateAbovePointPercent() {
        double mean = panel.getFieldText(0);
        double above = panel.getFieldText(3);
        double percent;
        if(above < mean) {
            percent = 50 + getTwoPointsPercent(mean, above);
        } else {
            percent = 50 - getTwoPointsPercent(above, mean);
        }
        panel.setFieldText(4, percent);
    }

    private void calculateBelowPointPercent() {
        double mean = panel.getFieldText(0);
        double below = panel.getFieldText(2);
        double percent;
        if(below > mean) {
            percent = 50 + getTwoPointsPercent(below, mean);
        } else {
            percent = 50 - getTwoPointsPercent(mean, below);
        }
        panel.setFieldText(4, percent);
    }

    private void calculateTwoPointsPercent() {
        panel.setFieldText(4, getTwoPointsPercent());
    }

    private double getTwoPointsPercent() {
        return getTwoPointsPercent( -1, -1);
    }

    private double getTwoPointsPercent(double belowSet, double aboveSet) {
        double deviation = panel.getFieldText(1);
        double below = belowSet > 0 ? belowSet : panel.getFieldText(2);
        double above = aboveSet > 0 ? aboveSet : panel.getFieldText(3);
        int[] low = getDeviationEncasingNumber(above);
        int[] high = getDeviationEncasingNumber(below);
        int percentIndex = low[0] >= 3 ? -low[0] + 3 : -low[0] + 2;
        double lowPercent = (distributionList.get(low[1]) - above) / deviation * distributionPercents[percentIndex];
        percentIndex = high[0] >= 3 ? high[0] - 3 : -high[0] + 2;
        double highPercent = (below - distributionList.get(high[0])) / deviation * distributionPercents[percentIndex];
        double betweenPercent = 0;
        for(int i = low[1]; i < high[0]; i++) {
            if(i < 3) {
                betweenPercent += distributionPercents[-i + 2];
            } else if(i > 3) {
                betweenPercent += distributionPercents[i - 3];
            }
        }
        return lowPercent + highPercent + betweenPercent;
    }

    private int[] getDeviationEncasingNumber(double number) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < distributionList.size(); i++) {
            if(distributionList.get(i) == number) {
                list.add(i);
                list.add(i);
                break;
            } else if(distributionList.get(i) < number && distributionList.get(i + 1) > number) {
                list.add(i);
                list.add(i + 1);
                break;
            } else if(i == 0 && distributionList.get(i) > number) {
                list.add(0);
                list.add(0);
                break;
            }
        }
        return new int[] {list.get(0), list.get(1)};
    }

    private void setFieldNames() {
        // input
        panel.setLabelText(0, "Mean");
        panel.setLabelText(1, "Deviation");
        panel.setLabelText(2, "Below");
        panel.setLabelText(3, "Above");
        // output
        panel.setLabelText(4, "Percentage");
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
