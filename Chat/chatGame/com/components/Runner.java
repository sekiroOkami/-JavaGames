package com.components;
import com.controller.Controller;
import javax.swing.*;

/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Runner
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> {
                    var app = new Controller();
                    app.start();
            }
        );
    }
}
