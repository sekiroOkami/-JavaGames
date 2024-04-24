package com.view;
import com.controller.Controller;
import javax.swing.*;

/**
 * Write a description of class DemoFrame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DemoFrame extends JFrame
{
    private Controller app;
    private DemoPanel panel;
    
    public DemoFrame(Controller app) {
        super();
        this.app = app;
        this.panel = new DemoPanel(this.app);
        setupFrame();
    }
    
    private void setupFrame() {
        this.setTitle("Chat server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }
    
}
