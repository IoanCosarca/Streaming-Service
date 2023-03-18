package com.example.demo.Service;

import com.example.demo.Model.Video;
import com.example.demo.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository)
    {
        this.videoRepository = videoRepository;
    }

    public Video getVideoByName(String name) {
        return videoRepository.findByName(name);
    }

    public List<Video> getVideosByChannel(String channel) {
        return videoRepository.findAllByChannel(channel);
    }

    public List<Video> getVideosByGenre(String genre) {
        return videoRepository.findAllByGenre(genre);
    }

    public List<Video> getVideosByStartHour(int startHour) {
        return videoRepository.findAllByStartHour(startHour);
    }

    public void deleteVideoByID(Long id) {
        videoRepository.deleteByID(id);
    }

    public void deleteVideoByName(String name) {
        videoRepository.deleteByName(name);
    }
}
