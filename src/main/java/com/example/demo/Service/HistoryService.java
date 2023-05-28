package com.example.demo.Service;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that insures the link between the application methods and the database.
 */
@Service
public class HistoryService {
    private DAO<History> dao;

    @Autowired
    public HistoryService(DAO<History> dao)
    {
        this.dao = dao;
    }

    /**
     * Sends the request to get the entire History from the database.
     * @return List[History]
     */
    public List<History> getHistory() {
        return dao.getAll();
    }

    /**
     * Sends a request to get all the entries of a user with a specified id.
     * @param userID the id of the user whose history must be shown
     * @return List[History]
     */
    public List<History> allVideosWatchedByThisUser(Long userID) {
        return dao.findAllByUserID(userID);
    }

    /**
     * Sends the new History entry to be added to the database.
     * @param history the entry to be added
     */
    public void saveHistory(History history) {
        dao.save(history);
    }

    /**
     * Sends the id of the user whose watch history must be deleted.
     * @param userID delete criteria
     */
    public void deleteUserHistory(Long userID) {
        dao.delete(userID);
    }
}
