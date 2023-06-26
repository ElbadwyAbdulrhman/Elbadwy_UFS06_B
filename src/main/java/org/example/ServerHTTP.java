package org.example;

// https://docs.oracle.com/en/java/javase/12/docs/api/jdk.httpserver/com/sun/net/httpserver/package-summary.html

// sample invocation:

// POST:
// curl -X POST "http://127.0.0.1:8000" -d "gimmeanswer=please&user=john"

//GET:
// http://127.0.0.1:8000/?gimmeanswer=please&id=100


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerHTTP
{
    public static void main( String[] args ) {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        server.createContext("/", new HttpClientHandler());
        server.setExecutor(null);
        server.start();
    }
}