package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Create a new project
    public Project createProject(Project project, Member admin) {
        project.setAdmin(admin);
        return projectRepository.save(project);
    }

    // Get projects by admin
    public List<Project> getProjectsByAdmin(Member admin) {
        return projectRepository.findByAdmin(admin);
    }

    // Search projects by name
    public List<Project> searchProjectsByName(String name) {
        return projectRepository.findByNameContainingIgnoreCase(name);
    }
}
