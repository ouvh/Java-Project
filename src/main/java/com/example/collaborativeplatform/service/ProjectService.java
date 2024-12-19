package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.repository.ChatGroupRepository;
import com.example.collaborativeplatform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    // Create a new project
    public Project createProject(Project project, Member admin) {
       // Set the admin and add them to the project's member list
        project.setAdmin(admin);
        project.getMembers().add(admin);

        // Save the project first
        Project savedProject = projectRepository.save(project);

        // Create and save the ChatGroup, associating it with the saved project
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setProject(savedProject);
        chatGroupRepository.save(chatGroup);

        // Set the saved ChatGroup back to the project
        savedProject.setChatGroup(chatGroup);

        // Update the project with the ChatGroup
        return projectRepository.save(savedProject);

    }

    // Get projects by admin
    public List<Project> getProjectsByAdmin(Member admin) {
        return projectRepository.findByAdmin(admin);
    }

    // Search projects by name
    public List<Project> searchProjectsByName(String name) {
        return projectRepository.findByNameContainingIgnoreCase(name);
    }

    public Project addMemberToProject(Project project, Member member) {
        // Add the member to the project's joinedMembers list
        project.getMembers().add(member);
        // Save the updated project
        return projectRepository.save(project);
    }

    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }
}
