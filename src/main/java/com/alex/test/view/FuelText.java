package com.alex.test.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class FuelText extends JPanel implements DocumentListener, Fuel {

    private JTextField textField = new JTextField(5);
    private JLabel label;
    private int fuel;

    FuelText(String name) {
        super();
        label = new JLabel(name);

        setLayout(new FlowLayout());

        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new MyNumberFilter());
        label.setLabelFor(textField);
        textField.getDocument().addDocumentListener(this);
        add(label);
        add(textField);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        fuel = Integer.parseInt(textField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            fuel = Integer.parseInt(textField.getText());
        } catch (Exception err) {
            fuel = 0;
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public int getFuel() {
        return fuel;
    }

    class MyNumberFilter extends DocumentFilter {

        public void insertString(FilterBypass fb, int pos, String text, AttributeSet attr) throws BadLocationException {
            try {
                Integer.parseInt(text);

            } catch (Exception e) {
                super.insertString(fb, 0, "", attr);
                return;
            }
            super.insertString(fb, pos, text, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            try {
                Integer.parseInt(text);
            } catch (Exception e) {
                super.replace(fb, offset, length, "", attrs);
                return;
            }
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
