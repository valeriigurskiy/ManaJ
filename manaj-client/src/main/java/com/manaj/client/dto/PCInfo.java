package com.manaj.client.dto;

import java.util.List;
import java.util.Map;

public record PCInfo(
        String platform,
        String pcModel,
        String availableMemory,
        String totalMemory,
        String osFamily,
        String osVersion,
        int osBitness,
        long osStartTime,
        long clientUpTime,
        Map<String, String> envs,
        List<GraphicCard> graphicCards,
        List<Service> services,
        List<Process> processes
) {

    public static final class PCInfoBuilder {
        private String platform;
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

        private PCInfoBuilder() {
        }

        public static PCInfoBuilder builder() {
            return new PCInfoBuilder();
        }

        public PCInfoBuilder platform(String platform) {
            this.platform = platform;
            return this;
        }

        public PCInfoBuilder pcModel(String pcModel) {
            this.pcModel = pcModel;
            return this;
        }

        public PCInfoBuilder availableMemory(String availableMemory) {
            this.availableMemory = availableMemory;
            return this;
        }

        public PCInfoBuilder totalMemory(String totalMemory) {
            this.totalMemory = totalMemory;
            return this;
        }

        public PCInfoBuilder osFamily(String osFamily) {
            this.osFamily = osFamily;
            return this;
        }

        public PCInfoBuilder osVersion(String osVersion) {
            this.osVersion = osVersion;
            return this;
        }

        public PCInfoBuilder osBitness(int osBitness) {
            this.osBitness = osBitness;
            return this;
        }

        public PCInfoBuilder osStartTime(long osStartTime) {
            this.osStartTime = osStartTime;
            return this;
        }

        public PCInfoBuilder clientUpTime(long clientUpTime) {
            this.clientUpTime = clientUpTime;
            return this;
        }

        public PCInfoBuilder envs(Map<String, String> envs) {
            this.envs = envs;
            return this;
        }

        public PCInfoBuilder graphicCards(List<GraphicCard> graphicCards) {
            this.graphicCards = graphicCards;
            return this;
        }

        public PCInfoBuilder services(List<Service> services) {
            this.services = services;
            return this;
        }

        public PCInfoBuilder processes(List<Process> processes) {
            this.processes = processes;
            return this;
        }

        public PCInfo build() {
            return new PCInfo(platform,
                    pcModel,
                    availableMemory,
                    totalMemory,
                    osFamily,
                    osVersion,
                    osBitness,
                    osStartTime,
                    clientUpTime,
                    envs,
                    graphicCards,
                    services,
                    processes);
        }
    }
}