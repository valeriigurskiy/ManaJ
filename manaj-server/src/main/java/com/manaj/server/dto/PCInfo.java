package com.manaj.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class PCInfo {
    @JsonProperty("platform")
    public String platform;
    @JsonProperty("pcModel")
    public String pcModel;
    @JsonProperty("availableMemory")
    public String availableMemory;
    @JsonProperty("totalMemory")
    public String totalMemory;
    @JsonProperty("osFamily")
    public String osFamily;
    @JsonProperty("osVersion")
    public String osVersion;
    @JsonProperty("osBitness")
    public int osBitness;
    @JsonProperty("osStartTime")
    public long osStartTime;
    @JsonProperty("clientUpTime")
    public long clientUpTime;
    @JsonProperty("envs")
    public Map<String, String> envs;
    @JsonProperty("graphicCards")
    public List<GraphicCard> graphicCards;
    @JsonProperty("services")
    public List<Service> services;
    @JsonProperty("processes")
    public List<Process> processes;

    public PCInfo() {
    }

    @Override
    public String toString() {
        return "PCInfo{" +
                "platform='" + platform + '\'' +
                ", pcModel='" + pcModel + '\'' +
                ", availableMemory='" + availableMemory + '\'' +
                ", totalMemory='" + totalMemory + '\'' +
                ", osFamily='" + osFamily + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osBitness=" + osBitness +
                ", osStartTime=" + osStartTime +
                ", clientUpTime=" + clientUpTime +
                ", envs=" + envs +
                ", graphicCards=" + graphicCards +
                ", services=" + services +
                ", processes=" + processes +
                '}';
    }

    public PCInfo(String platform, String pcModel, String availableMemory, String totalMemory, String osFamily, String osVersion,
                  int osBitness, long osStartTime, long clientUpTime, Map<String, String> envs,
                  List<GraphicCard> graphicCards, List<Service> services, List<Process> processes) {
        this.platform = platform;
        this.pcModel = pcModel;
        this.availableMemory = availableMemory;
        this.totalMemory = totalMemory;
        this.osFamily = osFamily;
        this.osVersion = osVersion;
        this.osBitness = osBitness;
        this.osStartTime = osStartTime;
        this.clientUpTime = clientUpTime;
        this.envs = envs;
        this.graphicCards = graphicCards;
        this.services = services;
        this.processes = processes;
    }

    public String getPcModel() {
        return pcModel;
    }

    public void setPcModel(String pcModel) {
        this.pcModel = pcModel;
    }

    public String getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(String availableMemory) {
        this.availableMemory = availableMemory;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getOsBitness() {
        return osBitness;
    }

    public void setOsBitness(int osBitness) {
        this.osBitness = osBitness;
    }

    public long getOsStartTime() {
        return osStartTime;
    }

    public void setOsStartTime(long osStartTime) {
        this.osStartTime = osStartTime;
    }

    public long getClientUpTime() {
        return clientUpTime;
    }

    public void setClientUpTime(long clientUpTime) {
        this.clientUpTime = clientUpTime;
    }

    public Map<String, String> getEnvs() {
        return envs;
    }

    public void setEnvs(Map<String, String> envs) {
        this.envs = envs;
    }

    public List<GraphicCard> getGraphicCards() {
        return graphicCards;
    }

    public void setGraphicCards(List<GraphicCard> graphicCards) {
        this.graphicCards = graphicCards;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public record GraphicCard(String vRam, String deviceId, String vendor) {
    }

    public record Process(int processID, String name, int threads, String CPU, String VSZ, String RSS) {
    }

    public record Service(int processID, String name, String status) {
    }



}