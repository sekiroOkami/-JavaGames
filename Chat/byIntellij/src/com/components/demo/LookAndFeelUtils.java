package com.components.demo;

import javax.swing.*;

public class LookAndFeelUtils {
    public static void setCrossPlatformLookAndFeel() {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
        }
    }
}
