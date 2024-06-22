package server;

import client.dto.PCInfo;
import client.service.ClientService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UpdatesHandler implements HttpHandler {

    private static final ClientService clientService = new ClientService();

    private static final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("New " + httpExchange.getRequestMethod() + " request");
        String response = "";

        if (httpExchange.getRequestMethod().equals("POST")) {
            handlePost(httpExchange);
        } else {
            response = "Not supported request method";
        }

        sendResponse(httpExchange, response);
    }

    private void handlePost(HttpExchange httpExchange) {
        InputStream requestBody = httpExchange.getRequestBody();

        try {
            PCInfo pcInfo = gson.fromJson(new String(requestBody.readAllBytes(), StandardCharsets.UTF_8), PCInfo.class);
            clientService.saveClient(pcInfo);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Can't read message");
        }
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        httpExchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
