package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Message;
import com.lms.librarymanagementsystem.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
