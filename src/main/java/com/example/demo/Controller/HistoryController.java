package com.example.demo.Controller;

import com.example.demo.Model.History;
import com.example.demo.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls the operations on the History table, not how they are implemented.
 */
@RestController
@RequestMapping(path = "api")
public class HistoryController {
    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    /**
     * Returns all the History instances in the table.
     * @return List[History]
     */
    @GetMapping("/getHistory")
    public List<History> getHistory() {
        return historyService.getHistory();
    }

    /**
     * Returns a list representing a User's watch history.
     * @param userID id of the user whose history must be shown
     * @return List[History]
     */
    @GetMapping("/allVideosWatchedByThisUser/{userID}")
    public List<History> allVideosWatchedByThisUser(@PathVariable Long userID) {
        return historyService.allVideosWatchedByThisUser(userID);
    }

    /**
     * Adds a new entry in the History table.
     * @param history the entry to be added
     */
    @PostMapping("/addHistory")
    public void addHistory(@RequestBody History history) {
        historyService.saveHistory(history);
    }

    /**
     * Deletes the entire history of a specified user.
     * @param userID id of the user whose watch history must be deleted
     */
    @DeleteMapping("/deleteUserHistory/{userID}")
    public void deleteUserHistory(@PathVariable Long userID) {
        historyService.deleteUserHistory(userID);
    }
}
