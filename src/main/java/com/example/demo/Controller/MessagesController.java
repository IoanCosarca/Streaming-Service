package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls the operations on the Messages table, not how they are implemented.
 */
@RestController
@RequestMapping(path = "api")
public class MessagesController {
    private MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    /**
     * Returns all the messages that the specified user has received.
     * @param userID search criteria
     * @return List[Message]
     */
    @GetMapping("/getUserMessages/{userID}")
    public List<Message> getUserMessages(@PathVariable Long userID) {
        return messagesService.getUserMessages(userID);
    }

    /**
     * Adds a new Message to the database.
     * @param message the new instance to be added in the table
     */
    @PostMapping("/addMessage")
    public void addMessage(@RequestBody Message message) {
        messagesService.saveMessage(message);
    }

}
