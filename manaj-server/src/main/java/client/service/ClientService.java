package client.service;

import client.dto.PCInfo;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ClientService {
    @Getter
    private final static Map<String, PCInfo> clients = new HashMap<>();

    public static void saveClient(PCInfo pcInfo) {
        clients.put("1", pcInfo);
    }

}
