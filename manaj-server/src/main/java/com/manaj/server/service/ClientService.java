package com.manaj.server.service;

import com.manaj.server.dto.PCInfo;
import com.manaj.server.dto.PCInfoWrapper;

import java.util.Collection;

public interface ClientService {
    void save(PCInfo pcInfo);

    Collection<PCInfoWrapper> getAll();
}
