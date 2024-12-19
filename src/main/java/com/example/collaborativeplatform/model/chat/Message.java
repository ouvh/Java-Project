package com.example.collaborativeplatform.model.chat;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.user.Member;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime timestamp;

    @ManyToOne
    @JsonIgnore
    private Member sender;

    @ManyToOne
    @JsonIgnore
    private ChatGroup chatGroup;

    @PrePersist
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    @JsonGetter("chatGroupId")  // Only serialize the chatgroup id
    public Long getChatGroupId() {
        return chatGroup.getId();
    }

   
    @JsonGetter("senderInfo")  // Only serialize the sender id
    public Map<String, Object> getSenderInfo() {
        Map<String, Object> info = new HashMap<>();
            info.put("id", sender.getId());
            info.put("handle", sender.getHandle());
            info.put("profileImage", sender.getProfileImage());
        return info;
    }

}
