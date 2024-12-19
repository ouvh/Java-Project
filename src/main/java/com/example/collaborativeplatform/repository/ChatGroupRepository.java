package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.project.Project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, Long> {
    Optional<ChatGroup> findByProject(Project project);
    Optional<ChatGroup> findById(Long id);

}
