package com.example.demo.Repository;

import com.example.demo.Model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByEmail(String email);

    List<Client> findAllByAge(int age);

    List<Client> findAllByCountry(String country);

    void deleteByUserID(Long userID);
}
