package com.example.demo.Service;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that insures the link between the application methods and the database.
 */
@Service
public class MessagesService {
    private DAO<Message> dao;

    @Autowired
    public MessagesService(DAO<Message> dao)
    {
        this.dao = dao;
    }

    /**
     * Sends the request to get all the messages that the user received.
     * @param userID search criteria
     * @return List[Message]
     */
    public List<Message> getUserMessages(Long userID) {
        return dao.findAllByUserID(userID);
    }

    /**
     * Sends the Message to be added to the database.
     * @param message the entry to be added
     */
    public void saveMessage(Message message) {
        dao.save(message);
    }
}
