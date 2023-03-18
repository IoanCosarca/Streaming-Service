package com.example.demo.Service;

import com.example.demo.Model.History;
import com.example.demo.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository)
    {
        this.historyRepository = historyRepository;
    }

    public List<History> getHistory() {
        return historyRepository.findAll();
    }

    public List<History> allVideosWatchedByThisUser(Long userID) {
        return historyRepository.findAllByUserID(userID);
    }

    public List<History> allUsersWatchingThisVideo(Long videoID) {
        return historyRepository.findAllByVideoID(videoID);
    }

    public void saveHistory(History history) {
        historyRepository.save(history);
    }

    public void deleteUserHistory(Long userID)
    {
        List<History> histories = historyRepository.findAllByUserID(userID);
        historyRepository.deleteAll(histories);
    }
}
