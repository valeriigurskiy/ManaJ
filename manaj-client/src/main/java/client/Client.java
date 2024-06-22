package client;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Client {

    private static final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(5);

    private Client() {

    }

    public static void init() {
        Process process = new Process();
        scheduler.scheduleAtFixedRate(process, 0, 1, TimeUnit.SECONDS);
        System.out.println("ManaJ client started");
    }
}