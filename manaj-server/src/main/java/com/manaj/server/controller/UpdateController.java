package com.manaj.server.controller;

import com.manaj.server.dto.PCInfo;
import com.manaj.server.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {

    private final ClientService clientService;

    public UpdateController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/update")
    public void update(@RequestBody PCInfo pcInfo) {
        clientService.save(pcInfo);
    }

}
