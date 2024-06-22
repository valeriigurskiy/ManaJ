import com.sun.net.httpserver.HttpServer;
import server.ClientHandler;
import server.UpdatesHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerMain {

    static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        int defaultPort = 1293;

        if (args.length != 0) {
            try {
                int port = Integer.parseInt(args[0]);
                if (port < 0 || port > 65535) {
                    throw new IllegalArgumentException("Port can't be less than 0 or greater than 65535");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Specific port not found. Skip...");
            }
        }

        InetSocketAddress localhostAddress = new InetSocketAddress(defaultPort);
        HttpServer httpServer = HttpServer.create(localhostAddress, 0);
        httpServer.setExecutor(executor);

        httpServer.createContext("/update", new UpdatesHandler());
        httpServer.createContext("/clients", new ClientHandler());

        httpServer.start();
        System.out.println("ManaJ server started");
    }
}