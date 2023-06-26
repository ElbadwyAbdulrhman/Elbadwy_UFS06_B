package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CombinedServer {
    static final int tcpPort = 1234;
    static final int httpPort = 8000;

    public static void main(String[] args) {
        System.out.println("Server started!");

        // Start TCP server
        Thread tcpThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(tcpPort);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    TcpClientHandler tcpClientHandler = new TcpClientHandler(clientSocket);
                    Thread newThread = new Thread(tcpClientHandler);
                    newThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tcpThread.start();

        // Start HTTP server
        Thread httpThread = new Thread(() -> {
            try {
                HttpServer httpServer = HttpServer.create(new InetSocketAddress(httpPort), 0);
                httpServer.createContext("/", new HttpClientHandler());
                httpServer.setExecutor(null);
                httpServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        httpThread.start();
    }
}
