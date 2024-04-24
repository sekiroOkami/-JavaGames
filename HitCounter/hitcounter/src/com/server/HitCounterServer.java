package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Channels;


public class HitCounterServer {

    private static final Integer PORT_NUMBER = 63457;
    private int count;

    public HitCounterServer() {
        System.out.println("Server is running.");

        // makes this server app 'listen' for client requests on the port it's bound to, connection object
        try (var serverChannel = ServerSocketChannel.open()) {
            // bind the serverSocketChannel to the port you want to run the app
            serverChannel.bind(new InetSocketAddress(PORT_NUMBER));

            // server goes into a permanent loop, waiting for client requests
            while(serverChannel.isOpen()) {
                System.out.println("Start new connection");
                // accept() blocks (just sits there) until a request cones in, and then the method returns a SocketChannel for communicating with the client
                SocketChannel client = serverChannel.accept();

                PrintWriter toClient = new PrintWriter(Channels.newOutputStream(client));
                count++;
                toClient.println(""+count);
                toClient.close(); // close the writer, which will also close the client SocketChannel
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new HitCounterServer();
    }
}
