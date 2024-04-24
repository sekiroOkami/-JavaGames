package com.components.demo;

import javax.swing.*;
import java.awt.*;

public class RootPaneExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Root Pane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Hello, World!"));

        // Get the root pane of the frame
        JRootPane rootPane = frame.getRootPane();

        // Add the panel to the content pane of the root pane
        rootPane.getContentPane().add(panel);

        // Set size and make frame visible
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
