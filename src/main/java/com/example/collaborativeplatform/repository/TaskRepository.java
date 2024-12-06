package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.project.Task;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
    List<Task> findByDeadlineBefore(java.util.Date deadline);
    List<Task> findByProject(Project project);
    List<Task> findByAssignedTo(Member member);
}
