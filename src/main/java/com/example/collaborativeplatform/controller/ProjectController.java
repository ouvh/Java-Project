package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create a new project
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project, @RequestParam Long adminId) {
        Member admin = new Member();
        admin.setId(adminId); // Simulate finding the admin
        Project createdProject = projectService.createProject(project, admin);
        return ResponseEntity.ok(createdProject);
    }

    // Get projects by admin
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Project>> getProjectsByAdmin(@PathVariable Long adminId) {
        Member admin = new Member();
        admin.setId(adminId); // Simulate finding the admin
        List<Project> projects = projectService.getProjectsByAdmin(admin);
        return ResponseEntity.ok(projects);
    }

    // Search projects by name
    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjectsByName(@RequestParam String name) {
        List<Project> projects = projectService.searchProjectsByName(name);
        return ResponseEntity.ok(projects);
    }
}
