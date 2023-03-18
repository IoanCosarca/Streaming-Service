package com.example.demo.Repository;

import com.example.demo.Model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByEmail(String email);

    void deleteByUserID(Long userID);
}
