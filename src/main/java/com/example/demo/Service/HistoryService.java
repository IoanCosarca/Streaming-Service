package com.example.demo.Service;

import com.example.demo.DAO.HistoryDAO;
import com.example.demo.Model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private HistoryDAO dao;

    @Autowired
    public HistoryService(HistoryDAO dao)
    {
        this.dao = dao;
    }

    public List<History> getHistory() {
        return dao.getAll();
    }

    public List<History> allVideosWatchedByThisUser(Long userID) {
        return dao.findAllByUserID(userID);
    }

    public List<History> allUsersWatchingThisVideo(Long videoID) {
        return dao.findAllByVideoID(videoID);
    }

    public void saveHistory(History history) {
        dao.save(history);
    }

    public void updateHistory(History history) {
        dao.update(history);
    }

    public void deleteHistoryByID(Long id) {
        dao.delete(id);
    }

    public void deleteUserHistory(Long userID) {
        dao.delete(userID);
    }
}
