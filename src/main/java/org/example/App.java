package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


// to test against console:
//  /usr/bin/nc 127.0.0.1 1234
// and type in console: server will receive.
// it will NOT block socket (for now..) when timeout.

public class App
{
    static final int portNumber = 1234;



    public static void main( String[] args )
    {
        System.out.println("Server started!");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;
        while(true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            Thread newThred = new Thread(clientHandler);
            newThred.start();
        }
    }

}