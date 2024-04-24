package com.client;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

public class HitCounterClient {
    private static final Integer PORT_NUMBER = 63457;

    public HitCounterClient() {
        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", PORT_NUMBER);
        try (SocketChannel socketChannel = SocketChannel.open(serverAddress)){
            Reader channelReader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
            BufferedReader fromServer = new BufferedReader(channelReader);
            System.out.println("I am visitor number: " + fromServer.readLine());
            fromServer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new HitCounterClient();
    }
}
