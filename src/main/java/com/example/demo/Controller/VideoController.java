package com.example.demo.Controller;

import com.example.demo.Model.Video;
import com.example.demo.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {
    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService)
    {
        this.videoService = videoService;
    }

    @GetMapping("/getVideos")
    public List<Video> getVideos() {
        return videoService.getVideos();
    }

    @GetMapping("/getVideo/{name}")
    public Video getVideoByName(@PathVariable String name) {
        return videoService.getVideoByName(name);
    }

    @GetMapping("/getVideos/{channel}")
    public List<Video> getVideosByChannel(@PathVariable String channel) {
        return videoService.getVideosByChannel(channel);
    }

    @GetMapping("/getVideos/{genre}")
    public List<Video> getVideosByGenre(@PathVariable String genre) {
        return videoService.getVideosByGenre(genre);
    }

    @GetMapping("/getVideos/{startHour}")
    public List<Video> getVideosByStartHour(@PathVariable int startHour) {
        return videoService.getVideosByStartHour(startHour);
    }

    @PostMapping("/addVideo")
    public void addVideoPost(@RequestBody Video video) {
        videoService.saveVideo(video);
    }

    @PutMapping("/updateVideos")
    public void updateVideos(@RequestBody Video video) {
        videoService.updateVideos(video);
    }

    @DeleteMapping("/deleteVideo/{id}")
    public void deleteVideoByID(@PathVariable Long id) {
        videoService.deleteVideoByID(id);
    }

    @DeleteMapping("/deleteVideo/{name}")
    public void deleteVideoByName(@PathVariable String name) {
        videoService.deleteVideoByName(name);
    }
}