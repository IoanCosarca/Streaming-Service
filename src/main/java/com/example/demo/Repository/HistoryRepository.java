package com.example.demo.Repository;

import com.example.demo.Model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
    List<History> findAllByUserID(Long userID);

    List<History> findAllByVideoID(Long videoID);

    void deleteByUserID(Long userID);

    //History deleteByVideoID(Long videoID);
}
