package com.example.demo.Repository;

import com.example.demo.Model.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
    Video findByName(String name);

    List<Video> findAllByName(String name);

    List<Video> findAllByChannel(String channel);

    List<Video> findAllByGenre(String genre);

    List<Video> findAllByStartHour(int startHour);

    Video deleteByID(Long id);
}
