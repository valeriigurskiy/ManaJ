package client.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class PCInfo {
    private String pcModel;

    private String availableMemory;

    private String totalMemory;

    private String osFamily;

    private String osVersion;

    private int osBitness;

    private long osStartTime;

    private long clientUpTime;

    private Map<String, String> envs;

    private List<GraphicCard> graphicCards;

    private List<Service> services;

    private List<Process> processes;

    @Getter
    @Builder
    public static class GraphicCard {
        private String vRam;

        private String deviceId;

        private String vendor;
    }

    @Getter
    @Builder
    public static class Service {
        private int processID;

        private String name;

        private String status;
    }

    @Getter
    @Builder
    public static class Process {
        private int processID;

        private String name;

        private int threads;

        private String CPU;

        private String VSZ;

        private String RSS;
    }
}