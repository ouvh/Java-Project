package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.chat.Message;
import com.example.collaborativeplatform.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Post a message
    @PostMapping("/message")
    public ResponseEntity<Message> postMessage(@RequestBody Message message, @RequestParam Long chatGroupId) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setId(chatGroupId); // Simulate finding the chat group
        Message postedMessage = chatService.postMessage(message, chatGroup);
        return ResponseEntity.ok(postedMessage);
    }

    // Get messages by chat group
    @GetMapping("/messages/{chatGroupId}")
    public ResponseEntity<List<Message>> getMessagesByChatGroup(@PathVariable Long chatGroupId) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setId(chatGroupId); // Simulate finding the chat group
        List<Message> messages = chatService.getMessagesByChatGroup(chatGroup);
        return ResponseEntity.ok(messages);
    }
}
