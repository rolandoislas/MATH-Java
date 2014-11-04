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
    private static final int OUTPUT_FIELDS = 1;
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
        panel.getField(0).addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                doFieldComputations();
            }
        });
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
        total /= list.size();
        total = Math.sqrt(total);
        panel.setFieldText(1, total);
    }

    private void calculateMean() {
        double total = 0;
        for(double number : list) {
            total += number;
        }
        average = total / list.size();
    }

    private void setFieldNames() {
        panel.setLabelText(0, "Standard Deviation");
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
