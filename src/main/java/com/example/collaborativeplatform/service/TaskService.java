package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.project.Task;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create a new task
    public Task createTask(Task task, Project project) {
        task.setProject(project);
        return taskRepository.save(task);
    }

    // Assign a task to a member
    public Task assignTask(Task task, Member member) {
        task.setAssignedTo(member);
        return taskRepository.save(task);
    }

    // Get tasks by project
    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    // Get tasks by status
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }
}
