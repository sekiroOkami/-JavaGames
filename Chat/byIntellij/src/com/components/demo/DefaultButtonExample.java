package com.components.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefaultButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Default Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JTextField textField = new JTextField(20);
        JButton startButton = new JButton("Start");
        JButton cancelButton = new JButton("Cancel");

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        // Add components to the content pane of the frame
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(textField);
        contentPane.add(buttonPanel);

        // Set the default button
        frame.getRootPane().setDefaultButton(startButton);

        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform start action
                JOptionPane.showMessageDialog(frame, "Start button clicked!");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform cancel action
                JOptionPane.showMessageDialog(frame, "Cancel button clicked!");
            }
        });

        // Set size and make frame visible
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}

