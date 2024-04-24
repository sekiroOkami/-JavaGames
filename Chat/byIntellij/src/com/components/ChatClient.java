package com.components;

import com.components.demo.LookAndFeelUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ChatClient  {
    public static final long serialVersionUID = 1L;
    JFrame frame = new JFrame();
    private static final int PORT_NUMBER = 63458;

    private String name = "Sekiro";
    private String host = "127.0.0.1";
    private InetSocketAddress serverAddress;


    public static void main(String[] args) {
        LookAndFeelUtils.setCrossPlatformLookAndFeel();
        SwingUtilities.invokeLater(() -> {
            new ChatClient();
        });
    }

    public ChatClient() {
        initGUI();
        new Thread(() -> {
            serverAddress =  new InetSocketAddress(host, PORT_NUMBER);
            try (SocketChannel socketChannel = SocketChannel.open(serverAddress)) {
                Reader channelReader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
                BufferedReader fromServer = new BufferedReader(channelReader);
                String input = fromServer.readLine();
            } catch (ConnectException e) {
                JOptionPane.showMessageDialog(frame, "The server is not running.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Lost connection to the server.");
            }
        }).start();

        // listener
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close();
            }
        });

    }



    private void close() {
        try {
            // Close the socket channel if it's open
            SocketChannel socketChannel = SocketChannel.open(serverAddress);
            if (socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }
        } catch (IOException e) {
            // Handle any exceptions that occur during the close operation
            e.printStackTrace();
        }
    }



    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Chat Client");
        frame.add(titleLabel, BorderLayout.PAGE_START);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(0);
    }


}
