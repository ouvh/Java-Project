package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.chat.Message;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.ChatGroupService;
import com.example.collaborativeplatform.service.ChatService;
import com.example.collaborativeplatform.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;
    private final ChatGroupService chatGroupService;
    private final UserService userService;



    public ChatController(ChatService chatService,ChatGroupService chatGroupService,UserService userService) {
        this.chatService = chatService;
        this.chatGroupService = chatGroupService;
        this.userService = userService;
    }

    // dont forget to initialize the chatgroup when creating the project;
    // Post a message
    // OK 
    @PostMapping("/message")
    public ResponseEntity<?> postMessage(@RequestBody Message message, @RequestParam Long chatGroupId,@RequestParam Long senderId) {


        // Validate chatGroup existence
        ChatGroup chatGroup = chatGroupService.findById(chatGroupId).orElse(null);
        if (chatGroup == null) {
            return ResponseEntity.badRequest().body("ChatGroup with ID " + chatGroupId + " not found");
        }

        // Validate member existence
        Member sender = userService.getUserById(senderId).orElse(null);
        if (sender == null) {
            return ResponseEntity.badRequest().body("Member with ID " + senderId + " not found");
        }

        message.setChatGroup(chatGroup);
        message.setSender(sender);


        Message postedMessage = chatService.postMessage(message);
        return ResponseEntity.ok(postedMessage);

    }

    // OK
    // Get messages by chat group
    @GetMapping("/messages/{chatGroupId}")
    public ResponseEntity<?> getMessagesByChatGroup(@PathVariable Long chatGroupId) {

        // Validate chatGroup existence
        ChatGroup chatGroup = chatGroupService.findById(chatGroupId).orElse(null);
        if (chatGroup == null) {
            return ResponseEntity.badRequest().body("ChatGroup with ID " + chatGroupId + " not found");
        }

        List<Message> messages = chatService.getMessagesByChatGroup(chatGroup);
        return ResponseEntity.ok(messages);

    }




}
