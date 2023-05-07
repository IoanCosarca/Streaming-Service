package com.example.demo;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Client;
import com.example.demo.Service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientTests {
    @Mock
    private DAO<Client> dao;

    @Test
    void testGetAll()
    {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client();
        Client client2 = new Client();
        clients.add(client1);
        clients.add(client2);
        ClientService clientService = new ClientService(dao);
        clientService.getClients();
        when (dao.getAll()).thenReturn(clients);
        verify(dao).getAll();
    }

    @Test
    void testFindByID()
    {
        Client client = new Client(1L, "Gica", "Pop", "goci@gmail", "1234", 25, "Romania");
        ClientService clientService = new ClientService(dao);
        clientService.getClientByID(client.getUserID());
        when (dao.findByID(client.getUserID())).thenReturn(client);
        verify(dao).findByID(client.getUserID());
    }

    @Test
    void testFindByEmail()
    {
        Client client = new Client(1L, "Gica", "Pop", "goci@gmail", "1234", 25, "Romania");
        ClientService clientService = new ClientService(dao);
        clientService.getClientByEmail(client.getEmail());
        when (dao.findByEmail(client.getEmail())).thenReturn(client);
        verify(dao).findByEmail(client.getEmail());
    }

    @Test
    void testFindByAge()
    {
        List<Client> clients = new ArrayList<>();
        ClientService clientService = new ClientService(dao);
        clientService.getClientsByAge(25);
        when (dao.findAllByAge(25)).thenReturn(clients);
        verify(dao).findAllByAge(25);
    }

    @Test
    void testFindByCountry()
    {
        List<Client> clients = new ArrayList<>();
        ClientService clientService = new ClientService(dao);
        clientService.getClientsByCountry("Romania");
        when (dao.findAllByCountry("Romania")).thenReturn(clients);
        verify(dao).findAllByCountry("Romania");
    }

    @Test
    void testSave()
    {
        Client client = new Client(1L, "Gica", "Pop", "goci@gmail", "1234", 25, "Romania");
        ClientService clientService = new ClientService(dao);
        clientService.saveClient(client);
        verify(dao).save(client);
    }

    @Test
    void testUpdate()
    {
        Client client = new Client(1L, "Gica", "Pop", "goci@gmail", "1234", 25, "Romania");
        ClientService clientService = new ClientService(dao);
        clientService.updateClient(client);
        verify(dao).update(client);
    }

    @Test
    void testDelete()
    {
        ClientService clientService = new ClientService(dao);
        clientService.deleteByID(1L);
        verify(dao).delete(1L);
    }
}
