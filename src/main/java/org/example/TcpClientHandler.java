package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class TcpClientHandler implements Runnable{
    private static Socket clientSocket = null;
    private PrintWriter out = null; // allocate to write answer to client.

    public TcpClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        InetAddress address = clientSocket.getInetAddress();
        System.out.println(address);

        int port = clientSocket.getPort();
        System.out.println(port);

    }
    void write(String s){
        out.println(s);
        out.flush();
    }
    @Override
    public void run() {
        handle();
    }

    Boolean readLoop(BufferedReader in, PrintWriter out) {
        // waits for data and reads it in until connection dies
        // readLine() blocks until the server receives a new line from client
        String s = "";
        try {
            while ((s = in.readLine()) != null) {
                System.out.println(clientSocket+" : "+s);
                switch (s){
                    case "all":
                        out.println(AlbergoManager.getInstance().toJson(AlbergoManager.getInstance().getAlberghiList()));
                        break;
                    case "all_sorted":
                        AlbergoManager.getInstance().sortAlbByName();
                        out.println(AlbergoManager.getInstance().toJson(AlbergoManager.getInstance().getAlberghiListSorted()));
                        break;
                    case "more_expensive_suite":
                        out.println(AlbergoManager.getInstance().findMoreExpensiveSuite());
                        break;
                    default:
                        out.println("wrong command -- commands available : " +
                                "[ all ]" +" "+
                                "[ all_sorted ]" +" "+
                                "[ more_expensive_suite ]");
                }
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }



    void handle() {

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            readLoop(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}