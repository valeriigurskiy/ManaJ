package server;

import client.dto.PCInfo;
import client.service.ClientService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static j2html.TagCreator.*;

public class ClientHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Map<String, PCInfo> clients = ClientService.getClients();
        OutputStream outputStream = httpExchange.getResponseBody();

        PCInfo pcInfo = clients.get("1");

        List<Map.Entry<String, String>> envs = pcInfo.getEnvs()
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().length()))
                .toList();

        String response = html(
                body(
                        h1(pcInfo.getPcModel()),
                        h4("RAM: " + pcInfo.getAvailableMemory() + "/" + pcInfo.getTotalMemory()),
                        h4("OS: " + pcInfo.getOsFamily() + " " + pcInfo.getOsVersion() + " x" + pcInfo.getOsBitness()),
                        h4("OS start time: " + new Date(pcInfo.getOsStartTime())),
                        h4("Client uptime: " + (System.currentTimeMillis() - pcInfo.getClientUpTime())),
                        each(envs, e -> div(
                                e.getKey() + ":" + e.getValue()
                        ).withStyle("font-size: 14px"))
                )
        ).toString();

//        <script src="" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        httpExchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
