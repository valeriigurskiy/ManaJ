package client;

import lombok.Getter;

@Getter
public enum Settings {
    INSTANCE;

    private Integer port = 1293;

    private String address = "http://localhost";

    private boolean logging;

    public Settings port(int port) {
        this.port = port;
        return this;
    }

    public Settings address(String address) {
        this.address = address;
        return this;
    }

    public Settings logging(boolean logging) {
        this.logging = logging;
        return this;
    }
}
