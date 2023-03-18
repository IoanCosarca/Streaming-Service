package com.example.demo.Repository;

import com.example.demo.Model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
    History findAllByUserID(Long userID);

    History findAllByVideoID(Long videoID);

    History deleteByUserID(Long userID);
}
