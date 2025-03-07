package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSimpleTaskDialog extends GeneralPurposeDialog{

    private JTextField startHour, endHour;
    private JLabel startHourLabel, endHourLabel;

    public AddSimpleTaskDialog(ProjectManager manager, Display owner) {
        super(manager, owner);
        startHour = new JTextField();
        endHour = new JTextField();
        startHourLabel = new JLabel("Start Hour");
        endHourLabel = new JLabel("End Hour");

        super.contentPanel.setLayout(new GridLayout(2, 2));
        super.contentPanel.add(startHourLabel);
        super.contentPanel.add(startHour);
        super.contentPanel.add(endHourLabel);
        super.contentPanel.add(endHour);

        setDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            try{
                manager.addTaskSimple(Integer.parseInt(startHour.getText()), Integer.parseInt(endHour.getText()));
                dispose();
            }
            catch (Exception ex){
                new ErrorMessage(ex.getMessage(), this);
            }
        }
        if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
