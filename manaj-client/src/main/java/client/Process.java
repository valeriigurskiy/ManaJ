package client;

import client.dto.PCInfo;
import com.google.gson.Gson;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.PowerSource;
import oshi.hardware.Sensors;
import oshi.software.os.OSProcess;
import oshi.software.os.OSService;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Process implements Runnable {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private static final Gson gson = new Gson();

    private static URI URL;

    private static final Settings settings = Settings.INSTANCE;

    private static final String UPDATE_PATH = "/update";

    public Process() {
        String address = settings.getAddress();

        if (settings.getPort() != null) {
            address = address
                    .concat(":")
                    .concat(String.valueOf(settings.getPort()));
        }

        address = address.concat(UPDATE_PATH);
        URL = URI.create(address);
    }

    @Override
    public void run() {
        try {
            sendData();
        } catch (Exception e) {
            System.out.println("Can't process send data: " + e.getMessage());
        }
    }

    private void sendData() {
        String jsonObject = gson.toJson(getData());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URL)
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject))
                .build();

        try {
            HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            if (e.getMessage() == null) {
                System.out.println("Can't reach server");
            }
        }
    }

    private PCInfo getData() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        GlobalMemory memory = hal.getMemory();
        ComputerSystem cs = hal.getComputerSystem();
        CentralProcessor cpu = hal.getProcessor();
        List<NetworkIF> networkIFs = hal.getNetworkIFs();
        Sensors sensors = hal.getSensors();


        OSProcess cp = os.getCurrentProcess();

        String pcModel = cs.getManufacturer().concat(" ").concat(cs.getModel());

        List<PCInfo.GraphicCard> graphicCards = mapGraphicCards(hal);

        List<PCInfo.Process> processes = mapProcesses(si);

        List<PCInfo.Service> services = mapServices(os);

        PCInfo pcInfo = PCInfo.builder()
                .pcModel(pcModel)
                .availableMemory(FormatUtil.formatBytes(memory.getAvailable()))
                .totalMemory(FormatUtil.formatBytes(memory.getTotal()))
                .osBitness(os.getBitness())
                .osFamily(os.getFamily())
                .osVersion(os.getVersionInfo().getVersion())
                .osStartTime(cp.getStartTime())
                .clientUpTime(cp.getUpTime())
                .envs(cp.getEnvironmentVariables())
                .graphicCards(graphicCards)
                .services(services)
                .processes(processes)
                .build();

        return pcInfo;
    }

    private List<PCInfo.GraphicCard> mapGraphicCards(HardwareAbstractionLayer hal) {
        return hal.getGraphicsCards().stream()
                .map(gc -> PCInfo.GraphicCard.builder()
                        .deviceId(gc.getDeviceId())
                        .vendor(gc.getVendor())
                        .vRam(FormatUtil.formatBytes(gc.getVRam()))
                        .build())
                .toList();
    }

    private List<PCInfo.Process> mapProcesses(SystemInfo si) {
        int cpuCount = si.getHardware().getProcessor().getLogicalProcessorCount();
        return si.getOperatingSystem().getProcesses().stream()
                .map(process -> PCInfo.Process.builder()
                        .processID(process.getProcessID())
                        .name(process.getName())
                        .threads(process.getThreadCount())
                        .CPU(String.format("%.1f", 100d * process.getProcessCpuLoadBetweenTicks(process) * cpuCount))
                        .VSZ(FormatUtil.formatBytes(process.getVirtualSize()))
                        .RSS(FormatUtil.formatBytes(process.getResidentSetSize()))
                        .build())
                .toList();
    }

    private List<PCInfo.Service> mapServices(OperatingSystem os) {
        return os.getServices().stream()
                .map(service -> PCInfo.Service.builder()
                        .processID(service.getProcessID())
                        .name(service.getName())
                        .status(service.getState().name())
                        .build())
                .toList();
    }
}