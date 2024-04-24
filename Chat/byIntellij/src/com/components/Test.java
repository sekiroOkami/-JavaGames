package com.components;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;


public class Test extends JFrame {
    private JButton startButton = new JButton("Start");
    private static final int PORT_NUMBER = 63458;

    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new ChatServer()
        );
    }

    public Test() {
        initGui();
    }

    private void initGui() {
        TitleLabel label = new TitleLabel("Chat Server");
        this.add(label, BorderLayout.PAGE_START);
        initFrame();
        initPanel();
    }


    private void initPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel, BorderLayout.CENTER);

        // button
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.PAGE_END);
        startButton.addActionListener(
                e -> startServer()
        );
        buttonPanel.add(startButton);
        this.getRootPane().setDefaultButton(startButton);

    }

    private void startServer() {
        if (serverSocketChannel.isOpen()) {
            startButton.setEnabled(false);
        } else {
            startButton.setText("Stop");
        }
    }

    public void start() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(PORT_NUMBER));
            while (true) {
                System.out.println("Start new connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFrame() {
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
