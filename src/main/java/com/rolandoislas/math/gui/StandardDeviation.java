package com.rolandoislas.math.gui;

import com.rolandoislas.math.gui.panel.ProcessList;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.util.List;

/**
 * @author Rolando Islas
 */
public class StandardDeviation implements ApplicationState {


    private static final int ID = 12;
    private static final String DISPLAY_NAME = "Standard Deviation";
    private static final int OUTPUT_FIELDS = 3;
    private ProcessList panel;
    private List<Double> list;
    private double average;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Container initialize(StateBasedApplication sba, JFrame frame) {
        panel = new ProcessList(DISPLAY_NAME, OUTPUT_FIELDS);
        setFieldNames();
        setFieldEvents();
        return panel;
    }

    private void setFieldEvents() {
        panel.getField(0).addCaretListener(e -> doFieldComputations());
    }

    private void doFieldComputations() {
        list = panel.getList();
        if(list != null) {
            calculateMean();
            calculateDeviation();
        }
    }

    private void calculateDeviation() {
        double total = 0;
        for(double number : list) {
            total += Math.pow(number - average, 2);
        }
        double population = Math.sqrt(total / list.size());
        double sample = Math.sqrt(total / (list.size() - 1));
        panel.setFieldText(2, population);
        panel.setFieldText(3, sample);
    }

    private void calculateMean() {
        double total = 0;
        for (double number : list) {
            total += number;
        }
        average = total / list.size();
        panel.setFieldText(1, average);
    }

    private void setFieldNames() {
        panel.setLabelText(0, "Mean");
        panel.setLabelText(1, "Standard Deviation (Population)");
        panel.setLabelText(2, "Standard Deviation (Sample)");
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
