package com.manaj.client.utils;

import com.manaj.client.dto.GraphicCard;
import com.manaj.client.dto.Process;
import com.manaj.client.dto.Service;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.util.List;

public class ClientUtils {

    public static void validateHost(String host) {
        if (host == null || host.isEmpty()) {
            throw new UnsupportedOperationException("Host can't be null");
        }
    }

    public static void validatePort(int port) {
        if (port < 0) {
            throw new UnsupportedOperationException("Port must be greater than 0");
        }
        if (port > 65535) {
            throw new UnsupportedOperationException("Port must be less than 65535");
        }
    }

    public static List<GraphicCard> mapGraphicCards(HardwareAbstractionLayer hal) {
        return hal.getGraphicsCards().stream()
                .map(gc -> GraphicCard.GraphicCardBuilder.builder()
                        .deviceId(gc.getDeviceId())
                        .vendor(gc.getVendor())
                        .vRam(FormatUtil.formatBytes(gc.getVRam()))
                        .build())
                .toList();
    }

    public static List<Process> mapProcesses(SystemInfo si) {
        int cpuCount = si.getHardware().getProcessor().getLogicalProcessorCount();
        return si.getOperatingSystem().getProcesses().stream()
                .map(process -> Process.ProcessBuilder.builder()
                        .processID(process.getProcessID())
                        .name(process.getName())
                        .threads(process.getThreadCount())
                        .CPU(String.format("%.1f", 100d * process.getProcessCpuLoadBetweenTicks(process) * cpuCount))
                        .VSZ(FormatUtil.formatBytes(process.getVirtualSize()))
                        .RSS(FormatUtil.formatBytes(process.getResidentSetSize()))
                        .build())
                .toList();
    }

    public static List<Service> mapServices(OperatingSystem os) {
        return os.getServices().stream()
                .map(service -> Service.ServiceBuilder.builder()
                        .processID(service.getProcessID())
                        .name(service.getName())
                        .status(service.getState().name())
                        .build())
                .toList();
    }

}
