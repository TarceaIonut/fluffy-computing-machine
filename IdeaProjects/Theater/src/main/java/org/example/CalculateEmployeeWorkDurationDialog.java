package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CalculateEmployeeWorkDurationDialog extends GeneralPurposeDialog{
    private JTextField idEmployee;
    private JLabel idEmployeeLabel;

    public CalculateEmployeeWorkDurationDialog(ProjectManager manager, Display owner) {
        super(manager, owner);
        idEmployee = new JTextField();
        idEmployeeLabel = new JLabel();
        idEmployeeLabel.setText("id Employee:");
        super.contentPanel.setLayout(new GridLayout(1, 2));
        super.contentPanel.add(idEmployeeLabel);
        super.contentPanel.add(idEmployee);


        super.setDialog();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.cancelButton) {
            dispose();
        }
        if (e.getSource() == super.okButton) {
            try{
                int d = manager.calculateEmployeeWorkDuration(Integer.parseInt(idEmployee.getText()));
                owner.setTextArea("Employee of id = " + idEmployee.getText() + " works for duration = " + d);
                dispose();
            }
            catch (Exception ex){
                new ErrorMessage(ex.getMessage(), this);
            }

        }
    }
}
