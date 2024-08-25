package com.manaj.client.service;

import com.google.gson.Gson;
import com.manaj.client.dto.GraphicCard;
import com.manaj.client.dto.PCInfo;
import com.manaj.client.dto.Process;
import com.manaj.client.dto.Service;
import com.manaj.client.utils.ClientUtils;
import oshi.PlatformEnum;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Client {
    private final ScheduledThreadPoolExecutor scheduler;
    private final HttpClient httpClient;
    private final Gson gson;
    private final URI url;
    private final ClientProperties clientProperties;

    public static void createInstance() {
        new Client(new ClientProperties());
    }

    public static void createInstance(ClientProperties clientProperties) {
        if (clientProperties == null) {
            throw new UnsupportedOperationException("Client properties can't be null");
        }
        new Client(clientProperties);
    }

    private Client(ClientProperties clientProperties) {
        this.scheduler = new ScheduledThreadPoolExecutor(5);
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.clientProperties = clientProperties;

        System.out.println(clientProperties.getHost());
        System.out.println(clientProperties.getPort());

        String host = clientProperties.getHost().concat(String.valueOf(clientProperties.getPort()));
        this.url = URI.create(host.concat("/update"));

        init();
    }

    private void init() {
        scheduler.scheduleAtFixedRate(this::sendData, 0, 5, TimeUnit.SECONDS);
        System.out.println("ManaJ client started");
    }

    private void sendData() {
        PCInfo pcInfoData = getData();
        String jsonObject = gson.toJson(pcInfoData);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject))
                .uri(url)
                .header("Content-Type", "application/json")
                .build();
        System.out.println(jsonObject);
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Can't reach server");
            } else {
                System.out.println(e.getMessage());
            }
        }
    }

    private PCInfo getData() {
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        GlobalMemory memory = hal.getMemory();
        ComputerSystem cs = hal.getComputerSystem();
//        CentralProcessor cpu = hal.getProcessor();
//        List<NetworkIF> networkIFs = hal.getNetworkIFs();
//        Sensors sensors = hal.getSensors();

        OSProcess cp = os.getCurrentProcess();

        String pcModel = cs.getManufacturer().concat(" ").concat(cs.getModel());

        List<GraphicCard> graphicCards = ClientUtils.mapGraphicCards(hal);

        PCInfo.PCInfoBuilder pcInfoBuilder = PCInfo.PCInfoBuilder.builder();

        pcInfoBuilder.platform(SystemInfo.getCurrentPlatform().getName())
                .pcModel(pcModel)
                .availableMemory(FormatUtil.formatBytes(memory.getAvailable()))
                .totalMemory(FormatUtil.formatBytes(memory.getTotal()))
                .osBitness(os.getBitness())
                .osFamily(os.getFamily())
                .osVersion(os.getVersionInfo().getVersion())
                .osStartTime(cp.getStartTime())
                .clientUpTime(cp.getUpTime())
                .graphicCards(graphicCards)
                .build();

        if (clientProperties.isIncludeEnvs()) {
            pcInfoBuilder.envs(cp.getEnvironmentVariables());
        }

        if (clientProperties.isIncludeProcess()) {
            List<Process> processes = ClientUtils.mapProcesses(si);
            pcInfoBuilder.processes(processes);
        }

        if (clientProperties.isIncludeService()) {
            List<Service> services = ClientUtils.mapServices(os);
            pcInfoBuilder.services(services);
        }

        return pcInfoBuilder.build();
    }
}