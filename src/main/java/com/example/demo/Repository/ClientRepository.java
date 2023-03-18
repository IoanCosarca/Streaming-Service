package com.example.demo.Repository;

import com.example.demo.Model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByEmail(String email);

    Client findByAge(int age);

    Client findByCountry(String country);

    Client deleteByUserID(Long userID);
}
