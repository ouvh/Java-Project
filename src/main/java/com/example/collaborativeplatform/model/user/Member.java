package com.example.collaborativeplatform.model.user;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.project.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Member extends User {
    @ManyToMany(mappedBy = "members")
    private List<Project> joinedProjects = new ArrayList<>();

    public List<Project> getJoinedProjects() {
        return joinedProjects;
    }

    public void setJoinedProjects(List<Project> joinedProjects) {
        this.joinedProjects = joinedProjects;
    }

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Project> createdProjects = new ArrayList<>();


    public List<Project> getCreatedProjects() {
        return createdProjects;
    }

    public void setCreatedProjects(List<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }

  
}

















































































