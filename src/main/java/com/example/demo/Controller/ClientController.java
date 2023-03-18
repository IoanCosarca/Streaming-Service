package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }

    @GetMapping("/getClients")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/getClient/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/getClients/{age}")
    public List<Client> getClientsByAge(@PathVariable int age) {
        return clientService.getClientsByAge(age);
    }

    @GetMapping("/getClients/{country}")
    public List<Client> getClientsByCountry(@PathVariable String country) {
        return clientService.getClientsByCountry(country);
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @DeleteMapping("/deleteClient")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteByID(id);
    }
}