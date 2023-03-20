package com.example.demo.Service;

import com.example.demo.DAO.VideoDAO;
import com.example.demo.Model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that insures the link between the application methods and the database.
 */
@Service
public class VideoService {
    private VideoDAO dao;

    @Autowired
    public VideoService(VideoDAO dao)
    {
        this.dao = dao;
    }

    /**
     * Sends the request to get all the Videos from the database.
     * @return List[Videos]
     */
    public List<Video> getVideos() {
        return dao.getAll();
    }

    /**
     * Sends the request to get the Video with the specified name.
     * @param name - search criteria
     * @return Video
     */
    public Video getVideoByName(String name) {
        return dao.findByName(name);
    }

    /**
     * Sends the request to get all the Videos posted by a specified channel.
     * @param channel - search criteria
     * @return List[Video]
     */
    public List<Video> getVideosByChannel(String channel) {
        return dao.findAllByChannel(channel);
    }

    /**
     * Sends the request to get all the Videos of a specified genre.
     * @param genre - search criteria
     * @return List[Video]
     */
    public List<Video> getVideosByGenre(String genre) {
        return dao.findAllByGenre(genre);
    }

    /**
     * Sends the request to get all the Videos that start at a specified hour.
     * @param startHour - search criteria
     * @return List[Video]
     */
    public List<Video> getVideosByStartHour(int startHour) {
        return dao.findAllByStartHour(startHour);
    }

    /**
     * Sends the Video to be added to the database.
     * @param video - the entry to be added
     */
    public void saveVideo(Video video) {
        dao.save(video);
    }

    /**
     * Sends the new Video object to update the database.
     * @param video - the entry containing the new information
     */
    public void updateVideos(Video video) {
        dao.update(video);
    }

    /**
     * Sends the id of the Video to be deleted from the database.
     * @param id - delete criteria
     */
    public void deleteVideoByID(Long id) {
        dao.delete(id);
    }

    /**
     * Given a name, sends the id of the Video who has that name to the database to be deleted.
     * @param name - the name of the video to be deleted
     */
    public void deleteVideoByName(String name)
    {
        Video video = dao.findByName(name);
        dao.delete(video.getId());
    }
}
