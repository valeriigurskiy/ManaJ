package com.manaj.server.controller;

import com.manaj.server.dto.PCInfo;
import com.manaj.server.dto.PCInfoWrapper;
import com.manaj.server.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(path = {"", "/"})
    public String getAll(Model model) {
        Collection<PCInfoWrapper> allClients = clientService.getAll();

        allClients.forEach(System.out::println);

        model.addAttribute("clients", allClients);
        return "clients";
    }
}