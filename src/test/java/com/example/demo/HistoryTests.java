package com.example.demo;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.History;
import com.example.demo.Service.HistoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HistoryTests {
    @Mock
    private DAO<History> dao;

    @Test
    void testGetAll()
    {
        List<History> histories = new ArrayList<>();
        History history1 = new History();
        History history2 = new History();
        histories.add(history1);
        histories.add(history2);
        HistoryService historyService = new HistoryService(dao);
        historyService.getHistory();
        when (dao.getAll()).thenReturn(histories);
        verify(dao).getAll();
    }

    @Test
    void testFindByUserID()
    {
        List<History> histories = new ArrayList<>();
        HistoryService historyService = new HistoryService(dao);
        historyService.allVideosWatchedByThisUser(1L);
        when (dao.findAllByUserID(1L)).thenReturn(histories);
        verify(dao).findAllByUserID(1L);
    }

    @Test
    void testSave()
    {
        Calendar h = Calendar.getInstance();
        String date = h.get(Calendar.YEAR) + "-" + (h.get(Calendar.MONTH) + 1) + "-" + h.get(Calendar.DAY_OF_MONTH);
        String time = h.get(Calendar.HOUR_OF_DAY) + ":" + h.get(Calendar.MINUTE) + ":" + h.get(Calendar.SECOND);
        History history = new History(3L, 5L, date, time);
        HistoryService historyService = new HistoryService(dao);
        historyService.saveHistory(history);
        verify(dao).save(history);
    }

    @Test
    void testDeleteByUserID()
    {
        HistoryService historyService = new HistoryService(dao);
        historyService.deleteUserHistory(1L);
        verify(dao).delete(1L);
    }
}
