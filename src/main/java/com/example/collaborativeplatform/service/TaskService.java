package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.project.Task;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task saveTask(Task task) {
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

     // Get tasks by idgetTaskById
     public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
