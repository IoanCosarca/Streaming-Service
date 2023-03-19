package com.example.demo.Service;

import com.example.demo.DAO.ClientDAO;
import com.example.demo.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientDAO dao;

    @Autowired
    public ClientService(ClientDAO dao)
    {
        this.dao = dao;
    }

    public List<Client> getClients() {
        return dao.getAll();
    }

    public Client getClientByID(Long id) {
        return dao.findByID(id);
    }

    public Client getClientByEmail(String email) {
        return dao.findByEmail(email);
    }

    public List<Client> getClientsByAge(int age) {
        return dao.findAllByAge(age);
    }

    public List<Client> getClientsByCountry(String country) {
        return dao.findAllByCountry(country);
    }

    public void saveClient(Client client) {
        dao.save(client);
    }

    public void updateClient(Client client) {
        dao.update(client);
    }

    public void deleteByID(Long id) {
        dao.delete(id);
    }
}
