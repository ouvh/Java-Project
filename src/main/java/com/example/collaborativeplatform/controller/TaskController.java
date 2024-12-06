package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.project.Task;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a new task
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestParam Long projectId) {
        Project project = new Project();
        project.setId(projectId); // Simulate finding the project
        Task createdTask = taskService.createTask(task, project);
        return ResponseEntity.ok(createdTask);
    }

    // Assign task to member
    @PostMapping("/assign")
    public ResponseEntity<Task> assignTask(@RequestParam Long taskId, @RequestParam Long memberId) {
        Task task = new Task();
        task.setId(taskId); // Simulate finding the task
        Member member = new Member();
        member.setId(memberId); // Simulate finding the member
        Task assignedTask = taskService.assignTask(task, member);
        return ResponseEntity.ok(assignedTask);
    }

    // Get tasks by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        Project project = new Project();
        project.setId(projectId); // Simulate finding the project
        List<Task> tasks = taskService.getTasksByProject(project);
        return ResponseEntity.ok(tasks);
    }
}
