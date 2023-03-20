package com.example.demo.Controller;

import com.example.demo.Model.Video;
import com.example.demo.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls the operations on the Video table, not how they are implemented.
 */
@RestController
public class VideoController {
    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService)
    {
        this.videoService = videoService;
    }

    /**
     * Returns all the Videos in the table.
     * @return List[Video]
     */
    @GetMapping("/getVideos")
    public List<Video> getVideos() {
        return videoService.getVideos();
    }

    /**
     * Returns the Video with the specified name
     * @param name - search criteria
     * @return Video
     */
    @GetMapping("/getVideoByName/{name}")
    public Video getVideoByName(@PathVariable String name) {
        return videoService.getVideoByName(name);
    }

    /**
     * Returns all the Videos that were posted by a certain channel.
     * @param channel - search criteria
     * @return List[Video]
     */
    @GetMapping("/getVideosByChannel/{channel}")
    public List<Video> getVideosByChannel(@PathVariable String channel) {
        return videoService.getVideosByChannel(channel);
    }

    /**
     * Returns all the Videos that are of a specified genre.
     * @param genre - search criteria
     * @return List[Video]
     */
    @GetMapping("/getVideosByGenre/{genre}")
    public List<Video> getVideosByGenre(@PathVariable String genre) {
        return videoService.getVideosByGenre(genre);
    }

    /**
     * Returns all the Videos that start at a certain hour.
     * @param startHour - search criteria
     * @return List[Video]
     */
    @GetMapping("/getVideosByHour/{startHour}")
    public List<Video> getVideosByStartHour(@PathVariable int startHour) {
        return videoService.getVideosByStartHour(startHour);
    }

    /**
     * Adds a new Video to the database.
     * @param video - the new instance to be added in the table
     */
    @PostMapping("/addVideo")
    public void addVideo(@RequestBody Video video) {
        System.out.println(video.toString());
        videoService.saveVideo(video);
    }

    /**
     * Updates the details of a Video in the database.
     * @param video - the instance containing all the modified values to be added in the database
     */
    @PutMapping("/updateVideos")
    public void updateVideos(@RequestBody Video video) {
        videoService.updateVideos(video);
    }

    /**
     * Deletes the Video with the specified id.
     * @param id - delete criteria
     */
    @DeleteMapping("/deleteVideoByID/{id}")
    public void deleteVideoByID(@PathVariable Long id) {
        videoService.deleteVideoByID(id);
    }

    /**
     * Deletes the Video with the specified name.
     * @param name - delete criteria
     */
    @DeleteMapping("/deleteVideoByName/{name}")
    public void deleteVideoByName(@PathVariable String name) {
        videoService.deleteVideoByName(name);
    }
}
