package com.manaj.client.service;

import static com.manaj.client.utils.ClientUtils.validateHost;
import static com.manaj.client.utils.ClientUtils.validatePort;

public class ClientProperties {
    private String host;
    private int port;
    private boolean includeEnvs;
    private boolean includeService;
    private boolean includeProcess;

    public ClientProperties(String host, int port) {
        this();
        validateHost(host);
        validatePort(port);
        this.host = host;
        this.port = port;
    }

    public ClientProperties() {
        this("http://localhost:", 1293, false, false, true);
    }

    public ClientProperties(String host, int port, boolean includeEnvs, boolean includeService, boolean includeProcess) {
        validateHost(host);
        validatePort(port);
        this.host = host;
        this.port = port;
        this.includeEnvs = includeEnvs;
        this.includeService = includeService;
        this.includeProcess = includeProcess;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        validateHost(host);
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        validatePort(port);
        this.port = port;
    }

    public boolean isIncludeEnvs() {
        return includeEnvs;
    }

    public void setIncludeEnvs(boolean includeEnvs) {
        this.includeEnvs = includeEnvs;
    }

    public boolean isIncludeService() {
        return includeService;
    }

    public void setIncludeService(boolean includeService) {
        this.includeService = includeService;
    }

    public boolean isIncludeProcess() {
        return includeProcess;
    }

    public void setIncludeProcess(boolean includeProcess) {
        this.includeProcess = includeProcess;
    }
}