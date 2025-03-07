package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddEmployeeDialog extends GeneralPurposeDialog{
    private JTextField nameField;
    private JLabel nameLabel;

    public AddEmployeeDialog(ProjectManager manager, Display owner) {
        super(manager, owner);
        nameField = new JTextField();
        nameLabel = new JLabel();
        nameLabel.setText("Name:");
        super.contentPanel.setLayout(new GridLayout(1, 2));
        super.contentPanel.add(nameLabel);
        super.contentPanel.add(nameField);

        super.setDialog();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.cancelButton) {
            dispose();
        }
        if (e.getSource() == super.okButton) {
            try{
                super.manager.addEmployees(nameField.getText());
                super.owner.operationDone = true;
                dispose();
            }
            catch (Exception ex){
                new ErrorMessage(ex.getMessage(), this);
            }

        }
    }
}
