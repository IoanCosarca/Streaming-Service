package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls the operations on the Client table, not how they are implemented.
 */
@RestController
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }

    /**
     * Returns all the Clients in the table.
     * @return List[Client]
     */
    @GetMapping("/getClients")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    /**
     * Returns the Client with the specified id.
     * @param id search criteria
     * @return Client
     */
    @GetMapping("/getClientByID/{id}")
    public Client getClientByID(@PathVariable Long id) {
        return clientService.getClientByID(id);
    }

    /**
     * Returns the Client with the specified email.
     * @param email search criteria
     * @return Client
     */
    @GetMapping("/getClientByEmail/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    /**
     * Returns all the Clients that have a certain age.
     * @param age search criteria
     * @return List[Client]
     */
    @GetMapping("/getClientsByAge/{age}")
    public List<Client> getClientsByAge(@PathVariable int age) {
        return clientService.getClientsByAge(age);
    }

    /**
     * Returns all the Clients that live in a certain country.
     * @param country search criteria
     * @return List[Client]
     */
    @GetMapping("/getClientsByCountry/{country}")
    public List<Client> getClientsByCountry(@PathVariable String country) {
        return clientService.getClientsByCountry(country);
    }

    /**
     * Adds a new Client to the database.
     * @param client the new instance to be added in the table
     */
    @PostMapping("/addClient")
    public void addClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    /**
     * Updates the details of a Client in the database.
     * @param client the instance containing all the modified values to be added in the database
     */
    @PutMapping("/updateClient")
    public void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    /**
     * Deletes the Client with the specified id.
     * @param id delete criteria
     */
    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteByID(id);
    }
}
