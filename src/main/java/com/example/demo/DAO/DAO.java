package com.example.demo.DAO;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(Long id);
}
