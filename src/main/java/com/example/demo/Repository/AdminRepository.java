package com.example.demo.Repository;

import com.example.demo.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Override
    List<Admin> findAll();

    Admin findByEmail(String email);

    @Override
    <S extends Admin> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
