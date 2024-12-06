package com.example.collaborativeplatform.model.chat;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.user.Member;

import java.time.LocalDateTime;

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
    private Member sender;

    @ManyToOne
    private ChatGroup chatGroup;

    @PrePersist
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }


}
