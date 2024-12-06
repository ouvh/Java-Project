package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.chat.Message;
import com.example.collaborativeplatform.model.chat.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatGroup(ChatGroup chatGroup);
}
