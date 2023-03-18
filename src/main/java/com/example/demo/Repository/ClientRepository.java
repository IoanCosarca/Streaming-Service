package com.example.demo.Repository;

import com.example.demo.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(Long aLong);

    Client findByEmail(String email);

    List<Client> findAllByAge(int age);

    List<Client> findAllByCountry(String country);

    @Override
    <S extends Client> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
