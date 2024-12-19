package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.project.File;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.FileService;
import com.example.collaborativeplatform.service.ProjectService;
import com.example.collaborativeplatform.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // OK
    // Add a file to a project
    @PostMapping("/projects/{projectId}")
    public ResponseEntity<?> addFileToProject(
            @PathVariable Long projectId,
            @RequestBody File file,
            @RequestParam Long uploadedById // ID of the member uploading the file
    ) {
        // Validate project existence
        Project project = projectService.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.badRequest().body("Project with ID " + projectId + " not found");
        }

        // Validate member existence
        Member uploadedBy = userService.getUserById(uploadedById).orElse(null);
        if (uploadedBy == null) {
            return ResponseEntity.badRequest().body("Member with ID " + uploadedById + " not found");
        }


        // Set the project and uploadedBy in the file object
        file.setProject(project);
        file.setUploadedBy(uploadedBy);

        // Save the file
        File savedFile = fileService.saveFile(file);
        return ResponseEntity.ok(savedFile);
    }

    // OK
    // List all files for a project
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<?> getFilesForProject(@PathVariable Long projectId) {
        // Validate project existence
        Project project = projectService.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.badRequest().body("Project with ID " + projectId + " not found");
        }
        // Fetch files associated with the project
        List<File> files = project.getFiles();
        return ResponseEntity.ok(files);
    }

    // OK
    // Get a file by its ID
    @GetMapping("/{fileId}")
    public ResponseEntity<File> getFileById(@PathVariable Long fileId) {
        File file = fileService.getFileById(fileId);
        return ResponseEntity.ok(file);
    }
}
