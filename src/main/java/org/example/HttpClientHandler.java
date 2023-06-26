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
                return AlbergoManager.getInstance().findMoreExpensiveSuite();

            default:return "bad request";
        }


    }

    /*
        public void handle(HttpExchange t) throws IOException {
            InputStream is = t.getRequestBody();

            URI uri = t.getRequestURI();
            String query = uri.getQuery();

            String s = read(is); // .. read the request body

            String response = "<!doctype html>\n" +
                    "<html lang=en>\n" +
                    "<head>\n" +
                    "<meta charset=utf-8>\n" +
                    "<title>MyJava Sample</title>\n" +
                    "</head>\n" +
                    "<body>\n" +

                    "</br>The content:" +
                    "</br>\n" +
                    s +

                    "</br>query:" +
                    "</br>\n" +
                    query +
                    "</body>\n" +
                    "</html>\n";

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    /*

     */
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
