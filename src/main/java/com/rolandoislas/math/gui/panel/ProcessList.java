package com.rolandoislas.math.gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.*;

import javax.swing.*;

import com.rolandoislas.math.gui.button.ButtonHome;

/**
 * @author Rolando Islas
 */
public class ProcessList extends JPanel {

	private static final long serialVersionUID = 1L;
    private int inputFields;
    private final int outputFields;
    private final String title;
	private Map<Integer, JLabel> mapLabel = new HashMap<Integer, JLabel>();
	private Map<Integer, JTextField> mapTextField = new HashMap<Integer, JTextField>();

    public ProcessList(String title, int inputFields, int outputFields) {
        this.title = title;
        this.inputFields = inputFields;
        this.outputFields = outputFields;
        createComponents();
    }

    public ProcessList(String title, int outputFields) {
        this(title, 0, outputFields);
    }

    private void createComponents() {
    	SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        
        ButtonHome homeButton = new ButtonHome();
		add(homeButton);
        
        JLabel lblTitle = new JLabel(title);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(lblTitle);
        
        JTextField inputField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, inputField, 25, SpringLayout.SOUTH, lblTitle);
        springLayout.putConstraint(SpringLayout.WEST, inputField, 60, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, inputField, 178, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, inputField, 557, SpringLayout.WEST, this);
        inputField.setColumns(10);
        mapTextField.put(0, inputField);
        add(mapTextField.get(0));

        for(int i = 0; i < inputFields; i++) {
            JLabel label = new JLabel();
            springLayout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.SOUTH, (i==0) ? inputField : mapLabel.get(i - 1));
            springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, inputField);
            mapLabel.put(i, label);
            add(mapLabel.get(i));

            JTextField textField = new JTextField();
            springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, label);
            springLayout.putConstraint(SpringLayout.SOUTH, textField, 3, SpringLayout.SOUTH, label);
            textField.setColumns(10);
            mapTextField.put(i + 1, textField);
            add(mapTextField.get(i + 1));
        }
        
        for(int i = inputFields; i < inputFields + outputFields; i++) {
	        JLabel label = new JLabel();
	        springLayout.putConstraint(SpringLayout.NORTH, label, (i==0) ? 54 : 10, SpringLayout.SOUTH, (i==0) ? inputField : mapLabel.get(i - 1));
	        springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, inputField);
	        mapLabel.put(i, label);
	        add(mapLabel.get(i));
	        
	        JTextField textField = new JTextField();
	        textField.setText("null");
	        springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, label);
	        springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, label);
	        textField.setColumns(200);
			textField.setBackground(new Color(238, 238, 238));
			textField.setEditable(false);
			textField.setBorder(null);
	        mapTextField.put(i + 1, textField);
	        add(mapTextField.get(i + 1));
        }
    }

	public void setLabelText(int key, String text) {
		mapLabel.get(key).setText(text);
	}

	public JTextField getField(int key) {
		return mapTextField.get(key);
	}

	public void setFieldText(int key, String text) {
		mapTextField.get(key).setText(text);
	}
	
	public void setFieldsNull() {
		for(int i = 1; i < outputFields + 1; i++) {
			setFieldText(i, "null");
		}
	}

	public void setFieldText(int key, Double value) {
		setFieldText(key, value + "");
	}

    public List<Double> getList() {
        String rawList = getField(0).getText();
        List<String> stringList = Arrays.asList(rawList.split("\\s*,\\s*"));
        for (String string : stringList) {
            try {
                Double.parseDouble(string);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        List<Double> list = new ArrayList<Double>();
        for(String string : stringList) {
            list.add(Double.parseDouble(string));
        }
        Collections.sort(list);
        return list;
    }

}
