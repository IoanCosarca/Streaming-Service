package com.example.demo.Service;

import com.example.demo.DAO.VideoDAO;
import com.example.demo.Model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private VideoDAO dao;

    @Autowired
    public VideoService(VideoDAO dao)
    {
        this.dao = dao;
    }

    public List<Video> getVideos() {
        return dao.getAll();
    }

    public Video getVideoByName(String name) {
        return dao.findByName(name);
    }

    public List<Video> getVideosByChannel(String channel) {
        return dao.findAllByChannel(channel);
    }

    public List<Video> getVideosByGenre(String genre) {
        return dao.findAllByGenre(genre);
    }

    public List<Video> getVideosByStartHour(int startHour) {
        return dao.findAllByStartHour(startHour);
    }

    public void saveVideo(Video video) {
        dao.save(video);
    }

    public void updateVideos(Video video) {
        dao.update(video);
    }

    public void deleteVideoByID(Long id) {
        dao.delete(id);
    }

    public void deleteVideoByName(String name)
    {
        Video video = dao.findByName(name);
        dao.delete(video.getId());
    }
}
