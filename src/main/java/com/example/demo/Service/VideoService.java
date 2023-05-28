package com.example.demo.Service;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that insures the link between the application methods and the database.
 */
@Service
public class VideoService {
    private DAO<Video> dao;

    @Autowired
    public VideoService(DAO<Video> dao)
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
     * Sends the request to get the Video with the specified id.
     * @param id search criteria
     * @return Client
     */
    public Video getVideoByID(Long id) {
        return dao.findByID(id);
    }

    /**
     * Sends the request to get the Video with the specified name.
     * @param name search criteria
     * @return Video
     */
    public List<Video> getVideoByName(String name) {
        return dao.findByName(name);
    }

    /**
     * Sends the request to get all the Videos posted by a specified channel.
     * @param channel search criteria
     * @return List[Video]
     */
    public List<Video> getVideosByChannel(String channel) {
        return dao.findAllByChannel(channel);
    }

    /**
     * Sends the request to get all the Videos of a specified genre.
     * @param genre search criteria
     * @return List[Video]
     */
    public List<Video> getVideosByGenre(String genre) {
        return dao.findAllByGenre(genre);
    }

    /**
     * Sends the request to get all the Videos that start at a specified hour.
     * @param startHour search criteria
     * @return List[Video]
     */
    public List<Video> getVideosByStartHour(int startHour) {
        return dao.findAllByStartHour(startHour);
    }

    /**
     * Sends the Video to be added to the database.
     * @param video the entry to be added
     */
    public void saveVideo(Video video) {
        dao.save(video);
    }

    /**
     * Sends the new Video object to update the database.
     * @param video the entry containing the new information
     */
    public void updateVideo(Video video) {
        dao.update(video);
    }

    /**
     * Sends the id of the Video to be deleted from the database.
     * @param id delete criteria
     */
    public void deleteVideoByID(Long id) {
        dao.delete(id);
    }

}
