package com.example.demo.Repository;

import com.example.demo.Model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Override
    List<History> findAll();

    List<History> findAllByUserID(Long userID);

    List<History> findAllByVideoID(Long videoID);

    @Override
    <S extends History> S save(S entity);

    @Override
    void deleteAll(Iterable<? extends History> entities);
}
