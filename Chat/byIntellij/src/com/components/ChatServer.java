package com.components;

import com.components.demo.LookAndFeelUtils;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.nio.channels.ServerSocketChannel;
import java.util.logging.Logger;

public class ChatServer extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private JTextArea logArea = new JTextArea();
    private JButton startButton = new JButton("Start");
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT_NUMBER = 63458;

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    public static void main(String[] args) {
        LookAndFeelUtils.setCrossPlatformLookAndFeel();
        SwingUtilities.invokeLater(
                () -> new ChatServer()
        );
    }

    public ChatServer() {
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

        // Scroll
        JScrollPane scrollPane = new JScrollPane(logArea);
        mainPanel.add(scrollPane);
        DefaultCaret caret = (DefaultCaret) logArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // button
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.PAGE_END);
        startButton.addActionListener(
                e -> startServer()
        );
        buttonPanel.add(startButton);
        this.getRootPane().setDefaultButton(startButton);

        // listeners
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    stop();
                    System.exit(0);
                }
            }
        );

    }

    private void startServer() {
        startButton.setText("Stop");
        new Thread(this).start();
    }

    private void stop() {
        if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
                log("Unable to close the server connection.");
                log(e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        log("The server is running.");
        logger.info("The server is running");
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(PORT_NUMBER));
            while (serverSocketChannel.isOpen()) {
                log("Starting a new Connection.");
                SocketChannel client = serverSocketChannel.accept();
            }
        } catch (IOException e) {
            log("Exception caught when trying to listen on port: " + PORT_NUMBER +".");
            log(e.getMessage());
        }
    }


    public void log(String message) {
        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss ");
        String timeStamp = dateFormat.format(time);
        logArea.append(timeStamp + message+ "\n");
    }

    private void initFrame() {
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
