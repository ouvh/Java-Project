package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.project.Task;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.TaskService;
import com.example.collaborativeplatform.service.ProjectService;
import com.example.collaborativeplatform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    // Constructor injection for repositories
    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    // OK
    // Create a new task and assign it to a project and member
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task, @RequestParam Long projectId, @RequestParam Long memberId) {
        // Fetch the project by ID
        Project project = projectService.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.badRequest().body("Project with ID " + projectId + " not found");
        }

        task.setProject(project); // Set the project in the task

        // Fetch the member by ID and assign the task
        Member member = userService.getUserById(memberId).orElse(null);
        if (member == null) {
            return ResponseEntity.badRequest().body("Member with ID " + memberId + " not found");
        }

        task.setAssignedTo(member);

        // Create the task
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    // OK
    // Update task details (e.g., description, deadline, status)
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody Task taskUpdates) {
        Task task = taskService.getTaskById(taskId).orElse(null);
        if (task == null) {
            return ResponseEntity.badRequest().body("Task with ID " + taskId + " not found");
        }

        // Only update fields that are not null
        if (taskUpdates.getDescription() != null) {
            task.setDescription(taskUpdates.getDescription());
        }
        if (taskUpdates.getDeadline() != null) {
            task.setDeadline(taskUpdates.getDeadline());
        }
        if (taskUpdates.getStatus() != null) {
            task.setStatus(taskUpdates.getStatus());
        }

        Task updatedTask = taskService.saveTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    // Get tasks by project
    // OK
    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getTasksByProject(@PathVariable Long projectId) {
        Project project = projectService.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.badRequest().body("Project with ID " + projectId + " not found");
        }

        List<Task> tasks = taskService.getTasksByProject(project);
        return ResponseEntity.ok(tasks);
    }
}
 