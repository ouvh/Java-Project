package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByAdmin(Admin admin);
    List<Project> findByNameContainingIgnoreCase(String name);
}
