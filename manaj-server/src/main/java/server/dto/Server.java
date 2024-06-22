package server.dto;

import client.dto.PCInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public final class Server {
    private UUID uuid;

    private int port;

    private int nodesCount;

    private long uptime;

    private long startTime;

    private List<PCInfo> allClients;
}