package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddTaskToEmployeeDialog extends GeneralPurposeDialog{

    private JTextField taskId, employeeId;
    private JLabel taskIdLabel, employeeIdLabel;

    public AddTaskToEmployeeDialog(ProjectManager manager, Display owner) {
        super(manager, owner);
        taskId = new JTextField(10);
        employeeId = new JTextField(10);
        taskIdLabel = new JLabel("Task ID");
        employeeIdLabel = new JLabel("Employee ID");

        contentPanel.setLayout(new GridLayout(2, 2));
        contentPanel.add(taskIdLabel);
        contentPanel.add(taskId);
        contentPanel.add(employeeIdLabel);
        contentPanel.add(employeeId);
        setDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
        }
        if (e.getSource() == okButton) {
            try{
                manager.addTaskToEmployee(Integer.parseInt(employeeId.getText()), Integer.parseInt(taskId.getText()));
                dispose();
            }catch (Exception ex){
                new ErrorMessage(ex.getMessage(), this);
            }
        }
    }
}
