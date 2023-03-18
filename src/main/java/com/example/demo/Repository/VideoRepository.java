package com.example.demo.Repository;

import com.example.demo.Model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Override
    List<Video> findAll();

    @Override
    Optional<Video> findById(Long aLong);

    Video findByName(String name);

    List<Video> findAllByChannel(String channel);

    List<Video> findAllByGenre(String genre);

    List<Video> findAllByStartHour(int startHour);

    @Override
    <S extends Video> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
