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

    @GetMapping("/getClientByID/{id}")
    public Client getClientByID(@PathVariable Long id) {
        return clientService.getClientByID(id);
    }

    @GetMapping("/getClientByEmail/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/getClientsByAge/{age}")
    public List<Client> getClientsByAge(@PathVariable int age) {
        return clientService.getClientsByAge(age);
    }

    @GetMapping("/getClientsByCountry/{country}")
    public List<Client> getClientsByCountry(@PathVariable String country) {
        return clientService.getClientsByCountry(country);
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @PutMapping("/updateClient")
    public void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    @DeleteMapping("/deleteClient")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteByID(id);
    }
}
