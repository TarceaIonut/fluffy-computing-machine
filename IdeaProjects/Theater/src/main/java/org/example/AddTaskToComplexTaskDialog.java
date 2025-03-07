package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddTaskToComplexTaskDialog extends GeneralPurposeDialog{

    private JTextField taskId, ComplexTaskId;
    private JLabel taskIdLabel, ComplexTaskLabel;

    public AddTaskToComplexTaskDialog(ProjectManager manager, Display owner) {
        super(manager, owner);
        taskId = new JTextField(10);
        ComplexTaskId = new JTextField(10);
        taskIdLabel = new JLabel("Task ID");
        ComplexTaskLabel = new JLabel("Complex Task ID");

        contentPanel.setLayout(new GridLayout(2, 2));
        contentPanel.add(taskIdLabel);
        contentPanel.add(taskId);
        contentPanel.add(ComplexTaskLabel);
        contentPanel.add(ComplexTaskId);

        setDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
        }
        if (e.getSource() == okButton) {
            try{
                manager.addTaskToTask(Integer.parseInt(ComplexTaskId.getText()), Integer.parseInt(taskId.getText()));
                dispose();
            }catch (Exception ex){
                new ErrorMessage(ex.getMessage(), this);
            }
        }
    }
}
