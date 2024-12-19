
package com.example.collaborativeplatform.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.repository.ChatGroupRepository;

@Service
public class ChatGroupService {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    public Optional<ChatGroup> findById(Long id){
        return chatGroupRepository.findById(id);
    };

    public Optional<ChatGroup> findByProject(Project project){
        return chatGroupRepository.findByProject(project);
    };
    
}
