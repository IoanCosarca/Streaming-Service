package com.example.demo.Controller;

import com.example.demo.Model.History;
import com.example.demo.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistoryController {
    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    @GetMapping("/getHistory")
    public List<History> getHistory() {
        return historyService.getHistory();
    }

    @GetMapping("/allVideosWatchedByThisUser/{userID}")
    public List<History> allVideosWatchedByThisUser(@PathVariable Long userID) {
        return historyService.allVideosWatchedByThisUser(userID);
    }

    @GetMapping("/allUsersWatchingThisVideo/{videoID}")
    public List<History> allUsersWatchingThisVideo(@PathVariable Long videoID) {
        return historyService.allUsersWatchingThisVideo(videoID);
    }

    @PostMapping("/addHistory")
    public void addHistory(@RequestBody History history) {
        historyService.saveHistory(history);
    }

    @PutMapping("/updateHistory")
    public void updateHistory(@RequestBody History history) {
        historyService.updateHistory(history);
    }

    @DeleteMapping("/deleteHistoryByID/{id}")
    public void deleteHistoryByID(@PathVariable Long id) {
        historyService.deleteHistoryByID(id);
    }

    @DeleteMapping("/deleteUserHistory/{userID}")
    public void deleteUserHistory(@PathVariable Long userID) {
        historyService.deleteUserHistory(userID);
    }
}
