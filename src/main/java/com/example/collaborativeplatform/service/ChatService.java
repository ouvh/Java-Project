package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.chat.Message;
import com.example.collaborativeplatform.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    
    // Post a message
    public Message postMessage(Message message) {
        return messageRepository.save(message);
    }

    // Retrieve messages by chat group
    public List<Message> getMessagesByChatGroup(ChatGroup chatGroup) {
        return messageRepository.findByChatGroup(chatGroup);
    }
}
