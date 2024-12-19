package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.ProjectService;
import com.example.collaborativeplatform.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userservice; 


    public ProjectController(ProjectService projectService,UserService userservice) {
        this.projectService = projectService;
        this.userservice = userservice;
    }

    // OK
    // Create a new project
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody Project project, @RequestParam Long adminId) {
         try {
            // Fetch the admin using userservice
            Optional<Member> adminOptional = userservice.getUserById(adminId);

            if (adminOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID " + adminId + " not found.");
            }

            Member admin = adminOptional.get();

            // Create the project using the fetched admin
            Project createdProject = projectService.createProject(project, admin);

            return ResponseEntity.ok(createdProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while creating the project: " + e.getMessage());
        }




    }

    // OK
    // Get projects by admin
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Project>> getProjectsByAdmin(@PathVariable Long adminId) {
        Member admin = new Member();
        admin.setId(adminId); // Simulate finding the admin
        List<Project> projects = projectService.getProjectsByAdmin(admin);
        return ResponseEntity.ok(projects);
    }

    // OK
    // Search projects by name
    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjectsByName(@RequestParam String name) {
        List<Project> projects = projectService.searchProjectsByName(name);
        return ResponseEntity.ok(projects);
    }

    // OK
    // add a new member
    @PostMapping("/addMembers")
    public ResponseEntity<Project> addMemberToProject(
        @RequestBody Long projectId,
        @RequestParam Long memberId
    ) {

        
        

        // Find the project by ID
        Project project = projectService.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.notFound().build(); // Return 404 if project not found
        }

        // Find the member by ID
        Member member = userservice.getUserById(memberId).orElse(null); 
        if (member == null) {
            return ResponseEntity.badRequest().body(null); // Return 400 if member not found
        }

        // Add member to project
        Project updatedProject = projectService.addMemberToProject(project, member);

        return ResponseEntity.ok(updatedProject);
    }


    // OK
    @GetMapping("/{id}")
    public ResponseEntity<Project> getUserById(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    

}
