package com.manaj.server.service.impl;

import com.manaj.server.dto.PCInfo;
import com.manaj.server.dto.PCInfoWrapper;
import com.manaj.server.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ClientServiceImpl implements ClientService {

    private final Map<String, PCInfoWrapper> clients = new HashMap<>();

    public void save(PCInfo pcInfo) {
        if (pcInfo != null) {
            Optional.ofNullable(clients.get(pcInfo.getPcModel()))
                    .ifPresentOrElse(
                            client -> {
                                client.setUp(true);
                                client.setLastUpdateTime(System.currentTimeMillis());
                            },
                            () -> clients.put(pcInfo.getPcModel(), new PCInfoWrapper(pcInfo)));
        }
    }

    public Collection<PCInfoWrapper> getAll() {
        return clients.values().stream()
                .peek(this::checkUpTime)
                .sorted(Comparator.comparing(PCInfoWrapper::getLastUpdateTime))
                .toList();
    }

    private void checkUpTime(PCInfoWrapper client) {
        if (!client.isUp()) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long lastUpdate = TimeUnit.MILLISECONDS.toSeconds(currentTime - client.getLastUpdateTime());

        if (lastUpdate >= 30) {
            client.setUp(false);
        }
    }
}
