package com.example.demo.DAO;

import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Interface declaring the common operations across every table, thus having a generic class type.
 * @param <T> - generic type for making it easier to implement the interface
 */
public interface DAO<T> {
    /**
     * Method for getting every entry in a table.
     * @return List[Class type]
     */
    List<T> getAll();

    /**
     * Method for adding a new entry in a table.
     * @param t - the new object entry
     */
    void save(T t);

    /**
     * Method for updating an existing entry in a table.
     * @param t - the object containing the new information for the table entry
     */
    void update(T t);

    /**
     * Method for deleting an entry with a certain id from a table.
     * @param id - delete criteria
     */
    void delete(Long id);
}
