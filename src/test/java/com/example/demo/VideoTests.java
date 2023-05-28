package com.example.demo;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Video;
import com.example.demo.Model.VideoGenre;
import com.example.demo.Service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VideoTests {
    @Mock
    private DAO<Video> dao;

    @Test
    void testGetAll()
    {
        List<Video> videos = new ArrayList<>();
        Video video1 = new Video();
        Video video2 = new Video();
        videos.add(video1);
        videos.add(video2);
        VideoService videoService = new VideoService(dao);
        videoService.getVideos();
        when (dao.getAll()).thenReturn(videos);
        verify(dao).getAll();
    }

    @Test
    void testFindByName()
    {
        List<Video> video = new ArrayList<>();
        VideoService videoService = new VideoService(dao);
        videoService.getVideoByName("BZRP");
        when (dao.findByName("BZRP")).thenReturn(video);
        verify(dao).findByName("BZRP");
    }

    @Test
    void testFindByChannel()
    {
        List<Video> videos = new ArrayList<>();
        VideoService videoService = new VideoService(dao);
        videoService.getVideosByChannel("Cracked");
        when (dao.findAllByChannel("Cracked")).thenReturn(videos);
        verify(dao).findAllByChannel("Cracked");
    }

    @Test
    void testFindByGenre()
    {
        List<Video> videos = new ArrayList<>();
        VideoService videoService = new VideoService(dao);
        videoService.getVideosByGenre("Music");
        when (dao.findAllByGenre("Music")).thenReturn(videos);
        verify(dao).findAllByGenre("Music");
    }

    @Test
    void testFindByStart()
    {
        List<Video> videos = new ArrayList<>();
        VideoService videoService = new VideoService(dao);
        videoService.getVideosByStartHour(15);
        when (dao.findAllByStartHour(15)).thenReturn(videos);
        verify(dao).findAllByStartHour(15);
    }

    @Test
    void testSave()
    {
        Video video = new Video();
        VideoService videoService = new VideoService(dao);
        videoService.saveVideo(video);
        verify(dao).save(video);
    }

    @Test
    void testUpdate()
    {
        Video video = new Video();
        VideoService videoService = new VideoService(dao);
        videoService.updateVideo(video);
        verify(dao).update(video);
    }

    @Test
    void testDelete()
    {
        VideoService videoService = new VideoService(dao);
        videoService.deleteVideoByID(1L);
        verify(dao).delete(1L);
    }
}
