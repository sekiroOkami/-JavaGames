package com.components;

import com.components.demo.LookAndFeelUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {
    public static final long serialVersionUID = 1L;
    private static final int PORT_NUMBER = 63458;
    private JFrame frame = new JFrame();
    private String name = "Sekiro";
    private String host = "127.0.0.1";
    private SocketChannel socketChannel;
    private JTextArea chatArea = new JTextArea(20, 20);
    private JTextArea inputArea = new JTextArea(3, 20);
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        LookAndFeelUtils.setCrossPlatformLookAndFeel();
        SwingUtilities.invokeLater(() -> {
            new ChatClient();
        });
    }

    public ChatClient() {
        initGUI();
        executor.submit(
                () -> {
                    var serverAddress = new InetSocketAddress(host, PORT_NUMBER);
                    try {
                        socketChannel = SocketChannel.open(serverAddress);
                        Reader channelReader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
                        BufferedReader fromServer = new BufferedReader(channelReader);
//                        String input = fromServer.readLine();
                        fromServer.close();
                    } catch (ConnectException e) {
                        JOptionPane.showMessageDialog(frame, "The server is not running");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "Lost connection to the server");
                    }
                }
        );
    }

    private void closeConnectionObject() {
        executor.shutdown();
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Chat Client");
        frame.add(titleLabel, BorderLayout.PAGE_START);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        iniPanel();


        // listener
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeConnectionObject();
                System.exit(0);
            }
        });
    }

    private void iniPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // chat area
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        Insets marginInsets = new Insets(3, 3, 3, 3);
        chatArea.setMargin(marginInsets);
        JScrollPane chatScrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(chatScrollPane);

        // input area
        JLabel messageLabel = new JLabel("Type your message here: ");
        mainPanel.add(messageLabel);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setMargin(marginInsets);
        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    send();
                }
            }
        });
        JScrollPane inputScrollPane = new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(inputScrollPane);

        // button panel
        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        buttonPanel.add(sendButton);
        sendButton.addActionListener(
                e -> send()
        );





        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
    }

    private void send() {
        String message = inputArea.getText().trim();
        if (message.length() > 0) {
            chatArea.setText(message);
            inputArea.setText("");
        }
    }

}
