package com.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LogInDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private boolean canceled = false;
    private JTextField ipAddressField = new JTextField(2);
    private JTextField userNameField = new JTextField(2);

    public LogInDialog(String appName) {
        setTitle("Log in to: " + appName);
        initGui();

        // user must respond to the dialog before doing anything else with the program.
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initGui() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        JLabel ipAddressLabel = new JLabel("IP Address");
        mainPanel.add(ipAddressLabel);
        mainPanel.add(ipAddressField);
        JLabel userNameLabel = new JLabel("User Name");
        mainPanel.add(userNameLabel);
        mainPanel.add(userNameField);

        // button
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.PAGE_END);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(
                e -> {
                    ok();
                }
        );
        mainPanel.add(okButton);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(
                e -> {
                    cancel();
                }
        );
        mainPanel.add(cancelButton);
        getRootPane().setDefaultButton(okButton);

        // listeners
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });


    }

    private void cancel() {
        canceled = true;
        setVisible(false);
    }

    private void ok() {
        canceled = false;
        setVisible(false);
    }
}
