package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;

public class HttpClientHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream is = exchange.getRequestBody();
            URI uri = exchange.getRequestURI();
            String method = exchange.getRequestMethod();
            String requestBody;

            if (method.equals("POST")) {
                requestBody = read(is); // Read the request body
            } else {
                requestBody = uri.getQuery();
            }

            System.out.println(requestBody);

            String response = process(requestBody);

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            // Handle the error and send a custom response
            String errorResponse = "An error occurred: " + "Bad request";
            exchange.sendResponseHeaders(200, errorResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(errorResponse.getBytes());
            os.close();
        }
    }


    private String process( String body){

        if (body.length() == 0)
            return "no data\n";


        System.out.println("body : "+body);

        switch (body){
            case "all":
                return AlbergoManager.getInstance().toHtmlAll(AlbergoManager.getInstance().getAlberghiList());
            case "all_sorted":
                AlbergoManager.getInstance().sortAlbByName();
                return AlbergoManager.getInstance().toHtmlAll(AlbergoManager.getInstance().getAlberghiListSorted());
            case "more_expensive_suite":
                return AlbergoManager.getInstance().toHtmlSingle(AlbergoManager.getInstance().findMoreExpensiveSuite());

            default:return "bad request";
        }


    }

    private String read(InputStream is) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is));

        System.out.println("\n");
        String received = "";
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            received += s;

        }
        return received;
    }

}
