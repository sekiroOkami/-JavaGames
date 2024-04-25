package com.components;

import javax.swing.*;
import java.awt.*;

public class LogInDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private boolean canceled = false;
    private JTextField ipAddressField = new JTextField(2);
    private JTextField userNameField = new JTextField(2);

    public LogInDialog(String appName) {
        setTitle("Log in to: " + appName);
        initGui();

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
    }
}
