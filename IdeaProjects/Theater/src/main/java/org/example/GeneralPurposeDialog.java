package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class GeneralPurposeDialog extends JDialog  implements ActionListener {
    protected JButton okButton = new JButton("OK");
    protected JButton cancelButton = new JButton("Cancel");

    protected ProjectManager manager;

    protected JPanel mainPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    protected JPanel contentPanel = new JPanel();

    protected Display owner;

    public GeneralPurposeDialog(ProjectManager manager, Display owner) {
        this.setModal(true);
        this.setSize(500,500);
        this.resize(500,500);
        this.manager = manager;
        this.owner = owner;
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(contentPanel);
        mainPanel.add(buttonPanel);

        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

//        this.add(mainPanel);
//        this.setSize(500, 300);
//        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        this.setLocation(owner.getLocation().x + 300, owner.getLocation().y + 300);
//        this.setVisible(true);
    }
    protected void setDialog(){
        this.add(this.mainPanel);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocation(owner.getLocation().x + 300, owner.getLocation().y + 300);
        this.setVisible(true);
    }
}
