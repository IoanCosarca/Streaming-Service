package com.example.demo.Service;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that insures the link between the application methods and the database.
 */
@Service
public class ClientService {
    private DAO<Client> dao;

    @Autowired
    public ClientService(DAO<Client> dao)
    {
        this.dao = dao;
    }

    /**
     * Sends the request to get all the Clients from the database.
     * @return List[Client]
     */
    public List<Client> getClients() {
        return dao.getAll();
    }

    /**
     * Sends the request to get the Client with the specified id.
     * @param id - search criteria
     * @return Client
     */
    public Client getClientByID(Long id) {
        return dao.findByID(id);
    }

    /**
     * Sends the request to get the Client with the specified email.
     * @param email - search criteria
     * @return Client
     */
    public Client getClientByEmail(String email) {
        return dao.findByEmail(email);
    }

    /**
     * Sends the request to get all the Clients with the specified age.
     * @param age - search criteria
     * @return List[Client]
     */
    public List<Client> getClientsByAge(int age) {
        return dao.findAllByAge(age);
    }

    /**
     * Sends the request to get all the Clients from a specified country.
     * @param country - search criteria
     * @return List[Client]
     */
    public List<Client> getClientsByCountry(String country) {
        return dao.findAllByCountry(country);
    }

    /**
     * Sends the Client to be added to the database.
     * @param client - the entry to be added
     */
    public void saveClient(Client client) {
        dao.save(client);
    }

    /**
     * Sends the new Client object to update the database.
     * @param client - the entry containing the new information
     */
    public void updateClient(Client client) {
        dao.update(client);
    }

    /**
     * Sends the id of the Client to be deleted from the database.
     * @param id - delete criteria
     */
    public void deleteByID(Long id) {
        dao.delete(id);
    }
}
