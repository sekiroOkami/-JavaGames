package com.components.demo;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BorderLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JButton button1 = new JButton("North");
        JButton button2 = new JButton("Center");
        JButton button3 = new JButton("South");

        // Add components to the frame using BorderLayout
        frame.add(button1, BorderLayout.PAGE_START); // North
        frame.add(button2, BorderLayout.CENTER);     // Center
        frame.add(button3, BorderLayout.PAGE_END);   // South

        // Set size and make frame visible
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
