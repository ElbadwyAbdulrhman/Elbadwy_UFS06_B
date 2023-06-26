package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{
    private static Socket clientSocket = null;
    private PrintWriter out = null; // allocate to write answer to client.

    public ClientHandler(Socket clientSocket) {
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
        /*- "all"
    - "all_sorted"
- "more_expensive_suite"*/
        String s = "";
        try {
            while ((s = in.readLine()) != null) {
                System.out.println(clientSocket+" : "+s);
                if(s.equals("all")){
                    out.println(Garage.getInstance().toJson(Garage.getInstance().getCarList()));
                } else if (s.equals("somma")) {
                    out.println("somma delle macchine nel garage : "+Garage.getInstance().somma());
                } else if (s.equals("more_expensive")) {
                    out.println("la machcina piu costosa e : "+Garage.getInstance().findMostExpensiveCar().toString());
                } else if (s.equals("all_sorted")) {
                    Garage.getInstance().sortCarsByBrand();
                    out.println(Garage.getInstance().toJson(Garage.getInstance().getCarListSorted()));

                } else
                    out.println("wrong command");
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