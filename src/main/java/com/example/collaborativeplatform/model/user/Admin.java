package com.example.collaborativeplatform.model.user;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.project.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Admin extends User {
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Project> createdProjects = new ArrayList<>();

    public List<Project> getCreatedProjects() {
        return createdProjects;
    }

    public void setCreatedProjects(List<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }
}
