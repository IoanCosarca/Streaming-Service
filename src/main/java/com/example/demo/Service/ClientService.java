package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<Client> getClientsByAge(int age) {
        return clientRepository.findAllByAge(age);
    }

    public List<Client> getClientsByCountry(String country) {
        return clientRepository.findAllByCountry(country);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteByID(Long id) {
        clientRepository.deleteById(id);
    }
}