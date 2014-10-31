package com.rolandoislas.math.gui;

import com.rolandoislas.math.gui.panel.ProcessList;
import com.rolandoislas.math.util.state.ApplicationState;
import com.rolandoislas.math.util.state.StateBasedApplication;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rolando Islas
 */
public class MeanMedianModeQuartile implements ApplicationState {

    private static final String DISPLAY_NAME = "Mean, Median, Mode, Quartile";
    private static final int OUTPUT_FIELDS = 9;
    private int ID = 11;
    private ProcessList panel;
	private List<String> stringList;
	private List<Double> list = new ArrayList<Double>();
	private List<Integer> middleNumbers = new ArrayList<Integer>();

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

	protected void doFieldComputations() {
		if(getList()) {
			organizeList();
			panel.setFieldsNull();
			displayOrderedLists();
			calculateHigh();
			calculateLow();
			calculateMean();
			calculateMedian(list, 6);
			calculateMode();
			calculateQuartyileOne();
			calculateQuartileThree();
		}
	}

	private void calculateQuartileThree() {
		ArrayList<Double> quartileList = new ArrayList<Double>();
		if((list.size() % 2) == 0) {
			for(int i = list.size() / 2; i < list.size(); i++) {
				quartileList.add(list.get(i));
			}
		} else if(list.size() > 1) {
			for(int i = ((list.size() - 1) / 2) + 1; i < list.size(); i++) {
				quartileList.add(list.get(i));
			}
		} else {
			quartileList.add(list.get(0));
		}
		calculateMedian(quartileList, 9);
	}

	private void calculateQuartyileOne() {
		ArrayList<Double> quartileList = new ArrayList<Double>();
		if((list.size() % 2) == 0) {
			for(int i = 0; i < list.size() / 2; i++) {
				quartileList.add(list.get(i));
			}
		} else {
			for(int i = 0; i < (list.size() - 1) / 2; i++) {
				quartileList.add(list.get(i));
			}
		}
		calculateMedian(quartileList, 8);
	}

	private void calculateMode() {
		Map<Double, Integer> frequency = new HashMap<Double, Integer>();
	    for (double number: list) {
	        Integer freq = frequency.get(number);
	        frequency.put(number, freq == null ? 1 : freq + 1);  
	    }
	    double mode = 0;
	    double maxFreq = 0;    
	    for (Map.Entry<Double, Integer> entry : frequency.entrySet()) {     
	        double freq = entry.getValue();
	        if (freq > maxFreq) {
	            maxFreq = freq;
	            mode = entry.getKey();
	        }
	    }
	    panel.setFieldText(7, mode);
	}

	private void calculateMedian(List<Double> list, int field) {
		double median;
		middleNumbers.clear();
		if(list.size() > 1 && (list.size() % 2) == 0) {
			int leftMedian = list.size() / 2 - 1;
			middleNumbers.add(leftMedian);
			middleNumbers.add(leftMedian + 1);
			median = (list.get(middleNumbers.get(0)) + list.get(middleNumbers.get(1))) / 2;
		} else if(list.size() > 1) {
			middleNumbers.add(((list.size() - 1) / 2));
			median = list.get(middleNumbers.get(0));
		} else if(list.size() >= 1) {
			median = list.get(0);
		} else {
			median = 0;
		}
		panel.setFieldText(field, median);
	}

	private void calculateMean() {
		double total = 0;
		for(double number : list) {
			total += number;
		}
		double average = total / list.size();
		panel.setFieldText(5, average);
	}

	private void calculateLow() {
		Double low = list.get(0);
		panel.setFieldText(4, low);
	}

	private void calculateHigh() {
		Double high = list.get(list.size() - 1);
		panel.setFieldText(3, high);
	}
	
	private void displayOrderedLists() {
		panel.setFieldText(1, list.toString());
		List<Double> reversedList = new ArrayList<Double>();
		reversedList.addAll(list);
		Collections.reverse(reversedList);
		panel.setFieldText(2, reversedList.toString());
	}

	private void organizeList() {
		list.clear();
		for(String string : stringList) {
			list.add(Double.parseDouble(string));
		}
		Collections.sort(list);
	}

	private boolean getList() {
		String rawList = panel.getField(0).getText();
		stringList = Arrays.asList(rawList.split("\\s*,\\s*"));
		boolean error = false;
		for(int i = 0; i < stringList.size(); i++) {
			try {
				Double.parseDouble(stringList.get(i));
			} catch (NumberFormatException e) {
				error = true;
				break;
			}
		}
		return !error;
	}

	private void setFieldNames() {
		panel.setLabelText(0, "List Low-High");
		panel.setLabelText(1, "List High-Low");
		panel.setLabelText(2, "High");
		panel.setLabelText(3, "Low");
		panel.setLabelText(4, "Mean");
		panel.setLabelText(5, "Median");
		panel.setLabelText(6, "Mode");
		panel.setLabelText(7, "1st Quartile");
		panel.setLabelText(8, "3rd Quartile");
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
